package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http2-4.1.42.Final.jar:io/netty/handler/codec/http2/Http2UnknownFrame.class */
public interface Http2UnknownFrame extends Http2StreamFrame, ByteBufHolder {
    @Override // io.netty.handler.codec.http2.Http2StreamFrame
    Http2FrameStream stream();

    @Override // io.netty.handler.codec.http2.Http2StreamFrame
    Http2UnknownFrame stream(Http2FrameStream http2FrameStream);

    byte frameType();

    Http2Flags flags();

    @Override // io.netty.buffer.ByteBufHolder
    Http2UnknownFrame copy();

    @Override // io.netty.buffer.ByteBufHolder
    Http2UnknownFrame duplicate();

    @Override // io.netty.buffer.ByteBufHolder
    Http2UnknownFrame retainedDuplicate();

    @Override // io.netty.buffer.ByteBufHolder
    Http2UnknownFrame replace(ByteBuf byteBuf);

    @Override // io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    Http2UnknownFrame retain();

    @Override // io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    Http2UnknownFrame retain(int i);

    @Override // io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    Http2UnknownFrame touch();

    @Override // io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    Http2UnknownFrame touch(Object obj);
}
