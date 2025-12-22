package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/AssembledHttpResponse.class */
class AssembledHttpResponse implements HttpResponse, HttpContent {
    private boolean head;
    private HttpResponseStatus status;
    private HttpVersion version;
    private HttpHeaders headers;
    private final ByteBuf content;
    private DecoderResult result;

    AssembledHttpResponse(boolean head, HttpVersion version, HttpResponseStatus status, HttpHeaders headers) {
        this(head, version, status, headers, Unpooled.EMPTY_BUFFER);
    }

    AssembledHttpResponse(boolean head, HttpVersion version, HttpResponseStatus status, HttpHeaders headers, ByteBuf content) {
        this.result = DecoderResult.SUCCESS;
        this.head = head;
        this.status = status;
        this.version = version;
        this.headers = headers;
        this.content = content;
    }

    boolean head() {
        return this.head;
    }

    @Override // io.netty.buffer.ByteBufHolder
    public HttpContent copy() {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.buffer.ByteBufHolder
    public HttpContent duplicate() {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.buffer.ByteBufHolder
    public HttpContent retainedDuplicate() {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.buffer.ByteBufHolder
    public HttpContent replace(ByteBuf content) {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.util.ReferenceCounted
    public AssembledHttpResponse retain() {
        this.content.retain();
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public AssembledHttpResponse retain(int increment) {
        this.content.retain(increment);
        return this;
    }

    @Override // io.netty.handler.codec.http.HttpResponse
    public HttpResponseStatus getStatus() {
        return this.status;
    }

    @Override // io.netty.handler.codec.http.HttpResponse, io.netty.handler.codec.http.FullHttpResponse
    public AssembledHttpResponse setStatus(HttpResponseStatus status) {
        this.status = status;
        return this;
    }

    @Override // io.netty.handler.codec.http.HttpMessage, io.netty.handler.codec.http.HttpRequest, io.netty.handler.codec.http.FullHttpRequest
    public AssembledHttpResponse setProtocolVersion(HttpVersion version) {
        this.version = version;
        return this;
    }

    @Override // io.netty.handler.codec.http.HttpMessage
    public HttpVersion getProtocolVersion() {
        return this.version;
    }

    @Override // io.netty.handler.codec.http.HttpMessage
    public HttpVersion protocolVersion() {
        return this.version;
    }

    @Override // io.netty.handler.codec.http.HttpResponse
    public HttpResponseStatus status() {
        return this.status;
    }

    @Override // io.netty.util.ReferenceCounted
    public AssembledHttpResponse touch() {
        this.content.touch();
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public AssembledHttpResponse touch(Object hint) {
        this.content.touch(hint);
        return this;
    }

    @Override // io.netty.handler.codec.DecoderResultProvider
    public DecoderResult decoderResult() {
        return this.result;
    }

    @Override // io.netty.handler.codec.http.HttpMessage
    public HttpHeaders headers() {
        return this.headers;
    }

    @Override // io.netty.handler.codec.http.HttpObject
    public DecoderResult getDecoderResult() {
        return this.result;
    }

    @Override // io.netty.handler.codec.DecoderResultProvider
    public void setDecoderResult(DecoderResult result) {
        this.result = result;
    }

    @Override // io.netty.buffer.ByteBufHolder
    public ByteBuf content() {
        return this.content;
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
