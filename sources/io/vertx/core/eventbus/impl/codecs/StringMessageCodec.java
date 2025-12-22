package io.vertx.core.eventbus.impl.codecs;

import io.netty.util.CharsetUtil;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/codecs/StringMessageCodec.class */
public class StringMessageCodec implements MessageCodec<String, String> {
    @Override // io.vertx.core.eventbus.MessageCodec
    public void encodeToWire(Buffer buffer, String s) {
        byte[] strBytes = s.getBytes(CharsetUtil.UTF_8);
        buffer.appendInt(strBytes.length);
        buffer.appendBytes(strBytes);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.eventbus.MessageCodec
    public String decodeFromWire(int pos, Buffer buffer) {
        int length = buffer.getInt(pos);
        int pos2 = pos + 4;
        byte[] bytes = buffer.getBytes(pos2, pos2 + length);
        return new String(bytes, CharsetUtil.UTF_8);
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public String transform(String s) {
        return s;
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public String name() {
        return "string";
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public byte systemCodecID() {
        return (byte) 9;
    }
}
