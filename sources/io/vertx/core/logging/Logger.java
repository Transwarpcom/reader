package io.vertx.core.logging;

import io.vertx.core.spi.logging.LogDelegate;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/logging/Logger.class */
public class Logger {
    final LogDelegate delegate;

    public Logger(LogDelegate delegate) {
        this.delegate = delegate;
    }

    public boolean isWarnEnabled() {
        return this.delegate.isWarnEnabled();
    }

    public boolean isInfoEnabled() {
        return this.delegate.isInfoEnabled();
    }

    public boolean isDebugEnabled() {
        return this.delegate.isDebugEnabled();
    }

    public boolean isTraceEnabled() {
        return this.delegate.isTraceEnabled();
    }

    public void fatal(Object message) {
        this.delegate.fatal(message);
    }

    public void fatal(Object message, Throwable t) {
        this.delegate.fatal(message, t);
    }

    public void error(Object message) {
        this.delegate.error(message);
    }

    public void error(Object message, Throwable t) {
        this.delegate.error(message, t);
    }

    public void error(Object message, Object... objects) {
        this.delegate.error(message, objects);
    }

    public void error(Object message, Throwable t, Object... objects) {
        this.delegate.error(message, t, objects);
    }

    public void warn(Object message) {
        this.delegate.warn(message);
    }

    public void warn(Object message, Throwable t) {
        this.delegate.warn(message, t);
    }

    public void warn(Object message, Object... objects) {
        this.delegate.warn(message, objects);
    }

    public void warn(Object message, Throwable t, Object... objects) {
        this.delegate.warn(message, t, objects);
    }

    public void info(Object message) {
        this.delegate.info(message);
    }

    public void info(Object message, Throwable t) {
        this.delegate.info(message, t);
    }

    public void info(Object message, Object... objects) {
        this.delegate.info(message, objects);
    }

    public void info(Object message, Throwable t, Object... objects) {
        this.delegate.info(message, t, objects);
    }

    public void debug(Object message) {
        this.delegate.debug(message);
    }

    public void debug(Object message, Throwable t) {
        this.delegate.debug(message, t);
    }

    public void debug(Object message, Object... objects) {
        this.delegate.debug(message, objects);
    }

    public void debug(Object message, Throwable t, Object... objects) {
        this.delegate.debug(message, t, objects);
    }

    public void trace(Object message) {
        this.delegate.trace(message);
    }

    public void trace(Object message, Throwable t) {
        this.delegate.trace(message, t);
    }

    public void trace(Object message, Object... objects) {
        this.delegate.trace(message, objects);
    }

    public void trace(Object message, Throwable t, Object... objects) {
        this.delegate.trace(message, t, objects);
    }

    public LogDelegate getDelegate() {
        return this.delegate;
    }
}
