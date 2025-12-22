package kotlin.reflect.jvm.internal.impl.resolve.deprecation;

import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import org.jetbrains.annotations.NotNull;

/* compiled from: deprecation.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/deprecation/DeprecationKt.class */
public final class DeprecationKt {

    @NotNull
    private static final CallableDescriptor.UserDataKey<Object> DEPRECATED_FUNCTION_KEY = new CallableDescriptor.UserDataKey<Object>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.deprecation.DeprecationKt$DEPRECATED_FUNCTION_KEY$1
    };

    @NotNull
    public static final CallableDescriptor.UserDataKey<Object> getDEPRECATED_FUNCTION_KEY() {
        return DEPRECATED_FUNCTION_KEY;
    }
}
