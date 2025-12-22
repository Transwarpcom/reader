package cn.hutool.core.lang.intern;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/intern/InternUtil.class */
public class InternUtil {
    public static <T> Interner<T> createWeakInterner() {
        return new WeakInterner();
    }

    public static Interner<String> createJdkInterner() {
        return new JdkStringInterner();
    }

    public static Interner<String> createStringInterner(boolean isWeak) {
        return isWeak ? createWeakInterner() : createJdkInterner();
    }
}
