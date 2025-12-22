package io.vertx.core.impl;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/NoStackTraceThrowable.class */
public class NoStackTraceThrowable extends Throwable {
    public NoStackTraceThrowable(String message) {
        super(message, null, false, false);
    }
}
