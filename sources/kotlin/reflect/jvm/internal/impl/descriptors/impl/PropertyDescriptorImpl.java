package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FieldDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ExtensionReceiver;
import kotlin.reflect.jvm.internal.impl.types.DescriptorSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyDescriptorImpl.class */
public class PropertyDescriptorImpl extends VariableDescriptorWithInitializerImpl implements PropertyDescriptor {
    private final Modality modality;
    private DescriptorVisibility visibility;
    private Collection<? extends PropertyDescriptor> overriddenProperties;
    private final PropertyDescriptor original;
    private final CallableMemberDescriptor.Kind kind;
    private final boolean lateInit;
    private final boolean isConst;
    private final boolean isExpect;
    private final boolean isActual;
    private final boolean isExternal;
    private final boolean isDelegated;
    private ReceiverParameterDescriptor dispatchReceiverParameter;
    private ReceiverParameterDescriptor extensionReceiverParameter;
    private List<TypeParameterDescriptor> typeParameters;
    private PropertyGetterDescriptorImpl getter;
    private PropertySetterDescriptor setter;
    private boolean setterProjectedOut;
    private FieldDescriptor backingField;
    private FieldDescriptor delegateField;

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
            case 15:
            case 16:
            case 22:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 35:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 33:
            case 34:
            case 36:
            case 37:
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
            case 15:
            case 16:
            case 22:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 35:
            default:
                i2 = 3;
                break;
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 33:
            case 34:
            case 36:
            case 37:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            case 7:
            default:
                objArr[0] = "containingDeclaration";
                break;
            case 1:
            case 8:
                objArr[0] = "annotations";
                break;
            case 2:
            case 9:
                objArr[0] = "modality";
                break;
            case 3:
            case 10:
            case 16:
                objArr[0] = "visibility";
                break;
            case 4:
            case 11:
                objArr[0] = "name";
                break;
            case 5:
            case 12:
            case 30:
                objArr[0] = "kind";
                break;
            case 6:
            case 13:
            case 32:
                objArr[0] = PackageDocumentBase.DCTags.source;
                break;
            case 14:
                objArr[0] = "outType";
                break;
            case 15:
                objArr[0] = "typeParameters";
                break;
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 33:
            case 34:
            case 36:
            case 37:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyDescriptorImpl";
                break;
            case 22:
                objArr[0] = "originalSubstitutor";
                break;
            case 24:
                objArr[0] = "copyConfiguration";
                break;
            case 25:
                objArr[0] = "substitutor";
                break;
            case 26:
                objArr[0] = "accessorDescriptor";
                break;
            case 27:
                objArr[0] = "newOwner";
                break;
            case 28:
                objArr[0] = "newModality";
                break;
            case 29:
                objArr[0] = "newVisibility";
                break;
            case 31:
                objArr[0] = "newName";
                break;
            case 35:
                objArr[0] = "overriddenDescriptors";
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
            case 15:
            case 16:
            case 22:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 35:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyDescriptorImpl";
                break;
            case 17:
                objArr[1] = "getTypeParameters";
                break;
            case 18:
                objArr[1] = "getReturnType";
                break;
            case 19:
                objArr[1] = "getModality";
                break;
            case 20:
                objArr[1] = "getVisibility";
                break;
            case 21:
                objArr[1] = "getAccessors";
                break;
            case 23:
                objArr[1] = "getSourceToUseForCopy";
                break;
            case 33:
                objArr[1] = "getOriginal";
                break;
            case 34:
                objArr[1] = "getKind";
                break;
            case 36:
                objArr[1] = "getOverriddenDescriptors";
                break;
            case 37:
                objArr[1] = "copy";
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
            default:
                objArr[2] = Constants.CONSTRUCTOR_NAME;
                break;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                objArr[2] = "create";
                break;
            case 14:
            case 15:
                objArr[2] = "setType";
                break;
            case 16:
                objArr[2] = "setVisibility";
                break;
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 33:
            case 34:
            case 36:
            case 37:
                break;
            case 22:
                objArr[2] = "substitute";
                break;
            case 24:
                objArr[2] = "doSubstitute";
                break;
            case 25:
            case 26:
                objArr[2] = "getSubstitutedInitialSignatureDescriptor";
                break;
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
                objArr[2] = "createSubstitutedCopy";
                break;
            case 35:
                objArr[2] = "setOverriddenDescriptors";
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
            case 15:
            case 16:
            case 22:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 35:
            default:
                throw new IllegalArgumentException(str2);
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 33:
            case 34:
            case 36:
            case 37:
                throw new IllegalStateException(str2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    protected PropertyDescriptorImpl(@NotNull DeclarationDescriptor containingDeclaration, @Nullable PropertyDescriptor original, @NotNull Annotations annotations, @NotNull Modality modality, @NotNull DescriptorVisibility visibility, boolean isVar, @NotNull Name name, @NotNull CallableMemberDescriptor.Kind kind, @NotNull SourceElement source, boolean lateInit, boolean isConst, boolean isExpect, boolean isActual, boolean isExternal, boolean isDelegated) {
        super(containingDeclaration, annotations, name, null, isVar, source);
        if (containingDeclaration == null) {
            $$$reportNull$$$0(0);
        }
        if (annotations == null) {
            $$$reportNull$$$0(1);
        }
        if (modality == null) {
            $$$reportNull$$$0(2);
        }
        if (visibility == null) {
            $$$reportNull$$$0(3);
        }
        if (name == null) {
            $$$reportNull$$$0(4);
        }
        if (kind == null) {
            $$$reportNull$$$0(5);
        }
        if (source == null) {
            $$$reportNull$$$0(6);
        }
        this.overriddenProperties = null;
        this.modality = modality;
        this.visibility = visibility;
        this.original = original == null ? this : original;
        this.kind = kind;
        this.lateInit = lateInit;
        this.isConst = isConst;
        this.isExpect = isExpect;
        this.isActual = isActual;
        this.isExternal = isExternal;
        this.isDelegated = isDelegated;
    }

    @NotNull
    public static PropertyDescriptorImpl create(@NotNull DeclarationDescriptor containingDeclaration, @NotNull Annotations annotations, @NotNull Modality modality, @NotNull DescriptorVisibility visibility, boolean isVar, @NotNull Name name, @NotNull CallableMemberDescriptor.Kind kind, @NotNull SourceElement source, boolean lateInit, boolean isConst, boolean isExpect, boolean isActual, boolean isExternal, boolean isDelegated) {
        if (containingDeclaration == null) {
            $$$reportNull$$$0(7);
        }
        if (annotations == null) {
            $$$reportNull$$$0(8);
        }
        if (modality == null) {
            $$$reportNull$$$0(9);
        }
        if (visibility == null) {
            $$$reportNull$$$0(10);
        }
        if (name == null) {
            $$$reportNull$$$0(11);
        }
        if (kind == null) {
            $$$reportNull$$$0(12);
        }
        if (source == null) {
            $$$reportNull$$$0(13);
        }
        return new PropertyDescriptorImpl(containingDeclaration, null, annotations, modality, visibility, isVar, name, kind, source, lateInit, isConst, isExpect, isActual, isExternal, isDelegated);
    }

    public void setType(@NotNull KotlinType outType, @NotNull List<? extends TypeParameterDescriptor> typeParameters, @Nullable ReceiverParameterDescriptor dispatchReceiverParameter, @Nullable ReceiverParameterDescriptor extensionReceiverParameter) {
        if (outType == null) {
            $$$reportNull$$$0(14);
        }
        if (typeParameters == null) {
            $$$reportNull$$$0(15);
        }
        setOutType(outType);
        this.typeParameters = new ArrayList(typeParameters);
        this.extensionReceiverParameter = extensionReceiverParameter;
        this.dispatchReceiverParameter = dispatchReceiverParameter;
    }

    public void initialize(@Nullable PropertyGetterDescriptorImpl getter, @Nullable PropertySetterDescriptor setter) {
        initialize(getter, setter, null, null);
    }

    public void initialize(@Nullable PropertyGetterDescriptorImpl getter, @Nullable PropertySetterDescriptor setter, @Nullable FieldDescriptor backingField, @Nullable FieldDescriptor delegateField) {
        this.getter = getter;
        this.setter = setter;
        this.backingField = backingField;
        this.delegateField = delegateField;
    }

    public void setSetterProjectedOut(boolean setterProjectedOut) {
        this.setterProjectedOut = setterProjectedOut;
    }

    public void setVisibility(@NotNull DescriptorVisibility visibility) {
        if (visibility == null) {
            $$$reportNull$$$0(16);
        }
        this.visibility = visibility;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    @NotNull
    public List<TypeParameterDescriptor> getTypeParameters() {
        List<TypeParameterDescriptor> parameters = this.typeParameters;
        if (parameters == null) {
            throw new IllegalStateException("typeParameters == null for " + toString());
        }
        if (parameters == null) {
            $$$reportNull$$$0(17);
        }
        return parameters;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    @Nullable
    public ReceiverParameterDescriptor getExtensionReceiverParameter() {
        return this.extensionReceiverParameter;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    @Nullable
    public ReceiverParameterDescriptor getDispatchReceiverParameter() {
        return this.dispatchReceiverParameter;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    @NotNull
    public KotlinType getReturnType() {
        KotlinType type = getType();
        if (type == null) {
            $$$reportNull$$$0(18);
        }
        return type;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    @NotNull
    public Modality getModality() {
        Modality modality = this.modality;
        if (modality == null) {
            $$$reportNull$$$0(19);
        }
        return modality;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    @NotNull
    public DescriptorVisibility getVisibility() {
        DescriptorVisibility descriptorVisibility = this.visibility;
        if (descriptorVisibility == null) {
            $$$reportNull$$$0(20);
        }
        return descriptorVisibility;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor
    @Nullable
    public PropertyGetterDescriptorImpl getGetter() {
        return this.getter;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor
    @Nullable
    public PropertySetterDescriptor getSetter() {
        return this.setter;
    }

    public boolean isSetterProjectedOut() {
        return this.setterProjectedOut;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor
    public boolean isLateInit() {
        return this.lateInit;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor
    public boolean isConst() {
        return this.isConst;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExternal() {
        return this.isExternal;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptorWithAccessors
    public boolean isDelegated() {
        return this.isDelegated;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor
    @NotNull
    public List<PropertyAccessorDescriptor> getAccessors() {
        List<PropertyAccessorDescriptor> result = new ArrayList<>(2);
        if (this.getter != null) {
            result.add(this.getter);
        }
        if (this.setter != null) {
            result.add(this.setter);
        }
        if (result == null) {
            $$$reportNull$$$0(21);
        }
        return result;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.Substitutable
    public PropertyDescriptor substitute(@NotNull TypeSubstitutor originalSubstitutor) {
        if (originalSubstitutor == null) {
            $$$reportNull$$$0(22);
        }
        if (originalSubstitutor.isEmpty()) {
            return this;
        }
        return newCopyBuilder().setSubstitution(originalSubstitutor.getSubstitution()).setOriginal(getOriginal()).build();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyDescriptorImpl$CopyConfiguration.class */
    public class CopyConfiguration {
        private DeclarationDescriptor owner;
        private Modality modality;
        private DescriptorVisibility visibility;
        private CallableMemberDescriptor.Kind kind;
        private ReceiverParameterDescriptor dispatchReceiverParameter;
        private Name name;
        private KotlinType returnType;
        private PropertyDescriptor original = null;
        private boolean preserveSourceElement = false;
        private TypeSubstitution substitution = TypeSubstitution.EMPTY;
        private boolean copyOverrides = true;
        private List<TypeParameterDescriptor> newTypeParameters = null;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str;
            int i2;
            switch (i) {
                case 0:
                case 4:
                case 6:
                case 8:
                case 10:
                case 12:
                case 15:
                case 18:
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
                case 1:
                case 2:
                case 3:
                case 5:
                case 7:
                case 9:
                case 11:
                case 13:
                case 14:
                case 16:
                case 17:
                case 19:
                    str = "@NotNull method %s.%s must not return null";
                    break;
            }
            switch (i) {
                case 0:
                case 4:
                case 6:
                case 8:
                case 10:
                case 12:
                case 15:
                case 18:
                default:
                    i2 = 3;
                    break;
                case 1:
                case 2:
                case 3:
                case 5:
                case 7:
                case 9:
                case 11:
                case 13:
                case 14:
                case 16:
                case 17:
                case 19:
                    i2 = 2;
                    break;
            }
            Object[] objArr = new Object[i2];
            switch (i) {
                case 0:
                default:
                    objArr[0] = "owner";
                    break;
                case 1:
                case 2:
                case 3:
                case 5:
                case 7:
                case 9:
                case 11:
                case 13:
                case 14:
                case 16:
                case 17:
                case 19:
                    objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyDescriptorImpl$CopyConfiguration";
                    break;
                case 4:
                    objArr[0] = "type";
                    break;
                case 6:
                    objArr[0] = "modality";
                    break;
                case 8:
                    objArr[0] = "visibility";
                    break;
                case 10:
                    objArr[0] = "kind";
                    break;
                case 12:
                    objArr[0] = "typeParameters";
                    break;
                case 15:
                    objArr[0] = "substitution";
                    break;
                case 18:
                    objArr[0] = "name";
                    break;
            }
            switch (i) {
                case 0:
                case 4:
                case 6:
                case 8:
                case 10:
                case 12:
                case 15:
                case 18:
                default:
                    objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyDescriptorImpl$CopyConfiguration";
                    break;
                case 1:
                    objArr[1] = "setOwner";
                    break;
                case 2:
                    objArr[1] = "setOriginal";
                    break;
                case 3:
                    objArr[1] = "setPreserveSourceElement";
                    break;
                case 5:
                    objArr[1] = "setReturnType";
                    break;
                case 7:
                    objArr[1] = "setModality";
                    break;
                case 9:
                    objArr[1] = "setVisibility";
                    break;
                case 11:
                    objArr[1] = "setKind";
                    break;
                case 13:
                    objArr[1] = "setTypeParameters";
                    break;
                case 14:
                    objArr[1] = "setDispatchReceiverParameter";
                    break;
                case 16:
                    objArr[1] = "setSubstitution";
                    break;
                case 17:
                    objArr[1] = "setCopyOverrides";
                    break;
                case 19:
                    objArr[1] = "setName";
                    break;
            }
            switch (i) {
                case 0:
                default:
                    objArr[2] = "setOwner";
                    break;
                case 1:
                case 2:
                case 3:
                case 5:
                case 7:
                case 9:
                case 11:
                case 13:
                case 14:
                case 16:
                case 17:
                case 19:
                    break;
                case 4:
                    objArr[2] = "setReturnType";
                    break;
                case 6:
                    objArr[2] = "setModality";
                    break;
                case 8:
                    objArr[2] = "setVisibility";
                    break;
                case 10:
                    objArr[2] = "setKind";
                    break;
                case 12:
                    objArr[2] = "setTypeParameters";
                    break;
                case 15:
                    objArr[2] = "setSubstitution";
                    break;
                case 18:
                    objArr[2] = "setName";
                    break;
            }
            String str2 = String.format(str, objArr);
            switch (i) {
                case 0:
                case 4:
                case 6:
                case 8:
                case 10:
                case 12:
                case 15:
                case 18:
                default:
                    throw new IllegalArgumentException(str2);
                case 1:
                case 2:
                case 3:
                case 5:
                case 7:
                case 9:
                case 11:
                case 13:
                case 14:
                case 16:
                case 17:
                case 19:
                    throw new IllegalStateException(str2);
            }
        }

        public CopyConfiguration() {
            this.owner = PropertyDescriptorImpl.this.getContainingDeclaration();
            this.modality = PropertyDescriptorImpl.this.getModality();
            this.visibility = PropertyDescriptorImpl.this.getVisibility();
            this.kind = PropertyDescriptorImpl.this.getKind();
            this.dispatchReceiverParameter = PropertyDescriptorImpl.this.dispatchReceiverParameter;
            this.name = PropertyDescriptorImpl.this.getName();
            this.returnType = PropertyDescriptorImpl.this.getType();
        }

        @NotNull
        public CopyConfiguration setOwner(@NotNull DeclarationDescriptor owner) {
            if (owner == null) {
                $$$reportNull$$$0(0);
            }
            this.owner = owner;
            if (this == null) {
                $$$reportNull$$$0(1);
            }
            return this;
        }

        @NotNull
        public CopyConfiguration setOriginal(@Nullable CallableMemberDescriptor original) {
            this.original = (PropertyDescriptor) original;
            if (this == null) {
                $$$reportNull$$$0(2);
            }
            return this;
        }

        @NotNull
        public CopyConfiguration setModality(@NotNull Modality modality) {
            if (modality == null) {
                $$$reportNull$$$0(6);
            }
            this.modality = modality;
            if (this == null) {
                $$$reportNull$$$0(7);
            }
            return this;
        }

        @NotNull
        public CopyConfiguration setVisibility(@NotNull DescriptorVisibility visibility) {
            if (visibility == null) {
                $$$reportNull$$$0(8);
            }
            this.visibility = visibility;
            if (this == null) {
                $$$reportNull$$$0(9);
            }
            return this;
        }

        @NotNull
        public CopyConfiguration setKind(@NotNull CallableMemberDescriptor.Kind kind) {
            if (kind == null) {
                $$$reportNull$$$0(10);
            }
            this.kind = kind;
            if (this == null) {
                $$$reportNull$$$0(11);
            }
            return this;
        }

        @NotNull
        public CopyConfiguration setSubstitution(@NotNull TypeSubstitution substitution) {
            if (substitution == null) {
                $$$reportNull$$$0(15);
            }
            this.substitution = substitution;
            if (this == null) {
                $$$reportNull$$$0(16);
            }
            return this;
        }

        @NotNull
        public CopyConfiguration setCopyOverrides(boolean copyOverrides) {
            this.copyOverrides = copyOverrides;
            if (this == null) {
                $$$reportNull$$$0(17);
            }
            return this;
        }

        @Nullable
        public PropertyDescriptor build() {
            return PropertyDescriptorImpl.this.doSubstitute(this);
        }

        PropertyGetterDescriptor getOriginalGetter() {
            if (this.original == null) {
                return null;
            }
            return this.original.getGetter();
        }

        PropertySetterDescriptor getOriginalSetter() {
            if (this.original == null) {
                return null;
            }
            return this.original.getSetter();
        }
    }

    @NotNull
    public CopyConfiguration newCopyBuilder() {
        return new CopyConfiguration();
    }

    @NotNull
    private SourceElement getSourceToUseForCopy(boolean preserveSource, @Nullable PropertyDescriptor original) {
        SourceElement source;
        if (preserveSource) {
            source = (original != null ? original : getOriginal()).getSource();
        } else {
            source = SourceElement.NO_SOURCE;
        }
        if (source == null) {
            $$$reportNull$$$0(23);
        }
        return source;
    }

    @Nullable
    protected PropertyDescriptor doSubstitute(@NotNull CopyConfiguration copyConfiguration) {
        ReceiverParameterDescriptor substitutedDispatchReceiver;
        ReceiverParameterDescriptor substitutedExtensionReceiver;
        if (copyConfiguration == null) {
            $$$reportNull$$$0(24);
        }
        PropertyDescriptorImpl substitutedDescriptor = createSubstitutedCopy(copyConfiguration.owner, copyConfiguration.modality, copyConfiguration.visibility, copyConfiguration.original, copyConfiguration.kind, copyConfiguration.name, getSourceToUseForCopy(copyConfiguration.preserveSourceElement, copyConfiguration.original));
        List<TypeParameterDescriptor> originalTypeParameters = copyConfiguration.newTypeParameters == null ? getTypeParameters() : copyConfiguration.newTypeParameters;
        List<TypeParameterDescriptor> substitutedTypeParameters = new ArrayList<>(originalTypeParameters.size());
        TypeSubstitutor substitutor = DescriptorSubstitutor.substituteTypeParameters(originalTypeParameters, copyConfiguration.substitution, substitutedDescriptor, substitutedTypeParameters);
        KotlinType originalOutType = copyConfiguration.returnType;
        KotlinType outType = substitutor.substitute(originalOutType, Variance.OUT_VARIANCE);
        if (outType != null) {
            ReceiverParameterDescriptor dispatchReceiver = copyConfiguration.dispatchReceiverParameter;
            if (dispatchReceiver != null) {
                substitutedDispatchReceiver = dispatchReceiver.substitute(substitutor);
                if (substitutedDispatchReceiver == null) {
                    return null;
                }
            } else {
                substitutedDispatchReceiver = null;
            }
            if (this.extensionReceiverParameter != null) {
                KotlinType substitutedReceiverType = substitutor.substitute(this.extensionReceiverParameter.getType(), Variance.IN_VARIANCE);
                if (substitutedReceiverType == null) {
                    return null;
                }
                substitutedExtensionReceiver = new ReceiverParameterDescriptorImpl(substitutedDescriptor, new ExtensionReceiver(substitutedDescriptor, substitutedReceiverType, this.extensionReceiverParameter.getValue()), this.extensionReceiverParameter.getAnnotations());
            } else {
                substitutedExtensionReceiver = null;
            }
            substitutedDescriptor.setType(outType, substitutedTypeParameters, substitutedDispatchReceiver, substitutedExtensionReceiver);
            PropertyGetterDescriptorImpl newGetter = this.getter == null ? null : new PropertyGetterDescriptorImpl(substitutedDescriptor, this.getter.getAnnotations(), copyConfiguration.modality, normalizeVisibility(this.getter.getVisibility(), copyConfiguration.kind), this.getter.isDefault(), this.getter.isExternal(), this.getter.isInline(), copyConfiguration.kind, copyConfiguration.getOriginalGetter(), SourceElement.NO_SOURCE);
            if (newGetter != null) {
                KotlinType returnType = this.getter.getReturnType();
                newGetter.setInitialSignatureDescriptor(getSubstitutedInitialSignatureDescriptor(substitutor, this.getter));
                newGetter.initialize(returnType != null ? substitutor.substitute(returnType, Variance.OUT_VARIANCE) : null);
            }
            PropertySetterDescriptorImpl newSetter = this.setter == null ? null : new PropertySetterDescriptorImpl(substitutedDescriptor, this.setter.getAnnotations(), copyConfiguration.modality, normalizeVisibility(this.setter.getVisibility(), copyConfiguration.kind), this.setter.isDefault(), this.setter.isExternal(), this.setter.isInline(), copyConfiguration.kind, copyConfiguration.getOriginalSetter(), SourceElement.NO_SOURCE);
            if (newSetter != null) {
                List<ValueParameterDescriptor> substitutedValueParameters = FunctionDescriptorImpl.getSubstitutedValueParameters(newSetter, this.setter.getValueParameters(), substitutor, false, false, null);
                if (substitutedValueParameters == null) {
                    substitutedDescriptor.setSetterProjectedOut(true);
                    substitutedValueParameters = Collections.singletonList(PropertySetterDescriptorImpl.createSetterParameter(newSetter, DescriptorUtilsKt.getBuiltIns(copyConfiguration.owner).getNothingType(), this.setter.getValueParameters().get(0).getAnnotations()));
                }
                if (substitutedValueParameters.size() != 1) {
                    throw new IllegalStateException();
                }
                newSetter.setInitialSignatureDescriptor(getSubstitutedInitialSignatureDescriptor(substitutor, this.setter));
                newSetter.initialize(substitutedValueParameters.get(0));
            }
            substitutedDescriptor.initialize(newGetter, newSetter, this.backingField == null ? null : new FieldDescriptorImpl(this.backingField.getAnnotations(), substitutedDescriptor), this.delegateField == null ? null : new FieldDescriptorImpl(this.delegateField.getAnnotations(), substitutedDescriptor));
            if (copyConfiguration.copyOverrides) {
                Collection<CallableMemberDescriptor> overridden = SmartSet.create();
                for (PropertyDescriptor propertyDescriptor : getOverriddenDescriptors()) {
                    overridden.add(propertyDescriptor.substitute(substitutor));
                }
                substitutedDescriptor.setOverriddenDescriptors(overridden);
            }
            if (isConst() && this.compileTimeInitializer != null) {
                substitutedDescriptor.setCompileTimeInitializer(this.compileTimeInitializer);
            }
            return substitutedDescriptor;
        }
        return null;
    }

    private static DescriptorVisibility normalizeVisibility(DescriptorVisibility prev, CallableMemberDescriptor.Kind kind) {
        if (kind == CallableMemberDescriptor.Kind.FAKE_OVERRIDE && DescriptorVisibilities.isPrivate(prev.normalize())) {
            return DescriptorVisibilities.INVISIBLE_FAKE;
        }
        return prev;
    }

    private static FunctionDescriptor getSubstitutedInitialSignatureDescriptor(@NotNull TypeSubstitutor substitutor, @NotNull PropertyAccessorDescriptor accessorDescriptor) {
        if (substitutor == null) {
            $$$reportNull$$$0(25);
        }
        if (accessorDescriptor == null) {
            $$$reportNull$$$0(26);
        }
        if (accessorDescriptor.getInitialSignatureDescriptor() != null) {
            return accessorDescriptor.getInitialSignatureDescriptor().substitute(substitutor);
        }
        return null;
    }

    @NotNull
    protected PropertyDescriptorImpl createSubstitutedCopy(@NotNull DeclarationDescriptor newOwner, @NotNull Modality newModality, @NotNull DescriptorVisibility newVisibility, @Nullable PropertyDescriptor original, @NotNull CallableMemberDescriptor.Kind kind, @NotNull Name newName, @NotNull SourceElement source) {
        if (newOwner == null) {
            $$$reportNull$$$0(27);
        }
        if (newModality == null) {
            $$$reportNull$$$0(28);
        }
        if (newVisibility == null) {
            $$$reportNull$$$0(29);
        }
        if (kind == null) {
            $$$reportNull$$$0(30);
        }
        if (newName == null) {
            $$$reportNull$$$0(31);
        }
        if (source == null) {
            $$$reportNull$$$0(32);
        }
        return new PropertyDescriptorImpl(newOwner, original, getAnnotations(), newModality, newVisibility, isVar(), newName, kind, source, isLateInit(), isConst(), isExpect(), isActual(), isExternal(), isDelegated());
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> visitor, D data) {
        return visitor.visitPropertyDescriptor(this, data);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    @NotNull
    public PropertyDescriptor getOriginal() {
        PropertyDescriptor original = this.original == this ? this : this.original.getOriginal();
        if (original == null) {
            $$$reportNull$$$0(33);
        }
        return original;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    @NotNull
    public CallableMemberDescriptor.Kind getKind() {
        CallableMemberDescriptor.Kind kind = this.kind;
        if (kind == null) {
            $$$reportNull$$$0(34);
        }
        return kind;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExpect() {
        return this.isExpect;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isActual() {
        return this.isActual;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor
    @Nullable
    public FieldDescriptor getBackingField() {
        return this.backingField;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor
    @Nullable
    public FieldDescriptor getDelegateField() {
        return this.delegateField;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public void setOverriddenDescriptors(@NotNull Collection<? extends CallableMemberDescriptor> collection) {
        if (collection == 0) {
            $$$reportNull$$$0(35);
        }
        this.overriddenProperties = collection;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    @NotNull
    public Collection<? extends PropertyDescriptor> getOverriddenDescriptors() {
        Collection<? extends PropertyDescriptor> collectionEmptyList = this.overriddenProperties != null ? this.overriddenProperties : Collections.emptyList();
        if (collectionEmptyList == null) {
            $$$reportNull$$$0(36);
        }
        return collectionEmptyList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    @NotNull
    public PropertyDescriptor copy(DeclarationDescriptor newOwner, Modality modality, DescriptorVisibility visibility, CallableMemberDescriptor.Kind kind, boolean copyOverrides) {
        PropertyDescriptor propertyDescriptorBuild = newCopyBuilder().setOwner(newOwner).setOriginal(null).setModality(modality).setVisibility(visibility).setKind(kind).setCopyOverrides(copyOverrides).build();
        if (propertyDescriptorBuild == null) {
            $$$reportNull$$$0(37);
        }
        return propertyDescriptorBuild;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    @Nullable
    public <V> V getUserData(CallableDescriptor.UserDataKey<V> key) {
        return null;
    }
}
