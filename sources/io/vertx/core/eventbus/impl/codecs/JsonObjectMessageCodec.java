package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.json.JsonObject;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/codecs/JsonObjectMessageCodec.class */
public class JsonObjectMessageCodec implements MessageCodec<JsonObject, JsonObject> {
    @Override // io.vertx.core.eventbus.MessageCodec
    public void encodeToWire(Buffer buffer, JsonObject jsonObject) {
        Buffer encoded = jsonObject.toBuffer();
        buffer.appendInt(encoded.length());
        buffer.appendBuffer(encoded);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.eventbus.MessageCodec
    public JsonObject decodeFromWire(int pos, Buffer buffer) {
        int length = buffer.getInt(pos);
        int pos2 = pos + 4;
        return new JsonObject(buffer.slice(pos2, pos2 + length));
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public JsonObject transform(JsonObject jsonObject) {
        return jsonObject.copy();
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public String name() {
        return "jsonobject";
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public byte systemCodecID() {
        return (byte) 13;
    }
}
