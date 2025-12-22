package io.vertx.core.spi;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.net.impl.transport.Transport;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/VertxFactory.class */
public interface VertxFactory {
    Vertx vertx();

    Vertx vertx(VertxOptions vertxOptions);

    Vertx vertx(VertxOptions vertxOptions, Transport transport);

    void clusteredVertx(VertxOptions vertxOptions, Handler<AsyncResult<Vertx>> handler);

    void clusteredVertx(VertxOptions vertxOptions, Transport transport, Handler<AsyncResult<Vertx>> handler);

    Context context();
}
