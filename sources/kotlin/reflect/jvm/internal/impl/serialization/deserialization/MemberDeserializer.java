package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.FieldDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertySetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirement;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedAnnotations;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedCallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedSimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedTypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.NonEmptyDeserializedAnnotations;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MemberDeserializer.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/serialization/deserialization/MemberDeserializer.class */
public final class MemberDeserializer {

    @NotNull
    private final DeserializationContext c;

    @NotNull
    private final AnnotationDeserializer annotationDeserializer;

    public MemberDeserializer(@NotNull DeserializationContext c) {
        Intrinsics.checkNotNullParameter(c, "c");
        this.c = c;
        this.annotationDeserializer = new AnnotationDeserializer(this.c.getComponents().getModuleDescriptor(), this.c.getComponents().getNotFoundClasses());
    }

    @NotNull
    public final PropertyDescriptor loadProperty(@NotNull final ProtoBuf.Property proto) {
        Annotations empty;
        ReceiverParameterDescriptor receiverParameterDescriptor;
        PropertyGetterDescriptorImpl propertyGetterDescriptorImpl;
        PropertySetterDescriptorImpl propertySetterDescriptorImpl;
        PropertyGetterDescriptorImpl propertyGetterDescriptorImpl2;
        Intrinsics.checkNotNullParameter(proto, "proto");
        int flags = proto.hasFlags() ? proto.getFlags() : loadOldFlags(proto.getOldFlags());
        DeclarationDescriptor containingDeclaration = this.c.getContainingDeclaration();
        Annotations annotations = getAnnotations(proto, flags, AnnotatedCallableKind.PROPERTY);
        Modality modality = ProtoEnumFlags.INSTANCE.modality(Flags.MODALITY.get(flags));
        DescriptorVisibility descriptorVisibility = ProtoEnumFlagsUtilsKt.descriptorVisibility(ProtoEnumFlags.INSTANCE, Flags.VISIBILITY.get(flags));
        Boolean bool = Flags.IS_VAR.get(flags);
        Intrinsics.checkNotNullExpressionValue(bool, "IS_VAR.get(flags)");
        boolean zBooleanValue = bool.booleanValue();
        Name name = NameResolverUtilKt.getName(this.c.getNameResolver(), proto.getName());
        CallableMemberDescriptor.Kind kindMemberKind = ProtoEnumFlagsUtilsKt.memberKind(ProtoEnumFlags.INSTANCE, Flags.MEMBER_KIND.get(flags));
        Boolean bool2 = Flags.IS_LATEINIT.get(flags);
        Intrinsics.checkNotNullExpressionValue(bool2, "IS_LATEINIT.get(flags)");
        boolean zBooleanValue2 = bool2.booleanValue();
        Boolean bool3 = Flags.IS_CONST.get(flags);
        Intrinsics.checkNotNullExpressionValue(bool3, "IS_CONST.get(flags)");
        boolean zBooleanValue3 = bool3.booleanValue();
        Boolean bool4 = Flags.IS_EXTERNAL_PROPERTY.get(flags);
        Intrinsics.checkNotNullExpressionValue(bool4, "IS_EXTERNAL_PROPERTY.get(flags)");
        boolean zBooleanValue4 = bool4.booleanValue();
        Boolean bool5 = Flags.IS_DELEGATED.get(flags);
        Intrinsics.checkNotNullExpressionValue(bool5, "IS_DELEGATED.get(flags)");
        boolean zBooleanValue5 = bool5.booleanValue();
        Boolean bool6 = Flags.IS_EXPECT_PROPERTY.get(flags);
        Intrinsics.checkNotNullExpressionValue(bool6, "IS_EXPECT_PROPERTY.get(flags)");
        final DeserializedPropertyDescriptor property = new DeserializedPropertyDescriptor(containingDeclaration, null, annotations, modality, descriptorVisibility, zBooleanValue, name, kindMemberKind, zBooleanValue2, zBooleanValue3, zBooleanValue4, zBooleanValue5, bool6.booleanValue(), proto, this.c.getNameResolver(), this.c.getTypeTable(), this.c.getVersionRequirementTable(), this.c.getContainerSource());
        List<ProtoBuf.TypeParameter> typeParameterList = proto.getTypeParameterList();
        Intrinsics.checkNotNullExpressionValue(typeParameterList, "proto.typeParameterList");
        DeserializationContext local = DeserializationContext.childContext$default(this.c, property, typeParameterList, null, null, null, null, 60, null);
        Boolean bool7 = Flags.HAS_GETTER.get(flags);
        Intrinsics.checkNotNullExpressionValue(bool7, "HAS_GETTER.get(flags)");
        boolean hasGetter = bool7.booleanValue();
        if (hasGetter && ProtoTypeTableUtilKt.hasReceiver(proto)) {
            empty = getReceiverParameterAnnotations(proto, AnnotatedCallableKind.PROPERTY_GETTER);
        } else {
            empty = Annotations.Companion.getEMPTY();
        }
        Annotations receiverAnnotations = empty;
        DeserializedPropertyDescriptor deserializedPropertyDescriptor = property;
        KotlinType kotlinTypeType = local.getTypeDeserializer().type(ProtoTypeTableUtilKt.returnType(proto, this.c.getTypeTable()));
        List<TypeParameterDescriptor> ownTypeParameters = local.getTypeDeserializer().getOwnTypeParameters();
        ReceiverParameterDescriptor dispatchReceiverParameter = getDispatchReceiverParameter();
        ProtoBuf.Type p0 = ProtoTypeTableUtilKt.receiverType(proto, this.c.getTypeTable());
        if (p0 == null) {
            receiverParameterDescriptor = null;
        } else {
            KotlinType receiverType = local.getTypeDeserializer().type(p0);
            deserializedPropertyDescriptor = deserializedPropertyDescriptor;
            kotlinTypeType = kotlinTypeType;
            ownTypeParameters = ownTypeParameters;
            dispatchReceiverParameter = dispatchReceiverParameter;
            if (receiverType == null) {
                receiverParameterDescriptor = null;
            } else {
                ReceiverParameterDescriptor receiverParameterDescriptorCreateExtensionReceiverParameterForCallable = DescriptorFactory.createExtensionReceiverParameterForCallable(property, receiverType, receiverAnnotations);
                deserializedPropertyDescriptor = deserializedPropertyDescriptor;
                kotlinTypeType = kotlinTypeType;
                ownTypeParameters = ownTypeParameters;
                dispatchReceiverParameter = dispatchReceiverParameter;
                receiverParameterDescriptor = receiverParameterDescriptorCreateExtensionReceiverParameterForCallable;
            }
        }
        deserializedPropertyDescriptor.setType(kotlinTypeType, ownTypeParameters, dispatchReceiverParameter, receiverParameterDescriptor);
        Boolean bool8 = Flags.HAS_ANNOTATIONS.get(flags);
        Intrinsics.checkNotNullExpressionValue(bool8, "HAS_ANNOTATIONS.get(flags)");
        int defaultAccessorFlags = Flags.getAccessorFlags(bool8.booleanValue(), Flags.VISIBILITY.get(flags), Flags.MODALITY.get(flags), false, false, false);
        if (hasGetter) {
            int getterFlags = proto.hasGetterFlags() ? proto.getGetterFlags() : defaultAccessorFlags;
            Boolean bool9 = Flags.IS_NOT_DEFAULT.get(getterFlags);
            Intrinsics.checkNotNullExpressionValue(bool9, "IS_NOT_DEFAULT.get(getterFlags)");
            boolean isNotDefault = bool9.booleanValue();
            Boolean bool10 = Flags.IS_EXTERNAL_ACCESSOR.get(getterFlags);
            Intrinsics.checkNotNullExpressionValue(bool10, "IS_EXTERNAL_ACCESSOR.get(getterFlags)");
            boolean isExternal = bool10.booleanValue();
            Boolean bool11 = Flags.IS_INLINE_ACCESSOR.get(getterFlags);
            Intrinsics.checkNotNullExpressionValue(bool11, "IS_INLINE_ACCESSOR.get(getterFlags)");
            boolean isInline = bool11.booleanValue();
            Annotations annotations2 = getAnnotations(proto, getterFlags, AnnotatedCallableKind.PROPERTY_GETTER);
            if (isNotDefault) {
                propertyGetterDescriptorImpl2 = new PropertyGetterDescriptorImpl(property, annotations2, ProtoEnumFlags.INSTANCE.modality(Flags.MODALITY.get(getterFlags)), ProtoEnumFlagsUtilsKt.descriptorVisibility(ProtoEnumFlags.INSTANCE, Flags.VISIBILITY.get(getterFlags)), !isNotDefault, isExternal, isInline, property.getKind(), null, SourceElement.NO_SOURCE);
            } else {
                PropertyGetterDescriptorImpl propertyGetterDescriptorImplCreateDefaultGetter = DescriptorFactory.createDefaultGetter(property, annotations2);
                Intrinsics.checkNotNullExpressionValue(propertyGetterDescriptorImplCreateDefaultGetter, "{\n                DescriptorFactory.createDefaultGetter(property, annotations)\n            }");
                propertyGetterDescriptorImpl2 = propertyGetterDescriptorImplCreateDefaultGetter;
            }
            PropertyGetterDescriptorImpl getter = propertyGetterDescriptorImpl2;
            getter.initialize(property.getReturnType());
            propertyGetterDescriptorImpl = getter;
        } else {
            propertyGetterDescriptorImpl = (PropertyGetterDescriptorImpl) null;
        }
        PropertyGetterDescriptorImpl getter2 = propertyGetterDescriptorImpl;
        Boolean bool12 = Flags.HAS_SETTER.get(flags);
        Intrinsics.checkNotNullExpressionValue(bool12, "HAS_SETTER.get(flags)");
        if (bool12.booleanValue()) {
            int setterFlags = proto.hasSetterFlags() ? proto.getSetterFlags() : defaultAccessorFlags;
            Boolean bool13 = Flags.IS_NOT_DEFAULT.get(setterFlags);
            Intrinsics.checkNotNullExpressionValue(bool13, "IS_NOT_DEFAULT.get(setterFlags)");
            boolean isNotDefault2 = bool13.booleanValue();
            Boolean bool14 = Flags.IS_EXTERNAL_ACCESSOR.get(setterFlags);
            Intrinsics.checkNotNullExpressionValue(bool14, "IS_EXTERNAL_ACCESSOR.get(setterFlags)");
            boolean isExternal2 = bool14.booleanValue();
            Boolean bool15 = Flags.IS_INLINE_ACCESSOR.get(setterFlags);
            Intrinsics.checkNotNullExpressionValue(bool15, "IS_INLINE_ACCESSOR.get(setterFlags)");
            boolean isInline2 = bool15.booleanValue();
            Annotations annotations3 = getAnnotations(proto, setterFlags, AnnotatedCallableKind.PROPERTY_SETTER);
            if (isNotDefault2) {
                PropertySetterDescriptorImpl setter = new PropertySetterDescriptorImpl(property, annotations3, ProtoEnumFlags.INSTANCE.modality(Flags.MODALITY.get(setterFlags)), ProtoEnumFlagsUtilsKt.descriptorVisibility(ProtoEnumFlags.INSTANCE, Flags.VISIBILITY.get(setterFlags)), !isNotDefault2, isExternal2, isInline2, property.getKind(), null, SourceElement.NO_SOURCE);
                DeserializationContext setterLocal = DeserializationContext.childContext$default(local, setter, CollectionsKt.emptyList(), null, null, null, null, 60, null);
                List valueParameters = setterLocal.getMemberDeserializer().valueParameters(CollectionsKt.listOf(proto.getSetterValueParameter()), proto, AnnotatedCallableKind.PROPERTY_SETTER);
                setter.initialize((ValueParameterDescriptor) CollectionsKt.single(valueParameters));
                propertySetterDescriptorImpl = setter;
            } else {
                PropertySetterDescriptorImpl propertySetterDescriptorImplCreateDefaultSetter = DescriptorFactory.createDefaultSetter(property, annotations3, Annotations.Companion.getEMPTY());
                Intrinsics.checkNotNullExpressionValue(propertySetterDescriptorImplCreateDefaultSetter, "{\n                DescriptorFactory.createDefaultSetter(\n                    property, annotations,\n                    Annotations.EMPTY /* Otherwise the setter is not default, see DescriptorResolver.resolvePropertySetterDescriptor */\n                )\n            }");
                propertySetterDescriptorImpl = propertySetterDescriptorImplCreateDefaultSetter;
            }
        } else {
            propertySetterDescriptorImpl = (PropertySetterDescriptorImpl) null;
        }
        PropertySetterDescriptorImpl setter2 = propertySetterDescriptorImpl;
        Boolean bool16 = Flags.HAS_CONSTANT.get(flags);
        Intrinsics.checkNotNullExpressionValue(bool16, "HAS_CONSTANT.get(flags)");
        if (bool16.booleanValue()) {
            property.setCompileTimeInitializer(this.c.getStorageManager().createNullableLazyValue(new Function0<ConstantValue<?>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer.loadProperty.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                @Nullable
                public final ConstantValue<?> invoke() {
                    ProtoContainer container = MemberDeserializer.this.asProtoContainer(MemberDeserializer.this.c.getContainingDeclaration());
                    Intrinsics.checkNotNull(container);
                    AnnotationAndConstantLoader<AnnotationDescriptor, ConstantValue<?>> annotationAndConstantLoader = MemberDeserializer.this.c.getComponents().getAnnotationAndConstantLoader();
                    ProtoBuf.Property property2 = proto;
                    KotlinType returnType = property.getReturnType();
                    Intrinsics.checkNotNullExpressionValue(returnType, "property.returnType");
                    return annotationAndConstantLoader.loadPropertyConstant(container, property2, returnType);
                }
            }));
        }
        property.initialize(getter2, setter2, new FieldDescriptorImpl(getPropertyFieldAnnotations(proto, false), property), new FieldDescriptorImpl(getPropertyFieldAnnotations(proto, true), property), checkExperimentalCoroutine(property, local.getTypeDeserializer()));
        return property;
    }

    private final DeserializedMemberDescriptor.CoroutinesCompatibilityMode checkExperimentalCoroutine(DeserializedMemberDescriptor $this$checkExperimentalCoroutine, TypeDeserializer typeDeserializer) {
        if (!versionAndReleaseCoroutinesMismatch($this$checkExperimentalCoroutine)) {
            return DeserializedMemberDescriptor.CoroutinesCompatibilityMode.COMPATIBLE;
        }
        forceUpperBoundsComputation(typeDeserializer);
        if (typeDeserializer.getExperimentalSuspendFunctionTypeEncountered()) {
            return DeserializedMemberDescriptor.CoroutinesCompatibilityMode.INCOMPATIBLE;
        }
        return DeserializedMemberDescriptor.CoroutinesCompatibilityMode.COMPATIBLE;
    }

    private final void forceUpperBoundsComputation(TypeDeserializer typeDeserializer) {
        Iterable $this$forEach$iv = typeDeserializer.getOwnTypeParameters();
        for (Object element$iv : $this$forEach$iv) {
            TypeParameterDescriptor it = (TypeParameterDescriptor) element$iv;
            it.getUpperBounds();
        }
    }

    private final void initializeWithCoroutinesExperimentalityStatus(DeserializedSimpleFunctionDescriptor $this$initializeWithCoroutinesExperimentalityStatus, ReceiverParameterDescriptor extensionReceiverParameter, ReceiverParameterDescriptor dispatchReceiverParameter, List<? extends TypeParameterDescriptor> list, List<? extends ValueParameterDescriptor> list2, KotlinType unsubstitutedReturnType, Modality modality, DescriptorVisibility visibility, Map<? extends CallableDescriptor.UserDataKey<?>, ?> map, boolean isSuspend) {
        $this$initializeWithCoroutinesExperimentalityStatus.initialize(extensionReceiverParameter, dispatchReceiverParameter, list, list2, unsubstitutedReturnType, modality, visibility, map, computeExperimentalityModeForFunctions($this$initializeWithCoroutinesExperimentalityStatus, extensionReceiverParameter, list2, list, unsubstitutedReturnType, isSuspend));
    }

    private final DeserializedMemberDescriptor.CoroutinesCompatibilityMode computeExperimentalityModeForFunctions(DeserializedCallableMemberDescriptor $this$computeExperimentalityModeForFunctions, ReceiverParameterDescriptor extensionReceiverParameter, Collection<? extends ValueParameterDescriptor> collection, Collection<? extends TypeParameterDescriptor> collection2, KotlinType returnType, boolean isSuspend) {
        boolean z;
        boolean z2;
        DeserializedMemberDescriptor.CoroutinesCompatibilityMode coroutinesCompatibilityMode;
        boolean z3;
        if (versionAndReleaseCoroutinesMismatch($this$computeExperimentalityModeForFunctions) && !Intrinsics.areEqual(DescriptorUtilsKt.fqNameOrNull($this$computeExperimentalityModeForFunctions), SuspendFunctionTypeUtilKt.KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME)) {
            Collection<? extends ValueParameterDescriptor> $this$map$iv = collection;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                destination$iv$iv.add(((ValueParameterDescriptor) item$iv$iv).getType());
            }
            Iterable types = CollectionsKt.plus(destination$iv$iv, (Iterable) CollectionsKt.listOfNotNull(extensionReceiverParameter == null ? null : extensionReceiverParameter.getType()));
            if (Intrinsics.areEqual((Object) (returnType == null ? null : Boolean.valueOf(containsSuspendFunctionType(returnType))), (Object) true)) {
                return DeserializedMemberDescriptor.CoroutinesCompatibilityMode.INCOMPATIBLE;
            }
            Collection<? extends TypeParameterDescriptor> $this$any$iv = collection2;
            if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
                Iterator it = $this$any$iv.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    Object element$iv = it.next();
                    TypeParameterDescriptor typeParameter = (TypeParameterDescriptor) element$iv;
                    Iterable upperBounds = typeParameter.getUpperBounds();
                    Intrinsics.checkNotNullExpressionValue(upperBounds, "typeParameter.upperBounds");
                    Iterable $this$any$iv2 = upperBounds;
                    if (!($this$any$iv2 instanceof Collection) || !((Collection) $this$any$iv2).isEmpty()) {
                        Iterator it2 = $this$any$iv2.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                z2 = false;
                                break;
                            }
                            Object element$iv2 = it2.next();
                            KotlinType it3 = (KotlinType) element$iv2;
                            Intrinsics.checkNotNullExpressionValue(it3, "it");
                            if (containsSuspendFunctionType(it3)) {
                                z2 = true;
                                break;
                            }
                        }
                    } else {
                        z2 = false;
                    }
                    if (z2) {
                        z = true;
                        break;
                    }
                }
            } else {
                z = false;
            }
            if (z) {
                return DeserializedMemberDescriptor.CoroutinesCompatibilityMode.INCOMPATIBLE;
            }
            Iterable $this$map$iv2 = types;
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
            for (Object item$iv$iv2 : $this$map$iv2) {
                KotlinType type = (KotlinType) item$iv$iv2;
                Intrinsics.checkNotNullExpressionValue(type, "type");
                if (!FunctionTypesKt.isSuspendFunctionType(type) || type.getArguments().size() > 3) {
                    coroutinesCompatibilityMode = containsSuspendFunctionType(type) ? DeserializedMemberDescriptor.CoroutinesCompatibilityMode.INCOMPATIBLE : DeserializedMemberDescriptor.CoroutinesCompatibilityMode.COMPATIBLE;
                } else {
                    Iterable $this$any$iv3 = type.getArguments();
                    if (!($this$any$iv3 instanceof Collection) || !((Collection) $this$any$iv3).isEmpty()) {
                        Iterator it4 = $this$any$iv3.iterator();
                        while (true) {
                            if (!it4.hasNext()) {
                                z3 = false;
                                break;
                            }
                            Object element$iv3 = it4.next();
                            KotlinType type2 = ((TypeProjection) element$iv3).getType();
                            Intrinsics.checkNotNullExpressionValue(type2, "it.type");
                            if (containsSuspendFunctionType(type2)) {
                                z3 = true;
                                break;
                            }
                        }
                    } else {
                        z3 = false;
                    }
                    coroutinesCompatibilityMode = z3 ? DeserializedMemberDescriptor.CoroutinesCompatibilityMode.INCOMPATIBLE : DeserializedMemberDescriptor.CoroutinesCompatibilityMode.NEEDS_WRAPPER;
                }
                destination$iv$iv2.add(coroutinesCompatibilityMode);
            }
            DeserializedMemberDescriptor.CoroutinesCompatibilityMode coroutinesCompatibilityMode2 = (DeserializedMemberDescriptor.CoroutinesCompatibilityMode) CollectionsKt.maxOrNull(destination$iv$iv2);
            DeserializedMemberDescriptor.CoroutinesCompatibilityMode maxFromParameters = coroutinesCompatibilityMode2 == null ? DeserializedMemberDescriptor.CoroutinesCompatibilityMode.COMPATIBLE : coroutinesCompatibilityMode2;
            return (DeserializedMemberDescriptor.CoroutinesCompatibilityMode) ComparisonsKt.maxOf(isSuspend ? DeserializedMemberDescriptor.CoroutinesCompatibilityMode.NEEDS_WRAPPER : DeserializedMemberDescriptor.CoroutinesCompatibilityMode.COMPATIBLE, maxFromParameters);
        }
        return DeserializedMemberDescriptor.CoroutinesCompatibilityMode.COMPATIBLE;
    }

    private final boolean containsSuspendFunctionType(KotlinType $this$containsSuspendFunctionType) {
        return TypeUtilsKt.contains($this$containsSuspendFunctionType, new PropertyReference1() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer.containsSuspendFunctionType.1
            @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
            @NotNull
            public String getName() {
                return "isSuspendFunctionType";
            }

            @Override // kotlin.jvm.internal.CallableReference
            @NotNull
            public KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinPackage(FunctionTypesKt.class, "deserialization");
            }

            @Override // kotlin.jvm.internal.CallableReference
            @NotNull
            public String getSignature() {
                return "isSuspendFunctionType(Lorg/jetbrains/kotlin/types/KotlinType;)Z";
            }

            @Override // kotlin.reflect.KProperty1
            @Nullable
            public Object get(@Nullable Object receiver0) {
                return Boolean.valueOf(FunctionTypesKt.isSuspendFunctionType((KotlinType) receiver0));
            }
        });
    }

    private final boolean versionAndReleaseCoroutinesMismatch(DeserializedMemberDescriptor $this$versionAndReleaseCoroutinesMismatch) {
        boolean z;
        if (this.c.getComponents().getConfiguration().getReleaseCoroutines()) {
            Iterable $this$none$iv = $this$versionAndReleaseCoroutinesMismatch.getVersionRequirements();
            if (!($this$none$iv instanceof Collection) || !((Collection) $this$none$iv).isEmpty()) {
                Iterator it = $this$none$iv.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Object element$iv = it.next();
                        VersionRequirement it2 = (VersionRequirement) element$iv;
                        if (Intrinsics.areEqual(it2.getVersion(), new VersionRequirement.Version(1, 3, 0, 4, null)) && it2.getKind() == ProtoBuf.VersionRequirement.VersionKind.LANGUAGE_VERSION) {
                            z = false;
                            break;
                        }
                    } else {
                        z = true;
                        break;
                    }
                }
            } else {
                z = true;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    private final int loadOldFlags(int oldFlags) {
        int lowSixBits = oldFlags & 63;
        int rest = (oldFlags >> 8) << 6;
        return lowSixBits + rest;
    }

    @NotNull
    public final SimpleFunctionDescriptor loadFunction(@NotNull ProtoBuf.Function proto) {
        Annotations empty;
        VersionRequirementTable versionRequirementTable;
        ReceiverParameterDescriptor receiverParameterDescriptor;
        Intrinsics.checkNotNullParameter(proto, "proto");
        int flags = proto.hasFlags() ? proto.getFlags() : loadOldFlags(proto.getOldFlags());
        Annotations annotations = getAnnotations(proto, flags, AnnotatedCallableKind.FUNCTION);
        if (ProtoTypeTableUtilKt.hasReceiver(proto)) {
            empty = getReceiverParameterAnnotations(proto, AnnotatedCallableKind.FUNCTION);
        } else {
            empty = Annotations.Companion.getEMPTY();
        }
        Annotations receiverAnnotations = empty;
        if (Intrinsics.areEqual(DescriptorUtilsKt.getFqNameSafe(this.c.getContainingDeclaration()).child(NameResolverUtilKt.getName(this.c.getNameResolver(), proto.getName())), SuspendFunctionTypeUtilKt.KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME)) {
            versionRequirementTable = VersionRequirementTable.Companion.getEMPTY();
        } else {
            versionRequirementTable = this.c.getVersionRequirementTable();
        }
        VersionRequirementTable versionRequirementTable2 = versionRequirementTable;
        DeserializedSimpleFunctionDescriptor function = new DeserializedSimpleFunctionDescriptor(this.c.getContainingDeclaration(), null, annotations, NameResolverUtilKt.getName(this.c.getNameResolver(), proto.getName()), ProtoEnumFlagsUtilsKt.memberKind(ProtoEnumFlags.INSTANCE, Flags.MEMBER_KIND.get(flags)), proto, this.c.getNameResolver(), this.c.getTypeTable(), versionRequirementTable2, this.c.getContainerSource(), null, 1024, null);
        List<ProtoBuf.TypeParameter> typeParameterList = proto.getTypeParameterList();
        Intrinsics.checkNotNullExpressionValue(typeParameterList, "proto.typeParameterList");
        DeserializationContext local = DeserializationContext.childContext$default(this.c, function, typeParameterList, null, null, null, null, 60, null);
        MemberDeserializer memberDeserializer = this;
        DeserializedSimpleFunctionDescriptor deserializedSimpleFunctionDescriptor = function;
        ProtoBuf.Type p0 = ProtoTypeTableUtilKt.receiverType(proto, this.c.getTypeTable());
        if (p0 == null) {
            receiverParameterDescriptor = null;
        } else {
            KotlinType receiverType = local.getTypeDeserializer().type(p0);
            memberDeserializer = memberDeserializer;
            deserializedSimpleFunctionDescriptor = deserializedSimpleFunctionDescriptor;
            if (receiverType == null) {
                receiverParameterDescriptor = null;
            } else {
                ReceiverParameterDescriptor receiverParameterDescriptorCreateExtensionReceiverParameterForCallable = DescriptorFactory.createExtensionReceiverParameterForCallable(function, receiverType, receiverAnnotations);
                memberDeserializer = memberDeserializer;
                deserializedSimpleFunctionDescriptor = deserializedSimpleFunctionDescriptor;
                receiverParameterDescriptor = receiverParameterDescriptorCreateExtensionReceiverParameterForCallable;
            }
        }
        ReceiverParameterDescriptor dispatchReceiverParameter = getDispatchReceiverParameter();
        List<TypeParameterDescriptor> ownTypeParameters = local.getTypeDeserializer().getOwnTypeParameters();
        MemberDeserializer memberDeserializer2 = local.getMemberDeserializer();
        List<ProtoBuf.ValueParameter> valueParameterList = proto.getValueParameterList();
        Intrinsics.checkNotNullExpressionValue(valueParameterList, "proto.valueParameterList");
        List<ValueParameterDescriptor> listValueParameters = memberDeserializer2.valueParameters(valueParameterList, proto, AnnotatedCallableKind.FUNCTION);
        KotlinType kotlinTypeType = local.getTypeDeserializer().type(ProtoTypeTableUtilKt.returnType(proto, this.c.getTypeTable()));
        Modality modality = ProtoEnumFlags.INSTANCE.modality(Flags.MODALITY.get(flags));
        DescriptorVisibility descriptorVisibility = ProtoEnumFlagsUtilsKt.descriptorVisibility(ProtoEnumFlags.INSTANCE, Flags.VISIBILITY.get(flags));
        Map<? extends CallableDescriptor.UserDataKey<?>, ?> mapEmptyMap = MapsKt.emptyMap();
        Boolean bool = Flags.IS_SUSPEND.get(flags);
        Intrinsics.checkNotNullExpressionValue(bool, "IS_SUSPEND.get(flags)");
        memberDeserializer.initializeWithCoroutinesExperimentalityStatus(deserializedSimpleFunctionDescriptor, receiverParameterDescriptor, dispatchReceiverParameter, ownTypeParameters, listValueParameters, kotlinTypeType, modality, descriptorVisibility, mapEmptyMap, bool.booleanValue());
        Boolean bool2 = Flags.IS_OPERATOR.get(flags);
        Intrinsics.checkNotNullExpressionValue(bool2, "IS_OPERATOR.get(flags)");
        function.setOperator(bool2.booleanValue());
        Boolean bool3 = Flags.IS_INFIX.get(flags);
        Intrinsics.checkNotNullExpressionValue(bool3, "IS_INFIX.get(flags)");
        function.setInfix(bool3.booleanValue());
        Boolean bool4 = Flags.IS_EXTERNAL_FUNCTION.get(flags);
        Intrinsics.checkNotNullExpressionValue(bool4, "IS_EXTERNAL_FUNCTION.get(flags)");
        function.setExternal(bool4.booleanValue());
        Boolean bool5 = Flags.IS_INLINE.get(flags);
        Intrinsics.checkNotNullExpressionValue(bool5, "IS_INLINE.get(flags)");
        function.setInline(bool5.booleanValue());
        Boolean bool6 = Flags.IS_TAILREC.get(flags);
        Intrinsics.checkNotNullExpressionValue(bool6, "IS_TAILREC.get(flags)");
        function.setTailrec(bool6.booleanValue());
        Boolean bool7 = Flags.IS_SUSPEND.get(flags);
        Intrinsics.checkNotNullExpressionValue(bool7, "IS_SUSPEND.get(flags)");
        function.setSuspend(bool7.booleanValue());
        Boolean bool8 = Flags.IS_EXPECT_FUNCTION.get(flags);
        Intrinsics.checkNotNullExpressionValue(bool8, "IS_EXPECT_FUNCTION.get(flags)");
        function.setExpect(bool8.booleanValue());
        function.setHasStableParameterNames(!Flags.IS_FUNCTION_WITH_NON_STABLE_PARAMETER_NAMES.get(flags).booleanValue());
        Pair mapValueForContract = this.c.getComponents().getContractDeserializer().deserializeContractFromFunction(proto, function, this.c.getTypeTable(), local.getTypeDeserializer());
        if (mapValueForContract != null) {
            function.putInUserDataMap(mapValueForContract.getFirst(), mapValueForContract.getSecond());
        }
        return function;
    }

    @NotNull
    public final TypeAliasDescriptor loadTypeAlias(@NotNull ProtoBuf.TypeAlias proto) {
        Intrinsics.checkNotNullParameter(proto, "proto");
        Annotations.Companion companion = Annotations.Companion;
        Iterable annotationList = proto.getAnnotationList();
        Intrinsics.checkNotNullExpressionValue(annotationList, "proto.annotationList");
        Iterable $this$map$iv = annotationList;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            ProtoBuf.Annotation it = (ProtoBuf.Annotation) item$iv$iv;
            AnnotationDeserializer annotationDeserializer = this.annotationDeserializer;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            destination$iv$iv.add(annotationDeserializer.deserializeAnnotation(it, this.c.getNameResolver()));
        }
        Annotations annotations = companion.create((List) destination$iv$iv);
        DescriptorVisibility visibility = ProtoEnumFlagsUtilsKt.descriptorVisibility(ProtoEnumFlags.INSTANCE, Flags.VISIBILITY.get(proto.getFlags()));
        DeserializedTypeAliasDescriptor typeAlias = new DeserializedTypeAliasDescriptor(this.c.getStorageManager(), this.c.getContainingDeclaration(), annotations, NameResolverUtilKt.getName(this.c.getNameResolver(), proto.getName()), visibility, proto, this.c.getNameResolver(), this.c.getTypeTable(), this.c.getVersionRequirementTable(), this.c.getContainerSource());
        List<ProtoBuf.TypeParameter> typeParameterList = proto.getTypeParameterList();
        Intrinsics.checkNotNullExpressionValue(typeParameterList, "proto.typeParameterList");
        DeserializationContext local = DeserializationContext.childContext$default(this.c, typeAlias, typeParameterList, null, null, null, null, 60, null);
        typeAlias.initialize(local.getTypeDeserializer().getOwnTypeParameters(), local.getTypeDeserializer().simpleType(ProtoTypeTableUtilKt.underlyingType(proto, this.c.getTypeTable()), false), local.getTypeDeserializer().simpleType(ProtoTypeTableUtilKt.expandedType(proto, this.c.getTypeTable()), false), checkExperimentalCoroutine(typeAlias, local.getTypeDeserializer()));
        return typeAlias;
    }

    private final ReceiverParameterDescriptor getDispatchReceiverParameter() {
        DeclarationDescriptor containingDeclaration = this.c.getContainingDeclaration();
        ClassDescriptor classDescriptor = containingDeclaration instanceof ClassDescriptor ? (ClassDescriptor) containingDeclaration : null;
        if (classDescriptor == null) {
            return null;
        }
        return classDescriptor.getThisAsReceiverParameter();
    }

    @NotNull
    public final ClassConstructorDescriptor loadConstructor(@NotNull ProtoBuf.Constructor proto, boolean isPrimary) {
        TypeDeserializer typeDeserializer;
        DeserializedMemberDescriptor.CoroutinesCompatibilityMode coroutinesCompatibilityModeComputeExperimentalityModeForFunctions;
        Intrinsics.checkNotNullParameter(proto, "proto");
        ClassDescriptor classDescriptor = (ClassDescriptor) this.c.getContainingDeclaration();
        DeserializedClassConstructorDescriptor descriptor = new DeserializedClassConstructorDescriptor(classDescriptor, null, getAnnotations(proto, proto.getFlags(), AnnotatedCallableKind.FUNCTION), isPrimary, CallableMemberDescriptor.Kind.DECLARATION, proto, this.c.getNameResolver(), this.c.getTypeTable(), this.c.getVersionRequirementTable(), this.c.getContainerSource(), null, 1024, null);
        DeserializationContext local = DeserializationContext.childContext$default(this.c, descriptor, CollectionsKt.emptyList(), null, null, null, null, 60, null);
        MemberDeserializer memberDeserializer = local.getMemberDeserializer();
        List<ProtoBuf.ValueParameter> valueParameterList = proto.getValueParameterList();
        Intrinsics.checkNotNullExpressionValue(valueParameterList, "proto.valueParameterList");
        descriptor.initialize(memberDeserializer.valueParameters(valueParameterList, proto, AnnotatedCallableKind.FUNCTION), ProtoEnumFlagsUtilsKt.descriptorVisibility(ProtoEnumFlags.INSTANCE, Flags.VISIBILITY.get(proto.getFlags())));
        descriptor.setReturnType(classDescriptor.getDefaultType());
        descriptor.setHasStableParameterNames(!Flags.IS_CONSTRUCTOR_WITH_NON_STABLE_PARAMETER_NAMES.get(proto.getFlags()).booleanValue());
        DeclarationDescriptor containingDeclaration = this.c.getContainingDeclaration();
        DeserializedClassDescriptor deserializedClassDescriptor = containingDeclaration instanceof DeserializedClassDescriptor ? (DeserializedClassDescriptor) containingDeclaration : null;
        DeserializationContext c = deserializedClassDescriptor == null ? null : deserializedClassDescriptor.getC();
        Boolean boolValueOf = (c == null || (typeDeserializer = c.getTypeDeserializer()) == null) ? null : Boolean.valueOf(typeDeserializer.getExperimentalSuspendFunctionTypeEncountered());
        boolean doesClassContainIncompatibility = Intrinsics.areEqual((Object) boolValueOf, (Object) true) && versionAndReleaseCoroutinesMismatch(descriptor);
        if (doesClassContainIncompatibility) {
            coroutinesCompatibilityModeComputeExperimentalityModeForFunctions = DeserializedMemberDescriptor.CoroutinesCompatibilityMode.INCOMPATIBLE;
        } else {
            List<ValueParameterDescriptor> valueParameters = descriptor.getValueParameters();
            Intrinsics.checkNotNullExpressionValue(valueParameters, "descriptor.valueParameters");
            List<TypeParameterDescriptor> typeParameters = descriptor.getTypeParameters();
            Intrinsics.checkNotNullExpressionValue(typeParameters, "descriptor.typeParameters");
            coroutinesCompatibilityModeComputeExperimentalityModeForFunctions = computeExperimentalityModeForFunctions(descriptor, null, valueParameters, typeParameters, descriptor.getReturnType(), false);
        }
        descriptor.setCoroutinesExperimentalCompatibilityMode$deserialization(coroutinesCompatibilityModeComputeExperimentalityModeForFunctions);
        return descriptor;
    }

    private final Annotations getAnnotations(final MessageLite proto, int flags, final AnnotatedCallableKind kind) {
        if (!Flags.HAS_ANNOTATIONS.get(flags).booleanValue()) {
            return Annotations.Companion.getEMPTY();
        }
        return new NonEmptyDeserializedAnnotations(this.c.getStorageManager(), new Function0<List<? extends AnnotationDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer.getAnnotations.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final List<? extends AnnotationDescriptor> invoke() {
                List<? extends AnnotationDescriptor> list;
                ProtoContainer it = MemberDeserializer.this.asProtoContainer(MemberDeserializer.this.c.getContainingDeclaration());
                if (it == null) {
                    list = null;
                } else {
                    list = CollectionsKt.toList(MemberDeserializer.this.c.getComponents().getAnnotationAndConstantLoader().loadCallableAnnotations(it, proto, kind));
                }
                List<? extends AnnotationDescriptor> list2 = list;
                return list2 != null ? list2 : CollectionsKt.emptyList();
            }
        });
    }

    private final Annotations getPropertyFieldAnnotations(final ProtoBuf.Property proto, final boolean isDelegate) {
        if (!Flags.HAS_ANNOTATIONS.get(proto.getFlags()).booleanValue()) {
            return Annotations.Companion.getEMPTY();
        }
        return new NonEmptyDeserializedAnnotations(this.c.getStorageManager(), new Function0<List<? extends AnnotationDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer.getPropertyFieldAnnotations.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final List<? extends AnnotationDescriptor> invoke() {
                List<? extends AnnotationDescriptor> list;
                ProtoContainer it = MemberDeserializer.this.asProtoContainer(MemberDeserializer.this.c.getContainingDeclaration());
                if (it == null) {
                    list = null;
                } else {
                    boolean z = isDelegate;
                    MemberDeserializer memberDeserializer = MemberDeserializer.this;
                    ProtoBuf.Property property = proto;
                    list = z ? CollectionsKt.toList(memberDeserializer.c.getComponents().getAnnotationAndConstantLoader().loadPropertyDelegateFieldAnnotations(it, property)) : CollectionsKt.toList(memberDeserializer.c.getComponents().getAnnotationAndConstantLoader().loadPropertyBackingFieldAnnotations(it, property));
                }
                List<? extends AnnotationDescriptor> list2 = list;
                return list2 != null ? list2 : CollectionsKt.emptyList();
            }
        });
    }

    private final Annotations getReceiverParameterAnnotations(final MessageLite proto, final AnnotatedCallableKind kind) {
        return new DeserializedAnnotations(this.c.getStorageManager(), new Function0<List<? extends AnnotationDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer.getReceiverParameterAnnotations.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final List<? extends AnnotationDescriptor> invoke() {
                List<AnnotationDescriptor> listLoadExtensionReceiverParameterAnnotations;
                ProtoContainer it = MemberDeserializer.this.asProtoContainer(MemberDeserializer.this.c.getContainingDeclaration());
                if (it == null) {
                    listLoadExtensionReceiverParameterAnnotations = null;
                } else {
                    listLoadExtensionReceiverParameterAnnotations = MemberDeserializer.this.c.getComponents().getAnnotationAndConstantLoader().loadExtensionReceiverParameterAnnotations(it, proto, kind);
                }
                List<AnnotationDescriptor> list = listLoadExtensionReceiverParameterAnnotations;
                return list != null ? list : CollectionsKt.emptyList();
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00e2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.util.List<kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor> valueParameters(java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter> r15, final kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r16, final kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind r17) {
        /*
            Method dump skipped, instructions count: 530
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer.valueParameters(java.util.List, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite, kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind):java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ProtoContainer asProtoContainer(DeclarationDescriptor $this$asProtoContainer) {
        if ($this$asProtoContainer instanceof PackageFragmentDescriptor) {
            return new ProtoContainer.Package(((PackageFragmentDescriptor) $this$asProtoContainer).getFqName(), this.c.getNameResolver(), this.c.getTypeTable(), this.c.getContainerSource());
        }
        if ($this$asProtoContainer instanceof DeserializedClassDescriptor) {
            return ((DeserializedClassDescriptor) $this$asProtoContainer).getThisAsProtoContainer$deserialization();
        }
        return null;
    }
}
