package kotlin.reflect.jvm.internal.impl.types.model;

import org.jetbrains.annotations.NotNull;

/* compiled from: TypeSystemContext.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/model/TypeVariance.class */
public enum TypeVariance {
    IN("in"),
    OUT("out"),
    INV("");


    @NotNull
    private final String presentation;

    TypeVariance(String presentation) {
        this.presentation = presentation;
    }

    @Override // java.lang.Enum
    @NotNull
    public String toString() {
        return this.presentation;
    }
}
