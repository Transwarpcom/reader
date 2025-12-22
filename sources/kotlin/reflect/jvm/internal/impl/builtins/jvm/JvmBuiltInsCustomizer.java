package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModalityUtilsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilterKt;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PackageFragmentDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureBuildingUtilsKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor;
import kotlin.reflect.jvm.internal.impl.storage.CacheWithNotNullValues;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.LazyWrappedType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.utils.DFS;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;
import org.jetbrains.annotations.NotNull;

/* compiled from: JvmBuiltInsCustomizer.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/builtins/jvm/JvmBuiltInsCustomizer.class */
public final class JvmBuiltInsCustomizer implements AdditionalClassPartsProvider, PlatformDependentDeclarationFilter {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JvmBuiltInsCustomizer.class), "settings", "getSettings()Lorg/jetbrains/kotlin/builtins/jvm/JvmBuiltIns$Settings;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JvmBuiltInsCustomizer.class), "cloneableType", "getCloneableType()Lorg/jetbrains/kotlin/types/SimpleType;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JvmBuiltInsCustomizer.class), "notConsideredDeprecation", "getNotConsideredDeprecation()Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;"))};

    @NotNull
    private final ModuleDescriptor moduleDescriptor;

    @NotNull
    private final JavaToKotlinClassMapper j2kClassMapper;

    @NotNull
    private final NotNullLazyValue settings$delegate;

    @NotNull
    private final KotlinType mockSerializableType;

    @NotNull
    private final NotNullLazyValue cloneableType$delegate;

    @NotNull
    private final CacheWithNotNullValues<FqName, ClassDescriptor> javaAnalogueClassesWithCustomSupertypeCache;

    @NotNull
    private final NotNullLazyValue notConsideredDeprecation$delegate;

    /* compiled from: JvmBuiltInsCustomizer.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/builtins/jvm/JvmBuiltInsCustomizer$JDKMemberStatus.class */
    private enum JDKMemberStatus {
        HIDDEN,
        VISIBLE,
        NOT_CONSIDERED,
        DROP
    }

    /* compiled from: JvmBuiltInsCustomizer.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/builtins/jvm/JvmBuiltInsCustomizer$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[JDKMemberStatus.values().length];
            iArr[JDKMemberStatus.HIDDEN.ordinal()] = 1;
            iArr[JDKMemberStatus.NOT_CONSIDERED.ordinal()] = 2;
            iArr[JDKMemberStatus.DROP.ordinal()] = 3;
            iArr[JDKMemberStatus.VISIBLE.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public JvmBuiltInsCustomizer(@NotNull ModuleDescriptor moduleDescriptor, @NotNull final StorageManager storageManager, @NotNull Function0<JvmBuiltIns.Settings> settingsComputation) {
        Intrinsics.checkNotNullParameter(moduleDescriptor, "moduleDescriptor");
        Intrinsics.checkNotNullParameter(storageManager, "storageManager");
        Intrinsics.checkNotNullParameter(settingsComputation, "settingsComputation");
        this.moduleDescriptor = moduleDescriptor;
        this.j2kClassMapper = JavaToKotlinClassMapper.INSTANCE;
        this.settings$delegate = storageManager.createLazyValue(settingsComputation);
        this.mockSerializableType = createMockJavaIoSerializableType(storageManager);
        this.cloneableType$delegate = storageManager.createLazyValue(new Function0<SimpleType>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsCustomizer$cloneableType$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final SimpleType invoke() {
                return FindClassInModuleKt.findNonGenericClassAcrossDependencies(this.this$0.getSettings().getOwnerModuleDescriptor(), JvmBuiltInClassDescriptorFactory.Companion.getCLONEABLE_CLASS_ID(), new NotFoundClasses(storageManager, this.this$0.getSettings().getOwnerModuleDescriptor())).getDefaultType();
            }
        });
        this.javaAnalogueClassesWithCustomSupertypeCache = storageManager.createCacheWithNotNullValues();
        this.notConsideredDeprecation$delegate = storageManager.createLazyValue(new Function0<Annotations>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsCustomizer$notConsideredDeprecation$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final Annotations invoke() {
                AnnotationDescriptor annotation = AnnotationUtilKt.createDeprecatedAnnotation$default(this.this$0.moduleDescriptor.getBuiltIns(), "This member is not fully supported by Kotlin compiler, so it may be absent or have different signature in next major version", null, null, 6, null);
                return Annotations.Companion.create(CollectionsKt.listOf(annotation));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JvmBuiltIns.Settings getSettings() {
        return (JvmBuiltIns.Settings) StorageKt.getValue(this.settings$delegate, this, (KProperty<?>) $$delegatedProperties[0]);
    }

    private final SimpleType getCloneableType() {
        return (SimpleType) StorageKt.getValue(this.cloneableType$delegate, this, (KProperty<?>) $$delegatedProperties[1]);
    }

    private final Annotations getNotConsideredDeprecation() {
        return (Annotations) StorageKt.getValue(this.notConsideredDeprecation$delegate, this, (KProperty<?>) $$delegatedProperties[2]);
    }

    private final KotlinType createMockJavaIoSerializableType(StorageManager $this$createMockJavaIoSerializableType) {
        final ModuleDescriptor moduleDescriptor = this.moduleDescriptor;
        final FqName fqName = new FqName("java.io");
        PackageFragmentDescriptorImpl packageFragmentDescriptorImpl = new PackageFragmentDescriptorImpl(moduleDescriptor, fqName) { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsCustomizer$createMockJavaIoSerializableType$mockJavaIoPackageFragment$1
            @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor
            @NotNull
            public MemberScope.Empty getMemberScope() {
                return MemberScope.Empty.INSTANCE;
            }
        };
        List superTypes = CollectionsKt.listOf(new LazyWrappedType($this$createMockJavaIoSerializableType, new Function0<KotlinType>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsCustomizer$createMockJavaIoSerializableType$superTypes$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final KotlinType invoke() {
                SimpleType anyType = this.this$0.moduleDescriptor.getBuiltIns().getAnyType();
                Intrinsics.checkNotNullExpressionValue(anyType, "moduleDescriptor.builtIns.anyType");
                return anyType;
            }
        }));
        ClassDescriptorImpl mockSerializableClass = new ClassDescriptorImpl(packageFragmentDescriptorImpl, Name.identifier("Serializable"), Modality.ABSTRACT, ClassKind.INTERFACE, superTypes, SourceElement.NO_SOURCE, false, $this$createMockJavaIoSerializableType);
        mockSerializableClass.initialize(MemberScope.Empty.INSTANCE, SetsKt.emptySet(), null);
        SimpleType defaultType = mockSerializableClass.getDefaultType();
        Intrinsics.checkNotNullExpressionValue(defaultType, "mockSerializableClass.defaultType");
        return defaultType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider
    @NotNull
    public Collection<KotlinType> getSupertypes(@NotNull ClassDescriptor classDescriptor) {
        Intrinsics.checkNotNullParameter(classDescriptor, "classDescriptor");
        FqNameUnsafe fqName = DescriptorUtilsKt.getFqNameUnsafe(classDescriptor);
        if (!JvmBuiltInsSignatures.INSTANCE.isArrayOrPrimitiveArray(fqName)) {
            return JvmBuiltInsSignatures.INSTANCE.isSerializableInJava(fqName) ? CollectionsKt.listOf(this.mockSerializableType) : CollectionsKt.emptyList();
        }
        SimpleType cloneableType = getCloneableType();
        Intrinsics.checkNotNullExpressionValue(cloneableType, "cloneableType");
        return CollectionsKt.listOf((Object[]) new KotlinType[]{cloneableType, this.mockSerializableType});
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider
    @NotNull
    public Collection<SimpleFunctionDescriptor> getFunctions(@NotNull final Name name, @NotNull ClassDescriptor classDescriptor) {
        SimpleFunctionDescriptor simpleFunctionDescriptor;
        boolean z;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(classDescriptor, "classDescriptor");
        if (Intrinsics.areEqual(name, CloneableClassScope.Companion.getCLONE_NAME()) && (classDescriptor instanceof DeserializedClassDescriptor) && KotlinBuiltIns.isArrayOrPrimitiveArray(classDescriptor)) {
            Iterable functionList = ((DeserializedClassDescriptor) classDescriptor).getClassProto().getFunctionList();
            Intrinsics.checkNotNullExpressionValue(functionList, "classDescriptor.classProto.functionList");
            Iterable $this$any$iv = functionList;
            if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                Iterator it = $this$any$iv.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Object element$iv = it.next();
                        ProtoBuf.Function functionProto = (ProtoBuf.Function) element$iv;
                        if (Intrinsics.areEqual(NameResolverUtilKt.getName(((DeserializedClassDescriptor) classDescriptor).getC().getNameResolver(), functionProto.getName()), CloneableClassScope.Companion.getCLONE_NAME())) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
            } else {
                z = false;
            }
            if (z) {
                return CollectionsKt.emptyList();
            }
            return CollectionsKt.listOf(createCloneForArray((DeserializedClassDescriptor) classDescriptor, (SimpleFunctionDescriptor) CollectionsKt.single(getCloneableType().getMemberScope().getContributedFunctions(name, NoLookupLocation.FROM_BUILTINS))));
        }
        if (!getSettings().isAdditionalBuiltInsFeatureSupported()) {
            return CollectionsKt.emptyList();
        }
        Iterable $this$mapNotNull$iv = getAdditionalFunctions(classDescriptor, new Function1<MemberScope, Collection<? extends SimpleFunctionDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsCustomizer.getFunctions.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final Collection<SimpleFunctionDescriptor> invoke(@NotNull MemberScope it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                return it2.getContributedFunctions(name, NoLookupLocation.FROM_BUILTINS);
            }
        });
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            SimpleFunctionDescriptor additionalMember = (SimpleFunctionDescriptor) element$iv$iv$iv;
            FunctionDescriptor functionDescriptorSubstitute = additionalMember.substitute(MappingUtilKt.createMappedTypeParametersSubstitution((ClassDescriptor) additionalMember.getContainingDeclaration(), classDescriptor).buildSubstitutor());
            if (functionDescriptorSubstitute == null) {
                throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor");
            }
            SimpleFunctionDescriptor substitutedWithKotlinTypeParameters = (SimpleFunctionDescriptor) functionDescriptorSubstitute;
            FunctionDescriptor.CopyBuilder $this$getFunctions_u24lambda_u2d2_u24lambda_u2d1 = substitutedWithKotlinTypeParameters.newCopyBuilder();
            $this$getFunctions_u24lambda_u2d2_u24lambda_u2d1.setOwner(classDescriptor);
            $this$getFunctions_u24lambda_u2d2_u24lambda_u2d1.setDispatchReceiverParameter(classDescriptor.getThisAsReceiverParameter());
            $this$getFunctions_u24lambda_u2d2_u24lambda_u2d1.setPreserveSourceElement();
            JDKMemberStatus memberStatus = getJdkMethodStatus(additionalMember);
            switch (WhenMappings.$EnumSwitchMapping$0[memberStatus.ordinal()]) {
                case 1:
                    if (ModalityUtilsKt.isFinalClass(classDescriptor)) {
                        simpleFunctionDescriptor = null;
                        break;
                    } else {
                        $this$getFunctions_u24lambda_u2d2_u24lambda_u2d1.setHiddenForResolutionEverywhereBesideSupercalls();
                        SimpleFunctionDescriptor simpleFunctionDescriptor2 = (SimpleFunctionDescriptor) $this$getFunctions_u24lambda_u2d2_u24lambda_u2d1.build();
                        Intrinsics.checkNotNull(simpleFunctionDescriptor2);
                        simpleFunctionDescriptor = simpleFunctionDescriptor2;
                        break;
                    }
                case 2:
                    $this$getFunctions_u24lambda_u2d2_u24lambda_u2d1.setAdditionalAnnotations(getNotConsideredDeprecation());
                    SimpleFunctionDescriptor simpleFunctionDescriptor22 = (SimpleFunctionDescriptor) $this$getFunctions_u24lambda_u2d2_u24lambda_u2d1.build();
                    Intrinsics.checkNotNull(simpleFunctionDescriptor22);
                    simpleFunctionDescriptor = simpleFunctionDescriptor22;
                    break;
                case 3:
                    simpleFunctionDescriptor = null;
                    break;
                case 4:
                default:
                    SimpleFunctionDescriptor simpleFunctionDescriptor222 = (SimpleFunctionDescriptor) $this$getFunctions_u24lambda_u2d2_u24lambda_u2d1.build();
                    Intrinsics.checkNotNull(simpleFunctionDescriptor222);
                    simpleFunctionDescriptor = simpleFunctionDescriptor222;
                    break;
            }
            if (simpleFunctionDescriptor != null) {
                destination$iv$iv.add(simpleFunctionDescriptor);
            }
        }
        return (List) destination$iv$iv;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider
    @NotNull
    public Set<Name> getFunctionsNames(@NotNull ClassDescriptor classDescriptor) {
        LazyJavaClassMemberScope unsubstitutedMemberScope;
        Intrinsics.checkNotNullParameter(classDescriptor, "classDescriptor");
        if (!getSettings().isAdditionalBuiltInsFeatureSupported()) {
            return SetsKt.emptySet();
        }
        LazyJavaClassDescriptor javaAnalogue = getJavaAnalogue(classDescriptor);
        Set<Name> functionNames = (javaAnalogue == null || (unsubstitutedMemberScope = javaAnalogue.getUnsubstitutedMemberScope()) == null) ? null : unsubstitutedMemberScope.getFunctionNames();
        Set<Name> set = functionNames;
        return set == null ? SetsKt.emptySet() : set;
    }

    private final Collection<SimpleFunctionDescriptor> getAdditionalFunctions(ClassDescriptor classDescriptor, Function1<? super MemberScope, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        boolean z;
        boolean z2;
        final LazyJavaClassDescriptor javaAnalogueDescriptor = getJavaAnalogue(classDescriptor);
        if (javaAnalogueDescriptor == null) {
            return CollectionsKt.emptyList();
        }
        Iterable kotlinClassDescriptors = this.j2kClassMapper.mapPlatformClass(DescriptorUtilsKt.getFqNameSafe(javaAnalogueDescriptor), FallbackBuiltIns.Companion.getInstance());
        final ClassDescriptor kotlinMutableClassIfContainer = (ClassDescriptor) CollectionsKt.lastOrNull(kotlinClassDescriptors);
        if (kotlinMutableClassIfContainer == null) {
            return CollectionsKt.emptyList();
        }
        SmartSet.Companion companion = SmartSet.Companion;
        Iterable $this$map$iv = kotlinClassDescriptors;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            ClassDescriptor it = (ClassDescriptor) item$iv$iv;
            destination$iv$iv.add(DescriptorUtilsKt.getFqNameSafe(it));
        }
        SmartSet kotlinVersions = companion.create((List) destination$iv$iv);
        boolean isMutable = this.j2kClassMapper.isMutable(classDescriptor);
        ClassDescriptor fakeJavaClassDescriptor = this.javaAnalogueClassesWithCustomSupertypeCache.computeIfAbsent(DescriptorUtilsKt.getFqNameSafe(javaAnalogueDescriptor), new Function0<ClassDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsCustomizer$getAdditionalFunctions$fakeJavaClassDescriptor$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ClassDescriptor invoke() {
                LazyJavaClassDescriptor lazyJavaClassDescriptor = javaAnalogueDescriptor;
                JavaResolverCache EMPTY = JavaResolverCache.EMPTY;
                Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
                return lazyJavaClassDescriptor.copy$descriptors_jvm(EMPTY, kotlinMutableClassIfContainer);
            }
        });
        MemberScope scope = fakeJavaClassDescriptor.getUnsubstitutedMemberScope();
        Intrinsics.checkNotNullExpressionValue(scope, "fakeJavaClassDescriptor.unsubstitutedMemberScope");
        Iterable $this$filter$iv = function1.invoke(scope);
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            SimpleFunctionDescriptor analogueMember = (SimpleFunctionDescriptor) element$iv$iv;
            if (analogueMember.getKind() == CallableMemberDescriptor.Kind.DECLARATION && analogueMember.getVisibility().isPublicAPI() && !KotlinBuiltIns.isDeprecated(analogueMember)) {
                Iterable overriddenDescriptors = analogueMember.getOverriddenDescriptors();
                Intrinsics.checkNotNullExpressionValue(overriddenDescriptors, "analogueMember.overriddenDescriptors");
                Iterable $this$any$iv = overriddenDescriptors;
                if (!((Collection) $this$any$iv).isEmpty()) {
                    Iterator it2 = $this$any$iv.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            Object element$iv = it2.next();
                            FunctionDescriptor it3 = (FunctionDescriptor) element$iv;
                            DeclarationDescriptor containingDeclaration = it3.getContainingDeclaration();
                            Intrinsics.checkNotNullExpressionValue(containingDeclaration, "it.containingDeclaration");
                            if (kotlinVersions.contains(DescriptorUtilsKt.getFqNameSafe(containingDeclaration))) {
                                z = true;
                                break;
                            }
                        } else {
                            z = false;
                            break;
                        }
                    }
                } else {
                    z = false;
                }
                z2 = (z || isMutabilityViolation(analogueMember, isMutable)) ? false : true;
            } else {
                z2 = false;
            }
            if (z2) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv2;
    }

    private final SimpleFunctionDescriptor createCloneForArray(DeserializedClassDescriptor arrayClassDescriptor, SimpleFunctionDescriptor cloneFromCloneable) {
        FunctionDescriptor.CopyBuilder $this$createCloneForArray_u24lambda_u2d6 = cloneFromCloneable.newCopyBuilder();
        $this$createCloneForArray_u24lambda_u2d6.setOwner(arrayClassDescriptor);
        $this$createCloneForArray_u24lambda_u2d6.setVisibility(DescriptorVisibilities.PUBLIC);
        $this$createCloneForArray_u24lambda_u2d6.setReturnType(arrayClassDescriptor.getDefaultType());
        $this$createCloneForArray_u24lambda_u2d6.setDispatchReceiverParameter(arrayClassDescriptor.getThisAsReceiverParameter());
        SimpleFunctionDescriptor simpleFunctionDescriptor = (SimpleFunctionDescriptor) $this$createCloneForArray_u24lambda_u2d6.build();
        Intrinsics.checkNotNull(simpleFunctionDescriptor);
        return simpleFunctionDescriptor;
    }

    private final boolean isMutabilityViolation(SimpleFunctionDescriptor $this$isMutabilityViolation, boolean isMutable) {
        ClassDescriptor owner = (ClassDescriptor) $this$isMutabilityViolation.getContainingDeclaration();
        String jvmDescriptor = MethodSignatureMappingKt.computeJvmDescriptor$default($this$isMutabilityViolation, false, false, 3, null);
        if (JvmBuiltInsSignatures.INSTANCE.getMUTABLE_METHOD_SIGNATURES().contains(MethodSignatureBuildingUtilsKt.signature(SignatureBuildingComponents.INSTANCE, owner, jvmDescriptor)) ^ isMutable) {
            return true;
        }
        Boolean boolIfAny = DFS.ifAny(CollectionsKt.listOf($this$isMutabilityViolation), new DFS.Neighbors<CallableMemberDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsCustomizer.isMutabilityViolation.1
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
            @NotNull
            public final Iterable<CallableMemberDescriptor> getNeighbors(CallableMemberDescriptor it) {
                return it.getOriginal().getOverriddenDescriptors();
            }
        }, new Function1<CallableMemberDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsCustomizer.isMutabilityViolation.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(CallableMemberDescriptor overridden) {
                return Boolean.valueOf(overridden.getKind() == CallableMemberDescriptor.Kind.DECLARATION && JvmBuiltInsCustomizer.this.j2kClassMapper.isMutable((ClassDescriptor) overridden.getContainingDeclaration()));
            }
        });
        Intrinsics.checkNotNullExpressionValue(boolIfAny, "private fun SimpleFunctionDescriptor.isMutabilityViolation(isMutable: Boolean): Boolean {\n        val owner = containingDeclaration as ClassDescriptor\n        val jvmDescriptor = computeJvmDescriptor()\n\n        if ((SignatureBuildingComponents.signature(owner, jvmDescriptor) in MUTABLE_METHOD_SIGNATURES) xor isMutable) return true\n\n        return DFS.ifAny<CallableMemberDescriptor>(\n            listOf(this),\n            { it.original.overriddenDescriptors }\n        ) { overridden ->\n            overridden.kind == CallableMemberDescriptor.Kind.DECLARATION &&\n                    j2kClassMapper.isMutable(overridden.containingDeclaration as ClassDescriptor)\n        }\n    }");
        return boolIfAny.booleanValue();
    }

    private final JDKMemberStatus getJdkMethodStatus(FunctionDescriptor $this$getJdkMethodStatus) {
        ClassDescriptor owner = (ClassDescriptor) $this$getJdkMethodStatus.getContainingDeclaration();
        final String jvmDescriptor = MethodSignatureMappingKt.computeJvmDescriptor$default($this$getJdkMethodStatus, false, false, 3, null);
        final Ref.ObjectRef result = new Ref.ObjectRef();
        Object objDfs = DFS.dfs(CollectionsKt.listOf(owner), new DFS.Neighbors<ClassDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsCustomizer.getJdkMethodStatus.1
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
            @NotNull
            public final Iterable<ClassDescriptor> getNeighbors(ClassDescriptor it) {
                Iterable iterableMo3835getSupertypes = it.getTypeConstructor().mo3835getSupertypes();
                Intrinsics.checkNotNullExpressionValue(iterableMo3835getSupertypes, "it.typeConstructor.supertypes");
                Iterable $this$mapNotNull$iv = iterableMo3835getSupertypes;
                JvmBuiltInsCustomizer jvmBuiltInsCustomizer = JvmBuiltInsCustomizer.this;
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                    KotlinType it2 = (KotlinType) element$iv$iv$iv;
                    ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = it2.getConstructor().mo3831getDeclarationDescriptor();
                    ClassifierDescriptor original = classifierDescriptorMo3831getDeclarationDescriptor == null ? null : classifierDescriptorMo3831getDeclarationDescriptor.getOriginal();
                    ClassDescriptor classDescriptor = original instanceof ClassDescriptor ? (ClassDescriptor) original : null;
                    LazyJavaClassDescriptor javaAnalogue = classDescriptor == null ? null : jvmBuiltInsCustomizer.getJavaAnalogue(classDescriptor);
                    if (javaAnalogue != null) {
                        destination$iv$iv.add(javaAnalogue);
                    }
                }
                return (List) destination$iv$iv;
            }
        }, new DFS.AbstractNodeHandler<ClassDescriptor, JDKMemberStatus>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsCustomizer.getJdkMethodStatus.2
            /* JADX WARN: Type inference failed for: r1v5, types: [T, kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsCustomizer$JDKMemberStatus] */
            /* JADX WARN: Type inference failed for: r1v6, types: [T, kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsCustomizer$JDKMemberStatus] */
            /* JADX WARN: Type inference failed for: r1v7, types: [T, kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsCustomizer$JDKMemberStatus] */
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler, kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public boolean beforeChildren(@NotNull ClassDescriptor javaClassDescriptor) {
                Intrinsics.checkNotNullParameter(javaClassDescriptor, "javaClassDescriptor");
                String signature = MethodSignatureBuildingUtilsKt.signature(SignatureBuildingComponents.INSTANCE, javaClassDescriptor, jvmDescriptor);
                if (JvmBuiltInsSignatures.INSTANCE.getHIDDEN_METHOD_SIGNATURES().contains(signature)) {
                    result.element = JDKMemberStatus.HIDDEN;
                } else if (JvmBuiltInsSignatures.INSTANCE.getVISIBLE_METHOD_SIGNATURES().contains(signature)) {
                    result.element = JDKMemberStatus.VISIBLE;
                } else if (JvmBuiltInsSignatures.INSTANCE.getDROP_LIST_METHOD_SIGNATURES().contains(signature)) {
                    result.element = JDKMemberStatus.DROP;
                }
                return result.element == null;
            }

            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            @NotNull
            /* renamed from: result */
            public JDKMemberStatus mo3664result() {
                JDKMemberStatus jDKMemberStatus = result.element;
                return jDKMemberStatus == null ? JDKMemberStatus.NOT_CONSIDERED : jDKMemberStatus;
            }
        });
        Intrinsics.checkNotNullExpressionValue(objDfs, "private fun FunctionDescriptor.getJdkMethodStatus(): JDKMemberStatus {\n        val owner = containingDeclaration as ClassDescriptor\n        val jvmDescriptor = computeJvmDescriptor()\n        var result: JDKMemberStatus? = null\n        return DFS.dfs<ClassDescriptor, JDKMemberStatus>(\n            listOf(owner),\n            {\n                // Search through mapped supertypes to determine that Set.toArray should be invisible, while we have only\n                // Collection.toArray there explicitly\n                // Note, that we can't find j.u.Collection.toArray within overriddenDescriptors of j.u.Set.toArray\n                it.typeConstructor.supertypes.mapNotNull {\n                    (it.constructor.declarationDescriptor?.original as? ClassDescriptor)?.getJavaAnalogue()\n                }\n            },\n            object : DFS.AbstractNodeHandler<ClassDescriptor, JDKMemberStatus>() {\n                override fun beforeChildren(javaClassDescriptor: ClassDescriptor): Boolean {\n                    val signature = SignatureBuildingComponents.signature(javaClassDescriptor, jvmDescriptor)\n                    when (signature) {\n                        in HIDDEN_METHOD_SIGNATURES -> result = JDKMemberStatus.HIDDEN\n                        in VISIBLE_METHOD_SIGNATURES -> result = JDKMemberStatus.VISIBLE\n                        in DROP_LIST_METHOD_SIGNATURES -> result = JDKMemberStatus.DROP\n                    }\n\n                    return result == null\n                }\n\n                override fun result() = result ?: JDKMemberStatus.NOT_CONSIDERED\n            })\n    }");
        return (JDKMemberStatus) objDfs;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LazyJavaClassDescriptor getJavaAnalogue(ClassDescriptor $this$getJavaAnalogue) {
        if (KotlinBuiltIns.isAny($this$getJavaAnalogue) || !KotlinBuiltIns.isUnderKotlinPackage($this$getJavaAnalogue)) {
            return null;
        }
        FqNameUnsafe fqName = DescriptorUtilsKt.getFqNameUnsafe($this$getJavaAnalogue);
        if (!fqName.isSafe()) {
            return null;
        }
        ClassId classIdMapKotlinToJava = JavaToKotlinClassMap.INSTANCE.mapKotlinToJava(fqName);
        FqName javaAnalogueFqName = classIdMapKotlinToJava == null ? null : classIdMapKotlinToJava.asSingleFqName();
        if (javaAnalogueFqName == null) {
            return null;
        }
        ClassDescriptor classDescriptorResolveClassByFqName = DescriptorUtilKt.resolveClassByFqName(getSettings().getOwnerModuleDescriptor(), javaAnalogueFqName, NoLookupLocation.FROM_BUILTINS);
        if (classDescriptorResolveClassByFqName instanceof LazyJavaClassDescriptor) {
            return (LazyJavaClassDescriptor) classDescriptorResolveClassByFqName;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x016e  */
    @Override // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.Collection<kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor> getConstructors(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r10) {
        /*
            Method dump skipped, instructions count: 642
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsCustomizer.getConstructors(kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor):java.util.Collection");
    }

    private static final boolean getConstructors$isEffectivelyTheSameAs(ConstructorDescriptor $this$getConstructors_u24isEffectivelyTheSameAs, TypeSubstitutor substitutor, ConstructorDescriptor javaConstructor) {
        return OverridingUtil.getBothWaysOverridability($this$getConstructors_u24isEffectivelyTheSameAs, javaConstructor.substitute(substitutor)) == OverridingUtil.OverrideCompatibilityInfo.Result.OVERRIDABLE;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter
    public boolean isFunctionAvailable(@NotNull ClassDescriptor classDescriptor, @NotNull SimpleFunctionDescriptor functionDescriptor) {
        Intrinsics.checkNotNullParameter(classDescriptor, "classDescriptor");
        Intrinsics.checkNotNullParameter(functionDescriptor, "functionDescriptor");
        LazyJavaClassDescriptor javaAnalogueClassDescriptor = getJavaAnalogue(classDescriptor);
        if (javaAnalogueClassDescriptor == null || !functionDescriptor.getAnnotations().hasAnnotation(PlatformDependentDeclarationFilterKt.getPLATFORM_DEPENDENT_ANNOTATION_FQ_NAME())) {
            return true;
        }
        if (!getSettings().isAdditionalBuiltInsFeatureSupported()) {
            return false;
        }
        String jvmDescriptor = MethodSignatureMappingKt.computeJvmDescriptor$default(functionDescriptor, false, false, 3, null);
        LazyJavaClassMemberScope unsubstitutedMemberScope = javaAnalogueClassDescriptor.getUnsubstitutedMemberScope();
        Name name = functionDescriptor.getName();
        Intrinsics.checkNotNullExpressionValue(name, "functionDescriptor.name");
        Iterable $this$any$iv = unsubstitutedMemberScope.getContributedFunctions(name, NoLookupLocation.FROM_BUILTINS);
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            SimpleFunctionDescriptor it = (SimpleFunctionDescriptor) element$iv;
            if (Intrinsics.areEqual(MethodSignatureMappingKt.computeJvmDescriptor$default(it, false, false, 3, null), jvmDescriptor)) {
                return true;
            }
        }
        return false;
    }

    private final boolean isTrivialCopyConstructorFor(ConstructorDescriptor $this$isTrivialCopyConstructorFor, ClassDescriptor classDescriptor) {
        if ($this$isTrivialCopyConstructorFor.getValueParameters().size() == 1) {
            List<ValueParameterDescriptor> valueParameters = $this$isTrivialCopyConstructorFor.getValueParameters();
            Intrinsics.checkNotNullExpressionValue(valueParameters, "valueParameters");
            ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = ((ValueParameterDescriptor) CollectionsKt.single((List) valueParameters)).getType().getConstructor().mo3831getDeclarationDescriptor();
            if (Intrinsics.areEqual(classifierDescriptorMo3831getDeclarationDescriptor == null ? null : DescriptorUtilsKt.getFqNameUnsafe(classifierDescriptorMo3831getDeclarationDescriptor), DescriptorUtilsKt.getFqNameUnsafe(classDescriptor))) {
                return true;
            }
        }
        return false;
    }
}
