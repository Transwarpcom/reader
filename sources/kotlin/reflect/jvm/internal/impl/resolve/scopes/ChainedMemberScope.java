package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.util.collectionUtils.ScopeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ChainedMemberScope.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/scopes/ChainedMemberScope.class */
public final class ChainedMemberScope implements MemberScope {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final String debugName;

    @NotNull
    private final MemberScope[] scopes;

    public /* synthetic */ ChainedMemberScope(String debugName, MemberScope[] scopes, DefaultConstructorMarker $constructor_marker) {
        this(debugName, scopes);
    }

    private ChainedMemberScope(String debugName, MemberScope[] scopes) {
        this.debugName = debugName;
        this.scopes = scopes;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @Nullable
    /* renamed from: getContributedClassifier */
    public ClassifierDescriptor mo3858getContributedClassifier(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        MemberScope[] memberScopeArr = this.scopes;
        ClassifierDescriptor result$iv = null;
        int i = 0;
        int length = memberScopeArr.length;
        while (i < length) {
            MemberScope memberScope = memberScopeArr[i];
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
        MemberScope[] memberScopeArr = this.scopes;
        switch (memberScopeArr.length) {
            case 0:
                return CollectionsKt.emptyList();
            case 1:
                MemberScope it = memberScopeArr[0];
                return it.getContributedVariables(name, location);
            default:
                Collection result$iv = null;
                int i = 0;
                int length = memberScopeArr.length;
                while (i < length) {
                    MemberScope memberScope = memberScopeArr[i];
                    i++;
                    result$iv = ScopeUtilsKt.concat(result$iv, memberScope.getContributedVariables(name, location));
                }
                Collection collection = result$iv;
                return collection == null ? SetsKt.emptySet() : collection;
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @NotNull
    public Collection<SimpleFunctionDescriptor> getContributedFunctions(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        MemberScope[] memberScopeArr = this.scopes;
        switch (memberScopeArr.length) {
            case 0:
                return CollectionsKt.emptyList();
            case 1:
                MemberScope it = memberScopeArr[0];
                return it.getContributedFunctions(name, location);
            default:
                Collection result$iv = null;
                int i = 0;
                int length = memberScopeArr.length;
                while (i < length) {
                    MemberScope memberScope = memberScopeArr[i];
                    i++;
                    result$iv = ScopeUtilsKt.concat(result$iv, memberScope.getContributedFunctions(name, location));
                }
                Collection collection = result$iv;
                return collection == null ? SetsKt.emptySet() : collection;
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @NotNull
    public Collection<DeclarationDescriptor> getContributedDescriptors(@NotNull DescriptorKindFilter kindFilter, @NotNull Function1<? super Name, Boolean> nameFilter) {
        Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
        Intrinsics.checkNotNullParameter(nameFilter, "nameFilter");
        MemberScope[] memberScopeArr = this.scopes;
        switch (memberScopeArr.length) {
            case 0:
                return CollectionsKt.emptyList();
            case 1:
                MemberScope it = memberScopeArr[0];
                return it.getContributedDescriptors(kindFilter, nameFilter);
            default:
                Collection result$iv = null;
                int i = 0;
                int length = memberScopeArr.length;
                while (i < length) {
                    MemberScope memberScope = memberScopeArr[i];
                    i++;
                    result$iv = ScopeUtilsKt.concat(result$iv, memberScope.getContributedDescriptors(kindFilter, nameFilter));
                }
                Collection collection = result$iv;
                return collection == null ? SetsKt.emptySet() : collection;
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    public Set<Name> getFunctionNames() {
        MemberScope[] memberScopeArr = this.scopes;
        Collection destination$iv = (Set) new LinkedHashSet();
        for (MemberScope memberScope : memberScopeArr) {
            Iterable list$iv = memberScope.getFunctionNames();
            CollectionsKt.addAll(destination$iv, list$iv);
        }
        return (Set) destination$iv;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    public Set<Name> getVariableNames() {
        MemberScope[] memberScopeArr = this.scopes;
        Collection destination$iv = (Set) new LinkedHashSet();
        for (MemberScope memberScope : memberScopeArr) {
            Iterable list$iv = memberScope.getVariableNames();
            CollectionsKt.addAll(destination$iv, list$iv);
        }
        return (Set) destination$iv;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @Nullable
    public Set<Name> getClassifierNames() {
        return MemberScopeKt.flatMapClassifierNamesOrNull(ArraysKt.asIterable(this.scopes));
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public void recordLookup(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        for (MemberScope memberScope : this.scopes) {
            memberScope.recordLookup(name, location);
        }
    }

    @NotNull
    public String toString() {
        return this.debugName;
    }

    /* compiled from: ChainedMemberScope.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/scopes/ChainedMemberScope$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final MemberScope create(@NotNull String debugName, @NotNull Iterable<? extends MemberScope> scopes) {
            Intrinsics.checkNotNullParameter(debugName, "debugName");
            Intrinsics.checkNotNullParameter(scopes, "scopes");
            SmartList flattenedNonEmptyScopes = new SmartList();
            for (MemberScope scope : scopes) {
                if (scope != MemberScope.Empty.INSTANCE) {
                    if (scope instanceof ChainedMemberScope) {
                        CollectionsKt.addAll(flattenedNonEmptyScopes, ((ChainedMemberScope) scope).scopes);
                    } else {
                        flattenedNonEmptyScopes.add(scope);
                    }
                }
            }
            return createOrSingle$descriptors(debugName, flattenedNonEmptyScopes);
        }

        @NotNull
        public final MemberScope createOrSingle$descriptors(@NotNull String debugName, @NotNull List<? extends MemberScope> scopes) {
            Intrinsics.checkNotNullParameter(debugName, "debugName");
            Intrinsics.checkNotNullParameter(scopes, "scopes");
            switch (scopes.size()) {
                case 0:
                    return MemberScope.Empty.INSTANCE;
                case 1:
                    return scopes.get(0);
                default:
                    List<? extends MemberScope> $this$toTypedArray$iv = scopes;
                    Object[] array = $this$toTypedArray$iv.toArray(new MemberScope[0]);
                    if (array == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                    return new ChainedMemberScope(debugName, (MemberScope[]) array, null);
            }
        }
    }
}
