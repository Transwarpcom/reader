package kotlin.reflect.jvm.internal.impl.types;

import kotlin._Assertions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedType;
import kotlin.reflect.jvm.internal.impl.types.checker.NewTypeVariableConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.NullabilityChecker;
import kotlin.reflect.jvm.internal.impl.types.model.DefinitelyNotNullTypeMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SpecialTypes.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/DefinitelyNotNullType.class */
public final class DefinitelyNotNullType extends DelegatingSimpleType implements CustomTypeVariable, DefinitelyNotNullTypeMarker {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final SimpleType original;
    private final boolean useCorrectedNullabilityForTypeParameters;

    public /* synthetic */ DefinitelyNotNullType(SimpleType original, boolean useCorrectedNullabilityForTypeParameters, DefaultConstructorMarker $constructor_marker) {
        this(original, useCorrectedNullabilityForTypeParameters);
    }

    @NotNull
    public final SimpleType getOriginal() {
        return this.original;
    }

    private DefinitelyNotNullType(SimpleType original, boolean useCorrectedNullabilityForTypeParameters) {
        this.original = original;
        this.useCorrectedNullabilityForTypeParameters = useCorrectedNullabilityForTypeParameters;
    }

    /* compiled from: SpecialTypes.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/DefinitelyNotNullType$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ DefinitelyNotNullType makeDefinitelyNotNull$default(Companion companion, UnwrappedType unwrappedType, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            return companion.makeDefinitelyNotNull(unwrappedType, z);
        }

        @Nullable
        public final DefinitelyNotNullType makeDefinitelyNotNull(@NotNull UnwrappedType type, boolean useCorrectedNullabilityForTypeParameters) {
            Intrinsics.checkNotNullParameter(type, "type");
            if (type instanceof DefinitelyNotNullType) {
                return (DefinitelyNotNullType) type;
            }
            if (makesSenseToBeDefinitelyNotNull(type, useCorrectedNullabilityForTypeParameters)) {
                if (type instanceof FlexibleType) {
                    boolean zAreEqual = Intrinsics.areEqual(((FlexibleType) type).getLowerBound().getConstructor(), ((FlexibleType) type).getUpperBound().getConstructor());
                    if (_Assertions.ENABLED && !zAreEqual) {
                        throw new AssertionError("DefinitelyNotNullType for flexible type (" + type + ") can be created only from type variable with the same constructor for bounds");
                    }
                }
                return new DefinitelyNotNullType(FlexibleTypesKt.lowerIfFlexible(type), useCorrectedNullabilityForTypeParameters, null);
            }
            return null;
        }

        private final boolean makesSenseToBeDefinitelyNotNull(UnwrappedType type, boolean useCorrectedNullabilityForFlexibleTypeParameters) {
            if (!canHaveUndefinedNullability(type)) {
                return false;
            }
            if (useCorrectedNullabilityForFlexibleTypeParameters && (type.getConstructor().mo3831getDeclarationDescriptor() instanceof TypeParameterDescriptor)) {
                return TypeUtils.isNullableType(type);
            }
            return !NullabilityChecker.INSTANCE.isSubtypeOfAny(type);
        }

        private final boolean canHaveUndefinedNullability(UnwrappedType $this$canHaveUndefinedNullability) {
            return ($this$canHaveUndefinedNullability.getConstructor() instanceof NewTypeVariableConstructor) || ($this$canHaveUndefinedNullability.getConstructor().mo3831getDeclarationDescriptor() instanceof TypeParameterDescriptor) || ($this$canHaveUndefinedNullability instanceof NewCapturedType);
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    @NotNull
    protected SimpleType getDelegate() {
        return this.original;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType, kotlin.reflect.jvm.internal.impl.types.KotlinType
    public boolean isMarkedNullable() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.CustomTypeVariable
    public boolean isTypeVariable() {
        return (getDelegate().getConstructor() instanceof NewTypeVariableConstructor) || (getDelegate().getConstructor().mo3831getDeclarationDescriptor() instanceof TypeParameterDescriptor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.CustomTypeVariable
    @NotNull
    public KotlinType substitutionResult(@NotNull KotlinType replacement) {
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        return SpecialTypesKt.makeDefinitelyNotNullOrNotNull(replacement.unwrap(), this.useCorrectedNullabilityForTypeParameters);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    @NotNull
    public DefinitelyNotNullType replaceAnnotations(@NotNull Annotations newAnnotations) {
        Intrinsics.checkNotNullParameter(newAnnotations, "newAnnotations");
        return new DefinitelyNotNullType(getDelegate().replaceAnnotations(newAnnotations), this.useCorrectedNullabilityForTypeParameters);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    @NotNull
    public SimpleType makeNullableAsSpecified(boolean newNullability) {
        return newNullability ? getDelegate().makeNullableAsSpecified(newNullability) : this;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.SimpleType
    @NotNull
    public String toString() {
        return getDelegate() + "!!";
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    @NotNull
    public DefinitelyNotNullType replaceDelegate(@NotNull SimpleType delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        return new DefinitelyNotNullType(delegate, this.useCorrectedNullabilityForTypeParameters);
    }
}
