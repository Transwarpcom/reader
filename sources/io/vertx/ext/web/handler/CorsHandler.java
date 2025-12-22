package io.vertx.ext.web.handler;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.impl.CorsHandlerImpl;
import java.util.Set;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/CorsHandler.class */
public interface CorsHandler extends Handler<RoutingContext> {
    @Fluent
    CorsHandler allowedMethod(HttpMethod httpMethod);

    @Fluent
    CorsHandler allowedMethods(Set<HttpMethod> set);

    @Fluent
    CorsHandler allowedHeader(String str);

    @Fluent
    CorsHandler allowedHeaders(Set<String> set);

    @Fluent
    CorsHandler exposedHeader(String str);

    @Fluent
    CorsHandler exposedHeaders(Set<String> set);

    @Fluent
    CorsHandler allowCredentials(boolean z);

    @Fluent
    CorsHandler maxAgeSeconds(int i);

    static CorsHandler create(String allowedOriginPattern) {
        return new CorsHandlerImpl(allowedOriginPattern);
    }
}
