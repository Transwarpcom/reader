package io.vertx.core.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.impl.VertxImpl;
import io.vertx.core.spi.metrics.Metrics;
import io.vertx.core.spi.metrics.MetricsProvider;
import io.vertx.core.spi.metrics.PoolMetrics;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/WorkerExecutorImpl.class */
class WorkerExecutorImpl implements MetricsProvider, WorkerExecutorInternal {
    private final Context ctx;
    private final VertxImpl.SharedWorkerPool pool;
    private boolean closed;

    public WorkerExecutorImpl(Context ctx, VertxImpl.SharedWorkerPool pool) {
        this.ctx = ctx;
        this.pool = pool;
    }

    @Override // io.vertx.core.spi.metrics.MetricsProvider
    public Metrics getMetrics() {
        return this.pool.metrics();
    }

    @Override // io.vertx.core.metrics.Measured
    public boolean isMetricsEnabled() {
        PoolMetrics metrics = this.pool.metrics();
        return metrics != null;
    }

    @Override // io.vertx.core.impl.WorkerExecutorInternal
    public Vertx vertx() {
        return this.ctx.owner();
    }

    @Override // io.vertx.core.impl.WorkerExecutorInternal
    public WorkerPool getPool() {
        return this.pool;
    }

    @Override // io.vertx.core.WorkerExecutor
    public synchronized <T> void executeBlocking(Handler<Promise<T>> blockingCodeHandler, boolean ordered, Handler<AsyncResult<T>> asyncResultHandler) {
        if (this.closed) {
            throw new IllegalStateException("Worker executor closed");
        }
        ContextImpl context = (ContextImpl) this.ctx.owner().getOrCreateContext();
        context.executeBlocking(blockingCodeHandler, asyncResultHandler, this.pool.executor(), ordered ? context.orderedTasks : null, this.pool.metrics());
    }

    @Override // io.vertx.core.WorkerExecutor
    public void close() {
        synchronized (this) {
            if (this.closed) {
                return;
            }
            this.closed = true;
            this.ctx.removeCloseHook(this);
            this.pool.release();
        }
    }

    @Override // io.vertx.core.Closeable
    public void close(Handler<AsyncResult<Void>> completionHandler) {
        close();
        completionHandler.handle(Future.succeededFuture());
    }
}
