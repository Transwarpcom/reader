package io.netty.handler.codec.socks;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-socks-4.1.42.Final.jar:io/netty/handler/codec/socks/SocksResponse.class */
public abstract class SocksResponse extends SocksMessage {
    private final SocksResponseType responseType;

    protected SocksResponse(SocksResponseType responseType) {
        super(SocksMessageType.RESPONSE);
        if (responseType == null) {
            throw new NullPointerException("responseType");
        }
        this.responseType = responseType;
    }

    public SocksResponseType responseType() {
        return this.responseType;
    }
}
