package io.netty.handler.codec.socks;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-socks-4.1.42.Final.jar:io/netty/handler/codec/socks/SocksRequest.class */
public abstract class SocksRequest extends SocksMessage {
    private final SocksRequestType requestType;

    protected SocksRequest(SocksRequestType requestType) {
        super(SocksMessageType.REQUEST);
        if (requestType == null) {
            throw new NullPointerException("requestType");
        }
        this.requestType = requestType;
    }

    public SocksRequestType requestType() {
        return this.requestType;
    }
}
