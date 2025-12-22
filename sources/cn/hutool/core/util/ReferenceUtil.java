package cn.hutool.core.util;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/util/ReferenceUtil.class */
public class ReferenceUtil {

    /* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/util/ReferenceUtil$ReferenceType.class */
    public enum ReferenceType {
        SOFT,
        WEAK,
        PHANTOM
    }

    public static <T> Reference<T> create(ReferenceType type, T referent) {
        return create(type, referent, null);
    }

    public static <T> Reference<T> create(ReferenceType type, T referent, ReferenceQueue<T> queue) {
        switch (type) {
            case SOFT:
                return new SoftReference(referent);
            case WEAK:
                return new WeakReference(referent);
            case PHANTOM:
                return new PhantomReference(referent, queue);
            default:
                return null;
        }
    }
}
