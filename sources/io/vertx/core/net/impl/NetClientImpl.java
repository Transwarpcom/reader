package io.vertx.core.net.impl;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.vertx.core.AsyncResult;
import io.vertx.core.Closeable;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.spi.metrics.Metrics;
import io.vertx.core.spi.metrics.MetricsProvider;
import io.vertx.core.spi.metrics.TCPMetrics;
import io.vertx.core.spi.metrics.VertxMetrics;
import java.io.FileNotFoundException;
import java.net.ConnectException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/NetClientImpl.class */
public class NetClientImpl implements MetricsProvider, NetClient {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) NetClientImpl.class);
    protected final int idleTimeout;
    private final TimeUnit idleTimeoutUnit;
    protected final boolean logEnabled;
    private final VertxInternal vertx;
    private final NetClientOptions options;
    protected final SSLHelper sslHelper;
    private final Map<Channel, NetSocketImpl> socketMap;
    private final Closeable closeHook;
    private final ContextInternal creatingContext;
    private final TCPMetrics metrics;
    private volatile boolean closed;

    public NetClientImpl(VertxInternal vertx, NetClientOptions options) {
        this(vertx, options, true);
    }

    public NetClientImpl(VertxInternal vertx, NetClientOptions options, boolean useCreatingContext) {
        this.socketMap = new ConcurrentHashMap();
        this.vertx = vertx;
        this.options = new NetClientOptions(options);
        this.sslHelper = new SSLHelper(options, options.getKeyCertOptions(), options.getTrustOptions());
        this.closeHook = completionHandler -> {
            close();
            completionHandler.handle(Future.succeededFuture());
        };
        if (useCreatingContext) {
            this.creatingContext = vertx.getContext();
            if (this.creatingContext != null) {
                if (this.creatingContext.isMultiThreadedWorkerContext()) {
                    throw new IllegalStateException("Cannot use NetClient in a multi-threaded worker verticle");
                }
                this.creatingContext.addCloseHook(this.closeHook);
            }
        } else {
            this.creatingContext = null;
        }
        VertxMetrics metrics = vertx.metricsSPI();
        this.metrics = metrics != null ? metrics.createNetClientMetrics(options) : null;
        this.logEnabled = options.getLogActivity();
        this.idleTimeout = options.getIdleTimeout();
        this.idleTimeoutUnit = options.getIdleTimeoutUnit();
    }

    protected void initChannel(ChannelPipeline pipeline) {
        if (this.logEnabled) {
            pipeline.addLast("logging", new LoggingHandler());
        }
        if (this.sslHelper.isSSL()) {
            pipeline.addLast("chunkedWriter", new ChunkedWriteHandler());
        }
        if (this.idleTimeout > 0) {
            pipeline.addLast("idle", new IdleStateHandler(0L, 0L, this.idleTimeout, this.idleTimeoutUnit));
        }
    }

    @Override // io.vertx.core.net.NetClient
    public synchronized NetClient connect(int port, String host, Handler<AsyncResult<NetSocket>> connectHandler) {
        connect(port, host, null, connectHandler);
        return this;
    }

    @Override // io.vertx.core.net.NetClient
    public NetClient connect(int port, String host, String serverName, Handler<AsyncResult<NetSocket>> connectHandler) {
        doConnect(SocketAddress.inetSocketAddress(port, host), serverName, connectHandler);
        return this;
    }

    @Override // io.vertx.core.net.NetClient
    public void close() {
        if (!this.closed) {
            for (NetSocketImpl sock : this.socketMap.values()) {
                sock.close();
            }
            if (this.creatingContext != null) {
                this.creatingContext.removeCloseHook(this.closeHook);
            }
            this.closed = true;
            if (this.metrics != null) {
                this.metrics.close();
            }
        }
    }

    @Override // io.vertx.core.metrics.Measured
    public boolean isMetricsEnabled() {
        return this.metrics != null && this.metrics.isEnabled();
    }

    @Override // io.vertx.core.spi.metrics.MetricsProvider
    public Metrics getMetrics() {
        return this.metrics;
    }

    private void checkClosed() {
        if (this.closed) {
            throw new IllegalStateException("Client is closed");
        }
    }

    private void applyConnectionOptions(boolean domainSocket, Bootstrap bootstrap) {
        this.vertx.transport().configure(this.options, domainSocket, bootstrap);
    }

    @Override // io.vertx.core.net.NetClient
    public NetClient connect(SocketAddress remoteAddress, String serverName, Handler<AsyncResult<NetSocket>> connectHandler) {
        doConnect(remoteAddress, serverName, connectHandler);
        return this;
    }

    @Override // io.vertx.core.net.NetClient
    public NetClient connect(SocketAddress remoteAddress, Handler<AsyncResult<NetSocket>> connectHandler) {
        doConnect(remoteAddress, null, connectHandler);
        return this;
    }

    protected void doConnect(SocketAddress remoteAddress, String serverName, Handler<AsyncResult<NetSocket>> connectHandler) {
        doConnect(remoteAddress, serverName, connectHandler, this.options.getReconnectAttempts());
    }

    protected void doConnect(SocketAddress remoteAddress, String serverName, Handler<AsyncResult<NetSocket>> connectHandler, int remainingAttempts) {
        checkClosed();
        Objects.requireNonNull(connectHandler, "No null connectHandler accepted");
        ContextInternal context = this.vertx.getOrCreateContext();
        this.sslHelper.validate(this.vertx);
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(context.nettyEventLoop());
        applyConnectionOptions(remoteAddress.path() != null, bootstrap);
        ChannelProvider channelProvider = new ChannelProvider(bootstrap, this.sslHelper, context, this.options.getProxyOptions());
        Handler<AsyncResult<Channel>> channelHandler = res -> {
            if (res.succeeded()) {
                Channel ch2 = (Channel) res.result();
                connected(context, ch2, connectHandler, remoteAddress);
                return;
            }
            Throwable cause = res.cause();
            boolean connectError = (cause instanceof ConnectException) || (cause instanceof FileNotFoundException);
            if (connectError && (remainingAttempts > 0 || remainingAttempts == -1)) {
                context.executeFromIO(v -> {
                    log.debug("Failed to create connection. Will retry in " + this.options.getReconnectInterval() + " milliseconds");
                    this.vertx.setTimer(this.options.getReconnectInterval(), tid -> {
                        doConnect(remoteAddress, serverName, connectHandler, remainingAttempts == -1 ? remainingAttempts : remainingAttempts - 1);
                    });
                });
            } else {
                failed(context, null, cause, connectHandler);
            }
        };
        SocketAddress peerAddress = remoteAddress;
        String peerHost = peerAddress.host();
        if (peerHost != null && peerHost.endsWith(".")) {
            peerAddress = SocketAddress.inetSocketAddress(peerAddress.port(), peerHost.substring(0, peerHost.length() - 1));
        }
        channelProvider.connect(remoteAddress, peerAddress, serverName, this.sslHelper.isSSL(), channelHandler);
    }

    private void connected(ContextInternal context, Channel ch2, Handler<AsyncResult<NetSocket>> connectHandler, SocketAddress remoteAddress) {
        initChannel(ch2.pipeline());
        VertxHandler<NetSocketImpl> handler = VertxHandler.create(context, ctx -> {
            return new NetSocketImpl(this.vertx, ctx, remoteAddress, context, this.sslHelper, this.metrics);
        });
        handler.addHandler(sock -> {
            this.socketMap.put(ch2, sock);
            context.executeFromIO(v -> {
                if (this.metrics != null) {
                    sock.metric(this.metrics.connected(sock.remoteAddress(), sock.remoteName()));
                }
                sock.registerEventBusHandler();
                connectHandler.handle(Future.succeededFuture(sock));
            });
        });
        handler.removeHandler(conn -> {
            this.socketMap.remove(ch2);
        });
        ch2.pipeline().addLast("handler", handler);
    }

    private void failed(ContextInternal context, Channel ch2, Throwable th, Handler<AsyncResult<NetSocket>> connectHandler) {
        if (ch2 != null) {
            ch2.close();
        }
        context.runOnContext(v -> {
            doFailed(connectHandler, th);
        });
    }

    private void doFailed(Handler<AsyncResult<NetSocket>> connectHandler, Throwable th) {
        connectHandler.handle(Future.failedFuture(th));
    }

    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }
}
