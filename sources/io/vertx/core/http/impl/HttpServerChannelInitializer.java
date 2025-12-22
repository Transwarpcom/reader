package io.vertx.core.http.impl;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoop;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SniHandler;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.impl.cgbystrom.FlashPolicyHandler;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.net.impl.HandlerHolder;
import io.vertx.core.net.impl.SSLHelper;
import io.vertx.core.net.impl.SslHandshakeCompletionHandler;
import io.vertx.core.net.impl.VertxHandler;
import io.vertx.core.spi.metrics.HttpServerMetrics;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/HttpServerChannelInitializer.class */
public class HttpServerChannelInitializer extends ChannelInitializer<Channel> {
    private final VertxInternal vertx;
    private final SSLHelper sslHelper;
    private final HttpServerOptions options;
    private final String serverOrigin;
    private final HttpServerMetrics metrics;
    private final boolean logEnabled;
    private final boolean disableH2C;
    private final Function<EventLoop, HandlerHolder<? extends Handler<HttpServerConnection>>> connectionHandler;
    private final Function<EventLoop, HandlerHolder<? extends Handler<Throwable>>> errorHandler;

    public HttpServerChannelInitializer(VertxInternal vertx, SSLHelper sslHelper, HttpServerOptions options, String serverOrigin, HttpServerMetrics metrics, boolean disableH2C, Function<EventLoop, HandlerHolder<? extends Handler<HttpServerConnection>>> connectionHandler, Function<EventLoop, HandlerHolder<? extends Handler<Throwable>>> errorHandler) {
        this.vertx = vertx;
        this.sslHelper = sslHelper;
        this.options = options;
        this.serverOrigin = serverOrigin;
        this.metrics = metrics;
        this.logEnabled = options.getLogActivity();
        this.disableH2C = disableH2C;
        this.connectionHandler = connectionHandler;
        this.errorHandler = errorHandler;
    }

    @Override // io.netty.channel.ChannelInitializer
    protected void initChannel(final Channel ch2) {
        IdleStateHandler idle;
        final ChannelPipeline pipeline = ch2.pipeline();
        if (this.sslHelper.isSSL()) {
            ch2.pipeline().addFirst("handshaker", new SslHandshakeCompletionHandler(ar -> {
                if (ar.succeeded()) {
                    if (this.options.isUseAlpn()) {
                        SslHandler sslHandler = (SslHandler) pipeline.get(SslHandler.class);
                        String protocol = sslHandler.applicationProtocol();
                        if ("h2".equals(protocol)) {
                            handleHttp2(ch2);
                            return;
                        } else {
                            handleHttp1(ch2);
                            return;
                        }
                    }
                    handleHttp1(ch2);
                    return;
                }
                handleException(ch2, ar.cause());
            }));
            if (this.options.isSni()) {
                SniHandler sniHandler = new SniHandler(this.sslHelper.serverNameMapper(this.vertx));
                pipeline.addFirst(sniHandler);
                return;
            } else {
                SslHandler handler = new SslHandler(this.sslHelper.createEngine(this.vertx));
                pipeline.addFirst("ssl", handler);
                return;
            }
        }
        if (this.disableH2C) {
            handleHttp1(ch2);
            return;
        }
        if (this.options.getIdleTimeout() > 0) {
            IdleStateHandler idleStateHandler = new IdleStateHandler(0L, 0L, this.options.getIdleTimeout(), this.options.getIdleTimeoutUnit());
            idle = idleStateHandler;
            pipeline.addLast("idle", idleStateHandler);
        } else {
            idle = null;
        }
        final IdleStateHandler idleStateHandler2 = idle;
        pipeline.addLast(new Http1xOrH2CHandler() { // from class: io.vertx.core.http.impl.HttpServerChannelInitializer.1
            @Override // io.vertx.core.http.impl.Http1xOrH2CHandler
            protected void configure(ChannelHandlerContext ctx, boolean h2c) {
                if (idleStateHandler2 != null) {
                    pipeline.remove(idleStateHandler2);
                }
                if (h2c) {
                    HttpServerChannelInitializer.this.handleHttp2(ctx.channel());
                } else {
                    HttpServerChannelInitializer.this.handleHttp1(ch2);
                }
            }

            @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
            public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                if ((evt instanceof IdleStateEvent) && ((IdleStateEvent) evt).state() == IdleState.ALL_IDLE) {
                    ctx.close();
                }
            }

            @Override // io.vertx.core.http.impl.Http1xOrH2CHandler, io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler, io.netty.channel.ChannelInboundHandler
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                super.exceptionCaught(ctx, cause);
                HttpServerChannelInitializer.this.handleException(ch2, cause);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleException(Channel ch2, Throwable cause) {
        HandlerHolder<? extends Handler<Throwable>> holder = this.errorHandler.apply(ch2.eventLoop());
        if (holder != null) {
            holder.context.executeFromIO(cause, (Handler) holder.handler);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleHttp1(Channel ch2) {
        HandlerHolder<? extends Handler<HttpServerConnection>> holder = this.connectionHandler.apply(ch2.eventLoop());
        if (holder == null) {
            sendServiceUnavailable(ch2);
        } else {
            configureHttp1OrH2C(ch2.pipeline(), holder);
        }
    }

    private void sendServiceUnavailable(Channel ch2) {
        ch2.writeAndFlush(Unpooled.copiedBuffer("HTTP/1.1 503 Service Unavailable\r\nContent-Length:0\r\n\r\n", StandardCharsets.ISO_8859_1)).addListener2((GenericFutureListener<? extends Future<? super Void>>) ChannelFutureListener.CLOSE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleHttp2(Channel ch2) {
        HandlerHolder<? extends Handler<HttpServerConnection>> holder = this.connectionHandler.apply(ch2.eventLoop());
        if (holder == null) {
            ch2.close();
            return;
        }
        VertxHttp2ConnectionHandler<Http2ServerConnection> handler = buildHttp2ConnectionHandler(holder.context, (Handler) holder.handler);
        ch2.pipeline().addLast("handler", handler);
        configureHttp2(ch2.pipeline());
    }

    void configureHttp2(ChannelPipeline pipeline) {
        if (this.options.getIdleTimeout() > 0) {
            pipeline.addBefore("handler", "idle", new IdleStateHandler(0L, 0L, this.options.getIdleTimeout(), this.options.getIdleTimeoutUnit()));
        }
    }

    VertxHttp2ConnectionHandler<Http2ServerConnection> buildHttp2ConnectionHandler(ContextInternal ctx, Handler<HttpServerConnection> handler_) {
        VertxHttp2ConnectionHandler<Http2ServerConnection> handler = new VertxHttp2ConnectionHandlerBuilder().server(true).useCompression(this.options.isCompressionSupported()).useDecompression(this.options.isDecompressionSupported()).compressionLevel(this.options.getCompressionLevel()).initialSettings(this.options.getInitialSettings()).connectionFactory(connHandler -> {
            return new Http2ServerConnection(ctx, this.serverOrigin, connHandler, this.options, this.metrics);
        }).logEnabled(this.logEnabled).build();
        handler.addHandler(conn -> {
            if (this.metrics != null) {
                conn.metric(this.metrics.connected(conn.remoteAddress(), conn.remoteName()));
            }
            if (this.options.getHttp2ConnectionWindowSize() > 0) {
                conn.setWindowSize(this.options.getHttp2ConnectionWindowSize());
            }
            ctx.executeFromIO(conn, handler_);
        });
        return handler;
    }

    private void configureHttp1OrH2C(ChannelPipeline pipeline, HandlerHolder<? extends Handler<HttpServerConnection>> holder) {
        if (this.logEnabled) {
            pipeline.addLast("logging", new LoggingHandler());
        }
        if (HttpServerImpl.USE_FLASH_POLICY_HANDLER) {
            pipeline.addLast("flashpolicy", new FlashPolicyHandler());
        }
        pipeline.addLast("httpDecoder", new VertxHttpRequestDecoder(this.options));
        pipeline.addLast("httpEncoder", new VertxHttpResponseEncoder());
        if (this.options.isDecompressionSupported()) {
            pipeline.addLast("inflater", new HttpContentDecompressor(false));
        }
        if (this.options.isCompressionSupported()) {
            pipeline.addLast("deflater", new HttpChunkContentCompressor(this.options.getCompressionLevel()));
        }
        if (this.sslHelper.isSSL() || this.options.isCompressionSupported()) {
            pipeline.addLast("chunkedWriter", new ChunkedWriteHandler());
        }
        if (this.options.getIdleTimeout() > 0) {
            pipeline.addLast("idle", new IdleStateHandler(0L, 0L, this.options.getIdleTimeout(), this.options.getIdleTimeoutUnit()));
        }
        if (this.disableH2C) {
            configureHttp1(pipeline, holder);
        } else {
            pipeline.addLast("h2c", new Http1xUpgradeToH2CHandler(this, holder, this.options.isCompressionSupported(), this.options.isDecompressionSupported()));
        }
    }

    void configureHttp1(ChannelPipeline pipeline, HandlerHolder<? extends Handler<HttpServerConnection>> holder) {
        VertxHandler<Http1xServerConnection> handler = VertxHandler.create(holder.context, chctx -> {
            Http1xServerConnection conn = new Http1xServerConnection(holder.context.owner(), this.sslHelper, this.options, chctx, holder.context, this.serverOrigin, this.metrics);
            return conn;
        });
        pipeline.addLast("handler", handler);
        Http1xServerConnection conn = (Http1xServerConnection) handler.getConnection();
        if (this.metrics != null) {
            holder.context.executeFromIO(v -> {
                conn.metric(this.metrics.connected(conn.remoteAddress(), conn.remoteName()));
            });
        }
        holder.context.executeFromIO(conn, (Handler) holder.handler);
    }
}
