package io.netty.handler.timeout;

import io.netty.channel.ChannelException;

/* loaded from: reader.jar:BOOT-INF/lib/netty-handler-4.1.42.Final.jar:io/netty/handler/timeout/TimeoutException.class */
public class TimeoutException extends ChannelException {
    private static final long serialVersionUID = 4673641882869672533L;

    TimeoutException() {
    }

    TimeoutException(boolean shared) {
        super(null, null, shared);
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        return this;
    }
}
