package io.vertx.core.spi;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.WebSocketFrame;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/WebSocketFrameFactory.class */
public interface WebSocketFrameFactory {
    WebSocketFrame binaryFrame(Buffer buffer, boolean z);

    WebSocketFrame textFrame(String str, boolean z);

    WebSocketFrame continuationFrame(Buffer buffer, boolean z);

    WebSocketFrame pingFrame(Buffer buffer);

    WebSocketFrame pongFrame(Buffer buffer);
}
