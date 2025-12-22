package io.netty.util.concurrent;

import io.netty.util.internal.DefaultPriorityQueue;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PriorityQueue;
import java.util.Comparator;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/concurrent/AbstractScheduledEventExecutor.class */
public abstract class AbstractScheduledEventExecutor extends AbstractEventExecutor {
    private static final Comparator<ScheduledFutureTask<?>> SCHEDULED_FUTURE_TASK_COMPARATOR;
    PriorityQueue<ScheduledFutureTask<?>> scheduledTaskQueue;
    long nextTaskId;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !AbstractScheduledEventExecutor.class.desiredAssertionStatus();
        SCHEDULED_FUTURE_TASK_COMPARATOR = new Comparator<ScheduledFutureTask<?>>() { // from class: io.netty.util.concurrent.AbstractScheduledEventExecutor.1
            @Override // java.util.Comparator
            public int compare(ScheduledFutureTask<?> o1, ScheduledFutureTask<?> o2) {
                return o1.compareTo((Delayed) o2);
            }
        };
    }

    protected AbstractScheduledEventExecutor() {
    }

    protected AbstractScheduledEventExecutor(EventExecutorGroup parent) {
        super(parent);
    }

    protected static long nanoTime() {
        return ScheduledFutureTask.nanoTime();
    }

    protected static long deadlineToDelayNanos(long deadlineNanos) {
        return ScheduledFutureTask.deadlineToDelayNanos(deadlineNanos);
    }

    protected static long initialNanoTime() {
        return ScheduledFutureTask.initialNanoTime();
    }

    PriorityQueue<ScheduledFutureTask<?>> scheduledTaskQueue() {
        if (this.scheduledTaskQueue == null) {
            this.scheduledTaskQueue = new DefaultPriorityQueue(SCHEDULED_FUTURE_TASK_COMPARATOR, 11);
        }
        return this.scheduledTaskQueue;
    }

    private static boolean isNullOrEmpty(Queue<ScheduledFutureTask<?>> queue) {
        return queue == null || queue.isEmpty();
    }

    protected void cancelScheduledTasks() {
        if (!$assertionsDisabled && !inEventLoop()) {
            throw new AssertionError();
        }
        PriorityQueue<ScheduledFutureTask<?>> scheduledTaskQueue = this.scheduledTaskQueue;
        if (isNullOrEmpty(scheduledTaskQueue)) {
            return;
        }
        ScheduledFutureTask<?>[] scheduledTasks = (ScheduledFutureTask[]) scheduledTaskQueue.toArray(new ScheduledFutureTask[0]);
        for (ScheduledFutureTask<?> task : scheduledTasks) {
            task.cancelWithoutRemove(false);
        }
        scheduledTaskQueue.clearIgnoringIndexes();
    }

    protected final Runnable pollScheduledTask() {
        return pollScheduledTask(nanoTime());
    }

    protected final Runnable pollScheduledTask(long nanoTime) {
        if (!$assertionsDisabled && !inEventLoop()) {
            throw new AssertionError();
        }
        Queue<ScheduledFutureTask<?>> scheduledTaskQueue = this.scheduledTaskQueue;
        ScheduledFutureTask<?> scheduledTask = scheduledTaskQueue == null ? null : scheduledTaskQueue.peek();
        if (scheduledTask == null || scheduledTask.deadlineNanos() - nanoTime > 0) {
            return null;
        }
        scheduledTaskQueue.remove();
        return scheduledTask;
    }

    protected final long nextScheduledTaskNano() {
        ScheduledFutureTask<?> scheduledTask = peekScheduledTask();
        if (scheduledTask != null) {
            return Math.max(0L, scheduledTask.deadlineNanos() - nanoTime());
        }
        return -1L;
    }

    protected final long nextScheduledTaskDeadlineNanos() {
        ScheduledFutureTask<?> scheduledTask = peekScheduledTask();
        if (scheduledTask != null) {
            return scheduledTask.deadlineNanos();
        }
        return -1L;
    }

    final ScheduledFutureTask<?> peekScheduledTask() {
        Queue<ScheduledFutureTask<?>> scheduledTaskQueue = this.scheduledTaskQueue;
        if (scheduledTaskQueue != null) {
            return scheduledTaskQueue.peek();
        }
        return null;
    }

    protected final boolean hasScheduledTasks() {
        ScheduledFutureTask<?> scheduledTask = peekScheduledTask();
        return scheduledTask != null && scheduledTask.deadlineNanos() <= nanoTime();
    }

    @Override // io.netty.util.concurrent.AbstractEventExecutor, java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        ObjectUtil.checkNotNull(command, "command");
        ObjectUtil.checkNotNull(unit, "unit");
        if (delay < 0) {
            delay = 0;
        }
        validateScheduled0(delay, unit);
        return schedule(new ScheduledFutureTask(this, command, (Object) null, ScheduledFutureTask.deadlineNanos(unit.toNanos(delay))));
    }

    @Override // io.netty.util.concurrent.AbstractEventExecutor, java.util.concurrent.ScheduledExecutorService
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
        ObjectUtil.checkNotNull(callable, "callable");
        ObjectUtil.checkNotNull(unit, "unit");
        if (delay < 0) {
            delay = 0;
        }
        validateScheduled0(delay, unit);
        return schedule(new ScheduledFutureTask<>(this, callable, ScheduledFutureTask.deadlineNanos(unit.toNanos(delay))));
    }

    @Override // io.netty.util.concurrent.AbstractEventExecutor, java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        ObjectUtil.checkNotNull(command, "command");
        ObjectUtil.checkNotNull(unit, "unit");
        if (initialDelay < 0) {
            throw new IllegalArgumentException(String.format("initialDelay: %d (expected: >= 0)", Long.valueOf(initialDelay)));
        }
        if (period <= 0) {
            throw new IllegalArgumentException(String.format("period: %d (expected: > 0)", Long.valueOf(period)));
        }
        validateScheduled0(initialDelay, unit);
        validateScheduled0(period, unit);
        return schedule(new ScheduledFutureTask(this, Executors.callable(command, null), ScheduledFutureTask.deadlineNanos(unit.toNanos(initialDelay)), unit.toNanos(period)));
    }

    @Override // io.netty.util.concurrent.AbstractEventExecutor, java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        ObjectUtil.checkNotNull(command, "command");
        ObjectUtil.checkNotNull(unit, "unit");
        if (initialDelay < 0) {
            throw new IllegalArgumentException(String.format("initialDelay: %d (expected: >= 0)", Long.valueOf(initialDelay)));
        }
        if (delay <= 0) {
            throw new IllegalArgumentException(String.format("delay: %d (expected: > 0)", Long.valueOf(delay)));
        }
        validateScheduled0(initialDelay, unit);
        validateScheduled0(delay, unit);
        return schedule(new ScheduledFutureTask(this, Executors.callable(command, null), ScheduledFutureTask.deadlineNanos(unit.toNanos(initialDelay)), -unit.toNanos(delay)));
    }

    private void validateScheduled0(long amount, TimeUnit unit) {
        validateScheduled(amount, unit);
    }

    @Deprecated
    protected void validateScheduled(long amount, TimeUnit unit) {
    }

    private <V> ScheduledFuture<V> schedule(final ScheduledFutureTask<V> task) {
        if (inEventLoop()) {
            PriorityQueue<ScheduledFutureTask<?>> priorityQueueScheduledTaskQueue = scheduledTaskQueue();
            long j = this.nextTaskId;
            this.nextTaskId = j + 1;
            priorityQueueScheduledTaskQueue.add(task.setId(j));
        } else {
            executeScheduledRunnable(new Runnable() { // from class: io.netty.util.concurrent.AbstractScheduledEventExecutor.2
                @Override // java.lang.Runnable
                public void run() {
                    PriorityQueue<ScheduledFutureTask<?>> priorityQueueScheduledTaskQueue2 = AbstractScheduledEventExecutor.this.scheduledTaskQueue();
                    ScheduledFutureTask scheduledFutureTask = task;
                    AbstractScheduledEventExecutor abstractScheduledEventExecutor = AbstractScheduledEventExecutor.this;
                    long j2 = abstractScheduledEventExecutor.nextTaskId;
                    abstractScheduledEventExecutor.nextTaskId = j2 + 1;
                    priorityQueueScheduledTaskQueue2.add(scheduledFutureTask.setId(j2));
                }
            }, true, task.deadlineNanos());
        }
        return task;
    }

    final void removeScheduled(final ScheduledFutureTask<?> task) {
        if (inEventLoop()) {
            scheduledTaskQueue().removeTyped(task);
        } else {
            executeScheduledRunnable(new Runnable() { // from class: io.netty.util.concurrent.AbstractScheduledEventExecutor.3
                @Override // java.lang.Runnable
                public void run() {
                    AbstractScheduledEventExecutor.this.scheduledTaskQueue().removeTyped(task);
                }
            }, false, task.deadlineNanos());
        }
    }

    void executeScheduledRunnable(Runnable runnable, boolean isAddition, long deadlineNanos) {
        execute(runnable);
    }
}
