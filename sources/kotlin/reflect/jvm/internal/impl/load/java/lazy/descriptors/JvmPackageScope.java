package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.UtilsKt;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeKt;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.util.collectionUtils.ScopeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JvmPackageScope.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/lazy/descriptors/JvmPackageScope.class */
public final class JvmPackageScope implements MemberScope {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JvmPackageScope.class), "kotlinScopes", "getKotlinScopes()[Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;"))};

    @NotNull
    private final LazyJavaResolverContext c;

    @NotNull
    private final LazyJavaPackageFragment packageFragment;

    @NotNull
    private final LazyJavaPackageScope javaScope;

    @NotNull
    private final NotNullLazyValue kotlinScopes$delegate;

    public JvmPackageScope(@NotNull LazyJavaResolverContext c, @NotNull JavaPackage jPackage, @NotNull LazyJavaPackageFragment packageFragment) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(jPackage, "jPackage");
        Intrinsics.checkNotNullParameter(packageFragment, "packageFragment");
        this.c = c;
        this.packageFragment = packageFragment;
        this.javaScope = new LazyJavaPackageScope(this.c, jPackage, this.packageFragment);
        this.kotlinScopes$delegate = this.c.getStorageManager().createLazyValue(new Function0<MemberScope[]>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.JvmPackageScope$kotlinScopes$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final MemberScope[] invoke() {
                Iterable $this$mapNotNull$iv = this.this$0.packageFragment.getBinaryClasses$descriptors_jvm().values();
                JvmPackageScope jvmPackageScope = this.this$0;
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                    KotlinJvmBinaryClass partClass = (KotlinJvmBinaryClass) element$iv$iv$iv;
                    MemberScope memberScopeCreateKotlinPackagePartScope = jvmPackageScope.c.getComponents().getDeserializedDescriptorResolver().createKotlinPackagePartScope(jvmPackageScope.packageFragment, partClass);
                    if (memberScopeCreateKotlinPackagePartScope != null) {
                        destination$iv$iv.add(memberScopeCreateKotlinPackagePartScope);
                    }
                }
                Collection $this$toTypedArray$iv = ScopeUtilsKt.listOfNonEmptyScopes((List) destination$iv$iv);
                Object[] array = $this$toTypedArray$iv.toArray(new MemberScope[0]);
                if (array == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                }
                return (MemberScope[]) array;
            }
        });
    }

    @NotNull
    public final LazyJavaPackageScope getJavaScope$descriptors_jvm() {
        return this.javaScope;
    }

    private final MemberScope[] getKotlinScopes() {
        return (MemberScope[]) StorageKt.getValue(this.kotlinScopes$delegate, this, (KProperty<?>) $$delegatedProperties[0]);
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @Nullable
    /* renamed from: getContributedClassifier */
    public ClassifierDescriptor mo3858getContributedClassifier(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        recordLookup(name, location);
        ClassDescriptor javaClassifier = this.javaScope.mo3858getContributedClassifier(name, location);
        if (javaClassifier != null) {
            return javaClassifier;
        }
        MemberScope[] kotlinScopes = getKotlinScopes();
        ClassifierDescriptor result$iv = null;
        int i = 0;
        int length = kotlinScopes.length;
        while (i < length) {
            MemberScope memberScope = kotlinScopes[i];
            i++;
            ClassifierDescriptor newResult$iv = memberScope.mo3858getContributedClassifier(name, location);
            if (newResult$iv != null) {
                if ((newResult$iv instanceof ClassifierDescriptorWithTypeParameters) && ((ClassifierDescriptorWithTypeParameters) newResult$iv).isExpect()) {
                    if (result$iv == null) {
                        result$iv = newResult$iv;
                    }
                } else {
                    return newResult$iv;
                }
            }
        }
        return result$iv;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    public Collection<PropertyDescriptor> getContributedVariables(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        recordLookup(name, location);
        MemberScope firstScope$iv = this.javaScope;
        MemberScope[] kotlinScopes = getKotlinScopes();
        MemberScope it = firstScope$iv;
        Collection result$iv = it.getContributedVariables(name, location);
        int i = 0;
        int length = kotlinScopes.length;
        while (i < length) {
            MemberScope memberScope = kotlinScopes[i];
            i++;
            result$iv = ScopeUtilsKt.concat(result$iv, memberScope.getContributedVariables(name, location));
        }
        Collection collection = result$iv;
        return collection == null ? SetsKt.emptySet() : collection;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @NotNull
    public Collection<SimpleFunctionDescriptor> getContributedFunctions(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        recordLookup(name, location);
        MemberScope firstScope$iv = this.javaScope;
        MemberScope[] kotlinScopes = getKotlinScopes();
        MemberScope it = firstScope$iv;
        Collection result$iv = it.getContributedFunctions(name, location);
        int i = 0;
        int length = kotlinScopes.length;
        while (i < length) {
            MemberScope memberScope = kotlinScopes[i];
            i++;
            result$iv = ScopeUtilsKt.concat(result$iv, memberScope.getContributedFunctions(name, location));
        }
        Collection collection = result$iv;
        return collection == null ? SetsKt.emptySet() : collection;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @NotNull
    public Collection<DeclarationDescriptor> getContributedDescriptors(@NotNull DescriptorKindFilter kindFilter, @NotNull Function1<? super Name, Boolean> nameFilter) {
        Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
        Intrinsics.checkNotNullParameter(nameFilter, "nameFilter");
        MemberScope firstScope$iv = this.javaScope;
        MemberScope[] kotlinScopes = getKotlinScopes();
        MemberScope it = firstScope$iv;
        Collection result$iv = it.getContributedDescriptors(kindFilter, nameFilter);
        int i = 0;
        int length = kotlinScopes.length;
        while (i < length) {
            MemberScope memberScope = kotlinScopes[i];
            i++;
            result$iv = ScopeUtilsKt.concat(result$iv, memberScope.getContributedDescriptors(kindFilter, nameFilter));
        }
        Collection collection = result$iv;
        return collection == null ? SetsKt.emptySet() : collection;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    public Set<Name> getFunctionNames() {
        MemberScope[] kotlinScopes = getKotlinScopes();
        Collection destination$iv = (Set) new LinkedHashSet();
        for (MemberScope memberScope : kotlinScopes) {
            Iterable list$iv = memberScope.getFunctionNames();
            CollectionsKt.addAll(destination$iv, list$iv);
        }
        Set $this$getFunctionNames_u24lambda_u2d5 = (Set) destination$iv;
        $this$getFunctionNames_u24lambda_u2d5.addAll(getJavaScope$descriptors_jvm().getFunctionNames());
        return (Set) destination$iv;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    public Set<Name> getVariableNames() {
        MemberScope[] kotlinScopes = getKotlinScopes();
        Collection destination$iv = (Set) new LinkedHashSet();
        for (MemberScope memberScope : kotlinScopes) {
            Iterable list$iv = memberScope.getVariableNames();
            CollectionsKt.addAll(destination$iv, list$iv);
        }
        Set $this$getVariableNames_u24lambda_u2d7 = (Set) destination$iv;
        $this$getVariableNames_u24lambda_u2d7.addAll(getJavaScope$descriptors_jvm().getVariableNames());
        return (Set) destination$iv;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @Nullable
    public Set<Name> getClassifierNames() {
        Set $this$getClassifierNames_u24lambda_u2d8 = MemberScopeKt.flatMapClassifierNamesOrNull(ArraysKt.asIterable(getKotlinScopes()));
        if ($this$getClassifierNames_u24lambda_u2d8 == null) {
            return null;
        }
        $this$getClassifierNames_u24lambda_u2d8.addAll(getJavaScope$descriptors_jvm().getClassifierNames());
        return $this$getClassifierNames_u24lambda_u2d8;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public void recordLookup(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        UtilsKt.record(this.c.getComponents().getLookupTracker(), location, this.packageFragment, name);
    }
}
