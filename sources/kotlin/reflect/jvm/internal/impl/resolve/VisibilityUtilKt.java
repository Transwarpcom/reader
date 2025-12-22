package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collection;
import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import org.jetbrains.annotations.NotNull;

/* compiled from: VisibilityUtil.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/VisibilityUtilKt.class */
public final class VisibilityUtilKt {
    @NotNull
    public static final CallableMemberDescriptor findMemberWithMaxVisibility(@NotNull Collection<? extends CallableMemberDescriptor> descriptors) {
        Intrinsics.checkNotNullParameter(descriptors, "descriptors");
        boolean z = !descriptors.isEmpty();
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Assertion failed");
        }
        CallableMemberDescriptor descriptor = null;
        for (CallableMemberDescriptor candidate : descriptors) {
            if (descriptor == null) {
                descriptor = candidate;
            } else {
                Integer result = DescriptorVisibilities.compare(descriptor.getVisibility(), candidate.getVisibility());
                if (result != null && result.intValue() < 0) {
                    descriptor = candidate;
                }
            }
        }
        Intrinsics.checkNotNull(descriptor);
        return descriptor;
    }
}
