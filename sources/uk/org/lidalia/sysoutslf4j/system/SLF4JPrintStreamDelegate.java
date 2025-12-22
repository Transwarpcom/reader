package uk.org.lidalia.sysoutslf4j.system;

import cn.hutool.core.text.StrPool;
import java.io.PrintStream;
import uk.org.lidalia.sysoutslf4j.common.LoggerAppender;
import uk.org.lidalia.sysoutslf4j.common.StringUtils;

/* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/system/SLF4JPrintStreamDelegate.class */
class SLF4JPrintStreamDelegate {
    private final PrintStream originalPrintStream;
    private final LoggerAppenderStore loggerAppenderStore;

    SLF4JPrintStreamDelegate(PrintStream originalPrintStream, LoggerAppenderStore loggerAppenderStore) {
        this.originalPrintStream = originalPrintStream;
        this.loggerAppenderStore = loggerAppenderStore;
    }

    void registerLoggerAppender(LoggerAppender loggerAppender) {
        this.loggerAppenderStore.put(loggerAppender);
    }

    void deregisterLoggerAppender() {
        this.loggerAppenderStore.remove();
    }

    void delegatePrintln(String message) {
        LoggerAppender loggerAppender = this.loggerAppenderStore.get();
        if (loggerAppender == null) {
            this.originalPrintStream.println(message);
        } else {
            appendAndLog(message, loggerAppender);
        }
    }

    void delegatePrint(String message) {
        LoggerAppender loggerAppender = this.loggerAppenderStore.get();
        if (loggerAppender == null) {
            this.originalPrintStream.print(message);
        } else if (message.endsWith("\n")) {
            String messageWithoutLineBreak = StringUtils.stripEnd(message, StrPool.CRLF);
            appendAndLog(messageWithoutLineBreak, loggerAppender);
        } else {
            loggerAppender.append(message);
        }
    }

    private static void appendAndLog(String message, LoggerAppender loggerAppender) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        CallOrigin callOrigin = CallOrigin.getCallOrigin(stackTraceElements, "uk.org.lidalia.sysoutslf4j");
        loggerAppender.appendAndLog(message, callOrigin.getClassName(), callOrigin.isPrintingStackTrace());
    }
}
