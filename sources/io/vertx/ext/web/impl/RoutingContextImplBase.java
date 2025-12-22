package io.vertx.ext.web.impl;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.impl.HttpStatusException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/impl/RoutingContextImplBase.class */
public abstract class RoutingContextImplBase implements RoutingContext {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) RoutingContextImplBase.class);
    private final Set<RouteImpl> routes;
    protected final String mountPoint;
    protected final HttpServerRequest request;
    protected Iterator<RouteImpl> iter;
    protected RouteState currentRoute;
    int matchFailure;
    boolean matchNormalized;
    int matchRest = -1;
    private AtomicInteger currentRouteNextHandlerIndex = new AtomicInteger(0);
    private AtomicInteger currentRouteNextFailureHandlerIndex = new AtomicInteger(0);

    RoutingContextImplBase(String mountPoint, HttpServerRequest request, Set<RouteImpl> routes) {
        this.mountPoint = mountPoint;
        this.request = new HttpServerRequestWrapper(request);
        this.routes = routes;
        this.iter = routes.iterator();
        resetMatchFailure();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public String mountPoint() {
        return this.mountPoint;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Route currentRoute() {
        return this.currentRoute.getRoute();
    }

    int currentRouteNextHandlerIndex() {
        return this.currentRouteNextHandlerIndex.intValue();
    }

    int currentRouteNextFailureHandlerIndex() {
        return this.currentRouteNextFailureHandlerIndex.intValue();
    }

    void restart() {
        this.iter = this.routes.iterator();
        this.currentRoute = null;
        next();
    }

    boolean iterateNext() {
        boolean failed = failed();
        if (this.currentRoute != null) {
            if (!failed) {
                try {
                    if (this.currentRoute.hasNextContextHandler(this)) {
                        this.currentRouteNextHandlerIndex.incrementAndGet();
                        resetMatchFailure();
                        this.currentRoute.handleContext(this);
                        return true;
                    }
                } catch (Throwable t) {
                    handleInHandlerRuntimeFailure(this.currentRoute.getRouter(), failed, t);
                    return true;
                }
            }
            if (failed && this.currentRoute.hasNextFailureHandler(this)) {
                this.currentRouteNextFailureHandlerIndex.incrementAndGet();
                this.currentRoute.handleFailure(this);
                return true;
            }
        }
        while (this.iter.hasNext()) {
            RouteState routeState = this.iter.next().state();
            this.currentRouteNextHandlerIndex.set(0);
            this.currentRouteNextFailureHandlerIndex.set(0);
            try {
                int matchResult = routeState.matches(this, mountPoint(), failed);
                if (matchResult == 0) {
                    if (LOG.isTraceEnabled()) {
                        LOG.trace("Route matches: " + routeState);
                    }
                    resetMatchFailure();
                    try {
                        this.currentRoute = routeState;
                        if (LOG.isTraceEnabled()) {
                            LOG.trace("Calling the " + (failed ? "failure" : "") + " handler");
                        }
                        if (failed && this.currentRoute.hasNextFailureHandler(this)) {
                            this.currentRouteNextFailureHandlerIndex.incrementAndGet();
                            routeState.handleFailure(this);
                        } else if (this.currentRoute.hasNextContextHandler(this)) {
                            this.currentRouteNextHandlerIndex.incrementAndGet();
                            routeState.handleContext(this);
                        }
                        return true;
                    } catch (Throwable t2) {
                        handleInHandlerRuntimeFailure(routeState.getRouter(), failed, t2);
                        return true;
                    }
                }
                if (matchResult != 404) {
                    this.matchFailure = matchResult;
                }
            } catch (Throwable e) {
                e.printStackTrace();
                if (LOG.isTraceEnabled()) {
                    LOG.trace("IllegalArgumentException thrown during iteration", e);
                }
                if (!response().ended()) {
                    unhandledFailure(e instanceof IllegalArgumentException ? OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL : -1, e, routeState.getRouter());
                    return true;
                }
                return true;
            }
        }
        return false;
    }

    private void handleInHandlerRuntimeFailure(RouterImpl router, boolean failed, Throwable t) {
        if (LOG.isTraceEnabled()) {
            LOG.trace("Throwable thrown from handler", t);
        }
        if (!failed) {
            if (LOG.isTraceEnabled()) {
                LOG.trace("Failing the routing");
            }
            fail(t);
        } else {
            if (LOG.isTraceEnabled()) {
                LOG.trace("Failure in handling failure");
            }
            unhandledFailure(-1, t, router);
        }
    }

    protected void unhandledFailure(int statusCode, Throwable failure, RouterImpl router) {
        int statusCode2;
        if (statusCode != -1) {
            statusCode2 = statusCode;
        } else {
            statusCode2 = failure instanceof HttpStatusException ? ((HttpStatusException) failure).getStatusCode() : 500;
        }
        int code = statusCode2;
        Handler<RoutingContext> errorHandler = router.getErrorHandlerByStatusCode(code);
        if (errorHandler != null) {
            try {
                errorHandler.handle(this);
            } catch (Throwable t) {
                LOG.error("Error in error handler", t);
            }
        }
        if (!response().ended() && !response().closed()) {
            try {
                response().setStatusCode(code);
            } catch (IllegalArgumentException e) {
                response().setStatusMessage(HttpResponseStatus.valueOf(code).reasonPhrase()).setStatusCode(code);
            }
            response().end(response().getStatusMessage());
        }
    }

    private void resetMatchFailure() {
        this.matchFailure = 404;
    }
}
