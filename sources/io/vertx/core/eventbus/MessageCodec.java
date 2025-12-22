package io.vertx.core.eventbus;

import io.vertx.core.buffer.Buffer;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/MessageCodec.class */
public interface MessageCodec<S, R> {
    void encodeToWire(Buffer buffer, S s);

    R decodeFromWire(int i, Buffer buffer);

    R transform(S s);

    String name();

    byte systemCodecID();
}
