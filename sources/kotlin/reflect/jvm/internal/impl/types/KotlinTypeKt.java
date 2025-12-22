package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinType.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/KotlinTypeKt.class */
public final class KotlinTypeKt {
    public static final boolean isNullable(@NotNull KotlinType $this$isNullable) {
        Intrinsics.checkNotNullParameter($this$isNullable, "<this>");
        return TypeUtils.isNullableType($this$isNullable);
    }

    public static final boolean isError(@NotNull KotlinType $this$isError) {
        Intrinsics.checkNotNullParameter($this$isError, "<this>");
        UnwrappedType unwrapped = $this$isError.unwrap();
        return (unwrapped instanceof ErrorType) || ((unwrapped instanceof FlexibleType) && (((FlexibleType) unwrapped).getDelegate() instanceof ErrorType));
    }
}
