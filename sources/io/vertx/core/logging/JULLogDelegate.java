package io.vertx.core.logging;

import ch.qos.logback.core.joran.action.ActionConst;
import io.vertx.core.spi.logging.LogDelegate;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/logging/JULLogDelegate.class */
public class JULLogDelegate implements LogDelegate {
    private final java.util.logging.Logger logger;

    JULLogDelegate(String name) {
        this.logger = java.util.logging.Logger.getLogger(name);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public boolean isWarnEnabled() {
        return this.logger.isLoggable(Level.WARNING);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public boolean isInfoEnabled() {
        return this.logger.isLoggable(Level.INFO);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public boolean isDebugEnabled() {
        return this.logger.isLoggable(Level.FINE);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public boolean isTraceEnabled() {
        return this.logger.isLoggable(Level.FINEST);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void fatal(Object message) {
        log(Level.SEVERE, message);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void fatal(Object message, Throwable t) {
        log(Level.SEVERE, message, t);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void error(Object message) {
        log(Level.SEVERE, message);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void error(Object message, Object... params) {
        log(Level.SEVERE, message, null, params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void error(Object message, Throwable t) {
        log(Level.SEVERE, message, t);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void error(Object message, Throwable t, Object... params) {
        log(Level.SEVERE, message, t, params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void warn(Object message) {
        log(Level.WARNING, message);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void warn(Object message, Object... params) {
        log(Level.WARNING, message, null, params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void warn(Object message, Throwable t) {
        log(Level.WARNING, message, t);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void warn(Object message, Throwable t, Object... params) {
        log(Level.WARNING, message, t, params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void info(Object message) {
        log(Level.INFO, message);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void info(Object message, Object... params) {
        log(Level.INFO, message, null, params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void info(Object message, Throwable t) {
        log(Level.INFO, message, t);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void info(Object message, Throwable t, Object... params) {
        log(Level.INFO, message, t, params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void debug(Object message) {
        log(Level.FINE, message);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void debug(Object message, Object... params) {
        log(Level.FINE, message, null, params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void debug(Object message, Throwable t) {
        log(Level.FINE, message, t);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void debug(Object message, Throwable t, Object... params) {
        log(Level.FINE, message, t, params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void trace(Object message) {
        log(Level.FINEST, message);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void trace(Object message, Object... params) {
        log(Level.FINEST, message, null, params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void trace(Object message, Throwable t) {
        log(Level.FINEST, message, t);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void trace(Object message, Throwable t, Object... params) {
        log(Level.FINEST, message, t, params);
    }

    private void log(Level level, Object message) {
        log(level, message, null);
    }

    private void log(Level level, Object message, Throwable t, Object... params) {
        if (!this.logger.isLoggable(level)) {
            return;
        }
        String msg = message == null ? ActionConst.NULL : message.toString();
        LogRecord record = new LogRecord(level, msg);
        record.setLoggerName(this.logger.getName());
        if (t != null) {
            record.setThrown(t);
        } else if (params != null && params.length != 0 && (params[params.length - 1] instanceof Throwable)) {
            record.setThrown((Throwable) params[params.length - 1]);
        }
        record.setSourceClassName(null);
        record.setParameters(params);
        this.logger.log(record);
    }

    private void log(Level level, Object message, Throwable t) {
        log(level, message, t, (Object[]) null);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public Object unwrap() {
        return this.logger;
    }
}
