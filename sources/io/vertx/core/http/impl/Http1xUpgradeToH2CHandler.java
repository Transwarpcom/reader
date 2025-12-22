package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http2.DefaultHttp2DataFrame;
import io.netty.handler.codec.http2.DefaultHttp2Headers;
import io.netty.handler.codec.http2.DefaultHttp2HeadersFrame;
import io.netty.handler.codec.http2.Http2CodecUtil;
import io.netty.handler.codec.http2.Http2ConnectionHandler;
import io.netty.handler.codec.http2.Http2Settings;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.net.impl.HandlerHolder;
import io.vertx.core.net.impl.VertxHandler;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/Http1xUpgradeToH2CHandler.class */
class Http1xUpgradeToH2CHandler extends ChannelInboundHandlerAdapter {
    private final HttpServerChannelInitializer initializer;
    private final HandlerHolder<? extends Handler<HttpServerConnection>> holder;
    private VertxHttp2ConnectionHandler<Http2ServerConnection> handler;
    private final boolean isCompressionSupported;
    private final boolean isDecompressionSupported;

    Http1xUpgradeToH2CHandler(HttpServerChannelInitializer initializer, HandlerHolder<? extends Handler<HttpServerConnection>> holder, boolean isCompressionSupported, boolean isDecompressionSupported) {
        this.initializer = initializer;
        this.holder = holder;
        this.isCompressionSupported = isCompressionSupported;
        this.isDecompressionSupported = isDecompressionSupported;
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String settingsHeader;
        Http2Settings settings;
        if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;
            if (request.headers().contains(HttpHeaders.UPGRADE, Http2CodecUtil.HTTP_UPGRADE_PROTOCOL_NAME, true)) {
                String connection = request.headers().get(HttpHeaders.CONNECTION);
                int found = 0;
                if (connection != null && connection.length() > 0) {
                    StringBuilder buff = new StringBuilder();
                    int pos = 0;
                    int len = connection.length();
                    while (pos < len) {
                        int i = pos;
                        pos++;
                        char c = connection.charAt(i);
                        if (c != ' ' && c != ',') {
                            buff.append(Character.toLowerCase(c));
                        }
                        if (c == ',' || pos == len) {
                            if (buff.indexOf("upgrade") == 0 && buff.length() == 7) {
                                found |= 1;
                            } else if (buff.indexOf("http2-settings") == 0 && buff.length() == 14) {
                                found |= 2;
                            }
                            buff.setLength(0);
                        }
                    }
                }
                if (found == 3 && (settingsHeader = request.headers().get(Http2CodecUtil.HTTP_UPGRADE_SETTINGS_HEADER)) != null && (settings = HttpUtils.decodeSettings(settingsHeader)) != null) {
                    if (this.holder != null && this.holder.context.isEventLoopContext()) {
                        ChannelPipeline pipeline = ctx.pipeline();
                        DefaultFullHttpResponse res = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.SWITCHING_PROTOCOLS, Unpooled.EMPTY_BUFFER, false);
                        res.headers().add(HttpHeaderNames.CONNECTION, HttpHeaderValues.UPGRADE);
                        res.headers().add(HttpHeaderNames.UPGRADE, Http2CodecUtil.HTTP_UPGRADE_PROTOCOL_NAME);
                        ctx.writeAndFlush(res);
                        pipeline.remove("httpEncoder");
                        if (this.isCompressionSupported) {
                            pipeline.remove("deflater");
                        }
                        if (this.isDecompressionSupported) {
                            pipeline.remove("inflater");
                        }
                        this.handler = this.initializer.buildHttp2ConnectionHandler(this.holder.context, (Handler) this.holder.handler);
                        pipeline.addLast("handler", this.handler);
                        this.handler.serverUpgrade(ctx, settings, request);
                        DefaultHttp2Headers headers = new DefaultHttp2Headers();
                        headers.method(request.method().name());
                        headers.path(request.uri());
                        headers.authority(request.headers().get("host"));
                        headers.scheme("http");
                        request.headers().mo1934remove("http2-settings");
                        request.headers().mo1934remove("host");
                        request.headers().forEach(header -> {
                            headers.set((DefaultHttp2Headers) ((String) header.getKey()).toLowerCase(), (String) header.getValue());
                        });
                        ctx.fireChannelRead(new DefaultHttp2HeadersFrame(headers, false));
                    } else {
                        HttpServerImpl.log.warn("Cannot perform HTTP/2 upgrade in a worker verticle");
                    }
                }
                if (this.handler == null) {
                    DefaultFullHttpResponse res2 = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST, Unpooled.EMPTY_BUFFER, false);
                    res2.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
                    ctx.writeAndFlush(res2);
                    return;
                }
                return;
            }
            this.initializer.configureHttp1(ctx.pipeline(), this.holder);
            ctx.fireChannelRead(msg);
            ctx.pipeline().remove(this);
            return;
        }
        if (this.handler != null) {
            if (msg instanceof HttpContent) {
                HttpContent content = (HttpContent) msg;
                ByteBuf buf = VertxHandler.safeBuffer(content.content(), ctx.alloc());
                boolean end = msg instanceof LastHttpContent;
                ctx.fireChannelRead(new DefaultHttp2DataFrame(buf, end, 0));
                if (end) {
                    ChannelPipeline pipeline2 = ctx.pipeline();
                    for (Map.Entry<String, ChannelHandler> handler : pipeline2) {
                        if (!(handler.getValue() instanceof Http2ConnectionHandler)) {
                            pipeline2.remove(handler.getKey());
                        }
                    }
                    this.initializer.configureHttp2(pipeline2);
                    return;
                }
                return;
            }
            super.channelRead(ctx, msg);
        }
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if ((evt instanceof IdleStateEvent) && ((IdleStateEvent) evt).state() == IdleState.ALL_IDLE) {
            ctx.close();
        } else {
            ctx.fireUserEventTriggered(evt);
        }
    }
}
