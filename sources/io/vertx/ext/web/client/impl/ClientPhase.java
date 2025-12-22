package io.vertx.ext.web.client.impl;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-client-3.8.5.jar:io/vertx/ext/web/client/impl/ClientPhase.class */
public enum ClientPhase {
    PREPARE_REQUEST,
    SEND_REQUEST,
    FOLLOW_REDIRECT,
    RECEIVE_RESPONSE,
    DISPATCH_RESPONSE,
    FAILURE
}
