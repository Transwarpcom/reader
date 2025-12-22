package io.vertx.ext.web.handler.sockjs.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/TransportListener.class */
interface TransportListener {
    void sendFrame(String str, Handler<AsyncResult<Void>> handler);

    void close();

    void sessionClosed();
}
