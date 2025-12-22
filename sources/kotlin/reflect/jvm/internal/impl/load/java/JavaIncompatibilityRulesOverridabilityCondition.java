package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.List;
import kotlin.Pair;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.SpecialGenericSignatures;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JavaIncompatibilityRulesOverridabilityCondition.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/JavaIncompatibilityRulesOverridabilityCondition.class */
public final class JavaIncompatibilityRulesOverridabilityCondition implements ExternalOverridabilityCondition {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @Override // kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition
    @NotNull
    public ExternalOverridabilityCondition.Result isOverridable(@NotNull CallableDescriptor superDescriptor, @NotNull CallableDescriptor subDescriptor, @Nullable ClassDescriptor subClassDescriptor) {
        Intrinsics.checkNotNullParameter(superDescriptor, "superDescriptor");
        Intrinsics.checkNotNullParameter(subDescriptor, "subDescriptor");
        if (isIncompatibleInAccordanceWithBuiltInOverridabilityRules(superDescriptor, subDescriptor, subClassDescriptor)) {
            return ExternalOverridabilityCondition.Result.INCOMPATIBLE;
        }
        if (Companion.doesJavaOverrideHaveIncompatibleValueParameterKinds(superDescriptor, subDescriptor)) {
            return ExternalOverridabilityCondition.Result.INCOMPATIBLE;
        }
        return ExternalOverridabilityCondition.Result.UNKNOWN;
    }

    private final boolean isIncompatibleInAccordanceWithBuiltInOverridabilityRules(CallableDescriptor superDescriptor, CallableDescriptor subDescriptor, ClassDescriptor subClassDescriptor) {
        if (!(superDescriptor instanceof CallableMemberDescriptor) || !(subDescriptor instanceof FunctionDescriptor) || KotlinBuiltIns.isBuiltIn(subDescriptor)) {
            return false;
        }
        BuiltinMethodsWithSpecialGenericSignature builtinMethodsWithSpecialGenericSignature = BuiltinMethodsWithSpecialGenericSignature.INSTANCE;
        Name name = ((FunctionDescriptor) subDescriptor).getName();
        Intrinsics.checkNotNullExpressionValue(name, "subDescriptor.name");
        if (!builtinMethodsWithSpecialGenericSignature.getSameAsBuiltinMethodWithErasedValueParameters(name)) {
            SpecialGenericSignatures.Companion companion = SpecialGenericSignatures.Companion;
            Name name2 = ((FunctionDescriptor) subDescriptor).getName();
            Intrinsics.checkNotNullExpressionValue(name2, "subDescriptor.name");
            if (!companion.getSameAsRenamedInJvmBuiltin(name2)) {
                return false;
            }
        }
        CallableMemberDescriptor overriddenBuiltin = SpecialBuiltinMembers.getOverriddenSpecialBuiltin((CallableMemberDescriptor) superDescriptor);
        Boolean boolValueOf = Boolean.valueOf(((FunctionDescriptor) subDescriptor).isHiddenToOvercomeSignatureClash());
        FunctionDescriptor functionDescriptor = superDescriptor instanceof FunctionDescriptor ? (FunctionDescriptor) superDescriptor : null;
        boolean isOneOfDescriptorsHidden = !Intrinsics.areEqual(boolValueOf, functionDescriptor == null ? null : Boolean.valueOf(functionDescriptor.isHiddenToOvercomeSignatureClash()));
        if (isOneOfDescriptorsHidden && (overriddenBuiltin == null || !((FunctionDescriptor) subDescriptor).isHiddenToOvercomeSignatureClash())) {
            return true;
        }
        if (!(subClassDescriptor instanceof JavaClassDescriptor) || ((FunctionDescriptor) subDescriptor).getInitialSignatureDescriptor() != null || overriddenBuiltin == null || SpecialBuiltinMembers.hasRealKotlinSuperClassWithOverrideOf(subClassDescriptor, overriddenBuiltin)) {
            return false;
        }
        if ((overriddenBuiltin instanceof FunctionDescriptor) && (superDescriptor instanceof FunctionDescriptor)) {
            BuiltinMethodsWithSpecialGenericSignature builtinMethodsWithSpecialGenericSignature2 = BuiltinMethodsWithSpecialGenericSignature.INSTANCE;
            if (BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava((FunctionDescriptor) overriddenBuiltin) != null) {
                String strComputeJvmDescriptor$default = MethodSignatureMappingKt.computeJvmDescriptor$default((FunctionDescriptor) subDescriptor, false, false, 2, null);
                FunctionDescriptor original = ((FunctionDescriptor) superDescriptor).getOriginal();
                Intrinsics.checkNotNullExpressionValue(original, "superDescriptor.original");
                if (Intrinsics.areEqual(strComputeJvmDescriptor$default, MethodSignatureMappingKt.computeJvmDescriptor$default(original, false, false, 2, null))) {
                    return false;
                }
                return true;
            }
            return true;
        }
        return true;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition
    @NotNull
    public ExternalOverridabilityCondition.Contract getContract() {
        return ExternalOverridabilityCondition.Contract.CONFLICTS_ONLY;
    }

    /* compiled from: JavaIncompatibilityRulesOverridabilityCondition.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/JavaIncompatibilityRulesOverridabilityCondition$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        public final boolean doesJavaOverrideHaveIncompatibleValueParameterKinds(@NotNull CallableDescriptor superDescriptor, @NotNull CallableDescriptor subDescriptor) {
            Intrinsics.checkNotNullParameter(superDescriptor, "superDescriptor");
            Intrinsics.checkNotNullParameter(subDescriptor, "subDescriptor");
            if (!(subDescriptor instanceof JavaMethodDescriptor) || !(superDescriptor instanceof FunctionDescriptor)) {
                return false;
            }
            boolean z = ((JavaMethodDescriptor) subDescriptor).getValueParameters().size() == ((FunctionDescriptor) superDescriptor).getValueParameters().size();
            if (_Assertions.ENABLED && !z) {
                throw new AssertionError("External overridability condition with CONFLICTS_ONLY should not be run with different value parameters size");
            }
            List<ValueParameterDescriptor> valueParameters = ((JavaMethodDescriptor) subDescriptor).getOriginal().getValueParameters();
            Intrinsics.checkNotNullExpressionValue(valueParameters, "subDescriptor.original.valueParameters");
            List<ValueParameterDescriptor> valueParameters2 = ((FunctionDescriptor) superDescriptor).getOriginal().getValueParameters();
            Intrinsics.checkNotNullExpressionValue(valueParameters2, "superDescriptor.original.valueParameters");
            for (Pair pair : CollectionsKt.zip(valueParameters, valueParameters2)) {
                ValueParameterDescriptor subParameter = (ValueParameterDescriptor) pair.component1();
                ValueParameterDescriptor superParameter = (ValueParameterDescriptor) pair.component2();
                Intrinsics.checkNotNullExpressionValue(subParameter, "subParameter");
                boolean isSubPrimitive = mapValueParameterType((FunctionDescriptor) subDescriptor, subParameter) instanceof JvmType.Primitive;
                Intrinsics.checkNotNullExpressionValue(superParameter, "superParameter");
                boolean isSuperPrimitive = mapValueParameterType((FunctionDescriptor) superDescriptor, superParameter) instanceof JvmType.Primitive;
                if (isSubPrimitive != isSuperPrimitive) {
                    return true;
                }
            }
            return false;
        }

        private final JvmType mapValueParameterType(FunctionDescriptor f, ValueParameterDescriptor valueParameterDescriptor) {
            if (MethodSignatureMappingKt.forceSingleValueParameterBoxing(f) || isPrimitiveCompareTo(f)) {
                KotlinType type = valueParameterDescriptor.getType();
                Intrinsics.checkNotNullExpressionValue(type, "valueParameterDescriptor.type");
                return MethodSignatureMappingKt.mapToJvmType(TypeUtilsKt.makeNullable(type));
            }
            KotlinType type2 = valueParameterDescriptor.getType();
            Intrinsics.checkNotNullExpressionValue(type2, "valueParameterDescriptor.type");
            return MethodSignatureMappingKt.mapToJvmType(type2);
        }

        private final boolean isPrimitiveCompareTo(FunctionDescriptor f) {
            if (f.getValueParameters().size() != 1) {
                return false;
            }
            DeclarationDescriptor containingDeclaration = f.getContainingDeclaration();
            ClassDescriptor classDescriptor = containingDeclaration instanceof ClassDescriptor ? (ClassDescriptor) containingDeclaration : null;
            if (classDescriptor == null) {
                return false;
            }
            List<ValueParameterDescriptor> valueParameters = f.getValueParameters();
            Intrinsics.checkNotNullExpressionValue(valueParameters, "f.valueParameters");
            ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = ((ValueParameterDescriptor) CollectionsKt.single((List) valueParameters)).getType().getConstructor().mo3831getDeclarationDescriptor();
            ClassDescriptor parameterClass = classifierDescriptorMo3831getDeclarationDescriptor instanceof ClassDescriptor ? (ClassDescriptor) classifierDescriptorMo3831getDeclarationDescriptor : null;
            return parameterClass != null && KotlinBuiltIns.isPrimitiveClass(classDescriptor) && Intrinsics.areEqual(DescriptorUtilsKt.getFqNameSafe(classDescriptor), DescriptorUtilsKt.getFqNameSafe(parameterClass));
        }
    }
}
