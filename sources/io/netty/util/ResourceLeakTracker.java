package io.netty.util;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/ResourceLeakTracker.class */
public interface ResourceLeakTracker<T> {
    void record();

    void record(Object obj);

    boolean close(T t);
}
