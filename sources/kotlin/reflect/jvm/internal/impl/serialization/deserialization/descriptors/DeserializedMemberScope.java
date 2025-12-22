package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.Parser;
import kotlin.reflect.jvm.internal.impl.resolve.MemberComparator;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.NullableLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DeserializedMemberScope.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/serialization/deserialization/descriptors/DeserializedMemberScope.class */
public abstract class DeserializedMemberScope extends MemberScopeImpl {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(DeserializedMemberScope.class), "classNames", "getClassNames$deserialization()Ljava/util/Set;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(DeserializedMemberScope.class), "classifierNamesLazy", "getClassifierNamesLazy()Ljava/util/Set;"))};

    @NotNull
    private final DeserializationContext c;

    @NotNull
    private final Implementation impl;

    @NotNull
    private final NotNullLazyValue classNames$delegate;

    @NotNull
    private final NullableLazyValue classifierNamesLazy$delegate;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DeserializedMemberScope.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/serialization/deserialization/descriptors/DeserializedMemberScope$Implementation.class */
    interface Implementation {
        @NotNull
        Set<Name> getFunctionNames();

        @NotNull
        Set<Name> getVariableNames();

        @NotNull
        Set<Name> getTypeAliasNames();

        @NotNull
        Collection<SimpleFunctionDescriptor> getContributedFunctions(@NotNull Name name, @NotNull LookupLocation lookupLocation);

        @NotNull
        Collection<PropertyDescriptor> getContributedVariables(@NotNull Name name, @NotNull LookupLocation lookupLocation);

        @Nullable
        TypeAliasDescriptor getTypeAliasByName(@NotNull Name name);

        void addFunctionsAndPropertiesTo(@NotNull Collection<DeclarationDescriptor> collection, @NotNull DescriptorKindFilter descriptorKindFilter, @NotNull Function1<? super Name, Boolean> function1, @NotNull LookupLocation lookupLocation);
    }

    @NotNull
    protected abstract ClassId createClassId(@NotNull Name name);

    @NotNull
    protected abstract Set<Name> getNonDeclaredFunctionNames();

    @NotNull
    protected abstract Set<Name> getNonDeclaredVariableNames();

    @Nullable
    protected abstract Set<Name> getNonDeclaredClassifierNames();

    protected abstract void addEnumEntryDescriptors(@NotNull Collection<DeclarationDescriptor> collection, @NotNull Function1<? super Name, Boolean> function1);

    @NotNull
    protected final DeserializationContext getC() {
        return this.c;
    }

    protected DeserializedMemberScope(@NotNull DeserializationContext c, @NotNull List<ProtoBuf.Function> functionList, @NotNull List<ProtoBuf.Property> propertyList, @NotNull List<ProtoBuf.TypeAlias> typeAliasList, @NotNull final Function0<? extends Collection<Name>> classNames) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(functionList, "functionList");
        Intrinsics.checkNotNullParameter(propertyList, "propertyList");
        Intrinsics.checkNotNullParameter(typeAliasList, "typeAliasList");
        Intrinsics.checkNotNullParameter(classNames, "classNames");
        this.c = c;
        this.impl = createImplementation(functionList, propertyList, typeAliasList);
        this.classNames$delegate = this.c.getStorageManager().createLazyValue(new Function0<Set<? extends Name>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$classNames$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final Set<? extends Name> invoke() {
                return CollectionsKt.toSet(classNames.invoke());
            }
        });
        this.classifierNamesLazy$delegate = this.c.getStorageManager().createNullableLazyValue(new Function0<Set<? extends Name>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$classifierNamesLazy$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @Nullable
            public final Set<? extends Name> invoke() {
                Set nonDeclaredNames = this.this$0.getNonDeclaredClassifierNames();
                if (nonDeclaredNames == null) {
                    return null;
                }
                return SetsKt.plus(SetsKt.plus((Set) this.this$0.getClassNames$deserialization(), (Iterable) this.this$0.impl.getTypeAliasNames()), (Iterable) nonDeclaredNames);
            }
        });
    }

    @NotNull
    public final Set<Name> getClassNames$deserialization() {
        return (Set) StorageKt.getValue(this.classNames$delegate, this, (KProperty<?>) $$delegatedProperties[0]);
    }

    private final Set<Name> getClassifierNamesLazy() {
        return (Set) StorageKt.getValue(this.classifierNamesLazy$delegate, this, (KProperty<?>) $$delegatedProperties[1]);
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    public Set<Name> getFunctionNames() {
        return this.impl.getFunctionNames();
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    public Set<Name> getVariableNames() {
        return this.impl.getVariableNames();
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @Nullable
    public Set<Name> getClassifierNames() {
        return getClassifierNamesLazy();
    }

    protected boolean isDeclaredFunctionAvailable(@NotNull SimpleFunctionDescriptor function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return true;
    }

    protected void computeNonDeclaredFunctions(@NotNull Name name, @NotNull List<SimpleFunctionDescriptor> functions) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(functions, "functions");
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @NotNull
    public Collection<SimpleFunctionDescriptor> getContributedFunctions(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        return this.impl.getContributedFunctions(name, location);
    }

    protected void computeNonDeclaredProperties(@NotNull Name name, @NotNull List<PropertyDescriptor> descriptors) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(descriptors, "descriptors");
    }

    private final TypeAliasDescriptor getTypeAliasByName(Name name) {
        return this.impl.getTypeAliasByName(name);
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    public Collection<PropertyDescriptor> getContributedVariables(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        return this.impl.getContributedVariables(name, location);
    }

    @NotNull
    protected final Collection<DeclarationDescriptor> computeDescriptors(@NotNull DescriptorKindFilter kindFilter, @NotNull Function1<? super Name, Boolean> nameFilter, @NotNull LookupLocation location) {
        Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
        Intrinsics.checkNotNullParameter(nameFilter, "nameFilter");
        Intrinsics.checkNotNullParameter(location, "location");
        ArrayList result = new ArrayList(0);
        if (kindFilter.acceptsKinds(DescriptorKindFilter.Companion.getSINGLETON_CLASSIFIERS_MASK())) {
            addEnumEntryDescriptors(result, nameFilter);
        }
        this.impl.addFunctionsAndPropertiesTo(result, kindFilter, nameFilter, location);
        if (kindFilter.acceptsKinds(DescriptorKindFilter.Companion.getCLASSIFIERS_MASK())) {
            for (Name className : getClassNames$deserialization()) {
                if (nameFilter.invoke(className).booleanValue()) {
                    kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(result, deserializeClass(className));
                }
            }
        }
        if (kindFilter.acceptsKinds(DescriptorKindFilter.Companion.getTYPE_ALIASES_MASK())) {
            for (Name typeAliasName : this.impl.getTypeAliasNames()) {
                if (nameFilter.invoke(typeAliasName).booleanValue()) {
                    kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(result, this.impl.getTypeAliasByName(typeAliasName));
                }
            }
        }
        return kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(result);
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @Nullable
    /* renamed from: getContributedClassifier */
    public ClassifierDescriptor mo3858getContributedClassifier(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        if (hasClass(name)) {
            return deserializeClass(name);
        }
        if (this.impl.getTypeAliasNames().contains(name)) {
            return getTypeAliasByName(name);
        }
        return null;
    }

    private final ClassDescriptor deserializeClass(Name name) {
        return this.c.getComponents().deserializeClass(createClassId(name));
    }

    protected boolean hasClass(@NotNull Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return getClassNames$deserialization().contains(name);
    }

    private final Implementation createImplementation(List<ProtoBuf.Function> list, List<ProtoBuf.Property> list2, List<ProtoBuf.TypeAlias> list3) {
        if (this.c.getComponents().getConfiguration().getPreserveDeclarationsOrdering()) {
            return new NoReorderImplementation(this, list, list2, list3);
        }
        return new OptimizedImplementation(this, list, list2, list3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DeserializedMemberScope.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/serialization/deserialization/descriptors/DeserializedMemberScope$OptimizedImplementation.class */
    final class OptimizedImplementation implements Implementation {
        static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(OptimizedImplementation.class), "functionNames", "getFunctionNames()Ljava/util/Set;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(OptimizedImplementation.class), "variableNames", "getVariableNames()Ljava/util/Set;"))};

        @NotNull
        private final Map<Name, byte[]> functionProtosBytes;

        @NotNull
        private final Map<Name, byte[]> propertyProtosBytes;

        @NotNull
        private final Map<Name, byte[]> typeAliasBytes;

        @NotNull
        private final MemoizedFunctionToNotNull<Name, Collection<SimpleFunctionDescriptor>> functions;

        @NotNull
        private final MemoizedFunctionToNotNull<Name, Collection<PropertyDescriptor>> properties;

        @NotNull
        private final MemoizedFunctionToNullable<Name, TypeAliasDescriptor> typeAliasByName;

        @NotNull
        private final NotNullLazyValue functionNames$delegate;

        @NotNull
        private final NotNullLazyValue variableNames$delegate;
        final /* synthetic */ DeserializedMemberScope this$0;

        public OptimizedImplementation(@NotNull DeserializedMemberScope this$0, @NotNull List<ProtoBuf.Function> functionList, @NotNull List<ProtoBuf.Property> propertyList, List<ProtoBuf.TypeAlias> typeAliasList) throws IOException {
            Map<Name, byte[]> mapEmptyMap;
            Object obj;
            Object obj2;
            Object obj3;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(functionList, "functionList");
            Intrinsics.checkNotNullParameter(propertyList, "propertyList");
            Intrinsics.checkNotNullParameter(typeAliasList, "typeAliasList");
            this.this$0 = this$0;
            List<ProtoBuf.Function> $this$groupByName$iv = functionList;
            List<ProtoBuf.Function> $this$groupBy$iv$iv = $this$groupByName$iv;
            DeserializedMemberScope deserializedMemberScope = this.this$0;
            Map destination$iv$iv$iv = new LinkedHashMap();
            for (Object element$iv$iv$iv : $this$groupBy$iv$iv) {
                MessageLite it$iv = (MessageLite) element$iv$iv$iv;
                ProtoBuf.Function it = (ProtoBuf.Function) it$iv;
                Name name = NameResolverUtilKt.getName(deserializedMemberScope.c.getNameResolver(), it.getName());
                Object value$iv$iv$iv$iv = destination$iv$iv$iv.get(name);
                if (value$iv$iv$iv$iv == null) {
                    ArrayList arrayList = new ArrayList();
                    destination$iv$iv$iv.put(name, arrayList);
                    obj3 = arrayList;
                } else {
                    obj3 = value$iv$iv$iv$iv;
                }
                List list$iv$iv$iv = (List) obj3;
                list$iv$iv$iv.add(element$iv$iv$iv);
            }
            this.functionProtosBytes = packToByteArray(destination$iv$iv$iv);
            List<ProtoBuf.Property> $this$groupByName$iv2 = propertyList;
            List<ProtoBuf.Property> $this$groupBy$iv$iv2 = $this$groupByName$iv2;
            DeserializedMemberScope deserializedMemberScope2 = this.this$0;
            Map destination$iv$iv$iv2 = new LinkedHashMap();
            for (Object element$iv$iv$iv2 : $this$groupBy$iv$iv2) {
                MessageLite it$iv2 = (MessageLite) element$iv$iv$iv2;
                ProtoBuf.Property it2 = (ProtoBuf.Property) it$iv2;
                Name name2 = NameResolverUtilKt.getName(deserializedMemberScope2.c.getNameResolver(), it2.getName());
                Object value$iv$iv$iv$iv2 = destination$iv$iv$iv2.get(name2);
                if (value$iv$iv$iv$iv2 == null) {
                    ArrayList arrayList2 = new ArrayList();
                    destination$iv$iv$iv2.put(name2, arrayList2);
                    obj2 = arrayList2;
                } else {
                    obj2 = value$iv$iv$iv$iv2;
                }
                List list$iv$iv$iv2 = (List) obj2;
                list$iv$iv$iv2.add(element$iv$iv$iv2);
            }
            this.propertyProtosBytes = packToByteArray(destination$iv$iv$iv2);
            OptimizedImplementation optimizedImplementation = this;
            if (!this.this$0.getC().getComponents().getConfiguration().getTypeAliasesAllowed()) {
                mapEmptyMap = MapsKt.emptyMap();
            } else {
                List<ProtoBuf.TypeAlias> $this$groupByName$iv3 = typeAliasList;
                List<ProtoBuf.TypeAlias> $this$groupBy$iv$iv3 = $this$groupByName$iv3;
                DeserializedMemberScope deserializedMemberScope3 = this.this$0;
                Map destination$iv$iv$iv3 = new LinkedHashMap();
                for (Object element$iv$iv$iv3 : $this$groupBy$iv$iv3) {
                    MessageLite it$iv3 = (MessageLite) element$iv$iv$iv3;
                    ProtoBuf.TypeAlias it3 = (ProtoBuf.TypeAlias) it$iv3;
                    Name name3 = NameResolverUtilKt.getName(deserializedMemberScope3.c.getNameResolver(), it3.getName());
                    Object value$iv$iv$iv$iv3 = destination$iv$iv$iv3.get(name3);
                    if (value$iv$iv$iv$iv3 == null) {
                        ArrayList arrayList3 = new ArrayList();
                        destination$iv$iv$iv3.put(name3, arrayList3);
                        obj = arrayList3;
                    } else {
                        obj = value$iv$iv$iv$iv3;
                    }
                    List list$iv$iv$iv3 = (List) obj;
                    list$iv$iv$iv3.add(element$iv$iv$iv3);
                }
                optimizedImplementation = optimizedImplementation;
                mapEmptyMap = packToByteArray(destination$iv$iv$iv3);
            }
            optimizedImplementation.typeAliasBytes = mapEmptyMap;
            this.functions = this.this$0.getC().getStorageManager().createMemoizedFunction(new Function1<Name, Collection<? extends SimpleFunctionDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$OptimizedImplementation$functions$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                @NotNull
                public final Collection<SimpleFunctionDescriptor> invoke(@NotNull Name it4) {
                    Intrinsics.checkNotNullParameter(it4, "it");
                    return this.this$0.computeFunctions(it4);
                }
            });
            this.properties = this.this$0.getC().getStorageManager().createMemoizedFunction(new Function1<Name, Collection<? extends PropertyDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$OptimizedImplementation$properties$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                @NotNull
                public final Collection<PropertyDescriptor> invoke(@NotNull Name it4) {
                    Intrinsics.checkNotNullParameter(it4, "it");
                    return this.this$0.computeProperties(it4);
                }
            });
            this.typeAliasByName = this.this$0.getC().getStorageManager().createMemoizedFunctionWithNullableValues(new Function1<Name, TypeAliasDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$OptimizedImplementation$typeAliasByName$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                @Nullable
                public final TypeAliasDescriptor invoke(@NotNull Name it4) {
                    Intrinsics.checkNotNullParameter(it4, "it");
                    return this.this$0.createTypeAlias(it4);
                }
            });
            StorageManager storageManager = this.this$0.getC().getStorageManager();
            final DeserializedMemberScope deserializedMemberScope4 = this.this$0;
            this.functionNames$delegate = storageManager.createLazyValue(new Function0<Set<? extends Name>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$OptimizedImplementation$functionNames$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                @NotNull
                public final Set<? extends Name> invoke() {
                    return SetsKt.plus(this.this$0.functionProtosBytes.keySet(), (Iterable) deserializedMemberScope4.getNonDeclaredFunctionNames());
                }
            });
            StorageManager storageManager2 = this.this$0.getC().getStorageManager();
            final DeserializedMemberScope deserializedMemberScope5 = this.this$0;
            this.variableNames$delegate = storageManager2.createLazyValue(new Function0<Set<? extends Name>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$OptimizedImplementation$variableNames$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                @NotNull
                public final Set<? extends Name> invoke() {
                    return SetsKt.plus(this.this$0.propertyProtosBytes.keySet(), (Iterable) deserializedMemberScope5.getNonDeclaredVariableNames());
                }
            });
        }

        private final Map<Name, byte[]> packToByteArray(Map<Name, ? extends Collection<? extends AbstractMessageLite>> map) throws IOException {
            Map destination$iv$iv = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
            Iterable $this$associateByTo$iv$iv$iv = map.entrySet();
            for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
                Map.Entry it$iv$iv = (Map.Entry) element$iv$iv$iv;
                Object key = it$iv$iv.getKey();
                Map.Entry entry = (Map.Entry) element$iv$iv$iv;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                Iterable $this$map$iv = (Iterable) entry.getValue();
                Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    AbstractMessageLite proto = (AbstractMessageLite) item$iv$iv;
                    proto.writeDelimitedTo(byteArrayOutputStream);
                    destination$iv$iv2.add(Unit.INSTANCE);
                }
                destination$iv$iv.put(key, byteArrayOutputStream.toByteArray());
            }
            return destination$iv$iv;
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope.Implementation
        @NotNull
        public Set<Name> getFunctionNames() {
            return (Set) StorageKt.getValue(this.functionNames$delegate, this, (KProperty<?>) $$delegatedProperties[0]);
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope.Implementation
        @NotNull
        public Set<Name> getVariableNames() {
            return (Set) StorageKt.getValue(this.variableNames$delegate, this, (KProperty<?>) $$delegatedProperties[1]);
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope.Implementation
        @NotNull
        public Set<Name> getTypeAliasNames() {
            return this.typeAliasBytes.keySet();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Collection<SimpleFunctionDescriptor> computeFunctions(Name name) {
            Collection list;
            Collection collectionEmptyList;
            Map bytesByName$iv = this.functionProtosBytes;
            Parser PARSER = ProtoBuf.Function.PARSER;
            Intrinsics.checkNotNullExpressionValue(PARSER, "PARSER");
            DeserializedMemberScope deserializedMemberScope = this.this$0;
            DeserializedMemberScope deserializedMemberScope2 = this.this$0;
            byte[] it$iv = bytesByName$iv.get(name);
            if (it$iv == null) {
                list = null;
            } else {
                DeserializedMemberScope deserializedMemberScope3 = this.this$0;
                ByteArrayInputStream inputStream$iv = new ByteArrayInputStream(it$iv);
                list = SequencesKt.toList(SequencesKt.generateSequence(new DeserializedMemberScope$OptimizedImplementation$computeDescriptors$1$1(PARSER, inputStream$iv, deserializedMemberScope3)));
            }
            Collection collection = list;
            if (collection != null) {
                collectionEmptyList = collection;
            } else {
                collectionEmptyList = CollectionsKt.emptyList();
            }
            Collection protos$iv$iv = collectionEmptyList;
            Collection $this$mapNotNullTo$iv$iv$iv = protos$iv$iv;
            Collection destination$iv$iv$iv = new ArrayList(protos$iv$iv.size());
            for (Object element$iv$iv$iv$iv : $this$mapNotNullTo$iv$iv$iv) {
                ProtoBuf.Function it = (ProtoBuf.Function) element$iv$iv$iv$iv;
                MemberDeserializer memberDeserializer = deserializedMemberScope.getC().getMemberDeserializer();
                Intrinsics.checkNotNullExpressionValue(it, "it");
                SimpleFunctionDescriptor p0 = memberDeserializer.loadFunction(it);
                SimpleFunctionDescriptor simpleFunctionDescriptor = deserializedMemberScope.isDeclaredFunctionAvailable(p0) ? p0 : null;
                if (simpleFunctionDescriptor != null) {
                    destination$iv$iv$iv.add(simpleFunctionDescriptor);
                }
            }
            ArrayList descriptors$iv$iv = (ArrayList) destination$iv$iv$iv;
            deserializedMemberScope2.computeNonDeclaredFunctions(name, descriptors$iv$iv);
            return kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(descriptors$iv$iv);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Collection<PropertyDescriptor> computeProperties(Name name) {
            Collection list;
            Collection collectionEmptyList;
            Map bytesByName$iv = this.propertyProtosBytes;
            Parser PARSER = ProtoBuf.Property.PARSER;
            Intrinsics.checkNotNullExpressionValue(PARSER, "PARSER");
            DeserializedMemberScope deserializedMemberScope = this.this$0;
            DeserializedMemberScope deserializedMemberScope2 = this.this$0;
            byte[] it$iv = bytesByName$iv.get(name);
            if (it$iv == null) {
                list = null;
            } else {
                DeserializedMemberScope deserializedMemberScope3 = this.this$0;
                ByteArrayInputStream inputStream$iv = new ByteArrayInputStream(it$iv);
                list = SequencesKt.toList(SequencesKt.generateSequence(new DeserializedMemberScope$OptimizedImplementation$computeDescriptors$1$1(PARSER, inputStream$iv, deserializedMemberScope3)));
            }
            Collection collection = list;
            if (collection != null) {
                collectionEmptyList = collection;
            } else {
                collectionEmptyList = CollectionsKt.emptyList();
            }
            Collection protos$iv$iv = collectionEmptyList;
            Collection $this$mapNotNullTo$iv$iv$iv = protos$iv$iv;
            Collection destination$iv$iv$iv = new ArrayList(protos$iv$iv.size());
            for (Object element$iv$iv$iv$iv : $this$mapNotNullTo$iv$iv$iv) {
                ProtoBuf.Property it = (ProtoBuf.Property) element$iv$iv$iv$iv;
                MemberDeserializer memberDeserializer = deserializedMemberScope.getC().getMemberDeserializer();
                Intrinsics.checkNotNullExpressionValue(it, "it");
                PropertyDescriptor propertyDescriptorLoadProperty = memberDeserializer.loadProperty(it);
                if (propertyDescriptorLoadProperty != null) {
                    destination$iv$iv$iv.add(propertyDescriptorLoadProperty);
                }
            }
            ArrayList descriptors$iv$iv = (ArrayList) destination$iv$iv$iv;
            deserializedMemberScope2.computeNonDeclaredProperties(name, descriptors$iv$iv);
            return kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(descriptors$iv$iv);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final TypeAliasDescriptor createTypeAlias(Name name) {
            ProtoBuf.TypeAlias proto;
            byte[] byteArray = this.typeAliasBytes.get(name);
            if (byteArray != null && (proto = ProtoBuf.TypeAlias.parseDelimitedFrom(new ByteArrayInputStream(byteArray), this.this$0.getC().getComponents().getExtensionRegistryLite())) != null) {
                return this.this$0.getC().getMemberDeserializer().loadTypeAlias(proto);
            }
            return null;
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope.Implementation
        @NotNull
        public Collection<SimpleFunctionDescriptor> getContributedFunctions(@NotNull Name name, @NotNull LookupLocation location) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(location, "location");
            return !getFunctionNames().contains(name) ? CollectionsKt.emptyList() : this.functions.invoke(name);
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope.Implementation
        @Nullable
        public TypeAliasDescriptor getTypeAliasByName(@NotNull Name name) {
            Intrinsics.checkNotNullParameter(name, "name");
            return this.typeAliasByName.invoke(name);
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope.Implementation
        @NotNull
        public Collection<PropertyDescriptor> getContributedVariables(@NotNull Name name, @NotNull LookupLocation location) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(location, "location");
            return !getVariableNames().contains(name) ? CollectionsKt.emptyList() : this.properties.invoke(name);
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope.Implementation
        public void addFunctionsAndPropertiesTo(@NotNull Collection<DeclarationDescriptor> result, @NotNull DescriptorKindFilter kindFilter, @NotNull Function1<? super Name, Boolean> nameFilter, @NotNull LookupLocation location) {
            Intrinsics.checkNotNullParameter(result, "result");
            Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
            Intrinsics.checkNotNullParameter(nameFilter, "nameFilter");
            Intrinsics.checkNotNullParameter(location, "location");
            if (kindFilter.acceptsKinds(DescriptorKindFilter.Companion.getVARIABLES_MASK())) {
                Collection names$iv = getVariableNames();
                ArrayList subResult$iv = new ArrayList();
                for (Name name$iv : names$iv) {
                    if (nameFilter.invoke(name$iv).booleanValue()) {
                        subResult$iv.addAll(getContributedVariables(name$iv, location));
                    }
                }
                MemberComparator.NameAndTypeMemberComparator INSTANCE = MemberComparator.NameAndTypeMemberComparator.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(INSTANCE, "INSTANCE");
                CollectionsKt.sortWith(subResult$iv, INSTANCE);
                result.addAll(subResult$iv);
            }
            if (kindFilter.acceptsKinds(DescriptorKindFilter.Companion.getFUNCTIONS_MASK())) {
                Collection names$iv2 = getFunctionNames();
                ArrayList subResult$iv2 = new ArrayList();
                for (Name name$iv2 : names$iv2) {
                    if (nameFilter.invoke(name$iv2).booleanValue()) {
                        subResult$iv2.addAll(getContributedFunctions(name$iv2, location));
                    }
                }
                MemberComparator.NameAndTypeMemberComparator INSTANCE2 = MemberComparator.NameAndTypeMemberComparator.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(INSTANCE2, "INSTANCE");
                CollectionsKt.sortWith(subResult$iv2, INSTANCE2);
                result.addAll(subResult$iv2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DeserializedMemberScope.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/serialization/deserialization/descriptors/DeserializedMemberScope$NoReorderImplementation.class */
    final class NoReorderImplementation implements Implementation {
        static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(NoReorderImplementation.class), "declaredFunctions", "getDeclaredFunctions()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(NoReorderImplementation.class), "declaredProperties", "getDeclaredProperties()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(NoReorderImplementation.class), "allTypeAliases", "getAllTypeAliases()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(NoReorderImplementation.class), "allFunctions", "getAllFunctions()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(NoReorderImplementation.class), "allProperties", "getAllProperties()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(NoReorderImplementation.class), "typeAliasesByName", "getTypeAliasesByName()Ljava/util/Map;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(NoReorderImplementation.class), "functionsByName", "getFunctionsByName()Ljava/util/Map;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(NoReorderImplementation.class), "propertiesByName", "getPropertiesByName()Ljava/util/Map;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(NoReorderImplementation.class), "functionNames", "getFunctionNames()Ljava/util/Set;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(NoReorderImplementation.class), "variableNames", "getVariableNames()Ljava/util/Set;"))};

        @NotNull
        private final List<ProtoBuf.Function> functionList;

        @NotNull
        private final List<ProtoBuf.Property> propertyList;

        @NotNull
        private final List<ProtoBuf.TypeAlias> typeAliasList;

        @NotNull
        private final NotNullLazyValue declaredFunctions$delegate;

        @NotNull
        private final NotNullLazyValue declaredProperties$delegate;

        @NotNull
        private final NotNullLazyValue allTypeAliases$delegate;

        @NotNull
        private final NotNullLazyValue allFunctions$delegate;

        @NotNull
        private final NotNullLazyValue allProperties$delegate;

        @NotNull
        private final NotNullLazyValue typeAliasesByName$delegate;

        @NotNull
        private final NotNullLazyValue functionsByName$delegate;

        @NotNull
        private final NotNullLazyValue propertiesByName$delegate;

        @NotNull
        private final NotNullLazyValue functionNames$delegate;

        @NotNull
        private final NotNullLazyValue variableNames$delegate;
        final /* synthetic */ DeserializedMemberScope this$0;

        public NoReorderImplementation(@NotNull DeserializedMemberScope this$0, @NotNull List<ProtoBuf.Function> functionList, @NotNull List<ProtoBuf.Property> propertyList, List<ProtoBuf.TypeAlias> typeAliasList) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(functionList, "functionList");
            Intrinsics.checkNotNullParameter(propertyList, "propertyList");
            Intrinsics.checkNotNullParameter(typeAliasList, "typeAliasList");
            this.this$0 = this$0;
            this.functionList = functionList;
            this.propertyList = propertyList;
            this.typeAliasList = this.this$0.getC().getComponents().getConfiguration().getTypeAliasesAllowed() ? typeAliasList : CollectionsKt.emptyList();
            this.declaredFunctions$delegate = this.this$0.getC().getStorageManager().createLazyValue(new Function0<List<? extends SimpleFunctionDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$NoReorderImplementation$declaredFunctions$2
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                @NotNull
                public final List<? extends SimpleFunctionDescriptor> invoke() {
                    return this.this$0.computeFunctions();
                }
            });
            this.declaredProperties$delegate = this.this$0.getC().getStorageManager().createLazyValue(new Function0<List<? extends PropertyDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$NoReorderImplementation$declaredProperties$2
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                @NotNull
                public final List<? extends PropertyDescriptor> invoke() {
                    return this.this$0.computeProperties();
                }
            });
            this.allTypeAliases$delegate = this.this$0.getC().getStorageManager().createLazyValue(new Function0<List<? extends TypeAliasDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$NoReorderImplementation$allTypeAliases$2
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                @NotNull
                public final List<? extends TypeAliasDescriptor> invoke() {
                    return this.this$0.computeTypeAliases();
                }
            });
            this.allFunctions$delegate = this.this$0.getC().getStorageManager().createLazyValue(new Function0<List<? extends SimpleFunctionDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$NoReorderImplementation$allFunctions$2
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                @NotNull
                public final List<? extends SimpleFunctionDescriptor> invoke() {
                    return CollectionsKt.plus((Collection) this.this$0.getDeclaredFunctions(), (Iterable) this.this$0.computeAllNonDeclaredFunctions());
                }
            });
            this.allProperties$delegate = this.this$0.getC().getStorageManager().createLazyValue(new Function0<List<? extends PropertyDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$NoReorderImplementation$allProperties$2
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                @NotNull
                public final List<? extends PropertyDescriptor> invoke() {
                    return CollectionsKt.plus((Collection) this.this$0.getDeclaredProperties(), (Iterable) this.this$0.computeAllNonDeclaredProperties());
                }
            });
            this.typeAliasesByName$delegate = this.this$0.getC().getStorageManager().createLazyValue(new Function0<Map<Name, ? extends TypeAliasDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$NoReorderImplementation$typeAliasesByName$2
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                @NotNull
                public final Map<Name, ? extends TypeAliasDescriptor> invoke() {
                    Iterable $this$associateBy$iv = this.this$0.getAllTypeAliases();
                    int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateBy$iv, 10)), 16);
                    Map destination$iv$iv = new LinkedHashMap(capacity$iv);
                    for (Object element$iv$iv : $this$associateBy$iv) {
                        TypeAliasDescriptor it = (TypeAliasDescriptor) element$iv$iv;
                        Name name = it.getName();
                        Intrinsics.checkNotNullExpressionValue(name, "it.name");
                        destination$iv$iv.put(name, element$iv$iv);
                    }
                    return destination$iv$iv;
                }
            });
            this.functionsByName$delegate = this.this$0.getC().getStorageManager().createLazyValue(new Function0<Map<Name, ? extends List<? extends SimpleFunctionDescriptor>>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$NoReorderImplementation$functionsByName$2
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                @NotNull
                public final Map<Name, ? extends List<? extends SimpleFunctionDescriptor>> invoke() {
                    Object obj;
                    Iterable $this$groupBy$iv = this.this$0.getAllFunctions();
                    Map destination$iv$iv = new LinkedHashMap();
                    for (Object element$iv$iv : $this$groupBy$iv) {
                        SimpleFunctionDescriptor it = (SimpleFunctionDescriptor) element$iv$iv;
                        Name name = it.getName();
                        Intrinsics.checkNotNullExpressionValue(name, "it.name");
                        Object value$iv$iv$iv = destination$iv$iv.get(name);
                        if (value$iv$iv$iv == null) {
                            ArrayList arrayList = new ArrayList();
                            destination$iv$iv.put(name, arrayList);
                            obj = arrayList;
                        } else {
                            obj = value$iv$iv$iv;
                        }
                        List list$iv$iv = (List) obj;
                        list$iv$iv.add(element$iv$iv);
                    }
                    return destination$iv$iv;
                }
            });
            this.propertiesByName$delegate = this.this$0.getC().getStorageManager().createLazyValue(new Function0<Map<Name, ? extends List<? extends PropertyDescriptor>>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$NoReorderImplementation$propertiesByName$2
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                @NotNull
                public final Map<Name, ? extends List<? extends PropertyDescriptor>> invoke() {
                    Object obj;
                    Iterable $this$groupBy$iv = this.this$0.getAllProperties();
                    Map destination$iv$iv = new LinkedHashMap();
                    for (Object element$iv$iv : $this$groupBy$iv) {
                        PropertyDescriptor it = (PropertyDescriptor) element$iv$iv;
                        Name name = it.getName();
                        Intrinsics.checkNotNullExpressionValue(name, "it.name");
                        Object value$iv$iv$iv = destination$iv$iv.get(name);
                        if (value$iv$iv$iv == null) {
                            ArrayList arrayList = new ArrayList();
                            destination$iv$iv.put(name, arrayList);
                            obj = arrayList;
                        } else {
                            obj = value$iv$iv$iv;
                        }
                        List list$iv$iv = (List) obj;
                        list$iv$iv.add(element$iv$iv);
                    }
                    return destination$iv$iv;
                }
            });
            StorageManager storageManager = this.this$0.getC().getStorageManager();
            final DeserializedMemberScope deserializedMemberScope = this.this$0;
            this.functionNames$delegate = storageManager.createLazyValue(new Function0<Set<? extends Name>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$NoReorderImplementation$functionNames$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                @NotNull
                public final Set<? extends Name> invoke() {
                    DeserializedMemberScope.NoReorderImplementation this_$iv = this.this$0;
                    Iterable $this$mapToNames$iv = this.this$0.functionList;
                    Iterable $this$mapTo$iv$iv = $this$mapToNames$iv;
                    Collection destination$iv$iv = (Set) new LinkedHashSet();
                    DeserializedMemberScope deserializedMemberScope2 = this_$iv.this$0;
                    for (Object item$iv$iv : $this$mapTo$iv$iv) {
                        MessageLite it$iv = (MessageLite) item$iv$iv;
                        ProtoBuf.Function it = (ProtoBuf.Function) it$iv;
                        destination$iv$iv.add(NameResolverUtilKt.getName(deserializedMemberScope2.c.getNameResolver(), it.getName()));
                    }
                    return SetsKt.plus((Set) destination$iv$iv, (Iterable) deserializedMemberScope.getNonDeclaredFunctionNames());
                }
            });
            StorageManager storageManager2 = this.this$0.getC().getStorageManager();
            final DeserializedMemberScope deserializedMemberScope2 = this.this$0;
            this.variableNames$delegate = storageManager2.createLazyValue(new Function0<Set<? extends Name>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$NoReorderImplementation$variableNames$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                @NotNull
                public final Set<? extends Name> invoke() {
                    DeserializedMemberScope.NoReorderImplementation this_$iv = this.this$0;
                    Iterable $this$mapToNames$iv = this.this$0.propertyList;
                    Iterable $this$mapTo$iv$iv = $this$mapToNames$iv;
                    Collection destination$iv$iv = (Set) new LinkedHashSet();
                    DeserializedMemberScope deserializedMemberScope3 = this_$iv.this$0;
                    for (Object item$iv$iv : $this$mapTo$iv$iv) {
                        MessageLite it$iv = (MessageLite) item$iv$iv;
                        ProtoBuf.Property it = (ProtoBuf.Property) it$iv;
                        destination$iv$iv.add(NameResolverUtilKt.getName(deserializedMemberScope3.c.getNameResolver(), it.getName()));
                    }
                    return SetsKt.plus((Set) destination$iv$iv, (Iterable) deserializedMemberScope2.getNonDeclaredVariableNames());
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<SimpleFunctionDescriptor> getDeclaredFunctions() {
            return (List) StorageKt.getValue(this.declaredFunctions$delegate, this, (KProperty<?>) $$delegatedProperties[0]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<PropertyDescriptor> getDeclaredProperties() {
            return (List) StorageKt.getValue(this.declaredProperties$delegate, this, (KProperty<?>) $$delegatedProperties[1]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<TypeAliasDescriptor> getAllTypeAliases() {
            return (List) StorageKt.getValue(this.allTypeAliases$delegate, this, (KProperty<?>) $$delegatedProperties[2]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<SimpleFunctionDescriptor> getAllFunctions() {
            return (List) StorageKt.getValue(this.allFunctions$delegate, this, (KProperty<?>) $$delegatedProperties[3]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<PropertyDescriptor> getAllProperties() {
            return (List) StorageKt.getValue(this.allProperties$delegate, this, (KProperty<?>) $$delegatedProperties[4]);
        }

        private final Map<Name, TypeAliasDescriptor> getTypeAliasesByName() {
            return (Map) StorageKt.getValue(this.typeAliasesByName$delegate, this, (KProperty<?>) $$delegatedProperties[5]);
        }

        private final Map<Name, Collection<SimpleFunctionDescriptor>> getFunctionsByName() {
            return (Map) StorageKt.getValue(this.functionsByName$delegate, this, (KProperty<?>) $$delegatedProperties[6]);
        }

        private final Map<Name, Collection<PropertyDescriptor>> getPropertiesByName() {
            return (Map) StorageKt.getValue(this.propertiesByName$delegate, this, (KProperty<?>) $$delegatedProperties[7]);
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope.Implementation
        @NotNull
        public Set<Name> getFunctionNames() {
            return (Set) StorageKt.getValue(this.functionNames$delegate, this, (KProperty<?>) $$delegatedProperties[8]);
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope.Implementation
        @NotNull
        public Set<Name> getVariableNames() {
            return (Set) StorageKt.getValue(this.variableNames$delegate, this, (KProperty<?>) $$delegatedProperties[9]);
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope.Implementation
        @NotNull
        public Set<Name> getTypeAliasNames() {
            Iterable $this$mapToNames$iv = this.typeAliasList;
            Iterable $this$mapTo$iv$iv = $this$mapToNames$iv;
            Collection destination$iv$iv = (Set) new LinkedHashSet();
            DeserializedMemberScope deserializedMemberScope = this.this$0;
            for (Object item$iv$iv : $this$mapTo$iv$iv) {
                MessageLite it$iv = (MessageLite) item$iv$iv;
                ProtoBuf.TypeAlias it = (ProtoBuf.TypeAlias) it$iv;
                destination$iv$iv.add(NameResolverUtilKt.getName(deserializedMemberScope.c.getNameResolver(), it.getName()));
            }
            return (Set) destination$iv$iv;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<SimpleFunctionDescriptor> computeFunctions() {
            Iterable $this$mapWithDeserializer$iv = this.functionList;
            DeserializedMemberScope deserializedMemberScope = this.this$0;
            Iterable $this$mapNotNull$iv$iv = $this$mapWithDeserializer$iv;
            DeserializedMemberScope deserializedMemberScope2 = this.this$0;
            Collection destination$iv$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv$iv : $this$mapNotNull$iv$iv) {
                MessageLite it$iv = (MessageLite) element$iv$iv$iv$iv;
                MemberDeserializer $this$computeFunctions_u24lambda_u2d1 = deserializedMemberScope2.c.getMemberDeserializer();
                ProtoBuf.Function it = (ProtoBuf.Function) it$iv;
                SimpleFunctionDescriptor p0 = $this$computeFunctions_u24lambda_u2d1.loadFunction(it);
                SimpleFunctionDescriptor simpleFunctionDescriptor = deserializedMemberScope.isDeclaredFunctionAvailable(p0) ? p0 : null;
                if (simpleFunctionDescriptor != null) {
                    destination$iv$iv$iv.add(simpleFunctionDescriptor);
                }
            }
            return (List) destination$iv$iv$iv;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<PropertyDescriptor> computeProperties() {
            Iterable $this$mapWithDeserializer$iv = this.propertyList;
            Iterable $this$mapNotNull$iv$iv = $this$mapWithDeserializer$iv;
            DeserializedMemberScope deserializedMemberScope = this.this$0;
            Collection destination$iv$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv$iv : $this$mapNotNull$iv$iv) {
                MessageLite it$iv = (MessageLite) element$iv$iv$iv$iv;
                MemberDeserializer $this$computeProperties_u24lambda_u2d2 = deserializedMemberScope.c.getMemberDeserializer();
                ProtoBuf.Property it = (ProtoBuf.Property) it$iv;
                PropertyDescriptor propertyDescriptorLoadProperty = $this$computeProperties_u24lambda_u2d2.loadProperty(it);
                if (propertyDescriptorLoadProperty != null) {
                    destination$iv$iv$iv.add(propertyDescriptorLoadProperty);
                }
            }
            return (List) destination$iv$iv$iv;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<TypeAliasDescriptor> computeTypeAliases() {
            Iterable $this$mapWithDeserializer$iv = this.typeAliasList;
            Iterable $this$mapNotNull$iv$iv = $this$mapWithDeserializer$iv;
            DeserializedMemberScope deserializedMemberScope = this.this$0;
            Collection destination$iv$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv$iv : $this$mapNotNull$iv$iv) {
                MessageLite it$iv = (MessageLite) element$iv$iv$iv$iv;
                MemberDeserializer $this$computeTypeAliases_u24lambda_u2d3 = deserializedMemberScope.c.getMemberDeserializer();
                ProtoBuf.TypeAlias it = (ProtoBuf.TypeAlias) it$iv;
                TypeAliasDescriptor typeAliasDescriptorLoadTypeAlias = $this$computeTypeAliases_u24lambda_u2d3.loadTypeAlias(it);
                if (typeAliasDescriptorLoadTypeAlias != null) {
                    destination$iv$iv$iv.add(typeAliasDescriptorLoadTypeAlias);
                }
            }
            return (List) destination$iv$iv$iv;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<SimpleFunctionDescriptor> computeAllNonDeclaredFunctions() {
            Iterable $this$flatMap$iv = this.this$0.getNonDeclaredFunctionNames();
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$flatMap$iv) {
                Name it = (Name) element$iv$iv;
                Iterable list$iv$iv = computeNonDeclaredFunctionsForName(it);
                CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
            }
            return (List) destination$iv$iv;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<PropertyDescriptor> computeAllNonDeclaredProperties() {
            Iterable $this$flatMap$iv = this.this$0.getNonDeclaredVariableNames();
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$flatMap$iv) {
                Name it = (Name) element$iv$iv;
                Iterable list$iv$iv = computeNonDeclaredPropertiesForName(it);
                CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
            }
            return (List) destination$iv$iv;
        }

        private final List<SimpleFunctionDescriptor> computeNonDeclaredFunctionsForName(Name name) {
            Iterable declaredDescriptors$iv = getDeclaredFunctions();
            DeserializedMemberScope deserializedMemberScope = this.this$0;
            Iterable $this$filterTo$iv$iv = declaredDescriptors$iv;
            Collection destination$iv$iv = (List) new ArrayList();
            for (Object element$iv$iv : $this$filterTo$iv$iv) {
                DeclarationDescriptor it$iv = (DeclarationDescriptor) element$iv$iv;
                if (Intrinsics.areEqual(it$iv.getName(), name)) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            List declaredDescriptorsWithSameName$iv = (List) destination$iv$iv;
            int nonDeclaredPropertiesStartIndex$iv = declaredDescriptorsWithSameName$iv.size();
            deserializedMemberScope.computeNonDeclaredFunctions(name, declaredDescriptorsWithSameName$iv);
            return declaredDescriptorsWithSameName$iv.subList(nonDeclaredPropertiesStartIndex$iv, declaredDescriptorsWithSameName$iv.size());
        }

        private final List<PropertyDescriptor> computeNonDeclaredPropertiesForName(Name name) {
            Iterable declaredDescriptors$iv = getDeclaredProperties();
            DeserializedMemberScope deserializedMemberScope = this.this$0;
            Iterable $this$filterTo$iv$iv = declaredDescriptors$iv;
            Collection destination$iv$iv = (List) new ArrayList();
            for (Object element$iv$iv : $this$filterTo$iv$iv) {
                DeclarationDescriptor it$iv = (DeclarationDescriptor) element$iv$iv;
                if (Intrinsics.areEqual(it$iv.getName(), name)) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            List declaredDescriptorsWithSameName$iv = (List) destination$iv$iv;
            int nonDeclaredPropertiesStartIndex$iv = declaredDescriptorsWithSameName$iv.size();
            deserializedMemberScope.computeNonDeclaredProperties(name, declaredDescriptorsWithSameName$iv);
            return declaredDescriptorsWithSameName$iv.subList(nonDeclaredPropertiesStartIndex$iv, declaredDescriptorsWithSameName$iv.size());
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope.Implementation
        @NotNull
        public Collection<SimpleFunctionDescriptor> getContributedFunctions(@NotNull Name name, @NotNull LookupLocation location) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(location, "location");
            if (!getFunctionNames().contains(name)) {
                return CollectionsKt.emptyList();
            }
            Collection<SimpleFunctionDescriptor> collection = getFunctionsByName().get(name);
            return collection != null ? collection : CollectionsKt.emptyList();
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope.Implementation
        @Nullable
        public TypeAliasDescriptor getTypeAliasByName(@NotNull Name name) {
            Intrinsics.checkNotNullParameter(name, "name");
            return getTypeAliasesByName().get(name);
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope.Implementation
        @NotNull
        public Collection<PropertyDescriptor> getContributedVariables(@NotNull Name name, @NotNull LookupLocation location) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(location, "location");
            if (!getVariableNames().contains(name)) {
                return CollectionsKt.emptyList();
            }
            Collection<PropertyDescriptor> collection = getPropertiesByName().get(name);
            return collection != null ? collection : CollectionsKt.emptyList();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope.Implementation
        public void addFunctionsAndPropertiesTo(@NotNull Collection<DeclarationDescriptor> result, @NotNull DescriptorKindFilter kindFilter, @NotNull Function1<? super Name, Boolean> nameFilter, @NotNull LookupLocation location) {
            Intrinsics.checkNotNullParameter(result, "result");
            Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
            Intrinsics.checkNotNullParameter(nameFilter, "nameFilter");
            Intrinsics.checkNotNullParameter(location, "location");
            if (kindFilter.acceptsKinds(DescriptorKindFilter.Companion.getVARIABLES_MASK())) {
                Iterable $this$filterTo$iv = getAllProperties();
                for (Object element$iv : $this$filterTo$iv) {
                    PropertyDescriptor it = (PropertyDescriptor) element$iv;
                    Name name = it.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "it.name");
                    if (nameFilter.invoke(name).booleanValue()) {
                        result.add(element$iv);
                    }
                }
            }
            if (kindFilter.acceptsKinds(DescriptorKindFilter.Companion.getFUNCTIONS_MASK())) {
                Iterable $this$filterTo$iv2 = getAllFunctions();
                for (Object element$iv2 : $this$filterTo$iv2) {
                    SimpleFunctionDescriptor it2 = (SimpleFunctionDescriptor) element$iv2;
                    Name name2 = it2.getName();
                    Intrinsics.checkNotNullExpressionValue(name2, "it.name");
                    if (nameFilter.invoke(name2).booleanValue()) {
                        result.add(element$iv2);
                    }
                }
            }
        }
    }
}
