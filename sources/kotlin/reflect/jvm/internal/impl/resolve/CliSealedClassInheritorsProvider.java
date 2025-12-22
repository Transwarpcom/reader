package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

/* compiled from: SealedClassInheritorsProvider.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/CliSealedClassInheritorsProvider.class */
public final class CliSealedClassInheritorsProvider extends SealedClassInheritorsProvider {

    @NotNull
    public static final CliSealedClassInheritorsProvider INSTANCE = new CliSealedClassInheritorsProvider();

    private CliSealedClassInheritorsProvider() {
    }

    @NotNull
    public Collection<ClassDescriptor> computeSealedSubclasses(@NotNull ClassDescriptor sealedClass, boolean allowSealedInheritorsInDifferentFilesOfSamePackage) {
        Object obj;
        DeclarationDescriptor containingDeclaration;
        Intrinsics.checkNotNullParameter(sealedClass, "sealedClass");
        if (sealedClass.getModality() != Modality.SEALED) {
            return CollectionsKt.emptyList();
        }
        LinkedHashSet result = new LinkedHashSet();
        if (!allowSealedInheritorsInDifferentFilesOfSamePackage) {
            containingDeclaration = sealedClass.getContainingDeclaration();
        } else {
            Sequence $this$firstOrNull$iv = DescriptorUtilsKt.getParents(sealedClass);
            Iterator<DeclarationDescriptor> it = $this$firstOrNull$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    DeclarationDescriptor it2 = (DeclarationDescriptor) element$iv;
                    if (it2 instanceof PackageFragmentDescriptor) {
                        obj = element$iv;
                        break;
                    }
                } else {
                    obj = null;
                    break;
                }
            }
            containingDeclaration = (DeclarationDescriptor) obj;
        }
        DeclarationDescriptor container = containingDeclaration;
        if (container instanceof PackageFragmentDescriptor) {
            computeSealedSubclasses$collectSubclasses(sealedClass, result, ((PackageFragmentDescriptor) container).getMemberScope(), allowSealedInheritorsInDifferentFilesOfSamePackage);
        }
        MemberScope unsubstitutedInnerClassesScope = sealedClass.getUnsubstitutedInnerClassesScope();
        Intrinsics.checkNotNullExpressionValue(unsubstitutedInnerClassesScope, "sealedClass.unsubstitutedInnerClassesScope");
        computeSealedSubclasses$collectSubclasses(sealedClass, result, unsubstitutedInnerClassesScope, true);
        return result;
    }

    private static final void computeSealedSubclasses$collectSubclasses(ClassDescriptor $sealedClass, LinkedHashSet<ClassDescriptor> linkedHashSet, MemberScope scope, boolean collectNested) {
        ClassDescriptor classDescriptor;
        for (DeclarationDescriptor descriptor : ResolutionScope.DefaultImpls.getContributedDescriptors$default(scope, DescriptorKindFilter.CLASSIFIERS, null, 2, null)) {
            if (descriptor instanceof ClassDescriptor) {
                if (((ClassDescriptor) descriptor).isExpect()) {
                    Name name = ((ClassDescriptor) descriptor).getName();
                    Intrinsics.checkNotNullExpressionValue(name, "descriptor.name");
                    ClassifierDescriptor actualDescriptor = scope.mo3858getContributedClassifier(name, NoLookupLocation.WHEN_GET_ALL_DESCRIPTORS);
                    if (actualDescriptor instanceof ClassDescriptor) {
                        classDescriptor = (ClassDescriptor) actualDescriptor;
                    } else {
                        classDescriptor = actualDescriptor instanceof TypeAliasDescriptor ? ((TypeAliasDescriptor) actualDescriptor).getClassDescriptor() : null;
                    }
                } else {
                    classDescriptor = (ClassDescriptor) descriptor;
                }
                ClassDescriptor refinedDescriptor = classDescriptor;
                if (refinedDescriptor != null) {
                    if (DescriptorUtils.isDirectSubclass(refinedDescriptor, $sealedClass)) {
                        linkedHashSet.add(refinedDescriptor);
                    }
                    if (collectNested) {
                        MemberScope unsubstitutedInnerClassesScope = refinedDescriptor.getUnsubstitutedInnerClassesScope();
                        Intrinsics.checkNotNullExpressionValue(unsubstitutedInnerClassesScope, "refinedDescriptor.unsubstitutedInnerClassesScope");
                        computeSealedSubclasses$collectSubclasses($sealedClass, linkedHashSet, unsubstitutedInnerClassesScope, collectNested);
                    }
                }
            }
        }
    }
}
