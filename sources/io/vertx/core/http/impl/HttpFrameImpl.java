package io.vertx.core.http.impl;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpFrame;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/HttpFrameImpl.class */
public class HttpFrameImpl implements HttpFrame {
    private final int type;
    private final int flags;
    private final Buffer payload;

    public HttpFrameImpl(int type, int flags, Buffer payload) {
        this.type = type;
        this.flags = flags;
        this.payload = payload;
    }

    @Override // io.vertx.core.http.HttpFrame
    public int flags() {
        return this.flags;
    }

    @Override // io.vertx.core.http.HttpFrame
    public int type() {
        return this.type;
    }

    @Override // io.vertx.core.http.HttpFrame
    public Buffer payload() {
        return this.payload;
    }
}
