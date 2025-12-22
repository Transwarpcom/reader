package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JobSupport.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\"\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010��\n\u0002\b\u0002\u001a\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016*\u0004\u0018\u00010\u0016H��\u001a\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0016*\u0004\u0018\u00010\u0016H��\"\u0016\u0010��\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n��\u0012\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0004\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n��\u0012\u0004\b\u0005\u0010\u0003\"\u0016\u0010\u0006\u001a\u00020\u00018��X\u0081\u0004¢\u0006\b\n��\u0012\u0004\b\u0007\u0010\u0003\"\u0016\u0010\b\u001a\u00020\t8\u0002X\u0083\u0004¢\u0006\b\n��\u0012\u0004\b\n\u0010\u0003\"\u0016\u0010\u000b\u001a\u00020\t8\u0002X\u0083\u0004¢\u0006\b\n��\u0012\u0004\b\f\u0010\u0003\"\u000e\u0010\r\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n��\"\u000e\u0010\u000f\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n��\"\u0016\u0010\u0010\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n��\u0012\u0004\b\u0011\u0010\u0003\"\u0016\u0010\u0012\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n��\u0012\u0004\b\u0013\u0010\u0003\"\u000e\u0010\u0014\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n��¨\u0006\u0018"}, d2 = {"COMPLETING_ALREADY", "Lkotlinx/coroutines/internal/Symbol;", "getCOMPLETING_ALREADY$annotations", "()V", "COMPLETING_RETRY", "getCOMPLETING_RETRY$annotations", "COMPLETING_WAITING_CHILDREN", "getCOMPLETING_WAITING_CHILDREN$annotations", "EMPTY_ACTIVE", "Lkotlinx/coroutines/Empty;", "getEMPTY_ACTIVE$annotations", "EMPTY_NEW", "getEMPTY_NEW$annotations", "FALSE", "", "RETRY", "SEALED", "getSEALED$annotations", "TOO_LATE_TO_CANCEL", "getTOO_LATE_TO_CANCEL$annotations", "TRUE", "boxIncomplete", "", "unboxState", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/JobSupportKt.class */
public final class JobSupportKt {
    private static final int RETRY = -1;
    private static final int FALSE = 0;
    private static final int TRUE = 1;

    @NotNull
    private static final Symbol COMPLETING_ALREADY = new Symbol("COMPLETING_ALREADY");

    @JvmField
    @NotNull
    public static final Symbol COMPLETING_WAITING_CHILDREN = new Symbol("COMPLETING_WAITING_CHILDREN");

    @NotNull
    private static final Symbol COMPLETING_RETRY = new Symbol("COMPLETING_RETRY");

    @NotNull
    private static final Symbol TOO_LATE_TO_CANCEL = new Symbol("TOO_LATE_TO_CANCEL");

    @NotNull
    private static final Symbol SEALED = new Symbol("SEALED");

    @NotNull
    private static final Empty EMPTY_NEW = new Empty(false);

    @NotNull
    private static final Empty EMPTY_ACTIVE = new Empty(true);

    private static /* synthetic */ void getCOMPLETING_ALREADY$annotations() {
    }

    public static /* synthetic */ void getCOMPLETING_WAITING_CHILDREN$annotations() {
    }

    private static /* synthetic */ void getCOMPLETING_RETRY$annotations() {
    }

    private static /* synthetic */ void getTOO_LATE_TO_CANCEL$annotations() {
    }

    private static /* synthetic */ void getSEALED$annotations() {
    }

    private static /* synthetic */ void getEMPTY_NEW$annotations() {
    }

    private static /* synthetic */ void getEMPTY_ACTIVE$annotations() {
    }

    @Nullable
    public static final Object boxIncomplete(@Nullable Object $this$boxIncomplete) {
        return $this$boxIncomplete instanceof Incomplete ? new IncompleteStateBox((Incomplete) $this$boxIncomplete) : $this$boxIncomplete;
    }

    @Nullable
    public static final Object unboxState(@Nullable Object $this$unboxState) {
        IncompleteStateBox incompleteStateBox = $this$unboxState instanceof IncompleteStateBox ? (IncompleteStateBox) $this$unboxState : null;
        return incompleteStateBox == null ? $this$unboxState : incompleteStateBox.state;
    }
}
