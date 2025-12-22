package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TypeCapabilities.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/TypeCapabilitiesKt.class */
public final class TypeCapabilitiesKt {
    public static final boolean isCustomTypeVariable(@NotNull KotlinType $this$isCustomTypeVariable) {
        Intrinsics.checkNotNullParameter($this$isCustomTypeVariable, "<this>");
        Object objUnwrap = $this$isCustomTypeVariable.unwrap();
        CustomTypeVariable customTypeVariable = objUnwrap instanceof CustomTypeVariable ? (CustomTypeVariable) objUnwrap : null;
        if (customTypeVariable == null) {
            return false;
        }
        return customTypeVariable.isTypeVariable();
    }

    @Nullable
    public static final CustomTypeVariable getCustomTypeVariable(@NotNull KotlinType $this$getCustomTypeVariable) {
        Intrinsics.checkNotNullParameter($this$getCustomTypeVariable, "<this>");
        Object objUnwrap = $this$getCustomTypeVariable.unwrap();
        CustomTypeVariable it = objUnwrap instanceof CustomTypeVariable ? (CustomTypeVariable) objUnwrap : null;
        if (it != null && it.isTypeVariable()) {
            return it;
        }
        return null;
    }
}
