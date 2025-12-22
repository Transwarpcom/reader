package io.vertx.core.metrics.impl;

import io.vertx.core.spi.metrics.DatagramSocketMetrics;
import io.vertx.core.spi.metrics.EventBusMetrics;
import io.vertx.core.spi.metrics.HttpClientMetrics;
import io.vertx.core.spi.metrics.HttpServerMetrics;
import io.vertx.core.spi.metrics.PoolMetrics;
import io.vertx.core.spi.metrics.TCPMetrics;
import io.vertx.core.spi.metrics.VertxMetrics;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/metrics/impl/DummyVertxMetrics.class */
public class DummyVertxMetrics implements VertxMetrics {
    public static final DummyVertxMetrics INSTANCE = new DummyVertxMetrics();

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/metrics/impl/DummyVertxMetrics$DummyDatagramMetrics.class */
    public static class DummyDatagramMetrics implements DatagramSocketMetrics {
        public static final DummyDatagramMetrics INSTANCE = new DummyDatagramMetrics();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/metrics/impl/DummyVertxMetrics$DummyEventBusMetrics.class */
    public static class DummyEventBusMetrics implements EventBusMetrics<Void> {
        public static final DummyEventBusMetrics INSTANCE = new DummyEventBusMetrics();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/metrics/impl/DummyVertxMetrics$DummyHttpClientMetrics.class */
    public static class DummyHttpClientMetrics implements HttpClientMetrics<Void, Void, Void, Void, Void> {
        public static final DummyHttpClientMetrics INSTANCE = new DummyHttpClientMetrics();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/metrics/impl/DummyVertxMetrics$DummyHttpServerMetrics.class */
    public static class DummyHttpServerMetrics implements HttpServerMetrics<Void, Void, Void> {
        public static final DummyHttpServerMetrics INSTANCE = new DummyHttpServerMetrics();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/metrics/impl/DummyVertxMetrics$DummyTCPMetrics.class */
    public static class DummyTCPMetrics implements TCPMetrics<Void> {
        public static final DummyTCPMetrics INSTANCE = new DummyTCPMetrics();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/metrics/impl/DummyVertxMetrics$DummyWorkerPoolMetrics.class */
    public static class DummyWorkerPoolMetrics implements PoolMetrics<Void> {
        public static final DummyWorkerPoolMetrics INSTANCE = new DummyWorkerPoolMetrics();
    }
}
