package io.vertx.core.impl;

import io.vertx.core.spi.metrics.PoolMetrics;
import java.util.concurrent.ExecutorService;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/WorkerPool.class */
public class WorkerPool {
    private final ExecutorService pool;
    private final PoolMetrics metrics;

    public WorkerPool(ExecutorService pool, PoolMetrics metrics) {
        this.pool = pool;
        this.metrics = metrics;
    }

    ExecutorService executor() {
        return this.pool;
    }

    PoolMetrics metrics() {
        return this.metrics;
    }

    void close() {
        if (this.metrics != null) {
            this.metrics.close();
        }
        this.pool.shutdownNow();
    }
}
