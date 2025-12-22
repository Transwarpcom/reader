package io.vertx.core.http.impl;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.vertx.core.AsyncResult;
import io.vertx.core.Closeable;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.net.impl.AsyncResolveConnectHelper;
import io.vertx.core.net.impl.ConnectionBase;
import io.vertx.core.net.impl.HandlerHolder;
import io.vertx.core.net.impl.HandlerManager;
import io.vertx.core.net.impl.SSLHelper;
import io.vertx.core.net.impl.ServerID;
import io.vertx.core.net.impl.VertxEventLoopGroup;
import io.vertx.core.spi.metrics.HttpServerMetrics;
import io.vertx.core.spi.metrics.Metrics;
import io.vertx.core.spi.metrics.MetricsProvider;
import io.vertx.core.spi.metrics.VertxMetrics;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/HttpServerImpl.class */
public class HttpServerImpl implements HttpServer, Closeable, MetricsProvider {
    private static final String DISABLE_H2C_PROP_NAME = "vertx.disableH2c";
    final HttpServerOptions options;
    final VertxInternal vertx;
    private final SSLHelper sslHelper;
    private final ContextInternal creatingContext;
    private final boolean disableH2c = Boolean.getBoolean(DISABLE_H2C_PROP_NAME);
    final Map<Channel, ConnectionBase> connectionMap = new ConcurrentHashMap();
    private final VertxEventLoopGroup availableWorkers = new VertxEventLoopGroup();
    private final HandlerManager<HttpHandlers> httpHandlerMgr = new HandlerManager<>(this.availableWorkers);
    private final HttpStreamHandler<ServerWebSocket> wsStream = new HttpStreamHandler<>();
    private final HttpStreamHandler<HttpServerRequest> requestStream = new HttpStreamHandler<>();
    private Handler<HttpConnection> connectionHandler;
    private ChannelGroup serverChannelGroup;
    private volatile boolean listening;
    private Future<Channel> bindFuture;
    private ServerID id;
    private HttpServerImpl actualServer;
    private volatile int actualPort;
    private ContextInternal listenContext;
    HttpServerMetrics metrics;
    private Handler<Throwable> exceptionHandler;
    static final Logger log = LoggerFactory.getLogger((Class<?>) HttpServerImpl.class);
    private static final Handler<Throwable> DEFAULT_EXCEPTION_HANDLER = t -> {
        log.trace("Connection failure", t);
    };
    private static final String FLASH_POLICY_HANDLER_PROP_NAME = "vertx.flashPolicyHandler";
    static final boolean USE_FLASH_POLICY_HANDLER = Boolean.getBoolean(FLASH_POLICY_HANDLER_PROP_NAME);
    private static final String DISABLE_WEBSOCKETS_PROP_NAME = "vertx.disableWebsockets";
    static final boolean DISABLE_WEBSOCKETS = Boolean.getBoolean(DISABLE_WEBSOCKETS_PROP_NAME);

    public HttpServerImpl(VertxInternal vertx, HttpServerOptions options) {
        this.options = new HttpServerOptions(options);
        this.vertx = vertx;
        this.creatingContext = vertx.getContext();
        if (this.creatingContext != null) {
            if (this.creatingContext.isMultiThreadedWorkerContext()) {
                throw new IllegalStateException("Cannot use HttpServer in a multi-threaded worker verticle");
            }
            this.creatingContext.addCloseHook(this);
        }
        this.sslHelper = new SSLHelper(options, options.getKeyCertOptions(), options.getTrustOptions());
    }

    @Override // io.vertx.core.http.HttpServer
    public synchronized HttpServer requestHandler(Handler<HttpServerRequest> handler) {
        this.requestStream.handler2(handler);
        return this;
    }

    @Override // io.vertx.core.http.HttpServer
    public ReadStream<HttpServerRequest> requestStream() {
        return this.requestStream;
    }

    @Override // io.vertx.core.http.HttpServer
    public HttpServer webSocketHandler(Handler<ServerWebSocket> handler) {
        webSocketStream().handler2(handler);
        return this;
    }

    @Override // io.vertx.core.http.HttpServer
    public HttpServer websocketHandler(Handler<ServerWebSocket> handler) {
        websocketStream().handler2(handler);
        return this;
    }

    @Override // io.vertx.core.http.HttpServer
    public Handler<HttpServerRequest> requestHandler() {
        return this.requestStream.handler();
    }

    @Override // io.vertx.core.http.HttpServer
    public synchronized HttpServer connectionHandler(Handler<HttpConnection> handler) {
        if (this.listening) {
            throw new IllegalStateException("Please set handler before server is listening");
        }
        this.connectionHandler = handler;
        return this;
    }

    @Override // io.vertx.core.http.HttpServer
    public synchronized HttpServer exceptionHandler(Handler<Throwable> handler) {
        if (this.listening) {
            throw new IllegalStateException("Please set handler before server is listening");
        }
        this.exceptionHandler = handler;
        return this;
    }

    @Override // io.vertx.core.http.HttpServer
    public Handler<ServerWebSocket> websocketHandler() {
        return this.wsStream.handler();
    }

    @Override // io.vertx.core.http.HttpServer
    public Handler<ServerWebSocket> webSocketHandler() {
        return this.wsStream.handler();
    }

    @Override // io.vertx.core.http.HttpServer
    public ReadStream<ServerWebSocket> websocketStream() {
        return this.wsStream;
    }

    @Override // io.vertx.core.http.HttpServer
    public ReadStream<ServerWebSocket> webSocketStream() {
        return this.wsStream;
    }

    @Override // io.vertx.core.http.HttpServer
    public HttpServer listen() {
        return listen(this.options.getPort(), this.options.getHost(), null);
    }

    @Override // io.vertx.core.http.HttpServer
    public HttpServer listen(Handler<AsyncResult<HttpServer>> listenHandler) {
        return listen(this.options.getPort(), this.options.getHost(), listenHandler);
    }

    @Override // io.vertx.core.http.HttpServer
    public HttpServer listen(int port, String host) {
        return listen(port, host, null);
    }

    @Override // io.vertx.core.http.HttpServer
    public HttpServer listen(int port) {
        return listen(port, NetServerOptions.DEFAULT_HOST, null);
    }

    @Override // io.vertx.core.http.HttpServer
    public HttpServer listen(int port, Handler<AsyncResult<HttpServer>> listenHandler) {
        return listen(port, NetServerOptions.DEFAULT_HOST, listenHandler);
    }

    @Override // io.vertx.core.http.HttpServer
    public HttpServer listen(int port, String host, Handler<AsyncResult<HttpServer>> listenHandler) {
        return listen(SocketAddress.inetSocketAddress(port, host), listenHandler);
    }

    private ChannelHandler childHandler(SocketAddress address, String serverOrigin) {
        VertxMetrics vertxMetrics = this.vertx.metricsSPI();
        this.metrics = vertxMetrics != null ? vertxMetrics.createHttpServerMetrics(this.options, address) : null;
        VertxInternal vertxInternal = this.vertx;
        SSLHelper sSLHelper = this.sslHelper;
        HttpServerOptions httpServerOptions = this.options;
        HttpServerMetrics httpServerMetrics = this.metrics;
        boolean z = this.disableH2c;
        HandlerManager<HttpHandlers> handlerManager = this.httpHandlerMgr;
        handlerManager.getClass();
        return new HttpServerChannelInitializer(vertxInternal, sSLHelper, httpServerOptions, serverOrigin, httpServerMetrics, z, handlerManager::chooseHandler, eventLoop -> {
            HandlerHolder<HttpHandlers> holder = this.httpHandlerMgr.chooseHandler(eventLoop);
            if (holder != null && holder.handler.exceptionHandler != null) {
                return new HandlerHolder(holder.context, holder.handler.exceptionHandler);
            }
            return null;
        }) { // from class: io.vertx.core.http.impl.HttpServerImpl.1
            @Override // io.vertx.core.http.impl.HttpServerChannelInitializer, io.netty.channel.ChannelInitializer
            protected void initChannel(Channel ch2) {
                if (!HttpServerImpl.this.requestStream.accept() || !HttpServerImpl.this.wsStream.accept()) {
                    ch2.close();
                } else {
                    super.initChannel(ch2);
                }
            }
        };
    }

    @Override // io.vertx.core.http.HttpServer
    public synchronized HttpServer listen(SocketAddress address, Handler<AsyncResult<HttpServer>> listenHandler) {
        if (this.requestStream.handler() == null && this.wsStream.handler() == null) {
            throw new IllegalStateException("Set request or websocket handler first");
        }
        if (this.listening) {
            throw new IllegalStateException("Already listening");
        }
        this.listenContext = this.vertx.getOrCreateContext();
        this.listening = true;
        String host = address.host() != null ? address.host() : "localhost";
        int port = address.port();
        List<HttpVersion> applicationProtocols = this.options.getAlpnVersions();
        if (this.listenContext.isWorkerContext()) {
            applicationProtocols = (List) applicationProtocols.stream().filter(v -> {
                return v != HttpVersion.HTTP_2;
            }).collect(Collectors.toList());
        }
        this.sslHelper.setApplicationProtocols(applicationProtocols);
        Map<ServerID, HttpServerImpl> sharedHttpServers = this.vertx.sharedHttpServers();
        synchronized (sharedHttpServers) {
            this.actualPort = port;
            this.id = new ServerID(port, host);
            HttpServerImpl shared = sharedHttpServers.get(this.id);
            if (shared == null || port == 0) {
                this.serverChannelGroup = new DefaultChannelGroup("vertx-acceptor-channels", GlobalEventExecutor.INSTANCE);
                ServerBootstrap bootstrap = new ServerBootstrap();
                bootstrap.group(this.vertx.getAcceptorEventLoopGroup(), this.availableWorkers);
                applyConnectionOptions(address.path() != null, bootstrap);
                this.sslHelper.validate(this.vertx);
                String serverOrigin = (this.options.isSsl() ? "https" : "http") + "://" + host + ":" + port;
                bootstrap.childHandler(childHandler(address, serverOrigin));
                addHandlers(this, this.listenContext);
                try {
                    this.bindFuture = AsyncResolveConnectHelper.doBind(this.vertx, address, bootstrap);
                    this.bindFuture.addListener2(res -> {
                        if (!res.isSuccess()) {
                            synchronized (sharedHttpServers) {
                                sharedHttpServers.remove(this.id);
                            }
                            return;
                        }
                        Channel serverChannel = (Channel) res.getNow();
                        if (serverChannel.localAddress() instanceof InetSocketAddress) {
                            this.actualPort = ((InetSocketAddress) serverChannel.localAddress()).getPort();
                        } else {
                            this.actualPort = address.port();
                        }
                        this.serverChannelGroup.add(serverChannel);
                    });
                    sharedHttpServers.put(this.id, this);
                    this.actualServer = this;
                } catch (Throwable t) {
                    if (listenHandler != null) {
                        this.vertx.runOnContext(v2 -> {
                            listenHandler.handle(io.vertx.core.Future.failedFuture(t));
                        });
                    } else {
                        log.error(t);
                    }
                    this.listening = false;
                    return this;
                }
            } else {
                this.actualServer = shared;
                this.actualPort = shared.actualPort;
                addHandlers(this.actualServer, this.listenContext);
                VertxMetrics metrics = this.vertx.metricsSPI();
                this.metrics = metrics != null ? metrics.createHttpServerMetrics(this.options, address) : null;
            }
            this.actualServer.bindFuture.addListener2(future -> {
                AsyncResult<HttpServer> res2;
                if (listenHandler != null) {
                    if (future.isSuccess()) {
                        res2 = io.vertx.core.Future.succeededFuture(this);
                    } else {
                        res2 = io.vertx.core.Future.failedFuture(future.cause());
                        this.listening = false;
                    }
                    AsyncResult<HttpServer> asyncResult = res2;
                    this.listenContext.runOnContext(v3 -> {
                        listenHandler.handle(asyncResult);
                    });
                    return;
                }
                if (!future.isSuccess()) {
                    this.listening = false;
                    if (this.metrics != null) {
                        this.metrics.close();
                        this.metrics = null;
                    }
                    log.error(future.cause());
                }
            });
        }
        return this;
    }

    public void closeAll(Handler<AsyncResult<Void>> handler) {
        List<HttpHandlers> list = this.httpHandlerMgr.handlers();
        List<io.vertx.core.Future> futures = (List) list.stream().map(handlers -> {
            HttpServerImpl httpServerImpl = handlers.server;
            httpServerImpl.getClass();
            return io.vertx.core.Future.future((v1) -> {
                r0.close(v1);
            });
        }).collect(Collectors.toList());
        CompositeFuture fut = CompositeFuture.all(futures);
        fut.setHandler2(ar -> {
            handler.handle(ar.mapEmpty());
        });
    }

    @Override // io.vertx.core.http.HttpServer
    public void close() {
        close(null);
    }

    @Override // io.vertx.core.http.HttpServer, io.vertx.core.Closeable
    public synchronized void close(Handler<AsyncResult<Void>> done) {
        if (this.wsStream.endHandler() != null || this.requestStream.endHandler() != null) {
            Handler<Void> wsEndHandler = this.wsStream.endHandler();
            this.wsStream.endHandler(null);
            Handler<Void> requestEndHandler = this.requestStream.endHandler();
            this.requestStream.endHandler(null);
            done = event -> {
                if (event.succeeded()) {
                    if (wsEndHandler != null) {
                        wsEndHandler.handle(event.result());
                    }
                    if (requestEndHandler != null) {
                        requestEndHandler.handle(event.result());
                    }
                }
                if (done != null) {
                    done.handle(event);
                }
            };
        }
        ContextInternal context = this.vertx.getOrCreateContext();
        if (!this.listening) {
            executeCloseDone(context, done, null);
            return;
        }
        this.listening = false;
        synchronized (this.vertx.sharedHttpServers()) {
            if (this.actualServer != null) {
                this.actualServer.httpHandlerMgr.removeHandler(new HttpHandlers(this, this.requestStream.handler(), this.wsStream.handler(), this.connectionHandler, this.exceptionHandler == null ? DEFAULT_EXCEPTION_HANDLER : this.exceptionHandler), this.listenContext);
                if (this.actualServer.httpHandlerMgr.hasHandlers()) {
                    if (done != null) {
                        executeCloseDone(context, done, null);
                    }
                } else {
                    this.actualServer.actualClose(context, done);
                }
            } else {
                executeCloseDone(context, done, null);
            }
        }
        if (this.creatingContext != null) {
            this.creatingContext.removeCloseHook(this);
        }
    }

    public synchronized boolean isClosed() {
        return !this.listening;
    }

    @Override // io.vertx.core.spi.metrics.MetricsProvider
    public Metrics getMetrics() {
        return this.metrics;
    }

    @Override // io.vertx.core.metrics.Measured
    public boolean isMetricsEnabled() {
        return this.metrics != null;
    }

    public SSLHelper getSslHelper() {
        return this.sslHelper;
    }

    private void applyConnectionOptions(boolean domainSocket, ServerBootstrap bootstrap) {
        this.vertx.transport().configure(this.options, domainSocket, bootstrap);
    }

    private void addHandlers(HttpServerImpl server, ContextInternal context) {
        server.httpHandlerMgr.addHandler(new HttpHandlers(this, this.requestStream.handler(), this.wsStream.handler(), this.connectionHandler, this.exceptionHandler == null ? DEFAULT_EXCEPTION_HANDLER : this.exceptionHandler), context);
    }

    private void actualClose(ContextInternal closeContext, Handler<AsyncResult<Void>> done) {
        if (this.id != null) {
            this.vertx.sharedHttpServers().remove(this.id);
        }
        ContextInternal currCon = this.vertx.getContext();
        for (ConnectionBase conn : this.connectionMap.values()) {
            conn.close();
        }
        if (this.vertx.getContext() != currCon) {
            throw new IllegalStateException("Context was changed");
        }
        if (this.metrics != null) {
            this.metrics.close();
        }
        ChannelGroupFuture fut = this.serverChannelGroup.close();
        fut.addListener2(cgf -> {
            executeCloseDone(closeContext, done, fut.cause());
        });
    }

    @Override // io.vertx.core.http.HttpServer
    public int actualPort() {
        return this.actualPort;
    }

    private void executeCloseDone(ContextInternal closeContext, Handler<AsyncResult<Void>> done, Exception e) {
        if (done != null) {
            io.vertx.core.Future<Void> fut = e != null ? io.vertx.core.Future.failedFuture(e) : io.vertx.core.Future.succeededFuture();
            closeContext.runOnContext(v -> {
                done.handle(fut);
            });
        }
    }

    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/HttpServerImpl$HttpStreamHandler.class */
    class HttpStreamHandler<C extends ReadStream<Buffer>> implements ReadStream<C> {
        private Handler<C> handler;
        private long demand = Long.MAX_VALUE;
        private Handler<Void> endHandler;

        HttpStreamHandler() {
        }

        @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
        public /* bridge */ /* synthetic */ StreamBase exceptionHandler(Handler handler) {
            return exceptionHandler((Handler<Throwable>) handler);
        }

        Handler<C> handler() {
            Handler<C> handler;
            synchronized (HttpServerImpl.this) {
                handler = this.handler;
            }
            return handler;
        }

        boolean accept() {
            boolean accept;
            synchronized (HttpServerImpl.this) {
                accept = this.demand > 0;
                if (accept && this.demand != Long.MAX_VALUE) {
                    this.demand--;
                }
            }
            return accept;
        }

        Handler<Void> endHandler() {
            Handler<Void> handler;
            synchronized (HttpServerImpl.this) {
                handler = this.endHandler;
            }
            return handler;
        }

        @Override // io.vertx.core.streams.ReadStream
        /* renamed from: handler */
        public ReadStream handler2(Handler<C> handler) {
            synchronized (HttpServerImpl.this) {
                if (HttpServerImpl.this.listening) {
                    throw new IllegalStateException("Please set handler before server is listening");
                }
                this.handler = handler;
            }
            return this;
        }

        @Override // io.vertx.core.streams.ReadStream
        /* renamed from: pause */
        public ReadStream pause2() {
            synchronized (HttpServerImpl.this) {
                this.demand = 0L;
            }
            return this;
        }

        @Override // io.vertx.core.streams.ReadStream
        /* renamed from: fetch */
        public ReadStream fetch2(long amount) {
            if (amount > 0) {
                this.demand += amount;
                if (this.demand < 0) {
                    this.demand = Long.MAX_VALUE;
                }
            }
            return this;
        }

        @Override // io.vertx.core.streams.ReadStream
        /* renamed from: resume */
        public ReadStream resume2() {
            synchronized (HttpServerImpl.this) {
                this.demand = Long.MAX_VALUE;
            }
            return this;
        }

        @Override // io.vertx.core.streams.ReadStream
        public ReadStream endHandler(Handler<Void> endHandler) {
            synchronized (HttpServerImpl.this) {
                this.endHandler = endHandler;
            }
            return this;
        }

        @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
        public ReadStream exceptionHandler(Handler<Throwable> handler) {
            return this;
        }
    }
}
