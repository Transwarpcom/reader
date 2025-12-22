package kotlin.reflect.jvm.internal.impl.resolve.jvm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;

/* compiled from: SyntheticJavaPartsProvider.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/jvm/CompositeSyntheticJavaPartsProvider.class */
public final class CompositeSyntheticJavaPartsProvider implements SyntheticJavaPartsProvider {

    @NotNull
    private final List<SyntheticJavaPartsProvider> inner;

    /* JADX WARN: Multi-variable type inference failed */
    public CompositeSyntheticJavaPartsProvider(@NotNull List<? extends SyntheticJavaPartsProvider> inner) {
        Intrinsics.checkNotNullParameter(inner, "inner");
        this.inner = inner;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.jvm.SyntheticJavaPartsProvider
    @NotNull
    public List<Name> getMethodNames(@NotNull ClassDescriptor thisDescriptor) {
        Intrinsics.checkNotNullParameter(thisDescriptor, "thisDescriptor");
        Iterable $this$flatMap$iv = this.inner;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$flatMap$iv) {
            SyntheticJavaPartsProvider it = (SyntheticJavaPartsProvider) element$iv$iv;
            Iterable list$iv$iv = it.getMethodNames(thisDescriptor);
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
        }
        return (List) destination$iv$iv;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.jvm.SyntheticJavaPartsProvider
    public void generateMethods(@NotNull ClassDescriptor thisDescriptor, @NotNull Name name, @NotNull Collection<SimpleFunctionDescriptor> result) {
        Intrinsics.checkNotNullParameter(thisDescriptor, "thisDescriptor");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(result, "result");
        Iterable $this$forEach$iv = this.inner;
        for (Object element$iv : $this$forEach$iv) {
            SyntheticJavaPartsProvider it = (SyntheticJavaPartsProvider) element$iv;
            it.generateMethods(thisDescriptor, name, result);
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.jvm.SyntheticJavaPartsProvider
    @NotNull
    public List<Name> getStaticFunctionNames(@NotNull ClassDescriptor thisDescriptor) {
        Intrinsics.checkNotNullParameter(thisDescriptor, "thisDescriptor");
        Iterable $this$flatMap$iv = this.inner;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$flatMap$iv) {
            SyntheticJavaPartsProvider it = (SyntheticJavaPartsProvider) element$iv$iv;
            Iterable list$iv$iv = it.getStaticFunctionNames(thisDescriptor);
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
        }
        return (List) destination$iv$iv;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.jvm.SyntheticJavaPartsProvider
    public void generateStaticFunctions(@NotNull ClassDescriptor thisDescriptor, @NotNull Name name, @NotNull Collection<SimpleFunctionDescriptor> result) {
        Intrinsics.checkNotNullParameter(thisDescriptor, "thisDescriptor");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(result, "result");
        Iterable $this$forEach$iv = this.inner;
        for (Object element$iv : $this$forEach$iv) {
            SyntheticJavaPartsProvider it = (SyntheticJavaPartsProvider) element$iv;
            it.generateStaticFunctions(thisDescriptor, name, result);
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.jvm.SyntheticJavaPartsProvider
    public void generateConstructors(@NotNull ClassDescriptor thisDescriptor, @NotNull List<ClassConstructorDescriptor> result) {
        Intrinsics.checkNotNullParameter(thisDescriptor, "thisDescriptor");
        Intrinsics.checkNotNullParameter(result, "result");
        Iterable $this$forEach$iv = this.inner;
        for (Object element$iv : $this$forEach$iv) {
            SyntheticJavaPartsProvider it = (SyntheticJavaPartsProvider) element$iv;
            it.generateConstructors(thisDescriptor, result);
        }
    }
}
