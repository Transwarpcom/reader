package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature;
import kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* compiled from: methodSignatureMapping.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/MethodSignatureMappingKt.class */
public final class MethodSignatureMappingKt {
    @NotNull
    public static final String computeJvmDescriptor(@NotNull FunctionDescriptor $this$computeJvmDescriptor, boolean withReturnType, boolean withName) {
        String str;
        Intrinsics.checkNotNullParameter($this$computeJvmDescriptor, "<this>");
        StringBuilder $this$computeJvmDescriptor_u24lambda_u2d1 = new StringBuilder();
        if (withName) {
            if ($this$computeJvmDescriptor instanceof ConstructorDescriptor) {
                str = Constants.CONSTRUCTOR_NAME;
            } else {
                String strAsString = $this$computeJvmDescriptor.getName().asString();
                Intrinsics.checkNotNullExpressionValue(strAsString, "name.asString()");
                str = strAsString;
            }
            $this$computeJvmDescriptor_u24lambda_u2d1.append(str);
        }
        $this$computeJvmDescriptor_u24lambda_u2d1.append("(");
        ReceiverParameterDescriptor it = $this$computeJvmDescriptor.getExtensionReceiverParameter();
        if (it != null) {
            KotlinType type = it.getType();
            Intrinsics.checkNotNullExpressionValue(type, "it.type");
            appendErasedType($this$computeJvmDescriptor_u24lambda_u2d1, type);
        }
        for (ValueParameterDescriptor parameter : $this$computeJvmDescriptor.getValueParameters()) {
            KotlinType type2 = parameter.getType();
            Intrinsics.checkNotNullExpressionValue(type2, "parameter.type");
            appendErasedType($this$computeJvmDescriptor_u24lambda_u2d1, type2);
        }
        $this$computeJvmDescriptor_u24lambda_u2d1.append(")");
        if (withReturnType) {
            if (DescriptorBasedTypeSignatureMappingKt.hasVoidReturnType($this$computeJvmDescriptor)) {
                $this$computeJvmDescriptor_u24lambda_u2d1.append("V");
            } else {
                KotlinType returnType = $this$computeJvmDescriptor.getReturnType();
                Intrinsics.checkNotNull(returnType);
                appendErasedType($this$computeJvmDescriptor_u24lambda_u2d1, returnType);
            }
        }
        String string = $this$computeJvmDescriptor_u24lambda_u2d1.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public static /* synthetic */ String computeJvmDescriptor$default(FunctionDescriptor functionDescriptor, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        return computeJvmDescriptor(functionDescriptor, z, z2);
    }

    public static final boolean forceSingleValueParameterBoxing(@NotNull CallableDescriptor f) {
        Intrinsics.checkNotNullParameter(f, "f");
        if (!(f instanceof FunctionDescriptor) || !Intrinsics.areEqual(((FunctionDescriptor) f).getName().asString(), "remove") || ((FunctionDescriptor) f).getValueParameters().size() != 1 || SpecialBuiltinMembers.isFromJavaOrBuiltins((CallableMemberDescriptor) f)) {
            return false;
        }
        List<ValueParameterDescriptor> valueParameters = ((FunctionDescriptor) f).getOriginal().getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters, "f.original.valueParameters");
        KotlinType type = ((ValueParameterDescriptor) CollectionsKt.single((List) valueParameters)).getType();
        Intrinsics.checkNotNullExpressionValue(type, "f.original.valueParameters.single().type");
        JvmType jvmTypeMapToJvmType = mapToJvmType(type);
        JvmType.Primitive primitive = jvmTypeMapToJvmType instanceof JvmType.Primitive ? (JvmType.Primitive) jvmTypeMapToJvmType : null;
        if ((primitive == null ? null : primitive.getJvmPrimitiveType()) != JvmPrimitiveType.INT) {
            return false;
        }
        BuiltinMethodsWithSpecialGenericSignature builtinMethodsWithSpecialGenericSignature = BuiltinMethodsWithSpecialGenericSignature.INSTANCE;
        FunctionDescriptor overridden = BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava((FunctionDescriptor) f);
        if (overridden == null) {
            return false;
        }
        List<ValueParameterDescriptor> valueParameters2 = overridden.getOriginal().getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters2, "overridden.original.valueParameters");
        KotlinType type2 = ((ValueParameterDescriptor) CollectionsKt.single((List) valueParameters2)).getType();
        Intrinsics.checkNotNullExpressionValue(type2, "overridden.original.valueParameters.single().type");
        JvmType overriddenParameterType = mapToJvmType(type2);
        DeclarationDescriptor containingDeclaration = overridden.getContainingDeclaration();
        Intrinsics.checkNotNullExpressionValue(containingDeclaration, "overridden.containingDeclaration");
        return Intrinsics.areEqual(DescriptorUtilsKt.getFqNameUnsafe(containingDeclaration), StandardNames.FqNames.mutableCollection.toUnsafe()) && (overriddenParameterType instanceof JvmType.Object) && Intrinsics.areEqual(((JvmType.Object) overriddenParameterType).getInternalName(), "java/lang/Object");
    }

    @Nullable
    public static final String computeJvmSignature(@NotNull CallableDescriptor $this$computeJvmSignature) {
        Intrinsics.checkNotNullParameter($this$computeJvmSignature, "<this>");
        SignatureBuildingComponents $this$computeJvmSignature_u24lambda_u2d2 = SignatureBuildingComponents.INSTANCE;
        if (DescriptorUtils.isLocal($this$computeJvmSignature)) {
            return null;
        }
        DeclarationDescriptor containingDeclaration = $this$computeJvmSignature.getContainingDeclaration();
        ClassDescriptor classDescriptor = containingDeclaration instanceof ClassDescriptor ? (ClassDescriptor) containingDeclaration : null;
        if (classDescriptor == null || classDescriptor.getName().isSpecial()) {
            return null;
        }
        CallableDescriptor original = $this$computeJvmSignature.getOriginal();
        SimpleFunctionDescriptor simpleFunctionDescriptor = original instanceof SimpleFunctionDescriptor ? (SimpleFunctionDescriptor) original : null;
        if (simpleFunctionDescriptor == null) {
            return null;
        }
        return MethodSignatureBuildingUtilsKt.signature($this$computeJvmSignature_u24lambda_u2d2, classDescriptor, computeJvmDescriptor$default(simpleFunctionDescriptor, false, false, 3, null));
    }

    @NotNull
    public static final String getInternalName(@NotNull ClassDescriptor $this$internalName) {
        Intrinsics.checkNotNullParameter($this$internalName, "<this>");
        JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
        FqNameUnsafe unsafe = DescriptorUtilsKt.getFqNameSafe($this$internalName).toUnsafe();
        Intrinsics.checkNotNullExpressionValue(unsafe, "fqNameSafe.toUnsafe()");
        ClassId it = javaToKotlinClassMap.mapKotlinToJava(unsafe);
        if (it != null) {
            String internalName = JvmClassName.byClassId(it).getInternalName();
            Intrinsics.checkNotNullExpressionValue(internalName, "byClassId(it).internalName");
            return internalName;
        }
        return DescriptorBasedTypeSignatureMappingKt.computeInternalName$default($this$internalName, null, 2, null);
    }

    private static final void appendErasedType(StringBuilder $this$appendErasedType, KotlinType type) {
        $this$appendErasedType.append(mapToJvmType(type));
    }

    @NotNull
    public static final JvmType mapToJvmType(@NotNull KotlinType $this$mapToJvmType) {
        Intrinsics.checkNotNullParameter($this$mapToJvmType, "<this>");
        return (JvmType) DescriptorBasedTypeSignatureMappingKt.mapType$default($this$mapToJvmType, JvmTypeFactoryImpl.INSTANCE, TypeMappingMode.DEFAULT, TypeMappingConfigurationImpl.INSTANCE, null, null, 32, null);
    }
}
