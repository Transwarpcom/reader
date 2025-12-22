package io.netty.handler.codec.http2;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http2-4.1.42.Final.jar:io/netty/handler/codec/http2/Http2StreamFrame.class */
public interface Http2StreamFrame extends Http2Frame {
    Http2StreamFrame stream(Http2FrameStream http2FrameStream);

    Http2FrameStream stream();
}
