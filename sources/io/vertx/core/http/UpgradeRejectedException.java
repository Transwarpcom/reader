package io.vertx.core.http;

import io.vertx.core.VertxException;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/UpgradeRejectedException.class */
public class UpgradeRejectedException extends VertxException {
    private final int status;

    public UpgradeRejectedException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }
}
