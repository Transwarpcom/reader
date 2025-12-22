package kotlin.reflect.jvm.internal.impl.types;

import ch.qos.logback.core.CoreConstants;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.CompositeAnnotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.FilteredAnnotations;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt;
import kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt;
import kotlin.reflect.jvm.internal.impl.utils.ExceptionUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/TypeSubstitutor.class */
public class TypeSubstitutor {
    public static final TypeSubstitutor EMPTY;

    @NotNull
    private final TypeSubstitution substitution;
    static final /* synthetic */ boolean $assertionsDisabled;

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/TypeSubstitutor$VarianceConflictType.class */
    private enum VarianceConflictType {
        NO_CONFLICT,
        IN_IN_OUT_POSITION,
        OUT_IN_IN_POSITION
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 0:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
            case 10:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 25:
            case 26:
            case 27:
            case 32:
            case 34:
            case 35:
            case 37:
            case 38:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 1:
            case 2:
            case 8:
            case 11:
            case 12:
            case 13:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 28:
            case 29:
            case 30:
            case 31:
            case 33:
            case 36:
            case 39:
            case 40:
            case 41:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 0:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
            case 10:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 25:
            case 26:
            case 27:
            case 32:
            case 34:
            case 35:
            case 37:
            case 38:
            default:
                i2 = 3;
                break;
            case 1:
            case 2:
            case 8:
            case 11:
            case 12:
            case 13:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 28:
            case 29:
            case 30:
            case 31:
            case 33:
            case 36:
            case 39:
            case 40:
            case 41:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            case 7:
            default:
                objArr[0] = "substitution";
                break;
            case 1:
            case 2:
            case 8:
            case 11:
            case 12:
            case 13:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 28:
            case 29:
            case 30:
            case 31:
            case 33:
            case 36:
            case 39:
            case 40:
            case 41:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/types/TypeSubstitutor";
                break;
            case 3:
                objArr[0] = "first";
                break;
            case 4:
                objArr[0] = "second";
                break;
            case 5:
                objArr[0] = "substitutionContext";
                break;
            case 6:
                objArr[0] = CoreConstants.CONTEXT_SCOPE_VALUE;
                break;
            case 9:
            case 14:
                objArr[0] = "type";
                break;
            case 10:
            case 15:
                objArr[0] = "howThisTypeIsUsed";
                break;
            case 16:
            case 17:
            case 35:
                objArr[0] = "typeProjection";
                break;
            case 18:
            case 27:
                objArr[0] = "originalProjection";
                break;
            case 25:
                objArr[0] = "originalType";
                break;
            case 26:
                objArr[0] = "substituted";
                break;
            case 32:
                objArr[0] = "annotations";
                break;
            case 34:
            case 37:
                objArr[0] = "typeParameterVariance";
                break;
            case 38:
                objArr[0] = "projectionKind";
                break;
        }
        switch (i) {
            case 0:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
            case 10:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 25:
            case 26:
            case 27:
            case 32:
            case 34:
            case 35:
            case 37:
            case 38:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/types/TypeSubstitutor";
                break;
            case 1:
                objArr[1] = "replaceWithNonApproximatingSubstitution";
                break;
            case 2:
                objArr[1] = "replaceWithContravariantApproximatingSubstitution";
                break;
            case 8:
                objArr[1] = "getSubstitution";
                break;
            case 11:
            case 12:
            case 13:
                objArr[1] = "safeSubstitute";
                break;
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
                objArr[1] = "unsafeSubstitute";
                break;
            case 28:
            case 29:
            case 30:
            case 31:
                objArr[1] = "projectedTypeForConflictedTypeWithUnsafeVariance";
                break;
            case 33:
                objArr[1] = "filterOutUnsafeVariance";
                break;
            case 36:
            case 39:
            case 40:
            case 41:
                objArr[1] = "combine";
                break;
        }
        switch (i) {
            case 0:
            case 5:
            case 6:
            default:
                objArr[2] = "create";
                break;
            case 1:
            case 2:
            case 8:
            case 11:
            case 12:
            case 13:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 28:
            case 29:
            case 30:
            case 31:
            case 33:
            case 36:
            case 39:
            case 40:
            case 41:
                break;
            case 3:
            case 4:
                objArr[2] = "createChainedSubstitutor";
                break;
            case 7:
                objArr[2] = Constants.CONSTRUCTOR_NAME;
                break;
            case 9:
            case 10:
                objArr[2] = "safeSubstitute";
                break;
            case 14:
            case 15:
            case 16:
                objArr[2] = "substitute";
                break;
            case 17:
                objArr[2] = "substituteWithoutApproximation";
                break;
            case 18:
                objArr[2] = "unsafeSubstitute";
                break;
            case 25:
            case 26:
            case 27:
                objArr[2] = "projectedTypeForConflictedTypeWithUnsafeVariance";
                break;
            case 32:
                objArr[2] = "filterOutUnsafeVariance";
                break;
            case 34:
            case 35:
            case 37:
            case 38:
                objArr[2] = "combine";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 0:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
            case 10:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 25:
            case 26:
            case 27:
            case 32:
            case 34:
            case 35:
            case 37:
            case 38:
            default:
                throw new IllegalArgumentException(str2);
            case 1:
            case 2:
            case 8:
            case 11:
            case 12:
            case 13:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 28:
            case 29:
            case 30:
            case 31:
            case 33:
            case 36:
            case 39:
            case 40:
            case 41:
                throw new IllegalStateException(str2);
        }
    }

    static {
        $assertionsDisabled = !TypeSubstitutor.class.desiredAssertionStatus();
        EMPTY = create(TypeSubstitution.EMPTY);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/TypeSubstitutor$SubstitutionException.class */
    private static final class SubstitutionException extends Exception {
        public SubstitutionException(String message) {
            super(message);
        }
    }

    @NotNull
    public static TypeSubstitutor create(@NotNull TypeSubstitution substitution) {
        if (substitution == null) {
            $$$reportNull$$$0(0);
        }
        return new TypeSubstitutor(substitution);
    }

    @NotNull
    public TypeSubstitutor replaceWithNonApproximatingSubstitution() {
        if ((this.substitution instanceof IndexedParametersSubstitution) && this.substitution.approximateContravariantCapturedTypes()) {
            return new TypeSubstitutor(new IndexedParametersSubstitution(((IndexedParametersSubstitution) this.substitution).getParameters(), ((IndexedParametersSubstitution) this.substitution).getArguments(), false));
        }
        if (this == null) {
            $$$reportNull$$$0(1);
        }
        return this;
    }

    @NotNull
    public static TypeSubstitutor createChainedSubstitutor(@NotNull TypeSubstitution first, @NotNull TypeSubstitution second) {
        if (first == null) {
            $$$reportNull$$$0(3);
        }
        if (second == null) {
            $$$reportNull$$$0(4);
        }
        return create(DisjointKeysUnionTypeSubstitution.create(first, second));
    }

    @NotNull
    public static TypeSubstitutor create(@NotNull KotlinType context) {
        if (context == null) {
            $$$reportNull$$$0(6);
        }
        return create(TypeConstructorSubstitution.create(context.getConstructor(), context.getArguments()));
    }

    protected TypeSubstitutor(@NotNull TypeSubstitution substitution) {
        if (substitution == null) {
            $$$reportNull$$$0(7);
        }
        this.substitution = substitution;
    }

    public boolean isEmpty() {
        return this.substitution.isEmpty();
    }

    @NotNull
    public TypeSubstitution getSubstitution() {
        TypeSubstitution typeSubstitution = this.substitution;
        if (typeSubstitution == null) {
            $$$reportNull$$$0(8);
        }
        return typeSubstitution;
    }

    @NotNull
    public KotlinType safeSubstitute(@NotNull KotlinType type, @NotNull Variance howThisTypeIsUsed) {
        if (type == null) {
            $$$reportNull$$$0(9);
        }
        if (howThisTypeIsUsed == null) {
            $$$reportNull$$$0(10);
        }
        if (isEmpty()) {
            if (type == null) {
                $$$reportNull$$$0(11);
            }
            return type;
        }
        try {
            KotlinType type2 = unsafeSubstitute(new TypeProjectionImpl(howThisTypeIsUsed, type), null, 0).getType();
            if (type2 == null) {
                $$$reportNull$$$0(12);
            }
            return type2;
        } catch (SubstitutionException e) {
            SimpleType simpleTypeCreateErrorType = ErrorUtils.createErrorType(e.getMessage());
            if (simpleTypeCreateErrorType == null) {
                $$$reportNull$$$0(13);
            }
            return simpleTypeCreateErrorType;
        }
    }

    @Nullable
    public KotlinType substitute(@NotNull KotlinType type, @NotNull Variance howThisTypeIsUsed) {
        if (type == null) {
            $$$reportNull$$$0(14);
        }
        if (howThisTypeIsUsed == null) {
            $$$reportNull$$$0(15);
        }
        TypeProjection projection = substitute(new TypeProjectionImpl(howThisTypeIsUsed, getSubstitution().prepareTopLevelType(type, howThisTypeIsUsed)));
        if (projection == null) {
            return null;
        }
        return projection.getType();
    }

    @Nullable
    public TypeProjection substitute(@NotNull TypeProjection typeProjection) {
        if (typeProjection == null) {
            $$$reportNull$$$0(16);
        }
        TypeProjection substitutedTypeProjection = substituteWithoutApproximation(typeProjection);
        if (!this.substitution.approximateCapturedTypes() && !this.substitution.approximateContravariantCapturedTypes()) {
            return substitutedTypeProjection;
        }
        return CapturedTypeApproximationKt.approximateCapturedTypesIfNecessary(substitutedTypeProjection, this.substitution.approximateContravariantCapturedTypes());
    }

    @Nullable
    public TypeProjection substituteWithoutApproximation(@NotNull TypeProjection typeProjection) {
        if (typeProjection == null) {
            $$$reportNull$$$0(17);
        }
        if (isEmpty()) {
            return typeProjection;
        }
        try {
            return unsafeSubstitute(typeProjection, null, 0);
        } catch (SubstitutionException e) {
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    private TypeProjection unsafeSubstitute(@NotNull TypeProjection originalProjection, @Nullable TypeParameterDescriptor typeParameter, int recursionDepth) throws SubstitutionException {
        KotlinType substitutedType;
        if (originalProjection == null) {
            $$$reportNull$$$0(18);
        }
        assertRecursionDepth(recursionDepth, originalProjection, this.substitution);
        if (originalProjection.isStarProjection()) {
            if (originalProjection == null) {
                $$$reportNull$$$0(19);
            }
            return originalProjection;
        }
        KotlinType type = originalProjection.getType();
        if (type instanceof TypeWithEnhancement) {
            KotlinType origin = ((TypeWithEnhancement) type).getOrigin();
            KotlinType enhancement = ((TypeWithEnhancement) type).getEnhancement();
            TypeProjection substitution = unsafeSubstitute(new TypeProjectionImpl(originalProjection.getProjectionKind(), origin), typeParameter, recursionDepth + 1);
            KotlinType kotlinTypeSubstitute = substitute(enhancement, originalProjection.getProjectionKind());
            KotlinType resultingType = TypeWithEnhancementKt.wrapEnhancement(substitution.getType().unwrap(), kotlinTypeSubstitute instanceof TypeWithEnhancement ? ((TypeWithEnhancement) kotlinTypeSubstitute).getEnhancement() : kotlinTypeSubstitute);
            return new TypeProjectionImpl(substitution.getProjectionKind(), resultingType);
        }
        if (DynamicTypesKt.isDynamic(type) || (type.unwrap() instanceof RawType)) {
            if (originalProjection == null) {
                $$$reportNull$$$0(20);
            }
            return originalProjection;
        }
        TypeProjection substituted = this.substitution.mo3923get(type);
        TypeProjection replacement = substituted != null ? projectedTypeForConflictedTypeWithUnsafeVariance(type, substituted, typeParameter, originalProjection) : null;
        Variance originalProjectionKind = originalProjection.getProjectionKind();
        if (replacement == null && FlexibleTypesKt.isFlexible(type) && !TypeCapabilitiesKt.isCustomTypeVariable(type)) {
            FlexibleType flexibleType = FlexibleTypesKt.asFlexibleType(type);
            TypeProjection substitutedLower = unsafeSubstitute(new TypeProjectionImpl(originalProjectionKind, flexibleType.getLowerBound()), typeParameter, recursionDepth + 1);
            TypeProjection substitutedUpper = unsafeSubstitute(new TypeProjectionImpl(originalProjectionKind, flexibleType.getUpperBound()), typeParameter, recursionDepth + 1);
            Variance substitutedProjectionKind = substitutedLower.getProjectionKind();
            if (!$assertionsDisabled && ((substitutedProjectionKind != substitutedUpper.getProjectionKind() || originalProjectionKind != Variance.INVARIANT) && originalProjectionKind != substitutedProjectionKind)) {
                throw new AssertionError("Unexpected substituted projection kind: " + substitutedProjectionKind + "; original: " + originalProjectionKind);
            }
            if (substitutedLower.getType() != flexibleType.getLowerBound() || substitutedUpper.getType() != flexibleType.getUpperBound()) {
                KotlinType substitutedFlexibleType = KotlinTypeFactory.flexibleType(TypeSubstitutionKt.asSimpleType(substitutedLower.getType()), TypeSubstitutionKt.asSimpleType(substitutedUpper.getType()));
                return new TypeProjectionImpl(substitutedProjectionKind, substitutedFlexibleType);
            }
            if (originalProjection == null) {
                $$$reportNull$$$0(21);
            }
            return originalProjection;
        }
        if (KotlinBuiltIns.isNothing(type) || KotlinTypeKt.isError(type)) {
            if (originalProjection == null) {
                $$$reportNull$$$0(22);
            }
            return originalProjection;
        }
        if (replacement != null) {
            VarianceConflictType varianceConflict = conflictType(originalProjectionKind, replacement.getProjectionKind());
            boolean allowVarianceConflict = CapturedTypeConstructorKt.isCaptured(type);
            if (!allowVarianceConflict) {
                switch (varianceConflict) {
                    case OUT_IN_IN_POSITION:
                        throw new SubstitutionException("Out-projection in in-position");
                    case IN_IN_OUT_POSITION:
                        return new TypeProjectionImpl(Variance.OUT_VARIANCE, type.getConstructor().getBuiltIns().getNullableAnyType());
                }
            }
            CustomTypeVariable typeVariable = TypeCapabilitiesKt.getCustomTypeVariable(type);
            if (replacement.isStarProjection()) {
                if (replacement == null) {
                    $$$reportNull$$$0(23);
                }
                return replacement;
            }
            if (typeVariable != null) {
                substitutedType = typeVariable.substitutionResult(replacement.getType());
            } else {
                substitutedType = TypeUtils.makeNullableIfNeeded(replacement.getType(), type.isMarkedNullable());
            }
            if (!type.getAnnotations().isEmpty()) {
                Annotations typeAnnotations = filterOutUnsafeVariance(this.substitution.filterAnnotations(type.getAnnotations()));
                substitutedType = TypeUtilsKt.replaceAnnotations(substitutedType, new CompositeAnnotations(substitutedType.getAnnotations(), typeAnnotations));
            }
            Variance resultingProjectionKind = varianceConflict == VarianceConflictType.NO_CONFLICT ? combine(originalProjectionKind, replacement.getProjectionKind()) : originalProjectionKind;
            return new TypeProjectionImpl(resultingProjectionKind, substitutedType);
        }
        TypeProjection typeProjectionSubstituteCompoundType = substituteCompoundType(originalProjection, recursionDepth);
        if (typeProjectionSubstituteCompoundType == null) {
            $$$reportNull$$$0(24);
        }
        return typeProjectionSubstituteCompoundType;
    }

    @NotNull
    private static TypeProjection projectedTypeForConflictedTypeWithUnsafeVariance(@NotNull KotlinType originalType, @NotNull TypeProjection substituted, @Nullable TypeParameterDescriptor typeParameter, @NotNull TypeProjection originalProjection) {
        if (originalType == null) {
            $$$reportNull$$$0(25);
        }
        if (substituted == null) {
            $$$reportNull$$$0(26);
        }
        if (originalProjection == null) {
            $$$reportNull$$$0(27);
        }
        if (!originalType.getAnnotations().hasAnnotation(StandardNames.FqNames.unsafeVariance)) {
            if (substituted == null) {
                $$$reportNull$$$0(28);
            }
            return substituted;
        }
        TypeConstructor constructor = substituted.getType().getConstructor();
        if (!(constructor instanceof NewCapturedTypeConstructor)) {
            if (substituted == null) {
                $$$reportNull$$$0(29);
            }
            return substituted;
        }
        NewCapturedTypeConstructor capturedType = (NewCapturedTypeConstructor) constructor;
        TypeProjection capturedTypeProjection = capturedType.getProjection();
        Variance varianceOfCapturedType = capturedTypeProjection.getProjectionKind();
        VarianceConflictType conflictWithTopLevelType = conflictType(originalProjection.getProjectionKind(), varianceOfCapturedType);
        if (conflictWithTopLevelType == VarianceConflictType.OUT_IN_IN_POSITION) {
            return new TypeProjectionImpl(capturedTypeProjection.getType());
        }
        if (typeParameter == null) {
            if (substituted == null) {
                $$$reportNull$$$0(30);
            }
            return substituted;
        }
        VarianceConflictType conflictTypeWithTypeParameter = conflictType(typeParameter.getVariance(), varianceOfCapturedType);
        if (conflictTypeWithTypeParameter == VarianceConflictType.OUT_IN_IN_POSITION) {
            return new TypeProjectionImpl(capturedTypeProjection.getType());
        }
        if (substituted == null) {
            $$$reportNull$$$0(31);
        }
        return substituted;
    }

    @NotNull
    private static Annotations filterOutUnsafeVariance(@NotNull Annotations annotations) {
        if (annotations == null) {
            $$$reportNull$$$0(32);
        }
        if (annotations.hasAnnotation(StandardNames.FqNames.unsafeVariance)) {
            return new FilteredAnnotations(annotations, new Function1<FqName, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor.1
                private static /* synthetic */ void $$$reportNull$$$0(int i) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "kotlin/reflect/jvm/internal/impl/types/TypeSubstitutor$1", "invoke"));
                }

                @Override // kotlin.jvm.functions.Function1
                public Boolean invoke(@NotNull FqName name) {
                    if (name == null) {
                        $$$reportNull$$$0(0);
                    }
                    return Boolean.valueOf(!name.equals(StandardNames.FqNames.unsafeVariance));
                }
            });
        }
        if (annotations == null) {
            $$$reportNull$$$0(33);
        }
        return annotations;
    }

    private TypeProjection substituteCompoundType(TypeProjection originalProjection, int recursionDepth) throws SubstitutionException {
        KotlinType type = originalProjection.getType();
        Variance projectionKind = originalProjection.getProjectionKind();
        if (type.getConstructor().mo3831getDeclarationDescriptor() instanceof TypeParameterDescriptor) {
            return originalProjection;
        }
        KotlinType substitutedAbbreviation = null;
        SimpleType abbreviation = SpecialTypesKt.getAbbreviation(type);
        if (abbreviation != null) {
            TypeSubstitutor substitutorForAbbreviation = replaceWithNonApproximatingSubstitution();
            substitutedAbbreviation = substitutorForAbbreviation.substitute(abbreviation, Variance.INVARIANT);
        }
        List<TypeProjection> substitutedArguments = substituteTypeArguments(type.getConstructor().getParameters(), type.getArguments(), recursionDepth);
        KotlinType substitutedType = TypeSubstitutionKt.replace(type, substitutedArguments, this.substitution.filterAnnotations(type.getAnnotations()));
        if ((substitutedType instanceof SimpleType) && (substitutedAbbreviation instanceof SimpleType)) {
            substitutedType = SpecialTypesKt.withAbbreviation((SimpleType) substitutedType, (SimpleType) substitutedAbbreviation);
        }
        return new TypeProjectionImpl(projectionKind, substitutedType);
    }

    private List<TypeProjection> substituteTypeArguments(List<TypeParameterDescriptor> typeParameters, List<TypeProjection> typeArguments, int recursionDepth) throws SubstitutionException {
        List<TypeProjection> substitutedArguments = new ArrayList<>(typeParameters.size());
        boolean wereChanges = false;
        for (int i = 0; i < typeParameters.size(); i++) {
            TypeParameterDescriptor typeParameter = typeParameters.get(i);
            TypeProjection typeArgument = typeArguments.get(i);
            TypeProjection substitutedTypeArgument = unsafeSubstitute(typeArgument, typeParameter, recursionDepth + 1);
            switch (conflictType(typeParameter.getVariance(), substitutedTypeArgument.getProjectionKind())) {
                case OUT_IN_IN_POSITION:
                case IN_IN_OUT_POSITION:
                    substitutedTypeArgument = TypeUtils.makeStarProjection(typeParameter);
                    break;
                case NO_CONFLICT:
                    if (typeParameter.getVariance() != Variance.INVARIANT && !substitutedTypeArgument.isStarProjection()) {
                        substitutedTypeArgument = new TypeProjectionImpl(Variance.INVARIANT, substitutedTypeArgument.getType());
                        break;
                    }
                    break;
            }
            if (substitutedTypeArgument != typeArgument) {
                wereChanges = true;
            }
            substitutedArguments.add(substitutedTypeArgument);
        }
        return !wereChanges ? typeArguments : substitutedArguments;
    }

    @NotNull
    public static Variance combine(@NotNull Variance typeParameterVariance, @NotNull TypeProjection typeProjection) {
        if (typeParameterVariance == null) {
            $$$reportNull$$$0(34);
        }
        if (typeProjection == null) {
            $$$reportNull$$$0(35);
        }
        if (!typeProjection.isStarProjection()) {
            return combine(typeParameterVariance, typeProjection.getProjectionKind());
        }
        Variance variance = Variance.OUT_VARIANCE;
        if (variance == null) {
            $$$reportNull$$$0(36);
        }
        return variance;
    }

    @NotNull
    public static Variance combine(@NotNull Variance typeParameterVariance, @NotNull Variance projectionKind) {
        if (typeParameterVariance == null) {
            $$$reportNull$$$0(37);
        }
        if (projectionKind == null) {
            $$$reportNull$$$0(38);
        }
        if (typeParameterVariance == Variance.INVARIANT) {
            if (projectionKind == null) {
                $$$reportNull$$$0(39);
            }
            return projectionKind;
        }
        if (projectionKind == Variance.INVARIANT) {
            if (typeParameterVariance == null) {
                $$$reportNull$$$0(40);
            }
            return typeParameterVariance;
        }
        if (typeParameterVariance != projectionKind) {
            throw new AssertionError("Variance conflict: type parameter variance '" + typeParameterVariance + "' and projection kind '" + projectionKind + "' cannot be combined");
        }
        if (projectionKind == null) {
            $$$reportNull$$$0(41);
        }
        return projectionKind;
    }

    private static VarianceConflictType conflictType(Variance position, Variance argument) {
        if (position == Variance.IN_VARIANCE && argument == Variance.OUT_VARIANCE) {
            return VarianceConflictType.OUT_IN_IN_POSITION;
        }
        if (position == Variance.OUT_VARIANCE && argument == Variance.IN_VARIANCE) {
            return VarianceConflictType.IN_IN_OUT_POSITION;
        }
        return VarianceConflictType.NO_CONFLICT;
    }

    private static void assertRecursionDepth(int recursionDepth, TypeProjection projection, TypeSubstitution substitution) {
        if (recursionDepth > 100) {
            throw new IllegalStateException("Recursion too deep. Most likely infinite loop while substituting " + safeToString(projection) + "; substitution: " + safeToString(substitution));
        }
    }

    private static String safeToString(Object o) {
        try {
            return o.toString();
        } catch (Throwable e) {
            if (ExceptionUtilsKt.isProcessCanceledException(e)) {
                throw ((RuntimeException) e);
            }
            return "[Exception while computing toString(): " + e + "]";
        }
    }
}
