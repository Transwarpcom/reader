package kotlin.reflect.jvm.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference;
import kotlin.reflect.KCallable;
import kotlin.reflect.KType;
import kotlin.reflect.KVisibility;
import kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectAnnotationSource;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectJavaClassFinderKt;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectKotlinClass;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.RuntimeModuleData;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.RuntimeSourceElementFactory;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaAnnotation;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaElement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinarySourceElement;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ErrorValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.NullValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.text.StringsKt;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: util.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��Â\u0001\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u001a\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H��\u001an\u0010\u0011\u001a\u0004\u0018\u0001H\u0012\"\b\b��\u0010\u0013*\u00020\u0014\"\b\b\u0001\u0010\u0012*\u00020\u00062\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u00162\u0006\u0010\u0017\u001a\u0002H\u00132\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u001d\u0010\u001e\u001a\u0019\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u00120\u001f¢\u0006\u0002\b!H��¢\u0006\u0002\u0010\"\u001a.\u0010#\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00162\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020*H\u0002\u001a(\u0010#\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00162\u0006\u0010$\u001a\u00020%2\u0006\u0010+\u001a\u00020,2\b\b\u0002\u0010)\u001a\u00020*H\u0002\u001a%\u0010-\u001a\u0002H.\"\u0004\b��\u0010.2\f\u0010/\u001a\b\u0012\u0004\u0012\u0002H.00H\u0080\bø\u0001��¢\u0006\u0002\u00101\u001a\u0014\u00102\u001a\b\u0012\u0002\b\u0003\u0018\u000103*\u0004\u0018\u00010\u000eH��\u001a\u0010\u00104\u001a\u0004\u0018\u000105*\u0004\u0018\u00010\u000eH��\u001a\u0014\u00106\u001a\b\u0012\u0002\b\u0003\u0018\u000107*\u0004\u0018\u00010\u000eH��\u001a\u0012\u00108\u001a\b\u0012\u0004\u0012\u00020:09*\u00020;H��\u001a\u0014\u0010<\u001a\u0006\u0012\u0002\b\u00030\u0016*\u0006\u0012\u0002\b\u00030\u0016H��\u001a\u000e\u0010=\u001a\u0004\u0018\u00010:*\u00020>H\u0002\u001a\u0012\u0010?\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0016*\u00020@H��\u001a\u000e\u0010A\u001a\u0004\u0018\u00010B*\u00020CH��\u001a\u001a\u0010D\u001a\u0004\u0018\u00010\u000e*\u0006\u0012\u0002\b\u00030E2\u0006\u0010$\u001a\u00020%H\u0002\"\u0014\u0010��\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u0002\u0010\u0003\"\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u00068@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u0018\u0010\t\u001a\u00020\n*\u00020\u000b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006F"}, d2 = {"JVM_STATIC", "Lkotlin/reflect/jvm/internal/impl/name/FqName;", "getJVM_STATIC", "()Lorg/jetbrains/kotlin/name/FqName;", "instanceReceiverParameter", "Lkotlin/reflect/jvm/internal/impl/descriptors/ReceiverParameterDescriptor;", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableDescriptor;", "getInstanceReceiverParameter", "(Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;)Lorg/jetbrains/kotlin/descriptors/ReceiverParameterDescriptor;", "isInlineClassType", "", "Lkotlin/reflect/KType;", "(Lkotlin/reflect/KType;)Z", "defaultPrimitiveValue", "", "type", "Ljava/lang/reflect/Type;", "deserializeToDescriptor", "D", OperatorName.SET_LINE_MITERLIMIT, "Lkotlin/reflect/jvm/internal/impl/protobuf/MessageLite;", "moduleAnchor", "Ljava/lang/Class;", "proto", "nameResolver", "Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/NameResolver;", "typeTable", "Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/TypeTable;", "metadataVersion", "Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/BinaryVersion;", "createDescriptor", "Lkotlin/Function2;", "Lkotlin/reflect/jvm/internal/impl/serialization/deserialization/MemberDeserializer;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Class;Lorg/jetbrains/kotlin/protobuf/MessageLite;Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;Lorg/jetbrains/kotlin/metadata/deserialization/TypeTable;Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;", "loadClass", "classLoader", "Ljava/lang/ClassLoader;", "packageName", "", "className", "arrayDimensions", "", "kotlinClassId", "Lkotlin/reflect/jvm/internal/impl/name/ClassId;", "reflectionCall", "R", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "asKCallableImpl", "Lkotlin/reflect/jvm/internal/KCallableImpl;", "asKFunctionImpl", "Lkotlin/reflect/jvm/internal/KFunctionImpl;", "asKPropertyImpl", "Lkotlin/reflect/jvm/internal/KPropertyImpl;", "computeAnnotations", "", "", "Lkotlin/reflect/jvm/internal/impl/descriptors/annotations/Annotated;", "createArrayType", "toAnnotationInstance", "Lkotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotationDescriptor;", "toJavaClass", "Lkotlin/reflect/jvm/internal/impl/descriptors/ClassDescriptor;", "toKVisibility", "Lkotlin/reflect/KVisibility;", "Lkotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibility;", "toRuntimeValue", "Lkotlin/reflect/jvm/internal/impl/resolve/constants/ConstantValue;", "kotlin-reflection"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/UtilKt.class */
public final class UtilKt {

    @NotNull
    private static final FqName JVM_STATIC = new FqName("kotlin.jvm.JvmStatic");

    @NotNull
    public static final FqName getJVM_STATIC() {
        return JVM_STATIC;
    }

    @Nullable
    public static final Class<?> toJavaClass(@NotNull ClassDescriptor toJavaClass) {
        Intrinsics.checkNotNullParameter(toJavaClass, "$this$toJavaClass");
        SourceElement source = toJavaClass.getSource();
        Intrinsics.checkNotNullExpressionValue(source, "source");
        if (source instanceof KotlinJvmBinarySourceElement) {
            KotlinJvmBinaryClass binaryClass = ((KotlinJvmBinarySourceElement) source).getBinaryClass();
            if (binaryClass == null) {
                throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.runtime.components.ReflectKotlinClass");
            }
            return ((ReflectKotlinClass) binaryClass).getKlass();
        }
        if (source instanceof RuntimeSourceElementFactory.RuntimeSourceElement) {
            ReflectJavaElement javaElement = ((RuntimeSourceElementFactory.RuntimeSourceElement) source).getJavaElement();
            if (javaElement == null) {
                throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.runtime.structure.ReflectJavaClass");
            }
            return ((ReflectJavaClass) javaElement).getElement();
        }
        ClassId classId = DescriptorUtilsKt.getClassId(toJavaClass);
        if (classId != null) {
            return loadClass(ReflectClassUtilKt.getSafeClassLoader(toJavaClass.getClass()), classId, 0);
        }
        return null;
    }

    static /* synthetic */ Class loadClass$default(ClassLoader classLoader, ClassId classId, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return loadClass(classLoader, classId, i);
    }

    private static final Class<?> loadClass(ClassLoader classLoader, ClassId kotlinClassId, int arrayDimensions) {
        JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
        FqNameUnsafe unsafe = kotlinClassId.asSingleFqName().toUnsafe();
        Intrinsics.checkNotNullExpressionValue(unsafe, "kotlinClassId.asSingleFqName().toUnsafe()");
        ClassId classIdMapKotlinToJava = javaToKotlinClassMap.mapKotlinToJava(unsafe);
        if (classIdMapKotlinToJava == null) {
            classIdMapKotlinToJava = kotlinClassId;
        }
        ClassId javaClassId = classIdMapKotlinToJava;
        String strAsString = javaClassId.getPackageFqName().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "javaClassId.packageFqName.asString()");
        String strAsString2 = javaClassId.getRelativeClassName().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString2, "javaClassId.relativeClassName.asString()");
        return loadClass(classLoader, strAsString, strAsString2, arrayDimensions);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    private static final Class<?> loadClass(ClassLoader classLoader, String packageName, String className, int arrayDimensions) {
        if (Intrinsics.areEqual(packageName, "kotlin")) {
            switch (className.hashCode()) {
                case -901856463:
                    if (className.equals("BooleanArray")) {
                        return boolean[].class;
                    }
                    break;
                case -763279523:
                    if (className.equals("ShortArray")) {
                        return short[].class;
                    }
                    break;
                case -755911549:
                    if (className.equals("CharArray")) {
                        return char[].class;
                    }
                    break;
                case -74930671:
                    if (className.equals("ByteArray")) {
                        return byte[].class;
                    }
                    break;
                case 22374632:
                    if (className.equals("DoubleArray")) {
                        return double[].class;
                    }
                    break;
                case 63537721:
                    if (className.equals("Array")) {
                        return Object[].class;
                    }
                    break;
                case 601811914:
                    if (className.equals("IntArray")) {
                        return int[].class;
                    }
                    break;
                case 948852093:
                    if (className.equals("FloatArray")) {
                        return float[].class;
                    }
                    break;
                case 2104330525:
                    if (className.equals("LongArray")) {
                        return long[].class;
                    }
                    break;
            }
        }
        String fqName = packageName + '.' + StringsKt.replace$default(className, '.', '$', false, 4, (Object) null);
        if (arrayDimensions > 0) {
            fqName = StringsKt.repeat("[", arrayDimensions) + 'L' + fqName + ';';
        }
        return ReflectJavaClassFinderKt.tryLoadClass(classLoader, fqName);
    }

    @NotNull
    public static final Class<?> createArrayType(@NotNull Class<?> createArrayType) {
        Intrinsics.checkNotNullParameter(createArrayType, "$this$createArrayType");
        return Array.newInstance(createArrayType, 0).getClass();
    }

    @Nullable
    public static final KVisibility toKVisibility(@NotNull DescriptorVisibility toKVisibility) {
        Intrinsics.checkNotNullParameter(toKVisibility, "$this$toKVisibility");
        if (Intrinsics.areEqual(toKVisibility, DescriptorVisibilities.PUBLIC)) {
            return KVisibility.PUBLIC;
        }
        if (Intrinsics.areEqual(toKVisibility, DescriptorVisibilities.PROTECTED)) {
            return KVisibility.PROTECTED;
        }
        if (Intrinsics.areEqual(toKVisibility, DescriptorVisibilities.INTERNAL)) {
            return KVisibility.INTERNAL;
        }
        if (Intrinsics.areEqual(toKVisibility, DescriptorVisibilities.PRIVATE) || Intrinsics.areEqual(toKVisibility, DescriptorVisibilities.PRIVATE_TO_THIS)) {
            return KVisibility.PRIVATE;
        }
        return null;
    }

    @NotNull
    public static final List<Annotation> computeAnnotations(@NotNull Annotated computeAnnotations) {
        Annotation annotationInstance;
        Intrinsics.checkNotNullParameter(computeAnnotations, "$this$computeAnnotations");
        Iterable $this$mapNotNull$iv = computeAnnotations.getAnnotations();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            AnnotationDescriptor it = (AnnotationDescriptor) element$iv$iv$iv;
            SourceElement source = it.getSource();
            if (source instanceof ReflectAnnotationSource) {
                annotationInstance = ((ReflectAnnotationSource) source).getAnnotation();
            } else if (source instanceof RuntimeSourceElementFactory.RuntimeSourceElement) {
                ReflectJavaElement javaElement = ((RuntimeSourceElementFactory.RuntimeSourceElement) source).getJavaElement();
                if (!(javaElement instanceof ReflectJavaAnnotation)) {
                    javaElement = null;
                }
                ReflectJavaAnnotation reflectJavaAnnotation = (ReflectJavaAnnotation) javaElement;
                annotationInstance = reflectJavaAnnotation != null ? reflectJavaAnnotation.getAnnotation() : null;
            } else {
                annotationInstance = toAnnotationInstance(it);
            }
            if (annotationInstance != null) {
                destination$iv$iv.add(annotationInstance);
            }
        }
        return (List) destination$iv$iv;
    }

    private static final Annotation toAnnotationInstance(AnnotationDescriptor $this$toAnnotationInstance) {
        ClassDescriptor annotationClass = DescriptorUtilsKt.getAnnotationClass($this$toAnnotationInstance);
        Class javaClass = annotationClass != null ? toJavaClass(annotationClass) : null;
        if (!(javaClass instanceof Class)) {
            javaClass = null;
        }
        if (javaClass == null) {
            return null;
        }
        Class annotationClass2 = javaClass;
        Iterable $this$mapNotNull$iv = $this$toAnnotationInstance.getAllValueArguments().entrySet();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            Map.Entry $dstr$name$value = (Map.Entry) element$iv$iv$iv;
            Name name = (Name) $dstr$name$value.getKey();
            ConstantValue value = (ConstantValue) $dstr$name$value.getValue();
            ClassLoader classLoader = annotationClass2.getClassLoader();
            Intrinsics.checkNotNullExpressionValue(classLoader, "annotationClass.classLoader");
            Object p1 = toRuntimeValue(value, classLoader);
            Pair pair = p1 != null ? TuplesKt.to(name.asString(), p1) : null;
            if (pair != null) {
                destination$iv$iv.add(pair);
            }
        }
        return (Annotation) AnnotationConstructorCallerKt.createAnnotationInstance$default(annotationClass2, MapsKt.toMap((List) destination$iv$iv), null, 4, null);
    }

    private static final Object toRuntimeValue(ConstantValue<?> constantValue, ClassLoader classLoader) {
        if (constantValue instanceof AnnotationValue) {
            return toAnnotationInstance(((AnnotationValue) constantValue).getValue());
        }
        if (!(constantValue instanceof ArrayValue)) {
            if (constantValue instanceof EnumValue) {
                Pair<? extends ClassId, ? extends Name> value = ((EnumValue) constantValue).getValue();
                ClassId enumClassId = value.component1();
                Name entryName = value.component2();
                Class enumClass = loadClass$default(classLoader, enumClassId, 0, 4, null);
                if (enumClass == null) {
                    return null;
                }
                if (enumClass == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<out kotlin.Enum<*>>");
                }
                return Util.getEnumConstantByName(enumClass, entryName.asString());
            }
            if (constantValue instanceof KClassValue) {
                KClassValue.Value classValue = ((KClassValue) constantValue).getValue();
                if (classValue instanceof KClassValue.Value.NormalClass) {
                    return loadClass(classLoader, ((KClassValue.Value.NormalClass) classValue).getClassId(), ((KClassValue.Value.NormalClass) classValue).getArrayDimensions());
                }
                if (!(classValue instanceof KClassValue.Value.LocalClass)) {
                    throw new NoWhenBranchMatchedException();
                }
                ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = ((KClassValue.Value.LocalClass) classValue).getType().getConstructor().mo3831getDeclarationDescriptor();
                if (!(classifierDescriptorMo3831getDeclarationDescriptor instanceof ClassDescriptor)) {
                    classifierDescriptorMo3831getDeclarationDescriptor = null;
                }
                ClassDescriptor classDescriptor = (ClassDescriptor) classifierDescriptorMo3831getDeclarationDescriptor;
                if (classDescriptor != null) {
                    return toJavaClass(classDescriptor);
                }
                return null;
            }
            if ((constantValue instanceof ErrorValue) || (constantValue instanceof NullValue)) {
                return null;
            }
            return constantValue.getValue();
        }
        Iterable $this$map$iv = ((ArrayValue) constantValue).getValue();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            ConstantValue it = (ConstantValue) item$iv$iv;
            destination$iv$iv.add(toRuntimeValue(it, classLoader));
        }
        Collection $this$toTypedArray$iv = (List) destination$iv$iv;
        Object[] array = $this$toTypedArray$iv.toArray(new Object[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        return array;
    }

    @Nullable
    public static final KFunctionImpl asKFunctionImpl(@Nullable Object $this$asKFunctionImpl) {
        Object obj = $this$asKFunctionImpl;
        if (!(obj instanceof KFunctionImpl)) {
            obj = null;
        }
        KFunctionImpl kFunctionImpl = (KFunctionImpl) obj;
        if (kFunctionImpl != null) {
            return kFunctionImpl;
        }
        Object obj2 = $this$asKFunctionImpl;
        if (!(obj2 instanceof FunctionReference)) {
            obj2 = null;
        }
        FunctionReference functionReference = (FunctionReference) obj2;
        KCallable kCallableCompute = functionReference != null ? functionReference.compute() : null;
        if (!(kCallableCompute instanceof KFunctionImpl)) {
            kCallableCompute = null;
        }
        return (KFunctionImpl) kCallableCompute;
    }

    @Nullable
    public static final KPropertyImpl<?> asKPropertyImpl(@Nullable Object $this$asKPropertyImpl) {
        Object obj = $this$asKPropertyImpl;
        if (!(obj instanceof KPropertyImpl)) {
            obj = null;
        }
        KPropertyImpl<?> kPropertyImpl = (KPropertyImpl) obj;
        if (kPropertyImpl != null) {
            return kPropertyImpl;
        }
        Object obj2 = $this$asKPropertyImpl;
        if (!(obj2 instanceof PropertyReference)) {
            obj2 = null;
        }
        PropertyReference propertyReference = (PropertyReference) obj2;
        KCallable kCallableCompute = propertyReference != null ? propertyReference.compute() : null;
        if (!(kCallableCompute instanceof KPropertyImpl)) {
            kCallableCompute = null;
        }
        return (KPropertyImpl) kCallableCompute;
    }

    @Nullable
    public static final KCallableImpl<?> asKCallableImpl(@Nullable Object $this$asKCallableImpl) {
        Object obj = $this$asKCallableImpl;
        if (!(obj instanceof KCallableImpl)) {
            obj = null;
        }
        KFunctionImpl kFunctionImplAsKFunctionImpl = (KCallableImpl) obj;
        if (kFunctionImplAsKFunctionImpl == null) {
            kFunctionImplAsKFunctionImpl = asKFunctionImpl($this$asKCallableImpl);
        }
        return kFunctionImplAsKFunctionImpl != null ? kFunctionImplAsKFunctionImpl : asKPropertyImpl($this$asKCallableImpl);
    }

    @Nullable
    public static final ReceiverParameterDescriptor getInstanceReceiverParameter(@NotNull CallableDescriptor instanceReceiverParameter) {
        Intrinsics.checkNotNullParameter(instanceReceiverParameter, "$this$instanceReceiverParameter");
        if (instanceReceiverParameter.getDispatchReceiverParameter() == null) {
            return null;
        }
        DeclarationDescriptor containingDeclaration = instanceReceiverParameter.getContainingDeclaration();
        if (containingDeclaration == null) {
            throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
        }
        return ((ClassDescriptor) containingDeclaration).getThisAsReceiverParameter();
    }

    @Nullable
    public static final <M extends MessageLite, D extends CallableDescriptor> D deserializeToDescriptor(@NotNull Class<?> moduleAnchor, @NotNull M proto, @NotNull NameResolver nameResolver, @NotNull TypeTable typeTable, @NotNull BinaryVersion metadataVersion, @NotNull Function2<? super MemberDeserializer, ? super M, ? extends D> createDescriptor) {
        List typeParameterList;
        Intrinsics.checkNotNullParameter(moduleAnchor, "moduleAnchor");
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        Intrinsics.checkNotNullParameter(metadataVersion, "metadataVersion");
        Intrinsics.checkNotNullParameter(createDescriptor, "createDescriptor");
        RuntimeModuleData moduleData = ModuleByClassLoaderKt.getOrCreateModule(moduleAnchor);
        if (proto instanceof ProtoBuf.Function) {
            typeParameterList = ((ProtoBuf.Function) proto).getTypeParameterList();
        } else {
            if (!(proto instanceof ProtoBuf.Property)) {
                throw new IllegalStateException(("Unsupported message: " + proto).toString());
            }
            typeParameterList = ((ProtoBuf.Property) proto).getTypeParameterList();
        }
        List typeParameters = typeParameterList;
        DeserializationComponents deserialization = moduleData.getDeserialization();
        ModuleDescriptor module = moduleData.getModule();
        VersionRequirementTable empty = VersionRequirementTable.Companion.getEMPTY();
        Intrinsics.checkNotNullExpressionValue(typeParameters, "typeParameters");
        DeserializationContext context = new DeserializationContext(deserialization, nameResolver, module, typeTable, empty, metadataVersion, null, null, typeParameters);
        return createDescriptor.invoke(new MemberDeserializer(context), proto);
    }

    public static final boolean isInlineClassType(@NotNull KType isInlineClassType) {
        Intrinsics.checkNotNullParameter(isInlineClassType, "$this$isInlineClassType");
        KType kType = isInlineClassType;
        if (!(kType instanceof KTypeImpl)) {
            kType = null;
        }
        KTypeImpl kTypeImpl = (KTypeImpl) kType;
        if (kTypeImpl != null) {
            KotlinType type = kTypeImpl.getType();
            if (type != null && InlineClassesUtilsKt.isInlineClassType(type)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public static final Object defaultPrimitiveValue(@NotNull Type type) {
        Intrinsics.checkNotNullParameter(type, "type");
        if ((type instanceof Class) && ((Class) type).isPrimitive()) {
            if (Intrinsics.areEqual(type, Boolean.TYPE)) {
                return false;
            }
            if (Intrinsics.areEqual(type, Character.TYPE)) {
                return Character.valueOf((char) 0);
            }
            if (Intrinsics.areEqual(type, Byte.TYPE)) {
                return Byte.valueOf((byte) 0);
            }
            if (Intrinsics.areEqual(type, Short.TYPE)) {
                return Short.valueOf((short) 0);
            }
            if (Intrinsics.areEqual(type, Integer.TYPE)) {
                return 0;
            }
            if (Intrinsics.areEqual(type, Float.TYPE)) {
                return Float.valueOf(0.0f);
            }
            if (Intrinsics.areEqual(type, Long.TYPE)) {
                return 0L;
            }
            if (Intrinsics.areEqual(type, Double.TYPE)) {
                return Double.valueOf(0.0d);
            }
            if (Intrinsics.areEqual(type, Void.TYPE)) {
                throw new IllegalStateException("Parameter with void type is illegal");
            }
            throw new UnsupportedOperationException("Unknown primitive: " + type);
        }
        return null;
    }
}
