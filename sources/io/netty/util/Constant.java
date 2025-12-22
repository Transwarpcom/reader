package io.netty.util;

import io.netty.util.Constant;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/Constant.class */
public interface Constant<T extends Constant<T>> extends Comparable<T> {
    int id();

    String name();
}
