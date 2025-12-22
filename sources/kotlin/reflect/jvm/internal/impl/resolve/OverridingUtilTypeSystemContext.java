package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeCheckerContext;
import kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeSystemContext;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.DefinitelyNotNullTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.DynamicTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.FlexibleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentListMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariableTypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: OverridingUtilTypeSystemContext.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/OverridingUtilTypeSystemContext.class */
public final class OverridingUtilTypeSystemContext implements ClassicTypeSystemContext {

    @Nullable
    private final Map<TypeConstructor, TypeConstructor> matchingTypeConstructors;

    @NotNull
    private final KotlinTypeChecker.TypeConstructorEquality equalityAxioms;

    @NotNull
    private final KotlinTypeRefiner kotlinTypeRefiner;

    /* JADX WARN: Multi-variable type inference failed */
    public OverridingUtilTypeSystemContext(@Nullable Map<TypeConstructor, ? extends TypeConstructor> map, @NotNull KotlinTypeChecker.TypeConstructorEquality equalityAxioms, @NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(equalityAxioms, "equalityAxioms");
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        this.matchingTypeConstructors = map;
        this.equalityAxioms = equalityAxioms;
        this.kotlinTypeRefiner = kotlinTypeRefiner;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @Nullable
    public SimpleTypeMarker captureFromArguments(@NotNull SimpleTypeMarker type, @NotNull CaptureStatus status) {
        return ClassicTypeSystemContext.DefaultImpls.captureFromArguments(this, type, status);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeSystemContext
    @NotNull
    public KotlinTypeMarker createFlexibleType(@NotNull SimpleTypeMarker lowerBound, @NotNull SimpleTypeMarker upperBound) {
        return ClassicTypeSystemContext.DefaultImpls.createFlexibleType(this, lowerBound, upperBound);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemOptimizationContext
    public boolean identicalArguments(@NotNull SimpleTypeMarker a, @NotNull SimpleTypeMarker b) {
        return ClassicTypeSystemContext.DefaultImpls.identicalArguments(this, a, b);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public KotlinTypeMarker intersectTypes(@NotNull List<? extends KotlinTypeMarker> list) {
        return ClassicTypeSystemContext.DefaultImpls.intersectTypes(this, list);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public TypeArgumentMarker projection(@NotNull CapturedTypeConstructorMarker $this$projection) {
        return ClassicTypeSystemContext.DefaultImpls.projection(this, $this$projection);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public CaptureStatus captureStatus(@NotNull CapturedTypeMarker $this$captureStatus) {
        return ClassicTypeSystemContext.DefaultImpls.captureStatus(this, $this$captureStatus);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isProjectionNotNull(@NotNull CapturedTypeMarker $this$isProjectionNotNull) {
        return ClassicTypeSystemContext.DefaultImpls.isProjectionNotNull(this, $this$isProjectionNotNull);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @Nullable
    public KotlinTypeMarker lowerType(@NotNull CapturedTypeMarker $this$lowerType) {
        return ClassicTypeSystemContext.DefaultImpls.lowerType(this, $this$lowerType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public CapturedTypeConstructorMarker typeConstructor(@NotNull CapturedTypeMarker $this$typeConstructor) {
        return ClassicTypeSystemContext.DefaultImpls.typeConstructor((ClassicTypeSystemContext) this, $this$typeConstructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public SimpleTypeMarker original(@NotNull DefinitelyNotNullTypeMarker $this$original) {
        return ClassicTypeSystemContext.DefaultImpls.original(this, $this$original);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @Nullable
    public DynamicTypeMarker asDynamicType(@NotNull FlexibleTypeMarker $this$asDynamicType) {
        return ClassicTypeSystemContext.DefaultImpls.asDynamicType(this, $this$asDynamicType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeSystemContext, kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public SimpleTypeMarker lowerBound(@NotNull FlexibleTypeMarker $this$lowerBound) {
        return ClassicTypeSystemContext.DefaultImpls.lowerBound(this, $this$lowerBound);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeSystemContext, kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public SimpleTypeMarker upperBound(@NotNull FlexibleTypeMarker $this$upperBound) {
        return ClassicTypeSystemContext.DefaultImpls.upperBound(this, $this$upperBound);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public int argumentsCount(@NotNull KotlinTypeMarker $this$argumentsCount) {
        return ClassicTypeSystemContext.DefaultImpls.argumentsCount(this, $this$argumentsCount);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @Nullable
    public FlexibleTypeMarker asFlexibleType(@NotNull KotlinTypeMarker $this$asFlexibleType) {
        return ClassicTypeSystemContext.DefaultImpls.asFlexibleType(this, $this$asFlexibleType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeSystemContext, kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @Nullable
    public SimpleTypeMarker asSimpleType(@NotNull KotlinTypeMarker $this$asSimpleType) {
        return ClassicTypeSystemContext.DefaultImpls.asSimpleType(this, $this$asSimpleType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public TypeArgumentMarker asTypeArgument(@NotNull KotlinTypeMarker $this$asTypeArgument) {
        return ClassicTypeSystemContext.DefaultImpls.asTypeArgument(this, $this$asTypeArgument);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public TypeArgumentMarker getArgument(@NotNull KotlinTypeMarker $this$getArgument, int index) {
        return ClassicTypeSystemContext.DefaultImpls.getArgument(this, $this$getArgument, index);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    @Nullable
    public KotlinTypeMarker getSubstitutedUnderlyingType(@NotNull KotlinTypeMarker $this$getSubstitutedUnderlyingType) {
        return ClassicTypeSystemContext.DefaultImpls.getSubstitutedUnderlyingType(this, $this$getSubstitutedUnderlyingType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public boolean hasAnnotation(@NotNull KotlinTypeMarker $this$hasAnnotation, @NotNull FqName fqName) {
        return ClassicTypeSystemContext.DefaultImpls.hasAnnotation(this, $this$hasAnnotation, fqName);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean hasFlexibleNullability(@NotNull KotlinTypeMarker $this$hasFlexibleNullability) {
        return ClassicTypeSystemContext.DefaultImpls.hasFlexibleNullability(this, $this$hasFlexibleNullability);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isDefinitelyNotNullType(@NotNull KotlinTypeMarker $this$isDefinitelyNotNullType) {
        return ClassicTypeSystemContext.DefaultImpls.isDefinitelyNotNullType(this, $this$isDefinitelyNotNullType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isDynamic(@NotNull KotlinTypeMarker $this$isDynamic) {
        return ClassicTypeSystemContext.DefaultImpls.isDynamic(this, $this$isDynamic);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isError(@NotNull KotlinTypeMarker $this$isError) {
        return ClassicTypeSystemContext.DefaultImpls.isError(this, $this$isError);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isMarkedNullable(@NotNull KotlinTypeMarker $this$isMarkedNullable) {
        return ClassicTypeSystemContext.DefaultImpls.isMarkedNullable(this, $this$isMarkedNullable);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isNothing(@NotNull KotlinTypeMarker $this$isNothing) {
        return ClassicTypeSystemContext.DefaultImpls.isNothing(this, $this$isNothing);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isNullableType(@NotNull KotlinTypeMarker $this$isNullableType) {
        return ClassicTypeSystemContext.DefaultImpls.isNullableType(this, $this$isNullableType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public SimpleTypeMarker lowerBoundIfFlexible(@NotNull KotlinTypeMarker $this$lowerBoundIfFlexible) {
        return ClassicTypeSystemContext.DefaultImpls.lowerBoundIfFlexible(this, $this$lowerBoundIfFlexible);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public KotlinTypeMarker makeDefinitelyNotNullOrNotNull(@NotNull KotlinTypeMarker $this$makeDefinitelyNotNullOrNotNull) {
        return ClassicTypeSystemContext.DefaultImpls.makeDefinitelyNotNullOrNotNull(this, $this$makeDefinitelyNotNullOrNotNull);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    @NotNull
    public KotlinTypeMarker makeNullable(@NotNull KotlinTypeMarker $this$makeNullable) {
        return ClassicTypeSystemContext.DefaultImpls.makeNullable(this, $this$makeNullable);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public TypeConstructorMarker typeConstructor(@NotNull KotlinTypeMarker $this$typeConstructor) {
        return ClassicTypeSystemContext.DefaultImpls.typeConstructor(this, $this$typeConstructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public SimpleTypeMarker upperBoundIfFlexible(@NotNull KotlinTypeMarker $this$upperBoundIfFlexible) {
        return ClassicTypeSystemContext.DefaultImpls.upperBoundIfFlexible(this, $this$upperBoundIfFlexible);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public KotlinTypeMarker withNullability(@NotNull KotlinTypeMarker $this$withNullability, boolean nullable) {
        return ClassicTypeSystemContext.DefaultImpls.withNullability(this, $this$withNullability, nullable);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public TypeArgumentListMarker asArgumentList(@NotNull SimpleTypeMarker $this$asArgumentList) {
        return ClassicTypeSystemContext.DefaultImpls.asArgumentList(this, $this$asArgumentList);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @Nullable
    public CapturedTypeMarker asCapturedType(@NotNull SimpleTypeMarker $this$asCapturedType) {
        return ClassicTypeSystemContext.DefaultImpls.asCapturedType(this, $this$asCapturedType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @Nullable
    public DefinitelyNotNullTypeMarker asDefinitelyNotNullType(@NotNull SimpleTypeMarker $this$asDefinitelyNotNullType) {
        return ClassicTypeSystemContext.DefaultImpls.asDefinitelyNotNullType(this, $this$asDefinitelyNotNullType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @Nullable
    public List<SimpleTypeMarker> fastCorrespondingSupertypes(@NotNull SimpleTypeMarker $this$fastCorrespondingSupertypes, @NotNull TypeConstructorMarker constructor) {
        return ClassicTypeSystemContext.DefaultImpls.fastCorrespondingSupertypes(this, $this$fastCorrespondingSupertypes, constructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @Nullable
    public TypeArgumentMarker getArgumentOrNull(@NotNull SimpleTypeMarker $this$getArgumentOrNull, int index) {
        return ClassicTypeSystemContext.DefaultImpls.getArgumentOrNull(this, $this$getArgumentOrNull, index);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isClassType(@NotNull SimpleTypeMarker $this$isClassType) {
        return ClassicTypeSystemContext.DefaultImpls.isClassType(this, $this$isClassType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isIntegerLiteralType(@NotNull SimpleTypeMarker $this$isIntegerLiteralType) {
        return ClassicTypeSystemContext.DefaultImpls.isIntegerLiteralType(this, $this$isIntegerLiteralType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isMarkedNullable(@NotNull SimpleTypeMarker $this$isMarkedNullable) {
        return ClassicTypeSystemContext.DefaultImpls.isMarkedNullable((ClassicTypeSystemContext) this, $this$isMarkedNullable);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isPrimitiveType(@NotNull SimpleTypeMarker $this$isPrimitiveType) {
        return ClassicTypeSystemContext.DefaultImpls.isPrimitiveType(this, $this$isPrimitiveType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isSingleClassifierType(@NotNull SimpleTypeMarker $this$isSingleClassifierType) {
        return ClassicTypeSystemContext.DefaultImpls.isSingleClassifierType(this, $this$isSingleClassifierType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isStubType(@NotNull SimpleTypeMarker $this$isStubType) {
        return ClassicTypeSystemContext.DefaultImpls.isStubType(this, $this$isStubType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public Collection<KotlinTypeMarker> possibleIntegerTypes(@NotNull SimpleTypeMarker $this$possibleIntegerTypes) {
        return ClassicTypeSystemContext.DefaultImpls.possibleIntegerTypes(this, $this$possibleIntegerTypes);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeSystemContext, kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public TypeConstructorMarker typeConstructor(@NotNull SimpleTypeMarker $this$typeConstructor) {
        return ClassicTypeSystemContext.DefaultImpls.typeConstructor((ClassicTypeSystemContext) this, $this$typeConstructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeSystemContext, kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public SimpleTypeMarker withNullability(@NotNull SimpleTypeMarker $this$withNullability, boolean nullable) {
        return ClassicTypeSystemContext.DefaultImpls.withNullability((ClassicTypeSystemContext) this, $this$withNullability, nullable);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public TypeArgumentMarker get(@NotNull TypeArgumentListMarker $this$get, int index) {
        return ClassicTypeSystemContext.DefaultImpls.get(this, $this$get, index);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public int size(@NotNull TypeArgumentListMarker $this$size) {
        return ClassicTypeSystemContext.DefaultImpls.size(this, $this$size);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public KotlinTypeMarker getType(@NotNull TypeArgumentMarker $this$getType) {
        return ClassicTypeSystemContext.DefaultImpls.getType(this, $this$getType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public TypeVariance getVariance(@NotNull TypeArgumentMarker $this$getVariance) {
        return ClassicTypeSystemContext.DefaultImpls.getVariance(this, $this$getVariance);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isStarProjection(@NotNull TypeArgumentMarker $this$isStarProjection) {
        return ClassicTypeSystemContext.DefaultImpls.isStarProjection(this, $this$isStarProjection);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    @NotNull
    public FqNameUnsafe getClassFqNameUnsafe(@NotNull TypeConstructorMarker $this$getClassFqNameUnsafe) {
        return ClassicTypeSystemContext.DefaultImpls.getClassFqNameUnsafe(this, $this$getClassFqNameUnsafe);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public TypeParameterMarker getParameter(@NotNull TypeConstructorMarker $this$getParameter, int index) {
        return ClassicTypeSystemContext.DefaultImpls.getParameter(this, $this$getParameter, index);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    @Nullable
    public PrimitiveType getPrimitiveArrayType(@NotNull TypeConstructorMarker $this$getPrimitiveArrayType) {
        return ClassicTypeSystemContext.DefaultImpls.getPrimitiveArrayType(this, $this$getPrimitiveArrayType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    @Nullable
    public PrimitiveType getPrimitiveType(@NotNull TypeConstructorMarker $this$getPrimitiveType) {
        return ClassicTypeSystemContext.DefaultImpls.getPrimitiveType(this, $this$getPrimitiveType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    @Nullable
    public TypeParameterMarker getTypeParameterClassifier(@NotNull TypeConstructorMarker $this$getTypeParameterClassifier) {
        return ClassicTypeSystemContext.DefaultImpls.getTypeParameterClassifier(this, $this$getTypeParameterClassifier);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isAnyConstructor(@NotNull TypeConstructorMarker $this$isAnyConstructor) {
        return ClassicTypeSystemContext.DefaultImpls.isAnyConstructor(this, $this$isAnyConstructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isClassTypeConstructor(@NotNull TypeConstructorMarker $this$isClassTypeConstructor) {
        return ClassicTypeSystemContext.DefaultImpls.isClassTypeConstructor(this, $this$isClassTypeConstructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isCommonFinalClassConstructor(@NotNull TypeConstructorMarker $this$isCommonFinalClassConstructor) {
        return ClassicTypeSystemContext.DefaultImpls.isCommonFinalClassConstructor(this, $this$isCommonFinalClassConstructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isDenotable(@NotNull TypeConstructorMarker $this$isDenotable) {
        return ClassicTypeSystemContext.DefaultImpls.isDenotable(this, $this$isDenotable);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public boolean isInlineClass(@NotNull TypeConstructorMarker $this$isInlineClass) {
        return ClassicTypeSystemContext.DefaultImpls.isInlineClass(this, $this$isInlineClass);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isIntegerLiteralTypeConstructor(@NotNull TypeConstructorMarker $this$isIntegerLiteralTypeConstructor) {
        return ClassicTypeSystemContext.DefaultImpls.isIntegerLiteralTypeConstructor(this, $this$isIntegerLiteralTypeConstructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isIntersection(@NotNull TypeConstructorMarker $this$isIntersection) {
        return ClassicTypeSystemContext.DefaultImpls.isIntersection(this, $this$isIntersection);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isNothingConstructor(@NotNull TypeConstructorMarker $this$isNothingConstructor) {
        return ClassicTypeSystemContext.DefaultImpls.isNothingConstructor(this, $this$isNothingConstructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public boolean isUnderKotlinPackage(@NotNull TypeConstructorMarker $this$isUnderKotlinPackage) {
        return ClassicTypeSystemContext.DefaultImpls.isUnderKotlinPackage(this, $this$isUnderKotlinPackage);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public int parametersCount(@NotNull TypeConstructorMarker $this$parametersCount) {
        return ClassicTypeSystemContext.DefaultImpls.parametersCount(this, $this$parametersCount);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public Collection<KotlinTypeMarker> supertypes(@NotNull TypeConstructorMarker $this$supertypes) {
        return ClassicTypeSystemContext.DefaultImpls.supertypes(this, $this$supertypes);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    @NotNull
    public KotlinTypeMarker getRepresentativeUpperBound(@NotNull TypeParameterMarker $this$getRepresentativeUpperBound) {
        return ClassicTypeSystemContext.DefaultImpls.getRepresentativeUpperBound(this, $this$getRepresentativeUpperBound);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public TypeVariance getVariance(@NotNull TypeParameterMarker $this$getVariance) {
        return ClassicTypeSystemContext.DefaultImpls.getVariance(this, $this$getVariance);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean hasRecursiveBounds(@NotNull TypeParameterMarker $this$hasRecursiveBounds, @NotNull TypeConstructorMarker selfConstructor) {
        return ClassicTypeSystemContext.DefaultImpls.hasRecursiveBounds(this, $this$hasRecursiveBounds, selfConstructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @Nullable
    public TypeParameterMarker getTypeParameter(@NotNull TypeVariableTypeConstructorMarker $this$typeParameter) {
        return ClassicTypeSystemContext.DefaultImpls.getTypeParameter(this, $this$typeParameter);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean areEqualTypeConstructors(@NotNull TypeConstructorMarker c1, @NotNull TypeConstructorMarker c2) {
        Intrinsics.checkNotNullParameter(c1, "c1");
        Intrinsics.checkNotNullParameter(c2, "c2");
        if (!(c1 instanceof TypeConstructor)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (c2 instanceof TypeConstructor) {
            return ClassicTypeSystemContext.DefaultImpls.areEqualTypeConstructors(this, c1, c2) || areEqualTypeConstructorsByAxioms((TypeConstructor) c1, (TypeConstructor) c2);
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    @NotNull
    public AbstractTypeCheckerContext newBaseTypeCheckerContext(boolean errorTypesEqualToAnything, boolean stubTypesEqualToAnything) {
        return new ClassicTypeCheckerContext(errorTypesEqualToAnything, stubTypesEqualToAnything, true, this.kotlinTypeRefiner, null, this, 16, null);
    }

    private final boolean areEqualTypeConstructorsByAxioms(TypeConstructor a, TypeConstructor b) {
        if (this.equalityAxioms.equals(a, b)) {
            return true;
        }
        if (this.matchingTypeConstructors == null) {
            return false;
        }
        TypeConstructor img1 = this.matchingTypeConstructors.get(a);
        TypeConstructor img2 = this.matchingTypeConstructors.get(b);
        return (img1 != null && Intrinsics.areEqual(img1, b)) || (img2 != null && Intrinsics.areEqual(img2, a));
    }
}
