package io.netty.handler.codec.http2;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http2-4.1.42.Final.jar:io/netty/handler/codec/http2/Http2FrameSizePolicy.class */
public interface Http2FrameSizePolicy {
    void maxFrameSize(int i) throws Http2Exception;

    int maxFrameSize();
}
