package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http2-4.1.42.Final.jar:io/netty/handler/codec/http2/Http2DataWriter.class */
public interface Http2DataWriter {
    ChannelFuture writeData(ChannelHandlerContext channelHandlerContext, int i, ByteBuf byteBuf, int i2, boolean z, ChannelPromise channelPromise);
}
