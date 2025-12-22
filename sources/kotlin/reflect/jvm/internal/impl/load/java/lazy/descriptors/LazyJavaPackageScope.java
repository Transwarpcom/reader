package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassFinder;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaPackageScope;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage;
import kotlin.reflect.jvm.internal.impl.load.java.structure.LightClassOriginKind;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinderKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.NullableLazyValue;
import kotlin.reflect.jvm.internal.impl.utils.FunctionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: LazyJavaPackageScope.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/lazy/descriptors/LazyJavaPackageScope.class */
public final class LazyJavaPackageScope extends LazyJavaStaticScope {

    @NotNull
    private final JavaPackage jPackage;

    @NotNull
    private final LazyJavaPackageFragment ownerDescriptor;

    @NotNull
    private final NullableLazyValue<Set<String>> knownClassNamesInPackage;

    @NotNull
    private final MemoizedFunctionToNullable<FindClassRequest, ClassDescriptor> classes;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    @NotNull
    public LazyJavaPackageFragment getOwnerDescriptor() {
        return this.ownerDescriptor;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyJavaPackageScope(@NotNull final LazyJavaResolverContext c, @NotNull JavaPackage jPackage, @NotNull LazyJavaPackageFragment ownerDescriptor) {
        super(c);
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(jPackage, "jPackage");
        Intrinsics.checkNotNullParameter(ownerDescriptor, "ownerDescriptor");
        this.jPackage = jPackage;
        this.ownerDescriptor = ownerDescriptor;
        this.knownClassNamesInPackage = c.getStorageManager().createNullableLazyValue(new Function0<Set<? extends String>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaPackageScope$knownClassNamesInPackage$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @Nullable
            public final Set<? extends String> invoke() {
                return c.getComponents().getFinder().knownClassNamesInPackage(this.getOwnerDescriptor().getFqName());
            }
        });
        this.classes = c.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1<FindClassRequest, ClassDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaPackageScope$classes$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @Nullable
            public final ClassDescriptor invoke(@NotNull LazyJavaPackageScope.FindClassRequest request) {
                JavaClass javaClassFindClass;
                JavaClassDescriptor javaClassDescriptor;
                byte[] content;
                Intrinsics.checkNotNullParameter(request, "request");
                ClassId requestClassId = new ClassId(this.this$0.getOwnerDescriptor().getFqName(), request.getName());
                KotlinClassFinder.Result kotlinClassOrClassFileContent = request.getJavaClass() != null ? c.getComponents().getKotlinClassFinder().findKotlinClassOrContent(request.getJavaClass()) : c.getComponents().getKotlinClassFinder().findKotlinClassOrContent(requestClassId);
                KotlinJvmBinaryClass kotlinBinaryClass = kotlinClassOrClassFileContent == null ? null : kotlinClassOrClassFileContent.toKotlinJvmBinaryClass();
                ClassId classId = kotlinBinaryClass == null ? null : kotlinBinaryClass.getClassId();
                if (classId != null && (classId.isNestedClass() || classId.isLocal())) {
                    return null;
                }
                LazyJavaPackageScope.KotlinClassLookupResult kotlinResult = this.this$0.resolveKotlinBinaryClass(kotlinBinaryClass);
                if (kotlinResult instanceof LazyJavaPackageScope.KotlinClassLookupResult.Found) {
                    return ((LazyJavaPackageScope.KotlinClassLookupResult.Found) kotlinResult).getDescriptor();
                }
                if (kotlinResult instanceof LazyJavaPackageScope.KotlinClassLookupResult.SyntheticClass) {
                    return null;
                }
                if (!(kotlinResult instanceof LazyJavaPackageScope.KotlinClassLookupResult.NotFound)) {
                    throw new NoWhenBranchMatchedException();
                }
                JavaClass javaClass = request.getJavaClass();
                if (javaClass == null) {
                    JavaClassFinder finder = c.getComponents().getFinder();
                    if (kotlinClassOrClassFileContent == null) {
                        content = null;
                    } else {
                        KotlinClassFinder.Result result = kotlinClassOrClassFileContent;
                        if (!(result instanceof KotlinClassFinder.Result.ClassFileContent)) {
                            result = null;
                        }
                        KotlinClassFinder.Result.ClassFileContent classFileContent = (KotlinClassFinder.Result.ClassFileContent) result;
                        content = classFileContent == null ? null : classFileContent.getContent();
                    }
                    javaClassFindClass = finder.findClass(new JavaClassFinder.Request(requestClassId, content, null, 4, null));
                } else {
                    javaClassFindClass = javaClass;
                }
                JavaClass javaClass2 = javaClassFindClass;
                if ((javaClass2 == null ? null : javaClass2.getLightClassOriginKind()) == LightClassOriginKind.BINARY) {
                    throw new IllegalStateException("Couldn't find kotlin binary class for light class created by kotlin binary file\nJavaClass: " + javaClass2 + "\nClassId: " + requestClassId + "\nfindKotlinClass(JavaClass) = " + KotlinClassFinderKt.findKotlinClass(c.getComponents().getKotlinClassFinder(), javaClass2) + "\nfindKotlinClass(ClassId) = " + KotlinClassFinderKt.findKotlinClass(c.getComponents().getKotlinClassFinder(), requestClassId) + '\n');
                }
                FqName actualFqName = javaClass2 == null ? null : javaClass2.getFqName();
                if (actualFqName == null || actualFqName.isRoot() || !Intrinsics.areEqual(actualFqName.parent(), this.this$0.getOwnerDescriptor().getFqName())) {
                    javaClassDescriptor = null;
                } else {
                    JavaClassDescriptor lazyJavaClassDescriptor = new LazyJavaClassDescriptor(c, this.this$0.getOwnerDescriptor(), javaClass2, null, 8, null);
                    JavaClassDescriptor p0 = lazyJavaClassDescriptor;
                    c.getComponents().getJavaClassesTracker().reportClass(p0);
                    javaClassDescriptor = lazyJavaClassDescriptor;
                }
                return javaClassDescriptor;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LazyJavaPackageScope.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/lazy/descriptors/LazyJavaPackageScope$KotlinClassLookupResult.class */
    static abstract class KotlinClassLookupResult {
        public /* synthetic */ KotlinClassLookupResult(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* compiled from: LazyJavaPackageScope.kt */
        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/lazy/descriptors/LazyJavaPackageScope$KotlinClassLookupResult$Found.class */
        public static final class Found extends KotlinClassLookupResult {

            @NotNull
            private final ClassDescriptor descriptor;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Found(@NotNull ClassDescriptor descriptor) {
                super(null);
                Intrinsics.checkNotNullParameter(descriptor, "descriptor");
                this.descriptor = descriptor;
            }

            @NotNull
            public final ClassDescriptor getDescriptor() {
                return this.descriptor;
            }
        }

        private KotlinClassLookupResult() {
        }

        /* compiled from: LazyJavaPackageScope.kt */
        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/lazy/descriptors/LazyJavaPackageScope$KotlinClassLookupResult$NotFound.class */
        public static final class NotFound extends KotlinClassLookupResult {

            @NotNull
            public static final NotFound INSTANCE = new NotFound();

            private NotFound() {
                super(null);
            }
        }

        /* compiled from: LazyJavaPackageScope.kt */
        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/lazy/descriptors/LazyJavaPackageScope$KotlinClassLookupResult$SyntheticClass.class */
        public static final class SyntheticClass extends KotlinClassLookupResult {

            @NotNull
            public static final SyntheticClass INSTANCE = new SyntheticClass();

            private SyntheticClass() {
                super(null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final KotlinClassLookupResult resolveKotlinBinaryClass(KotlinJvmBinaryClass kotlinClass) {
        if (kotlinClass == null) {
            return KotlinClassLookupResult.NotFound.INSTANCE;
        }
        if (kotlinClass.getClassHeader().getKind() == KotlinClassHeader.Kind.CLASS) {
            ClassDescriptor descriptor = getC().getComponents().getDeserializedDescriptorResolver().resolveClass(kotlinClass);
            return descriptor != null ? new KotlinClassLookupResult.Found(descriptor) : KotlinClassLookupResult.NotFound.INSTANCE;
        }
        return KotlinClassLookupResult.SyntheticClass.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LazyJavaPackageScope.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/lazy/descriptors/LazyJavaPackageScope$FindClassRequest.class */
    static final class FindClassRequest {

        @NotNull
        private final Name name;

        @Nullable
        private final JavaClass javaClass;

        public FindClassRequest(@NotNull Name name, @Nullable JavaClass javaClass) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.name = name;
            this.javaClass = javaClass;
        }

        @NotNull
        public final Name getName() {
            return this.name;
        }

        @Nullable
        public final JavaClass getJavaClass() {
            return this.javaClass;
        }

        public boolean equals(@Nullable Object other) {
            return (other instanceof FindClassRequest) && Intrinsics.areEqual(this.name, ((FindClassRequest) other).name);
        }

        public int hashCode() {
            return this.name.hashCode();
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @Nullable
    /* renamed from: getContributedClassifier */
    public ClassDescriptor mo3858getContributedClassifier(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        return findClassifier(name, null);
    }

    private final ClassDescriptor findClassifier(Name name, JavaClass javaClass) {
        if (!SpecialNames.isSafeIdentifier(name)) {
            return null;
        }
        Set knownClassNamesInPackage = this.knownClassNamesInPackage.invoke();
        if (javaClass == null && knownClassNamesInPackage != null && !knownClassNamesInPackage.contains(name.asString())) {
            return null;
        }
        return this.classes.invoke(new FindClassRequest(name, javaClass));
    }

    @Nullable
    public final ClassDescriptor findClassifierByJavaClass$descriptors_jvm(@NotNull JavaClass javaClass) {
        Intrinsics.checkNotNullParameter(javaClass, "javaClass");
        return findClassifier(javaClass.getName(), javaClass);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    public Collection<PropertyDescriptor> getContributedVariables(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        return CollectionsKt.emptyList();
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    @NotNull
    protected DeclaredMemberIndex computeMemberIndex() {
        return DeclaredMemberIndex.Empty.INSTANCE;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    @NotNull
    protected Set<Name> computeClassNames(@NotNull DescriptorKindFilter kindFilter, @Nullable Function1<? super Name, Boolean> function1) {
        Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
        if (!kindFilter.acceptsKinds(DescriptorKindFilter.Companion.getNON_SINGLETON_CLASSIFIERS_MASK())) {
            return SetsKt.emptySet();
        }
        Iterable knownClassNamesInPackage = (Set) this.knownClassNamesInPackage.invoke();
        if (knownClassNamesInPackage == null) {
            Iterable $this$mapNotNullTo$iv = this.jPackage.getClasses(function1 == null ? FunctionsKt.alwaysTrue() : function1);
            Collection destination$iv = new LinkedHashSet();
            for (Object element$iv$iv : $this$mapNotNullTo$iv) {
                JavaClass klass = (JavaClass) element$iv$iv;
                Name name = klass.getLightClassOriginKind() == LightClassOriginKind.SOURCE ? null : klass.getName();
                if (name != null) {
                    destination$iv.add(name);
                }
            }
            return (Set) destination$iv;
        }
        Iterable $this$mapTo$iv = knownClassNamesInPackage;
        Collection destination$iv2 = new HashSet();
        for (Object item$iv : $this$mapTo$iv) {
            String it = (String) item$iv;
            destination$iv2.add(Name.identifier(it));
        }
        return (Set) destination$iv2;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    @NotNull
    protected Set<Name> computeFunctionNames(@NotNull DescriptorKindFilter kindFilter, @Nullable Function1<? super Name, Boolean> function1) {
        Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
        return SetsKt.emptySet();
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected void computeNonDeclaredFunctions(@NotNull Collection<SimpleFunctionDescriptor> result, @NotNull Name name) {
        Intrinsics.checkNotNullParameter(result, "result");
        Intrinsics.checkNotNullParameter(name, "name");
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    @NotNull
    protected Set<Name> computePropertyNames(@NotNull DescriptorKindFilter kindFilter, @Nullable Function1<? super Name, Boolean> function1) {
        Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
        return SetsKt.emptySet();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x00a1  */
    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.Collection<kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor> getContributedDescriptors(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter r5, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super kotlin.reflect.jvm.internal.impl.name.Name, java.lang.Boolean> r6) {
        /*
            r4 = this;
            r0 = r5
            java.lang.String r1 = "kindFilter"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r0 = r6
            java.lang.String r1 = "nameFilter"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r0 = r5
            kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter$Companion r1 = kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter.Companion
            int r1 = r1.getCLASSIFIERS_MASK()
            kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter$Companion r2 = kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter.Companion
            int r2 = r2.getNON_SINGLETON_CLASSIFIERS_MASK()
            r1 = r1 | r2
            boolean r0 = r0.acceptsKinds(r1)
            if (r0 != 0) goto L29
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
            java.util.Collection r0 = (java.util.Collection) r0
            goto Lbb
        L29:
            r0 = r4
            kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue r0 = r0.getAllDescriptors()
            java.lang.Object r0 = r0.invoke()
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            r7 = r0
            r0 = 0
            r8 = r0
            r0 = r7
            r9 = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = r0
            r1.<init>()
            java.util.Collection r0 = (java.util.Collection) r0
            r10 = r0
            r0 = 0
            r11 = r0
            r0 = r9
            java.util.Iterator r0 = r0.iterator()
            r12 = r0
        L54:
            r0 = r12
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto Lb2
            r0 = r12
            java.lang.Object r0 = r0.next()
            r13 = r0
            r0 = r13
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor) r0
            r14 = r0
            r0 = 0
            r15 = r0
            r0 = r14
            boolean r0 = r0 instanceof kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
            if (r0 == 0) goto La1
            r0 = r6
            r1 = r14
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r1 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor) r1
            kotlin.reflect.jvm.internal.impl.name.Name r1 = r1.getName()
            r16 = r1
            r1 = r16
            java.lang.String r2 = "it.name"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            r1 = r16
            java.lang.Object r0 = r0.invoke(r1)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto La1
            r0 = 1
            goto La2
        La1:
            r0 = 0
        La2:
            if (r0 == 0) goto L54
            r0 = r10
            r1 = r13
            boolean r0 = r0.add(r1)
            goto L54
        Lb2:
            r0 = r10
            java.util.List r0 = (java.util.List) r0
            java.util.Collection r0 = (java.util.Collection) r0
        Lbb:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaPackageScope.getContributedDescriptors(kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter, kotlin.jvm.functions.Function1):java.util.Collection");
    }
}
