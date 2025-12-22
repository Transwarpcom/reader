package io.netty.util.concurrent;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/concurrent/BlockingOperationException.class */
public class BlockingOperationException extends IllegalStateException {
    private static final long serialVersionUID = 2462223247762460301L;

    public BlockingOperationException() {
    }

    public BlockingOperationException(String s) {
        super(s);
    }

    public BlockingOperationException(Throwable cause) {
        super(cause);
    }

    public BlockingOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
