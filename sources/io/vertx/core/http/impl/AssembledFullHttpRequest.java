package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.DefaultLastHttpContent;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/AssembledFullHttpRequest.class */
class AssembledFullHttpRequest extends AssembledHttpRequest implements FullHttpRequest {
    public AssembledFullHttpRequest(HttpRequest request, LastHttpContent content) {
        super(request, content);
    }

    public AssembledFullHttpRequest(HttpRequest request) {
        super(request, LastHttpContent.EMPTY_LAST_CONTENT);
    }

    public AssembledFullHttpRequest(HttpRequest request, ByteBuf buf) {
        super(request, toLastContent(buf));
    }

    private static LastHttpContent toLastContent(ByteBuf buf) {
        if (buf.isReadable()) {
            return new DefaultLastHttpContent(buf, false);
        }
        return LastHttpContent.EMPTY_LAST_CONTENT;
    }

    @Override // io.vertx.core.http.impl.AssembledHttpRequest, io.netty.handler.codec.http.HttpContent, io.netty.buffer.ByteBufHolder
    public AssembledFullHttpRequest replace(ByteBuf content) {
        super.replace(content);
        return this;
    }

    @Override // io.vertx.core.http.impl.AssembledHttpRequest, io.netty.handler.codec.http.HttpContent, io.netty.buffer.ByteBufHolder
    public AssembledFullHttpRequest retainedDuplicate() {
        super.retainedDuplicate();
        return this;
    }

    @Override // io.vertx.core.http.impl.AssembledHttpRequest, io.netty.handler.codec.http.HttpRequest, io.netty.handler.codec.http.FullHttpRequest
    public AssembledFullHttpRequest setUri(String uri) {
        super.setUri(uri);
        return this;
    }

    @Override // io.vertx.core.http.impl.AssembledHttpRequest, io.netty.handler.codec.http.HttpRequest, io.netty.handler.codec.http.FullHttpRequest
    public AssembledFullHttpRequest setProtocolVersion(HttpVersion version) {
        super.setProtocolVersion(version);
        return this;
    }

    @Override // io.vertx.core.http.impl.AssembledHttpRequest, io.netty.handler.codec.http.HttpRequest, io.netty.handler.codec.http.FullHttpRequest
    public AssembledFullHttpRequest setMethod(HttpMethod method) {
        super.setMethod(method);
        return this;
    }

    @Override // io.vertx.core.http.impl.AssembledHttpRequest, io.netty.handler.codec.http.HttpContent, io.netty.buffer.ByteBufHolder
    public AssembledFullHttpRequest duplicate() {
        throw new UnsupportedOperationException();
    }

    @Override // io.vertx.core.http.impl.AssembledHttpRequest, io.netty.handler.codec.http.HttpContent, io.netty.buffer.ByteBufHolder
    public AssembledFullHttpRequest copy() {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http.LastHttpContent
    public HttpHeaders trailingHeaders() {
        return ((LastHttpContent) this.content).trailingHeaders();
    }

    @Override // io.vertx.core.http.impl.AssembledHttpRequest, io.netty.handler.codec.http.HttpContent, io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    public AssembledFullHttpRequest retain() {
        super.retain();
        return this;
    }

    @Override // io.vertx.core.http.impl.AssembledHttpRequest, io.netty.handler.codec.http.HttpContent, io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    public AssembledFullHttpRequest retain(int increment) {
        super.retain(increment);
        return this;
    }

    @Override // io.vertx.core.http.impl.AssembledHttpRequest, io.netty.handler.codec.http.HttpContent, io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    public AssembledFullHttpRequest touch(Object hint) {
        super.touch(hint);
        return this;
    }

    @Override // io.vertx.core.http.impl.AssembledHttpRequest, io.netty.handler.codec.http.HttpContent, io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    public AssembledFullHttpRequest touch() {
        super.touch();
        return this;
    }
}
