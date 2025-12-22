package io.vertx.core.net.impl;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SniHandler;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.vertx.core.AsyncResult;
import io.vertx.core.Closeable;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Handler;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.spi.metrics.Metrics;
import io.vertx.core.spi.metrics.MetricsProvider;
import io.vertx.core.spi.metrics.TCPMetrics;
import io.vertx.core.spi.metrics.VertxMetrics;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/NetServerImpl.class */
public class NetServerImpl implements Closeable, MetricsProvider, NetServer {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) NetServerImpl.class);
    protected final VertxInternal vertx;
    protected final NetServerOptions options;
    protected final ContextInternal creatingContext;
    protected final SSLHelper sslHelper;
    protected final boolean logEnabled;
    private ChannelGroup serverChannelGroup;
    private volatile boolean listening;
    private Handler<NetSocket> registeredHandler;
    private volatile ServerID id;
    private NetServerImpl actualServer;
    private Future<Channel> bindFuture;
    private volatile int actualPort;
    private ContextInternal listenContext;
    private TCPMetrics metrics;
    private Handler<NetSocket> handler;
    private Handler<Void> endHandler;
    private Handler<Throwable> exceptionHandler;
    private final Map<Channel, NetSocketImpl> socketMap = new ConcurrentHashMap();
    private final VertxEventLoopGroup availableWorkers = new VertxEventLoopGroup();
    private final HandlerManager<Handlers> handlerManager = new HandlerManager<>(this.availableWorkers);
    private final NetSocketStream connectStream = new NetSocketStream();
    private long demand = Long.MAX_VALUE;

    public NetServerImpl(VertxInternal vertx, NetServerOptions options) {
        this.vertx = vertx;
        this.options = new NetServerOptions(options);
        this.sslHelper = new SSLHelper(options, options.getKeyCertOptions(), options.getTrustOptions());
        this.creatingContext = vertx.getContext();
        this.logEnabled = options.getLogActivity();
        if (this.creatingContext != null) {
            if (this.creatingContext.isMultiThreadedWorkerContext()) {
                throw new IllegalStateException("Cannot use NetServer in a multi-threaded worker verticle");
            }
            this.creatingContext.addCloseHook(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void pauseAccepting() {
        this.demand = 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void resumeAccepting() {
        this.demand = Long.MAX_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void fetchAccepting(long amount) {
        if (amount > 0) {
            this.demand += amount;
            if (this.demand < 0) {
                this.demand = Long.MAX_VALUE;
            }
        }
    }

    protected synchronized boolean accept() {
        boolean accept = this.demand > 0;
        if (accept && this.demand != Long.MAX_VALUE) {
            this.demand--;
        }
        return accept;
    }

    protected boolean isListening() {
        return this.listening;
    }

    @Override // io.vertx.core.net.NetServer
    public synchronized Handler<NetSocket> connectHandler() {
        return this.handler;
    }

    @Override // io.vertx.core.net.NetServer
    public synchronized NetServer connectHandler(Handler<NetSocket> handler) {
        if (isListening()) {
            throw new IllegalStateException("Cannot set connectHandler when server is listening");
        }
        this.handler = handler;
        return this;
    }

    @Override // io.vertx.core.net.NetServer
    public synchronized NetServer exceptionHandler(Handler<Throwable> handler) {
        if (isListening()) {
            throw new IllegalStateException("Cannot set exceptionHandler when server is listening");
        }
        this.exceptionHandler = handler;
        return this;
    }

    protected void initChannel(ChannelPipeline pipeline) {
        if (this.logEnabled) {
            pipeline.addLast("logging", new LoggingHandler());
        }
        if (this.sslHelper.isSSL()) {
            pipeline.addLast("chunkedWriter", new ChunkedWriteHandler());
        }
        if (this.options.getIdleTimeout() > 0) {
            pipeline.addLast("idle", new IdleStateHandler(0L, 0L, this.options.getIdleTimeout(), this.options.getIdleTimeoutUnit()));
        }
    }

    public synchronized void listen(Handler<NetSocket> handler, SocketAddress socketAddress, Handler<AsyncResult<Void>> listenHandler) {
        if (handler == null) {
            throw new IllegalStateException("Set connect handler first");
        }
        if (this.listening) {
            throw new IllegalStateException("Listen already called");
        }
        this.listening = true;
        this.listenContext = this.vertx.getOrCreateContext();
        this.registeredHandler = handler;
        Map<ServerID, NetServerImpl> sharedNetServers = this.vertx.sharedNetServers();
        synchronized (sharedNetServers) {
            this.actualPort = socketAddress.port();
            String hostOrPath = socketAddress.host() != null ? socketAddress.host() : socketAddress.path();
            this.id = new ServerID(this.actualPort, hostOrPath);
            NetServerImpl shared = sharedNetServers.get(this.id);
            if (shared == null || this.actualPort == 0) {
                this.serverChannelGroup = new DefaultChannelGroup("vertx-acceptor-channels", GlobalEventExecutor.INSTANCE);
                ServerBootstrap bootstrap = new ServerBootstrap();
                bootstrap.group((EventLoopGroup) this.availableWorkers);
                this.sslHelper.validate(this.vertx);
                bootstrap.childHandler(new ChannelInitializer<Channel>() { // from class: io.vertx.core.net.impl.NetServerImpl.1
                    @Override // io.netty.channel.ChannelInitializer
                    protected void initChannel(Channel ch2) {
                        if (NetServerImpl.this.accept()) {
                            HandlerHolder<Handlers> handler2 = NetServerImpl.this.handlerManager.chooseHandler(ch2.eventLoop());
                            if (handler2 != null) {
                                if (!NetServerImpl.this.sslHelper.isSSL()) {
                                    NetServerImpl.this.connected(handler2, ch2);
                                    return;
                                }
                                ch2.pipeline().addFirst("handshaker", new SslHandshakeCompletionHandler(ar -> {
                                    if (ar.succeeded()) {
                                        NetServerImpl.this.connected(handler2, ch2);
                                        return;
                                    }
                                    Handler<Throwable> exceptionHandler = ((Handlers) handler2.handler).exceptionHandler;
                                    if (exceptionHandler == null) {
                                        NetServerImpl.log.error("Client from origin " + ch2.remoteAddress() + " failed to connect over ssl: " + ar.cause());
                                    } else {
                                        handler2.context.executeFromIO(v -> {
                                            exceptionHandler.handle(ar.cause());
                                        });
                                    }
                                }));
                                if (NetServerImpl.this.options.isSni()) {
                                    SniHandler sniHandler = new SniHandler(NetServerImpl.this.sslHelper.serverNameMapper(NetServerImpl.this.vertx));
                                    ch2.pipeline().addFirst("ssl", sniHandler);
                                    return;
                                } else {
                                    SslHandler sslHandler = new SslHandler(NetServerImpl.this.sslHelper.createEngine(NetServerImpl.this.vertx));
                                    sslHandler.setHandshakeTimeout(NetServerImpl.this.sslHelper.getSslHandshakeTimeout(), NetServerImpl.this.sslHelper.getSslHandshakeTimeoutUnit());
                                    ch2.pipeline().addFirst("ssl", sslHandler);
                                    return;
                                }
                            }
                            return;
                        }
                        ch2.close();
                    }
                });
                applyConnectionOptions(socketAddress.path() != null, bootstrap);
                this.handlerManager.addHandler(new Handlers(this, handler, this.exceptionHandler), this.listenContext);
                try {
                    this.bindFuture = AsyncResolveConnectHelper.doBind(this.vertx, socketAddress, bootstrap);
                    this.bindFuture.addListener2(res -> {
                        if (res.isSuccess()) {
                            Channel ch2 = (Channel) res.getNow();
                            log.trace("Net server listening on " + hostOrPath + ":" + ch2.localAddress());
                            if (this.actualPort != -1) {
                                this.actualPort = ((InetSocketAddress) ch2.localAddress()).getPort();
                            }
                            this.id = new ServerID(this.actualPort, this.id.host);
                            this.serverChannelGroup.add(ch2);
                            synchronized (sharedNetServers) {
                                sharedNetServers.put(this.id, this);
                            }
                            VertxMetrics metrics = this.vertx.metricsSPI();
                            if (metrics != null) {
                                this.metrics = metrics.createNetServerMetrics(this.options, new SocketAddressImpl(this.id.port, this.id.host));
                                return;
                            }
                            return;
                        }
                        synchronized (sharedNetServers) {
                            sharedNetServers.remove(this.id);
                        }
                    });
                    if (this.actualPort != 0) {
                        sharedNetServers.put(this.id, this);
                    }
                    this.actualServer = this;
                } catch (Throwable t) {
                    if (listenHandler != null) {
                        this.vertx.runOnContext(v -> {
                            listenHandler.handle(io.vertx.core.Future.failedFuture(t));
                        });
                    } else {
                        log.error(t);
                    }
                    this.listening = false;
                    return;
                }
            } else {
                this.actualServer = shared;
                this.actualPort = shared.actualPort();
                VertxMetrics metrics = this.vertx.metricsSPI();
                this.metrics = metrics != null ? metrics.createNetServerMetrics(this.options, new SocketAddressImpl(this.id.port, this.id.host)) : null;
                this.actualServer.handlerManager.addHandler(new Handlers(this, handler, this.exceptionHandler), this.listenContext);
            }
            this.actualServer.bindFuture.addListener2(res2 -> {
                AsyncResult<Void> ares;
                if (listenHandler == null) {
                    if (!res2.isSuccess()) {
                        log.error("Failed to listen", res2.cause());
                        this.listening = false;
                        return;
                    }
                    return;
                }
                if (res2.isSuccess()) {
                    ares = io.vertx.core.Future.succeededFuture();
                } else {
                    this.listening = false;
                    ares = io.vertx.core.Future.failedFuture(res2.cause());
                }
                AsyncResult<Void> asyncResult = ares;
                this.listenContext.runOnContext(v2 -> {
                    listenHandler.handle(asyncResult);
                });
            });
        }
    }

    @Override // io.vertx.core.net.NetServer
    public synchronized void close() {
        close(null);
    }

    @Override // io.vertx.core.net.NetServer
    public NetServer listen(int port, String host) {
        return listen(port, host, (Handler<AsyncResult<NetServer>>) null);
    }

    @Override // io.vertx.core.net.NetServer
    public NetServer listen(int port) {
        return listen(port, NetServerOptions.DEFAULT_HOST, (Handler<AsyncResult<NetServer>>) null);
    }

    @Override // io.vertx.core.net.NetServer
    public NetServer listen(int port, Handler<AsyncResult<NetServer>> listenHandler) {
        return listen(port, NetServerOptions.DEFAULT_HOST, listenHandler);
    }

    @Override // io.vertx.core.net.NetServer
    public NetServer listen(SocketAddress localAddress) {
        return listen(localAddress, (Handler<AsyncResult<NetServer>>) null);
    }

    @Override // io.vertx.core.net.NetServer
    public synchronized NetServer listen(SocketAddress localAddress, Handler<AsyncResult<NetServer>> listenHandler) {
        listen(this.handler, localAddress, ar -> {
            if (listenHandler != null) {
                listenHandler.handle(ar.map((AsyncResult) this));
            }
        });
        return this;
    }

    @Override // io.vertx.core.net.NetServer
    public NetServer listen() {
        listen((Handler<AsyncResult<NetServer>>) null);
        return this;
    }

    @Override // io.vertx.core.net.NetServer
    public NetServer listen(int port, String host, Handler<AsyncResult<NetServer>> listenHandler) {
        return listen(SocketAddress.inetSocketAddress(port, host), listenHandler);
    }

    @Override // io.vertx.core.net.NetServer
    public synchronized NetServer listen(Handler<AsyncResult<NetServer>> listenHandler) {
        return listen(this.options.getPort(), this.options.getHost(), listenHandler);
    }

    @Override // io.vertx.core.net.NetServer
    public ReadStream<NetSocket> connectStream() {
        return this.connectStream;
    }

    public void closeAll(Handler<AsyncResult<Void>> handler) {
        List<Handlers> list = this.handlerManager.handlers();
        List<io.vertx.core.Future> futures = (List) list.stream().map(handlers -> {
            NetServer netServer = handlers.server;
            netServer.getClass();
            return io.vertx.core.Future.future((v1) -> {
                r0.close(v1);
            });
        }).collect(Collectors.toList());
        CompositeFuture fut = CompositeFuture.all(futures);
        fut.setHandler2(ar -> {
            handler.handle(ar.mapEmpty());
        });
    }

    @Override // io.vertx.core.Closeable
    public synchronized void close(Handler<AsyncResult<Void>> completionHandler) {
        Handler<AsyncResult<Void>> done;
        if (this.creatingContext != null) {
            this.creatingContext.removeCloseHook(this);
        }
        if (this.endHandler != null) {
            Handler<Void> handler = this.endHandler;
            this.endHandler = null;
            done = event -> {
                if (event.succeeded()) {
                    handler.handle(event.result());
                }
                if (completionHandler != null) {
                    completionHandler.handle(event);
                }
            };
        } else {
            done = completionHandler;
        }
        ContextInternal context = this.vertx.getOrCreateContext();
        if (!this.listening) {
            if (done != null) {
                executeCloseDone(context, done, null);
                return;
            }
            return;
        }
        this.listening = false;
        synchronized (this.vertx.sharedNetServers()) {
            if (this.actualServer != null) {
                this.actualServer.handlerManager.removeHandler(new Handlers(this, this.registeredHandler, this.exceptionHandler), this.listenContext);
                if (this.actualServer.handlerManager.hasHandlers()) {
                    if (done != null) {
                        executeCloseDone(context, done, null);
                    }
                } else {
                    this.actualServer.actualClose(context, done);
                }
            } else {
                Handler<AsyncResult<Void>> handler2 = done;
                context.runOnContext(v -> {
                    handler2.handle(io.vertx.core.Future.succeededFuture());
                });
            }
        }
    }

    public synchronized boolean isClosed() {
        return !this.listening;
    }

    @Override // io.vertx.core.net.NetServer
    public int actualPort() {
        return this.actualPort;
    }

    @Override // io.vertx.core.metrics.Measured
    public boolean isMetricsEnabled() {
        return this.metrics != null;
    }

    @Override // io.vertx.core.spi.metrics.MetricsProvider
    public Metrics getMetrics() {
        return this.metrics;
    }

    private void actualClose(ContextInternal closeContext, Handler<AsyncResult<Void>> done) {
        if (this.id != null) {
            this.vertx.sharedNetServers().remove(this.id);
        }
        ContextInternal currCon = this.vertx.getContext();
        for (NetSocketImpl sock : this.socketMap.values()) {
            sock.close();
        }
        if (this.vertx.getContext() != currCon) {
            throw new IllegalStateException("Context was changed");
        }
        ChannelGroupFuture fut = this.serverChannelGroup.close();
        fut.addListener2(cg -> {
            if (this.metrics != null) {
                this.metrics.close();
            }
            executeCloseDone(closeContext, done, fut.cause());
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connected(HandlerHolder<Handlers> handler, Channel ch2) {
        initChannel(ch2.pipeline());
        VertxHandler<NetSocketImpl> nh = VertxHandler.create(handler.context, ctx -> {
            return new NetSocketImpl(this.vertx, ctx, handler.context, this.sslHelper, this.metrics);
        });
        nh.addHandler(conn -> {
            this.socketMap.put(ch2, conn);
            handler.context.executeFromIO(v -> {
                if (this.metrics != null) {
                    conn.metric(this.metrics.connected(conn.remoteAddress(), conn.remoteName()));
                }
                conn.registerEventBusHandler();
                ((Handlers) handler.handler).connectionHandler.handle(conn);
            });
        });
        nh.removeHandler(conn2 -> {
            this.socketMap.remove(ch2);
        });
        ch2.pipeline().addLast("handler", nh);
    }

    private void executeCloseDone(ContextInternal closeContext, Handler<AsyncResult<Void>> done, Exception e) {
        if (done != null) {
            io.vertx.core.Future<Void> fut = e == null ? io.vertx.core.Future.succeededFuture() : io.vertx.core.Future.failedFuture(e);
            closeContext.runOnContext(v -> {
                done.handle(fut);
            });
        }
    }

    private void applyConnectionOptions(boolean domainSocket, ServerBootstrap bootstrap) {
        this.vertx.transport().configure(this.options, domainSocket, bootstrap);
    }

    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/NetServerImpl$NetSocketStream.class */
    private class NetSocketStream implements ReadStream<NetSocket> {
        private NetSocketStream() {
        }

        @Override // io.vertx.core.streams.ReadStream
        /* renamed from: endHandler, reason: avoid collision after fix types in other method */
        public /* bridge */ /* synthetic */ ReadStream<NetSocket> endHandler2(Handler handler) {
            return endHandler((Handler<Void>) handler);
        }

        @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
        public /* bridge */ /* synthetic */ ReadStream exceptionHandler(Handler handler) {
            return exceptionHandler((Handler<Throwable>) handler);
        }

        @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
        public /* bridge */ /* synthetic */ StreamBase exceptionHandler(Handler handler) {
            return exceptionHandler((Handler<Throwable>) handler);
        }

        @Override // io.vertx.core.streams.ReadStream
        /* renamed from: handler */
        public ReadStream<NetSocket> handler2(Handler<NetSocket> handler) {
            NetServerImpl.this.connectHandler(handler);
            return this;
        }

        @Override // io.vertx.core.streams.ReadStream
        /* renamed from: pause */
        public ReadStream<NetSocket> pause2() {
            NetServerImpl.this.pauseAccepting();
            return this;
        }

        @Override // io.vertx.core.streams.ReadStream
        /* renamed from: resume */
        public ReadStream<NetSocket> resume2() {
            NetServerImpl.this.resumeAccepting();
            return this;
        }

        @Override // io.vertx.core.streams.ReadStream
        /* renamed from: fetch */
        public ReadStream<NetSocket> fetch2(long amount) {
            NetServerImpl.this.fetchAccepting(amount);
            return this;
        }

        @Override // io.vertx.core.streams.ReadStream
        public ReadStream<NetSocket> endHandler(Handler<Void> handler) {
            synchronized (NetServerImpl.this) {
                NetServerImpl.this.endHandler = handler;
            }
            return this;
        }

        @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
        public NetSocketStream exceptionHandler(Handler<Throwable> handler) {
            return this;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/NetServerImpl$Handlers.class */
    static class Handlers {
        final NetServer server;
        final Handler<NetSocket> connectionHandler;
        final Handler<Throwable> exceptionHandler;

        public Handlers(NetServer server, Handler<NetSocket> connectionHandler, Handler<Throwable> exceptionHandler) {
            this.server = server;
            this.connectionHandler = connectionHandler;
            this.exceptionHandler = exceptionHandler;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Handlers that = (Handlers) o;
            return Objects.equals(this.connectionHandler, that.connectionHandler) && Objects.equals(this.exceptionHandler, that.exceptionHandler);
        }

        public int hashCode() {
            int result = 0;
            if (this.connectionHandler != null) {
                result = (31 * 0) + this.connectionHandler.hashCode();
            }
            if (this.exceptionHandler != null) {
                result = (31 * result) + this.exceptionHandler.hashCode();
            }
            return result;
        }
    }
}
