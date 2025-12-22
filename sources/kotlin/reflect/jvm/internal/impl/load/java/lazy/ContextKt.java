package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassOrPackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationQualifierApplicabilityType;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultQualifiers;
import kotlin.reflect.jvm.internal.impl.load.java.JavaTypeQualifiersByElementType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameterListOwner;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus;
import kotlin.reflect.jvm.internal.impl.utils.ReportLevel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: context.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/lazy/ContextKt.class */
public final class ContextKt {
    @NotNull
    public static final LazyJavaResolverContext child(@NotNull LazyJavaResolverContext $this$child, @NotNull TypeParameterResolver typeParameterResolver) {
        Intrinsics.checkNotNullParameter($this$child, "<this>");
        Intrinsics.checkNotNullParameter(typeParameterResolver, "typeParameterResolver");
        return new LazyJavaResolverContext($this$child.getComponents(), typeParameterResolver, $this$child.getDelegateForDefaultTypeQualifiers$descriptors_jvm());
    }

    @Nullable
    public static final JavaTypeQualifiersByElementType computeNewDefaultTypeQualifiers(@NotNull LazyJavaResolverContext $this$computeNewDefaultTypeQualifiers, @NotNull Annotations additionalAnnotations) {
        EnumMap p0;
        EnumMap enumMap;
        Intrinsics.checkNotNullParameter($this$computeNewDefaultTypeQualifiers, "<this>");
        Intrinsics.checkNotNullParameter(additionalAnnotations, "additionalAnnotations");
        if ($this$computeNewDefaultTypeQualifiers.getComponents().getJavaTypeEnhancementState().getDisabledDefaultAnnotations()) {
            return $this$computeNewDefaultTypeQualifiers.getDefaultTypeQualifiers();
        }
        Annotations $this$mapNotNull$iv = additionalAnnotations;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            JavaDefaultQualifiers javaDefaultQualifiersExtractDefaultNullabilityQualifier = extractDefaultNullabilityQualifier($this$computeNewDefaultTypeQualifiers, (AnnotationDescriptor) element$iv$iv$iv);
            if (javaDefaultQualifiersExtractDefaultNullabilityQualifier != null) {
                destination$iv$iv.add(javaDefaultQualifiersExtractDefaultNullabilityQualifier);
            }
        }
        List<JavaDefaultQualifiers> defaultQualifiers = (List) destination$iv$iv;
        if (defaultQualifiers.isEmpty()) {
            return $this$computeNewDefaultTypeQualifiers.getDefaultTypeQualifiers();
        }
        JavaTypeQualifiersByElementType defaultTypeQualifiers = $this$computeNewDefaultTypeQualifiers.getDefaultTypeQualifiers();
        EnumMap enumMap2 = (defaultTypeQualifiers == null || (p0 = defaultTypeQualifiers.getDefaultQualifiers()) == null) ? null : new EnumMap(p0);
        EnumMap enumMap3 = enumMap2;
        if (enumMap3 != null) {
            enumMap = enumMap3;
        } else {
            enumMap = new EnumMap(AnnotationQualifierApplicabilityType.class);
        }
        EnumMap defaultQualifiersByType = enumMap;
        boolean wasUpdate = false;
        for (JavaDefaultQualifiers qualifier : defaultQualifiers) {
            for (AnnotationQualifierApplicabilityType applicabilityType : qualifier.getQualifierApplicabilityTypes()) {
                defaultQualifiersByType.put((EnumMap) applicabilityType, (AnnotationQualifierApplicabilityType) qualifier);
                wasUpdate = true;
            }
        }
        return !wasUpdate ? $this$computeNewDefaultTypeQualifiers.getDefaultTypeQualifiers() : new JavaTypeQualifiersByElementType(defaultQualifiersByType);
    }

    private static final JavaDefaultQualifiers extractDefaultNullabilityQualifier(LazyJavaResolverContext $this$extractDefaultNullabilityQualifier, AnnotationDescriptor annotationDescriptor) {
        AnnotationTypeQualifierResolver typeQualifierResolver = $this$extractDefaultNullabilityQualifier.getComponents().getAnnotationTypeQualifierResolver();
        JavaDefaultQualifiers it = typeQualifierResolver.resolveQualifierBuiltInDefaultAnnotation(annotationDescriptor);
        if (it != null) {
            return it;
        }
        AnnotationTypeQualifierResolver.TypeQualifierWithApplicability typeQualifierWithApplicabilityResolveTypeQualifierDefaultAnnotation = typeQualifierResolver.resolveTypeQualifierDefaultAnnotation(annotationDescriptor);
        if (typeQualifierWithApplicabilityResolveTypeQualifierDefaultAnnotation == null) {
            return null;
        }
        AnnotationDescriptor typeQualifier = typeQualifierWithApplicabilityResolveTypeQualifierDefaultAnnotation.component1();
        List applicability = typeQualifierWithApplicabilityResolveTypeQualifierDefaultAnnotation.component2();
        ReportLevel reportLevelResolveJsr305CustomState = typeQualifierResolver.resolveJsr305CustomState(annotationDescriptor);
        ReportLevel jsr305State = reportLevelResolveJsr305CustomState == null ? typeQualifierResolver.resolveJsr305AnnotationState(typeQualifier) : reportLevelResolveJsr305CustomState;
        if (jsr305State.isIgnore()) {
            return null;
        }
        boolean areImprovementsInStrictMode = $this$extractDefaultNullabilityQualifier.getComponents().getSettings().getTypeEnhancementImprovementsInStrictMode();
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatusExtractNullability = $this$extractDefaultNullabilityQualifier.getComponents().getSignatureEnhancement().extractNullability(typeQualifier, areImprovementsInStrictMode, false);
        NullabilityQualifierWithMigrationStatus nullabilityQualifier = nullabilityQualifierWithMigrationStatusExtractNullability == null ? null : NullabilityQualifierWithMigrationStatus.copy$default(nullabilityQualifierWithMigrationStatusExtractNullability, null, jsr305State.isWarning(), 1, null);
        if (nullabilityQualifier == null) {
            return null;
        }
        return new JavaDefaultQualifiers(nullabilityQualifier, applicability, false, 4, null);
    }

    @NotNull
    public static final LazyJavaResolverContext replaceComponents(@NotNull LazyJavaResolverContext $this$replaceComponents, @NotNull JavaResolverComponents components) {
        Intrinsics.checkNotNullParameter($this$replaceComponents, "<this>");
        Intrinsics.checkNotNullParameter(components, "components");
        return new LazyJavaResolverContext(components, $this$replaceComponents.getTypeParameterResolver(), $this$replaceComponents.getDelegateForDefaultTypeQualifiers$descriptors_jvm());
    }

    private static final LazyJavaResolverContext child(LazyJavaResolverContext $this$child, DeclarationDescriptor containingDeclaration, JavaTypeParameterListOwner typeParameterOwner, int typeParametersIndexOffset, Lazy<JavaTypeQualifiersByElementType> lazy) {
        LazyJavaTypeParameterResolver lazyJavaTypeParameterResolver;
        LazyJavaTypeParameterResolver typeParameterResolver;
        JavaResolverComponents components = $this$child.getComponents();
        if (typeParameterOwner == null) {
            lazyJavaTypeParameterResolver = null;
        } else {
            components = components;
            lazyJavaTypeParameterResolver = new LazyJavaTypeParameterResolver($this$child, containingDeclaration, typeParameterOwner, typeParametersIndexOffset);
        }
        LazyJavaTypeParameterResolver lazyJavaTypeParameterResolver2 = lazyJavaTypeParameterResolver;
        if (lazyJavaTypeParameterResolver2 != null) {
            typeParameterResolver = lazyJavaTypeParameterResolver2;
        } else {
            typeParameterResolver = $this$child.getTypeParameterResolver();
        }
        return new LazyJavaResolverContext(components, typeParameterResolver, lazy);
    }

    public static /* synthetic */ LazyJavaResolverContext childForMethod$default(LazyJavaResolverContext lazyJavaResolverContext, DeclarationDescriptor declarationDescriptor, JavaTypeParameterListOwner javaTypeParameterListOwner, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return childForMethod(lazyJavaResolverContext, declarationDescriptor, javaTypeParameterListOwner, i);
    }

    @NotNull
    public static final LazyJavaResolverContext childForMethod(@NotNull LazyJavaResolverContext $this$childForMethod, @NotNull DeclarationDescriptor containingDeclaration, @NotNull JavaTypeParameterListOwner typeParameterOwner, int typeParametersIndexOffset) {
        Intrinsics.checkNotNullParameter($this$childForMethod, "<this>");
        Intrinsics.checkNotNullParameter(containingDeclaration, "containingDeclaration");
        Intrinsics.checkNotNullParameter(typeParameterOwner, "typeParameterOwner");
        return child($this$childForMethod, containingDeclaration, typeParameterOwner, typeParametersIndexOffset, $this$childForMethod.getDelegateForDefaultTypeQualifiers$descriptors_jvm());
    }

    public static /* synthetic */ LazyJavaResolverContext childForClassOrPackage$default(LazyJavaResolverContext lazyJavaResolverContext, ClassOrPackageFragmentDescriptor classOrPackageFragmentDescriptor, JavaTypeParameterListOwner javaTypeParameterListOwner, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            javaTypeParameterListOwner = null;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return childForClassOrPackage(lazyJavaResolverContext, classOrPackageFragmentDescriptor, javaTypeParameterListOwner, i);
    }

    @NotNull
    public static final LazyJavaResolverContext childForClassOrPackage(@NotNull final LazyJavaResolverContext $this$childForClassOrPackage, @NotNull final ClassOrPackageFragmentDescriptor containingDeclaration, @Nullable JavaTypeParameterListOwner typeParameterOwner, int typeParametersIndexOffset) {
        Intrinsics.checkNotNullParameter($this$childForClassOrPackage, "<this>");
        Intrinsics.checkNotNullParameter(containingDeclaration, "containingDeclaration");
        return child($this$childForClassOrPackage, containingDeclaration, typeParameterOwner, typeParametersIndexOffset, LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<JavaTypeQualifiersByElementType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt.childForClassOrPackage.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @Nullable
            public final JavaTypeQualifiersByElementType invoke() {
                return ContextKt.computeNewDefaultTypeQualifiers($this$childForClassOrPackage, containingDeclaration.getAnnotations());
            }
        }));
    }

    @NotNull
    public static final LazyJavaResolverContext copyWithNewDefaultTypeQualifiers(@NotNull final LazyJavaResolverContext $this$copyWithNewDefaultTypeQualifiers, @NotNull final Annotations additionalAnnotations) {
        Intrinsics.checkNotNullParameter($this$copyWithNewDefaultTypeQualifiers, "<this>");
        Intrinsics.checkNotNullParameter(additionalAnnotations, "additionalAnnotations");
        return additionalAnnotations.isEmpty() ? $this$copyWithNewDefaultTypeQualifiers : new LazyJavaResolverContext($this$copyWithNewDefaultTypeQualifiers.getComponents(), $this$copyWithNewDefaultTypeQualifiers.getTypeParameterResolver(), LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<JavaTypeQualifiersByElementType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt.copyWithNewDefaultTypeQualifiers.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @Nullable
            public final JavaTypeQualifiersByElementType invoke() {
                return ContextKt.computeNewDefaultTypeQualifiers($this$copyWithNewDefaultTypeQualifiers, additionalAnnotations);
            }
        }));
    }
}
