package org.apache.commons.lang3.concurrent;

/* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/concurrent/CircuitBreaker.class */
public interface CircuitBreaker<T> {
    boolean isOpen();

    boolean isClosed();

    boolean checkState();

    void close();

    void open();

    boolean incrementAndCheckState(T t);
}
