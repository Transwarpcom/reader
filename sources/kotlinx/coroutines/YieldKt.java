package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.DispatchedContinuationKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Yield.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\u0011\u0010��\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0003"}, d2 = {"yield", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/YieldKt.class */
public final class YieldKt {
    @Nullable
    public static final Object yield(@NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        CoroutineContext context = continuation.getContext();
        JobKt.ensureActive(context);
        Continuation continuationIntercepted = IntrinsicsKt.intercepted(continuation);
        DispatchedContinuation cont = continuationIntercepted instanceof DispatchedContinuation ? (DispatchedContinuation) continuationIntercepted : null;
        if (cont == null) {
            coroutine_suspended = Unit.INSTANCE;
        } else {
            if (cont.dispatcher.isDispatchNeeded(context)) {
                cont.dispatchYield$kotlinx_coroutines_core(context, Unit.INSTANCE);
            } else {
                YieldContext yieldContext = new YieldContext();
                cont.dispatchYield$kotlinx_coroutines_core(context.plus(yieldContext), Unit.INSTANCE);
                coroutine_suspended = (!yieldContext.dispatcherWasUnconfined || DispatchedContinuationKt.yieldUndispatched(cont)) ? IntrinsicsKt.getCOROUTINE_SUSPENDED() : Unit.INSTANCE;
            }
            coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        if (coroutine_suspended == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return coroutine_suspended == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? coroutine_suspended : Unit.INSTANCE;
    }
}
