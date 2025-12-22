package io.vertx.core.logging;

import ch.qos.logback.core.joran.action.ActionConst;
import io.vertx.core.spi.logging.LogDelegate;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/logging/SLF4JLogDelegate.class */
public class SLF4JLogDelegate implements LogDelegate {
    private static final String FQCN = Logger.class.getCanonicalName();
    private final org.slf4j.Logger logger;

    SLF4JLogDelegate(String name) {
        this.logger = org.slf4j.LoggerFactory.getLogger(name);
    }

    public SLF4JLogDelegate(Object logger) {
        this.logger = (org.slf4j.Logger) logger;
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
        log(40, message);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void fatal(Object message, Throwable t) {
        log(40, message, t);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void error(Object message) {
        log(40, message);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void error(Object message, Object... params) {
        log(40, message, null, params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void error(Object message, Throwable t) {
        log(40, message, t);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void error(Object message, Throwable t, Object... params) {
        log(40, message, t, params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void warn(Object message) {
        log(30, message);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void warn(Object message, Object... params) {
        log(30, message, null, params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void warn(Object message, Throwable t) {
        log(30, message, t);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void warn(Object message, Throwable t, Object... params) {
        log(30, message, t, params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void info(Object message) {
        log(20, message);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void info(Object message, Object... params) {
        log(20, message, null, params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void info(Object message, Throwable t) {
        log(20, message, t);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void info(Object message, Throwable t, Object... params) {
        log(20, message, t, params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void debug(Object message) {
        log(10, message);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void debug(Object message, Object... params) {
        log(10, message, null, params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void debug(Object message, Throwable t) {
        log(10, message, t);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void debug(Object message, Throwable t, Object... params) {
        log(10, message, t, params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void trace(Object message) {
        log(0, message);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void trace(Object message, Object... params) {
        log(0, message, null, params);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void trace(Object message, Throwable t) {
        log(0, message, t);
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public void trace(Object message, Throwable t, Object... params) {
        log(0, message, t, params);
    }

    private void log(int level, Object message) {
        log(level, message, null);
    }

    private void log(int level, Object message, Throwable t) {
        log(level, message, t, (Object[]) null);
    }

    private void log(int level, Object message, Throwable t, Object... params) {
        String msg = message == null ? ActionConst.NULL : message.toString();
        Object[] parameters = params;
        if (params != null && t != null) {
            parameters = new Object[params.length + 1];
            System.arraycopy(params, 0, parameters, 0, params.length);
            parameters[params.length] = t;
        } else if (params == null && t != null) {
            parameters = new Object[]{t};
        }
        if (this.logger instanceof LocationAwareLogger) {
            if ((level == 0 && this.logger.isTraceEnabled()) || ((level == 10 && this.logger.isDebugEnabled()) || ((level == 20 && this.logger.isInfoEnabled()) || ((level == 30 && this.logger.isWarnEnabled()) || (level == 40 && this.logger.isErrorEnabled()))))) {
                LocationAwareLogger l = (LocationAwareLogger) this.logger;
                FormattingTuple ft = MessageFormatter.arrayFormat(msg, parameters);
                l.log(null, FQCN, level, ft.getMessage(), null, ft.getThrowable());
                return;
            }
            return;
        }
        switch (level) {
            case 0:
                this.logger.trace(msg, parameters);
                return;
            case 10:
                this.logger.debug(msg, parameters);
                return;
            case 20:
                this.logger.info(msg, parameters);
                return;
            case 30:
                this.logger.warn(msg, parameters);
                return;
            case 40:
                this.logger.error(msg, parameters);
                return;
            default:
                throw new IllegalArgumentException("Unknown log level " + level);
        }
    }

    @Override // io.vertx.core.spi.logging.LogDelegate
    public Object unwrap() {
        return this.logger;
    }
}
