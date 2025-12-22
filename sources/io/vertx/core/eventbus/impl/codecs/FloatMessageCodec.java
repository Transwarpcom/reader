package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/codecs/FloatMessageCodec.class */
public class FloatMessageCodec implements MessageCodec<Float, Float> {
    @Override // io.vertx.core.eventbus.MessageCodec
    public void encodeToWire(Buffer buffer, Float f) {
        buffer.appendFloat(f.floatValue());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.eventbus.MessageCodec
    public Float decodeFromWire(int pos, Buffer buffer) {
        return Float.valueOf(buffer.getFloat(pos));
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public Float transform(Float f) {
        return f;
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public String name() {
        return "float";
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public byte systemCodecID() {
        return (byte) 7;
    }
}
