package uk.org.lidalia.sysoutslf4j.context.exceptionhandlers;

import java.io.PrintStream;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import uk.org.lidalia.sysoutslf4j.context.LogLevel;

/* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/context/exceptionhandlers/LogPerLineExceptionHandlingStrategyFactory.class */
public final class LogPerLineExceptionHandlingStrategyFactory implements ExceptionHandlingStrategyFactory {
    private static final ExceptionHandlingStrategyFactory INSTANCE = new LogPerLineExceptionHandlingStrategyFactory();

    public static ExceptionHandlingStrategyFactory getInstance() {
        return INSTANCE;
    }

    private LogPerLineExceptionHandlingStrategyFactory() {
    }

    @Override // uk.org.lidalia.sysoutslf4j.context.exceptionhandlers.ExceptionHandlingStrategyFactory
    public ExceptionHandlingStrategy makeExceptionHandlingStrategy(LogLevel logLevel, PrintStream originalPrintStream) {
        return new LogPerLineExceptionHandlingStrategy(logLevel);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/context/exceptionhandlers/LogPerLineExceptionHandlingStrategyFactory$LogPerLineExceptionHandlingStrategy.class */
    private static final class LogPerLineExceptionHandlingStrategy implements ExceptionHandlingStrategy {
        private static final Marker MARKER = MarkerFactory.getMarker("stacktrace");
        private final LogLevel logLevel;

        LogPerLineExceptionHandlingStrategy(LogLevel logLevel) {
            this.logLevel = logLevel;
        }

        @Override // uk.org.lidalia.sysoutslf4j.context.exceptionhandlers.ExceptionHandlingStrategy
        public void notifyNotStackTrace() {
        }

        @Override // uk.org.lidalia.sysoutslf4j.context.exceptionhandlers.ExceptionHandlingStrategy
        public void handleExceptionLine(String line, Logger log) {
            this.logLevel.log(log, MARKER, line);
        }
    }
}
