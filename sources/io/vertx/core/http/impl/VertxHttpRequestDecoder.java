package io.vertx.core.http.impl;

import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpVersion;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.impl.headers.VertxHttpHeaders;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/VertxHttpRequestDecoder.class */
public class VertxHttpRequestDecoder extends HttpRequestDecoder {
    public VertxHttpRequestDecoder(HttpServerOptions options) {
        super(options.getMaxInitialLineLength(), options.getMaxHeaderSize(), options.getMaxChunkSize(), !HttpHeaders.DISABLE_HTTP_HEADERS_VALIDATION, options.getDecoderInitialBufferSize());
    }

    @Override // io.netty.handler.codec.http.HttpRequestDecoder, io.netty.handler.codec.http.HttpObjectDecoder
    protected HttpMessage createMessage(String[] initialLine) {
        return new DefaultHttpRequest(HttpVersion.valueOf(initialLine[2]), HttpMethod.valueOf(initialLine[0]), initialLine[1], new VertxHttpHeaders());
    }
}
