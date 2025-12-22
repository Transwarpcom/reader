package io.netty.util;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/Attribute.class */
public interface Attribute<T> {
    AttributeKey<T> key();

    T get();

    void set(T t);

    T getAndSet(T t);

    T setIfAbsent(T t);

    @Deprecated
    T getAndRemove();

    boolean compareAndSet(T t, T t2);

    @Deprecated
    void remove();
}
