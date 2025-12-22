package io.vertx.core.http.impl;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.WebSocketFrame;
import io.vertx.core.http.impl.ws.WebSocketFrameImpl;
import io.vertx.core.spi.WebSocketFrameFactory;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/WebSocketFrameFactoryImpl.class */
public class WebSocketFrameFactoryImpl implements WebSocketFrameFactory {
    @Override // io.vertx.core.spi.WebSocketFrameFactory
    public WebSocketFrame binaryFrame(Buffer data, boolean isFinal) {
        return new WebSocketFrameImpl(FrameType.BINARY, data.getByteBuf(), isFinal);
    }

    @Override // io.vertx.core.spi.WebSocketFrameFactory
    public WebSocketFrame textFrame(String str, boolean isFinal) {
        return new WebSocketFrameImpl(str, isFinal);
    }

    @Override // io.vertx.core.spi.WebSocketFrameFactory
    public WebSocketFrame continuationFrame(Buffer data, boolean isFinal) {
        return new WebSocketFrameImpl(FrameType.CONTINUATION, data.getByteBuf(), isFinal);
    }

    @Override // io.vertx.core.spi.WebSocketFrameFactory
    public WebSocketFrame pingFrame(Buffer data) {
        return new WebSocketFrameImpl(FrameType.PING, data.getByteBuf(), true);
    }

    @Override // io.vertx.core.spi.WebSocketFrameFactory
    public WebSocketFrame pongFrame(Buffer data) {
        return new WebSocketFrameImpl(FrameType.PONG, data.getByteBuf(), true);
    }
}
