package io.vertx.core.http.impl;

import java.util.concurrent.TimeoutException;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/NoStackTraceTimeoutException.class */
class NoStackTraceTimeoutException extends TimeoutException {
    NoStackTraceTimeoutException(String message) {
        super(message);
    }

    @Override // java.lang.Throwable
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
