package io.vertx.core.http.impl.ws;

import io.netty.buffer.ByteBuf;
import io.vertx.core.http.WebSocketFrame;
import io.vertx.core.http.impl.FrameType;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/ws/WebSocketFrameInternal.class */
public interface WebSocketFrameInternal extends WebSocketFrame {
    ByteBuf getBinaryData();

    void setBinaryData(ByteBuf byteBuf);

    void setTextData(String str);

    int length();

    FrameType type();
}
