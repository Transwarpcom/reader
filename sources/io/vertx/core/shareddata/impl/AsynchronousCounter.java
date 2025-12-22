package io.vertx.core.shareddata.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.shareddata.Counter;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/shareddata/impl/AsynchronousCounter.class */
public class AsynchronousCounter implements Counter {
    private final VertxInternal vertx;
    private final AtomicLong counter;

    public AsynchronousCounter(VertxInternal vertx) {
        this.vertx = vertx;
        this.counter = new AtomicLong();
    }

    public AsynchronousCounter(VertxInternal vertx, AtomicLong counter) {
        this.vertx = vertx;
        this.counter = counter;
    }

    @Override // io.vertx.core.shareddata.Counter
    public void get(Handler<AsyncResult<Long>> resultHandler) {
        Objects.requireNonNull(resultHandler, "resultHandler");
        Context context = this.vertx.getOrCreateContext();
        context.runOnContext(v -> {
            resultHandler.handle(Future.succeededFuture(Long.valueOf(this.counter.get())));
        });
    }

    @Override // io.vertx.core.shareddata.Counter
    public void incrementAndGet(Handler<AsyncResult<Long>> resultHandler) {
        Objects.requireNonNull(resultHandler, "resultHandler");
        Context context = this.vertx.getOrCreateContext();
        context.runOnContext(v -> {
            resultHandler.handle(Future.succeededFuture(Long.valueOf(this.counter.incrementAndGet())));
        });
    }

    @Override // io.vertx.core.shareddata.Counter
    public void getAndIncrement(Handler<AsyncResult<Long>> resultHandler) {
        Objects.requireNonNull(resultHandler, "resultHandler");
        Context context = this.vertx.getOrCreateContext();
        context.runOnContext(v -> {
            resultHandler.handle(Future.succeededFuture(Long.valueOf(this.counter.getAndIncrement())));
        });
    }

    @Override // io.vertx.core.shareddata.Counter
    public void decrementAndGet(Handler<AsyncResult<Long>> resultHandler) {
        Objects.requireNonNull(resultHandler, "resultHandler");
        Context context = this.vertx.getOrCreateContext();
        context.runOnContext(v -> {
            resultHandler.handle(Future.succeededFuture(Long.valueOf(this.counter.decrementAndGet())));
        });
    }

    @Override // io.vertx.core.shareddata.Counter
    public void addAndGet(long value, Handler<AsyncResult<Long>> resultHandler) {
        Objects.requireNonNull(resultHandler, "resultHandler");
        Context context = this.vertx.getOrCreateContext();
        context.runOnContext(v -> {
            resultHandler.handle(Future.succeededFuture(Long.valueOf(this.counter.addAndGet(value))));
        });
    }

    @Override // io.vertx.core.shareddata.Counter
    public void getAndAdd(long value, Handler<AsyncResult<Long>> resultHandler) {
        Objects.requireNonNull(resultHandler, "resultHandler");
        Context context = this.vertx.getOrCreateContext();
        context.runOnContext(v -> {
            resultHandler.handle(Future.succeededFuture(Long.valueOf(this.counter.getAndAdd(value))));
        });
    }

    @Override // io.vertx.core.shareddata.Counter
    public void compareAndSet(long expected, long value, Handler<AsyncResult<Boolean>> resultHandler) {
        Objects.requireNonNull(resultHandler, "resultHandler");
        Context context = this.vertx.getOrCreateContext();
        context.runOnContext(v -> {
            resultHandler.handle(Future.succeededFuture(Boolean.valueOf(this.counter.compareAndSet(expected, value))));
        });
    }
}
