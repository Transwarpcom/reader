package com.mongodb.diagnostics.logging;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/diagnostics/logging/Logger.class */
public interface Logger {
    String getName();

    boolean isTraceEnabled();

    void trace(String str);

    void trace(String str, Throwable th);

    boolean isDebugEnabled();

    void debug(String str);

    void debug(String str, Throwable th);

    boolean isInfoEnabled();

    void info(String str);

    void info(String str, Throwable th);

    boolean isWarnEnabled();

    void warn(String str);

    void warn(String str, Throwable th);

    boolean isErrorEnabled();

    void error(String str);

    void error(String str, Throwable th);
}
