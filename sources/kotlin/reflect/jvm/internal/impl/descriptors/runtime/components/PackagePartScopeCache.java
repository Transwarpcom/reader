package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.EmptyPackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializedDescriptorResolver;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinderKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ChainedMemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import org.jetbrains.annotations.NotNull;

/* compiled from: PackagePartScopeCache.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/runtime/components/PackagePartScopeCache.class */
public final class PackagePartScopeCache {

    @NotNull
    private final DeserializedDescriptorResolver resolver;

    @NotNull
    private final ReflectKotlinClassFinder kotlinClassFinder;

    @NotNull
    private final ConcurrentHashMap<ClassId, MemberScope> cache;

    public PackagePartScopeCache(@NotNull DeserializedDescriptorResolver resolver, @NotNull ReflectKotlinClassFinder kotlinClassFinder) {
        Intrinsics.checkNotNullParameter(resolver, "resolver");
        Intrinsics.checkNotNullParameter(kotlinClassFinder, "kotlinClassFinder");
        this.resolver = resolver;
        this.kotlinClassFinder = kotlinClassFinder;
        this.cache = new ConcurrentHashMap<>();
    }

    @NotNull
    public final MemberScope getPackagePartScope(@NotNull ReflectKotlinClass fileClass) {
        Iterable iterableListOf;
        Intrinsics.checkNotNullParameter(fileClass, "fileClass");
        ConcurrentMap $this$getOrPut$iv = this.cache;
        ClassId classId = fileClass.getClassId();
        MemberScope memberScopePutIfAbsent = $this$getOrPut$iv.get(classId);
        if (memberScopePutIfAbsent == null) {
            FqName fqName = fileClass.getClassId().getPackageFqName();
            Intrinsics.checkNotNullExpressionValue(fqName, "fileClass.classId.packageFqName");
            if (fileClass.getClassHeader().getKind() == KotlinClassHeader.Kind.MULTIFILE_CLASS) {
                Iterable $this$mapNotNull$iv = fileClass.getClassHeader().getMultifilePartNames();
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                    String partName = (String) element$iv$iv$iv;
                    ClassId classId2 = ClassId.topLevel(JvmClassName.byInternalName(partName).getFqNameForTopLevelClassMaybeWithDollars());
                    Intrinsics.checkNotNullExpressionValue(classId2, "topLevel(JvmClassName.byInternalName(partName).fqNameForTopLevelClassMaybeWithDollars)");
                    KotlinJvmBinaryClass kotlinJvmBinaryClassFindKotlinClass = KotlinClassFinderKt.findKotlinClass(this.kotlinClassFinder, classId2);
                    if (kotlinJvmBinaryClassFindKotlinClass != null) {
                        destination$iv$iv.add(kotlinJvmBinaryClassFindKotlinClass);
                    }
                }
                iterableListOf = (List) destination$iv$iv;
            } else {
                iterableListOf = CollectionsKt.listOf(fileClass);
            }
            Iterable parts = iterableListOf;
            EmptyPackageFragmentDescriptor packageFragment = new EmptyPackageFragmentDescriptor(this.resolver.getComponents().getModuleDescriptor(), fqName);
            Iterable $this$mapNotNull$iv2 = parts;
            Collection destination$iv$iv2 = new ArrayList();
            for (Object element$iv$iv$iv2 : $this$mapNotNull$iv2) {
                KotlinJvmBinaryClass part = (KotlinJvmBinaryClass) element$iv$iv$iv2;
                MemberScope memberScopeCreateKotlinPackagePartScope = this.resolver.createKotlinPackagePartScope(packageFragment, part);
                if (memberScopeCreateKotlinPackagePartScope != null) {
                    destination$iv$iv2.add(memberScopeCreateKotlinPackagePartScope);
                }
            }
            List scopes = CollectionsKt.toList((List) destination$iv$iv2);
            MemberScope memberScopeCreate = ChainedMemberScope.Companion.create("package " + fqName + " (" + fileClass + ')', scopes);
            memberScopePutIfAbsent = $this$getOrPut$iv.putIfAbsent(classId, memberScopeCreate);
            if (memberScopePutIfAbsent == null) {
                memberScopePutIfAbsent = memberScopeCreate;
            }
        }
        MemberScope memberScope = memberScopePutIfAbsent;
        Intrinsics.checkNotNullExpressionValue(memberScope, "cache.getOrPut(fileClass.classId) {\n        val fqName = fileClass.classId.packageFqName\n\n        val parts =\n            if (fileClass.classHeader.kind == KotlinClassHeader.Kind.MULTIFILE_CLASS)\n                fileClass.classHeader.multifilePartNames.mapNotNull { partName ->\n                    val classId = ClassId.topLevel(JvmClassName.byInternalName(partName).fqNameForTopLevelClassMaybeWithDollars)\n                    kotlinClassFinder.findKotlinClass(classId)\n                }\n            else listOf(fileClass)\n\n        val packageFragment = EmptyPackageFragmentDescriptor(resolver.components.moduleDescriptor, fqName)\n\n        val scopes = parts.mapNotNull { part ->\n            resolver.createKotlinPackagePartScope(packageFragment, part)\n        }.toList()\n\n        ChainedMemberScope.create(\"package $fqName ($fileClass)\", scopes)\n    }");
        return memberScope;
    }
}
