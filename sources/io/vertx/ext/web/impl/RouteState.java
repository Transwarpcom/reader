package io.vertx.ext.web.impl;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.net.impl.URIDecoder;
import io.vertx.ext.web.MIMEHeader;
import io.vertx.ext.web.RoutingContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/impl/RouteState.class */
final class RouteState {
    private final RouteImpl route;
    private final String path;
    private final int order;
    private final boolean enabled;
    private final Set<HttpMethod> methods;
    private final Set<MIMEHeader> consumes;
    private final boolean emptyBodyPermittedWithConsumes;
    private final Set<MIMEHeader> produces;
    private final List<Handler<RoutingContext>> contextHandlers;
    private final List<Handler<RoutingContext>> failureHandlers;
    private final boolean added;
    private final Pattern pattern;
    private final List<String> groups;
    private final boolean useNormalisedPath;
    private final Set<String> namedGroupsInRegex;
    private final Pattern virtualHostPattern;
    private final boolean pathEndsWithSlash;
    private final boolean exclusive;
    private final boolean exactPath;

    private RouteState(RouteImpl route, String path, int order, boolean enabled, Set<HttpMethod> methods, Set<MIMEHeader> consumes, boolean emptyBodyPermittedWithConsumes, Set<MIMEHeader> produces, List<Handler<RoutingContext>> contextHandlers, List<Handler<RoutingContext>> failureHandlers, boolean added, Pattern pattern, List<String> groups, boolean useNormalisedPath, Set<String> namedGroupsInRegex, Pattern virtualHostPattern, boolean pathEndsWithSlash, boolean exclusive, boolean exactPath) {
        this.route = route;
        this.path = path;
        this.order = order;
        this.enabled = enabled;
        this.methods = methods;
        this.consumes = consumes;
        this.emptyBodyPermittedWithConsumes = emptyBodyPermittedWithConsumes;
        this.produces = produces;
        this.contextHandlers = contextHandlers;
        this.failureHandlers = failureHandlers;
        this.added = added;
        this.pattern = pattern;
        this.groups = groups;
        this.useNormalisedPath = useNormalisedPath;
        this.namedGroupsInRegex = namedGroupsInRegex;
        this.virtualHostPattern = virtualHostPattern;
        this.pathEndsWithSlash = pathEndsWithSlash;
        this.exclusive = exclusive;
        this.exactPath = exactPath;
    }

    RouteState(RouteImpl route, int order) {
        this(route, null, order, true, null, null, false, null, null, null, false, null, null, true, null, null, false, false, false);
    }

    public RouteImpl getRoute() {
        return this.route;
    }

    public RouterImpl getRouter() {
        return this.route.router();
    }

    public String getPath() {
        return this.path;
    }

    RouteState setPath(String path) {
        return new RouteState(this.route, path, this.order, this.enabled, this.methods, this.consumes, this.emptyBodyPermittedWithConsumes, this.produces, this.contextHandlers, this.failureHandlers, this.added, this.pattern, this.groups, this.useNormalisedPath, this.namedGroupsInRegex, this.virtualHostPattern, this.pathEndsWithSlash, this.exclusive, this.exactPath);
    }

    public int getOrder() {
        return this.order;
    }

    RouteState setOrder(int order) {
        return new RouteState(this.route, this.path, order, this.enabled, this.methods, this.consumes, this.emptyBodyPermittedWithConsumes, this.produces, this.contextHandlers, this.failureHandlers, this.added, this.pattern, this.groups, this.useNormalisedPath, this.namedGroupsInRegex, this.virtualHostPattern, this.pathEndsWithSlash, this.exclusive, this.exactPath);
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    RouteState setEnabled(boolean enabled) {
        return new RouteState(this.route, this.path, this.order, enabled, this.methods, this.consumes, this.emptyBodyPermittedWithConsumes, this.produces, this.contextHandlers, this.failureHandlers, this.added, this.pattern, this.groups, this.useNormalisedPath, this.namedGroupsInRegex, this.virtualHostPattern, this.pathEndsWithSlash, this.exclusive, this.exactPath);
    }

    public Set<HttpMethod> getMethods() {
        return this.methods;
    }

    RouteState setMethods(Set<HttpMethod> methods) {
        return new RouteState(this.route, this.path, this.order, this.enabled, methods, this.consumes, this.emptyBodyPermittedWithConsumes, this.produces, this.contextHandlers, this.failureHandlers, this.added, this.pattern, this.groups, this.useNormalisedPath, this.namedGroupsInRegex, this.virtualHostPattern, this.pathEndsWithSlash, this.exclusive, this.exactPath);
    }

    public RouteState addMethod(HttpMethod method) {
        RouteState newState = new RouteState(this.route, this.path, this.order, this.enabled, this.methods == null ? new HashSet() : new HashSet(this.methods), this.consumes, this.emptyBodyPermittedWithConsumes, this.produces, this.contextHandlers, this.failureHandlers, this.added, this.pattern, this.groups, this.useNormalisedPath, this.namedGroupsInRegex, this.virtualHostPattern, this.pathEndsWithSlash, this.exclusive, this.exactPath);
        newState.methods.add(method);
        return newState;
    }

    public Set<MIMEHeader> getConsumes() {
        return this.consumes;
    }

    RouteState setConsumes(Set<MIMEHeader> consumes) {
        return new RouteState(this.route, this.path, this.order, this.enabled, this.methods, consumes, this.emptyBodyPermittedWithConsumes, this.produces, this.contextHandlers, this.failureHandlers, this.added, this.pattern, this.groups, this.useNormalisedPath, this.namedGroupsInRegex, this.virtualHostPattern, this.pathEndsWithSlash, this.exclusive, this.exactPath);
    }

    RouteState addConsume(MIMEHeader mime) {
        RouteState newState = new RouteState(this.route, this.path, this.order, this.enabled, this.methods, this.consumes == null ? new HashSet() : new HashSet(this.consumes), this.emptyBodyPermittedWithConsumes, this.produces, this.contextHandlers, this.failureHandlers, this.added, this.pattern, this.groups, this.useNormalisedPath, this.namedGroupsInRegex, this.virtualHostPattern, this.pathEndsWithSlash, this.exclusive, this.exactPath);
        newState.consumes.add(mime);
        return newState;
    }

    public boolean isEmptyBodyPermittedWithConsumes() {
        return this.emptyBodyPermittedWithConsumes;
    }

    RouteState setEmptyBodyPermittedWithConsumes(boolean emptyBodyPermittedWithConsumes) {
        return new RouteState(this.route, this.path, this.order, this.enabled, this.methods, this.consumes, emptyBodyPermittedWithConsumes, this.produces, this.contextHandlers, this.failureHandlers, this.added, this.pattern, this.groups, this.useNormalisedPath, this.namedGroupsInRegex, this.virtualHostPattern, this.pathEndsWithSlash, this.exclusive, this.exactPath);
    }

    public Set<MIMEHeader> getProduces() {
        return this.produces;
    }

    RouteState setProduces(Set<MIMEHeader> produces) {
        return new RouteState(this.route, this.path, this.order, this.enabled, this.methods, this.consumes, this.emptyBodyPermittedWithConsumes, produces, this.contextHandlers, this.failureHandlers, this.added, this.pattern, this.groups, this.useNormalisedPath, this.namedGroupsInRegex, this.virtualHostPattern, this.pathEndsWithSlash, this.exclusive, this.exactPath);
    }

    RouteState addProduce(MIMEHeader mime) {
        RouteState newState = new RouteState(this.route, this.path, this.order, this.enabled, this.methods, this.consumes, this.emptyBodyPermittedWithConsumes, this.produces == null ? new HashSet() : new HashSet(this.produces), this.contextHandlers, this.failureHandlers, this.added, this.pattern, this.groups, this.useNormalisedPath, this.namedGroupsInRegex, this.virtualHostPattern, this.pathEndsWithSlash, this.exclusive, this.exactPath);
        newState.produces.add(mime);
        return newState;
    }

    public List<Handler<RoutingContext>> getContextHandlers() {
        return this.contextHandlers;
    }

    public int getContextHandlersLength() {
        if (this.contextHandlers == null) {
            return 0;
        }
        return this.contextHandlers.size();
    }

    RouteState setContextHandlers(List<Handler<RoutingContext>> contextHandlers) {
        return new RouteState(this.route, this.path, this.order, this.enabled, this.methods, this.consumes, this.emptyBodyPermittedWithConsumes, this.produces, contextHandlers, this.failureHandlers, this.added, this.pattern, this.groups, this.useNormalisedPath, this.namedGroupsInRegex, this.virtualHostPattern, this.pathEndsWithSlash, this.exclusive, this.exactPath);
    }

    RouteState addContextHandler(Handler<RoutingContext> contextHandler) {
        RouteState newState = new RouteState(this.route, this.path, this.order, this.enabled, this.methods, this.consumes, this.emptyBodyPermittedWithConsumes, this.produces, this.contextHandlers == null ? new ArrayList() : new ArrayList(this.contextHandlers), this.failureHandlers, this.added, this.pattern, this.groups, this.useNormalisedPath, this.namedGroupsInRegex, this.virtualHostPattern, this.pathEndsWithSlash, this.exclusive, this.exactPath);
        newState.contextHandlers.add(contextHandler);
        return newState;
    }

    public List<Handler<RoutingContext>> getFailureHandlers() {
        return this.failureHandlers;
    }

    public int getFailureHandlersLength() {
        if (this.failureHandlers == null) {
            return 0;
        }
        return this.failureHandlers.size();
    }

    RouteState setFailureHandlers(List<Handler<RoutingContext>> failureHandlers) {
        return new RouteState(this.route, this.path, this.order, this.enabled, this.methods, this.consumes, this.emptyBodyPermittedWithConsumes, this.produces, this.contextHandlers, failureHandlers, this.added, this.pattern, this.groups, this.useNormalisedPath, this.namedGroupsInRegex, this.virtualHostPattern, this.pathEndsWithSlash, this.exclusive, this.exactPath);
    }

    RouteState addFailureHandler(Handler<RoutingContext> failureHandler) {
        RouteState newState = new RouteState(this.route, this.path, this.order, this.enabled, this.methods, this.consumes, this.emptyBodyPermittedWithConsumes, this.produces, this.contextHandlers, this.failureHandlers == null ? new ArrayList() : new ArrayList(this.failureHandlers), this.added, this.pattern, this.groups, this.useNormalisedPath, this.namedGroupsInRegex, this.virtualHostPattern, this.pathEndsWithSlash, this.exclusive, this.exactPath);
        newState.failureHandlers.add(failureHandler);
        return newState;
    }

    public boolean isAdded() {
        return this.added;
    }

    RouteState setAdded(boolean added) {
        return new RouteState(this.route, this.path, this.order, this.enabled, this.methods, this.consumes, this.emptyBodyPermittedWithConsumes, this.produces, this.contextHandlers, this.failureHandlers, added, this.pattern, this.groups, this.useNormalisedPath, this.namedGroupsInRegex, this.virtualHostPattern, this.pathEndsWithSlash, this.exclusive, this.exactPath);
    }

    public Pattern getPattern() {
        return this.pattern;
    }

    RouteState setPattern(Pattern pattern) {
        return new RouteState(this.route, this.path, this.order, this.enabled, this.methods, this.consumes, this.emptyBodyPermittedWithConsumes, this.produces, this.contextHandlers, this.failureHandlers, this.added, pattern, this.groups, this.useNormalisedPath, this.namedGroupsInRegex, this.virtualHostPattern, this.pathEndsWithSlash, this.exclusive, this.exactPath);
    }

    public List<String> getGroups() {
        return this.groups;
    }

    RouteState setGroups(List<String> groups) {
        return new RouteState(this.route, this.path, this.order, this.enabled, this.methods, this.consumes, this.emptyBodyPermittedWithConsumes, this.produces, this.contextHandlers, this.failureHandlers, this.added, this.pattern, groups, this.useNormalisedPath, this.namedGroupsInRegex, this.virtualHostPattern, this.pathEndsWithSlash, this.exclusive, this.exactPath);
    }

    RouteState addGroup(String group) {
        RouteState newState = new RouteState(this.route, this.path, this.order, this.enabled, this.methods, this.consumes, this.emptyBodyPermittedWithConsumes, this.produces, this.contextHandlers, this.failureHandlers, this.added, this.pattern, this.groups == null ? new ArrayList() : new ArrayList(this.groups), this.useNormalisedPath, this.namedGroupsInRegex, this.virtualHostPattern, this.pathEndsWithSlash, this.exclusive, this.exactPath);
        newState.groups.add(group);
        return newState;
    }

    public boolean isUseNormalisedPath() {
        return this.useNormalisedPath;
    }

    RouteState setUseNormalisedPath(boolean useNormalisedPath) {
        return new RouteState(this.route, this.path, this.order, this.enabled, this.methods, this.consumes, this.emptyBodyPermittedWithConsumes, this.produces, this.contextHandlers, this.failureHandlers, this.added, this.pattern, this.groups, useNormalisedPath, this.namedGroupsInRegex, this.virtualHostPattern, this.pathEndsWithSlash, this.exclusive, this.exactPath);
    }

    public Set<String> getNamedGroupsInRegex() {
        return this.namedGroupsInRegex;
    }

    RouteState setNamedGroupsInRegex(Set<String> namedGroupsInRegex) {
        return new RouteState(this.route, this.path, this.order, this.enabled, this.methods, this.consumes, this.emptyBodyPermittedWithConsumes, this.produces, this.contextHandlers, this.failureHandlers, this.added, this.pattern, this.groups, this.useNormalisedPath, namedGroupsInRegex, this.virtualHostPattern, this.pathEndsWithSlash, this.exclusive, this.exactPath);
    }

    RouteState addNamedGroupInRegex(String namedGroupInRegex) {
        RouteState newState = new RouteState(this.route, this.path, this.order, this.enabled, this.methods, this.consumes, this.emptyBodyPermittedWithConsumes, this.produces, this.contextHandlers, this.failureHandlers, this.added, this.pattern, this.groups, this.useNormalisedPath, this.namedGroupsInRegex == null ? new HashSet() : new HashSet(this.namedGroupsInRegex), this.virtualHostPattern, this.pathEndsWithSlash, this.exclusive, this.exactPath);
        newState.namedGroupsInRegex.add(namedGroupInRegex);
        return newState;
    }

    public Pattern getVirtualHostPattern() {
        return this.virtualHostPattern;
    }

    RouteState setVirtualHostPattern(Pattern virtualHostPattern) {
        return new RouteState(this.route, this.path, this.order, this.enabled, this.methods, this.consumes, this.emptyBodyPermittedWithConsumes, this.produces, this.contextHandlers, this.failureHandlers, this.added, this.pattern, this.groups, this.useNormalisedPath, this.namedGroupsInRegex, virtualHostPattern, this.pathEndsWithSlash, this.exclusive, this.exactPath);
    }

    public boolean isPathEndsWithSlash() {
        return this.pathEndsWithSlash;
    }

    RouteState setPathEndsWithSlash(boolean pathEndsWithSlash) {
        return new RouteState(this.route, this.path, this.order, this.enabled, this.methods, this.consumes, this.emptyBodyPermittedWithConsumes, this.produces, this.contextHandlers, this.failureHandlers, this.added, this.pattern, this.groups, this.useNormalisedPath, this.namedGroupsInRegex, this.virtualHostPattern, pathEndsWithSlash, this.exclusive, this.exactPath);
    }

    public boolean isExclusive() {
        return this.exclusive;
    }

    RouteState setExclusive(boolean exclusive) {
        return new RouteState(this.route, this.path, this.order, this.enabled, this.methods, this.consumes, this.emptyBodyPermittedWithConsumes, this.produces, this.contextHandlers, this.failureHandlers, this.added, this.pattern, this.groups, this.useNormalisedPath, this.namedGroupsInRegex, this.virtualHostPattern, this.pathEndsWithSlash, exclusive, this.exactPath);
    }

    public boolean isExactPath() {
        return this.exactPath;
    }

    RouteState setExactPath(boolean exactPath) {
        return new RouteState(this.route, this.path, this.order, this.enabled, this.methods, this.consumes, this.emptyBodyPermittedWithConsumes, this.produces, this.contextHandlers, this.failureHandlers, this.added, this.pattern, this.groups, this.useNormalisedPath, this.namedGroupsInRegex, this.virtualHostPattern, this.pathEndsWithSlash, this.exclusive, exactPath);
    }

    private static <T> boolean contains(Collection<T> collection, T value) {
        return (collection == null || collection.isEmpty() || !collection.contains(value)) ? false : true;
    }

    private static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public int matches(RoutingContextImplBase context, String mountPoint, boolean failure) {
        String undecodedValue;
        if (failure && !hasNextFailureHandler(context)) {
            return 404;
        }
        if ((!failure && !hasNextContextHandler(context)) || !this.enabled) {
            return 404;
        }
        HttpServerRequest request = context.request();
        if (this.path != null && this.pattern == null && !pathMatches(mountPoint, context)) {
            return 404;
        }
        if (this.pattern != null) {
            String path = this.useNormalisedPath ? context.normalisedPath() : context.request().path();
            if (mountPoint != null) {
                int strip = mountPoint.length();
                if (mountPoint.charAt(strip - 1) == '/') {
                    strip--;
                }
                path = path.substring(strip);
            }
            Matcher m = this.pattern.matcher(path);
            if (m.matches()) {
                if (!isEmpty(this.methods) && !contains(this.methods, request.method())) {
                    return 405;
                }
                context.matchRest = -1;
                context.matchNormalized = this.useNormalisedPath;
                if (m.groupCount() > 0) {
                    if (!this.exactPath) {
                        context.matchRest = m.start("rest");
                    }
                    if (!isEmpty(this.groups)) {
                        for (int i = 0; i < Math.min(this.groups.size(), m.groupCount()); i++) {
                            String k = this.groups.get(i);
                            try {
                                undecodedValue = m.group("p" + i);
                            } catch (IllegalArgumentException e) {
                                try {
                                    undecodedValue = m.group(k);
                                } catch (IllegalArgumentException e2) {
                                    undecodedValue = m.group(i + 1);
                                }
                            }
                            addPathParam(context, k, undecodedValue);
                        }
                    } else {
                        if (!isEmpty(this.namedGroupsInRegex)) {
                            for (String namedGroup : this.namedGroupsInRegex) {
                                String namedGroupValue = m.group(namedGroup);
                                if (namedGroupValue != null) {
                                    addPathParam(context, namedGroup, namedGroupValue);
                                }
                            }
                        }
                        for (int i2 = 0; i2 < m.groupCount(); i2++) {
                            String group = m.group(i2 + 1);
                            if (group != null) {
                                addPathParam(context, "param" + i2, group);
                            }
                        }
                    }
                }
            } else {
                return 404;
            }
        } else if (!isEmpty(this.methods) && !contains(this.methods, request.method())) {
            return 405;
        }
        if (!isEmpty(this.consumes)) {
            MIMEHeader contentType = context.parsedHeaders().contentType();
            MIMEHeader consumal = (MIMEHeader) contentType.findMatchedBy(this.consumes);
            if (consumal == null && (!contentType.rawValue().isEmpty() || !this.emptyBodyPermittedWithConsumes)) {
                if (contentType.rawValue().isEmpty()) {
                    return OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL;
                }
                return 415;
            }
        }
        List<MIMEHeader> acceptableTypes = context.parsedHeaders().accept();
        if (!isEmpty(this.produces) && !acceptableTypes.isEmpty()) {
            MIMEHeader selectedAccept = (MIMEHeader) context.parsedHeaders().findBestUserAcceptedIn(acceptableTypes, this.produces);
            if (selectedAccept != null) {
                context.setAcceptableContentType(selectedAccept.rawValue());
            } else {
                return 406;
            }
        }
        if (!virtualHostMatches(context.request.host())) {
            return 404;
        }
        return 0;
    }

    private boolean pathMatches(String mountPoint, RoutingContext ctx) {
        String thePath;
        boolean pathEndsWithSlash;
        String requestPath;
        boolean rootRouter = mountPoint == null;
        if (rootRouter) {
            thePath = this.path;
            pathEndsWithSlash = this.pathEndsWithSlash;
        } else {
            boolean mountPointEndsWithSlash = mountPoint.charAt(mountPoint.length() - 1) == '/';
            if (this.path.length() == 1) {
                thePath = mountPoint;
                pathEndsWithSlash = mountPointEndsWithSlash;
            } else {
                if (mountPointEndsWithSlash) {
                    thePath = mountPoint + this.path.substring(1);
                } else {
                    thePath = mountPoint + this.path;
                }
                pathEndsWithSlash = this.pathEndsWithSlash;
            }
        }
        if (this.useNormalisedPath) {
            requestPath = ctx.normalisedPath();
        } else {
            requestPath = ctx.request().path();
            if (requestPath == null) {
                requestPath = "/";
            }
        }
        if (this.exactPath) {
            return pathMatchesExact(thePath, requestPath, pathEndsWithSlash);
        }
        if (pathEndsWithSlash) {
            if (requestPath.charAt(requestPath.length() - 1) == '/') {
                if (requestPath.equals(thePath)) {
                    return true;
                }
            } else if (thePath.regionMatches(0, requestPath, 0, thePath.length())) {
                return true;
            }
        }
        return requestPath.startsWith(thePath);
    }

    private boolean virtualHostMatches(String host) {
        if (this.virtualHostPattern == null) {
            return true;
        }
        boolean match = false;
        String[] strArrSplit = host.split(":");
        int length = strArrSplit.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String h = strArrSplit[i];
            if (!this.virtualHostPattern.matcher(h).matches()) {
                i++;
            } else {
                match = true;
                break;
            }
        }
        return match;
    }

    private static boolean pathMatchesExact(String base, String other, boolean significantSlash) {
        int len = other.length();
        if (significantSlash) {
            if (other.charAt(len - 1) != '/') {
                return false;
            }
        } else if (other.charAt(len - 1) == '/') {
            len--;
        }
        if (base.length() != len) {
            return false;
        }
        return other.regionMatches(0, base, 0, len);
    }

    private void addPathParam(RoutingContext context, String name, String value) {
        HttpServerRequest request = context.request();
        String decodedValue = URIDecoder.decodeURIComponent(value, false);
        if (!request.params().contains(name)) {
            request.params().add(name, decodedValue);
        }
        context.pathParams().put(name, decodedValue);
    }

    boolean hasNextContextHandler(RoutingContextImplBase context) {
        return context.currentRouteNextHandlerIndex() < getContextHandlersLength();
    }

    boolean hasNextFailureHandler(RoutingContextImplBase context) {
        return context.currentRouteNextFailureHandlerIndex() < getFailureHandlersLength();
    }

    void handleContext(RoutingContextImplBase context) {
        this.contextHandlers.get(context.currentRouteNextHandlerIndex() - 1).handle(context);
    }

    void handleFailure(RoutingContextImplBase context) {
        this.failureHandlers.get(context.currentRouteNextFailureHandlerIndex() - 1).handle(context);
    }

    public String toString() {
        return "RouteState{path='" + this.path + "', order=" + this.order + ", enabled=" + this.enabled + ", methods=" + this.methods + ", consumes=" + this.consumes + ", emptyBodyPermittedWithConsumes=" + this.emptyBodyPermittedWithConsumes + ", produces=" + this.produces + ", contextHandlers=" + this.contextHandlers + ", failureHandlers=" + this.failureHandlers + ", added=" + this.added + ", pattern=" + this.pattern + ", groups=" + this.groups + ", useNormalisedPath=" + this.useNormalisedPath + ", namedGroupsInRegex=" + this.namedGroupsInRegex + ", virtualHostPattern=" + this.virtualHostPattern + ", pathEndsWithSlash=" + this.pathEndsWithSlash + ", exclusive=" + this.exclusive + ", exactPath=" + this.exactPath + '}';
    }
}
