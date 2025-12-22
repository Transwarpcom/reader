package kotlin.reflect.jvm.internal.impl.incremental;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LocationInfo;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker;
import kotlin.reflect.jvm.internal.impl.incremental.components.Position;
import kotlin.reflect.jvm.internal.impl.incremental.components.ScopeKind;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import org.jetbrains.annotations.NotNull;

/* compiled from: utils.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/incremental/UtilsKt.class */
public final class UtilsKt {
    public static final void record(@NotNull LookupTracker $this$record, @NotNull LookupLocation from, @NotNull ClassDescriptor scopeOwner, @NotNull Name name) {
        LocationInfo location;
        Intrinsics.checkNotNullParameter($this$record, "<this>");
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(scopeOwner, "scopeOwner");
        Intrinsics.checkNotNullParameter(name, "name");
        if ($this$record == LookupTracker.DO_NOTHING.INSTANCE || (location = from.getLocation()) == null) {
            return;
        }
        Position position = $this$record.getRequiresPosition() ? location.getPosition() : Position.Companion.getNO_POSITION();
        String filePath = location.getFilePath();
        String strAsString = DescriptorUtils.getFqName(scopeOwner).asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "getFqName(scopeOwner).asString()");
        ScopeKind scopeKind = ScopeKind.CLASSIFIER;
        String strAsString2 = name.asString();
        Intrinsics.checkNotNullExpressionValue(strAsString2, "name.asString()");
        $this$record.record(filePath, position, strAsString, scopeKind, strAsString2);
    }

    public static final void record(@NotNull LookupTracker $this$record, @NotNull LookupLocation from, @NotNull PackageFragmentDescriptor scopeOwner, @NotNull Name name) {
        Intrinsics.checkNotNullParameter($this$record, "<this>");
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(scopeOwner, "scopeOwner");
        Intrinsics.checkNotNullParameter(name, "name");
        String strAsString = scopeOwner.getFqName().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "scopeOwner.fqName.asString()");
        String strAsString2 = name.asString();
        Intrinsics.checkNotNullExpressionValue(strAsString2, "name.asString()");
        recordPackageLookup($this$record, from, strAsString, strAsString2);
    }

    public static final void recordPackageLookup(@NotNull LookupTracker $this$recordPackageLookup, @NotNull LookupLocation from, @NotNull String packageFqName, @NotNull String name) {
        LocationInfo location;
        Intrinsics.checkNotNullParameter($this$recordPackageLookup, "<this>");
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(packageFqName, "packageFqName");
        Intrinsics.checkNotNullParameter(name, "name");
        if ($this$recordPackageLookup == LookupTracker.DO_NOTHING.INSTANCE || (location = from.getLocation()) == null) {
            return;
        }
        Position position = $this$recordPackageLookup.getRequiresPosition() ? location.getPosition() : Position.Companion.getNO_POSITION();
        $this$recordPackageLookup.record(location.getFilePath(), position, packageFqName, ScopeKind.PACKAGE, name);
    }
}
