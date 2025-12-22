package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JobSupport.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0010\u0003\n��\b\u0002\u0018��*\u0004\b��\u0010\u00012\u00020\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u0004¢\u0006\u0002\u0010\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0096\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u0004X\u0082\u0004¢\u0006\u0002\n��¨\u0006\n"}, d2 = {"Lkotlinx/coroutines/ResumeAwaitOnCompletion;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/JobNode;", "continuation", "Lkotlinx/coroutines/CancellableContinuationImpl;", "(Lkotlinx/coroutines/CancellableContinuationImpl;)V", "invoke", "", "cause", "", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/ResumeAwaitOnCompletion.class */
final class ResumeAwaitOnCompletion<T> extends JobNode {

    @NotNull
    private final CancellableContinuationImpl<T> continuation;

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke(th);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ResumeAwaitOnCompletion(@NotNull CancellableContinuationImpl<? super T> cancellableContinuationImpl) {
        this.continuation = cancellableContinuationImpl;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlinx.coroutines.CompletionHandlerBase
    public void invoke(@Nullable Throwable cause) {
        Object state = getJob().getState$kotlinx_coroutines_core();
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(!(state instanceof Incomplete))) {
                throw new AssertionError();
            }
        }
        if (state instanceof CompletedExceptionally) {
            CancellableContinuationImpl<T> cancellableContinuationImpl = this.continuation;
            Throwable th = ((CompletedExceptionally) state).cause;
            Result.Companion companion = Result.Companion;
            cancellableContinuationImpl.resumeWith(Result.m2105constructorimpl(ResultKt.createFailure(th)));
            return;
        }
        CancellableContinuationImpl<T> cancellableContinuationImpl2 = this.continuation;
        Object objUnboxState = JobSupportKt.unboxState(state);
        Result.Companion companion2 = Result.Companion;
        cancellableContinuationImpl2.resumeWith(Result.m2105constructorimpl(objUnboxState));
    }
}
