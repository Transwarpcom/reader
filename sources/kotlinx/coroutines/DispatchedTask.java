package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.scheduling.Task;
import kotlinx.coroutines.scheduling.TaskContext;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DispatchedTask.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0010\u0003\n\u0002\b\u000e\b \u0018��*\u0006\b��\u0010\u0001 ��2\u00060\u0002j\u0002`\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001f\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0010¢\u0006\u0002\b\u0011J\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u000eH\u0010¢\u0006\u0002\b\u0014J\u001f\u0010\u0015\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\b\u0010\u0013\u001a\u0004\u0018\u00010\u000eH\u0010¢\u0006\u0004\b\u0016\u0010\u0017J\u001a\u0010\u0018\u001a\u00020\f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u00102\b\u0010\u001a\u001a\u0004\u0018\u00010\u0010J\u0006\u0010\u001b\u001a\u00020\fJ\u000f\u0010\u001c\u001a\u0004\u0018\u00010\u000eH ¢\u0006\u0002\b\u001dR\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028��0\bX \u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n��¨\u0006\u001e"}, d2 = {"Lkotlinx/coroutines/DispatchedTask;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/scheduling/Task;", "Lkotlinx/coroutines/SchedulerTask;", "resumeMode", "", "(I)V", "delegate", "Lkotlin/coroutines/Continuation;", "getDelegate$kotlinx_coroutines_core", "()Lkotlin/coroutines/Continuation;", "cancelCompletedResult", "", "takenState", "", "cause", "", "cancelCompletedResult$kotlinx_coroutines_core", "getExceptionalResult", "state", "getExceptionalResult$kotlinx_coroutines_core", "getSuccessfulResult", "getSuccessfulResult$kotlinx_coroutines_core", "(Ljava/lang/Object;)Ljava/lang/Object;", "handleFatalException", "exception", "finallyException", "run", "takeState", "takeState$kotlinx_coroutines_core", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/DispatchedTask.class */
public abstract class DispatchedTask<T> extends Task {

    @JvmField
    public int resumeMode;

    @NotNull
    public abstract Continuation<T> getDelegate$kotlinx_coroutines_core();

    @Nullable
    public abstract Object takeState$kotlinx_coroutines_core();

    public DispatchedTask(int resumeMode) {
        this.resumeMode = resumeMode;
    }

    public void cancelCompletedResult$kotlinx_coroutines_core(@Nullable Object takenState, @NotNull Throwable cause) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T getSuccessfulResult$kotlinx_coroutines_core(@Nullable Object obj) {
        return obj;
    }

    @Nullable
    public Throwable getExceptionalResult$kotlinx_coroutines_core(@Nullable Object state) {
        CompletedExceptionally completedExceptionally = state instanceof CompletedExceptionally ? (CompletedExceptionally) state : null;
        if (completedExceptionally == null) {
            return null;
        }
        return completedExceptionally.cause;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object objM2105constructorimpl;
        Object objM2105constructorimpl2;
        Object objM2105constructorimpl3;
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(this.resumeMode != -1)) {
                throw new AssertionError();
            }
        }
        TaskContext taskContext = this.taskContext;
        try {
            DispatchedContinuation delegate = (DispatchedContinuation) getDelegate$kotlinx_coroutines_core();
            Continuation continuation = delegate.continuation;
            Object countOrElement$iv = delegate.countOrElement;
            CoroutineContext context$iv = continuation.getContext();
            Object oldValue$iv = ThreadContextKt.updateThreadContext(context$iv, countOrElement$iv);
            UndispatchedCoroutine undispatchedCompletion$iv = oldValue$iv != ThreadContextKt.NO_THREAD_ELEMENTS ? CoroutineContextKt.updateUndispatchedCompletion(continuation, context$iv, oldValue$iv) : (UndispatchedCoroutine) null;
            try {
                CoroutineContext context = continuation.getContext();
                Object state = takeState$kotlinx_coroutines_core();
                Throwable exception = getExceptionalResult$kotlinx_coroutines_core(state);
                Job job = (exception == null && DispatchedTaskKt.isCancellableMode(this.resumeMode)) ? (Job) context.get(Job.Key) : null;
                if (job != null && !job.isActive()) {
                    CancellationException cause = job.getCancellationException();
                    cancelCompletedResult$kotlinx_coroutines_core(state, cause);
                    Result.Companion companion = Result.Companion;
                    continuation.resumeWith(Result.m2105constructorimpl(ResultKt.createFailure((DebugKt.getRECOVER_STACK_TRACES() && (continuation instanceof CoroutineStackFrame)) ? StackTraceRecoveryKt.recoverFromStackFrame(cause, (CoroutineStackFrame) continuation) : cause)));
                } else if (exception != null) {
                    Result.Companion companion2 = Result.Companion;
                    continuation.resumeWith(Result.m2105constructorimpl(ResultKt.createFailure(exception)));
                } else {
                    T successfulResult$kotlinx_coroutines_core = getSuccessfulResult$kotlinx_coroutines_core(state);
                    Result.Companion companion3 = Result.Companion;
                    continuation.resumeWith(Result.m2105constructorimpl(successfulResult$kotlinx_coroutines_core));
                }
                Unit unit = Unit.INSTANCE;
                if (undispatchedCompletion$iv == null || undispatchedCompletion$iv.clearThreadContext()) {
                    ThreadContextKt.restoreThreadContext(context$iv, oldValue$iv);
                }
                try {
                    Result.Companion companion4 = Result.Companion;
                    DispatchedTask<T> dispatchedTask = this;
                    taskContext.afterTask();
                    objM2105constructorimpl3 = Result.m2105constructorimpl(Unit.INSTANCE);
                } catch (Throwable th) {
                    Result.Companion companion5 = Result.Companion;
                    objM2105constructorimpl3 = Result.m2105constructorimpl(ResultKt.createFailure(th));
                }
                Object result = objM2105constructorimpl3;
                handleFatalException(null, Result.m2103exceptionOrNullimpl(result));
            } catch (Throwable th2) {
                if (undispatchedCompletion$iv == null || undispatchedCompletion$iv.clearThreadContext()) {
                    ThreadContextKt.restoreThreadContext(context$iv, oldValue$iv);
                }
                throw th2;
            }
        } catch (Throwable e) {
            try {
                Result.Companion companion6 = Result.Companion;
                DispatchedTask<T> dispatchedTask2 = this;
                taskContext.afterTask();
                objM2105constructorimpl = Result.m2105constructorimpl(Unit.INSTANCE);
            } catch (Throwable th3) {
                Result.Companion companion7 = Result.Companion;
                objM2105constructorimpl = Result.m2105constructorimpl(ResultKt.createFailure(th3));
            }
            Object result2 = objM2105constructorimpl;
            handleFatalException(e, Result.m2103exceptionOrNullimpl(result2));
        }
    }

    public final void handleFatalException(@Nullable Throwable exception, @Nullable Throwable finallyException) {
        if (exception == null && finallyException == null) {
            return;
        }
        if (exception != null && finallyException != null) {
            kotlin.ExceptionsKt.addSuppressed(exception, finallyException);
        }
        Throwable cause = exception == null ? finallyException : exception;
        Intrinsics.checkNotNull(cause);
        CoroutinesInternalError reason = new CoroutinesInternalError("Fatal exception in coroutines machinery for " + this + ". Please read KDoc to 'handleFatalException' method and report this incident to maintainers", cause);
        CoroutineExceptionHandlerKt.handleCoroutineException(getDelegate$kotlinx_coroutines_core().getContext(), reason);
    }
}
