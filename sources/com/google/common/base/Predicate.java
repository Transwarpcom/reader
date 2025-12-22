package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

@FunctionalInterface
@GwtCompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/base/Predicate.class */
public interface Predicate<T> extends java.util.function.Predicate<T> {
    @CanIgnoreReturnValue
    boolean apply(T t);

    boolean equals(Object obj);

    @Override // java.util.function.Predicate
    default boolean test(T input) {
        return apply(input);
    }
}
