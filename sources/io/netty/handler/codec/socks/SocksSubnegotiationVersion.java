package io.netty.handler.codec.socks;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-socks-4.1.42.Final.jar:io/netty/handler/codec/socks/SocksSubnegotiationVersion.class */
public enum SocksSubnegotiationVersion {
    AUTH_PASSWORD((byte) 1),
    UNKNOWN((byte) -1);

    private final byte b;

    SocksSubnegotiationVersion(byte b) {
        this.b = b;
    }

    @Deprecated
    public static SocksSubnegotiationVersion fromByte(byte b) {
        return valueOf(b);
    }

    public static SocksSubnegotiationVersion valueOf(byte b) {
        for (SocksSubnegotiationVersion code : values()) {
            if (code.b == b) {
                return code;
            }
        }
        return UNKNOWN;
    }

    public byte byteValue() {
        return this.b;
    }
}
