package io.netty.handler.codec.spdy;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/spdy/SpdySynStreamFrame.class */
public interface SpdySynStreamFrame extends SpdyHeadersFrame {
    int associatedStreamId();

    SpdySynStreamFrame setAssociatedStreamId(int i);

    byte priority();

    SpdySynStreamFrame setPriority(byte b);

    boolean isUnidirectional();

    SpdySynStreamFrame setUnidirectional(boolean z);

    @Override // io.netty.handler.codec.spdy.SpdyHeadersFrame, io.netty.handler.codec.spdy.SpdyStreamFrame, io.netty.handler.codec.spdy.SpdyDataFrame
    SpdySynStreamFrame setStreamId(int i);

    @Override // io.netty.handler.codec.spdy.SpdyHeadersFrame, io.netty.handler.codec.spdy.SpdyStreamFrame, io.netty.handler.codec.spdy.SpdyDataFrame
    SpdySynStreamFrame setLast(boolean z);

    @Override // io.netty.handler.codec.spdy.SpdyHeadersFrame
    SpdySynStreamFrame setInvalid();
}
