package kotlin.reflect.jvm.internal.impl.builtins.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.BuiltInsPackageFragment;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionInterfacePackageFragment;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BuiltInFictitiousFunctionClassFactory.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/builtins/functions/BuiltInFictitiousFunctionClassFactory.class */
public final class BuiltInFictitiousFunctionClassFactory implements ClassDescriptorFactory {

    @NotNull
    private final StorageManager storageManager;

    @NotNull
    private final ModuleDescriptor module;

    public BuiltInFictitiousFunctionClassFactory(@NotNull StorageManager storageManager, @NotNull ModuleDescriptor module) {
        Intrinsics.checkNotNullParameter(storageManager, "storageManager");
        Intrinsics.checkNotNullParameter(module, "module");
        this.storageManager = storageManager;
        this.module = module;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory
    public boolean shouldCreateClass(@NotNull FqName packageFqName, @NotNull Name name) {
        Intrinsics.checkNotNullParameter(packageFqName, "packageFqName");
        Intrinsics.checkNotNullParameter(name, "name");
        String string = name.asString();
        Intrinsics.checkNotNullExpressionValue(string, "name.asString()");
        return (StringsKt.startsWith$default(string, "Function", false, 2, (Object) null) || StringsKt.startsWith$default(string, "KFunction", false, 2, (Object) null) || StringsKt.startsWith$default(string, "SuspendFunction", false, 2, (Object) null) || StringsKt.startsWith$default(string, "KSuspendFunction", false, 2, (Object) null)) && FunctionClassKind.Companion.parseClassName(string, packageFqName) != null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory
    @Nullable
    public ClassDescriptor createClass(@NotNull ClassId classId) {
        Intrinsics.checkNotNullParameter(classId, "classId");
        if (classId.isLocal() || classId.isNestedClass()) {
            return null;
        }
        String className = classId.getRelativeClassName().asString();
        Intrinsics.checkNotNullExpressionValue(className, "classId.relativeClassName.asString()");
        if (!StringsKt.contains$default((CharSequence) className, (CharSequence) "Function", false, 2, (Object) null)) {
            return null;
        }
        FqName packageFqName = classId.getPackageFqName();
        Intrinsics.checkNotNullExpressionValue(packageFqName, "classId.packageFqName");
        FunctionClassKind.Companion.KindWithArity className2 = FunctionClassKind.Companion.parseClassName(className, packageFqName);
        if (className2 == null) {
            return null;
        }
        FunctionClassKind kind = className2.component1();
        int arity = className2.component2();
        Iterable $this$filterIsInstance$iv = this.module.getPackage(packageFqName).getFragments();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterIsInstance$iv) {
            if (element$iv$iv instanceof BuiltInsPackageFragment) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List builtInsFragments = (List) destination$iv$iv;
        List $this$filterIsInstance$iv2 = builtInsFragments;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv2 : $this$filterIsInstance$iv2) {
            if (element$iv$iv2 instanceof FunctionInterfacePackageFragment) {
                destination$iv$iv2.add(element$iv$iv2);
            }
        }
        BuiltInsPackageFragment builtInsPackageFragment = (FunctionInterfacePackageFragment) CollectionsKt.firstOrNull((List) destination$iv$iv2);
        BuiltInsPackageFragment containingPackageFragment = builtInsPackageFragment == null ? (BuiltInsPackageFragment) CollectionsKt.first(builtInsFragments) : builtInsPackageFragment;
        return new FunctionClassDescriptor(this.storageManager, containingPackageFragment, kind, arity);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory
    @NotNull
    public Collection<ClassDescriptor> getAllContributedClassesIfPossible(@NotNull FqName packageFqName) {
        Intrinsics.checkNotNullParameter(packageFqName, "packageFqName");
        return SetsKt.emptySet();
    }
}
