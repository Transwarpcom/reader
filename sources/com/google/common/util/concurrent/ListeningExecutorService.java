package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@GwtIncompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/util/concurrent/ListeningExecutorService.class */
public interface ListeningExecutorService extends ExecutorService {
    @Override // java.util.concurrent.ExecutorService, com.google.common.util.concurrent.ListeningExecutorService
    <T> ListenableFuture<T> submit(Callable<T> callable);

    @Override // java.util.concurrent.ExecutorService, com.google.common.util.concurrent.ListeningExecutorService
    ListenableFuture<?> submit(Runnable runnable);

    <T> ListenableFuture<T> submit(Runnable runnable, T t);

    @Override // java.util.concurrent.ExecutorService
    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException;

    @Override // java.util.concurrent.ExecutorService
    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException;

    /* bridge */ /* synthetic */ default Future submit(Runnable runnable, Object obj) {
        return submit(runnable, (Runnable) obj);
    }
}
