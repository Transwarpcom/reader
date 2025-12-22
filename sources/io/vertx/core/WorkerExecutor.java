package io.vertx.core;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.metrics.Measured;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/WorkerExecutor.class */
public interface WorkerExecutor extends Measured {
    <T> void executeBlocking(Handler<Promise<T>> handler, boolean z, Handler<AsyncResult<T>> handler2);

    default <T> void executeBlocking(Handler<Promise<T>> blockingCodeHandler, Handler<AsyncResult<T>> resultHandler) {
        executeBlocking(blockingCodeHandler, true, resultHandler);
    }

    default void close() {
    }
}
