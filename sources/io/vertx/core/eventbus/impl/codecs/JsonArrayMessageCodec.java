package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.json.JsonArray;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/codecs/JsonArrayMessageCodec.class */
public class JsonArrayMessageCodec implements MessageCodec<JsonArray, JsonArray> {
    @Override // io.vertx.core.eventbus.MessageCodec
    public void encodeToWire(Buffer buffer, JsonArray jsonArray) {
        Buffer encoded = jsonArray.toBuffer();
        buffer.appendInt(encoded.length());
        buffer.appendBuffer(encoded);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.eventbus.MessageCodec
    public JsonArray decodeFromWire(int pos, Buffer buffer) {
        int length = buffer.getInt(pos);
        int pos2 = pos + 4;
        return new JsonArray(buffer.slice(pos2, pos2 + length));
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public JsonArray transform(JsonArray jsonArray) {
        return jsonArray.copy();
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public String name() {
        return "jsonarray";
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public byte systemCodecID() {
        return (byte) 14;
    }
}
