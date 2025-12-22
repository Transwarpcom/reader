package uk.org.lidalia.sysoutslf4j.common;

import java.io.PrintStream;

/* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/common/SLF4JPrintStream.class */
public interface SLF4JPrintStream {
    void registerLoggerAppender(Object obj);

    void deregisterLoggerAppender();

    PrintStream getOriginalPrintStream();
}
