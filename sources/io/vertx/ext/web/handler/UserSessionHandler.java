package io.vertx.ext.web.handler;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.impl.UserSessionHandlerImpl;

@VertxGen
@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/UserSessionHandler.class */
public interface UserSessionHandler extends Handler<RoutingContext> {
    static UserSessionHandler create(AuthProvider authProvider) {
        return new UserSessionHandlerImpl(authProvider);
    }
}
