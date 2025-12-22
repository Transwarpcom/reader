package io.vertx.core.eventbus.impl;

import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/BodyReadStream.class */
public class BodyReadStream<T> implements ReadStream<T> {
    private ReadStream<Message<T>> delegate;

    @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    public BodyReadStream(ReadStream<Message<T>> delegate) {
        this.delegate = delegate;
    }

    @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public ReadStream<T> exceptionHandler(Handler<Throwable> handler) {
        this.delegate.exceptionHandler(handler);
        return null;
    }

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    public ReadStream<T> handler2(Handler<T> handler) {
        if (handler != null) {
            this.delegate.handler2(message -> {
                handler.handle(message.body());
            });
        } else {
            this.delegate.handler2(null);
        }
        return this;
    }

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    public ReadStream<T> pause2() {
        this.delegate.pause2();
        return this;
    }

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    public ReadStream<T> fetch2(long amount) {
        this.delegate.fetch2(amount);
        return this;
    }

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    public ReadStream<T> resume2() {
        this.delegate.resume2();
        return this;
    }

    @Override // io.vertx.core.streams.ReadStream
    public ReadStream<T> endHandler(Handler<Void> endHandler) {
        this.delegate.endHandler(endHandler);
        return this;
    }
}
