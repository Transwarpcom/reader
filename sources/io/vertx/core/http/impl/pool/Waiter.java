package io.vertx.core.http.impl.pool;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/pool/Waiter.class */
final class Waiter<C> {
    public final Handler<AsyncResult<C>> handler;

    Waiter(Handler<AsyncResult<C>> handler) {
        this.handler = handler;
    }
}
