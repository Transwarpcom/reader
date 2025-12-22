package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: ModalityUtils.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/ModalityUtilsKt.class */
public final class ModalityUtilsKt {
    public static final boolean isFinalClass(@NotNull ClassDescriptor $this$isFinalClass) {
        Intrinsics.checkNotNullParameter($this$isFinalClass, "<this>");
        return $this$isFinalClass.getModality() == Modality.FINAL && $this$isFinalClass.getKind() != ClassKind.ENUM_CLASS;
    }
}
