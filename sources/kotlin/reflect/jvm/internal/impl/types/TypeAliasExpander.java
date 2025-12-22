package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.TypeAliasExpansionReportStrategy;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: TypeAliasExpander.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/TypeAliasExpander.class */
public final class TypeAliasExpander {

    @NotNull
    private final TypeAliasExpansionReportStrategy reportStrategy;
    private final boolean shouldCheckBounds;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final TypeAliasExpander NON_REPORTING = new TypeAliasExpander(TypeAliasExpansionReportStrategy.DO_NOTHING.INSTANCE, false);

    public TypeAliasExpander(@NotNull TypeAliasExpansionReportStrategy reportStrategy, boolean shouldCheckBounds) {
        Intrinsics.checkNotNullParameter(reportStrategy, "reportStrategy");
        this.reportStrategy = reportStrategy;
        this.shouldCheckBounds = shouldCheckBounds;
    }

    @NotNull
    public final SimpleType expand(@NotNull TypeAliasExpansion typeAliasExpansion, @NotNull Annotations annotations) {
        Intrinsics.checkNotNullParameter(typeAliasExpansion, "typeAliasExpansion");
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        return expandRecursively(typeAliasExpansion, annotations, false, 0, true);
    }

    private final SimpleType expandRecursively(TypeAliasExpansion typeAliasExpansion, Annotations annotations, boolean isNullable, int recursionDepth, boolean withAbbreviatedType) {
        TypeProjectionImpl underlyingProjection = new TypeProjectionImpl(Variance.INVARIANT, typeAliasExpansion.getDescriptor().getUnderlyingType());
        TypeProjection expandedProjection = expandTypeProjection(underlyingProjection, typeAliasExpansion, null, recursionDepth);
        KotlinType type = expandedProjection.getType();
        Intrinsics.checkNotNullExpressionValue(type, "expandedProjection.type");
        SimpleType expandedType = TypeSubstitutionKt.asSimpleType(type);
        if (KotlinTypeKt.isError(expandedType)) {
            return expandedType;
        }
        boolean z = expandedProjection.getProjectionKind() == Variance.INVARIANT;
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Type alias expansion: result for " + typeAliasExpansion.getDescriptor() + " is " + expandedProjection.getProjectionKind() + ", should be invariant");
        }
        checkRepeatedAnnotations(expandedType.getAnnotations(), annotations);
        SimpleType it = combineAnnotations(expandedType, annotations);
        SimpleType expandedTypeWithExtraAnnotations = TypeUtils.makeNullableIfNeeded(it, isNullable);
        Intrinsics.checkNotNullExpressionValue(expandedTypeWithExtraAnnotations, "expandedType.combineAnnotations(annotations).let { TypeUtils.makeNullableIfNeeded(it, isNullable) }");
        if (withAbbreviatedType) {
            return SpecialTypesKt.withAbbreviation(expandedTypeWithExtraAnnotations, createAbbreviation(typeAliasExpansion, annotations, isNullable));
        }
        return expandedTypeWithExtraAnnotations;
    }

    private final SimpleType createAbbreviation(TypeAliasExpansion $this$createAbbreviation, Annotations annotations, boolean isNullable) {
        KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
        TypeConstructor typeConstructor = $this$createAbbreviation.getDescriptor().getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor, "descriptor.typeConstructor");
        return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(annotations, typeConstructor, $this$createAbbreviation.getArguments(), isNullable, MemberScope.Empty.INSTANCE);
    }

    private final TypeProjection expandTypeProjection(TypeProjection underlyingProjection, TypeAliasExpansion typeAliasExpansion, TypeParameterDescriptor typeParameterDescriptor, int recursionDepth) {
        Variance variance;
        Variance variance2;
        UnwrappedType unwrappedTypeCombineNullabilityAndAnnotations;
        Companion.assertRecursionDepth(recursionDepth, typeAliasExpansion.getDescriptor());
        if (underlyingProjection.isStarProjection()) {
            Intrinsics.checkNotNull(typeParameterDescriptor);
            TypeProjection typeProjectionMakeStarProjection = TypeUtils.makeStarProjection(typeParameterDescriptor);
            Intrinsics.checkNotNullExpressionValue(typeProjectionMakeStarProjection, "makeStarProjection(typeParameterDescriptor!!)");
            return typeProjectionMakeStarProjection;
        }
        KotlinType underlyingType = underlyingProjection.getType();
        Intrinsics.checkNotNullExpressionValue(underlyingType, "underlyingProjection.type");
        TypeProjection argument = typeAliasExpansion.getReplacement(underlyingType.getConstructor());
        if (argument == null) {
            return expandNonArgumentTypeProjection(underlyingProjection, typeAliasExpansion, recursionDepth);
        }
        if (argument.isStarProjection()) {
            Intrinsics.checkNotNull(typeParameterDescriptor);
            TypeProjection typeProjectionMakeStarProjection2 = TypeUtils.makeStarProjection(typeParameterDescriptor);
            Intrinsics.checkNotNullExpressionValue(typeProjectionMakeStarProjection2, "makeStarProjection(typeParameterDescriptor!!)");
            return typeProjectionMakeStarProjection2;
        }
        UnwrappedType argumentType = argument.getType().unwrap();
        TypeAliasExpander $this$expandTypeProjection_u24lambda_u2d2 = this;
        Variance argumentVariance = argument.getProjectionKind();
        Intrinsics.checkNotNullExpressionValue(argumentVariance, "argument.projectionKind");
        Variance underlyingVariance = underlyingProjection.getProjectionKind();
        Intrinsics.checkNotNullExpressionValue(underlyingVariance, "underlyingProjection.projectionKind");
        if (underlyingVariance == argumentVariance || underlyingVariance == Variance.INVARIANT) {
            variance = argumentVariance;
        } else if (argumentVariance == Variance.INVARIANT) {
            variance = underlyingVariance;
        } else {
            $this$expandTypeProjection_u24lambda_u2d2.reportStrategy.conflictingProjection(typeAliasExpansion.getDescriptor(), typeParameterDescriptor, argumentType);
            variance = argumentVariance;
        }
        Variance substitutionVariance = variance;
        Variance variance3 = typeParameterDescriptor == null ? null : typeParameterDescriptor.getVariance();
        Variance parameterVariance = variance3 == null ? Variance.INVARIANT : variance3;
        Intrinsics.checkNotNullExpressionValue(parameterVariance, "typeParameterDescriptor?.variance ?: Variance.INVARIANT");
        if (parameterVariance == substitutionVariance || parameterVariance == Variance.INVARIANT) {
            variance2 = substitutionVariance;
        } else if (substitutionVariance == Variance.INVARIANT) {
            variance2 = Variance.INVARIANT;
        } else {
            $this$expandTypeProjection_u24lambda_u2d2.reportStrategy.conflictingProjection(typeAliasExpansion.getDescriptor(), typeParameterDescriptor, argumentType);
            variance2 = substitutionVariance;
        }
        Variance resultingVariance = variance2;
        checkRepeatedAnnotations(underlyingType.getAnnotations(), argumentType.getAnnotations());
        if (argumentType instanceof DynamicType) {
            unwrappedTypeCombineNullabilityAndAnnotations = combineAnnotations((DynamicType) argumentType, underlyingType.getAnnotations());
        } else {
            unwrappedTypeCombineNullabilityAndAnnotations = combineNullabilityAndAnnotations(TypeSubstitutionKt.asSimpleType(argumentType), underlyingType);
        }
        UnwrappedType substitutedType = unwrappedTypeCombineNullabilityAndAnnotations;
        return new TypeProjectionImpl(resultingVariance, substitutedType);
    }

    private final DynamicType combineAnnotations(DynamicType $this$combineAnnotations, Annotations newAnnotations) {
        return $this$combineAnnotations.replaceAnnotations(createCombinedAnnotations($this$combineAnnotations, newAnnotations));
    }

    private final SimpleType combineAnnotations(SimpleType $this$combineAnnotations, Annotations newAnnotations) {
        return KotlinTypeKt.isError($this$combineAnnotations) ? $this$combineAnnotations : TypeSubstitutionKt.replace$default($this$combineAnnotations, null, createCombinedAnnotations($this$combineAnnotations, newAnnotations), 1, null);
    }

    private final Annotations createCombinedAnnotations(KotlinType $this$createCombinedAnnotations, Annotations newAnnotations) {
        return KotlinTypeKt.isError($this$createCombinedAnnotations) ? $this$createCombinedAnnotations.getAnnotations() : AnnotationsKt.composeAnnotations(newAnnotations, $this$createCombinedAnnotations.getAnnotations());
    }

    private final void checkRepeatedAnnotations(Annotations existingAnnotations, Annotations newAnnotations) {
        Annotations $this$mapTo$iv = existingAnnotations;
        Collection destination$iv = new HashSet();
        for (Object item$iv : $this$mapTo$iv) {
            AnnotationDescriptor it = (AnnotationDescriptor) item$iv;
            destination$iv.add(it.getFqName());
        }
        HashSet existingAnnotationFqNames = (HashSet) destination$iv;
        for (AnnotationDescriptor annotation : newAnnotations) {
            if (existingAnnotationFqNames.contains(annotation.getFqName())) {
                this.reportStrategy.repeatedAnnotation(annotation);
            }
        }
    }

    private final SimpleType combineNullability(SimpleType $this$combineNullability, KotlinType fromType) {
        SimpleType simpleTypeMakeNullableIfNeeded = TypeUtils.makeNullableIfNeeded($this$combineNullability, fromType.isMarkedNullable());
        Intrinsics.checkNotNullExpressionValue(simpleTypeMakeNullableIfNeeded, "makeNullableIfNeeded(this, fromType.isMarkedNullable)");
        return simpleTypeMakeNullableIfNeeded;
    }

    private final SimpleType combineNullabilityAndAnnotations(SimpleType $this$combineNullabilityAndAnnotations, KotlinType fromType) {
        return combineAnnotations(combineNullability($this$combineNullabilityAndAnnotations, fromType), fromType.getAnnotations());
    }

    private final TypeProjection expandNonArgumentTypeProjection(TypeProjection originalProjection, TypeAliasExpansion typeAliasExpansion, int recursionDepth) {
        UnwrappedType originalType = originalProjection.getType().unwrap();
        if (DynamicTypesKt.isDynamic(originalType)) {
            return originalProjection;
        }
        SimpleType type = TypeSubstitutionKt.asSimpleType(originalType);
        if (KotlinTypeKt.isError(type) || !TypeUtilsKt.requiresTypeAliasExpansion(type)) {
            return originalProjection;
        }
        TypeConstructor typeConstructor = type.getConstructor();
        ClassifierDescriptor typeDescriptor = typeConstructor.mo3831getDeclarationDescriptor();
        boolean z = typeConstructor.getParameters().size() == type.getArguments().size();
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError(Intrinsics.stringPlus("Unexpected malformed type: ", type));
        }
        if (typeDescriptor instanceof TypeParameterDescriptor) {
            return originalProjection;
        }
        if (typeDescriptor instanceof TypeAliasDescriptor) {
            if (typeAliasExpansion.isRecursion((TypeAliasDescriptor) typeDescriptor)) {
                this.reportStrategy.recursiveTypeAlias((TypeAliasDescriptor) typeDescriptor);
                return new TypeProjectionImpl(Variance.INVARIANT, ErrorUtils.createErrorType(Intrinsics.stringPlus("Recursive type alias: ", ((TypeAliasDescriptor) typeDescriptor).getName())));
            }
            Iterable $this$mapIndexed$iv = type.getArguments();
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$mapIndexed$iv, 10));
            int index$iv$iv = 0;
            for (Object item$iv$iv : $this$mapIndexed$iv) {
                int i = index$iv$iv;
                index$iv$iv++;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                TypeProjection typeAliasArgument = (TypeProjection) item$iv$iv;
                destination$iv$iv.add(expandTypeProjection(typeAliasArgument, typeAliasExpansion, typeConstructor.getParameters().get(i), recursionDepth + 1));
            }
            List expandedArguments = (List) destination$iv$iv;
            TypeAliasExpansion nestedExpansion = TypeAliasExpansion.Companion.create(typeAliasExpansion, (TypeAliasDescriptor) typeDescriptor, expandedArguments);
            SimpleType nestedExpandedType = expandRecursively(nestedExpansion, type.getAnnotations(), type.isMarkedNullable(), recursionDepth + 1, false);
            SimpleType typeWithAbbreviation = DynamicTypesKt.isDynamic(nestedExpandedType) ? nestedExpandedType : SpecialTypesKt.withAbbreviation(nestedExpandedType, substituteArguments(type, typeAliasExpansion, recursionDepth));
            return new TypeProjectionImpl(originalProjection.getProjectionKind(), typeWithAbbreviation);
        }
        SimpleType substitutedType = substituteArguments(type, typeAliasExpansion, recursionDepth);
        checkTypeArgumentsSubstitution(type, substitutedType);
        return new TypeProjectionImpl(originalProjection.getProjectionKind(), substitutedType);
    }

    private final SimpleType substituteArguments(SimpleType $this$substituteArguments, TypeAliasExpansion typeAliasExpansion, int recursionDepth) {
        TypeConstructor typeConstructor = $this$substituteArguments.getConstructor();
        Iterable $this$mapIndexed$iv = $this$substituteArguments.getArguments();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$mapIndexed$iv, 10));
        int index$iv$iv = 0;
        for (Object item$iv$iv : $this$mapIndexed$iv) {
            int i = index$iv$iv;
            index$iv$iv++;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TypeProjection originalArgument = (TypeProjection) item$iv$iv;
            TypeProjection projection = expandTypeProjection(originalArgument, typeAliasExpansion, typeConstructor.getParameters().get(i), recursionDepth + 1);
            destination$iv$iv.add(projection.isStarProjection() ? projection : new TypeProjectionImpl(projection.getProjectionKind(), TypeUtils.makeNullableIfNeeded(projection.getType(), originalArgument.getType().isMarkedNullable())));
        }
        List substitutedArguments = (List) destination$iv$iv;
        return TypeSubstitutionKt.replace$default($this$substituteArguments, substitutedArguments, null, 2, null);
    }

    private final void checkTypeArgumentsSubstitution(KotlinType unsubstitutedType, KotlinType substitutedType) {
        TypeSubstitutor typeSubstitutor = TypeSubstitutor.create(substitutedType);
        Intrinsics.checkNotNullExpressionValue(typeSubstitutor, "create(substitutedType)");
        Iterable $this$forEachIndexed$iv = substitutedType.getArguments();
        int index$iv = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            int i = index$iv;
            index$iv++;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TypeProjection substitutedArgument = (TypeProjection) item$iv;
            if (!substitutedArgument.isStarProjection()) {
                KotlinType type = substitutedArgument.getType();
                Intrinsics.checkNotNullExpressionValue(type, "substitutedArgument.type");
                if (!TypeUtilsKt.containsTypeAliasParameters(type)) {
                    TypeProjection unsubstitutedArgument = unsubstitutedType.getArguments().get(i);
                    TypeParameterDescriptor typeParameter = unsubstitutedType.getConstructor().getParameters().get(i);
                    if (this.shouldCheckBounds) {
                        TypeAliasExpansionReportStrategy typeAliasExpansionReportStrategy = this.reportStrategy;
                        KotlinType type2 = unsubstitutedArgument.getType();
                        Intrinsics.checkNotNullExpressionValue(type2, "unsubstitutedArgument.type");
                        KotlinType type3 = substitutedArgument.getType();
                        Intrinsics.checkNotNullExpressionValue(type3, "substitutedArgument.type");
                        Intrinsics.checkNotNullExpressionValue(typeParameter, "typeParameter");
                        typeAliasExpansionReportStrategy.boundsViolationInSubstitution(typeSubstitutor, type2, type3, typeParameter);
                    }
                }
            }
        }
    }

    /* compiled from: TypeAliasExpander.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/TypeAliasExpander$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void assertRecursionDepth(int recursionDepth, TypeAliasDescriptor typeAliasDescriptor) {
            if (recursionDepth > 100) {
                throw new AssertionError(Intrinsics.stringPlus("Too deep recursion while expanding type alias ", typeAliasDescriptor.getName()));
            }
        }
    }
}
