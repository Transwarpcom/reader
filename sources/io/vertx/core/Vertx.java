package io.vertx.core;

import io.netty.channel.EventLoopGroup;
import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.datagram.DatagramSocket;
import io.vertx.core.datagram.DatagramSocketOptions;
import io.vertx.core.dns.DnsClient;
import io.vertx.core.dns.DnsClientOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.file.FileSystem;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.metrics.Measured;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.shareddata.SharedData;
import io.vertx.core.spi.VerticleFactory;
import io.vertx.core.spi.VertxFactory;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/Vertx.class */
public interface Vertx extends Measured {

    @GenIgnore
    public static final VertxFactory factory = (VertxFactory) ServiceHelper.loadFactory(VertxFactory.class);

    Context getOrCreateContext();

    NetServer createNetServer(NetServerOptions netServerOptions);

    NetServer createNetServer();

    NetClient createNetClient(NetClientOptions netClientOptions);

    NetClient createNetClient();

    HttpServer createHttpServer(HttpServerOptions httpServerOptions);

    HttpServer createHttpServer();

    HttpClient createHttpClient(HttpClientOptions httpClientOptions);

    HttpClient createHttpClient();

    DatagramSocket createDatagramSocket(DatagramSocketOptions datagramSocketOptions);

    DatagramSocket createDatagramSocket();

    @CacheReturn
    FileSystem fileSystem();

    @CacheReturn
    EventBus eventBus();

    DnsClient createDnsClient(int i, String str);

    DnsClient createDnsClient();

    DnsClient createDnsClient(DnsClientOptions dnsClientOptions);

    @CacheReturn
    SharedData sharedData();

    long setTimer(long j, Handler<Long> handler);

    TimeoutStream timerStream(long j);

    long setPeriodic(long j, Handler<Long> handler);

    TimeoutStream periodicStream(long j);

    boolean cancelTimer(long j);

    void runOnContext(Handler<Void> handler);

    void close();

    void close(Handler<AsyncResult<Void>> handler);

    @GenIgnore({"permitted-type"})
    void deployVerticle(Verticle verticle);

    @GenIgnore({"permitted-type"})
    void deployVerticle(Verticle verticle, Handler<AsyncResult<String>> handler);

    @GenIgnore({"permitted-type"})
    void deployVerticle(Verticle verticle, DeploymentOptions deploymentOptions);

    @GenIgnore
    void deployVerticle(Class<? extends Verticle> cls, DeploymentOptions deploymentOptions);

    @GenIgnore({"permitted-type"})
    void deployVerticle(Supplier<Verticle> supplier, DeploymentOptions deploymentOptions);

    @GenIgnore({"permitted-type"})
    void deployVerticle(Verticle verticle, DeploymentOptions deploymentOptions, Handler<AsyncResult<String>> handler);

    @GenIgnore
    void deployVerticle(Class<? extends Verticle> cls, DeploymentOptions deploymentOptions, Handler<AsyncResult<String>> handler);

    @GenIgnore({"permitted-type"})
    void deployVerticle(Supplier<Verticle> supplier, DeploymentOptions deploymentOptions, Handler<AsyncResult<String>> handler);

    void deployVerticle(String str);

    void deployVerticle(String str, Handler<AsyncResult<String>> handler);

    void deployVerticle(String str, DeploymentOptions deploymentOptions);

    void deployVerticle(String str, DeploymentOptions deploymentOptions, Handler<AsyncResult<String>> handler);

    void undeploy(String str);

    void undeploy(String str, Handler<AsyncResult<Void>> handler);

    Set<String> deploymentIDs();

    @GenIgnore({"permitted-type"})
    void registerVerticleFactory(VerticleFactory verticleFactory);

    @GenIgnore({"permitted-type"})
    void unregisterVerticleFactory(VerticleFactory verticleFactory);

    @GenIgnore({"permitted-type"})
    Set<VerticleFactory> verticleFactories();

    boolean isClustered();

    <T> void executeBlocking(Handler<Promise<T>> handler, boolean z, Handler<AsyncResult<T>> handler2);

    <T> void executeBlocking(Handler<Promise<T>> handler, Handler<AsyncResult<T>> handler2);

    @GenIgnore({"permitted-type"})
    EventLoopGroup nettyEventLoopGroup();

    WorkerExecutor createSharedWorkerExecutor(String str);

    WorkerExecutor createSharedWorkerExecutor(String str, int i);

    WorkerExecutor createSharedWorkerExecutor(String str, int i, long j);

    WorkerExecutor createSharedWorkerExecutor(String str, int i, long j, TimeUnit timeUnit);

    @CacheReturn
    boolean isNativeTransportEnabled();

    @Fluent
    Vertx exceptionHandler(Handler<Throwable> handler);

    @GenIgnore
    Handler<Throwable> exceptionHandler();

    static Vertx vertx() {
        return factory.vertx();
    }

    static Vertx vertx(VertxOptions options) {
        return factory.vertx(options);
    }

    static void clusteredVertx(VertxOptions options, Handler<AsyncResult<Vertx>> resultHandler) {
        factory.clusteredVertx(options, resultHandler);
    }

    static Context currentContext() {
        return factory.context();
    }
}
