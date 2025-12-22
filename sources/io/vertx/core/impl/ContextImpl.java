package io.vertx.core.impl;

import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;
import io.vertx.core.AsyncResult;
import io.vertx.core.Closeable;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Starter;
import io.vertx.core.VertxOptions;
import io.vertx.core.impl.BlockedThreadChecker;
import io.vertx.core.impl.launcher.VertxCommandLauncher;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.spi.metrics.PoolMetrics;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/ContextImpl.class */
abstract class ContextImpl implements ContextInternal {
    protected final VertxInternal owner;
    protected final String deploymentID;
    protected final JsonObject config;
    private Deployment deployment;
    private CloseHooks closeHooks;
    private final ClassLoader tccl;
    private final EventLoop eventLoop;
    private ConcurrentMap<Object, Object> contextData;
    private volatile Handler<Throwable> exceptionHandler;
    protected final WorkerPool workerPool;
    protected final WorkerPool internalBlockingPool;
    final TaskQueue orderedTasks;
    protected final TaskQueue internalOrderedTasks;
    private static FastThreadLocal<Holder> holderLocal = new FastThreadLocal<Holder>() { // from class: io.vertx.core.impl.ContextImpl.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.netty.util.concurrent.FastThreadLocal
        public Holder initialValue() {
            return new Holder();
        }
    };
    private static final Logger log = LoggerFactory.getLogger((Class<?>) ContextImpl.class);
    private static final String THREAD_CHECKS_PROP_NAME = "vertx.threadChecks";
    private static final boolean THREAD_CHECKS = Boolean.getBoolean(THREAD_CHECKS_PROP_NAME);
    private static final String DISABLE_TIMINGS_PROP_NAME = "vertx.disableContextTimings";
    private static final boolean DISABLE_TIMINGS = Boolean.getBoolean(DISABLE_TIMINGS_PROP_NAME);
    private static final String DISABLE_TCCL_PROP_NAME = "vertx.disableTCCL";
    private static final boolean DISABLE_TCCL = Boolean.getBoolean(DISABLE_TCCL_PROP_NAME);

    abstract void executeAsync(Handler<Void> handler);

    abstract <T> void execute(T t, Handler<T> handler);

    @Override // io.vertx.core.Context
    public abstract boolean isEventLoopContext();

    @Override // io.vertx.core.Context
    public abstract boolean isMultiThreadedWorkerContext();

    public static Context context() {
        Thread current = Thread.currentThread();
        if (current instanceof VertxThread) {
            return ((VertxThread) current).getContext();
        }
        if (current instanceof FastThreadLocalThread) {
            return holderLocal.get().ctx;
        }
        return null;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/ContextImpl$Holder.class */
    static class Holder implements BlockedThreadChecker.Task {
        BlockedThreadChecker checker;
        ContextInternal ctx;
        long startTime = 0;
        long maxExecTime = VertxOptions.DEFAULT_MAX_EVENT_LOOP_EXECUTE_TIME;
        TimeUnit maxExecTimeUnit = VertxOptions.DEFAULT_MAX_EVENT_LOOP_EXECUTE_TIME_UNIT;

        Holder() {
        }

        @Override // io.vertx.core.impl.BlockedThreadChecker.Task
        public long startTime() {
            return this.startTime;
        }

        @Override // io.vertx.core.impl.BlockedThreadChecker.Task
        public long maxExecTime() {
            return this.maxExecTime;
        }

        @Override // io.vertx.core.impl.BlockedThreadChecker.Task
        public TimeUnit maxExecTimeUnit() {
            return this.maxExecTimeUnit;
        }
    }

    private static EventLoop getEventLoop(VertxInternal vertx) {
        EventLoopGroup group = vertx.getEventLoopGroup();
        if (group != null) {
            return group.next();
        }
        return null;
    }

    protected ContextImpl(VertxInternal vertx, WorkerPool internalBlockingPool, WorkerPool workerPool, String deploymentID, JsonObject config, ClassLoader tccl) {
        this(vertx, getEventLoop(vertx), internalBlockingPool, workerPool, deploymentID, config, tccl);
    }

    protected ContextImpl(VertxInternal vertx, EventLoop eventLoop, WorkerPool internalBlockingPool, WorkerPool workerPool, String deploymentID, JsonObject config, ClassLoader tccl) {
        if (DISABLE_TCCL && tccl != ClassLoader.getSystemClassLoader()) {
            log.warn("You have disabled TCCL checks but you have a custom TCCL to set.");
        }
        this.deploymentID = deploymentID;
        this.config = config;
        this.eventLoop = eventLoop;
        this.tccl = tccl;
        this.owner = vertx;
        this.workerPool = workerPool;
        this.internalBlockingPool = internalBlockingPool;
        this.orderedTasks = new TaskQueue();
        this.internalOrderedTasks = new TaskQueue();
        this.closeHooks = new CloseHooks(log);
    }

    static void setContext(ContextImpl context) {
        Thread current = Thread.currentThread();
        if (current instanceof VertxThread) {
            setContext((VertxThread) current, context);
            return;
        }
        throw new IllegalStateException("Attempt to setContext on non Vert.x thread " + Thread.currentThread());
    }

    private static void setContext(FastThreadLocalThread thread, ContextImpl context) {
        if (thread instanceof VertxThread) {
            ((VertxThread) thread).setContext(context);
        } else {
            Holder holder = holderLocal.get();
            holder.ctx = context;
        }
        if (!DISABLE_TCCL) {
            if (context != null) {
                context.setTCCL();
            } else {
                Thread.currentThread().setContextClassLoader(null);
            }
        }
    }

    public void setDeployment(Deployment deployment) {
        this.deployment = deployment;
    }

    @Override // io.vertx.core.impl.ContextInternal
    public Deployment getDeployment() {
        return this.deployment;
    }

    @Override // io.vertx.core.Context
    public void addCloseHook(Closeable hook) {
        this.closeHooks.add(hook);
    }

    @Override // io.vertx.core.Context
    public boolean removeCloseHook(Closeable hook) {
        return this.closeHooks.remove(hook);
    }

    public void runCloseHooks(Handler<AsyncResult<Void>> completionHandler) {
        this.closeHooks.run(completionHandler);
        VertxThreadFactory.unsetContext(this);
    }

    @Override // io.vertx.core.Context
    public <T> T get(String str) {
        return (T) contextData().get(str);
    }

    @Override // io.vertx.core.Context
    public void put(String key, Object value) {
        contextData().put(key, value);
    }

    @Override // io.vertx.core.Context
    public boolean remove(String key) {
        return contextData().remove(key) != null;
    }

    @Override // io.vertx.core.Context
    public boolean isWorkerContext() {
        return !isEventLoopContext();
    }

    static boolean isOnVertxThread(boolean worker) {
        Thread t = Thread.currentThread();
        if (t instanceof VertxThread) {
            VertxThread vt = (VertxThread) t;
            return vt.isWorker() == worker;
        }
        return false;
    }

    @Override // io.vertx.core.impl.ContextInternal
    public final void executeFromIO(Handler<Void> task) {
        executeFromIO(null, task);
    }

    @Override // io.vertx.core.impl.ContextInternal
    public final <T> void executeFromIO(T value, Handler<T> task) {
        if (THREAD_CHECKS) {
            checkEventLoopThread();
        }
        execute(value, task);
    }

    private void checkEventLoopThread() {
        Thread current = Thread.currentThread();
        if (!(current instanceof FastThreadLocalThread)) {
            throw new IllegalStateException("Expected to be on Vert.x thread, but actually on: " + current);
        }
        if ((current instanceof VertxThread) && ((VertxThread) current).isWorker()) {
            throw new IllegalStateException("Event delivered on unexpected worker thread " + current);
        }
    }

    @Override // io.vertx.core.Context
    public void runOnContext(Handler<Void> task) {
        try {
            executeAsync(task);
        } catch (RejectedExecutionException e) {
        }
    }

    @Override // io.vertx.core.Context
    public String deploymentID() {
        return this.deploymentID;
    }

    @Override // io.vertx.core.Context
    public JsonObject config() {
        return this.config;
    }

    @Override // io.vertx.core.Context
    public List<String> processArgs() {
        List<String> processArgument = VertxCommandLauncher.getProcessArguments();
        return processArgument != null ? processArgument : Starter.PROCESS_ARGS;
    }

    @Override // io.vertx.core.impl.ContextInternal
    public EventLoop nettyEventLoop() {
        return this.eventLoop;
    }

    @Override // io.vertx.core.impl.ContextInternal, io.vertx.core.Context
    public VertxInternal owner() {
        return this.owner;
    }

    @Override // io.vertx.core.impl.ContextInternal
    public <T> void executeBlockingInternal(Handler<Promise<T>> action, Handler<AsyncResult<T>> resultHandler) {
        executeBlocking(action, resultHandler, this.internalBlockingPool.executor(), this.internalOrderedTasks, this.internalBlockingPool.metrics());
    }

    @Override // io.vertx.core.Context
    public <T> void executeBlocking(Handler<Promise<T>> blockingCodeHandler, boolean ordered, Handler<AsyncResult<T>> resultHandler) {
        executeBlocking(blockingCodeHandler, resultHandler, this.workerPool.executor(), ordered ? this.orderedTasks : null, this.workerPool.metrics());
    }

    @Override // io.vertx.core.Context
    public <T> void executeBlocking(Handler<Promise<T>> blockingCodeHandler, Handler<AsyncResult<T>> resultHandler) {
        executeBlocking((Handler) blockingCodeHandler, true, (Handler) resultHandler);
    }

    @Override // io.vertx.core.impl.ContextInternal
    public <T> void executeBlocking(Handler<Promise<T>> blockingCodeHandler, TaskQueue queue, Handler<AsyncResult<T>> resultHandler) {
        executeBlocking(blockingCodeHandler, resultHandler, this.workerPool.executor(), queue, this.workerPool.metrics());
    }

    /* JADX WARN: Multi-variable type inference failed */
    <T> void executeBlocking(Handler<Promise<T>> handler, Handler<AsyncResult<T>> handler2, Executor executor, TaskQueue taskQueue, PoolMetrics poolMetrics) {
        T tSubmitted = poolMetrics != null ? poolMetrics.submitted() : null;
        try {
            Runnable runnable = () -> {
                VertxThread current = (VertxThread) Thread.currentThread();
                Object execMetric = null;
                if (poolMetrics != null) {
                    execMetric = poolMetrics.begin(tSubmitted);
                }
                if (!DISABLE_TIMINGS) {
                    current.executeStart();
                }
                Promise promise = Promise.promise();
                try {
                    try {
                        setContext(this);
                        handler.handle(promise);
                        if (!DISABLE_TIMINGS) {
                            current.executeEnd();
                        }
                    } catch (Throwable e) {
                        promise.tryFail(e);
                        if (!DISABLE_TIMINGS) {
                            current.executeEnd();
                        }
                    }
                    Future future = promise.future();
                    if (poolMetrics != null) {
                        poolMetrics.end(execMetric, future.succeeded());
                    }
                    future.setHandler2(ar -> {
                        if (handler2 != null) {
                            runOnContext(v -> {
                                handler2.handle(ar);
                            });
                        } else if (ar.failed()) {
                            reportException(ar.cause());
                        }
                    });
                } catch (Throwable th) {
                    if (!DISABLE_TIMINGS) {
                        current.executeEnd();
                    }
                    throw th;
                }
            };
            if (taskQueue != null) {
                taskQueue.execute(runnable, executor);
            } else {
                executor.execute(runnable);
            }
        } catch (RejectedExecutionException e) {
            if (poolMetrics != null) {
                poolMetrics.rejected(tSubmitted);
            }
            throw e;
        }
    }

    @Override // io.vertx.core.impl.ContextInternal
    public synchronized ConcurrentMap<Object, Object> contextData() {
        if (this.contextData == null) {
            this.contextData = new ConcurrentHashMap();
        }
        return this.contextData;
    }

    <T> boolean executeTask(T arg, Handler<T> hTask) {
        Thread th = Thread.currentThread();
        if (!(th instanceof FastThreadLocalThread)) {
            throw new IllegalStateException("Uh oh! context executing with wrong thread! " + th);
        }
        FastThreadLocalThread current = (FastThreadLocalThread) th;
        if (!DISABLE_TIMINGS) {
            executeStart(current);
        }
        try {
            try {
                setContext(current, this);
                hTask.handle(arg);
                if (!DISABLE_TIMINGS) {
                    executeEnd(current);
                }
                return true;
            } catch (Throwable t) {
                reportException(t);
                if (!DISABLE_TIMINGS) {
                    executeEnd(current);
                }
                return false;
            }
        } catch (Throwable th2) {
            if (!DISABLE_TIMINGS) {
                executeEnd(current);
            }
            throw th2;
        }
    }

    private void executeStart(FastThreadLocalThread thread) {
        if (thread instanceof VertxThread) {
            ((VertxThread) thread).executeStart();
            return;
        }
        Holder holder = holderLocal.get();
        if (holder.checker == null) {
            BlockedThreadChecker checker = owner().blockedThreadChecker();
            holder.checker = checker;
            holder.maxExecTime = this.owner.maxEventLoopExecTime();
            holder.maxExecTimeUnit = this.owner.maxEventLoopExecTimeUnit();
            checker.registerThread(thread, holder);
        }
        holder.startTime = System.nanoTime();
    }

    private void executeEnd(FastThreadLocalThread thread) {
        if (thread instanceof VertxThread) {
            ((VertxThread) thread).executeEnd();
        } else {
            Holder holder = holderLocal.get();
            holder.startTime = 0L;
        }
    }

    @Override // io.vertx.core.impl.ContextInternal
    public void reportException(Throwable t) {
        Handler<Throwable> handler = this.exceptionHandler;
        if (handler == null) {
            handler = this.owner.exceptionHandler();
        }
        if (handler != null) {
            handler.handle(t);
        } else {
            log.error("Unhandled exception", t);
        }
    }

    private void setTCCL() {
        Thread.currentThread().setContextClassLoader(this.tccl);
    }

    @Override // io.vertx.core.Context
    public Context exceptionHandler(Handler<Throwable> handler) {
        this.exceptionHandler = handler;
        return this;
    }

    @Override // io.vertx.core.Context
    public Handler<Throwable> exceptionHandler() {
        return this.exceptionHandler;
    }

    @Override // io.vertx.core.Context
    public int getInstanceCount() {
        if (this.deployment == null) {
            return 0;
        }
        if (this.deployment.deploymentOptions() == null) {
            return 1;
        }
        return this.deployment.deploymentOptions().getInstances();
    }
}
