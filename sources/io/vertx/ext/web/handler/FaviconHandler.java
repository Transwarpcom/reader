package io.vertx.ext.web.handler;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.impl.FaviconHandlerImpl;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/FaviconHandler.class */
public interface FaviconHandler extends Handler<RoutingContext> {
    public static final long DEFAULT_MAX_AGE_SECONDS = 86400;

    static FaviconHandler create() {
        return new FaviconHandlerImpl();
    }

    static FaviconHandler create(String path) {
        return new FaviconHandlerImpl(path);
    }

    static FaviconHandler create(String path, long maxAgeSeconds) {
        return new FaviconHandlerImpl(path, maxAgeSeconds);
    }

    static FaviconHandler create(long maxAgeSeconds) {
        return new FaviconHandlerImpl(maxAgeSeconds);
    }
}
