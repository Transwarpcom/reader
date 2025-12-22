package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CompletionState.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��8\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a4\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H��ø\u0001��¢\u0006\u0002\u0010\u0007\u001aI\u0010\b\u001a\u0004\u0018\u00010\u0004\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012%\b\u0002\u0010\t\u001a\u001f\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\nH��ø\u0001��¢\u0006\u0002\u0010\u0010\u001a.\u0010\b\u001a\u0004\u0018\u00010\u0004\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0012H��ø\u0001��¢\u0006\u0002\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"recoverResult", "Lkotlin/Result;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "state", "", "uCont", "Lkotlin/coroutines/Continuation;", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toState", "onCancellation", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "caller", "Lkotlinx/coroutines/CancellableContinuation;", "(Ljava/lang/Object;Lkotlinx/coroutines/CancellableContinuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/CompletionStateKt.class */
public final class CompletionStateKt {
    public static /* synthetic */ Object toState$default(Object obj, Function1 function1, int i, Object obj2) {
        if ((i & 1) != 0) {
            function1 = null;
        }
        return toState(obj, (Function1<? super Throwable, Unit>) function1);
    }

    @Nullable
    public static final <T> Object toState(@NotNull Object $this$toState, @Nullable Function1<? super Throwable, Unit> function1) {
        Throwable it = Result.m2103exceptionOrNullimpl($this$toState);
        if (it == null) {
            return function1 != null ? new CompletedWithCancellation($this$toState, function1) : $this$toState;
        }
        return new CompletedExceptionally(it, false, 2, null);
    }

    @Nullable
    public static final <T> Object toState(@NotNull Object $this$toState, @NotNull CancellableContinuation<?> cancellableContinuation) {
        Throwable it = Result.m2103exceptionOrNullimpl($this$toState);
        if (it == null) {
            return $this$toState;
        }
        return new CompletedExceptionally((DebugKt.getRECOVER_STACK_TRACES() && (cancellableContinuation instanceof CoroutineStackFrame)) ? StackTraceRecoveryKt.recoverFromStackFrame(it, (CoroutineStackFrame) cancellableContinuation) : it, false, 2, null);
    }

    @NotNull
    public static final <T> Object recoverResult(@Nullable Object state, @NotNull Continuation<? super T> continuation) {
        if (state instanceof CompletedExceptionally) {
            Result.Companion companion = Result.Companion;
            Throwable exception$iv = ((CompletedExceptionally) state).cause;
            return Result.m2105constructorimpl(ResultKt.createFailure((DebugKt.getRECOVER_STACK_TRACES() && (continuation instanceof CoroutineStackFrame)) ? StackTraceRecoveryKt.recoverFromStackFrame(exception$iv, (CoroutineStackFrame) continuation) : exception$iv));
        }
        Result.Companion companion2 = Result.Companion;
        return Result.m2105constructorimpl(state);
    }
}
