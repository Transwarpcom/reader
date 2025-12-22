package kotlin.reflect.jvm.internal.impl.load.java.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Pair;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: util.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/descriptors/UtilKt.class */
public final class UtilKt {
    @NotNull
    public static final List<ValueParameterDescriptor> copyValueParameters(@NotNull Collection<ValueParameterData> newValueParametersTypes, @NotNull Collection<? extends ValueParameterDescriptor> oldValueParameters, @NotNull CallableDescriptor newOwner) {
        Intrinsics.checkNotNullParameter(newValueParametersTypes, "newValueParametersTypes");
        Intrinsics.checkNotNullParameter(oldValueParameters, "oldValueParameters");
        Intrinsics.checkNotNullParameter(newOwner, "newOwner");
        boolean z = newValueParametersTypes.size() == oldValueParameters.size();
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Different value parameters sizes: Enhanced = " + newValueParametersTypes.size() + ", Old = " + oldValueParameters.size());
        }
        Iterable $this$map$iv = CollectionsKt.zip(newValueParametersTypes, oldValueParameters);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            Pair $dstr$newParameter$oldParameter = (Pair) item$iv$iv;
            ValueParameterData newParameter = (ValueParameterData) $dstr$newParameter$oldParameter.component1();
            ValueParameterDescriptor oldParameter = (ValueParameterDescriptor) $dstr$newParameter$oldParameter.component2();
            int index = oldParameter.getIndex();
            Annotations annotations = oldParameter.getAnnotations();
            Name name = oldParameter.getName();
            Intrinsics.checkNotNullExpressionValue(name, "oldParameter.name");
            KotlinType type = newParameter.getType();
            boolean hasDefaultValue = newParameter.getHasDefaultValue();
            boolean zIsCrossinline = oldParameter.isCrossinline();
            boolean zIsNoinline = oldParameter.isNoinline();
            KotlinType arrayElementType = oldParameter.getVarargElementType() != null ? DescriptorUtilsKt.getModule(newOwner).getBuiltIns().getArrayElementType(newParameter.getType()) : null;
            SourceElement source = oldParameter.getSource();
            Intrinsics.checkNotNullExpressionValue(source, "oldParameter.source");
            destination$iv$iv.add(new ValueParameterDescriptorImpl(newOwner, null, index, annotations, name, type, hasDefaultValue, zIsCrossinline, zIsNoinline, arrayElementType, source));
        }
        return (List) destination$iv$iv;
    }

    @Nullable
    public static final LazyJavaStaticClassScope getParentJavaStaticClassScope(@NotNull ClassDescriptor $this$getParentJavaStaticClassScope) {
        Intrinsics.checkNotNullParameter($this$getParentJavaStaticClassScope, "<this>");
        ClassDescriptor superClassDescriptor = DescriptorUtilsKt.getSuperClassNotAny($this$getParentJavaStaticClassScope);
        if (superClassDescriptor == null) {
            return null;
        }
        MemberScope staticScope = superClassDescriptor.getStaticScope();
        LazyJavaStaticClassScope lazyJavaStaticClassScope = staticScope instanceof LazyJavaStaticClassScope ? (LazyJavaStaticClassScope) staticScope : null;
        return lazyJavaStaticClassScope == null ? getParentJavaStaticClassScope(superClassDescriptor) : lazyJavaStaticClassScope;
    }
}
