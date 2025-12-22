package io.netty.handler.codec;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-4.1.42.Final.jar:io/netty/handler/codec/DecoderException.class */
public class DecoderException extends CodecException {
    private static final long serialVersionUID = 6926716840699621852L;

    public DecoderException() {
    }

    public DecoderException(String message, Throwable cause) {
        super(message, cause);
    }

    public DecoderException(String message) {
        super(message);
    }

    public DecoderException(Throwable cause) {
        super(cause);
    }
}
