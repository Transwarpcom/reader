package io.vertx.ext.web.handler;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.web.handler.impl.ChainAuthHandlerImpl;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/ChainAuthHandler.class */
public interface ChainAuthHandler extends AuthHandler {
    @Fluent
    ChainAuthHandler append(AuthHandler authHandler);

    boolean remove(AuthHandler authHandler);

    void clear();

    static ChainAuthHandler create() {
        return new ChainAuthHandlerImpl();
    }
}
