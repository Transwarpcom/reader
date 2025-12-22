package kotlinx.coroutines.scheduling;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.internal.SystemPropsKt;
import kotlinx.coroutines.internal.SystemPropsKt__SystemProps_commonKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: Tasks.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��,\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\"\u0010\u0010��\u001a\u00020\u00018��X\u0081\u0004¢\u0006\u0002\n��\"\u0010\u0010\u0002\u001a\u00020\u00018��X\u0081\u0004¢\u0006\u0002\n��\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n��\"\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n��\"\u0010\u0010\u0006\u001a\u00020\u00078��X\u0081\u0004¢\u0006\u0002\n��\"\u0010\u0010\b\u001a\u00020\u00018��X\u0081\u0004¢\u0006\u0002\n��\"\u000e\u0010\t\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n��\"\u000e\u0010\n\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n��\"\u0010\u0010\u000b\u001a\u00020\u00078��X\u0081\u0004¢\u0006\u0002\n��\"\u0012\u0010\f\u001a\u00020\r8��@��X\u0081\u000e¢\u0006\u0002\n��\"\u0019\u0010\u000e\u001a\u00020\u000f*\u00020\u00108À\u0002X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0011¨\u0006\u0012"}, d2 = {"BLOCKING_DEFAULT_PARALLELISM", "", "CORE_POOL_SIZE", "DEFAULT_DISPATCHER_NAME", "", "DEFAULT_SCHEDULER_NAME", "IDLE_WORKER_KEEP_ALIVE_NS", "", "MAX_POOL_SIZE", "TASK_NON_BLOCKING", "TASK_PROBABLY_BLOCKING", "WORK_STEALING_TIME_RESOLUTION_NS", "schedulerTimeSource", "Lkotlinx/coroutines/scheduling/SchedulerTimeSource;", "isBlocking", "", "Lkotlinx/coroutines/scheduling/Task;", "(Lkotlinx/coroutines/scheduling/Task;)Z", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/scheduling/TasksKt.class */
public final class TasksKt {

    @NotNull
    public static final String DEFAULT_DISPATCHER_NAME = "Dispatchers.Default";

    @NotNull
    public static final String DEFAULT_SCHEDULER_NAME = "DefaultDispatcher";

    @JvmField
    public static final long WORK_STEALING_TIME_RESOLUTION_NS = SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.scheduler.resolution.ns", 100000L, 0L, 0L, 12, (Object) null);

    @JvmField
    public static final int BLOCKING_DEFAULT_PARALLELISM = SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.scheduler.blocking.parallelism", 16, 0, 0, 12, (Object) null);

    @JvmField
    public static final int CORE_POOL_SIZE = SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.scheduler.core.pool.size", RangesKt.coerceAtLeast(SystemPropsKt.getAVAILABLE_PROCESSORS(), 2), 1, 0, 8, (Object) null);

    @JvmField
    public static final int MAX_POOL_SIZE = SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.scheduler.max.pool.size", RangesKt.coerceIn(SystemPropsKt.getAVAILABLE_PROCESSORS() * 128, CORE_POOL_SIZE, CoroutineScheduler.MAX_SUPPORTED_POOL_SIZE), 0, CoroutineScheduler.MAX_SUPPORTED_POOL_SIZE, 4, (Object) null);

    @JvmField
    public static final long IDLE_WORKER_KEEP_ALIVE_NS = TimeUnit.SECONDS.toNanos(SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.scheduler.keep.alive.sec", 60L, 0L, 0L, 12, (Object) null));

    @JvmField
    @NotNull
    public static SchedulerTimeSource schedulerTimeSource = NanoTimeSource.INSTANCE;
    public static final int TASK_NON_BLOCKING = 0;
    public static final int TASK_PROBABLY_BLOCKING = 1;

    public static final boolean isBlocking(@NotNull Task $this$isBlocking) {
        return $this$isBlocking.taskContext.getTaskMode() == 1;
    }
}
