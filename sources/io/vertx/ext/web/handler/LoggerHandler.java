package io.vertx.ext.web.handler;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.impl.LoggerHandlerImpl;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/LoggerHandler.class */
public interface LoggerHandler extends Handler<RoutingContext> {
    public static final LoggerFormat DEFAULT_FORMAT = LoggerFormat.DEFAULT;

    static LoggerHandler create() {
        return new LoggerHandlerImpl(DEFAULT_FORMAT);
    }

    static LoggerHandler create(LoggerFormat format) {
        return new LoggerHandlerImpl(format);
    }

    static LoggerHandler create(boolean immediate, LoggerFormat format) {
        return new LoggerHandlerImpl(immediate, format);
    }
}
