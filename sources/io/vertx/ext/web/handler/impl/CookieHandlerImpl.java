package io.vertx.ext.web.handler.impl;

import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.CookieHandler;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/CookieHandlerImpl.class */
public class CookieHandlerImpl implements CookieHandler {
    @Override // io.vertx.core.Handler
    public void handle(RoutingContext context) {
        context.next();
    }
}
