package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: dynamicTypes.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/DynamicTypesKt.class */
public final class DynamicTypesKt {
    public static final boolean isDynamic(@NotNull KotlinType $this$isDynamic) {
        Intrinsics.checkNotNullParameter($this$isDynamic, "<this>");
        return $this$isDynamic.unwrap() instanceof DynamicType;
    }
}
