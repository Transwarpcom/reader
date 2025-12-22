package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Job.kt */
@Metadata(mv = {1, 5, 1}, k = 5, xi = 48, d1 = {"��D\n��\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\t\u001a\u0019\u0010\b\u001a\u00020\t2\u000e\b\u0004\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0087\b\u001a\u0012\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u001a\u0019\u0010\u0010\u001a\u00020\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\b\r\u001a\f\u0010\u0011\u001a\u00020\f*\u00020\u0002H\u0007\u001a\u0018\u0010\u0011\u001a\u00020\u0001*\u00020\u00022\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0007\u001a\u001c\u0010\u0011\u001a\u00020\f*\u00020\u00022\u0010\b\u0002\u0010\u0012\u001a\n\u0018\u00010\u0014j\u0004\u0018\u0001`\u0015\u001a\u001e\u0010\u0011\u001a\u00020\f*\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u001a\u0015\u0010\u0018\u001a\u00020\f*\u00020\u0005H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0019\u001a\f\u0010\u001a\u001a\u00020\f*\u00020\u0002H\u0007\u001a\u0018\u0010\u001a\u001a\u00020\f*\u00020\u00022\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0007\u001a\u001c\u0010\u001a\u001a\u00020\f*\u00020\u00022\u0010\b\u0002\u0010\u0012\u001a\n\u0018\u00010\u0014j\u0004\u0018\u0001`\u0015\u001a\f\u0010\u001a\u001a\u00020\f*\u00020\u0005H\u0007\u001a\u0018\u0010\u001a\u001a\u00020\f*\u00020\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0007\u001a\u001c\u0010\u001a\u001a\u00020\f*\u00020\u00052\u0010\b\u0002\u0010\u0012\u001a\n\u0018\u00010\u0014j\u0004\u0018\u0001`\u0015\u001a\u0014\u0010\u001b\u001a\u00020\t*\u00020\u00052\u0006\u0010\u001c\u001a\u00020\tH��\u001a\n\u0010\u001d\u001a\u00020\f*\u00020\u0002\u001a\n\u0010\u001d\u001a\u00020\f*\u00020\u0005\u001a\u001b\u0010\u001e\u001a\u00020\u0013*\u0004\u0018\u00010\u00132\u0006\u0010\u0004\u001a\u00020\u0005H\u0002¢\u0006\u0002\b\u001f\"\u0015\u0010��\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b��\u0010\u0003\"\u0015\u0010\u0004\u001a\u00020\u0005*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, d2 = {"isActive", "", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)Z", "job", "Lkotlinx/coroutines/Job;", "getJob", "(Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/Job;", "DisposableHandle", "Lkotlinx/coroutines/DisposableHandle;", "block", "Lkotlin/Function0;", "", "Job", "Lkotlinx/coroutines/CompletableJob;", "parent", "Job0", "cancel", "cause", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "message", "", "cancelAndJoin", "(Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancelChildren", "disposeOnCompletion", "handle", "ensureActive", "orCancellation", "orCancellation$JobKt__JobKt", "kotlinx-coroutines-core"}, xs = "kotlinx/coroutines/JobKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/JobKt__JobKt.class */
public final /* synthetic */ class JobKt__JobKt {
    @NotNull
    public static final CompletableJob Job(@Nullable Job parent) {
        return new JobImpl(parent);
    }

    public static /* synthetic */ CompletableJob Job$default(Job job, int i, Object obj) {
        if ((i & 1) != 0) {
            job = null;
        }
        return JobKt.Job(job);
    }

    @Deprecated(message = "Since 1.2.0, binary compatibility with versions <= 1.1.x", level = DeprecationLevel.HIDDEN)
    @JvmName(name = "Job")
    /* renamed from: Job, reason: collision with other method in class */
    public static final /* synthetic */ Job m4193Job(Job parent) {
        return JobKt.Job(parent);
    }

    /* renamed from: Job$default, reason: collision with other method in class */
    public static /* synthetic */ Job m4194Job$default(Job job, int i, Object obj) {
        if ((i & 1) != 0) {
            job = null;
        }
        return m4193Job(job);
    }

    @InternalCoroutinesApi
    @NotNull
    public static final DisposableHandle DisposableHandle(@NotNull final Function0<Unit> function0) {
        return new DisposableHandle() { // from class: kotlinx.coroutines.JobKt__JobKt.DisposableHandle.1
            @Override // kotlinx.coroutines.DisposableHandle
            public void dispose() {
                function0.invoke();
            }
        };
    }

    @NotNull
    public static final DisposableHandle disposeOnCompletion(@NotNull Job $this$disposeOnCompletion, @NotNull DisposableHandle handle) {
        CompletionHandlerBase $this$asHandler$iv = new DisposeOnCompletion(handle);
        return $this$disposeOnCompletion.invokeOnCompletion($this$asHandler$iv);
    }

    @Nullable
    public static final Object cancelAndJoin(@NotNull Job $this$cancelAndJoin, @NotNull Continuation<? super Unit> continuation) {
        Job.DefaultImpls.cancel$default($this$cancelAndJoin, (CancellationException) null, 1, (Object) null);
        Object objJoin = $this$cancelAndJoin.join(continuation);
        return objJoin == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objJoin : Unit.INSTANCE;
    }

    public static /* synthetic */ void cancelChildren$default(Job job, CancellationException cancellationException, int i, Object obj) {
        if ((i & 1) != 0) {
            cancellationException = null;
        }
        JobKt.cancelChildren(job, cancellationException);
    }

    public static final void cancelChildren(@NotNull Job $this$cancelChildren, @Nullable CancellationException cause) {
        Sequence $this$forEach$iv = $this$cancelChildren.getChildren();
        for (Object element$iv : $this$forEach$iv) {
            Job it = (Job) element$iv;
            it.cancel(cause);
        }
    }

    public static /* synthetic */ void cancelChildren$default(Job job, Throwable th, int i, Object obj) throws Throwable {
        if ((i & 1) != 0) {
            th = null;
        }
        cancelChildren(job, th);
    }

    @Deprecated(message = "Since 1.2.0, binary compatibility with versions <= 1.1.x", level = DeprecationLevel.HIDDEN)
    public static final /* synthetic */ void cancelChildren(Job $this$cancelChildren, Throwable cause) throws Throwable {
        Sequence $this$forEach$iv = $this$cancelChildren.getChildren();
        for (Object element$iv : $this$forEach$iv) {
            Job it = (Job) element$iv;
            JobSupport jobSupport = it instanceof JobSupport ? (JobSupport) it : null;
            if (jobSupport != null) {
                jobSupport.cancelInternal(orCancellation$JobKt__JobKt(cause, $this$cancelChildren));
            }
        }
    }

    public static final boolean isActive(@NotNull CoroutineContext $this$isActive) {
        Job job = (Job) $this$isActive.get(Job.Key);
        return job != null && job.isActive();
    }

    public static /* synthetic */ void cancel$default(CoroutineContext coroutineContext, CancellationException cancellationException, int i, Object obj) {
        if ((i & 1) != 0) {
            cancellationException = null;
        }
        JobKt.cancel(coroutineContext, cancellationException);
    }

    public static final void cancel(@NotNull CoroutineContext $this$cancel, @Nullable CancellationException cause) {
        Job job = (Job) $this$cancel.get(Job.Key);
        if (job == null) {
            return;
        }
        job.cancel(cause);
    }

    public static final void ensureActive(@NotNull Job $this$ensureActive) {
        if (!$this$ensureActive.isActive()) {
            throw $this$ensureActive.getCancellationException();
        }
    }

    public static final void ensureActive(@NotNull CoroutineContext $this$ensureActive) {
        Job job = (Job) $this$ensureActive.get(Job.Key);
        if (job == null) {
            return;
        }
        JobKt.ensureActive(job);
    }

    public static final void cancel(@NotNull Job $this$cancel, @NotNull String message, @Nullable Throwable cause) {
        $this$cancel.cancel(ExceptionsKt.CancellationException(message, cause));
    }

    public static /* synthetic */ void cancel$default(Job job, String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        JobKt.cancel(job, str, th);
    }

    public static /* synthetic */ boolean cancel$default(CoroutineContext coroutineContext, Throwable th, int i, Object obj) {
        if ((i & 1) != 0) {
            th = null;
        }
        return cancel(coroutineContext, th);
    }

    @Deprecated(message = "Since 1.2.0, binary compatibility with versions <= 1.1.x", level = DeprecationLevel.HIDDEN)
    public static final /* synthetic */ boolean cancel(CoroutineContext $this$cancel, Throwable cause) throws Throwable {
        CoroutineContext.Element element = $this$cancel.get(Job.Key);
        JobSupport job = element instanceof JobSupport ? (JobSupport) element : null;
        if (job == null) {
            return false;
        }
        job.cancelInternal(orCancellation$JobKt__JobKt(cause, job));
        return true;
    }

    public static /* synthetic */ void cancelChildren$default(CoroutineContext coroutineContext, CancellationException cancellationException, int i, Object obj) {
        if ((i & 1) != 0) {
            cancellationException = null;
        }
        JobKt.cancelChildren(coroutineContext, cancellationException);
    }

    public static final void cancelChildren(@NotNull CoroutineContext $this$cancelChildren, @Nullable CancellationException cause) {
        Job job = (Job) $this$cancelChildren.get(Job.Key);
        if (job == null) {
            return;
        }
        Sequence $this$forEach$iv = job.getChildren();
        for (Object element$iv : $this$forEach$iv) {
            Job it = (Job) element$iv;
            it.cancel(cause);
        }
    }

    @NotNull
    public static final Job getJob(@NotNull CoroutineContext $this$job) {
        Job job = (Job) $this$job.get(Job.Key);
        if (job == null) {
            throw new IllegalStateException(Intrinsics.stringPlus("Current context doesn't contain Job in it: ", $this$job).toString());
        }
        return job;
    }

    public static /* synthetic */ void cancelChildren$default(CoroutineContext coroutineContext, Throwable th, int i, Object obj) throws Throwable {
        if ((i & 1) != 0) {
            th = null;
        }
        cancelChildren(coroutineContext, th);
    }

    @Deprecated(message = "Since 1.2.0, binary compatibility with versions <= 1.1.x", level = DeprecationLevel.HIDDEN)
    public static final /* synthetic */ void cancelChildren(CoroutineContext $this$cancelChildren, Throwable cause) throws Throwable {
        Job job = (Job) $this$cancelChildren.get(Job.Key);
        if (job == null) {
            return;
        }
        Sequence $this$forEach$iv = job.getChildren();
        for (Object element$iv : $this$forEach$iv) {
            Job it = (Job) element$iv;
            JobSupport jobSupport = it instanceof JobSupport ? (JobSupport) it : null;
            if (jobSupport != null) {
                jobSupport.cancelInternal(orCancellation$JobKt__JobKt(cause, job));
            }
        }
    }

    private static final Throwable orCancellation$JobKt__JobKt(Throwable $this$orCancellation, Job job) {
        return $this$orCancellation == null ? new JobCancellationException("Job was cancelled", null, job) : $this$orCancellation;
    }
}
