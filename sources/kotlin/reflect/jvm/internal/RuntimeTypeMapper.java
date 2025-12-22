package kotlin.reflect.jvm.internal;

import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.JvmFunctionSignature;
import kotlin.reflect.jvm.internal.JvmPropertySignature;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.CloneableClassScope;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaConstructor;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaField;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaMethod;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAbi;
import kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaPropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElement;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaElement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedCallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor;
import org.jetbrains.annotations.NotNull;

/* compiled from: RuntimeTypeMapper.kt */
@Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��P\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0012\u0010\u000e\u001a\u00020\u00042\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0007J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\u0014H\u0002J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u0006\u0012\u0002\b\u00030\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u001c"}, d2 = {"Lkotlin/reflect/jvm/internal/RuntimeTypeMapper;", "", "()V", "JAVA_LANG_VOID", "Lkotlin/reflect/jvm/internal/impl/name/ClassId;", "primitiveType", "Lkotlin/reflect/jvm/internal/impl/builtins/PrimitiveType;", "Ljava/lang/Class;", "getPrimitiveType", "(Ljava/lang/Class;)Lorg/jetbrains/kotlin/builtins/PrimitiveType;", "isKnownBuiltInFunction", "", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/FunctionDescriptor;", "mapJvmClassToKotlinClassId", "klass", "mapJvmFunctionSignature", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature$KotlinFunction;", "mapName", "", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableMemberDescriptor;", "mapPropertySignature", "Lkotlin/reflect/jvm/internal/JvmPropertySignature;", "possiblyOverriddenProperty", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "mapSignature", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature;", "possiblySubstitutedFunction", "kotlin-reflection"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/RuntimeTypeMapper.class */
public final class RuntimeTypeMapper {
    private static final ClassId JAVA_LANG_VOID;

    @NotNull
    public static final RuntimeTypeMapper INSTANCE = new RuntimeTypeMapper();

    private RuntimeTypeMapper() {
    }

    static {
        ClassId classId = ClassId.topLevel(new FqName("java.lang.Void"));
        Intrinsics.checkNotNullExpressionValue(classId, "ClassId.topLevel(FqName(\"java.lang.Void\"))");
        JAVA_LANG_VOID = classId;
    }

    @NotNull
    public final JvmFunctionSignature mapSignature(@NotNull FunctionDescriptor possiblySubstitutedFunction) {
        Method method;
        JvmMemberSignature.Method signature;
        JvmMemberSignature.Method signature2;
        Intrinsics.checkNotNullParameter(possiblySubstitutedFunction, "possiblySubstitutedFunction");
        CallableMemberDescriptor callableMemberDescriptorUnwrapFakeOverride = DescriptorUtils.unwrapFakeOverride(possiblySubstitutedFunction);
        Intrinsics.checkNotNullExpressionValue(callableMemberDescriptorUnwrapFakeOverride, "DescriptorUtils.unwrapFa…siblySubstitutedFunction)");
        FunctionDescriptor function = ((FunctionDescriptor) callableMemberDescriptorUnwrapFakeOverride).getOriginal();
        Intrinsics.checkNotNullExpressionValue(function, "DescriptorUtils.unwrapFa…titutedFunction).original");
        if (function instanceof DeserializedCallableMemberDescriptor) {
            MessageLite proto = ((DeserializedCallableMemberDescriptor) function).getProto();
            if ((proto instanceof ProtoBuf.Function) && (signature2 = JvmProtoBufUtil.INSTANCE.getJvmMethodSignature((ProtoBuf.Function) proto, ((DeserializedCallableMemberDescriptor) function).getNameResolver(), ((DeserializedCallableMemberDescriptor) function).getTypeTable())) != null) {
                return new JvmFunctionSignature.KotlinFunction(signature2);
            }
            if ((proto instanceof ProtoBuf.Constructor) && (signature = JvmProtoBufUtil.INSTANCE.getJvmConstructorSignature((ProtoBuf.Constructor) proto, ((DeserializedCallableMemberDescriptor) function).getNameResolver(), ((DeserializedCallableMemberDescriptor) function).getTypeTable())) != null) {
                DeclarationDescriptor containingDeclaration = possiblySubstitutedFunction.getContainingDeclaration();
                Intrinsics.checkNotNullExpressionValue(containingDeclaration, "possiblySubstitutedFunction.containingDeclaration");
                if (InlineClassesUtilsKt.isInlineClass(containingDeclaration)) {
                    return new JvmFunctionSignature.KotlinFunction(signature);
                }
                return new JvmFunctionSignature.KotlinConstructor(signature);
            }
            return mapJvmFunctionSignature(function);
        }
        if (function instanceof JavaMethodDescriptor) {
            SourceElement source = ((JavaMethodDescriptor) function).getSource();
            if (!(source instanceof JavaSourceElement)) {
                source = null;
            }
            JavaSourceElement javaSourceElement = (JavaSourceElement) source;
            JavaElement javaElement = javaSourceElement != null ? javaSourceElement.getJavaElement() : null;
            if (!(javaElement instanceof ReflectJavaMethod)) {
                javaElement = null;
            }
            ReflectJavaMethod reflectJavaMethod = (ReflectJavaMethod) javaElement;
            if (reflectJavaMethod != null && (method = reflectJavaMethod.getMember()) != null) {
                return new JvmFunctionSignature.JavaMethod(method);
            }
            throw new KotlinReflectionInternalError("Incorrect resolution sequence for Java method " + function);
        }
        if (function instanceof JavaClassConstructorDescriptor) {
            SourceElement source2 = ((JavaClassConstructorDescriptor) function).getSource();
            if (!(source2 instanceof JavaSourceElement)) {
                source2 = null;
            }
            JavaSourceElement javaSourceElement2 = (JavaSourceElement) source2;
            JavaElement element = javaSourceElement2 != null ? javaSourceElement2.getJavaElement() : null;
            if (element instanceof ReflectJavaConstructor) {
                return new JvmFunctionSignature.JavaConstructor(((ReflectJavaConstructor) element).getMember());
            }
            if ((element instanceof ReflectJavaClass) && ((ReflectJavaClass) element).isAnnotationType()) {
                return new JvmFunctionSignature.FakeJavaAnnotationConstructor(((ReflectJavaClass) element).getElement());
            }
            throw new KotlinReflectionInternalError("Incorrect resolution sequence for Java constructor " + function + " (" + element + ')');
        }
        if (isKnownBuiltInFunction(function)) {
            return mapJvmFunctionSignature(function);
        }
        throw new KotlinReflectionInternalError("Unknown origin of " + function + " (" + function.getClass() + ')');
    }

    @NotNull
    public final JvmPropertySignature mapPropertySignature(@NotNull PropertyDescriptor possiblyOverriddenProperty) {
        JvmFunctionSignature.KotlinFunction kotlinFunctionMapJvmFunctionSignature;
        Intrinsics.checkNotNullParameter(possiblyOverriddenProperty, "possiblyOverriddenProperty");
        CallableMemberDescriptor callableMemberDescriptorUnwrapFakeOverride = DescriptorUtils.unwrapFakeOverride(possiblyOverriddenProperty);
        Intrinsics.checkNotNullExpressionValue(callableMemberDescriptorUnwrapFakeOverride, "DescriptorUtils.unwrapFa…ssiblyOverriddenProperty)");
        PropertyDescriptor property = ((PropertyDescriptor) callableMemberDescriptorUnwrapFakeOverride).getOriginal();
        Intrinsics.checkNotNullExpressionValue(property, "DescriptorUtils.unwrapFa…rriddenProperty).original");
        if (property instanceof DeserializedPropertyDescriptor) {
            ProtoBuf.Property proto = ((DeserializedPropertyDescriptor) property).getProto();
            GeneratedMessageLite.GeneratedExtension<ProtoBuf.Property, JvmProtoBuf.JvmPropertySignature> generatedExtension = JvmProtoBuf.propertySignature;
            Intrinsics.checkNotNullExpressionValue(generatedExtension, "JvmProtoBuf.propertySignature");
            JvmProtoBuf.JvmPropertySignature signature = (JvmProtoBuf.JvmPropertySignature) ProtoBufUtilKt.getExtensionOrNull(proto, generatedExtension);
            if (signature != null) {
                return new JvmPropertySignature.KotlinProperty(property, proto, signature, ((DeserializedPropertyDescriptor) property).getNameResolver(), ((DeserializedPropertyDescriptor) property).getTypeTable());
            }
        } else if (property instanceof JavaPropertyDescriptor) {
            SourceElement source = ((JavaPropertyDescriptor) property).getSource();
            if (!(source instanceof JavaSourceElement)) {
                source = null;
            }
            JavaSourceElement javaSourceElement = (JavaSourceElement) source;
            JavaElement element = javaSourceElement != null ? javaSourceElement.getJavaElement() : null;
            if (element instanceof ReflectJavaField) {
                return new JvmPropertySignature.JavaField(((ReflectJavaField) element).getMember());
            }
            if (element instanceof ReflectJavaMethod) {
                Method member = ((ReflectJavaMethod) element).getMember();
                PropertySetterDescriptor setter = property.getSetter();
                SourceElement source2 = setter != null ? setter.getSource() : null;
                if (!(source2 instanceof JavaSourceElement)) {
                    source2 = null;
                }
                JavaSourceElement javaSourceElement2 = (JavaSourceElement) source2;
                JavaElement javaElement = javaSourceElement2 != null ? javaSourceElement2.getJavaElement() : null;
                if (!(javaElement instanceof ReflectJavaMethod)) {
                    javaElement = null;
                }
                ReflectJavaMethod reflectJavaMethod = (ReflectJavaMethod) javaElement;
                return new JvmPropertySignature.JavaMethodProperty(member, reflectJavaMethod != null ? reflectJavaMethod.getMember() : null);
            }
            throw new KotlinReflectionInternalError("Incorrect resolution sequence for Java field " + property + " (source = " + element + ')');
        }
        FunctionDescriptor getter = property.getGetter();
        Intrinsics.checkNotNull(getter);
        FunctionDescriptor p1 = getter;
        JvmFunctionSignature.KotlinFunction kotlinFunctionMapJvmFunctionSignature2 = mapJvmFunctionSignature(p1);
        FunctionDescriptor setter2 = property.getSetter();
        if (setter2 != null) {
            FunctionDescriptor p12 = setter2;
            kotlinFunctionMapJvmFunctionSignature2 = kotlinFunctionMapJvmFunctionSignature2;
            kotlinFunctionMapJvmFunctionSignature = mapJvmFunctionSignature(p12);
        } else {
            kotlinFunctionMapJvmFunctionSignature = null;
        }
        return new JvmPropertySignature.MappedKotlinProperty(kotlinFunctionMapJvmFunctionSignature2, kotlinFunctionMapJvmFunctionSignature);
    }

    private final boolean isKnownBuiltInFunction(FunctionDescriptor descriptor) {
        if (DescriptorFactory.isEnumValueOfMethod(descriptor) || DescriptorFactory.isEnumValuesMethod(descriptor)) {
            return true;
        }
        return Intrinsics.areEqual(descriptor.getName(), CloneableClassScope.Companion.getCLONE_NAME()) && descriptor.getValueParameters().isEmpty();
    }

    private final JvmFunctionSignature.KotlinFunction mapJvmFunctionSignature(FunctionDescriptor descriptor) {
        return new JvmFunctionSignature.KotlinFunction(new JvmMemberSignature.Method(mapName(descriptor), MethodSignatureMappingKt.computeJvmDescriptor$default(descriptor, false, false, 1, null)));
    }

    private final String mapName(CallableMemberDescriptor descriptor) {
        String jvmMethodNameIfSpecial = SpecialBuiltinMembers.getJvmMethodNameIfSpecial(descriptor);
        if (jvmMethodNameIfSpecial == null) {
            if (descriptor instanceof PropertyGetterDescriptor) {
                String strAsString = DescriptorUtilsKt.getPropertyIfAccessor(descriptor).getName().asString();
                Intrinsics.checkNotNullExpressionValue(strAsString, "descriptor.propertyIfAccessor.name.asString()");
                return JvmAbi.getterName(strAsString);
            }
            if (descriptor instanceof PropertySetterDescriptor) {
                String strAsString2 = DescriptorUtilsKt.getPropertyIfAccessor(descriptor).getName().asString();
                Intrinsics.checkNotNullExpressionValue(strAsString2, "descriptor.propertyIfAccessor.name.asString()");
                return JvmAbi.setterName(strAsString2);
            }
            String strAsString3 = descriptor.getName().asString();
            Intrinsics.checkNotNullExpressionValue(strAsString3, "descriptor.name.asString()");
            return strAsString3;
        }
        return jvmMethodNameIfSpecial;
    }

    @NotNull
    public final ClassId mapJvmClassToKotlinClassId(@NotNull Class<?> klass) {
        Intrinsics.checkNotNullParameter(klass, "klass");
        if (klass.isArray()) {
            Class<?> componentType = klass.getComponentType();
            Intrinsics.checkNotNullExpressionValue(componentType, "klass.componentType");
            PrimitiveType it = getPrimitiveType(componentType);
            if (it != null) {
                return new ClassId(StandardNames.BUILT_INS_PACKAGE_FQ_NAME, it.getArrayTypeName());
            }
            ClassId classId = ClassId.topLevel(StandardNames.FqNames.array.toSafe());
            Intrinsics.checkNotNullExpressionValue(classId, "ClassId.topLevel(Standar…s.FqNames.array.toSafe())");
            return classId;
        }
        if (Intrinsics.areEqual(klass, Void.TYPE)) {
            return JAVA_LANG_VOID;
        }
        PrimitiveType it2 = getPrimitiveType(klass);
        if (it2 != null) {
            return new ClassId(StandardNames.BUILT_INS_PACKAGE_FQ_NAME, it2.getTypeName());
        }
        ClassId classId2 = ReflectClassUtilKt.getClassId(klass);
        if (!classId2.isLocal()) {
            JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
            FqName fqNameAsSingleFqName = classId2.asSingleFqName();
            Intrinsics.checkNotNullExpressionValue(fqNameAsSingleFqName, "classId.asSingleFqName()");
            ClassId it3 = javaToKotlinClassMap.mapJavaToKotlin(fqNameAsSingleFqName);
            if (it3 != null) {
                return it3;
            }
        }
        return classId2;
    }

    private final PrimitiveType getPrimitiveType(Class<?> cls) {
        if (!cls.isPrimitive()) {
            return null;
        }
        JvmPrimitiveType jvmPrimitiveType = JvmPrimitiveType.get(cls.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(jvmPrimitiveType, "JvmPrimitiveType.get(simpleName)");
        return jvmPrimitiveType.getPrimitiveType();
    }
}
