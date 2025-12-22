package io.netty.handler.codec.spdy;

import io.netty.util.internal.ObjectUtil;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/spdy/DefaultSpdyStreamFrame.class */
public abstract class DefaultSpdyStreamFrame implements SpdyStreamFrame {
    private int streamId;
    private boolean last;

    protected DefaultSpdyStreamFrame(int streamId) {
        setStreamId(streamId);
    }

    @Override // io.netty.handler.codec.spdy.SpdyStreamFrame
    public int streamId() {
        return this.streamId;
    }

    @Override // io.netty.handler.codec.spdy.SpdyStreamFrame, io.netty.handler.codec.spdy.SpdyDataFrame
    public SpdyStreamFrame setStreamId(int streamId) {
        ObjectUtil.checkPositive(streamId, "streamId");
        this.streamId = streamId;
        return this;
    }

    @Override // io.netty.handler.codec.spdy.SpdyStreamFrame
    public boolean isLast() {
        return this.last;
    }

    @Override // io.netty.handler.codec.spdy.SpdyStreamFrame, io.netty.handler.codec.spdy.SpdyDataFrame
    public SpdyStreamFrame setLast(boolean last) {
        this.last = last;
        return this;
    }
}
