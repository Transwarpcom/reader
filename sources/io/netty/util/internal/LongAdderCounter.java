package io.netty.util.internal;

import java.util.concurrent.atomic.LongAdder;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/internal/LongAdderCounter.class */
final class LongAdderCounter extends LongAdder implements LongCounter {
    LongAdderCounter() {
    }

    @Override // io.netty.util.internal.LongCounter
    public long value() {
        return longValue();
    }
}
