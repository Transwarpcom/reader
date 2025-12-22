package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.internal.ThreadLocalElement;
import kotlinx.coroutines.internal.ThreadLocalKey;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ThreadContextElement.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\u001e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n��\u001a+\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u0002H\u0002¢\u0006\u0002\u0010\u0005\u001a\u0019\u0010\u0006\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\u0003H\u0086Hø\u0001��¢\u0006\u0002\u0010\b\u001a\u0019\u0010\t\u001a\u00020\n*\u0006\u0012\u0002\b\u00030\u0003H\u0086Hø\u0001��¢\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"asContextElement", "Lkotlinx/coroutines/ThreadContextElement;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Ljava/lang/ThreadLocal;", "value", "(Ljava/lang/ThreadLocal;Ljava/lang/Object;)Lkotlinx/coroutines/ThreadContextElement;", "ensurePresent", "", "(Ljava/lang/ThreadLocal;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isPresent", "", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/ThreadContextElementKt.class */
public final class ThreadContextElementKt {
    public static /* synthetic */ ThreadContextElement asContextElement$default(ThreadLocal threadLocal, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = threadLocal.get();
        }
        return asContextElement(threadLocal, obj);
    }

    @NotNull
    public static final <T> ThreadContextElement<T> asContextElement(@NotNull ThreadLocal<T> threadLocal, T t) {
        return new ThreadLocalElement(t, threadLocal);
    }

    @Nullable
    public static final Object isPresent(@NotNull ThreadLocal<?> threadLocal, @NotNull Continuation<? super Boolean> continuation) {
        return Boxing.boxBoolean(continuation.getContext().get(new ThreadLocalKey(threadLocal)) != null);
    }

    private static final Object isPresent$$forInline(ThreadLocal<?> threadLocal, Continuation<? super Boolean> continuation) {
        InlineMarker.mark(3);
        Continuation continuation2 = null;
        return Boolean.valueOf(continuation2.getContext().get(new ThreadLocalKey(threadLocal)) != null);
    }

    @Nullable
    public static final Object ensurePresent(@NotNull ThreadLocal<?> threadLocal, @NotNull Continuation<? super Unit> continuation) {
        if (continuation.getContext().get(new ThreadLocalKey(threadLocal)) != null) {
            return Unit.INSTANCE;
        }
        throw new IllegalStateException(("ThreadLocal " + threadLocal + " is missing from context " + continuation.getContext()).toString());
    }

    private static final Object ensurePresent$$forInline(ThreadLocal<?> threadLocal, Continuation<? super Unit> continuation) {
        InlineMarker.mark(3);
        Continuation continuation2 = null;
        if (continuation2.getContext().get(new ThreadLocalKey(threadLocal)) != null) {
            return Unit.INSTANCE;
        }
        StringBuilder sbAppend = new StringBuilder().append("ThreadLocal ").append(threadLocal).append(" is missing from context ");
        InlineMarker.mark(3);
        Continuation continuation3 = null;
        throw new IllegalStateException(sbAppend.append(continuation3.getContext()).toString().toString());
    }
}
