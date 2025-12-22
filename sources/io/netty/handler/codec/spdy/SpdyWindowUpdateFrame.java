package io.netty.handler.codec.spdy;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/spdy/SpdyWindowUpdateFrame.class */
public interface SpdyWindowUpdateFrame extends SpdyFrame {
    int streamId();

    SpdyWindowUpdateFrame setStreamId(int i);

    int deltaWindowSize();

    SpdyWindowUpdateFrame setDeltaWindowSize(int i);
}
