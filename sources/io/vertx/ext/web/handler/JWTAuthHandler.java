package io.vertx.ext.web.handler;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.web.handler.impl.JWTAuthHandlerImpl;
import java.util.List;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/JWTAuthHandler.class */
public interface JWTAuthHandler extends AuthHandler {
    @Fluent
    JWTAuthHandler setAudience(List<String> list);

    @Fluent
    JWTAuthHandler setIssuer(String str);

    @Fluent
    JWTAuthHandler setIgnoreExpiration(boolean z);

    static JWTAuthHandler create(JWTAuth authProvider) {
        return new JWTAuthHandlerImpl(authProvider, null);
    }

    static JWTAuthHandler create(JWTAuth authProvider, String skip) {
        return new JWTAuthHandlerImpl(authProvider, skip);
    }
}
