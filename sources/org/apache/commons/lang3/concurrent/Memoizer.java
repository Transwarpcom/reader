package org.apache.commons.lang3.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/concurrent/Memoizer.class */
public class Memoizer<I, O> implements Computable<I, O> {
    private final ConcurrentMap<I, Future<O>> cache;
    private final Computable<I, O> computable;
    private final boolean recalculate;

    public Memoizer(Computable<I, O> computable) {
        this(computable, false);
    }

    public Memoizer(Computable<I, O> computable, boolean recalculate) {
        this.cache = new ConcurrentHashMap();
        this.computable = computable;
        this.recalculate = recalculate;
    }

    @Override // org.apache.commons.lang3.concurrent.Computable
    public O compute(final I arg) throws InterruptedException {
        while (true) {
            Future<O> future = this.cache.get(arg);
            if (future == null) {
                Callable<O> eval = new Callable<O>() { // from class: org.apache.commons.lang3.concurrent.Memoizer.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.concurrent.Callable
                    public O call() throws InterruptedException {
                        return (O) Memoizer.this.computable.compute(arg);
                    }
                };
                FutureTask<O> futureTask = new FutureTask<>(eval);
                future = this.cache.putIfAbsent(arg, futureTask);
                if (future == null) {
                    future = futureTask;
                    futureTask.run();
                }
            }
            try {
                return future.get();
            } catch (CancellationException e) {
                this.cache.remove(arg, future);
            } catch (ExecutionException e2) {
                if (this.recalculate) {
                    this.cache.remove(arg, future);
                }
                throw launderException(e2.getCause());
            }
        }
    }

    private RuntimeException launderException(Throwable throwable) {
        if (throwable instanceof RuntimeException) {
            return (RuntimeException) throwable;
        }
        if (throwable instanceof Error) {
            throw ((Error) throwable);
        }
        throw new IllegalStateException("Unchecked exception", throwable);
    }
}
