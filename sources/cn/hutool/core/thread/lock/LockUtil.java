package cn.hutool.core.thread.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/thread/lock/LockUtil.class */
public class LockUtil {
    private static final NoLock NO_LOCK = new NoLock();

    public static StampedLock createStampLock() {
        return new StampedLock();
    }

    public static ReentrantReadWriteLock createReadWriteLock(boolean fair) {
        return new ReentrantReadWriteLock(fair);
    }

    public static NoLock getNoLock() {
        return NO_LOCK;
    }
}
