package io.vertx.core.http;

import io.vertx.core.VertxException;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/StreamResetException.class */
public class StreamResetException extends VertxException {
    private final long code;

    public StreamResetException(long code) {
        super("Stream reset: " + code, true);
        this.code = code;
    }

    public long getCode() {
        return this.code;
    }
}
