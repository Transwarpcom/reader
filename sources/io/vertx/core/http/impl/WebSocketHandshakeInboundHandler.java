package io.vertx.core.http.impl;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketHandshakeException;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.WebsocketRejectedException;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/WebSocketHandshakeInboundHandler.class */
class WebSocketHandshakeInboundHandler extends ChannelInboundHandlerAdapter {
    private final Handler<AsyncResult<HeadersAdaptor>> wsHandler;
    private final WebSocketClientHandshaker handshaker;
    private ChannelHandlerContext chctx;
    private FullHttpResponse response;

    WebSocketHandshakeInboundHandler(WebSocketClientHandshaker handshaker, Handler<AsyncResult<HeadersAdaptor>> wsHandler) {
        this.handshaker = handshaker;
        this.wsHandler = wsHandler;
    }

    @Override // io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        this.chctx = ctx;
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        this.wsHandler.handle(Future.failedFuture(new WebSocketHandshakeException("Connection closed while handshake in process")));
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof HttpResponse) {
            HttpResponse resp = (HttpResponse) msg;
            this.response = new DefaultFullHttpResponse(resp.protocolVersion(), resp.status());
            this.response.headers().add(resp.headers());
        }
        if ((msg instanceof HttpContent) && this.response != null) {
            this.response.content().writeBytes(((HttpContent) msg).content());
            if (msg instanceof LastHttpContent) {
                this.response.trailingHeaders().add(((LastHttpContent) msg).trailingHeaders());
                ChannelPipeline pipeline = this.chctx.pipeline();
                pipeline.remove(this);
                ChannelHandler handler = pipeline.get((Class<ChannelHandler>) HttpContentDecompressor.class);
                if (handler != null) {
                    ctx.pipeline().remove(handler);
                }
                Future<HeadersAdaptor> fut = handshakeComplete(this.response);
                this.wsHandler.handle(fut);
            }
        }
    }

    private Future<HeadersAdaptor> handshakeComplete(FullHttpResponse response) {
        if (response.status().code() != 101) {
            WebsocketRejectedException failure = new WebsocketRejectedException(response.status().code());
            return Future.failedFuture(failure);
        }
        try {
            this.handshaker.finishHandshake(this.chctx.channel(), response);
            return Future.succeededFuture(new HeadersAdaptor(response.headers()));
        } catch (WebSocketHandshakeException e) {
            return Future.failedFuture(e);
        }
    }
}
