package kotlin.reflect.jvm.internal.impl.util;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: modifierChecks.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/util/Check.class */
public interface Check {
    @NotNull
    String getDescription();

    boolean check(@NotNull FunctionDescriptor functionDescriptor);

    @Nullable
    String invoke(@NotNull FunctionDescriptor functionDescriptor);

    /* compiled from: modifierChecks.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/util/Check$DefaultImpls.class */
    public static final class DefaultImpls {
        @Nullable
        public static String invoke(@NotNull Check check, @NotNull FunctionDescriptor functionDescriptor) {
            Intrinsics.checkNotNullParameter(check, "this");
            Intrinsics.checkNotNullParameter(functionDescriptor, "functionDescriptor");
            if (check.check(functionDescriptor)) {
                return null;
            }
            return check.getDescription();
        }
    }
}
