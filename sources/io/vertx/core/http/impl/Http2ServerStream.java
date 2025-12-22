package io.vertx.core.http.impl;

import io.netty.handler.codec.http2.Http2Headers;
import io.netty.handler.codec.http2.Http2Stream;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.spi.metrics.HttpServerMetrics;
import io.vertx.core.spi.metrics.Metrics;
import okhttp3.internal.http2.Header;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/Http2ServerStream.class */
abstract class Http2ServerStream extends VertxHttp2Stream<Http2ServerConnection> {
    protected final Http2Headers headers;
    protected final String rawMethod;
    protected final HttpMethod method;
    protected final String uri;
    protected final String contentEncoding;
    protected final String host;
    protected final Http2ServerResponseImpl response;
    private Object metric;

    Http2ServerStream(Http2ServerConnection conn, Http2Stream stream, String contentEncoding, HttpMethod method, String uri, boolean writable) {
        super(conn, stream, writable);
        this.headers = null;
        this.method = method;
        this.rawMethod = method.name();
        this.contentEncoding = contentEncoding;
        this.uri = uri;
        this.host = null;
        this.response = new Http2ServerResponseImpl(conn, this, method, true, contentEncoding, null);
    }

    Http2ServerStream(Http2ServerConnection conn, Http2Stream stream, Http2Headers headers, String contentEncoding, String serverOrigin, boolean writable) {
        super(conn, stream, writable);
        String host = headers.get(Header.TARGET_AUTHORITY_UTF8) != null ? headers.get(Header.TARGET_AUTHORITY_UTF8).toString() : null;
        if (host == null) {
            int idx = serverOrigin.indexOf("://");
            host = serverOrigin.substring(idx + 3);
        }
        this.headers = headers;
        this.host = host;
        this.contentEncoding = contentEncoding;
        this.uri = headers.get(Header.TARGET_PATH_UTF8) != null ? headers.get(Header.TARGET_PATH_UTF8).toString() : null;
        this.rawMethod = headers.get(Header.TARGET_METHOD_UTF8) != null ? headers.get(Header.TARGET_METHOD_UTF8).toString() : null;
        this.method = HttpUtils.toVertxMethod(this.rawMethod);
        this.response = new Http2ServerResponseImpl(conn, this, this.method, false, contentEncoding, host);
    }

    /* JADX WARN: Multi-variable type inference failed */
    void registerMetrics() {
        HttpServerMetrics metrics;
        if (Metrics.METRICS_ENABLED && (metrics = ((Http2ServerConnection) this.conn).metrics()) != null) {
            if (this.response.isPush()) {
                this.metric = metrics.responsePushed(((Http2ServerConnection) this.conn).metric(), method(), this.uri, this.response);
            } else {
                this.metric = metrics.requestBegin(((Http2ServerConnection) this.conn).metric(), (HttpServerRequest) this);
            }
        }
    }

    void writeHead(Http2Headers headers, boolean end, Handler<AsyncResult<Void>> handler) {
        if (Metrics.METRICS_ENABLED && this.metric != null) {
            ((Http2ServerConnection) this.conn).metrics().responseBegin(this.metric, this.response);
        }
        writeHeaders(headers, end, handler);
    }

    @Override // io.vertx.core.http.impl.VertxHttp2Stream
    void handleInterestedOpsChanged() {
        if (this.response != null) {
            this.response.writabilityChanged();
        }
    }

    public HttpMethod method() {
        return this.method;
    }

    public String rawMethod() {
        return this.rawMethod;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.vertx.core.http.impl.VertxHttp2Stream
    public void handleClose() {
        HttpServerMetrics metrics;
        super.handleClose();
        if (Metrics.METRICS_ENABLED && (metrics = ((Http2ServerConnection) this.conn).metrics()) != null) {
            boolean failed = !this.response.ended();
            if (failed) {
                metrics.requestReset(this.metric);
            } else {
                metrics.responseEnd(this.metric, this.response);
            }
        }
    }
}
