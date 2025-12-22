package io.vertx.core.spi;

import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.metrics.MetricsOptions;
import io.vertx.core.spi.metrics.VertxMetrics;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/VertxMetricsFactory.class */
public interface VertxMetricsFactory {
    VertxMetrics metrics(VertxOptions vertxOptions);

    default MetricsOptions newOptions() {
        return new MetricsOptions();
    }

    default MetricsOptions newOptions(JsonObject jsonObject) {
        return new MetricsOptions(jsonObject);
    }
}
