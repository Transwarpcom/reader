package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderKt;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderOptimized;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;

/* compiled from: CompositePackageFragmentProvider.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/impl/CompositePackageFragmentProvider.class */
public final class CompositePackageFragmentProvider implements PackageFragmentProviderOptimized {

    @NotNull
    private final List<PackageFragmentProvider> providers;

    /* JADX WARN: Multi-variable type inference failed */
    public CompositePackageFragmentProvider(@NotNull List<? extends PackageFragmentProvider> providers) {
        Intrinsics.checkNotNullParameter(providers, "providers");
        this.providers = providers;
        boolean z = this.providers.size() == CollectionsKt.toSet(this.providers).size();
        if (!_Assertions.ENABLED || z) {
        } else {
            throw new AssertionError("providers.size is " + this.providers.size() + " while only " + CollectionsKt.toSet(this.providers).size() + " unique providers");
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    @NotNull
    public List<PackageFragmentDescriptor> getPackageFragments(@NotNull FqName fqName) {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        ArrayList result = new ArrayList();
        for (PackageFragmentProvider provider : this.providers) {
            PackageFragmentProviderKt.collectPackageFragmentsOptimizedIfPossible(provider, fqName, result);
        }
        return CollectionsKt.toList(result);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderOptimized
    public void collectPackageFragments(@NotNull FqName fqName, @NotNull Collection<PackageFragmentDescriptor> packageFragments) {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        Intrinsics.checkNotNullParameter(packageFragments, "packageFragments");
        for (PackageFragmentProvider provider : this.providers) {
            PackageFragmentProviderKt.collectPackageFragmentsOptimizedIfPossible(provider, fqName, packageFragments);
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderOptimized
    public boolean isEmpty(@NotNull FqName fqName) {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        Iterable $this$all$iv = this.providers;
        if (($this$all$iv instanceof Collection) && ((Collection) $this$all$iv).isEmpty()) {
            return true;
        }
        for (Object element$iv : $this$all$iv) {
            PackageFragmentProvider it = (PackageFragmentProvider) element$iv;
            if (!PackageFragmentProviderKt.isEmpty(it, fqName)) {
                return false;
            }
        }
        return true;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    @NotNull
    public Collection<FqName> getSubPackagesOf(@NotNull FqName fqName, @NotNull Function1<? super Name, Boolean> nameFilter) {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        Intrinsics.checkNotNullParameter(nameFilter, "nameFilter");
        HashSet result = new HashSet();
        for (PackageFragmentProvider provider : this.providers) {
            result.addAll(provider.getSubPackagesOf(fqName, nameFilter));
        }
        return result;
    }
}
