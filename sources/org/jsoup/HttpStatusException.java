package org.jsoup;

import java.io.IOException;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/HttpStatusException.class */
public class HttpStatusException extends IOException {
    private final int statusCode;
    private final String url;

    public HttpStatusException(String message, int statusCode, String url) {
        super(message + ". Status=" + statusCode + ", URL=[" + url + "]");
        this.statusCode = statusCode;
        this.url = url;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getUrl() {
        return this.url;
    }
}
