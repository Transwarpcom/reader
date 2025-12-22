package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Collection;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/FunctionDescriptor.class */
public interface FunctionDescriptor extends CallableMemberDescriptor {

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/FunctionDescriptor$CopyBuilder.class */
    public interface CopyBuilder<D extends FunctionDescriptor> {
        @NotNull
        CopyBuilder<D> setOwner(@NotNull DeclarationDescriptor declarationDescriptor);

        @NotNull
        CopyBuilder<D> setModality(@NotNull Modality modality);

        @NotNull
        CopyBuilder<D> setVisibility(@NotNull DescriptorVisibility descriptorVisibility);

        @NotNull
        CopyBuilder<D> setKind(@NotNull CallableMemberDescriptor.Kind kind);

        @NotNull
        CopyBuilder<D> setCopyOverrides(boolean z);

        @NotNull
        CopyBuilder<D> setName(@NotNull Name name);

        @NotNull
        CopyBuilder<D> setValueParameters(@NotNull List<ValueParameterDescriptor> list);

        @NotNull
        CopyBuilder<D> setTypeParameters(@NotNull List<TypeParameterDescriptor> list);

        @NotNull
        CopyBuilder<D> setReturnType(@NotNull KotlinType kotlinType);

        @NotNull
        CopyBuilder<D> setExtensionReceiverParameter(@Nullable ReceiverParameterDescriptor receiverParameterDescriptor);

        @NotNull
        CopyBuilder<D> setDispatchReceiverParameter(@Nullable ReceiverParameterDescriptor receiverParameterDescriptor);

        @NotNull
        CopyBuilder<D> setOriginal(@Nullable CallableMemberDescriptor callableMemberDescriptor);

        @NotNull
        CopyBuilder<D> setSignatureChange();

        @NotNull
        CopyBuilder<D> setPreserveSourceElement();

        @NotNull
        CopyBuilder<D> setDropOriginalInContainingParts();

        @NotNull
        CopyBuilder<D> setHiddenToOvercomeSignatureClash();

        @NotNull
        CopyBuilder<D> setHiddenForResolutionEverywhereBesideSupercalls();

        @NotNull
        CopyBuilder<D> setAdditionalAnnotations(@NotNull Annotations annotations);

        @NotNull
        CopyBuilder<D> setSubstitution(@NotNull TypeSubstitution typeSubstitution);

        @Nullable
        D build();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    @NotNull
    DeclarationDescriptor getContainingDeclaration();

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    @NotNull
    FunctionDescriptor getOriginal();

    @Nullable
    FunctionDescriptor substitute(@NotNull TypeSubstitutor typeSubstitutor);

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    @NotNull
    Collection<? extends FunctionDescriptor> getOverriddenDescriptors();

    @Nullable
    FunctionDescriptor getInitialSignatureDescriptor();

    boolean isHiddenToOvercomeSignatureClash();

    boolean isOperator();

    boolean isInfix();

    boolean isInline();

    boolean isTailrec();

    boolean isHiddenForResolutionEverywhereBesideSupercalls();

    boolean isSuspend();

    @NotNull
    CopyBuilder<? extends FunctionDescriptor> newCopyBuilder();
}
