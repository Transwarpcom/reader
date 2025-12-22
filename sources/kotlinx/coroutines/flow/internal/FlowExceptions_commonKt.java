package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;

/* compiled from: FlowExceptions.common.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\u001a\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u001a\u0011\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0081\b\u001a\u0018\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007H��¨\u0006\b"}, d2 = {"checkIndexOverflow", "", "index", "checkOwnership", "", "Lkotlinx/coroutines/flow/internal/AbortFlowException;", "owner", "Lkotlinx/coroutines/flow/FlowCollector;", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/internal/FlowExceptions_commonKt.class */
public final class FlowExceptions_commonKt {
    public static final void checkOwnership(@NotNull AbortFlowException $this$checkOwnership, @NotNull FlowCollector<?> flowCollector) {
        if ($this$checkOwnership.getOwner() != flowCollector) {
            throw $this$checkOwnership;
        }
    }

    @PublishedApi
    public static final int checkIndexOverflow(int index) {
        if (index < 0) {
            throw new ArithmeticException("Index overflow has happened");
        }
        return index;
    }
}
