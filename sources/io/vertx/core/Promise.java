package io.vertx.core;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/Promise.class */
public interface Promise<T> extends Handler<AsyncResult<T>> {
    @Override // io.vertx.core.Handler
    @GenIgnore
    void handle(AsyncResult<T> asyncResult);

    void complete(T t);

    void complete();

    void fail(Throwable th);

    void fail(String str);

    boolean tryComplete(T t);

    boolean tryComplete();

    boolean tryFail(Throwable th);

    boolean tryFail(String str);

    @CacheReturn
    Future<T> future();

    static <T> Promise<T> promise() {
        return Future.factory.promise();
    }
}
