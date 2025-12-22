package io.vertx.ext.web.impl;

import io.vertx.core.Handler;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/impl/RouterState.class */
final class RouterState {
    private static final Comparator<RouteImpl> routeComparator = (o1, o2) -> {
        int compare = Integer.compare(o1.order(), o2.order());
        if (compare == 0) {
            if (o1.equals(o2)) {
                return 0;
            }
            return 1;
        }
        return compare;
    };
    private final RouterImpl router;
    private final Set<RouteImpl> routes;
    private final int orderSequence;
    private final Map<Integer, Handler<RoutingContext>> errorHandlers;
    private final Handler<Router> modifiedHandler;

    public RouterState(RouterImpl router, Set<RouteImpl> routes, int orderSequence, Map<Integer, Handler<RoutingContext>> errorHandlers, Handler<Router> modifiedHandler) {
        this.router = router;
        this.routes = routes;
        this.orderSequence = orderSequence;
        this.errorHandlers = errorHandlers;
        this.modifiedHandler = modifiedHandler;
    }

    public RouterState(RouterImpl router) {
        this(router, null, 0, null, null);
    }

    public RouterImpl router() {
        return this.router;
    }

    public Set<RouteImpl> getRoutes() {
        if (this.routes == null) {
            return Collections.emptySet();
        }
        return this.routes;
    }

    RouterState setRoutes(Set<RouteImpl> routes) {
        RouterState newState = new RouterState(this.router, new TreeSet(routeComparator), this.orderSequence, this.errorHandlers, this.modifiedHandler);
        newState.routes.addAll(routes);
        return newState;
    }

    RouterState addRoute(RouteImpl route) {
        Set<RouteImpl> routes = new TreeSet<>(routeComparator);
        if (this.routes != null) {
            routes.addAll(this.routes);
        }
        routes.add(route);
        return new RouterState(this.router, routes, this.orderSequence, this.errorHandlers, this.modifiedHandler);
    }

    RouterState clearRoutes() {
        return new RouterState(this.router, new TreeSet(routeComparator), this.orderSequence, this.errorHandlers, this.modifiedHandler);
    }

    RouterState removeRoute(RouteImpl route) {
        Set<RouteImpl> routes = new TreeSet<>(routeComparator);
        if (this.routes != null) {
            routes.addAll(this.routes);
        }
        routes.remove(route);
        return new RouterState(this.router, routes, this.orderSequence, this.errorHandlers, this.modifiedHandler);
    }

    public int getOrderSequence() {
        return this.orderSequence;
    }

    RouterState incrementOrderSequence() {
        return new RouterState(this.router, this.routes, this.orderSequence + 1, this.errorHandlers, this.modifiedHandler);
    }

    RouterState setOrderSequence(int orderSequence) {
        return new RouterState(this.router, this.routes, orderSequence, this.errorHandlers, this.modifiedHandler);
    }

    public Map<Integer, Handler<RoutingContext>> getErrorHandlers() {
        return this.errorHandlers;
    }

    RouterState setErrorHandlers(Map<Integer, Handler<RoutingContext>> errorHandlers) {
        return new RouterState(this.router, this.routes, this.orderSequence, errorHandlers, this.modifiedHandler);
    }

    Handler<RoutingContext> getErrorHandler(int errorCode) {
        if (this.errorHandlers != null) {
            return this.errorHandlers.get(Integer.valueOf(errorCode));
        }
        return null;
    }

    RouterState putErrorHandler(int errorCode, Handler<RoutingContext> errorHandler) {
        RouterState newState = new RouterState(this.router, this.routes, this.orderSequence, this.errorHandlers == null ? new HashMap() : new HashMap(this.errorHandlers), this.modifiedHandler);
        newState.errorHandlers.put(Integer.valueOf(errorCode), errorHandler);
        return newState;
    }

    public Handler<Router> getModifiedHandler() {
        return this.modifiedHandler;
    }

    public RouterState setModifiedHandler(Handler<Router> modifiedHandler) {
        return new RouterState(this.router, this.routes, this.orderSequence, this.errorHandlers, modifiedHandler);
    }

    public String toString() {
        return "RouterState{routes=" + this.routes + ", orderSequence=" + this.orderSequence + ", errorHandlers=" + this.errorHandlers + ", modifiedHandler=" + this.modifiedHandler + '}';
    }
}
