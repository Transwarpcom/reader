package kotlinx.coroutines;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* compiled from: DefaultExecutor.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\n\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0014\u0010��\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"DefaultDelay", "Lkotlinx/coroutines/Delay;", "getDefaultDelay", "()Lkotlinx/coroutines/Delay;", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/DefaultExecutorKt.class */
public final class DefaultExecutorKt {

    @NotNull
    private static final Delay DefaultDelay = DefaultExecutor.INSTANCE;

    @NotNull
    public static final Delay getDefaultDelay() {
        return DefaultDelay;
    }
}
