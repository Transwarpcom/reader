package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/impl/ClassConstructorDescriptorImpl.class */
public class ClassConstructorDescriptorImpl extends FunctionDescriptorImpl implements ClassConstructorDescriptor {
    protected final boolean isPrimary;
    private static final Name NAME;
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
            case 12:
            case 13:
            case 14:
            case 18:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 15:
            case 16:
            case 17:
            case 19:
            case 25:
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
            case 13:
            case 14:
            case 18:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            default:
                i2 = 3;
                break;
            case 15:
            case 16:
            case 17:
            case 19:
            case 25:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            case 4:
            case 7:
            default:
                objArr[0] = "containingDeclaration";
                break;
            case 1:
            case 5:
            case 8:
            case 23:
                objArr[0] = "annotations";
                break;
            case 2:
            case 22:
                objArr[0] = "kind";
                break;
            case 3:
            case 6:
            case 9:
            case 24:
                objArr[0] = PackageDocumentBase.DCTags.source;
                break;
            case 10:
            case 13:
                objArr[0] = "unsubstitutedValueParameters";
                break;
            case 11:
            case 14:
                objArr[0] = "visibility";
                break;
            case 12:
                objArr[0] = "typeParameterDescriptors";
                break;
            case 15:
            case 16:
            case 17:
            case 19:
            case 25:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ClassConstructorDescriptorImpl";
                break;
            case 18:
                objArr[0] = "originalSubstitutor";
                break;
            case 20:
                objArr[0] = "overriddenDescriptors";
                break;
            case 21:
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
            case 13:
            case 14:
            case 18:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ClassConstructorDescriptorImpl";
                break;
            case 15:
                objArr[1] = "getContainingDeclaration";
                break;
            case 16:
                objArr[1] = "getConstructedClass";
                break;
            case 17:
                objArr[1] = "getOriginal";
                break;
            case 19:
                objArr[1] = "getOverriddenDescriptors";
                break;
            case 25:
                objArr[1] = "copy";
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
                objArr[2] = "create";
                break;
            case 7:
            case 8:
            case 9:
                objArr[2] = "createSynthesized";
                break;
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                objArr[2] = "initialize";
                break;
            case 15:
            case 16:
            case 17:
            case 19:
            case 25:
                break;
            case 18:
                objArr[2] = "substitute";
                break;
            case 20:
                objArr[2] = "setOverriddenDescriptors";
                break;
            case 21:
            case 22:
            case 23:
            case 24:
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
            case 13:
            case 14:
            case 18:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            default:
                throw new IllegalArgumentException(str2);
            case 15:
            case 16:
            case 17:
            case 19:
            case 25:
                throw new IllegalStateException(str2);
        }
    }

    static {
        $assertionsDisabled = !ClassConstructorDescriptorImpl.class.desiredAssertionStatus();
        NAME = Name.special(Constants.CONSTRUCTOR_NAME);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    protected ClassConstructorDescriptorImpl(@NotNull ClassDescriptor containingDeclaration, @Nullable ConstructorDescriptor original, @NotNull Annotations annotations, boolean isPrimary, @NotNull CallableMemberDescriptor.Kind kind, @NotNull SourceElement source) {
        super(containingDeclaration, original, annotations, NAME, kind, source);
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
        this.isPrimary = isPrimary;
    }

    @NotNull
    public static ClassConstructorDescriptorImpl create(@NotNull ClassDescriptor containingDeclaration, @NotNull Annotations annotations, boolean isPrimary, @NotNull SourceElement source) {
        if (containingDeclaration == null) {
            $$$reportNull$$$0(4);
        }
        if (annotations == null) {
            $$$reportNull$$$0(5);
        }
        if (source == null) {
            $$$reportNull$$$0(6);
        }
        return new ClassConstructorDescriptorImpl(containingDeclaration, null, annotations, isPrimary, CallableMemberDescriptor.Kind.DECLARATION, source);
    }

    public ClassConstructorDescriptorImpl initialize(@NotNull List<ValueParameterDescriptor> unsubstitutedValueParameters, @NotNull DescriptorVisibility visibility, @NotNull List<TypeParameterDescriptor> typeParameterDescriptors) {
        if (unsubstitutedValueParameters == null) {
            $$$reportNull$$$0(10);
        }
        if (visibility == null) {
            $$$reportNull$$$0(11);
        }
        if (typeParameterDescriptors == null) {
            $$$reportNull$$$0(12);
        }
        super.initialize(null, calculateDispatchReceiverParameter(), typeParameterDescriptors, unsubstitutedValueParameters, null, Modality.FINAL, visibility);
        return this;
    }

    public ClassConstructorDescriptorImpl initialize(@NotNull List<ValueParameterDescriptor> unsubstitutedValueParameters, @NotNull DescriptorVisibility visibility) {
        if (unsubstitutedValueParameters == null) {
            $$$reportNull$$$0(13);
        }
        if (visibility == null) {
            $$$reportNull$$$0(14);
        }
        initialize(unsubstitutedValueParameters, visibility, getContainingDeclaration().getDeclaredTypeParameters());
        return this;
    }

    @Nullable
    public ReceiverParameterDescriptor calculateDispatchReceiverParameter() {
        ClassDescriptor classDescriptor = getContainingDeclaration();
        if (classDescriptor.isInner()) {
            DeclarationDescriptor classContainer = classDescriptor.getContainingDeclaration();
            if (classContainer instanceof ClassDescriptor) {
                return ((ClassDescriptor) classContainer).getThisAsReceiverParameter();
            }
            return null;
        }
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    @NotNull
    public ClassDescriptor getContainingDeclaration() {
        ClassDescriptor classDescriptor = (ClassDescriptor) super.getContainingDeclaration();
        if (classDescriptor == null) {
            $$$reportNull$$$0(15);
        }
        return classDescriptor;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor
    @NotNull
    public ClassDescriptor getConstructedClass() {
        ClassDescriptor containingDeclaration = getContainingDeclaration();
        if (containingDeclaration == null) {
            $$$reportNull$$$0(16);
        }
        return containingDeclaration;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    @NotNull
    public ClassConstructorDescriptor getOriginal() {
        ClassConstructorDescriptor classConstructorDescriptor = (ClassConstructorDescriptor) super.getOriginal();
        if (classConstructorDescriptor == null) {
            $$$reportNull$$$0(17);
        }
        return classConstructorDescriptor;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.Substitutable
    @Nullable
    public ClassConstructorDescriptor substitute(@NotNull TypeSubstitutor originalSubstitutor) {
        if (originalSubstitutor == null) {
            $$$reportNull$$$0(18);
        }
        return (ClassConstructorDescriptor) super.substitute(originalSubstitutor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> visitor, D data) {
        return visitor.visitConstructorDescriptor(this, data);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor
    public boolean isPrimary() {
        return this.isPrimary;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    @NotNull
    public Collection<? extends FunctionDescriptor> getOverriddenDescriptors() {
        Set setEmptySet = Collections.emptySet();
        if (setEmptySet == null) {
            $$$reportNull$$$0(19);
        }
        return setEmptySet;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public void setOverriddenDescriptors(@NotNull Collection<? extends CallableMemberDescriptor> overriddenDescriptors) {
        if (overriddenDescriptors == null) {
            $$$reportNull$$$0(20);
        }
        if (!$assertionsDisabled && !overriddenDescriptors.isEmpty()) {
            throw new AssertionError("Constructors cannot override anything");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    @NotNull
    public ClassConstructorDescriptorImpl createSubstitutedCopy(@NotNull DeclarationDescriptor newOwner, @Nullable FunctionDescriptor original, @NotNull CallableMemberDescriptor.Kind kind, @Nullable Name newName, @NotNull Annotations annotations, @NotNull SourceElement source) {
        if (newOwner == null) {
            $$$reportNull$$$0(21);
        }
        if (kind == null) {
            $$$reportNull$$$0(22);
        }
        if (annotations == null) {
            $$$reportNull$$$0(23);
        }
        if (source == null) {
            $$$reportNull$$$0(24);
        }
        if (kind != CallableMemberDescriptor.Kind.DECLARATION && kind != CallableMemberDescriptor.Kind.SYNTHESIZED) {
            throw new IllegalStateException("Attempt at creating a constructor that is not a declaration: \ncopy from: " + this + "\nnewOwner: " + newOwner + "\nkind: " + kind);
        }
        if ($assertionsDisabled || newName == null) {
            return new ClassConstructorDescriptorImpl((ClassDescriptor) newOwner, this, annotations, this.isPrimary, CallableMemberDescriptor.Kind.DECLARATION, source);
        }
        throw new AssertionError("Attempt to rename constructor: " + this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    @NotNull
    public ClassConstructorDescriptor copy(DeclarationDescriptor newOwner, Modality modality, DescriptorVisibility visibility, CallableMemberDescriptor.Kind kind, boolean copyOverrides) {
        ClassConstructorDescriptor classConstructorDescriptor = (ClassConstructorDescriptor) super.copy(newOwner, modality, visibility, kind, copyOverrides);
        if (classConstructorDescriptor == null) {
            $$$reportNull$$$0(25);
        }
        return classConstructorDescriptor;
    }
}
