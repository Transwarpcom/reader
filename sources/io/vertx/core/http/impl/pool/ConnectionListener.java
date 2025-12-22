package io.vertx.core.http.impl.pool;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/pool/ConnectionListener.class */
public interface ConnectionListener<C> {
    void onConcurrencyChange(long j);

    void onRecycle(long j);

    void onEvict();
}
