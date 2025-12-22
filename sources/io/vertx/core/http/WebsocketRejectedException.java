package io.vertx.core.http;

@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/WebsocketRejectedException.class */
public class WebsocketRejectedException extends UpgradeRejectedException {
    public WebsocketRejectedException(int status) {
        super("Websocket connection attempt returned HTTP status code " + status, status);
    }
}
