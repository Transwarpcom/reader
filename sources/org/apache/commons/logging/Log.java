package org.apache.commons.logging;

/* JADX WARN: Classes with same name are omitted:
  reader.jar:BOOT-INF/lib/commons-logging-1.2.jar:org/apache/commons/logging/Log.class
 */
/* loaded from: reader.jar:BOOT-INF/lib/spring-jcl-5.1.8.RELEASE.jar:org/apache/commons/logging/Log.class */
public interface Log {
    boolean isFatalEnabled();

    boolean isErrorEnabled();

    boolean isWarnEnabled();

    boolean isInfoEnabled();

    boolean isDebugEnabled();

    boolean isTraceEnabled();

    void fatal(Object obj);

    void fatal(Object obj, Throwable th);

    void error(Object obj);

    void error(Object obj, Throwable th);

    void warn(Object obj);

    void warn(Object obj, Throwable th);

    void info(Object obj);

    void info(Object obj, Throwable th);

    void debug(Object obj);

    void debug(Object obj, Throwable th);

    void trace(Object obj);

    void trace(Object obj, Throwable th);
}
