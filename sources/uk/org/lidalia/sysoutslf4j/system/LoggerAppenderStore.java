package uk.org.lidalia.sysoutslf4j.system;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import uk.org.lidalia.sysoutslf4j.common.LoggerAppender;

/* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/system/LoggerAppenderStore.class */
class LoggerAppenderStore {
    private final Map<ClassLoader, WeakReference<LoggerAppender>> loggerAppenderMap = new WeakHashMap();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = this.lock.readLock();
    private final Lock writeLock = this.lock.writeLock();

    LoggerAppenderStore() {
    }

    LoggerAppender get() {
        this.readLock.lock();
        try {
            return get(contextClassLoader());
        } finally {
            this.readLock.unlock();
        }
    }

    private LoggerAppender get(ClassLoader classLoader) {
        LoggerAppender result;
        WeakReference<LoggerAppender> loggerAppenderReference = this.loggerAppenderMap.get(classLoader);
        if (loggerAppenderReference == null) {
            if (classLoader == null) {
                result = null;
            } else {
                result = get(classLoader.getParent());
            }
        } else {
            result = loggerAppenderReference.get();
        }
        return result;
    }

    void put(LoggerAppender loggerAppender) {
        this.writeLock.lock();
        try {
            this.loggerAppenderMap.put(contextClassLoader(), new WeakReference<>(loggerAppender));
        } finally {
            this.writeLock.unlock();
        }
    }

    void remove() {
        this.writeLock.lock();
        try {
            this.loggerAppenderMap.remove(contextClassLoader());
        } finally {
            this.writeLock.unlock();
        }
    }

    private ClassLoader contextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}
