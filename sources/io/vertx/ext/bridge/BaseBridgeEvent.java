package io.vertx.ext.bridge;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-bridge-common-3.8.5.jar:io/vertx/ext/bridge/BaseBridgeEvent.class */
public interface BaseBridgeEvent extends Promise<Boolean> {
    @CacheReturn
    BridgeEventType type();

    JsonObject getRawMessage();

    @Fluent
    BaseBridgeEvent setRawMessage(JsonObject jsonObject);
}
