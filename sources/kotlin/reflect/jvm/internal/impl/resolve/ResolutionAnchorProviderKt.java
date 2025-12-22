package kotlin.reflect.jvm.internal.impl.resolve;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleCapability;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ResolutionAnchorProvider.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/ResolutionAnchorProviderKt.class */
public final class ResolutionAnchorProviderKt {

    @NotNull
    private static final ModuleCapability<ResolutionAnchorProvider> RESOLUTION_ANCHOR_PROVIDER_CAPABILITY = new ModuleCapability<>("ResolutionAnchorProvider");

    @Nullable
    public static final ModuleDescriptor getResolutionAnchorIfAny(@NotNull ModuleDescriptor $this$getResolutionAnchorIfAny) {
        Intrinsics.checkNotNullParameter($this$getResolutionAnchorIfAny, "<this>");
        ResolutionAnchorProvider resolutionAnchorProvider = (ResolutionAnchorProvider) $this$getResolutionAnchorIfAny.getCapability(RESOLUTION_ANCHOR_PROVIDER_CAPABILITY);
        if (resolutionAnchorProvider == null) {
            return null;
        }
        return resolutionAnchorProvider.getResolutionAnchor($this$getResolutionAnchorIfAny);
    }
}
