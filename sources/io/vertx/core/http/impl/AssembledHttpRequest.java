package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.UnsupportedMessageTypeException;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/AssembledHttpRequest.class */
class AssembledHttpRequest implements HttpContent, HttpRequest {
    private final HttpRequest request;
    protected final HttpContent content;

    AssembledHttpRequest(HttpRequest request, ByteBuf buf) {
        this(request, new DefaultHttpContent(buf));
    }

    AssembledHttpRequest(HttpRequest request, HttpContent content) {
        this.request = request;
        this.content = content;
    }

    @Override // io.netty.buffer.ByteBufHolder
    public AssembledHttpRequest copy() {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.buffer.ByteBufHolder
    public AssembledHttpRequest duplicate() {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.buffer.ByteBufHolder
    public HttpContent retainedDuplicate() {
        throw new UnsupportedMessageTypeException();
    }

    @Override // io.netty.buffer.ByteBufHolder
    public HttpContent replace(ByteBuf content) {
        throw new UnsupportedMessageTypeException();
    }

    @Override // io.netty.util.ReferenceCounted
    public AssembledHttpRequest retain() {
        this.content.retain();
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public AssembledHttpRequest retain(int increment) {
        this.content.retain(increment);
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public AssembledHttpRequest touch(Object hint) {
        this.content.touch(hint);
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public AssembledHttpRequest touch() {
        this.content.touch();
        return this;
    }

    @Override // io.netty.handler.codec.http.HttpRequest
    public HttpMethod method() {
        return this.request.method();
    }

    @Override // io.netty.handler.codec.http.HttpRequest
    public HttpMethod getMethod() {
        return this.request.method();
    }

    @Override // io.netty.handler.codec.http.HttpRequest
    public String uri() {
        return this.request.uri();
    }

    @Override // io.netty.handler.codec.http.HttpRequest
    public String getUri() {
        return this.request.uri();
    }

    @Override // io.netty.handler.codec.http.HttpMessage
    public HttpHeaders headers() {
        return this.request.headers();
    }

    public HttpRequest setMethod(HttpMethod method) {
        return this.request.setMethod(method);
    }

    @Override // io.netty.handler.codec.http.HttpMessage
    public HttpVersion protocolVersion() {
        return this.request.protocolVersion();
    }

    @Override // io.netty.handler.codec.http.HttpMessage
    public HttpVersion getProtocolVersion() {
        return this.request.protocolVersion();
    }

    public HttpRequest setUri(String uri) {
        return this.request.setUri(uri);
    }

    @Override // io.netty.handler.codec.http.HttpMessage, io.netty.handler.codec.http.HttpRequest, io.netty.handler.codec.http.FullHttpRequest
    public HttpRequest setProtocolVersion(HttpVersion version) {
        return this.request.setProtocolVersion(version);
    }

    @Override // io.netty.handler.codec.DecoderResultProvider
    public DecoderResult decoderResult() {
        return this.request.decoderResult();
    }

    @Override // io.netty.handler.codec.http.HttpObject
    public DecoderResult getDecoderResult() {
        return this.request.decoderResult();
    }

    @Override // io.netty.handler.codec.DecoderResultProvider
    public void setDecoderResult(DecoderResult result) {
        this.request.setDecoderResult(result);
    }

    @Override // io.netty.buffer.ByteBufHolder
    public ByteBuf content() {
        return this.content.content();
    }

    @Override // io.netty.util.ReferenceCounted
    public int refCnt() {
        return this.content.refCnt();
    }

    @Override // io.netty.util.ReferenceCounted
    public boolean release() {
        return this.content.release();
    }

    @Override // io.netty.util.ReferenceCounted
    public boolean release(int decrement) {
        return this.content.release(decrement);
    }
}
