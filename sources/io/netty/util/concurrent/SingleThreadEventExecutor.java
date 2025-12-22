package io.netty.util.concurrent;

import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.ThreadExecutorMap;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/concurrent/SingleThreadEventExecutor.class */
public abstract class SingleThreadEventExecutor extends AbstractScheduledEventExecutor implements OrderedEventExecutor {
    static final int DEFAULT_MAX_PENDING_EXECUTOR_TASKS;
    private static final InternalLogger logger;
    private static final int ST_NOT_STARTED = 1;
    private static final int ST_STARTED = 2;
    private static final int ST_SHUTTING_DOWN = 3;
    private static final int ST_SHUTDOWN = 4;
    private static final int ST_TERMINATED = 5;
    private static final Runnable WAKEUP_TASK;
    private static final Runnable NOOP_TASK;
    private static final AtomicIntegerFieldUpdater<SingleThreadEventExecutor> STATE_UPDATER;
    private static final AtomicReferenceFieldUpdater<SingleThreadEventExecutor, ThreadProperties> PROPERTIES_UPDATER;
    private final Queue<Runnable> taskQueue;
    private volatile Thread thread;
    private volatile ThreadProperties threadProperties;
    private final Executor executor;
    private volatile boolean interrupted;
    private final CountDownLatch threadLock;
    private final Set<Runnable> shutdownHooks;
    private final boolean addTaskWakesUp;
    private final int maxPendingTasks;
    private final RejectedExecutionHandler rejectedExecutionHandler;
    private long lastExecutionTime;
    private volatile int state;
    private volatile long gracefulShutdownQuietPeriod;
    private volatile long gracefulShutdownTimeout;
    private long gracefulShutdownStartTime;
    private final Promise<?> terminationFuture;
    private static final long SCHEDULE_PURGE_INTERVAL;
    static final /* synthetic */ boolean $assertionsDisabled;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/concurrent/SingleThreadEventExecutor$NonWakeupRunnable.class */
    public interface NonWakeupRunnable extends Runnable {
    }

    protected abstract void run();

    static {
        $assertionsDisabled = !SingleThreadEventExecutor.class.desiredAssertionStatus();
        DEFAULT_MAX_PENDING_EXECUTOR_TASKS = Math.max(16, SystemPropertyUtil.getInt("io.netty.eventexecutor.maxPendingTasks", Integer.MAX_VALUE));
        logger = InternalLoggerFactory.getInstance((Class<?>) SingleThreadEventExecutor.class);
        WAKEUP_TASK = new Runnable() { // from class: io.netty.util.concurrent.SingleThreadEventExecutor.1
            @Override // java.lang.Runnable
            public void run() {
            }
        };
        NOOP_TASK = new Runnable() { // from class: io.netty.util.concurrent.SingleThreadEventExecutor.2
            @Override // java.lang.Runnable
            public void run() {
            }
        };
        STATE_UPDATER = AtomicIntegerFieldUpdater.newUpdater(SingleThreadEventExecutor.class, "state");
        PROPERTIES_UPDATER = AtomicReferenceFieldUpdater.newUpdater(SingleThreadEventExecutor.class, ThreadProperties.class, "threadProperties");
        SCHEDULE_PURGE_INTERVAL = TimeUnit.SECONDS.toNanos(1L);
    }

    protected SingleThreadEventExecutor(EventExecutorGroup parent, ThreadFactory threadFactory, boolean addTaskWakesUp) {
        this(parent, new ThreadPerTaskExecutor(threadFactory), addTaskWakesUp);
    }

    protected SingleThreadEventExecutor(EventExecutorGroup parent, ThreadFactory threadFactory, boolean addTaskWakesUp, int maxPendingTasks, RejectedExecutionHandler rejectedHandler) {
        this(parent, new ThreadPerTaskExecutor(threadFactory), addTaskWakesUp, maxPendingTasks, rejectedHandler);
    }

    protected SingleThreadEventExecutor(EventExecutorGroup parent, Executor executor, boolean addTaskWakesUp) {
        this(parent, executor, addTaskWakesUp, DEFAULT_MAX_PENDING_EXECUTOR_TASKS, RejectedExecutionHandlers.reject());
    }

    protected SingleThreadEventExecutor(EventExecutorGroup parent, Executor executor, boolean addTaskWakesUp, int maxPendingTasks, RejectedExecutionHandler rejectedHandler) {
        super(parent);
        this.threadLock = new CountDownLatch(1);
        this.shutdownHooks = new LinkedHashSet();
        this.state = 1;
        this.terminationFuture = new DefaultPromise(GlobalEventExecutor.INSTANCE);
        this.addTaskWakesUp = addTaskWakesUp;
        this.maxPendingTasks = Math.max(16, maxPendingTasks);
        this.executor = ThreadExecutorMap.apply(executor, this);
        this.taskQueue = newTaskQueue(this.maxPendingTasks);
        this.rejectedExecutionHandler = (RejectedExecutionHandler) ObjectUtil.checkNotNull(rejectedHandler, "rejectedHandler");
    }

    protected SingleThreadEventExecutor(EventExecutorGroup parent, Executor executor, boolean addTaskWakesUp, Queue<Runnable> taskQueue, RejectedExecutionHandler rejectedHandler) {
        super(parent);
        this.threadLock = new CountDownLatch(1);
        this.shutdownHooks = new LinkedHashSet();
        this.state = 1;
        this.terminationFuture = new DefaultPromise(GlobalEventExecutor.INSTANCE);
        this.addTaskWakesUp = addTaskWakesUp;
        this.maxPendingTasks = DEFAULT_MAX_PENDING_EXECUTOR_TASKS;
        this.executor = ThreadExecutorMap.apply(executor, this);
        this.taskQueue = (Queue) ObjectUtil.checkNotNull(taskQueue, "taskQueue");
        this.rejectedExecutionHandler = (RejectedExecutionHandler) ObjectUtil.checkNotNull(rejectedHandler, "rejectedHandler");
    }

    protected boolean beforeScheduledTaskSubmitted(long deadlineNanos) {
        return true;
    }

    protected boolean afterScheduledTaskSubmitted(long deadlineNanos) {
        return true;
    }

    @Deprecated
    protected Queue<Runnable> newTaskQueue() {
        return newTaskQueue(this.maxPendingTasks);
    }

    protected Queue<Runnable> newTaskQueue(int maxPendingTasks) {
        return new LinkedBlockingQueue(maxPendingTasks);
    }

    protected void interruptThread() {
        Thread currentThread = this.thread;
        if (currentThread == null) {
            this.interrupted = true;
        } else {
            currentThread.interrupt();
        }
    }

    protected Runnable pollTask() {
        if ($assertionsDisabled || inEventLoop()) {
            return pollTaskFrom(this.taskQueue);
        }
        throw new AssertionError();
    }

    protected static Runnable pollTaskFrom(Queue<Runnable> taskQueue) {
        Runnable task;
        do {
            task = taskQueue.poll();
        } while (task == WAKEUP_TASK);
        return task;
    }

    protected Runnable takeTask() throws InterruptedException {
        Runnable task;
        if (!$assertionsDisabled && !inEventLoop()) {
            throw new AssertionError();
        }
        if (!(this.taskQueue instanceof BlockingQueue)) {
            throw new UnsupportedOperationException();
        }
        BlockingQueue<Runnable> taskQueue = (BlockingQueue) this.taskQueue;
        do {
            ScheduledFutureTask<?> scheduledTask = peekScheduledTask();
            if (scheduledTask == null) {
                Runnable task2 = null;
                try {
                    task2 = taskQueue.take();
                    if (task2 == WAKEUP_TASK) {
                        task2 = null;
                    }
                } catch (InterruptedException e) {
                }
                return task2;
            }
            long delayNanos = scheduledTask.delayNanos();
            task = null;
            if (delayNanos > 0) {
                try {
                    task = taskQueue.poll(delayNanos, TimeUnit.NANOSECONDS);
                } catch (InterruptedException e2) {
                    return null;
                }
            }
            if (task == null) {
                fetchFromScheduledTaskQueue();
                task = taskQueue.poll();
            }
        } while (task == null);
        return task;
    }

    private boolean fetchFromScheduledTaskQueue() {
        Runnable scheduledTask;
        if (this.scheduledTaskQueue == null || this.scheduledTaskQueue.isEmpty()) {
            return true;
        }
        long nanoTime = AbstractScheduledEventExecutor.nanoTime();
        do {
            scheduledTask = pollScheduledTask(nanoTime);
            if (scheduledTask == null) {
                return true;
            }
        } while (this.taskQueue.offer(scheduledTask));
        this.scheduledTaskQueue.add((ScheduledFutureTask) scheduledTask);
        return false;
    }

    private boolean executeExpiredScheduledTasks() {
        Runnable runnablePollScheduledTask;
        if (this.scheduledTaskQueue == null || this.scheduledTaskQueue.isEmpty()) {
            return false;
        }
        long nanoTime = AbstractScheduledEventExecutor.nanoTime();
        Runnable scheduledTask = pollScheduledTask(nanoTime);
        if (scheduledTask == null) {
            return false;
        }
        do {
            safeExecute(scheduledTask);
            runnablePollScheduledTask = pollScheduledTask(nanoTime);
            scheduledTask = runnablePollScheduledTask;
        } while (runnablePollScheduledTask != null);
        return true;
    }

    protected Runnable peekTask() {
        if ($assertionsDisabled || inEventLoop()) {
            return this.taskQueue.peek();
        }
        throw new AssertionError();
    }

    protected boolean hasTasks() {
        if ($assertionsDisabled || inEventLoop()) {
            return !this.taskQueue.isEmpty();
        }
        throw new AssertionError();
    }

    public int pendingTasks() {
        return this.taskQueue.size();
    }

    protected void addTask(Runnable task) {
        if (task == null) {
            throw new NullPointerException("task");
        }
        if (!offerTask(task)) {
            reject(task);
        }
    }

    final boolean offerTask(Runnable task) {
        if (isShutdown()) {
            reject();
        }
        return this.taskQueue.offer(task);
    }

    protected boolean removeTask(Runnable task) {
        if (task == null) {
            throw new NullPointerException("task");
        }
        return this.taskQueue.remove(task);
    }

    protected boolean runAllTasks() {
        boolean fetchedAll;
        if (!$assertionsDisabled && !inEventLoop()) {
            throw new AssertionError();
        }
        boolean ranAtLeastOne = false;
        do {
            fetchedAll = fetchFromScheduledTaskQueue();
            if (runAllTasksFrom(this.taskQueue)) {
                ranAtLeastOne = true;
            }
        } while (!fetchedAll);
        if (ranAtLeastOne) {
            this.lastExecutionTime = ScheduledFutureTask.nanoTime();
        }
        afterRunningAllTasks();
        return ranAtLeastOne;
    }

    /* JADX WARN: Incorrect condition in loop: B:10:0x0026 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final boolean runScheduledAndExecutorTasks(int r5) {
        /*
            r4 = this;
            boolean r0 = io.netty.util.concurrent.SingleThreadEventExecutor.$assertionsDisabled
            if (r0 != 0) goto L15
            r0 = r4
            boolean r0 = r0.inEventLoop()
            if (r0 != 0) goto L15
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r1 = r0
            r1.<init>()
            throw r0
        L15:
            r0 = 0
            r7 = r0
        L17:
            r0 = r4
            r1 = r4
            java.util.Queue<java.lang.Runnable> r1 = r1.taskQueue
            boolean r0 = r0.runExistingTasksFrom(r1)
            r1 = r4
            boolean r1 = r1.executeExpiredScheduledTasks()
            r0 = r0 | r1
            r6 = r0
            r0 = r6
            if (r0 == 0) goto L31
            int r7 = r7 + 1
            r0 = r7
            r1 = r5
            if (r0 < r1) goto L17
        L31:
            r0 = r7
            if (r0 <= 0) goto L3c
            r0 = r4
            long r1 = io.netty.util.concurrent.ScheduledFutureTask.nanoTime()
            r0.lastExecutionTime = r1
        L3c:
            r0 = r4
            r0.afterRunningAllTasks()
            r0 = r7
            if (r0 <= 0) goto L48
            r0 = 1
            goto L49
        L48:
            r0 = 0
        L49:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.util.concurrent.SingleThreadEventExecutor.runScheduledAndExecutorTasks(int):boolean");
    }

    protected final boolean runAllTasksFrom(Queue<Runnable> taskQueue) {
        Runnable task = pollTaskFrom(taskQueue);
        if (task == null) {
            return false;
        }
        do {
            safeExecute(task);
            task = pollTaskFrom(taskQueue);
        } while (task != null);
        return true;
    }

    private boolean runExistingTasksFrom(Queue<Runnable> taskQueue) {
        Runnable task;
        Runnable task2 = pollTaskFrom(taskQueue);
        if (task2 == null) {
            return false;
        }
        int remaining = Math.min(this.maxPendingTasks, taskQueue.size());
        safeExecute(task2);
        while (true) {
            int i = remaining;
            remaining--;
            if (i > 0 && (task = taskQueue.poll()) != null) {
                safeExecute(task);
            } else {
                return true;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected boolean runAllTasks(long r6) {
        /*
            r5 = this;
            r0 = r5
            boolean r0 = r0.fetchFromScheduledTaskQueue()
            r0 = r5
            java.lang.Runnable r0 = r0.pollTask()
            r8 = r0
            r0 = r8
            if (r0 != 0) goto L14
            r0 = r5
            r0.afterRunningAllTasks()
            r0 = 0
            return r0
        L14:
            long r0 = io.netty.util.concurrent.ScheduledFutureTask.nanoTime()
            r1 = r6
            long r0 = r0 + r1
            r9 = r0
            r0 = 0
            r11 = r0
        L1e:
            r0 = r8
            safeExecute(r0)
            r0 = r11
            r1 = 1
            long r0 = r0 + r1
            r11 = r0
            r0 = r11
            r1 = 63
            long r0 = r0 & r1
            r1 = 0
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 != 0) goto L43
            long r0 = io.netty.util.concurrent.ScheduledFutureTask.nanoTime()
            r13 = r0
            r0 = r13
            r1 = r9
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 < 0) goto L43
            goto L54
        L43:
            r0 = r5
            java.lang.Runnable r0 = r0.pollTask()
            r8 = r0
            r0 = r8
            if (r0 != 0) goto L1e
            long r0 = io.netty.util.concurrent.ScheduledFutureTask.nanoTime()
            r13 = r0
            goto L54
        L54:
            r0 = r5
            r0.afterRunningAllTasks()
            r0 = r5
            r1 = r13
            r0.lastExecutionTime = r1
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(long):boolean");
    }

    protected void afterRunningAllTasks() {
    }

    protected long delayNanos(long currentTimeNanos) {
        ScheduledFutureTask<?> scheduledTask = peekScheduledTask();
        if (scheduledTask == null) {
            return SCHEDULE_PURGE_INTERVAL;
        }
        return scheduledTask.delayNanos(currentTimeNanos);
    }

    protected long deadlineNanos() {
        ScheduledFutureTask<?> scheduledTask = peekScheduledTask();
        if (scheduledTask == null) {
            return nanoTime() + SCHEDULE_PURGE_INTERVAL;
        }
        return scheduledTask.deadlineNanos();
    }

    protected void updateLastExecutionTime() {
        this.lastExecutionTime = ScheduledFutureTask.nanoTime();
    }

    protected void cleanup() {
    }

    protected void wakeup(boolean inEventLoop) {
        if (!inEventLoop || this.state == 3) {
            this.taskQueue.offer(WAKEUP_TASK);
        }
    }

    @Override // io.netty.util.concurrent.AbstractScheduledEventExecutor
    final void executeScheduledRunnable(final Runnable runnable, boolean isAddition, long deadlineNanos) {
        if (isAddition && beforeScheduledTaskSubmitted(deadlineNanos)) {
            super.executeScheduledRunnable(runnable, isAddition, deadlineNanos);
            return;
        }
        super.executeScheduledRunnable(new NonWakeupRunnable() { // from class: io.netty.util.concurrent.SingleThreadEventExecutor.3
            @Override // java.lang.Runnable
            public void run() {
                runnable.run();
            }
        }, isAddition, deadlineNanos);
        if (isAddition && afterScheduledTaskSubmitted(deadlineNanos)) {
            wakeup(false);
        }
    }

    @Override // io.netty.util.concurrent.EventExecutor
    public boolean inEventLoop(Thread thread) {
        return thread == this.thread;
    }

    public void addShutdownHook(final Runnable task) {
        if (inEventLoop()) {
            this.shutdownHooks.add(task);
        } else {
            execute(new Runnable() { // from class: io.netty.util.concurrent.SingleThreadEventExecutor.4
                @Override // java.lang.Runnable
                public void run() {
                    SingleThreadEventExecutor.this.shutdownHooks.add(task);
                }
            });
        }
    }

    public void removeShutdownHook(final Runnable task) {
        if (inEventLoop()) {
            this.shutdownHooks.remove(task);
        } else {
            execute(new Runnable() { // from class: io.netty.util.concurrent.SingleThreadEventExecutor.5
                @Override // java.lang.Runnable
                public void run() {
                    SingleThreadEventExecutor.this.shutdownHooks.remove(task);
                }
            });
        }
    }

    private boolean runShutdownHooks() {
        boolean ran = false;
        while (!this.shutdownHooks.isEmpty()) {
            List<Runnable> copy = new ArrayList<>(this.shutdownHooks);
            this.shutdownHooks.clear();
            for (Runnable task : copy) {
                try {
                    try {
                        task.run();
                        ran = true;
                    } catch (Throwable t) {
                        logger.warn("Shutdown hook raised an exception.", t);
                        ran = true;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        if (ran) {
            this.lastExecutionTime = ScheduledFutureTask.nanoTime();
        }
        return ran;
    }

    @Override // io.netty.util.concurrent.EventExecutorGroup
    public Future<?> shutdownGracefully(long quietPeriod, long timeout, TimeUnit unit) {
        int newState;
        if (quietPeriod < 0) {
            throw new IllegalArgumentException("quietPeriod: " + quietPeriod + " (expected >= 0)");
        }
        if (timeout < quietPeriod) {
            throw new IllegalArgumentException("timeout: " + timeout + " (expected >= quietPeriod (" + quietPeriod + "))");
        }
        if (unit == null) {
            throw new NullPointerException("unit");
        }
        if (isShuttingDown()) {
            return terminationFuture();
        }
        boolean inEventLoop = inEventLoop();
        while (!isShuttingDown()) {
            boolean wakeup = true;
            int oldState = this.state;
            if (inEventLoop) {
                newState = 3;
            } else {
                switch (oldState) {
                    case 1:
                    case 2:
                        newState = 3;
                        break;
                    default:
                        newState = oldState;
                        wakeup = false;
                        break;
                }
            }
            if (STATE_UPDATER.compareAndSet(this, oldState, newState)) {
                this.gracefulShutdownQuietPeriod = unit.toNanos(quietPeriod);
                this.gracefulShutdownTimeout = unit.toNanos(timeout);
                if (ensureThreadStarted(oldState)) {
                    return this.terminationFuture;
                }
                if (wakeup) {
                    wakeup(inEventLoop);
                }
                return terminationFuture();
            }
        }
        return terminationFuture();
    }

    @Override // io.netty.util.concurrent.EventExecutorGroup
    public Future<?> terminationFuture() {
        return this.terminationFuture;
    }

    @Override // io.netty.util.concurrent.AbstractEventExecutor, java.util.concurrent.ExecutorService, io.netty.util.concurrent.EventExecutorGroup
    @Deprecated
    public void shutdown() {
        int newState;
        if (isShutdown()) {
            return;
        }
        boolean inEventLoop = inEventLoop();
        while (!isShuttingDown()) {
            boolean wakeup = true;
            int oldState = this.state;
            if (inEventLoop) {
                newState = 4;
            } else {
                switch (oldState) {
                    case 1:
                    case 2:
                    case 3:
                        newState = 4;
                        break;
                    default:
                        newState = oldState;
                        wakeup = false;
                        break;
                }
            }
            if (STATE_UPDATER.compareAndSet(this, oldState, newState)) {
                if (!ensureThreadStarted(oldState) && wakeup) {
                    wakeup(inEventLoop);
                    return;
                }
                return;
            }
        }
    }

    @Override // io.netty.util.concurrent.EventExecutorGroup
    public boolean isShuttingDown() {
        return this.state >= 3;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return this.state >= 4;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return this.state == 5;
    }

    protected boolean confirmShutdown() throws InterruptedException {
        if (!isShuttingDown()) {
            return false;
        }
        if (!inEventLoop()) {
            throw new IllegalStateException("must be invoked from an event loop");
        }
        cancelScheduledTasks();
        if (this.gracefulShutdownStartTime == 0) {
            this.gracefulShutdownStartTime = ScheduledFutureTask.nanoTime();
        }
        if (runAllTasks() || runShutdownHooks()) {
            if (isShutdown() || this.gracefulShutdownQuietPeriod == 0) {
                return true;
            }
            wakeup(true);
            return false;
        }
        long nanoTime = ScheduledFutureTask.nanoTime();
        if (!isShutdown() && nanoTime - this.gracefulShutdownStartTime <= this.gracefulShutdownTimeout && nanoTime - this.lastExecutionTime <= this.gracefulShutdownQuietPeriod) {
            wakeup(true);
            try {
                Thread.sleep(100L);
                return false;
            } catch (InterruptedException e) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        if (unit == null) {
            throw new NullPointerException("unit");
        }
        if (inEventLoop()) {
            throw new IllegalStateException("cannot await termination of the current thread");
        }
        this.threadLock.await(timeout, unit);
        return isTerminated();
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable task) {
        if (task == null) {
            throw new NullPointerException("task");
        }
        boolean inEventLoop = inEventLoop();
        addTask(task);
        if (!inEventLoop) {
            startThread();
            if (isShutdown()) {
                boolean reject = false;
                try {
                    if (removeTask(task)) {
                        reject = true;
                    }
                } catch (UnsupportedOperationException e) {
                }
                if (reject) {
                    reject();
                }
            }
        }
        if (!this.addTaskWakesUp && wakesUpForTask(task)) {
            wakeup(inEventLoop);
        }
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws ExecutionException, InterruptedException {
        throwIfInEventLoop("invokeAny");
        return (T) super.invokeAny(collection);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        throwIfInEventLoop("invokeAny");
        return (T) super.invokeAny(collection, j, timeUnit);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> List<java.util.concurrent.Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        throwIfInEventLoop("invokeAll");
        return super.invokeAll(tasks);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> List<java.util.concurrent.Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        throwIfInEventLoop("invokeAll");
        return super.invokeAll(tasks, timeout, unit);
    }

    private void throwIfInEventLoop(String method) {
        if (inEventLoop()) {
            throw new RejectedExecutionException("Calling " + method + " from within the EventLoop is not allowed");
        }
    }

    public final ThreadProperties threadProperties() {
        ThreadProperties threadProperties = this.threadProperties;
        if (threadProperties == null) {
            Thread thread = this.thread;
            if (thread == null) {
                if (!$assertionsDisabled && inEventLoop()) {
                    throw new AssertionError();
                }
                submit(NOOP_TASK).syncUninterruptibly();
                thread = this.thread;
                if (!$assertionsDisabled && thread == null) {
                    throw new AssertionError();
                }
            }
            threadProperties = new DefaultThreadProperties(thread);
            if (!PROPERTIES_UPDATER.compareAndSet(this, null, threadProperties)) {
                threadProperties = this.threadProperties;
            }
        }
        return threadProperties;
    }

    protected boolean wakesUpForTask(Runnable task) {
        return !(task instanceof NonWakeupRunnable);
    }

    protected static void reject() {
        throw new RejectedExecutionException("event executor terminated");
    }

    protected final void reject(Runnable task) {
        this.rejectedExecutionHandler.rejected(task, this);
    }

    private void startThread() {
        if (this.state == 1 && STATE_UPDATER.compareAndSet(this, 1, 2)) {
            boolean success = false;
            try {
                doStartThread();
                success = true;
                if (1 == 0) {
                    STATE_UPDATER.compareAndSet(this, 2, 1);
                }
            } catch (Throwable th) {
                if (!success) {
                    STATE_UPDATER.compareAndSet(this, 2, 1);
                }
                throw th;
            }
        }
    }

    private boolean ensureThreadStarted(int oldState) {
        if (oldState == 1) {
            try {
                doStartThread();
                return false;
            } catch (Throwable cause) {
                STATE_UPDATER.set(this, 5);
                this.terminationFuture.tryFailure(cause);
                if (!(cause instanceof Exception)) {
                    PlatformDependent.throwException(cause);
                    return true;
                }
                return true;
            }
        }
        return false;
    }

    private void doStartThread() {
        if (!$assertionsDisabled && this.thread != null) {
            throw new AssertionError();
        }
        this.executor.execute(new Runnable() { // from class: io.netty.util.concurrent.SingleThreadEventExecutor.6
            /* JADX WARN: Code restructure failed: missing block: B:146:0x05d0, code lost:
            
                r16 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:147:0x05d2, code lost:
            
                io.netty.util.concurrent.FastThreadLocal.removeAll();
             */
            /* JADX WARN: Code restructure failed: missing block: B:148:0x05d5, code lost:
            
                io.netty.util.concurrent.SingleThreadEventExecutor.STATE_UPDATER.set(r5.this$0, 5);
                r5.this$0.threadLock.countDown();
             */
            /* JADX WARN: Code restructure failed: missing block: B:149:0x05f2, code lost:
            
                if (io.netty.util.concurrent.SingleThreadEventExecutor.logger.isWarnEnabled() != false) goto L150;
             */
            /* JADX WARN: Code restructure failed: missing block: B:152:0x0604, code lost:
            
                io.netty.util.concurrent.SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + r5.this$0.taskQueue.size() + ')');
             */
            /* JADX WARN: Code restructure failed: missing block: B:153:0x062f, code lost:
            
                r5.this$0.terminationFuture.setSuccess(null);
             */
            /* JADX WARN: Code restructure failed: missing block: B:154:0x063f, code lost:
            
                throw r16;
             */
            /* JADX WARN: Code restructure failed: missing block: B:32:0x0121, code lost:
            
                r8 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:33:0x0122, code lost:
            
                io.netty.util.concurrent.FastThreadLocal.removeAll();
                io.netty.util.concurrent.SingleThreadEventExecutor.STATE_UPDATER.set(r5.this$0, 5);
                r5.this$0.threadLock.countDown();
             */
            /* JADX WARN: Code restructure failed: missing block: B:34:0x0142, code lost:
            
                if (io.netty.util.concurrent.SingleThreadEventExecutor.logger.isWarnEnabled() != false) goto L35;
             */
            /* JADX WARN: Code restructure failed: missing block: B:37:0x0154, code lost:
            
                io.netty.util.concurrent.SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + r5.this$0.taskQueue.size() + ')');
             */
            /* JADX WARN: Code restructure failed: missing block: B:38:0x017f, code lost:
            
                r5.this$0.terminationFuture.setSuccess(null);
             */
            /* JADX WARN: Code restructure failed: missing block: B:39:0x018e, code lost:
            
                throw r8;
             */
            /* JADX WARN: Code restructure failed: missing block: B:89:0x037b, code lost:
            
                r11 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:90:0x037d, code lost:
            
                io.netty.util.concurrent.FastThreadLocal.removeAll();
             */
            /* JADX WARN: Code restructure failed: missing block: B:91:0x0380, code lost:
            
                io.netty.util.concurrent.SingleThreadEventExecutor.STATE_UPDATER.set(r5.this$0, 5);
                r5.this$0.threadLock.countDown();
             */
            /* JADX WARN: Code restructure failed: missing block: B:92:0x039d, code lost:
            
                if (io.netty.util.concurrent.SingleThreadEventExecutor.logger.isWarnEnabled() != false) goto L93;
             */
            /* JADX WARN: Code restructure failed: missing block: B:95:0x03af, code lost:
            
                io.netty.util.concurrent.SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + r5.this$0.taskQueue.size() + ')');
             */
            /* JADX WARN: Code restructure failed: missing block: B:96:0x03da, code lost:
            
                r5.this$0.terminationFuture.setSuccess(null);
             */
            /* JADX WARN: Code restructure failed: missing block: B:97:0x03ea, code lost:
            
                throw r11;
             */
            /* JADX WARN: Finally extract failed */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 1841
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: io.netty.util.concurrent.SingleThreadEventExecutor.AnonymousClass6.run():void");
            }
        });
    }

    /* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/concurrent/SingleThreadEventExecutor$DefaultThreadProperties.class */
    private static final class DefaultThreadProperties implements ThreadProperties {
        private final Thread t;

        DefaultThreadProperties(Thread t) {
            this.t = t;
        }

        @Override // io.netty.util.concurrent.ThreadProperties
        public Thread.State state() {
            return this.t.getState();
        }

        @Override // io.netty.util.concurrent.ThreadProperties
        public int priority() {
            return this.t.getPriority();
        }

        @Override // io.netty.util.concurrent.ThreadProperties
        public boolean isInterrupted() {
            return this.t.isInterrupted();
        }

        @Override // io.netty.util.concurrent.ThreadProperties
        public boolean isDaemon() {
            return this.t.isDaemon();
        }

        @Override // io.netty.util.concurrent.ThreadProperties
        public String name() {
            return this.t.getName();
        }

        @Override // io.netty.util.concurrent.ThreadProperties
        public long id() {
            return this.t.getId();
        }

        @Override // io.netty.util.concurrent.ThreadProperties
        public StackTraceElement[] stackTrace() {
            return this.t.getStackTrace();
        }

        @Override // io.netty.util.concurrent.ThreadProperties
        public boolean isAlive() {
            return this.t.isAlive();
        }
    }
}
