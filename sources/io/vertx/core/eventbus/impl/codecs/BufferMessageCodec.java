package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/codecs/BufferMessageCodec.class */
public class BufferMessageCodec implements MessageCodec<Buffer, Buffer> {
    @Override // io.vertx.core.eventbus.MessageCodec
    public void encodeToWire(Buffer buffer, Buffer b) {
        buffer.appendInt(b.length());
        buffer.appendBuffer(b);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.eventbus.MessageCodec
    public Buffer decodeFromWire(int pos, Buffer buffer) {
        int length = buffer.getInt(pos);
        int pos2 = pos + 4;
        return buffer.getBuffer(pos2, pos2 + length);
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public Buffer transform(Buffer b) {
        return b.copy();
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public String name() {
        return "buffer";
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public byte systemCodecID() {
        return (byte) 11;
    }
}
