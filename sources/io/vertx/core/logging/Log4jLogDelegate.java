package io.vertx.core.logging;

import io.vertx.core.spi.logging.LogDelegate;
import org.apache.log4j.Level;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/logging/Log4jLogDelegate.class */
public class Log4jLogDelegate implements LogDelegate {
    private static final String FQCN = Logger.class.getCanonicalName();
    private final org.apache.log4j.Logger logger;

    Log4jLogDelegate(String name) {
        this.logger = org.apache.log4j.Logger.getLogger(name);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public boolean isWarnEnabled() {
        return this.logger.isEnabledFor(Level.WARN);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public boolean isInfoEnabled() {
        return this.logger.isInfoEnabled();
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public boolean isDebugEnabled() {
        return this.logger.isDebugEnabled();
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public boolean isTraceEnabled() {
        return this.logger.isTraceEnabled();
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void fatal(Object message) {
        log(Level.FATAL, message);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void fatal(Object message, Throwable t) {
        log(Level.FATAL, message, t);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void error(Object message) {
        log(Level.ERROR, message);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void error(Object message, Object... params) {
        throwUnsupportedOperationException();
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void error(Object message, Throwable t) {
        log(Level.ERROR, message, t);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void error(Object message, Throwable t, Object... params) {
        throwUnsupportedOperationException();
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void warn(Object message) {
        log(Level.WARN, message);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void warn(Object message, Object... params) {
        throwUnsupportedOperationException();
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void warn(Object message, Throwable t) {
        log(Level.WARN, message, t);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void warn(Object message, Throwable t, Object... params) {
        throwUnsupportedOperationException();
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void info(Object message) {
        log(Level.INFO, message);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void info(Object message, Object... params) {
        throwUnsupportedOperationException();
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void info(Object message, Throwable t) {
        log(Level.INFO, message, t);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void info(Object message, Throwable t, Object... params) {
        throwUnsupportedOperationException();
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void debug(Object message) {
        log(Level.DEBUG, message);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void debug(Object message, Object... params) {
        throwUnsupportedOperationException();
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void debug(Object message, Throwable t) {
        log(Level.DEBUG, message, t);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void debug(Object message, Throwable t, Object... params) {
        throwUnsupportedOperationException();
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void trace(Object message) {
        log(Level.TRACE, message);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void trace(Object message, Object... params) {
        throwUnsupportedOperationException();
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void trace(Object message, Throwable t) {
        log(Level.TRACE, message, t);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void trace(Object message, Throwable t, Object... params) {
        throwUnsupportedOperationException();
    }

    private void log(Level level, Object message) {
        log(level, message, null);
    }

    private void log(Level level, Object message, Throwable t) {
        this.logger.log(FQCN, level, message, t);
    }

    private void throwUnsupportedOperationException() {
        throw new UnsupportedOperationException("Log4j version used in Vertx doesn't support parameterized logging.");
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public Object unwrap() {
        return this.logger;
    }
}
