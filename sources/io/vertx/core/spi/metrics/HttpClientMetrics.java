package io.vertx.core.spi.metrics;

import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.WebSocket;
import io.vertx.core.net.SocketAddress;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/metrics/HttpClientMetrics.class */
public interface HttpClientMetrics<R, W, S, E, T> extends TCPMetrics<S> {
    default E createEndpoint(String host, int port, int maxPoolSize) {
        return null;
    }

    default void closeEndpoint(String host, int port, E endpointMetric) {
    }

    default T enqueueRequest(E endpointMetric) {
        return null;
    }

    default void dequeueRequest(E endpointMetric, T taskMetric) {
    }

    default void endpointConnected(E endpointMetric, S socketMetric) {
    }

    default void endpointDisconnected(E endpointMetric, S socketMetric) {
    }

    default R requestBegin(E endpointMetric, S socketMetric, SocketAddress localAddress, SocketAddress remoteAddress, HttpClientRequest request) {
        return null;
    }

    default void requestEnd(R requestMetric) {
    }

    default void responseBegin(R requestMetric, HttpClientResponse response) {
    }

    default R responsePushed(E endpointMetric, S socketMetric, SocketAddress localAddress, SocketAddress remoteAddress, HttpClientRequest request) {
        return null;
    }

    default void requestReset(R requestMetric) {
    }

    default void responseEnd(R requestMetric, HttpClientResponse response) {
    }

    default W connected(E endpointMetric, S socketMetric, WebSocket webSocket) {
        return null;
    }

    default void disconnected(W webSocketMetric) {
    }
}
