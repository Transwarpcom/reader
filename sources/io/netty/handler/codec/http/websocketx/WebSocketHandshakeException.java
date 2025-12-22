package io.netty.handler.codec.http.websocketx;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/http/websocketx/WebSocketHandshakeException.class */
public class WebSocketHandshakeException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public WebSocketHandshakeException(String s) {
        super(s);
    }

    public WebSocketHandshakeException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
