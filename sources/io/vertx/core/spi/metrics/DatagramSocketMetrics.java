package io.vertx.core.spi.metrics;

import io.vertx.core.net.SocketAddress;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/metrics/DatagramSocketMetrics.class */
public interface DatagramSocketMetrics extends NetworkMetrics<Void> {
    default void listening(String localName, SocketAddress localAddress) {
    }
}
