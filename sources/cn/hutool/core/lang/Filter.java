package cn.hutool.core.lang;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/Filter.class */
public interface Filter<T> {
    boolean accept(T t);
}
