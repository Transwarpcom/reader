package kotlinx.coroutines.test;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.joran.util.beans.BeanUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TestCoroutineContext.kt */
@Deprecated(message = "This API has been deprecated to integrate with Structured Concurrency.", replaceWith = @ReplaceWith(expression = "TestCoroutineScope", imports = {"kotlin.coroutines.test"}), level = DeprecationLevel.WARNING)
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018��2\u00020\u0001:\u0001<B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u0019J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u0019J$\u0010\u001d\u001a\u00020\u001b2\b\b\u0002\u0010\u001e\u001a\u00020\u00032\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020!0 J$\u0010\"\u001a\u00020\u001b2\b\b\u0002\u0010\u001e\u001a\u00020\u00032\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020!0 J*\u0010#\u001a\u00020\u001b2\b\b\u0002\u0010\u001e\u001a\u00020\u00032\u0018\u0010\u001f\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0004\u0012\u00020!0 J$\u0010$\u001a\u00020\u001b2\b\b\u0002\u0010\u001e\u001a\u00020\u00032\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020!0 J\u0006\u0010%\u001a\u00020\u001bJ\u0014\u0010&\u001a\u00020\u001b2\n\u0010'\u001a\u00060(j\u0002`)H\u0002J5\u0010*\u001a\u0002H+\"\u0004\b��\u0010+2\u0006\u0010,\u001a\u0002H+2\u0018\u0010-\u001a\u0014\u0012\u0004\u0012\u0002H+\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u0002H+0.H\u0016¢\u0006\u0002\u00100J(\u00101\u001a\u0004\u0018\u0001H2\"\b\b��\u00102*\u00020/2\f\u00103\u001a\b\u0012\u0004\u0012\u0002H204H\u0096\u0002¢\u0006\u0002\u00105J\u0014\u00106\u001a\u00020\u00012\n\u00103\u001a\u0006\u0012\u0002\b\u000304H\u0016J\u0010\u00107\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u0019J\u001c\u00108\u001a\u00020\u00122\n\u0010'\u001a\u00060(j\u0002`)2\u0006\u0010\u0017\u001a\u00020\u0006H\u0002J\b\u00109\u001a\u00020\u0006H\u0002J\b\u0010:\u001a\u00020\u0003H\u0016J\u0006\u0010;\u001a\u00020\u001bJ\u0010\u0010;\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0006H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n��R\u0012\u0010\u0007\u001a\u00060\bR\u00020��X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n��R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0013\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n��R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u0015X\u0082\u0004¢\u0006\u0002\n��¨\u0006="}, d2 = {"Lkotlinx/coroutines/test/TestCoroutineContext;", "Lkotlin/coroutines/CoroutineContext;", "name", "", "(Ljava/lang/String;)V", "counter", "", "ctxDispatcher", "Lkotlinx/coroutines/test/TestCoroutineContext$Dispatcher;", "ctxHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "exceptions", "", "", "getExceptions", "()Ljava/util/List;", "queue", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "Lkotlinx/coroutines/test/TimedRunnableObsolete;", "time", "uncaughtExceptions", "", "advanceTimeBy", "delayTime", "unit", "Ljava/util/concurrent/TimeUnit;", "advanceTimeTo", "", "targetTime", "assertAllUnhandledExceptions", "message", "predicate", "Lkotlin/Function1;", "", "assertAnyUnhandledException", "assertExceptions", "assertUnhandledException", "cancelAllActions", "enqueue", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "Lkotlin/coroutines/CoroutineContext$Element;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", BeanUtil.PREFIX_GETTER_GET, "E", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "minusKey", "now", "postDelayed", "processNextEvent", "toString", "triggerActions", "Dispatcher", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/test/TestCoroutineContext.class */
public final class TestCoroutineContext implements CoroutineContext {

    @Nullable
    private final String name;

    @NotNull
    private final List<Throwable> uncaughtExceptions;

    @NotNull
    private final Dispatcher ctxDispatcher;

    @NotNull
    private final CoroutineExceptionHandler ctxHandler;

    @NotNull
    private final ThreadSafeHeap<TimedRunnableObsolete> queue;
    private long counter;
    private long time;

    public TestCoroutineContext() {
        this(null, 1, null);
    }

    public TestCoroutineContext(@Nullable String name) {
        this.name = name;
        this.uncaughtExceptions = new ArrayList();
        this.ctxDispatcher = new Dispatcher();
        this.ctxHandler = new TestCoroutineContext$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.Key, this);
        this.queue = new ThreadSafeHeap<>();
    }

    @Override // kotlin.coroutines.CoroutineContext
    @NotNull
    public CoroutineContext plus(@NotNull CoroutineContext context) {
        return CoroutineContext.DefaultImpls.plus(this, context);
    }

    public /* synthetic */ TestCoroutineContext(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str);
    }

    @NotNull
    public final List<Throwable> getExceptions() {
        return this.uncaughtExceptions;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public <R> R fold(R r, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        return function2.invoke(function2.invoke(r, this.ctxDispatcher), this.ctxHandler);
    }

    @Override // kotlin.coroutines.CoroutineContext
    @Nullable
    public <E extends CoroutineContext.Element> E get(@NotNull CoroutineContext.Key<E> key) {
        if (key == ContinuationInterceptor.Key) {
            return this.ctxDispatcher;
        }
        if (key == CoroutineExceptionHandler.Key) {
            return this.ctxHandler;
        }
        return null;
    }

    @Override // kotlin.coroutines.CoroutineContext
    @NotNull
    public CoroutineContext minusKey(@NotNull CoroutineContext.Key<?> key) {
        return key == ContinuationInterceptor.Key ? this.ctxHandler : key == CoroutineExceptionHandler.Key ? this.ctxDispatcher : this;
    }

    public static /* synthetic */ long now$default(TestCoroutineContext testCoroutineContext, TimeUnit timeUnit, int i, Object obj) {
        if ((i & 1) != 0) {
            timeUnit = TimeUnit.MILLISECONDS;
        }
        return testCoroutineContext.now(timeUnit);
    }

    public final long now(@NotNull TimeUnit unit) {
        return unit.convert(this.time, TimeUnit.NANOSECONDS);
    }

    public static /* synthetic */ long advanceTimeBy$default(TestCoroutineContext testCoroutineContext, long j, TimeUnit timeUnit, int i, Object obj) {
        if ((i & 2) != 0) {
            timeUnit = TimeUnit.MILLISECONDS;
        }
        return testCoroutineContext.advanceTimeBy(j, timeUnit);
    }

    public final long advanceTimeBy(long delayTime, @NotNull TimeUnit unit) {
        long oldTime = this.time;
        advanceTimeTo(oldTime + unit.toNanos(delayTime), TimeUnit.NANOSECONDS);
        return unit.convert(this.time - oldTime, TimeUnit.NANOSECONDS);
    }

    public static /* synthetic */ void advanceTimeTo$default(TestCoroutineContext testCoroutineContext, long j, TimeUnit timeUnit, int i, Object obj) {
        if ((i & 2) != 0) {
            timeUnit = TimeUnit.MILLISECONDS;
        }
        testCoroutineContext.advanceTimeTo(j, timeUnit);
    }

    public final void advanceTimeTo(long targetTime, @NotNull TimeUnit unit) {
        long nanoTime = unit.toNanos(targetTime);
        triggerActions(nanoTime);
        if (nanoTime > this.time) {
            this.time = nanoTime;
        }
    }

    public final void triggerActions() {
        triggerActions(this.time);
    }

    public final void cancelAllActions() {
        if (!this.queue.isEmpty()) {
            this.queue.clear();
        }
    }

    public static /* synthetic */ void assertUnhandledException$default(TestCoroutineContext testCoroutineContext, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        testCoroutineContext.assertUnhandledException(str, function1);
    }

    public final void assertUnhandledException(@NotNull String message, @NotNull Function1<? super Throwable, Boolean> function1) {
        if (this.uncaughtExceptions.size() != 1 || !function1.invoke(this.uncaughtExceptions.get(0)).booleanValue()) {
            throw new AssertionError(message);
        }
        this.uncaughtExceptions.clear();
    }

    public static /* synthetic */ void assertAllUnhandledExceptions$default(TestCoroutineContext testCoroutineContext, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        testCoroutineContext.assertAllUnhandledExceptions(str, function1);
    }

    public final void assertAllUnhandledExceptions(@NotNull String message, @NotNull Function1<? super Throwable, Boolean> function1) {
        boolean z;
        Iterable $this$all$iv = this.uncaughtExceptions;
        if (!($this$all$iv instanceof Collection) || !((Collection) $this$all$iv).isEmpty()) {
            Iterator it = $this$all$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = true;
                    break;
                }
                Object element$iv = it.next();
                if (!function1.invoke(element$iv).booleanValue()) {
                    z = false;
                    break;
                }
            }
        } else {
            z = true;
        }
        if (!z) {
            throw new AssertionError(message);
        }
        this.uncaughtExceptions.clear();
    }

    public static /* synthetic */ void assertAnyUnhandledException$default(TestCoroutineContext testCoroutineContext, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        testCoroutineContext.assertAnyUnhandledException(str, function1);
    }

    public final void assertAnyUnhandledException(@NotNull String message, @NotNull Function1<? super Throwable, Boolean> function1) {
        boolean z;
        Iterable $this$any$iv = this.uncaughtExceptions;
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                Object element$iv = it.next();
                if (function1.invoke(element$iv).booleanValue()) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
        }
        if (!z) {
            throw new AssertionError(message);
        }
        this.uncaughtExceptions.clear();
    }

    public static /* synthetic */ void assertExceptions$default(TestCoroutineContext testCoroutineContext, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        testCoroutineContext.assertExceptions(str, function1);
    }

    public final void assertExceptions(@NotNull String message, @NotNull Function1<? super List<? extends Throwable>, Boolean> function1) {
        if (!function1.invoke(this.uncaughtExceptions).booleanValue()) {
            throw new AssertionError(message);
        }
        this.uncaughtExceptions.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void enqueue(Runnable block) {
        ThreadSafeHeap<TimedRunnableObsolete> threadSafeHeap = this.queue;
        long j = this.counter;
        this.counter = j + 1;
        threadSafeHeap.addLast(new TimedRunnableObsolete(block, j, 0L, 4, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TimedRunnableObsolete postDelayed(Runnable block, long delayTime) {
        long j = this.counter;
        this.counter = j + 1;
        TimedRunnableObsolete it = new TimedRunnableObsolete(block, j, this.time + TimeUnit.MILLISECONDS.toNanos(delayTime));
        this.queue.addLast(it);
        return it;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long processNextEvent() {
        TimedRunnableObsolete current = (TimedRunnableObsolete) this.queue.peek();
        if (current != null) {
            triggerActions(current.time);
        }
        return this.queue.isEmpty() ? Long.MAX_VALUE : 0L;
    }

    private final void triggerActions(long targetTime) {
        ThreadSafeHeapNode threadSafeHeapNodeRemoveAtImpl;
        ThreadSafeHeapNode threadSafeHeapNode;
        while (true) {
            ThreadSafeHeap this_$iv = this.queue;
            synchronized (this_$iv) {
                ThreadSafeHeapNode first$iv = this_$iv.firstImpl();
                if (first$iv == null) {
                    threadSafeHeapNode = null;
                } else {
                    TimedRunnableObsolete it = (TimedRunnableObsolete) first$iv;
                    if (it.time <= targetTime) {
                        threadSafeHeapNodeRemoveAtImpl = this_$iv.removeAtImpl(0);
                    } else {
                        threadSafeHeapNodeRemoveAtImpl = (ThreadSafeHeapNode) null;
                    }
                    threadSafeHeapNode = threadSafeHeapNodeRemoveAtImpl;
                }
            }
            TimedRunnableObsolete current = (TimedRunnableObsolete) threadSafeHeapNode;
            if (current != null) {
                if (current.time != 0) {
                    this.time = current.time;
                }
                current.run();
            } else {
                return;
            }
        }
    }

    @NotNull
    public String toString() {
        String str = this.name;
        return str == null ? Intrinsics.stringPlus("TestCoroutineContext@", DebugStringsKt.getHexAddress(this)) : str;
    }

    /* compiled from: TestCoroutineContext.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u000e\n��\b\u0082\u0004\u0018��2\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u00060\tj\u0002`\nH\u0016J$\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\n\u0010\b\u001a\u00060\tj\u0002`\n2\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\u001e\u0010\u0010\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016¨\u0006\u0017"}, d2 = {"Lkotlinx/coroutines/test/TestCoroutineContext$Dispatcher;", "Lkotlinx/coroutines/EventLoop;", "Lkotlinx/coroutines/Delay;", "(Lkotlinx/coroutines/test/TestCoroutineContext;)V", "dispatch", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "", "processNextEvent", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "shouldBeProcessedFromContext", "", "toString", "", "kotlinx-coroutines-core"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/test/TestCoroutineContext$Dispatcher.class */
    private final class Dispatcher extends EventLoop implements Delay {
        public Dispatcher() {
            EventLoop.incrementUseCount$default(this, false, 1, null);
        }

        @Override // kotlinx.coroutines.Delay
        @Nullable
        public Object delay(long time, @NotNull Continuation<? super Unit> continuation) {
            return Delay.DefaultImpls.delay(this, time, continuation);
        }

        @Override // kotlinx.coroutines.CoroutineDispatcher
        /* renamed from: dispatch */
        public void mo4326dispatch(@NotNull CoroutineContext context, @NotNull Runnable block) {
            TestCoroutineContext.this.enqueue(block);
        }

        @Override // kotlinx.coroutines.EventLoop
        public boolean shouldBeProcessedFromContext() {
            return true;
        }

        @Override // kotlinx.coroutines.Delay
        /* renamed from: scheduleResumeAfterDelay */
        public void mo4327scheduleResumeAfterDelay(long timeMillis, @NotNull final CancellableContinuation<? super Unit> cancellableContinuation) {
            TestCoroutineContext.this.postDelayed(new Runnable() { // from class: kotlinx.coroutines.test.TestCoroutineContext$Dispatcher$scheduleResumeAfterDelay$$inlined$Runnable$1
                @Override // java.lang.Runnable
                public final void run() {
                    CancellableContinuation $this$scheduleResumeAfterDelay_u24lambda_u2d1_u24lambda_u2d0 = cancellableContinuation;
                    $this$scheduleResumeAfterDelay_u24lambda_u2d1_u24lambda_u2d0.resumeUndispatched(this, Unit.INSTANCE);
                }
            }, timeMillis);
        }

        @Override // kotlinx.coroutines.Delay
        @NotNull
        public DisposableHandle invokeOnTimeout(long timeMillis, @NotNull Runnable block, @NotNull CoroutineContext context) {
            final TimedRunnableObsolete node = TestCoroutineContext.this.postDelayed(block, timeMillis);
            final TestCoroutineContext testCoroutineContext = TestCoroutineContext.this;
            return new DisposableHandle() { // from class: kotlinx.coroutines.test.TestCoroutineContext$Dispatcher$invokeOnTimeout$1
                @Override // kotlinx.coroutines.DisposableHandle
                public void dispose() {
                    testCoroutineContext.queue.remove(node);
                }
            };
        }

        @Override // kotlinx.coroutines.EventLoop
        public long processNextEvent() {
            return TestCoroutineContext.this.processNextEvent();
        }

        @Override // kotlinx.coroutines.CoroutineDispatcher
        @NotNull
        public String toString() {
            return "Dispatcher(" + TestCoroutineContext.this + ')';
        }
    }
}
