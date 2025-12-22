package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/codecs/IntMessageCodec.class */
public class IntMessageCodec implements MessageCodec<Integer, Integer> {
    @Override // io.vertx.core.eventbus.MessageCodec
    public void encodeToWire(Buffer buffer, Integer i) {
        buffer.appendInt(i.intValue());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.eventbus.MessageCodec
    public Integer decodeFromWire(int pos, Buffer buffer) {
        return Integer.valueOf(buffer.getInt(pos));
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public Integer transform(Integer i) {
        return i;
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public String name() {
        return "int";
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public byte systemCodecID() {
        return (byte) 5;
    }
}
