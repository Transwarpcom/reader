package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.time.Duration;
import kotlin.time.ExperimentalTime;
import kotlinx.coroutines.flow.SharingStarted;
import org.jetbrains.annotations.NotNull;

/* compiled from: SharingStarted.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\u0014\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a-\u0010��\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0007ø\u0001��ø\u0001\u0001¢\u0006\u0004\b\u0006\u0010\u0007\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\b"}, d2 = {"WhileSubscribed", "Lkotlinx/coroutines/flow/SharingStarted;", "Lkotlinx/coroutines/flow/SharingStarted$Companion;", "stopTimeout", "Lkotlin/time/Duration;", "replayExpiration", "WhileSubscribed-5qebJ5I", "(Lkotlinx/coroutines/flow/SharingStarted$Companion;JJ)Lkotlinx/coroutines/flow/SharingStarted;", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/SharingStartedKt.class */
public final class SharingStartedKt {
    /* renamed from: WhileSubscribed-5qebJ5I$default, reason: not valid java name */
    public static /* synthetic */ SharingStarted m4284WhileSubscribed5qebJ5I$default(SharingStarted.Companion companion, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = Duration.Companion.m4114getZEROUwyO8pc();
        }
        if ((i & 2) != 0) {
            j2 = Duration.Companion.m4115getINFINITEUwyO8pc();
        }
        return m4283WhileSubscribed5qebJ5I(companion, j, j2);
    }

    @ExperimentalTime
    @NotNull
    /* renamed from: WhileSubscribed-5qebJ5I, reason: not valid java name */
    public static final SharingStarted m4283WhileSubscribed5qebJ5I(@NotNull SharingStarted.Companion $this$WhileSubscribed, long stopTimeout, long replayExpiration) {
        return new StartedWhileSubscribed(Duration.m4094getInWholeMillisecondsimpl(stopTimeout), Duration.m4094getInWholeMillisecondsimpl(replayExpiration));
    }
}
