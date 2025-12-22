package io.vertx.ext.web.impl;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/impl/RouterImpl.class */
public class RouterImpl implements Router {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) RouterImpl.class);
    private final Vertx vertx;
    private volatile RouterState state = new RouterState(this);

    public RouterImpl(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override // io.vertx.core.Handler
    public void handle(HttpServerRequest request) {
        if (log.isTraceEnabled()) {
            log.trace("Router: " + System.identityHashCode(this) + " accepting request " + request.method() + " " + request.absoluteURI());
        }
        new RoutingContextImpl(null, this, request, this.state.getRoutes()).next();
    }

    @Override // io.vertx.ext.web.Router
    public synchronized Route route() {
        this.state = this.state.incrementOrderSequence();
        return new RouteImpl(this, this.state.getOrderSequence());
    }

    @Override // io.vertx.ext.web.Router
    public synchronized Route route(HttpMethod method, String path) {
        this.state = this.state.incrementOrderSequence();
        return new RouteImpl(this, this.state.getOrderSequence(), method, path);
    }

    @Override // io.vertx.ext.web.Router
    public synchronized Route route(String path) {
        this.state = this.state.incrementOrderSequence();
        return new RouteImpl(this, this.state.getOrderSequence(), path);
    }

    @Override // io.vertx.ext.web.Router
    public synchronized Route routeWithRegex(HttpMethod method, String regex) {
        this.state = this.state.incrementOrderSequence();
        return new RouteImpl(this, this.state.getOrderSequence(), method, regex, true);
    }

    @Override // io.vertx.ext.web.Router
    public synchronized Route routeWithRegex(String regex) {
        this.state = this.state.incrementOrderSequence();
        return new RouteImpl(this, this.state.getOrderSequence(), regex, true);
    }

    @Override // io.vertx.ext.web.Router
    public Route get() {
        return route().method(HttpMethod.GET);
    }

    @Override // io.vertx.ext.web.Router
    public Route get(String path) {
        return route(HttpMethod.GET, path);
    }

    @Override // io.vertx.ext.web.Router
    public Route getWithRegex(String path) {
        return route().method(HttpMethod.GET).pathRegex(path);
    }

    @Override // io.vertx.ext.web.Router
    public Route head() {
        return route().method(HttpMethod.HEAD);
    }

    @Override // io.vertx.ext.web.Router
    public Route head(String path) {
        return route(HttpMethod.HEAD, path);
    }

    @Override // io.vertx.ext.web.Router
    public Route headWithRegex(String path) {
        return route().method(HttpMethod.HEAD).pathRegex(path);
    }

    @Override // io.vertx.ext.web.Router
    public Route options() {
        return route().method(HttpMethod.OPTIONS);
    }

    @Override // io.vertx.ext.web.Router
    public Route options(String path) {
        return route(HttpMethod.OPTIONS, path);
    }

    @Override // io.vertx.ext.web.Router
    public Route optionsWithRegex(String path) {
        return route().method(HttpMethod.OPTIONS).pathRegex(path);
    }

    @Override // io.vertx.ext.web.Router
    public Route put() {
        return route().method(HttpMethod.PUT);
    }

    @Override // io.vertx.ext.web.Router
    public Route put(String path) {
        return route(HttpMethod.PUT, path);
    }

    @Override // io.vertx.ext.web.Router
    public Route putWithRegex(String path) {
        return route().method(HttpMethod.PUT).pathRegex(path);
    }

    @Override // io.vertx.ext.web.Router
    public Route post() {
        return route().method(HttpMethod.POST);
    }

    @Override // io.vertx.ext.web.Router
    public Route post(String path) {
        return route(HttpMethod.POST, path);
    }

    @Override // io.vertx.ext.web.Router
    public Route postWithRegex(String path) {
        return route().method(HttpMethod.POST).pathRegex(path);
    }

    @Override // io.vertx.ext.web.Router
    public Route delete() {
        return route().method(HttpMethod.DELETE);
    }

    @Override // io.vertx.ext.web.Router
    public Route delete(String path) {
        return route(HttpMethod.DELETE, path);
    }

    @Override // io.vertx.ext.web.Router
    public Route deleteWithRegex(String path) {
        return route().method(HttpMethod.DELETE).pathRegex(path);
    }

    @Override // io.vertx.ext.web.Router
    public Route trace() {
        return route().method(HttpMethod.TRACE);
    }

    @Override // io.vertx.ext.web.Router
    public Route trace(String path) {
        return route(HttpMethod.TRACE, path);
    }

    @Override // io.vertx.ext.web.Router
    public Route traceWithRegex(String path) {
        return route().method(HttpMethod.TRACE).pathRegex(path);
    }

    @Override // io.vertx.ext.web.Router
    public Route connect() {
        return route().method(HttpMethod.CONNECT);
    }

    @Override // io.vertx.ext.web.Router
    public Route connect(String path) {
        return route(HttpMethod.CONNECT, path);
    }

    @Override // io.vertx.ext.web.Router
    public Route connectWithRegex(String path) {
        return route().method(HttpMethod.CONNECT).pathRegex(path);
    }

    @Override // io.vertx.ext.web.Router
    public Route patch() {
        return route().method(HttpMethod.PATCH);
    }

    @Override // io.vertx.ext.web.Router
    public Route patch(String path) {
        return route(HttpMethod.PATCH, path);
    }

    @Override // io.vertx.ext.web.Router
    public Route patchWithRegex(String path) {
        return route().method(HttpMethod.PATCH).pathRegex(path);
    }

    @Override // io.vertx.ext.web.Router
    public List<Route> getRoutes() {
        return new ArrayList(this.state.getRoutes());
    }

    @Override // io.vertx.ext.web.Router
    public synchronized Router clear() {
        this.state = this.state.clearRoutes();
        return this;
    }

    @Override // io.vertx.ext.web.Router
    public void handleContext(RoutingContext ctx) {
        new RoutingContextWrapper(getAndCheckRoutePath(ctx), ctx.request(), this.state.getRoutes(), ctx).next();
    }

    @Override // io.vertx.ext.web.Router
    public void handleFailure(RoutingContext ctx) {
        new RoutingContextWrapper(getAndCheckRoutePath(ctx), ctx.request(), this.state.getRoutes(), ctx).next();
    }

    @Override // io.vertx.ext.web.Router
    public synchronized Router modifiedHandler(Handler<Router> handler) {
        if (this.state.getModifiedHandler() == null) {
            this.state = this.state.setModifiedHandler(handler);
        } else {
            Handler<Router> previousHandler = this.state.getModifiedHandler();
            this.state = this.state.setModifiedHandler(router -> {
                try {
                    previousHandler.handle(router);
                } catch (RuntimeException e) {
                    log.error("Router modified notification failed", e);
                }
                try {
                    handler.handle(router);
                } catch (RuntimeException e2) {
                    log.error("Router modified notification failed", e2);
                }
            });
        }
        return this;
    }

    @Override // io.vertx.ext.web.Router
    public Router mountSubRouter(String mountPoint, Router subRouter) {
        if (mountPoint.endsWith("*")) {
            throw new IllegalArgumentException("Don't include * when mounting a sub router");
        }
        route(mountPoint + "*").subRouter(subRouter);
        return this;
    }

    @Override // io.vertx.ext.web.Router
    @Deprecated
    public synchronized Router exceptionHandler(Handler<Throwable> exceptionHandler) {
        if (exceptionHandler != null) {
            errorHandler(500, routingContext -> {
                exceptionHandler.handle(routingContext.failure());
            });
        }
        return this;
    }

    @Override // io.vertx.ext.web.Router
    public synchronized Router errorHandler(int statusCode, Handler<RoutingContext> errorHandler) {
        this.state = this.state.putErrorHandler(statusCode, errorHandler);
        return this;
    }

    synchronized void add(RouteImpl route) {
        this.state = this.state.addRoute(route);
        if (this.state.getModifiedHandler() != null) {
            this.state.getModifiedHandler().handle(this);
        }
    }

    synchronized void remove(RouteImpl route) {
        this.state = this.state.removeRoute(route);
        if (this.state.getModifiedHandler() != null) {
            this.state.getModifiedHandler().handle(this);
        }
    }

    Vertx vertx() {
        return this.vertx;
    }

    Iterator<RouteImpl> iterator() {
        return this.state.getRoutes().iterator();
    }

    Handler<RoutingContext> getErrorHandlerByStatusCode(int statusCode) {
        return this.state.getErrorHandler(statusCode);
    }

    private String getAndCheckRoutePath(RoutingContext routingContext) {
        RoutingContextImplBase ctx = (RoutingContextImplBase) routingContext;
        Route route = ctx.currentRoute();
        if (route.getPath() != null && !route.isRegexPath()) {
            return route.getPath();
        }
        if (ctx.matchRest != -1) {
            if (ctx.matchNormalized) {
                return ctx.normalisedPath().substring(0, ctx.matchRest);
            }
            return ctx.request().path().substring(0, ctx.matchRest);
        }
        throw new IllegalStateException("Sub routers must be mounted on paths (constant or parameterized)");
    }

    public String toString() {
        return "RouterImpl@" + System.identityHashCode(this) + "{vertx=" + this.vertx + ", state=" + this.state + '}';
    }
}
