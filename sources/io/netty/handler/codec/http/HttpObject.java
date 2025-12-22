package io.netty.handler.codec.http;

import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.DecoderResultProvider;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/http/HttpObject.class */
public interface HttpObject extends DecoderResultProvider {
    @Deprecated
    DecoderResult getDecoderResult();
}
