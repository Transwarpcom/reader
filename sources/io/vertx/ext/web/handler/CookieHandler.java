package io.vertx.ext.web.handler;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.impl.CookieHandlerImpl;

@VertxGen
@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/CookieHandler.class */
public interface CookieHandler extends Handler<RoutingContext> {
    static CookieHandler create() {
        return new CookieHandlerImpl();
    }
}
