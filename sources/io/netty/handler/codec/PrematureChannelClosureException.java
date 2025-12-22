package io.netty.handler.codec;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-4.1.42.Final.jar:io/netty/handler/codec/PrematureChannelClosureException.class */
public class PrematureChannelClosureException extends CodecException {
    private static final long serialVersionUID = 4907642202594703094L;

    public PrematureChannelClosureException() {
    }

    public PrematureChannelClosureException(String message, Throwable cause) {
        super(message, cause);
    }

    public PrematureChannelClosureException(String message) {
        super(message);
    }

    public PrematureChannelClosureException(Throwable cause) {
        super(cause);
    }
}
