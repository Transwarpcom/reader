package io.netty.handler.codec.http;

import io.netty.handler.codec.DecoderResult;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/http/DefaultHttpObject.class */
public class DefaultHttpObject implements HttpObject {
    private static final int HASH_CODE_PRIME = 31;
    private DecoderResult decoderResult = DecoderResult.SUCCESS;

    protected DefaultHttpObject() {
    }

    @Override // io.netty.handler.codec.DecoderResultProvider
    public DecoderResult decoderResult() {
        return this.decoderResult;
    }

    @Override // io.netty.handler.codec.http.HttpObject
    @Deprecated
    public DecoderResult getDecoderResult() {
        return decoderResult();
    }

    @Override // io.netty.handler.codec.DecoderResultProvider
    public void setDecoderResult(DecoderResult decoderResult) {
        if (decoderResult == null) {
            throw new NullPointerException("decoderResult");
        }
        this.decoderResult = decoderResult;
    }

    public int hashCode() {
        int result = (31 * 1) + this.decoderResult.hashCode();
        return result;
    }

    public boolean equals(Object o) {
        if (!(o instanceof DefaultHttpObject)) {
            return false;
        }
        DefaultHttpObject other = (DefaultHttpObject) o;
        return decoderResult().equals(other.decoderResult());
    }
}
