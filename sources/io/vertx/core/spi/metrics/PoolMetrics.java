package io.vertx.core.spi.metrics;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/metrics/PoolMetrics.class */
public interface PoolMetrics<T> extends Metrics {
    default T submitted() {
        return null;
    }

    default T begin(T t) {
        return null;
    }

    default void rejected(T t) {
    }

    default void end(T t, boolean succeeded) {
    }
}
