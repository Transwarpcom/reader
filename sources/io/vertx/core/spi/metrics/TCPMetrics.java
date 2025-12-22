package io.vertx.core.spi.metrics;

import io.vertx.core.net.SocketAddress;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/metrics/TCPMetrics.class */
public interface TCPMetrics<S> extends NetworkMetrics<S> {
    default S connected(SocketAddress remoteAddress, String remoteName) {
        return null;
    }

    default void disconnected(S socketMetric, SocketAddress remoteAddress) {
    }
}
