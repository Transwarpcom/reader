package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;

/* compiled from: AbstractSharedFlow.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b \u0018��*\u0004\b��\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0015\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028��H&¢\u0006\u0002\u0010\u0007J#\u0010\b\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n0\t2\u0006\u0010\u0006\u001a\u00028��H&¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "F", "", "()V", "allocateLocked", "", "flow", "(Ljava/lang/Object;)Z", "freeLocked", "", "Lkotlin/coroutines/Continuation;", "", "(Ljava/lang/Object;)[Lkotlin/coroutines/Continuation;", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/internal/AbstractSharedFlowSlot.class */
public abstract class AbstractSharedFlowSlot<F> {
    public abstract boolean allocateLocked(F f);

    @NotNull
    public abstract Continuation<Unit>[] freeLocked(F f);
}
