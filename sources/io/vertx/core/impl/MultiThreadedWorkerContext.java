package io.vertx.core.impl;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/MultiThreadedWorkerContext.class */
class MultiThreadedWorkerContext extends WorkerContext {
    MultiThreadedWorkerContext(VertxInternal vertx, WorkerPool internalBlockingPool, WorkerPool workerPool, String deploymentID, JsonObject config, ClassLoader tccl) {
        super(vertx, internalBlockingPool, workerPool, deploymentID, config, tccl);
    }

    @Override // io.vertx.core.impl.WorkerContext, io.vertx.core.impl.ContextImpl
    <T> void execute(T value, Handler<T> task) {
        this.workerPool.executor().execute(wrapTask(value, task, this.workerPool.metrics()));
    }

    @Override // io.vertx.core.impl.WorkerContext, io.vertx.core.impl.ContextImpl, io.vertx.core.Context
    public boolean isMultiThreadedWorkerContext() {
        return true;
    }
}
