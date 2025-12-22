package kotlin.reflect.jvm.internal.impl.types.typeUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.StubType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariableTypeConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TypeUtils.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/typeUtil/TypeUtilsKt.class */
public final class TypeUtilsKt {
    @NotNull
    public static final KotlinBuiltIns getBuiltIns(@NotNull KotlinType $this$builtIns) {
        Intrinsics.checkNotNullParameter($this$builtIns, "<this>");
        KotlinBuiltIns builtIns = $this$builtIns.getConstructor().getBuiltIns();
        Intrinsics.checkNotNullExpressionValue(builtIns, "constructor.builtIns");
        return builtIns;
    }

    @NotNull
    public static final KotlinType makeNullable(@NotNull KotlinType $this$makeNullable) {
        Intrinsics.checkNotNullParameter($this$makeNullable, "<this>");
        KotlinType kotlinTypeMakeNullable = TypeUtils.makeNullable($this$makeNullable);
        Intrinsics.checkNotNullExpressionValue(kotlinTypeMakeNullable, "makeNullable(this)");
        return kotlinTypeMakeNullable;
    }

    @NotNull
    public static final KotlinType makeNotNullable(@NotNull KotlinType $this$makeNotNullable) {
        Intrinsics.checkNotNullParameter($this$makeNotNullable, "<this>");
        KotlinType kotlinTypeMakeNotNullable = TypeUtils.makeNotNullable($this$makeNotNullable);
        Intrinsics.checkNotNullExpressionValue(kotlinTypeMakeNotNullable, "makeNotNullable(this)");
        return kotlinTypeMakeNotNullable;
    }

    public static final boolean isTypeParameter(@NotNull KotlinType $this$isTypeParameter) {
        Intrinsics.checkNotNullParameter($this$isTypeParameter, "<this>");
        return TypeUtils.isTypeParameter($this$isTypeParameter);
    }

    public static final boolean isSubtypeOf(@NotNull KotlinType $this$isSubtypeOf, @NotNull KotlinType superType) {
        Intrinsics.checkNotNullParameter($this$isSubtypeOf, "<this>");
        Intrinsics.checkNotNullParameter(superType, "superType");
        return KotlinTypeChecker.DEFAULT.isSubtypeOf($this$isSubtypeOf, superType);
    }

    @NotNull
    public static final KotlinType replaceAnnotations(@NotNull KotlinType $this$replaceAnnotations, @NotNull Annotations newAnnotations) {
        Intrinsics.checkNotNullParameter($this$replaceAnnotations, "<this>");
        Intrinsics.checkNotNullParameter(newAnnotations, "newAnnotations");
        return ($this$replaceAnnotations.getAnnotations().isEmpty() && newAnnotations.isEmpty()) ? $this$replaceAnnotations : $this$replaceAnnotations.unwrap().replaceAnnotations(newAnnotations);
    }

    @NotNull
    public static final TypeProjection createProjection(@NotNull KotlinType type, @NotNull Variance projectionKind, @Nullable TypeParameterDescriptor typeParameterDescriptor) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(projectionKind, "projectionKind");
        return new TypeProjectionImpl((typeParameterDescriptor == null ? null : typeParameterDescriptor.getVariance()) == projectionKind ? Variance.INVARIANT : projectionKind, type);
    }

    @NotNull
    public static final TypeProjection asTypeProjection(@NotNull KotlinType $this$asTypeProjection) {
        Intrinsics.checkNotNullParameter($this$asTypeProjection, "<this>");
        return new TypeProjectionImpl($this$asTypeProjection);
    }

    public static final boolean contains(@NotNull KotlinType $this$contains, @NotNull Function1<? super UnwrappedType, Boolean> predicate) {
        Intrinsics.checkNotNullParameter($this$contains, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        return TypeUtils.contains($this$contains, predicate);
    }

    @NotNull
    public static final KotlinType replaceArgumentsWithStarProjections(@NotNull KotlinType $this$replaceArgumentsWithStarProjections) {
        SimpleType simpleTypeReplace$default;
        SimpleType simpleTypeFlexibleType;
        SimpleType simpleTypeReplace$default2;
        SimpleType simpleTypeReplace$default3;
        Intrinsics.checkNotNullParameter($this$replaceArgumentsWithStarProjections, "<this>");
        UnwrappedType unwrapped$iv = $this$replaceArgumentsWithStarProjections.unwrap();
        if (unwrapped$iv instanceof FlexibleType) {
            KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
            SimpleType $this$replaceArgumentsWith$iv$iv = ((FlexibleType) unwrapped$iv).getLowerBound();
            if ($this$replaceArgumentsWith$iv$iv.getConstructor().getParameters().isEmpty() || $this$replaceArgumentsWith$iv$iv.getConstructor().mo3831getDeclarationDescriptor() == null) {
                simpleTypeReplace$default2 = $this$replaceArgumentsWith$iv$iv;
            } else {
                Iterable parameters = $this$replaceArgumentsWith$iv$iv.getConstructor().getParameters();
                Intrinsics.checkNotNullExpressionValue(parameters, "constructor.parameters");
                Iterable $this$map$iv$iv$iv = parameters;
                Collection destination$iv$iv$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv$iv$iv, 10));
                for (Object item$iv$iv$iv$iv : $this$map$iv$iv$iv) {
                    TypeParameterDescriptor p0 = (TypeParameterDescriptor) item$iv$iv$iv$iv;
                    destination$iv$iv$iv$iv.add(new StarProjectionImpl(p0));
                }
                List newArguments$iv$iv = (List) destination$iv$iv$iv$iv;
                simpleTypeReplace$default2 = TypeSubstitutionKt.replace$default($this$replaceArgumentsWith$iv$iv, newArguments$iv$iv, null, 2, null);
            }
            SimpleType $this$replaceArgumentsWith$iv$iv2 = ((FlexibleType) unwrapped$iv).getUpperBound();
            SimpleType simpleType = simpleTypeReplace$default2;
            if ($this$replaceArgumentsWith$iv$iv2.getConstructor().getParameters().isEmpty() || $this$replaceArgumentsWith$iv$iv2.getConstructor().mo3831getDeclarationDescriptor() == null) {
                simpleTypeReplace$default3 = $this$replaceArgumentsWith$iv$iv2;
            } else {
                Iterable parameters2 = $this$replaceArgumentsWith$iv$iv2.getConstructor().getParameters();
                Intrinsics.checkNotNullExpressionValue(parameters2, "constructor.parameters");
                Iterable $this$map$iv$iv$iv2 = parameters2;
                Collection destination$iv$iv$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv$iv$iv2, 10));
                for (Object item$iv$iv$iv$iv2 : $this$map$iv$iv$iv2) {
                    TypeParameterDescriptor p02 = (TypeParameterDescriptor) item$iv$iv$iv$iv2;
                    destination$iv$iv$iv$iv2.add(new StarProjectionImpl(p02));
                }
                List newArguments$iv$iv2 = (List) destination$iv$iv$iv$iv2;
                simpleTypeReplace$default3 = TypeSubstitutionKt.replace$default($this$replaceArgumentsWith$iv$iv2, newArguments$iv$iv2, null, 2, null);
            }
            simpleTypeFlexibleType = KotlinTypeFactory.flexibleType(simpleType, simpleTypeReplace$default3);
        } else {
            if (!(unwrapped$iv instanceof SimpleType)) {
                throw new NoWhenBranchMatchedException();
            }
            SimpleType $this$replaceArgumentsWith$iv$iv3 = (SimpleType) unwrapped$iv;
            if ($this$replaceArgumentsWith$iv$iv3.getConstructor().getParameters().isEmpty() || $this$replaceArgumentsWith$iv$iv3.getConstructor().mo3831getDeclarationDescriptor() == null) {
                simpleTypeReplace$default = $this$replaceArgumentsWith$iv$iv3;
            } else {
                Iterable parameters3 = $this$replaceArgumentsWith$iv$iv3.getConstructor().getParameters();
                Intrinsics.checkNotNullExpressionValue(parameters3, "constructor.parameters");
                Iterable $this$map$iv$iv$iv3 = parameters3;
                Collection destination$iv$iv$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv$iv$iv3, 10));
                for (Object item$iv$iv$iv$iv3 : $this$map$iv$iv$iv3) {
                    TypeParameterDescriptor p03 = (TypeParameterDescriptor) item$iv$iv$iv$iv3;
                    destination$iv$iv$iv$iv3.add(new StarProjectionImpl(p03));
                }
                List newArguments$iv$iv3 = (List) destination$iv$iv$iv$iv3;
                simpleTypeReplace$default = TypeSubstitutionKt.replace$default($this$replaceArgumentsWith$iv$iv3, newArguments$iv$iv3, null, 2, null);
            }
            simpleTypeFlexibleType = simpleTypeReplace$default;
        }
        return TypeWithEnhancementKt.inheritEnhancement(simpleTypeFlexibleType, unwrapped$iv);
    }

    @NotNull
    public static final Set<TypeParameterDescriptor> extractTypeParametersFromUpperBounds(@NotNull KotlinType $this$extractTypeParametersFromUpperBounds, @Nullable Set<? extends TypeParameterDescriptor> set) {
        Intrinsics.checkNotNullParameter($this$extractTypeParametersFromUpperBounds, "<this>");
        Set it = new LinkedHashSet();
        extractTypeParametersFromUpperBounds($this$extractTypeParametersFromUpperBounds, $this$extractTypeParametersFromUpperBounds, it, set);
        return it;
    }

    private static final void extractTypeParametersFromUpperBounds(KotlinType $this$extractTypeParametersFromUpperBounds, KotlinType baseType, Set<TypeParameterDescriptor> set, Set<? extends TypeParameterDescriptor> set2) {
        ClassifierDescriptor declarationDescriptor = $this$extractTypeParametersFromUpperBounds.getConstructor().mo3831getDeclarationDescriptor();
        if (declarationDescriptor instanceof TypeParameterDescriptor) {
            if (!Intrinsics.areEqual($this$extractTypeParametersFromUpperBounds.getConstructor(), baseType.getConstructor())) {
                set.add(declarationDescriptor);
                return;
            }
            for (KotlinType upperBound : ((TypeParameterDescriptor) declarationDescriptor).getUpperBounds()) {
                Intrinsics.checkNotNullExpressionValue(upperBound, "upperBound");
                extractTypeParametersFromUpperBounds(upperBound, baseType, set, set2);
            }
            return;
        }
        ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = $this$extractTypeParametersFromUpperBounds.getConstructor().mo3831getDeclarationDescriptor();
        ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters = classifierDescriptorMo3831getDeclarationDescriptor instanceof ClassifierDescriptorWithTypeParameters ? (ClassifierDescriptorWithTypeParameters) classifierDescriptorMo3831getDeclarationDescriptor : null;
        List typeParameters = classifierDescriptorWithTypeParameters == null ? null : classifierDescriptorWithTypeParameters.getDeclaredTypeParameters();
        int i = 0;
        for (TypeProjection argument : $this$extractTypeParametersFromUpperBounds.getArguments()) {
            int i2 = i;
            i++;
            TypeParameterDescriptor typeParameter = typeParameters == null ? null : (TypeParameterDescriptor) CollectionsKt.getOrNull(typeParameters, i2);
            boolean isTypeParameterVisited = (typeParameter == null || set2 == null || !set2.contains(typeParameter)) ? false : true;
            if (!isTypeParameterVisited && !argument.isStarProjection() && !CollectionsKt.contains(set, argument.getType().getConstructor().mo3831getDeclarationDescriptor()) && !Intrinsics.areEqual(argument.getType().getConstructor(), baseType.getConstructor())) {
                KotlinType type = argument.getType();
                Intrinsics.checkNotNullExpressionValue(type, "argument.type");
                extractTypeParametersFromUpperBounds(type, baseType, set, set2);
            }
        }
    }

    public static /* synthetic */ boolean hasTypeParameterRecursiveBounds$default(TypeParameterDescriptor typeParameterDescriptor, TypeConstructor typeConstructor, Set set, int i, Object obj) {
        if ((i & 2) != 0) {
            typeConstructor = null;
        }
        if ((i & 4) != 0) {
            set = null;
        }
        return hasTypeParameterRecursiveBounds(typeParameterDescriptor, typeConstructor, set);
    }

    public static final boolean hasTypeParameterRecursiveBounds(@NotNull TypeParameterDescriptor typeParameter, @Nullable TypeConstructor selfConstructor, @Nullable Set<? extends TypeParameterDescriptor> set) {
        Intrinsics.checkNotNullParameter(typeParameter, "typeParameter");
        Iterable upperBounds = typeParameter.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(upperBounds, "typeParameter.upperBounds");
        Iterable $this$any$iv = upperBounds;
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            KotlinType upperBound = (KotlinType) element$iv;
            Intrinsics.checkNotNullExpressionValue(upperBound, "upperBound");
            if (containsSelfTypeParameter(upperBound, typeParameter.getDefaultType().getConstructor(), set) && (selfConstructor == null || Intrinsics.areEqual(upperBound.getConstructor(), selfConstructor))) {
                return true;
            }
        }
        return false;
    }

    private static final boolean containsSelfTypeParameter(KotlinType $this$containsSelfTypeParameter, TypeConstructor baseConstructor, Set<? extends TypeParameterDescriptor> set) {
        boolean zContainsSelfTypeParameter;
        if (Intrinsics.areEqual($this$containsSelfTypeParameter.getConstructor(), baseConstructor)) {
            return true;
        }
        ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = $this$containsSelfTypeParameter.getConstructor().mo3831getDeclarationDescriptor();
        ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters = classifierDescriptorMo3831getDeclarationDescriptor instanceof ClassifierDescriptorWithTypeParameters ? (ClassifierDescriptorWithTypeParameters) classifierDescriptorMo3831getDeclarationDescriptor : null;
        List typeParameters = classifierDescriptorWithTypeParameters == null ? null : classifierDescriptorWithTypeParameters.getDeclaredTypeParameters();
        Iterable $this$any$iv = CollectionsKt.withIndex($this$containsSelfTypeParameter.getArguments());
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            IndexedValue $dstr$i$argument = (IndexedValue) element$iv;
            int i = $dstr$i$argument.component1();
            TypeProjection argument = (TypeProjection) $dstr$i$argument.component2();
            TypeParameterDescriptor typeParameter = typeParameters == null ? null : (TypeParameterDescriptor) CollectionsKt.getOrNull(typeParameters, i);
            boolean isTypeParameterVisited = (typeParameter == null || set == null || !set.contains(typeParameter)) ? false : true;
            if (isTypeParameterVisited || argument.isStarProjection()) {
                zContainsSelfTypeParameter = false;
            } else {
                KotlinType type = argument.getType();
                Intrinsics.checkNotNullExpressionValue(type, "argument.type");
                zContainsSelfTypeParameter = containsSelfTypeParameter(type, baseConstructor, set);
            }
            if (zContainsSelfTypeParameter) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public static final KotlinType replaceArgumentsWithStarProjectionOrMapped(@NotNull KotlinType $this$replaceArgumentsWithStarProjectionOrMapped, @NotNull TypeSubstitutor substitutor, @NotNull Map<TypeConstructor, ? extends TypeProjection> substitutionMap, @NotNull Variance variance, @Nullable Set<? extends TypeParameterDescriptor> set) {
        SimpleType simpleTypeReplace$default;
        SimpleType simpleTypeFlexibleType;
        SimpleType simpleTypeReplace$default2;
        SimpleType simpleTypeReplace$default3;
        Intrinsics.checkNotNullParameter($this$replaceArgumentsWithStarProjectionOrMapped, "<this>");
        Intrinsics.checkNotNullParameter(substitutor, "substitutor");
        Intrinsics.checkNotNullParameter(substitutionMap, "substitutionMap");
        Intrinsics.checkNotNullParameter(variance, "variance");
        UnwrappedType unwrapped$iv = $this$replaceArgumentsWithStarProjectionOrMapped.unwrap();
        if (unwrapped$iv instanceof FlexibleType) {
            KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
            SimpleType $this$replaceArgumentsWith$iv$iv = ((FlexibleType) unwrapped$iv).getLowerBound();
            if ($this$replaceArgumentsWith$iv$iv.getConstructor().getParameters().isEmpty() || $this$replaceArgumentsWith$iv$iv.getConstructor().mo3831getDeclarationDescriptor() == null) {
                simpleTypeReplace$default2 = $this$replaceArgumentsWith$iv$iv;
            } else {
                Iterable parameters = $this$replaceArgumentsWith$iv$iv.getConstructor().getParameters();
                Intrinsics.checkNotNullExpressionValue(parameters, "constructor.parameters");
                Iterable $this$map$iv$iv$iv = parameters;
                Collection destination$iv$iv$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv$iv$iv, 10));
                for (Object item$iv$iv$iv$iv : $this$map$iv$iv$iv) {
                    TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) item$iv$iv$iv$iv;
                    TypeProjection argument = (TypeProjection) CollectionsKt.getOrNull($this$replaceArgumentsWithStarProjectionOrMapped.getArguments(), typeParameterDescriptor.getIndex());
                    boolean isTypeParameterVisited = set != null && set.contains(typeParameterDescriptor);
                    destination$iv$iv$iv$iv.add((isTypeParameterVisited || argument == null || !substitutionMap.containsKey(argument.getType().getConstructor())) ? new StarProjectionImpl(typeParameterDescriptor) : argument);
                }
                List newArguments$iv$iv = (List) destination$iv$iv$iv$iv;
                simpleTypeReplace$default2 = TypeSubstitutionKt.replace$default($this$replaceArgumentsWith$iv$iv, newArguments$iv$iv, null, 2, null);
            }
            SimpleType $this$replaceArgumentsWith$iv$iv2 = ((FlexibleType) unwrapped$iv).getUpperBound();
            SimpleType simpleType = simpleTypeReplace$default2;
            if ($this$replaceArgumentsWith$iv$iv2.getConstructor().getParameters().isEmpty() || $this$replaceArgumentsWith$iv$iv2.getConstructor().mo3831getDeclarationDescriptor() == null) {
                simpleTypeReplace$default3 = $this$replaceArgumentsWith$iv$iv2;
            } else {
                Iterable parameters2 = $this$replaceArgumentsWith$iv$iv2.getConstructor().getParameters();
                Intrinsics.checkNotNullExpressionValue(parameters2, "constructor.parameters");
                Iterable $this$map$iv$iv$iv2 = parameters2;
                Collection destination$iv$iv$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv$iv$iv2, 10));
                for (Object item$iv$iv$iv$iv2 : $this$map$iv$iv$iv2) {
                    TypeParameterDescriptor typeParameterDescriptor2 = (TypeParameterDescriptor) item$iv$iv$iv$iv2;
                    TypeProjection argument2 = (TypeProjection) CollectionsKt.getOrNull($this$replaceArgumentsWithStarProjectionOrMapped.getArguments(), typeParameterDescriptor2.getIndex());
                    boolean isTypeParameterVisited2 = set != null && set.contains(typeParameterDescriptor2);
                    destination$iv$iv$iv$iv2.add((isTypeParameterVisited2 || argument2 == null || !substitutionMap.containsKey(argument2.getType().getConstructor())) ? new StarProjectionImpl(typeParameterDescriptor2) : argument2);
                }
                List newArguments$iv$iv2 = (List) destination$iv$iv$iv$iv2;
                simpleTypeReplace$default3 = TypeSubstitutionKt.replace$default($this$replaceArgumentsWith$iv$iv2, newArguments$iv$iv2, null, 2, null);
            }
            simpleTypeFlexibleType = KotlinTypeFactory.flexibleType(simpleType, simpleTypeReplace$default3);
        } else {
            if (!(unwrapped$iv instanceof SimpleType)) {
                throw new NoWhenBranchMatchedException();
            }
            SimpleType $this$replaceArgumentsWith$iv$iv3 = (SimpleType) unwrapped$iv;
            if ($this$replaceArgumentsWith$iv$iv3.getConstructor().getParameters().isEmpty() || $this$replaceArgumentsWith$iv$iv3.getConstructor().mo3831getDeclarationDescriptor() == null) {
                simpleTypeReplace$default = $this$replaceArgumentsWith$iv$iv3;
            } else {
                Iterable parameters3 = $this$replaceArgumentsWith$iv$iv3.getConstructor().getParameters();
                Intrinsics.checkNotNullExpressionValue(parameters3, "constructor.parameters");
                Iterable $this$map$iv$iv$iv3 = parameters3;
                Collection destination$iv$iv$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv$iv$iv3, 10));
                for (Object item$iv$iv$iv$iv3 : $this$map$iv$iv$iv3) {
                    TypeParameterDescriptor typeParameterDescriptor3 = (TypeParameterDescriptor) item$iv$iv$iv$iv3;
                    TypeProjection argument3 = (TypeProjection) CollectionsKt.getOrNull($this$replaceArgumentsWithStarProjectionOrMapped.getArguments(), typeParameterDescriptor3.getIndex());
                    boolean isTypeParameterVisited3 = set != null && set.contains(typeParameterDescriptor3);
                    destination$iv$iv$iv$iv3.add((isTypeParameterVisited3 || argument3 == null || !substitutionMap.containsKey(argument3.getType().getConstructor())) ? new StarProjectionImpl(typeParameterDescriptor3) : argument3);
                }
                List newArguments$iv$iv3 = (List) destination$iv$iv$iv$iv3;
                simpleTypeReplace$default = TypeSubstitutionKt.replace$default($this$replaceArgumentsWith$iv$iv3, newArguments$iv$iv3, null, 2, null);
            }
            simpleTypeFlexibleType = simpleTypeReplace$default;
        }
        KotlinType it = TypeWithEnhancementKt.inheritEnhancement(simpleTypeFlexibleType, unwrapped$iv);
        KotlinType kotlinTypeSafeSubstitute = substitutor.safeSubstitute(it, variance);
        Intrinsics.checkNotNullExpressionValue(kotlinTypeSafeSubstitute, "replaceArgumentsWith { typeParameterDescriptor ->\n        val argument = arguments.getOrNull(typeParameterDescriptor.index)\n        val isTypeParameterVisited = visitedTypeParameters != null && typeParameterDescriptor in visitedTypeParameters\n        if (!isTypeParameterVisited && argument != null && argument.type.constructor in substitutionMap) {\n            argument\n        } else StarProjectionImpl(typeParameterDescriptor)\n    }.let { substitutor.safeSubstitute(it, variance) }");
        return kotlinTypeSafeSubstitute;
    }

    public static final boolean containsTypeAliasParameters(@NotNull KotlinType $this$containsTypeAliasParameters) {
        Intrinsics.checkNotNullParameter($this$containsTypeAliasParameters, "<this>");
        return contains($this$containsTypeAliasParameters, new Function1<UnwrappedType, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.containsTypeAliasParameters.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(UnwrappedType unwrappedType) {
                return Boolean.valueOf(invoke2(unwrappedType));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(@NotNull UnwrappedType it) {
                Intrinsics.checkNotNullParameter(it, "it");
                ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = it.getConstructor().mo3831getDeclarationDescriptor();
                if (classifierDescriptorMo3831getDeclarationDescriptor == null) {
                    return false;
                }
                return TypeUtilsKt.isTypeAliasParameter(classifierDescriptorMo3831getDeclarationDescriptor);
            }
        });
    }

    public static final boolean isTypeAliasParameter(@NotNull ClassifierDescriptor $this$isTypeAliasParameter) {
        Intrinsics.checkNotNullParameter($this$isTypeAliasParameter, "<this>");
        return ($this$isTypeAliasParameter instanceof TypeParameterDescriptor) && (((TypeParameterDescriptor) $this$isTypeAliasParameter).getContainingDeclaration() instanceof TypeAliasDescriptor);
    }

    public static final boolean requiresTypeAliasExpansion(@NotNull KotlinType $this$requiresTypeAliasExpansion) {
        Intrinsics.checkNotNullParameter($this$requiresTypeAliasExpansion, "<this>");
        return contains($this$requiresTypeAliasExpansion, new Function1<UnwrappedType, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.requiresTypeAliasExpansion.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(UnwrappedType unwrappedType) {
                return Boolean.valueOf(invoke2(unwrappedType));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(@NotNull UnwrappedType it) {
                Intrinsics.checkNotNullParameter(it, "it");
                ClassifierDescriptor it2 = it.getConstructor().mo3831getDeclarationDescriptor();
                if (it2 != null) {
                    return (it2 instanceof TypeAliasDescriptor) || (it2 instanceof TypeParameterDescriptor);
                }
                return false;
            }
        });
    }

    @NotNull
    public static final KotlinType getRepresentativeUpperBound(@NotNull TypeParameterDescriptor $this$representativeUpperBound) {
        Object obj;
        boolean z;
        Intrinsics.checkNotNullParameter($this$representativeUpperBound, "<this>");
        List<KotlinType> upperBounds = $this$representativeUpperBound.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(upperBounds, "upperBounds");
        boolean z2 = !upperBounds.isEmpty();
        if (_Assertions.ENABLED && !z2) {
            throw new AssertionError(Intrinsics.stringPlus("Upper bounds should not be empty: ", $this$representativeUpperBound));
        }
        Iterable upperBounds2 = $this$representativeUpperBound.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(upperBounds2, "upperBounds");
        Iterable $this$firstOrNull$iv = upperBounds2;
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                Object element$iv = it.next();
                KotlinType it2 = (KotlinType) element$iv;
                ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = it2.getConstructor().mo3831getDeclarationDescriptor();
                ClassDescriptor classDescriptor = classifierDescriptorMo3831getDeclarationDescriptor instanceof ClassDescriptor ? (ClassDescriptor) classifierDescriptorMo3831getDeclarationDescriptor : null;
                if (classDescriptor == null) {
                    z = false;
                } else {
                    z = (classDescriptor.getKind() == ClassKind.INTERFACE || classDescriptor.getKind() == ClassKind.ANNOTATION_CLASS) ? false : true;
                }
                if (z) {
                    obj = element$iv;
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        KotlinType kotlinType = (KotlinType) obj;
        if (kotlinType != null) {
            return kotlinType;
        }
        List<KotlinType> upperBounds3 = $this$representativeUpperBound.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(upperBounds3, "upperBounds");
        Object objFirst = CollectionsKt.first((List<? extends Object>) upperBounds3);
        Intrinsics.checkNotNullExpressionValue(objFirst, "upperBounds.first()");
        return (KotlinType) objFirst;
    }

    public static final boolean shouldBeUpdated(@NotNull KotlinType $this$shouldBeUpdated) {
        Intrinsics.checkNotNullParameter($this$shouldBeUpdated, "<this>");
        return contains($this$shouldBeUpdated, new Function1<UnwrappedType, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.shouldBeUpdated.1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(@NotNull UnwrappedType it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return (it instanceof StubType) || (it.getConstructor() instanceof TypeVariableTypeConstructorMarker) || KotlinTypeKt.isError(it);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(UnwrappedType unwrappedType) {
                return Boolean.valueOf(invoke2(unwrappedType));
            }
        });
    }
}
