package kotlin.reflect.jvm.internal.impl.platform;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: platformUtil.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/platform/PlatformUtilKt.class */
public final class PlatformUtilKt {
    @NotNull
    public static final String getPresentableDescription(@NotNull TargetPlatform $this$presentableDescription) {
        Intrinsics.checkNotNullParameter($this$presentableDescription, "<this>");
        return CollectionsKt.joinToString$default($this$presentableDescription.getComponentPlatforms(), "/", null, null, 0, null, null, 62, null);
    }
}
