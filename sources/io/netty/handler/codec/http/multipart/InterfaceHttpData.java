package io.netty.handler.codec.http.multipart;

import io.netty.util.ReferenceCounted;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/http/multipart/InterfaceHttpData.class */
public interface InterfaceHttpData extends Comparable<InterfaceHttpData>, ReferenceCounted {

    /* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/http/multipart/InterfaceHttpData$HttpDataType.class */
    public enum HttpDataType {
        Attribute,
        FileUpload,
        InternalAttribute
    }

    String getName();

    HttpDataType getHttpDataType();

    InterfaceHttpData retain();

    InterfaceHttpData retain(int i);

    InterfaceHttpData touch();

    InterfaceHttpData touch(Object obj);
}
