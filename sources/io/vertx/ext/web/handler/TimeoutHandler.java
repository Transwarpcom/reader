package io.vertx.ext.web.handler;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.impl.TimeoutHandlerImpl;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/TimeoutHandler.class */
public interface TimeoutHandler extends Handler<RoutingContext> {
    public static final long DEFAULT_TIMEOUT = 5000;
    public static final int DEFAULT_ERRORCODE = 503;

    static TimeoutHandler create() {
        return new TimeoutHandlerImpl(5000L, DEFAULT_ERRORCODE);
    }

    static TimeoutHandler create(long timeout) {
        return new TimeoutHandlerImpl(timeout, DEFAULT_ERRORCODE);
    }

    static TimeoutHandler create(long timeout, int errorCode) {
        return new TimeoutHandlerImpl(timeout, errorCode);
    }
}
