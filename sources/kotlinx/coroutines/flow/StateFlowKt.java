package kotlinx.coroutines.flow;

import ch.qos.logback.core.CoreConstants;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* compiled from: StateFlow.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��@\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u001a\u001f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b��\u0010\b2\u0006\u0010\t\u001a\u0002H\b¢\u0006\u0002\u0010\n\u001a6\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\b0\f\"\u0004\b��\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H��\u001a2\u0010\u0014\u001a\u0002H\b\"\u0004\b��\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u00072\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\b0\u0016H\u0086\b¢\u0006\u0002\u0010\u0017\u001a\u001a\u0010\u0018\u001a\u00020\u0019*\b\u0012\u0004\u0012\u00020\u00110\u00072\u0006\u0010\u001a\u001a\u00020\u0011H��\u001a-\u0010\u001b\u001a\u00020\u0019\"\u0004\b��\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u00072\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\b0\u0016H\u0086\b\u001a2\u0010\u001c\u001a\u0002H\b\"\u0004\b��\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u00072\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\b0\u0016H\u0086\b¢\u0006\u0002\u0010\u0017\"\u0016\u0010��\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n��\u0012\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0004\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n��\u0012\u0004\b\u0005\u0010\u0003¨\u0006\u001d"}, d2 = {"NONE", "Lkotlinx/coroutines/internal/Symbol;", "getNONE$annotations", "()V", "PENDING", "getPENDING$annotations", "MutableStateFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "value", "(Ljava/lang/Object;)Lkotlinx/coroutines/flow/MutableStateFlow;", "fuseStateFlow", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/StateFlow;", CoreConstants.CONTEXT_SCOPE_VALUE, "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "getAndUpdate", "function", "Lkotlin/Function1;", "(Lkotlinx/coroutines/flow/MutableStateFlow;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "increment", "", "delta", "update", "updateAndGet", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/StateFlowKt.class */
public final class StateFlowKt {

    @NotNull
    private static final Symbol NONE = new Symbol("NONE");

    @NotNull
    private static final Symbol PENDING = new Symbol("PENDING");

    private static /* synthetic */ void getNONE$annotations() {
    }

    private static /* synthetic */ void getPENDING$annotations() {
    }

    @NotNull
    public static final <T> MutableStateFlow<T> MutableStateFlow(T t) {
        return new StateFlowImpl(t == null ? NullSurrogateKt.NULL : t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> T updateAndGet(@NotNull MutableStateFlow<T> mutableStateFlow, @NotNull Function1<? super T, ? extends T> function1) {
        Object prevValue;
        T tInvoke;
        do {
            prevValue = mutableStateFlow.getValue();
            tInvoke = function1.invoke(prevValue);
        } while (!mutableStateFlow.compareAndSet(prevValue, tInvoke));
        return tInvoke;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object] */
    public static final <T> T getAndUpdate(@NotNull MutableStateFlow<T> mutableStateFlow, @NotNull Function1<? super T, ? extends T> function1) {
        ?? r0;
        do {
            r0 = (Object) mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(r0, function1.invoke(r0)));
        return r0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> void update(@NotNull MutableStateFlow<T> mutableStateFlow, @NotNull Function1<? super T, ? extends T> function1) {
        Object prevValue;
        do {
            prevValue = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(prevValue, function1.invoke(prevValue)));
    }

    public static final void increment(@NotNull MutableStateFlow<Integer> mutableStateFlow, int delta) {
        Integer value;
        int it;
        do {
            value = mutableStateFlow.getValue();
            it = value.intValue();
        } while (!mutableStateFlow.compareAndSet(value, Integer.valueOf(it + delta)));
    }

    @NotNull
    public static final <T> Flow<T> fuseStateFlow(@NotNull StateFlow<? extends T> stateFlow, @NotNull CoroutineContext context, int capacity, @NotNull BufferOverflow onBufferOverflow) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(capacity != -1)) {
                throw new AssertionError();
            }
        }
        boolean z = 0 <= capacity && capacity <= 1;
        if ((z || capacity == -2) && onBufferOverflow == BufferOverflow.DROP_OLDEST) {
            return stateFlow;
        }
        return SharedFlowKt.fuseSharedFlow(stateFlow, context, capacity, onBufferOverflow);
    }
}
