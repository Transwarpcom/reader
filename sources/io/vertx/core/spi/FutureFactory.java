package io.vertx.core.spi;

import io.vertx.core.Future;
import io.vertx.core.Promise;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/FutureFactory.class */
public interface FutureFactory {
    <T> Promise<T> promise();

    <T> Future<T> future();

    <T> Future<T> succeededFuture();

    <T> Future<T> succeededFuture(T t);

    <T> Future<T> failedFuture(Throwable th);

    <T> Future<T> failureFuture(String str);
}
