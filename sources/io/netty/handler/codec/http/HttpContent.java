package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/http/HttpContent.class */
public interface HttpContent extends HttpObject, ByteBufHolder {
    HttpContent copy();

    HttpContent duplicate();

    HttpContent retainedDuplicate();

    HttpContent replace(ByteBuf byteBuf);

    HttpContent retain();

    HttpContent retain(int i);

    HttpContent touch();

    HttpContent touch(Object obj);
}
