package io.vertx.ext.web;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.impl.RouterImpl;
import java.util.List;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/Router.class */
public interface Router extends Handler<HttpServerRequest> {
    Route route();

    Route route(HttpMethod httpMethod, String str);

    Route route(String str);

    Route routeWithRegex(HttpMethod httpMethod, String str);

    Route routeWithRegex(String str);

    Route get();

    Route get(String str);

    Route getWithRegex(String str);

    Route head();

    Route head(String str);

    Route headWithRegex(String str);

    Route options();

    Route options(String str);

    Route optionsWithRegex(String str);

    Route put();

    Route put(String str);

    Route putWithRegex(String str);

    Route post();

    Route post(String str);

    Route postWithRegex(String str);

    Route delete();

    Route delete(String str);

    Route deleteWithRegex(String str);

    Route trace();

    Route trace(String str);

    Route traceWithRegex(String str);

    Route connect();

    Route connect(String str);

    Route connectWithRegex(String str);

    Route patch();

    Route patch(String str);

    Route patchWithRegex(String str);

    List<Route> getRoutes();

    @Fluent
    Router clear();

    @Fluent
    Router mountSubRouter(String str, Router router);

    @Fluent
    @Deprecated
    Router exceptionHandler(Handler<Throwable> handler);

    @Fluent
    Router errorHandler(int i, Handler<RoutingContext> handler);

    void handleContext(RoutingContext routingContext);

    void handleFailure(RoutingContext routingContext);

    @Fluent
    Router modifiedHandler(Handler<Router> handler);

    static Router router(Vertx vertx) {
        return new RouterImpl(vertx);
    }

    @Deprecated
    default void accept(HttpServerRequest request) {
        handle(request);
    }
}
