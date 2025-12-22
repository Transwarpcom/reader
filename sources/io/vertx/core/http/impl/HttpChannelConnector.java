package io.vertx.core.http.impl;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.impl.pool.ConnectResult;
import io.vertx.core.http.impl.pool.ConnectionListener;
import io.vertx.core.http.impl.pool.ConnectionProvider;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.net.ProxyOptions;
import io.vertx.core.net.ProxyType;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.net.impl.ChannelProvider;
import io.vertx.core.net.impl.SSLHelper;
import io.vertx.core.net.impl.VertxHandler;
import io.vertx.core.spi.metrics.HttpClientMetrics;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/HttpChannelConnector.class */
class HttpChannelConnector implements ConnectionProvider<HttpClientConnection> {
    private final HttpClientImpl client;
    private final HttpClientOptions options;
    private final HttpClientMetrics metrics;
    private final SSLHelper sslHelper;
    private final HttpVersion version;
    private final long weight;
    private final long http1Weight;
    private final long http2Weight;
    private final long http1MaxConcurrency;
    private final boolean ssl;
    private final SocketAddress peerAddress;
    private final SocketAddress server;
    private final Object endpointMetric;

    HttpChannelConnector(HttpClientImpl client, Object endpointMetric, HttpVersion version, boolean ssl, SocketAddress peerAddress, SocketAddress server) {
        this.client = client;
        this.endpointMetric = endpointMetric;
        this.options = client.getOptions();
        this.metrics = client.metrics();
        this.sslHelper = client.getSslHelper();
        this.version = version;
        this.http1Weight = this.options.getHttp2MaxPoolSize();
        this.http2Weight = this.options.getMaxPoolSize();
        this.weight = version == HttpVersion.HTTP_2 ? this.http2Weight : this.http1Weight;
        this.http1MaxConcurrency = this.options.isPipelining() ? this.options.getPipeliningLimit() : 1L;
        this.ssl = ssl;
        this.peerAddress = peerAddress;
        this.server = server;
    }

    public long weight() {
        return this.weight;
    }

    @Override // io.vertx.core.http.impl.pool.ConnectionProvider
    public void close(HttpClientConnection conn) {
        conn.close();
    }

    @Override // io.vertx.core.http.impl.pool.ConnectionProvider
    public void connect(ConnectionListener<HttpClientConnection> listener, ContextInternal context, Handler<AsyncResult<ConnectResult<HttpClientConnection>>> handler) {
        Promise<ConnectResult<HttpClientConnection>> promise = Promise.promise();
        promise.future().setHandler2(handler);
        try {
            doConnect(listener, context, promise);
        } catch (Exception e) {
            promise.tryFail(e);
        }
    }

    private void doConnect(ConnectionListener<HttpClientConnection> listener, ContextInternal context, Promise<ConnectResult<HttpClientConnection>> future) {
        boolean domainSocket = this.server.path() != null;
        boolean useAlpn = this.options.isUseAlpn();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(context.nettyEventLoop());
        applyConnectionOptions(domainSocket, bootstrap);
        ProxyOptions options = this.options.getProxyOptions();
        if (options != null && !this.ssl && options.getType() == ProxyType.HTTP) {
            options = null;
        }
        ChannelProvider channelProvider = new ChannelProvider(bootstrap, this.sslHelper, context, options);
        Handler<AsyncResult<Channel>> channelHandler = res -> {
            if (res.succeeded()) {
                Channel ch2 = (Channel) res.result();
                if (this.ssl) {
                    String protocol = channelProvider.applicationProtocol();
                    if (useAlpn) {
                        if ("h2".equals(protocol)) {
                            applyHttp2ConnectionOptions(ch2.pipeline());
                            http2Connected(listener, context, ch2, future);
                            return;
                        } else {
                            applyHttp1xConnectionOptions(ch2.pipeline());
                            HttpVersion fallbackProtocol = "http/1.0".equals(protocol) ? HttpVersion.HTTP_1_0 : HttpVersion.HTTP_1_1;
                            http1xConnected(listener, fallbackProtocol, this.server, true, context, ch2, this.http1Weight, future);
                            return;
                        }
                    }
                    applyHttp1xConnectionOptions(ch2.pipeline());
                    http1xConnected(listener, this.version, this.server, true, context, ch2, this.http1Weight, future);
                    return;
                }
                ChannelPipeline pipeline = ch2.pipeline();
                if (this.version == HttpVersion.HTTP_2) {
                    if (this.options.isHttp2ClearTextUpgrade()) {
                        applyHttp1xConnectionOptions(pipeline);
                        http1xConnected(listener, this.version, this.server, false, context, ch2, this.http2Weight, future);
                        return;
                    } else {
                        applyHttp2ConnectionOptions(pipeline);
                        http2Connected(listener, context, ch2, future);
                        return;
                    }
                }
                applyHttp1xConnectionOptions(pipeline);
                http1xConnected(listener, this.version, this.server, false, context, ch2, this.http1Weight, future);
                return;
            }
            connectFailed(channelProvider.channel(), listener, res.cause(), future);
        };
        channelProvider.connect(this.server, this.peerAddress, this.options.isForceSni() ? this.peerAddress.host() : null, this.ssl, channelHandler);
    }

    private void applyConnectionOptions(boolean domainSocket, Bootstrap bootstrap) {
        this.client.getVertx().transport().configure(this.options, domainSocket, bootstrap);
    }

    private void applyHttp2ConnectionOptions(ChannelPipeline pipeline) {
        if (this.options.getIdleTimeout() > 0) {
            pipeline.addLast("idle", new IdleStateHandler(0L, 0L, this.options.getIdleTimeout(), this.options.getIdleTimeoutUnit()));
        }
    }

    private void applyHttp1xConnectionOptions(ChannelPipeline pipeline) {
        if (this.options.getIdleTimeout() > 0) {
            pipeline.addLast("idle", new IdleStateHandler(0L, 0L, this.options.getIdleTimeout(), this.options.getIdleTimeoutUnit()));
        }
        if (this.options.getLogActivity()) {
            pipeline.addLast("logging", new LoggingHandler());
        }
        pipeline.addLast("codec", new HttpClientCodec(this.options.getMaxInitialLineLength(), this.options.getMaxHeaderSize(), this.options.getMaxChunkSize(), false, false, this.options.getDecoderInitialBufferSize()));
        if (this.options.isTryUseCompression()) {
            pipeline.addLast("inflater", new HttpContentDecompressor(false));
        }
    }

    private void http1xConnected(ConnectionListener<HttpClientConnection> listener, HttpVersion version, SocketAddress server, boolean ssl, ContextInternal context, Channel ch2, long weight, Promise<ConnectResult<HttpClientConnection>> future) {
        boolean upgrade = version == HttpVersion.HTTP_2 && this.options.isHttp2ClearTextUpgrade();
        VertxHandler<Http1xClientConnection> clientHandler = VertxHandler.create(context, chctx -> {
            Http1xClientConnection conn = new Http1xClientConnection(listener, upgrade ? HttpVersion.HTTP_1_1 : version, this.client, this.endpointMetric, chctx, ssl, server, context, this.metrics);
            if (this.metrics != null) {
                context.executeFromIO(v -> {
                    Object socketMetric = this.metrics.connected(conn.remoteAddress(), conn.remoteName());
                    conn.metric(socketMetric);
                    this.metrics.endpointConnected(this.endpointMetric, socketMetric);
                });
            }
            return conn;
        });
        clientHandler.addHandler(conn -> {
            if (upgrade) {
                future.complete(new ConnectResult(new Http2UpgradedClientConnection(this.client, conn), 1L, this.http2Weight));
            } else {
                future.complete(new ConnectResult(conn, this.http1MaxConcurrency, this.http1Weight));
            }
        });
        clientHandler.removeHandler(conn2 -> {
            listener.onEvict();
        });
        ch2.pipeline().addLast("handler", clientHandler);
    }

    private void http2Connected(ConnectionListener<HttpClientConnection> listener, ContextInternal context, Channel ch2, Promise<ConnectResult<HttpClientConnection>> future) {
        try {
            VertxHttp2ConnectionHandler<Http2ClientConnection> clientHandler = Http2ClientConnection.createHttp2ConnectionHandler(this.client, this.endpointMetric, listener, context, null, (conn, concurrency) -> {
                future.complete(new ConnectResult(conn, concurrency.longValue(), this.http2Weight));
            });
            ch2.pipeline().addLast("handler", clientHandler);
            ch2.flush();
        } catch (Exception e) {
            connectFailed(ch2, listener, e, future);
        }
    }

    private void connectFailed(Channel ch2, ConnectionListener<HttpClientConnection> listener, Throwable t, Promise<ConnectResult<HttpClientConnection>> future) {
        if (ch2 != null) {
            try {
                ch2.close();
            } catch (Exception e) {
            }
        }
        future.tryFail(t);
    }
}
