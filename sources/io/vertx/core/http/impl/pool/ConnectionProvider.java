package io.vertx.core.http.impl.pool;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.impl.ContextInternal;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/pool/ConnectionProvider.class */
public interface ConnectionProvider<C> {
    void connect(ConnectionListener<C> connectionListener, ContextInternal contextInternal, Handler<AsyncResult<ConnectResult<C>>> handler);

    void close(C c);
}
