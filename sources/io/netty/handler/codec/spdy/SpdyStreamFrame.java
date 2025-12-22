package io.netty.handler.codec.spdy;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/spdy/SpdyStreamFrame.class */
public interface SpdyStreamFrame extends SpdyFrame {
    int streamId();

    SpdyStreamFrame setStreamId(int i);

    boolean isLast();

    SpdyStreamFrame setLast(boolean z);
}
