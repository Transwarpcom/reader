package io.vertx.ext.web.handler.impl;

import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.ResponseTimeHandler;
import java.util.concurrent.TimeUnit;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/ResponseTimeHandlerImpl.class */
public class ResponseTimeHandlerImpl implements ResponseTimeHandler {
    private static final CharSequence HEADER_NAME = HttpHeaders.createOptimized("x-response-time");

    @Override // io.vertx.core.Handler
    public void handle(RoutingContext ctx) {
        long start = System.nanoTime();
        ctx.addHeadersEndHandler(v -> {
            long duration = TimeUnit.MILLISECONDS.convert(System.nanoTime() - start, TimeUnit.NANOSECONDS);
            ctx.response().putHeader(HEADER_NAME, duration + "ms");
        });
        ctx.next();
    }
}
