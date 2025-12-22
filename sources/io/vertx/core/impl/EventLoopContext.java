package io.vertx.core.impl;

import io.netty.channel.EventLoop;
import io.vertx.core.Closeable;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/EventLoopContext.class */
public class EventLoopContext extends ContextImpl {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) EventLoopContext.class);

    @Override // io.vertx.core.impl.ContextImpl, io.vertx.core.Context
    public /* bridge */ /* synthetic */ int getInstanceCount() {
        return super.getInstanceCount();
    }

    @Override // io.vertx.core.impl.ContextImpl, io.vertx.core.Context
    public /* bridge */ /* synthetic */ Handler exceptionHandler() {
        return super.exceptionHandler();
    }

    @Override // io.vertx.core.impl.ContextImpl, io.vertx.core.Context
    public /* bridge */ /* synthetic */ Context exceptionHandler(Handler handler) {
        return super.exceptionHandler(handler);
    }

    @Override // io.vertx.core.impl.ContextImpl, io.vertx.core.impl.ContextInternal
    public /* bridge */ /* synthetic */ void reportException(Throwable th) {
        super.reportException(th);
    }

    @Override // io.vertx.core.impl.ContextImpl, io.vertx.core.impl.ContextInternal
    public /* bridge */ /* synthetic */ ConcurrentMap contextData() {
        return super.contextData();
    }

    @Override // io.vertx.core.impl.ContextImpl, io.vertx.core.impl.ContextInternal
    public /* bridge */ /* synthetic */ void executeBlocking(Handler handler, TaskQueue taskQueue, Handler handler2) {
        super.executeBlocking(handler, taskQueue, handler2);
    }

    @Override // io.vertx.core.impl.ContextImpl, io.vertx.core.Context
    public /* bridge */ /* synthetic */ void executeBlocking(Handler handler, Handler handler2) {
        super.executeBlocking(handler, handler2);
    }

    @Override // io.vertx.core.impl.ContextImpl, io.vertx.core.Context
    public /* bridge */ /* synthetic */ void executeBlocking(Handler handler, boolean z, Handler handler2) {
        super.executeBlocking(handler, z, handler2);
    }

    @Override // io.vertx.core.impl.ContextImpl, io.vertx.core.impl.ContextInternal
    public /* bridge */ /* synthetic */ void executeBlockingInternal(Handler handler, Handler handler2) {
        super.executeBlockingInternal(handler, handler2);
    }

    @Override // io.vertx.core.impl.ContextImpl, io.vertx.core.impl.ContextInternal, io.vertx.core.Context
    public /* bridge */ /* synthetic */ VertxInternal owner() {
        return super.owner();
    }

    @Override // io.vertx.core.impl.ContextImpl, io.vertx.core.impl.ContextInternal
    public /* bridge */ /* synthetic */ EventLoop nettyEventLoop() {
        return super.nettyEventLoop();
    }

    @Override // io.vertx.core.impl.ContextImpl, io.vertx.core.Context
    public /* bridge */ /* synthetic */ List processArgs() {
        return super.processArgs();
    }

    @Override // io.vertx.core.impl.ContextImpl, io.vertx.core.Context
    public /* bridge */ /* synthetic */ JsonObject config() {
        return super.config();
    }

    @Override // io.vertx.core.impl.ContextImpl, io.vertx.core.Context
    public /* bridge */ /* synthetic */ String deploymentID() {
        return super.deploymentID();
    }

    @Override // io.vertx.core.impl.ContextImpl, io.vertx.core.Context
    public /* bridge */ /* synthetic */ void runOnContext(Handler handler) {
        super.runOnContext(handler);
    }

    @Override // io.vertx.core.impl.ContextImpl, io.vertx.core.Context
    public /* bridge */ /* synthetic */ boolean isWorkerContext() {
        return super.isWorkerContext();
    }

    @Override // io.vertx.core.impl.ContextImpl, io.vertx.core.Context
    public /* bridge */ /* synthetic */ boolean remove(String str) {
        return super.remove(str);
    }

    @Override // io.vertx.core.impl.ContextImpl, io.vertx.core.Context
    public /* bridge */ /* synthetic */ void put(String str, Object obj) {
        super.put(str, obj);
    }

    @Override // io.vertx.core.impl.ContextImpl, io.vertx.core.Context
    public /* bridge */ /* synthetic */ Object get(String str) {
        return super.get(str);
    }

    @Override // io.vertx.core.impl.ContextImpl
    public /* bridge */ /* synthetic */ void runCloseHooks(Handler handler) {
        super.runCloseHooks(handler);
    }

    @Override // io.vertx.core.impl.ContextImpl, io.vertx.core.Context
    public /* bridge */ /* synthetic */ boolean removeCloseHook(Closeable closeable) {
        return super.removeCloseHook(closeable);
    }

    @Override // io.vertx.core.impl.ContextImpl, io.vertx.core.Context
    public /* bridge */ /* synthetic */ void addCloseHook(Closeable closeable) {
        super.addCloseHook(closeable);
    }

    @Override // io.vertx.core.impl.ContextImpl, io.vertx.core.impl.ContextInternal
    public /* bridge */ /* synthetic */ Deployment getDeployment() {
        return super.getDeployment();
    }

    @Override // io.vertx.core.impl.ContextImpl
    public /* bridge */ /* synthetic */ void setDeployment(Deployment deployment) {
        super.setDeployment(deployment);
    }

    EventLoopContext(VertxInternal vertx, WorkerPool internalBlockingPool, WorkerPool workerPool, String deploymentID, JsonObject config, ClassLoader tccl) {
        super(vertx, internalBlockingPool, workerPool, deploymentID, config, tccl);
    }

    public EventLoopContext(VertxInternal vertx, EventLoop eventLoop, WorkerPool internalBlockingPool, WorkerPool workerPool, String deploymentID, JsonObject config, ClassLoader tccl) {
        super(vertx, eventLoop, internalBlockingPool, workerPool, deploymentID, config, tccl);
    }

    @Override // io.vertx.core.impl.ContextImpl
    void executeAsync(Handler<Void> task) {
        nettyEventLoop().execute(() -> {
            executeTask(null, task);
        });
    }

    @Override // io.vertx.core.impl.ContextImpl
    <T> void execute(T value, Handler<T> task) {
        executeTask(value, task);
    }

    @Override // io.vertx.core.impl.ContextImpl, io.vertx.core.Context
    public boolean isEventLoopContext() {
        return true;
    }

    @Override // io.vertx.core.impl.ContextImpl, io.vertx.core.Context
    public boolean isMultiThreadedWorkerContext() {
        return false;
    }
}
