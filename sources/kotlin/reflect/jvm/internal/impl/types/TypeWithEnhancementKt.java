package kotlin.reflect.jvm.internal.impl.types;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TypeWithEnhancement.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/TypeWithEnhancementKt.class */
public final class TypeWithEnhancementKt {
    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public static final KotlinType getEnhancement(@NotNull KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        if (kotlinType instanceof TypeWithEnhancement) {
            return ((TypeWithEnhancement) kotlinType).getEnhancement();
        }
        return null;
    }

    @NotNull
    public static final KotlinType unwrapEnhancement(@NotNull KotlinType $this$unwrapEnhancement) {
        Intrinsics.checkNotNullParameter($this$unwrapEnhancement, "<this>");
        KotlinType enhancement = getEnhancement($this$unwrapEnhancement);
        return enhancement == null ? $this$unwrapEnhancement : enhancement;
    }

    @NotNull
    public static final UnwrappedType inheritEnhancement(@NotNull UnwrappedType $this$inheritEnhancement, @NotNull KotlinType origin) {
        Intrinsics.checkNotNullParameter($this$inheritEnhancement, "<this>");
        Intrinsics.checkNotNullParameter(origin, "origin");
        return wrapEnhancement($this$inheritEnhancement, getEnhancement(origin));
    }

    @NotNull
    public static final UnwrappedType wrapEnhancement(@NotNull UnwrappedType $this$wrapEnhancement, @Nullable KotlinType enhancement) {
        Intrinsics.checkNotNullParameter($this$wrapEnhancement, "<this>");
        if (enhancement == null) {
            return $this$wrapEnhancement;
        }
        if ($this$wrapEnhancement instanceof SimpleType) {
            return new SimpleTypeWithEnhancement((SimpleType) $this$wrapEnhancement, enhancement);
        }
        if ($this$wrapEnhancement instanceof FlexibleType) {
            return new FlexibleTypeWithEnhancement((FlexibleType) $this$wrapEnhancement, enhancement);
        }
        throw new NoWhenBranchMatchedException();
    }
}
