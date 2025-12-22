package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/codecs/BooleanMessageCodec.class */
public class BooleanMessageCodec implements MessageCodec<Boolean, Boolean> {
    @Override // io.vertx.core.eventbus.MessageCodec
    public void encodeToWire(Buffer buffer, Boolean b) {
        buffer.appendByte((byte) (b.booleanValue() ? 0 : 1));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.eventbus.MessageCodec
    public Boolean decodeFromWire(int pos, Buffer buffer) {
        return Boolean.valueOf(buffer.getByte(pos) == 0);
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public Boolean transform(Boolean b) {
        return b;
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public String name() {
        return "boolean";
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public byte systemCodecID() {
        return (byte) 3;
    }
}
