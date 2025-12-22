package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.ResolutionAnchorProviderKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: findClassInModule.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/FindClassInModuleKt.class */
public final class FindClassInModuleKt {
    @Nullable
    public static final ClassifierDescriptor findClassifierAcrossModuleDependencies(@NotNull ModuleDescriptor $this$findClassifierAcrossModuleDependencies, @NotNull ClassId classId) {
        ClassifierDescriptor classifierDescriptor;
        Intrinsics.checkNotNullParameter($this$findClassifierAcrossModuleDependencies, "<this>");
        Intrinsics.checkNotNullParameter(classId, "classId");
        ModuleDescriptor anchor$iv = ResolutionAnchorProviderKt.getResolutionAnchorIfAny($this$findClassifierAcrossModuleDependencies);
        if (anchor$iv == null) {
            FqName packageFqName = classId.getPackageFqName();
            Intrinsics.checkNotNullExpressionValue(packageFqName, "classId.packageFqName");
            PackageViewDescriptor packageViewDescriptor = $this$findClassifierAcrossModuleDependencies.getPackage(packageFqName);
            List segments = classId.getRelativeClassName().pathSegments();
            Intrinsics.checkNotNullExpressionValue(segments, "classId.relativeClassName.pathSegments()");
            MemberScope memberScope = packageViewDescriptor.getMemberScope();
            Object objFirst = CollectionsKt.first((List<? extends Object>) segments);
            Intrinsics.checkNotNullExpressionValue(objFirst, "segments.first()");
            ClassifierDescriptor topLevelClass = memberScope.mo3858getContributedClassifier((Name) objFirst, NoLookupLocation.FROM_DESERIALIZATION);
            if (topLevelClass == null) {
                return null;
            }
            ClassifierDescriptor result = topLevelClass;
            for (Name name : segments.subList(1, segments.size())) {
                if (!(result instanceof ClassDescriptor)) {
                    return null;
                }
                MemberScope unsubstitutedInnerClassesScope = ((ClassDescriptor) result).getUnsubstitutedInnerClassesScope();
                Intrinsics.checkNotNullExpressionValue(name, "name");
                ClassifierDescriptor contributedClassifier = unsubstitutedInnerClassesScope.mo3858getContributedClassifier(name, NoLookupLocation.FROM_DESERIALIZATION);
                ClassDescriptor classDescriptor = contributedClassifier instanceof ClassDescriptor ? (ClassDescriptor) contributedClassifier : null;
                if (classDescriptor == null) {
                    return null;
                }
                result = classDescriptor;
            }
            return result;
        }
        FqName packageFqName2 = classId.getPackageFqName();
        Intrinsics.checkNotNullExpressionValue(packageFqName2, "classId.packageFqName");
        PackageViewDescriptor packageViewDescriptor2 = anchor$iv.getPackage(packageFqName2);
        List segments2 = classId.getRelativeClassName().pathSegments();
        Intrinsics.checkNotNullExpressionValue(segments2, "classId.relativeClassName.pathSegments()");
        MemberScope memberScope2 = packageViewDescriptor2.getMemberScope();
        Object objFirst2 = CollectionsKt.first((List<? extends Object>) segments2);
        Intrinsics.checkNotNullExpressionValue(objFirst2, "segments.first()");
        ClassifierDescriptor topLevelClass2 = memberScope2.mo3858getContributedClassifier((Name) objFirst2, NoLookupLocation.FROM_DESERIALIZATION);
        if (topLevelClass2 != null) {
            ClassifierDescriptor result2 = topLevelClass2;
            Iterator<Name> it = segments2.subList(1, segments2.size()).iterator();
            while (true) {
                if (!it.hasNext()) {
                    classifierDescriptor = result2;
                    break;
                }
                Name name2 = it.next();
                if (!(result2 instanceof ClassDescriptor)) {
                    classifierDescriptor = null;
                    break;
                }
                MemberScope unsubstitutedInnerClassesScope2 = ((ClassDescriptor) result2).getUnsubstitutedInnerClassesScope();
                Intrinsics.checkNotNullExpressionValue(name2, "name");
                ClassifierDescriptor contributedClassifier2 = unsubstitutedInnerClassesScope2.mo3858getContributedClassifier(name2, NoLookupLocation.FROM_DESERIALIZATION);
                ClassDescriptor classDescriptor2 = contributedClassifier2 instanceof ClassDescriptor ? (ClassDescriptor) contributedClassifier2 : null;
                if (classDescriptor2 == null) {
                    classifierDescriptor = null;
                    break;
                }
                result2 = classDescriptor2;
            }
        } else {
            classifierDescriptor = null;
        }
        ClassifierDescriptor classifierDescriptor2 = classifierDescriptor;
        if (classifierDescriptor2 != null) {
            return classifierDescriptor2;
        }
        FqName packageFqName3 = classId.getPackageFqName();
        Intrinsics.checkNotNullExpressionValue(packageFqName3, "classId.packageFqName");
        PackageViewDescriptor packageViewDescriptor3 = $this$findClassifierAcrossModuleDependencies.getPackage(packageFqName3);
        List segments3 = classId.getRelativeClassName().pathSegments();
        Intrinsics.checkNotNullExpressionValue(segments3, "classId.relativeClassName.pathSegments()");
        MemberScope memberScope3 = packageViewDescriptor3.getMemberScope();
        Object objFirst3 = CollectionsKt.first((List<? extends Object>) segments3);
        Intrinsics.checkNotNullExpressionValue(objFirst3, "segments.first()");
        ClassifierDescriptor topLevelClass3 = memberScope3.mo3858getContributedClassifier((Name) objFirst3, NoLookupLocation.FROM_DESERIALIZATION);
        if (topLevelClass3 == null) {
            return null;
        }
        ClassifierDescriptor result3 = topLevelClass3;
        for (Name name3 : segments3.subList(1, segments3.size())) {
            if (!(result3 instanceof ClassDescriptor)) {
                return null;
            }
            MemberScope unsubstitutedInnerClassesScope3 = ((ClassDescriptor) result3).getUnsubstitutedInnerClassesScope();
            Intrinsics.checkNotNullExpressionValue(name3, "name");
            ClassifierDescriptor contributedClassifier3 = unsubstitutedInnerClassesScope3.mo3858getContributedClassifier(name3, NoLookupLocation.FROM_DESERIALIZATION);
            ClassDescriptor classDescriptor3 = contributedClassifier3 instanceof ClassDescriptor ? (ClassDescriptor) contributedClassifier3 : null;
            if (classDescriptor3 == null) {
                return null;
            }
            result3 = classDescriptor3;
        }
        return result3;
    }

    @Nullable
    public static final ClassDescriptor findClassAcrossModuleDependencies(@NotNull ModuleDescriptor $this$findClassAcrossModuleDependencies, @NotNull ClassId classId) {
        Intrinsics.checkNotNullParameter($this$findClassAcrossModuleDependencies, "<this>");
        Intrinsics.checkNotNullParameter(classId, "classId");
        ClassifierDescriptor classifierDescriptorFindClassifierAcrossModuleDependencies = findClassifierAcrossModuleDependencies($this$findClassAcrossModuleDependencies, classId);
        if (classifierDescriptorFindClassifierAcrossModuleDependencies instanceof ClassDescriptor) {
            return (ClassDescriptor) classifierDescriptorFindClassifierAcrossModuleDependencies;
        }
        return null;
    }

    @NotNull
    public static final ClassDescriptor findNonGenericClassAcrossDependencies(@NotNull ModuleDescriptor $this$findNonGenericClassAcrossDependencies, @NotNull ClassId classId, @NotNull NotFoundClasses notFoundClasses) {
        Intrinsics.checkNotNullParameter($this$findNonGenericClassAcrossDependencies, "<this>");
        Intrinsics.checkNotNullParameter(classId, "classId");
        Intrinsics.checkNotNullParameter(notFoundClasses, "notFoundClasses");
        ClassDescriptor existingClass = findClassAcrossModuleDependencies($this$findNonGenericClassAcrossDependencies, classId);
        if (existingClass != null) {
            return existingClass;
        }
        List typeParametersCount = SequencesKt.toList(SequencesKt.map(SequencesKt.generateSequence(classId, FindClassInModuleKt$findNonGenericClassAcrossDependencies$typeParametersCount$1.INSTANCE), new Function1<ClassId, Integer>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt$findNonGenericClassAcrossDependencies$typeParametersCount$2
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final int invoke2(@NotNull ClassId it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return 0;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(ClassId classId2) {
                return Integer.valueOf(invoke2(classId2));
            }
        }));
        return notFoundClasses.getClass(classId, typeParametersCount);
    }

    @Nullable
    public static final TypeAliasDescriptor findTypeAliasAcrossModuleDependencies(@NotNull ModuleDescriptor $this$findTypeAliasAcrossModuleDependencies, @NotNull ClassId classId) {
        Intrinsics.checkNotNullParameter($this$findTypeAliasAcrossModuleDependencies, "<this>");
        Intrinsics.checkNotNullParameter(classId, "classId");
        ClassifierDescriptor classifierDescriptorFindClassifierAcrossModuleDependencies = findClassifierAcrossModuleDependencies($this$findTypeAliasAcrossModuleDependencies, classId);
        if (classifierDescriptorFindClassifierAcrossModuleDependencies instanceof TypeAliasDescriptor) {
            return (TypeAliasDescriptor) classifierDescriptorFindClassifierAcrossModuleDependencies;
        }
        return null;
    }
}
