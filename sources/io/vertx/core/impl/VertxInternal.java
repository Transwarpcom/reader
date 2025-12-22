package io.vertx.core.impl;

import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.resolver.AddressResolverGroup;
import io.vertx.core.AsyncResult;
import io.vertx.core.Closeable;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.impl.HttpServerImpl;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.impl.NetServerImpl;
import io.vertx.core.net.impl.ServerID;
import io.vertx.core.net.impl.transport.Transport;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.core.spi.metrics.VertxMetrics;
import java.io.File;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/VertxInternal.class */
public interface VertxInternal extends Vertx {
    long maxEventLoopExecTime();

    TimeUnit maxEventLoopExecTimeUnit();

    @Override // io.vertx.core.Vertx
    ContextInternal getOrCreateContext();

    EventLoopGroup getEventLoopGroup();

    EventLoopGroup getAcceptorEventLoopGroup();

    ExecutorService getWorkerPool();

    Map<ServerID, HttpServerImpl> sharedHttpServers();

    Map<ServerID, NetServerImpl> sharedNetServers();

    VertxMetrics metricsSPI();

    Transport transport();

    ContextInternal getContext();

    ContextInternal createEventLoopContext(String str, WorkerPool workerPool, JsonObject jsonObject, ClassLoader classLoader);

    ContextInternal createEventLoopContext(EventLoop eventLoop, WorkerPool workerPool, ClassLoader classLoader);

    ContextInternal createWorkerContext(boolean z, String str, WorkerPool workerPool, JsonObject jsonObject, ClassLoader classLoader);

    @Override // io.vertx.core.Vertx
    WorkerExecutorInternal createSharedWorkerExecutor(String str);

    @Override // io.vertx.core.Vertx
    WorkerExecutorInternal createSharedWorkerExecutor(String str, int i);

    @Override // io.vertx.core.Vertx
    WorkerExecutorInternal createSharedWorkerExecutor(String str, int i, long j);

    @Override // io.vertx.core.Vertx
    WorkerExecutorInternal createSharedWorkerExecutor(String str, int i, long j, TimeUnit timeUnit);

    void simulateKill();

    Deployment getDeployment(String str);

    void failoverCompleteHandler(FailoverCompleteHandler failoverCompleteHandler);

    boolean isKilled();

    void failDuringFailover(boolean z);

    String getNodeID();

    File resolveFile(String str);

    <T> void executeBlockingInternal(Handler<Promise<T>> handler, Handler<AsyncResult<T>> handler2);

    ClusterManager getClusterManager();

    HAManager haManager();

    void resolveAddress(String str, Handler<AsyncResult<InetAddress>> handler);

    AddressResolver addressResolver();

    AddressResolverGroup<InetSocketAddress> nettyAddressResolverGroup();

    BlockedThreadChecker blockedThreadChecker();

    void addCloseHook(Closeable closeable);

    void removeCloseHook(Closeable closeable);
}
