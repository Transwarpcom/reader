package mu.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: MessageInvoker.kt */
@Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0010\n��\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010��\n��\u001a\u0015\u0010��\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H\u0080\b¨\u0006\u0004"}, d2 = {"toStringSafe", "", "Lkotlin/Function0;", "", "kotlin-logging"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-logging-1.6.24.jar:mu/internal/MessageInvokerKt.class */
public final class MessageInvokerKt {
    @NotNull
    public static final String toStringSafe(@NotNull Function0<? extends Object> receiver$0) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        try {
            strValueOf = String.valueOf(receiver$0.invoke());
        } catch (Exception e) {
            strValueOf = "Log message invocation failed: " + e;
        }
        return strValueOf;
    }
}
