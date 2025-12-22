package io.vertx.core.http.impl;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/FrameType.class */
public enum FrameType {
    CONTINUATION,
    TEXT,
    BINARY,
    CLOSE,
    PING,
    PONG
}
