package kotlinx.coroutines.internal;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.CompletionStateKt;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadLocalEventLoop;
import kotlinx.coroutines.UndispatchedCoroutine;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cache.interceptor.CacheOperationExpressionEvaluator;

/* compiled from: DispatchedContinuation.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��J\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a;\u0010\u0006\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00072\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0082\b\u001aU\u0010\u0011\u001a\u00020\u0010\"\u0004\b��\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00152%\b\u0002\u0010\u0016\u001a\u001f\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0017H\u0007ø\u0001��¢\u0006\u0002\u0010\u001c\u001a\u0012\u0010\u001d\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00100\bH��\"\u0016\u0010��\u001a\u00020\u00018��X\u0081\u0004¢\u0006\b\n��\u0012\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0004\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n��\u0012\u0004\b\u0005\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"REUSABLE_CLAIMED", "Lkotlinx/coroutines/internal/Symbol;", "getREUSABLE_CLAIMED$annotations", "()V", "UNDEFINED", "getUNDEFINED$annotations", "executeUnconfined", "", "Lkotlinx/coroutines/internal/DispatchedContinuation;", "contState", "", "mode", "", "doYield", "block", "Lkotlin/Function0;", "", "resumeCancellableWith", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlin/coroutines/Continuation;", CacheOperationExpressionEvaluator.RESULT_VARIABLE, "Lkotlin/Result;", "onCancellation", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "yieldUndispatched", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/internal/DispatchedContinuationKt.class */
public final class DispatchedContinuationKt {

    @NotNull
    private static final Symbol UNDEFINED = new Symbol("UNDEFINED");

    @JvmField
    @NotNull
    public static final Symbol REUSABLE_CLAIMED = new Symbol("REUSABLE_CLAIMED");

    private static /* synthetic */ void getUNDEFINED$annotations() {
    }

    public static /* synthetic */ void getREUSABLE_CLAIMED$annotations() {
    }

    public static /* synthetic */ void resumeCancellableWith$default(Continuation continuation, Object obj, Function1 function1, int i, Object obj2) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        resumeCancellableWith(continuation, obj, function1);
    }

    @InternalCoroutinesApi
    public static final <T> void resumeCancellableWith(@NotNull Continuation<? super T> continuation, @NotNull Object result, @Nullable Function1<? super Throwable, Unit> function1) {
        boolean z;
        UndispatchedCoroutine undispatchedCoroutineUpdateUndispatchedCompletion;
        if (!(continuation instanceof DispatchedContinuation)) {
            continuation.resumeWith(result);
            return;
        }
        DispatchedContinuation this_$iv = (DispatchedContinuation) continuation;
        Object state$iv = CompletionStateKt.toState(result, function1);
        if (this_$iv.dispatcher.isDispatchNeeded(this_$iv.getContext())) {
            this_$iv._state = state$iv;
            this_$iv.resumeMode = 1;
            this_$iv.dispatcher.mo4326dispatch(this_$iv.getContext(), this_$iv);
            return;
        }
        if (DebugKt.getASSERTIONS_ENABLED() && 1 == 0) {
            throw new AssertionError();
        }
        EventLoop eventLoop$iv$iv = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if (eventLoop$iv$iv.isUnconfinedLoopActive()) {
            this_$iv._state = state$iv;
            this_$iv.resumeMode = 1;
            eventLoop$iv$iv.dispatchUnconfined(this_$iv);
            return;
        }
        DispatchedContinuation $this$runUnconfinedEventLoop$iv$iv$iv = this_$iv;
        eventLoop$iv$iv.incrementUseCount(true);
        try {
            try {
                Job job$iv$iv = (Job) this_$iv.getContext().get(Job.Key);
                if (job$iv$iv != null && !job$iv$iv.isActive()) {
                    CancellationException cause$iv$iv = job$iv$iv.getCancellationException();
                    this_$iv.cancelCompletedResult$kotlinx_coroutines_core(state$iv, cause$iv$iv);
                    Result.Companion companion = Result.Companion;
                    this_$iv.resumeWith(Result.m2105constructorimpl(ResultKt.createFailure(cause$iv$iv)));
                    z = true;
                } else {
                    z = false;
                }
                if (!z) {
                    Continuation continuation$iv$iv$iv = this_$iv.continuation;
                    Object countOrElement$iv$iv$iv = this_$iv.countOrElement;
                    CoroutineContext context$iv$iv$iv = continuation$iv$iv$iv.getContext();
                    Object oldValue$iv$iv$iv = ThreadContextKt.updateThreadContext(context$iv$iv$iv, countOrElement$iv$iv$iv);
                    if (oldValue$iv$iv$iv != ThreadContextKt.NO_THREAD_ELEMENTS) {
                        undispatchedCoroutineUpdateUndispatchedCompletion = CoroutineContextKt.updateUndispatchedCompletion(continuation$iv$iv$iv, context$iv$iv$iv, oldValue$iv$iv$iv);
                    } else {
                        undispatchedCoroutineUpdateUndispatchedCompletion = (UndispatchedCoroutine) null;
                    }
                    UndispatchedCoroutine undispatchedCompletion$iv$iv$iv = undispatchedCoroutineUpdateUndispatchedCompletion;
                    try {
                        this_$iv.continuation.resumeWith(result);
                        Unit unit = Unit.INSTANCE;
                        if (undispatchedCompletion$iv$iv$iv == null || undispatchedCompletion$iv$iv$iv.clearThreadContext()) {
                            ThreadContextKt.restoreThreadContext(context$iv$iv$iv, oldValue$iv$iv$iv);
                        }
                    } catch (Throwable th) {
                        if (undispatchedCompletion$iv$iv$iv == null || undispatchedCompletion$iv$iv$iv.clearThreadContext()) {
                            ThreadContextKt.restoreThreadContext(context$iv$iv$iv, oldValue$iv$iv$iv);
                        }
                        throw th;
                    }
                }
                while (eventLoop$iv$iv.processUnconfinedEvent()) {
                }
                eventLoop$iv$iv.decrementUseCount(true);
            } catch (Throwable e$iv$iv$iv) {
                $this$runUnconfinedEventLoop$iv$iv$iv.handleFatalException(e$iv$iv$iv, null);
                eventLoop$iv$iv.decrementUseCount(true);
            }
        } catch (Throwable th2) {
            eventLoop$iv$iv.decrementUseCount(true);
            throw th2;
        }
    }

    public static final boolean yieldUndispatched(@NotNull DispatchedContinuation<? super Unit> dispatchedContinuation) {
        Object contState$iv = Unit.INSTANCE;
        if (DebugKt.getASSERTIONS_ENABLED() && 1 == 0) {
            throw new AssertionError();
        }
        EventLoop eventLoop$iv = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if (eventLoop$iv.isUnconfinedQueueEmpty()) {
            return false;
        }
        if (eventLoop$iv.isUnconfinedLoopActive()) {
            dispatchedContinuation._state = contState$iv;
            dispatchedContinuation.resumeMode = 1;
            eventLoop$iv.dispatchUnconfined(dispatchedContinuation);
            return true;
        }
        DispatchedContinuation<? super Unit> $this$runUnconfinedEventLoop$iv$iv = dispatchedContinuation;
        eventLoop$iv.incrementUseCount(true);
        try {
            try {
                dispatchedContinuation.run();
                do {
                } while (eventLoop$iv.processUnconfinedEvent());
                eventLoop$iv.decrementUseCount(true);
            } catch (Throwable e$iv$iv) {
                $this$runUnconfinedEventLoop$iv$iv.handleFatalException(e$iv$iv, null);
                eventLoop$iv.decrementUseCount(true);
            }
            return false;
        } catch (Throwable th) {
            eventLoop$iv.decrementUseCount(true);
            throw th;
        }
    }

    static /* synthetic */ boolean executeUnconfined$default(DispatchedContinuation $this$executeUnconfined_u24default, Object contState, int mode, boolean doYield, Function0 block, int i, Object obj) {
        if ((i & 4) != 0) {
            doYield = false;
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(mode != -1)) {
                throw new AssertionError();
            }
        }
        EventLoop eventLoop = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if (doYield && eventLoop.isUnconfinedQueueEmpty()) {
            return false;
        }
        if (eventLoop.isUnconfinedLoopActive()) {
            $this$executeUnconfined_u24default._state = contState;
            $this$executeUnconfined_u24default.resumeMode = mode;
            eventLoop.dispatchUnconfined($this$executeUnconfined_u24default);
            return true;
        }
        DispatchedContinuation $this$runUnconfinedEventLoop$iv = $this$executeUnconfined_u24default;
        eventLoop.incrementUseCount(true);
        try {
            try {
                block.invoke();
                do {
                } while (eventLoop.processUnconfinedEvent());
                InlineMarker.finallyStart(1);
                eventLoop.decrementUseCount(true);
                InlineMarker.finallyEnd(1);
            } catch (Throwable e$iv) {
                $this$runUnconfinedEventLoop$iv.handleFatalException(e$iv, null);
                InlineMarker.finallyStart(1);
                eventLoop.decrementUseCount(true);
                InlineMarker.finallyEnd(1);
            }
            return false;
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            eventLoop.decrementUseCount(true);
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    /* JADX WARN: Finally extract failed */
    private static final boolean executeUnconfined(DispatchedContinuation<?> dispatchedContinuation, Object contState, int mode, boolean doYield, Function0<Unit> function0) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(mode != -1)) {
                throw new AssertionError();
            }
        }
        EventLoop eventLoop = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if (doYield && eventLoop.isUnconfinedQueueEmpty()) {
            return false;
        }
        if (eventLoop.isUnconfinedLoopActive()) {
            dispatchedContinuation._state = contState;
            dispatchedContinuation.resumeMode = mode;
            eventLoop.dispatchUnconfined(dispatchedContinuation);
            return true;
        }
        DispatchedContinuation<?> $this$runUnconfinedEventLoop$iv = dispatchedContinuation;
        eventLoop.incrementUseCount(true);
        try {
            try {
                function0.invoke();
                do {
                } while (eventLoop.processUnconfinedEvent());
                InlineMarker.finallyStart(1);
                eventLoop.decrementUseCount(true);
                InlineMarker.finallyEnd(1);
            } catch (Throwable e$iv) {
                $this$runUnconfinedEventLoop$iv.handleFatalException(e$iv, null);
                InlineMarker.finallyStart(1);
                eventLoop.decrementUseCount(true);
                InlineMarker.finallyEnd(1);
            }
            return false;
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            eventLoop.decrementUseCount(true);
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }
}
