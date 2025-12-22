package io.vertx.core.shareddata;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/shareddata/Counter.class */
public interface Counter {
    void get(Handler<AsyncResult<Long>> handler);

    void incrementAndGet(Handler<AsyncResult<Long>> handler);

    void getAndIncrement(Handler<AsyncResult<Long>> handler);

    void decrementAndGet(Handler<AsyncResult<Long>> handler);

    void addAndGet(long j, Handler<AsyncResult<Long>> handler);

    void getAndAdd(long j, Handler<AsyncResult<Long>> handler);

    void compareAndSet(long j, long j2, Handler<AsyncResult<Boolean>> handler);
}
