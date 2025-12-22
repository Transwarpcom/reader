package kotlinx.coroutines;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.Deferred;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CompletableDeferred.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0003\n��\bf\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028��H&¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lkotlinx/coroutines/CompletableDeferred;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/Deferred;", "complete", "", "value", "(Ljava/lang/Object;)Z", "completeExceptionally", "exception", "", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/CompletableDeferred.class */
public interface CompletableDeferred<T> extends Deferred<T> {
    boolean complete(T t);

    boolean completeExceptionally(@NotNull Throwable th);

    /* compiled from: CompletableDeferred.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/CompletableDeferred$DefaultImpls.class */
    public static final class DefaultImpls {
        @Deprecated(message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.", level = DeprecationLevel.ERROR)
        @NotNull
        public static <T> Job plus(@NotNull CompletableDeferred<T> completableDeferred, @NotNull Job other) {
            return Deferred.DefaultImpls.plus((Deferred) completableDeferred, other);
        }

        @Nullable
        public static <T, E extends CoroutineContext.Element> E get(@NotNull CompletableDeferred<T> completableDeferred, @NotNull CoroutineContext.Key<E> key) {
            return (E) Deferred.DefaultImpls.get(completableDeferred, key);
        }

        public static <T, R> R fold(@NotNull CompletableDeferred<T> completableDeferred, R r, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
            return (R) Deferred.DefaultImpls.fold(completableDeferred, r, function2);
        }

        @NotNull
        public static <T> CoroutineContext minusKey(@NotNull CompletableDeferred<T> completableDeferred, @NotNull CoroutineContext.Key<?> key) {
            return Deferred.DefaultImpls.minusKey(completableDeferred, key);
        }

        @NotNull
        public static <T> CoroutineContext plus(@NotNull CompletableDeferred<T> completableDeferred, @NotNull CoroutineContext context) {
            return Deferred.DefaultImpls.plus(completableDeferred, context);
        }
    }
}
