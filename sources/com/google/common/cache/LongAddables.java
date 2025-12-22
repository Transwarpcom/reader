package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Supplier;
import java.util.concurrent.atomic.AtomicLong;

@GwtCompatible(emulated = true)
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/cache/LongAddables.class */
final class LongAddables {
    private static final Supplier<LongAddable> SUPPLIER;

    LongAddables() {
    }

    static {
        Supplier<LongAddable> supplier;
        try {
            new LongAdder();
            supplier = new Supplier<LongAddable>() { // from class: com.google.common.cache.LongAddables.1
                @Override // com.google.common.base.Supplier, java.util.function.Supplier
                public LongAddable get() {
                    return new LongAdder();
                }
            };
        } catch (Throwable th) {
            supplier = new Supplier<LongAddable>() { // from class: com.google.common.cache.LongAddables.2
                @Override // com.google.common.base.Supplier, java.util.function.Supplier
                public LongAddable get() {
                    return new PureJavaLongAddable();
                }
            };
        }
        SUPPLIER = supplier;
    }

    public static LongAddable create() {
        return SUPPLIER.get();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/cache/LongAddables$PureJavaLongAddable.class */
    private static final class PureJavaLongAddable extends AtomicLong implements LongAddable {
        private PureJavaLongAddable() {
        }

        @Override // com.google.common.cache.LongAddable
        public void increment() {
            getAndIncrement();
        }

        @Override // com.google.common.cache.LongAddable
        public void add(long x) {
            getAndAdd(x);
        }

        @Override // com.google.common.cache.LongAddable
        public long sum() {
            return get();
        }
    }
}
