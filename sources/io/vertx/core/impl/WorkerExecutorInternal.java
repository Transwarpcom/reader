package io.vertx.core.impl;

import io.vertx.core.Closeable;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/WorkerExecutorInternal.class */
public interface WorkerExecutorInternal extends WorkerExecutor, Closeable {
    Vertx vertx();

    WorkerPool getPool();
}
