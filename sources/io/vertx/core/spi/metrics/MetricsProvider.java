package io.vertx.core.spi.metrics;

import io.vertx.core.metrics.Measured;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/metrics/MetricsProvider.class */
public interface MetricsProvider extends Measured {
    Metrics getMetrics();
}
