package io.vertx.core.http;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.ServiceHelper;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.spi.WebSocketFrameFactory;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/WebSocketFrame.class */
public interface WebSocketFrame {

    @GenIgnore
    public static final WebSocketFrameFactory factory = (WebSocketFrameFactory) ServiceHelper.loadFactory(WebSocketFrameFactory.class);

    boolean isText();

    boolean isBinary();

    boolean isContinuation();

    boolean isClose();

    @CacheReturn
    String textData();

    @CacheReturn
    Buffer binaryData();

    boolean isFinal();

    short closeStatusCode();

    String closeReason();

    static WebSocketFrame binaryFrame(Buffer data, boolean isFinal) {
        return factory.binaryFrame(data, isFinal);
    }

    static WebSocketFrame textFrame(String str, boolean isFinal) {
        return factory.textFrame(str, isFinal);
    }

    static WebSocketFrame pingFrame(Buffer data) {
        return factory.pingFrame(data);
    }

    static WebSocketFrame pongFrame(Buffer data) {
        return factory.pongFrame(data);
    }

    static WebSocketFrame continuationFrame(Buffer data, boolean isFinal) {
        return factory.continuationFrame(data, isFinal);
    }
}
