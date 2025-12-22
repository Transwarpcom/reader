package io.vertx.core.logging;

import io.vertx.core.spi.logging.LogDelegate;
import io.vertx.core.spi.logging.LogDelegateFactory;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/logging/LoggerFactory.class */
public class LoggerFactory {
    public static final String LOGGER_DELEGATE_FACTORY_CLASS_NAME = "vertx.logger-delegate-factory-class-name";
    private static volatile LogDelegateFactory delegateFactory;
    private static final ConcurrentMap<String, Logger> loggers = new ConcurrentHashMap();

    static {
        initialise();
    }

    public static synchronized void initialise() throws ClassNotFoundException {
        LogDelegateFactory delegateFactory2;
        String className = JULLogDelegateFactory.class.getName();
        try {
            className = System.getProperty(LOGGER_DELEGATE_FACTORY_CLASS_NAME);
        } catch (Exception e) {
        }
        if (className != null) {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            try {
                Class<?> clz = loader.loadClass(className);
                delegateFactory2 = (LogDelegateFactory) clz.newInstance();
            } catch (Exception e2) {
                throw new IllegalArgumentException("Error instantiating transformer class \"" + className + OperatorName.SHOW_TEXT_LINE_AND_SPACE, e2);
            }
        } else {
            delegateFactory2 = new JULLogDelegateFactory();
        }
        delegateFactory = delegateFactory2;
    }

    public static Logger getLogger(Class<?> clazz) {
        String canonicalName;
        if (clazz.isAnonymousClass()) {
            canonicalName = clazz.getEnclosingClass().getCanonicalName();
        } else {
            canonicalName = clazz.getCanonicalName();
        }
        String name = canonicalName;
        return getLogger(name);
    }

    public static Logger getLogger(String name) {
        Logger logger = loggers.get(name);
        if (logger == null) {
            LogDelegate delegate = delegateFactory.createDelegate(name);
            logger = new Logger(delegate);
            Logger oldLogger = loggers.putIfAbsent(name, logger);
            if (oldLogger != null) {
                logger = oldLogger;
            }
        }
        return logger;
    }

    public static void removeLogger(String name) {
        loggers.remove(name);
    }
}
