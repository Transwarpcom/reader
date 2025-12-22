package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import org.jetbrains.annotations.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/VariableDescriptor.class */
public interface VariableDescriptor extends ValueDescriptor {
    boolean isVar();

    @Nullable
    /* renamed from: getCompileTimeInitializer */
    ConstantValue<?> mo3574getCompileTimeInitializer();

    boolean isConst();

    boolean isLateInit();
}
