package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/codecs/ByteArrayMessageCodec.class */
public class ByteArrayMessageCodec implements MessageCodec<byte[], byte[]> {
    @Override // io.vertx.core.eventbus.MessageCodec
    public void encodeToWire(Buffer buffer, byte[] byteArray) {
        buffer.appendInt(byteArray.length);
        buffer.appendBytes(byteArray);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.eventbus.MessageCodec
    public byte[] decodeFromWire(int pos, Buffer buffer) {
        int length = buffer.getInt(pos);
        int pos2 = pos + 4;
        return buffer.getBytes(pos2, pos2 + length);
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public byte[] transform(byte[] bytes) {
        byte[] copied = new byte[bytes.length];
        System.arraycopy(bytes, 0, copied, 0, bytes.length);
        return copied;
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public String name() {
        return "bytearray";
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public byte systemCodecID() {
        return (byte) 12;
    }
}
