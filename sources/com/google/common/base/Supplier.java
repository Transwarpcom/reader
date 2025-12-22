package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

@FunctionalInterface
@GwtCompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/base/Supplier.class */
public interface Supplier<T> extends java.util.function.Supplier<T> {
    @Override // java.util.function.Supplier
    @CanIgnoreReturnValue
    T get();
}
