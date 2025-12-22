package io.vertx.core;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/TimeoutStream.class */
public interface TimeoutStream extends ReadStream<Long> {
    @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    TimeoutStream exceptionHandler(Handler<Throwable> handler);

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    ReadStream<Long> handler2(Handler<Long> handler);

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    ReadStream<Long> pause2();

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    ReadStream<Long> resume2();

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    ReadStream<Long> fetch2(long j);

    @Override // io.vertx.core.streams.ReadStream
    ReadStream<Long> endHandler(Handler<Void> handler);

    void cancel();

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: endHandler, reason: avoid collision after fix types in other method */
    /* bridge */ /* synthetic */ default ReadStream<Long> endHandler2(Handler handler) {
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
