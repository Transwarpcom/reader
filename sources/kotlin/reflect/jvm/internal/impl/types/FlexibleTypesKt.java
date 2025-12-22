package kotlin.reflect.jvm.internal.impl.types;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: flexibleTypes.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/FlexibleTypesKt.class */
public final class FlexibleTypesKt {
    public static final boolean isFlexible(@NotNull KotlinType $this$isFlexible) {
        Intrinsics.checkNotNullParameter($this$isFlexible, "<this>");
        return $this$isFlexible.unwrap() instanceof FlexibleType;
    }

    @NotNull
    public static final FlexibleType asFlexibleType(@NotNull KotlinType $this$asFlexibleType) {
        Intrinsics.checkNotNullParameter($this$asFlexibleType, "<this>");
        return (FlexibleType) $this$asFlexibleType.unwrap();
    }

    @NotNull
    public static final SimpleType lowerIfFlexible(@NotNull KotlinType $this$lowerIfFlexible) {
        Intrinsics.checkNotNullParameter($this$lowerIfFlexible, "<this>");
        UnwrappedType $this$lowerIfFlexible_u24lambda_u2d4 = $this$lowerIfFlexible.unwrap();
        if ($this$lowerIfFlexible_u24lambda_u2d4 instanceof FlexibleType) {
            return ((FlexibleType) $this$lowerIfFlexible_u24lambda_u2d4).getLowerBound();
        }
        if ($this$lowerIfFlexible_u24lambda_u2d4 instanceof SimpleType) {
            return (SimpleType) $this$lowerIfFlexible_u24lambda_u2d4;
        }
        throw new NoWhenBranchMatchedException();
    }

    @NotNull
    public static final SimpleType upperIfFlexible(@NotNull KotlinType $this$upperIfFlexible) {
        Intrinsics.checkNotNullParameter($this$upperIfFlexible, "<this>");
        UnwrappedType $this$upperIfFlexible_u24lambda_u2d5 = $this$upperIfFlexible.unwrap();
        if ($this$upperIfFlexible_u24lambda_u2d5 instanceof FlexibleType) {
            return ((FlexibleType) $this$upperIfFlexible_u24lambda_u2d5).getUpperBound();
        }
        if ($this$upperIfFlexible_u24lambda_u2d5 instanceof SimpleType) {
            return (SimpleType) $this$upperIfFlexible_u24lambda_u2d5;
        }
        throw new NoWhenBranchMatchedException();
    }
}
