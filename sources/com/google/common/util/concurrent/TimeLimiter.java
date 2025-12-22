package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Beta
@GwtIncompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/util/concurrent/TimeLimiter.class */
public interface TimeLimiter {
    <T> T newProxy(T t, Class<T> cls, long j, TimeUnit timeUnit);

    @CanIgnoreReturnValue
    <T> T callWithTimeout(Callable<T> callable, long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException;

    @CanIgnoreReturnValue
    <T> T callUninterruptiblyWithTimeout(Callable<T> callable, long j, TimeUnit timeUnit) throws ExecutionException, TimeoutException;

    void runWithTimeout(Runnable runnable, long j, TimeUnit timeUnit) throws InterruptedException, TimeoutException;

    void runUninterruptiblyWithTimeout(Runnable runnable, long j, TimeUnit timeUnit) throws TimeoutException;

    default <T> T newProxy(T t, Class<T> cls, Duration duration) {
        return (T) newProxy(t, cls, Internal.saturatedToNanos(duration), TimeUnit.NANOSECONDS);
    }

    @CanIgnoreReturnValue
    default <T> T callWithTimeout(Callable<T> callable, Duration duration) throws ExecutionException, InterruptedException, TimeoutException {
        return (T) callWithTimeout(callable, Internal.saturatedToNanos(duration), TimeUnit.NANOSECONDS);
    }

    @CanIgnoreReturnValue
    default <T> T callUninterruptiblyWithTimeout(Callable<T> callable, Duration duration) throws ExecutionException, TimeoutException {
        return (T) callUninterruptiblyWithTimeout(callable, Internal.saturatedToNanos(duration), TimeUnit.NANOSECONDS);
    }

    default void runWithTimeout(Runnable runnable, Duration timeout) throws InterruptedException, TimeoutException {
        runWithTimeout(runnable, Internal.saturatedToNanos(timeout), TimeUnit.NANOSECONDS);
    }

    default void runUninterruptiblyWithTimeout(Runnable runnable, Duration timeout) throws TimeoutException {
        runUninterruptiblyWithTimeout(runnable, Internal.saturatedToNanos(timeout), TimeUnit.NANOSECONDS);
    }
}
