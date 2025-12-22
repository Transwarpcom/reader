package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: typeParameterUtils.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/TypeParameterUtilsKt.class */
public final class TypeParameterUtilsKt {
    @NotNull
    public static final List<TypeParameterDescriptor> computeConstructorTypeParameters(@NotNull ClassifierDescriptorWithTypeParameters $this$computeConstructorTypeParameters) {
        Object obj;
        TypeConstructor typeConstructor;
        Intrinsics.checkNotNullParameter($this$computeConstructorTypeParameters, "<this>");
        List declaredTypeParameters = $this$computeConstructorTypeParameters.getDeclaredTypeParameters();
        Intrinsics.checkNotNullExpressionValue(declaredTypeParameters, "declaredTypeParameters");
        if (!$this$computeConstructorTypeParameters.isInner() && !($this$computeConstructorTypeParameters.getContainingDeclaration() instanceof CallableDescriptor)) {
            return declaredTypeParameters;
        }
        List parametersFromContainingFunctions = SequencesKt.toList(SequencesKt.flatMap(SequencesKt.filter(SequencesKt.takeWhile(DescriptorUtilsKt.getParents($this$computeConstructorTypeParameters), new Function1<DeclarationDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(@NotNull DeclarationDescriptor it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof CallableDescriptor;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(DeclarationDescriptor declarationDescriptor) {
                return Boolean.valueOf(invoke2(declarationDescriptor));
            }
        }), new Function1<DeclarationDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$2
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(@NotNull DeclarationDescriptor it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return !(it instanceof ConstructorDescriptor);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(DeclarationDescriptor declarationDescriptor) {
                return Boolean.valueOf(invoke2(declarationDescriptor));
            }
        }), new Function1<DeclarationDescriptor, Sequence<? extends TypeParameterDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$3
            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final Sequence<TypeParameterDescriptor> invoke(@NotNull DeclarationDescriptor it) {
                Intrinsics.checkNotNullParameter(it, "it");
                List<TypeParameterDescriptor> typeParameters = ((CallableDescriptor) it).getTypeParameters();
                Intrinsics.checkNotNullExpressionValue(typeParameters, "it as CallableDescriptor).typeParameters");
                return CollectionsKt.asSequence(typeParameters);
            }
        }));
        Sequence $this$firstIsInstanceOrNull$iv = DescriptorUtilsKt.getParents($this$computeConstructorTypeParameters);
        Iterator<DeclarationDescriptor> it = $this$firstIsInstanceOrNull$iv.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            Object element$iv = it.next();
            if (element$iv instanceof ClassDescriptor) {
                obj = element$iv;
                break;
            }
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) obj;
        List parameters = (classDescriptor == null || (typeConstructor = classDescriptor.getTypeConstructor()) == null) ? null : typeConstructor.getParameters();
        List listEmptyList = parameters;
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        List containingClassTypeConstructorParameters = listEmptyList;
        if (parametersFromContainingFunctions.isEmpty() && containingClassTypeConstructorParameters.isEmpty()) {
            List<TypeParameterDescriptor> declaredTypeParameters2 = $this$computeConstructorTypeParameters.getDeclaredTypeParameters();
            Intrinsics.checkNotNullExpressionValue(declaredTypeParameters2, "declaredTypeParameters");
            return declaredTypeParameters2;
        }
        Iterable $this$map$iv = CollectionsKt.plus((Collection) parametersFromContainingFunctions, (Iterable) containingClassTypeConstructorParameters);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            TypeParameterDescriptor it2 = (TypeParameterDescriptor) item$iv$iv;
            Intrinsics.checkNotNullExpressionValue(it2, "it");
            destination$iv$iv.add(capturedCopyForInnerDeclaration(it2, $this$computeConstructorTypeParameters, declaredTypeParameters.size()));
        }
        List additional = (List) destination$iv$iv;
        return CollectionsKt.plus((Collection) declaredTypeParameters, (Iterable) additional);
    }

    private static final CapturedTypeParameterDescriptor capturedCopyForInnerDeclaration(TypeParameterDescriptor $this$capturedCopyForInnerDeclaration, DeclarationDescriptor declarationDescriptor, int declaredTypeParametersCount) {
        return new CapturedTypeParameterDescriptor($this$capturedCopyForInnerDeclaration, declarationDescriptor, declaredTypeParametersCount);
    }

    @Nullable
    public static final PossiblyInnerType buildPossiblyInnerType(@NotNull KotlinType $this$buildPossiblyInnerType) {
        Intrinsics.checkNotNullParameter($this$buildPossiblyInnerType, "<this>");
        ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = $this$buildPossiblyInnerType.getConstructor().mo3831getDeclarationDescriptor();
        return buildPossiblyInnerType($this$buildPossiblyInnerType, classifierDescriptorMo3831getDeclarationDescriptor instanceof ClassifierDescriptorWithTypeParameters ? (ClassifierDescriptorWithTypeParameters) classifierDescriptorMo3831getDeclarationDescriptor : null, 0);
    }

    private static final PossiblyInnerType buildPossiblyInnerType(KotlinType $this$buildPossiblyInnerType, ClassifierDescriptorWithTypeParameters classifierDescriptor, int index) {
        if (classifierDescriptor == null || ErrorUtils.isError(classifierDescriptor)) {
            return null;
        }
        int toIndex = classifierDescriptor.getDeclaredTypeParameters().size() + index;
        if (!classifierDescriptor.isInner()) {
            boolean z = toIndex == $this$buildPossiblyInnerType.getArguments().size() || DescriptorUtils.isLocal(classifierDescriptor);
            if (_Assertions.ENABLED && !z) {
                throw new AssertionError(($this$buildPossiblyInnerType.getArguments().size() - toIndex) + " trailing arguments were found in " + $this$buildPossiblyInnerType + " type");
            }
            return new PossiblyInnerType(classifierDescriptor, $this$buildPossiblyInnerType.getArguments().subList(index, $this$buildPossiblyInnerType.getArguments().size()), null);
        }
        List argumentsSubList = $this$buildPossiblyInnerType.getArguments().subList(index, toIndex);
        DeclarationDescriptor containingDeclaration = classifierDescriptor.getContainingDeclaration();
        return new PossiblyInnerType(classifierDescriptor, argumentsSubList, buildPossiblyInnerType($this$buildPossiblyInnerType, containingDeclaration instanceof ClassifierDescriptorWithTypeParameters ? (ClassifierDescriptorWithTypeParameters) containingDeclaration : null, toIndex));
    }
}
