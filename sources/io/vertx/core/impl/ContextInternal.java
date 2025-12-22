package io.vertx.core.impl;

import io.netty.channel.EventLoop;
import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import java.util.concurrent.ConcurrentMap;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/ContextInternal.class */
public interface ContextInternal extends Context {
    EventLoop nettyEventLoop();

    <T> void executeBlocking(Handler<Promise<T>> handler, TaskQueue taskQueue, Handler<AsyncResult<T>> handler2);

    <T> void executeBlockingInternal(Handler<Promise<T>> handler, Handler<AsyncResult<T>> handler2);

    Deployment getDeployment();

    @Override // io.vertx.core.Context
    VertxInternal owner();

    void executeFromIO(Handler<Void> handler);

    <T> void executeFromIO(T t, Handler<T> handler);

    void reportException(Throwable th);

    ConcurrentMap<Object, Object> contextData();

    static boolean isOnWorkerThread() {
        return ContextImpl.isOnVertxThread(true);
    }

    static boolean isOnEventLoopThread() {
        return ContextImpl.isOnVertxThread(false);
    }

    static boolean isOnVertxThread() {
        Thread t = Thread.currentThread();
        return t instanceof VertxThread;
    }
}
