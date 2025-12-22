package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/codecs/ByteMessageCodec.class */
public class ByteMessageCodec implements MessageCodec<Byte, Byte> {
    @Override // io.vertx.core.eventbus.MessageCodec
    public void encodeToWire(Buffer buffer, Byte b) {
        buffer.appendByte(b.byteValue());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.eventbus.MessageCodec
    public Byte decodeFromWire(int pos, Buffer buffer) {
        return Byte.valueOf(buffer.getByte(pos));
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public Byte transform(Byte b) {
        return b;
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public String name() {
        return "byte";
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public byte systemCodecID() {
        return (byte) 2;
    }
}
