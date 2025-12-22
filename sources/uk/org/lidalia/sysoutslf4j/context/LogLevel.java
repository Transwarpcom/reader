package uk.org.lidalia.sysoutslf4j.context;

import org.slf4j.Logger;
import org.slf4j.Marker;

/* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/context/LogLevel.class */
public enum LogLevel {
    TRACE { // from class: uk.org.lidalia.sysoutslf4j.context.LogLevel.1
        @Override // uk.org.lidalia.sysoutslf4j.context.LogLevel
        public void log(Logger logger, String message) {
            logger.trace(message);
        }

        @Override // uk.org.lidalia.sysoutslf4j.context.LogLevel
        public void log(Logger logger, Marker marker, String message) {
            logger.trace(marker, message);
        }
    },
    DEBUG { // from class: uk.org.lidalia.sysoutslf4j.context.LogLevel.2
        @Override // uk.org.lidalia.sysoutslf4j.context.LogLevel
        public void log(Logger logger, String message) {
            logger.debug(message);
        }

        @Override // uk.org.lidalia.sysoutslf4j.context.LogLevel
        public void log(Logger logger, Marker marker, String message) {
            logger.debug(marker, message);
        }
    },
    INFO { // from class: uk.org.lidalia.sysoutslf4j.context.LogLevel.3
        @Override // uk.org.lidalia.sysoutslf4j.context.LogLevel
        public void log(Logger logger, String message) {
            logger.info(message);
        }

        @Override // uk.org.lidalia.sysoutslf4j.context.LogLevel
        public void log(Logger logger, Marker marker, String message) {
            logger.info(marker, message);
        }
    },
    WARN { // from class: uk.org.lidalia.sysoutslf4j.context.LogLevel.4
        @Override // uk.org.lidalia.sysoutslf4j.context.LogLevel
        public void log(Logger logger, String message) {
            logger.warn(message);
        }

        @Override // uk.org.lidalia.sysoutslf4j.context.LogLevel
        public void log(Logger logger, Marker marker, String message) {
            logger.warn(marker, message);
        }
    },
    ERROR { // from class: uk.org.lidalia.sysoutslf4j.context.LogLevel.5
        @Override // uk.org.lidalia.sysoutslf4j.context.LogLevel
        public void log(Logger logger, String message) {
            logger.error(message);
        }

        @Override // uk.org.lidalia.sysoutslf4j.context.LogLevel
        public void log(Logger logger, Marker marker, String message) {
            logger.error(marker, message);
        }
    };

    public abstract void log(Logger logger, String str);

    public abstract void log(Logger logger, Marker marker, String str);

    /* renamed from: values, reason: to resolve conflict with enum method */
    public static LogLevel[] valuesCustom() {
        LogLevel[] logLevelArrValuesCustom = values();
        int length = logLevelArrValuesCustom.length;
        LogLevel[] logLevelArr = new LogLevel[length];
        System.arraycopy(logLevelArrValuesCustom, 0, logLevelArr, 0, length);
        return logLevelArr;
    }

    /* synthetic */ LogLevel(LogLevel logLevel) {
        this();
    }
}
