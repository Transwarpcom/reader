package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.internal.DispatchedContinuation;
import org.jetbrains.annotations.NotNull;

/* compiled from: DebugStrings.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\u0014\n��\n\u0002\u0010\u000e\n\u0002\u0010��\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\u001a\u0010\u0010\u0007\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\bH��\"\u0018\u0010��\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0018\u0010\u0005\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004¨\u0006\t"}, d2 = {"classSimpleName", "", "", "getClassSimpleName", "(Ljava/lang/Object;)Ljava/lang/String;", "hexAddress", "getHexAddress", "toDebugString", "Lkotlin/coroutines/Continuation;", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/DebugStringsKt.class */
public final class DebugStringsKt {
    @NotNull
    public static final String getHexAddress(@NotNull Object $this$hexAddress) {
        return Integer.toHexString(System.identityHashCode($this$hexAddress));
    }

    @NotNull
    public static final String toDebugString(@NotNull Continuation<?> continuation) {
        Object objM2105constructorimpl;
        if (continuation instanceof DispatchedContinuation) {
            return continuation.toString();
        }
        try {
            Result.Companion companion = Result.Companion;
            objM2105constructorimpl = Result.m2105constructorimpl(continuation + '@' + getHexAddress(continuation));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            objM2105constructorimpl = Result.m2105constructorimpl(ResultKt.createFailure(th));
        }
        Object obj = objM2105constructorimpl;
        return (String) (Result.m2103exceptionOrNullimpl(obj) == null ? obj : ((Object) continuation.getClass().getName()) + '@' + getHexAddress(continuation));
    }

    @NotNull
    public static final String getClassSimpleName(@NotNull Object $this$classSimpleName) {
        return $this$classSimpleName.getClass().getSimpleName();
    }
}
