package cn.hutool.core.lang.hash;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/hash/Hash.class */
public interface Hash<T> {
    Number hash(T t);
}
