package io.netty.handler.codec.spdy;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/spdy/SpdyGoAwayFrame.class */
public interface SpdyGoAwayFrame extends SpdyFrame {
    int lastGoodStreamId();

    SpdyGoAwayFrame setLastGoodStreamId(int i);

    SpdySessionStatus status();

    SpdyGoAwayFrame setStatus(SpdySessionStatus spdySessionStatus);
}
