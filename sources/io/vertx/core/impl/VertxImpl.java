package io.vertx.core.impl;

import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.resolver.AddressResolverGroup;
import io.netty.util.ResourceLeakDetector;
import io.netty.util.concurrent.GenericFutureListener;
import io.vertx.core.AsyncResult;
import io.vertx.core.Closeable;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.ServiceHelper;
import io.vertx.core.TimeoutStream;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.datagram.DatagramSocket;
import io.vertx.core.datagram.DatagramSocketOptions;
import io.vertx.core.datagram.impl.DatagramSocketImpl;
import io.vertx.core.dns.AddressResolverOptions;
import io.vertx.core.dns.DnsClient;
import io.vertx.core.dns.DnsClientOptions;
import io.vertx.core.dns.impl.DnsClientImpl;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.impl.EventBusImpl;
import io.vertx.core.eventbus.impl.clustered.ClusteredEventBus;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.impl.FileResolver;
import io.vertx.core.file.impl.FileSystemImpl;
import io.vertx.core.file.impl.WindowsFileSystem;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.impl.HttpClientImpl;
import io.vertx.core.http.impl.HttpServerImpl;
import io.vertx.core.impl.resolver.DnsResolverProvider;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.impl.NetClientImpl;
import io.vertx.core.net.impl.NetServerImpl;
import io.vertx.core.net.impl.ServerID;
import io.vertx.core.net.impl.transport.Transport;
import io.vertx.core.shareddata.SharedData;
import io.vertx.core.shareddata.impl.SharedDataImpl;
import io.vertx.core.spi.VerticleFactory;
import io.vertx.core.spi.VertxMetricsFactory;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.core.spi.metrics.Metrics;
import io.vertx.core.spi.metrics.MetricsProvider;
import io.vertx.core.spi.metrics.PoolMetrics;
import io.vertx.core.spi.metrics.VertxMetrics;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/VertxImpl.class */
public class VertxImpl implements VertxInternal, MetricsProvider {
    private static final String CLUSTER_MAP_NAME = "__vertx.haInfo";
    private final SharedData sharedData;
    private final VertxMetrics metrics;
    private final ClusterManager clusterManager;
    private final DeploymentManager deploymentManager;
    private final FileResolver fileResolver;
    final WorkerPool workerPool;
    final WorkerPool internalBlockingPool;
    private final ThreadFactory eventLoopThreadFactory;
    private final EventLoopGroup eventLoopGroup;
    private final EventLoopGroup acceptorEventLoopGroup;
    private final BlockedThreadChecker checker;
    private final AddressResolver addressResolver;
    private final AddressResolverOptions addressResolverOptions;
    private final EventBus eventBus;
    private volatile HAManager haManager;
    private boolean closed;
    private volatile Handler<Throwable> exceptionHandler;
    private final Map<String, SharedWorkerPool> namedWorkerPools;
    private final int defaultWorkerPoolSize;
    private final long maxWorkerExecTime;
    private final TimeUnit maxWorkerExecTimeUnit;
    private final long maxEventLoopExTime;
    private final TimeUnit maxEventLoopExecTimeUnit;
    private final CloseHooks closeHooks;
    private final Transport transport;
    private static final Logger log = LoggerFactory.getLogger((Class<?>) VertxImpl.class);
    private static final String NETTY_IO_RATIO_PROPERTY_NAME = "vertx.nettyIORatio";
    private static final int NETTY_IO_RATIO = Integer.getInteger(NETTY_IO_RATIO_PROPERTY_NAME, 50).intValue();
    private final FileSystem fileSystem = getFileSystem();
    private final ConcurrentMap<Long, InternalTimerHandler> timeouts = new ConcurrentHashMap();
    private final AtomicLong timeoutCounter = new AtomicLong(0);
    private final Map<ServerID, HttpServerImpl> sharedHttpServers = new HashMap();
    private final Map<ServerID, NetServerImpl> sharedNetServers = new HashMap();

    static {
        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.DISABLED);
        System.setProperty("io.netty.noJdkZlibDecoder", "false");
    }

    static VertxImpl vertx(VertxOptions options, Transport transport) {
        VertxImpl vertx = new VertxImpl(options, transport);
        vertx.init();
        return vertx;
    }

    static void clusteredVertx(VertxOptions options, Transport transport, Handler<AsyncResult<Vertx>> resultHandler) {
        VertxImpl vertx = new VertxImpl(options, transport);
        vertx.joinCluster(options, resultHandler);
    }

    private VertxImpl(VertxOptions options, Transport transport) {
        if (Vertx.currentContext() != null) {
            log.warn("You're already on a Vert.x context, are you sure you want to create a new Vertx instance?");
        }
        this.closeHooks = new CloseHooks(log);
        this.checker = new BlockedThreadChecker(options.getBlockedThreadCheckInterval(), options.getBlockedThreadCheckIntervalUnit(), options.getWarningExceptionTime(), options.getWarningExceptionTimeUnit());
        this.maxEventLoopExTime = options.getMaxEventLoopExecuteTime();
        this.maxEventLoopExecTimeUnit = options.getMaxEventLoopExecuteTimeUnit();
        this.eventLoopThreadFactory = new VertxThreadFactory("vert.x-eventloop-thread-", this.checker, false, this.maxEventLoopExTime, this.maxEventLoopExecTimeUnit);
        this.eventLoopGroup = transport.eventLoopGroup(1, options.getEventLoopPoolSize(), this.eventLoopThreadFactory, NETTY_IO_RATIO);
        ThreadFactory acceptorEventLoopThreadFactory = new VertxThreadFactory("vert.x-acceptor-thread-", this.checker, false, options.getMaxEventLoopExecuteTime(), options.getMaxEventLoopExecuteTimeUnit());
        this.acceptorEventLoopGroup = transport.eventLoopGroup(0, 1, acceptorEventLoopThreadFactory, 100);
        this.metrics = initialiseMetrics(options);
        int workerPoolSize = options.getWorkerPoolSize();
        ExecutorService workerExec = new ThreadPoolExecutor(workerPoolSize, workerPoolSize, 0L, TimeUnit.MILLISECONDS, new LinkedTransferQueue(), new VertxThreadFactory("vert.x-worker-thread-", this.checker, true, options.getMaxWorkerExecuteTime(), options.getMaxWorkerExecuteTimeUnit()));
        PoolMetrics workerPoolMetrics = this.metrics != null ? this.metrics.createPoolMetrics("worker", "vert.x-worker-thread", options.getWorkerPoolSize()) : null;
        ExecutorService internalBlockingExec = Executors.newFixedThreadPool(options.getInternalBlockingPoolSize(), new VertxThreadFactory("vert.x-internal-blocking-", this.checker, true, options.getMaxWorkerExecuteTime(), options.getMaxWorkerExecuteTimeUnit()));
        PoolMetrics internalBlockingPoolMetrics = this.metrics != null ? this.metrics.createPoolMetrics("worker", "vert.x-internal-blocking", options.getInternalBlockingPoolSize()) : null;
        this.internalBlockingPool = new WorkerPool(internalBlockingExec, internalBlockingPoolMetrics);
        this.namedWorkerPools = new HashMap();
        this.workerPool = new WorkerPool(workerExec, workerPoolMetrics);
        this.defaultWorkerPoolSize = options.getWorkerPoolSize();
        this.maxWorkerExecTime = options.getMaxWorkerExecuteTime();
        this.maxWorkerExecTimeUnit = options.getMaxWorkerExecuteTimeUnit();
        this.transport = transport;
        this.fileResolver = new FileResolver(options.getFileSystemOptions());
        this.addressResolverOptions = options.getAddressResolverOptions();
        this.addressResolver = new AddressResolver(this, options.getAddressResolverOptions());
        this.deploymentManager = new DeploymentManager(this);
        if (options.getEventBusOptions().isClustered()) {
            this.clusterManager = getClusterManager(options);
            this.eventBus = new ClusteredEventBus(this, options, this.clusterManager);
        } else {
            this.clusterManager = null;
            this.eventBus = new EventBusImpl(this);
        }
        this.sharedData = new SharedDataImpl(this, this.clusterManager);
    }

    private void init() {
        this.eventBus.start(ar -> {
        });
        if (this.metrics != null) {
            this.metrics.vertxCreated(this);
        }
    }

    private void joinCluster(VertxOptions options, Handler<AsyncResult<Vertx>> resultHandler) {
        this.clusterManager.setVertx(this);
        this.clusterManager.join(ar -> {
            if (ar.succeeded()) {
                createHaManager(options, resultHandler);
            } else {
                log.error("Failed to join cluster", ar.cause());
                resultHandler.handle(Future.failedFuture(ar.cause()));
            }
        });
    }

    private void createHaManager(VertxOptions options, Handler<AsyncResult<Vertx>> resultHandler) {
        executeBlocking(fut -> {
            fut.complete(this.clusterManager.getSyncMap(CLUSTER_MAP_NAME));
        }, false, ar -> {
            if (ar.succeeded()) {
                Map<String, String> clusterMap = (Map) ar.result();
                this.haManager = new HAManager(this, this.deploymentManager, this.clusterManager, clusterMap, options.getQuorumSize(), options.getHAGroup(), options.isHAEnabled());
                startEventBus(resultHandler);
            } else {
                log.error("Failed to start HAManager", ar.cause());
                resultHandler.handle(Future.failedFuture(ar.cause()));
            }
        });
    }

    private void startEventBus(Handler<AsyncResult<Vertx>> resultHandler) {
        this.eventBus.start(ar -> {
            if (ar.succeeded()) {
                initializeHaManager(resultHandler);
            } else {
                log.error("Failed to start event bus", ar.cause());
                resultHandler.handle(Future.failedFuture(ar.cause()));
            }
        });
    }

    private void initializeHaManager(Handler<AsyncResult<Vertx>> resultHandler) {
        executeBlocking(fut -> {
            this.haManager.init();
            fut.complete();
        }, false, ar -> {
            if (ar.succeeded()) {
                if (this.metrics != null) {
                    this.metrics.vertxCreated(this);
                }
                resultHandler.handle(Future.succeededFuture(this));
            } else {
                log.error("Failed to initialize HAManager", ar.cause());
                resultHandler.handle(Future.failedFuture(ar.cause()));
            }
        });
    }

    protected FileSystem getFileSystem() {
        return Utils.isWindows() ? new WindowsFileSystem(this) : new FileSystemImpl(this);
    }

    @Override // io.vertx.core.impl.VertxInternal
    public long maxEventLoopExecTime() {
        return this.maxEventLoopExTime;
    }

    @Override // io.vertx.core.impl.VertxInternal
    public TimeUnit maxEventLoopExecTimeUnit() {
        return this.maxEventLoopExecTimeUnit;
    }

    @Override // io.vertx.core.Vertx
    public DatagramSocket createDatagramSocket(DatagramSocketOptions options) {
        return DatagramSocketImpl.create(this, options);
    }

    @Override // io.vertx.core.Vertx
    public DatagramSocket createDatagramSocket() {
        return createDatagramSocket(new DatagramSocketOptions());
    }

    @Override // io.vertx.core.Vertx
    public NetServer createNetServer(NetServerOptions options) {
        return new NetServerImpl(this, options);
    }

    @Override // io.vertx.core.Vertx
    public NetServer createNetServer() {
        return createNetServer(new NetServerOptions());
    }

    @Override // io.vertx.core.Vertx
    public NetClient createNetClient(NetClientOptions options) {
        return new NetClientImpl(this, options);
    }

    @Override // io.vertx.core.Vertx
    public NetClient createNetClient() {
        return createNetClient(new NetClientOptions());
    }

    @Override // io.vertx.core.impl.VertxInternal
    public Transport transport() {
        return this.transport;
    }

    @Override // io.vertx.core.Vertx
    public boolean isNativeTransportEnabled() {
        return this.transport != Transport.JDK;
    }

    @Override // io.vertx.core.Vertx
    public FileSystem fileSystem() {
        return this.fileSystem;
    }

    @Override // io.vertx.core.Vertx
    public SharedData sharedData() {
        return this.sharedData;
    }

    @Override // io.vertx.core.Vertx
    public HttpServer createHttpServer(HttpServerOptions serverOptions) {
        return new HttpServerImpl(this, serverOptions);
    }

    @Override // io.vertx.core.Vertx
    public HttpServer createHttpServer() {
        return createHttpServer(new HttpServerOptions());
    }

    @Override // io.vertx.core.Vertx
    public HttpClient createHttpClient(HttpClientOptions options) {
        return new HttpClientImpl(this, options);
    }

    @Override // io.vertx.core.Vertx
    public HttpClient createHttpClient() {
        return createHttpClient(new HttpClientOptions());
    }

    @Override // io.vertx.core.Vertx
    public EventBus eventBus() {
        return this.eventBus;
    }

    @Override // io.vertx.core.Vertx
    public long setPeriodic(long delay, Handler<Long> handler) {
        return scheduleTimeout(getOrCreateContext(), handler, delay, true);
    }

    @Override // io.vertx.core.Vertx
    public TimeoutStream periodicStream(long delay) {
        return new TimeoutStreamImpl(delay, true);
    }

    @Override // io.vertx.core.Vertx
    public long setTimer(long delay, Handler<Long> handler) {
        return scheduleTimeout(getOrCreateContext(), handler, delay, false);
    }

    @Override // io.vertx.core.Vertx
    public TimeoutStream timerStream(long delay) {
        return new TimeoutStreamImpl(delay, false);
    }

    @Override // io.vertx.core.Vertx
    public void runOnContext(Handler<Void> task) {
        ContextImpl context = getOrCreateContext();
        context.runOnContext(task);
    }

    @Override // io.vertx.core.impl.VertxInternal
    public ExecutorService getWorkerPool() {
        return this.workerPool.executor();
    }

    @Override // io.vertx.core.impl.VertxInternal
    public EventLoopGroup getEventLoopGroup() {
        return this.eventLoopGroup;
    }

    @Override // io.vertx.core.impl.VertxInternal
    public EventLoopGroup getAcceptorEventLoopGroup() {
        return this.acceptorEventLoopGroup;
    }

    @Override // io.vertx.core.impl.VertxInternal, io.vertx.core.Vertx
    public ContextImpl getOrCreateContext() {
        ContextImpl ctx = getContext();
        if (ctx == null) {
            ctx = createEventLoopContext((String) null, (WorkerPool) null, new JsonObject(), Thread.currentThread().getContextClassLoader());
        }
        return ctx;
    }

    @Override // io.vertx.core.impl.VertxInternal
    public Map<ServerID, HttpServerImpl> sharedHttpServers() {
        return this.sharedHttpServers;
    }

    @Override // io.vertx.core.impl.VertxInternal
    public Map<ServerID, NetServerImpl> sharedNetServers() {
        return this.sharedNetServers;
    }

    @Override // io.vertx.core.metrics.Measured
    public boolean isMetricsEnabled() {
        return this.metrics != null;
    }

    @Override // io.vertx.core.spi.metrics.MetricsProvider
    public Metrics getMetrics() {
        return this.metrics;
    }

    @Override // io.vertx.core.Vertx
    public boolean cancelTimer(long id) {
        InternalTimerHandler handler = this.timeouts.remove(Long.valueOf(id));
        if (handler == null) {
            return false;
        }
        handler.cancel();
        return true;
    }

    @Override // io.vertx.core.impl.VertxInternal
    public EventLoopContext createEventLoopContext(String deploymentID, WorkerPool workerPool, JsonObject config, ClassLoader tccl) {
        return new EventLoopContext(this, this.internalBlockingPool, workerPool != null ? workerPool : this.workerPool, deploymentID, config, tccl);
    }

    @Override // io.vertx.core.impl.VertxInternal
    public EventLoopContext createEventLoopContext(EventLoop eventLoop, WorkerPool workerPool, ClassLoader tccl) {
        return new EventLoopContext(this, eventLoop, this.internalBlockingPool, workerPool != null ? workerPool : this.workerPool, null, null, tccl);
    }

    @Override // io.vertx.core.impl.VertxInternal
    public ContextImpl createWorkerContext(boolean multiThreaded, String deploymentID, WorkerPool workerPool, JsonObject config, ClassLoader tccl) {
        if (workerPool == null) {
            workerPool = this.workerPool;
        }
        if (multiThreaded) {
            return new MultiThreadedWorkerContext(this, this.internalBlockingPool, workerPool, deploymentID, config, tccl);
        }
        return new WorkerContext(this, this.internalBlockingPool, workerPool, deploymentID, config, tccl);
    }

    @Override // io.vertx.core.Vertx
    public DnsClient createDnsClient(int port, String host) {
        return createDnsClient(new DnsClientOptions().setHost(host).setPort(port));
    }

    @Override // io.vertx.core.Vertx
    public DnsClient createDnsClient() {
        return createDnsClient(new DnsClientOptions());
    }

    @Override // io.vertx.core.Vertx
    public DnsClient createDnsClient(DnsClientOptions options) {
        String host = options.getHost();
        int port = options.getPort();
        if (host == null || port < 0) {
            DnsResolverProvider provider = new DnsResolverProvider(this, this.addressResolverOptions);
            InetSocketAddress address = provider.nameServerAddresses().get(0);
            options = new DnsClientOptions(options).setHost(address.getAddress().getHostAddress()).setPort(address.getPort());
        }
        return new DnsClientImpl(this, options);
    }

    private VertxMetrics initialiseMetrics(VertxOptions options) {
        if (options.getMetricsOptions() != null && options.getMetricsOptions().isEnabled()) {
            VertxMetricsFactory factory = options.getMetricsOptions().getFactory();
            if (factory == null) {
                factory = (VertxMetricsFactory) ServiceHelper.loadFactoryOrNull(VertxMetricsFactory.class);
                if (factory == null) {
                    log.warn("Metrics has been set to enabled but no VertxMetricsFactory found on classpath");
                }
            }
            if (factory != null) {
                VertxMetrics metrics = factory.metrics(options);
                Objects.requireNonNull(metrics, "The metric instance created from " + factory + " cannot be null");
                return metrics;
            }
            return null;
        }
        return null;
    }

    private ClusterManager getClusterManager(VertxOptions options) throws ClassNotFoundException {
        ClusterManager mgr = options.getClusterManager();
        if (mgr == null) {
            String clusterManagerClassName = System.getProperty("vertx.cluster.managerClass");
            if (clusterManagerClassName != null) {
                try {
                    Class<?> clazz = Class.forName(clusterManagerClassName);
                    mgr = (ClusterManager) clazz.newInstance();
                } catch (Exception e) {
                    throw new IllegalStateException("Failed to instantiate " + clusterManagerClassName, e);
                }
            } else {
                mgr = (ClusterManager) ServiceHelper.loadFactoryOrNull(ClusterManager.class);
                if (mgr == null) {
                    throw new IllegalStateException("No ClusterManagerFactory instances found on classpath");
                }
            }
        }
        return mgr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long scheduleTimeout(ContextImpl context, Handler<Long> handler, long delay, boolean periodic) {
        if (delay < 1) {
            throw new IllegalArgumentException("Cannot schedule a timer with delay < 1 ms");
        }
        long timerId = this.timeoutCounter.getAndIncrement();
        InternalTimerHandler task = new InternalTimerHandler(timerId, handler, periodic, delay, context);
        this.timeouts.put(Long.valueOf(timerId), task);
        context.addCloseHook(task);
        return timerId;
    }

    @Override // io.vertx.core.impl.VertxInternal
    public ContextImpl getContext() {
        ContextImpl context = (ContextImpl) ContextImpl.context();
        if (context != null && context.owner == this) {
            return context;
        }
        return null;
    }

    @Override // io.vertx.core.impl.VertxInternal
    public ClusterManager getClusterManager() {
        return this.clusterManager;
    }

    @Override // io.vertx.core.Vertx
    public void close() {
        close(null);
    }

    private void closeClusterManager(Handler<AsyncResult<Void>> completionHandler) {
        if (this.clusterManager != null) {
            this.clusterManager.leave(ar -> {
                if (ar.failed()) {
                    log.error("Failed to leave cluster", ar.cause());
                }
                if (completionHandler != null) {
                    runOnContext(v -> {
                        completionHandler.handle(Future.succeededFuture());
                    });
                }
            });
        } else if (completionHandler != null) {
            runOnContext(v -> {
                completionHandler.handle(Future.succeededFuture());
            });
        }
    }

    @Override // io.vertx.core.Vertx
    public synchronized void close(Handler<AsyncResult<Void>> completionHandler) {
        if (this.closed || this.eventBus == null) {
            if (completionHandler != null) {
                completionHandler.handle(Future.succeededFuture());
            }
        } else {
            this.closed = true;
            this.closeHooks.run(ar -> {
                this.deploymentManager.undeployAll(ar1 -> {
                    HAManager haManager = haManager();
                    Promise<Void> haPromise = Promise.promise();
                    if (haManager != null) {
                        executeBlocking(fut -> {
                            haManager.stop();
                            fut.complete();
                        }, false, haPromise);
                    } else {
                        haPromise.complete();
                    }
                    haPromise.future().setHandler2(ar2 -> {
                        this.addressResolver.close(ar3 -> {
                            this.eventBus.close(ar4 -> {
                                closeClusterManager(ar5 -> {
                                    Set<HttpServerImpl> httpServers = new HashSet<>(this.sharedHttpServers.values());
                                    Set<NetServerImpl> netServers = new HashSet<>(this.sharedNetServers.values());
                                    this.sharedHttpServers.clear();
                                    this.sharedNetServers.clear();
                                    int serverCount = httpServers.size() + netServers.size();
                                    AtomicInteger serverCloseCount = new AtomicInteger();
                                    Handler<AsyncResult<Void>> serverCloseHandler = res -> {
                                        if (res.failed()) {
                                            log.error("Failure in shutting down server", res.cause());
                                        }
                                        if (serverCloseCount.incrementAndGet() == serverCount) {
                                            deleteCacheDirAndShutdown(completionHandler);
                                        }
                                    };
                                    for (HttpServerImpl server : httpServers) {
                                        server.closeAll(serverCloseHandler);
                                    }
                                    for (NetServerImpl server2 : netServers) {
                                        server2.closeAll(serverCloseHandler);
                                    }
                                    if (serverCount == 0) {
                                        deleteCacheDirAndShutdown(completionHandler);
                                    }
                                });
                            });
                        });
                    });
                });
            });
        }
    }

    @Override // io.vertx.core.Vertx
    public void deployVerticle(Verticle verticle) {
        deployVerticle(verticle, new DeploymentOptions(), (Handler<AsyncResult<String>>) null);
    }

    @Override // io.vertx.core.Vertx
    public void deployVerticle(Verticle verticle, Handler<AsyncResult<String>> completionHandler) {
        deployVerticle(verticle, new DeploymentOptions(), completionHandler);
    }

    @Override // io.vertx.core.Vertx
    public void deployVerticle(String name, Handler<AsyncResult<String>> completionHandler) {
        deployVerticle(name, new DeploymentOptions(), completionHandler);
    }

    @Override // io.vertx.core.Vertx
    public void deployVerticle(Verticle verticle, DeploymentOptions options) {
        deployVerticle(verticle, options, (Handler<AsyncResult<String>>) null);
    }

    @Override // io.vertx.core.Vertx
    public void deployVerticle(Class<? extends Verticle> verticleClass, DeploymentOptions options) {
        deployVerticle(() -> {
            try {
                return (Verticle) verticleClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, options);
    }

    @Override // io.vertx.core.Vertx
    public void deployVerticle(Supplier<Verticle> verticleSupplier, DeploymentOptions options) {
        deployVerticle(verticleSupplier, options, (Handler<AsyncResult<String>>) null);
    }

    @Override // io.vertx.core.Vertx
    public void deployVerticle(Verticle verticle, DeploymentOptions options, Handler<AsyncResult<String>> completionHandler) {
        if (options.getInstances() != 1) {
            throw new IllegalArgumentException("Can't specify > 1 instances for already created verticle");
        }
        deployVerticle(() -> {
            return verticle;
        }, options, completionHandler);
    }

    @Override // io.vertx.core.Vertx
    public void deployVerticle(Class<? extends Verticle> verticleClass, DeploymentOptions options, Handler<AsyncResult<String>> completionHandler) {
        deployVerticle(() -> {
            try {
                return (Verticle) verticleClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, options, completionHandler);
    }

    @Override // io.vertx.core.Vertx
    public void deployVerticle(Supplier<Verticle> verticleSupplier, DeploymentOptions options, Handler<AsyncResult<String>> completionHandler) {
        boolean closed;
        synchronized (this) {
            closed = this.closed;
        }
        if (closed) {
            if (completionHandler != null) {
                completionHandler.handle(Future.failedFuture("Vert.x closed"));
                return;
            }
            return;
        }
        this.deploymentManager.deployVerticle(verticleSupplier, options, completionHandler);
    }

    @Override // io.vertx.core.Vertx
    public void deployVerticle(String name) {
        deployVerticle(name, new DeploymentOptions(), (Handler<AsyncResult<String>>) null);
    }

    @Override // io.vertx.core.Vertx
    public void deployVerticle(String name, DeploymentOptions options) {
        deployVerticle(name, options, (Handler<AsyncResult<String>>) null);
    }

    @Override // io.vertx.core.Vertx
    public void deployVerticle(String name, DeploymentOptions options, Handler<AsyncResult<String>> completionHandler) {
        if (options.isHa() && haManager() != null && haManager().isEnabled()) {
            haManager().deployVerticle(name, options, completionHandler);
        } else {
            this.deploymentManager.deployVerticle(name, options, completionHandler);
        }
    }

    @Override // io.vertx.core.impl.VertxInternal
    public String getNodeID() {
        return this.clusterManager.getNodeID();
    }

    @Override // io.vertx.core.Vertx
    public void undeploy(String deploymentID) {
        undeploy(deploymentID, res -> {
        });
    }

    @Override // io.vertx.core.Vertx
    public void undeploy(String deploymentID, Handler<AsyncResult<Void>> completionHandler) {
        HAManager haManager = haManager();
        Promise<Void> haFuture = Promise.promise();
        if (haManager != null && haManager.isEnabled()) {
            executeBlocking(fut -> {
                haManager.removeFromHA(deploymentID);
                fut.complete();
            }, false, haFuture);
        } else {
            haFuture.complete();
        }
        haFuture.future().compose(v -> {
            Promise<Void> deploymentFuture = Promise.promise();
            this.deploymentManager.undeployVerticle(deploymentID, deploymentFuture);
            return deploymentFuture.future();
        }).setHandler2(completionHandler);
    }

    @Override // io.vertx.core.Vertx
    public Set<String> deploymentIDs() {
        return this.deploymentManager.deployments();
    }

    @Override // io.vertx.core.Vertx
    public void registerVerticleFactory(VerticleFactory factory) {
        this.deploymentManager.registerVerticleFactory(factory);
    }

    @Override // io.vertx.core.Vertx
    public void unregisterVerticleFactory(VerticleFactory factory) {
        this.deploymentManager.unregisterVerticleFactory(factory);
    }

    @Override // io.vertx.core.Vertx
    public Set<VerticleFactory> verticleFactories() {
        return this.deploymentManager.verticleFactories();
    }

    @Override // io.vertx.core.impl.VertxInternal
    public <T> void executeBlockingInternal(Handler<Promise<T>> blockingCodeHandler, Handler<AsyncResult<T>> resultHandler) {
        ContextImpl context = getOrCreateContext();
        context.executeBlockingInternal(blockingCodeHandler, resultHandler);
    }

    @Override // io.vertx.core.Vertx
    public <T> void executeBlocking(Handler<Promise<T>> blockingCodeHandler, boolean ordered, Handler<AsyncResult<T>> asyncResultHandler) {
        ContextImpl context = getOrCreateContext();
        context.executeBlocking(blockingCodeHandler, ordered, asyncResultHandler);
    }

    @Override // io.vertx.core.Vertx
    public <T> void executeBlocking(Handler<Promise<T>> blockingCodeHandler, Handler<AsyncResult<T>> asyncResultHandler) {
        executeBlocking(blockingCodeHandler, true, asyncResultHandler);
    }

    @Override // io.vertx.core.Vertx
    public boolean isClustered() {
        return this.clusterManager != null;
    }

    @Override // io.vertx.core.Vertx
    public EventLoopGroup nettyEventLoopGroup() {
        return this.eventLoopGroup;
    }

    @Override // io.vertx.core.impl.VertxInternal
    public void simulateKill() {
        if (haManager() != null) {
            haManager().simulateKill();
        }
    }

    @Override // io.vertx.core.impl.VertxInternal
    public Deployment getDeployment(String deploymentID) {
        return this.deploymentManager.getDeployment(deploymentID);
    }

    @Override // io.vertx.core.impl.VertxInternal
    public synchronized void failoverCompleteHandler(FailoverCompleteHandler failoverCompleteHandler) {
        if (haManager() != null) {
            haManager().setFailoverCompleteHandler(failoverCompleteHandler);
        }
    }

    @Override // io.vertx.core.impl.VertxInternal
    public boolean isKilled() {
        return haManager().isKilled();
    }

    @Override // io.vertx.core.impl.VertxInternal
    public void failDuringFailover(boolean fail) {
        if (haManager() != null) {
            haManager().failDuringFailover(fail);
        }
    }

    @Override // io.vertx.core.impl.VertxInternal
    public VertxMetrics metricsSPI() {
        return this.metrics;
    }

    @Override // io.vertx.core.impl.VertxInternal
    public File resolveFile(String fileName) {
        return this.fileResolver.resolveFile(fileName);
    }

    @Override // io.vertx.core.impl.VertxInternal
    public void resolveAddress(String hostname, Handler<AsyncResult<InetAddress>> resultHandler) {
        this.addressResolver.resolveHostname(hostname, resultHandler);
    }

    @Override // io.vertx.core.impl.VertxInternal
    public AddressResolver addressResolver() {
        return this.addressResolver;
    }

    @Override // io.vertx.core.impl.VertxInternal
    public AddressResolverGroup<InetSocketAddress> nettyAddressResolverGroup() {
        return this.addressResolver.nettyAddressResolverGroup();
    }

    @Override // io.vertx.core.impl.VertxInternal
    public BlockedThreadChecker blockedThreadChecker() {
        return this.checker;
    }

    private void deleteCacheDirAndShutdown(Handler<AsyncResult<Void>> completionHandler) {
        executeBlockingInternal(fut -> {
            try {
                this.fileResolver.close();
                fut.complete();
            } catch (IOException e) {
                fut.tryFail(e);
            }
        }, ar -> {
            this.workerPool.close();
            this.internalBlockingPool.close();
            new ArrayList(this.namedWorkerPools.values()).forEach((v0) -> {
                v0.close();
            });
            this.acceptorEventLoopGroup.shutdownGracefully(0L, 10L, TimeUnit.SECONDS).addListener2(new GenericFutureListener() { // from class: io.vertx.core.impl.VertxImpl.1
                @Override // io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(io.netty.util.concurrent.Future future) throws Exception {
                    if (!future.isSuccess()) {
                        VertxImpl.log.warn("Failure in shutting down acceptor event loop group", future.cause());
                    }
                    VertxImpl.this.eventLoopGroup.shutdownGracefully(0L, 10L, TimeUnit.SECONDS).addListener2(new GenericFutureListener() { // from class: io.vertx.core.impl.VertxImpl.1.1
                        @Override // io.netty.util.concurrent.GenericFutureListener
                        public void operationComplete(io.netty.util.concurrent.Future future2) throws Exception {
                            if (!future2.isSuccess()) {
                                VertxImpl.log.warn("Failure in shutting down event loop group", future2.cause());
                            }
                            if (VertxImpl.this.metrics != null) {
                                VertxImpl.this.metrics.close();
                            }
                            VertxImpl.this.checker.close();
                            if (completionHandler != null) {
                                ThreadFactory threadFactory = VertxImpl.this.eventLoopThreadFactory;
                                Handler handler = completionHandler;
                                threadFactory.newThread(() -> {
                                    handler.handle(Future.succeededFuture());
                                }).start();
                            }
                        }
                    });
                }
            });
        });
    }

    @Override // io.vertx.core.impl.VertxInternal
    public HAManager haManager() {
        return this.haManager;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/VertxImpl$InternalTimerHandler.class */
    private class InternalTimerHandler implements Handler<Void>, Closeable, Runnable {
        private final Handler<Long> handler;
        private final boolean periodic;
        private final long timerID;
        private final ContextImpl context;
        private final java.util.concurrent.Future<?> future;

        InternalTimerHandler(long timerID, Handler<Long> runnable, boolean periodic, long delay, ContextImpl context) {
            this.context = context;
            this.timerID = timerID;
            this.handler = runnable;
            this.periodic = periodic;
            EventLoop el = context.nettyEventLoop();
            if (periodic) {
                this.future = el.scheduleAtFixedRate((Runnable) this, delay, delay, TimeUnit.MILLISECONDS);
            } else {
                this.future = el.schedule((Runnable) this, delay, TimeUnit.MILLISECONDS);
            }
            if (VertxImpl.this.metrics != null) {
                VertxImpl.this.metrics.timerCreated(timerID);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.context.executeFromIO(this);
        }

        @Override // io.vertx.core.Handler
        public void handle(Void v) {
            if (this.periodic) {
                if (VertxImpl.this.timeouts.containsKey(Long.valueOf(this.timerID))) {
                    this.handler.handle(Long.valueOf(this.timerID));
                }
            } else if (VertxImpl.this.timeouts.remove(Long.valueOf(this.timerID)) != null) {
                try {
                    this.handler.handle(Long.valueOf(this.timerID));
                } finally {
                    if (this.context.removeCloseHook(this) && VertxImpl.this.metrics != null) {
                        VertxImpl.this.metrics.timerEnded(this.timerID, false);
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void cancel() {
            this.future.cancel(false);
            if (this.context.removeCloseHook(this) && VertxImpl.this.metrics != null) {
                VertxImpl.this.metrics.timerEnded(this.timerID, true);
            }
        }

        @Override // io.vertx.core.Closeable
        public void close(Handler<AsyncResult<Void>> completionHandler) {
            if (VertxImpl.this.timeouts.remove(Long.valueOf(this.timerID)) != null) {
                this.future.cancel(false);
                if (VertxImpl.this.metrics != null) {
                    VertxImpl.this.metrics.timerEnded(this.timerID, true);
                }
            }
            completionHandler.handle(Future.succeededFuture());
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/VertxImpl$TimeoutStreamImpl.class */
    private class TimeoutStreamImpl implements TimeoutStream, Handler<Long> {
        private final long delay;
        private final boolean periodic;
        private Long id;
        private Handler<Long> handler;
        private Handler<Void> endHandler;
        private long demand = Long.MAX_VALUE;

        @Override // io.vertx.core.TimeoutStream, io.vertx.core.streams.ReadStream
        /* renamed from: endHandler, reason: avoid collision after fix types in other method */
        public /* bridge */ /* synthetic */ ReadStream<Long> endHandler2(Handler handler) {
            return endHandler((Handler<Void>) handler);
        }

        @Override // io.vertx.core.TimeoutStream, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
        public /* bridge */ /* synthetic */ ReadStream exceptionHandler(Handler handler) {
            return exceptionHandler((Handler<Throwable>) handler);
        }

        @Override // io.vertx.core.TimeoutStream, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
        public /* bridge */ /* synthetic */ StreamBase exceptionHandler(Handler handler) {
            return exceptionHandler((Handler<Throwable>) handler);
        }

        public TimeoutStreamImpl(long delay, boolean periodic) {
            this.delay = delay;
            this.periodic = periodic;
        }

        @Override // io.vertx.core.Handler
        public synchronized void handle(Long event) {
            boolean z;
            try {
                if (this.demand > 0) {
                    this.demand--;
                    this.handler.handle(event);
                }
                if (z) {
                    return;
                }
            } finally {
                if (!this.periodic && this.endHandler != null) {
                    this.endHandler.handle(null);
                }
            }
        }

        @Override // io.vertx.core.TimeoutStream, io.vertx.core.streams.ReadStream
        /* renamed from: fetch */
        public synchronized ReadStream<Long> fetch2(long amount) {
            this.demand += amount;
            if (this.demand < 0) {
                this.demand = Long.MAX_VALUE;
            }
            return this;
        }

        @Override // io.vertx.core.TimeoutStream, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
        public TimeoutStream exceptionHandler(Handler<Throwable> handler) {
            return this;
        }

        @Override // io.vertx.core.TimeoutStream
        public void cancel() {
            if (this.id != null) {
                VertxImpl.this.cancelTimer(this.id.longValue());
            }
        }

        @Override // io.vertx.core.TimeoutStream, io.vertx.core.streams.ReadStream
        /* renamed from: handler */
        public synchronized ReadStream<Long> handler2(Handler<Long> handler) {
            if (handler != null) {
                if (this.id != null) {
                    throw new IllegalStateException();
                }
                this.handler = handler;
                this.id = Long.valueOf(VertxImpl.this.scheduleTimeout(VertxImpl.this.getOrCreateContext(), this, this.delay, this.periodic));
            } else {
                cancel();
            }
            return this;
        }

        @Override // io.vertx.core.TimeoutStream, io.vertx.core.streams.ReadStream
        /* renamed from: pause */
        public synchronized ReadStream<Long> pause2() {
            this.demand = 0L;
            return this;
        }

        @Override // io.vertx.core.TimeoutStream, io.vertx.core.streams.ReadStream
        /* renamed from: resume */
        public synchronized ReadStream<Long> resume2() {
            this.demand = Long.MAX_VALUE;
            return this;
        }

        @Override // io.vertx.core.TimeoutStream, io.vertx.core.streams.ReadStream
        public synchronized ReadStream<Long> endHandler(Handler<Void> endHandler) {
            this.endHandler = endHandler;
            return this;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/VertxImpl$SharedWorkerPool.class */
    class SharedWorkerPool extends WorkerPool {
        private final String name;
        private int refCount;

        static /* synthetic */ int access$508(SharedWorkerPool x0) {
            int i = x0.refCount;
            x0.refCount = i + 1;
            return i;
        }

        SharedWorkerPool(String name, ExecutorService workerExec, PoolMetrics workerMetrics) {
            super(workerExec, workerMetrics);
            this.refCount = 1;
            this.name = name;
        }

        @Override // io.vertx.core.impl.WorkerPool
        void close() {
            synchronized (VertxImpl.this) {
                if (this.refCount > 0) {
                    this.refCount = 0;
                    super.close();
                }
            }
        }

        void release() {
            synchronized (VertxImpl.this) {
                int i = this.refCount - 1;
                this.refCount = i;
                if (i == 0) {
                    VertxImpl.this.namedWorkerPools.remove(this.name);
                    super.close();
                }
            }
        }
    }

    @Override // io.vertx.core.impl.VertxInternal, io.vertx.core.Vertx
    public WorkerExecutorImpl createSharedWorkerExecutor(String name) {
        return createSharedWorkerExecutor(name, this.defaultWorkerPoolSize);
    }

    @Override // io.vertx.core.impl.VertxInternal, io.vertx.core.Vertx
    public WorkerExecutorImpl createSharedWorkerExecutor(String name, int poolSize) {
        return createSharedWorkerExecutor(name, poolSize, this.maxWorkerExecTime);
    }

    @Override // io.vertx.core.impl.VertxInternal, io.vertx.core.Vertx
    public synchronized WorkerExecutorImpl createSharedWorkerExecutor(String name, int poolSize, long maxExecuteTime) {
        return createSharedWorkerExecutor(name, poolSize, maxExecuteTime, TimeUnit.NANOSECONDS);
    }

    @Override // io.vertx.core.impl.VertxInternal, io.vertx.core.Vertx
    public synchronized WorkerExecutorImpl createSharedWorkerExecutor(String name, int poolSize, long maxExecuteTime, TimeUnit maxExecuteTimeUnit) {
        if (poolSize < 1) {
            throw new IllegalArgumentException("poolSize must be > 0");
        }
        if (maxExecuteTime < 1) {
            throw new IllegalArgumentException("maxExecuteTime must be > 0");
        }
        SharedWorkerPool sharedWorkerPool = this.namedWorkerPools.get(name);
        if (sharedWorkerPool == null) {
            ExecutorService workerExec = Executors.newFixedThreadPool(poolSize, new VertxThreadFactory(name + "-", this.checker, true, maxExecuteTime, maxExecuteTimeUnit));
            PoolMetrics workerMetrics = this.metrics != null ? this.metrics.createPoolMetrics("worker", name, poolSize) : null;
            Map<String, SharedWorkerPool> map = this.namedWorkerPools;
            SharedWorkerPool sharedWorkerPool2 = new SharedWorkerPool(name, workerExec, workerMetrics);
            sharedWorkerPool = sharedWorkerPool2;
            map.put(name, sharedWorkerPool2);
        } else {
            SharedWorkerPool.access$508(sharedWorkerPool);
        }
        ContextImpl context = getOrCreateContext();
        WorkerExecutorImpl namedExec = new WorkerExecutorImpl(context, sharedWorkerPool);
        context.addCloseHook(namedExec);
        return namedExec;
    }

    @Override // io.vertx.core.Vertx
    public Vertx exceptionHandler(Handler<Throwable> handler) {
        this.exceptionHandler = handler;
        return this;
    }

    @Override // io.vertx.core.Vertx
    public Handler<Throwable> exceptionHandler() {
        return this.exceptionHandler;
    }

    @Override // io.vertx.core.impl.VertxInternal
    public void addCloseHook(Closeable hook) {
        this.closeHooks.add(hook);
    }

    @Override // io.vertx.core.impl.VertxInternal
    public void removeCloseHook(Closeable hook) {
        this.closeHooks.remove(hook);
    }
}
