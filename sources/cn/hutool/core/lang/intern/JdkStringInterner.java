package cn.hutool.core.lang.intern;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/intern/JdkStringInterner.class */
public class JdkStringInterner implements Interner<String> {
    @Override // cn.hutool.core.lang.intern.Interner
    public String intern(String sample) {
        if (null == sample) {
            return null;
        }
        return sample.intern();
    }
}
