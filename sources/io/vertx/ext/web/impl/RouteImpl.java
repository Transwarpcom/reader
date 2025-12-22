package io.vertx.ext.web.impl;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/impl/RouteImpl.class */
public class RouteImpl implements Route {
    private final RouterImpl router;
    private volatile RouteState state;
    private static final Pattern RE_OPERATORS_NO_STAR = Pattern.compile("([\\(\\)\\$\\+\\.])");
    private static final Pattern RE_TOKEN_SEARCH = Pattern.compile(":([A-Za-z][A-Za-z0-9_]*)");

    RouteImpl(RouterImpl router, int order) {
        this.router = router;
        this.state = new RouteState(this, order);
    }

    RouteImpl(RouterImpl router, int order, String path) {
        this(router, order);
        checkPath(path);
        setPath(path);
    }

    RouteImpl(RouterImpl router, int order, HttpMethod method, String path) {
        this(router, order);
        method(method);
        checkPath(path);
        setPath(path);
    }

    RouteImpl(RouterImpl router, int order, String regex, boolean bregex) {
        this(router, order);
        setRegex(regex);
    }

    RouteImpl(RouterImpl router, int order, HttpMethod method, String regex, boolean bregex) {
        this(router, order);
        method(method);
        setRegex(regex);
    }

    RouteState state() {
        return this.state;
    }

    @Override // io.vertx.ext.web.Route
    public synchronized Route method(HttpMethod method) {
        this.state = this.state.addMethod(method);
        return this;
    }

    @Override // io.vertx.ext.web.Route
    public Route path(String path) {
        checkPath(path);
        setPath(path);
        return this;
    }

    @Override // io.vertx.ext.web.Route
    public Route pathRegex(String regex) {
        setRegex(regex);
        return this;
    }

    @Override // io.vertx.ext.web.Route
    public synchronized Route produces(String contentType) {
        this.state = this.state.addProduce(new ParsableMIMEValue(contentType).forceParse());
        return this;
    }

    @Override // io.vertx.ext.web.Route
    public synchronized Route consumes(String contentType) {
        this.state = this.state.addConsume(new ParsableMIMEValue(contentType).forceParse());
        return this;
    }

    @Override // io.vertx.ext.web.Route
    public synchronized Route order(int order) {
        if (this.state.isAdded()) {
            throw new IllegalStateException("Can't change order after route is active");
        }
        this.state = this.state.setOrder(order);
        return this;
    }

    @Override // io.vertx.ext.web.Route
    public Route last() {
        return order(Integer.MAX_VALUE);
    }

    @Override // io.vertx.ext.web.Route
    public synchronized Route handler(Handler<RoutingContext> contextHandler) {
        if (this.state.isExclusive()) {
            throw new IllegalStateException("This Route is exclusive for already mounted sub router.");
        }
        this.state = this.state.addContextHandler(contextHandler);
        checkAdd();
        return this;
    }

    @Override // io.vertx.ext.web.Route
    public Route blockingHandler(Handler<RoutingContext> contextHandler) {
        return blockingHandler(contextHandler, true);
    }

    @Override // io.vertx.ext.web.Route
    public synchronized Route subRouter(Router subRouter) {
        if (this.state.isExactPath()) {
            throw new IllegalStateException("Sub router cannot be mounted on an exact path.");
        }
        if (this.state.getPath() == null && this.state.getPattern() != null) {
            throw new IllegalStateException("Sub router cannot be mounted on a regular expression path.");
        }
        if (this.state.getContextHandlersLength() > 0 || this.state.getFailureHandlersLength() > 0) {
            throw new IllegalStateException("Only one sub router per Route object is allowed.");
        }
        subRouter.getClass();
        handler(subRouter::handleContext);
        subRouter.getClass();
        failureHandler(subRouter::handleFailure);
        subRouter.modifiedHandler(this::validateMount);
        validateMount(subRouter);
        this.state = this.state.setExclusive(true);
        return this;
    }

    @Override // io.vertx.ext.web.Route
    public Route blockingHandler(Handler<RoutingContext> contextHandler, boolean ordered) {
        return handler(new BlockingHandlerDecorator(contextHandler, ordered));
    }

    @Override // io.vertx.ext.web.Route
    public synchronized Route failureHandler(Handler<RoutingContext> exceptionHandler) {
        if (this.state.isExclusive()) {
            throw new IllegalStateException("This Route is exclusive for already mounted sub router.");
        }
        this.state = this.state.addFailureHandler(exceptionHandler);
        checkAdd();
        return this;
    }

    @Override // io.vertx.ext.web.Route
    public Route remove() {
        this.router.remove(this);
        return this;
    }

    @Override // io.vertx.ext.web.Route
    public synchronized Route disable() {
        this.state = this.state.setEnabled(false);
        return this;
    }

    @Override // io.vertx.ext.web.Route
    public synchronized Route enable() {
        this.state = this.state.setEnabled(true);
        return this;
    }

    @Override // io.vertx.ext.web.Route
    public synchronized Route useNormalisedPath(boolean useNormalisedPath) {
        this.state = this.state.setUseNormalisedPath(useNormalisedPath);
        return this;
    }

    @Override // io.vertx.ext.web.Route
    public String getPath() {
        return this.state.getPath();
    }

    @Override // io.vertx.ext.web.Route
    public boolean isRegexPath() {
        return this.state.getPattern() != null;
    }

    @Override // io.vertx.ext.web.Route
    public Set<HttpMethod> methods() {
        return this.state.getMethods();
    }

    @Override // io.vertx.ext.web.Route
    public synchronized Route setRegexGroupsNames(List<String> groups) {
        this.state = this.state.setGroups(groups);
        return this;
    }

    public String toString() {
        return "RouteImpl@" + System.identityHashCode(this) + "{state=" + this.state + '}';
    }

    RouterImpl router() {
        return this.router;
    }

    private synchronized void setPath(String path) {
        if (path.charAt(path.length() - 1) != '*') {
            this.state = this.state.setExactPath(true);
            this.state = this.state.setPath(path);
        } else {
            this.state = this.state.setExactPath(false);
            this.state = this.state.setPath(path.substring(0, path.length() - 1));
        }
        if (path.indexOf(58) != -1) {
            createPatternRegex(path);
        }
        this.state = this.state.setPathEndsWithSlash(this.state.getPath().endsWith("/"));
    }

    private synchronized void setRegex(String regex) {
        this.state = this.state.setPattern(Pattern.compile(regex));
        this.state = this.state.setExactPath(true);
        findNamedGroups(this.state.getPattern().pattern());
    }

    private synchronized void findNamedGroups(String path) {
        Matcher m = Pattern.compile("\\(\\?<([a-zA-Z][a-zA-Z0-9]*)>").matcher(path);
        while (m.find()) {
            this.state = this.state.addNamedGroupInRegex(m.group(1));
        }
    }

    private synchronized void createPatternRegex(String path) {
        String path2 = RE_OPERATORS_NO_STAR.matcher(path).replaceAll("\\\\$1");
        if (path2.charAt(path2.length() - 1) == '*') {
            path2 = path2.substring(0, path2.length() - 1) + "(?<rest>.*)";
            this.state = this.state.setExactPath(false);
        } else {
            this.state = this.state.setExactPath(true);
        }
        Matcher m = RE_TOKEN_SEARCH.matcher(path2);
        StringBuffer sb = new StringBuffer();
        List<String> groups = new ArrayList<>();
        int index = 0;
        while (m.find()) {
            String param = "p" + index;
            String group = m.group().substring(1);
            if (groups.contains(group)) {
                throw new IllegalArgumentException("Cannot use identifier " + group + " more than once in pattern string");
            }
            m.appendReplacement(sb, "(?<" + param + ">[^/]+)");
            groups.add(group);
            index++;
        }
        m.appendTail(sb);
        String path3 = sb.toString();
        this.state = this.state.setGroups(groups);
        this.state = this.state.setPattern(Pattern.compile(path3));
    }

    private void checkPath(String path) {
        if ("".equals(path) || path.charAt(0) != '/') {
            throw new IllegalArgumentException("Path must start with /");
        }
    }

    int order() {
        return this.state.getOrder();
    }

    private synchronized void checkAdd() {
        if (!this.state.isAdded()) {
            this.router.add(this);
            this.state = this.state.setAdded(true);
        }
    }

    public synchronized RouteImpl setEmptyBodyPermittedWithConsumes(boolean emptyBodyPermittedWithConsumes) {
        this.state = this.state.setEmptyBodyPermittedWithConsumes(emptyBodyPermittedWithConsumes);
        return this;
    }

    private void validateMount(Router router) {
        for (Route route : router.getRoutes()) {
            if (route.getPath() != null) {
                String combinedPath = RE_OPERATORS_NO_STAR.matcher(this.state.getPath() + (this.state.isPathEndsWithSlash() ? route.getPath().substring(1) : route.getPath())).replaceAll("\\\\$1");
                Matcher m = RE_TOKEN_SEARCH.matcher(combinedPath);
                Set<String> groups = new HashSet<>();
                while (m.find()) {
                    String group = m.group();
                    if (groups.contains(group)) {
                        throw new IllegalStateException("Cannot use identifier " + group + " more than once in pattern string");
                    }
                    groups.add(group);
                }
            }
        }
    }
}
