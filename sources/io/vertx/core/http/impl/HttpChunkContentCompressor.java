package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.HttpContentCompressor;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/HttpChunkContentCompressor.class */
final class HttpChunkContentCompressor extends HttpContentCompressor {
    @Override // io.netty.handler.codec.MessageToMessageCodec, io.netty.channel.ChannelDuplexHandler, io.netty.channel.ChannelOutboundHandler
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof ByteBuf) {
            ByteBuf buff = (ByteBuf) msg;
            if (buff.isReadable()) {
                msg = new DefaultHttpContent(buff);
            }
        }
        super.write(ctx, msg, promise);
    }

    HttpChunkContentCompressor(int compressionLevel) {
        super(compressionLevel);
    }
}
