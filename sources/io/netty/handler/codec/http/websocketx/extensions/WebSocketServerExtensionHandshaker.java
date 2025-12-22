package io.netty.handler.codec.http.websocketx.extensions;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/http/websocketx/extensions/WebSocketServerExtensionHandshaker.class */
public interface WebSocketServerExtensionHandshaker {
    WebSocketServerExtension handshakeExtension(WebSocketExtensionData webSocketExtensionData);
}
