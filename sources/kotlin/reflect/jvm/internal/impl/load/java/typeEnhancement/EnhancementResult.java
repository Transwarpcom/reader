package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: typeEnhancement.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/typeEnhancement/EnhancementResult.class */
final class EnhancementResult<T> {
    private final T result;

    @Nullable
    private final Annotations enhancementAnnotations;

    public final T component1() {
        return this.result;
    }

    @Nullable
    public final Annotations component2() {
        return this.enhancementAnnotations;
    }

    @NotNull
    public String toString() {
        return "EnhancementResult(result=" + this.result + ", enhancementAnnotations=" + this.enhancementAnnotations + ')';
    }

    public int hashCode() {
        int result = this.result == null ? 0 : this.result.hashCode();
        return (result * 31) + (this.enhancementAnnotations == null ? 0 : this.enhancementAnnotations.hashCode());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EnhancementResult)) {
            return false;
        }
        EnhancementResult enhancementResult = (EnhancementResult) other;
        return Intrinsics.areEqual(this.result, enhancementResult.result) && Intrinsics.areEqual(this.enhancementAnnotations, enhancementResult.enhancementAnnotations);
    }

    public EnhancementResult(T t, @Nullable Annotations enhancementAnnotations) {
        this.result = t;
        this.enhancementAnnotations = enhancementAnnotations;
    }
}
