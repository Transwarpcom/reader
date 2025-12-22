package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultQualifiers;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: signatureEnhancement.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/typeEnhancement/TypeAndDefaultQualifiers.class */
final class TypeAndDefaultQualifiers {

    @NotNull
    private final KotlinType type;

    @Nullable
    private final JavaDefaultQualifiers defaultQualifiers;

    @Nullable
    private final TypeParameterDescriptor typeParameterForArgument;
    private final boolean isFromStarProjection;

    @NotNull
    public final KotlinType component1() {
        return this.type;
    }

    @Nullable
    public final JavaDefaultQualifiers component2() {
        return this.defaultQualifiers;
    }

    @Nullable
    public final TypeParameterDescriptor component3() {
        return this.typeParameterForArgument;
    }

    public final boolean component4() {
        return this.isFromStarProjection;
    }

    @NotNull
    public String toString() {
        return "TypeAndDefaultQualifiers(type=" + this.type + ", defaultQualifiers=" + this.defaultQualifiers + ", typeParameterForArgument=" + this.typeParameterForArgument + ", isFromStarProjection=" + this.isFromStarProjection + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((((this.type.hashCode() * 31) + (this.defaultQualifiers == null ? 0 : this.defaultQualifiers.hashCode())) * 31) + (this.typeParameterForArgument == null ? 0 : this.typeParameterForArgument.hashCode())) * 31;
        boolean z = this.isFromStarProjection;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return iHashCode + i;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TypeAndDefaultQualifiers)) {
            return false;
        }
        TypeAndDefaultQualifiers typeAndDefaultQualifiers = (TypeAndDefaultQualifiers) other;
        return Intrinsics.areEqual(this.type, typeAndDefaultQualifiers.type) && Intrinsics.areEqual(this.defaultQualifiers, typeAndDefaultQualifiers.defaultQualifiers) && Intrinsics.areEqual(this.typeParameterForArgument, typeAndDefaultQualifiers.typeParameterForArgument) && this.isFromStarProjection == typeAndDefaultQualifiers.isFromStarProjection;
    }

    public TypeAndDefaultQualifiers(@NotNull KotlinType type, @Nullable JavaDefaultQualifiers defaultQualifiers, @Nullable TypeParameterDescriptor typeParameterForArgument, boolean isFromStarProjection) {
        Intrinsics.checkNotNullParameter(type, "type");
        this.type = type;
        this.defaultQualifiers = defaultQualifiers;
        this.typeParameterForArgument = typeParameterForArgument;
        this.isFromStarProjection = isFromStarProjection;
    }

    @NotNull
    public final KotlinType getType() {
        return this.type;
    }
}
