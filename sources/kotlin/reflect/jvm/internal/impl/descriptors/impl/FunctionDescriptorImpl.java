package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ExtensionReceiver;
import kotlin.reflect.jvm.internal.impl.types.DescriptorSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/impl/FunctionDescriptorImpl.class */
public abstract class FunctionDescriptorImpl extends DeclarationDescriptorNonRootImpl implements FunctionDescriptor {
    private List<TypeParameterDescriptor> typeParameters;
    private List<ValueParameterDescriptor> unsubstitutedValueParameters;
    private KotlinType unsubstitutedReturnType;
    private ReceiverParameterDescriptor extensionReceiverParameter;
    private ReceiverParameterDescriptor dispatchReceiverParameter;
    private Modality modality;
    private DescriptorVisibility visibility;
    private boolean isOperator;
    private boolean isInfix;
    private boolean isExternal;
    private boolean isInline;
    private boolean isTailrec;
    private boolean isExpect;
    private boolean isActual;
    private boolean isHiddenToOvercomeSignatureClash;
    private boolean isHiddenForResolutionEverywhereBesideSupercalls;
    private boolean isSuspend;
    private boolean hasStableParameterNames;
    private boolean hasSynthesizedParameterNames;
    private Collection<? extends FunctionDescriptor> overriddenFunctions;
    private volatile Function0<Collection<FunctionDescriptor>> lazyOverriddenFunctionsTask;
    private final FunctionDescriptor original;
    private final CallableMemberDescriptor.Kind kind;

    @Nullable
    private FunctionDescriptor initialSignatureDescriptor;
    protected Map<CallableDescriptor.UserDataKey<?>, Object> userDataMap;

    @NotNull
    protected abstract FunctionDescriptorImpl createSubstitutedCopy(@NotNull DeclarationDescriptor declarationDescriptor, @Nullable FunctionDescriptor functionDescriptor, @NotNull CallableMemberDescriptor.Kind kind, @Nullable Name name, @NotNull Annotations annotations, @NotNull SourceElement sourceElement);

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
            case 9:
            case 10:
            case 11:
            case 15:
            case 20:
            case 22:
            case 23:
            case 26:
            case 27:
            case 28:
            case 29:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 8:
            case 12:
            case 13:
            case 14:
            case 16:
            case 17:
            case 18:
            case 19:
            case 21:
            case 24:
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
            case 9:
            case 10:
            case 11:
            case 15:
            case 20:
            case 22:
            case 23:
            case 26:
            case 27:
            case 28:
            case 29:
            default:
                i2 = 3;
                break;
            case 8:
            case 12:
            case 13:
            case 14:
            case 16:
            case 17:
            case 18:
            case 19:
            case 21:
            case 24:
            case 25:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            default:
                objArr[0] = "containingDeclaration";
                break;
            case 1:
                objArr[0] = "annotations";
                break;
            case 2:
                objArr[0] = "name";
                break;
            case 3:
                objArr[0] = "kind";
                break;
            case 4:
                objArr[0] = PackageDocumentBase.DCTags.source;
                break;
            case 5:
                objArr[0] = "typeParameters";
                break;
            case 6:
            case 26:
            case 28:
                objArr[0] = "unsubstitutedValueParameters";
                break;
            case 7:
            case 9:
                objArr[0] = "visibility";
                break;
            case 8:
            case 12:
            case 13:
            case 14:
            case 16:
            case 17:
            case 18:
            case 19:
            case 21:
            case 24:
            case 25:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/FunctionDescriptorImpl";
                break;
            case 10:
                objArr[0] = "unsubstitutedReturnType";
                break;
            case 11:
                objArr[0] = "extensionReceiverParameter";
                break;
            case 15:
                objArr[0] = "overriddenDescriptors";
                break;
            case 20:
                objArr[0] = "originalSubstitutor";
                break;
            case 22:
            case 27:
            case 29:
                objArr[0] = "substitutor";
                break;
            case 23:
                objArr[0] = "configuration";
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
            case 9:
            case 10:
            case 11:
            case 15:
            case 20:
            case 22:
            case 23:
            case 26:
            case 27:
            case 28:
            case 29:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/FunctionDescriptorImpl";
                break;
            case 8:
                objArr[1] = "initialize";
                break;
            case 12:
                objArr[1] = "getOverriddenDescriptors";
                break;
            case 13:
                objArr[1] = "getModality";
                break;
            case 14:
                objArr[1] = "getVisibility";
                break;
            case 16:
                objArr[1] = "getTypeParameters";
                break;
            case 17:
                objArr[1] = "getValueParameters";
                break;
            case 18:
                objArr[1] = "getOriginal";
                break;
            case 19:
                objArr[1] = "getKind";
                break;
            case 21:
                objArr[1] = "newCopyBuilder";
                break;
            case 24:
                objArr[1] = "copy";
                break;
            case 25:
                objArr[1] = "getSourceToUseForCopy";
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
                objArr[2] = "initialize";
                break;
            case 8:
            case 12:
            case 13:
            case 14:
            case 16:
            case 17:
            case 18:
            case 19:
            case 21:
            case 24:
            case 25:
                break;
            case 9:
                objArr[2] = "setVisibility";
                break;
            case 10:
                objArr[2] = "setReturnType";
                break;
            case 11:
                objArr[2] = "setExtensionReceiverParameter";
                break;
            case 15:
                objArr[2] = "setOverriddenDescriptors";
                break;
            case 20:
                objArr[2] = "substitute";
                break;
            case 22:
                objArr[2] = "newCopyBuilder";
                break;
            case 23:
                objArr[2] = "doSubstitute";
                break;
            case 26:
            case 27:
            case 28:
            case 29:
                objArr[2] = "getSubstitutedValueParameters";
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
            case 9:
            case 10:
            case 11:
            case 15:
            case 20:
            case 22:
            case 23:
            case 26:
            case 27:
            case 28:
            case 29:
            default:
                throw new IllegalArgumentException(str2);
            case 8:
            case 12:
            case 13:
            case 14:
            case 16:
            case 17:
            case 18:
            case 19:
            case 21:
            case 24:
            case 25:
                throw new IllegalStateException(str2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    protected FunctionDescriptorImpl(@NotNull DeclarationDescriptor containingDeclaration, @Nullable FunctionDescriptor original, @NotNull Annotations annotations, @NotNull Name name, @NotNull CallableMemberDescriptor.Kind kind, @NotNull SourceElement source) {
        super(containingDeclaration, annotations, name, source);
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
        this.visibility = DescriptorVisibilities.UNKNOWN;
        this.isOperator = false;
        this.isInfix = false;
        this.isExternal = false;
        this.isInline = false;
        this.isTailrec = false;
        this.isExpect = false;
        this.isActual = false;
        this.isHiddenToOvercomeSignatureClash = false;
        this.isHiddenForResolutionEverywhereBesideSupercalls = false;
        this.isSuspend = false;
        this.hasStableParameterNames = true;
        this.hasSynthesizedParameterNames = false;
        this.overriddenFunctions = null;
        this.lazyOverriddenFunctionsTask = null;
        this.initialSignatureDescriptor = null;
        this.userDataMap = null;
        this.original = original == null ? this : original;
        this.kind = kind;
    }

    @NotNull
    public FunctionDescriptorImpl initialize(@Nullable ReceiverParameterDescriptor extensionReceiverParameter, @Nullable ReceiverParameterDescriptor dispatchReceiverParameter, @NotNull List<? extends TypeParameterDescriptor> typeParameters, @NotNull List<ValueParameterDescriptor> unsubstitutedValueParameters, @Nullable KotlinType unsubstitutedReturnType, @Nullable Modality modality, @NotNull DescriptorVisibility visibility) {
        if (typeParameters == null) {
            $$$reportNull$$$0(5);
        }
        if (unsubstitutedValueParameters == null) {
            $$$reportNull$$$0(6);
        }
        if (visibility == null) {
            $$$reportNull$$$0(7);
        }
        this.typeParameters = CollectionsKt.toList(typeParameters);
        this.unsubstitutedValueParameters = CollectionsKt.toList(unsubstitutedValueParameters);
        this.unsubstitutedReturnType = unsubstitutedReturnType;
        this.modality = modality;
        this.visibility = visibility;
        this.extensionReceiverParameter = extensionReceiverParameter;
        this.dispatchReceiverParameter = dispatchReceiverParameter;
        for (int i = 0; i < typeParameters.size(); i++) {
            TypeParameterDescriptor typeParameterDescriptor = typeParameters.get(i);
            if (typeParameterDescriptor.getIndex() != i) {
                throw new IllegalStateException(typeParameterDescriptor + " index is " + typeParameterDescriptor.getIndex() + " but position is " + i);
            }
        }
        for (int i2 = 0; i2 < unsubstitutedValueParameters.size(); i2++) {
            ValueParameterDescriptor valueParameterDescriptor = unsubstitutedValueParameters.get(i2);
            if (valueParameterDescriptor.getIndex() != i2 + 0) {
                throw new IllegalStateException(valueParameterDescriptor + "index is " + valueParameterDescriptor.getIndex() + " but position is " + i2);
            }
        }
        if (this == null) {
            $$$reportNull$$$0(8);
        }
        return this;
    }

    public void setVisibility(@NotNull DescriptorVisibility visibility) {
        if (visibility == null) {
            $$$reportNull$$$0(9);
        }
        this.visibility = visibility;
    }

    public void setOperator(boolean isOperator) {
        this.isOperator = isOperator;
    }

    public void setInfix(boolean isInfix) {
        this.isInfix = isInfix;
    }

    public void setExternal(boolean isExternal) {
        this.isExternal = isExternal;
    }

    public void setInline(boolean isInline) {
        this.isInline = isInline;
    }

    public void setTailrec(boolean isTailrec) {
        this.isTailrec = isTailrec;
    }

    public void setExpect(boolean isExpect) {
        this.isExpect = isExpect;
    }

    public void setActual(boolean isActual) {
        this.isActual = isActual;
    }

    private void setHiddenToOvercomeSignatureClash(boolean hiddenToOvercomeSignatureClash) {
        this.isHiddenToOvercomeSignatureClash = hiddenToOvercomeSignatureClash;
    }

    private void setHiddenForResolutionEverywhereBesideSupercalls(boolean hiddenForResolutionEverywhereBesideSupercalls) {
        this.isHiddenForResolutionEverywhereBesideSupercalls = hiddenForResolutionEverywhereBesideSupercalls;
    }

    public void setSuspend(boolean suspend) {
        this.isSuspend = suspend;
    }

    public void setReturnType(@NotNull KotlinType unsubstitutedReturnType) {
        if (unsubstitutedReturnType == null) {
            $$$reportNull$$$0(10);
        }
        if (this.unsubstitutedReturnType != null) {
        }
        this.unsubstitutedReturnType = unsubstitutedReturnType;
    }

    public void setHasStableParameterNames(boolean hasStableParameterNames) {
        this.hasStableParameterNames = hasStableParameterNames;
    }

    public void setHasSynthesizedParameterNames(boolean hasSynthesizedParameterNames) {
        this.hasSynthesizedParameterNames = hasSynthesizedParameterNames;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    @Nullable
    public ReceiverParameterDescriptor getExtensionReceiverParameter() {
        return this.extensionReceiverParameter;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    @Nullable
    public ReceiverParameterDescriptor getDispatchReceiverParameter() {
        return this.dispatchReceiverParameter;
    }

    @NotNull
    public Collection<? extends FunctionDescriptor> getOverriddenDescriptors() {
        performOverriddenLazyCalculationIfNeeded();
        Collection<? extends FunctionDescriptor> collectionEmptyList = this.overriddenFunctions != null ? this.overriddenFunctions : Collections.emptyList();
        if (collectionEmptyList == null) {
            $$$reportNull$$$0(12);
        }
        return collectionEmptyList;
    }

    private void performOverriddenLazyCalculationIfNeeded() {
        Function0<Collection<FunctionDescriptor>> overriddenTask = this.lazyOverriddenFunctionsTask;
        if (overriddenTask != null) {
            this.overriddenFunctions = overriddenTask.invoke();
            this.lazyOverriddenFunctionsTask = null;
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    @NotNull
    public Modality getModality() {
        Modality modality = this.modality;
        if (modality == null) {
            $$$reportNull$$$0(13);
        }
        return modality;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    @NotNull
    public DescriptorVisibility getVisibility() {
        DescriptorVisibility descriptorVisibility = this.visibility;
        if (descriptorVisibility == null) {
            $$$reportNull$$$0(14);
        }
        return descriptorVisibility;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isOperator() {
        if (this.isOperator) {
            return true;
        }
        for (FunctionDescriptor descriptor : getOriginal().getOverriddenDescriptors()) {
            if (descriptor.isOperator()) {
                return true;
            }
        }
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isInfix() {
        if (this.isInfix) {
            return true;
        }
        for (FunctionDescriptor descriptor : getOriginal().getOverriddenDescriptors()) {
            if (descriptor.isInfix()) {
                return true;
            }
        }
        return false;
    }

    public boolean isExternal() {
        return this.isExternal;
    }

    public boolean isInline() {
        return this.isInline;
    }

    public boolean isTailrec() {
        return this.isTailrec;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isSuspend() {
        return this.isSuspend;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExpect() {
        return this.isExpect;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isActual() {
        return this.isActual;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public <V> V getUserData(CallableDescriptor.UserDataKey<V> userDataKey) {
        if (this.userDataMap == null) {
            return null;
        }
        return (V) this.userDataMap.get(userDataKey);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isHiddenToOvercomeSignatureClash() {
        return this.isHiddenToOvercomeSignatureClash;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setOverriddenDescriptors(@NotNull Collection<? extends CallableMemberDescriptor> collection) {
        if (collection == 0) {
            $$$reportNull$$$0(15);
        }
        this.overriddenFunctions = collection;
        for (FunctionDescriptor function : this.overriddenFunctions) {
            if (function.isHiddenForResolutionEverywhereBesideSupercalls()) {
                this.isHiddenForResolutionEverywhereBesideSupercalls = true;
                return;
            }
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    @NotNull
    public List<TypeParameterDescriptor> getTypeParameters() {
        List<TypeParameterDescriptor> parameters = this.typeParameters;
        if (parameters == null) {
            throw new IllegalStateException("typeParameters == null for " + this);
        }
        if (parameters == null) {
            $$$reportNull$$$0(16);
        }
        return parameters;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    @NotNull
    public List<ValueParameterDescriptor> getValueParameters() {
        List<ValueParameterDescriptor> list = this.unsubstitutedValueParameters;
        if (list == null) {
            $$$reportNull$$$0(17);
        }
        return list;
    }

    public boolean hasStableParameterNames() {
        return this.hasStableParameterNames;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public boolean hasSynthesizedParameterNames() {
        return this.hasSynthesizedParameterNames;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public KotlinType getReturnType() {
        return this.unsubstitutedReturnType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    @NotNull
    public FunctionDescriptor getOriginal() {
        FunctionDescriptor original = this.original == this ? this : this.original.getOriginal();
        if (original == null) {
            $$$reportNull$$$0(18);
        }
        return original;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    @NotNull
    public CallableMemberDescriptor.Kind getKind() {
        CallableMemberDescriptor.Kind kind = this.kind;
        if (kind == null) {
            $$$reportNull$$$0(19);
        }
        return kind;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.Substitutable
    public FunctionDescriptor substitute(@NotNull TypeSubstitutor originalSubstitutor) {
        if (originalSubstitutor == null) {
            $$$reportNull$$$0(20);
        }
        if (originalSubstitutor.isEmpty()) {
            return this;
        }
        return newCopyBuilder(originalSubstitutor).setOriginal((CallableMemberDescriptor) getOriginal()).setPreserveSourceElement().setJustForTypeSubstitution(true).build();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isHiddenForResolutionEverywhereBesideSupercalls() {
        return this.isHiddenForResolutionEverywhereBesideSupercalls;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/impl/FunctionDescriptorImpl$CopyConfiguration.class */
    public class CopyConfiguration implements FunctionDescriptor.CopyBuilder<FunctionDescriptor> {

        @NotNull
        protected TypeSubstitution substitution;

        @NotNull
        protected DeclarationDescriptor newOwner;

        @NotNull
        protected Modality newModality;

        @NotNull
        protected DescriptorVisibility newVisibility;

        @Nullable
        protected FunctionDescriptor original;

        @NotNull
        protected CallableMemberDescriptor.Kind kind;

        @NotNull
        protected List<ValueParameterDescriptor> newValueParameterDescriptors;

        @Nullable
        protected ReceiverParameterDescriptor newExtensionReceiverParameter;

        @Nullable
        protected ReceiverParameterDescriptor dispatchReceiverParameter;

        @NotNull
        protected KotlinType newReturnType;

        @Nullable
        protected Name name;
        protected boolean copyOverrides;
        protected boolean signatureChange;
        protected boolean preserveSourceElement;
        protected boolean dropOriginalInContainingParts;
        private boolean isHiddenToOvercomeSignatureClash;
        private List<TypeParameterDescriptor> newTypeParameters;
        private Annotations additionalAnnotations;
        private boolean isHiddenForResolutionEverywhereBesideSupercalls;
        private Map<CallableDescriptor.UserDataKey<?>, Object> userDataMap;
        private Boolean newHasSynthesizedParameterNames;
        protected boolean justForTypeSubstitution;
        final /* synthetic */ FunctionDescriptorImpl this$0;

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
                case 9:
                case 11:
                case 13:
                case 16:
                case 18:
                case 20:
                case 22:
                case 32:
                case 34:
                case 36:
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
                case 8:
                case 10:
                case 12:
                case 14:
                case 15:
                case 17:
                case 19:
                case 21:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 33:
                case 35:
                case 37:
                case 38:
                case 39:
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
                case 9:
                case 11:
                case 13:
                case 16:
                case 18:
                case 20:
                case 22:
                case 32:
                case 34:
                case 36:
                default:
                    i2 = 3;
                    break;
                case 8:
                case 10:
                case 12:
                case 14:
                case 15:
                case 17:
                case 19:
                case 21:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 33:
                case 35:
                case 37:
                case 38:
                case 39:
                    i2 = 2;
                    break;
            }
            Object[] objArr = new Object[i2];
            switch (i) {
                case 0:
                case 34:
                default:
                    objArr[0] = "substitution";
                    break;
                case 1:
                    objArr[0] = "newOwner";
                    break;
                case 2:
                    objArr[0] = "newModality";
                    break;
                case 3:
                    objArr[0] = "newVisibility";
                    break;
                case 4:
                case 13:
                    objArr[0] = "kind";
                    break;
                case 5:
                    objArr[0] = "newValueParameterDescriptors";
                    break;
                case 6:
                    objArr[0] = "newReturnType";
                    break;
                case 7:
                    objArr[0] = "owner";
                    break;
                case 8:
                case 10:
                case 12:
                case 14:
                case 15:
                case 17:
                case 19:
                case 21:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 33:
                case 35:
                case 37:
                case 38:
                case 39:
                    objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/FunctionDescriptorImpl$CopyConfiguration";
                    break;
                case 9:
                    objArr[0] = "modality";
                    break;
                case 11:
                    objArr[0] = "visibility";
                    break;
                case 16:
                    objArr[0] = "name";
                    break;
                case 18:
                case 20:
                    objArr[0] = "parameters";
                    break;
                case 22:
                    objArr[0] = "type";
                    break;
                case 32:
                    objArr[0] = "additionalAnnotations";
                    break;
                case 36:
                    objArr[0] = "userDataKey";
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
                case 9:
                case 11:
                case 13:
                case 16:
                case 18:
                case 20:
                case 22:
                case 32:
                case 34:
                case 36:
                default:
                    objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/FunctionDescriptorImpl$CopyConfiguration";
                    break;
                case 8:
                    objArr[1] = "setOwner";
                    break;
                case 10:
                    objArr[1] = "setModality";
                    break;
                case 12:
                    objArr[1] = "setVisibility";
                    break;
                case 14:
                    objArr[1] = "setKind";
                    break;
                case 15:
                    objArr[1] = "setCopyOverrides";
                    break;
                case 17:
                    objArr[1] = "setName";
                    break;
                case 19:
                    objArr[1] = "setValueParameters";
                    break;
                case 21:
                    objArr[1] = "setTypeParameters";
                    break;
                case 23:
                    objArr[1] = "setReturnType";
                    break;
                case 24:
                    objArr[1] = "setExtensionReceiverParameter";
                    break;
                case 25:
                    objArr[1] = "setDispatchReceiverParameter";
                    break;
                case 26:
                    objArr[1] = "setOriginal";
                    break;
                case 27:
                    objArr[1] = "setSignatureChange";
                    break;
                case 28:
                    objArr[1] = "setPreserveSourceElement";
                    break;
                case 29:
                    objArr[1] = "setDropOriginalInContainingParts";
                    break;
                case 30:
                    objArr[1] = "setHiddenToOvercomeSignatureClash";
                    break;
                case 31:
                    objArr[1] = "setHiddenForResolutionEverywhereBesideSupercalls";
                    break;
                case 33:
                    objArr[1] = "setAdditionalAnnotations";
                    break;
                case 35:
                    objArr[1] = "setSubstitution";
                    break;
                case 37:
                    objArr[1] = "putUserData";
                    break;
                case 38:
                    objArr[1] = "getSubstitution";
                    break;
                case 39:
                    objArr[1] = "setJustForTypeSubstitution";
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
                    objArr[2] = "setOwner";
                    break;
                case 8:
                case 10:
                case 12:
                case 14:
                case 15:
                case 17:
                case 19:
                case 21:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 33:
                case 35:
                case 37:
                case 38:
                case 39:
                    break;
                case 9:
                    objArr[2] = "setModality";
                    break;
                case 11:
                    objArr[2] = "setVisibility";
                    break;
                case 13:
                    objArr[2] = "setKind";
                    break;
                case 16:
                    objArr[2] = "setName";
                    break;
                case 18:
                    objArr[2] = "setValueParameters";
                    break;
                case 20:
                    objArr[2] = "setTypeParameters";
                    break;
                case 22:
                    objArr[2] = "setReturnType";
                    break;
                case 32:
                    objArr[2] = "setAdditionalAnnotations";
                    break;
                case 34:
                    objArr[2] = "setSubstitution";
                    break;
                case 36:
                    objArr[2] = "putUserData";
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
                case 9:
                case 11:
                case 13:
                case 16:
                case 18:
                case 20:
                case 22:
                case 32:
                case 34:
                case 36:
                default:
                    throw new IllegalArgumentException(str2);
                case 8:
                case 10:
                case 12:
                case 14:
                case 15:
                case 17:
                case 19:
                case 21:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 33:
                case 35:
                case 37:
                case 38:
                case 39:
                    throw new IllegalStateException(str2);
            }
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
        public /* bridge */ /* synthetic */ FunctionDescriptor.CopyBuilder setTypeParameters(List x0) {
            return setTypeParameters((List<TypeParameterDescriptor>) x0);
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
        public /* bridge */ /* synthetic */ FunctionDescriptor.CopyBuilder setValueParameters(List x0) {
            return setValueParameters((List<ValueParameterDescriptor>) x0);
        }

        public CopyConfiguration(@NotNull FunctionDescriptorImpl functionDescriptorImpl, @NotNull TypeSubstitution substitution, @NotNull DeclarationDescriptor newOwner, @NotNull Modality newModality, @NotNull DescriptorVisibility newVisibility, @NotNull CallableMemberDescriptor.Kind kind, @Nullable List<ValueParameterDescriptor> newValueParameterDescriptors, @NotNull ReceiverParameterDescriptor newExtensionReceiverParameter, @Nullable KotlinType newReturnType, Name name) {
            if (substitution == null) {
                $$$reportNull$$$0(0);
            }
            if (newOwner == null) {
                $$$reportNull$$$0(1);
            }
            if (newModality == null) {
                $$$reportNull$$$0(2);
            }
            if (newVisibility == null) {
                $$$reportNull$$$0(3);
            }
            if (kind == null) {
                $$$reportNull$$$0(4);
            }
            if (newValueParameterDescriptors == null) {
                $$$reportNull$$$0(5);
            }
            if (newReturnType == null) {
                $$$reportNull$$$0(6);
            }
            this.this$0 = functionDescriptorImpl;
            this.original = null;
            this.dispatchReceiverParameter = this.this$0.dispatchReceiverParameter;
            this.copyOverrides = true;
            this.signatureChange = false;
            this.preserveSourceElement = false;
            this.dropOriginalInContainingParts = false;
            this.isHiddenToOvercomeSignatureClash = this.this$0.isHiddenToOvercomeSignatureClash();
            this.newTypeParameters = null;
            this.additionalAnnotations = null;
            this.isHiddenForResolutionEverywhereBesideSupercalls = this.this$0.isHiddenForResolutionEverywhereBesideSupercalls();
            this.userDataMap = new LinkedHashMap();
            this.newHasSynthesizedParameterNames = null;
            this.justForTypeSubstitution = false;
            this.substitution = substitution;
            this.newOwner = newOwner;
            this.newModality = newModality;
            this.newVisibility = newVisibility;
            this.kind = kind;
            this.newValueParameterDescriptors = newValueParameterDescriptors;
            this.newExtensionReceiverParameter = newExtensionReceiverParameter;
            this.newReturnType = newReturnType;
            this.name = name;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
        @NotNull
        public CopyConfiguration setOwner(@NotNull DeclarationDescriptor owner) {
            if (owner == null) {
                $$$reportNull$$$0(7);
            }
            this.newOwner = owner;
            if (this == null) {
                $$$reportNull$$$0(8);
            }
            return this;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
        @NotNull
        public CopyConfiguration setModality(@NotNull Modality modality) {
            if (modality == null) {
                $$$reportNull$$$0(9);
            }
            this.newModality = modality;
            if (this == null) {
                $$$reportNull$$$0(10);
            }
            return this;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
        @NotNull
        public CopyConfiguration setVisibility(@NotNull DescriptorVisibility visibility) {
            if (visibility == null) {
                $$$reportNull$$$0(11);
            }
            this.newVisibility = visibility;
            if (this == null) {
                $$$reportNull$$$0(12);
            }
            return this;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
        @NotNull
        public CopyConfiguration setKind(@NotNull CallableMemberDescriptor.Kind kind) {
            if (kind == null) {
                $$$reportNull$$$0(13);
            }
            this.kind = kind;
            if (this == null) {
                $$$reportNull$$$0(14);
            }
            return this;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
        @NotNull
        public CopyConfiguration setCopyOverrides(boolean copyOverrides) {
            this.copyOverrides = copyOverrides;
            if (this == null) {
                $$$reportNull$$$0(15);
            }
            return this;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
        @NotNull
        public CopyConfiguration setName(@NotNull Name name) {
            if (name == null) {
                $$$reportNull$$$0(16);
            }
            this.name = name;
            if (this == null) {
                $$$reportNull$$$0(17);
            }
            return this;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
        @NotNull
        public CopyConfiguration setValueParameters(@NotNull List<ValueParameterDescriptor> parameters) {
            if (parameters == null) {
                $$$reportNull$$$0(18);
            }
            this.newValueParameterDescriptors = parameters;
            if (this == null) {
                $$$reportNull$$$0(19);
            }
            return this;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
        @NotNull
        public CopyConfiguration setTypeParameters(@NotNull List<TypeParameterDescriptor> parameters) {
            if (parameters == null) {
                $$$reportNull$$$0(20);
            }
            this.newTypeParameters = parameters;
            if (this == null) {
                $$$reportNull$$$0(21);
            }
            return this;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
        @NotNull
        public CopyConfiguration setReturnType(@NotNull KotlinType type) {
            if (type == null) {
                $$$reportNull$$$0(22);
            }
            this.newReturnType = type;
            if (this == null) {
                $$$reportNull$$$0(23);
            }
            return this;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
        @NotNull
        public CopyConfiguration setExtensionReceiverParameter(@Nullable ReceiverParameterDescriptor extensionReceiverParameter) {
            this.newExtensionReceiverParameter = extensionReceiverParameter;
            if (this == null) {
                $$$reportNull$$$0(24);
            }
            return this;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
        @NotNull
        public CopyConfiguration setDispatchReceiverParameter(@Nullable ReceiverParameterDescriptor dispatchReceiverParameter) {
            this.dispatchReceiverParameter = dispatchReceiverParameter;
            if (this == null) {
                $$$reportNull$$$0(25);
            }
            return this;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
        @NotNull
        public CopyConfiguration setOriginal(@Nullable CallableMemberDescriptor original) {
            this.original = (FunctionDescriptor) original;
            if (this == null) {
                $$$reportNull$$$0(26);
            }
            return this;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
        @NotNull
        public CopyConfiguration setSignatureChange() {
            this.signatureChange = true;
            if (this == null) {
                $$$reportNull$$$0(27);
            }
            return this;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
        @NotNull
        public CopyConfiguration setPreserveSourceElement() {
            this.preserveSourceElement = true;
            if (this == null) {
                $$$reportNull$$$0(28);
            }
            return this;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
        @NotNull
        public CopyConfiguration setDropOriginalInContainingParts() {
            this.dropOriginalInContainingParts = true;
            if (this == null) {
                $$$reportNull$$$0(29);
            }
            return this;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
        @NotNull
        public CopyConfiguration setHiddenToOvercomeSignatureClash() {
            this.isHiddenToOvercomeSignatureClash = true;
            if (this == null) {
                $$$reportNull$$$0(30);
            }
            return this;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
        @NotNull
        public CopyConfiguration setHiddenForResolutionEverywhereBesideSupercalls() {
            this.isHiddenForResolutionEverywhereBesideSupercalls = true;
            if (this == null) {
                $$$reportNull$$$0(31);
            }
            return this;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
        @NotNull
        public CopyConfiguration setAdditionalAnnotations(@NotNull Annotations additionalAnnotations) {
            if (additionalAnnotations == null) {
                $$$reportNull$$$0(32);
            }
            this.additionalAnnotations = additionalAnnotations;
            if (this == null) {
                $$$reportNull$$$0(33);
            }
            return this;
        }

        public CopyConfiguration setHasSynthesizedParameterNames(boolean value) {
            this.newHasSynthesizedParameterNames = Boolean.valueOf(value);
            return this;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
        @NotNull
        public CopyConfiguration setSubstitution(@NotNull TypeSubstitution substitution) {
            if (substitution == null) {
                $$$reportNull$$$0(34);
            }
            this.substitution = substitution;
            if (this == null) {
                $$$reportNull$$$0(35);
            }
            return this;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
        @Nullable
        public FunctionDescriptor build() {
            return this.this$0.doSubstitute(this);
        }

        @NotNull
        public CopyConfiguration setJustForTypeSubstitution(boolean value) {
            this.justForTypeSubstitution = value;
            if (this == null) {
                $$$reportNull$$$0(39);
            }
            return this;
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    @NotNull
    public FunctionDescriptor.CopyBuilder<? extends FunctionDescriptor> newCopyBuilder() {
        CopyConfiguration copyConfigurationNewCopyBuilder = newCopyBuilder(TypeSubstitutor.EMPTY);
        if (copyConfigurationNewCopyBuilder == null) {
            $$$reportNull$$$0(21);
        }
        return copyConfigurationNewCopyBuilder;
    }

    @NotNull
    protected CopyConfiguration newCopyBuilder(@NotNull TypeSubstitutor substitutor) {
        if (substitutor == null) {
            $$$reportNull$$$0(22);
        }
        return new CopyConfiguration(this, substitutor.getSubstitution(), getContainingDeclaration(), getModality(), getVisibility(), getKind(), getValueParameters(), getExtensionReceiverParameter(), getReturnType(), null);
    }

    @Nullable
    protected FunctionDescriptor doSubstitute(@NotNull CopyConfiguration configuration) {
        KotlinType substitutedReturnType;
        if (configuration == null) {
            $$$reportNull$$$0(23);
        }
        boolean[] wereChanges = new boolean[1];
        Annotations resultAnnotations = configuration.additionalAnnotations != null ? AnnotationsKt.composeAnnotations(getAnnotations(), configuration.additionalAnnotations) : getAnnotations();
        FunctionDescriptorImpl substitutedDescriptor = createSubstitutedCopy(configuration.newOwner, configuration.original, configuration.kind, configuration.name, resultAnnotations, getSourceToUseForCopy(configuration.preserveSourceElement, configuration.original));
        List<TypeParameterDescriptor> unsubstitutedTypeParameters = configuration.newTypeParameters == null ? getTypeParameters() : configuration.newTypeParameters;
        wereChanges[0] = wereChanges[0] | (!unsubstitutedTypeParameters.isEmpty());
        List<TypeParameterDescriptor> substitutedTypeParameters = new ArrayList<>(unsubstitutedTypeParameters.size());
        final TypeSubstitutor substitutor = DescriptorSubstitutor.substituteTypeParameters(unsubstitutedTypeParameters, configuration.substitution, substitutedDescriptor, substitutedTypeParameters, wereChanges);
        if (substitutor == null) {
            return null;
        }
        ReceiverParameterDescriptor substitutedReceiverParameter = null;
        if (configuration.newExtensionReceiverParameter != null) {
            KotlinType substitutedExtensionReceiverType = substitutor.substitute(configuration.newExtensionReceiverParameter.getType(), Variance.IN_VARIANCE);
            if (substitutedExtensionReceiverType == null) {
                return null;
            }
            substitutedReceiverParameter = new ReceiverParameterDescriptorImpl(substitutedDescriptor, new ExtensionReceiver(substitutedDescriptor, substitutedExtensionReceiverType, configuration.newExtensionReceiverParameter.getValue()), configuration.newExtensionReceiverParameter.getAnnotations());
            wereChanges[0] = wereChanges[0] | (substitutedExtensionReceiverType != configuration.newExtensionReceiverParameter.getType());
        }
        ReceiverParameterDescriptor substitutedExpectedThis = null;
        if (configuration.dispatchReceiverParameter != null) {
            substitutedExpectedThis = configuration.dispatchReceiverParameter.substitute(substitutor);
            if (substitutedExpectedThis == null) {
                return null;
            }
            wereChanges[0] = wereChanges[0] | (substitutedExpectedThis != configuration.dispatchReceiverParameter);
        }
        List<ValueParameterDescriptor> substitutedValueParameters = getSubstitutedValueParameters(substitutedDescriptor, configuration.newValueParameterDescriptors, substitutor, configuration.dropOriginalInContainingParts, configuration.preserveSourceElement, wereChanges);
        if (substitutedValueParameters == null || (substitutedReturnType = substitutor.substitute(configuration.newReturnType, Variance.OUT_VARIANCE)) == null) {
            return null;
        }
        wereChanges[0] = wereChanges[0] | (substitutedReturnType != configuration.newReturnType);
        if (!wereChanges[0] && configuration.justForTypeSubstitution) {
            return this;
        }
        substitutedDescriptor.initialize(substitutedReceiverParameter, substitutedExpectedThis, substitutedTypeParameters, substitutedValueParameters, substitutedReturnType, configuration.newModality, configuration.newVisibility);
        substitutedDescriptor.setOperator(this.isOperator);
        substitutedDescriptor.setInfix(this.isInfix);
        substitutedDescriptor.setExternal(this.isExternal);
        substitutedDescriptor.setInline(this.isInline);
        substitutedDescriptor.setTailrec(this.isTailrec);
        substitutedDescriptor.setSuspend(this.isSuspend);
        substitutedDescriptor.setExpect(this.isExpect);
        substitutedDescriptor.setActual(this.isActual);
        substitutedDescriptor.setHasStableParameterNames(this.hasStableParameterNames);
        substitutedDescriptor.setHiddenToOvercomeSignatureClash(configuration.isHiddenToOvercomeSignatureClash);
        substitutedDescriptor.setHiddenForResolutionEverywhereBesideSupercalls(configuration.isHiddenForResolutionEverywhereBesideSupercalls);
        substitutedDescriptor.setHasSynthesizedParameterNames(configuration.newHasSynthesizedParameterNames != null ? configuration.newHasSynthesizedParameterNames.booleanValue() : this.hasSynthesizedParameterNames);
        if (!configuration.userDataMap.isEmpty() || this.userDataMap != null) {
            Map<CallableDescriptor.UserDataKey<?>, Object> newMap = configuration.userDataMap;
            if (this.userDataMap != null) {
                for (Map.Entry<CallableDescriptor.UserDataKey<?>, Object> entry : this.userDataMap.entrySet()) {
                    if (!newMap.containsKey(entry.getKey())) {
                        newMap.put(entry.getKey(), entry.getValue());
                    }
                }
            }
            if (newMap.size() == 1) {
                substitutedDescriptor.userDataMap = Collections.singletonMap(newMap.keySet().iterator().next(), newMap.values().iterator().next());
            } else {
                substitutedDescriptor.userDataMap = newMap;
            }
        }
        if (configuration.signatureChange || getInitialSignatureDescriptor() != null) {
            FunctionDescriptor initialSignature = getInitialSignatureDescriptor() != null ? getInitialSignatureDescriptor() : this;
            FunctionDescriptor initialSignatureSubstituted = initialSignature.substitute(substitutor);
            substitutedDescriptor.setInitialSignatureDescriptor(initialSignatureSubstituted);
        }
        if (configuration.copyOverrides && !getOriginal().getOverriddenDescriptors().isEmpty()) {
            if (configuration.substitution.isEmpty()) {
                Function0<Collection<FunctionDescriptor>> overriddenFunctionsTask = this.lazyOverriddenFunctionsTask;
                if (overriddenFunctionsTask != null) {
                    substitutedDescriptor.lazyOverriddenFunctionsTask = overriddenFunctionsTask;
                } else {
                    substitutedDescriptor.setOverriddenDescriptors(getOverriddenDescriptors());
                }
            } else {
                substitutedDescriptor.lazyOverriddenFunctionsTask = new Function0<Collection<FunctionDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public Collection<FunctionDescriptor> invoke() {
                        Collection<FunctionDescriptor> result = new SmartList<>();
                        for (FunctionDescriptor overriddenFunction : FunctionDescriptorImpl.this.getOverriddenDescriptors()) {
                            result.add(overriddenFunction.substitute(substitutor));
                        }
                        return result;
                    }
                };
            }
        }
        return substitutedDescriptor;
    }

    @NotNull
    public FunctionDescriptor copy(DeclarationDescriptor newOwner, Modality modality, DescriptorVisibility visibility, CallableMemberDescriptor.Kind kind, boolean copyOverrides) {
        FunctionDescriptor functionDescriptorBuild = newCopyBuilder().setOwner(newOwner).setModality(modality).setVisibility(visibility).setKind(kind).setCopyOverrides(copyOverrides).build();
        if (functionDescriptorBuild == null) {
            $$$reportNull$$$0(24);
        }
        return functionDescriptorBuild;
    }

    @NotNull
    private SourceElement getSourceToUseForCopy(boolean preserveSource, @Nullable FunctionDescriptor original) {
        SourceElement source;
        if (preserveSource) {
            source = (original != null ? original : getOriginal()).getSource();
        } else {
            source = SourceElement.NO_SOURCE;
        }
        if (source == null) {
            $$$reportNull$$$0(25);
        }
        return source;
    }

    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> visitor, D data) {
        return visitor.visitFunctionDescriptor(this, data);
    }

    @Nullable
    public static List<ValueParameterDescriptor> getSubstitutedValueParameters(FunctionDescriptor substitutedDescriptor, @NotNull List<ValueParameterDescriptor> unsubstitutedValueParameters, @NotNull TypeSubstitutor substitutor) {
        if (unsubstitutedValueParameters == null) {
            $$$reportNull$$$0(26);
        }
        if (substitutor == null) {
            $$$reportNull$$$0(27);
        }
        return getSubstitutedValueParameters(substitutedDescriptor, unsubstitutedValueParameters, substitutor, false, false, null);
    }

    @Nullable
    public static List<ValueParameterDescriptor> getSubstitutedValueParameters(FunctionDescriptor substitutedDescriptor, @NotNull List<ValueParameterDescriptor> unsubstitutedValueParameters, @NotNull TypeSubstitutor substitutor, boolean dropOriginal, boolean preserveSourceElement, @Nullable boolean[] wereChanges) {
        if (unsubstitutedValueParameters == null) {
            $$$reportNull$$$0(28);
        }
        if (substitutor == null) {
            $$$reportNull$$$0(29);
        }
        List<ValueParameterDescriptor> result = new ArrayList<>(unsubstitutedValueParameters.size());
        for (ValueParameterDescriptor unsubstitutedValueParameter : unsubstitutedValueParameters) {
            KotlinType substitutedType = substitutor.substitute(unsubstitutedValueParameter.getType(), Variance.IN_VARIANCE);
            KotlinType varargElementType = unsubstitutedValueParameter.getVarargElementType();
            KotlinType substituteVarargElementType = varargElementType == null ? null : substitutor.substitute(varargElementType, Variance.IN_VARIANCE);
            if (substitutedType == null) {
                return null;
            }
            if ((substitutedType != unsubstitutedValueParameter.getType() || varargElementType != substituteVarargElementType) && wereChanges != null) {
                wereChanges[0] = true;
            }
            Function0<List<VariableDescriptor>> destructuringVariablesAction = null;
            if (unsubstitutedValueParameter instanceof ValueParameterDescriptorImpl.WithDestructuringDeclaration) {
                final List<VariableDescriptor> destructuringVariables = ((ValueParameterDescriptorImpl.WithDestructuringDeclaration) unsubstitutedValueParameter).getDestructuringVariables();
                destructuringVariablesAction = new Function0<List<VariableDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl.2
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public List<VariableDescriptor> invoke() {
                        return destructuringVariables;
                    }
                };
            }
            result.add(ValueParameterDescriptorImpl.createWithDestructuringDeclarations(substitutedDescriptor, dropOriginal ? null : unsubstitutedValueParameter, unsubstitutedValueParameter.getIndex(), unsubstitutedValueParameter.getAnnotations(), unsubstitutedValueParameter.getName(), substitutedType, unsubstitutedValueParameter.declaresDefaultValue(), unsubstitutedValueParameter.isCrossinline(), unsubstitutedValueParameter.isNoinline(), substituteVarargElementType, preserveSourceElement ? unsubstitutedValueParameter.getSource() : SourceElement.NO_SOURCE, destructuringVariablesAction));
        }
        return result;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    @Nullable
    public FunctionDescriptor getInitialSignatureDescriptor() {
        return this.initialSignatureDescriptor;
    }

    private void setInitialSignatureDescriptor(@Nullable FunctionDescriptor initialSignatureDescriptor) {
        this.initialSignatureDescriptor = initialSignatureDescriptor;
    }

    public <V> void putInUserDataMap(CallableDescriptor.UserDataKey<V> key, Object value) {
        if (this.userDataMap == null) {
            this.userDataMap = new LinkedHashMap();
        }
        this.userDataMap.put(key, value);
    }
}
