package uk.org.lidalia.sysoutslf4j.context;

import java.io.PrintStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.org.lidalia.sysoutslf4j.common.ReflectionUtils;
import uk.org.lidalia.sysoutslf4j.common.SLF4JPrintStream;
import uk.org.lidalia.sysoutslf4j.common.SystemOutput;
import uk.org.lidalia.sysoutslf4j.context.exceptionhandlers.ExceptionHandlingStrategy;
import uk.org.lidalia.sysoutslf4j.context.exceptionhandlers.ExceptionHandlingStrategyFactory;

/* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/context/SLF4JPrintStreamManager.class */
class SLF4JPrintStreamManager {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) SysOutOverSLF4J.class);

    SLF4JPrintStreamManager() {
    }

    void sendSystemOutAndErrToSLF4J(LogLevel outLevel, LogLevel errLevel, ExceptionHandlingStrategyFactory exceptionHandlingStrategyFactory) {
        makeSystemOutputsSLF4JPrintStreamsIfNecessary();
        sendSystemOutAndErrToSLF4JForThisContext(outLevel, errLevel, exceptionHandlingStrategyFactory);
        LOG.info("Redirected System.out and System.err to SLF4J for this context");
    }

    private void makeSystemOutputsSLF4JPrintStreamsIfNecessary() {
        if (SysOutOverSLF4J.systemOutputsAreSLF4JPrintStreams()) {
            LOG.debug("System.out and System.err are already SLF4JPrintStreams");
        } else {
            PrintStreamCoordinatorFactory.createPrintStreamCoordinator().replaceSystemOutputsWithSLF4JPrintStreams();
            LOG.info("Replaced standard System.out and System.err PrintStreams with SLF4JPrintStreams");
        }
    }

    private void sendSystemOutAndErrToSLF4JForThisContext(LogLevel outLevel, LogLevel errLevel, ExceptionHandlingStrategyFactory exceptionHandlingStrategyFactory) {
        registerNewLoggerAppender(exceptionHandlingStrategyFactory, wrap(SystemOutput.OUT.get()), outLevel);
        registerNewLoggerAppender(exceptionHandlingStrategyFactory, wrap(SystemOutput.ERR.get()), errLevel);
    }

    private void registerNewLoggerAppender(ExceptionHandlingStrategyFactory exceptionHandlingStrategyFactory, SLF4JPrintStream slf4jPrintStream, LogLevel logLevel) {
        PrintStream originalPrintStream = slf4jPrintStream.getOriginalPrintStream();
        ExceptionHandlingStrategy exceptionHandlingStrategy = exceptionHandlingStrategyFactory.makeExceptionHandlingStrategy(logLevel, originalPrintStream);
        Object loggerAppender = new LoggerAppenderImpl(logLevel, exceptionHandlingStrategy, originalPrintStream);
        ReferenceHolder.preventGarbageCollectionForLifeOfClassLoader(loggerAppender);
        slf4jPrintStream.registerLoggerAppender(loggerAppender);
    }

    void stopSendingSystemOutAndErrToSLF4J() {
        if (SysOutOverSLF4J.systemOutputsAreSLF4JPrintStreams()) {
            for (SystemOutput systemOutput : SystemOutput.valuesCustom()) {
                SLF4JPrintStream slf4jPrintStream = wrap(systemOutput.get());
                slf4jPrintStream.deregisterLoggerAppender();
            }
            return;
        }
        LOG.warn("Cannot stop sending System.out and System.err to SLF4J - they are not being sent there at the moment");
    }

    private SLF4JPrintStream wrap(PrintStream target) {
        return (SLF4JPrintStream) ReflectionUtils.wrap(target, SLF4JPrintStream.class);
    }

    void restoreOriginalSystemOutputsIfNecessary() {
        if (SysOutOverSLF4J.systemOutputsAreSLF4JPrintStreams()) {
            PrintStreamCoordinatorFactory.createPrintStreamCoordinator().restoreOriginalSystemOutputs();
            LOG.info("Restored original System.out and System.err");
        } else {
            LOG.warn("System.out and System.err are not SLF4JPrintStreams - cannot restore");
        }
    }
}
