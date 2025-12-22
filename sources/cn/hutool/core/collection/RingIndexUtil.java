package cn.hutool.core.collection;

import cn.hutool.core.lang.Assert;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/collection/RingIndexUtil.class */
public class RingIndexUtil {
    public static int ringNextIntByObj(Object object, AtomicInteger atomicInteger) throws IllegalArgumentException {
        Assert.notNull(object);
        int modulo = CollUtil.size(object);
        return ringNextInt(modulo, atomicInteger);
    }

    public static int ringNextInt(int modulo, AtomicInteger atomicInteger) throws Throwable {
        int current;
        int next;
        Assert.notNull(atomicInteger);
        Assert.isTrue(modulo > 0);
        if (modulo <= 1) {
            return 0;
        }
        do {
            current = atomicInteger.get();
            next = (current + 1) % modulo;
        } while (!atomicInteger.compareAndSet(current, next));
        return next;
    }

    public static long ringNextLong(long modulo, AtomicLong atomicLong) throws Throwable {
        long current;
        long next;
        Assert.notNull(atomicLong);
        Assert.isTrue(modulo > 0);
        if (modulo <= 1) {
            return 0L;
        }
        do {
            current = atomicLong.get();
            next = (current + 1) % modulo;
        } while (!atomicLong.compareAndSet(current, next));
        return next;
    }
}
