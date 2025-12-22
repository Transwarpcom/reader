package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.SpecialJvmAnnotations;
import kotlin.reflect.jvm.internal.impl.builtins.UnsignedTypes;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MemberSignature;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ClassMapperLite;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AbstractBinaryClassAnnotationAndConstantLoader.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/AbstractBinaryClassAnnotationAndConstantLoader.class */
public abstract class AbstractBinaryClassAnnotationAndConstantLoader<A, C> implements AnnotationAndConstantLoader<A, C> {

    @NotNull
    private final KotlinClassFinder kotlinClassFinder;

    @NotNull
    private final MemoizedFunctionToNotNull<KotlinJvmBinaryClass, Storage<A, C>> storage;

    /* compiled from: AbstractBinaryClassAnnotationAndConstantLoader.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/AbstractBinaryClassAnnotationAndConstantLoader$PropertyRelatedElement.class */
    private enum PropertyRelatedElement {
        PROPERTY,
        BACKING_FIELD,
        DELEGATE_FIELD
    }

    /* compiled from: AbstractBinaryClassAnnotationAndConstantLoader.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/AbstractBinaryClassAnnotationAndConstantLoader$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AnnotatedCallableKind.values().length];
            iArr[AnnotatedCallableKind.PROPERTY_GETTER.ordinal()] = 1;
            iArr[AnnotatedCallableKind.PROPERTY_SETTER.ordinal()] = 2;
            iArr[AnnotatedCallableKind.PROPERTY.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Nullable
    protected abstract C loadConstant(@NotNull String str, @NotNull Object obj);

    @Nullable
    protected abstract C transformToUnsignedConstant(@NotNull C c);

    @Nullable
    protected abstract KotlinJvmBinaryClass.AnnotationArgumentVisitor loadAnnotation(@NotNull ClassId classId, @NotNull SourceElement sourceElement, @NotNull List<A> list);

    @NotNull
    protected abstract A loadTypeAnnotation(@NotNull ProtoBuf.Annotation annotation, @NotNull NameResolver nameResolver);

    public AbstractBinaryClassAnnotationAndConstantLoader(@NotNull StorageManager storageManager, @NotNull KotlinClassFinder kotlinClassFinder) {
        Intrinsics.checkNotNullParameter(storageManager, "storageManager");
        Intrinsics.checkNotNullParameter(kotlinClassFinder, "kotlinClassFinder");
        this.kotlinClassFinder = kotlinClassFinder;
        this.storage = storageManager.createMemoizedFunction(new Function1<KotlinJvmBinaryClass, Storage<? extends A, ? extends C>>(this) { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader$storage$1
            final /* synthetic */ AbstractBinaryClassAnnotationAndConstantLoader<A, C> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final AbstractBinaryClassAnnotationAndConstantLoader.Storage<A, C> invoke(@NotNull KotlinJvmBinaryClass kotlinClass) {
                Intrinsics.checkNotNullParameter(kotlinClass, "kotlinClass");
                return this.this$0.loadAnnotationsAndInitializers(kotlinClass);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final KotlinJvmBinaryClass.AnnotationArgumentVisitor loadAnnotationIfNotSpecial(ClassId annotationClassId, SourceElement source, List<A> list) {
        if (SpecialJvmAnnotations.INSTANCE.getSPECIAL_ANNOTATIONS().contains(annotationClassId)) {
            return null;
        }
        return loadAnnotation(annotationClassId, source, list);
    }

    private final KotlinJvmBinaryClass toBinaryClass(ProtoContainer.Class $this$toBinaryClass) {
        SourceElement source = $this$toBinaryClass.getSource();
        KotlinJvmBinarySourceElement kotlinJvmBinarySourceElement = source instanceof KotlinJvmBinarySourceElement ? (KotlinJvmBinarySourceElement) source : null;
        if (kotlinJvmBinarySourceElement == null) {
            return null;
        }
        return kotlinJvmBinarySourceElement.getBinaryClass();
    }

    @Nullable
    protected byte[] getCachedFileContent(@NotNull KotlinJvmBinaryClass kotlinClass) {
        Intrinsics.checkNotNullParameter(kotlinClass, "kotlinClass");
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
    @NotNull
    public List<A> loadClassAnnotations(@NotNull ProtoContainer.Class container) {
        Intrinsics.checkNotNullParameter(container, "container");
        KotlinJvmBinaryClass kotlinClass = toBinaryClass(container);
        if (kotlinClass == null) {
            throw new IllegalStateException(Intrinsics.stringPlus("Class for loading annotations is not found: ", container.debugFqName()).toString());
        }
        final ArrayList result = new ArrayList(1);
        kotlinClass.loadClassAnnotations(new KotlinJvmBinaryClass.AnnotationVisitor(this) { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader.loadClassAnnotations.1
            final /* synthetic */ AbstractBinaryClassAnnotationAndConstantLoader<A, C> this$0;

            {
                this.this$0 = this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor
            @Nullable
            public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(@NotNull ClassId classId, @NotNull SourceElement source) {
                Intrinsics.checkNotNullParameter(classId, "classId");
                Intrinsics.checkNotNullParameter(source, "source");
                return this.this$0.loadAnnotationIfNotSpecial(classId, source, result);
            }

            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor
            public void visitEnd() {
            }
        }, getCachedFileContent(kotlinClass));
        return result;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
    @NotNull
    public List<A> loadCallableAnnotations(@NotNull ProtoContainer container, @NotNull MessageLite proto, @NotNull AnnotatedCallableKind kind) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(kind, "kind");
        if (kind == AnnotatedCallableKind.PROPERTY) {
            return loadPropertyAnnotations(container, (ProtoBuf.Property) proto, PropertyRelatedElement.PROPERTY);
        }
        MemberSignature signature = getCallableSignature$default(this, proto, container.getNameResolver(), container.getTypeTable(), kind, false, 16, null);
        return signature == null ? CollectionsKt.emptyList() : findClassAndLoadMemberAnnotations$default(this, container, signature, false, false, null, false, 60, null);
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
    @NotNull
    public List<A> loadPropertyBackingFieldAnnotations(@NotNull ProtoContainer container, @NotNull ProtoBuf.Property proto) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(proto, "proto");
        return loadPropertyAnnotations(container, proto, PropertyRelatedElement.BACKING_FIELD);
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
    @NotNull
    public List<A> loadPropertyDelegateFieldAnnotations(@NotNull ProtoContainer container, @NotNull ProtoBuf.Property proto) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(proto, "proto");
        return loadPropertyAnnotations(container, proto, PropertyRelatedElement.DELEGATE_FIELD);
    }

    private final List<A> loadPropertyAnnotations(ProtoContainer container, ProtoBuf.Property proto, PropertyRelatedElement element) {
        Boolean bool = Flags.IS_CONST.get(proto.getFlags());
        Intrinsics.checkNotNullExpressionValue(bool, "IS_CONST.get(proto.flags)");
        boolean isConst = bool.booleanValue();
        JvmProtoBufUtil jvmProtoBufUtil = JvmProtoBufUtil.INSTANCE;
        boolean isMovedFromInterfaceCompanion = JvmProtoBufUtil.isMovedFromInterfaceCompanion(proto);
        if (element == PropertyRelatedElement.PROPERTY) {
            MemberSignature syntheticFunctionSignature = getPropertySignature$default(this, proto, container.getNameResolver(), container.getTypeTable(), false, true, false, 40, null);
            return syntheticFunctionSignature == null ? CollectionsKt.emptyList() : findClassAndLoadMemberAnnotations$default(this, container, syntheticFunctionSignature, true, false, Boolean.valueOf(isConst), isMovedFromInterfaceCompanion, 8, null);
        }
        MemberSignature fieldSignature = getPropertySignature$default(this, proto, container.getNameResolver(), container.getTypeTable(), true, false, false, 48, null);
        if (fieldSignature == null) {
            return CollectionsKt.emptyList();
        }
        boolean isDelegated = StringsKt.contains$default((CharSequence) fieldSignature.getSignature(), (CharSequence) "$delegate", false, 2, (Object) null);
        return isDelegated != (element == PropertyRelatedElement.DELEGATE_FIELD) ? CollectionsKt.emptyList() : findClassAndLoadMemberAnnotations(container, fieldSignature, true, true, Boolean.valueOf(isConst), isMovedFromInterfaceCompanion);
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
    @NotNull
    public List<A> loadEnumEntryAnnotations(@NotNull ProtoContainer container, @NotNull ProtoBuf.EnumEntry proto) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(proto, "proto");
        MemberSignature.Companion companion = MemberSignature.Companion;
        String string = container.getNameResolver().getString(proto.getName());
        ClassMapperLite classMapperLite = ClassMapperLite.INSTANCE;
        String strAsString = ((ProtoContainer.Class) container).getClassId().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "container as ProtoContainer.Class).classId.asString()");
        MemberSignature signature = companion.fromFieldNameAndDesc(string, ClassMapperLite.mapClass(strAsString));
        return findClassAndLoadMemberAnnotations$default(this, container, signature, false, false, null, false, 60, null);
    }

    static /* synthetic */ List findClassAndLoadMemberAnnotations$default(AbstractBinaryClassAnnotationAndConstantLoader abstractBinaryClassAnnotationAndConstantLoader, ProtoContainer protoContainer, MemberSignature memberSignature, boolean z, boolean z2, Boolean bool, boolean z3, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: findClassAndLoadMemberAnnotations");
        }
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        if ((i & 16) != 0) {
            bool = null;
        }
        if ((i & 32) != 0) {
            z3 = false;
        }
        return abstractBinaryClassAnnotationAndConstantLoader.findClassAndLoadMemberAnnotations(protoContainer, memberSignature, z, z2, bool, z3);
    }

    private final List<A> findClassAndLoadMemberAnnotations(ProtoContainer container, MemberSignature signature, boolean property, boolean field, Boolean isConst, boolean isMovedFromInterfaceCompanion) {
        List<A> list;
        KotlinJvmBinaryClass kotlinClass = findClassWithAnnotationsAndInitializers(container, getSpecialCaseContainerClass(container, property, field, isConst, isMovedFromInterfaceCompanion));
        if (kotlinClass != null && (list = this.storage.invoke(kotlinClass).getMemberAnnotations().get(signature)) != null) {
            return list;
        }
        return CollectionsKt.emptyList();
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
    @NotNull
    public List<A> loadValueParameterAnnotations(@NotNull ProtoContainer container, @NotNull MessageLite callableProto, @NotNull AnnotatedCallableKind kind, int parameterIndex, @NotNull ProtoBuf.ValueParameter proto) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(callableProto, "callableProto");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(proto, "proto");
        MemberSignature methodSignature = getCallableSignature$default(this, callableProto, container.getNameResolver(), container.getTypeTable(), kind, false, 16, null);
        if (methodSignature == null) {
            return CollectionsKt.emptyList();
        }
        int index = parameterIndex + computeJvmParameterIndexShift(container, callableProto);
        MemberSignature paramSignature = MemberSignature.Companion.fromMethodSignatureAndParameterIndex(methodSignature, index);
        return findClassAndLoadMemberAnnotations$default(this, container, paramSignature, false, false, null, false, 60, null);
    }

    private final int computeJvmParameterIndexShift(ProtoContainer container, MessageLite message) {
        if (message instanceof ProtoBuf.Function) {
            return ProtoTypeTableUtilKt.hasReceiver((ProtoBuf.Function) message) ? 1 : 0;
        }
        if (message instanceof ProtoBuf.Property) {
            return ProtoTypeTableUtilKt.hasReceiver((ProtoBuf.Property) message) ? 1 : 0;
        }
        if (message instanceof ProtoBuf.Constructor) {
            if (((ProtoContainer.Class) container).getKind() == ProtoBuf.Class.Kind.ENUM_CLASS) {
                return 2;
            }
            return ((ProtoContainer.Class) container).isInner() ? 1 : 0;
        }
        throw new UnsupportedOperationException(Intrinsics.stringPlus("Unsupported message: ", message.getClass()));
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
    @NotNull
    public List<A> loadExtensionReceiverParameterAnnotations(@NotNull ProtoContainer container, @NotNull MessageLite proto, @NotNull AnnotatedCallableKind kind) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(kind, "kind");
        MemberSignature methodSignature = getCallableSignature$default(this, proto, container.getNameResolver(), container.getTypeTable(), kind, false, 16, null);
        if (methodSignature != null) {
            MemberSignature paramSignature = MemberSignature.Companion.fromMethodSignatureAndParameterIndex(methodSignature, 0);
            return findClassAndLoadMemberAnnotations$default(this, container, paramSignature, false, false, null, false, 60, null);
        }
        return CollectionsKt.emptyList();
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
    @NotNull
    public List<A> loadTypeAnnotations(@NotNull ProtoBuf.Type proto, @NotNull NameResolver nameResolver) {
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Object extension = proto.getExtension(JvmProtoBuf.typeAnnotation);
        Intrinsics.checkNotNullExpressionValue(extension, "proto.getExtension(JvmProtoBuf.typeAnnotation)");
        Iterable $this$map$iv = (Iterable) extension;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            ProtoBuf.Annotation it = (ProtoBuf.Annotation) item$iv$iv;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            destination$iv$iv.add(loadTypeAnnotation(it, nameResolver));
        }
        return (List) destination$iv$iv;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
    @NotNull
    public List<A> loadTypeParameterAnnotations(@NotNull ProtoBuf.TypeParameter proto, @NotNull NameResolver nameResolver) {
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Object extension = proto.getExtension(JvmProtoBuf.typeParameterAnnotation);
        Intrinsics.checkNotNullExpressionValue(extension, "proto.getExtension(JvmProtoBuf.typeParameterAnnotation)");
        Iterable $this$map$iv = (Iterable) extension;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            ProtoBuf.Annotation it = (ProtoBuf.Annotation) item$iv$iv;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            destination$iv$iv.add(loadTypeAnnotation(it, nameResolver));
        }
        return (List) destination$iv$iv;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
    @Nullable
    public C loadPropertyConstant(@NotNull ProtoContainer container, @NotNull ProtoBuf.Property proto, @NotNull KotlinType expectedType) {
        C c;
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(expectedType, "expectedType");
        Boolean bool = Flags.IS_CONST.get(proto.getFlags());
        JvmProtoBufUtil jvmProtoBufUtil = JvmProtoBufUtil.INSTANCE;
        KotlinJvmBinaryClass kotlinJvmBinaryClassFindClassWithAnnotationsAndInitializers = findClassWithAnnotationsAndInitializers(container, getSpecialCaseContainerClass(container, true, true, bool, JvmProtoBufUtil.isMovedFromInterfaceCompanion(proto)));
        if (kotlinJvmBinaryClassFindClassWithAnnotationsAndInitializers == null) {
            return null;
        }
        MemberSignature callableSignature = getCallableSignature(proto, container.getNameResolver(), container.getTypeTable(), AnnotatedCallableKind.PROPERTY, kotlinJvmBinaryClassFindClassWithAnnotationsAndInitializers.getClassHeader().getMetadataVersion().isAtLeast(DeserializedDescriptorResolver.Companion.getKOTLIN_1_3_RC_METADATA_VERSION$descriptors_jvm()));
        if (callableSignature == null || (c = this.storage.invoke(kotlinJvmBinaryClassFindClassWithAnnotationsAndInitializers).getPropertyConstants().get(callableSignature)) == null) {
            return null;
        }
        UnsignedTypes unsignedTypes = UnsignedTypes.INSTANCE;
        return UnsignedTypes.isUnsignedType(expectedType) ? transformToUnsignedConstant(c) : c;
    }

    private final KotlinJvmBinaryClass findClassWithAnnotationsAndInitializers(ProtoContainer container, KotlinJvmBinaryClass specialCase) {
        if (specialCase != null) {
            return specialCase;
        }
        if (container instanceof ProtoContainer.Class) {
            return toBinaryClass((ProtoContainer.Class) container);
        }
        return null;
    }

    private final KotlinJvmBinaryClass getSpecialCaseContainerClass(ProtoContainer container, boolean property, boolean field, Boolean isConst, boolean isMovedFromInterfaceCompanion) {
        ProtoContainer.Class outerClass;
        if (property) {
            if (isConst == null) {
                throw new IllegalStateException(("isConst should not be null for property (container=" + container + ')').toString());
            }
            if ((container instanceof ProtoContainer.Class) && ((ProtoContainer.Class) container).getKind() == ProtoBuf.Class.Kind.INTERFACE) {
                KotlinClassFinder kotlinClassFinder = this.kotlinClassFinder;
                ClassId classIdCreateNestedClassId = ((ProtoContainer.Class) container).getClassId().createNestedClassId(Name.identifier("DefaultImpls"));
                Intrinsics.checkNotNullExpressionValue(classIdCreateNestedClassId, "container.classId.createNestedClassId(Name.identifier(JvmAbi.DEFAULT_IMPLS_CLASS_NAME))");
                return KotlinClassFinderKt.findKotlinClass(kotlinClassFinder, classIdCreateNestedClassId);
            }
            if (isConst.booleanValue() && (container instanceof ProtoContainer.Package)) {
                SourceElement source = container.getSource();
                JvmPackagePartSource jvmPackagePartSource = source instanceof JvmPackagePartSource ? (JvmPackagePartSource) source : null;
                JvmClassName facadeClassName = jvmPackagePartSource == null ? null : jvmPackagePartSource.getFacadeClassName();
                if (facadeClassName != null) {
                    KotlinClassFinder kotlinClassFinder2 = this.kotlinClassFinder;
                    String internalName = facadeClassName.getInternalName();
                    Intrinsics.checkNotNullExpressionValue(internalName, "facadeClassName.internalName");
                    ClassId classId = ClassId.topLevel(new FqName(StringsKt.replace$default(internalName, '/', '.', false, 4, (Object) null)));
                    Intrinsics.checkNotNullExpressionValue(classId, "topLevel(FqName(facadeClassName.internalName.replace('/', '.')))");
                    return KotlinClassFinderKt.findKotlinClass(kotlinClassFinder2, classId);
                }
            }
        }
        if (field && (container instanceof ProtoContainer.Class) && ((ProtoContainer.Class) container).getKind() == ProtoBuf.Class.Kind.COMPANION_OBJECT && (outerClass = ((ProtoContainer.Class) container).getOuterClass()) != null && (outerClass.getKind() == ProtoBuf.Class.Kind.CLASS || outerClass.getKind() == ProtoBuf.Class.Kind.ENUM_CLASS || (isMovedFromInterfaceCompanion && (outerClass.getKind() == ProtoBuf.Class.Kind.INTERFACE || outerClass.getKind() == ProtoBuf.Class.Kind.ANNOTATION_CLASS)))) {
            return toBinaryClass(outerClass);
        }
        if ((container instanceof ProtoContainer.Package) && (container.getSource() instanceof JvmPackagePartSource)) {
            SourceElement source2 = container.getSource();
            if (source2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.load.kotlin.JvmPackagePartSource");
            }
            JvmPackagePartSource jvmPackagePartSource2 = (JvmPackagePartSource) source2;
            KotlinJvmBinaryClass knownJvmBinaryClass = jvmPackagePartSource2.getKnownJvmBinaryClass();
            if (knownJvmBinaryClass != null) {
                return knownJvmBinaryClass;
            }
            return KotlinClassFinderKt.findKotlinClass(this.kotlinClassFinder, jvmPackagePartSource2.getClassId());
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Storage<A, C> loadAnnotationsAndInitializers(KotlinJvmBinaryClass kotlinClass) {
        final HashMap memberAnnotations = new HashMap();
        final HashMap propertyConstants = new HashMap();
        kotlinClass.visitMembers(new KotlinJvmBinaryClass.MemberVisitor(this) { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader.loadAnnotationsAndInitializers.1
            final /* synthetic */ AbstractBinaryClassAnnotationAndConstantLoader<A, C> this$0;

            {
                this.this$0 = this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.MemberVisitor
            @Nullable
            public KotlinJvmBinaryClass.MethodAnnotationVisitor visitMethod(@NotNull Name name, @NotNull String desc) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(desc, "desc");
                MemberSignature.Companion companion = MemberSignature.Companion;
                String strAsString = name.asString();
                Intrinsics.checkNotNullExpressionValue(strAsString, "name.asString()");
                return new AnnotationVisitorForMethod(this, companion.fromMethodNameAndDesc(strAsString, desc));
            }

            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.MemberVisitor
            @Nullable
            public KotlinJvmBinaryClass.AnnotationVisitor visitField(@NotNull Name name, @NotNull String desc, @Nullable Object initializer) {
                C cLoadConstant;
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(desc, "desc");
                MemberSignature.Companion companion = MemberSignature.Companion;
                String strAsString = name.asString();
                Intrinsics.checkNotNullExpressionValue(strAsString, "name.asString()");
                MemberSignature signature = companion.fromFieldNameAndDesc(strAsString, desc);
                if (initializer != null && (cLoadConstant = this.this$0.loadConstant(desc, initializer)) != null) {
                    propertyConstants.put(signature, cLoadConstant);
                }
                return new MemberAnnotationVisitor(this, signature);
            }

            /* compiled from: AbstractBinaryClassAnnotationAndConstantLoader.kt */
            /* renamed from: kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader$loadAnnotationsAndInitializers$1$AnnotationVisitorForMethod */
            /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/AbstractBinaryClassAnnotationAndConstantLoader$loadAnnotationsAndInitializers$1$AnnotationVisitorForMethod.class */
            public final class AnnotationVisitorForMethod extends MemberAnnotationVisitor implements KotlinJvmBinaryClass.MethodAnnotationVisitor {
                final /* synthetic */ AnonymousClass1 this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnnotationVisitorForMethod(@NotNull AnonymousClass1 this$0, MemberSignature signature) {
                    super(this$0, signature);
                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                    Intrinsics.checkNotNullParameter(signature, "signature");
                    this.this$0 = this$0;
                }

                @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.MethodAnnotationVisitor
                @Nullable
                public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitParameterAnnotation(int index, @NotNull ClassId classId, @NotNull SourceElement source) {
                    Intrinsics.checkNotNullParameter(classId, "classId");
                    Intrinsics.checkNotNullParameter(source, "source");
                    MemberSignature paramSignature = MemberSignature.Companion.fromMethodSignatureAndParameterIndex(getSignature(), index);
                    List result = memberAnnotations.get(paramSignature);
                    if (result == null) {
                        result = new ArrayList();
                        memberAnnotations.put(paramSignature, result);
                    }
                    return this.this$0.this$0.loadAnnotationIfNotSpecial(classId, source, result);
                }
            }

            /* compiled from: AbstractBinaryClassAnnotationAndConstantLoader.kt */
            /* renamed from: kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader$loadAnnotationsAndInitializers$1$MemberAnnotationVisitor */
            /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/AbstractBinaryClassAnnotationAndConstantLoader$loadAnnotationsAndInitializers$1$MemberAnnotationVisitor.class */
            public class MemberAnnotationVisitor implements KotlinJvmBinaryClass.AnnotationVisitor {

                @NotNull
                private final MemberSignature signature;

                @NotNull
                private final ArrayList<A> result;
                final /* synthetic */ AnonymousClass1 this$0;

                public MemberAnnotationVisitor(@NotNull AnonymousClass1 this$0, MemberSignature signature) {
                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                    Intrinsics.checkNotNullParameter(signature, "signature");
                    this.this$0 = this$0;
                    this.signature = signature;
                    this.result = new ArrayList<>();
                }

                @NotNull
                protected final MemberSignature getSignature() {
                    return this.signature;
                }

                @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor
                @Nullable
                public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(@NotNull ClassId classId, @NotNull SourceElement source) {
                    Intrinsics.checkNotNullParameter(classId, "classId");
                    Intrinsics.checkNotNullParameter(source, "source");
                    return this.this$0.this$0.loadAnnotationIfNotSpecial(classId, source, this.result);
                }

                @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor
                public void visitEnd() {
                    if (!this.result.isEmpty()) {
                        memberAnnotations.put(this.signature, this.result);
                    }
                }
            }
        }, getCachedFileContent(kotlinClass));
        return new Storage<>(memberAnnotations, propertyConstants);
    }

    static /* synthetic */ MemberSignature getPropertySignature$default(AbstractBinaryClassAnnotationAndConstantLoader abstractBinaryClassAnnotationAndConstantLoader, ProtoBuf.Property property, NameResolver nameResolver, TypeTable typeTable, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getPropertySignature");
        }
        if ((i & 8) != 0) {
            z = false;
        }
        if ((i & 16) != 0) {
            z2 = false;
        }
        if ((i & 32) != 0) {
            z3 = true;
        }
        return abstractBinaryClassAnnotationAndConstantLoader.getPropertySignature(property, nameResolver, typeTable, z, z2, z3);
    }

    private final MemberSignature getPropertySignature(ProtoBuf.Property proto, NameResolver nameResolver, TypeTable typeTable, boolean field, boolean synthetic, boolean requireHasFieldFlagForField) {
        GeneratedMessageLite.GeneratedExtension<ProtoBuf.Property, JvmProtoBuf.JvmPropertySignature> propertySignature = JvmProtoBuf.propertySignature;
        Intrinsics.checkNotNullExpressionValue(propertySignature, "propertySignature");
        JvmProtoBuf.JvmPropertySignature signature = (JvmProtoBuf.JvmPropertySignature) ProtoBufUtilKt.getExtensionOrNull(proto, propertySignature);
        if (signature == null) {
            return null;
        }
        if (field) {
            JvmMemberSignature.Field fieldSignature = JvmProtoBufUtil.INSTANCE.getJvmFieldSignature(proto, nameResolver, typeTable, requireHasFieldFlagForField);
            if (fieldSignature == null) {
                return null;
            }
            return MemberSignature.Companion.fromJvmMemberSignature(fieldSignature);
        }
        if (synthetic && signature.hasSyntheticMethod()) {
            MemberSignature.Companion companion = MemberSignature.Companion;
            JvmProtoBuf.JvmMethodSignature syntheticMethod = signature.getSyntheticMethod();
            Intrinsics.checkNotNullExpressionValue(syntheticMethod, "signature.syntheticMethod");
            return companion.fromMethod(nameResolver, syntheticMethod);
        }
        return null;
    }

    static /* synthetic */ MemberSignature getCallableSignature$default(AbstractBinaryClassAnnotationAndConstantLoader abstractBinaryClassAnnotationAndConstantLoader, MessageLite messageLite, NameResolver nameResolver, TypeTable typeTable, AnnotatedCallableKind annotatedCallableKind, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getCallableSignature");
        }
        if ((i & 16) != 0) {
            z = false;
        }
        return abstractBinaryClassAnnotationAndConstantLoader.getCallableSignature(messageLite, nameResolver, typeTable, annotatedCallableKind, z);
    }

    private final MemberSignature getCallableSignature(MessageLite proto, NameResolver nameResolver, TypeTable typeTable, AnnotatedCallableKind kind, boolean requireHasFieldFlagForField) {
        if (proto instanceof ProtoBuf.Constructor) {
            MemberSignature.Companion companion = MemberSignature.Companion;
            JvmMemberSignature.Method jvmConstructorSignature = JvmProtoBufUtil.INSTANCE.getJvmConstructorSignature((ProtoBuf.Constructor) proto, nameResolver, typeTable);
            if (jvmConstructorSignature == null) {
                return null;
            }
            return companion.fromJvmMemberSignature(jvmConstructorSignature);
        }
        if (proto instanceof ProtoBuf.Function) {
            MemberSignature.Companion companion2 = MemberSignature.Companion;
            JvmMemberSignature.Method jvmMethodSignature = JvmProtoBufUtil.INSTANCE.getJvmMethodSignature((ProtoBuf.Function) proto, nameResolver, typeTable);
            if (jvmMethodSignature == null) {
                return null;
            }
            return companion2.fromJvmMemberSignature(jvmMethodSignature);
        }
        if (proto instanceof ProtoBuf.Property) {
            GeneratedMessageLite.GeneratedExtension<ProtoBuf.Property, JvmProtoBuf.JvmPropertySignature> propertySignature = JvmProtoBuf.propertySignature;
            Intrinsics.checkNotNullExpressionValue(propertySignature, "propertySignature");
            JvmProtoBuf.JvmPropertySignature signature = (JvmProtoBuf.JvmPropertySignature) ProtoBufUtilKt.getExtensionOrNull((GeneratedMessageLite.ExtendableMessage) proto, propertySignature);
            if (signature == null) {
                return null;
            }
            switch (WhenMappings.$EnumSwitchMapping$0[kind.ordinal()]) {
                case 1:
                    if (signature.hasGetter()) {
                        MemberSignature.Companion companion3 = MemberSignature.Companion;
                        JvmProtoBuf.JvmMethodSignature getter = signature.getGetter();
                        Intrinsics.checkNotNullExpressionValue(getter, "signature.getter");
                        break;
                    }
                    break;
                case 2:
                    if (signature.hasSetter()) {
                        MemberSignature.Companion companion4 = MemberSignature.Companion;
                        JvmProtoBuf.JvmMethodSignature setter = signature.getSetter();
                        Intrinsics.checkNotNullExpressionValue(setter, "signature.setter");
                        break;
                    }
                    break;
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AbstractBinaryClassAnnotationAndConstantLoader.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/AbstractBinaryClassAnnotationAndConstantLoader$Storage.class */
    static final class Storage<A, C> {

        @NotNull
        private final Map<MemberSignature, List<A>> memberAnnotations;

        @NotNull
        private final Map<MemberSignature, C> propertyConstants;

        /* JADX WARN: Multi-variable type inference failed */
        public Storage(@NotNull Map<MemberSignature, ? extends List<? extends A>> memberAnnotations, @NotNull Map<MemberSignature, ? extends C> propertyConstants) {
            Intrinsics.checkNotNullParameter(memberAnnotations, "memberAnnotations");
            Intrinsics.checkNotNullParameter(propertyConstants, "propertyConstants");
            this.memberAnnotations = memberAnnotations;
            this.propertyConstants = propertyConstants;
        }

        @NotNull
        public final Map<MemberSignature, List<A>> getMemberAnnotations() {
            return this.memberAnnotations;
        }

        @NotNull
        public final Map<MemberSignature, C> getPropertyConstants() {
            return this.propertyConstants;
        }
    }
}
