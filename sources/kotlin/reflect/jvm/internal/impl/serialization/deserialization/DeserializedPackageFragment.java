package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PackageFragmentDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;

/* compiled from: DeserializedPackageFragment.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/serialization/deserialization/DeserializedPackageFragment.class */
public abstract class DeserializedPackageFragment extends PackageFragmentDescriptorImpl {

    @NotNull
    private final StorageManager storageManager;

    public abstract void initialize(@NotNull DeserializationComponents deserializationComponents);

    @NotNull
    public abstract ClassDataFinder getClassDataFinder();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeserializedPackageFragment(@NotNull FqName fqName, @NotNull StorageManager storageManager, @NotNull ModuleDescriptor module) {
        super(module, fqName);
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        Intrinsics.checkNotNullParameter(storageManager, "storageManager");
        Intrinsics.checkNotNullParameter(module, "module");
        this.storageManager = storageManager;
    }

    public boolean hasTopLevelClass(@NotNull Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        MemberScope scope = getMemberScope();
        return (scope instanceof DeserializedMemberScope) && ((DeserializedMemberScope) scope).getClassNames$deserialization().contains(name);
    }
}
