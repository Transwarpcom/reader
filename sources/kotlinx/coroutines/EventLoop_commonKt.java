package kotlinx.coroutines;

import kotlin.Metadata;
import kotlinx.coroutines.internal.Symbol;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* compiled from: EventLoop.common.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"�� \n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n��\u001a\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0007H��\u001a\u0010\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007H��\"\u0016\u0010��\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n��\u0012\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0004\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n��\u0012\u0004\b\u0005\u0010\u0003\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n��\"\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n��\"\u000e\u0010\t\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n��\"\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n��\"\u000e\u0010\f\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n��\"\u000e\u0010\r\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n��*\u001e\b\u0002\u0010\u0012\u001a\u0004\b��\u0010\u0013\"\b\u0012\u0004\u0012\u0002H\u00130\u00142\b\u0012\u0004\u0012\u0002H\u00130\u0014¨\u0006\u0015"}, d2 = {"CLOSED_EMPTY", "Lkotlinx/coroutines/internal/Symbol;", "getCLOSED_EMPTY$annotations", "()V", "DISPOSED_TASK", "getDISPOSED_TASK$annotations", "MAX_DELAY_NS", "", "MAX_MS", "MS_TO_NS", "SCHEDULE_COMPLETED", "", "SCHEDULE_DISPOSED", "SCHEDULE_OK", "delayNanosToMillis", "timeNanos", "delayToNanos", "timeMillis", "Queue", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/EventLoop_commonKt.class */
public final class EventLoop_commonKt {
    private static final int SCHEDULE_OK = 0;
    private static final int SCHEDULE_COMPLETED = 1;
    private static final int SCHEDULE_DISPOSED = 2;
    private static final long MS_TO_NS = 1000000;
    private static final long MAX_MS = 9223372036854L;
    private static final long MAX_DELAY_NS = 4611686018427387903L;

    @NotNull
    private static final Symbol DISPOSED_TASK = new Symbol("REMOVED_TASK");

    @NotNull
    private static final Symbol CLOSED_EMPTY = new Symbol("CLOSED_EMPTY");

    private static /* synthetic */ void getDISPOSED_TASK$annotations() {
    }

    private static /* synthetic */ void getCLOSED_EMPTY$annotations() {
    }

    public static final long delayToNanos(long timeMillis) {
        if (timeMillis <= 0) {
            return 0L;
        }
        if (timeMillis >= MAX_MS) {
            return Long.MAX_VALUE;
        }
        return timeMillis * 1000000;
    }

    public static final long delayNanosToMillis(long timeNanos) {
        return timeNanos / 1000000;
    }
}
