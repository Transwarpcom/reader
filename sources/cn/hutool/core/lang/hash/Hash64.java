package cn.hutool.core.lang.hash;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/hash/Hash64.class */
public interface Hash64<T> extends Hash<T> {
    long hash64(T t);

    @Override // cn.hutool.core.lang.hash.Hash
    default Number hash(T t) {
        return Long.valueOf(hash64(t));
    }
}
