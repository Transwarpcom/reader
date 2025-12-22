package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMapper;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.PossiblyExternalAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolverKt;
import kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElement;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationAsAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassObjectAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaEnumValueAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaLiteralAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValueFactory;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.NullValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.NullableLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: LazyJavaAnnotationDescriptor.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/lazy/descriptors/LazyJavaAnnotationDescriptor.class */
public final class LazyJavaAnnotationDescriptor implements AnnotationDescriptor, PossiblyExternalAnnotationDescriptor {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaAnnotationDescriptor.class), "fqName", "getFqName()Lorg/jetbrains/kotlin/name/FqName;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaAnnotationDescriptor.class), "type", "getType()Lorg/jetbrains/kotlin/types/SimpleType;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaAnnotationDescriptor.class), "allValueArguments", "getAllValueArguments()Ljava/util/Map;"))};

    @NotNull
    private final LazyJavaResolverContext c;

    @NotNull
    private final JavaAnnotation javaAnnotation;

    @NotNull
    private final NullableLazyValue fqName$delegate;

    @NotNull
    private final NotNullLazyValue type$delegate;

    @NotNull
    private final JavaSourceElement source;

    @NotNull
    private final NotNullLazyValue allValueArguments$delegate;
    private final boolean isIdeExternalAnnotation;
    private final boolean isFreshlySupportedTypeUseAnnotation;

    public LazyJavaAnnotationDescriptor(@NotNull LazyJavaResolverContext c, @NotNull JavaAnnotation javaAnnotation, boolean isFreshlySupportedAnnotation) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(javaAnnotation, "javaAnnotation");
        this.c = c;
        this.javaAnnotation = javaAnnotation;
        this.fqName$delegate = this.c.getStorageManager().createNullableLazyValue(new Function0<FqName>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaAnnotationDescriptor$fqName$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @Nullable
            public final FqName invoke() {
                ClassId classId = this.this$0.javaAnnotation.getClassId();
                if (classId == null) {
                    return null;
                }
                return classId.asSingleFqName();
            }
        });
        this.type$delegate = this.c.getStorageManager().createLazyValue(new Function0<SimpleType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaAnnotationDescriptor$type$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final SimpleType invoke() {
                ClassDescriptor classDescriptorCreateTypeForMissingDependencies;
                FqName fqName = this.this$0.getFqName();
                if (fqName == null) {
                    return ErrorUtils.createErrorType(Intrinsics.stringPlus("No fqName: ", this.this$0.javaAnnotation));
                }
                ClassDescriptor classDescriptorMapJavaToKotlin$default = JavaToKotlinClassMapper.mapJavaToKotlin$default(JavaToKotlinClassMapper.INSTANCE, fqName, this.this$0.c.getModule().getBuiltIns(), null, 4, null);
                if (classDescriptorMapJavaToKotlin$default == null) {
                    JavaClass javaClass = this.this$0.javaAnnotation.resolve();
                    ClassDescriptor classDescriptorResolveClass = javaClass == null ? null : this.this$0.c.getComponents().getModuleClassResolver().resolveClass(javaClass);
                    if (classDescriptorResolveClass == null) {
                        classDescriptorCreateTypeForMissingDependencies = this.this$0.createTypeForMissingDependencies(fqName);
                    } else {
                        classDescriptorCreateTypeForMissingDependencies = classDescriptorResolveClass;
                    }
                } else {
                    classDescriptorCreateTypeForMissingDependencies = classDescriptorMapJavaToKotlin$default;
                }
                ClassDescriptor annotationClass = classDescriptorCreateTypeForMissingDependencies;
                return annotationClass.getDefaultType();
            }
        });
        this.source = this.c.getComponents().getSourceElementFactory().source(this.javaAnnotation);
        this.allValueArguments$delegate = this.c.getStorageManager().createLazyValue(new Function0<Map<Name, ? extends ConstantValue<?>>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaAnnotationDescriptor$allValueArguments$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final Map<Name, ? extends ConstantValue<?>> invoke() {
                Iterable $this$mapNotNull$iv = this.this$0.javaAnnotation.getArguments();
                LazyJavaAnnotationDescriptor lazyJavaAnnotationDescriptor = this.this$0;
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                    JavaAnnotationArgument arg = (JavaAnnotationArgument) element$iv$iv$iv;
                    Name name = arg.getName();
                    Name name2 = name == null ? JvmAnnotationNames.DEFAULT_ANNOTATION_MEMBER_NAME : name;
                    ConstantValue value = lazyJavaAnnotationDescriptor.resolveAnnotationArgument(arg);
                    Pair pair = value == null ? null : TuplesKt.to(name2, value);
                    if (pair != null) {
                        destination$iv$iv.add(pair);
                    }
                }
                return MapsKt.toMap((List) destination$iv$iv);
            }
        });
        this.isIdeExternalAnnotation = this.javaAnnotation.isIdeExternalAnnotation();
        this.isFreshlySupportedTypeUseAnnotation = this.javaAnnotation.isFreshlySupportedTypeUseAnnotation() || isFreshlySupportedAnnotation;
    }

    public /* synthetic */ LazyJavaAnnotationDescriptor(LazyJavaResolverContext lazyJavaResolverContext, JavaAnnotation javaAnnotation, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(lazyJavaResolverContext, javaAnnotation, (i & 4) != 0 ? false : z);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    @Nullable
    public FqName getFqName() {
        return (FqName) StorageKt.getValue(this.fqName$delegate, this, (KProperty<?>) $$delegatedProperties[0]);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    @NotNull
    public SimpleType getType() {
        return (SimpleType) StorageKt.getValue(this.type$delegate, this, (KProperty<?>) $$delegatedProperties[1]);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    @NotNull
    public JavaSourceElement getSource() {
        return this.source;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    @NotNull
    public Map<Name, ConstantValue<?>> getAllValueArguments() {
        return (Map) StorageKt.getValue(this.allValueArguments$delegate, this, (KProperty<?>) $$delegatedProperties[2]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ConstantValue<?> resolveAnnotationArgument(JavaAnnotationArgument argument) {
        if (argument instanceof JavaLiteralAnnotationArgument) {
            return ConstantValueFactory.INSTANCE.createConstantValue(((JavaLiteralAnnotationArgument) argument).getValue());
        }
        if (argument instanceof JavaEnumValueAnnotationArgument) {
            return resolveFromEnumValue(((JavaEnumValueAnnotationArgument) argument).getEnumClassId(), ((JavaEnumValueAnnotationArgument) argument).getEntryName());
        }
        if (argument instanceof JavaArrayAnnotationArgument) {
            Name name = ((JavaArrayAnnotationArgument) argument).getName();
            Name name2 = name == null ? JvmAnnotationNames.DEFAULT_ANNOTATION_MEMBER_NAME : name;
            Intrinsics.checkNotNullExpressionValue(name2, "argument.name ?: DEFAULT_ANNOTATION_MEMBER_NAME");
            return resolveFromArray(name2, ((JavaArrayAnnotationArgument) argument).getElements());
        }
        if (argument instanceof JavaAnnotationAsAnnotationArgument) {
            return resolveFromAnnotation(((JavaAnnotationAsAnnotationArgument) argument).getAnnotation());
        }
        if (argument instanceof JavaClassObjectAnnotationArgument) {
            return resolveFromJavaClassObjectType(((JavaClassObjectAnnotationArgument) argument).getReferencedType());
        }
        return null;
    }

    private final ConstantValue<?> resolveFromAnnotation(JavaAnnotation javaAnnotation) {
        return new AnnotationValue(new LazyJavaAnnotationDescriptor(this.c, javaAnnotation, false, 4, null));
    }

    private final ConstantValue<?> resolveFromArray(Name argumentName, List<? extends JavaAnnotationArgument> list) {
        SimpleType arrayType;
        SimpleType type = getType();
        Intrinsics.checkNotNullExpressionValue(type, "type");
        if (KotlinTypeKt.isError(type)) {
            return null;
        }
        ClassDescriptor annotationClass = DescriptorUtilsKt.getAnnotationClass(this);
        Intrinsics.checkNotNull(annotationClass);
        ValueParameterDescriptor annotationParameterByName = DescriptorResolverUtils.getAnnotationParameterByName(argumentName, annotationClass);
        KotlinType type2 = annotationParameterByName == null ? null : annotationParameterByName.getType();
        if (type2 != null) {
            arrayType = type2;
        } else {
            arrayType = this.c.getComponents().getModule().getBuiltIns().getArrayType(Variance.INVARIANT, ErrorUtils.createErrorType("Unknown array element type"));
        }
        KotlinType arrayType2 = arrayType;
        Intrinsics.checkNotNullExpressionValue(arrayType2, "DescriptorResolverUtils.getAnnotationParameterByName(argumentName, annotationClass!!)?.type\n            // Try to load annotation arguments even if the annotation class is not found\n                ?: c.components.module.builtIns.getArrayType(\n                    Variance.INVARIANT,\n                    ErrorUtils.createErrorType(\"Unknown array element type\")\n                )");
        List<? extends JavaAnnotationArgument> $this$map$iv = list;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            JavaAnnotationArgument argument = (JavaAnnotationArgument) item$iv$iv;
            ConstantValue<?> constantValueResolveAnnotationArgument = resolveAnnotationArgument(argument);
            destination$iv$iv.add(constantValueResolveAnnotationArgument == null ? new NullValue() : constantValueResolveAnnotationArgument);
        }
        List values = (List) destination$iv$iv;
        return ConstantValueFactory.INSTANCE.createArrayValue((List<? extends ConstantValue<?>>) values, arrayType2);
    }

    private final ConstantValue<?> resolveFromEnumValue(ClassId enumClassId, Name entryName) {
        if (enumClassId == null || entryName == null) {
            return null;
        }
        return new EnumValue(enumClassId, entryName);
    }

    private final ConstantValue<?> resolveFromJavaClassObjectType(JavaType javaType) {
        return KClassValue.Companion.create(this.c.getTypeResolver().transformJavaType(javaType, JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null)));
    }

    @NotNull
    public String toString() {
        return DescriptorRenderer.renderAnnotation$default(DescriptorRenderer.FQ_NAMES_IN_TYPES, this, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassDescriptor createTypeForMissingDependencies(FqName fqName) {
        ModuleDescriptor module = this.c.getModule();
        ClassId classId = ClassId.topLevel(fqName);
        Intrinsics.checkNotNullExpressionValue(classId, "topLevel(fqName)");
        return FindClassInModuleKt.findNonGenericClassAcrossDependencies(module, classId, this.c.getComponents().getDeserializedDescriptorResolver().getComponents().getNotFoundClasses());
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.descriptors.PossiblyExternalAnnotationDescriptor
    public boolean isIdeExternalAnnotation() {
        return this.isIdeExternalAnnotation;
    }

    public final boolean isFreshlySupportedTypeUseAnnotation() {
        return this.isFreshlySupportedTypeUseAnnotation;
    }
}
