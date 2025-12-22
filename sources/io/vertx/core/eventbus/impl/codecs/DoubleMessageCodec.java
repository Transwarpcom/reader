package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/codecs/DoubleMessageCodec.class */
public class DoubleMessageCodec implements MessageCodec<Double, Double> {
    @Override // io.vertx.core.eventbus.MessageCodec
    public void encodeToWire(Buffer buffer, Double d) {
        buffer.appendDouble(d.doubleValue());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.eventbus.MessageCodec
    public Double decodeFromWire(int pos, Buffer buffer) {
        return Double.valueOf(buffer.getDouble(pos));
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public Double transform(Double d) {
        return d;
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public String name() {
        return "double";
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public byte systemCodecID() {
        return (byte) 8;
    }
}
