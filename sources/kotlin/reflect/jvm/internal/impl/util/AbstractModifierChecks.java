package kotlin.reflect.jvm.internal.impl.util;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.util.CheckResult;
import org.jetbrains.annotations.NotNull;

/* compiled from: modifierChecks.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/util/AbstractModifierChecks.class */
public abstract class AbstractModifierChecks {
    @NotNull
    public abstract List<Checks> getChecks$descriptors();

    @NotNull
    public final CheckResult check(@NotNull FunctionDescriptor functionDescriptor) {
        Intrinsics.checkNotNullParameter(functionDescriptor, "functionDescriptor");
        for (Checks check : getChecks$descriptors()) {
            if (check.isApplicable(functionDescriptor)) {
                return check.checkAll(functionDescriptor);
            }
        }
        return CheckResult.IllegalFunctionName.INSTANCE;
    }
}
