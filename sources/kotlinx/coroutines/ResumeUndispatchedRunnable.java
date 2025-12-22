package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;

/* compiled from: Executors.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018��2\u00060\u0001j\u0002`\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\u0007H\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��¨\u0006\n"}, d2 = {"Lkotlinx/coroutines/ResumeUndispatchedRunnable;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CancellableContinuation;)V", "run", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/ResumeUndispatchedRunnable.class */
final class ResumeUndispatchedRunnable implements Runnable {

    @NotNull
    private final CoroutineDispatcher dispatcher;

    @NotNull
    private final CancellableContinuation<Unit> continuation;

    /* JADX WARN: Multi-variable type inference failed */
    public ResumeUndispatchedRunnable(@NotNull CoroutineDispatcher dispatcher, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
        this.dispatcher = dispatcher;
        this.continuation = cancellableContinuation;
    }

    @Override // java.lang.Runnable
    public void run() {
        CancellableContinuation $this$run_u24lambda_u2d0 = this.continuation;
        $this$run_u24lambda_u2d0.resumeUndispatched(this.dispatcher, Unit.INSTANCE);
    }
}
