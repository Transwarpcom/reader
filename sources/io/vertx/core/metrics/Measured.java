package io.vertx.core.metrics;

import io.vertx.codegen.annotations.VertxGen;

@VertxGen(concrete = false)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/metrics/Measured.class */
public interface Measured {
    default boolean isMetricsEnabled() {
        return false;
    }
}
