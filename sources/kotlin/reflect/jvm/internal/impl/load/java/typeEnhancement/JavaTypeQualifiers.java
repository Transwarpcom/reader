package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: typeQualifiers.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/typeEnhancement/JavaTypeQualifiers.class */
public final class JavaTypeQualifiers {

    @Nullable
    private final NullabilityQualifier nullability;

    @Nullable
    private final MutabilityQualifier mutability;
    private final boolean isNotNullTypeParameter;
    private final boolean isNullabilityQualifierForWarning;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final JavaTypeQualifiers NONE = new JavaTypeQualifiers(null, null, false, false, 8, null);

    public JavaTypeQualifiers(@Nullable NullabilityQualifier nullability, @Nullable MutabilityQualifier mutability, boolean isNotNullTypeParameter, boolean isNullabilityQualifierForWarning) {
        this.nullability = nullability;
        this.mutability = mutability;
        this.isNotNullTypeParameter = isNotNullTypeParameter;
        this.isNullabilityQualifierForWarning = isNullabilityQualifierForWarning;
    }

    public /* synthetic */ JavaTypeQualifiers(NullabilityQualifier nullabilityQualifier, MutabilityQualifier mutabilityQualifier, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nullabilityQualifier, mutabilityQualifier, z, (i & 8) != 0 ? false : z2);
    }

    @Nullable
    public final NullabilityQualifier getNullability() {
        return this.nullability;
    }

    @Nullable
    public final MutabilityQualifier getMutability() {
        return this.mutability;
    }

    public final boolean isNotNullTypeParameter() {
        return this.isNotNullTypeParameter;
    }

    public final boolean isNullabilityQualifierForWarning() {
        return this.isNullabilityQualifierForWarning;
    }

    /* compiled from: typeQualifiers.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/typeEnhancement/JavaTypeQualifiers$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final JavaTypeQualifiers getNONE() {
            return JavaTypeQualifiers.NONE;
        }
    }
}
