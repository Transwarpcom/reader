package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: PackageFragmentProviderImpl.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/PackageFragmentProviderImpl.class */
public final class PackageFragmentProviderImpl implements PackageFragmentProviderOptimized {

    @NotNull
    private final Collection<PackageFragmentDescriptor> packageFragments;

    /* JADX WARN: Multi-variable type inference failed */
    public PackageFragmentProviderImpl(@NotNull Collection<? extends PackageFragmentDescriptor> packageFragments) {
        Intrinsics.checkNotNullParameter(packageFragments, "packageFragments");
        this.packageFragments = packageFragments;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderOptimized
    public void collectPackageFragments(@NotNull FqName fqName, @NotNull Collection<PackageFragmentDescriptor> packageFragments) {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        Intrinsics.checkNotNullParameter(packageFragments, "packageFragments");
        Iterable $this$filterTo$iv = this.packageFragments;
        for (Object element$iv : $this$filterTo$iv) {
            PackageFragmentDescriptor it = (PackageFragmentDescriptor) element$iv;
            if (Intrinsics.areEqual(it.getFqName(), fqName)) {
                packageFragments.add(element$iv);
            }
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderOptimized
    public boolean isEmpty(@NotNull FqName fqName) {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        Iterable $this$none$iv = this.packageFragments;
        if (($this$none$iv instanceof Collection) && ((Collection) $this$none$iv).isEmpty()) {
            return true;
        }
        for (Object element$iv : $this$none$iv) {
            PackageFragmentDescriptor it = (PackageFragmentDescriptor) element$iv;
            if (Intrinsics.areEqual(it.getFqName(), fqName)) {
                return false;
            }
        }
        return true;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    @NotNull
    public List<PackageFragmentDescriptor> getPackageFragments(@NotNull FqName fqName) {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        Iterable $this$filter$iv = this.packageFragments;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            PackageFragmentDescriptor it = (PackageFragmentDescriptor) element$iv$iv;
            if (Intrinsics.areEqual(it.getFqName(), fqName)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    @NotNull
    public Collection<FqName> getSubPackagesOf(@NotNull final FqName fqName, @NotNull Function1<? super Name, Boolean> nameFilter) {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        Intrinsics.checkNotNullParameter(nameFilter, "nameFilter");
        return SequencesKt.toList(SequencesKt.filter(SequencesKt.map(CollectionsKt.asSequence(this.packageFragments), new Function1<PackageFragmentDescriptor, FqName>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderImpl.getSubPackagesOf.1
            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final FqName invoke(@NotNull PackageFragmentDescriptor it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getFqName();
            }
        }), new Function1<FqName, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderImpl.getSubPackagesOf.2
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(@NotNull FqName it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return !it.isRoot() && Intrinsics.areEqual(it.parent(), fqName);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(FqName fqName2) {
                return Boolean.valueOf(invoke2(fqName2));
            }
        }));
    }
}
