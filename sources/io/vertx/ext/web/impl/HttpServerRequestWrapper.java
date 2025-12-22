package io.vertx.ext.web.impl;

import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.HttpFrame;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerFileUpload;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import java.util.Map;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.security.cert.X509Certificate;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/impl/HttpServerRequestWrapper.class */
class HttpServerRequestWrapper implements HttpServerRequest {
    private final HttpServerRequest delegate;
    private boolean modified;
    private HttpMethod method;
    private String rawMethod;
    private String path;
    private String query;
    private String uri;
    private String absoluteURI;

    @Override // io.vertx.core.http.HttpServerRequest, io.vertx.core.streams.ReadStream
    /* renamed from: endHandler, reason: avoid collision after fix types in other method */
    public /* bridge */ /* synthetic */ ReadStream<Buffer> endHandler2(Handler handler) {
        return endHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.http.HttpServerRequest, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ ReadStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.http.HttpServerRequest, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    HttpServerRequestWrapper(HttpServerRequest request) {
        this.delegate = request;
    }

    void changeTo(HttpMethod method, String uri) {
        this.modified = true;
        this.method = method;
        this.uri = uri;
        this.rawMethod = null;
        this.path = null;
        this.query = null;
        this.absoluteURI = null;
        int queryIndex = uri.indexOf(63);
        int fragmentIndex = uri.indexOf(35);
        if (queryIndex != -1) {
            this.path = uri.substring(0, queryIndex);
            if (fragmentIndex != -1) {
                this.query = uri.substring(queryIndex + 1, fragmentIndex);
                return;
            } else {
                this.query = uri.substring(queryIndex + 1);
                return;
            }
        }
        if (fragmentIndex != -1) {
            this.path = uri.substring(0, fragmentIndex);
        } else {
            this.path = uri;
        }
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public long bytesRead() {
        return this.delegate.bytesRead();
    }

    @Override // io.vertx.core.http.HttpServerRequest, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public HttpServerRequest exceptionHandler(Handler<Throwable> handler) {
        return this.delegate.exceptionHandler(handler);
    }

    @Override // io.vertx.core.http.HttpServerRequest, io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    public ReadStream<Buffer> handler2(Handler<Buffer> handler) {
        return this.delegate.handler2(handler);
    }

    @Override // io.vertx.core.http.HttpServerRequest, io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    public ReadStream<Buffer> pause2() {
        return this.delegate.pause2();
    }

    @Override // io.vertx.core.http.HttpServerRequest, io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    public ReadStream<Buffer> resume2() {
        return this.delegate.resume2();
    }

    @Override // io.vertx.core.http.HttpServerRequest, io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    public ReadStream<Buffer> fetch2(long amount) {
        return this.delegate.fetch2(amount);
    }

    @Override // io.vertx.core.http.HttpServerRequest, io.vertx.core.streams.ReadStream
    public ReadStream<Buffer> endHandler(Handler<Void> handler) {
        return this.delegate.endHandler(handler);
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public HttpVersion version() {
        return this.delegate.version();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public HttpMethod method() {
        if (!this.modified) {
            return this.delegate.method();
        }
        return this.method;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String rawMethod() {
        if (!this.modified) {
            return this.delegate.rawMethod();
        }
        if (this.rawMethod == null) {
            this.rawMethod = this.method.toString();
        }
        return this.rawMethod;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String uri() {
        if (!this.modified) {
            return this.delegate.uri();
        }
        return this.uri;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String path() {
        if (!this.modified) {
            return this.delegate.path();
        }
        return this.path;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String query() {
        if (!this.modified) {
            return this.delegate.query();
        }
        return this.query;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public HttpServerResponse response() {
        return this.delegate.response();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public MultiMap headers() {
        return this.delegate.headers();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String getHeader(String s) {
        return this.delegate.getHeader(s);
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String getHeader(CharSequence charSequence) {
        return this.delegate.getHeader(charSequence);
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public MultiMap params() {
        return this.delegate.params();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String getParam(String s) {
        return this.delegate.getParam(s);
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public SocketAddress remoteAddress() {
        return this.delegate.remoteAddress();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public SocketAddress localAddress() {
        return this.delegate.localAddress();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException {
        return this.delegate.peerCertificateChain();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public SSLSession sslSession() {
        return this.delegate.sslSession();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String absoluteURI() {
        if (!this.modified) {
            return this.delegate.absoluteURI();
        }
        if (this.absoluteURI == null) {
            String scheme = this.delegate.scheme();
            String host = this.delegate.host();
            if (scheme != null && host != null) {
                this.absoluteURI = scheme + "://" + host + this.uri;
            } else {
                this.absoluteURI = this.uri;
            }
        }
        return this.absoluteURI;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String scheme() {
        return this.delegate.scheme();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String host() {
        return this.delegate.host();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public HttpServerRequest customFrameHandler(Handler<HttpFrame> handler) {
        this.delegate.customFrameHandler(handler);
        return this;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public HttpConnection connection() {
        return this.delegate.connection();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public HttpServerRequest bodyHandler(Handler<Buffer> handler) {
        return this.delegate.bodyHandler(handler);
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public NetSocket netSocket() {
        return this.delegate.netSocket();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public HttpServerRequest setExpectMultipart(boolean b) {
        return this.delegate.setExpectMultipart(b);
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public boolean isExpectMultipart() {
        return this.delegate.isExpectMultipart();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public HttpServerRequest uploadHandler(Handler<HttpServerFileUpload> handler) {
        return this.delegate.uploadHandler(handler);
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public MultiMap formAttributes() {
        return this.delegate.formAttributes();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String getFormAttribute(String s) {
        return this.delegate.getFormAttribute(s);
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public ServerWebSocket upgrade() {
        return this.delegate.upgrade();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public boolean isEnded() {
        return this.delegate.isEnded();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public boolean isSSL() {
        return this.delegate.isSSL();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public HttpServerRequest streamPriorityHandler(Handler<StreamPriority> handler) {
        this.delegate.streamPriorityHandler(handler);
        return this;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public StreamPriority streamPriority() {
        return this.delegate.streamPriority();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public Cookie getCookie(String name) {
        return this.delegate.getCookie(name);
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public int cookieCount() {
        return this.delegate.cookieCount();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public Map<String, Cookie> cookieMap() {
        return this.delegate.cookieMap();
    }
}
