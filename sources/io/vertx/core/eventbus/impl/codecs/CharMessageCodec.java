package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/codecs/CharMessageCodec.class */
public class CharMessageCodec implements MessageCodec<Character, Character> {
    @Override // io.vertx.core.eventbus.MessageCodec
    public void encodeToWire(Buffer buffer, Character chr) {
        buffer.appendShort((short) chr.charValue());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.eventbus.MessageCodec
    public Character decodeFromWire(int pos, Buffer buffer) {
        return Character.valueOf((char) buffer.getShort(pos));
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public Character transform(Character c) {
        return c;
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public String name() {
        return "char";
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public byte systemCodecID() {
        return (byte) 10;
    }
}
