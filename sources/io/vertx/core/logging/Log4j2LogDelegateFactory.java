package io.vertx.core.logging;

import io.vertx.core.spi.logging.LogDelegate;
import io.vertx.core.spi.logging.LogDelegateFactory;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/logging/Log4j2LogDelegateFactory.class */
public class Log4j2LogDelegateFactory implements LogDelegateFactory {
    @Override // io.vertx.core.spi.logging.LogDelegateFactory
    public LogDelegate createDelegate(String name) {
        return new Log4j2LogDelegate(name);
    }
}
