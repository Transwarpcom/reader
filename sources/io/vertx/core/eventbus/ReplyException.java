package io.vertx.core.eventbus;

import io.vertx.core.VertxException;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/ReplyException.class */
public class ReplyException extends VertxException {
    private final ReplyFailure failureType;
    private final int failureCode;

    public ReplyException(ReplyFailure failureType, int failureCode, String message) {
        super(message);
        this.failureType = failureType;
        this.failureCode = failureCode;
    }

    public ReplyException(ReplyFailure failureType, String message) {
        this(failureType, -1, message);
    }

    public ReplyException(ReplyFailure failureType) {
        this(failureType, -1, null);
    }

    public ReplyFailure failureType() {
        return this.failureType;
    }

    public int failureCode() {
        return this.failureCode;
    }

    @Override // java.lang.Throwable
    public String toString() {
        String message = getMessage();
        return "(" + this.failureType + "," + this.failureCode + ") " + (message != null ? message : "");
    }
}
