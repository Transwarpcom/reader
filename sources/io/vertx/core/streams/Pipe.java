package io.vertx.core.streams;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/streams/Pipe.class */
public interface Pipe<T> {
    @Fluent
    Pipe<T> endOnFailure(boolean z);

    @Fluent
    Pipe<T> endOnSuccess(boolean z);

    @Fluent
    Pipe<T> endOnComplete(boolean z);

    void to(WriteStream<T> writeStream);

    void to(WriteStream<T> writeStream, Handler<AsyncResult<Void>> handler);

    void close();
}
