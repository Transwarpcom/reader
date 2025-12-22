package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckingProcedure;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: specialBuiltinMembers.kt */
@JvmName(name = "SpecialBuiltinMembers")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/SpecialBuiltinMembers.class */
public final class SpecialBuiltinMembers {
    @Nullable
    public static final <T extends CallableMemberDescriptor> T getOverriddenBuiltinWithDifferentJvmName(@NotNull T t) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        if (!SpecialGenericSignatures.Companion.getORIGINAL_SHORT_NAMES().contains(t.getName()) && !BuiltinSpecialProperties.INSTANCE.getSPECIAL_SHORT_NAMES().contains(DescriptorUtilsKt.getPropertyIfAccessor(t).getName())) {
            return null;
        }
        if (t instanceof PropertyDescriptor ? true : t instanceof PropertyAccessorDescriptor) {
            return (T) DescriptorUtilsKt.firstOverridden$default(t, false, new Function1<CallableMemberDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName.1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final boolean invoke2(@NotNull CallableMemberDescriptor it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return ClassicBuiltinSpecialProperties.INSTANCE.hasBuiltinSpecialPropertyFqName(DescriptorUtilsKt.getPropertyIfAccessor(it));
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(CallableMemberDescriptor callableMemberDescriptor) {
                    return Boolean.valueOf(invoke2(callableMemberDescriptor));
                }
            }, 1, null);
        }
        if (t instanceof SimpleFunctionDescriptor) {
            return (T) DescriptorUtilsKt.firstOverridden$default(t, false, new Function1<CallableMemberDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName.2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(CallableMemberDescriptor callableMemberDescriptor) {
                    return Boolean.valueOf(invoke2(callableMemberDescriptor));
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final boolean invoke2(@NotNull CallableMemberDescriptor it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return BuiltinMethodsWithDifferentJvmName.INSTANCE.isBuiltinFunctionWithDifferentNameInJvm((SimpleFunctionDescriptor) it);
                }
            }, 1, null);
        }
        return null;
    }

    public static final boolean doesOverrideBuiltinWithDifferentJvmName(@NotNull CallableMemberDescriptor $this$doesOverrideBuiltinWithDifferentJvmName) {
        Intrinsics.checkNotNullParameter($this$doesOverrideBuiltinWithDifferentJvmName, "<this>");
        return getOverriddenBuiltinWithDifferentJvmName($this$doesOverrideBuiltinWithDifferentJvmName) != null;
    }

    @Nullable
    public static final <T extends CallableMemberDescriptor> T getOverriddenSpecialBuiltin(@NotNull T t) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        T t2 = (T) getOverriddenBuiltinWithDifferentJvmName(t);
        if (t2 != null) {
            return t2;
        }
        BuiltinMethodsWithSpecialGenericSignature builtinMethodsWithSpecialGenericSignature = BuiltinMethodsWithSpecialGenericSignature.INSTANCE;
        Name name = t.getName();
        Intrinsics.checkNotNullExpressionValue(name, "name");
        if (builtinMethodsWithSpecialGenericSignature.getSameAsBuiltinMethodWithErasedValueParameters(name)) {
            return (T) DescriptorUtilsKt.firstOverridden$default(t, false, new Function1<CallableMemberDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers.getOverriddenSpecialBuiltin.2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(CallableMemberDescriptor callableMemberDescriptor) {
                    return Boolean.valueOf(invoke2(callableMemberDescriptor));
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final boolean invoke2(@NotNull CallableMemberDescriptor it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    if (KotlinBuiltIns.isBuiltIn(it)) {
                        BuiltinMethodsWithSpecialGenericSignature builtinMethodsWithSpecialGenericSignature2 = BuiltinMethodsWithSpecialGenericSignature.INSTANCE;
                        if (BuiltinMethodsWithSpecialGenericSignature.getSpecialSignatureInfo(it) != null) {
                            return true;
                        }
                    }
                    return false;
                }
            }, 1, null);
        }
        return null;
    }

    @Nullable
    public static final String getJvmMethodNameIfSpecial(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        Name jvmName;
        Intrinsics.checkNotNullParameter(callableMemberDescriptor, "callableMemberDescriptor");
        CallableMemberDescriptor overriddenBuiltinThatAffectsJvmName = getOverriddenBuiltinThatAffectsJvmName(callableMemberDescriptor);
        CallableMemberDescriptor overriddenBuiltin = overriddenBuiltinThatAffectsJvmName == null ? null : DescriptorUtilsKt.getPropertyIfAccessor(overriddenBuiltinThatAffectsJvmName);
        if (overriddenBuiltin == null) {
            return null;
        }
        if (overriddenBuiltin instanceof PropertyDescriptor) {
            return ClassicBuiltinSpecialProperties.INSTANCE.getBuiltinSpecialPropertyGetterName(overriddenBuiltin);
        }
        if (!(overriddenBuiltin instanceof SimpleFunctionDescriptor) || (jvmName = BuiltinMethodsWithDifferentJvmName.INSTANCE.getJvmName((SimpleFunctionDescriptor) overriddenBuiltin)) == null) {
            return null;
        }
        return jvmName.asString();
    }

    private static final CallableMemberDescriptor getOverriddenBuiltinThatAffectsJvmName(CallableMemberDescriptor callableMemberDescriptor) {
        if (KotlinBuiltIns.isBuiltIn(callableMemberDescriptor)) {
            return getOverriddenBuiltinWithDifferentJvmName(callableMemberDescriptor);
        }
        return null;
    }

    public static final boolean hasRealKotlinSuperClassWithOverrideOf(@NotNull ClassDescriptor $this$hasRealKotlinSuperClassWithOverrideOf, @NotNull CallableDescriptor specialCallableDescriptor) {
        Intrinsics.checkNotNullParameter($this$hasRealKotlinSuperClassWithOverrideOf, "<this>");
        Intrinsics.checkNotNullParameter(specialCallableDescriptor, "specialCallableDescriptor");
        SimpleType builtinContainerDefaultType = ((ClassDescriptor) specialCallableDescriptor.getContainingDeclaration()).getDefaultType();
        Intrinsics.checkNotNullExpressionValue(builtinContainerDefaultType, "specialCallableDescriptor.containingDeclaration as ClassDescriptor).defaultType");
        ClassDescriptor superClassDescriptor = DescriptorUtils.getSuperClassDescriptor($this$hasRealKotlinSuperClassWithOverrideOf);
        while (true) {
            ClassDescriptor superClassDescriptor2 = superClassDescriptor;
            if (superClassDescriptor2 != null) {
                if (!(superClassDescriptor2 instanceof JavaClassDescriptor)) {
                    boolean doesOverrideBuiltinDeclaration = TypeCheckingProcedure.findCorrespondingSupertype(superClassDescriptor2.getDefaultType(), builtinContainerDefaultType) != null;
                    if (doesOverrideBuiltinDeclaration) {
                        return !KotlinBuiltIns.isBuiltIn(superClassDescriptor2);
                    }
                }
                superClassDescriptor = DescriptorUtils.getSuperClassDescriptor(superClassDescriptor2);
            } else {
                return false;
            }
        }
    }

    public static final boolean isFromJava(@NotNull CallableMemberDescriptor $this$isFromJava) {
        Intrinsics.checkNotNullParameter($this$isFromJava, "<this>");
        CallableMemberDescriptor descriptor = DescriptorUtilsKt.getPropertyIfAccessor($this$isFromJava);
        return descriptor.getContainingDeclaration() instanceof JavaClassDescriptor;
    }

    public static final boolean isFromJavaOrBuiltins(@NotNull CallableMemberDescriptor $this$isFromJavaOrBuiltins) {
        Intrinsics.checkNotNullParameter($this$isFromJavaOrBuiltins, "<this>");
        return isFromJava($this$isFromJavaOrBuiltins) || KotlinBuiltIns.isBuiltIn($this$isFromJavaOrBuiltins);
    }
}
