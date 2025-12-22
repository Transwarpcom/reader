package org.apache.commons.lang3.concurrent;

/* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/concurrent/LazyInitializer.class */
public abstract class LazyInitializer<T> implements ConcurrentInitializer<T> {
    private static final Object NO_INIT = new Object();
    private volatile T object = (T) NO_INIT;

    protected abstract T initialize() throws ConcurrentException;

    @Override // org.apache.commons.lang3.concurrent.ConcurrentInitializer
    public T get() throws ConcurrentException {
        T result = this.object;
        if (result == NO_INIT) {
            synchronized (this) {
                result = this.object;
                if (result == NO_INIT) {
                    T tInitialize = initialize();
                    result = tInitialize;
                    this.object = tInitialize;
                }
            }
        }
        return result;
    }
}
