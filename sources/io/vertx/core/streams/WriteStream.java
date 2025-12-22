package io.vertx.core.streams;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@VertxGen(concrete = false)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/streams/WriteStream.class */
public interface WriteStream<T> extends StreamBase {
    @Override // io.vertx.core.streams.StreamBase
    WriteStream<T> exceptionHandler(Handler<Throwable> handler);

    @Fluent
    WriteStream<T> write(T t);

    @Fluent
    WriteStream<T> write(T t, Handler<AsyncResult<Void>> handler);

    void end();

    void end(Handler<AsyncResult<Void>> handler);

    @Fluent
    WriteStream<T> setWriteQueueMaxSize(int i);

    boolean writeQueueFull();

    @Fluent
    WriteStream<T> drainHandler(Handler<Void> handler);

    @Override // io.vertx.core.streams.StreamBase
    /* bridge */ /* synthetic */ default StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    default void end(T data) {
        write(data);
        end();
    }

    default void end(T data, Handler<AsyncResult<Void>> handler) {
        if (handler != null) {
            write(data, ar -> {
                if (ar.succeeded()) {
                    end((Handler<AsyncResult<Void>>) handler);
                } else {
                    handler.handle(ar);
                }
            });
        } else {
            end((WriteStream<T>) data);
        }
    }
}
