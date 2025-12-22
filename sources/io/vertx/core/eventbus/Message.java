package io.vertx.core.eventbus;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/Message.class */
public interface Message<T> {
    String address();

    MultiMap headers();

    @CacheReturn
    T body();

    String replyAddress();

    boolean isSend();

    void reply(Object obj);

    @Deprecated
    <R> void reply(Object obj, Handler<AsyncResult<Message<R>>> handler);

    void reply(Object obj, DeliveryOptions deliveryOptions);

    @Deprecated
    <R> void reply(Object obj, DeliveryOptions deliveryOptions, Handler<AsyncResult<Message<R>>> handler);

    void fail(int i, String str);

    default <R> void replyAndRequest(Object message, Handler<AsyncResult<Message<R>>> replyHandler) {
        reply(message, replyHandler);
    }

    default <R> void replyAndRequest(Object message, DeliveryOptions options, Handler<AsyncResult<Message<R>>> replyHandler) {
        reply(message, options, replyHandler);
    }
}
