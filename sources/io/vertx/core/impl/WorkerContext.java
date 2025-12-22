package io.vertx.core.impl;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.metrics.PoolMetrics;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/WorkerContext.class */
class WorkerContext extends ContextImpl {
    WorkerContext(VertxInternal vertx, WorkerPool internalBlockingPool, WorkerPool workerPool, String deploymentID, JsonObject config, ClassLoader tccl) {
        super(vertx, internalBlockingPool, workerPool, deploymentID, config, tccl);
    }

    final <T> Runnable wrapTask(T arg, Handler<T> hTask, PoolMetrics metrics) {
        Object queueMetric = metrics != null ? metrics.submitted() : null;
        return () -> {
            Object execMetric = null;
            if (metrics != null) {
                execMetric = metrics.begin(queueMetric);
            }
            boolean succeeded = executeTask(arg, hTask);
            if (metrics != null) {
                metrics.end(execMetric, succeeded);
            }
        };
    }

    @Override // io.vertx.core.impl.ContextImpl
    void executeAsync(Handler<Void> task) {
        execute(null, task);
    }

    @Override // io.vertx.core.impl.ContextImpl, io.vertx.core.Context
    public boolean isEventLoopContext() {
        return false;
    }

    @Override // io.vertx.core.impl.ContextImpl, io.vertx.core.Context
    public boolean isMultiThreadedWorkerContext() {
        return false;
    }

    @Override // io.vertx.core.impl.ContextImpl
    <T> void execute(T value, Handler<T> task) {
        this.orderedTasks.execute(wrapTask(value, task, this.workerPool.metrics()), this.workerPool.executor());
    }
}
