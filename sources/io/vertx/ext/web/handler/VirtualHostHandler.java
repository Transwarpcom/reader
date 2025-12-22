package io.vertx.ext.web.handler;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.impl.VirtualHostHandlerImpl;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/VirtualHostHandler.class */
public interface VirtualHostHandler extends Handler<RoutingContext> {
    static VirtualHostHandler create(String hostname, Handler<RoutingContext> handler) {
        return new VirtualHostHandlerImpl(hostname, handler);
    }
}
