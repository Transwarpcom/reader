package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: descriptorUtil.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/DescriptorUtilKt.class */
public final class DescriptorUtilKt {
    @Nullable
    public static final ClassDescriptor resolveClassByFqName(@NotNull ModuleDescriptor $this$resolveClassByFqName, @NotNull FqName fqName, @NotNull LookupLocation lookupLocation) {
        MemberScope unsubstitutedInnerClassesScope;
        ClassifierDescriptor contributedClassifier;
        Intrinsics.checkNotNullParameter($this$resolveClassByFqName, "<this>");
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        Intrinsics.checkNotNullParameter(lookupLocation, "lookupLocation");
        if (fqName.isRoot()) {
            return null;
        }
        FqName fqNameParent = fqName.parent();
        Intrinsics.checkNotNullExpressionValue(fqNameParent, "fqName.parent()");
        MemberScope memberScope = $this$resolveClassByFqName.getPackage(fqNameParent).getMemberScope();
        Name nameShortName = fqName.shortName();
        Intrinsics.checkNotNullExpressionValue(nameShortName, "fqName.shortName()");
        ClassifierDescriptor contributedClassifier2 = memberScope.mo3858getContributedClassifier(nameShortName, lookupLocation);
        ClassDescriptor it = contributedClassifier2 instanceof ClassDescriptor ? (ClassDescriptor) contributedClassifier2 : null;
        if (it != null) {
            return it;
        }
        FqName fqNameParent2 = fqName.parent();
        Intrinsics.checkNotNullExpressionValue(fqNameParent2, "fqName.parent()");
        ClassDescriptor classDescriptorResolveClassByFqName = resolveClassByFqName($this$resolveClassByFqName, fqNameParent2, lookupLocation);
        if (classDescriptorResolveClassByFqName == null || (unsubstitutedInnerClassesScope = classDescriptorResolveClassByFqName.getUnsubstitutedInnerClassesScope()) == null) {
            contributedClassifier = null;
        } else {
            Name nameShortName2 = fqName.shortName();
            Intrinsics.checkNotNullExpressionValue(nameShortName2, "fqName.shortName()");
            contributedClassifier = unsubstitutedInnerClassesScope.mo3858getContributedClassifier(nameShortName2, lookupLocation);
        }
        ClassifierDescriptor classifierDescriptor = contributedClassifier;
        if (classifierDescriptor instanceof ClassDescriptor) {
            return (ClassDescriptor) classifierDescriptor;
        }
        return null;
    }

    public static final boolean isTopLevelInPackage(@NotNull DeclarationDescriptor $this$isTopLevelInPackage) {
        Intrinsics.checkNotNullParameter($this$isTopLevelInPackage, "<this>");
        return $this$isTopLevelInPackage.getContainingDeclaration() instanceof PackageFragmentDescriptor;
    }

    @Nullable
    public static final ClassifierDescriptor getTopLevelContainingClassifier(@NotNull DeclarationDescriptor $this$getTopLevelContainingClassifier) {
        Intrinsics.checkNotNullParameter($this$getTopLevelContainingClassifier, "<this>");
        DeclarationDescriptor containingDeclaration = $this$getTopLevelContainingClassifier.getContainingDeclaration();
        if (containingDeclaration == null || ($this$getTopLevelContainingClassifier instanceof PackageFragmentDescriptor)) {
            return null;
        }
        if (!isTopLevelInPackage(containingDeclaration)) {
            return getTopLevelContainingClassifier(containingDeclaration);
        }
        if (containingDeclaration instanceof ClassifierDescriptor) {
            return (ClassifierDescriptor) containingDeclaration;
        }
        return null;
    }
}
