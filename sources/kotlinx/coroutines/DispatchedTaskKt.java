package kotlinx.coroutines;

import io.legado.app.constant.Action;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.ThreadContextKt;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* compiled from: DispatchedTask.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��<\n��\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u001a \u0010\f\u001a\u00020\r\"\u0004\b��\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\u000f2\u0006\u0010\u0010\u001a\u00020\u0001H��\u001a.\u0010\u0011\u001a\u00020\r\"\u0004\b��\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\u000f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00132\u0006\u0010\u0014\u001a\u00020\tH��\u001a\u0010\u0010\u0015\u001a\u00020\r*\u0006\u0012\u0002\b\u00030\u000fH\u0002\u001a\u0019\u0010\u0016\u001a\u00020\r*\u0006\u0012\u0002\b\u00030\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0080\b\u001a'\u0010\u0019\u001a\u00020\r*\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\r0\u001dH\u0080\b\"\u000e\u0010��\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n��\"\u0016\u0010\u0002\u001a\u00020\u00018��X\u0081T¢\u0006\b\n��\u0012\u0004\b\u0003\u0010\u0004\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n��\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n��\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n��\"\u0018\u0010\b\u001a\u00020\t*\u00020\u00018@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\n\"\u0018\u0010\u000b\u001a\u00020\t*\u00020\u00018@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\n¨\u0006\u001e"}, d2 = {"MODE_ATOMIC", "", "MODE_CANCELLABLE", "getMODE_CANCELLABLE$annotations", "()V", "MODE_CANCELLABLE_REUSABLE", "MODE_UNDISPATCHED", "MODE_UNINITIALIZED", "isCancellableMode", "", "(I)Z", "isReusableMode", "dispatch", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/DispatchedTask;", "mode", Action.resume, "delegate", "Lkotlin/coroutines/Continuation;", "undispatched", "resumeUnconfined", "resumeWithStackTrace", "exception", "", "runUnconfinedEventLoop", "eventLoop", "Lkotlinx/coroutines/EventLoop;", "block", "Lkotlin/Function0;", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/DispatchedTaskKt.class */
public final class DispatchedTaskKt {
    public static final int MODE_ATOMIC = 0;
    public static final int MODE_CANCELLABLE = 1;
    public static final int MODE_CANCELLABLE_REUSABLE = 2;
    public static final int MODE_UNDISPATCHED = 4;
    public static final int MODE_UNINITIALIZED = -1;

    @PublishedApi
    public static /* synthetic */ void getMODE_CANCELLABLE$annotations() {
    }

    public static final boolean isCancellableMode(int $this$isCancellableMode) {
        return $this$isCancellableMode == 1 || $this$isCancellableMode == 2;
    }

    public static final boolean isReusableMode(int $this$isReusableMode) {
        return $this$isReusableMode == 2;
    }

    public static final <T> void dispatch(@NotNull DispatchedTask<? super T> dispatchedTask, int mode) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(mode != -1)) {
                throw new AssertionError();
            }
        }
        Continuation delegate = dispatchedTask.getDelegate$kotlinx_coroutines_core();
        boolean undispatched = mode == 4;
        if (!undispatched && (delegate instanceof DispatchedContinuation) && isCancellableMode(mode) == isCancellableMode(dispatchedTask.resumeMode)) {
            CoroutineDispatcher dispatcher = ((DispatchedContinuation) delegate).dispatcher;
            CoroutineContext context = delegate.getContext();
            if (dispatcher.isDispatchNeeded(context)) {
                dispatcher.mo4326dispatch(context, dispatchedTask);
                return;
            } else {
                resumeUnconfined(dispatchedTask);
                return;
            }
        }
        resume(dispatchedTask, delegate, undispatched);
    }

    public static final <T> void resume(@NotNull DispatchedTask<? super T> dispatchedTask, @NotNull Continuation<? super T> continuation, boolean undispatched) {
        Object objM2105constructorimpl;
        UndispatchedCoroutine undispatchedCoroutineUpdateUndispatchedCompletion;
        Object state = dispatchedTask.takeState$kotlinx_coroutines_core();
        Throwable exception = dispatchedTask.getExceptionalResult$kotlinx_coroutines_core(state);
        if (exception != null) {
            Result.Companion companion = Result.Companion;
            objM2105constructorimpl = Result.m2105constructorimpl(ResultKt.createFailure(exception));
        } else {
            Result.Companion companion2 = Result.Companion;
            objM2105constructorimpl = Result.m2105constructorimpl(dispatchedTask.getSuccessfulResult$kotlinx_coroutines_core(state));
        }
        Object result = objM2105constructorimpl;
        if (!undispatched) {
            continuation.resumeWith(result);
            return;
        }
        DispatchedContinuation this_$iv = (DispatchedContinuation) continuation;
        Continuation continuation$iv$iv = this_$iv.continuation;
        Object countOrElement$iv$iv = this_$iv.countOrElement;
        CoroutineContext context$iv$iv = continuation$iv$iv.getContext();
        Object oldValue$iv$iv = ThreadContextKt.updateThreadContext(context$iv$iv, countOrElement$iv$iv);
        if (oldValue$iv$iv != ThreadContextKt.NO_THREAD_ELEMENTS) {
            undispatchedCoroutineUpdateUndispatchedCompletion = CoroutineContextKt.updateUndispatchedCompletion(continuation$iv$iv, context$iv$iv, oldValue$iv$iv);
        } else {
            undispatchedCoroutineUpdateUndispatchedCompletion = (UndispatchedCoroutine) null;
        }
        UndispatchedCoroutine undispatchedCompletion$iv$iv = undispatchedCoroutineUpdateUndispatchedCompletion;
        try {
            this_$iv.continuation.resumeWith(result);
            Unit unit = Unit.INSTANCE;
            if (undispatchedCompletion$iv$iv != null && !undispatchedCompletion$iv$iv.clearThreadContext()) {
                return;
            }
            ThreadContextKt.restoreThreadContext(context$iv$iv, oldValue$iv$iv);
        } catch (Throwable th) {
            if (undispatchedCompletion$iv$iv == null || undispatchedCompletion$iv$iv.clearThreadContext()) {
                ThreadContextKt.restoreThreadContext(context$iv$iv, oldValue$iv$iv);
            }
            throw th;
        }
    }

    private static final void resumeUnconfined(DispatchedTask<?> dispatchedTask) {
        EventLoop eventLoop = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if (eventLoop.isUnconfinedLoopActive()) {
            eventLoop.dispatchUnconfined(dispatchedTask);
            return;
        }
        eventLoop.incrementUseCount(true);
        try {
            try {
                resume(dispatchedTask, dispatchedTask.getDelegate$kotlinx_coroutines_core(), true);
                do {
                } while (eventLoop.processUnconfinedEvent());
                eventLoop.decrementUseCount(true);
            } catch (Throwable e$iv) {
                dispatchedTask.handleFatalException(e$iv, null);
                eventLoop.decrementUseCount(true);
            }
        } catch (Throwable th) {
            eventLoop.decrementUseCount(true);
            throw th;
        }
    }

    /* JADX WARN: Finally extract failed */
    public static final void runUnconfinedEventLoop(@NotNull DispatchedTask<?> dispatchedTask, @NotNull EventLoop eventLoop, @NotNull Function0<Unit> function0) {
        eventLoop.incrementUseCount(true);
        try {
            try {
                function0.invoke();
                do {
                } while (eventLoop.processUnconfinedEvent());
                InlineMarker.finallyStart(1);
                eventLoop.decrementUseCount(true);
                InlineMarker.finallyEnd(1);
            } catch (Throwable e) {
                dispatchedTask.handleFatalException(e, null);
                InlineMarker.finallyStart(1);
                eventLoop.decrementUseCount(true);
                InlineMarker.finallyEnd(1);
            }
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            eventLoop.decrementUseCount(true);
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    public static final void resumeWithStackTrace(@NotNull Continuation<?> continuation, @NotNull Throwable exception) {
        Result.Companion companion = Result.Companion;
        continuation.resumeWith(Result.m2105constructorimpl(ResultKt.createFailure((DebugKt.getRECOVER_STACK_TRACES() && (continuation instanceof CoroutineStackFrame)) ? StackTraceRecoveryKt.recoverFromStackFrame(exception, (CoroutineStackFrame) continuation) : exception)));
    }
}
