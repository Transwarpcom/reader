package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.JavaDescriptorUtilKt;
import kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: FieldOverridabilityCondition.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/FieldOverridabilityCondition.class */
public final class FieldOverridabilityCondition implements ExternalOverridabilityCondition {
    @Override // kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition
    @NotNull
    public ExternalOverridabilityCondition.Result isOverridable(@NotNull CallableDescriptor superDescriptor, @NotNull CallableDescriptor subDescriptor, @Nullable ClassDescriptor subClassDescriptor) {
        Intrinsics.checkNotNullParameter(superDescriptor, "superDescriptor");
        Intrinsics.checkNotNullParameter(subDescriptor, "subDescriptor");
        return ((subDescriptor instanceof PropertyDescriptor) && (superDescriptor instanceof PropertyDescriptor)) ? !Intrinsics.areEqual(((PropertyDescriptor) subDescriptor).getName(), ((PropertyDescriptor) superDescriptor).getName()) ? ExternalOverridabilityCondition.Result.UNKNOWN : (JavaDescriptorUtilKt.isJavaField((PropertyDescriptor) subDescriptor) && JavaDescriptorUtilKt.isJavaField((PropertyDescriptor) superDescriptor)) ? ExternalOverridabilityCondition.Result.OVERRIDABLE : (JavaDescriptorUtilKt.isJavaField((PropertyDescriptor) subDescriptor) || JavaDescriptorUtilKt.isJavaField((PropertyDescriptor) superDescriptor)) ? ExternalOverridabilityCondition.Result.INCOMPATIBLE : ExternalOverridabilityCondition.Result.UNKNOWN : ExternalOverridabilityCondition.Result.UNKNOWN;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition
    @NotNull
    public ExternalOverridabilityCondition.Contract getContract() {
        return ExternalOverridabilityCondition.Contract.BOTH;
    }
}
