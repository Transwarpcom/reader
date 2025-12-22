package io.vertx.ext.web.handler;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.web.handler.impl.BasicAuthHandlerImpl;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/BasicAuthHandler.class */
public interface BasicAuthHandler extends AuthHandler {
    public static final String DEFAULT_REALM = "vertx-web";

    static AuthHandler create(AuthProvider authProvider) {
        return new BasicAuthHandlerImpl(authProvider, DEFAULT_REALM);
    }

    static AuthHandler create(AuthProvider authProvider, String realm) {
        return new BasicAuthHandlerImpl(authProvider, realm);
    }
}
