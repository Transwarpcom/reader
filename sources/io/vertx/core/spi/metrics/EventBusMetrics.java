package io.vertx.core.spi.metrics;

import io.vertx.core.eventbus.ReplyFailure;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/metrics/EventBusMetrics.class */
public interface EventBusMetrics<H> extends Metrics {
    default H handlerRegistered(String address, String repliedAddress) {
        return null;
    }

    default void handlerUnregistered(H handler) {
    }

    default void scheduleMessage(H handler, boolean local) {
    }

    default void beginHandleMessage(H handler, boolean local) {
    }

    default void endHandleMessage(H handler, Throwable failure) {
    }

    default void messageSent(String address, boolean publish, boolean local, boolean remote) {
    }

    default void messageReceived(String address, boolean publish, boolean local, int handlers) {
    }

    default void messageWritten(String address, int numberOfBytes) {
    }

    default void messageRead(String address, int numberOfBytes) {
    }

    default void replyFailure(String address, ReplyFailure failure) {
    }
}
