package uk.org.lidalia.sysoutslf4j.context;

import java.io.PrintStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.org.lidalia.sysoutslf4j.common.LoggerAppender;
import uk.org.lidalia.sysoutslf4j.context.exceptionhandlers.ExceptionHandlingStrategy;

/* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/context/LoggerAppenderImpl.class */
public class LoggerAppenderImpl implements LoggerAppender {
    private final LogLevel level;
    private final ExceptionHandlingStrategy exceptionHandlingStrategy;
    private final PrintStream originalPrintStream;
    private StringBuilder buffer = new StringBuilder();

    LoggerAppenderImpl(LogLevel level, ExceptionHandlingStrategy exceptionHandlingStrategy, PrintStream originalPrintStream) {
        this.level = level;
        this.exceptionHandlingStrategy = exceptionHandlingStrategy;
        this.originalPrintStream = originalPrintStream;
    }

    @Override // uk.org.lidalia.sysoutslf4j.common.LoggerAppender
    public void append(String message) {
        this.exceptionHandlingStrategy.notifyNotStackTrace();
        this.buffer.append(message);
    }

    @Override // uk.org.lidalia.sysoutslf4j.common.LoggerAppender
    public void appendAndLog(String message, String className, boolean isStackTrace) {
        this.buffer.append(message);
        String logStatement = flushBuffer();
        logOrPrint(logStatement, className, isStackTrace);
    }

    private String flushBuffer() {
        String logStatement = this.buffer.toString();
        this.buffer = new StringBuilder();
        return logStatement;
    }

    private void logOrPrint(String logStatement, String className, boolean isStackTrace) {
        if (SysOutOverSLF4J.isInLoggingSystem(className)) {
            this.originalPrintStream.println(logStatement);
        } else {
            log(logStatement, className, isStackTrace);
        }
    }

    private void log(String logStatement, String className, boolean isStackTrace) {
        Logger log = LoggerFactory.getLogger(className);
        if (isStackTrace) {
            this.exceptionHandlingStrategy.handleExceptionLine(logStatement, log);
        } else {
            this.exceptionHandlingStrategy.notifyNotStackTrace();
            this.level.log(log, logStatement);
        }
    }
}
