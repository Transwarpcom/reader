package kotlinx.coroutines;

import ch.qos.logback.core.CoreConstants;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.time.DurationKt;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* compiled from: EventLoop.common.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��Z\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b \u0018��2\u0002092\u00020::\u00044567B\u0007¢\u0006\u0004\b\u0001\u0010\u0002J\u000f\u0010\u0004\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0004\u0010\u0002J\u0017\u0010\u0007\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ!\u0010\f\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\t2\n\u0010\u000b\u001a\u00060\u0005j\u0002`\u0006¢\u0006\u0004\b\f\u0010\rJ\u0019\u0010\u000f\u001a\u00020\u00032\n\u0010\u000e\u001a\u00060\u0005j\u0002`\u0006¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0012\u001a\u00020\u00112\n\u0010\u000e\u001a\u00060\u0005j\u0002`\u0006H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0017\u0010\u0002J\u000f\u0010\u0018\u001a\u00020\u0003H\u0004¢\u0006\u0004\b\u0018\u0010\u0002J\u001d\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b\u001c\u0010\u001dJ\u001f\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001aH\u0002¢\u0006\u0004\b\u001f\u0010 J#\u0010#\u001a\u00020\"2\u0006\u0010!\u001a\u00020\u00142\n\u0010\u000b\u001a\u00060\u0005j\u0002`\u0006H\u0004¢\u0006\u0004\b#\u0010$J%\u0010'\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00142\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00030%H\u0016¢\u0006\u0004\b'\u0010(J\u0017\u0010)\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u001aH\u0002¢\u0006\u0004\b)\u0010*J\u000f\u0010+\u001a\u00020\u0003H\u0014¢\u0006\u0004\b+\u0010\u0002R$\u0010-\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\u00118B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u0014\u00101\u001a\u00020\u00118TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b1\u0010.R\u0014\u00103\u001a\u00020\u00148TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b2\u0010\u0016¨\u00068"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase;", Constants.CONSTRUCTOR_NAME, "()V", "", "closeQueue", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "dequeue", "()Ljava/lang/Runnable;", "Lkotlin/coroutines/CoroutineContext;", CoreConstants.CONTEXT_SCOPE_VALUE, "block", "dispatch", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Runnable;)V", "task", "enqueue", "(Ljava/lang/Runnable;)V", "", "enqueueImpl", "(Ljava/lang/Runnable;)Z", "", "processNextEvent", "()J", "rescheduleAllDelayed", "resetAll", "now", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "delayedTask", "schedule", "(JLkotlinx/coroutines/EventLoopImplBase$DelayedTask;)V", "", "scheduleImpl", "(JLkotlinx/coroutines/EventLoopImplBase$DelayedTask;)I", "timeMillis", "Lkotlinx/coroutines/DisposableHandle;", "scheduleInvokeOnTimeout", "(JLjava/lang/Runnable;)Lkotlinx/coroutines/DisposableHandle;", "Lkotlinx/coroutines/CancellableContinuation;", "continuation", "scheduleResumeAfterDelay", "(JLkotlinx/coroutines/CancellableContinuation;)V", "shouldUnpark", "(Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;)Z", "shutdown", "value", "isCompleted", "()Z", "setCompleted", "(Z)V", "isEmpty", "getNextTime", "nextTime", "DelayedResumeTask", "DelayedRunnableTask", "DelayedTask", "DelayedTaskQueue", "kotlinx-coroutines-core", "Lkotlinx/coroutines/EventLoopImplPlatform;", "Lkotlinx/coroutines/Delay;"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/EventLoopImplBase.class */
public abstract class EventLoopImplBase extends EventLoopImplPlatform implements Delay {

    @NotNull
    private volatile /* synthetic */ Object _queue = null;

    @NotNull
    private volatile /* synthetic */ Object _delayed = null;

    @NotNull
    private volatile /* synthetic */ int _isCompleted = 0;
    private static final /* synthetic */ AtomicReferenceFieldUpdater _queue$FU = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_queue");
    private static final /* synthetic */ AtomicReferenceFieldUpdater _delayed$FU = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_delayed");

    @NotNull
    public DisposableHandle invokeOnTimeout(long timeMillis, @NotNull Runnable block, @NotNull CoroutineContext context) {
        return Delay.DefaultImpls.invokeOnTimeout(this, timeMillis, block, context);
    }

    @Override // kotlinx.coroutines.Delay
    @Nullable
    public Object delay(long time, @NotNull Continuation<? super Unit> continuation) {
        return Delay.DefaultImpls.delay(this, time, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean, int] */
    public final boolean isCompleted() {
        return this._isCompleted;
    }

    private final void setCompleted(boolean z) {
        this._isCompleted = z ? 1 : 0;
    }

    @Override // kotlinx.coroutines.EventLoop
    protected boolean isEmpty() {
        if (!isUnconfinedQueueEmpty()) {
            return false;
        }
        DelayedTaskQueue delayed = (DelayedTaskQueue) this._delayed;
        if (delayed != null && !delayed.isEmpty()) {
            return false;
        }
        Object queue = this._queue;
        if (queue == null) {
            return true;
        }
        return queue instanceof LockFreeTaskQueueCore ? ((LockFreeTaskQueueCore) queue).isEmpty() : queue == EventLoop_commonKt.CLOSED_EMPTY;
    }

    @Override // kotlinx.coroutines.EventLoop
    protected long getNextTime() {
        if (super.getNextTime() == 0) {
            return 0L;
        }
        Object queue = this._queue;
        if (queue != null) {
            if (!(queue instanceof LockFreeTaskQueueCore)) {
                return queue == EventLoop_commonKt.CLOSED_EMPTY ? Long.MAX_VALUE : 0L;
            }
            if (!((LockFreeTaskQueueCore) queue).isEmpty()) {
                return 0L;
            }
        }
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed;
        DelayedTask nextDelayedTask = delayedTaskQueue == null ? null : delayedTaskQueue.peek();
        if (nextDelayedTask == null) {
            return Long.MAX_VALUE;
        }
        long j = nextDelayedTask.nanoTime;
        AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
        return RangesKt.coerceAtLeast(j - (timeSource == null ? System.nanoTime() : timeSource.nanoTime()), 0L);
    }

    @Override // kotlinx.coroutines.EventLoop
    protected void shutdown() {
        ThreadLocalEventLoop.INSTANCE.resetEventLoop$kotlinx_coroutines_core();
        setCompleted(true);
        closeQueue();
        while (processNextEvent() <= 0) {
        }
        rescheduleAllDelayed();
    }

    @Override // kotlinx.coroutines.Delay
    /* renamed from: scheduleResumeAfterDelay */
    public void mo4327scheduleResumeAfterDelay(long timeMillis, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
        long timeNanos = EventLoop_commonKt.delayToNanos(timeMillis);
        if (timeNanos < DurationKt.MAX_MILLIS) {
            AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
            long now = timeSource == null ? System.nanoTime() : timeSource.nanoTime();
            DelayedResumeTask task = new DelayedResumeTask(now + timeNanos, cancellableContinuation);
            CancellableContinuationKt.disposeOnCancellation(cancellableContinuation, task);
            schedule(now, task);
        }
    }

    @NotNull
    protected final DisposableHandle scheduleInvokeOnTimeout(long timeMillis, @NotNull Runnable block) {
        long timeNanos = EventLoop_commonKt.delayToNanos(timeMillis);
        if (timeNanos < DurationKt.MAX_MILLIS) {
            AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
            long now = timeSource == null ? System.nanoTime() : timeSource.nanoTime();
            DelayedRunnableTask task = new DelayedRunnableTask(now + timeNanos, block);
            schedule(now, task);
            return task;
        }
        return NonDisposableHandle.INSTANCE;
    }

    @Override // kotlinx.coroutines.EventLoop
    public long processNextEvent() {
        boolean zEnqueueImpl;
        Object objRemoveAtImpl;
        Object obj;
        if (processUnconfinedEvent()) {
            return 0L;
        }
        ThreadSafeHeap delayed = (DelayedTaskQueue) this._delayed;
        if (delayed != null && !delayed.isEmpty()) {
            AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
            long now = timeSource == null ? System.nanoTime() : timeSource.nanoTime();
            do {
                ThreadSafeHeap this_$iv = delayed;
                synchronized (this_$iv) {
                    ThreadSafeHeapNode first$iv = this_$iv.firstImpl();
                    if (first$iv == null) {
                        obj = null;
                    } else {
                        DelayedTask it = (DelayedTask) first$iv;
                        if (it.timeToExecute(now)) {
                            zEnqueueImpl = enqueueImpl(it);
                        } else {
                            zEnqueueImpl = false;
                        }
                        if (zEnqueueImpl) {
                            objRemoveAtImpl = this_$iv.removeAtImpl(0);
                        } else {
                            objRemoveAtImpl = (ThreadSafeHeapNode) null;
                        }
                        obj = objRemoveAtImpl;
                    }
                }
            } while (((DelayedTask) obj) != null);
        }
        Runnable task = dequeue();
        if (task != null) {
            task.run();
            return 0L;
        }
        return getNextTime();
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    /* renamed from: dispatch */
    public final void mo4326dispatch(@NotNull CoroutineContext context, @NotNull Runnable block) {
        enqueue(block);
    }

    public final void enqueue(@NotNull Runnable task) {
        if (enqueueImpl(task)) {
            unpark();
        } else {
            DefaultExecutor.INSTANCE.enqueue(task);
        }
    }

    private final boolean enqueueImpl(Runnable task) {
        while (true) {
            Object queue = this._queue;
            if (isCompleted()) {
                return false;
            }
            if (queue == null) {
                if (_queue$FU.compareAndSet(this, null, task)) {
                    return true;
                }
            } else if (queue instanceof LockFreeTaskQueueCore) {
                if (queue == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeTaskQueueCore<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }>{ kotlinx.coroutines.EventLoop_commonKt.Queue<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }> }");
                }
                switch (((LockFreeTaskQueueCore) queue).addLast(task)) {
                    case 0:
                        return true;
                    case 1:
                        _queue$FU.compareAndSet(this, queue, ((LockFreeTaskQueueCore) queue).next());
                        break;
                    case 2:
                        return false;
                }
            } else {
                if (queue == EventLoop_commonKt.CLOSED_EMPTY) {
                    return false;
                }
                LockFreeTaskQueueCore newQueue = new LockFreeTaskQueueCore(8, true);
                if (queue == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }");
                }
                newQueue.addLast((Runnable) queue);
                newQueue.addLast(task);
                if (_queue$FU.compareAndSet(this, queue, newQueue)) {
                    return true;
                }
            }
        }
    }

    private final Runnable dequeue() {
        while (true) {
            Object queue = this._queue;
            if (queue == null) {
                return null;
            }
            if (queue instanceof LockFreeTaskQueueCore) {
                if (queue == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeTaskQueueCore<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }>{ kotlinx.coroutines.EventLoop_commonKt.Queue<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }> }");
                }
                Object result = ((LockFreeTaskQueueCore) queue).removeFirstOrNull();
                if (result != LockFreeTaskQueueCore.REMOVE_FROZEN) {
                    return (Runnable) result;
                }
                _queue$FU.compareAndSet(this, queue, ((LockFreeTaskQueueCore) queue).next());
            } else {
                if (queue == EventLoop_commonKt.CLOSED_EMPTY) {
                    return null;
                }
                if (_queue$FU.compareAndSet(this, queue, null)) {
                    if (queue == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }");
                    }
                    return (Runnable) queue;
                }
            }
        }
    }

    private final void closeQueue() {
        if (DebugKt.getASSERTIONS_ENABLED() && !isCompleted()) {
            throw new AssertionError();
        }
        while (true) {
            Object queue = this._queue;
            if (queue == null) {
                if (_queue$FU.compareAndSet(this, null, EventLoop_commonKt.CLOSED_EMPTY)) {
                    return;
                }
            } else {
                if (queue instanceof LockFreeTaskQueueCore) {
                    ((LockFreeTaskQueueCore) queue).close();
                    return;
                }
                if (queue == EventLoop_commonKt.CLOSED_EMPTY) {
                    return;
                }
                LockFreeTaskQueueCore newQueue = new LockFreeTaskQueueCore(8, true);
                if (queue == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }");
                }
                newQueue.addLast((Runnable) queue);
                if (_queue$FU.compareAndSet(this, queue, newQueue)) {
                    return;
                }
            }
        }
    }

    public final void schedule(long now, @NotNull DelayedTask delayedTask) {
        switch (scheduleImpl(now, delayedTask)) {
            case 0:
                if (shouldUnpark(delayedTask)) {
                    unpark();
                    return;
                }
                return;
            case 1:
                reschedule(now, delayedTask);
                return;
            case 2:
                return;
            default:
                throw new IllegalStateException("unexpected result".toString());
        }
    }

    private final boolean shouldUnpark(DelayedTask task) {
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed;
        return (delayedTaskQueue == null ? null : delayedTaskQueue.peek()) == task;
    }

    private final int scheduleImpl(long now, DelayedTask delayedTask) {
        DelayedTaskQueue delayedTaskQueue;
        if (isCompleted()) {
            return 1;
        }
        DelayedTaskQueue delayedTaskQueue2 = (DelayedTaskQueue) this._delayed;
        if (delayedTaskQueue2 != null) {
            delayedTaskQueue = delayedTaskQueue2;
        } else {
            EventLoopImplBase $this$scheduleImpl_u24lambda_u2d7 = this;
            _delayed$FU.compareAndSet($this$scheduleImpl_u24lambda_u2d7, null, new DelayedTaskQueue(now));
            Object obj = $this$scheduleImpl_u24lambda_u2d7._delayed;
            Intrinsics.checkNotNull(obj);
            delayedTaskQueue = (DelayedTaskQueue) obj;
        }
        DelayedTaskQueue delayedQueue = delayedTaskQueue;
        return delayedTask.scheduleTask(now, delayedQueue, this);
    }

    protected final void resetAll() {
        this._queue = null;
        this._delayed = null;
    }

    private final void rescheduleAllDelayed() {
        AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
        long now = timeSource == null ? System.nanoTime() : timeSource.nanoTime();
        while (true) {
            DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed;
            DelayedTask delayedTask = delayedTaskQueue == null ? null : delayedTaskQueue.removeFirstOrNull();
            if (delayedTask != null) {
                reschedule(now, delayedTask);
            } else {
                return;
            }
        }
    }

    /* compiled from: EventLoop.common.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u000e\n��\b \u0018��2\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020��0\u00032\u00020\u00042\u00020\u0005B\r\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0011\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020��H\u0096\u0002J\u0006\u0010\u001a\u001a\u00020\u001bJ\u001e\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020#2\u0006\u0010\u001d\u001a\u00020\u0007J\b\u0010$\u001a\u00020%H\u0016R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n��R0\u0010\r\u001a\b\u0012\u0002\b\u0003\u0018\u00010\f2\f\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\f8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0012\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n��¨\u0006&"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "", "Lkotlinx/coroutines/DisposableHandle;", "Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "nanoTime", "", "(J)V", "_heap", "", "value", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "heap", "getHeap", "()Lkotlinx/coroutines/internal/ThreadSafeHeap;", "setHeap", "(Lkotlinx/coroutines/internal/ThreadSafeHeap;)V", "index", "", "getIndex", "()I", "setIndex", "(I)V", "compareTo", "other", "dispose", "", "scheduleTask", "now", "delayed", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTaskQueue;", "eventLoop", "Lkotlinx/coroutines/EventLoopImplBase;", "timeToExecute", "", "toString", "", "kotlinx-coroutines-core"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/EventLoopImplBase$DelayedTask.class */
    public static abstract class DelayedTask implements Runnable, Comparable<DelayedTask>, DisposableHandle, ThreadSafeHeapNode {

        @JvmField
        public long nanoTime;

        @Nullable
        private Object _heap;
        private int index = -1;

        public DelayedTask(long nanoTime) {
            this.nanoTime = nanoTime;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        @Nullable
        public ThreadSafeHeap<?> getHeap() {
            Object obj = this._heap;
            if (obj instanceof ThreadSafeHeap) {
                return (ThreadSafeHeap) obj;
            }
            return null;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public void setHeap(@Nullable ThreadSafeHeap<?> threadSafeHeap) {
            if (!(this._heap != EventLoop_commonKt.DISPOSED_TASK)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            this._heap = threadSafeHeap;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public int getIndex() {
            return this.index;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public void setIndex(int i) {
            this.index = i;
        }

        @Override // java.lang.Comparable
        public int compareTo(@NotNull DelayedTask other) {
            long dTime = this.nanoTime - other.nanoTime;
            if (dTime > 0) {
                return 1;
            }
            return dTime < 0 ? -1 : 0;
        }

        public final boolean timeToExecute(long now) {
            return now - this.nanoTime >= 0;
        }

        public final synchronized int scheduleTask(long now, @NotNull DelayedTaskQueue delayed, @NotNull EventLoopImplBase eventLoop) {
            boolean z;
            if (this._heap == EventLoop_commonKt.DISPOSED_TASK) {
                return 2;
            }
            DelayedTaskQueue this_$iv = delayed;
            synchronized (this_$iv) {
                DelayedTask firstTask = this_$iv.firstImpl();
                if (eventLoop.isCompleted()) {
                    return 1;
                }
                if (firstTask == null) {
                    delayed.timeNow = now;
                } else {
                    long firstTime = firstTask.nanoTime;
                    long minTime = firstTime - now >= 0 ? now : firstTime;
                    if (minTime - delayed.timeNow > 0) {
                        delayed.timeNow = minTime;
                    }
                }
                if (this.nanoTime - delayed.timeNow < 0) {
                    this.nanoTime = delayed.timeNow;
                }
                if (1 != 0) {
                    this_$iv.addImpl(this);
                    z = true;
                } else {
                    z = false;
                }
                return 0;
            }
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public final synchronized void dispose() {
            Object heap = this._heap;
            if (heap == EventLoop_commonKt.DISPOSED_TASK) {
                return;
            }
            DelayedTaskQueue delayedTaskQueue = heap instanceof DelayedTaskQueue ? (DelayedTaskQueue) heap : null;
            if (delayedTaskQueue != null) {
                delayedTaskQueue.remove(this);
            }
            this._heap = EventLoop_commonKt.DISPOSED_TASK;
        }

        @NotNull
        public String toString() {
            return "Delayed[nanos=" + this.nanoTime + ']';
        }
    }

    /* compiled from: EventLoop.common.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\b\u0082\u0004\u0018��2\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0006H\u0016J\b\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u000b"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedResumeTask;", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "nanoTime", "", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "(Lkotlinx/coroutines/EventLoopImplBase;JLkotlinx/coroutines/CancellableContinuation;)V", "run", "toString", "", "kotlinx-coroutines-core"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/EventLoopImplBase$DelayedResumeTask.class */
    private final class DelayedResumeTask extends DelayedTask {

        @NotNull
        private final CancellableContinuation<Unit> cont;

        /* JADX WARN: Multi-variable type inference failed */
        public DelayedResumeTask(long nanoTime, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
            super(nanoTime);
            this.cont = cancellableContinuation;
        }

        @Override // java.lang.Runnable
        public void run() {
            CancellableContinuation $this$run_u24lambda_u2d0 = this.cont;
            $this$run_u24lambda_u2d0.resumeUndispatched(EventLoopImplBase.this, Unit.INSTANCE);
        }

        @Override // kotlinx.coroutines.EventLoopImplBase.DelayedTask
        @NotNull
        public String toString() {
            return Intrinsics.stringPlus(super.toString(), this.cont);
        }
    }

    /* compiled from: EventLoop.common.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0010\u000e\n��\b\u0002\u0018��2\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016R\u0012\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0082\u0004¢\u0006\u0002\n��¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedRunnableTask;", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "nanoTime", "", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "(JLjava/lang/Runnable;)V", "run", "", "toString", "", "kotlinx-coroutines-core"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/EventLoopImplBase$DelayedRunnableTask.class */
    private static final class DelayedRunnableTask extends DelayedTask {

        @NotNull
        private final Runnable block;

        public DelayedRunnableTask(long nanoTime, @NotNull Runnable block) {
            super(nanoTime);
            this.block = block;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.block.run();
        }

        @Override // kotlinx.coroutines.EventLoopImplBase.DelayedTask
        @NotNull
        public String toString() {
            return Intrinsics.stringPlus(super.toString(), this.block);
        }
    }

    /* compiled from: EventLoop.common.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0002\b��\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0012\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedTaskQueue;", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "timeNow", "", "(J)V", "kotlinx-coroutines-core"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/EventLoopImplBase$DelayedTaskQueue.class */
    public static final class DelayedTaskQueue extends ThreadSafeHeap<DelayedTask> {

        @JvmField
        public long timeNow;

        public DelayedTaskQueue(long timeNow) {
            this.timeNow = timeNow;
        }
    }
}
