package cn.hutool.core.lang;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/Chain.class */
public interface Chain<E, T> extends Iterable<E> {
    T addChain(E e);
}
