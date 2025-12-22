package io.vertx.core;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/Verticle.class */
public interface Verticle {
    Vertx getVertx();

    void init(Vertx vertx, Context context);

    @Deprecated
    void start(Future<Void> future) throws Exception;

    @Deprecated
    void stop(Future<Void> future) throws Exception;

    default void start(Promise<Void> startPromise) throws Exception {
        start((Future<Void>) startPromise);
    }

    default void stop(Promise<Void> stopPromise) throws Exception {
        stop((Future<Void>) stopPromise);
    }
}
