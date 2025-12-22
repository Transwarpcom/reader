package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.SuspendFunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassConstructorDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorBase;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.EnumEntrySyntheticClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertySetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithDifferentJvmName;
import kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature;
import kotlin.reflect.jvm.internal.impl.load.java.ClassicBuiltinSpecialProperties;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassFinder;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassesTracker;
import kotlin.reflect.jvm.internal.impl.load.java.JavaDescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.load.java.JavaIncompatibilityRulesOverridabilityCondition;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAbi;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers;
import kotlin.reflect.jvm.internal.impl.load.java.SpecialGenericSignatures;
import kotlin.reflect.jvm.internal.impl.load.java.UtilsKt;
import kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils;
import kotlin.reflect.jvm.internal.impl.load.java.components.SignaturePropagator;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaForKotlinOverridePropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaPropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.UtilKt;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.ValueParameterData;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotationsKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeAttributes;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolverKt;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaConstructor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMember;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaRecordComponent;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: LazyJavaClassMemberScope.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/lazy/descriptors/LazyJavaClassMemberScope.class */
public final class LazyJavaClassMemberScope extends LazyJavaScope {

    @NotNull
    private final ClassDescriptor ownerDescriptor;

    @NotNull
    private final JavaClass jClass;
    private final boolean skipRefinement;

    @NotNull
    private final NotNullLazyValue<List<ClassConstructorDescriptor>> constructors;

    @NotNull
    private final NotNullLazyValue<Set<Name>> nestedClassIndex;

    @NotNull
    private final NotNullLazyValue<Map<Name, JavaField>> enumEntryIndex;

    @NotNull
    private final MemoizedFunctionToNullable<Name, ClassDescriptorBase> nestedClasses;

    public /* synthetic */ LazyJavaClassMemberScope(LazyJavaResolverContext lazyJavaResolverContext, ClassDescriptor classDescriptor, JavaClass javaClass, boolean z, LazyJavaClassMemberScope lazyJavaClassMemberScope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(lazyJavaResolverContext, classDescriptor, javaClass, z, (i & 16) != 0 ? null : lazyJavaClassMemberScope);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    public /* bridge */ /* synthetic */ Set computeFunctionNames(DescriptorKindFilter kindFilter, Function1 nameFilter) {
        return computeFunctionNames(kindFilter, (Function1<? super Name, Boolean>) nameFilter);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    @NotNull
    public ClassDescriptor getOwnerDescriptor() {
        return this.ownerDescriptor;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyJavaClassMemberScope(@NotNull final LazyJavaResolverContext c, @NotNull ClassDescriptor ownerDescriptor, @NotNull JavaClass jClass, boolean skipRefinement, @Nullable LazyJavaClassMemberScope mainScope) {
        super(c, mainScope);
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(ownerDescriptor, "ownerDescriptor");
        Intrinsics.checkNotNullParameter(jClass, "jClass");
        this.ownerDescriptor = ownerDescriptor;
        this.jClass = jClass;
        this.skipRefinement = skipRefinement;
        this.constructors = c.getStorageManager().createLazyValue(new Function0<List<? extends ClassConstructorDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope$constructors$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final List<? extends ClassConstructorDescriptor> invoke() {
                List listListOfNotNull;
                boolean z;
                Collection constructors = this.this$0.jClass.getConstructors();
                ArrayList result = new ArrayList(constructors.size());
                for (JavaConstructor constructor : constructors) {
                    JavaClassConstructorDescriptor descriptor = this.this$0.resolveConstructor(constructor);
                    result.add(descriptor);
                }
                if (this.this$0.jClass.isRecord()) {
                    ClassConstructorDescriptor defaultConstructor = this.this$0.createDefaultRecordConstructor();
                    String jvmDescriptor = MethodSignatureMappingKt.computeJvmDescriptor$default(defaultConstructor, false, false, 2, null);
                    ArrayList $this$none$iv = result;
                    if (!($this$none$iv instanceof Collection) || !$this$none$iv.isEmpty()) {
                        Iterator it = $this$none$iv.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                Object element$iv = it.next();
                                ClassConstructorDescriptor it2 = (ClassConstructorDescriptor) element$iv;
                                if (Intrinsics.areEqual(MethodSignatureMappingKt.computeJvmDescriptor$default(it2, false, false, 2, null), jvmDescriptor)) {
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
                        result.add(defaultConstructor);
                        c.getComponents().getJavaResolverCache().recordConstructor(this.this$0.jClass, defaultConstructor);
                    }
                }
                c.getComponents().getSyntheticPartsProvider().generateConstructors(this.this$0.getOwnerDescriptor(), result);
                SignatureEnhancement signatureEnhancement = c.getComponents().getSignatureEnhancement();
                LazyJavaResolverContext lazyJavaResolverContext = c;
                ArrayList arrayList = result;
                LazyJavaClassMemberScope lazyJavaClassMemberScope = this.this$0;
                if (arrayList.isEmpty()) {
                    signatureEnhancement = signatureEnhancement;
                    lazyJavaResolverContext = lazyJavaResolverContext;
                    listListOfNotNull = CollectionsKt.listOfNotNull(lazyJavaClassMemberScope.createDefaultConstructor());
                } else {
                    listListOfNotNull = arrayList;
                }
                return CollectionsKt.toList(signatureEnhancement.enhanceSignatures(lazyJavaResolverContext, listListOfNotNull));
            }
        });
        this.nestedClassIndex = c.getStorageManager().createLazyValue(new Function0<Set<? extends Name>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope$nestedClassIndex$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final Set<? extends Name> invoke() {
                return CollectionsKt.toSet(this.this$0.jClass.getInnerClassNames());
            }
        });
        this.enumEntryIndex = c.getStorageManager().createLazyValue(new Function0<Map<Name, ? extends JavaField>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope$enumEntryIndex$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final Map<Name, ? extends JavaField> invoke() {
                Iterable $this$filter$iv = this.this$0.jClass.getFields();
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv : $this$filter$iv) {
                    JavaField it = (JavaField) element$iv$iv;
                    if (it.isEnumEntry()) {
                        destination$iv$iv.add(element$iv$iv);
                    }
                }
                Iterable $this$associateBy$iv = (List) destination$iv$iv;
                int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateBy$iv, 10)), 16);
                Map destination$iv$iv2 = new LinkedHashMap(capacity$iv);
                for (Object element$iv$iv2 : $this$associateBy$iv) {
                    JavaField f = (JavaField) element$iv$iv2;
                    destination$iv$iv2.put(f.getName(), element$iv$iv2);
                }
                return destination$iv$iv2;
            }
        });
        this.nestedClasses = c.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1<Name, ClassDescriptorBase>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope$nestedClasses$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @Nullable
            public final ClassDescriptorBase invoke(@NotNull Name name) {
                LazyJavaClassDescriptor lazyJavaClassDescriptor;
                EnumEntrySyntheticClassDescriptor enumEntrySyntheticClassDescriptorCreate;
                Intrinsics.checkNotNullParameter(name, "name");
                if (!((Set) this.this$0.nestedClassIndex.invoke()).contains(name)) {
                    JavaField field = (JavaField) ((Map) this.this$0.enumEntryIndex.invoke()).get(name);
                    if (field != null) {
                        StorageManager storageManager = c.getStorageManager();
                        final LazyJavaClassMemberScope lazyJavaClassMemberScope = this.this$0;
                        NotNullLazyValue enumMemberNames = storageManager.createLazyValue(new Function0<Set<? extends Name>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope$nestedClasses$1$enumMemberNames$1
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            @NotNull
                            public final Set<? extends Name> invoke() {
                                return SetsKt.plus((Set) lazyJavaClassMemberScope.getFunctionNames(), (Iterable) lazyJavaClassMemberScope.getVariableNames());
                            }
                        });
                        enumEntrySyntheticClassDescriptorCreate = EnumEntrySyntheticClassDescriptor.create(c.getStorageManager(), this.this$0.getOwnerDescriptor(), name, enumMemberNames, LazyJavaAnnotationsKt.resolveAnnotations(c, field), c.getComponents().getSourceElementFactory().source(field));
                    } else {
                        enumEntrySyntheticClassDescriptorCreate = null;
                    }
                    return enumEntrySyntheticClassDescriptorCreate;
                }
                JavaClassFinder finder = c.getComponents().getFinder();
                ClassId classId = DescriptorUtilsKt.getClassId(this.this$0.getOwnerDescriptor());
                Intrinsics.checkNotNull(classId);
                ClassId classIdCreateNestedClassId = classId.createNestedClassId(name);
                Intrinsics.checkNotNullExpressionValue(classIdCreateNestedClassId, "ownerDescriptor.classId!!.createNestedClassId(name)");
                JavaClass it = finder.findClass(new JavaClassFinder.Request(classIdCreateNestedClassId, null, this.this$0.jClass, 2, null));
                if (it == null) {
                    lazyJavaClassDescriptor = null;
                } else {
                    LazyJavaResolverContext lazyJavaResolverContext = c;
                    LazyJavaClassDescriptor lazyJavaClassDescriptor2 = new LazyJavaClassDescriptor(lazyJavaResolverContext, this.this$0.getOwnerDescriptor(), it, null, 8, null);
                    JavaClassesTracker javaClassesTracker = lazyJavaResolverContext.getComponents().getJavaClassesTracker();
                    LazyJavaClassDescriptor p0 = lazyJavaClassDescriptor2;
                    javaClassesTracker.reportClass(p0);
                    lazyJavaClassDescriptor = lazyJavaClassDescriptor2;
                }
                return lazyJavaClassDescriptor;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    @NotNull
    public ClassDeclaredMemberIndex computeMemberIndex() {
        return new ClassDeclaredMemberIndex(this.jClass, new Function1<JavaMember, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.computeMemberIndex.1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(@NotNull JavaMember it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return !it.isStatic();
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(JavaMember javaMember) {
                return Boolean.valueOf(invoke2(javaMember));
            }
        });
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    @NotNull
    protected LinkedHashSet<Name> computeFunctionNames(@NotNull DescriptorKindFilter kindFilter, @Nullable Function1<? super Name, Boolean> function1) {
        Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
        Iterable iterableMo3835getSupertypes = getOwnerDescriptor().getTypeConstructor().mo3835getSupertypes();
        Intrinsics.checkNotNullExpressionValue(iterableMo3835getSupertypes, "ownerDescriptor.typeConstructor.supertypes");
        Iterable $this$flatMapTo$iv = iterableMo3835getSupertypes;
        Collection destination$iv = new LinkedHashSet();
        for (Object element$iv : $this$flatMapTo$iv) {
            KotlinType it = (KotlinType) element$iv;
            Iterable list$iv = it.getMemberScope().getFunctionNames();
            CollectionsKt.addAll(destination$iv, list$iv);
        }
        LinkedHashSet $this$computeFunctionNames_u24lambda_u2d1 = (LinkedHashSet) destination$iv;
        $this$computeFunctionNames_u24lambda_u2d1.addAll(getDeclaredMemberIndex().invoke().getMethodNames());
        $this$computeFunctionNames_u24lambda_u2d1.addAll(getDeclaredMemberIndex().invoke().getRecordComponentNames());
        $this$computeFunctionNames_u24lambda_u2d1.addAll(computeClassNames(kindFilter, function1));
        $this$computeFunctionNames_u24lambda_u2d1.addAll(getC().getComponents().getSyntheticPartsProvider().getMethodNames(getOwnerDescriptor()));
        return (LinkedHashSet) destination$iv;
    }

    @NotNull
    public final NotNullLazyValue<List<ClassConstructorDescriptor>> getConstructors$descriptors_jvm() {
        return this.constructors;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassConstructorDescriptor createDefaultRecordConstructor() {
        ClassDescriptor classDescriptor = getOwnerDescriptor();
        JavaClassConstructorDescriptor constructorDescriptor = JavaClassConstructorDescriptor.createJavaConstructor(classDescriptor, Annotations.Companion.getEMPTY(), true, getC().getComponents().getSourceElementFactory().source(this.jClass));
        Intrinsics.checkNotNullExpressionValue(constructorDescriptor, "createJavaConstructor(\n            classDescriptor, Annotations.EMPTY, /* isPrimary = */ true, c.components.sourceElementFactory.source(jClass)\n        )");
        List valueParameters = createRecordConstructorParameters(constructorDescriptor);
        constructorDescriptor.setHasSynthesizedParameterNames(false);
        constructorDescriptor.initialize(valueParameters, getConstructorVisibility(classDescriptor));
        constructorDescriptor.setHasStableParameterNames(false);
        constructorDescriptor.setReturnType(classDescriptor.getDefaultType());
        return constructorDescriptor;
    }

    private final List<ValueParameterDescriptor> createRecordConstructorParameters(ClassConstructorDescriptorImpl constructor) {
        Collection components = this.jClass.getRecordComponents();
        ArrayList result = new ArrayList(components.size());
        JavaTypeAttributes attr = JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 2, null);
        int i = 0;
        for (JavaRecordComponent component : components) {
            int index = i;
            i++;
            KotlinType parameterType = getC().getTypeResolver().transformJavaType(component.getType(), attr);
            KotlinType varargElementType = component.isVararg() ? getC().getComponents().getModule().getBuiltIns().getArrayElementType(parameterType) : null;
            result.add(new ValueParameterDescriptorImpl(constructor, null, index, Annotations.Companion.getEMPTY(), component.getName(), parameterType, false, false, false, varargElementType, getC().getComponents().getSourceElementFactory().source(component)));
        }
        return result;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected boolean isVisibleAsFunction(@NotNull JavaMethodDescriptor $this$isVisibleAsFunction) {
        Intrinsics.checkNotNullParameter($this$isVisibleAsFunction, "<this>");
        if (this.jClass.isAnnotationType()) {
            return false;
        }
        return isVisibleAsFunctionInCurrentClass($this$isVisibleAsFunction);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00e6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:? A[LOOP:1: B:16:0x0083->B:56:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean isVisibleAsFunctionInCurrentClass(final kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor r8) {
        /*
            Method dump skipped, instructions count: 279
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.isVisibleAsFunctionInCurrentClass(kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor):boolean");
    }

    private final boolean shouldBeVisibleAsOverrideOfBuiltInWithErasedValueParameters(SimpleFunctionDescriptor $this$shouldBeVisibleAsOverrideOfBuiltInWithErasedValueParameters) {
        BuiltinMethodsWithSpecialGenericSignature builtinMethodsWithSpecialGenericSignature = BuiltinMethodsWithSpecialGenericSignature.INSTANCE;
        Name name = $this$shouldBeVisibleAsOverrideOfBuiltInWithErasedValueParameters.getName();
        Intrinsics.checkNotNullExpressionValue(name, "name");
        if (!builtinMethodsWithSpecialGenericSignature.getSameAsBuiltinMethodWithErasedValueParameters(name)) {
            return false;
        }
        Name name2 = $this$shouldBeVisibleAsOverrideOfBuiltInWithErasedValueParameters.getName();
        Intrinsics.checkNotNullExpressionValue(name2, "name");
        Iterable $this$mapNotNull$iv = getFunctionsFromSupertypes(name2);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            SimpleFunctionDescriptor it = (SimpleFunctionDescriptor) element$iv$iv$iv;
            BuiltinMethodsWithSpecialGenericSignature builtinMethodsWithSpecialGenericSignature2 = BuiltinMethodsWithSpecialGenericSignature.INSTANCE;
            FunctionDescriptor overriddenBuiltinFunctionWithErasedValueParametersInJava = BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava(it);
            if (overriddenBuiltinFunctionWithErasedValueParametersInJava != null) {
                destination$iv$iv.add(overriddenBuiltinFunctionWithErasedValueParametersInJava);
            }
        }
        Iterable candidatesToOverride = (List) destination$iv$iv;
        Iterable $this$any$iv = candidatesToOverride;
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            FunctionDescriptor candidate = (FunctionDescriptor) element$iv;
            if (hasSameJvmDescriptorButDoesNotOverride($this$shouldBeVisibleAsOverrideOfBuiltInWithErasedValueParameters, candidate)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Collection<SimpleFunctionDescriptor> searchMethodsByNameWithoutBuiltinMagic(Name name) {
        Iterable $this$map$iv = getDeclaredMemberIndex().invoke().findMethodsByName(name);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            JavaMethod it = (JavaMethod) item$iv$iv;
            destination$iv$iv.add(resolveMethodToFunctionDescriptor(it));
        }
        return (List) destination$iv$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:9:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.Collection<kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor> searchMethodsInSupertypesWithoutBuiltinMagic(kotlin.reflect.jvm.internal.impl.name.Name r4) {
        /*
            r3 = this;
            r0 = r3
            r1 = r4
            java.util.Set r0 = r0.getFunctionsFromSupertypes(r1)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            r5 = r0
            r0 = 0
            r6 = r0
            r0 = r5
            r7 = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = r0
            r1.<init>()
            java.util.Collection r0 = (java.util.Collection) r0
            r8 = r0
            r0 = 0
            r9 = r0
            r0 = r7
            java.util.Iterator r0 = r0.iterator()
            r10 = r0
        L26:
            r0 = r10
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto L73
            r0 = r10
            java.lang.Object r0 = r0.next()
            r11 = r0
            r0 = r11
            kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor) r0
            r12 = r0
            r0 = 0
            r13 = r0
            r0 = r12
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor) r0
            boolean r0 = kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers.doesOverrideBuiltinWithDifferentJvmName(r0)
            if (r0 != 0) goto L5d
            kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature r0 = kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature.INSTANCE
            r0 = r12
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor) r0
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r0 = kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava(r0)
            if (r0 == 0) goto L61
        L5d:
            r0 = 1
            goto L62
        L61:
            r0 = 0
        L62:
            if (r0 != 0) goto L26
            r0 = r8
            r1 = r11
            boolean r0 = r0.add(r1)
            goto L26
        L73:
            r0 = r8
            java.util.List r0 = (java.util.List) r0
            java.util.Collection r0 = (java.util.Collection) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.searchMethodsInSupertypesWithoutBuiltinMagic(kotlin.reflect.jvm.internal.impl.name.Name):java.util.Collection");
    }

    private final boolean doesOverrideRenamedBuiltins(SimpleFunctionDescriptor $this$doesOverrideRenamedBuiltins) {
        boolean z;
        SpecialGenericSignatures.Companion companion = SpecialGenericSignatures.Companion;
        Name name = $this$doesOverrideRenamedBuiltins.getName();
        Intrinsics.checkNotNullExpressionValue(name, "name");
        Iterable $this$any$iv = companion.getBuiltinFunctionNamesByJvmName(name);
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            Name builtinName = (Name) element$iv;
            Iterable $this$filter$iv = getFunctionsFromSupertypes(builtinName);
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                SimpleFunctionDescriptor it = (SimpleFunctionDescriptor) element$iv$iv;
                if (SpecialBuiltinMembers.doesOverrideBuiltinWithDifferentJvmName(it)) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            List builtinSpecialFromSuperTypes = (List) destination$iv$iv;
            if (builtinSpecialFromSuperTypes.isEmpty()) {
                z = false;
            } else {
                SimpleFunctionDescriptor methodDescriptor = createRenamedCopy($this$doesOverrideRenamedBuiltins, builtinName);
                List $this$any$iv2 = builtinSpecialFromSuperTypes;
                if (!($this$any$iv2 instanceof Collection) || !$this$any$iv2.isEmpty()) {
                    Iterator it2 = $this$any$iv2.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            Object element$iv2 = it2.next();
                            SimpleFunctionDescriptor it3 = (SimpleFunctionDescriptor) element$iv2;
                            if (doesOverrideRenamedDescriptor(it3, methodDescriptor)) {
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
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    private final boolean doesOverrideSuspendFunction(SimpleFunctionDescriptor $this$doesOverrideSuspendFunction) {
        SimpleFunctionDescriptor suspendView = createSuspendView($this$doesOverrideSuspendFunction);
        if (suspendView == null) {
            return false;
        }
        Name name = $this$doesOverrideSuspendFunction.getName();
        Intrinsics.checkNotNullExpressionValue(name, "name");
        Iterable $this$any$iv = getFunctionsFromSupertypes(name);
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            SimpleFunctionDescriptor overriddenCandidate = (SimpleFunctionDescriptor) element$iv;
            if (overriddenCandidate.isSuspend() && doesOverride(suspendView, overriddenCandidate)) {
                return true;
            }
        }
        return false;
    }

    private final SimpleFunctionDescriptor createSuspendView(SimpleFunctionDescriptor $this$createSuspendView) {
        FqName safe;
        ValueParameterDescriptor valueParameterDescriptor;
        List<ValueParameterDescriptor> valueParameters = $this$createSuspendView.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters, "valueParameters");
        ValueParameterDescriptor it = (ValueParameterDescriptor) CollectionsKt.lastOrNull((List) valueParameters);
        if (it == null) {
            valueParameterDescriptor = null;
        } else {
            ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = it.getType().getConstructor().mo3831getDeclarationDescriptor();
            FqNameUnsafe p0 = classifierDescriptorMo3831getDeclarationDescriptor == null ? null : DescriptorUtilsKt.getFqNameUnsafe(classifierDescriptorMo3831getDeclarationDescriptor);
            if (p0 == null) {
                safe = null;
            } else {
                FqNameUnsafe fqNameUnsafe = p0.isSafe() ? p0 : null;
                safe = fqNameUnsafe == null ? null : fqNameUnsafe.toSafe();
            }
            valueParameterDescriptor = SuspendFunctionTypesKt.isContinuation(safe, getC().getComponents().getSettings().isReleaseCoroutines()) ? it : null;
        }
        ValueParameterDescriptor continuationParameter = valueParameterDescriptor;
        if (continuationParameter == null) {
            return null;
        }
        FunctionDescriptor.CopyBuilder<? extends SimpleFunctionDescriptor> copyBuilderNewCopyBuilder = $this$createSuspendView.newCopyBuilder();
        List<ValueParameterDescriptor> valueParameters2 = $this$createSuspendView.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters2, "valueParameters");
        SimpleFunctionDescriptor functionDescriptor = (SimpleFunctionDescriptor) copyBuilderNewCopyBuilder.setValueParameters(CollectionsKt.dropLast(valueParameters2, 1)).setReturnType(continuationParameter.getType().getArguments().get(0).getType()).build();
        SimpleFunctionDescriptorImpl simpleFunctionDescriptorImpl = (SimpleFunctionDescriptorImpl) functionDescriptor;
        if (simpleFunctionDescriptorImpl != null) {
            simpleFunctionDescriptorImpl.setSuspend(true);
        }
        return functionDescriptor;
    }

    private final SimpleFunctionDescriptor createRenamedCopy(SimpleFunctionDescriptor $this$createRenamedCopy, Name builtinName) {
        FunctionDescriptor.CopyBuilder $this$createRenamedCopy_u24lambda_u2d13 = $this$createRenamedCopy.newCopyBuilder();
        $this$createRenamedCopy_u24lambda_u2d13.setName(builtinName);
        $this$createRenamedCopy_u24lambda_u2d13.setSignatureChange();
        $this$createRenamedCopy_u24lambda_u2d13.setPreserveSourceElement();
        SimpleFunctionDescriptor simpleFunctionDescriptor = (SimpleFunctionDescriptor) $this$createRenamedCopy_u24lambda_u2d13.build();
        Intrinsics.checkNotNull(simpleFunctionDescriptor);
        return simpleFunctionDescriptor;
    }

    private final boolean doesOverrideRenamedDescriptor(SimpleFunctionDescriptor superDescriptor, FunctionDescriptor subDescriptor) {
        FunctionDescriptor subDescriptorToCheck = BuiltinMethodsWithDifferentJvmName.INSTANCE.isRemoveAtByIndex(superDescriptor) ? subDescriptor.getOriginal() : subDescriptor;
        Intrinsics.checkNotNullExpressionValue(subDescriptorToCheck, "if (superDescriptor.isRemoveAtByIndex) subDescriptor.original else subDescriptor");
        return doesOverride(subDescriptorToCheck, superDescriptor);
    }

    private final boolean doesOverride(CallableDescriptor $this$doesOverride, CallableDescriptor superDescriptor) {
        OverridingUtil.OverrideCompatibilityInfo.Result commonOverridabilityResult = OverridingUtil.DEFAULT.isOverridableByWithoutExternalConditions(superDescriptor, $this$doesOverride, true).getResult();
        Intrinsics.checkNotNullExpressionValue(commonOverridabilityResult, "DEFAULT.isOverridableByWithoutExternalConditions(superDescriptor, this, true).result");
        return commonOverridabilityResult == OverridingUtil.OverrideCompatibilityInfo.Result.OVERRIDABLE && !JavaIncompatibilityRulesOverridabilityCondition.Companion.doesJavaOverrideHaveIncompatibleValueParameterKinds(superDescriptor, $this$doesOverride);
    }

    private final SimpleFunctionDescriptor findGetterOverride(PropertyDescriptor $this$findGetterOverride, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        PropertyGetterDescriptor getter = $this$findGetterOverride.getGetter();
        PropertyGetterDescriptor overriddenBuiltinProperty = getter == null ? null : (PropertyGetterDescriptor) SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName(getter);
        String specialGetterName = overriddenBuiltinProperty == null ? null : ClassicBuiltinSpecialProperties.INSTANCE.getBuiltinSpecialPropertyGetterName(overriddenBuiltinProperty);
        if (specialGetterName != null && !SpecialBuiltinMembers.hasRealKotlinSuperClassWithOverrideOf(getOwnerDescriptor(), overriddenBuiltinProperty)) {
            return findGetterByName($this$findGetterOverride, specialGetterName, function1);
        }
        JvmAbi jvmAbi = JvmAbi.INSTANCE;
        String strAsString = $this$findGetterOverride.getName().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "name.asString()");
        return findGetterByName($this$findGetterOverride, JvmAbi.getterName(strAsString), function1);
    }

    private final SimpleFunctionDescriptor findGetterByName(PropertyDescriptor $this$findGetterByName, String getterName, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        SimpleFunctionDescriptor simpleFunctionDescriptor;
        Name nameIdentifier = Name.identifier(getterName);
        Intrinsics.checkNotNullExpressionValue(nameIdentifier, "identifier(getterName)");
        Iterable $this$firstNotNullResult$iv = function1.invoke(nameIdentifier);
        for (Object element$iv : $this$firstNotNullResult$iv) {
            SimpleFunctionDescriptor descriptor = (SimpleFunctionDescriptor) element$iv;
            if (descriptor.getValueParameters().size() != 0) {
                simpleFunctionDescriptor = null;
            } else {
                KotlinTypeChecker kotlinTypeChecker = KotlinTypeChecker.DEFAULT;
                KotlinType returnType = descriptor.getReturnType();
                simpleFunctionDescriptor = returnType == null ? false : kotlinTypeChecker.isSubtypeOf(returnType, $this$findGetterByName.getType()) ? descriptor : null;
            }
            SimpleFunctionDescriptor simpleFunctionDescriptor2 = simpleFunctionDescriptor;
            if (simpleFunctionDescriptor2 != null) {
                return simpleFunctionDescriptor2;
            }
        }
        return null;
    }

    private final SimpleFunctionDescriptor findSetterOverride(PropertyDescriptor $this$findSetterOverride, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        KotlinType returnType;
        SimpleFunctionDescriptor simpleFunctionDescriptor;
        JvmAbi jvmAbi = JvmAbi.INSTANCE;
        String strAsString = $this$findSetterOverride.getName().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "name.asString()");
        Name nameIdentifier = Name.identifier(JvmAbi.setterName(strAsString));
        Intrinsics.checkNotNullExpressionValue(nameIdentifier, "identifier(JvmAbi.setterName(name.asString()))");
        Iterable $this$firstNotNullResult$iv = function1.invoke(nameIdentifier);
        for (Object element$iv : $this$firstNotNullResult$iv) {
            SimpleFunctionDescriptor descriptor = (SimpleFunctionDescriptor) element$iv;
            if (descriptor.getValueParameters().size() == 1 && (returnType = descriptor.getReturnType()) != null && KotlinBuiltIns.isUnit(returnType)) {
                KotlinTypeChecker kotlinTypeChecker = KotlinTypeChecker.DEFAULT;
                List<ValueParameterDescriptor> valueParameters = descriptor.getValueParameters();
                Intrinsics.checkNotNullExpressionValue(valueParameters, "descriptor.valueParameters");
                simpleFunctionDescriptor = kotlinTypeChecker.equalTypes(((ValueParameterDescriptor) CollectionsKt.single((List) valueParameters)).getType(), $this$findSetterOverride.getType()) ? descriptor : null;
            } else {
                simpleFunctionDescriptor = null;
            }
            SimpleFunctionDescriptor simpleFunctionDescriptor2 = simpleFunctionDescriptor;
            if (simpleFunctionDescriptor2 != null) {
                return simpleFunctionDescriptor2;
            }
        }
        return null;
    }

    private final boolean doesClassOverridesProperty(PropertyDescriptor property, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        if (JavaDescriptorUtilKt.isJavaField(property)) {
            return false;
        }
        SimpleFunctionDescriptor getter = findGetterOverride(property, function1);
        SimpleFunctionDescriptor setter = findSetterOverride(property, function1);
        if (getter == null) {
            return false;
        }
        if (property.isVar()) {
            return setter != null && setter.getModality() == getter.getModality();
        }
        return true;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected void computeNonDeclaredFunctions(@NotNull Collection<SimpleFunctionDescriptor> result, @NotNull Name name) {
        boolean z;
        Intrinsics.checkNotNullParameter(result, "result");
        Intrinsics.checkNotNullParameter(name, "name");
        Iterable functionsFromSupertypes = getFunctionsFromSupertypes(name);
        if (!SpecialGenericSignatures.Companion.getSameAsRenamedInJvmBuiltin(name) && !BuiltinMethodsWithSpecialGenericSignature.INSTANCE.getSameAsBuiltinMethodWithErasedValueParameters(name)) {
            Iterable $this$none$iv = functionsFromSupertypes;
            if (!($this$none$iv instanceof Collection) || !((Collection) $this$none$iv).isEmpty()) {
                Iterator it = $this$none$iv.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Object element$iv = it.next();
                        FunctionDescriptor p0 = (FunctionDescriptor) element$iv;
                        if (p0.isSuspend()) {
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
                Iterable $this$filter$iv = functionsFromSupertypes;
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv : $this$filter$iv) {
                    SimpleFunctionDescriptor it2 = (SimpleFunctionDescriptor) element$iv$iv;
                    if (isVisibleAsFunctionInCurrentClass(it2)) {
                        destination$iv$iv.add(element$iv$iv);
                    }
                }
                addFunctionFromSupertypes(result, name, (List) destination$iv$iv, false);
                return;
            }
        }
        SmartSet specialBuiltinsFromSuperTypes = SmartSet.Companion.create();
        Collection mergedFunctionFromSuperTypes = DescriptorResolverUtils.resolveOverridesForNonStaticMembers(name, (Collection) functionsFromSupertypes, CollectionsKt.emptyList(), getOwnerDescriptor(), ErrorReporter.DO_NOTHING, getC().getComponents().getKotlinTypeChecker().getOverridingUtil());
        Intrinsics.checkNotNullExpressionValue(mergedFunctionFromSuperTypes, "resolveOverridesForNonStaticMembers(\n            name, functionsFromSupertypes, emptyList(), ownerDescriptor, ErrorReporter.DO_NOTHING,\n            c.components.kotlinTypeChecker.overridingUtil\n        )");
        addOverriddenSpecialMethods(name, result, mergedFunctionFromSuperTypes, result, new AnonymousClass3(this));
        addOverriddenSpecialMethods(name, result, mergedFunctionFromSuperTypes, specialBuiltinsFromSuperTypes, new AnonymousClass4(this));
        Iterable $this$filter$iv2 = functionsFromSupertypes;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv2) {
            SimpleFunctionDescriptor it3 = (SimpleFunctionDescriptor) element$iv$iv2;
            if (isVisibleAsFunctionInCurrentClass(it3)) {
                destination$iv$iv2.add(element$iv$iv2);
            }
        }
        List visibleFunctionsFromSupertypes = CollectionsKt.plus(destination$iv$iv2, (Iterable) specialBuiltinsFromSuperTypes);
        addFunctionFromSupertypes(result, name, visibleFunctionsFromSupertypes, true);
    }

    /* compiled from: LazyJavaClassMemberScope.kt */
    /* renamed from: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope$computeNonDeclaredFunctions$3, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/lazy/descriptors/LazyJavaClassMemberScope$computeNonDeclaredFunctions$3.class */
    /* synthetic */ class AnonymousClass3 extends FunctionReference implements Function1<Name, Collection<? extends SimpleFunctionDescriptor>> {
        AnonymousClass3(LazyJavaClassMemberScope lazyJavaClassMemberScope) {
            super(1, lazyJavaClassMemberScope);
        }

        @Override // kotlin.jvm.functions.Function1
        @NotNull
        public final Collection<SimpleFunctionDescriptor> invoke(@NotNull Name p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return ((LazyJavaClassMemberScope) this.receiver).searchMethodsByNameWithoutBuiltinMagic(p0);
        }

        @Override // kotlin.jvm.internal.CallableReference
        @NotNull
        public final String getSignature() {
            return "searchMethodsByNameWithoutBuiltinMagic(Lorg/jetbrains/kotlin/name/Name;)Ljava/util/Collection;";
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        @NotNull
        public final String getName() {
            return "searchMethodsByNameWithoutBuiltinMagic";
        }

        @Override // kotlin.jvm.internal.CallableReference
        @NotNull
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(LazyJavaClassMemberScope.class);
        }
    }

    /* compiled from: LazyJavaClassMemberScope.kt */
    /* renamed from: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope$computeNonDeclaredFunctions$4, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/lazy/descriptors/LazyJavaClassMemberScope$computeNonDeclaredFunctions$4.class */
    /* synthetic */ class AnonymousClass4 extends FunctionReference implements Function1<Name, Collection<? extends SimpleFunctionDescriptor>> {
        AnonymousClass4(LazyJavaClassMemberScope lazyJavaClassMemberScope) {
            super(1, lazyJavaClassMemberScope);
        }

        @Override // kotlin.jvm.functions.Function1
        @NotNull
        public final Collection<SimpleFunctionDescriptor> invoke(@NotNull Name p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return ((LazyJavaClassMemberScope) this.receiver).searchMethodsInSupertypesWithoutBuiltinMagic(p0);
        }

        @Override // kotlin.jvm.internal.CallableReference
        @NotNull
        public final String getSignature() {
            return "searchMethodsInSupertypesWithoutBuiltinMagic(Lorg/jetbrains/kotlin/name/Name;)Ljava/util/Collection;";
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        @NotNull
        public final String getName() {
            return "searchMethodsInSupertypesWithoutBuiltinMagic";
        }

        @Override // kotlin.jvm.internal.CallableReference
        @NotNull
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(LazyJavaClassMemberScope.class);
        }
    }

    private final void addFunctionFromSupertypes(Collection<SimpleFunctionDescriptor> collection, Name name, Collection<? extends SimpleFunctionDescriptor> collection2, boolean isSpecialBuiltinName) {
        SimpleFunctionDescriptor simpleFunctionDescriptorCreateHiddenCopyIfBuiltinAlreadyAccidentallyOverridden;
        Collection additionalOverrides = DescriptorResolverUtils.resolveOverridesForNonStaticMembers(name, collection2, collection, getOwnerDescriptor(), getC().getComponents().getErrorReporter(), getC().getComponents().getKotlinTypeChecker().getOverridingUtil());
        Intrinsics.checkNotNullExpressionValue(additionalOverrides, "resolveOverridesForNonStaticMembers(\n            name, functionsFromSupertypes, result, ownerDescriptor, c.components.errorReporter,\n            c.components.kotlinTypeChecker.overridingUtil\n        )");
        if (!isSpecialBuiltinName) {
            collection.addAll(additionalOverrides);
            return;
        }
        List allDescriptors = CollectionsKt.plus((Collection) collection, (Iterable) additionalOverrides);
        Collection $this$map$iv = additionalOverrides;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            SimpleFunctionDescriptor resolvedOverride = (SimpleFunctionDescriptor) item$iv$iv;
            SimpleFunctionDescriptor overriddenBuiltin = (SimpleFunctionDescriptor) SpecialBuiltinMembers.getOverriddenSpecialBuiltin(resolvedOverride);
            if (overriddenBuiltin == null) {
                Intrinsics.checkNotNullExpressionValue(resolvedOverride, "resolvedOverride");
                simpleFunctionDescriptorCreateHiddenCopyIfBuiltinAlreadyAccidentallyOverridden = resolvedOverride;
            } else {
                Intrinsics.checkNotNullExpressionValue(resolvedOverride, "resolvedOverride");
                simpleFunctionDescriptorCreateHiddenCopyIfBuiltinAlreadyAccidentallyOverridden = createHiddenCopyIfBuiltinAlreadyAccidentallyOverridden(resolvedOverride, overriddenBuiltin, allDescriptors);
            }
            destination$iv$iv.add(simpleFunctionDescriptorCreateHiddenCopyIfBuiltinAlreadyAccidentallyOverridden);
        }
        collection.addAll((List) destination$iv$iv);
    }

    private final void addOverriddenSpecialMethods(Name name, Collection<? extends SimpleFunctionDescriptor> collection, Collection<? extends SimpleFunctionDescriptor> collection2, Collection<SimpleFunctionDescriptor> collection3, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        for (SimpleFunctionDescriptor descriptor : collection2) {
            kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(collection3, obtainOverrideForBuiltinWithDifferentJvmName(descriptor, function1, name, collection));
            kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(collection3, obtainOverrideForBuiltInWithErasedValueParametersInJava(descriptor, function1, collection));
            kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(collection3, obtainOverrideForSuspend(descriptor, function1));
        }
    }

    private final SimpleFunctionDescriptor obtainOverrideForBuiltInWithErasedValueParametersInJava(SimpleFunctionDescriptor descriptor, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1, Collection<? extends SimpleFunctionDescriptor> collection) {
        SimpleFunctionDescriptor p0;
        BuiltinMethodsWithSpecialGenericSignature builtinMethodsWithSpecialGenericSignature = BuiltinMethodsWithSpecialGenericSignature.INSTANCE;
        FunctionDescriptor overriddenBuiltin = BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava(descriptor);
        if (overriddenBuiltin != null && (p0 = createOverrideForBuiltinFunctionWithErasedParameterIfNeeded(overriddenBuiltin, function1)) != null) {
            SimpleFunctionDescriptor simpleFunctionDescriptor = isVisibleAsFunctionInCurrentClass(p0) ? p0 : null;
            if (simpleFunctionDescriptor != null) {
                return createHiddenCopyIfBuiltinAlreadyAccidentallyOverridden(simpleFunctionDescriptor, overriddenBuiltin, collection);
            }
            return null;
        }
        return null;
    }

    private final SimpleFunctionDescriptor obtainOverrideForBuiltinWithDifferentJvmName(SimpleFunctionDescriptor descriptor, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1, Name name, Collection<? extends SimpleFunctionDescriptor> collection) {
        SimpleFunctionDescriptor overriddenBuiltin = (SimpleFunctionDescriptor) SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName(descriptor);
        if (overriddenBuiltin == null) {
            return null;
        }
        String nameInJava = SpecialBuiltinMembers.getJvmMethodNameIfSpecial(overriddenBuiltin);
        Intrinsics.checkNotNull(nameInJava);
        Name nameIdentifier = Name.identifier(nameInJava);
        Intrinsics.checkNotNullExpressionValue(nameIdentifier, "identifier(nameInJava)");
        for (SimpleFunctionDescriptor method : function1.invoke(nameIdentifier)) {
            SimpleFunctionDescriptor renamedCopy = createRenamedCopy(method, name);
            if (doesOverrideRenamedDescriptor(overriddenBuiltin, renamedCopy)) {
                return createHiddenCopyIfBuiltinAlreadyAccidentallyOverridden(renamedCopy, overriddenBuiltin, collection);
            }
        }
        return null;
    }

    private final SimpleFunctionDescriptor obtainOverrideForSuspend(SimpleFunctionDescriptor descriptor, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        if (!descriptor.isSuspend()) {
            return null;
        }
        Name name = descriptor.getName();
        Intrinsics.checkNotNullExpressionValue(name, "descriptor.name");
        Iterable $this$firstNotNullResult$iv = function1.invoke(name);
        for (Object element$iv : $this$firstNotNullResult$iv) {
            SimpleFunctionDescriptor overrideCandidate = (SimpleFunctionDescriptor) element$iv;
            SimpleFunctionDescriptor suspendView = createSuspendView(overrideCandidate);
            SimpleFunctionDescriptor simpleFunctionDescriptor = (suspendView != null && doesOverride(suspendView, descriptor)) ? suspendView : null;
            SimpleFunctionDescriptor simpleFunctionDescriptor2 = simpleFunctionDescriptor;
            if (simpleFunctionDescriptor2 != null) {
                return simpleFunctionDescriptor2;
            }
        }
        return null;
    }

    private final SimpleFunctionDescriptor createHiddenCopyIfBuiltinAlreadyAccidentallyOverridden(SimpleFunctionDescriptor $this$createHiddenCopyIfBuiltinAlreadyAccidentallyOverridden, CallableDescriptor specialBuiltin, Collection<? extends SimpleFunctionDescriptor> collection) {
        boolean z;
        Collection<? extends SimpleFunctionDescriptor> $this$none$iv = collection;
        if (!($this$none$iv instanceof Collection) || !$this$none$iv.isEmpty()) {
            Iterator it = $this$none$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    SimpleFunctionDescriptor it2 = (SimpleFunctionDescriptor) element$iv;
                    if (!Intrinsics.areEqual($this$createHiddenCopyIfBuiltinAlreadyAccidentallyOverridden, it2) && it2.getInitialSignatureDescriptor() == null && doesOverride(it2, specialBuiltin)) {
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
            return $this$createHiddenCopyIfBuiltinAlreadyAccidentallyOverridden;
        }
        SimpleFunctionDescriptor simpleFunctionDescriptor = (SimpleFunctionDescriptor) $this$createHiddenCopyIfBuiltinAlreadyAccidentallyOverridden.newCopyBuilder().setHiddenToOvercomeSignatureClash().build();
        Intrinsics.checkNotNull(simpleFunctionDescriptor);
        return simpleFunctionDescriptor;
    }

    private final SimpleFunctionDescriptor createOverrideForBuiltinFunctionWithErasedParameterIfNeeded(FunctionDescriptor overridden, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        Object obj;
        Name name = overridden.getName();
        Intrinsics.checkNotNullExpressionValue(name, "overridden.name");
        Iterable $this$firstOrNull$iv = function1.invoke(name);
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            Object element$iv = it.next();
            if (hasSameJvmDescriptorButDoesNotOverride((SimpleFunctionDescriptor) element$iv, overridden)) {
                obj = element$iv;
                break;
            }
        }
        SimpleFunctionDescriptor override = (SimpleFunctionDescriptor) obj;
        if (override == null) {
            return null;
        }
        FunctionDescriptor.CopyBuilder $this$createOverrideForBuiltinFunctionWithErasedParameterIfNeeded_u24lambda_u2d29_u24lambda_u2d28 = override.newCopyBuilder();
        Iterable valueParameters = overridden.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters, "overridden.valueParameters");
        Iterable $this$map$iv = valueParameters;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            ValueParameterDescriptor it2 = (ValueParameterDescriptor) item$iv$iv;
            KotlinType type = it2.getType();
            Intrinsics.checkNotNullExpressionValue(type, "it.type");
            destination$iv$iv.add(new ValueParameterData(type, it2.declaresDefaultValue()));
        }
        List<ValueParameterDescriptor> valueParameters2 = override.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters2, "override.valueParameters");
        $this$createOverrideForBuiltinFunctionWithErasedParameterIfNeeded_u24lambda_u2d29_u24lambda_u2d28.setValueParameters(UtilKt.copyValueParameters((List) destination$iv$iv, valueParameters2, overridden));
        $this$createOverrideForBuiltinFunctionWithErasedParameterIfNeeded_u24lambda_u2d29_u24lambda_u2d28.setSignatureChange();
        $this$createOverrideForBuiltinFunctionWithErasedParameterIfNeeded_u24lambda_u2d29_u24lambda_u2d28.setPreserveSourceElement();
        return (SimpleFunctionDescriptor) $this$createOverrideForBuiltinFunctionWithErasedParameterIfNeeded_u24lambda_u2d29_u24lambda_u2d28.build();
    }

    private final Set<SimpleFunctionDescriptor> getFunctionsFromSupertypes(Name name) {
        Iterable $this$flatMapTo$iv = computeSupertypes();
        Collection destination$iv = new LinkedHashSet();
        for (Object element$iv : $this$flatMapTo$iv) {
            KotlinType it = (KotlinType) element$iv;
            Iterable list$iv = it.getMemberScope().getContributedFunctions(name, NoLookupLocation.WHEN_GET_SUPER_MEMBERS);
            CollectionsKt.addAll(destination$iv, list$iv);
        }
        return (Set) destination$iv;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected void computeImplicitlyDeclaredFunctions(@NotNull Collection<SimpleFunctionDescriptor> result, @NotNull Name name) {
        boolean z;
        Intrinsics.checkNotNullParameter(result, "result");
        Intrinsics.checkNotNullParameter(name, "name");
        if (this.jClass.isRecord() && getDeclaredMemberIndex().invoke().findRecordComponentByName(name) != null) {
            Collection<SimpleFunctionDescriptor> $this$none$iv = result;
            if (!$this$none$iv.isEmpty()) {
                Iterator it = $this$none$iv.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Object element$iv = it.next();
                        SimpleFunctionDescriptor it2 = (SimpleFunctionDescriptor) element$iv;
                        if (it2.getValueParameters().isEmpty()) {
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
                JavaRecordComponent javaRecordComponentFindRecordComponentByName = getDeclaredMemberIndex().invoke().findRecordComponentByName(name);
                Intrinsics.checkNotNull(javaRecordComponentFindRecordComponentByName);
                result.add(resolveRecordComponentToFunctionDescriptor(javaRecordComponentFindRecordComponentByName));
            }
        }
        getC().getComponents().getSyntheticPartsProvider().generateMethods(getOwnerDescriptor(), name, result);
    }

    private final JavaMethodDescriptor resolveRecordComponentToFunctionDescriptor(JavaRecordComponent recordComponent) {
        Annotations annotations = LazyJavaAnnotationsKt.resolveAnnotations(getC(), recordComponent);
        JavaMethodDescriptor functionDescriptorImpl = JavaMethodDescriptor.createJavaMethod(getOwnerDescriptor(), annotations, recordComponent.getName(), getC().getComponents().getSourceElementFactory().source(recordComponent), true);
        Intrinsics.checkNotNullExpressionValue(functionDescriptorImpl, "createJavaMethod(\n            ownerDescriptor, annotations, recordComponent.name, c.components.sourceElementFactory.source(recordComponent), true\n        )");
        JavaTypeAttributes returnTypeAttrs = JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 2, null);
        KotlinType returnType = getC().getTypeResolver().transformJavaType(recordComponent.getType(), returnTypeAttrs);
        functionDescriptorImpl.initialize(null, getDispatchReceiverParameter(), CollectionsKt.emptyList(), CollectionsKt.emptyList(), returnType, Modality.Companion.convertFromFlags(false, false, true), DescriptorVisibilities.PUBLIC, null);
        functionDescriptorImpl.setParameterNamesStatus(false, false);
        getC().getComponents().getJavaResolverCache().recordMethod(recordComponent, functionDescriptorImpl);
        return functionDescriptorImpl;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected void computeNonDeclaredProperties(@NotNull Name name, @NotNull Collection<PropertyDescriptor> result) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(result, "result");
        if (this.jClass.isAnnotationType()) {
            computeAnnotationProperties(name, result);
        }
        Set propertiesFromSupertypes = getPropertiesFromSupertypes(name);
        if (propertiesFromSupertypes.isEmpty()) {
            return;
        }
        SmartSet handledProperties = SmartSet.Companion.create();
        SmartSet propertiesOverridesFromSuperTypes = SmartSet.Companion.create();
        addPropertyOverrideByMethod(propertiesFromSupertypes, result, handledProperties, new Function1<Name, Collection<? extends SimpleFunctionDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.computeNonDeclaredProperties.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final Collection<SimpleFunctionDescriptor> invoke(@NotNull Name it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return LazyJavaClassMemberScope.this.searchMethodsByNameWithoutBuiltinMagic(it);
            }
        });
        addPropertyOverrideByMethod(SetsKt.minus(propertiesFromSupertypes, (Iterable) handledProperties), propertiesOverridesFromSuperTypes, null, new Function1<Name, Collection<? extends SimpleFunctionDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.computeNonDeclaredProperties.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final Collection<SimpleFunctionDescriptor> invoke(@NotNull Name it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return LazyJavaClassMemberScope.this.searchMethodsInSupertypesWithoutBuiltinMagic(it);
            }
        });
        Collection<? extends PropertyDescriptor> collectionResolveOverridesForNonStaticMembers = DescriptorResolverUtils.resolveOverridesForNonStaticMembers(name, SetsKt.plus(propertiesFromSupertypes, (Iterable) propertiesOverridesFromSuperTypes), result, getOwnerDescriptor(), getC().getComponents().getErrorReporter(), getC().getComponents().getKotlinTypeChecker().getOverridingUtil());
        Intrinsics.checkNotNullExpressionValue(collectionResolveOverridesForNonStaticMembers, "resolveOverridesForNonStaticMembers(\n                name,\n                propertiesFromSupertypes + propertiesOverridesFromSuperTypes,\n                result,\n                ownerDescriptor,\n                c.components.errorReporter,\n                c.components.kotlinTypeChecker.overridingUtil\n            )");
        result.addAll(collectionResolveOverridesForNonStaticMembers);
    }

    private final void addPropertyOverrideByMethod(Set<? extends PropertyDescriptor> set, Collection<PropertyDescriptor> collection, Set<PropertyDescriptor> set2, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        for (PropertyDescriptor property : set) {
            JavaPropertyDescriptor newProperty = createPropertyDescriptorByMethods(property, function1);
            if (newProperty != null) {
                collection.add(newProperty);
                if (set2 != null) {
                    set2.add(property);
                    return;
                }
                return;
            }
        }
    }

    private final void computeAnnotationProperties(Name name, Collection<PropertyDescriptor> collection) {
        JavaMethod method = (JavaMethod) CollectionsKt.singleOrNull(getDeclaredMemberIndex().invoke().findMethodsByName(name));
        if (method == null) {
            return;
        }
        collection.add(createPropertyDescriptorWithDefaultGetter$default(this, method, null, Modality.FINAL, 2, null));
    }

    static /* synthetic */ JavaPropertyDescriptor createPropertyDescriptorWithDefaultGetter$default(LazyJavaClassMemberScope lazyJavaClassMemberScope, JavaMethod javaMethod, KotlinType kotlinType, Modality modality, int i, Object obj) {
        if ((i & 2) != 0) {
            kotlinType = null;
        }
        return lazyJavaClassMemberScope.createPropertyDescriptorWithDefaultGetter(javaMethod, kotlinType, modality);
    }

    private final JavaPropertyDescriptor createPropertyDescriptorWithDefaultGetter(JavaMethod method, KotlinType givenType, Modality modality) {
        Annotations annotations = LazyJavaAnnotationsKt.resolveAnnotations(getC(), method);
        JavaPropertyDescriptor propertyDescriptor = JavaPropertyDescriptor.create(getOwnerDescriptor(), annotations, modality, UtilsKt.toDescriptorVisibility(method.getVisibility()), false, method.getName(), getC().getComponents().getSourceElementFactory().source(method), false);
        Intrinsics.checkNotNullExpressionValue(propertyDescriptor, "create(\n            ownerDescriptor, annotations, modality, method.visibility.toDescriptorVisibility(),\n            /* isVar = */ false, method.name, c.components.sourceElementFactory.source(method),\n            /* isStaticFinal = */ false\n        )");
        PropertyGetterDescriptorImpl getter = DescriptorFactory.createDefaultGetter(propertyDescriptor, Annotations.Companion.getEMPTY());
        Intrinsics.checkNotNullExpressionValue(getter, "createDefaultGetter(propertyDescriptor, Annotations.EMPTY)");
        propertyDescriptor.initialize(getter, null);
        KotlinType returnType = givenType == null ? computeMethodReturnType(method, ContextKt.childForMethod$default(getC(), propertyDescriptor, method, 0, 4, null)) : givenType;
        propertyDescriptor.setType(returnType, CollectionsKt.emptyList(), getDispatchReceiverParameter(), null);
        getter.initialize(returnType);
        return propertyDescriptor;
    }

    private final JavaPropertyDescriptor createPropertyDescriptorByMethods(PropertyDescriptor overriddenProperty, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        SimpleFunctionDescriptor simpleFunctionDescriptor;
        boolean z;
        PropertySetterDescriptorImpl propertySetterDescriptorImpl;
        if (!doesClassOverridesProperty(overriddenProperty, function1)) {
            return null;
        }
        SimpleFunctionDescriptor getterMethod = findGetterOverride(overriddenProperty, function1);
        Intrinsics.checkNotNull(getterMethod);
        if (overriddenProperty.isVar()) {
            SimpleFunctionDescriptor simpleFunctionDescriptorFindSetterOverride = findSetterOverride(overriddenProperty, function1);
            Intrinsics.checkNotNull(simpleFunctionDescriptorFindSetterOverride);
            simpleFunctionDescriptor = simpleFunctionDescriptorFindSetterOverride;
        } else {
            simpleFunctionDescriptor = null;
        }
        SimpleFunctionDescriptor setterMethod = simpleFunctionDescriptor;
        if (setterMethod == null) {
            z = true;
        } else {
            z = setterMethod.getModality() == getterMethod.getModality();
        }
        boolean z2 = z;
        if (_Assertions.ENABLED && !z2) {
            throw new AssertionError("Different accessors modalities when creating overrides for " + overriddenProperty + " in " + getOwnerDescriptor() + "for getter is " + getterMethod.getModality() + ", but for setter is " + (setterMethod == null ? null : setterMethod.getModality()));
        }
        JavaForKotlinOverridePropertyDescriptor propertyDescriptor = new JavaForKotlinOverridePropertyDescriptor(getOwnerDescriptor(), getterMethod, setterMethod, overriddenProperty);
        KotlinType returnType = getterMethod.getReturnType();
        Intrinsics.checkNotNull(returnType);
        propertyDescriptor.setType(returnType, CollectionsKt.emptyList(), getDispatchReceiverParameter(), null);
        PropertyGetterDescriptorImpl $this$createPropertyDescriptorByMethods_u24lambda_u2d34 = DescriptorFactory.createGetter(propertyDescriptor, getterMethod.getAnnotations(), false, false, false, getterMethod.getSource());
        $this$createPropertyDescriptorByMethods_u24lambda_u2d34.setInitialSignatureDescriptor(getterMethod);
        $this$createPropertyDescriptorByMethods_u24lambda_u2d34.initialize(propertyDescriptor.getType());
        Intrinsics.checkNotNullExpressionValue($this$createPropertyDescriptorByMethods_u24lambda_u2d34, "createGetter(\n            propertyDescriptor, getterMethod.annotations, /* isDefault = */false,\n            /* isExternal = */ false, /* isInline = */ false, getterMethod.source\n        ).apply {\n            initialSignatureDescriptor = getterMethod\n            initialize(propertyDescriptor.type)\n        }");
        if (setterMethod != null) {
            List<ValueParameterDescriptor> valueParameters = setterMethod.getValueParameters();
            Intrinsics.checkNotNullExpressionValue(valueParameters, "setterMethod.valueParameters");
            ValueParameterDescriptor parameter = (ValueParameterDescriptor) CollectionsKt.firstOrNull((List) valueParameters);
            if (parameter == null) {
                throw new AssertionError(Intrinsics.stringPlus("No parameter found for ", setterMethod));
            }
            PropertySetterDescriptorImpl $this$createPropertyDescriptorByMethods_u24lambda_u2d35 = DescriptorFactory.createSetter(propertyDescriptor, setterMethod.getAnnotations(), parameter.getAnnotations(), false, false, false, setterMethod.getVisibility(), setterMethod.getSource());
            $this$createPropertyDescriptorByMethods_u24lambda_u2d35.setInitialSignatureDescriptor(setterMethod);
            propertySetterDescriptorImpl = $this$createPropertyDescriptorByMethods_u24lambda_u2d35;
        } else {
            propertySetterDescriptorImpl = null;
        }
        PropertySetterDescriptorImpl setter = propertySetterDescriptorImpl;
        propertyDescriptor.initialize($this$createPropertyDescriptorByMethods_u24lambda_u2d34, setter);
        return propertyDescriptor;
    }

    private final Set<PropertyDescriptor> getPropertiesFromSupertypes(Name name) {
        Iterable $this$flatMap$iv = computeSupertypes();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$flatMap$iv) {
            KotlinType it = (KotlinType) element$iv$iv;
            Iterable $this$map$iv = it.getMemberScope().getContributedVariables(name, NoLookupLocation.WHEN_GET_SUPER_MEMBERS);
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                PropertyDescriptor p = (PropertyDescriptor) item$iv$iv;
                destination$iv$iv2.add(p);
            }
            Iterable list$iv$iv = (List) destination$iv$iv2;
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
        }
        return CollectionsKt.toSet((List) destination$iv$iv);
    }

    private final Collection<KotlinType> computeSupertypes() {
        if (!this.skipRefinement) {
            return getC().getComponents().getKotlinTypeChecker().getKotlinTypeRefiner().refineSupertypes(getOwnerDescriptor());
        }
        Collection<KotlinType> collectionMo3835getSupertypes = getOwnerDescriptor().getTypeConstructor().mo3835getSupertypes();
        Intrinsics.checkNotNullExpressionValue(collectionMo3835getSupertypes, "ownerDescriptor.typeConstructor.supertypes");
        return collectionMo3835getSupertypes;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    @NotNull
    protected LazyJavaScope.MethodSignatureData resolveMethodSignature(@NotNull JavaMethod method, @NotNull List<? extends TypeParameterDescriptor> methodTypeParameters, @NotNull KotlinType returnType, @NotNull List<? extends ValueParameterDescriptor> valueParameters) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(methodTypeParameters, "methodTypeParameters");
        Intrinsics.checkNotNullParameter(returnType, "returnType");
        Intrinsics.checkNotNullParameter(valueParameters, "valueParameters");
        SignaturePropagator.PropagatedSignature propagated = getC().getComponents().getSignaturePropagator().resolvePropagatedSignature(method, getOwnerDescriptor(), returnType, null, valueParameters, methodTypeParameters);
        Intrinsics.checkNotNullExpressionValue(propagated, "c.components.signaturePropagator.resolvePropagatedSignature(\n            method, ownerDescriptor, returnType, null, valueParameters, methodTypeParameters\n        )");
        KotlinType returnType2 = propagated.getReturnType();
        Intrinsics.checkNotNullExpressionValue(returnType2, "propagated.returnType");
        KotlinType receiverType = propagated.getReceiverType();
        List<ValueParameterDescriptor> valueParameters2 = propagated.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters2, "propagated.valueParameters");
        List<TypeParameterDescriptor> typeParameters = propagated.getTypeParameters();
        Intrinsics.checkNotNullExpressionValue(typeParameters, "propagated.typeParameters");
        boolean zHasStableParameterNames = propagated.hasStableParameterNames();
        List<String> errors = propagated.getErrors();
        Intrinsics.checkNotNullExpressionValue(errors, "propagated.errors");
        return new LazyJavaScope.MethodSignatureData(returnType2, receiverType, valueParameters2, typeParameters, zHasStableParameterNames, errors);
    }

    private final boolean hasSameJvmDescriptorButDoesNotOverride(SimpleFunctionDescriptor $this$hasSameJvmDescriptorButDoesNotOverride, FunctionDescriptor builtinWithErasedParameters) {
        String strComputeJvmDescriptor$default = MethodSignatureMappingKt.computeJvmDescriptor$default($this$hasSameJvmDescriptorButDoesNotOverride, false, false, 2, null);
        FunctionDescriptor original = builtinWithErasedParameters.getOriginal();
        Intrinsics.checkNotNullExpressionValue(original, "builtinWithErasedParameters.original");
        return Intrinsics.areEqual(strComputeJvmDescriptor$default, MethodSignatureMappingKt.computeJvmDescriptor$default(original, false, false, 2, null)) && !doesOverride($this$hasSameJvmDescriptorButDoesNotOverride, builtinWithErasedParameters);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JavaClassConstructorDescriptor resolveConstructor(JavaConstructor constructor) {
        ClassDescriptor classDescriptor = getOwnerDescriptor();
        JavaClassConstructorDescriptor constructorDescriptor = JavaClassConstructorDescriptor.createJavaConstructor(classDescriptor, LazyJavaAnnotationsKt.resolveAnnotations(getC(), constructor), false, getC().getComponents().getSourceElementFactory().source(constructor));
        Intrinsics.checkNotNullExpressionValue(constructorDescriptor, "createJavaConstructor(\n            classDescriptor,\n            c.resolveAnnotations(constructor), /* isPrimary = */\n            false,\n            c.components.sourceElementFactory.source(constructor)\n        )");
        LazyJavaResolverContext c = ContextKt.childForMethod(getC(), constructorDescriptor, constructor, classDescriptor.getDeclaredTypeParameters().size());
        LazyJavaScope.ResolvedValueParameters valueParameters = resolveValueParameters(c, constructorDescriptor, constructor.getValueParameters());
        List<TypeParameterDescriptor> declaredTypeParameters = classDescriptor.getDeclaredTypeParameters();
        Intrinsics.checkNotNullExpressionValue(declaredTypeParameters, "classDescriptor.declaredTypeParameters");
        List<TypeParameterDescriptor> list = declaredTypeParameters;
        Iterable $this$map$iv = constructor.getTypeParameters();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            JavaTypeParameter p = (JavaTypeParameter) item$iv$iv;
            TypeParameterDescriptor typeParameterDescriptorResolveTypeParameter = c.getTypeParameterResolver().resolveTypeParameter(p);
            Intrinsics.checkNotNull(typeParameterDescriptorResolveTypeParameter);
            destination$iv$iv.add(typeParameterDescriptorResolveTypeParameter);
        }
        List constructorTypeParameters = CollectionsKt.plus((Collection) list, destination$iv$iv);
        constructorDescriptor.initialize(valueParameters.getDescriptors(), UtilsKt.toDescriptorVisibility(constructor.getVisibility()), constructorTypeParameters);
        constructorDescriptor.setHasStableParameterNames(false);
        constructorDescriptor.setHasSynthesizedParameterNames(valueParameters.getHasSynthesizedNames());
        constructorDescriptor.setReturnType(classDescriptor.getDefaultType());
        c.getComponents().getJavaResolverCache().recordConstructor(constructor, constructorDescriptor);
        return constructorDescriptor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassConstructorDescriptor createDefaultConstructor() {
        boolean isAnnotation = this.jClass.isAnnotationType();
        if ((this.jClass.isInterface() || !this.jClass.hasDefaultConstructor()) && !isAnnotation) {
            return null;
        }
        ClassDescriptor classDescriptor = getOwnerDescriptor();
        JavaClassConstructorDescriptor constructorDescriptor = JavaClassConstructorDescriptor.createJavaConstructor(classDescriptor, Annotations.Companion.getEMPTY(), true, getC().getComponents().getSourceElementFactory().source(this.jClass));
        Intrinsics.checkNotNullExpressionValue(constructorDescriptor, "createJavaConstructor(\n            classDescriptor, Annotations.EMPTY, /* isPrimary = */ true, c.components.sourceElementFactory.source(jClass)\n        )");
        List valueParameters = isAnnotation ? createAnnotationConstructorParameters(constructorDescriptor) : Collections.emptyList();
        constructorDescriptor.setHasSynthesizedParameterNames(false);
        constructorDescriptor.initialize(valueParameters, getConstructorVisibility(classDescriptor));
        constructorDescriptor.setHasStableParameterNames(true);
        constructorDescriptor.setReturnType(classDescriptor.getDefaultType());
        getC().getComponents().getJavaResolverCache().recordConstructor(this.jClass, constructorDescriptor);
        return constructorDescriptor;
    }

    private final DescriptorVisibility getConstructorVisibility(ClassDescriptor classDescriptor) {
        DescriptorVisibility visibility = classDescriptor.getVisibility();
        Intrinsics.checkNotNullExpressionValue(visibility, "classDescriptor.visibility");
        if (Intrinsics.areEqual(visibility, JavaDescriptorVisibilities.PROTECTED_STATIC_VISIBILITY)) {
            DescriptorVisibility PROTECTED_AND_PACKAGE = JavaDescriptorVisibilities.PROTECTED_AND_PACKAGE;
            Intrinsics.checkNotNullExpressionValue(PROTECTED_AND_PACKAGE, "PROTECTED_AND_PACKAGE");
            return PROTECTED_AND_PACKAGE;
        }
        return visibility;
    }

    private final List<ValueParameterDescriptor> createAnnotationConstructorParameters(ClassConstructorDescriptorImpl constructor) {
        Pair pair;
        Collection methods = this.jClass.getMethods();
        ArrayList result = new ArrayList(methods.size());
        JavaTypeAttributes attr = JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, true, null, 2, null);
        Collection $this$partition$iv = methods;
        ArrayList first$iv = new ArrayList();
        ArrayList second$iv = new ArrayList();
        for (Object element$iv : $this$partition$iv) {
            JavaMethod it = (JavaMethod) element$iv;
            if (Intrinsics.areEqual(it.getName(), JvmAnnotationNames.DEFAULT_ANNOTATION_MEMBER_NAME)) {
                first$iv.add(element$iv);
            } else {
                second$iv.add(element$iv);
            }
        }
        Pair pair2 = new Pair(first$iv, second$iv);
        List methodsNamedValue = (List) pair2.component1();
        List<JavaMethod> otherMethods = (List) pair2.component2();
        boolean z = methodsNamedValue.size() <= 1;
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError(Intrinsics.stringPlus("There can't be more than one method named 'value' in annotation class: ", this.jClass));
        }
        JavaMethod methodNamedValue = (JavaMethod) CollectionsKt.firstOrNull(methodsNamedValue);
        if (methodNamedValue != null) {
            JavaType parameterNamedValueJavaType = methodNamedValue.getReturnType();
            if (parameterNamedValueJavaType instanceof JavaArrayType) {
                pair = new Pair(getC().getTypeResolver().transformArrayType((JavaArrayType) parameterNamedValueJavaType, attr, true), getC().getTypeResolver().transformJavaType(((JavaArrayType) parameterNamedValueJavaType).getComponentType(), attr));
            } else {
                pair = new Pair(getC().getTypeResolver().transformJavaType(parameterNamedValueJavaType, attr), null);
            }
            Pair pair3 = pair;
            KotlinType parameterType = (KotlinType) pair3.component1();
            KotlinType varargType = (KotlinType) pair3.component2();
            addAnnotationValueParameter(result, constructor, 0, methodNamedValue, parameterType, varargType);
        }
        int startIndex = methodNamedValue != null ? 1 : 0;
        int i = 0;
        for (JavaMethod method : otherMethods) {
            int index = i;
            i++;
            KotlinType parameterType2 = getC().getTypeResolver().transformJavaType(method.getReturnType(), attr);
            addAnnotationValueParameter(result, constructor, index + startIndex, method, parameterType2, null);
        }
        return result;
    }

    private final void addAnnotationValueParameter(List<ValueParameterDescriptor> list, ConstructorDescriptor constructor, int index, JavaMethod method, KotlinType returnType, KotlinType varargElementType) {
        KotlinType kotlinTypeMakeNotNullable;
        List<ValueParameterDescriptor> list2 = list;
        ConstructorDescriptor constructorDescriptor = constructor;
        ValueParameterDescriptor valueParameterDescriptor = null;
        int i = index;
        Annotations empty = Annotations.Companion.getEMPTY();
        Name name = method.getName();
        KotlinType kotlinTypeMakeNotNullable2 = TypeUtils.makeNotNullable(returnType);
        Intrinsics.checkNotNullExpressionValue(kotlinTypeMakeNotNullable2, "makeNotNullable(returnType)");
        KotlinType kotlinType = kotlinTypeMakeNotNullable2;
        boolean hasAnnotationParameterDefaultValue = method.getHasAnnotationParameterDefaultValue();
        boolean z = false;
        boolean z2 = false;
        if (varargElementType == null) {
            kotlinTypeMakeNotNullable = null;
        } else {
            list2 = list2;
            constructorDescriptor = constructorDescriptor;
            valueParameterDescriptor = null;
            i = i;
            empty = empty;
            name = name;
            kotlinType = kotlinType;
            hasAnnotationParameterDefaultValue = hasAnnotationParameterDefaultValue;
            z = false;
            z2 = false;
            kotlinTypeMakeNotNullable = TypeUtils.makeNotNullable(varargElementType);
        }
        boolean z3 = z;
        boolean z4 = hasAnnotationParameterDefaultValue;
        KotlinType kotlinType2 = kotlinType;
        Name name2 = name;
        Annotations annotations = empty;
        int i2 = i;
        ValueParameterDescriptor valueParameterDescriptor2 = valueParameterDescriptor;
        ConstructorDescriptor constructorDescriptor2 = constructorDescriptor;
        list2.add(new ValueParameterDescriptorImpl(constructorDescriptor2, valueParameterDescriptor2, i2, annotations, name2, kotlinType2, z4, z3, z2, kotlinTypeMakeNotNullable, getC().getComponents().getSourceElementFactory().source(method)));
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    @Nullable
    protected ReceiverParameterDescriptor getDispatchReceiverParameter() {
        return DescriptorUtils.getDispatchReceiverParameterIfNeeded(getOwnerDescriptor());
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @Nullable
    /* renamed from: getContributedClassifier */
    public ClassifierDescriptor mo3858getContributedClassifier(@NotNull Name name, @NotNull LookupLocation location) {
        MemoizedFunctionToNullable<Name, ClassDescriptorBase> memoizedFunctionToNullable;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        recordLookup(name, location);
        LazyJavaClassMemberScope lazyJavaClassMemberScope = (LazyJavaClassMemberScope) getMainScope();
        ClassDescriptorBase classDescriptorBaseInvoke = (lazyJavaClassMemberScope == null || (memoizedFunctionToNullable = lazyJavaClassMemberScope.nestedClasses) == null) ? null : memoizedFunctionToNullable.invoke(name);
        ClassDescriptorBase classDescriptorBase = classDescriptorBaseInvoke;
        return classDescriptorBase == null ? this.nestedClasses.invoke(name) : classDescriptorBase;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @NotNull
    public Collection<SimpleFunctionDescriptor> getContributedFunctions(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        recordLookup(name, location);
        return super.getContributedFunctions(name, location);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    public Collection<PropertyDescriptor> getContributedVariables(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        recordLookup(name, location);
        return super.getContributedVariables(name, location);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    @NotNull
    protected Set<Name> computeClassNames(@NotNull DescriptorKindFilter kindFilter, @Nullable Function1<? super Name, Boolean> function1) {
        Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
        return SetsKt.plus((Set) this.nestedClassIndex.invoke(), (Iterable) this.enumEntryIndex.invoke().keySet());
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    @NotNull
    protected Set<Name> computePropertyNames(@NotNull DescriptorKindFilter kindFilter, @Nullable Function1<? super Name, Boolean> function1) {
        Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
        if (this.jClass.isAnnotationType()) {
            return getFunctionNames();
        }
        LinkedHashSet result = new LinkedHashSet(getDeclaredMemberIndex().invoke().getFieldNames());
        Iterable iterableMo3835getSupertypes = getOwnerDescriptor().getTypeConstructor().mo3835getSupertypes();
        Intrinsics.checkNotNullExpressionValue(iterableMo3835getSupertypes, "ownerDescriptor.typeConstructor.supertypes");
        Iterable $this$flatMapTo$iv = iterableMo3835getSupertypes;
        for (Object element$iv : $this$flatMapTo$iv) {
            KotlinType supertype = (KotlinType) element$iv;
            Iterable list$iv = supertype.getMemberScope().getVariableNames();
            CollectionsKt.addAll(result, list$iv);
        }
        return result;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public void recordLookup(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        kotlin.reflect.jvm.internal.impl.incremental.UtilsKt.record(getC().getComponents().getLookupTracker(), location, getOwnerDescriptor(), name);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    @NotNull
    public String toString() {
        return Intrinsics.stringPlus("Lazy Java member scope for ", this.jClass.getFqName());
    }
}
