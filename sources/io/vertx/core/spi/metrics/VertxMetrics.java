package io.vertx.core.spi.metrics;

import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.datagram.DatagramSocketOptions;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.metrics.Measured;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.SocketAddress;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/metrics/VertxMetrics.class */
public interface VertxMetrics extends Metrics, Measured {
    default void verticleDeployed(Verticle verticle) {
    }

    default void verticleUndeployed(Verticle verticle) {
    }

    default void timerCreated(long id) {
    }

    default void timerEnded(long id, boolean cancelled) {
    }

    default EventBusMetrics createEventBusMetrics() {
        return null;
    }

    default HttpServerMetrics<?, ?, ?> createHttpServerMetrics(HttpServerOptions options, SocketAddress localAddress) {
        return null;
    }

    default HttpClientMetrics<?, ?, ?, ?, ?> createHttpClientMetrics(HttpClientOptions options) {
        return null;
    }

    default TCPMetrics<?> createNetServerMetrics(NetServerOptions options, SocketAddress localAddress) {
        return null;
    }

    default TCPMetrics<?> createNetClientMetrics(NetClientOptions options) {
        return null;
    }

    default DatagramSocketMetrics createDatagramSocketMetrics(DatagramSocketOptions options) {
        return null;
    }

    default PoolMetrics<?> createPoolMetrics(String poolType, String poolName, int maxPoolSize) {
        return null;
    }

    default void vertxCreated(Vertx vertx) {
    }
}
