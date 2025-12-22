package io.netty.util;

@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/ResourceLeak.class */
public interface ResourceLeak {
    void record();

    void record(Object obj);

    boolean close();
}
