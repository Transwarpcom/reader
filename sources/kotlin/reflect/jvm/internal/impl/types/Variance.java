package kotlin.reflect.jvm.internal.impl.types;

import org.jetbrains.annotations.NotNull;

/* compiled from: Variance.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/Variance.class */
public enum Variance {
    INVARIANT("", true, true, 0),
    IN_VARIANCE("in", true, false, -1),
    OUT_VARIANCE("out", false, true, 1);


    @NotNull
    private final String label;
    private final boolean allowsInPosition;
    private final boolean allowsOutPosition;
    private final int superpositionFactor;

    Variance(String label, boolean allowsInPosition, boolean allowsOutPosition, int superpositionFactor) {
        this.label = label;
        this.allowsInPosition = allowsInPosition;
        this.allowsOutPosition = allowsOutPosition;
        this.superpositionFactor = superpositionFactor;
    }

    @NotNull
    public final String getLabel() {
        return this.label;
    }

    public final boolean getAllowsOutPosition() {
        return this.allowsOutPosition;
    }

    @Override // java.lang.Enum
    @NotNull
    public String toString() {
        return this.label;
    }
}
