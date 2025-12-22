package io.vertx.core.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.net.impl.transport.Transport;
import io.vertx.core.spi.VertxFactory;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/VertxFactoryImpl.class */
public class VertxFactoryImpl implements VertxFactory {
    @Override // io.vertx.core.spi.VertxFactory
    public Vertx vertx() {
        return vertx(new VertxOptions());
    }

    @Override // io.vertx.core.spi.VertxFactory
    public Vertx vertx(VertxOptions options) {
        return vertx(options, Transport.transport(options.getPreferNativeTransport()));
    }

    @Override // io.vertx.core.spi.VertxFactory
    public Vertx vertx(VertxOptions options, Transport transport) {
        if (options.getEventBusOptions().isClustered()) {
            throw new IllegalArgumentException("Please use Vertx.clusteredVertx() to create a clustered Vert.x instance");
        }
        return VertxImpl.vertx(options, transport);
    }

    @Override // io.vertx.core.spi.VertxFactory
    public void clusteredVertx(VertxOptions options, Handler<AsyncResult<Vertx>> resultHandler) {
        clusteredVertx(options, Transport.transport(options.getPreferNativeTransport()), resultHandler);
    }

    @Override // io.vertx.core.spi.VertxFactory
    public void clusteredVertx(VertxOptions options, Transport transport, Handler<AsyncResult<Vertx>> resultHandler) {
        options.getEventBusOptions().setClustered(true);
        VertxImpl.clusteredVertx(options, transport, resultHandler);
    }

    @Override // io.vertx.core.spi.VertxFactory
    public Context context() {
        return ContextImpl.context();
    }
}
