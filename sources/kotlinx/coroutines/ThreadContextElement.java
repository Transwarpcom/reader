package kotlinx.coroutines;

import ch.qos.logback.core.CoreConstants;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ThreadContextElement.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018��*\u0004\b��\u0010\u00012\u00020\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00028��H&¢\u0006\u0002\u0010\bJ\u0015\u0010\t\u001a\u00028��2\u0006\u0010\u0005\u001a\u00020\u0006H&¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lkotlinx/coroutines/ThreadContextElement;", "S", "Lkotlin/coroutines/CoroutineContext$Element;", "restoreThreadContext", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Lkotlin/coroutines/CoroutineContext;", "oldState", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;)V", "updateThreadContext", "(Lkotlin/coroutines/CoroutineContext;)Ljava/lang/Object;", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/ThreadContextElement.class */
public interface ThreadContextElement<S> extends CoroutineContext.Element {
    S updateThreadContext(@NotNull CoroutineContext coroutineContext);

    void restoreThreadContext(@NotNull CoroutineContext coroutineContext, S s);

    /* compiled from: ThreadContextElement.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/ThreadContextElement$DefaultImpls.class */
    public static final class DefaultImpls {
        @Nullable
        public static <S, E extends CoroutineContext.Element> E get(@NotNull ThreadContextElement<S> threadContextElement, @NotNull CoroutineContext.Key<E> key) {
            return (E) CoroutineContext.Element.DefaultImpls.get(threadContextElement, key);
        }

        public static <S, R> R fold(@NotNull ThreadContextElement<S> threadContextElement, R r, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
            return (R) CoroutineContext.Element.DefaultImpls.fold(threadContextElement, r, function2);
        }

        @NotNull
        public static <S> CoroutineContext minusKey(@NotNull ThreadContextElement<S> threadContextElement, @NotNull CoroutineContext.Key<?> key) {
            return CoroutineContext.Element.DefaultImpls.minusKey(threadContextElement, key);
        }

        @NotNull
        public static <S> CoroutineContext plus(@NotNull ThreadContextElement<S> threadContextElement, @NotNull CoroutineContext context) {
            return CoroutineContext.Element.DefaultImpls.plus(threadContextElement, context);
        }
    }
}
