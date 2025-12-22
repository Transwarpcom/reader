package io.netty.util;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/AttributeMap.class */
public interface AttributeMap {
    <T> Attribute<T> attr(AttributeKey<T> attributeKey);

    <T> boolean hasAttr(AttributeKey<T> attributeKey);
}
