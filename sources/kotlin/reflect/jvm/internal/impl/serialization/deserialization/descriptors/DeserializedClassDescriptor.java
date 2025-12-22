package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.DeserializedDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ScopesHolderForClass;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassConstructorDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.EnumEntrySyntheticClassDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.UtilsKt;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.CliSealedClassInheritorsProvider;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.NonReportingOverrideStrategy;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.StaticScopeForKotlinEnum;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlagsUtilsKt;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.NullableLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DeserializedClassDescriptor.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/serialization/deserialization/descriptors/DeserializedClassDescriptor.class */
public final class DeserializedClassDescriptor extends AbstractClassDescriptor implements DeserializedDescriptor {

    @NotNull
    private final ProtoBuf.Class classProto;

    @NotNull
    private final BinaryVersion metadataVersion;

    @NotNull
    private final SourceElement sourceElement;

    @NotNull
    private final ClassId classId;

    @NotNull
    private final Modality modality;

    @NotNull
    private final DescriptorVisibility visibility;

    @NotNull
    private final ClassKind kind;

    @NotNull
    private final DeserializationContext c;

    @NotNull
    private final MemberScopeImpl staticScope;

    @NotNull
    private final DeserializedClassTypeConstructor typeConstructor;

    @NotNull
    private final ScopesHolderForClass<DeserializedClassMemberScope> memberScopeHolder;

    @Nullable
    private final EnumEntryClassDescriptors enumEntries;

    @NotNull
    private final DeclarationDescriptor containingDeclaration;

    @NotNull
    private final NullableLazyValue<ClassConstructorDescriptor> primaryConstructor;

    @NotNull
    private final NotNullLazyValue<Collection<ClassConstructorDescriptor>> constructors;

    @NotNull
    private final NullableLazyValue<ClassDescriptor> companionObjectDescriptor;

    @NotNull
    private final NotNullLazyValue<Collection<ClassDescriptor>> sealedSubclasses;

    @NotNull
    private final ProtoContainer.Class thisAsProtoContainer;

    @NotNull
    private final Annotations annotations;

    @NotNull
    public final ProtoBuf.Class getClassProto() {
        return this.classProto;
    }

    @NotNull
    public final BinaryVersion getMetadataVersion() {
        return this.metadataVersion;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeserializedClassDescriptor(@NotNull DeserializationContext outerContext, @NotNull ProtoBuf.Class classProto, @NotNull NameResolver nameResolver, @NotNull BinaryVersion metadataVersion, @NotNull SourceElement sourceElement) {
        NonEmptyDeserializedAnnotations nonEmptyDeserializedAnnotations;
        super(outerContext.getStorageManager(), NameResolverUtilKt.getClassId(nameResolver, classProto.getFqName()).getShortClassName());
        Intrinsics.checkNotNullParameter(outerContext, "outerContext");
        Intrinsics.checkNotNullParameter(classProto, "classProto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Intrinsics.checkNotNullParameter(metadataVersion, "metadataVersion");
        Intrinsics.checkNotNullParameter(sourceElement, "sourceElement");
        this.classProto = classProto;
        this.metadataVersion = metadataVersion;
        this.sourceElement = sourceElement;
        this.classId = NameResolverUtilKt.getClassId(nameResolver, this.classProto.getFqName());
        this.modality = ProtoEnumFlags.INSTANCE.modality(Flags.MODALITY.get(this.classProto.getFlags()));
        this.visibility = ProtoEnumFlagsUtilsKt.descriptorVisibility(ProtoEnumFlags.INSTANCE, Flags.VISIBILITY.get(this.classProto.getFlags()));
        this.kind = ProtoEnumFlags.INSTANCE.classKind(Flags.CLASS_KIND.get(this.classProto.getFlags()));
        List<ProtoBuf.TypeParameter> typeParameterList = this.classProto.getTypeParameterList();
        Intrinsics.checkNotNullExpressionValue(typeParameterList, "classProto.typeParameterList");
        ProtoBuf.TypeTable typeTable = this.classProto.getTypeTable();
        Intrinsics.checkNotNullExpressionValue(typeTable, "classProto.typeTable");
        TypeTable typeTable2 = new TypeTable(typeTable);
        VersionRequirementTable.Companion companion = VersionRequirementTable.Companion;
        ProtoBuf.VersionRequirementTable versionRequirementTable = this.classProto.getVersionRequirementTable();
        Intrinsics.checkNotNullExpressionValue(versionRequirementTable, "classProto.versionRequirementTable");
        this.c = outerContext.childContext(this, typeParameterList, nameResolver, typeTable2, companion.create(versionRequirementTable), this.metadataVersion);
        this.staticScope = this.kind == ClassKind.ENUM_CLASS ? new StaticScopeForKotlinEnum(this.c.getStorageManager(), this) : MemberScope.Empty.INSTANCE;
        this.typeConstructor = new DeserializedClassTypeConstructor(this);
        this.memberScopeHolder = ScopesHolderForClass.Companion.create(this, this.c.getStorageManager(), this.c.getComponents().getKotlinTypeChecker().getKotlinTypeRefiner(), new DeserializedClassDescriptor$memberScopeHolder$1(this));
        this.enumEntries = this.kind == ClassKind.ENUM_CLASS ? new EnumEntryClassDescriptors(this) : null;
        this.containingDeclaration = outerContext.getContainingDeclaration();
        this.primaryConstructor = this.c.getStorageManager().createNullableLazyValue(new Function0<ClassConstructorDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$primaryConstructor$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @Nullable
            public final ClassConstructorDescriptor invoke() {
                return this.this$0.computePrimaryConstructor();
            }
        });
        this.constructors = this.c.getStorageManager().createLazyValue(new Function0<Collection<? extends ClassConstructorDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$constructors$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final Collection<? extends ClassConstructorDescriptor> invoke() {
                return this.this$0.computeConstructors();
            }
        });
        this.companionObjectDescriptor = this.c.getStorageManager().createNullableLazyValue(new Function0<ClassDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$companionObjectDescriptor$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @Nullable
            public final ClassDescriptor invoke() {
                return this.this$0.computeCompanionObjectDescriptor();
            }
        });
        this.sealedSubclasses = this.c.getStorageManager().createLazyValue(new Function0<Collection<? extends ClassDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$sealedSubclasses$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final Collection<? extends ClassDescriptor> invoke() {
                return this.this$0.computeSubclassesForSealedClass();
            }
        });
        ProtoBuf.Class r3 = this.classProto;
        NameResolver nameResolver2 = this.c.getNameResolver();
        TypeTable typeTable3 = this.c.getTypeTable();
        SourceElement sourceElement2 = this.sourceElement;
        DeclarationDescriptor declarationDescriptor = this.containingDeclaration;
        DeserializedClassDescriptor deserializedClassDescriptor = declarationDescriptor instanceof DeserializedClassDescriptor ? (DeserializedClassDescriptor) declarationDescriptor : null;
        this.thisAsProtoContainer = new ProtoContainer.Class(r3, nameResolver2, typeTable3, sourceElement2, deserializedClassDescriptor == null ? null : deserializedClassDescriptor.thisAsProtoContainer);
        if (!Flags.HAS_ANNOTATIONS.get(this.classProto.getFlags()).booleanValue()) {
            nonEmptyDeserializedAnnotations = Annotations.Companion.getEMPTY();
        } else {
            nonEmptyDeserializedAnnotations = new NonEmptyDeserializedAnnotations(this.c.getStorageManager(), new Function0<List<? extends AnnotationDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$annotations$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                @NotNull
                public final List<? extends AnnotationDescriptor> invoke() {
                    return CollectionsKt.toList(this.this$0.getC().getComponents().getAnnotationAndConstantLoader().loadClassAnnotations(this.this$0.getThisAsProtoContainer$deserialization()));
                }
            });
        }
        this.annotations = nonEmptyDeserializedAnnotations;
    }

    @NotNull
    public final DeserializationContext getC() {
        return this.c;
    }

    private final DeserializedClassMemberScope getMemberScope() {
        return (DeserializedClassMemberScope) this.memberScopeHolder.getScope(this.c.getComponents().getKotlinTypeChecker().getKotlinTypeRefiner());
    }

    @NotNull
    public final ProtoContainer.Class getThisAsProtoContainer$deserialization() {
        return this.thisAsProtoContainer;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    @NotNull
    public Annotations getAnnotations() {
        return this.annotations;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    @NotNull
    public DeclarationDescriptor getContainingDeclaration() {
        return this.containingDeclaration;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    @NotNull
    public TypeConstructor getTypeConstructor() {
        return this.typeConstructor;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    @NotNull
    public ClassKind getKind() {
        return this.kind;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    @NotNull
    public Modality getModality() {
        return this.modality;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    @NotNull
    public DescriptorVisibility getVisibility() {
        return this.visibility;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
    public boolean isInner() {
        Boolean bool = Flags.IS_INNER.get(this.classProto.getFlags());
        Intrinsics.checkNotNullExpressionValue(bool, "IS_INNER.get(classProto.flags)");
        return bool.booleanValue();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isData() {
        Boolean bool = Flags.IS_DATA.get(this.classProto.getFlags());
        Intrinsics.checkNotNullExpressionValue(bool, "IS_DATA.get(classProto.flags)");
        return bool.booleanValue();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isInline() {
        Boolean bool = Flags.IS_INLINE_CLASS.get(this.classProto.getFlags());
        Intrinsics.checkNotNullExpressionValue(bool, "IS_INLINE_CLASS.get(classProto.flags)");
        return bool.booleanValue() && this.metadataVersion.isAtMost(1, 4, 1);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExpect() {
        Boolean bool = Flags.IS_EXPECT_CLASS.get(this.classProto.getFlags());
        Intrinsics.checkNotNullExpressionValue(bool, "IS_EXPECT_CLASS.get(classProto.flags)");
        return bool.booleanValue();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isActual() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExternal() {
        Boolean bool = Flags.IS_EXTERNAL_CLASS.get(this.classProto.getFlags());
        Intrinsics.checkNotNullExpressionValue(bool, "IS_EXTERNAL_CLASS.get(classProto.flags)");
        return bool.booleanValue();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isFun() {
        Boolean bool = Flags.IS_FUN_INTERFACE.get(this.classProto.getFlags());
        Intrinsics.checkNotNullExpressionValue(bool, "IS_FUN_INTERFACE.get(classProto.flags)");
        return bool.booleanValue();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isValue() {
        Boolean bool = Flags.IS_INLINE_CLASS.get(this.classProto.getFlags());
        Intrinsics.checkNotNullExpressionValue(bool, "IS_INLINE_CLASS.get(classProto.flags)");
        return bool.booleanValue() && this.metadataVersion.isAtLeast(1, 4, 2);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptor
    @NotNull
    protected MemberScope getUnsubstitutedMemberScope(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        return this.memberScopeHolder.getScope(kotlinTypeRefiner);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    @NotNull
    public MemberScopeImpl getStaticScope() {
        return this.staticScope;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isCompanionObject() {
        return Flags.CLASS_KIND.get(this.classProto.getFlags()) == ProtoBuf.Class.Kind.COMPANION_OBJECT;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassConstructorDescriptor computePrimaryConstructor() {
        Object obj;
        if (this.kind.isSingleton()) {
            ClassConstructorDescriptorImpl $this$computePrimaryConstructor_u24lambda_u2d0 = DescriptorFactory.createPrimaryConstructorForObject(this, SourceElement.NO_SOURCE);
            $this$computePrimaryConstructor_u24lambda_u2d0.setReturnType(getDefaultType());
            return $this$computePrimaryConstructor_u24lambda_u2d0;
        }
        Iterable constructorList = this.classProto.getConstructorList();
        Intrinsics.checkNotNullExpressionValue(constructorList, "classProto.constructorList");
        Iterable $this$firstOrNull$iv = constructorList;
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            Object element$iv = it.next();
            ProtoBuf.Constructor it2 = (ProtoBuf.Constructor) element$iv;
            if (!Flags.IS_SECONDARY.get(it2.getFlags()).booleanValue()) {
                obj = element$iv;
                break;
            }
        }
        ProtoBuf.Constructor constructorProto = (ProtoBuf.Constructor) obj;
        if (constructorProto == null) {
            return null;
        }
        return getC().getMemberDeserializer().loadConstructor(constructorProto, true);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    @Nullable
    /* renamed from: getUnsubstitutedPrimaryConstructor */
    public ClassConstructorDescriptor mo3498getUnsubstitutedPrimaryConstructor() {
        return this.primaryConstructor.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Collection<ClassConstructorDescriptor> computeConstructors() {
        return CollectionsKt.plus((Collection) CollectionsKt.plus((Collection) computeSecondaryConstructors(), (Iterable) CollectionsKt.listOfNotNull(mo3498getUnsubstitutedPrimaryConstructor())), (Iterable) this.c.getComponents().getAdditionalClassPartsProvider().getConstructors(this));
    }

    private final List<ClassConstructorDescriptor> computeSecondaryConstructors() {
        Iterable constructorList = this.classProto.getConstructorList();
        Intrinsics.checkNotNullExpressionValue(constructorList, "classProto.constructorList");
        Iterable $this$filter$iv = constructorList;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Boolean bool = Flags.IS_SECONDARY.get(((ProtoBuf.Constructor) element$iv$iv).getFlags());
            Intrinsics.checkNotNullExpressionValue(bool, "IS_SECONDARY.get(it.flags)");
            if (bool.booleanValue()) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$map$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            ProtoBuf.Constructor it = (ProtoBuf.Constructor) item$iv$iv;
            MemberDeserializer memberDeserializer = getC().getMemberDeserializer();
            Intrinsics.checkNotNullExpressionValue(it, "it");
            destination$iv$iv2.add(memberDeserializer.loadConstructor(it, false));
        }
        return (List) destination$iv$iv2;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    @NotNull
    public Collection<ClassConstructorDescriptor> getConstructors() {
        return this.constructors.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassDescriptor computeCompanionObjectDescriptor() {
        if (!this.classProto.hasCompanionObjectName()) {
            return null;
        }
        Name companionObjectName = NameResolverUtilKt.getName(this.c.getNameResolver(), this.classProto.getCompanionObjectName());
        ClassifierDescriptor classifierDescriptorMo3858getContributedClassifier = getMemberScope().mo3858getContributedClassifier(companionObjectName, NoLookupLocation.FROM_DESERIALIZATION);
        if (classifierDescriptorMo3858getContributedClassifier instanceof ClassDescriptor) {
            return (ClassDescriptor) classifierDescriptorMo3858getContributedClassifier;
        }
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    @Nullable
    /* renamed from: getCompanionObjectDescriptor */
    public ClassDescriptor mo3497getCompanionObjectDescriptor() {
        return this.companionObjectDescriptor.invoke();
    }

    public final boolean hasNestedClass$deserialization(@NotNull Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return getMemberScope().getClassNames$deserialization().contains(name);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Collection<ClassDescriptor> computeSubclassesForSealedClass() {
        if (this.modality != Modality.SEALED) {
            return CollectionsKt.emptyList();
        }
        Iterable fqNames = this.classProto.getSealedSubclassFqNameList();
        Intrinsics.checkNotNullExpressionValue(fqNames, "fqNames");
        if (!((Collection) fqNames).isEmpty()) {
            Iterable $this$mapNotNull$iv = fqNames;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                Integer index = (Integer) element$iv$iv$iv;
                DeserializationComponents components = getC().getComponents();
                NameResolver nameResolver = getC().getNameResolver();
                Intrinsics.checkNotNullExpressionValue(index, "index");
                ClassDescriptor classDescriptorDeserializeClass = components.deserializeClass(NameResolverUtilKt.getClassId(nameResolver, index.intValue()));
                if (classDescriptorDeserializeClass != null) {
                    destination$iv$iv.add(classDescriptorDeserializeClass);
                }
            }
            return (List) destination$iv$iv;
        }
        return CliSealedClassInheritorsProvider.INSTANCE.computeSealedSubclasses(this, false);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    @NotNull
    public Collection<ClassDescriptor> getSealedSubclasses() {
        return this.sealedSubclasses.invoke();
    }

    @NotNull
    public String toString() {
        return "deserialized " + (isExpect() ? "expect " : "") + "class " + getName();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource
    @NotNull
    public SourceElement getSource() {
        return this.sourceElement;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
    @NotNull
    public List<TypeParameterDescriptor> getDeclaredTypeParameters() {
        return this.c.getTypeDeserializer().getOwnTypeParameters();
    }

    /* compiled from: DeserializedClassDescriptor.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/serialization/deserialization/descriptors/DeserializedClassDescriptor$DeserializedClassTypeConstructor.class */
    private final class DeserializedClassTypeConstructor extends AbstractClassTypeConstructor {

        @NotNull
        private final NotNullLazyValue<List<TypeParameterDescriptor>> parameters;
        final /* synthetic */ DeserializedClassDescriptor this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DeserializedClassTypeConstructor(DeserializedClassDescriptor this$0) {
            super(this$0.getC().getStorageManager());
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
            StorageManager storageManager = this.this$0.getC().getStorageManager();
            final DeserializedClassDescriptor deserializedClassDescriptor = this.this$0;
            this.parameters = storageManager.createLazyValue(new Function0<List<? extends TypeParameterDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$DeserializedClassTypeConstructor$parameters$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                @NotNull
                public final List<? extends TypeParameterDescriptor> invoke() {
                    return TypeParameterUtilsKt.computeConstructorTypeParameters(deserializedClassDescriptor);
                }
            });
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
        @NotNull
        protected Collection<KotlinType> computeSupertypes() {
            FqName fqNameAsSingleFqName;
            Iterable $this$map$iv = ProtoTypeTableUtilKt.supertypes(this.this$0.getClassProto(), this.this$0.getC().getTypeTable());
            DeserializedClassDescriptor deserializedClassDescriptor = this.this$0;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                ProtoBuf.Type supertypeProto = (ProtoBuf.Type) item$iv$iv;
                destination$iv$iv.add(deserializedClassDescriptor.getC().getTypeDeserializer().type(supertypeProto));
            }
            Iterable result = CollectionsKt.plus(destination$iv$iv, (Iterable) this.this$0.getC().getComponents().getAdditionalClassPartsProvider().getSupertypes(this.this$0));
            Iterable $this$mapNotNull$iv = result;
            Collection destination$iv$iv2 = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                KotlinType supertype = (KotlinType) element$iv$iv$iv;
                ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = supertype.getConstructor().mo3831getDeclarationDescriptor();
                NotFoundClasses.MockClassDescriptor mockClassDescriptor = classifierDescriptorMo3831getDeclarationDescriptor instanceof NotFoundClasses.MockClassDescriptor ? (NotFoundClasses.MockClassDescriptor) classifierDescriptorMo3831getDeclarationDescriptor : null;
                if (mockClassDescriptor != null) {
                    destination$iv$iv2.add(mockClassDescriptor);
                }
            }
            Iterable unresolved = (List) destination$iv$iv2;
            if (!((Collection) unresolved).isEmpty()) {
                ErrorReporter errorReporter = this.this$0.getC().getComponents().getErrorReporter();
                DeserializedClassDescriptor deserializedClassDescriptor2 = this.this$0;
                Iterable $this$map$iv2 = unresolved;
                Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
                for (Object item$iv$iv2 : $this$map$iv2) {
                    NotFoundClasses.MockClassDescriptor it = (NotFoundClasses.MockClassDescriptor) item$iv$iv2;
                    ClassId classId = DescriptorUtilsKt.getClassId(it);
                    String strAsString = (classId == null || (fqNameAsSingleFqName = classId.asSingleFqName()) == null) ? null : fqNameAsSingleFqName.asString();
                    String str = strAsString;
                    destination$iv$iv3.add(str == null ? it.getName().asString() : str);
                }
                errorReporter.reportIncompleteHierarchy(deserializedClassDescriptor2, (List) destination$iv$iv3);
            }
            return CollectionsKt.toList(result);
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        @NotNull
        public List<TypeParameterDescriptor> getParameters() {
            return this.parameters.invoke();
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public boolean isDenotable() {
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor, kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor, kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        @NotNull
        /* renamed from: getDeclarationDescriptor */
        public DeserializedClassDescriptor mo3831getDeclarationDescriptor() {
            return this.this$0;
        }

        @NotNull
        public String toString() {
            String string = this.this$0.getName().toString();
            Intrinsics.checkNotNullExpressionValue(string, "name.toString()");
            return string;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
        @NotNull
        protected SupertypeLoopChecker getSupertypeLoopChecker() {
            return SupertypeLoopChecker.EMPTY.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DeserializedClassDescriptor.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/serialization/deserialization/descriptors/DeserializedClassDescriptor$DeserializedClassMemberScope.class */
    final class DeserializedClassMemberScope extends DeserializedMemberScope {

        @NotNull
        private final KotlinTypeRefiner kotlinTypeRefiner;

        @NotNull
        private final NotNullLazyValue<Collection<DeclarationDescriptor>> allDescriptors;

        @NotNull
        private final NotNullLazyValue<Collection<KotlinType>> refinedSupertypes;
        final /* synthetic */ DeserializedClassDescriptor this$0;

        public DeserializedClassMemberScope(@NotNull DeserializedClassDescriptor this$0, KotlinTypeRefiner kotlinTypeRefiner) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
            this.this$0 = this$0;
            DeserializationContext c = this$0.getC();
            List<ProtoBuf.Function> functionList = this$0.getClassProto().getFunctionList();
            Intrinsics.checkNotNullExpressionValue(functionList, "classProto.functionList");
            List<ProtoBuf.Property> propertyList = this$0.getClassProto().getPropertyList();
            Intrinsics.checkNotNullExpressionValue(propertyList, "classProto.propertyList");
            List<ProtoBuf.TypeAlias> typeAliasList = this$0.getClassProto().getTypeAliasList();
            Intrinsics.checkNotNullExpressionValue(typeAliasList, "classProto.typeAliasList");
            Iterable nestedClassNameList = this$0.getClassProto().getNestedClassNameList();
            Intrinsics.checkNotNullExpressionValue(nestedClassNameList, "classProto.nestedClassNameList");
            Iterable $this$map$iv = nestedClassNameList;
            NameResolver nameResolver = this$0.getC().getNameResolver();
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                int p0 = ((Number) item$iv$iv).intValue();
                destination$iv$iv.add(NameResolverUtilKt.getName(nameResolver, p0));
            }
            final List it = (List) destination$iv$iv;
            super(c, functionList, propertyList, typeAliasList, new Function0<List<? extends Name>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$DeserializedClassMemberScope$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                @NotNull
                public final List<? extends Name> invoke() {
                    return it;
                }
            });
            this.kotlinTypeRefiner = kotlinTypeRefiner;
            this.allDescriptors = getC().getStorageManager().createLazyValue(new Function0<Collection<? extends DeclarationDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$DeserializedClassMemberScope$allDescriptors$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                @NotNull
                public final Collection<? extends DeclarationDescriptor> invoke() {
                    return this.this$0.computeDescriptors(DescriptorKindFilter.ALL, MemberScope.Companion.getALL_NAME_FILTER(), NoLookupLocation.WHEN_GET_ALL_DESCRIPTORS);
                }
            });
            this.refinedSupertypes = getC().getStorageManager().createLazyValue(new Function0<Collection<? extends KotlinType>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$DeserializedClassMemberScope$refinedSupertypes$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                @NotNull
                public final Collection<? extends KotlinType> invoke() {
                    return this.this$0.kotlinTypeRefiner.refineSupertypes(this.this$0.getClassDescriptor());
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final DeserializedClassDescriptor getClassDescriptor() {
            return this.this$0;
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
        @NotNull
        public Collection<DeclarationDescriptor> getContributedDescriptors(@NotNull DescriptorKindFilter kindFilter, @NotNull Function1<? super Name, Boolean> nameFilter) {
            Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
            Intrinsics.checkNotNullParameter(nameFilter, "nameFilter");
            return this.allDescriptors.invoke();
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
        @NotNull
        public Collection<SimpleFunctionDescriptor> getContributedFunctions(@NotNull Name name, @NotNull LookupLocation location) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(location, "location");
            recordLookup(name, location);
            return super.getContributedFunctions(name, location);
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
        @NotNull
        public Collection<PropertyDescriptor> getContributedVariables(@NotNull Name name, @NotNull LookupLocation location) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(location, "location");
            recordLookup(name, location);
            return super.getContributedVariables(name, location);
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        protected boolean isDeclaredFunctionAvailable(@NotNull SimpleFunctionDescriptor function) {
            Intrinsics.checkNotNullParameter(function, "function");
            return getC().getComponents().getPlatformDependentDeclarationFilter().isFunctionAvailable(this.this$0, function);
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        protected void computeNonDeclaredFunctions(@NotNull Name name, @NotNull List<SimpleFunctionDescriptor> functions) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(functions, "functions");
            ArrayList fromSupertypes = new ArrayList();
            for (KotlinType supertype : this.refinedSupertypes.invoke()) {
                fromSupertypes.addAll(supertype.getMemberScope().getContributedFunctions(name, NoLookupLocation.FOR_ALREADY_TRACKED));
            }
            functions.addAll(getC().getComponents().getAdditionalClassPartsProvider().getFunctions(name, this.this$0));
            generateFakeOverrides(name, fromSupertypes, functions);
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        protected void computeNonDeclaredProperties(@NotNull Name name, @NotNull List<PropertyDescriptor> descriptors) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(descriptors, "descriptors");
            ArrayList fromSupertypes = new ArrayList();
            for (KotlinType supertype : this.refinedSupertypes.invoke()) {
                fromSupertypes.addAll(supertype.getMemberScope().getContributedVariables(name, NoLookupLocation.FOR_ALREADY_TRACKED));
            }
            generateFakeOverrides(name, fromSupertypes, descriptors);
        }

        private final <D extends CallableMemberDescriptor> void generateFakeOverrides(Name name, Collection<? extends D> collection, final List<D> list) {
            ArrayList fromCurrent = new ArrayList(list);
            getC().getComponents().getKotlinTypeChecker().getOverridingUtil().generateOverridesInFunctionGroup(name, collection, fromCurrent, getClassDescriptor(), new NonReportingOverrideStrategy() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$DeserializedClassMemberScope$generateFakeOverrides$1
                @Override // kotlin.reflect.jvm.internal.impl.resolve.OverridingStrategy
                public void addFakeOverride(@NotNull CallableMemberDescriptor fakeOverride) {
                    Intrinsics.checkNotNullParameter(fakeOverride, "fakeOverride");
                    OverridingUtil.resolveUnknownVisibilityForMember(fakeOverride, null);
                    list.add(fakeOverride);
                }

                @Override // kotlin.reflect.jvm.internal.impl.resolve.NonReportingOverrideStrategy
                protected void conflict(@NotNull CallableMemberDescriptor fromSuper, @NotNull CallableMemberDescriptor fromCurrent2) {
                    Intrinsics.checkNotNullParameter(fromSuper, "fromSuper");
                    Intrinsics.checkNotNullParameter(fromCurrent2, "fromCurrent");
                }
            });
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        @NotNull
        protected Set<Name> getNonDeclaredFunctionNames() {
            Iterable $this$flatMapTo$iv = getClassDescriptor().typeConstructor.mo3835getSupertypes();
            Collection destination$iv = new LinkedHashSet();
            for (Object element$iv : $this$flatMapTo$iv) {
                KotlinType it = (KotlinType) element$iv;
                Iterable list$iv = it.getMemberScope().getFunctionNames();
                CollectionsKt.addAll(destination$iv, list$iv);
            }
            LinkedHashSet $this$getNonDeclaredFunctionNames_u24lambda_u2d2 = (LinkedHashSet) destination$iv;
            $this$getNonDeclaredFunctionNames_u24lambda_u2d2.addAll(getC().getComponents().getAdditionalClassPartsProvider().getFunctionsNames(this.this$0));
            return (Set) destination$iv;
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        @NotNull
        protected Set<Name> getNonDeclaredVariableNames() {
            Iterable $this$flatMapTo$iv = getClassDescriptor().typeConstructor.mo3835getSupertypes();
            Collection destination$iv = new LinkedHashSet();
            for (Object element$iv : $this$flatMapTo$iv) {
                KotlinType it = (KotlinType) element$iv;
                Iterable list$iv = it.getMemberScope().getVariableNames();
                CollectionsKt.addAll(destination$iv, list$iv);
            }
            return (Set) destination$iv;
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        @Nullable
        protected Set<Name> getNonDeclaredClassifierNames() {
            Collection collection;
            Iterable $this$flatMapToNullable$iv = getClassDescriptor().typeConstructor.mo3835getSupertypes();
            Collection destination$iv = new LinkedHashSet();
            Iterator it = $this$flatMapToNullable$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    KotlinType it2 = (KotlinType) element$iv;
                    Iterable list$iv = it2.getMemberScope().getClassifierNames();
                    if (list$iv == null) {
                        collection = null;
                        break;
                    }
                    CollectionsKt.addAll(destination$iv, list$iv);
                } else {
                    collection = destination$iv;
                    break;
                }
            }
            return (Set) collection;
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
        @Nullable
        /* renamed from: getContributedClassifier */
        public ClassifierDescriptor mo3858getContributedClassifier(@NotNull Name name, @NotNull LookupLocation location) {
            ClassDescriptor it;
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(location, "location");
            recordLookup(name, location);
            EnumEntryClassDescriptors enumEntryClassDescriptors = getClassDescriptor().enumEntries;
            if (enumEntryClassDescriptors != null && (it = enumEntryClassDescriptors.findEnumEntry(name)) != null) {
                return it;
            }
            return super.mo3858getContributedClassifier(name, location);
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        @NotNull
        protected ClassId createClassId(@NotNull Name name) {
            Intrinsics.checkNotNullParameter(name, "name");
            ClassId classIdCreateNestedClassId = this.this$0.classId.createNestedClassId(name);
            Intrinsics.checkNotNullExpressionValue(classIdCreateNestedClassId, "classId.createNestedClassId(name)");
            return classIdCreateNestedClassId;
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        protected void addEnumEntryDescriptors(@NotNull Collection<DeclarationDescriptor> result, @NotNull Function1<? super Name, Boolean> nameFilter) {
            Intrinsics.checkNotNullParameter(result, "result");
            Intrinsics.checkNotNullParameter(nameFilter, "nameFilter");
            EnumEntryClassDescriptors enumEntryClassDescriptors = getClassDescriptor().enumEntries;
            List listAll = enumEntryClassDescriptors == null ? null : enumEntryClassDescriptors.all();
            if (listAll == null) {
                listAll = CollectionsKt.emptyList();
            }
            result.addAll(listAll);
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
        public void recordLookup(@NotNull Name name, @NotNull LookupLocation location) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(location, "location");
            UtilsKt.record(getC().getComponents().getLookupTracker(), location, getClassDescriptor(), name);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DeserializedClassDescriptor.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/serialization/deserialization/descriptors/DeserializedClassDescriptor$EnumEntryClassDescriptors.class */
    final class EnumEntryClassDescriptors {

        @NotNull
        private final Map<Name, ProtoBuf.EnumEntry> enumEntryProtos;

        @NotNull
        private final MemoizedFunctionToNullable<Name, ClassDescriptor> enumEntryByName;

        @NotNull
        private final NotNullLazyValue<Set<Name>> enumMemberNames;
        final /* synthetic */ DeserializedClassDescriptor this$0;

        public EnumEntryClassDescriptors(DeserializedClassDescriptor this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
            Iterable enumEntryList = this.this$0.getClassProto().getEnumEntryList();
            Intrinsics.checkNotNullExpressionValue(enumEntryList, "classProto.enumEntryList");
            Iterable $this$associateBy$iv = enumEntryList;
            DeserializedClassDescriptor deserializedClassDescriptor = this.this$0;
            int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateBy$iv, 10)), 16);
            Map destination$iv$iv = new LinkedHashMap(capacity$iv);
            for (Object element$iv$iv : $this$associateBy$iv) {
                ProtoBuf.EnumEntry it = (ProtoBuf.EnumEntry) element$iv$iv;
                destination$iv$iv.put(NameResolverUtilKt.getName(deserializedClassDescriptor.getC().getNameResolver(), it.getName()), element$iv$iv);
            }
            this.enumEntryProtos = destination$iv$iv;
            StorageManager storageManager = this.this$0.getC().getStorageManager();
            final DeserializedClassDescriptor deserializedClassDescriptor2 = this.this$0;
            this.enumEntryByName = storageManager.createMemoizedFunctionWithNullableValues(new Function1<Name, ClassDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$EnumEntryClassDescriptors$enumEntryByName$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                @Nullable
                public final ClassDescriptor invoke(@NotNull Name name) {
                    EnumEntrySyntheticClassDescriptor enumEntrySyntheticClassDescriptorCreate;
                    Intrinsics.checkNotNullParameter(name, "name");
                    final ProtoBuf.EnumEntry proto = (ProtoBuf.EnumEntry) this.this$0.enumEntryProtos.get(name);
                    if (proto == null) {
                        enumEntrySyntheticClassDescriptorCreate = null;
                    } else {
                        final DeserializedClassDescriptor deserializedClassDescriptor3 = deserializedClassDescriptor2;
                        enumEntrySyntheticClassDescriptorCreate = EnumEntrySyntheticClassDescriptor.create(deserializedClassDescriptor3.getC().getStorageManager(), deserializedClassDescriptor3, name, this.this$0.enumMemberNames, new DeserializedAnnotations(deserializedClassDescriptor3.getC().getStorageManager(), new Function0<List<? extends AnnotationDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$EnumEntryClassDescriptors$enumEntryByName$1$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            @NotNull
                            public final List<? extends AnnotationDescriptor> invoke() {
                                return CollectionsKt.toList(deserializedClassDescriptor3.getC().getComponents().getAnnotationAndConstantLoader().loadEnumEntryAnnotations(deserializedClassDescriptor3.getThisAsProtoContainer$deserialization(), proto));
                            }
                        }), SourceElement.NO_SOURCE);
                    }
                    return enumEntrySyntheticClassDescriptorCreate;
                }
            });
            this.enumMemberNames = this.this$0.getC().getStorageManager().createLazyValue(new Function0<Set<? extends Name>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$EnumEntryClassDescriptors$enumMemberNames$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                @NotNull
                public final Set<? extends Name> invoke() {
                    return this.this$0.computeEnumMemberNames();
                }
            });
        }

        @Nullable
        public final ClassDescriptor findEnumEntry(@NotNull Name name) {
            Intrinsics.checkNotNullParameter(name, "name");
            return this.enumEntryByName.invoke(name);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Set<Name> computeEnumMemberNames() {
            HashSet result = new HashSet();
            for (KotlinType supertype : this.this$0.getTypeConstructor().mo3835getSupertypes()) {
                for (DeclarationDescriptor descriptor : ResolutionScope.DefaultImpls.getContributedDescriptors$default(supertype.getMemberScope(), null, null, 3, null)) {
                    if ((descriptor instanceof SimpleFunctionDescriptor) || (descriptor instanceof PropertyDescriptor)) {
                        result.add(descriptor.getName());
                    }
                }
            }
            Iterable functionList = this.this$0.getClassProto().getFunctionList();
            Intrinsics.checkNotNullExpressionValue(functionList, "classProto.functionList");
            Iterable $this$mapTo$iv = functionList;
            DeserializedClassDescriptor deserializedClassDescriptor = this.this$0;
            for (Object item$iv : $this$mapTo$iv) {
                ProtoBuf.Function it = (ProtoBuf.Function) item$iv;
                result.add(NameResolverUtilKt.getName(deserializedClassDescriptor.getC().getNameResolver(), it.getName()));
            }
            HashSet hashSet = result;
            Iterable propertyList = this.this$0.getClassProto().getPropertyList();
            Intrinsics.checkNotNullExpressionValue(propertyList, "classProto.propertyList");
            Iterable $this$mapTo$iv2 = propertyList;
            DeserializedClassDescriptor deserializedClassDescriptor2 = this.this$0;
            for (Object item$iv2 : $this$mapTo$iv2) {
                ProtoBuf.Property it2 = (ProtoBuf.Property) item$iv2;
                result.add(NameResolverUtilKt.getName(deserializedClassDescriptor2.getC().getNameResolver(), it2.getName()));
            }
            return SetsKt.plus((Set) hashSet, (Iterable) result);
        }

        @NotNull
        public final Collection<ClassDescriptor> all() {
            Iterable $this$mapNotNull$iv = this.enumEntryProtos.keySet();
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                Name name = (Name) element$iv$iv$iv;
                ClassDescriptor classDescriptorFindEnumEntry = findEnumEntry(name);
                if (classDescriptorFindEnumEntry != null) {
                    destination$iv$iv.add(classDescriptorFindEnumEntry);
                }
            }
            return (List) destination$iv$iv;
        }
    }
}
