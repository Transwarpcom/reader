package org.springframework.util.function;

import java.util.function.Supplier;
import org.springframework.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/util/function/SupplierUtils.class */
public abstract class SupplierUtils {
    @Nullable
    public static <T> T resolve(@Nullable Supplier<T> supplier) {
        if (supplier != null) {
            return supplier.get();
        }
        return null;
    }
}
