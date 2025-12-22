package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.SuspendFunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.ExpandedTypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.SimpleClassicTypeSystemContext;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.FunctionsKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: descriptorBasedTypeSignatureMapping.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/DescriptorBasedTypeSignatureMappingKt.class */
public final class DescriptorBasedTypeSignatureMappingKt {
    public static /* synthetic */ Object mapType$default(KotlinType kotlinType, JvmTypeFactory jvmTypeFactory, TypeMappingMode typeMappingMode, TypeMappingConfiguration typeMappingConfiguration, JvmDescriptorTypeWriter jvmDescriptorTypeWriter, Function3 function3, int i, Object obj) {
        if ((i & 32) != 0) {
            function3 = FunctionsKt.getDO_NOTHING_3();
        }
        return mapType(kotlinType, jvmTypeFactory, typeMappingMode, typeMappingConfiguration, jvmDescriptorTypeWriter, function3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v163, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r15v3, types: [T, java.lang.Object] */
    @NotNull
    public static final <T> T mapType(@NotNull KotlinType kotlinType, @NotNull JvmTypeFactory<T> factory, @NotNull TypeMappingMode mode, @NotNull TypeMappingConfiguration<? extends T> typeMappingConfiguration, @Nullable JvmDescriptorTypeWriter<T> jvmDescriptorTypeWriter, @NotNull Function3<? super KotlinType, ? super T, ? super TypeMappingMode, Unit> writeGenericType) {
        T tCreateObjectType2;
        KotlinType kotlinType2;
        T tMapType;
        Intrinsics.checkNotNullParameter(kotlinType, "kotlinType");
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(typeMappingConfiguration, "typeMappingConfiguration");
        Intrinsics.checkNotNullParameter(writeGenericType, "writeGenericType");
        KotlinType kotlinTypePreprocessType = typeMappingConfiguration.preprocessType(kotlinType);
        if (kotlinTypePreprocessType != null) {
            return (T) mapType(kotlinTypePreprocessType, factory, mode, typeMappingConfiguration, jvmDescriptorTypeWriter, writeGenericType);
        }
        if (FunctionTypesKt.isSuspendFunctionType(kotlinType)) {
            return (T) mapType(SuspendFunctionTypesKt.transformSuspendFunctionToRuntimeFunctionType(kotlinType, typeMappingConfiguration.releaseCoroutines()), factory, mode, typeMappingConfiguration, jvmDescriptorTypeWriter, writeGenericType);
        }
        Object objMapBuiltInType = TypeSignatureMappingKt.mapBuiltInType(SimpleClassicTypeSystemContext.INSTANCE, kotlinType, factory, mode);
        if (objMapBuiltInType != null) {
            ?? r0 = (Object) TypeSignatureMappingKt.boxTypeIfNeeded(factory, objMapBuiltInType, mode.getNeedPrimitiveBoxing());
            writeGenericType.invoke(kotlinType, r0, mode);
            return r0;
        }
        TypeConstructor constructor = kotlinType.getConstructor();
        if (constructor instanceof IntersectionTypeConstructor) {
            KotlinType alternativeType = ((IntersectionTypeConstructor) constructor).getAlternativeType();
            return (T) mapType(TypeUtilsKt.replaceArgumentsWithStarProjections(alternativeType == null ? typeMappingConfiguration.commonSupertype(((IntersectionTypeConstructor) constructor).mo3835getSupertypes()) : alternativeType), factory, mode, typeMappingConfiguration, jvmDescriptorTypeWriter, writeGenericType);
        }
        ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = constructor.mo3831getDeclarationDescriptor();
        if (classifierDescriptorMo3831getDeclarationDescriptor == null) {
            throw new UnsupportedOperationException(Intrinsics.stringPlus("no descriptor for type constructor of ", kotlinType));
        }
        if (ErrorUtils.isError(classifierDescriptorMo3831getDeclarationDescriptor)) {
            T tCreateObjectType22 = factory.createObjectType2("error/NonExistentClass");
            typeMappingConfiguration.processErrorType(kotlinType, (ClassDescriptor) classifierDescriptorMo3831getDeclarationDescriptor);
            if (jvmDescriptorTypeWriter != null) {
                jvmDescriptorTypeWriter.writeClass(tCreateObjectType22);
            }
            return tCreateObjectType22;
        }
        if ((classifierDescriptorMo3831getDeclarationDescriptor instanceof ClassDescriptor) && KotlinBuiltIns.isArray(kotlinType)) {
            if (kotlinType.getArguments().size() != 1) {
                throw new UnsupportedOperationException("arrays must have one type argument");
            }
            TypeProjection typeProjection = kotlinType.getArguments().get(0);
            KotlinType type = typeProjection.getType();
            Intrinsics.checkNotNullExpressionValue(type, "memberProjection.type");
            if (typeProjection.getProjectionKind() == Variance.IN_VARIANCE) {
                tMapType = factory.createObjectType2("java/lang/Object");
                if (jvmDescriptorTypeWriter != null) {
                    jvmDescriptorTypeWriter.writeArrayType();
                    jvmDescriptorTypeWriter.writeClass(tMapType);
                    jvmDescriptorTypeWriter.writeArrayEnd();
                }
            } else {
                if (jvmDescriptorTypeWriter != null) {
                    jvmDescriptorTypeWriter.writeArrayType();
                }
                Variance projectionKind = typeProjection.getProjectionKind();
                Intrinsics.checkNotNullExpressionValue(projectionKind, "memberProjection.projectionKind");
                tMapType = mapType(type, factory, mode.toGenericArgumentMode(projectionKind, true), typeMappingConfiguration, jvmDescriptorTypeWriter, writeGenericType);
                if (jvmDescriptorTypeWriter != null) {
                    jvmDescriptorTypeWriter.writeArrayEnd();
                }
            }
            return factory.createFromString(Intrinsics.stringPlus("[", factory.toString(tMapType)));
        }
        if (!(classifierDescriptorMo3831getDeclarationDescriptor instanceof ClassDescriptor)) {
            if (!(classifierDescriptorMo3831getDeclarationDescriptor instanceof TypeParameterDescriptor)) {
                if ((classifierDescriptorMo3831getDeclarationDescriptor instanceof TypeAliasDescriptor) && mode.getMapTypeAliases()) {
                    return (T) mapType(((TypeAliasDescriptor) classifierDescriptorMo3831getDeclarationDescriptor).getExpandedType(), factory, mode, typeMappingConfiguration, jvmDescriptorTypeWriter, writeGenericType);
                }
                throw new UnsupportedOperationException(Intrinsics.stringPlus("Unknown type ", kotlinType));
            }
            T t = (T) mapType(TypeUtilsKt.getRepresentativeUpperBound((TypeParameterDescriptor) classifierDescriptorMo3831getDeclarationDescriptor), factory, mode, typeMappingConfiguration, null, FunctionsKt.getDO_NOTHING_3());
            if (jvmDescriptorTypeWriter != null) {
                Name name = classifierDescriptorMo3831getDeclarationDescriptor.getName();
                Intrinsics.checkNotNullExpressionValue(name, "descriptor.getName()");
                jvmDescriptorTypeWriter.writeTypeVariable(name, t);
            }
            return t;
        }
        if (InlineClassesUtilsKt.isInlineClass(classifierDescriptorMo3831getDeclarationDescriptor) && !mode.getNeedInlineClassWrapping() && (kotlinType2 = (KotlinType) ExpandedTypeUtilsKt.computeExpandedTypeForInlineClass(SimpleClassicTypeSystemContext.INSTANCE, kotlinType)) != null) {
            return (T) mapType(kotlinType2, factory, mode.wrapInlineClassesMode(), typeMappingConfiguration, jvmDescriptorTypeWriter, writeGenericType);
        }
        if (mode.isForAnnotationParameter() && KotlinBuiltIns.isKClass((ClassDescriptor) classifierDescriptorMo3831getDeclarationDescriptor)) {
            tCreateObjectType2 = factory.getJavaLangClassType();
        } else {
            ClassDescriptor original = ((ClassDescriptor) classifierDescriptorMo3831getDeclarationDescriptor).getOriginal();
            Intrinsics.checkNotNullExpressionValue(original, "descriptor.original");
            T predefinedTypeForClass = typeMappingConfiguration.getPredefinedTypeForClass(original);
            if (predefinedTypeForClass == null) {
                ClassDescriptor original2 = (((ClassDescriptor) classifierDescriptorMo3831getDeclarationDescriptor).getKind() == ClassKind.ENUM_ENTRY ? (ClassDescriptor) ((ClassDescriptor) classifierDescriptorMo3831getDeclarationDescriptor).getContainingDeclaration() : (ClassDescriptor) classifierDescriptorMo3831getDeclarationDescriptor).getOriginal();
                Intrinsics.checkNotNullExpressionValue(original2, "enumClassIfEnumEntry.original");
                tCreateObjectType2 = factory.createObjectType2(computeInternalName(original2, typeMappingConfiguration));
            } else {
                tCreateObjectType2 = predefinedTypeForClass;
            }
        }
        ?? r15 = tCreateObjectType2;
        writeGenericType.invoke(kotlinType, r15, mode);
        return r15;
    }

    public static final boolean hasVoidReturnType(@NotNull CallableDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (descriptor instanceof ConstructorDescriptor) {
            return true;
        }
        KotlinType returnType = descriptor.getReturnType();
        Intrinsics.checkNotNull(returnType);
        if (KotlinBuiltIns.isUnit(returnType)) {
            KotlinType returnType2 = descriptor.getReturnType();
            Intrinsics.checkNotNull(returnType2);
            if (!TypeUtils.isNullableType(returnType2) && !(descriptor instanceof PropertyGetterDescriptor)) {
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ String computeInternalName$default(ClassDescriptor classDescriptor, TypeMappingConfiguration typeMappingConfiguration, int i, Object obj) {
        if ((i & 2) != 0) {
            typeMappingConfiguration = TypeMappingConfigurationImpl.INSTANCE;
        }
        return computeInternalName(classDescriptor, typeMappingConfiguration);
    }

    @NotNull
    public static final String computeInternalName(@NotNull ClassDescriptor klass, @NotNull TypeMappingConfiguration<?> typeMappingConfiguration) {
        String strComputeInternalName;
        Intrinsics.checkNotNullParameter(klass, "klass");
        Intrinsics.checkNotNullParameter(typeMappingConfiguration, "typeMappingConfiguration");
        String it = typeMappingConfiguration.getPredefinedFullInternalNameForClass(klass);
        if (it != null) {
            return it;
        }
        DeclarationDescriptor container = klass.getContainingDeclaration();
        Intrinsics.checkNotNullExpressionValue(container, "klass.containingDeclaration");
        String name = SpecialNames.safeIdentifier(klass.getName()).getIdentifier();
        Intrinsics.checkNotNullExpressionValue(name, "safeIdentifier(klass.name).identifier");
        if (container instanceof PackageFragmentDescriptor) {
            FqName fqName = ((PackageFragmentDescriptor) container).getFqName();
            if (fqName.isRoot()) {
                return name;
            }
            StringBuilder sb = new StringBuilder();
            String strAsString = fqName.asString();
            Intrinsics.checkNotNullExpressionValue(strAsString, "fqName.asString()");
            return sb.append(StringsKt.replace$default(strAsString, '.', '/', false, 4, (Object) null)).append('/').append(name).toString();
        }
        ClassDescriptor containerClass = container instanceof ClassDescriptor ? (ClassDescriptor) container : null;
        if (containerClass == null) {
            throw new IllegalArgumentException("Unexpected container: " + container + " for " + klass);
        }
        String predefinedInternalNameForClass = typeMappingConfiguration.getPredefinedInternalNameForClass(containerClass);
        if (predefinedInternalNameForClass != null) {
            strComputeInternalName = predefinedInternalNameForClass;
        } else {
            strComputeInternalName = computeInternalName(containerClass, typeMappingConfiguration);
        }
        String containerInternalName = strComputeInternalName;
        return containerInternalName + '$' + name;
    }
}
