package kotlinx.coroutines.scheduling;

import kotlin.Metadata;

/* compiled from: WorkQueue.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\u0012\n��\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\"\u000e\u0010��\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n��\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n��\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n��\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n��\"\u000e\u0010\u0006\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n��¨\u0006\u0007"}, d2 = {"BUFFER_CAPACITY", "", "BUFFER_CAPACITY_BASE", "MASK", "NOTHING_TO_STEAL", "", "TASK_STOLEN", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/scheduling/WorkQueueKt.class */
public final class WorkQueueKt {
    public static final int BUFFER_CAPACITY_BASE = 7;
    public static final int BUFFER_CAPACITY = 128;
    public static final int MASK = 127;
    public static final long TASK_STOLEN = -1;
    public static final long NOTHING_TO_STEAL = -2;
}
