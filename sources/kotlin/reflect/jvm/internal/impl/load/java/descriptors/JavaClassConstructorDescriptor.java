package kotlin.reflect.jvm.internal.impl.load.java.descriptors;

import java.util.List;
import kotlin.Pair;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassConstructorDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaClassConstructorDescriptor.class */
public class JavaClassConstructorDescriptor extends ClassConstructorDescriptorImpl implements JavaCallableMemberDescriptor {
    private Boolean hasStableParameterNames;
    private Boolean hasSynthesizedParameterNames;
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
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 11:
            case 18:
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
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            default:
                i2 = 3;
                break;
            case 11:
            case 18:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            case 4:
            default:
                objArr[0] = "containingDeclaration";
                break;
            case 1:
            case 5:
            case 9:
            case 15:
                objArr[0] = "annotations";
                break;
            case 2:
            case 8:
            case 13:
                objArr[0] = "kind";
                break;
            case 3:
            case 6:
            case 10:
                objArr[0] = PackageDocumentBase.DCTags.source;
                break;
            case 7:
            case 12:
                objArr[0] = "newOwner";
                break;
            case 11:
            case 18:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaClassConstructorDescriptor";
                break;
            case 14:
                objArr[0] = "sourceElement";
                break;
            case 16:
                objArr[0] = "enhancedValueParametersData";
                break;
            case 17:
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
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaClassConstructorDescriptor";
                break;
            case 11:
                objArr[1] = "createSubstitutedCopy";
                break;
            case 18:
                objArr[1] = "enhance";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            default:
                objArr[2] = Constants.CONSTRUCTOR_NAME;
                break;
            case 4:
            case 5:
            case 6:
                objArr[2] = "createJavaConstructor";
                break;
            case 7:
            case 8:
            case 9:
            case 10:
                objArr[2] = "createSubstitutedCopy";
                break;
            case 11:
            case 18:
                break;
            case 12:
            case 13:
            case 14:
            case 15:
                objArr[2] = "createDescriptor";
                break;
            case 16:
            case 17:
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
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            default:
                throw new IllegalArgumentException(str2);
            case 11:
            case 18:
                throw new IllegalStateException(str2);
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaCallableMemberDescriptor
    public /* bridge */ /* synthetic */ JavaCallableMemberDescriptor enhance(KotlinType x0, List x1, KotlinType x2, Pair x3) {
        return enhance(x0, (List<ValueParameterData>) x1, x2, (Pair<CallableDescriptor.UserDataKey<?>, ?>) x3);
    }

    static {
        $assertionsDisabled = !JavaClassConstructorDescriptor.class.desiredAssertionStatus();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    protected JavaClassConstructorDescriptor(@NotNull ClassDescriptor containingDeclaration, @Nullable JavaClassConstructorDescriptor original, @NotNull Annotations annotations, boolean isPrimary, @NotNull CallableMemberDescriptor.Kind kind, @NotNull SourceElement source) {
        super(containingDeclaration, original, annotations, isPrimary, kind, source);
        if (containingDeclaration == null) {
            $$$reportNull$$$0(0);
        }
        if (annotations == null) {
            $$$reportNull$$$0(1);
        }
        if (kind == null) {
            $$$reportNull$$$0(2);
        }
        if (source == null) {
            $$$reportNull$$$0(3);
        }
        this.hasStableParameterNames = null;
        this.hasSynthesizedParameterNames = null;
    }

    @NotNull
    public static JavaClassConstructorDescriptor createJavaConstructor(@NotNull ClassDescriptor containingDeclaration, @NotNull Annotations annotations, boolean isPrimary, @NotNull SourceElement source) {
        if (containingDeclaration == null) {
            $$$reportNull$$$0(4);
        }
        if (annotations == null) {
            $$$reportNull$$$0(5);
        }
        if (source == null) {
            $$$reportNull$$$0(6);
        }
        return new JavaClassConstructorDescriptor(containingDeclaration, null, annotations, isPrimary, CallableMemberDescriptor.Kind.DECLARATION, source);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    public boolean hasStableParameterNames() {
        if ($assertionsDisabled || this.hasStableParameterNames != null) {
            return this.hasStableParameterNames.booleanValue();
        }
        throw new AssertionError("hasStableParameterNames was not set: " + this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    public void setHasStableParameterNames(boolean hasStableParameterNames) {
        this.hasStableParameterNames = Boolean.valueOf(hasStableParameterNames);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public boolean hasSynthesizedParameterNames() {
        if ($assertionsDisabled || this.hasSynthesizedParameterNames != null) {
            return this.hasSynthesizedParameterNames.booleanValue();
        }
        throw new AssertionError("hasSynthesizedParameterNames was not set: " + this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    public void setHasSynthesizedParameterNames(boolean hasSynthesizedParameterNames) {
        this.hasSynthesizedParameterNames = Boolean.valueOf(hasSynthesizedParameterNames);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassConstructorDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    @NotNull
    public JavaClassConstructorDescriptor createSubstitutedCopy(@NotNull DeclarationDescriptor newOwner, @Nullable FunctionDescriptor original, @NotNull CallableMemberDescriptor.Kind kind, @Nullable Name newName, @NotNull Annotations annotations, @NotNull SourceElement source) {
        if (newOwner == null) {
            $$$reportNull$$$0(7);
        }
        if (kind == null) {
            $$$reportNull$$$0(8);
        }
        if (annotations == null) {
            $$$reportNull$$$0(9);
        }
        if (source == null) {
            $$$reportNull$$$0(10);
        }
        if (kind != CallableMemberDescriptor.Kind.DECLARATION && kind != CallableMemberDescriptor.Kind.SYNTHESIZED) {
            throw new IllegalStateException("Attempt at creating a constructor that is not a declaration: \ncopy from: " + this + "\nnewOwner: " + newOwner + "\nkind: " + kind);
        }
        if (!$assertionsDisabled && newName != null) {
            throw new AssertionError("Attempt to rename constructor: " + this);
        }
        JavaClassConstructorDescriptor result = createDescriptor((ClassDescriptor) newOwner, (JavaClassConstructorDescriptor) original, kind, source, annotations);
        result.setHasStableParameterNames(hasStableParameterNames());
        result.setHasSynthesizedParameterNames(hasSynthesizedParameterNames());
        if (result == null) {
            $$$reportNull$$$0(11);
        }
        return result;
    }

    @NotNull
    protected JavaClassConstructorDescriptor createDescriptor(@NotNull ClassDescriptor newOwner, @Nullable JavaClassConstructorDescriptor original, @NotNull CallableMemberDescriptor.Kind kind, @NotNull SourceElement sourceElement, @NotNull Annotations annotations) {
        if (newOwner == null) {
            $$$reportNull$$$0(12);
        }
        if (kind == null) {
            $$$reportNull$$$0(13);
        }
        if (sourceElement == null) {
            $$$reportNull$$$0(14);
        }
        if (annotations == null) {
            $$$reportNull$$$0(15);
        }
        return new JavaClassConstructorDescriptor(newOwner, original, annotations, this.isPrimary, kind, sourceElement);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaCallableMemberDescriptor
    @NotNull
    public JavaClassConstructorDescriptor enhance(@Nullable KotlinType enhancedReceiverType, @NotNull List<ValueParameterData> enhancedValueParametersData, @NotNull KotlinType enhancedReturnType, @Nullable Pair<CallableDescriptor.UserDataKey<?>, ?> additionalUserData) {
        if (enhancedValueParametersData == null) {
            $$$reportNull$$$0(16);
        }
        if (enhancedReturnType == null) {
            $$$reportNull$$$0(17);
        }
        JavaClassConstructorDescriptor enhanced = createSubstitutedCopy((DeclarationDescriptor) getContainingDeclaration(), (FunctionDescriptor) null, getKind(), (Name) null, getAnnotations(), getSource());
        ReceiverParameterDescriptor enhancedReceiver = enhancedReceiverType == null ? null : DescriptorFactory.createExtensionReceiverParameterForCallable(enhanced, enhancedReceiverType, Annotations.Companion.getEMPTY());
        enhanced.initialize(enhancedReceiver, getDispatchReceiverParameter(), getTypeParameters(), UtilKt.copyValueParameters(enhancedValueParametersData, getValueParameters(), enhanced), enhancedReturnType, getModality(), getVisibility());
        if (additionalUserData != null) {
            enhanced.putInUserDataMap(additionalUserData.getFirst(), additionalUserData.getSecond());
        }
        if (enhanced == null) {
            $$$reportNull$$$0(18);
        }
        return enhanced;
    }
}
