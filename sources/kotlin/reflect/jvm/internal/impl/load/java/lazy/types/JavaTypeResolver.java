package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMapper;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotations;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.TypeParameterResolver;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifier;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPrimitiveType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaWildcardType;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JavaTypeResolver.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/lazy/types/JavaTypeResolver.class */
public final class JavaTypeResolver {

    @NotNull
    private final LazyJavaResolverContext c;

    @NotNull
    private final TypeParameterResolver typeParameterResolver;

    public JavaTypeResolver(@NotNull LazyJavaResolverContext c, @NotNull TypeParameterResolver typeParameterResolver) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(typeParameterResolver, "typeParameterResolver");
        this.c = c;
        this.typeParameterResolver = typeParameterResolver;
    }

    @NotNull
    public final KotlinType transformJavaType(@Nullable JavaType javaType, @NotNull JavaTypeAttributes attr) {
        Intrinsics.checkNotNullParameter(attr, "attr");
        if (javaType instanceof JavaPrimitiveType) {
            PrimitiveType primitiveType = ((JavaPrimitiveType) javaType).getType();
            SimpleType primitiveKotlinType = primitiveType != null ? this.c.getModule().getBuiltIns().getPrimitiveKotlinType(primitiveType) : this.c.getModule().getBuiltIns().getUnitType();
            Intrinsics.checkNotNullExpressionValue(primitiveKotlinType, "{\n                val primitiveType = javaType.type\n                if (primitiveType != null) c.module.builtIns.getPrimitiveKotlinType(primitiveType)\n                else c.module.builtIns.unitType\n            }");
            return primitiveKotlinType;
        }
        if (javaType instanceof JavaClassifierType) {
            return transformJavaClassifierType((JavaClassifierType) javaType, attr);
        }
        if (javaType instanceof JavaArrayType) {
            return transformArrayType$default(this, (JavaArrayType) javaType, attr, false, 4, null);
        }
        if (javaType instanceof JavaWildcardType) {
            JavaType it = ((JavaWildcardType) javaType).getBound();
            KotlinType kotlinTypeTransformJavaType = it == null ? null : transformJavaType(it, attr);
            if (kotlinTypeTransformJavaType != null) {
                return kotlinTypeTransformJavaType;
            }
            SimpleType defaultBound = this.c.getModule().getBuiltIns().getDefaultBound();
            Intrinsics.checkNotNullExpressionValue(defaultBound, "c.module.builtIns.defaultBound");
            return defaultBound;
        }
        if (javaType != null) {
            throw new UnsupportedOperationException(Intrinsics.stringPlus("Unsupported type: ", javaType));
        }
        SimpleType defaultBound2 = this.c.getModule().getBuiltIns().getDefaultBound();
        Intrinsics.checkNotNullExpressionValue(defaultBound2, "c.module.builtIns.defaultBound");
        return defaultBound2;
    }

    public static /* synthetic */ KotlinType transformArrayType$default(JavaTypeResolver javaTypeResolver, JavaArrayType javaArrayType, JavaTypeAttributes javaTypeAttributes, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return javaTypeResolver.transformArrayType(javaArrayType, javaTypeAttributes, z);
    }

    @NotNull
    public final KotlinType transformArrayType(@NotNull JavaArrayType arrayType, @NotNull JavaTypeAttributes attr, boolean isVararg) {
        Intrinsics.checkNotNullParameter(arrayType, "arrayType");
        Intrinsics.checkNotNullParameter(attr, "attr");
        JavaType javaComponentType = arrayType.getComponentType();
        JavaPrimitiveType javaPrimitiveType = javaComponentType instanceof JavaPrimitiveType ? (JavaPrimitiveType) javaComponentType : null;
        PrimitiveType primitiveType = javaPrimitiveType == null ? null : javaPrimitiveType.getType();
        LazyJavaAnnotations annotations = new LazyJavaAnnotations(this.c, arrayType, true);
        if (primitiveType != null) {
            SimpleType jetType = this.c.getModule().getBuiltIns().getPrimitiveArrayKotlinType(primitiveType);
            Intrinsics.checkNotNullExpressionValue(jetType, "c.module.builtIns.getPrimitiveArrayKotlinType(primitiveType)");
            jetType.replaceAnnotations(Annotations.Companion.create(CollectionsKt.plus((Iterable) annotations, (Iterable) jetType.getAnnotations())));
            if (attr.isForAnnotationParameter()) {
                return jetType;
            }
            KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
            return KotlinTypeFactory.flexibleType(jetType, jetType.makeNullableAsSpecified(true));
        }
        KotlinType componentType = transformJavaType(javaComponentType, JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, attr.isForAnnotationParameter(), null, 2, null));
        if (attr.isForAnnotationParameter()) {
            Variance projectionKind = isVararg ? Variance.OUT_VARIANCE : Variance.INVARIANT;
            SimpleType arrayType2 = this.c.getModule().getBuiltIns().getArrayType(projectionKind, componentType, annotations);
            Intrinsics.checkNotNullExpressionValue(arrayType2, "c.module.builtIns.getArrayType(projectionKind, componentType, annotations)");
            return arrayType2;
        }
        KotlinTypeFactory kotlinTypeFactory2 = KotlinTypeFactory.INSTANCE;
        SimpleType arrayType3 = this.c.getModule().getBuiltIns().getArrayType(Variance.INVARIANT, componentType, annotations);
        Intrinsics.checkNotNullExpressionValue(arrayType3, "c.module.builtIns.getArrayType(INVARIANT, componentType, annotations)");
        return KotlinTypeFactory.flexibleType(arrayType3, this.c.getModule().getBuiltIns().getArrayType(Variance.OUT_VARIANCE, componentType, annotations).makeNullableAsSpecified(true));
    }

    private static final SimpleType transformJavaClassifierType$errorType(JavaClassifierType $javaType) {
        SimpleType simpleTypeCreateErrorType = ErrorUtils.createErrorType(Intrinsics.stringPlus("Unresolved java class ", $javaType.getPresentableText()));
        Intrinsics.checkNotNullExpressionValue(simpleTypeCreateErrorType, "createErrorType(\"Unresolved java class ${javaType.presentableText}\")");
        return simpleTypeCreateErrorType;
    }

    private final KotlinType transformJavaClassifierType(JavaClassifierType javaType, JavaTypeAttributes attr) {
        boolean useFlexible = (attr.isForAnnotationParameter() || attr.getHowThisTypeIsUsed() == TypeUsage.SUPERTYPE) ? false : true;
        boolean isRaw = javaType.isRaw();
        if (!isRaw && !useFlexible) {
            SimpleType simpleTypeComputeSimpleJavaClassifierType = computeSimpleJavaClassifierType(javaType, attr, null);
            return simpleTypeComputeSimpleJavaClassifierType == null ? transformJavaClassifierType$errorType(javaType) : simpleTypeComputeSimpleJavaClassifierType;
        }
        SimpleType lower = computeSimpleJavaClassifierType(javaType, attr.withFlexibility(JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND), null);
        if (lower == null) {
            return transformJavaClassifierType$errorType(javaType);
        }
        SimpleType upper = computeSimpleJavaClassifierType(javaType, attr.withFlexibility(JavaTypeFlexibility.FLEXIBLE_UPPER_BOUND), lower);
        if (upper == null) {
            return transformJavaClassifierType$errorType(javaType);
        }
        if (isRaw) {
            return new RawTypeImpl(lower, upper);
        }
        KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
        return KotlinTypeFactory.flexibleType(lower, upper);
    }

    private final SimpleType computeSimpleJavaClassifierType(JavaClassifierType javaType, JavaTypeAttributes attr, SimpleType lowerResult) {
        Annotations annotations = lowerResult == null ? null : lowerResult.getAnnotations();
        Annotations annotations2 = annotations == null ? new LazyJavaAnnotations(this.c, javaType, false, 4, null) : annotations;
        TypeConstructor constructor = computeTypeConstructor(javaType, attr);
        if (constructor == null) {
            return null;
        }
        boolean isNullable = isNullable(attr);
        if (Intrinsics.areEqual(lowerResult == null ? null : lowerResult.getConstructor(), constructor) && !javaType.isRaw() && isNullable) {
            return lowerResult.makeNullableAsSpecified(true);
        }
        List arguments = computeArguments(javaType, attr, constructor);
        KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
        return KotlinTypeFactory.simpleType$default(annotations2, constructor, arguments, isNullable, null, 16, null);
    }

    private final TypeConstructor computeTypeConstructor(JavaClassifierType javaType, JavaTypeAttributes attr) {
        JavaClassifier classifier = javaType.getClassifier();
        if (classifier == null) {
            return createNotFoundClass(javaType);
        }
        if (classifier instanceof JavaClass) {
            FqName fqName = ((JavaClass) classifier).getFqName();
            if (fqName == null) {
                throw new AssertionError(Intrinsics.stringPlus("Class type should have a FQ name: ", classifier));
            }
            ClassDescriptor classDescriptorMapKotlinClass = mapKotlinClass(javaType, attr, fqName);
            ClassDescriptor classData = classDescriptorMapKotlinClass == null ? this.c.getComponents().getModuleClassResolver().resolveClass((JavaClass) classifier) : classDescriptorMapKotlinClass;
            TypeConstructor typeConstructor = classData == null ? null : classData.getTypeConstructor();
            return typeConstructor == null ? createNotFoundClass(javaType) : typeConstructor;
        }
        if (classifier instanceof JavaTypeParameter) {
            TypeParameterDescriptor typeParameterDescriptorResolveTypeParameter = this.typeParameterResolver.resolveTypeParameter((JavaTypeParameter) classifier);
            if (typeParameterDescriptorResolveTypeParameter == null) {
                return null;
            }
            return typeParameterDescriptorResolveTypeParameter.getTypeConstructor();
        }
        throw new IllegalStateException(Intrinsics.stringPlus("Unknown classifier kind: ", classifier));
    }

    private final TypeConstructor createNotFoundClass(JavaClassifierType javaType) {
        ClassId classId = ClassId.topLevel(new FqName(javaType.getClassifierQualifiedName()));
        Intrinsics.checkNotNullExpressionValue(classId, "topLevel(FqName(javaType.classifierQualifiedName))");
        TypeConstructor typeConstructor = this.c.getComponents().getDeserializedDescriptorResolver().getComponents().getNotFoundClasses().getClass(classId, CollectionsKt.listOf(0)).getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor, "c.components.deserializedDescriptorResolver.components.notFoundClasses.getClass(classId, listOf(0)).typeConstructor");
        return typeConstructor;
    }

    private final ClassDescriptor mapKotlinClass(JavaClassifierType javaType, JavaTypeAttributes attr, FqName fqName) {
        if (attr.isForAnnotationParameter() && Intrinsics.areEqual(fqName, JavaTypeResolverKt.JAVA_LANG_CLASS_FQ_NAME)) {
            return this.c.getComponents().getReflectionTypes().getKClass();
        }
        JavaToKotlinClassMapper javaToKotlin = JavaToKotlinClassMapper.INSTANCE;
        ClassDescriptor kotlinDescriptor = JavaToKotlinClassMapper.mapJavaToKotlin$default(javaToKotlin, fqName, this.c.getModule().getBuiltIns(), null, 4, null);
        if (kotlinDescriptor == null) {
            return null;
        }
        if (javaToKotlin.isReadOnly(kotlinDescriptor) && (attr.getFlexibility() == JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND || attr.getHowThisTypeIsUsed() == TypeUsage.SUPERTYPE || argumentsMakeSenseOnlyForMutableContainer(javaType, kotlinDescriptor))) {
            return javaToKotlin.convertReadOnlyToMutable(kotlinDescriptor);
        }
        return kotlinDescriptor;
    }

    private static final boolean argumentsMakeSenseOnlyForMutableContainer$isSuperWildcard(JavaType $this$argumentsMakeSenseOnlyForMutableContainer_u24isSuperWildcard) {
        JavaWildcardType it = $this$argumentsMakeSenseOnlyForMutableContainer_u24isSuperWildcard instanceof JavaWildcardType ? (JavaWildcardType) $this$argumentsMakeSenseOnlyForMutableContainer_u24isSuperWildcard : null;
        if (it == null) {
            return false;
        }
        return (it.getBound() == null || it.isExtends()) ? false : true;
    }

    private final boolean argumentsMakeSenseOnlyForMutableContainer(JavaClassifierType $this$argumentsMakeSenseOnlyForMutableContainer, ClassDescriptor readOnlyContainer) {
        if (!argumentsMakeSenseOnlyForMutableContainer$isSuperWildcard((JavaType) CollectionsKt.lastOrNull((List) $this$argumentsMakeSenseOnlyForMutableContainer.getTypeArguments()))) {
            return false;
        }
        List<TypeParameterDescriptor> parameters = JavaToKotlinClassMapper.INSTANCE.convertReadOnlyToMutable(readOnlyContainer).getTypeConstructor().getParameters();
        Intrinsics.checkNotNullExpressionValue(parameters, "JavaToKotlinClassMapper.convertReadOnlyToMutable(readOnlyContainer)\n            .typeConstructor.parameters");
        TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) CollectionsKt.lastOrNull((List) parameters);
        Variance mutableLastParameterVariance = typeParameterDescriptor == null ? null : typeParameterDescriptor.getVariance();
        return (mutableLastParameterVariance == null || mutableLastParameterVariance == Variance.OUT_VARIANCE) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.util.List<kotlin.reflect.jvm.internal.impl.types.TypeProjection> computeArguments(kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType r11, final kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeAttributes r12, final kotlin.reflect.jvm.internal.impl.types.TypeConstructor r13) {
        /*
            Method dump skipped, instructions count: 712
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolver.computeArguments(kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType, kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeAttributes, kotlin.reflect.jvm.internal.impl.types.TypeConstructor):java.util.List");
    }

    private final TypeProjection transformToTypeProjection(JavaType javaType, JavaTypeAttributes attr, TypeParameterDescriptor typeParameter) {
        if (javaType instanceof JavaWildcardType) {
            JavaType bound = ((JavaWildcardType) javaType).getBound();
            Variance projectionKind = ((JavaWildcardType) javaType).isExtends() ? Variance.OUT_VARIANCE : Variance.IN_VARIANCE;
            if (bound == null || isConflictingArgumentFor(projectionKind, typeParameter)) {
                return JavaTypeResolverKt.makeStarProjection(typeParameter, attr);
            }
            return TypeUtilsKt.createProjection(transformJavaType(bound, JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null)), projectionKind, typeParameter);
        }
        return new TypeProjectionImpl(Variance.INVARIANT, transformJavaType(javaType, attr));
    }

    private final boolean isConflictingArgumentFor(Variance $this$isConflictingArgumentFor, TypeParameterDescriptor typeParameter) {
        return (typeParameter.getVariance() == Variance.INVARIANT || $this$isConflictingArgumentFor == typeParameter.getVariance()) ? false : true;
    }

    private final boolean isNullable(JavaTypeAttributes $this$isNullable) {
        return ($this$isNullable.getFlexibility() == JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND || $this$isNullable.isForAnnotationParameter() || $this$isNullable.getHowThisTypeIsUsed() == TypeUsage.SUPERTYPE) ? false : true;
    }
}
