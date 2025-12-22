package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedAnnotations;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedTypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.DefinitelyNotNullType;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionForAbsentTypeParameter;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TypeDeserializer.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/serialization/deserialization/TypeDeserializer.class */
public final class TypeDeserializer {

    @NotNull
    private final DeserializationContext c;

    @Nullable
    private final TypeDeserializer parent;

    @NotNull
    private final String debugName;

    @NotNull
    private final String containerPresentableName;
    private boolean experimentalSuspendFunctionTypeEncountered;

    @NotNull
    private final Function1<Integer, ClassifierDescriptor> classifierDescriptors;

    @NotNull
    private final Function1<Integer, ClassifierDescriptor> typeAliasDescriptors;

    @NotNull
    private final Map<Integer, TypeParameterDescriptor> typeParameterDescriptors;

    public TypeDeserializer(@NotNull DeserializationContext c, @Nullable TypeDeserializer parent, @NotNull List<ProtoBuf.TypeParameter> typeParameterProtos, @NotNull String debugName, @NotNull String containerPresentableName, boolean experimentalSuspendFunctionTypeEncountered) {
        LinkedHashMap linkedHashMapEmptyMap;
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(typeParameterProtos, "typeParameterProtos");
        Intrinsics.checkNotNullParameter(debugName, "debugName");
        Intrinsics.checkNotNullParameter(containerPresentableName, "containerPresentableName");
        this.c = c;
        this.parent = parent;
        this.debugName = debugName;
        this.containerPresentableName = containerPresentableName;
        this.experimentalSuspendFunctionTypeEncountered = experimentalSuspendFunctionTypeEncountered;
        this.classifierDescriptors = this.c.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1<Integer, ClassifierDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer$classifierDescriptors$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ ClassifierDescriptor invoke(Integer num) {
                return invoke(num.intValue());
            }

            @Nullable
            public final ClassifierDescriptor invoke(int fqNameIndex) {
                return this.this$0.computeClassifierDescriptor(fqNameIndex);
            }
        });
        this.typeAliasDescriptors = this.c.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1<Integer, ClassifierDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer$typeAliasDescriptors$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ ClassifierDescriptor invoke(Integer num) {
                return invoke(num.intValue());
            }

            @Nullable
            public final ClassifierDescriptor invoke(int fqNameIndex) {
                return this.this$0.computeTypeAliasDescriptor(fqNameIndex);
            }
        });
        TypeDeserializer typeDeserializer = this;
        if (typeParameterProtos.isEmpty()) {
            linkedHashMapEmptyMap = MapsKt.emptyMap();
        } else {
            LinkedHashMap result = new LinkedHashMap();
            int i = 0;
            for (ProtoBuf.TypeParameter proto : typeParameterProtos) {
                int index = i;
                i++;
                result.put(Integer.valueOf(proto.getId()), new DeserializedTypeParameterDescriptor(this.c, proto, index));
            }
            typeDeserializer = typeDeserializer;
            linkedHashMapEmptyMap = result;
        }
        typeDeserializer.typeParameterDescriptors = linkedHashMapEmptyMap;
    }

    public /* synthetic */ TypeDeserializer(DeserializationContext deserializationContext, TypeDeserializer typeDeserializer, List list, String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(deserializationContext, typeDeserializer, list, str, str2, (i & 32) != 0 ? false : z);
    }

    public final boolean getExperimentalSuspendFunctionTypeEncountered() {
        return this.experimentalSuspendFunctionTypeEncountered;
    }

    @NotNull
    public final List<TypeParameterDescriptor> getOwnTypeParameters() {
        return CollectionsKt.toList(this.typeParameterDescriptors.values());
    }

    @NotNull
    public final KotlinType type(@NotNull ProtoBuf.Type proto) {
        Intrinsics.checkNotNullParameter(proto, "proto");
        if (proto.hasFlexibleTypeCapabilitiesId()) {
            String id = this.c.getNameResolver().getString(proto.getFlexibleTypeCapabilitiesId());
            SimpleType lowerBound = simpleType$default(this, proto, false, 2, null);
            ProtoBuf.Type typeFlexibleUpperBound = ProtoTypeTableUtilKt.flexibleUpperBound(proto, this.c.getTypeTable());
            Intrinsics.checkNotNull(typeFlexibleUpperBound);
            SimpleType upperBound = simpleType$default(this, typeFlexibleUpperBound, false, 2, null);
            return this.c.getComponents().getFlexibleTypeDeserializer().create(proto, id, lowerBound, upperBound);
        }
        return simpleType(proto, true);
    }

    public static /* synthetic */ SimpleType simpleType$default(TypeDeserializer typeDeserializer, ProtoBuf.Type type, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return typeDeserializer.simpleType(type, z);
    }

    @NotNull
    public final SimpleType simpleType(@NotNull final ProtoBuf.Type proto, boolean expandTypeAliases) {
        DefinitelyNotNullType definitelyNotNullTypeCreateSuspendFunctionType;
        SimpleType simpleType;
        Intrinsics.checkNotNullParameter(proto, "proto");
        SimpleType localClassifierType = proto.hasClassName() ? computeLocalClassifierReplacementType(proto.getClassName()) : proto.hasTypeAliasName() ? computeLocalClassifierReplacementType(proto.getTypeAliasName()) : null;
        if (localClassifierType != null) {
            return localClassifierType;
        }
        TypeConstructor constructor = typeConstructor(proto);
        if (ErrorUtils.isError(constructor.mo3831getDeclarationDescriptor())) {
            SimpleType simpleTypeCreateErrorTypeWithCustomConstructor = ErrorUtils.createErrorTypeWithCustomConstructor(constructor.toString(), constructor);
            Intrinsics.checkNotNullExpressionValue(simpleTypeCreateErrorTypeWithCustomConstructor, "createErrorTypeWithCustomConstructor(constructor.toString(), constructor)");
            return simpleTypeCreateErrorTypeWithCustomConstructor;
        }
        DeserializedAnnotations annotations = new DeserializedAnnotations(this.c.getStorageManager(), new Function0<List<? extends AnnotationDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer$simpleType$annotations$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final List<? extends AnnotationDescriptor> invoke() {
                return this.this$0.c.getComponents().getAnnotationAndConstantLoader().loadTypeAnnotations(proto, this.this$0.c.getNameResolver());
            }
        });
        Iterable $this$mapIndexed$iv = simpleType$collectAllArguments(proto, this);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$mapIndexed$iv, 10));
        int index$iv$iv = 0;
        for (Object item$iv$iv : $this$mapIndexed$iv) {
            int index = index$iv$iv;
            index$iv$iv++;
            if (index < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ProtoBuf.Type.Argument argumentProto = (ProtoBuf.Type.Argument) item$iv$iv;
            List<TypeParameterDescriptor> parameters = constructor.getParameters();
            Intrinsics.checkNotNullExpressionValue(parameters, "constructor.parameters");
            destination$iv$iv.add(typeArgument((TypeParameterDescriptor) CollectionsKt.getOrNull(parameters, index), argumentProto));
        }
        List arguments = CollectionsKt.toList((List) destination$iv$iv);
        ClassifierDescriptor declarationDescriptor = constructor.mo3831getDeclarationDescriptor();
        if (expandTypeAliases && (declarationDescriptor instanceof TypeAliasDescriptor)) {
            KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
            SimpleType expandedType = KotlinTypeFactory.computeExpandedType((TypeAliasDescriptor) declarationDescriptor, arguments);
            definitelyNotNullTypeCreateSuspendFunctionType = expandedType.makeNullableAsSpecified(KotlinTypeKt.isNullable(expandedType) || proto.getNullable()).replaceAnnotations(Annotations.Companion.create(CollectionsKt.plus((Iterable) annotations, (Iterable) expandedType.getAnnotations())));
        } else {
            Boolean bool = Flags.SUSPEND_TYPE.get(proto.getFlags());
            Intrinsics.checkNotNullExpressionValue(bool, "SUSPEND_TYPE.get(proto.flags)");
            if (bool.booleanValue()) {
                definitelyNotNullTypeCreateSuspendFunctionType = createSuspendFunctionType(annotations, constructor, arguments, proto.getNullable());
            } else {
                KotlinTypeFactory kotlinTypeFactory2 = KotlinTypeFactory.INSTANCE;
                SimpleType it = KotlinTypeFactory.simpleType$default(annotations, constructor, arguments, proto.getNullable(), null, 16, null);
                Boolean bool2 = Flags.DEFINITELY_NOT_NULL_TYPE.get(proto.getFlags());
                Intrinsics.checkNotNullExpressionValue(bool2, "DEFINITELY_NOT_NULL_TYPE.get(proto.flags)");
                if (bool2.booleanValue()) {
                    DefinitelyNotNullType definitelyNotNullTypeMakeDefinitelyNotNull$default = DefinitelyNotNullType.Companion.makeDefinitelyNotNull$default(DefinitelyNotNullType.Companion, it, false, 2, null);
                    if (definitelyNotNullTypeMakeDefinitelyNotNull$default == null) {
                        throw new IllegalStateException(("null DefinitelyNotNullType for '" + it + '\'').toString());
                    }
                    definitelyNotNullTypeCreateSuspendFunctionType = definitelyNotNullTypeMakeDefinitelyNotNull$default;
                } else {
                    definitelyNotNullTypeCreateSuspendFunctionType = it;
                }
            }
        }
        SimpleType simpleType2 = definitelyNotNullTypeCreateSuspendFunctionType;
        ProtoBuf.Type it2 = ProtoTypeTableUtilKt.abbreviatedType(proto, this.c.getTypeTable());
        if (it2 == null) {
            simpleType = simpleType2;
        } else {
            SimpleType simpleTypeWithAbbreviation = SpecialTypesKt.withAbbreviation(simpleType2, simpleType(it2, false));
            simpleType = simpleTypeWithAbbreviation == null ? simpleType2 : simpleTypeWithAbbreviation;
        }
        SimpleType computedType = simpleType;
        if (!proto.hasClassName()) {
            return computedType;
        }
        ClassId classId = NameResolverUtilKt.getClassId(this.c.getNameResolver(), proto.getClassName());
        return this.c.getComponents().getPlatformDependentTypeTransformer().transformPlatformType(classId, computedType);
    }

    private static final List<ProtoBuf.Type.Argument> simpleType$collectAllArguments(ProtoBuf.Type $this$simpleType_u24collectAllArguments, TypeDeserializer this$0) {
        List<ProtoBuf.Type.Argument> argumentList = $this$simpleType_u24collectAllArguments.getArgumentList();
        Intrinsics.checkNotNullExpressionValue(argumentList, "argumentList");
        List<ProtoBuf.Type.Argument> list = argumentList;
        ProtoBuf.Type typeOuterType = ProtoTypeTableUtilKt.outerType($this$simpleType_u24collectAllArguments, this$0.c.getTypeTable());
        List<ProtoBuf.Type.Argument> listSimpleType$collectAllArguments = typeOuterType == null ? null : simpleType$collectAllArguments(typeOuterType, this$0);
        if (listSimpleType$collectAllArguments == null) {
            listSimpleType$collectAllArguments = CollectionsKt.emptyList();
        }
        return CollectionsKt.plus((Collection) list, (Iterable) listSimpleType$collectAllArguments);
    }

    private static final ClassDescriptor typeConstructor$notFoundClass(final TypeDeserializer this$0, ProtoBuf.Type $proto, int classIdIndex) {
        ClassId classId = NameResolverUtilKt.getClassId(this$0.c.getNameResolver(), classIdIndex);
        List typeParametersCount = SequencesKt.toMutableList(SequencesKt.map(SequencesKt.generateSequence($proto, new Function1<ProtoBuf.Type, ProtoBuf.Type>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer$typeConstructor$notFoundClass$typeParametersCount$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @Nullable
            public final ProtoBuf.Type invoke(@NotNull ProtoBuf.Type it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return ProtoTypeTableUtilKt.outerType(it, this.this$0.c.getTypeTable());
            }
        }), new Function1<ProtoBuf.Type, Integer>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer$typeConstructor$notFoundClass$typeParametersCount$2
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final int invoke2(@NotNull ProtoBuf.Type it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getArgumentCount();
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(ProtoBuf.Type type) {
                return Integer.valueOf(invoke2(type));
            }
        }));
        int classNestingLevel = SequencesKt.count(SequencesKt.generateSequence(classId, TypeDeserializer$typeConstructor$notFoundClass$classNestingLevel$1.INSTANCE));
        while (typeParametersCount.size() < classNestingLevel) {
            typeParametersCount.add(0);
        }
        return this$0.c.getComponents().getNotFoundClasses().getClass(classId, typeParametersCount);
    }

    private final TypeConstructor typeConstructor(ProtoBuf.Type proto) {
        Object obj;
        if (proto.hasClassName()) {
            ClassifierDescriptor classifierDescriptorInvoke = this.classifierDescriptors.invoke(Integer.valueOf(proto.getClassName()));
            TypeConstructor typeConstructor = (classifierDescriptorInvoke == null ? typeConstructor$notFoundClass(this, proto, proto.getClassName()) : classifierDescriptorInvoke).getTypeConstructor();
            Intrinsics.checkNotNullExpressionValue(typeConstructor, "classifierDescriptors(proto.className) ?: notFoundClass(proto.className)).typeConstructor");
            return typeConstructor;
        }
        if (proto.hasTypeParameter()) {
            TypeConstructor typeConstructorTypeParameterTypeConstructor = typeParameterTypeConstructor(proto.getTypeParameter());
            if (typeConstructorTypeParameterTypeConstructor != null) {
                return typeConstructorTypeParameterTypeConstructor;
            }
            TypeConstructor typeConstructorCreateErrorTypeConstructor = ErrorUtils.createErrorTypeConstructor("Unknown type parameter " + proto.getTypeParameter() + ". Please try recompiling module containing \"" + this.containerPresentableName + '\"');
            Intrinsics.checkNotNullExpressionValue(typeConstructorCreateErrorTypeConstructor, "createErrorTypeConstructor(\n                        \"Unknown type parameter ${proto.typeParameter}. Please try recompiling module containing \\\"$containerPresentableName\\\"\"\n                    )");
            return typeConstructorCreateErrorTypeConstructor;
        }
        if (proto.hasTypeParameterName()) {
            DeclarationDescriptor container = this.c.getContainingDeclaration();
            String name = this.c.getNameResolver().getString(proto.getTypeParameterName());
            Iterator<T> it = getOwnTypeParameters().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                Object next = it.next();
                TypeParameterDescriptor it2 = (TypeParameterDescriptor) next;
                if (Intrinsics.areEqual(it2.getName().asString(), name)) {
                    obj = next;
                    break;
                }
            }
            TypeParameterDescriptor parameter = (TypeParameterDescriptor) obj;
            TypeConstructor typeConstructor2 = parameter == null ? null : parameter.getTypeConstructor();
            TypeConstructor typeConstructorCreateErrorTypeConstructor2 = typeConstructor2 == null ? ErrorUtils.createErrorTypeConstructor("Deserialized type parameter " + name + " in " + container) : typeConstructor2;
            Intrinsics.checkNotNullExpressionValue(typeConstructorCreateErrorTypeConstructor2, "{\n                val container = c.containingDeclaration\n                val name = c.nameResolver.getString(proto.typeParameterName)\n                val parameter = ownTypeParameters.find { it.name.asString() == name }\n                parameter?.typeConstructor ?: ErrorUtils.createErrorTypeConstructor(\"Deserialized type parameter $name in $container\")\n            }");
            return typeConstructorCreateErrorTypeConstructor2;
        }
        if (!proto.hasTypeAliasName()) {
            TypeConstructor typeConstructorCreateErrorTypeConstructor3 = ErrorUtils.createErrorTypeConstructor("Unknown type");
            Intrinsics.checkNotNullExpressionValue(typeConstructorCreateErrorTypeConstructor3, "createErrorTypeConstructor(\"Unknown type\")");
            return typeConstructorCreateErrorTypeConstructor3;
        }
        ClassifierDescriptor classifierDescriptorInvoke2 = this.typeAliasDescriptors.invoke(Integer.valueOf(proto.getTypeAliasName()));
        TypeConstructor typeConstructor3 = (classifierDescriptorInvoke2 == null ? typeConstructor$notFoundClass(this, proto, proto.getTypeAliasName()) : classifierDescriptorInvoke2).getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor3, "typeAliasDescriptors(proto.typeAliasName) ?: notFoundClass(proto.typeAliasName)).typeConstructor");
        return typeConstructor3;
    }

    private final SimpleType createSuspendFunctionType(Annotations annotations, TypeConstructor functionTypeConstructor, List<? extends TypeProjection> list, boolean nullable) {
        SimpleType simpleTypeSimpleType$default;
        switch (functionTypeConstructor.getParameters().size() - list.size()) {
            case 0:
                simpleTypeSimpleType$default = createSuspendFunctionTypeForBasicCase(annotations, functionTypeConstructor, list, nullable);
                break;
            case 1:
                int arity = list.size() - 1;
                if (arity >= 0) {
                    KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
                    TypeConstructor typeConstructor = functionTypeConstructor.getBuiltIns().getSuspendFunction(arity).getTypeConstructor();
                    Intrinsics.checkNotNullExpressionValue(typeConstructor, "functionTypeConstructor.builtIns.getSuspendFunction(arity).typeConstructor");
                    simpleTypeSimpleType$default = KotlinTypeFactory.simpleType$default(annotations, typeConstructor, list, nullable, null, 16, null);
                    break;
                } else {
                    simpleTypeSimpleType$default = (SimpleType) null;
                    break;
                }
            default:
                simpleTypeSimpleType$default = null;
                break;
        }
        SimpleType result = simpleTypeSimpleType$default;
        if (result == null) {
            SimpleType simpleTypeCreateErrorTypeWithArguments = ErrorUtils.createErrorTypeWithArguments(Intrinsics.stringPlus("Bad suspend function in metadata with constructor: ", functionTypeConstructor), list);
            Intrinsics.checkNotNullExpressionValue(simpleTypeCreateErrorTypeWithArguments, "createErrorTypeWithArguments(\n            \"Bad suspend function in metadata with constructor: $functionTypeConstructor\",\n            arguments\n        )");
            return simpleTypeCreateErrorTypeWithArguments;
        }
        return result;
    }

    private final SimpleType createSuspendFunctionTypeForBasicCase(Annotations annotations, TypeConstructor functionTypeConstructor, List<? extends TypeProjection> list, boolean nullable) {
        KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
        SimpleType functionType = KotlinTypeFactory.simpleType$default(annotations, functionTypeConstructor, list, nullable, null, 16, null);
        if (FunctionTypesKt.isFunctionType(functionType)) {
            return transformRuntimeFunctionTypeToSuspendFunction(functionType);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00f5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final kotlin.reflect.jvm.internal.impl.types.SimpleType transformRuntimeFunctionTypeToSuspendFunction(kotlin.reflect.jvm.internal.impl.types.KotlinType r5) {
        /*
            Method dump skipped, instructions count: 261
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer.transformRuntimeFunctionTypeToSuspendFunction(kotlin.reflect.jvm.internal.impl.types.KotlinType):kotlin.reflect.jvm.internal.impl.types.SimpleType");
    }

    private final SimpleType createSimpleSuspendFunctionType(KotlinType funType, KotlinType suspendReturnType) {
        KotlinBuiltIns builtIns = TypeUtilsKt.getBuiltIns(funType);
        Annotations annotations = funType.getAnnotations();
        KotlinType receiverTypeFromFunctionType = FunctionTypesKt.getReceiverTypeFromFunctionType(funType);
        Iterable $this$map$iv = CollectionsKt.dropLast(FunctionTypesKt.getValueParameterTypesFromFunctionType(funType), 1);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            TypeProjection p0 = (TypeProjection) item$iv$iv;
            destination$iv$iv.add(p0.getType());
        }
        return FunctionTypesKt.createFunctionType(builtIns, annotations, receiverTypeFromFunctionType, (List) destination$iv$iv, null, suspendReturnType, true).makeNullableAsSpecified(funType.isMarkedNullable());
    }

    private final TypeConstructor typeParameterTypeConstructor(int typeParameterId) {
        TypeParameterDescriptor typeParameterDescriptor = this.typeParameterDescriptors.get(Integer.valueOf(typeParameterId));
        TypeConstructor typeConstructor = typeParameterDescriptor == null ? null : typeParameterDescriptor.getTypeConstructor();
        if (typeConstructor != null) {
            return typeConstructor;
        }
        TypeDeserializer typeDeserializer = this.parent;
        if (typeDeserializer == null) {
            return null;
        }
        return typeDeserializer.typeParameterTypeConstructor(typeParameterId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassifierDescriptor computeClassifierDescriptor(int fqNameIndex) {
        ClassId id = NameResolverUtilKt.getClassId(this.c.getNameResolver(), fqNameIndex);
        if (id.isLocal()) {
            return this.c.getComponents().deserializeClass(id);
        }
        return FindClassInModuleKt.findClassifierAcrossModuleDependencies(this.c.getComponents().getModuleDescriptor(), id);
    }

    private final SimpleType computeLocalClassifierReplacementType(int className) {
        if (NameResolverUtilKt.getClassId(this.c.getNameResolver(), className).isLocal()) {
            return this.c.getComponents().getLocalClassifierTypeSettings().getReplacementTypeForLocalClassifiers();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassifierDescriptor computeTypeAliasDescriptor(int fqNameIndex) {
        ClassId id = NameResolverUtilKt.getClassId(this.c.getNameResolver(), fqNameIndex);
        if (id.isLocal()) {
            return null;
        }
        return FindClassInModuleKt.findTypeAliasAcrossModuleDependencies(this.c.getComponents().getModuleDescriptor(), id);
    }

    private final TypeProjection typeArgument(TypeParameterDescriptor parameter, ProtoBuf.Type.Argument typeArgumentProto) {
        if (typeArgumentProto.getProjection() == ProtoBuf.Type.Argument.Projection.STAR) {
            if (parameter == null) {
                return new StarProjectionForAbsentTypeParameter(this.c.getComponents().getModuleDescriptor().getBuiltIns());
            }
            return new StarProjectionImpl(parameter);
        }
        ProtoEnumFlags protoEnumFlags = ProtoEnumFlags.INSTANCE;
        ProtoBuf.Type.Argument.Projection projection = typeArgumentProto.getProjection();
        Intrinsics.checkNotNullExpressionValue(projection, "typeArgumentProto.projection");
        Variance projection2 = protoEnumFlags.variance(projection);
        ProtoBuf.Type type = ProtoTypeTableUtilKt.type(typeArgumentProto, this.c.getTypeTable());
        return type == null ? new TypeProjectionImpl(ErrorUtils.createErrorType("No type recorded")) : new TypeProjectionImpl(projection2, type(type));
    }

    @NotNull
    public String toString() {
        return Intrinsics.stringPlus(this.debugName, this.parent == null ? "" : Intrinsics.stringPlus(". Child of ", this.parent.debugName));
    }
}
