package io.vertx.core.spi.metrics;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.ServerWebSocket;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/metrics/HttpServerMetrics.class */
public interface HttpServerMetrics<R, W, S> extends TCPMetrics<S> {
    default R requestBegin(S socketMetric, HttpServerRequest request) {
        return null;
    }

    default void requestReset(R requestMetric) {
    }

    default void responseBegin(R requestMetric, HttpServerResponse response) {
    }

    default R responsePushed(S socketMetric, HttpMethod method, String uri, HttpServerResponse response) {
        return null;
    }

    default void responseEnd(R requestMetric, HttpServerResponse response) {
    }

    default W connected(S socketMetric, R requestMetric, ServerWebSocket serverWebSocket) {
        return null;
    }

    default void disconnected(W serverWebSocketMetric) {
    }
}
