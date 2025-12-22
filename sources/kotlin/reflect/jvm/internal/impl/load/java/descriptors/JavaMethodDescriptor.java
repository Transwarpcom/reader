package kotlin.reflect.jvm.internal.impl.load.java.descriptors;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.util.OperatorChecks;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaMethodDescriptor.class */
public class JavaMethodDescriptor extends SimpleFunctionDescriptorImpl implements JavaCallableMemberDescriptor {
    public static final CallableDescriptor.UserDataKey<ValueParameterDescriptor> ORIGINAL_VALUE_PARAMETER_FOR_EXTENSION_RECEIVER;
    private ParameterNamesStatus parameterNamesStatus;
    private final boolean isForRecordComponent;
    static final /* synthetic */ boolean $assertionsDisabled;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            case 19:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 12:
            case 17:
            case 20:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            case 19:
            default:
                i2 = 3;
                break;
            case 12:
            case 17:
            case 20:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            case 5:
            default:
                objArr[0] = "containingDeclaration";
                break;
            case 1:
            case 6:
            case 15:
                objArr[0] = "annotations";
                break;
            case 2:
            case 7:
                objArr[0] = "name";
                break;
            case 3:
            case 14:
                objArr[0] = "kind";
                break;
            case 4:
            case 8:
            case 16:
                objArr[0] = PackageDocumentBase.DCTags.source;
                break;
            case 9:
                objArr[0] = "typeParameters";
                break;
            case 10:
                objArr[0] = "unsubstitutedValueParameters";
                break;
            case 11:
                objArr[0] = "visibility";
                break;
            case 12:
            case 17:
            case 20:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaMethodDescriptor";
                break;
            case 13:
                objArr[0] = "newOwner";
                break;
            case 18:
                objArr[0] = "enhancedValueParametersData";
                break;
            case 19:
                objArr[0] = "enhancedReturnType";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            case 19:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaMethodDescriptor";
                break;
            case 12:
                objArr[1] = "initialize";
                break;
            case 17:
                objArr[1] = "createSubstitutedCopy";
                break;
            case 20:
                objArr[1] = "enhance";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            default:
                objArr[2] = Constants.CONSTRUCTOR_NAME;
                break;
            case 5:
            case 6:
            case 7:
            case 8:
                objArr[2] = "createJavaMethod";
                break;
            case 9:
            case 10:
            case 11:
                objArr[2] = "initialize";
                break;
            case 12:
            case 17:
            case 20:
                break;
            case 13:
            case 14:
            case 15:
            case 16:
                objArr[2] = "createSubstitutedCopy";
                break;
            case 18:
            case 19:
                objArr[2] = "enhance";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            case 19:
            default:
                throw new IllegalArgumentException(str2);
            case 12:
            case 17:
            case 20:
                throw new IllegalStateException(str2);
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaCallableMemberDescriptor
    public /* bridge */ /* synthetic */ JavaCallableMemberDescriptor enhance(KotlinType x0, List x1, KotlinType x2, Pair x3) {
        return enhance(x0, (List<ValueParameterData>) x1, x2, (Pair<CallableDescriptor.UserDataKey<?>, ?>) x3);
    }

    static {
        $assertionsDisabled = !JavaMethodDescriptor.class.desiredAssertionStatus();
        ORIGINAL_VALUE_PARAMETER_FOR_EXTENSION_RECEIVER = new CallableDescriptor.UserDataKey<ValueParameterDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor.1
        };
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaMethodDescriptor$ParameterNamesStatus.class */
    private enum ParameterNamesStatus {
        NON_STABLE_DECLARED(false, false),
        STABLE_DECLARED(true, false),
        NON_STABLE_SYNTHESIZED(false, true),
        STABLE_SYNTHESIZED(true, true);

        public final boolean isStable;
        public final boolean isSynthesized;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaMethodDescriptor$ParameterNamesStatus", BeanUtil.PREFIX_GETTER_GET));
        }

        ParameterNamesStatus(boolean isStable, boolean isSynthesized) {
            this.isStable = isStable;
            this.isSynthesized = isSynthesized;
        }

        @NotNull
        public static ParameterNamesStatus get(boolean stable, boolean synthesized) {
            ParameterNamesStatus parameterNamesStatus = stable ? synthesized ? STABLE_SYNTHESIZED : STABLE_DECLARED : synthesized ? NON_STABLE_SYNTHESIZED : NON_STABLE_DECLARED;
            if (parameterNamesStatus == null) {
                $$$reportNull$$$0(0);
            }
            return parameterNamesStatus;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    protected JavaMethodDescriptor(@NotNull DeclarationDescriptor containingDeclaration, @Nullable SimpleFunctionDescriptor original, @NotNull Annotations annotations, @NotNull Name name, @NotNull CallableMemberDescriptor.Kind kind, @NotNull SourceElement source, boolean isForRecordComponent) {
        super(containingDeclaration, original, annotations, name, kind, source);
        if (containingDeclaration == null) {
            $$$reportNull$$$0(0);
        }
        if (annotations == null) {
            $$$reportNull$$$0(1);
        }
        if (name == null) {
            $$$reportNull$$$0(2);
        }
        if (kind == null) {
            $$$reportNull$$$0(3);
        }
        if (source == null) {
            $$$reportNull$$$0(4);
        }
        this.parameterNamesStatus = null;
        this.isForRecordComponent = isForRecordComponent;
    }

    @NotNull
    public static JavaMethodDescriptor createJavaMethod(@NotNull DeclarationDescriptor containingDeclaration, @NotNull Annotations annotations, @NotNull Name name, @NotNull SourceElement source, boolean isForRecordComponent) {
        if (containingDeclaration == null) {
            $$$reportNull$$$0(5);
        }
        if (annotations == null) {
            $$$reportNull$$$0(6);
        }
        if (name == null) {
            $$$reportNull$$$0(7);
        }
        if (source == null) {
            $$$reportNull$$$0(8);
        }
        return new JavaMethodDescriptor(containingDeclaration, null, annotations, name, CallableMemberDescriptor.Kind.DECLARATION, source, isForRecordComponent);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl
    @NotNull
    public SimpleFunctionDescriptorImpl initialize(@Nullable ReceiverParameterDescriptor extensionReceiverParameter, @Nullable ReceiverParameterDescriptor dispatchReceiverParameter, @NotNull List<? extends TypeParameterDescriptor> typeParameters, @NotNull List<ValueParameterDescriptor> unsubstitutedValueParameters, @Nullable KotlinType unsubstitutedReturnType, @Nullable Modality modality, @NotNull DescriptorVisibility visibility, @Nullable Map<? extends CallableDescriptor.UserDataKey<?>, ?> userData) {
        if (typeParameters == null) {
            $$$reportNull$$$0(9);
        }
        if (unsubstitutedValueParameters == null) {
            $$$reportNull$$$0(10);
        }
        if (visibility == null) {
            $$$reportNull$$$0(11);
        }
        SimpleFunctionDescriptorImpl descriptor = super.initialize(extensionReceiverParameter, dispatchReceiverParameter, typeParameters, unsubstitutedValueParameters, unsubstitutedReturnType, modality, visibility, userData);
        setOperator(OperatorChecks.INSTANCE.check(descriptor).isSuccess());
        if (descriptor == null) {
            $$$reportNull$$$0(12);
        }
        return descriptor;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    public boolean hasStableParameterNames() {
        if ($assertionsDisabled || this.parameterNamesStatus != null) {
            return this.parameterNamesStatus.isStable;
        }
        throw new AssertionError("Parameter names status was not set: " + this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public boolean hasSynthesizedParameterNames() {
        if ($assertionsDisabled || this.parameterNamesStatus != null) {
            return this.parameterNamesStatus.isSynthesized;
        }
        throw new AssertionError("Parameter names status was not set: " + this);
    }

    public void setParameterNamesStatus(boolean hasStableParameterNames, boolean hasSynthesizedParameterNames) {
        this.parameterNamesStatus = ParameterNamesStatus.get(hasStableParameterNames, hasSynthesizedParameterNames);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    @NotNull
    public JavaMethodDescriptor createSubstitutedCopy(@NotNull DeclarationDescriptor newOwner, @Nullable FunctionDescriptor original, @NotNull CallableMemberDescriptor.Kind kind, @Nullable Name newName, @NotNull Annotations annotations, @NotNull SourceElement source) {
        if (newOwner == null) {
            $$$reportNull$$$0(13);
        }
        if (kind == null) {
            $$$reportNull$$$0(14);
        }
        if (annotations == null) {
            $$$reportNull$$$0(15);
        }
        if (source == null) {
            $$$reportNull$$$0(16);
        }
        JavaMethodDescriptor result = new JavaMethodDescriptor(newOwner, (SimpleFunctionDescriptor) original, annotations, newName != null ? newName : getName(), kind, source, this.isForRecordComponent);
        result.setParameterNamesStatus(hasStableParameterNames(), hasSynthesizedParameterNames());
        if (result == null) {
            $$$reportNull$$$0(17);
        }
        return result;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaCallableMemberDescriptor
    @NotNull
    public JavaMethodDescriptor enhance(@Nullable KotlinType enhancedReceiverType, @NotNull List<ValueParameterData> enhancedValueParametersData, @NotNull KotlinType enhancedReturnType, @Nullable Pair<CallableDescriptor.UserDataKey<?>, ?> additionalUserData) {
        if (enhancedValueParametersData == null) {
            $$$reportNull$$$0(18);
        }
        if (enhancedReturnType == null) {
            $$$reportNull$$$0(19);
        }
        List<ValueParameterDescriptor> enhancedValueParameters = UtilKt.copyValueParameters(enhancedValueParametersData, getValueParameters(), this);
        ReceiverParameterDescriptor enhancedReceiver = enhancedReceiverType == null ? null : DescriptorFactory.createExtensionReceiverParameterForCallable(this, enhancedReceiverType, Annotations.Companion.getEMPTY());
        JavaMethodDescriptor enhancedMethod = (JavaMethodDescriptor) newCopyBuilder().setValueParameters(enhancedValueParameters).setReturnType(enhancedReturnType).setExtensionReceiverParameter(enhancedReceiver).setDropOriginalInContainingParts().setPreserveSourceElement().build();
        if (!$assertionsDisabled && enhancedMethod == null) {
            throw new AssertionError("null after substitution while enhancing " + toString());
        }
        if (additionalUserData != null) {
            enhancedMethod.putInUserDataMap(additionalUserData.getFirst(), additionalUserData.getSecond());
        }
        if (enhancedMethod == null) {
            $$$reportNull$$$0(20);
        }
        return enhancedMethod;
    }
}
