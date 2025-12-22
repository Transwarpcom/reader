package io.vertx.core;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/Closeable.class */
public interface Closeable {
    void close(Handler<AsyncResult<Void>> handler);
}
