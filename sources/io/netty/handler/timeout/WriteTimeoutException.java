package io.netty.handler.timeout;

import io.netty.util.internal.PlatformDependent;

/* loaded from: reader.jar:BOOT-INF/lib/netty-handler-4.1.42.Final.jar:io/netty/handler/timeout/WriteTimeoutException.class */
public final class WriteTimeoutException extends TimeoutException {
    private static final long serialVersionUID = -144786655770296065L;
    public static final WriteTimeoutException INSTANCE;

    static {
        INSTANCE = PlatformDependent.javaVersion() >= 7 ? new WriteTimeoutException(true) : new WriteTimeoutException();
    }

    private WriteTimeoutException() {
    }

    private WriteTimeoutException(boolean shared) {
        super(shared);
    }
}
