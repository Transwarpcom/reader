package io.vertx.core.net.impl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.ssl.SniCompletionEvent;
import io.netty.handler.ssl.SslHandshakeCompletionEvent;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/SslHandshakeCompletionHandler.class */
public class SslHandshakeCompletionHandler extends ChannelInboundHandlerAdapter {
    static AttributeKey<String> SERVER_NAME_ATTR = AttributeKey.valueOf("sniServerName");
    private final Handler<AsyncResult<Channel>> handler;

    public SslHandshakeCompletionHandler(Handler<AsyncResult<Channel>> handler) {
        this.handler = handler;
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        if (evt instanceof SniCompletionEvent) {
            Attribute<String> val = ctx.channel().attr(SERVER_NAME_ATTR);
            val.set(((SniCompletionEvent) evt).hostname());
        } else {
            if (evt instanceof SslHandshakeCompletionEvent) {
                SslHandshakeCompletionEvent completion = (SslHandshakeCompletionEvent) evt;
                if (completion.isSuccess()) {
                    ctx.pipeline().remove(this);
                    this.handler.handle(Future.succeededFuture(ctx.channel()));
                    return;
                } else {
                    this.handler.handle(Future.failedFuture(completion.cause()));
                    return;
                }
            }
            ctx.fireUserEventTriggered(evt);
        }
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler, io.netty.channel.ChannelInboundHandler
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    }
}
