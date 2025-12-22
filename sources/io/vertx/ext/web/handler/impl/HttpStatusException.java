package io.vertx.ext.web.handler.impl;

import io.netty.handler.codec.http.HttpResponseStatus;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/HttpStatusException.class */
public class HttpStatusException extends RuntimeException {
    private final int statusCode;
    private final String payload;

    public HttpStatusException(int statusCode) {
        this(statusCode, null, null);
    }

    public HttpStatusException(int statusCode, Throwable cause) {
        this(statusCode, null, cause);
    }

    public HttpStatusException(int statusCode, String payload) {
        this(statusCode, payload, null);
    }

    public HttpStatusException(int statusCode, String payload, Throwable cause) {
        super(HttpResponseStatus.valueOf(statusCode).reasonPhrase(), cause, false, false);
        this.statusCode = statusCode;
        this.payload = payload;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getPayload() {
        return this.payload;
    }
}
