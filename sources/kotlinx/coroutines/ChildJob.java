package kotlinx.coroutines;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Job.kt */
@Deprecated(message = "This is internal API and may be removed in the future releases", level = DeprecationLevel.ERROR)
@InternalCoroutinesApi
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\bg\u0018��2\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/ChildJob;", "Lkotlinx/coroutines/Job;", "parentCancelled", "", "parentJob", "Lkotlinx/coroutines/ParentJob;", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/ChildJob.class */
public interface ChildJob extends Job {
    @InternalCoroutinesApi
    void parentCancelled(@NotNull ParentJob parentJob);

    /* compiled from: Job.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/ChildJob$DefaultImpls.class */
    public static final class DefaultImpls {
        @Deprecated(message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.", level = DeprecationLevel.ERROR)
        @NotNull
        public static Job plus(@NotNull ChildJob childJob, @NotNull Job other) {
            return Job.DefaultImpls.plus((Job) childJob, other);
        }

        @Nullable
        public static <E extends CoroutineContext.Element> E get(@NotNull ChildJob childJob, @NotNull CoroutineContext.Key<E> key) {
            return (E) Job.DefaultImpls.get(childJob, key);
        }

        public static <R> R fold(@NotNull ChildJob childJob, R r, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
            return (R) Job.DefaultImpls.fold(childJob, r, function2);
        }

        @NotNull
        public static CoroutineContext minusKey(@NotNull ChildJob childJob, @NotNull CoroutineContext.Key<?> key) {
            return Job.DefaultImpls.minusKey(childJob, key);
        }

        @NotNull
        public static CoroutineContext plus(@NotNull ChildJob childJob, @NotNull CoroutineContext context) {
            return Job.DefaultImpls.plus(childJob, context);
        }
    }
}
