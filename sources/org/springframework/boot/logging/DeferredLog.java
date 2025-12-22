package org.springframework.boot.logging;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/logging/DeferredLog.class */
public class DeferredLog implements Log {
    private volatile Log destination;
    private final List<Line> lines = new ArrayList();

    @Override // org.apache.commons.logging.Log
    public boolean isTraceEnabled() {
        boolean zIsTraceEnabled;
        synchronized (this.lines) {
            zIsTraceEnabled = this.destination != null ? this.destination.isTraceEnabled() : true;
        }
        return zIsTraceEnabled;
    }

    @Override // org.apache.commons.logging.Log
    public boolean isDebugEnabled() {
        boolean zIsDebugEnabled;
        synchronized (this.lines) {
            zIsDebugEnabled = this.destination != null ? this.destination.isDebugEnabled() : true;
        }
        return zIsDebugEnabled;
    }

    @Override // org.apache.commons.logging.Log
    public boolean isInfoEnabled() {
        boolean zIsInfoEnabled;
        synchronized (this.lines) {
            zIsInfoEnabled = this.destination != null ? this.destination.isInfoEnabled() : true;
        }
        return zIsInfoEnabled;
    }

    @Override // org.apache.commons.logging.Log
    public boolean isWarnEnabled() {
        boolean zIsWarnEnabled;
        synchronized (this.lines) {
            zIsWarnEnabled = this.destination != null ? this.destination.isWarnEnabled() : true;
        }
        return zIsWarnEnabled;
    }

    @Override // org.apache.commons.logging.Log
    public boolean isErrorEnabled() {
        boolean zIsErrorEnabled;
        synchronized (this.lines) {
            zIsErrorEnabled = this.destination != null ? this.destination.isErrorEnabled() : true;
        }
        return zIsErrorEnabled;
    }

    @Override // org.apache.commons.logging.Log
    public boolean isFatalEnabled() {
        boolean zIsFatalEnabled;
        synchronized (this.lines) {
            zIsFatalEnabled = this.destination != null ? this.destination.isFatalEnabled() : true;
        }
        return zIsFatalEnabled;
    }

    @Override // org.apache.commons.logging.Log
    public void trace(Object message) {
        log(LogLevel.TRACE, message, null);
    }

    @Override // org.apache.commons.logging.Log
    public void trace(Object message, Throwable t) {
        log(LogLevel.TRACE, message, t);
    }

    @Override // org.apache.commons.logging.Log
    public void debug(Object message) {
        log(LogLevel.DEBUG, message, null);
    }

    @Override // org.apache.commons.logging.Log
    public void debug(Object message, Throwable t) {
        log(LogLevel.DEBUG, message, t);
    }

    @Override // org.apache.commons.logging.Log
    public void info(Object message) {
        log(LogLevel.INFO, message, null);
    }

    @Override // org.apache.commons.logging.Log
    public void info(Object message, Throwable t) {
        log(LogLevel.INFO, message, t);
    }

    @Override // org.apache.commons.logging.Log
    public void warn(Object message) {
        log(LogLevel.WARN, message, null);
    }

    @Override // org.apache.commons.logging.Log
    public void warn(Object message, Throwable t) {
        log(LogLevel.WARN, message, t);
    }

    @Override // org.apache.commons.logging.Log
    public void error(Object message) {
        log(LogLevel.ERROR, message, null);
    }

    @Override // org.apache.commons.logging.Log
    public void error(Object message, Throwable t) {
        log(LogLevel.ERROR, message, t);
    }

    @Override // org.apache.commons.logging.Log
    public void fatal(Object message) {
        log(LogLevel.FATAL, message, null);
    }

    @Override // org.apache.commons.logging.Log
    public void fatal(Object message, Throwable t) {
        log(LogLevel.FATAL, message, t);
    }

    private void log(LogLevel level, Object message, Throwable t) {
        synchronized (this.lines) {
            if (this.destination != null) {
                logTo(this.destination, level, message, t);
            } else {
                this.lines.add(new Line(level, message, t));
            }
        }
    }

    public void switchTo(Class<?> destination) {
        switchTo(LogFactory.getLog(destination));
    }

    public void switchTo(Log destination) {
        synchronized (this.lines) {
            replayTo(destination);
            this.destination = destination;
        }
    }

    public void replayTo(Class<?> destination) {
        replayTo(LogFactory.getLog(destination));
    }

    public void replayTo(Log destination) {
        synchronized (this.lines) {
            for (Line line : this.lines) {
                logTo(destination, line.getLevel(), line.getMessage(), line.getThrowable());
            }
            this.lines.clear();
        }
    }

    public static Log replay(Log source, Class<?> destination) {
        return replay(source, LogFactory.getLog(destination));
    }

    public static Log replay(Log source, Log destination) {
        if (source instanceof DeferredLog) {
            ((DeferredLog) source).replayTo(destination);
        }
        return destination;
    }

    private static void logTo(Log log, LogLevel level, Object message, Throwable throwable) {
        switch (level) {
            case TRACE:
                log.trace(message, throwable);
                break;
            case DEBUG:
                log.debug(message, throwable);
                break;
            case INFO:
                log.info(message, throwable);
                break;
            case WARN:
                log.warn(message, throwable);
                break;
            case ERROR:
                log.error(message, throwable);
                break;
            case FATAL:
                log.fatal(message, throwable);
                break;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/logging/DeferredLog$Line.class */
    private static class Line {
        private final LogLevel level;
        private final Object message;
        private final Throwable throwable;

        Line(LogLevel level, Object message, Throwable throwable) {
            this.level = level;
            this.message = message;
            this.throwable = throwable;
        }

        public LogLevel getLevel() {
            return this.level;
        }

        public Object getMessage() {
            return this.message;
        }

        public Throwable getThrowable() {
            return this.throwable;
        }
    }
}
