package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/codecs/ShortMessageCodec.class */
public class ShortMessageCodec implements MessageCodec<Short, Short> {
    @Override // io.vertx.core.eventbus.MessageCodec
    public void encodeToWire(Buffer buffer, Short s) {
        buffer.appendShort(s.shortValue());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.eventbus.MessageCodec
    public Short decodeFromWire(int pos, Buffer buffer) {
        return Short.valueOf(buffer.getShort(pos));
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public Short transform(Short s) {
        return s;
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public String name() {
        return "short";
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public byte systemCodecID() {
        return (byte) 4;
    }
}
