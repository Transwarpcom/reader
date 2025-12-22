package kotlin.reflect.jvm.internal.impl.types;

import org.jetbrains.annotations.NotNull;

/* compiled from: TypeWithEnhancement.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/TypeWithEnhancement.class */
public interface TypeWithEnhancement {
    @NotNull
    UnwrappedType getOrigin();

    @NotNull
    KotlinType getEnhancement();
}
