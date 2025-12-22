package io.vertx.core.eventbus;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/MessageConsumer.class */
public interface MessageConsumer<T> extends ReadStream<Message<T>> {
    @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    MessageConsumer<T> exceptionHandler(Handler<Throwable> handler);

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    MessageConsumer<T> handler2(Handler<Message<T>> handler);

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    MessageConsumer<T> pause2();

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    MessageConsumer<T> resume2();

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    MessageConsumer<T> fetch2(long j);

    @Override // io.vertx.core.streams.ReadStream
    MessageConsumer<T> endHandler(Handler<Void> handler);

    ReadStream<T> bodyStream();

    boolean isRegistered();

    String address();

    MessageConsumer<T> setMaxBufferedMessages(int i);

    int getMaxBufferedMessages();

    void completionHandler(Handler<AsyncResult<Void>> handler);

    void unregister();

    void unregister(Handler<AsyncResult<Void>> handler);

    @Override // io.vertx.core.streams.ReadStream
    /* bridge */ /* synthetic */ default ReadStream endHandler(Handler handler) {
        return endHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    /* bridge */ /* synthetic */ default ReadStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    /* bridge */ /* synthetic */ default StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }
}
