package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/AssembledFullHttpResponse.class */
class AssembledFullHttpResponse extends AssembledHttpResponse implements FullHttpResponse {
    private HttpHeaders trailingHeaders;

    public AssembledFullHttpResponse(boolean head, HttpVersion version, HttpResponseStatus status, HttpHeaders headers, ByteBuf buf, HttpHeaders trailingHeaders) {
        super(head, version, status, headers, buf);
        this.trailingHeaders = trailingHeaders;
    }

    @Override // io.netty.handler.codec.http.LastHttpContent
    public HttpHeaders trailingHeaders() {
        return this.trailingHeaders;
    }

    @Override // io.vertx.core.http.impl.AssembledHttpResponse, io.netty.handler.codec.http.HttpResponse, io.netty.handler.codec.http.FullHttpResponse
    public AssembledFullHttpResponse setStatus(HttpResponseStatus status) {
        super.setStatus(status);
        return this;
    }

    @Override // io.vertx.core.http.impl.AssembledHttpResponse, io.netty.handler.codec.http.HttpContent, io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    public AssembledFullHttpResponse retain(int increment) {
        super.retain(increment);
        return this;
    }

    @Override // io.vertx.core.http.impl.AssembledHttpResponse, io.netty.handler.codec.http.HttpContent, io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    public AssembledFullHttpResponse retain() {
        super.retain();
        return this;
    }

    @Override // io.vertx.core.http.impl.AssembledHttpResponse, io.netty.handler.codec.http.HttpContent, io.netty.buffer.ByteBufHolder
    public AssembledFullHttpResponse duplicate() {
        super.duplicate();
        return this;
    }

    @Override // io.vertx.core.http.impl.AssembledHttpResponse, io.netty.handler.codec.http.HttpContent, io.netty.buffer.ByteBufHolder
    public AssembledFullHttpResponse copy() {
        super.copy();
        return this;
    }

    @Override // io.vertx.core.http.impl.AssembledHttpResponse, io.netty.handler.codec.http.HttpContent, io.netty.buffer.ByteBufHolder
    public AssembledFullHttpResponse retainedDuplicate() {
        super.retainedDuplicate();
        return this;
    }

    @Override // io.vertx.core.http.impl.AssembledHttpResponse, io.netty.handler.codec.http.HttpContent, io.netty.buffer.ByteBufHolder
    public AssembledFullHttpResponse replace(ByteBuf content) {
        super.replace(content);
        return this;
    }

    @Override // io.vertx.core.http.impl.AssembledHttpResponse, io.netty.handler.codec.http.HttpResponse, io.netty.handler.codec.http.HttpMessage, io.netty.handler.codec.http.HttpRequest, io.netty.handler.codec.http.FullHttpRequest
    public AssembledFullHttpResponse setProtocolVersion(HttpVersion version) {
        super.setProtocolVersion(version);
        return this;
    }

    @Override // io.vertx.core.http.impl.AssembledHttpResponse, io.netty.handler.codec.http.HttpContent, io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    public AssembledFullHttpResponse touch() {
        super.touch();
        return this;
    }

    @Override // io.vertx.core.http.impl.AssembledHttpResponse, io.netty.handler.codec.http.HttpContent, io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    public AssembledFullHttpResponse touch(Object hint) {
        super.touch(hint);
        return this;
    }
}
