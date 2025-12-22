package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.vertx.core.http.impl.headers.VertxHttpHeaders;
import io.vertx.core.net.impl.PartialPooledByteBufAllocator;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/VertxHttpResponseEncoder.class */
final class VertxHttpResponseEncoder extends HttpResponseEncoder {
    private ChannelHandlerContext context;

    VertxHttpResponseEncoder() {
    }

    @Override // io.netty.handler.codec.http.HttpObjectEncoder, io.netty.handler.codec.MessageToMessageEncoder
    protected void encode(ChannelHandlerContext ctx, Object msg, List<Object> out) throws Exception {
        super.encode(this.context, msg, out);
    }

    @Override // io.netty.handler.codec.http.HttpObjectEncoder
    protected void encodeHeaders(HttpHeaders headers, ByteBuf buf) {
        if (headers instanceof VertxHttpHeaders) {
            VertxHttpHeaders vertxHeaders = (VertxHttpHeaders) headers;
            vertxHeaders.encode(buf);
        } else {
            super.encodeHeaders(headers, buf);
        }
    }

    @Override // io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        this.context = PartialPooledByteBufAllocator.forceDirectAllocator(ctx);
        super.handlerAdded(ctx);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.http.HttpResponseEncoder, io.netty.handler.codec.http.HttpObjectEncoder
    public boolean isContentAlwaysEmpty(HttpResponse msg) {
        return ((msg instanceof AssembledHttpResponse) && ((AssembledHttpResponse) msg).head()) || super.isContentAlwaysEmpty(msg);
    }
}
