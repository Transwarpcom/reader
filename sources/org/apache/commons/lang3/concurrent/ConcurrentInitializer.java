package org.apache.commons.lang3.concurrent;

/* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/concurrent/ConcurrentInitializer.class */
public interface ConcurrentInitializer<T> {
    T get() throws ConcurrentException;
}
