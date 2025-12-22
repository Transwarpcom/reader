package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/codecs/PingMessageCodec.class */
public class PingMessageCodec implements MessageCodec<String, String> {
    @Override // io.vertx.core.eventbus.MessageCodec
    public void encodeToWire(Buffer buffer, String s) {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.eventbus.MessageCodec
    public String decodeFromWire(int pos, Buffer buffer) {
        return null;
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public String transform(String s) {
        return null;
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public String name() {
        return "ping";
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public byte systemCodecID() {
        return (byte) 1;
    }
}
