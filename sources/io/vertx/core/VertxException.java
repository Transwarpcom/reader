package io.vertx.core;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/VertxException.class */
public class VertxException extends RuntimeException {
    public VertxException(String message) {
        super(message);
    }

    public VertxException(String message, Throwable cause) {
        super(message, cause);
    }

    public VertxException(Throwable cause) {
        super(cause);
    }

    public VertxException(String message, boolean noStackTrace) {
        super(message, null, !noStackTrace, !noStackTrace);
    }

    public VertxException(Throwable cause, boolean noStackTrace) {
        super(null, cause, !noStackTrace, !noStackTrace);
    }
}
