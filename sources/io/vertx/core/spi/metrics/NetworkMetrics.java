package io.vertx.core.spi.metrics;

import io.vertx.core.net.SocketAddress;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/metrics/NetworkMetrics.class */
public interface NetworkMetrics<S> extends Metrics {
    default void bytesRead(S socketMetric, SocketAddress remoteAddress, long numberOfBytes) {
    }

    default void bytesWritten(S socketMetric, SocketAddress remoteAddress, long numberOfBytes) {
    }

    default void exceptionOccurred(S socketMetric, SocketAddress remoteAddress, Throwable t) {
    }
}
