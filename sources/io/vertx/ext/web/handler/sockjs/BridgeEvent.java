package io.vertx.ext.web.handler.sockjs;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.bridge.BaseBridgeEvent;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/BridgeEvent.class */
public interface BridgeEvent extends BaseBridgeEvent {
    @Override // io.vertx.ext.bridge.BaseBridgeEvent
    @Fluent
    BridgeEvent setRawMessage(JsonObject jsonObject);

    @CacheReturn
    SockJSSocket socket();
}
