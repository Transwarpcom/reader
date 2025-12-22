package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import java.time.Duration;

@GwtIncompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/util/concurrent/Internal.class */
final class Internal {
    static long saturatedToNanos(Duration duration) {
        try {
            return duration.toNanos();
        } catch (ArithmeticException e) {
            return duration.isNegative() ? Long.MIN_VALUE : Long.MAX_VALUE;
        }
    }

    private Internal() {
    }
}
