package com.mongodb.internal.connection;

import com.mongodb.MongoInternalException;
import com.mongodb.MongoInterruptedException;
import com.mongodb.MongoTimeoutException;
import com.mongodb.internal.connection.ConcurrentLinkedDeque;
import java.util.Iterator;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ConcurrentPool.class */
public class ConcurrentPool<T> implements Pool<T> {
    private final int maxSize;
    private final ItemFactory<T> itemFactory;
    private final ConcurrentLinkedDeque<T> available = new ConcurrentLinkedDeque<>();
    private final Semaphore permits;
    private volatile boolean closed;

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ConcurrentPool$ItemFactory.class */
    public interface ItemFactory<T> {
        T create(boolean z);

        void close(T t);

        Prune shouldPrune(T t);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ConcurrentPool$Prune.class */
    public enum Prune {
        YES,
        NO,
        STOP
    }

    public ConcurrentPool(int maxSize, ItemFactory<T> itemFactory) {
        this.maxSize = maxSize;
        this.itemFactory = itemFactory;
        this.permits = new Semaphore(maxSize, true);
    }

    @Override // com.mongodb.internal.connection.Pool
    public void release(T t) {
        release(t, false);
    }

    @Override // com.mongodb.internal.connection.Pool
    public void release(T t, boolean prune) {
        if (t == null) {
            throw new IllegalArgumentException("Can not return a null item to the pool");
        }
        if (this.closed) {
            close(t);
            return;
        }
        if (prune) {
            close(t);
        } else {
            this.available.addLast(t);
        }
        releasePermit();
    }

    @Override // com.mongodb.internal.connection.Pool
    public T get() {
        return get(-1L, TimeUnit.MILLISECONDS);
    }

    @Override // com.mongodb.internal.connection.Pool
    public T get(long timeout, TimeUnit timeUnit) {
        if (this.closed) {
            throw new IllegalStateException("The pool is closed");
        }
        if (!acquirePermit(timeout, timeUnit)) {
            throw new MongoTimeoutException(String.format("Timeout waiting for a pooled item after %d %s", Long.valueOf(timeout), timeUnit));
        }
        T t = this.available.pollLast();
        if (t == null) {
            t = createNewAndReleasePermitIfFailure(false);
        }
        return t;
    }

    public void prune() {
        T cur;
        Prune shouldPrune;
        ConcurrentLinkedDeque.RemovalReportingIterator<T> iter = this.available.iterator();
        while (iter.hasNext() && (shouldPrune = this.itemFactory.shouldPrune((cur = iter.next()))) != Prune.STOP) {
            if (shouldPrune == Prune.YES) {
                boolean removed = iter.reportingRemove();
                if (removed) {
                    close(cur);
                }
            }
        }
    }

    public void ensureMinSize(int minSize, boolean initialize) {
        while (getCount() < minSize && acquirePermit(10L, TimeUnit.MILLISECONDS)) {
            release(createNewAndReleasePermitIfFailure(initialize));
        }
    }

    private T createNewAndReleasePermitIfFailure(boolean initialize) {
        try {
            T newMember = this.itemFactory.create(initialize);
            if (newMember == null) {
                throw new MongoInternalException("The factory for the pool created a null item");
            }
            return newMember;
        } catch (RuntimeException e) {
            this.permits.release();
            throw e;
        }
    }

    protected boolean acquirePermit(long timeout, TimeUnit timeUnit) throws InterruptedException {
        try {
            if (this.closed) {
                return false;
            }
            if (timeout >= 0) {
                return this.permits.tryAcquire(timeout, timeUnit);
            }
            this.permits.acquire();
            return true;
        } catch (InterruptedException e) {
            throw new MongoInterruptedException("Interrupted acquiring a permit to retrieve an item from the pool ", e);
        }
    }

    protected void releasePermit() {
        this.permits.release();
    }

    @Override // com.mongodb.internal.connection.Pool
    public void close() {
        this.closed = true;
        Iterator<T> iter = this.available.iterator();
        while (iter.hasNext()) {
            T t = iter.next();
            close(t);
            iter.remove();
        }
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public int getInUseCount() {
        return this.maxSize - this.permits.availablePermits();
    }

    public int getAvailableCount() {
        return this.available.size();
    }

    public int getCount() {
        return getInUseCount() + getAvailableCount();
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("pool: ").append(" maxSize: ").append(this.maxSize).append(" availableCount ").append(getAvailableCount()).append(" inUseCount ").append(getInUseCount());
        return buf.toString();
    }

    private void close(T t) {
        try {
            this.itemFactory.close(t);
        } catch (RuntimeException e) {
        }
    }
}
