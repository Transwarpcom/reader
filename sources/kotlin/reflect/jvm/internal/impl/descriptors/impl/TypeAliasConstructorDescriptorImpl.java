package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.List;
import kotlin._Assertions;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.storage.NullableLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* compiled from: TypeAliasConstructorDescriptor.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/impl/TypeAliasConstructorDescriptorImpl.class */
public final class TypeAliasConstructorDescriptorImpl extends FunctionDescriptorImpl implements TypeAliasConstructorDescriptor {

    @NotNull
    private final StorageManager storageManager;

    @NotNull
    private final TypeAliasDescriptor typeAliasDescriptor;

    @NotNull
    private final NullableLazyValue withDispatchReceiver$delegate;

    @NotNull
    private ClassConstructorDescriptor underlyingConstructorDescriptor;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(TypeAliasConstructorDescriptorImpl.class), "withDispatchReceiver", "getWithDispatchReceiver()Lorg/jetbrains/kotlin/descriptors/impl/TypeAliasConstructorDescriptor;"))};

    @NotNull
    public static final Companion Companion = new Companion(null);

    public /* synthetic */ TypeAliasConstructorDescriptorImpl(StorageManager storageManager, TypeAliasDescriptor typeAliasDescriptor, ClassConstructorDescriptor underlyingConstructorDescriptor, TypeAliasConstructorDescriptor original, Annotations annotations, CallableMemberDescriptor.Kind kind, SourceElement source, DefaultConstructorMarker $constructor_marker) {
        this(storageManager, typeAliasDescriptor, underlyingConstructorDescriptor, original, annotations, kind, source);
    }

    @NotNull
    public final StorageManager getStorageManager() {
        return this.storageManager;
    }

    @NotNull
    public TypeAliasDescriptor getTypeAliasDescriptor() {
        return this.typeAliasDescriptor;
    }

    private TypeAliasConstructorDescriptorImpl(StorageManager storageManager, TypeAliasDescriptor typeAliasDescriptor, final ClassConstructorDescriptor underlyingConstructorDescriptor, TypeAliasConstructorDescriptor original, Annotations annotations, CallableMemberDescriptor.Kind kind, SourceElement source) {
        super(typeAliasDescriptor, original, annotations, Name.special(Constants.CONSTRUCTOR_NAME), kind, source);
        this.storageManager = storageManager;
        this.typeAliasDescriptor = typeAliasDescriptor;
        setActual(getTypeAliasDescriptor().isActual());
        this.withDispatchReceiver$delegate = this.storageManager.createNullableLazyValue(new Function0<TypeAliasConstructorDescriptorImpl>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeAliasConstructorDescriptorImpl$withDispatchReceiver$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @Nullable
            public final TypeAliasConstructorDescriptorImpl invoke() {
                StorageManager storageManager2 = this.this$0.getStorageManager();
                TypeAliasDescriptor typeAliasDescriptor2 = this.this$0.getTypeAliasDescriptor();
                ClassConstructorDescriptor classConstructorDescriptor = underlyingConstructorDescriptor;
                TypeAliasConstructorDescriptorImpl typeAliasConstructorDescriptorImpl = this.this$0;
                Annotations annotations2 = underlyingConstructorDescriptor.getAnnotations();
                CallableMemberDescriptor.Kind kind2 = underlyingConstructorDescriptor.getKind();
                Intrinsics.checkNotNullExpressionValue(kind2, "underlyingConstructorDescriptor.kind");
                SourceElement source2 = this.this$0.getTypeAliasDescriptor().getSource();
                Intrinsics.checkNotNullExpressionValue(source2, "typeAliasDescriptor.source");
                TypeAliasConstructorDescriptorImpl typeAliasConstructor = new TypeAliasConstructorDescriptorImpl(storageManager2, typeAliasDescriptor2, classConstructorDescriptor, typeAliasConstructorDescriptorImpl, annotations2, kind2, source2, null);
                TypeAliasConstructorDescriptorImpl typeAliasConstructorDescriptorImpl2 = this.this$0;
                ClassConstructorDescriptor classConstructorDescriptor2 = underlyingConstructorDescriptor;
                TypeSubstitutor substitutorForUnderlyingClass = TypeAliasConstructorDescriptorImpl.Companion.getTypeSubstitutorForUnderlyingClass(typeAliasConstructorDescriptorImpl2.getTypeAliasDescriptor());
                if (substitutorForUnderlyingClass == null) {
                    return null;
                }
                ReceiverParameterDescriptor dispatchReceiverParameter = classConstructorDescriptor2.getDispatchReceiverParameter();
                typeAliasConstructor.initialize(null, dispatchReceiverParameter == null ? null : dispatchReceiverParameter.substitute(substitutorForUnderlyingClass), typeAliasConstructorDescriptorImpl2.getTypeAliasDescriptor().getDeclaredTypeParameters(), typeAliasConstructorDescriptorImpl2.getValueParameters(), typeAliasConstructorDescriptorImpl2.getReturnType(), Modality.FINAL, typeAliasConstructorDescriptorImpl2.getTypeAliasDescriptor().getVisibility());
                return typeAliasConstructor;
            }
        });
        this.underlyingConstructorDescriptor = underlyingConstructorDescriptor;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeAliasConstructorDescriptor
    @NotNull
    public ClassConstructorDescriptor getUnderlyingConstructorDescriptor() {
        return this.underlyingConstructorDescriptor;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor
    public boolean isPrimary() {
        return getUnderlyingConstructorDescriptor().isPrimary();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    @NotNull
    public TypeAliasDescriptor getContainingDeclaration() {
        return getTypeAliasDescriptor();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor
    @NotNull
    public ClassDescriptor getConstructedClass() {
        ClassDescriptor constructedClass = getUnderlyingConstructorDescriptor().getConstructedClass();
        Intrinsics.checkNotNullExpressionValue(constructedClass, "underlyingConstructorDescriptor.constructedClass");
        return constructedClass;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    @NotNull
    public KotlinType getReturnType() {
        KotlinType returnType = super.getReturnType();
        Intrinsics.checkNotNull(returnType);
        return returnType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    @NotNull
    public TypeAliasConstructorDescriptor getOriginal() {
        return (TypeAliasConstructorDescriptor) super.getOriginal();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.Substitutable
    @Nullable
    public TypeAliasConstructorDescriptor substitute(@NotNull TypeSubstitutor substitutor) {
        Intrinsics.checkNotNullParameter(substitutor, "substitutor");
        FunctionDescriptor functionDescriptorSubstitute = super.substitute(substitutor);
        if (functionDescriptorSubstitute == null) {
            throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.impl.TypeAliasConstructorDescriptorImpl");
        }
        TypeAliasConstructorDescriptorImpl substitutedTypeAliasConstructor = (TypeAliasConstructorDescriptorImpl) functionDescriptorSubstitute;
        TypeSubstitutor underlyingConstructorSubstitutor = TypeSubstitutor.create(substitutedTypeAliasConstructor.getReturnType());
        Intrinsics.checkNotNullExpressionValue(underlyingConstructorSubstitutor, "create(substitutedTypeAliasConstructor.returnType)");
        ClassConstructorDescriptor substitutedUnderlyingConstructor = getUnderlyingConstructorDescriptor().getOriginal().substitute(underlyingConstructorSubstitutor);
        if (substitutedUnderlyingConstructor == null) {
            return null;
        }
        substitutedTypeAliasConstructor.underlyingConstructorDescriptor = substitutedUnderlyingConstructor;
        return substitutedTypeAliasConstructor;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    @NotNull
    public TypeAliasConstructorDescriptor copy(@NotNull DeclarationDescriptor newOwner, @NotNull Modality modality, @NotNull DescriptorVisibility visibility, @NotNull CallableMemberDescriptor.Kind kind, boolean copyOverrides) {
        Intrinsics.checkNotNullParameter(newOwner, "newOwner");
        Intrinsics.checkNotNullParameter(modality, "modality");
        Intrinsics.checkNotNullParameter(visibility, "visibility");
        Intrinsics.checkNotNullParameter(kind, "kind");
        FunctionDescriptor functionDescriptorBuild = newCopyBuilder().setOwner(newOwner).setModality(modality).setVisibility(visibility).setKind(kind).setCopyOverrides(copyOverrides).build();
        if (functionDescriptorBuild == null) {
            throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.impl.TypeAliasConstructorDescriptor");
        }
        return (TypeAliasConstructorDescriptor) functionDescriptorBuild;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    @NotNull
    public TypeAliasConstructorDescriptorImpl createSubstitutedCopy(@NotNull DeclarationDescriptor newOwner, @Nullable FunctionDescriptor original, @NotNull CallableMemberDescriptor.Kind kind, @Nullable Name newName, @NotNull Annotations annotations, @NotNull SourceElement source) {
        Intrinsics.checkNotNullParameter(newOwner, "newOwner");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(source, "source");
        boolean z = kind == CallableMemberDescriptor.Kind.DECLARATION || kind == CallableMemberDescriptor.Kind.SYNTHESIZED;
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Creating a type alias constructor that is not a declaration: \ncopy from: " + this + "\nnewOwner: " + newOwner + "\nkind: " + kind);
        }
        boolean z2 = newName == null;
        if (_Assertions.ENABLED && !z2) {
            throw new AssertionError(Intrinsics.stringPlus("Renaming type alias constructor: ", this));
        }
        return new TypeAliasConstructorDescriptorImpl(this.storageManager, getTypeAliasDescriptor(), getUnderlyingConstructorDescriptor(), this, annotations, CallableMemberDescriptor.Kind.DECLARATION, source);
    }

    /* compiled from: TypeAliasConstructorDescriptor.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/impl/TypeAliasConstructorDescriptorImpl$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final TypeSubstitutor getTypeSubstitutorForUnderlyingClass(TypeAliasDescriptor $this$getTypeSubstitutorForUnderlyingClass) {
            if ($this$getTypeSubstitutorForUnderlyingClass.getClassDescriptor() == null) {
                return null;
            }
            return TypeSubstitutor.create($this$getTypeSubstitutorForUnderlyingClass.getExpandedType());
        }

        @Nullable
        public final TypeAliasConstructorDescriptor createIfAvailable(@NotNull StorageManager storageManager, @NotNull TypeAliasDescriptor typeAliasDescriptor, @NotNull ClassConstructorDescriptor constructor) {
            ClassConstructorDescriptor substitutedConstructor;
            Intrinsics.checkNotNullParameter(storageManager, "storageManager");
            Intrinsics.checkNotNullParameter(typeAliasDescriptor, "typeAliasDescriptor");
            Intrinsics.checkNotNullParameter(constructor, "constructor");
            TypeSubstitutor substitutorForUnderlyingClass = getTypeSubstitutorForUnderlyingClass(typeAliasDescriptor);
            if (substitutorForUnderlyingClass == null || (substitutedConstructor = constructor.substitute(substitutorForUnderlyingClass)) == null) {
                return null;
            }
            Annotations annotations = constructor.getAnnotations();
            CallableMemberDescriptor.Kind kind = constructor.getKind();
            Intrinsics.checkNotNullExpressionValue(kind, "constructor.kind");
            SourceElement source = typeAliasDescriptor.getSource();
            Intrinsics.checkNotNullExpressionValue(source, "typeAliasDescriptor.source");
            TypeAliasConstructorDescriptorImpl typeAliasConstructor = new TypeAliasConstructorDescriptorImpl(storageManager, typeAliasDescriptor, substitutedConstructor, null, annotations, kind, source, null);
            List valueParameters = FunctionDescriptorImpl.getSubstitutedValueParameters(typeAliasConstructor, constructor.getValueParameters(), substitutorForUnderlyingClass);
            if (valueParameters == null) {
                return null;
            }
            SimpleType simpleTypeLowerIfFlexible = FlexibleTypesKt.lowerIfFlexible(substitutedConstructor.getReturnType().unwrap());
            SimpleType defaultType = typeAliasDescriptor.getDefaultType();
            Intrinsics.checkNotNullExpressionValue(defaultType, "typeAliasDescriptor.defaultType");
            SimpleType returnType = SpecialTypesKt.withAbbreviation(simpleTypeLowerIfFlexible, defaultType);
            ReceiverParameterDescriptor it = constructor.getDispatchReceiverParameter();
            ReceiverParameterDescriptor receiverParameter = it == null ? null : DescriptorFactory.createExtensionReceiverParameterForCallable(typeAliasConstructor, substitutorForUnderlyingClass.safeSubstitute(it.getType(), Variance.INVARIANT), Annotations.Companion.getEMPTY());
            typeAliasConstructor.initialize(receiverParameter, null, typeAliasDescriptor.getDeclaredTypeParameters(), valueParameters, returnType, Modality.FINAL, typeAliasDescriptor.getVisibility());
            return typeAliasConstructor;
        }
    }
}
