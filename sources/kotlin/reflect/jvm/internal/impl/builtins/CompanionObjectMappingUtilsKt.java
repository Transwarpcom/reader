package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: CompanionObjectMappingUtils.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/builtins/CompanionObjectMappingUtilsKt.class */
public final class CompanionObjectMappingUtilsKt {
    public static final boolean isMappedIntrinsicCompanionObject(@NotNull CompanionObjectMapping $this$isMappedIntrinsicCompanionObject, @NotNull ClassDescriptor classDescriptor) {
        Intrinsics.checkNotNullParameter($this$isMappedIntrinsicCompanionObject, "<this>");
        Intrinsics.checkNotNullParameter(classDescriptor, "classDescriptor");
        if (DescriptorUtils.isCompanionObject(classDescriptor)) {
            Set<ClassId> classIds = $this$isMappedIntrinsicCompanionObject.getClassIds();
            ClassId classId = DescriptorUtilsKt.getClassId(classDescriptor);
            if (CollectionsKt.contains(classIds, classId == null ? null : classId.getOuterClassId())) {
                return true;
            }
        }
        return false;
    }
}
