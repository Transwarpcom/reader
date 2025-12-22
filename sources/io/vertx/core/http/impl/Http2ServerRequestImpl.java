package io.vertx.core.http.impl;

import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.handler.codec.http2.Http2Stream;
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
import io.vertx.core.http.StreamResetException;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.spi.metrics.HttpServerMetrics;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import java.net.URISyntaxException;
import java.nio.channels.ClosedChannelException;
import java.util.Map;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.security.cert.X509Certificate;
import okhttp3.internal.http2.Header;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/Http2ServerRequestImpl.class */
public class Http2ServerRequestImpl extends Http2ServerStream implements HttpServerRequest {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) HttpServerRequestImpl.class);
    private final String serverOrigin;
    private final MultiMap headersMap;
    private final String scheme;
    private String path;
    private String query;
    private MultiMap params;
    private String absoluteURI;
    private MultiMap attributes;
    private Handler<Buffer> dataHandler;
    private Handler<Void> endHandler;
    private boolean streamEnded;
    private boolean ended;
    private Handler<HttpServerFileUpload> uploadHandler;
    private HttpPostRequestDecoder postRequestDecoder;
    private Handler<Throwable> exceptionHandler;
    private Handler<HttpFrame> customFrameHandler;
    private Handler<StreamPriority> streamPriorityHandler;

    @Override // io.vertx.core.http.impl.Http2ServerStream, io.vertx.core.http.HttpServerRequest
    public /* bridge */ /* synthetic */ String rawMethod() {
        return super.rawMethod();
    }

    @Override // io.vertx.core.http.impl.Http2ServerStream, io.vertx.core.http.HttpServerRequest
    public /* bridge */ /* synthetic */ HttpMethod method() {
        return super.method();
    }

    @Override // io.vertx.core.http.impl.VertxHttp2Stream
    public /* bridge */ /* synthetic */ void doFetch(long j) {
        super.doFetch(j);
    }

    @Override // io.vertx.core.http.impl.VertxHttp2Stream
    public /* bridge */ /* synthetic */ void doPause() {
        super.doPause();
    }

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

    public Http2ServerRequestImpl(Http2ServerConnection conn, Http2Stream stream, HttpServerMetrics metrics, String serverOrigin, Http2Headers headers, String contentEncoding, boolean writable, boolean streamEnded) {
        super(conn, stream, headers, contentEncoding, serverOrigin, writable);
        String scheme = headers.get(Header.TARGET_SCHEME_UTF8) != null ? headers.get(Header.TARGET_SCHEME_UTF8).toString() : null;
        headers.remove(Header.TARGET_METHOD_UTF8);
        headers.remove(Header.TARGET_SCHEME_UTF8);
        headers.remove(Header.TARGET_PATH_UTF8);
        headers.remove(Header.TARGET_AUTHORITY_UTF8);
        Http2HeadersAdaptor headersMap = new Http2HeadersAdaptor(headers);
        this.serverOrigin = serverOrigin;
        this.streamEnded = streamEnded;
        this.scheme = scheme;
        this.headersMap = headersMap;
    }

    @Override // io.vertx.core.http.impl.Http2ServerStream, io.vertx.core.http.impl.VertxHttp2Stream
    void handleInterestedOpsChanged() {
        this.response.writabilityChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.vertx.core.http.impl.VertxHttp2Stream
    public void handleException(Throwable cause) {
        boolean notify;
        synchronized (((Http2ServerConnection) this.conn)) {
            notify = !this.ended;
        }
        if (notify) {
            notifyException(cause);
        }
        this.response.handleException(cause);
    }

    private void notifyException(Throwable failure) {
        Handler<Throwable> handler;
        InterfaceHttpData upload = null;
        synchronized (((Http2ServerConnection) this.conn)) {
            handler = this.exceptionHandler;
            if (this.postRequestDecoder != null) {
                upload = this.postRequestDecoder.currentPartialHttpData();
            }
        }
        if (handler != null) {
            handler.handle(failure);
        }
        if (upload instanceof NettyFileUpload) {
            ((NettyFileUpload) upload).handleException(failure);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.vertx.core.http.impl.Http2ServerStream, io.vertx.core.http.impl.VertxHttp2Stream
    public void handleClose() {
        boolean notify;
        super.handleClose();
        synchronized (((Http2ServerConnection) this.conn)) {
            notify = !this.streamEnded;
        }
        if (notify) {
            notifyException(new ClosedChannelException());
        }
        this.response.handleClose();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.vertx.core.http.impl.VertxHttp2Stream
    public void handleCustomFrame(int type, int flags, Buffer buff) {
        if (this.customFrameHandler != null) {
            this.customFrameHandler.handle(new HttpFrameImpl(type, flags, buff));
        }
    }

    @Override // io.vertx.core.http.impl.VertxHttp2Stream
    void handleData(Buffer data) {
        if (this.postRequestDecoder != null) {
            try {
                this.postRequestDecoder.offer(new DefaultHttpContent(data.getByteBuf()));
            } catch (Exception e) {
                handleException(e);
            }
        }
        if (this.dataHandler != null) {
            this.dataHandler.handle(data);
        }
    }

    /* JADX WARN: Finally extract failed */
    @Override // io.vertx.core.http.impl.VertxHttp2Stream
    void handleEnd(MultiMap trailers) {
        Handler<Void> handler;
        synchronized (((Http2ServerConnection) this.conn)) {
            this.streamEnded = true;
            this.ended = true;
            try {
                if (this.postRequestDecoder != null) {
                    try {
                        this.postRequestDecoder.offer(LastHttpContent.EMPTY_LAST_CONTENT);
                        while (this.postRequestDecoder.hasNext()) {
                            InterfaceHttpData data = this.postRequestDecoder.next();
                            if (data instanceof Attribute) {
                                Attribute attr = (Attribute) data;
                                try {
                                    formAttributes().add(attr.getName(), attr.getValue());
                                } catch (Exception e) {
                                    handleException(e);
                                }
                            }
                        }
                        this.postRequestDecoder.destroy();
                    } catch (HttpPostRequestDecoder.EndOfDataDecoderException e2) {
                        this.postRequestDecoder.destroy();
                    } catch (Exception e3) {
                        handleException(e3);
                        this.postRequestDecoder.destroy();
                    }
                }
                handler = this.endHandler;
            } catch (Throwable th) {
                this.postRequestDecoder.destroy();
                throw th;
            }
        }
        if (handler != null) {
            handler.handle(null);
        }
    }

    @Override // io.vertx.core.http.impl.VertxHttp2Stream
    void handleReset(long errorCode) {
        boolean notify;
        synchronized (((Http2ServerConnection) this.conn)) {
            notify = !this.ended;
            this.ended = true;
        }
        if (notify) {
            notifyException(new StreamResetException(errorCode));
        }
        this.response.handleReset(errorCode);
    }

    private void checkEnded() {
        if (this.ended) {
            throw new IllegalStateException("Request has already been read");
        }
    }

    @Override // io.vertx.core.http.HttpServerRequest, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public HttpServerRequest exceptionHandler(Handler<Throwable> handler) {
        synchronized (((Http2ServerConnection) this.conn)) {
            this.exceptionHandler = handler;
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerRequest, io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    public ReadStream<Buffer> handler2(Handler<Buffer> handler) {
        synchronized (((Http2ServerConnection) this.conn)) {
            if (handler != null) {
                checkEnded();
                this.dataHandler = handler;
            } else {
                this.dataHandler = handler;
            }
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerRequest, io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    public ReadStream<Buffer> pause2() {
        synchronized (((Http2ServerConnection) this.conn)) {
            checkEnded();
            doPause();
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerRequest, io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    public ReadStream<Buffer> resume2() {
        return fetch2(Long.MAX_VALUE);
    }

    @Override // io.vertx.core.http.HttpServerRequest, io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    public ReadStream<Buffer> fetch2(long amount) {
        synchronized (((Http2ServerConnection) this.conn)) {
            checkEnded();
            doFetch(amount);
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerRequest, io.vertx.core.streams.ReadStream
    public ReadStream<Buffer> endHandler(Handler<Void> handler) {
        synchronized (((Http2ServerConnection) this.conn)) {
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
    public HttpVersion version() {
        return HttpVersion.HTTP_2;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public boolean isSSL() {
        return ((Http2ServerConnection) this.conn).isSsl();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String uri() {
        return this.uri;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String path() {
        String str;
        synchronized (((Http2ServerConnection) this.conn)) {
            this.path = this.uri != null ? HttpUtils.parsePath(this.uri) : null;
            str = this.path;
        }
        return str;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String query() {
        String str;
        synchronized (((Http2ServerConnection) this.conn)) {
            this.query = this.uri != null ? HttpUtils.parseQuery(this.uri) : null;
            str = this.query;
        }
        return str;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String scheme() {
        return this.scheme;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String host() {
        return this.host;
    }

    @Override // io.vertx.core.http.impl.VertxHttp2Stream, io.vertx.core.http.HttpServerRequest
    public long bytesRead() {
        return super.bytesRead();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public Http2ServerResponseImpl response() {
        return this.response;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public MultiMap headers() {
        return this.headersMap;
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
        MultiMap multiMap;
        synchronized (((Http2ServerConnection) this.conn)) {
            if (this.params == null) {
                this.params = HttpUtils.params(uri());
            }
            multiMap = this.params;
        }
        return multiMap;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String getParam(String paramName) {
        return params().get(paramName);
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public SocketAddress remoteAddress() {
        return ((Http2ServerConnection) this.conn).remoteAddress();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public SocketAddress localAddress() {
        return ((Http2ServerConnection) this.conn).localAddress();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public SSLSession sslSession() {
        return ((Http2ServerConnection) this.conn).sslSession();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException {
        return ((Http2ServerConnection) this.conn).peerCertificateChain();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String absoluteURI() {
        String str;
        if (this.method == HttpMethod.CONNECT) {
            return null;
        }
        synchronized (((Http2ServerConnection) this.conn)) {
            if (this.absoluteURI == null) {
                try {
                    this.absoluteURI = HttpUtils.absoluteURI(this.serverOrigin, this);
                } catch (URISyntaxException e) {
                    log.error("Failed to create abs uri", e);
                }
                str = this.absoluteURI;
            } else {
                str = this.absoluteURI;
            }
        }
        return str;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public NetSocket netSocket() {
        return this.response.netSocket();
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public HttpServerRequest setExpectMultipart(boolean expect) {
        synchronized (((Http2ServerConnection) this.conn)) {
            checkEnded();
            if (expect) {
                if (this.postRequestDecoder == null) {
                    String contentType = this.headersMap.get(HttpHeaderNames.CONTENT_TYPE);
                    if (contentType != null) {
                        io.netty.handler.codec.http.HttpMethod method = io.netty.handler.codec.http.HttpMethod.valueOf(this.rawMethod);
                        String lowerCaseContentType = contentType.toString().toLowerCase();
                        boolean isURLEncoded = lowerCaseContentType.startsWith(HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString());
                        if ((lowerCaseContentType.startsWith(HttpHeaderValues.MULTIPART_FORM_DATA.toString()) || isURLEncoded) && (method == io.netty.handler.codec.http.HttpMethod.POST || method == io.netty.handler.codec.http.HttpMethod.PUT || method == io.netty.handler.codec.http.HttpMethod.PATCH || method == io.netty.handler.codec.http.HttpMethod.DELETE)) {
                            HttpRequest req = new DefaultHttpRequest(io.netty.handler.codec.http.HttpVersion.HTTP_1_1, method, this.uri);
                            req.headers().add(HttpHeaderNames.CONTENT_TYPE, contentType);
                            this.postRequestDecoder = new HttpPostRequestDecoder(new NettyFileUploadDataFactory(this.context, this, () -> {
                                return this.uploadHandler;
                            }), req);
                        }
                    }
                }
            } else {
                this.postRequestDecoder = null;
            }
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public boolean isExpectMultipart() {
        boolean z;
        synchronized (((Http2ServerConnection) this.conn)) {
            z = this.postRequestDecoder != null;
        }
        return z;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public HttpServerRequest uploadHandler(Handler<HttpServerFileUpload> handler) {
        synchronized (((Http2ServerConnection) this.conn)) {
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
        MultiMap multiMap;
        synchronized (((Http2ServerConnection) this.conn)) {
            if (this.attributes == null) {
                this.attributes = new CaseInsensitiveHeaders();
            }
            multiMap = this.attributes;
        }
        return multiMap;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public String getFormAttribute(String attributeName) {
        return formAttributes().get(attributeName);
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public ServerWebSocket upgrade() {
        throw new UnsupportedOperationException("HTTP/2 request cannot be upgraded to a websocket");
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public boolean isEnded() {
        boolean z;
        synchronized (((Http2ServerConnection) this.conn)) {
            z = this.ended;
        }
        return z;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public HttpServerRequest customFrameHandler(Handler<HttpFrame> handler) {
        synchronized (((Http2ServerConnection) this.conn)) {
            this.customFrameHandler = handler;
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public HttpConnection connection() {
        return this.conn;
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public HttpServerRequest streamPriorityHandler(Handler<StreamPriority> handler) {
        synchronized (((Http2ServerConnection) this.conn)) {
            this.streamPriorityHandler = handler;
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.vertx.core.http.impl.VertxHttp2Stream
    public void handlePriorityChange(StreamPriority streamPriority) {
        Handler<StreamPriority> handler;
        boolean priorityChanged = false;
        synchronized (((Http2ServerConnection) this.conn)) {
            handler = this.streamPriorityHandler;
            if (streamPriority != null && !streamPriority.equals(streamPriority())) {
                priority(streamPriority);
                priorityChanged = true;
            }
        }
        if (handler != null && priorityChanged) {
            handler.handle(streamPriority);
        }
    }

    @Override // io.vertx.core.http.HttpServerRequest
    public StreamPriority streamPriority() {
        return priority();
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
