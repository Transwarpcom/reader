package io.vertx.core.spi.logging;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/logging/LogDelegate.class */
public interface LogDelegate {
    boolean isWarnEnabled();

    boolean isInfoEnabled();

    boolean isDebugEnabled();

    boolean isTraceEnabled();

    void fatal(Object obj);

    void fatal(Object obj, Throwable th);

    void error(Object obj);

    void error(Object obj, Object... objArr);

    void error(Object obj, Throwable th);

    void error(Object obj, Throwable th, Object... objArr);

    void warn(Object obj);

    void warn(Object obj, Object... objArr);

    void warn(Object obj, Throwable th);

    void warn(Object obj, Throwable th, Object... objArr);

    void info(Object obj);

    void info(Object obj, Object... objArr);

    void info(Object obj, Throwable th);

    void info(Object obj, Throwable th, Object... objArr);

    void debug(Object obj);

    void debug(Object obj, Object... objArr);

    void debug(Object obj, Throwable th);

    void debug(Object obj, Throwable th, Object... objArr);

    void trace(Object obj);

    void trace(Object obj, Object... objArr);

    void trace(Object obj, Throwable th);

    void trace(Object obj, Throwable th, Object... objArr);

    default Object unwrap() {
        return null;
    }
}
