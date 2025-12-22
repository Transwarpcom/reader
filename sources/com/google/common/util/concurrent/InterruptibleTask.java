package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.j2objc.annotations.ReflectionSupport;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

@ReflectionSupport(ReflectionSupport.Level.FULL)
@GwtCompatible(emulated = true)
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/util/concurrent/InterruptibleTask.class */
abstract class InterruptibleTask<T> extends AtomicReference<Runnable> implements Runnable {
    private static final Runnable DONE = new DoNothingRunnable();
    private static final Runnable INTERRUPTING = new DoNothingRunnable();
    private static final Runnable PARKED = new DoNothingRunnable();
    private static final int MAX_BUSY_WAIT_SPINS = 1000;

    abstract boolean isDone();

    abstract T runInterruptibly() throws Exception;

    abstract void afterRanInterruptibly(T t, Throwable th);

    abstract String toPendingString();

    InterruptibleTask() {
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/util/concurrent/InterruptibleTask$DoNothingRunnable.class */
    private static final class DoNothingRunnable implements Runnable {
        private DoNothingRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        Thread currentThread = Thread.currentThread();
        if (!compareAndSet(null, currentThread)) {
            return;
        }
        boolean run = !isDone();
        T result = null;
        if (run) {
            try {
                result = runInterruptibly();
            } catch (Throwable th) {
                if (!compareAndSet(currentThread, DONE)) {
                    boolean restoreInterruptedBit = false;
                    int spinCount = 0;
                    Runnable runnable = get();
                    while (true) {
                        Runnable state = runnable;
                        if (state != INTERRUPTING && state != PARKED) {
                            break;
                        }
                        spinCount++;
                        if (spinCount > 1000) {
                            if (state == PARKED || compareAndSet(INTERRUPTING, PARKED)) {
                                restoreInterruptedBit = Thread.interrupted() || restoreInterruptedBit;
                                LockSupport.park(this);
                            }
                        } else {
                            Thread.yield();
                        }
                        runnable = get();
                    }
                    if (restoreInterruptedBit) {
                        currentThread.interrupt();
                    }
                }
                if (run) {
                    afterRanInterruptibly(result, null);
                }
                throw th;
            }
        }
        if (!compareAndSet(currentThread, DONE)) {
            boolean restoreInterruptedBit2 = false;
            int spinCount2 = 0;
            Runnable runnable2 = get();
            while (true) {
                Runnable state2 = runnable2;
                if (state2 != INTERRUPTING && state2 != PARKED) {
                    break;
                }
                spinCount2++;
                if (spinCount2 > 1000) {
                    if (state2 == PARKED || compareAndSet(INTERRUPTING, PARKED)) {
                        restoreInterruptedBit2 = Thread.interrupted() || restoreInterruptedBit2;
                        LockSupport.park(this);
                    }
                } else {
                    Thread.yield();
                }
                runnable2 = get();
            }
            if (restoreInterruptedBit2) {
                currentThread.interrupt();
            }
        }
        if (run) {
            afterRanInterruptibly(result, null);
        }
    }

    final void interruptTask() {
        Runnable currentRunner = get();
        if ((currentRunner instanceof Thread) && compareAndSet(currentRunner, INTERRUPTING)) {
            try {
                ((Thread) currentRunner).interrupt();
            } finally {
                Runnable prev = getAndSet(DONE);
                if (prev == PARKED) {
                    LockSupport.unpark((Thread) currentRunner);
                }
            }
        }
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public final String toString() {
        String result;
        Runnable state = get();
        if (state == DONE) {
            result = "running=[DONE]";
        } else if (state == INTERRUPTING) {
            result = "running=[INTERRUPTED]";
        } else if (state instanceof Thread) {
            result = "running=[RUNNING ON " + ((Thread) state).getName() + "]";
        } else {
            result = "running=[NOT STARTED YET]";
        }
        return result + ", " + toPendingString();
    }
}
