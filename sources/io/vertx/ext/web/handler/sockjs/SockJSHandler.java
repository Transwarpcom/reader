package io.vertx.ext.web.handler.sockjs;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.sockjs.impl.SockJSHandlerImpl;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/SockJSHandler.class */
public interface SockJSHandler extends Handler<RoutingContext> {
    Router socketHandler(Handler<SockJSSocket> handler);

    Router bridge(BridgeOptions bridgeOptions);

    Router bridge(BridgeOptions bridgeOptions, Handler<BridgeEvent> handler);

    @Override // io.vertx.core.Handler
    @Deprecated
    void handle(RoutingContext routingContext);

    static SockJSHandler create(Vertx vertx) {
        return new SockJSHandlerImpl(vertx, new SockJSHandlerOptions());
    }

    static SockJSHandler create(Vertx vertx, SockJSHandlerOptions options) {
        return new SockJSHandlerImpl(vertx, options);
    }
}
