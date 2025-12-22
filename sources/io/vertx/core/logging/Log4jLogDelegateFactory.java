package io.vertx.core.logging;

import io.vertx.core.spi.logging.LogDelegate;
import io.vertx.core.spi.logging.LogDelegateFactory;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/logging/Log4jLogDelegateFactory.class */
public class Log4jLogDelegateFactory implements LogDelegateFactory {
    @Override // io.vertx.core.spi.logging.LogDelegateFactory
    public LogDelegate createDelegate(String name) {
        return new Log4jLogDelegate(name);
    }
}
