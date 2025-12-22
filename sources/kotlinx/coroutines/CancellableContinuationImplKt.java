package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.debug.internal.DebugCoroutineInfoImplKt;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;

/* compiled from: CancellableContinuationImpl.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\u0010\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u000e\u0010��\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n��\"\u0016\u0010\u0002\u001a\u00020\u00038��X\u0081\u0004¢\u0006\b\n��\u0012\u0004\b\u0004\u0010\u0005\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n��\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n��¨\u0006\b"}, d2 = {"RESUMED", "", "RESUME_TOKEN", "Lkotlinx/coroutines/internal/Symbol;", "getRESUME_TOKEN$annotations", "()V", DebugCoroutineInfoImplKt.SUSPENDED, "UNDECIDED", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/CancellableContinuationImplKt.class */
public final class CancellableContinuationImplKt {
    private static final int UNDECIDED = 0;
    private static final int SUSPENDED = 1;
    private static final int RESUMED = 2;

    @JvmField
    @NotNull
    public static final Symbol RESUME_TOKEN = new Symbol("RESUME_TOKEN");

    public static /* synthetic */ void getRESUME_TOKEN$annotations() {
    }
}
