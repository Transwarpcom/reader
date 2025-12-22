package kotlinx.coroutines.internal;

import ch.qos.logback.core.CoreConstants;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletedWithCancellation;
import kotlinx.coroutines.CompletionStateKt;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.DispatchedTask;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadLocalEventLoop;
import kotlinx.coroutines.UndispatchedCoroutine;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cache.interceptor.CacheOperationExpressionEvaluator;
import org.springframework.cglib.core.Constants;

/* compiled from: DispatchedContinuation.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0080\u0001\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\b��\u0018��*\u0006\b��\u0010\u0001 ��2\b\u0012\u0004\u0012\u00028��0O2\u00060?j\u0002`@2\b\u0012\u0004\u0012\u00028��0\u0004B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028��0\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\t\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ!\u0010\u0011\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0010¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0013\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00028��H��¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001d\u001a\n\u0018\u00010\u001bj\u0004\u0018\u0001`\u001cH\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\r\u0010 \u001a\u00020\u001f¢\u0006\u0004\b \u0010!J\u0015\u0010\"\u001a\u00020\u001f2\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\"\u0010#J\r\u0010$\u001a\u00020\b¢\u0006\u0004\b$\u0010\nJH\u0010+\u001a\u00020\b2\f\u0010&\u001a\b\u0012\u0004\u0012\u00028��0%2%\b\b\u0010*\u001a\u001f\u0012\u0013\u0012\u00110\r¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\b\u0018\u00010'H\u0086\bø\u0001��¢\u0006\u0004\b+\u0010,J\u001a\u0010.\u001a\u00020\u001f2\b\u0010-\u001a\u0004\u0018\u00010\u000bH\u0086\b¢\u0006\u0004\b.\u0010/J!\u00100\u001a\u00020\b2\f\u0010&\u001a\b\u0012\u0004\u0012\u00028��0%H\u0086\bø\u0001��¢\u0006\u0004\b0\u00101J \u00102\u001a\u00020\b2\f\u0010&\u001a\b\u0012\u0004\u0012\u00028��0%H\u0016ø\u0001��¢\u0006\u0004\b2\u00101J\u0011\u00105\u001a\u0004\u0018\u00010\u000bH\u0010¢\u0006\u0004\b3\u00104J\u000f\u00107\u001a\u000206H\u0016¢\u0006\u0004\b7\u00108J\u001b\u0010:\u001a\u0004\u0018\u00010\r2\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u000309¢\u0006\u0004\b:\u0010;R\u001e\u0010<\u001a\u0004\u0018\u00010\u000b8��@��X\u0081\u000e¢\u0006\f\n\u0004\b<\u0010=\u0012\u0004\b>\u0010\nR\u001c\u0010C\u001a\n\u0018\u00010?j\u0004\u0018\u0001`@8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bA\u0010BR\u0014\u0010\u0016\u001a\u00020\u00158\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\bD\u0010ER\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028��0\u00048\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010FR\u0014\u0010G\u001a\u00020\u000b8��X\u0081\u0004¢\u0006\u0006\n\u0004\bG\u0010=R\u001a\u0010J\u001a\b\u0012\u0004\u0012\u00028��0\u00048PX\u0090\u0004¢\u0006\u0006\u001a\u0004\bH\u0010IR\u0014\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010KR\u001a\u0010M\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bL\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006N"}, d2 = {"Lkotlinx/coroutines/internal/DispatchedContinuation;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/CoroutineDispatcher;", "dispatcher", "Lkotlin/coroutines/Continuation;", "continuation", Constants.CONSTRUCTOR_NAME, "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/coroutines/Continuation;)V", "", "awaitReusability", "()V", "", "takenState", "", "cause", "cancelCompletedResult$kotlinx_coroutines_core", "(Ljava/lang/Object;Ljava/lang/Throwable;)V", "cancelCompletedResult", "Lkotlinx/coroutines/CancellableContinuationImpl;", "claimReusableCancellableContinuation", "()Lkotlinx/coroutines/CancellableContinuationImpl;", "Lkotlin/coroutines/CoroutineContext;", CoreConstants.CONTEXT_SCOPE_VALUE, "value", "dispatchYield$kotlinx_coroutines_core", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;)V", "dispatchYield", "Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/internal/StackTraceElement;", "getStackTraceElement", "()Ljava/lang/StackTraceElement;", "", "isReusable", "()Z", "postponeCancellation", "(Ljava/lang/Throwable;)Z", "release", "Lkotlin/Result;", CacheOperationExpressionEvaluator.RESULT_VARIABLE, "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "onCancellation", "resumeCancellableWith", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "state", "resumeCancelled", "(Ljava/lang/Object;)Z", "resumeUndispatchedWith", "(Ljava/lang/Object;)V", "resumeWith", "takeState$kotlinx_coroutines_core", "()Ljava/lang/Object;", "takeState", "", "toString", "()Ljava/lang/String;", "Lkotlinx/coroutines/CancellableContinuation;", "tryReleaseClaimedContinuation", "(Lkotlinx/coroutines/CancellableContinuation;)Ljava/lang/Throwable;", "_state", "Ljava/lang/Object;", "get_state$kotlinx_coroutines_core$annotations", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "callerFrame", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "Lkotlin/coroutines/Continuation;", "countOrElement", "getDelegate$kotlinx_coroutines_core", "()Lkotlin/coroutines/Continuation;", "delegate", "Lkotlinx/coroutines/CoroutineDispatcher;", "getReusableCancellableContinuation", "reusableCancellableContinuation", "kotlinx-coroutines-core", "Lkotlinx/coroutines/DispatchedTask;"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/internal/DispatchedContinuation.class */
public final class DispatchedContinuation<T> extends DispatchedTask<T> implements CoroutineStackFrame, Continuation<T> {

    @JvmField
    @NotNull
    public final CoroutineDispatcher dispatcher;

    @JvmField
    @NotNull
    public final Continuation<T> continuation;

    @JvmField
    @Nullable
    public Object _state;

    @JvmField
    @NotNull
    public final Object countOrElement;

    @NotNull
    private volatile /* synthetic */ Object _reusableCancellableContinuation;
    private static final /* synthetic */ AtomicReferenceFieldUpdater _reusableCancellableContinuation$FU = AtomicReferenceFieldUpdater.newUpdater(DispatchedContinuation.class, Object.class, "_reusableCancellableContinuation");

    @Override // kotlin.coroutines.Continuation
    @NotNull
    public CoroutineContext getContext() {
        return this.continuation.getContext();
    }

    public static /* synthetic */ void get_state$kotlinx_coroutines_core$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DispatchedContinuation(@NotNull CoroutineDispatcher dispatcher, @NotNull Continuation<? super T> continuation) {
        super(-1);
        this.dispatcher = dispatcher;
        this.continuation = continuation;
        this._state = DispatchedContinuationKt.UNDEFINED;
        this.countOrElement = ThreadContextKt.threadContextElements(getContext());
        this._reusableCancellableContinuation = null;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    @Nullable
    public CoroutineStackFrame getCallerFrame() {
        Continuation<T> continuation = this.continuation;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    @Nullable
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    private final CancellableContinuationImpl<?> getReusableCancellableContinuation() {
        Object obj = this._reusableCancellableContinuation;
        if (obj instanceof CancellableContinuationImpl) {
            return (CancellableContinuationImpl) obj;
        }
        return null;
    }

    public final boolean isReusable() {
        return this._reusableCancellableContinuation != null;
    }

    public final void awaitReusability() {
        Object it;
        do {
            it = this._reusableCancellableContinuation;
        } while (it == DispatchedContinuationKt.REUSABLE_CLAIMED);
    }

    public final void release() {
        awaitReusability();
        CancellableContinuationImpl<?> reusableCancellableContinuation = getReusableCancellableContinuation();
        if (reusableCancellableContinuation == null) {
            return;
        }
        reusableCancellableContinuation.detachChild$kotlinx_coroutines_core();
    }

    @Nullable
    public final CancellableContinuationImpl<T> claimReusableCancellableContinuation() {
        while (true) {
            Object state = this._reusableCancellableContinuation;
            if (state == null) {
                this._reusableCancellableContinuation = DispatchedContinuationKt.REUSABLE_CLAIMED;
                return null;
            }
            if (state instanceof CancellableContinuationImpl) {
                if (_reusableCancellableContinuation$FU.compareAndSet(this, state, DispatchedContinuationKt.REUSABLE_CLAIMED)) {
                    return (CancellableContinuationImpl) state;
                }
            } else if (state != DispatchedContinuationKt.REUSABLE_CLAIMED && !(state instanceof Throwable)) {
                throw new IllegalStateException(Intrinsics.stringPlus("Inconsistent state ", state).toString());
            }
        }
    }

    @Nullable
    public final Throwable tryReleaseClaimedContinuation(@NotNull CancellableContinuation<?> cancellableContinuation) {
        do {
            Object state = this._reusableCancellableContinuation;
            if (state != DispatchedContinuationKt.REUSABLE_CLAIMED) {
                if (state instanceof Throwable) {
                    if (_reusableCancellableContinuation$FU.compareAndSet(this, state, null)) {
                        return (Throwable) state;
                    }
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                throw new IllegalStateException(Intrinsics.stringPlus("Inconsistent state ", state).toString());
            }
        } while (!_reusableCancellableContinuation$FU.compareAndSet(this, DispatchedContinuationKt.REUSABLE_CLAIMED, cancellableContinuation));
        return null;
    }

    public final boolean postponeCancellation(@NotNull Throwable cause) {
        while (true) {
            Object state = this._reusableCancellableContinuation;
            if (Intrinsics.areEqual(state, DispatchedContinuationKt.REUSABLE_CLAIMED)) {
                if (_reusableCancellableContinuation$FU.compareAndSet(this, DispatchedContinuationKt.REUSABLE_CLAIMED, cause)) {
                    return true;
                }
            } else {
                if (state instanceof Throwable) {
                    return true;
                }
                if (_reusableCancellableContinuation$FU.compareAndSet(this, state, null)) {
                    return false;
                }
            }
        }
    }

    @Override // kotlinx.coroutines.DispatchedTask
    @Nullable
    public Object takeState$kotlinx_coroutines_core() {
        Object state = this._state;
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(state != DispatchedContinuationKt.UNDEFINED)) {
                throw new AssertionError();
            }
        }
        this._state = DispatchedContinuationKt.UNDEFINED;
        return state;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    @NotNull
    public Continuation<T> getDelegate$kotlinx_coroutines_core() {
        return this;
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(@NotNull Object result) {
        CoroutineContext context = this.continuation.getContext();
        Object state = CompletionStateKt.toState$default(result, null, 1, null);
        if (this.dispatcher.isDispatchNeeded(context)) {
            this._state = state;
            this.resumeMode = 0;
            this.dispatcher.mo4326dispatch(context, this);
            return;
        }
        if (DebugKt.getASSERTIONS_ENABLED() && 1 == 0) {
            throw new AssertionError();
        }
        EventLoop eventLoop$iv = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if (eventLoop$iv.isUnconfinedLoopActive()) {
            this._state = state;
            this.resumeMode = 0;
            eventLoop$iv.dispatchUnconfined(this);
            return;
        }
        DispatchedContinuation<T> $this$runUnconfinedEventLoop$iv$iv = this;
        eventLoop$iv.incrementUseCount(true);
        try {
            try {
                CoroutineContext context$iv = getContext();
                Object countOrElement$iv = this.countOrElement;
                Object oldValue$iv = ThreadContextKt.updateThreadContext(context$iv, countOrElement$iv);
                try {
                    this.continuation.resumeWith(result);
                    Unit unit = Unit.INSTANCE;
                    ThreadContextKt.restoreThreadContext(context$iv, oldValue$iv);
                    while (eventLoop$iv.processUnconfinedEvent()) {
                    }
                    eventLoop$iv.decrementUseCount(true);
                } catch (Throwable th) {
                    ThreadContextKt.restoreThreadContext(context$iv, oldValue$iv);
                    throw th;
                }
            } catch (Throwable e$iv$iv) {
                $this$runUnconfinedEventLoop$iv$iv.handleFatalException(e$iv$iv, null);
                eventLoop$iv.decrementUseCount(true);
            }
        } catch (Throwable th2) {
            eventLoop$iv.decrementUseCount(true);
            throw th2;
        }
    }

    /* JADX WARN: Finally extract failed */
    public final void resumeCancellableWith(@NotNull Object result, @Nullable Function1<? super Throwable, Unit> function1) {
        boolean z;
        UndispatchedCoroutine undispatchedCoroutineUpdateUndispatchedCompletion;
        Object state = CompletionStateKt.toState(result, function1);
        if (this.dispatcher.isDispatchNeeded(getContext())) {
            this._state = state;
            this.resumeMode = 1;
            this.dispatcher.mo4326dispatch(getContext(), this);
            return;
        }
        if (DebugKt.getASSERTIONS_ENABLED() && 1 == 0) {
            throw new AssertionError();
        }
        EventLoop eventLoop$iv = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if (eventLoop$iv.isUnconfinedLoopActive()) {
            this._state = state;
            this.resumeMode = 1;
            eventLoop$iv.dispatchUnconfined(this);
            return;
        }
        DispatchedContinuation<T> $this$runUnconfinedEventLoop$iv$iv = this;
        eventLoop$iv.incrementUseCount(true);
        try {
            try {
                Job job$iv = (Job) getContext().get(Job.Key);
                if (job$iv != null && !job$iv.isActive()) {
                    CancellationException cause$iv = job$iv.getCancellationException();
                    cancelCompletedResult$kotlinx_coroutines_core(state, cause$iv);
                    Result.Companion companion = Result.Companion;
                    resumeWith(Result.m2105constructorimpl(ResultKt.createFailure(cause$iv)));
                    z = true;
                } else {
                    z = false;
                }
                if (!z) {
                    Continuation continuation$iv$iv = this.continuation;
                    Object countOrElement$iv$iv = this.countOrElement;
                    CoroutineContext context$iv$iv = continuation$iv$iv.getContext();
                    Object oldValue$iv$iv = ThreadContextKt.updateThreadContext(context$iv$iv, countOrElement$iv$iv);
                    if (oldValue$iv$iv != ThreadContextKt.NO_THREAD_ELEMENTS) {
                        undispatchedCoroutineUpdateUndispatchedCompletion = CoroutineContextKt.updateUndispatchedCompletion(continuation$iv$iv, context$iv$iv, oldValue$iv$iv);
                    } else {
                        undispatchedCoroutineUpdateUndispatchedCompletion = (UndispatchedCoroutine) null;
                    }
                    UndispatchedCoroutine undispatchedCompletion$iv$iv = undispatchedCoroutineUpdateUndispatchedCompletion;
                    try {
                        this.continuation.resumeWith(result);
                        Unit unit = Unit.INSTANCE;
                        InlineMarker.finallyStart(1);
                        if (undispatchedCompletion$iv$iv == null || undispatchedCompletion$iv$iv.clearThreadContext()) {
                            ThreadContextKt.restoreThreadContext(context$iv$iv, oldValue$iv$iv);
                        }
                        InlineMarker.finallyEnd(1);
                    } catch (Throwable th) {
                        InlineMarker.finallyStart(1);
                        if (undispatchedCompletion$iv$iv == null || undispatchedCompletion$iv$iv.clearThreadContext()) {
                            ThreadContextKt.restoreThreadContext(context$iv$iv, oldValue$iv$iv);
                        }
                        InlineMarker.finallyEnd(1);
                        throw th;
                    }
                }
                while (eventLoop$iv.processUnconfinedEvent()) {
                }
                InlineMarker.finallyStart(1);
                eventLoop$iv.decrementUseCount(true);
                InlineMarker.finallyEnd(1);
            } catch (Throwable e$iv$iv) {
                $this$runUnconfinedEventLoop$iv$iv.handleFatalException(e$iv$iv, null);
                InlineMarker.finallyStart(1);
                eventLoop$iv.decrementUseCount(true);
                InlineMarker.finallyEnd(1);
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            eventLoop$iv.decrementUseCount(true);
            InlineMarker.finallyEnd(1);
            throw th2;
        }
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public void cancelCompletedResult$kotlinx_coroutines_core(@Nullable Object takenState, @NotNull Throwable cause) {
        if (takenState instanceof CompletedWithCancellation) {
            ((CompletedWithCancellation) takenState).onCancellation.invoke(cause);
        }
    }

    public final boolean resumeCancelled(@Nullable Object state) {
        Job job = (Job) getContext().get(Job.Key);
        if (job != null && !job.isActive()) {
            CancellationException cause = job.getCancellationException();
            cancelCompletedResult$kotlinx_coroutines_core(state, cause);
            Result.Companion companion = Result.Companion;
            resumeWith(Result.m2105constructorimpl(ResultKt.createFailure(cause)));
            return true;
        }
        return false;
    }

    public final void resumeUndispatchedWith(@NotNull Object result) {
        UndispatchedCoroutine undispatchedCoroutineUpdateUndispatchedCompletion;
        Continuation continuation$iv = this.continuation;
        Object countOrElement$iv = this.countOrElement;
        CoroutineContext context$iv = continuation$iv.getContext();
        Object oldValue$iv = ThreadContextKt.updateThreadContext(context$iv, countOrElement$iv);
        if (oldValue$iv != ThreadContextKt.NO_THREAD_ELEMENTS) {
            undispatchedCoroutineUpdateUndispatchedCompletion = CoroutineContextKt.updateUndispatchedCompletion(continuation$iv, context$iv, oldValue$iv);
        } else {
            undispatchedCoroutineUpdateUndispatchedCompletion = (UndispatchedCoroutine) null;
        }
        UndispatchedCoroutine undispatchedCompletion$iv = undispatchedCoroutineUpdateUndispatchedCompletion;
        try {
            this.continuation.resumeWith(result);
            Unit unit = Unit.INSTANCE;
            InlineMarker.finallyStart(1);
            if (undispatchedCompletion$iv == null || undispatchedCompletion$iv.clearThreadContext()) {
                ThreadContextKt.restoreThreadContext(context$iv, oldValue$iv);
            }
            InlineMarker.finallyEnd(1);
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            if (undispatchedCompletion$iv == null || undispatchedCompletion$iv.clearThreadContext()) {
                ThreadContextKt.restoreThreadContext(context$iv, oldValue$iv);
            }
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    public final void dispatchYield$kotlinx_coroutines_core(@NotNull CoroutineContext context, T t) {
        this._state = t;
        this.resumeMode = 1;
        this.dispatcher.dispatchYield(context, this);
    }

    @NotNull
    public String toString() {
        return "DispatchedContinuation[" + this.dispatcher + ", " + DebugStringsKt.toDebugString(this.continuation) + ']';
    }
}
