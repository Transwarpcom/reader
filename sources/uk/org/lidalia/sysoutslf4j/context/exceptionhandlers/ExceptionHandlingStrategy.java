package uk.org.lidalia.sysoutslf4j.context.exceptionhandlers;

import org.slf4j.Logger;

/* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/context/exceptionhandlers/ExceptionHandlingStrategy.class */
public interface ExceptionHandlingStrategy {
    void handleExceptionLine(String str, Logger logger);

    void notifyNotStackTrace();
}
