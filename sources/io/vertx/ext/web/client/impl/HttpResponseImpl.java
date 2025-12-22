package io.vertx.ext.web.client.impl;

import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.codec.impl.BodyCodecImpl;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-client-3.8.5.jar:io/vertx/ext/web/client/impl/HttpResponseImpl.class */
public class HttpResponseImpl<T> implements HttpResponse<T> {
    private final HttpVersion version;
    private final int statusCode;
    private final String statusMessage;
    private final MultiMap headers;
    private final MultiMap trailers;
    private final List<String> cookies;
    private final T body;
    private final List<String> redirects;

    public HttpResponseImpl(HttpVersion version, int statusCode, String statusMessage, MultiMap headers, MultiMap trailers, List<String> cookies, T body, List<String> redirects) {
        this.version = version;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.headers = headers;
        this.trailers = trailers;
        this.cookies = cookies;
        this.body = body;
        this.redirects = redirects;
    }

    @Override // io.vertx.ext.web.client.HttpResponse
    public HttpVersion version() {
        return this.version;
    }

    @Override // io.vertx.ext.web.client.HttpResponse
    public int statusCode() {
        return this.statusCode;
    }

    @Override // io.vertx.ext.web.client.HttpResponse
    public String statusMessage() {
        return this.statusMessage;
    }

    @Override // io.vertx.ext.web.client.HttpResponse
    public String getHeader(String headerName) {
        return this.headers.get(headerName);
    }

    @Override // io.vertx.ext.web.client.HttpResponse
    public MultiMap trailers() {
        return this.trailers;
    }

    @Override // io.vertx.ext.web.client.HttpResponse
    public String getTrailer(String trailerName) {
        return this.trailers.get(trailerName);
    }

    @Override // io.vertx.ext.web.client.HttpResponse
    public List<String> cookies() {
        return this.cookies;
    }

    @Override // io.vertx.ext.web.client.HttpResponse
    public MultiMap headers() {
        return this.headers;
    }

    @Override // io.vertx.ext.web.client.HttpResponse
    public T body() {
        return this.body;
    }

    @Override // io.vertx.ext.web.client.HttpResponse
    public Buffer bodyAsBuffer() {
        if (this.body instanceof Buffer) {
            return (Buffer) this.body;
        }
        return null;
    }

    @Override // io.vertx.ext.web.client.HttpResponse
    public List<String> followedRedirects() {
        return this.redirects;
    }

    @Override // io.vertx.ext.web.client.HttpResponse
    public JsonArray bodyAsJsonArray() {
        Buffer b = bodyAsBuffer();
        if (b != null) {
            return BodyCodecImpl.JSON_ARRAY_DECODER.apply(b);
        }
        return null;
    }
}
