package io.vertx.core.spi.metrics;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/metrics/Metrics.class */
public interface Metrics {
    public static final String DISABLE_METRICS_PROPERTY_NAME = "vertx.disableMetrics";
    public static final boolean METRICS_ENABLED;

    static {
        METRICS_ENABLED = !Boolean.getBoolean(DISABLE_METRICS_PROPERTY_NAME);
    }

    @Deprecated
    default boolean isEnabled() {
        return true;
    }

    default void close() {
    }
}
