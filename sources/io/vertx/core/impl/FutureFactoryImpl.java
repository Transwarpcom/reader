package io.vertx.core.impl;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.spi.FutureFactory;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/FutureFactoryImpl.class */
public class FutureFactoryImpl implements FutureFactory {
    private static final SucceededFuture EMPTY = new SucceededFuture(null);

    @Override // io.vertx.core.spi.FutureFactory
    public <T> Promise<T> promise() {
        return new FutureImpl();
    }

    @Override // io.vertx.core.spi.FutureFactory
    public <T> Future<T> future() {
        return new FutureImpl();
    }

    @Override // io.vertx.core.spi.FutureFactory
    public <T> Future<T> succeededFuture() {
        Future<T> fut = EMPTY;
        return fut;
    }

    @Override // io.vertx.core.spi.FutureFactory
    public <T> Future<T> succeededFuture(T result) {
        return new SucceededFuture(result);
    }

    @Override // io.vertx.core.spi.FutureFactory
    public <T> Future<T> failedFuture(Throwable t) {
        return new FailedFuture(t);
    }

    @Override // io.vertx.core.spi.FutureFactory
    public <T> Future<T> failureFuture(String failureMessage) {
        return new FailedFuture(failureMessage);
    }
}
