package io.vertx.core.eventbus;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.metrics.Measured;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/EventBus.class */
public interface EventBus extends Measured {
    @Fluent
    EventBus send(String str, Object obj);

    @Fluent
    @Deprecated
    <T> EventBus send(String str, Object obj, Handler<AsyncResult<Message<T>>> handler);

    @Fluent
    EventBus send(String str, Object obj, DeliveryOptions deliveryOptions);

    @Fluent
    @Deprecated
    <T> EventBus send(String str, Object obj, DeliveryOptions deliveryOptions, Handler<AsyncResult<Message<T>>> handler);

    @Fluent
    EventBus publish(String str, Object obj);

    @Fluent
    EventBus publish(String str, Object obj, DeliveryOptions deliveryOptions);

    <T> MessageConsumer<T> consumer(String str);

    <T> MessageConsumer<T> consumer(String str, Handler<Message<T>> handler);

    <T> MessageConsumer<T> localConsumer(String str);

    <T> MessageConsumer<T> localConsumer(String str, Handler<Message<T>> handler);

    <T> MessageProducer<T> sender(String str);

    <T> MessageProducer<T> sender(String str, DeliveryOptions deliveryOptions);

    <T> MessageProducer<T> publisher(String str);

    <T> MessageProducer<T> publisher(String str, DeliveryOptions deliveryOptions);

    @GenIgnore({"permitted-type"})
    EventBus registerCodec(MessageCodec messageCodec);

    @GenIgnore({"permitted-type"})
    EventBus unregisterCodec(String str);

    @GenIgnore
    <T> EventBus registerDefaultCodec(Class<T> cls, MessageCodec<T, ?> messageCodec);

    @GenIgnore
    EventBus unregisterDefaultCodec(Class cls);

    @GenIgnore
    void start(Handler<AsyncResult<Void>> handler);

    @GenIgnore
    void close(Handler<AsyncResult<Void>> handler);

    @Fluent
    <T> EventBus addOutboundInterceptor(Handler<DeliveryContext<T>> handler);

    @Fluent
    <T> EventBus removeOutboundInterceptor(Handler<DeliveryContext<T>> handler);

    @Fluent
    <T> EventBus addInboundInterceptor(Handler<DeliveryContext<T>> handler);

    @Fluent
    <T> EventBus removeInboundInterceptor(Handler<DeliveryContext<T>> handler);

    @Fluent
    default <T> EventBus request(String address, Object message, Handler<AsyncResult<Message<T>>> replyHandler) {
        return send(address, message, replyHandler);
    }

    @Fluent
    default <T> EventBus request(String address, Object message, DeliveryOptions options, Handler<AsyncResult<Message<T>>> replyHandler) {
        return send(address, message, options, replyHandler);
    }
}
