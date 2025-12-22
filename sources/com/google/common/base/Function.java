package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

@FunctionalInterface
@GwtCompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/base/Function.class */
public interface Function<F, T> extends java.util.function.Function<F, T> {
    @Override // java.util.function.Function
    @CanIgnoreReturnValue
    T apply(F f);

    boolean equals(Object obj);
}
