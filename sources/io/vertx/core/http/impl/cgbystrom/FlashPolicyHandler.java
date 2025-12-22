package io.vertx.core.http.impl.cgbystrom;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/cgbystrom/FlashPolicyHandler.class */
public class FlashPolicyHandler extends ChannelInboundHandlerAdapter {
    private static final String XML = "<cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"*\" /></cross-domain-policy>";
    private ParseState state = ParseState.MAGIC1;

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/cgbystrom/FlashPolicyHandler$ParseState.class */
    enum ParseState {
        MAGIC1,
        MAGIC2
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buffer = (ByteBuf) msg;
        int index = buffer.readerIndex();
        switch (this.state) {
            case MAGIC1:
                if (!buffer.isReadable()) {
                    return;
                }
                index++;
                int magic1 = buffer.getUnsignedByte(index);
                this.state = ParseState.MAGIC2;
                if (magic1 != 60) {
                    ctx.fireChannelRead((Object) buffer);
                    ctx.pipeline().remove(this);
                    return;
                }
                break;
            case MAGIC2:
                break;
            default:
                return;
        }
        if (!buffer.isReadable()) {
            return;
        }
        int magic2 = buffer.getUnsignedByte(index);
        if (magic2 != 112) {
            ctx.fireChannelRead((Object) buffer);
            ctx.pipeline().remove(this);
        } else {
            ctx.writeAndFlush(Unpooled.copiedBuffer(XML, CharsetUtil.UTF_8)).addListener2((GenericFutureListener<? extends Future<? super Void>>) ChannelFutureListener.CLOSE);
        }
    }
}
