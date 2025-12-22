package uk.org.lidalia.sysoutslf4j.system;

import io.netty.handler.codec.rtsp.RtspHeaders;
import java.lang.reflect.Method;
import uk.org.lidalia.sysoutslf4j.common.LoggerAppender;
import uk.org.lidalia.sysoutslf4j.common.ReflectionUtils;

/* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/system/LoggerAppenderProxy.class */
final class LoggerAppenderProxy implements LoggerAppender {
    private final Object targetLoggerAppender;
    private final Method appendMethod;
    private final Method appendAndLogMethod;

    private LoggerAppenderProxy(Object targetLoggerAppender) {
        try {
            Class<?> loggerAppenderClass = targetLoggerAppender.getClass();
            this.targetLoggerAppender = targetLoggerAppender;
            this.appendMethod = loggerAppenderClass.getDeclaredMethod(RtspHeaders.Values.APPEND, String.class);
            this.appendAndLogMethod = loggerAppenderClass.getDeclaredMethod("appendAndLog", String.class, String.class, Boolean.TYPE);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Must only be instantiated with a LoggerAppender instance, got a " + targetLoggerAppender.getClass(), e);
        }
    }

    @Override // uk.org.lidalia.sysoutslf4j.common.LoggerAppender
    public void append(String message) {
        ReflectionUtils.invokeMethod(this.appendMethod, this.targetLoggerAppender, message);
    }

    @Override // uk.org.lidalia.sysoutslf4j.common.LoggerAppender
    public void appendAndLog(String message, String className, boolean isStackTrace) {
        ReflectionUtils.invokeMethod(this.appendAndLogMethod, this.targetLoggerAppender, message, className, Boolean.valueOf(isStackTrace));
    }

    static LoggerAppender wrap(Object targetLoggerAppender) {
        LoggerAppender result;
        if (targetLoggerAppender instanceof LoggerAppender) {
            result = (LoggerAppender) targetLoggerAppender;
        } else {
            result = new LoggerAppenderProxy(targetLoggerAppender);
        }
        return result;
    }
}
