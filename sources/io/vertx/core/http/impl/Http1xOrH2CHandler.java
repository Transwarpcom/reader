package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/Http1xOrH2CHandler.class */
public class Http1xOrH2CHandler extends ChannelInboundHandlerAdapter {
    public static final String HTTP_2_PREFACE = "PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n";
    private static final byte[] HTTP_2_PREFACE_ARRAY = HTTP_2_PREFACE.getBytes();
    private int current = 0;

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        int len = Math.min(buf.readableBytes(), HTTP_2_PREFACE_ARRAY.length - this.current);
        int i = 0;
        while (i < len) {
            if (buf.getByte(buf.readerIndex() + i) != HTTP_2_PREFACE_ARRAY[this.current + i]) {
                end(ctx, buf, false);
                return;
            }
            i++;
        }
        if (this.current + i == HTTP_2_PREFACE_ARRAY.length) {
            end(ctx, buf, true);
        } else {
            this.current += len;
            buf.release();
        }
    }

    private void end(ChannelHandlerContext ctx, ByteBuf buf, boolean h2c) {
        if (this.current > 0) {
            ByteBuf msg = Unpooled.buffer(this.current + buf.readableBytes());
            msg.writeBytes(HTTP_2_PREFACE_ARRAY, 0, this.current);
            msg.writeBytes(buf);
            buf.release();
            buf = msg;
        }
        configure(ctx, h2c);
        ctx.pipeline().remove(this);
        ctx.fireChannelRead((Object) buf);
    }

    protected void configure(ChannelHandlerContext ctx, boolean h2c) {
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler, io.netty.channel.ChannelInboundHandler
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel channel = ctx.channel();
        channel.close();
    }
}
