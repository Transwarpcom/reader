package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/codecs/LongMessageCodec.class */
public class LongMessageCodec implements MessageCodec<Long, Long> {
    @Override // io.vertx.core.eventbus.MessageCodec
    public void encodeToWire(Buffer buffer, Long l) {
        buffer.appendLong(l.longValue());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.eventbus.MessageCodec
    public Long decodeFromWire(int pos, Buffer buffer) {
        return Long.valueOf(buffer.getLong(pos));
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public Long transform(Long l) {
        return l;
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public String name() {
        return "long";
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public byte systemCodecID() {
        return (byte) 6;
    }
}
