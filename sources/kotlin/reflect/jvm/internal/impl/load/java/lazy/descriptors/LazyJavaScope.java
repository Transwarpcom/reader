package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.UtilsKt;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaPropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotationsKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeAttributes;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolverKt;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaValueParameter;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindExclude;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: LazyJavaScope.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/lazy/descriptors/LazyJavaScope.class */
public abstract class LazyJavaScope extends MemberScopeImpl {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaScope.class), "functionNamesLazy", "getFunctionNamesLazy()Ljava/util/Set;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaScope.class), "propertyNamesLazy", "getPropertyNamesLazy()Ljava/util/Set;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaScope.class), "classNamesLazy", "getClassNamesLazy()Ljava/util/Set;"))};

    @NotNull
    private final LazyJavaResolverContext c;

    @Nullable
    private final LazyJavaScope mainScope;

    @NotNull
    private final NotNullLazyValue<Collection<DeclarationDescriptor>> allDescriptors;

    @NotNull
    private final NotNullLazyValue<DeclaredMemberIndex> declaredMemberIndex;

    @NotNull
    private final MemoizedFunctionToNotNull<Name, Collection<SimpleFunctionDescriptor>> declaredFunctions;

    @NotNull
    private final MemoizedFunctionToNullable<Name, PropertyDescriptor> declaredField;

    @NotNull
    private final MemoizedFunctionToNotNull<Name, Collection<SimpleFunctionDescriptor>> functions;

    @NotNull
    private final NotNullLazyValue functionNamesLazy$delegate;

    @NotNull
    private final NotNullLazyValue propertyNamesLazy$delegate;

    @NotNull
    private final NotNullLazyValue classNamesLazy$delegate;

    @NotNull
    private final MemoizedFunctionToNotNull<Name, List<PropertyDescriptor>> properties;

    @NotNull
    protected abstract DeclarationDescriptor getOwnerDescriptor();

    @NotNull
    protected abstract DeclaredMemberIndex computeMemberIndex();

    protected abstract void computeNonDeclaredFunctions(@NotNull Collection<SimpleFunctionDescriptor> collection, @NotNull Name name);

    @Nullable
    protected abstract ReceiverParameterDescriptor getDispatchReceiverParameter();

    @NotNull
    protected abstract MethodSignatureData resolveMethodSignature(@NotNull JavaMethod javaMethod, @NotNull List<? extends TypeParameterDescriptor> list, @NotNull KotlinType kotlinType, @NotNull List<? extends ValueParameterDescriptor> list2);

    @NotNull
    protected abstract Set<Name> computeFunctionNames(@NotNull DescriptorKindFilter descriptorKindFilter, @Nullable Function1<? super Name, Boolean> function1);

    protected abstract void computeNonDeclaredProperties(@NotNull Name name, @NotNull Collection<PropertyDescriptor> collection);

    @NotNull
    protected abstract Set<Name> computePropertyNames(@NotNull DescriptorKindFilter descriptorKindFilter, @Nullable Function1<? super Name, Boolean> function1);

    @NotNull
    protected abstract Set<Name> computeClassNames(@NotNull DescriptorKindFilter descriptorKindFilter, @Nullable Function1<? super Name, Boolean> function1);

    public /* synthetic */ LazyJavaScope(LazyJavaResolverContext lazyJavaResolverContext, LazyJavaScope lazyJavaScope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(lazyJavaResolverContext, (i & 2) != 0 ? null : lazyJavaScope);
    }

    @NotNull
    protected final LazyJavaResolverContext getC() {
        return this.c;
    }

    @Nullable
    protected final LazyJavaScope getMainScope() {
        return this.mainScope;
    }

    public LazyJavaScope(@NotNull LazyJavaResolverContext c, @Nullable LazyJavaScope mainScope) {
        Intrinsics.checkNotNullParameter(c, "c");
        this.c = c;
        this.mainScope = mainScope;
        this.allDescriptors = this.c.getStorageManager().createRecursionTolerantLazyValue(new Function0<Collection<? extends DeclarationDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$allDescriptors$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final Collection<? extends DeclarationDescriptor> invoke() {
                return this.this$0.computeDescriptors(DescriptorKindFilter.ALL, MemberScope.Companion.getALL_NAME_FILTER());
            }
        }, CollectionsKt.emptyList());
        this.declaredMemberIndex = this.c.getStorageManager().createLazyValue(new Function0<DeclaredMemberIndex>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$declaredMemberIndex$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final DeclaredMemberIndex invoke() {
                return this.this$0.computeMemberIndex();
            }
        });
        this.declaredFunctions = this.c.getStorageManager().createMemoizedFunction(new Function1<Name, Collection<? extends SimpleFunctionDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$declaredFunctions$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final Collection<SimpleFunctionDescriptor> invoke(@NotNull Name name) {
                Intrinsics.checkNotNullParameter(name, "name");
                if (this.this$0.getMainScope() != null) {
                    return (Collection) this.this$0.getMainScope().declaredFunctions.invoke(name);
                }
                List result = new ArrayList();
                for (JavaMethod method : this.this$0.getDeclaredMemberIndex().invoke().findMethodsByName(name)) {
                    JavaMethodDescriptor descriptor = this.this$0.resolveMethodToFunctionDescriptor(method);
                    if (this.this$0.isVisibleAsFunction(descriptor)) {
                        this.this$0.getC().getComponents().getJavaResolverCache().recordMethod(method, descriptor);
                        result.add(descriptor);
                    }
                }
                this.this$0.computeImplicitlyDeclaredFunctions(result, name);
                return result;
            }
        });
        this.declaredField = this.c.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1<Name, PropertyDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$declaredField$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @Nullable
            public final PropertyDescriptor invoke(@NotNull Name name) {
                Intrinsics.checkNotNullParameter(name, "name");
                if (this.this$0.getMainScope() != null) {
                    return (PropertyDescriptor) this.this$0.getMainScope().declaredField.invoke(name);
                }
                JavaField field = this.this$0.getDeclaredMemberIndex().invoke().findFieldByName(name);
                if (field != null && !field.isEnumEntry()) {
                    return this.this$0.resolveProperty(field);
                }
                return null;
            }
        });
        this.functions = this.c.getStorageManager().createMemoizedFunction(new Function1<Name, Collection<? extends SimpleFunctionDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$functions$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final Collection<SimpleFunctionDescriptor> invoke(@NotNull Name name) {
                Intrinsics.checkNotNullParameter(name, "name");
                LinkedHashSet result = new LinkedHashSet((Collection) this.this$0.declaredFunctions.invoke(name));
                this.this$0.retainMostSpecificMethods(result);
                this.this$0.computeNonDeclaredFunctions(result, name);
                return CollectionsKt.toList(this.this$0.getC().getComponents().getSignatureEnhancement().enhanceSignatures(this.this$0.getC(), result));
            }
        });
        this.functionNamesLazy$delegate = this.c.getStorageManager().createLazyValue(new Function0<Set<? extends Name>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$functionNamesLazy$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final Set<? extends Name> invoke() {
                return this.this$0.computeFunctionNames(DescriptorKindFilter.FUNCTIONS, null);
            }
        });
        this.propertyNamesLazy$delegate = this.c.getStorageManager().createLazyValue(new Function0<Set<? extends Name>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$propertyNamesLazy$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final Set<? extends Name> invoke() {
                return this.this$0.computePropertyNames(DescriptorKindFilter.VARIABLES, null);
            }
        });
        this.classNamesLazy$delegate = this.c.getStorageManager().createLazyValue(new Function0<Set<? extends Name>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$classNamesLazy$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final Set<? extends Name> invoke() {
                return this.this$0.computeClassNames(DescriptorKindFilter.CLASSIFIERS, null);
            }
        });
        this.properties = this.c.getStorageManager().createMemoizedFunction(new Function1<Name, List<? extends PropertyDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$properties$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final List<PropertyDescriptor> invoke(@NotNull Name name) {
                Intrinsics.checkNotNullParameter(name, "name");
                ArrayList properties = new ArrayList();
                kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(properties, this.this$0.declaredField.invoke(name));
                this.this$0.computeNonDeclaredProperties(name, properties);
                if (DescriptorUtils.isAnnotationClass(this.this$0.getOwnerDescriptor())) {
                    return CollectionsKt.toList(properties);
                }
                return CollectionsKt.toList(this.this$0.getC().getComponents().getSignatureEnhancement().enhanceSignatures(this.this$0.getC(), properties));
            }
        });
    }

    @NotNull
    protected final NotNullLazyValue<Collection<DeclarationDescriptor>> getAllDescriptors() {
        return this.allDescriptors;
    }

    @NotNull
    protected final NotNullLazyValue<DeclaredMemberIndex> getDeclaredMemberIndex() {
        return this.declaredMemberIndex;
    }

    protected void computeImplicitlyDeclaredFunctions(@NotNull Collection<SimpleFunctionDescriptor> result, @NotNull Name name) {
        Intrinsics.checkNotNullParameter(result, "result");
        Intrinsics.checkNotNullParameter(name, "name");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void retainMostSpecificMethods(Set<SimpleFunctionDescriptor> set) {
        Object obj;
        Set<SimpleFunctionDescriptor> $this$groupBy$iv = set;
        Map destination$iv$iv = new LinkedHashMap();
        for (Object element$iv$iv : $this$groupBy$iv) {
            SimpleFunctionDescriptor it = (SimpleFunctionDescriptor) element$iv$iv;
            String strComputeJvmDescriptor$default = MethodSignatureMappingKt.computeJvmDescriptor$default(it, false, false, 2, null);
            Object value$iv$iv$iv = destination$iv$iv.get(strComputeJvmDescriptor$default);
            if (value$iv$iv$iv == null) {
                ArrayList arrayList = new ArrayList();
                destination$iv$iv.put(strComputeJvmDescriptor$default, arrayList);
                obj = arrayList;
            } else {
                obj = value$iv$iv$iv;
            }
            List list$iv$iv = (List) obj;
            list$iv$iv.add(element$iv$iv);
        }
        Collection<List> groups = destination$iv$iv.values();
        for (List group : groups) {
            if (group.size() != 1) {
                Collection mostSpecificMethods = OverridingUtilsKt.selectMostSpecificInEachOverridableGroup(group, new Function1<SimpleFunctionDescriptor, CallableDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$retainMostSpecificMethods$mostSpecificMethods$1
                    @Override // kotlin.jvm.functions.Function1
                    @NotNull
                    public final CallableDescriptor invoke(@NotNull SimpleFunctionDescriptor selectMostSpecificInEachOverridableGroup) {
                        Intrinsics.checkNotNullParameter(selectMostSpecificInEachOverridableGroup, "$this$selectMostSpecificInEachOverridableGroup");
                        return selectMostSpecificInEachOverridableGroup;
                    }
                });
                set.removeAll(group);
                set.addAll(mostSpecificMethods);
            }
        }
    }

    protected boolean isVisibleAsFunction(@NotNull JavaMethodDescriptor $this$isVisibleAsFunction) {
        Intrinsics.checkNotNullParameter($this$isVisibleAsFunction, "<this>");
        return true;
    }

    /* compiled from: LazyJavaScope.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/lazy/descriptors/LazyJavaScope$MethodSignatureData.class */
    protected static final class MethodSignatureData {

        @NotNull
        private final KotlinType returnType;

        @Nullable
        private final KotlinType receiverType;

        @NotNull
        private final List<ValueParameterDescriptor> valueParameters;

        @NotNull
        private final List<TypeParameterDescriptor> typeParameters;
        private final boolean hasStableParameterNames;

        @NotNull
        private final List<String> errors;

        @NotNull
        public String toString() {
            return "MethodSignatureData(returnType=" + this.returnType + ", receiverType=" + this.receiverType + ", valueParameters=" + this.valueParameters + ", typeParameters=" + this.typeParameters + ", hasStableParameterNames=" + this.hasStableParameterNames + ", errors=" + this.errors + ')';
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int iHashCode = ((((((this.returnType.hashCode() * 31) + (this.receiverType == null ? 0 : this.receiverType.hashCode())) * 31) + this.valueParameters.hashCode()) * 31) + this.typeParameters.hashCode()) * 31;
            boolean z = this.hasStableParameterNames;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            return ((iHashCode + i) * 31) + this.errors.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MethodSignatureData)) {
                return false;
            }
            MethodSignatureData methodSignatureData = (MethodSignatureData) other;
            return Intrinsics.areEqual(this.returnType, methodSignatureData.returnType) && Intrinsics.areEqual(this.receiverType, methodSignatureData.receiverType) && Intrinsics.areEqual(this.valueParameters, methodSignatureData.valueParameters) && Intrinsics.areEqual(this.typeParameters, methodSignatureData.typeParameters) && this.hasStableParameterNames == methodSignatureData.hasStableParameterNames && Intrinsics.areEqual(this.errors, methodSignatureData.errors);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public MethodSignatureData(@NotNull KotlinType returnType, @Nullable KotlinType receiverType, @NotNull List<? extends ValueParameterDescriptor> valueParameters, @NotNull List<? extends TypeParameterDescriptor> typeParameters, boolean hasStableParameterNames, @NotNull List<String> errors) {
            Intrinsics.checkNotNullParameter(returnType, "returnType");
            Intrinsics.checkNotNullParameter(valueParameters, "valueParameters");
            Intrinsics.checkNotNullParameter(typeParameters, "typeParameters");
            Intrinsics.checkNotNullParameter(errors, "errors");
            this.returnType = returnType;
            this.receiverType = receiverType;
            this.valueParameters = valueParameters;
            this.typeParameters = typeParameters;
            this.hasStableParameterNames = hasStableParameterNames;
            this.errors = errors;
        }

        @NotNull
        public final KotlinType getReturnType() {
            return this.returnType;
        }

        @Nullable
        public final KotlinType getReceiverType() {
            return this.receiverType;
        }

        @NotNull
        public final List<ValueParameterDescriptor> getValueParameters() {
            return this.valueParameters;
        }

        @NotNull
        public final List<TypeParameterDescriptor> getTypeParameters() {
            return this.typeParameters;
        }

        public final boolean getHasStableParameterNames() {
            return this.hasStableParameterNames;
        }

        @NotNull
        public final List<String> getErrors() {
            return this.errors;
        }
    }

    @NotNull
    protected final JavaMethodDescriptor resolveMethodToFunctionDescriptor(@NotNull JavaMethod method) {
        ReceiverParameterDescriptor receiverParameterDescriptor;
        Map<? extends CallableDescriptor.UserDataKey<?>, ?> mapEmptyMap;
        Intrinsics.checkNotNullParameter(method, "method");
        Annotations annotations = LazyJavaAnnotationsKt.resolveAnnotations(this.c, method);
        JavaMethodDescriptor functionDescriptorImpl = JavaMethodDescriptor.createJavaMethod(getOwnerDescriptor(), annotations, method.getName(), this.c.getComponents().getSourceElementFactory().source(method), this.declaredMemberIndex.invoke().findRecordComponentByName(method.getName()) != null && method.getValueParameters().isEmpty());
        Intrinsics.checkNotNullExpressionValue(functionDescriptorImpl, "createJavaMethod(\n            ownerDescriptor, annotations, method.name, c.components.sourceElementFactory.source(method),\n            declaredMemberIndex().findRecordComponentByName(method.name) != null && method.valueParameters.isEmpty()\n        )");
        LazyJavaResolverContext c = ContextKt.childForMethod$default(this.c, functionDescriptorImpl, method, 0, 4, null);
        Iterable $this$map$iv = method.getTypeParameters();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            JavaTypeParameter p = (JavaTypeParameter) item$iv$iv;
            TypeParameterDescriptor typeParameterDescriptorResolveTypeParameter = c.getTypeParameterResolver().resolveTypeParameter(p);
            Intrinsics.checkNotNull(typeParameterDescriptorResolveTypeParameter);
            destination$iv$iv.add(typeParameterDescriptorResolveTypeParameter);
        }
        List methodTypeParameters = (List) destination$iv$iv;
        ResolvedValueParameters valueParameters = resolveValueParameters(c, functionDescriptorImpl, method.getValueParameters());
        KotlinType returnType = computeMethodReturnType(method, c);
        MethodSignatureData effectiveSignature = resolveMethodSignature(method, methodTypeParameters, returnType, valueParameters.getDescriptors());
        JavaMethodDescriptor javaMethodDescriptor = functionDescriptorImpl;
        KotlinType it = effectiveSignature.getReceiverType();
        if (it == null) {
            receiverParameterDescriptor = null;
        } else {
            ReceiverParameterDescriptor receiverParameterDescriptorCreateExtensionReceiverParameterForCallable = DescriptorFactory.createExtensionReceiverParameterForCallable(functionDescriptorImpl, it, Annotations.Companion.getEMPTY());
            javaMethodDescriptor = javaMethodDescriptor;
            receiverParameterDescriptor = receiverParameterDescriptorCreateExtensionReceiverParameterForCallable;
        }
        ReceiverParameterDescriptor dispatchReceiverParameter = getDispatchReceiverParameter();
        List<TypeParameterDescriptor> typeParameters = effectiveSignature.getTypeParameters();
        List<ValueParameterDescriptor> valueParameters2 = effectiveSignature.getValueParameters();
        KotlinType returnType2 = effectiveSignature.getReturnType();
        Modality modalityConvertFromFlags = Modality.Companion.convertFromFlags(false, method.isAbstract(), !method.isFinal());
        DescriptorVisibility descriptorVisibility = UtilsKt.toDescriptorVisibility(method.getVisibility());
        if (effectiveSignature.getReceiverType() != null) {
            mapEmptyMap = MapsKt.mapOf(TuplesKt.to(JavaMethodDescriptor.ORIGINAL_VALUE_PARAMETER_FOR_EXTENSION_RECEIVER, CollectionsKt.first((List) valueParameters.getDescriptors())));
        } else {
            mapEmptyMap = MapsKt.emptyMap();
        }
        javaMethodDescriptor.initialize(receiverParameterDescriptor, dispatchReceiverParameter, typeParameters, valueParameters2, returnType2, modalityConvertFromFlags, descriptorVisibility, mapEmptyMap);
        functionDescriptorImpl.setParameterNamesStatus(effectiveSignature.getHasStableParameterNames(), valueParameters.getHasSynthesizedNames());
        if (!effectiveSignature.getErrors().isEmpty()) {
            c.getComponents().getSignaturePropagator().reportSignatureErrors(functionDescriptorImpl, effectiveSignature.getErrors());
        }
        return functionDescriptorImpl;
    }

    @NotNull
    protected final KotlinType computeMethodReturnType(@NotNull JavaMethod method, @NotNull LazyJavaResolverContext c) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(c, "c");
        boolean annotationMethod = method.getContainingClass().isAnnotationType();
        JavaTypeAttributes returnTypeAttrs = JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, annotationMethod, null, 2, null);
        return c.getTypeResolver().transformJavaType(method.getReturnType(), returnTypeAttrs);
    }

    /* compiled from: LazyJavaScope.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/lazy/descriptors/LazyJavaScope$ResolvedValueParameters.class */
    protected static final class ResolvedValueParameters {

        @NotNull
        private final List<ValueParameterDescriptor> descriptors;
        private final boolean hasSynthesizedNames;

        /* JADX WARN: Multi-variable type inference failed */
        public ResolvedValueParameters(@NotNull List<? extends ValueParameterDescriptor> descriptors, boolean hasSynthesizedNames) {
            Intrinsics.checkNotNullParameter(descriptors, "descriptors");
            this.descriptors = descriptors;
            this.hasSynthesizedNames = hasSynthesizedNames;
        }

        @NotNull
        public final List<ValueParameterDescriptor> getDescriptors() {
            return this.descriptors;
        }

        public final boolean getHasSynthesizedNames() {
            return this.hasSynthesizedNames;
        }
    }

    @NotNull
    protected final ResolvedValueParameters resolveValueParameters(@NotNull LazyJavaResolverContext c, @NotNull FunctionDescriptor function, @NotNull List<? extends JavaValueParameter> jValueParameters) {
        Pair pair;
        Name nameIdentifier;
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(function, "function");
        Intrinsics.checkNotNullParameter(jValueParameters, "jValueParameters");
        boolean synthesizedNames = false;
        Iterable $this$map$iv = CollectionsKt.withIndex(jValueParameters);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            IndexedValue $dstr$index$javaParameter = (IndexedValue) item$iv$iv;
            int index = $dstr$index$javaParameter.component1();
            JavaValueParameter javaParameter = (JavaValueParameter) $dstr$index$javaParameter.component2();
            Annotations annotations = LazyJavaAnnotationsKt.resolveAnnotations(c, javaParameter);
            JavaTypeAttributes typeUsage = JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null);
            if (javaParameter.isVararg()) {
                JavaType type = javaParameter.getType();
                JavaArrayType paramType = type instanceof JavaArrayType ? (JavaArrayType) type : null;
                if (paramType != null) {
                    KotlinType outType = c.getTypeResolver().transformArrayType(paramType, typeUsage, true);
                    pair = TuplesKt.to(outType, c.getModule().getBuiltIns().getArrayElementType(outType));
                } else {
                    throw new AssertionError(Intrinsics.stringPlus("Vararg parameter should be an array: ", javaParameter));
                }
            } else {
                pair = TuplesKt.to(c.getTypeResolver().transformJavaType(javaParameter.getType(), typeUsage), null);
            }
            Pair pair2 = pair;
            KotlinType outType2 = (KotlinType) pair2.component1();
            KotlinType varargElementType = (KotlinType) pair2.component2();
            if (Intrinsics.areEqual(function.getName().asString(), "equals") && jValueParameters.size() == 1 && Intrinsics.areEqual(c.getModule().getBuiltIns().getNullableAnyType(), outType2)) {
                nameIdentifier = Name.identifier("other");
            } else {
                Name javaName = javaParameter.getName();
                if (javaName == null) {
                    synthesizedNames = true;
                }
                if (javaName == null) {
                    Name nameIdentifier2 = Name.identifier(Intrinsics.stringPlus("p", Integer.valueOf(index)));
                    Intrinsics.checkNotNullExpressionValue(nameIdentifier2, "identifier(\"p$index\")");
                    nameIdentifier = nameIdentifier2;
                } else {
                    nameIdentifier = javaName;
                }
            }
            Name name = nameIdentifier;
            Intrinsics.checkNotNullExpressionValue(name, "if (function.name.asString() == \"equals\" &&\n                jValueParameters.size == 1 &&\n                c.module.builtIns.nullableAnyType == outType\n            ) {\n                // This is a hack to prevent numerous warnings on Kotlin classes that inherit Java classes: if you override \"equals\" in such\n                // class without this hack, you'll be warned that in the superclass the name is \"p0\" (regardless of the fact that it's\n                // \"other\" in Any)\n                // TODO: fix Java parameter name loading logic somehow (don't always load \"p0\", \"p1\", etc.)\n                Name.identifier(\"other\")\n            } else {\n                // TODO: parameter names may be drawn from attached sources, which is slow; it's better to make them lazy\n                val javaName = javaParameter.name\n                if (javaName == null) synthesizedNames = true\n                javaName ?: Name.identifier(\"p$index\")\n            }");
            destination$iv$iv.add(new ValueParameterDescriptorImpl(function, null, index, annotations, name, outType2, false, false, false, varargElementType, c.getComponents().getSourceElementFactory().source(javaParameter)));
        }
        List descriptors = CollectionsKt.toList((List) destination$iv$iv);
        return new ResolvedValueParameters(descriptors, synthesizedNames);
    }

    private final Set<Name> getFunctionNamesLazy() {
        return (Set) StorageKt.getValue(this.functionNamesLazy$delegate, this, (KProperty<?>) $$delegatedProperties[0]);
    }

    private final Set<Name> getPropertyNamesLazy() {
        return (Set) StorageKt.getValue(this.propertyNamesLazy$delegate, this, (KProperty<?>) $$delegatedProperties[1]);
    }

    private final Set<Name> getClassNamesLazy() {
        return (Set) StorageKt.getValue(this.classNamesLazy$delegate, this, (KProperty<?>) $$delegatedProperties[2]);
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    public Set<Name> getFunctionNames() {
        return getFunctionNamesLazy();
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    public Set<Name> getVariableNames() {
        return getPropertyNamesLazy();
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    public Set<Name> getClassifierNames() {
        return getClassNamesLazy();
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @NotNull
    public Collection<SimpleFunctionDescriptor> getContributedFunctions(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        return !getFunctionNames().contains(name) ? CollectionsKt.emptyList() : this.functions.invoke(name);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PropertyDescriptor resolveProperty(final JavaField field) {
        final PropertyDescriptorImpl propertyDescriptor = createPropertyDescriptor(field);
        propertyDescriptor.initialize(null, null, null, null);
        KotlinType propertyType = getPropertyType(field);
        propertyDescriptor.setType(propertyType, CollectionsKt.emptyList(), getDispatchReceiverParameter(), null);
        if (DescriptorUtils.shouldRecordInitializerForProperty(propertyDescriptor, propertyDescriptor.getType())) {
            propertyDescriptor.setCompileTimeInitializer(this.c.getStorageManager().createNullableLazyValue(new Function0<ConstantValue<?>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope.resolveProperty.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                @Nullable
                public final ConstantValue<?> invoke() {
                    return LazyJavaScope.this.getC().getComponents().getJavaPropertyInitializerEvaluator().getInitializerConstant(field, propertyDescriptor);
                }
            }));
        }
        this.c.getComponents().getJavaResolverCache().recordField(field, propertyDescriptor);
        return propertyDescriptor;
    }

    private final PropertyDescriptorImpl createPropertyDescriptor(JavaField field) {
        boolean isVar = !field.isFinal();
        Annotations annotations = LazyJavaAnnotationsKt.resolveAnnotations(this.c, field);
        JavaPropertyDescriptor javaPropertyDescriptorCreate = JavaPropertyDescriptor.create(getOwnerDescriptor(), annotations, Modality.FINAL, UtilsKt.toDescriptorVisibility(field.getVisibility()), isVar, field.getName(), this.c.getComponents().getSourceElementFactory().source(field), isFinalStatic(field));
        Intrinsics.checkNotNullExpressionValue(javaPropertyDescriptorCreate, "create(\n            ownerDescriptor, annotations, Modality.FINAL, field.visibility.toDescriptorVisibility(), isVar, field.name,\n            c.components.sourceElementFactory.source(field), /* isConst = */ field.isFinalStatic\n        )");
        return javaPropertyDescriptorCreate;
    }

    private final boolean isFinalStatic(JavaField $this$isFinalStatic) {
        return $this$isFinalStatic.isFinal() && $this$isFinalStatic.isStatic();
    }

    private final KotlinType getPropertyType(JavaField field) {
        KotlinType propertyType = this.c.getTypeResolver().transformJavaType(field.getType(), JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null));
        boolean isNotNullable = (KotlinBuiltIns.isPrimitiveType(propertyType) || KotlinBuiltIns.isString(propertyType)) && isFinalStatic(field) && field.getHasConstantNotNullInitializer();
        if (isNotNullable) {
            KotlinType kotlinTypeMakeNotNullable = TypeUtils.makeNotNullable(propertyType);
            Intrinsics.checkNotNullExpressionValue(kotlinTypeMakeNotNullable, "makeNotNullable(propertyType)");
            return kotlinTypeMakeNotNullable;
        }
        return propertyType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    public Collection<PropertyDescriptor> getContributedVariables(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        return !getVariableNames().contains(name) ? CollectionsKt.emptyList() : this.properties.invoke(name);
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @NotNull
    public Collection<DeclarationDescriptor> getContributedDescriptors(@NotNull DescriptorKindFilter kindFilter, @NotNull Function1<? super Name, Boolean> nameFilter) {
        Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
        Intrinsics.checkNotNullParameter(nameFilter, "nameFilter");
        return this.allDescriptors.invoke();
    }

    @NotNull
    protected final List<DeclarationDescriptor> computeDescriptors(@NotNull DescriptorKindFilter kindFilter, @NotNull Function1<? super Name, Boolean> nameFilter) {
        Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
        Intrinsics.checkNotNullParameter(nameFilter, "nameFilter");
        NoLookupLocation location = NoLookupLocation.WHEN_GET_ALL_DESCRIPTORS;
        LinkedHashSet result = new LinkedHashSet();
        if (kindFilter.acceptsKinds(DescriptorKindFilter.Companion.getCLASSIFIERS_MASK())) {
            for (Name name : computeClassNames(kindFilter, nameFilter)) {
                if (nameFilter.invoke(name).booleanValue()) {
                    kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(result, mo3858getContributedClassifier(name, location));
                }
            }
        }
        if (kindFilter.acceptsKinds(DescriptorKindFilter.Companion.getFUNCTIONS_MASK()) && !kindFilter.getExcludes().contains(DescriptorKindExclude.NonExtensions.INSTANCE)) {
            for (Name name2 : computeFunctionNames(kindFilter, nameFilter)) {
                if (nameFilter.invoke(name2).booleanValue()) {
                    result.addAll(getContributedFunctions(name2, location));
                }
            }
        }
        if (kindFilter.acceptsKinds(DescriptorKindFilter.Companion.getVARIABLES_MASK()) && !kindFilter.getExcludes().contains(DescriptorKindExclude.NonExtensions.INSTANCE)) {
            for (Name name3 : computePropertyNames(kindFilter, nameFilter)) {
                if (nameFilter.invoke(name3).booleanValue()) {
                    result.addAll(getContributedVariables(name3, location));
                }
            }
        }
        return CollectionsKt.toList(result);
    }

    @NotNull
    public String toString() {
        return Intrinsics.stringPlus("Lazy scope for ", getOwnerDescriptor());
    }
}
