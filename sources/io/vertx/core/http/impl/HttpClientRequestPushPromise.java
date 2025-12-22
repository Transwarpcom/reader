package io.vertx.core.http.impl;

import io.netty.handler.codec.http2.Http2Stream;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.http.impl.Http2ClientConnection;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.WriteStream;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/HttpClientRequestPushPromise.class */
class HttpClientRequestPushPromise extends HttpClientRequestBase {
    private final Http2ClientConnection conn;
    private final Http2ClientConnection.Http2ClientStream stream;
    private final String rawMethod;
    private final MultiMap headers;
    private Handler<HttpClientResponse> respHandler;

    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.WriteStream
    /* renamed from: drainHandler, reason: avoid collision after fix types in other method */
    public /* bridge */ /* synthetic */ WriteStream<Buffer> drainHandler2(Handler handler) {
        return drainHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.WriteStream
    public /* bridge */ /* synthetic */ void end(Buffer buffer, Handler handler) {
        end(buffer, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.WriteStream
    public /* bridge */ /* synthetic */ WriteStream<Buffer> write(Buffer buffer, Handler handler) {
        return write(buffer, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.ReadStream
    /* renamed from: endHandler, reason: avoid collision after fix types in other method */
    public /* bridge */ /* synthetic */ ReadStream<HttpClientResponse> endHandler2(Handler handler) {
        return endHandler((Handler<Void>) handler);
    }

    public HttpClientRequestPushPromise(Http2ClientConnection conn, Http2Stream stream, HttpClientImpl client, boolean ssl, HttpMethod method, String rawMethod, String uri, String host, int port, MultiMap headers) {
        super(client, ssl, method, SocketAddress.inetSocketAddress(port, host), host, port, uri);
        this.conn = conn;
        this.stream = new Http2ClientConnection.Http2ClientStream(conn, this, stream, false);
        this.rawMethod = rawMethod;
        this.headers = headers;
    }

    Http2ClientConnection.Http2ClientStream getStream() {
        return this.stream;
    }

    @Override // io.vertx.core.http.impl.HttpClientRequestBase
    void handleResponse(HttpClientResponse resp, long timeoutMs) {
        synchronized (this) {
            Handler<HttpClientResponse> handler = this.respHandler;
            if (handler == null) {
                return;
            }
            handler.handle(resp);
        }
    }

    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    public synchronized ReadStream<HttpClientResponse> handler2(Handler<HttpClientResponse> handler) {
        this.respHandler = handler;
        return this;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpConnection connection() {
        return this.conn;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest connectionHandler(Handler<HttpConnection> handler) {
        return this;
    }

    @Override // io.vertx.core.http.impl.HttpClientRequestBase
    boolean reset(Throwable cause) {
        this.stream.reset(cause);
        return true;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public boolean isChunked() {
        return false;
    }

    @Override // io.vertx.core.http.impl.HttpClientRequestBase, io.vertx.core.http.HttpClientRequest
    public HttpMethod method() {
        return this.method;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public String getRawMethod() {
        return this.rawMethod;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest setRawMethod(String method) {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.impl.HttpClientRequestBase, io.vertx.core.http.HttpClientRequest
    public String uri() {
        return this.uri;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public String getHost() {
        return this.server.host();
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public MultiMap headers() {
        return this.headers;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.WriteStream
    public HttpClientRequest write(Buffer data) {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.WriteStream
    /* renamed from: setWriteQueueMaxSize */
    public WriteStream<Buffer> setWriteQueueMaxSize2(int maxSize) {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.WriteStream
    public WriteStream<Buffer> drainHandler(Handler<Void> handler) {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.ReadStream
    public ReadStream<HttpClientResponse> endHandler(Handler<Void> endHandler) {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest setFollowRedirects(boolean followRedirect) {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest setMaxRedirects(int maxRedirects) {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest setChunked(boolean chunked) {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest setHost(String host) {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest putHeader(String name, String value) {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest putHeader(CharSequence name, CharSequence value) {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest putHeader(String name, Iterable<String> values) {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest putHeader(CharSequence name, Iterable<CharSequence> values) {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest write(String chunk) {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest write(String chunk, String enc) {
        throw new IllegalStateException();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest write(Buffer data, Handler<AsyncResult<Void>> handler) {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest write(String chunk, Handler<AsyncResult<Void>> handler) {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest write(String chunk, String enc, Handler<AsyncResult<Void>> handler) {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest continueHandler(Handler<Void> handler) {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest sendHead() {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest sendHead(Handler<HttpVersion> completionHandler) {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public void end(String chunk) {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public void end(String chunk, Handler<AsyncResult<Void>> handler) {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public void end(String chunk, String enc) {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public void end(String chunk, String enc, Handler<AsyncResult<Void>> handler) {
        throw new IllegalStateException();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.WriteStream
    public void end(Buffer chunk) {
        throw new IllegalStateException();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.http.HttpClientRequest
    public void end(Buffer chunk, Handler<AsyncResult<Void>> handler) {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest pushHandler(Handler<HttpClientRequest> handler) {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.WriteStream
    public void end() {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.WriteStream
    public void end(Handler<AsyncResult<Void>> handler) {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.streams.WriteStream
    public boolean writeQueueFull() {
        throw new IllegalStateException();
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public StreamPriority getStreamPriority() {
        return this.stream.priority();
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest writeCustomFrame(int type, int flags, Buffer payload) {
        throw new UnsupportedOperationException("Cannot write frame with HTTP/1.x ");
    }
}
