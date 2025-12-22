package io.vertx.ext.web.handler.impl;

import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.TimeoutHandler;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/TimeoutHandlerImpl.class */
public class TimeoutHandlerImpl implements TimeoutHandler {
    private final long timeout;
    private final int errorCode;

    public TimeoutHandlerImpl(long timeout, int errorCode) {
        this.timeout = timeout;
        this.errorCode = errorCode;
    }

    @Override // io.vertx.core.Handler
    public void handle(RoutingContext ctx) {
        long tid = ctx.vertx().setTimer(this.timeout, t -> {
            ctx.fail(this.errorCode);
        });
        ctx.addBodyEndHandler(v -> {
            ctx.vertx().cancelTimer(tid);
        });
        ctx.next();
    }
}
