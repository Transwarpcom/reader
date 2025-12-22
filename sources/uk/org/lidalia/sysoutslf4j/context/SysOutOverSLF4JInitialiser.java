package uk.org.lidalia.sysoutslf4j.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/context/SysOutOverSLF4JInitialiser.class */
class SysOutOverSLF4JInitialiser {
    private static final String UNKNOWN_LOGGING_SYSTEM_MESSAGE = "Your logging framework {} is not known - if it needs access to the standard println methods on the console you will need to register it by calling registerLoggingSystemPackage";
    private static final String LOGGING_SYSTEM_DOES_NOT_NEED_PRINTLN_MESSAGE = "Your logging framework {} should not need access to the standard println methods on the console, so you should not need to register a logging system package.";
    private final LoggingSystemRegister loggingSystemRegister;
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) SysOutOverSLF4JInitialiser.class);
    private static final String[] LOGGING_SYSTEMS_THAT_DO_NOT_ACCESS_CONSOLE = {"ch.qos.logback.", "org.slf4j.impl.Log4jLoggerAdapter", "org.slf4j.impl.JDK14LoggerAdapter", "org.apache.log4j."};
    private static final String[] LOGGING_SYSTEMS_THAT_MIGHT_ACCESS_CONSOLE = {"org.x4juli.", "org.grlea.log.", "org.slf4j.impl.SimpleLogger"};

    SysOutOverSLF4JInitialiser(LoggingSystemRegister loggingSystemRegister) {
        this.loggingSystemRegister = loggingSystemRegister;
    }

    void initialise(Logger currentLoggerImplementation) {
        if (loggingSystemKnownAndMightAccessConsoleViaPrintln(currentLoggerImplementation)) {
            registerCurrentLoggingSystemPackage(currentLoggerImplementation);
        } else if (loggingSystemDoesNotAccessConsoleViaPrintln(currentLoggerImplementation)) {
            LOG.debug(LOGGING_SYSTEM_DOES_NOT_NEED_PRINTLN_MESSAGE, currentLoggerImplementation.getClass());
        } else {
            LOG.warn(UNKNOWN_LOGGING_SYSTEM_MESSAGE, currentLoggerImplementation.getClass());
        }
    }

    private boolean loggingSystemDoesNotAccessConsoleViaPrintln(Logger currentLoggerImplementation) {
        boolean loggingSystemDoesNotAccessConsoleViaPrintln = false;
        String[] strArr = LOGGING_SYSTEMS_THAT_DO_NOT_ACCESS_CONSOLE;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String loggingPackage = strArr[i];
            if (!usingLogFramework(currentLoggerImplementation, loggingPackage)) {
                i++;
            } else {
                loggingSystemDoesNotAccessConsoleViaPrintln = true;
                break;
            }
        }
        return loggingSystemDoesNotAccessConsoleViaPrintln;
    }

    private boolean loggingSystemKnownAndMightAccessConsoleViaPrintln(Logger currentLoggerImplementation) {
        boolean loggingSystemKnownAndMightAccessConsoleViaPrintln = false;
        String[] strArr = LOGGING_SYSTEMS_THAT_MIGHT_ACCESS_CONSOLE;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String loggingPackage = strArr[i];
            if (!usingLogFramework(currentLoggerImplementation, loggingPackage)) {
                i++;
            } else {
                loggingSystemKnownAndMightAccessConsoleViaPrintln = true;
                break;
            }
        }
        return loggingSystemKnownAndMightAccessConsoleViaPrintln;
    }

    private void registerCurrentLoggingSystemPackage(Logger currentLoggerImplementation) {
        for (String loggingPackage : LOGGING_SYSTEMS_THAT_MIGHT_ACCESS_CONSOLE) {
            if (usingLogFramework(currentLoggerImplementation, loggingPackage)) {
                this.loggingSystemRegister.registerLoggingSystem(loggingPackage);
            }
        }
    }

    private boolean usingLogFramework(Logger currentLoggerImplementation, String packageName) {
        return currentLoggerImplementation.getClass().getName().startsWith(packageName);
    }
}
