package io.netty.handler.codec.http;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/http/HttpMessage.class */
public interface HttpMessage extends HttpObject {
    @Deprecated
    HttpVersion getProtocolVersion();

    HttpVersion protocolVersion();

    HttpMessage setProtocolVersion(HttpVersion httpVersion);

    HttpHeaders headers();
}
