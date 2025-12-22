package kotlinx.coroutines.debug;

import android.annotation.SuppressLint;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sun.misc.Signal;

/* compiled from: AgentPremain.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018��2\u00020\u0001:\u0001\u0010B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u001a\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010\u0005\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lkotlinx/coroutines/debug/AgentPremain;", "", "()V", "enableCreationStackTraces", "", "isInstalledStatically", "()Z", "setInstalledStatically", "(Z)V", "installSignalHandler", "", "premain", "args", "", "instrumentation", "Ljava/lang/instrument/Instrumentation;", "DebugProbesTransformer", "kotlinx-coroutines-core"})
@SuppressLint({"all"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/debug/AgentPremain.class */
public final class AgentPremain {

    @NotNull
    public static final AgentPremain INSTANCE = new AgentPremain();
    private static boolean isInstalledStatically;
    private static final boolean enableCreationStackTraces;

    private AgentPremain() {
    }

    public final boolean isInstalledStatically() {
        return isInstalledStatically;
    }

    public final void setInstalledStatically(boolean z) {
        isInstalledStatically = z;
    }

    static {
        Object objM2105constructorimpl;
        AgentPremain agentPremain = INSTANCE;
        try {
            Result.Companion companion = Result.Companion;
            String property = System.getProperty("kotlinx.coroutines.debug.enable.creation.stack.trace");
            objM2105constructorimpl = Result.m2105constructorimpl(property == null ? null : Boolean.valueOf(Boolean.parseBoolean(property)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            objM2105constructorimpl = Result.m2105constructorimpl(ResultKt.createFailure(th));
        }
        Object obj = objM2105constructorimpl;
        Boolean bool = (Boolean) (Result.m2101isFailureimpl(obj) ? null : obj);
        enableCreationStackTraces = bool == null ? DebugProbesImpl.INSTANCE.getEnableCreationStackTraces() : bool.booleanValue();
    }

    @JvmStatic
    public static final void premain(@Nullable String args, @NotNull Instrumentation instrumentation) {
        AgentPremain agentPremain = INSTANCE;
        isInstalledStatically = true;
        instrumentation.addTransformer(DebugProbesTransformer.INSTANCE);
        DebugProbesImpl.INSTANCE.setEnableCreationStackTraces(enableCreationStackTraces);
        DebugProbesImpl.INSTANCE.install();
        INSTANCE.installSignalHandler();
    }

    /* compiled from: AgentPremain.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J:\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0004H\u0016¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/debug/AgentPremain$DebugProbesTransformer;", "Ljava/lang/instrument/ClassFileTransformer;", "()V", "transform", "", "loader", "Ljava/lang/ClassLoader;", "className", "", "classBeingRedefined", "Ljava/lang/Class;", "protectionDomain", "Ljava/security/ProtectionDomain;", "classfileBuffer", "kotlinx-coroutines-core"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/debug/AgentPremain$DebugProbesTransformer.class */
    public static final class DebugProbesTransformer implements ClassFileTransformer {

        @NotNull
        public static final DebugProbesTransformer INSTANCE = new DebugProbesTransformer();

        private DebugProbesTransformer() {
        }

        @Nullable
        public byte[] transform(@NotNull ClassLoader loader, @NotNull String className, @Nullable Class<?> cls, @NotNull ProtectionDomain protectionDomain, @Nullable byte[] classfileBuffer) {
            if (!Intrinsics.areEqual(className, "kotlin/coroutines/jvm/internal/DebugProbesKt")) {
                return null;
            }
            AgentPremain.INSTANCE.setInstalledStatically(true);
            return ByteStreamsKt.readBytes(loader.getResourceAsStream("DebugProbesKt.bin"));
        }
    }

    private final void installSignalHandler() {
        try {
            Signal.handle(new Signal("TRAP"), AgentPremain::m4246installSignalHandler$lambda1);
        } catch (Throwable th) {
        }
    }

    /* renamed from: installSignalHandler$lambda-1, reason: not valid java name */
    private static final void m4246installSignalHandler$lambda1(Signal it) {
        if (DebugProbesImpl.INSTANCE.isInstalled$kotlinx_coroutines_core()) {
            DebugProbesImpl.INSTANCE.dumpCoroutines(System.out);
        } else {
            System.out.println((Object) "Cannot perform coroutines dump, debug probes are disabled");
        }
    }
}
