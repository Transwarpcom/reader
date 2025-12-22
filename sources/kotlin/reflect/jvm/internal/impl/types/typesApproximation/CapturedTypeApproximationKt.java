package kotlin.reflect.jvm.internal.impl.types.typesApproximation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.renderer.ClassifierNamePolicy;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructor;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CapturedTypeApproximation.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/typesApproximation/CapturedTypeApproximationKt.class */
public final class CapturedTypeApproximationKt {

    /* compiled from: CapturedTypeApproximation.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/typesApproximation/CapturedTypeApproximationKt$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Variance.values().length];
            iArr[Variance.INVARIANT.ordinal()] = 1;
            iArr[Variance.IN_VARIANCE.ordinal()] = 2;
            iArr[Variance.OUT_VARIANCE.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static final TypeProjection toTypeProjection(TypeArgument $this$toTypeProjection) {
        boolean zIsConsistent = $this$toTypeProjection.isConsistent();
        if (!_Assertions.ENABLED || zIsConsistent) {
            return (Intrinsics.areEqual($this$toTypeProjection.getInProjection(), $this$toTypeProjection.getOutProjection()) || $this$toTypeProjection.getTypeParameter().getVariance() == Variance.IN_VARIANCE) ? new TypeProjectionImpl($this$toTypeProjection.getInProjection()) : (!KotlinBuiltIns.isNothing($this$toTypeProjection.getInProjection()) || $this$toTypeProjection.getTypeParameter().getVariance() == Variance.IN_VARIANCE) ? KotlinBuiltIns.isNullableAny($this$toTypeProjection.getOutProjection()) ? new TypeProjectionImpl(toTypeProjection$removeProjectionIfRedundant($this$toTypeProjection, Variance.IN_VARIANCE), $this$toTypeProjection.getInProjection()) : new TypeProjectionImpl(toTypeProjection$removeProjectionIfRedundant($this$toTypeProjection, Variance.OUT_VARIANCE), $this$toTypeProjection.getOutProjection()) : new TypeProjectionImpl(toTypeProjection$removeProjectionIfRedundant($this$toTypeProjection, Variance.OUT_VARIANCE), $this$toTypeProjection.getOutProjection());
        }
        DescriptorRenderer descriptorRenderer = DescriptorRenderer.Companion.withOptions(new Function1<DescriptorRendererOptions, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt$toTypeProjection$1$descriptorRenderer$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DescriptorRendererOptions descriptorRendererOptions) {
                invoke2(descriptorRendererOptions);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull DescriptorRendererOptions withOptions) {
                Intrinsics.checkNotNullParameter(withOptions, "$this$withOptions");
                withOptions.setClassifierNamePolicy(ClassifierNamePolicy.FULLY_QUALIFIED.INSTANCE);
            }
        });
        throw new AssertionError("Only consistent enhanced type projection can be converted to type projection, but [" + descriptorRenderer.render($this$toTypeProjection.getTypeParameter()) + ": <" + descriptorRenderer.renderType($this$toTypeProjection.getInProjection()) + ", " + descriptorRenderer.renderType($this$toTypeProjection.getOutProjection()) + ">] was found");
    }

    private static final Variance toTypeProjection$removeProjectionIfRedundant(TypeArgument $this_toTypeProjection, Variance variance) {
        return variance == $this_toTypeProjection.getTypeParameter().getVariance() ? Variance.INVARIANT : variance;
    }

    private static final TypeArgument toTypeArgument(TypeProjection $this$toTypeArgument, TypeParameterDescriptor typeParameter) {
        switch (WhenMappings.$EnumSwitchMapping$0[TypeSubstitutor.combine(typeParameter.getVariance(), $this$toTypeArgument).ordinal()]) {
            case 1:
                KotlinType type = $this$toTypeArgument.getType();
                Intrinsics.checkNotNullExpressionValue(type, "type");
                KotlinType type2 = $this$toTypeArgument.getType();
                Intrinsics.checkNotNullExpressionValue(type2, "type");
                return new TypeArgument(typeParameter, type, type2);
            case 2:
                KotlinType type3 = $this$toTypeArgument.getType();
                Intrinsics.checkNotNullExpressionValue(type3, "type");
                SimpleType nullableAnyType = DescriptorUtilsKt.getBuiltIns(typeParameter).getNullableAnyType();
                Intrinsics.checkNotNullExpressionValue(nullableAnyType, "typeParameter.builtIns.nullableAnyType");
                return new TypeArgument(typeParameter, type3, nullableAnyType);
            case 3:
                SimpleType nothingType = DescriptorUtilsKt.getBuiltIns(typeParameter).getNothingType();
                Intrinsics.checkNotNullExpressionValue(nothingType, "typeParameter.builtIns.nothingType");
                KotlinType type4 = $this$toTypeArgument.getType();
                Intrinsics.checkNotNullExpressionValue(type4, "type");
                return new TypeArgument(typeParameter, nothingType, type4);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    @Nullable
    public static final TypeProjection approximateCapturedTypesIfNecessary(@Nullable TypeProjection typeProjection, boolean approximateContravariant) {
        if (typeProjection == null) {
            return null;
        }
        if (typeProjection.isStarProjection()) {
            return typeProjection;
        }
        KotlinType type = typeProjection.getType();
        Intrinsics.checkNotNullExpressionValue(type, "typeProjection.type");
        if (!TypeUtils.contains(type, new Function1<UnwrappedType, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt.approximateCapturedTypesIfNecessary.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(UnwrappedType it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                return Boolean.valueOf(CapturedTypeConstructorKt.isCaptured(it));
            }
        })) {
            return typeProjection;
        }
        Variance howThisTypeIsUsed = typeProjection.getProjectionKind();
        Intrinsics.checkNotNullExpressionValue(howThisTypeIsUsed, "typeProjection.projectionKind");
        if (howThisTypeIsUsed == Variance.OUT_VARIANCE) {
            ApproximationBounds approximation = approximateCapturedTypes(type);
            return new TypeProjectionImpl(howThisTypeIsUsed, approximation.getUpper());
        }
        if (approximateContravariant) {
            KotlinType approximation2 = approximateCapturedTypes(type).getLower();
            return new TypeProjectionImpl(howThisTypeIsUsed, approximation2);
        }
        return substituteCapturedTypesWithProjections(typeProjection);
    }

    private static final TypeProjection substituteCapturedTypesWithProjections(TypeProjection typeProjection) {
        TypeSubstitutor typeSubstitutor = TypeSubstitutor.create(new TypeConstructorSubstitution() { // from class: kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt$substituteCapturedTypesWithProjections$typeSubstitutor$1
            @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution
            @Nullable
            public TypeProjection get(@NotNull TypeConstructor key) {
                Intrinsics.checkNotNullParameter(key, "key");
                CapturedTypeConstructor capturedTypeConstructor = key instanceof CapturedTypeConstructor ? (CapturedTypeConstructor) key : null;
                if (capturedTypeConstructor == null) {
                    return null;
                }
                if (capturedTypeConstructor.getProjection().isStarProjection()) {
                    return new TypeProjectionImpl(Variance.OUT_VARIANCE, capturedTypeConstructor.getProjection().getType());
                }
                return capturedTypeConstructor.getProjection();
            }
        });
        Intrinsics.checkNotNullExpressionValue(typeSubstitutor, "create(object : TypeConstructorSubstitution() {\n        override fun get(key: TypeConstructor): TypeProjection? {\n            val capturedTypeConstructor = key as? CapturedTypeConstructor ?: return null\n            if (capturedTypeConstructor.projection.isStarProjection) {\n                return TypeProjectionImpl(Variance.OUT_VARIANCE, capturedTypeConstructor.projection.type)\n            }\n            return capturedTypeConstructor.projection\n        }\n    })");
        return typeSubstitutor.substituteWithoutApproximation(typeProjection);
    }

    @NotNull
    public static final ApproximationBounds<KotlinType> approximateCapturedTypes(@NotNull KotlinType type) {
        boolean z;
        SimpleType simpleTypeReplaceTypeArguments;
        Intrinsics.checkNotNullParameter(type, "type");
        if (FlexibleTypesKt.isFlexible(type)) {
            ApproximationBounds boundsForFlexibleLower = approximateCapturedTypes(FlexibleTypesKt.lowerIfFlexible(type));
            ApproximationBounds boundsForFlexibleUpper = approximateCapturedTypes(FlexibleTypesKt.upperIfFlexible(type));
            KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
            UnwrappedType unwrappedTypeInheritEnhancement = TypeWithEnhancementKt.inheritEnhancement(KotlinTypeFactory.flexibleType(FlexibleTypesKt.lowerIfFlexible(boundsForFlexibleLower.getLower()), FlexibleTypesKt.upperIfFlexible(boundsForFlexibleUpper.getLower())), type);
            KotlinTypeFactory kotlinTypeFactory2 = KotlinTypeFactory.INSTANCE;
            return new ApproximationBounds<>(unwrappedTypeInheritEnhancement, TypeWithEnhancementKt.inheritEnhancement(KotlinTypeFactory.flexibleType(FlexibleTypesKt.lowerIfFlexible(boundsForFlexibleLower.getUpper()), FlexibleTypesKt.upperIfFlexible(boundsForFlexibleUpper.getUpper())), type));
        }
        TypeConstructor typeConstructor = type.getConstructor();
        if (CapturedTypeConstructorKt.isCaptured(type)) {
            TypeProjection typeProjection = ((CapturedTypeConstructor) typeConstructor).getProjection();
            KotlinType type2 = typeProjection.getType();
            Intrinsics.checkNotNullExpressionValue(type2, "typeProjection.type");
            KotlinType bound = approximateCapturedTypes$makeNullableIfNeeded(type2, type);
            switch (WhenMappings.$EnumSwitchMapping$0[typeProjection.getProjectionKind().ordinal()]) {
                case 2:
                    SimpleType nullableAnyType = TypeUtilsKt.getBuiltIns(type).getNullableAnyType();
                    Intrinsics.checkNotNullExpressionValue(nullableAnyType, "type.builtIns.nullableAnyType");
                    return new ApproximationBounds<>(bound, nullableAnyType);
                case 3:
                    SimpleType nothingType = TypeUtilsKt.getBuiltIns(type).getNothingType();
                    Intrinsics.checkNotNullExpressionValue(nothingType, "type.builtIns.nothingType");
                    return new ApproximationBounds<>(approximateCapturedTypes$makeNullableIfNeeded(nothingType, type), bound);
                default:
                    throw new AssertionError(Intrinsics.stringPlus("Only nontrivial projections should have been captured, not: ", typeProjection));
            }
        }
        if (type.getArguments().isEmpty() || type.getArguments().size() != typeConstructor.getParameters().size()) {
            return new ApproximationBounds<>(type, type);
        }
        ArrayList lowerBoundArguments = new ArrayList();
        ArrayList upperBoundArguments = new ArrayList();
        List<TypeProjection> arguments = type.getArguments();
        List<TypeParameterDescriptor> parameters = typeConstructor.getParameters();
        Intrinsics.checkNotNullExpressionValue(parameters, "typeConstructor.parameters");
        for (Pair pair : CollectionsKt.zip(arguments, parameters)) {
            TypeProjection typeProjection2 = (TypeProjection) pair.component1();
            TypeParameterDescriptor typeParameter = (TypeParameterDescriptor) pair.component2();
            Intrinsics.checkNotNullExpressionValue(typeParameter, "typeParameter");
            TypeArgument typeArgument = toTypeArgument(typeProjection2, typeParameter);
            if (typeProjection2.isStarProjection()) {
                lowerBoundArguments.add(typeArgument);
                upperBoundArguments.add(typeArgument);
            } else {
                ApproximationBounds<TypeArgument> approximationBoundsApproximateProjection = approximateProjection(typeArgument);
                TypeArgument lower = approximationBoundsApproximateProjection.component1();
                TypeArgument upper = approximationBoundsApproximateProjection.component2();
                lowerBoundArguments.add(lower);
                upperBoundArguments.add(upper);
            }
        }
        ArrayList $this$any$iv = lowerBoundArguments;
        if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    TypeArgument it2 = (TypeArgument) element$iv;
                    if (!it2.isConsistent()) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
        } else {
            z = false;
        }
        boolean lowerBoundIsTrivial = z;
        if (lowerBoundIsTrivial) {
            SimpleType nothingType2 = TypeUtilsKt.getBuiltIns(type).getNothingType();
            Intrinsics.checkNotNullExpressionValue(nothingType2, "type.builtIns.nothingType");
            simpleTypeReplaceTypeArguments = nothingType2;
        } else {
            simpleTypeReplaceTypeArguments = replaceTypeArguments(type, lowerBoundArguments);
        }
        return new ApproximationBounds<>(simpleTypeReplaceTypeArguments, replaceTypeArguments(type, upperBoundArguments));
    }

    private static final KotlinType approximateCapturedTypes$makeNullableIfNeeded(KotlinType $this$approximateCapturedTypes_u24makeNullableIfNeeded, KotlinType $type) {
        KotlinType kotlinTypeMakeNullableIfNeeded = TypeUtils.makeNullableIfNeeded($this$approximateCapturedTypes_u24makeNullableIfNeeded, $type.isMarkedNullable());
        Intrinsics.checkNotNullExpressionValue(kotlinTypeMakeNullableIfNeeded, "makeNullableIfNeeded(this, type.isMarkedNullable)");
        return kotlinTypeMakeNullableIfNeeded;
    }

    private static final KotlinType replaceTypeArguments(KotlinType $this$replaceTypeArguments, List<TypeArgument> list) {
        boolean z = $this$replaceTypeArguments.getArguments().size() == list.size();
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError(Intrinsics.stringPlus("Incorrect type arguments ", list));
        }
        List<TypeArgument> $this$map$iv = list;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            TypeArgument it = (TypeArgument) item$iv$iv;
            destination$iv$iv.add(toTypeProjection(it));
        }
        return TypeSubstitutionKt.replace$default($this$replaceTypeArguments, (List) destination$iv$iv, null, null, 6, null);
    }

    private static final ApproximationBounds<TypeArgument> approximateProjection(TypeArgument typeArgument) {
        ApproximationBounds<KotlinType> approximationBoundsApproximateCapturedTypes = approximateCapturedTypes(typeArgument.getInProjection());
        KotlinType inLower = approximationBoundsApproximateCapturedTypes.component1();
        KotlinType inUpper = approximationBoundsApproximateCapturedTypes.component2();
        ApproximationBounds<KotlinType> approximationBoundsApproximateCapturedTypes2 = approximateCapturedTypes(typeArgument.getOutProjection());
        KotlinType outLower = approximationBoundsApproximateCapturedTypes2.component1();
        KotlinType outUpper = approximationBoundsApproximateCapturedTypes2.component2();
        return new ApproximationBounds<>(new TypeArgument(typeArgument.getTypeParameter(), inUpper, outLower), new TypeArgument(typeArgument.getTypeParameter(), inLower, outUpper));
    }
}
