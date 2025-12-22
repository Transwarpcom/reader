package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.MappingUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.ScopesHolderForClass;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorBase;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.FakePureImplementationsProvider;
import kotlin.reflect.jvm.internal.impl.load.java.JavaDescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.load.java.UtilsKt;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotationsKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeAttributes;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolverKt;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNamesUtilKt;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.InnerClassesScopeWrapper;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/* compiled from: LazyJavaClassDescriptor.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/lazy/descriptors/LazyJavaClassDescriptor.class */
public final class LazyJavaClassDescriptor extends ClassDescriptorBase implements JavaClassDescriptor {

    @NotNull
    private final LazyJavaResolverContext outerContext;

    @NotNull
    private final JavaClass jClass;

    @Nullable
    private final ClassDescriptor additionalSupertypeClassDescriptor;

    @NotNull
    private final LazyJavaResolverContext c;

    @NotNull
    private final Lazy moduleAnnotations$delegate;

    @NotNull
    private final ClassKind kind;

    @NotNull
    private final Modality modality;

    @NotNull
    private final Visibility visibility;
    private final boolean isInner;

    @NotNull
    private final LazyJavaClassTypeConstructor typeConstructor;

    @NotNull
    private final LazyJavaClassMemberScope unsubstitutedMemberScope;

    @NotNull
    private final ScopesHolderForClass<LazyJavaClassMemberScope> scopeHolder;

    @NotNull
    private final InnerClassesScopeWrapper innerClassesScope;

    @NotNull
    private final LazyJavaStaticClassScope staticScope;

    @NotNull
    private final Annotations annotations;

    @NotNull
    private final NotNullLazyValue<List<TypeParameterDescriptor>> declaredParameters;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final Set<String> PUBLIC_METHOD_NAMES_IN_OBJECT = SetsKt.setOf((Object[]) new String[]{"equals", IdentityNamingStrategy.HASH_CODE_KEY, "getClass", "wait", "notify", "notifyAll", "toString"});

    public /* synthetic */ LazyJavaClassDescriptor(LazyJavaResolverContext lazyJavaResolverContext, DeclarationDescriptor declarationDescriptor, JavaClass javaClass, ClassDescriptor classDescriptor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(lazyJavaResolverContext, declarationDescriptor, javaClass, (i & 8) != 0 ? null : classDescriptor);
    }

    @NotNull
    public final LazyJavaResolverContext getOuterContext() {
        return this.outerContext;
    }

    @NotNull
    public final JavaClass getJClass() {
        return this.jClass;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyJavaClassDescriptor(@NotNull LazyJavaResolverContext outerContext, @NotNull DeclarationDescriptor containingDeclaration, @NotNull JavaClass jClass, @Nullable ClassDescriptor additionalSupertypeClassDescriptor) {
        ClassKind classKind;
        Modality modalityConvertFromFlags;
        super(outerContext.getStorageManager(), containingDeclaration, jClass.getName(), outerContext.getComponents().getSourceElementFactory().source(jClass), false);
        Intrinsics.checkNotNullParameter(outerContext, "outerContext");
        Intrinsics.checkNotNullParameter(containingDeclaration, "containingDeclaration");
        Intrinsics.checkNotNullParameter(jClass, "jClass");
        this.outerContext = outerContext;
        this.jClass = jClass;
        this.additionalSupertypeClassDescriptor = additionalSupertypeClassDescriptor;
        this.c = ContextKt.childForClassOrPackage$default(this.outerContext, this, this.jClass, 0, 4, null);
        this.c.getComponents().getJavaResolverCache().recordClass(this.jClass, this);
        boolean z = this.jClass.getLightClassOriginKind() == null;
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError(Intrinsics.stringPlus("Creating LazyJavaClassDescriptor for light class ", getJClass()));
        }
        this.moduleAnnotations$delegate = LazyKt.lazy(new Function0<List<? extends JavaAnnotation>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor$moduleAnnotations$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @Nullable
            public final List<? extends JavaAnnotation> invoke() {
                ClassId it = DescriptorUtilsKt.getClassId(this.this$0);
                if (it == null) {
                    return null;
                }
                return this.this$0.getOuterContext().getComponents().getJavaModuleResolver().getAnnotationsForModuleOwnerOfClass(it);
            }
        });
        if (this.jClass.isAnnotationType()) {
            classKind = ClassKind.ANNOTATION_CLASS;
        } else if (this.jClass.isInterface()) {
            classKind = ClassKind.INTERFACE;
        } else {
            classKind = this.jClass.isEnum() ? ClassKind.ENUM_CLASS : ClassKind.CLASS;
        }
        this.kind = classKind;
        if (this.jClass.isAnnotationType() || this.jClass.isEnum()) {
            modalityConvertFromFlags = Modality.FINAL;
        } else {
            modalityConvertFromFlags = Modality.Companion.convertFromFlags(false, this.jClass.isSealed() || this.jClass.isAbstract() || this.jClass.isInterface(), !this.jClass.isFinal());
        }
        this.modality = modalityConvertFromFlags;
        this.visibility = this.jClass.getVisibility();
        this.isInner = (this.jClass.getOuterClass() == null || this.jClass.isStatic()) ? false : true;
        this.typeConstructor = new LazyJavaClassTypeConstructor(this);
        this.unsubstitutedMemberScope = new LazyJavaClassMemberScope(this.c, this, this.jClass, this.additionalSupertypeClassDescriptor != null, null, 16, null);
        this.scopeHolder = ScopesHolderForClass.Companion.create(this, this.c.getStorageManager(), this.c.getComponents().getKotlinTypeChecker().getKotlinTypeRefiner(), new Function1<KotlinTypeRefiner, LazyJavaClassMemberScope>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor$scopeHolder$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final LazyJavaClassMemberScope invoke(@NotNull KotlinTypeRefiner it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return new LazyJavaClassMemberScope(this.this$0.c, this.this$0, this.this$0.getJClass(), this.this$0.additionalSupertypeClassDescriptor != null, this.this$0.unsubstitutedMemberScope);
            }
        });
        this.innerClassesScope = new InnerClassesScopeWrapper(this.unsubstitutedMemberScope);
        this.staticScope = new LazyJavaStaticClassScope(this.c, this.jClass, this);
        this.annotations = LazyJavaAnnotationsKt.resolveAnnotations(this.c, this.jClass);
        this.declaredParameters = this.c.getStorageManager().createLazyValue(new Function0<List<? extends TypeParameterDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor$declaredParameters$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final List<? extends TypeParameterDescriptor> invoke() {
                Iterable $this$map$iv = this.this$0.getJClass().getTypeParameters();
                LazyJavaClassDescriptor lazyJavaClassDescriptor = this.this$0;
                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    JavaTypeParameter p = (JavaTypeParameter) item$iv$iv;
                    TypeParameterDescriptor typeParameterDescriptorResolveTypeParameter = lazyJavaClassDescriptor.c.getTypeParameterResolver().resolveTypeParameter(p);
                    if (typeParameterDescriptorResolveTypeParameter == null) {
                        throw new AssertionError("Parameter " + p + " surely belongs to class " + lazyJavaClassDescriptor.getJClass() + ", so it must be resolved");
                    }
                    destination$iv$iv.add(typeParameterDescriptorResolveTypeParameter);
                }
                return (List) destination$iv$iv;
            }
        });
    }

    /* compiled from: LazyJavaClassDescriptor.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/lazy/descriptors/LazyJavaClassDescriptor$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    @Nullable
    public final List<JavaAnnotation> getModuleAnnotations() {
        return (List) this.moduleAnnotations$delegate.getValue();
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
        if (Intrinsics.areEqual(this.visibility, DescriptorVisibilities.PRIVATE) && this.jClass.getOuterClass() == null) {
            DescriptorVisibility descriptorVisibility = JavaDescriptorVisibilities.PACKAGE_VISIBILITY;
            Intrinsics.checkNotNullExpressionValue(descriptorVisibility, "{\n            JavaDescriptorVisibilities.PACKAGE_VISIBILITY\n        }");
            return descriptorVisibility;
        }
        return UtilsKt.toDescriptorVisibility(this.visibility);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
    public boolean isInner() {
        return this.isInner;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isData() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isInline() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isCompanionObject() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExpect() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isActual() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isFun() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isValue() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    @NotNull
    public TypeConstructor getTypeConstructor() {
        return this.typeConstructor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptor
    @NotNull
    public LazyJavaClassMemberScope getUnsubstitutedMemberScope(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        return (LazyJavaClassMemberScope) this.scopeHolder.getScope(kotlinTypeRefiner);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    @NotNull
    public MemberScope getUnsubstitutedInnerClassesScope() {
        return this.innerClassesScope;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    @NotNull
    public MemberScope getStaticScope() {
        return this.staticScope;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    @Nullable
    /* renamed from: getUnsubstitutedPrimaryConstructor */
    public ClassConstructorDescriptor mo3498getUnsubstitutedPrimaryConstructor() {
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    @Nullable
    /* renamed from: getCompanionObjectDescriptor */
    public ClassDescriptor mo3497getCompanionObjectDescriptor() {
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    @NotNull
    public LazyJavaClassMemberScope getUnsubstitutedMemberScope() {
        return (LazyJavaClassMemberScope) super.getUnsubstitutedMemberScope();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    @NotNull
    public List<ClassConstructorDescriptor> getConstructors() {
        return this.unsubstitutedMemberScope.getConstructors$descriptors_jvm().invoke();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    @NotNull
    public Annotations getAnnotations() {
        return this.annotations;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
    @NotNull
    public List<TypeParameterDescriptor> getDeclaredTypeParameters() {
        return this.declaredParameters.invoke();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    @NotNull
    public Collection<ClassDescriptor> getSealedSubclasses() {
        if (this.modality == Modality.SEALED) {
            JavaTypeAttributes attributes = JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null);
            Iterable $this$mapNotNull$iv = this.jClass.getPermittedTypes();
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                JavaClassifierType it = (JavaClassifierType) element$iv$iv$iv;
                ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = this.c.getTypeResolver().transformJavaType(it, attributes).getConstructor().mo3831getDeclarationDescriptor();
                ClassDescriptor classDescriptor = classifierDescriptorMo3831getDeclarationDescriptor instanceof ClassDescriptor ? (ClassDescriptor) classifierDescriptorMo3831getDeclarationDescriptor : null;
                if (classDescriptor != null) {
                    destination$iv$iv.add(classDescriptor);
                }
            }
            return (List) destination$iv$iv;
        }
        return CollectionsKt.emptyList();
    }

    @NotNull
    public String toString() {
        return Intrinsics.stringPlus("Lazy Java class ", DescriptorUtilsKt.getFqNameUnsafe(this));
    }

    /* compiled from: LazyJavaClassDescriptor.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/lazy/descriptors/LazyJavaClassDescriptor$LazyJavaClassTypeConstructor.class */
    private final class LazyJavaClassTypeConstructor extends AbstractClassTypeConstructor {

        @NotNull
        private final NotNullLazyValue<List<TypeParameterDescriptor>> parameters;
        final /* synthetic */ LazyJavaClassDescriptor this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LazyJavaClassTypeConstructor(LazyJavaClassDescriptor this$0) {
            super(this$0.c.getStorageManager());
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
            StorageManager storageManager = this.this$0.c.getStorageManager();
            final LazyJavaClassDescriptor lazyJavaClassDescriptor = this.this$0;
            this.parameters = storageManager.createLazyValue(new Function0<List<? extends TypeParameterDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor$LazyJavaClassTypeConstructor$parameters$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                @NotNull
                public final List<? extends TypeParameterDescriptor> invoke() {
                    return TypeParameterUtilsKt.computeConstructorTypeParameters(lazyJavaClassDescriptor);
                }
            });
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        @NotNull
        public List<TypeParameterDescriptor> getParameters() {
            return this.parameters.invoke();
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
        @NotNull
        protected Collection<KotlinType> computeSupertypes() {
            KotlinType kotlinTypeSubstitute;
            Collection javaTypes = this.this$0.getJClass().getSupertypes();
            ArrayList result = new ArrayList(javaTypes.size());
            ArrayList incomplete = new ArrayList(0);
            KotlinType purelyImplementedSupertype = getPurelyImplementedSupertype();
            for (JavaClassifierType javaType : javaTypes) {
                KotlinType kotlinType = this.this$0.c.getTypeResolver().transformJavaType(javaType, JavaTypeResolverKt.toAttributes$default(TypeUsage.SUPERTYPE, false, null, 3, null));
                KotlinType enhancedKotlinType = this.this$0.c.getComponents().getSignatureEnhancement().enhanceSuperType(kotlinType, this.this$0.c);
                if (enhancedKotlinType.getConstructor().mo3831getDeclarationDescriptor() instanceof NotFoundClasses.MockClassDescriptor) {
                    incomplete.add(javaType);
                }
                if (!Intrinsics.areEqual(enhancedKotlinType.getConstructor(), purelyImplementedSupertype == null ? null : purelyImplementedSupertype.getConstructor()) && !KotlinBuiltIns.isAnyOrNullableAny(enhancedKotlinType)) {
                    result.add(enhancedKotlinType);
                }
            }
            ArrayList arrayList = result;
            ClassDescriptor it = this.this$0.additionalSupertypeClassDescriptor;
            if (it == null) {
                kotlinTypeSubstitute = null;
            } else {
                arrayList = arrayList;
                kotlinTypeSubstitute = MappingUtilKt.createMappedTypeParametersSubstitution(it, this.this$0).buildSubstitutor().substitute(it.getDefaultType(), Variance.INVARIANT);
            }
            kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(arrayList, kotlinTypeSubstitute);
            kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(result, purelyImplementedSupertype);
            if (!incomplete.isEmpty()) {
                ErrorReporter errorReporter = this.this$0.c.getComponents().getErrorReporter();
                ClassDescriptor classDescriptorMo3831getDeclarationDescriptor = mo3831getDeclarationDescriptor();
                ArrayList $this$map$iv = incomplete;
                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    destination$iv$iv.add(((JavaClassifierType) ((JavaType) item$iv$iv)).getPresentableText());
                }
                errorReporter.reportIncompleteHierarchy(classDescriptorMo3831getDeclarationDescriptor, (List) destination$iv$iv);
            }
            return !result.isEmpty() ? CollectionsKt.toList(result) : CollectionsKt.listOf(this.this$0.c.getModule().getBuiltIns().getAnyType());
        }

        private final KotlinType getPurelyImplementedSupertype() {
            FqName fqName;
            FqName fqName2;
            List list;
            FqName fqName3 = getPurelyImplementsFqNameFromAnnotation();
            if (fqName3 == null) {
                fqName = null;
            } else {
                fqName = !fqName3.isRoot() && fqName3.startsWith(StandardNames.BUILT_INS_PACKAGE_NAME) ? fqName3 : null;
            }
            FqName annotatedPurelyImplementedFqName = fqName;
            if (annotatedPurelyImplementedFqName != null) {
                fqName2 = annotatedPurelyImplementedFqName;
            } else {
                FqName purelyImplementedInterface = FakePureImplementationsProvider.INSTANCE.getPurelyImplementedInterface(DescriptorUtilsKt.getFqNameSafe(this.this$0));
                if (purelyImplementedInterface != null) {
                    fqName2 = purelyImplementedInterface;
                } else {
                    return null;
                }
            }
            FqName purelyImplementedFqName = fqName2;
            ClassDescriptor classDescriptor = DescriptorUtilsKt.resolveTopLevelClass(this.this$0.c.getModule(), purelyImplementedFqName, NoLookupLocation.FROM_JAVA_LOADER);
            if (classDescriptor == null) {
                return null;
            }
            int supertypeParameterCount = classDescriptor.getTypeConstructor().getParameters().size();
            List typeParameters = this.this$0.getTypeConstructor().getParameters();
            Intrinsics.checkNotNullExpressionValue(typeParameters, "getTypeConstructor().parameters");
            int typeParameterCount = typeParameters.size();
            if (typeParameterCount == supertypeParameterCount) {
                List $this$map$iv = typeParameters;
                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    TypeParameterDescriptor parameter = (TypeParameterDescriptor) item$iv$iv;
                    destination$iv$iv.add(new TypeProjectionImpl(Variance.INVARIANT, parameter.getDefaultType()));
                }
                list = (List) destination$iv$iv;
            } else if (typeParameterCount == 1 && supertypeParameterCount > 1 && annotatedPurelyImplementedFqName == null) {
                TypeProjectionImpl parameter2 = new TypeProjectionImpl(Variance.INVARIANT, ((TypeParameterDescriptor) CollectionsKt.single(typeParameters)).getDefaultType());
                Iterable $this$map$iv2 = new IntRange(1, supertypeParameterCount);
                Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
                Iterator<Integer> it = $this$map$iv2.iterator();
                while (it.hasNext()) {
                    ((IntIterator) it).nextInt();
                    destination$iv$iv2.add(parameter2);
                }
                list = (List) destination$iv$iv2;
            } else {
                return null;
            }
            List parametersAsTypeProjections = list;
            KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
            return KotlinTypeFactory.simpleNotNullType(Annotations.Companion.getEMPTY(), classDescriptor, parametersAsTypeProjections);
        }

        private final FqName getPurelyImplementsFqNameFromAnnotation() {
            Annotations annotations = this.this$0.getAnnotations();
            FqName PURELY_IMPLEMENTS_ANNOTATION = JvmAnnotationNames.PURELY_IMPLEMENTS_ANNOTATION;
            Intrinsics.checkNotNullExpressionValue(PURELY_IMPLEMENTS_ANNOTATION, "PURELY_IMPLEMENTS_ANNOTATION");
            AnnotationDescriptor annotation = annotations.mo3547findAnnotation(PURELY_IMPLEMENTS_ANNOTATION);
            if (annotation == null) {
                return null;
            }
            Object objSingleOrNull = CollectionsKt.singleOrNull(annotation.getAllValueArguments().values());
            StringValue stringValue = objSingleOrNull instanceof StringValue ? (StringValue) objSingleOrNull : null;
            String fqNameString = stringValue == null ? null : stringValue.getValue();
            if (fqNameString != null && FqNamesUtilKt.isValidJavaFqName(fqNameString)) {
                return new FqName(fqNameString);
            }
            return null;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
        @NotNull
        protected SupertypeLoopChecker getSupertypeLoopChecker() {
            return this.this$0.c.getComponents().getSupertypeLoopChecker();
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public boolean isDenotable() {
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor, kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor, kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        @NotNull
        /* renamed from: getDeclarationDescriptor */
        public ClassDescriptor mo3831getDeclarationDescriptor() {
            return this.this$0;
        }

        @NotNull
        public String toString() {
            String strAsString = this.this$0.getName().asString();
            Intrinsics.checkNotNullExpressionValue(strAsString, "name.asString()");
            return strAsString;
        }
    }

    @NotNull
    public final LazyJavaClassDescriptor copy$descriptors_jvm(@NotNull JavaResolverCache javaResolverCache, @Nullable ClassDescriptor additionalSupertypeClassDescriptor) {
        Intrinsics.checkNotNullParameter(javaResolverCache, "javaResolverCache");
        LazyJavaResolverContext lazyJavaResolverContextReplaceComponents = ContextKt.replaceComponents(this.c, this.c.getComponents().replace(javaResolverCache));
        DeclarationDescriptor containingDeclaration = getContainingDeclaration();
        Intrinsics.checkNotNullExpressionValue(containingDeclaration, "containingDeclaration");
        return new LazyJavaClassDescriptor(lazyJavaResolverContextReplaceComponents, containingDeclaration, this.jClass, additionalSupertypeClassDescriptor);
    }
}
