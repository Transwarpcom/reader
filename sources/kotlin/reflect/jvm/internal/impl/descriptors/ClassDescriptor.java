package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Collection;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/ClassDescriptor.class */
public interface ClassDescriptor extends ClassOrPackageFragmentDescriptor, ClassifierDescriptorWithTypeParameters {
    @NotNull
    MemberScope getMemberScope(@NotNull TypeSubstitution typeSubstitution);

    @NotNull
    MemberScope getUnsubstitutedMemberScope();

    @NotNull
    MemberScope getUnsubstitutedInnerClassesScope();

    @NotNull
    MemberScope getStaticScope();

    @NotNull
    Collection<ClassConstructorDescriptor> getConstructors();

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    @NotNull
    DeclarationDescriptor getContainingDeclaration();

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    @NotNull
    SimpleType getDefaultType();

    @Nullable
    /* renamed from: getCompanionObjectDescriptor */
    ClassDescriptor mo3497getCompanionObjectDescriptor();

    @NotNull
    ClassKind getKind();

    @NotNull
    Modality getModality();

    @NotNull
    DescriptorVisibility getVisibility();

    boolean isCompanionObject();

    boolean isData();

    boolean isInline();

    boolean isFun();

    boolean isValue();

    @NotNull
    ReceiverParameterDescriptor getThisAsReceiverParameter();

    @Nullable
    /* renamed from: getUnsubstitutedPrimaryConstructor */
    ClassConstructorDescriptor mo3498getUnsubstitutedPrimaryConstructor();

    @NotNull
    List<TypeParameterDescriptor> getDeclaredTypeParameters();

    @NotNull
    Collection<ClassDescriptor> getSealedSubclasses();

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    @NotNull
    ClassDescriptor getOriginal();
}
