package uk.org.lidalia.sysoutslf4j.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.org.lidalia.sysoutslf4j.context.exceptionhandlers.ExceptionHandlingStrategyFactory;
import uk.org.lidalia.sysoutslf4j.context.exceptionhandlers.LogPerLineExceptionHandlingStrategyFactory;
import uk.org.lidalia.sysoutslf4j.system.SLF4JPrintStreamImpl;

/* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/context/SysOutOverSLF4J.class */
public final class SysOutOverSLF4J {
    private static final LoggingSystemRegister LOGGING_SYSTEM_REGISTER = new LoggingSystemRegister();
    private static final SLF4JPrintStreamManager SLF4J_PRINT_STREAM_MANAGER = new SLF4JPrintStreamManager();

    static {
        SysOutOverSLF4JInitialiser sysOutOverSLF4JInitialiser = new SysOutOverSLF4JInitialiser(LOGGING_SYSTEM_REGISTER);
        Logger loggerImplementation = LoggerFactory.getLogger("ROOT");
        sysOutOverSLF4JInitialiser.initialise(loggerImplementation);
    }

    public static void sendSystemOutAndErrToSLF4J() {
        sendSystemOutAndErrToSLF4J(LogLevel.INFO, LogLevel.ERROR);
    }

    public static void sendSystemOutAndErrToSLF4J(LogLevel outLevel, LogLevel errLevel) {
        ExceptionHandlingStrategyFactory exceptionHandlingStrategyFactory = LogPerLineExceptionHandlingStrategyFactory.getInstance();
        sendSystemOutAndErrToSLF4J(outLevel, errLevel, exceptionHandlingStrategyFactory);
    }

    public static void sendSystemOutAndErrToSLF4J(ExceptionHandlingStrategyFactory exceptionHandlingStrategyFactory) {
        sendSystemOutAndErrToSLF4J(LogLevel.INFO, LogLevel.ERROR, exceptionHandlingStrategyFactory);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Class<java.lang.System>] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3 */
    public static void sendSystemOutAndErrToSLF4J(LogLevel outLevel, LogLevel errLevel, ExceptionHandlingStrategyFactory exceptionHandlingStrategyFactory) {
        ?? r0 = System.class;
        synchronized (r0) {
            SLF4J_PRINT_STREAM_MANAGER.sendSystemOutAndErrToSLF4J(outLevel, errLevel, exceptionHandlingStrategyFactory);
            r0 = r0;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Class<java.lang.System>] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3 */
    public static void stopSendingSystemOutAndErrToSLF4J() {
        ?? r0 = System.class;
        synchronized (r0) {
            SLF4J_PRINT_STREAM_MANAGER.stopSendingSystemOutAndErrToSLF4J();
            r0 = r0;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Class<java.lang.System>] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3 */
    public static void restoreOriginalSystemOutputs() {
        ?? r0 = System.class;
        synchronized (r0) {
            SLF4J_PRINT_STREAM_MANAGER.restoreOriginalSystemOutputsIfNecessary();
            r0 = r0;
        }
    }

    public static void registerLoggingSystem(String packageName) {
        LOGGING_SYSTEM_REGISTER.registerLoggingSystem(packageName);
    }

    public static void unregisterLoggingSystem(String packageName) {
        LOGGING_SYSTEM_REGISTER.unregisterLoggingSystem(packageName);
    }

    public static boolean isInLoggingSystem(String className) {
        return LOGGING_SYSTEM_REGISTER.isInLoggingSystem(className);
    }

    private SysOutOverSLF4J() {
        throw new UnsupportedOperationException("Not instantiable");
    }

    public static boolean systemOutputsAreSLF4JPrintStreams() {
        return System.out.getClass().getName().equals(SLF4JPrintStreamImpl.class.getName());
    }
}
