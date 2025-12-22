package uk.org.lidalia.sysoutslf4j.context.exceptionhandlers;

import java.io.PrintStream;
import uk.org.lidalia.sysoutslf4j.context.LogLevel;

/* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/context/exceptionhandlers/ExceptionHandlingStrategyFactory.class */
public interface ExceptionHandlingStrategyFactory {
    ExceptionHandlingStrategy makeExceptionHandlingStrategy(LogLevel logLevel, PrintStream printStream);
}
