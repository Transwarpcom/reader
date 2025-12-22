package kotlin.reflect.jvm.internal.impl.types;

import org.jetbrains.annotations.NotNull;

/* compiled from: TypeCapabilities.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/CustomTypeVariable.class */
public interface CustomTypeVariable {
    boolean isTypeVariable();

    @NotNull
    KotlinType substitutionResult(@NotNull KotlinType kotlinType);
}
