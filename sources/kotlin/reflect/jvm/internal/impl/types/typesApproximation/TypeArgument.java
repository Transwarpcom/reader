package kotlin.reflect.jvm.internal.impl.types.typesApproximation;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import org.jetbrains.annotations.NotNull;

/* compiled from: CapturedTypeApproximation.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/typesApproximation/TypeArgument.class */
final class TypeArgument {

    @NotNull
    private final TypeParameterDescriptor typeParameter;

    @NotNull
    private final KotlinType inProjection;

    @NotNull
    private final KotlinType outProjection;

    public TypeArgument(@NotNull TypeParameterDescriptor typeParameter, @NotNull KotlinType inProjection, @NotNull KotlinType outProjection) {
        Intrinsics.checkNotNullParameter(typeParameter, "typeParameter");
        Intrinsics.checkNotNullParameter(inProjection, "inProjection");
        Intrinsics.checkNotNullParameter(outProjection, "outProjection");
        this.typeParameter = typeParameter;
        this.inProjection = inProjection;
        this.outProjection = outProjection;
    }

    @NotNull
    public final TypeParameterDescriptor getTypeParameter() {
        return this.typeParameter;
    }

    @NotNull
    public final KotlinType getInProjection() {
        return this.inProjection;
    }

    @NotNull
    public final KotlinType getOutProjection() {
        return this.outProjection;
    }

    public final boolean isConsistent() {
        return KotlinTypeChecker.DEFAULT.isSubtypeOf(this.inProjection, this.outProjection);
    }
}
