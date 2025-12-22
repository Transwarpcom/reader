package io.vertx.ext.web.client.impl;

import io.netty.handler.codec.http.QueryStringDecoder;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.CaseInsensitiveHeaders;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.streams.ReadStream;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.ext.web.client.predicate.ResponsePredicate;
import io.vertx.ext.web.codec.BodyCodec;
import io.vertx.ext.web.multipart.MultipartForm;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-client-3.8.5.jar:io/vertx/ext/web/client/impl/HttpRequestImpl.class */
public class HttpRequestImpl<T> implements HttpRequest<T> {
    final WebClientInternal client;
    final WebClientOptions options;
    SocketAddress serverAddress;
    MultiMap params;
    HttpMethod method;
    String rawMethod;
    String protocol;
    int port;
    String host;
    String virtualHost;
    String uri;
    MultiMap headers;
    long timeout;
    BodyCodec<T> codec;
    boolean followRedirects;
    Boolean ssl;
    boolean multipartMixed;
    public List<ResponsePredicate> expectations;

    HttpRequestImpl(WebClientInternal client, HttpMethod method, SocketAddress serverAddress, Boolean ssl, int port, String host, String uri, BodyCodec<T> codec, WebClientOptions options) {
        this(client, method, serverAddress, null, ssl, port, host, uri, codec, options);
    }

    HttpRequestImpl(WebClientInternal client, HttpMethod method, SocketAddress serverAddress, String protocol, Boolean ssl, int port, String host, String uri, BodyCodec<T> codec, WebClientOptions options) {
        this.timeout = -1L;
        this.multipartMixed = true;
        this.client = client;
        this.method = method;
        this.protocol = protocol;
        this.codec = codec;
        this.port = port;
        this.host = host;
        this.uri = uri;
        this.ssl = ssl;
        this.serverAddress = serverAddress;
        this.followRedirects = options.isFollowRedirects();
        this.options = options;
        if (options.isUserAgentEnabled()) {
            this.headers = new CaseInsensitiveHeaders().add(HttpHeaders.USER_AGENT, options.getUserAgent());
        }
    }

    private HttpRequestImpl(HttpRequestImpl<T> other) {
        this.timeout = -1L;
        this.multipartMixed = true;
        this.client = other.client;
        this.serverAddress = other.serverAddress;
        this.options = other.options;
        this.method = other.method;
        this.protocol = other.protocol;
        this.port = other.port;
        this.host = other.host;
        this.timeout = other.timeout;
        this.uri = other.uri;
        this.headers = other.headers != null ? new CaseInsensitiveHeaders().addAll(other.headers) : null;
        this.params = other.params != null ? new CaseInsensitiveHeaders().addAll(other.params) : null;
        this.codec = other.codec;
        this.followRedirects = other.followRedirects;
        this.ssl = other.ssl;
        this.multipartMixed = other.multipartMixed;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.vertx.ext.web.client.HttpRequest
    public <U> HttpRequest<U> as(BodyCodec<U> bodyCodec) {
        this.codec = bodyCodec;
        return this;
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public HttpRequest<T> method(HttpMethod value) {
        this.method = value;
        return this;
    }

    public HttpMethod method() {
        return this.method;
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public HttpRequest<T> rawMethod(String method) {
        this.rawMethod = method;
        method(HttpMethod.OTHER);
        return this;
    }

    public String rawMethod() {
        return this.rawMethod;
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public HttpRequest<T> port(int value) {
        this.port = value;
        return this;
    }

    public int port() {
        return this.port;
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public HttpRequest<T> host(String value) {
        this.host = value;
        return this;
    }

    public String host() {
        return this.host;
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public HttpRequest<T> virtualHost(String value) {
        this.virtualHost = value;
        return this;
    }

    public String virtualHost() {
        return this.virtualHost;
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public HttpRequest<T> uri(String value) {
        this.params = null;
        this.uri = value;
        return this;
    }

    public String uri() {
        return this.uri;
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public HttpRequest<T> putHeaders(MultiMap headers) {
        headers().addAll(headers);
        return this;
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public HttpRequest<T> putHeader(String name, String value) {
        headers().set(name, value);
        return this;
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public HttpRequest<T> putHeader(String name, Iterable<String> value) {
        headers().mo1936set(name, value);
        return this;
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public MultiMap headers() {
        if (this.headers == null) {
            this.headers = new CaseInsensitiveHeaders();
        }
        return this.headers;
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public HttpRequest<T> basicAuthentication(String id, String password) {
        return basicAuthentication(Buffer.buffer(id), Buffer.buffer(password));
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public HttpRequest<T> basicAuthentication(Buffer id, Buffer password) {
        Buffer buff = Buffer.buffer().appendBuffer(id).appendString(":").appendBuffer(password);
        String credentials = new String(Base64.getEncoder().encode(buff.getBytes()));
        return putHeader(HttpHeaders.AUTHORIZATION.toString(), "Basic " + credentials);
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public HttpRequest<T> bearerTokenAuthentication(String bearerToken) {
        return putHeader(HttpHeaders.AUTHORIZATION.toString(), "Bearer " + bearerToken);
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public HttpRequest<T> ssl(Boolean value) {
        this.ssl = value;
        return this;
    }

    public Boolean ssl() {
        return this.ssl;
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public HttpRequest<T> timeout(long value) {
        this.timeout = value;
        return this;
    }

    public long timeout() {
        return this.timeout;
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public HttpRequest<T> addQueryParam(String paramName, String paramValue) {
        queryParams().add(paramName, paramValue);
        return this;
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public HttpRequest<T> setQueryParam(String paramName, String paramValue) {
        queryParams().set(paramName, paramValue);
        return this;
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public HttpRequest<T> followRedirects(boolean value) {
        this.followRedirects = value;
        return this;
    }

    public boolean followRedirects() {
        return this.followRedirects;
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public HttpRequest<T> expect(ResponsePredicate expectation) {
        if (this.expectations == null) {
            this.expectations = new ArrayList();
        }
        this.expectations.add(expectation);
        return this;
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public MultiMap queryParams() {
        int idx;
        if (this.params == null) {
            this.params = new CaseInsensitiveHeaders();
        }
        if (this.params.isEmpty() && (idx = this.uri.indexOf(63)) >= 0) {
            QueryStringDecoder dec = new QueryStringDecoder(this.uri);
            dec.parameters().forEach((name, value) -> {
                this.params.mo1938add(name, (Iterable<String>) value);
            });
            this.uri = this.uri.substring(0, idx);
        }
        return this.params;
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public HttpRequest<T> copy() {
        return new HttpRequestImpl(this);
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public HttpRequest<T> multipartMixed(boolean allow) {
        this.multipartMixed = allow;
        return this;
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public void sendStream(ReadStream<Buffer> body, Handler<AsyncResult<HttpResponse<T>>> handler) {
        send(null, body, handler);
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public void send(Handler<AsyncResult<HttpResponse<T>>> handler) {
        send(null, null, handler);
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public void sendBuffer(Buffer body, Handler<AsyncResult<HttpResponse<T>>> handler) {
        send(null, body, handler);
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public void sendJsonObject(JsonObject body, Handler<AsyncResult<HttpResponse<T>>> handler) {
        send("application/json", body, handler);
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public void sendJson(Object body, Handler<AsyncResult<HttpResponse<T>>> handler) {
        send("application/json", body, handler);
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public void sendForm(MultiMap body, Handler<AsyncResult<HttpResponse<T>>> handler) {
        send("application/x-www-form-urlencoded", body, handler);
    }

    @Override // io.vertx.ext.web.client.HttpRequest
    public void sendMultipartForm(MultipartForm body, Handler<AsyncResult<HttpResponse<T>>> handler) {
        send("multipart/form-data", body, handler);
    }

    private void send(String contentType, Object body, Handler<AsyncResult<HttpResponse<T>>> handler) {
        HttpContext<T> ctx = this.client.createContext(handler);
        ctx.prepareRequest(this, contentType, body);
    }
}
