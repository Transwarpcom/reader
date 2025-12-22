package io.netty.util;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/Mapping.class */
public interface Mapping<IN, OUT> {
    OUT map(IN in);
}
