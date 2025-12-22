package io.vertx.core.logging;

import io.vertx.core.spi.logging.LogDelegate;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.FormattedMessage;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.spi.ExtendedLogger;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/logging/Log4j2LogDelegate.class */
public class Log4j2LogDelegate implements LogDelegate {
    final ExtendedLogger logger;
    static final String FQCN = Logger.class.getCanonicalName();

    Log4j2LogDelegate(String name) {
        this.logger = (ExtendedLogger) LogManager.getLogger(name);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public boolean isWarnEnabled() {
        return this.logger.isWarnEnabled();
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
        log(Level.ERROR, message.toString(), params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void error(Object message, Throwable t) {
        log(Level.ERROR, message, t);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void error(Object message, Throwable t, Object... params) {
        log(Level.ERROR, message.toString(), t, params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void warn(Object message) {
        log(Level.WARN, message);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void warn(Object message, Object... params) {
        log(Level.WARN, message.toString(), params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void warn(Object message, Throwable t) {
        log(Level.WARN, message, t);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void warn(Object message, Throwable t, Object... params) {
        log(Level.WARN, message.toString(), t, params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void info(Object message) {
        log(Level.INFO, message);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void info(Object message, Object... params) {
        log(Level.INFO, message.toString(), params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void info(Object message, Throwable t) {
        log(Level.INFO, message, t);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void info(Object message, Throwable t, Object... params) {
        log(Level.INFO, message.toString(), t, params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void debug(Object message) {
        log(Level.DEBUG, message);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void debug(Object message, Object... params) {
        log(Level.DEBUG, message.toString(), params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void debug(Object message, Throwable t) {
        log(Level.DEBUG, message, t);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void debug(Object message, Throwable t, Object... params) {
        log(Level.DEBUG, message.toString(), t, params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void trace(Object message) {
        log(Level.TRACE, message);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void trace(Object message, Object... params) {
        log(Level.TRACE, message.toString(), params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void trace(Object message, Throwable t) {
        log(Level.TRACE, message.toString(), t);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void trace(Object message, Throwable t, Object... params) {
        log(Level.TRACE, message.toString(), t, params);
    }

    private void log(Level level, Object message) {
        log(level, message, (Throwable) null);
    }

    private void log(Level level, Object message, Throwable t) {
        if (message instanceof Message) {
            this.logger.logIfEnabled(FQCN, level, (Marker) null, (Message) message, t);
        } else {
            this.logger.logIfEnabled(FQCN, level, (Marker) null, message, t);
        }
    }

    private void log(Level level, String message, Object... params) {
        this.logger.logIfEnabled(FQCN, level, (Marker) null, message, params);
    }

    private void log(Level level, String message, Throwable t, Object... params) {
        this.logger.logIfEnabled(FQCN, level, (Marker) null, (Message) new FormattedMessage(message, params), t);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public Object unwrap() {
        return this.logger;
    }
}
