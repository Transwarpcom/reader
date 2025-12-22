package io.vertx.core.http.impl;

import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.util.CharsetUtil;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.CaseInsensitiveHeaders;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.HttpFrame;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerFileUpload;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.spi.metrics.Metrics;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.impl.InboundBuffer;
import java.net.URISyntaxException;
import java.util.Map;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.security.cert.X509Certificate;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/HttpServerRequestImpl.class */
public class HttpServerRequestImpl implements HttpServerRequest {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) HttpServerRequestImpl.class);
    private final Http1xServerConnection conn;
    private HttpRequest request;
    private HttpVersion version;
    private HttpMethod method;
    private String rawMethod;
    private String uri;
    private String path;
    private String query;
    private HttpServerResponseImpl response;
    private HttpServerRequestImpl next;
    private Object metric;
    private Handler<Buffer> dataHandler;
    private Handler<Throwable> exceptionHandler;
    private MultiMap params;
    private MultiMap headers;
    private String absoluteURI;
    private Handler<HttpServerFileUpload> uploadHandler;
    private Handler<Void> endHandler;
    private MultiMap attributes;
    private HttpPostRequestDecoder decoder;
    private boolean ended;
    private long bytesRead;
    private InboundBuffer<Object> pending;

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

    HttpServerRequestImpl(Http1xServerConnection conn, HttpRequest request) {
        this.conn = conn;
        this.request = request;
    }

    HttpRequest nettyRequest() {
        HttpRequest httpRequest;
        synchronized (this.conn) {
            httpRequest = this.request;
        }
        return httpRequest;
    }

    void setRequest(HttpRequest request) {
        synchronized (this.conn) {
            this.request = request;
        }
    }

    private InboundBuffer<Object> pendingQueue() {
        if (this.pending == null) {
            this.pending = new InboundBuffer<>(this.conn.getContext(), 8L);
            this.pending.drainHandler(v -> {
                this.conn.doResume();
            });
            this.pending.handler(buffer -> {
                if (buffer == InboundBuffer.END_SENTINEL) {
                    onEnd();
                } else {
                    onData((Buffer) buffer);
                }
            });
        }
        return this.pending;
    }

    void handleContent(Buffer buffer) {
        InboundBuffer<Object> queue;
        synchronized (this.conn) {
            queue = this.pending;
        }
        if (queue != null) {
            if (!queue.write((InboundBuffer<Object>) buffer)) {
                this.conn.doPause();
                return;
            }
            return;
        }
        onData(buffer);
    }

    void handleBegin() {
        if (Metrics.METRICS_ENABLED) {
            reportRequestBegin();
        }
        this.response = new HttpServerResponseImpl((VertxInternal) this.conn.vertx(), this.conn, this.request, this.metric);
        if (this.conn.handle100ContinueAutomatically) {
            check100();
        }
    }

    void enqueue(HttpServerRequestImpl request) {
        HttpServerRequestImpl httpServerRequestImpl = this;
        while (true) {
            HttpServerRequestImpl current = httpServerRequestImpl;
            if (current.next != null) {
                httpServerRequestImpl = current.next;
            } else {
                current.next = request;
                return;
            }
        }
    }

    HttpServerRequestImpl next() {
        return this.next;
    }

    private void reportRequestBegin() {
        if (this.conn.metrics != null) {
            this.metric = this.conn.metrics.requestBegin(this.conn.metric(), this);
        }
    }

    private void check100() {
        if (HttpUtil.is100ContinueExpected(this.request)) {
            this.conn.write100Continue();
        }
    }

    Object metric() {
        return this.metric;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public HttpVersion version() {
        if (this.version == null) {
            io.netty.handler.codec.http.HttpVersion nettyVersion = this.request.protocolVersion();
            if (nettyVersion == io.netty.handler.codec.http.HttpVersion.HTTP_1_0) {
                this.version = HttpVersion.HTTP_1_0;
            } else if (nettyVersion == io.netty.handler.codec.http.HttpVersion.HTTP_1_1) {
                this.version = HttpVersion.HTTP_1_1;
            } else {
                sendNotImplementedAndClose();
                throw new IllegalStateException("Unsupported HTTP version: " + nettyVersion);
            }
        }
        return this.version;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public HttpMethod method() {
        if (this.method == null) {
            String sMethod = this.request.method().toString();
            try {
                this.method = HttpMethod.valueOf(sMethod);
            } catch (IllegalArgumentException e) {
                this.method = HttpMethod.OTHER;
            }
        }
        return this.method;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String rawMethod() {
        if (this.rawMethod == null) {
            this.rawMethod = this.request.method().toString();
        }
        return this.rawMethod;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String uri() {
        if (this.uri == null) {
            this.uri = this.request.uri();
        }
        return this.uri;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String path() {
        if (this.path == null) {
            this.path = HttpUtils.parsePath(uri());
        }
        return this.path;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String query() {
        if (this.query == null) {
            this.query = HttpUtils.parseQuery(uri());
        }
        return this.query;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String host() {
        return getHeader(HttpHeaderNames.HOST);
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public long bytesRead() {
        long j;
        synchronized (this.conn) {
            j = this.bytesRead;
        }
        return j;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public HttpServerResponseImpl response() {
        return this.response;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.vertx.core.http.HttpServerRequest
    public MultiMap headers() {
        if (this.headers == null) {
            HttpHeaders httpHeadersHeaders = this.request.headers();
            if (httpHeadersHeaders instanceof MultiMap) {
                this.headers = (MultiMap) httpHeadersHeaders;
            } else {
                this.headers = new HeadersAdaptor(httpHeadersHeaders);
            }
        }
        return this.headers;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String getHeader(String headerName) {
        return headers().get(headerName);
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String getHeader(CharSequence headerName) {
        return headers().get(headerName);
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public MultiMap params() {
        if (this.params == null) {
            this.params = HttpUtils.params(uri());
        }
        return this.params;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String getParam(String paramName) {
        return params().get(paramName);
    }

    @Override // io.vertx.core.http.HttpServerRequest, io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    public ReadStream<Buffer> handler2(Handler<Buffer> handler) {
        synchronized (this.conn) {
            if (handler != null) {
                checkEnded();
                this.dataHandler = handler;
            } else {
                this.dataHandler = handler;
            }
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerRequest, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public HttpServerRequest exceptionHandler(Handler<Throwable> handler) {
        synchronized (this.conn) {
            this.exceptionHandler = handler;
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerRequest, io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    public ReadStream<Buffer> pause2() {
        synchronized (this.conn) {
            pendingQueue().pause();
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerRequest, io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    public ReadStream<Buffer> fetch2(long amount) {
        synchronized (this.conn) {
            pendingQueue().fetch(amount);
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerRequest, io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    public ReadStream<Buffer> resume2() {
        return fetch2(Long.MAX_VALUE);
    }

    @Override // io.vertx.core.http.HttpServerRequest, io.vertx.core.streams.ReadStream
    public ReadStream<Buffer> endHandler(Handler<Void> handler) {
        synchronized (this.conn) {
            if (handler != null) {
                checkEnded();
                this.endHandler = handler;
            } else {
                this.endHandler = handler;
            }
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String scheme() {
        return isSSL() ? "https" : "http";
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public boolean isSSL() {
        return this.conn.isSsl();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public SocketAddress remoteAddress() {
        return this.conn.remoteAddress();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String absoluteURI() {
        if (this.absoluteURI == null) {
            try {
                this.absoluteURI = HttpUtils.absoluteURI(this.conn.getServerOrigin(), this);
            } catch (URISyntaxException e) {
                log.error("Failed to create abs uri", e);
            }
        }
        return this.absoluteURI;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public SSLSession sslSession() {
        return this.conn.sslSession();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException {
        return this.conn.peerCertificateChain();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public NetSocket netSocket() {
        NetSocket netSocket;
        synchronized (this.conn) {
            netSocket = this.response.netSocket(method() == HttpMethod.CONNECT);
        }
        return netSocket;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public HttpServerRequest uploadHandler(Handler<HttpServerFileUpload> handler) {
        synchronized (this.conn) {
            if (handler != null) {
                checkEnded();
                this.uploadHandler = handler;
            } else {
                this.uploadHandler = handler;
            }
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public MultiMap formAttributes() {
        return attributes();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String getFormAttribute(String attributeName) {
        return formAttributes().get(attributeName);
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public ServerWebSocket upgrade() {
        ServerWebSocketImpl ws = this.conn.createWebSocket(this);
        if (ws == null) {
            throw new IllegalStateException("Can't upgrade this request");
        }
        ws.accept();
        return ws;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public HttpServerRequest setExpectMultipart(boolean expect) {
        synchronized (this.conn) {
            checkEnded();
            if (expect) {
                if (this.decoder == null) {
                    String contentType = this.request.headers().get(HttpHeaderNames.CONTENT_TYPE);
                    if (contentType != null) {
                        io.netty.handler.codec.http.HttpMethod method = this.request.method();
                        if (isValidMultipartContentType(contentType) && isValidMultipartMethod(method)) {
                            this.decoder = new HttpPostRequestDecoder(new NettyFileUploadDataFactory(this.conn.getContext(), this, () -> {
                                return this.uploadHandler;
                            }), this.request);
                        }
                    }
                }
            } else {
                this.decoder = null;
            }
        }
        return this;
    }

    private boolean isValidMultipartContentType(String contentType) {
        return HttpHeaderValues.MULTIPART_FORM_DATA.regionMatches(true, 0, contentType, 0, HttpHeaderValues.MULTIPART_FORM_DATA.length()) || HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.regionMatches(true, 0, contentType, 0, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.length());
    }

    private boolean isValidMultipartMethod(io.netty.handler.codec.http.HttpMethod method) {
        return method.equals(io.netty.handler.codec.http.HttpMethod.POST) || method.equals(io.netty.handler.codec.http.HttpMethod.PUT) || method.equals(io.netty.handler.codec.http.HttpMethod.PATCH) || method.equals(io.netty.handler.codec.http.HttpMethod.DELETE);
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public boolean isExpectMultipart() {
        boolean z;
        synchronized (this.conn) {
            z = this.decoder != null;
        }
        return z;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public SocketAddress localAddress() {
        return this.conn.localAddress();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public boolean isEnded() {
        boolean z;
        synchronized (this.conn) {
            z = this.ended && (this.pending == null || (!this.pending.isPaused() && this.pending.isEmpty()));
        }
        return z;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public HttpServerRequest customFrameHandler(Handler<HttpFrame> handler) {
        return this;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public HttpConnection connection() {
        return this.conn;
    }

    private void onData(Buffer data) {
        Handler<Buffer> handler;
        synchronized (this.conn) {
            this.bytesRead += data.length();
            if (this.decoder != null) {
                try {
                    this.decoder.offer(new DefaultHttpContent(data.getByteBuf()));
                } catch (HttpPostRequestDecoder.ErrorDataDecoderException e) {
                    handleException(e);
                }
                handler = this.dataHandler;
            } else {
                handler = this.dataHandler;
            }
        }
        if (handler != null) {
            handler.handle(data);
        }
    }

    void handleEnd() {
        InboundBuffer<Object> queue;
        synchronized (this.conn) {
            this.ended = true;
            queue = this.pending;
        }
        if (queue != null) {
            queue.write((InboundBuffer<Object>) InboundBuffer.END_SENTINEL);
        } else {
            onEnd();
        }
    }

    private void onEnd() {
        Handler<Void> handler;
        synchronized (this.conn) {
            if (this.decoder != null) {
                endDecode();
            }
            handler = this.endHandler;
        }
        if (handler != null) {
            handler.handle(null);
        }
    }

    private void endDecode() {
        try {
            try {
                this.decoder.offer(LastHttpContent.EMPTY_LAST_CONTENT);
                while (this.decoder.hasNext()) {
                    InterfaceHttpData data = this.decoder.next();
                    if (data instanceof Attribute) {
                        Attribute attr = (Attribute) data;
                        try {
                            attributes().add(attr.getName(), attr.getValue());
                        } catch (Exception e) {
                            handleException(e);
                        }
                    }
                }
                this.decoder.destroy();
            } catch (HttpPostRequestDecoder.EndOfDataDecoderException e2) {
                this.decoder.destroy();
            } catch (HttpPostRequestDecoder.ErrorDataDecoderException e3) {
                handleException(e3);
                this.decoder.destroy();
            }
        } catch (Throwable th) {
            this.decoder.destroy();
            throw th;
        }
    }

    void handleException(Throwable t) {
        Handler<Throwable> handler = null;
        HttpServerResponseImpl resp = null;
        InterfaceHttpData upload = null;
        synchronized (this.conn) {
            if (!isEnded()) {
                handler = this.exceptionHandler;
                if (this.decoder != null) {
                    upload = this.decoder.currentPartialHttpData();
                }
            }
            if (!this.response.ended()) {
                if (Metrics.METRICS_ENABLED) {
                    reportRequestReset();
                }
                resp = this.response;
            }
        }
        if (resp != null) {
            resp.handleException(t);
        }
        if (upload instanceof NettyFileUpload) {
            ((NettyFileUpload) upload).handleException(t);
        }
        if (handler != null) {
            handler.handle(t);
        }
    }

    private void reportRequestReset() {
        if (this.conn.metrics != null) {
            this.conn.metrics.requestReset(this.metric);
        }
    }

    private void sendNotImplementedAndClose() {
        response().setStatusCode(501).end();
        response().close();
    }

    private void checkEnded() {
        if (isEnded()) {
            throw new IllegalStateException("Request has already been read");
        }
    }

    private MultiMap attributes() {
        if (this.attributes == null) {
            this.attributes = new CaseInsensitiveHeaders();
        }
        return this.attributes;
    }

    private static String urlDecode(String str) {
        return QueryStringDecoder.decodeComponent(str, CharsetUtil.UTF_8);
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public HttpServerRequest streamPriorityHandler(Handler<StreamPriority> handler) {
        return this;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public Cookie getCookie(String name) {
        return this.response.cookies().get(name);
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public int cookieCount() {
        return this.response.cookies().size();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public Map<String, Cookie> cookieMap() {
        return this.response.cookies();
    }
}
