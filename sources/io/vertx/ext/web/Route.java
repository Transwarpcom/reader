package io.vertx.ext.web;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import java.util.List;
import java.util.Set;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/Route.class */
public interface Route {
    @Fluent
    Route method(HttpMethod httpMethod);

    @Fluent
    Route path(String str);

    @Fluent
    Route pathRegex(String str);

    @Fluent
    Route produces(String str);

    @Fluent
    Route consumes(String str);

    @Fluent
    Route order(int i);

    @Fluent
    Route last();

    @Fluent
    Route handler(Handler<RoutingContext> handler);

    @Fluent
    Route blockingHandler(Handler<RoutingContext> handler);

    @Fluent
    Route subRouter(Router router);

    @Fluent
    Route blockingHandler(Handler<RoutingContext> handler, boolean z);

    @Fluent
    Route failureHandler(Handler<RoutingContext> handler);

    @Fluent
    Route remove();

    @Fluent
    Route disable();

    @Fluent
    Route enable();

    @Fluent
    Route useNormalisedPath(boolean z);

    String getPath();

    boolean isRegexPath();

    Set<HttpMethod> methods();

    @Fluent
    Route setRegexGroupsNames(List<String> list);
}
