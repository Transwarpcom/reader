package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/impl/SimpleFunctionDescriptorImpl.class */
public class SimpleFunctionDescriptorImpl extends FunctionDescriptorImpl implements SimpleFunctionDescriptor {
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
            case 12:
            case 14:
            case 15:
            case 16:
            case 19:
            case 20:
            case 21:
            case 22:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 13:
            case 17:
            case 18:
            case 23:
            case 24:
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
            case 12:
            case 14:
            case 15:
            case 16:
            case 19:
            case 20:
            case 21:
            case 22:
            default:
                i2 = 3;
                break;
            case 13:
            case 17:
            case 18:
            case 23:
            case 24:
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
            case 21:
                objArr[0] = "annotations";
                break;
            case 2:
            case 7:
                objArr[0] = "name";
                break;
            case 3:
            case 8:
            case 20:
                objArr[0] = "kind";
                break;
            case 4:
            case 9:
            case 22:
                objArr[0] = PackageDocumentBase.DCTags.source;
                break;
            case 10:
            case 14:
                objArr[0] = "typeParameters";
                break;
            case 11:
            case 15:
                objArr[0] = "unsubstitutedValueParameters";
                break;
            case 12:
            case 16:
                objArr[0] = "visibility";
                break;
            case 13:
            case 17:
            case 18:
            case 23:
            case 24:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/SimpleFunctionDescriptorImpl";
                break;
            case 19:
                objArr[0] = "newOwner";
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
            case 12:
            case 14:
            case 15:
            case 16:
            case 19:
            case 20:
            case 21:
            case 22:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/SimpleFunctionDescriptorImpl";
                break;
            case 13:
            case 17:
                objArr[1] = "initialize";
                break;
            case 18:
                objArr[1] = "getOriginal";
                break;
            case 23:
                objArr[1] = "copy";
                break;
            case 24:
                objArr[1] = "newCopyBuilder";
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
            case 9:
                objArr[2] = "create";
                break;
            case 10:
            case 11:
            case 12:
            case 14:
            case 15:
            case 16:
                objArr[2] = "initialize";
                break;
            case 13:
            case 17:
            case 18:
            case 23:
            case 24:
                break;
            case 19:
            case 20:
            case 21:
            case 22:
                objArr[2] = "createSubstitutedCopy";
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
            case 12:
            case 14:
            case 15:
            case 16:
            case 19:
            case 20:
            case 21:
            case 22:
            default:
                throw new IllegalArgumentException(str2);
            case 13:
            case 17:
            case 18:
            case 23:
            case 24:
                throw new IllegalStateException(str2);
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    public /* bridge */ /* synthetic */ FunctionDescriptorImpl initialize(ReceiverParameterDescriptor x0, ReceiverParameterDescriptor x1, List x2, List x3, KotlinType x4, Modality x5, DescriptorVisibility x6) {
        return initialize(x0, x1, (List<? extends TypeParameterDescriptor>) x2, (List<ValueParameterDescriptor>) x3, x4, x5, x6);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    protected SimpleFunctionDescriptorImpl(@NotNull DeclarationDescriptor containingDeclaration, @Nullable SimpleFunctionDescriptor original, @NotNull Annotations annotations, @NotNull Name name, @NotNull CallableMemberDescriptor.Kind kind, @NotNull SourceElement source) {
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
    }

    @NotNull
    public static SimpleFunctionDescriptorImpl create(@NotNull DeclarationDescriptor containingDeclaration, @NotNull Annotations annotations, @NotNull Name name, @NotNull CallableMemberDescriptor.Kind kind, @NotNull SourceElement source) {
        if (containingDeclaration == null) {
            $$$reportNull$$$0(5);
        }
        if (annotations == null) {
            $$$reportNull$$$0(6);
        }
        if (name == null) {
            $$$reportNull$$$0(7);
        }
        if (kind == null) {
            $$$reportNull$$$0(8);
        }
        if (source == null) {
            $$$reportNull$$$0(9);
        }
        return new SimpleFunctionDescriptorImpl(containingDeclaration, null, annotations, name, kind, source);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    @NotNull
    public SimpleFunctionDescriptorImpl initialize(@Nullable ReceiverParameterDescriptor extensionReceiverParameter, @Nullable ReceiverParameterDescriptor dispatchReceiverParameter, @NotNull List<? extends TypeParameterDescriptor> typeParameters, @NotNull List<ValueParameterDescriptor> unsubstitutedValueParameters, @Nullable KotlinType unsubstitutedReturnType, @Nullable Modality modality, @NotNull DescriptorVisibility visibility) {
        if (typeParameters == null) {
            $$$reportNull$$$0(10);
        }
        if (unsubstitutedValueParameters == null) {
            $$$reportNull$$$0(11);
        }
        if (visibility == null) {
            $$$reportNull$$$0(12);
        }
        SimpleFunctionDescriptorImpl simpleFunctionDescriptorImplInitialize = initialize(extensionReceiverParameter, dispatchReceiverParameter, typeParameters, unsubstitutedValueParameters, unsubstitutedReturnType, modality, visibility, null);
        if (simpleFunctionDescriptorImplInitialize == null) {
            $$$reportNull$$$0(13);
        }
        return simpleFunctionDescriptorImplInitialize;
    }

    @NotNull
    public SimpleFunctionDescriptorImpl initialize(@Nullable ReceiverParameterDescriptor extensionReceiverParameter, @Nullable ReceiverParameterDescriptor dispatchReceiverParameter, @NotNull List<? extends TypeParameterDescriptor> typeParameters, @NotNull List<ValueParameterDescriptor> unsubstitutedValueParameters, @Nullable KotlinType unsubstitutedReturnType, @Nullable Modality modality, @NotNull DescriptorVisibility visibility, @Nullable Map<? extends CallableDescriptor.UserDataKey<?>, ?> userData) {
        if (typeParameters == null) {
            $$$reportNull$$$0(14);
        }
        if (unsubstitutedValueParameters == null) {
            $$$reportNull$$$0(15);
        }
        if (visibility == null) {
            $$$reportNull$$$0(16);
        }
        super.initialize(extensionReceiverParameter, dispatchReceiverParameter, typeParameters, unsubstitutedValueParameters, unsubstitutedReturnType, modality, visibility);
        if (userData != null && !userData.isEmpty()) {
            this.userDataMap = new LinkedHashMap(userData);
        }
        if (this == null) {
            $$$reportNull$$$0(17);
        }
        return this;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    @NotNull
    public SimpleFunctionDescriptor getOriginal() {
        SimpleFunctionDescriptor simpleFunctionDescriptor = (SimpleFunctionDescriptor) super.getOriginal();
        if (simpleFunctionDescriptor == null) {
            $$$reportNull$$$0(18);
        }
        return simpleFunctionDescriptor;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    @NotNull
    protected FunctionDescriptorImpl createSubstitutedCopy(@NotNull DeclarationDescriptor newOwner, @Nullable FunctionDescriptor original, @NotNull CallableMemberDescriptor.Kind kind, @Nullable Name newName, @NotNull Annotations annotations, @NotNull SourceElement source) {
        if (newOwner == null) {
            $$$reportNull$$$0(19);
        }
        if (kind == null) {
            $$$reportNull$$$0(20);
        }
        if (annotations == null) {
            $$$reportNull$$$0(21);
        }
        if (source == null) {
            $$$reportNull$$$0(22);
        }
        return new SimpleFunctionDescriptorImpl(newOwner, (SimpleFunctionDescriptor) original, annotations, newName != null ? newName : getName(), kind, source);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    @NotNull
    public SimpleFunctionDescriptor copy(DeclarationDescriptor newOwner, Modality modality, DescriptorVisibility visibility, CallableMemberDescriptor.Kind kind, boolean copyOverrides) {
        SimpleFunctionDescriptor simpleFunctionDescriptor = (SimpleFunctionDescriptor) super.copy(newOwner, modality, visibility, kind, copyOverrides);
        if (simpleFunctionDescriptor == null) {
            $$$reportNull$$$0(23);
        }
        return simpleFunctionDescriptor;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    @NotNull
    public FunctionDescriptor.CopyBuilder<? extends SimpleFunctionDescriptor> newCopyBuilder() {
        FunctionDescriptor.CopyBuilder copyBuilderNewCopyBuilder = super.newCopyBuilder();
        if (copyBuilderNewCopyBuilder == null) {
            $$$reportNull$$$0(24);
        }
        return copyBuilderNewCopyBuilder;
    }
}
