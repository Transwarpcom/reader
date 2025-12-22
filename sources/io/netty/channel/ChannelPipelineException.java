package io.netty.channel;

/* loaded from: reader.jar:BOOT-INF/lib/netty-transport-4.1.42.Final.jar:io/netty/channel/ChannelPipelineException.class */
public class ChannelPipelineException extends ChannelException {
    private static final long serialVersionUID = 3379174210419885980L;

    public ChannelPipelineException() {
    }

    public ChannelPipelineException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChannelPipelineException(String message) {
        super(message);
    }

    public ChannelPipelineException(Throwable cause) {
        super(cause);
    }
}
