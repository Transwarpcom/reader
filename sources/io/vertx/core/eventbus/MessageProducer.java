package io.vertx.core.eventbus;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.WriteStream;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/MessageProducer.class */
public interface MessageProducer<T> extends WriteStream<T> {
    public static final int DEFAULT_WRITE_QUEUE_MAX_SIZE = 1000;

    @Deprecated
    MessageProducer<T> send(T t);

    @Deprecated
    <R> MessageProducer<T> send(T t, Handler<AsyncResult<Message<R>>> handler);

    @Override // io.vertx.core.streams.WriteStream, io.vertx.core.streams.StreamBase
    MessageProducer<T> exceptionHandler(Handler<Throwable> handler);

    @Override // io.vertx.core.streams.WriteStream
    MessageProducer<T> write(T t);

    @Override // io.vertx.core.streams.WriteStream
    @Fluent
    MessageProducer<T> write(T t, Handler<AsyncResult<Void>> handler);

    @Override // io.vertx.core.streams.WriteStream
    /* renamed from: setWriteQueueMaxSize */
    MessageProducer<T> setWriteQueueMaxSize2(int i);

    @Override // io.vertx.core.streams.WriteStream
    MessageProducer<T> drainHandler(Handler<Void> handler);

    @Fluent
    MessageProducer<T> deliveryOptions(DeliveryOptions deliveryOptions);

    String address();

    @Override // io.vertx.core.streams.WriteStream
    void end();

    @Override // io.vertx.core.streams.WriteStream
    void end(Handler<AsyncResult<Void>> handler);

    void close();

    void close(Handler<AsyncResult<Void>> handler);

    @Override // io.vertx.core.streams.WriteStream
    /* bridge */ /* synthetic */ default WriteStream drainHandler(Handler handler) {
        return drainHandler((Handler<Void>) handler);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.vertx.core.streams.WriteStream
    @Fluent
    /* bridge */ /* synthetic */ default WriteStream write(Object obj, Handler handler) {
        return write((MessageProducer<T>) obj, (Handler<AsyncResult<Void>>) handler);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.vertx.core.streams.WriteStream
    /* bridge */ /* synthetic */ default WriteStream write(Object obj) {
        return write((MessageProducer<T>) obj);
    }

    @Override // io.vertx.core.streams.WriteStream, io.vertx.core.streams.StreamBase
    /* bridge */ /* synthetic */ default WriteStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.streams.WriteStream, io.vertx.core.streams.StreamBase
    /* bridge */ /* synthetic */ default StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }
}
