package kotlinx.coroutines;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CompletableJob.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0003\n��\bf\u0018��2\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/CompletableJob;", "Lkotlinx/coroutines/Job;", "complete", "", "completeExceptionally", "exception", "", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/CompletableJob.class */
public interface CompletableJob extends Job {
    boolean complete();

    boolean completeExceptionally(@NotNull Throwable th);

    /* compiled from: CompletableJob.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/CompletableJob$DefaultImpls.class */
    public static final class DefaultImpls {
        @Deprecated(message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.", level = DeprecationLevel.ERROR)
        @NotNull
        public static Job plus(@NotNull CompletableJob completableJob, @NotNull Job other) {
            return Job.DefaultImpls.plus((Job) completableJob, other);
        }

        @Nullable
        public static <E extends CoroutineContext.Element> E get(@NotNull CompletableJob completableJob, @NotNull CoroutineContext.Key<E> key) {
            return (E) Job.DefaultImpls.get(completableJob, key);
        }

        public static <R> R fold(@NotNull CompletableJob completableJob, R r, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
            return (R) Job.DefaultImpls.fold(completableJob, r, function2);
        }

        @NotNull
        public static CoroutineContext minusKey(@NotNull CompletableJob completableJob, @NotNull CoroutineContext.Key<?> key) {
            return Job.DefaultImpls.minusKey(completableJob, key);
        }

        @NotNull
        public static CoroutineContext plus(@NotNull CompletableJob completableJob, @NotNull CoroutineContext context) {
            return Job.DefaultImpls.plus(completableJob, context);
        }
    }
}
