package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.DefaultByteBufHolder;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.LastHttpContent;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/AssembledLastHttpContent.class */
class AssembledLastHttpContent extends DefaultByteBufHolder implements LastHttpContent {
    private final HttpHeaders trailingHeaders;
    private DecoderResult result;

    AssembledLastHttpContent(ByteBuf buf, HttpHeaders trailingHeaders) {
        this(buf, trailingHeaders, DecoderResult.SUCCESS);
    }

    AssembledLastHttpContent(ByteBuf buf, HttpHeaders trailingHeaders, DecoderResult result) {
        super(buf);
        this.trailingHeaders = trailingHeaders;
        this.result = result;
    }

    @Override // io.netty.handler.codec.http.LastHttpContent
    public HttpHeaders trailingHeaders() {
        return this.trailingHeaders;
    }

    @Override // io.netty.buffer.DefaultByteBufHolder, io.netty.buffer.ByteBufHolder
    public LastHttpContent copy() {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.buffer.DefaultByteBufHolder, io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    public LastHttpContent retain(int increment) {
        super.retain(increment);
        return this;
    }

    @Override // io.netty.buffer.DefaultByteBufHolder, io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    public LastHttpContent retain() {
        super.retain();
        return this;
    }

    @Override // io.netty.buffer.DefaultByteBufHolder, io.netty.buffer.ByteBufHolder
    public LastHttpContent duplicate() {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.buffer.DefaultByteBufHolder, io.netty.buffer.ByteBufHolder
    public LastHttpContent replace(ByteBuf content) {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.buffer.DefaultByteBufHolder, io.netty.buffer.ByteBufHolder
    public LastHttpContent retainedDuplicate() {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.DecoderResultProvider
    public DecoderResult decoderResult() {
        return this.result;
    }

    @Override // io.netty.handler.codec.http.HttpObject
    public DecoderResult getDecoderResult() {
        return this.result;
    }

    @Override // io.netty.handler.codec.DecoderResultProvider
    public void setDecoderResult(DecoderResult result) {
        this.result = result;
    }

    @Override // io.netty.buffer.DefaultByteBufHolder, io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    public AssembledLastHttpContent touch() {
        super.touch();
        return this;
    }

    @Override // io.netty.buffer.DefaultByteBufHolder, io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    public AssembledLastHttpContent touch(Object hint) {
        super.touch(hint);
        return this;
    }
}
