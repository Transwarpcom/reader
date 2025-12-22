package kotlinx.coroutines.internal;

import ch.qos.logback.core.CoreConstants;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ThreadContextElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ThreadContext.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��0\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001a\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004H��\u001a\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH��\u001a\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u00042\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004H��\"\u0010\u0010��\u001a\u00020\u00018��X\u0081\u0004¢\u0006\u0002\n��\"$\u0010\u0002\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003X\u0082\u0004¢\u0006\u0002\n��\",\u0010\u0006\u001a \u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u0007\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00070\u0003X\u0082\u0004¢\u0006\u0002\n��\" \u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\u0003X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0012"}, d2 = {"NO_THREAD_ELEMENTS", "Lkotlinx/coroutines/internal/Symbol;", "countAll", "Lkotlin/Function2;", "", "Lkotlin/coroutines/CoroutineContext$Element;", "findOne", "Lkotlinx/coroutines/ThreadContextElement;", "updateState", "Lkotlinx/coroutines/internal/ThreadState;", "restoreThreadContext", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Lkotlin/coroutines/CoroutineContext;", "oldState", "threadContextElements", "updateThreadContext", "countOrElement", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/internal/ThreadContextKt.class */
public final class ThreadContextKt {

    @JvmField
    @NotNull
    public static final Symbol NO_THREAD_ELEMENTS = new Symbol("NO_THREAD_ELEMENTS");

    @NotNull
    private static final Function2<Object, CoroutineContext.Element, Object> countAll = new Function2<Object, CoroutineContext.Element, Object>() { // from class: kotlinx.coroutines.internal.ThreadContextKt$countAll$1
        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@Nullable Object countOrElement, @NotNull CoroutineContext.Element element) {
            if (element instanceof ThreadContextElement) {
                Integer num = countOrElement instanceof Integer ? (Integer) countOrElement : null;
                int inCount = num == null ? 1 : num.intValue();
                return inCount == 0 ? element : Integer.valueOf(inCount + 1);
            }
            return countOrElement;
        }
    };

    @NotNull
    private static final Function2<ThreadContextElement<?>, CoroutineContext.Element, ThreadContextElement<?>> findOne = new Function2<ThreadContextElement<?>, CoroutineContext.Element, ThreadContextElement<?>>() { // from class: kotlinx.coroutines.internal.ThreadContextKt$findOne$1
        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final ThreadContextElement<?> invoke(@Nullable ThreadContextElement<?> threadContextElement, @NotNull CoroutineContext.Element element) {
            if (threadContextElement != null) {
                return threadContextElement;
            }
            if (element instanceof ThreadContextElement) {
                return (ThreadContextElement) element;
            }
            return null;
        }
    };

    @NotNull
    private static final Function2<ThreadState, CoroutineContext.Element, ThreadState> updateState = new Function2<ThreadState, CoroutineContext.Element, ThreadState>() { // from class: kotlinx.coroutines.internal.ThreadContextKt$updateState$1
        @Override // kotlin.jvm.functions.Function2
        @NotNull
        public final ThreadState invoke(@NotNull ThreadState state, @NotNull CoroutineContext.Element element) {
            if (element instanceof ThreadContextElement) {
                state.append((ThreadContextElement) element, ((ThreadContextElement) element).updateThreadContext2(state.context));
            }
            return state;
        }
    };

    @NotNull
    public static final Object threadContextElements(@NotNull CoroutineContext context) {
        Object objFold = context.fold(0, countAll);
        Intrinsics.checkNotNull(objFold);
        return objFold;
    }

    @Nullable
    public static final Object updateThreadContext(@NotNull CoroutineContext context, @Nullable Object countOrElement) {
        Object countOrElement2 = countOrElement == null ? threadContextElements(context) : countOrElement;
        if (countOrElement2 == 0) {
            return NO_THREAD_ELEMENTS;
        }
        if (countOrElement2 instanceof Integer) {
            return context.fold(new ThreadState(context, ((Number) countOrElement2).intValue()), updateState);
        }
        ThreadContextElement element = (ThreadContextElement) countOrElement2;
        return element.updateThreadContext2(context);
    }

    public static final void restoreThreadContext(@NotNull CoroutineContext context, @Nullable Object oldState) {
        if (oldState == NO_THREAD_ELEMENTS) {
            return;
        }
        if (oldState instanceof ThreadState) {
            ((ThreadState) oldState).restore(context);
            return;
        }
        Object objFold = context.fold(null, findOne);
        if (objFold == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
        }
        ThreadContextElement element = (ThreadContextElement) objFold;
        element.restoreThreadContext(context, oldState);
    }
}
