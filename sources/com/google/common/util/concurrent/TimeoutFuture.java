package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.FluentFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@GwtIncompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/util/concurrent/TimeoutFuture.class */
final class TimeoutFuture<V> extends FluentFuture.TrustedFuture<V> {
    private ListenableFuture<V> delegateRef;
    private ScheduledFuture<?> timer;

    static <V> ListenableFuture<V> create(ListenableFuture<V> delegate, long time, TimeUnit unit, ScheduledExecutorService scheduledExecutor) {
        TimeoutFuture<V> result = new TimeoutFuture<>(delegate);
        Fire<V> fire = new Fire<>(result);
        ((TimeoutFuture) result).timer = scheduledExecutor.schedule(fire, time, unit);
        delegate.addListener(fire, MoreExecutors.directExecutor());
        return result;
    }

    private TimeoutFuture(ListenableFuture<V> delegate) {
        this.delegateRef = (ListenableFuture) Preconditions.checkNotNull(delegate);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/util/concurrent/TimeoutFuture$Fire.class */
    private static final class Fire<V> implements Runnable {
        TimeoutFuture<V> timeoutFutureRef;

        Fire(TimeoutFuture<V> timeoutFuture) {
            this.timeoutFutureRef = timeoutFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            ListenableFuture<? extends V> listenableFuture;
            TimeoutFuture<V> timeoutFuture = this.timeoutFutureRef;
            if (timeoutFuture == null || (listenableFuture = ((TimeoutFuture) timeoutFuture).delegateRef) == null) {
                return;
            }
            this.timeoutFutureRef = null;
            if (!listenableFuture.isDone()) {
                try {
                    ScheduledFuture<?> timer = ((TimeoutFuture) timeoutFuture).timer;
                    ((TimeoutFuture) timeoutFuture).timer = null;
                    String message = "Timed out";
                    if (timer != null) {
                        try {
                            long overDelayMs = Math.abs(timer.getDelay(TimeUnit.MILLISECONDS));
                            if (overDelayMs > 10) {
                                message = message + " (timeout delayed by " + overDelayMs + " ms after scheduled time)";
                            }
                        } catch (Throwable th) {
                            timeoutFuture.setException(new TimeoutFutureException(message));
                            throw th;
                        }
                    }
                    message = message + ": " + listenableFuture;
                    timeoutFuture.setException(new TimeoutFutureException(message));
                    return;
                } finally {
                    listenableFuture.cancel(true);
                }
            }
            timeoutFuture.setFuture(listenableFuture);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/util/concurrent/TimeoutFuture$TimeoutFutureException.class */
    private static final class TimeoutFutureException extends TimeoutException {
        private TimeoutFutureException(String message) {
            super(message);
        }

        @Override // java.lang.Throwable
        public synchronized Throwable fillInStackTrace() {
            setStackTrace(new StackTraceElement[0]);
            return this;
        }
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    protected String pendingToString() {
        ListenableFuture<V> listenableFuture = this.delegateRef;
        ScheduledFuture<?> localTimer = this.timer;
        if (listenableFuture != null) {
            String message = "inputFuture=[" + listenableFuture + "]";
            if (localTimer != null) {
                long delay = localTimer.getDelay(TimeUnit.MILLISECONDS);
                if (delay > 0) {
                    message = message + ", remaining delay=[" + delay + " ms]";
                }
            }
            return message;
        }
        return null;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    protected void afterDone() {
        maybePropagateCancellationTo(this.delegateRef);
        Future<?> localTimer = this.timer;
        if (localTimer != null) {
            localTimer.cancel(false);
        }
        this.delegateRef = null;
        this.timer = null;
    }
}
