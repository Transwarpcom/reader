package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import java.util.concurrent.Future;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 5, 1}, k = 4, xi = 48, d1 = {"kotlinx/coroutines/JobKt__FutureKt", "kotlinx/coroutines/JobKt__JobKt"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/JobKt.class */
public final class JobKt {
    @InternalCoroutinesApi
    @NotNull
    public static final DisposableHandle cancelFutureOnCompletion(@NotNull Job $this$cancelFutureOnCompletion, @NotNull Future<?> future) {
        return JobKt__FutureKt.cancelFutureOnCompletion($this$cancelFutureOnCompletion, future);
    }

    public static final void cancelFutureOnCancellation(@NotNull CancellableContinuation<?> cancellableContinuation, @NotNull Future<?> future) {
        JobKt__FutureKt.cancelFutureOnCancellation(cancellableContinuation, future);
    }

    @NotNull
    public static final CompletableJob Job(@Nullable Job parent) {
        return JobKt__JobKt.Job(parent);
    }

    @InternalCoroutinesApi
    @NotNull
    public static final DisposableHandle DisposableHandle(@NotNull Function0<Unit> function0) {
        return JobKt__JobKt.DisposableHandle(function0);
    }

    @NotNull
    public static final DisposableHandle disposeOnCompletion(@NotNull Job $this$disposeOnCompletion, @NotNull DisposableHandle handle) {
        return JobKt__JobKt.disposeOnCompletion($this$disposeOnCompletion, handle);
    }

    @Nullable
    public static final Object cancelAndJoin(@NotNull Job $this$cancelAndJoin, @NotNull Continuation<? super Unit> continuation) {
        return JobKt__JobKt.cancelAndJoin($this$cancelAndJoin, continuation);
    }

    public static final void cancelChildren(@NotNull Job $this$cancelChildren, @Nullable CancellationException cause) {
        JobKt__JobKt.cancelChildren($this$cancelChildren, cause);
    }

    public static final boolean isActive(@NotNull CoroutineContext $this$isActive) {
        return JobKt__JobKt.isActive($this$isActive);
    }

    public static final void cancel(@NotNull CoroutineContext $this$cancel, @Nullable CancellationException cause) {
        JobKt__JobKt.cancel($this$cancel, cause);
    }

    public static final void ensureActive(@NotNull Job $this$ensureActive) {
        JobKt__JobKt.ensureActive($this$ensureActive);
    }

    public static final void ensureActive(@NotNull CoroutineContext $this$ensureActive) {
        JobKt__JobKt.ensureActive($this$ensureActive);
    }

    public static final void cancel(@NotNull Job $this$cancel, @NotNull String message, @Nullable Throwable cause) {
        JobKt__JobKt.cancel($this$cancel, message, cause);
    }

    public static final void cancelChildren(@NotNull CoroutineContext $this$cancelChildren, @Nullable CancellationException cause) {
        JobKt__JobKt.cancelChildren($this$cancelChildren, cause);
    }

    @NotNull
    public static final Job getJob(@NotNull CoroutineContext $this$job) {
        return JobKt__JobKt.getJob($this$job);
    }
}
