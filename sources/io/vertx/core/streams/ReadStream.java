package io.vertx.core.streams;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.streams.impl.PipeImpl;

@VertxGen(concrete = false)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/streams/ReadStream.class */
public interface ReadStream<T> extends StreamBase {
    @Override // io.vertx.core.streams.StreamBase
    ReadStream<T> exceptionHandler(Handler<Throwable> handler);

    @Fluent
    /* renamed from: handler */
    ReadStream<T> handler2(Handler<T> handler);

    @Fluent
    /* renamed from: pause */
    ReadStream<T> pause2();

    @Fluent
    /* renamed from: resume */
    ReadStream<T> resume2();

    @Fluent
    /* renamed from: fetch */
    ReadStream<T> fetch2(long j);

    @Fluent
    ReadStream<T> endHandler(Handler<Void> handler);

    @Override // io.vertx.core.streams.StreamBase
    /* bridge */ /* synthetic */ default StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    default Pipe<T> pipe() {
        pause2();
        return new PipeImpl(this);
    }

    default void pipeTo(WriteStream<T> dst) {
        new PipeImpl(this).to(dst);
    }

    default void pipeTo(WriteStream<T> dst, Handler<AsyncResult<Void>> handler) {
        new PipeImpl(this).to(dst, handler);
    }
}
