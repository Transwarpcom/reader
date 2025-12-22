package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: TypeComponentPosition.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/typeEnhancement/TypeComponentPositionKt.class */
public final class TypeComponentPositionKt {
    public static final boolean shouldEnhance(@NotNull TypeComponentPosition $this$shouldEnhance) {
        Intrinsics.checkNotNullParameter($this$shouldEnhance, "<this>");
        return $this$shouldEnhance != TypeComponentPosition.INFLEXIBLE;
    }
}
