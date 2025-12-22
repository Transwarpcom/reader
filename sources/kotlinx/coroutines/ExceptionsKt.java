package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Exceptions.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"�� \n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0003\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\u001e\u0010��\u001a\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u001a\u0015\u0010\u0007\u001a\u00020\b*\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0080\b*\n\u0010��\"\u00020\u00012\u00020\u0001¨\u0006\n"}, d2 = {"CancellationException", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "message", "", "cause", "", "addSuppressedThrowable", "", "other", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/ExceptionsKt.class */
public final class ExceptionsKt {
    @NotNull
    public static final CancellationException CancellationException(@Nullable String message, @Nullable Throwable cause) {
        CancellationException $this$CancellationException_u24lambda_u2d0 = new CancellationException(message);
        $this$CancellationException_u24lambda_u2d0.initCause(cause);
        return $this$CancellationException_u24lambda_u2d0;
    }

    public static final void addSuppressedThrowable(@NotNull Throwable $this$addSuppressedThrowable, @NotNull Throwable other) {
        kotlin.ExceptionsKt.addSuppressed($this$addSuppressedThrowable, other);
    }
}
