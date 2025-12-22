package io.vertx.core.http.impl;

import ch.qos.logback.classic.spi.CallerData;
import io.vertx.core.AsyncResult;
import io.vertx.core.Closeable;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.VertxException;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.RequestOptions;
import io.vertx.core.http.WebSocket;
import io.vertx.core.http.WebSocketConnectOptions;
import io.vertx.core.http.WebsocketVersion;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.ProxyOptions;
import io.vertx.core.net.ProxyType;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.net.impl.SSLHelper;
import io.vertx.core.spi.metrics.HttpClientMetrics;
import io.vertx.core.spi.metrics.Metrics;
import io.vertx.core.spi.metrics.MetricsProvider;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/HttpClientImpl.class */
public class HttpClientImpl implements HttpClient, MetricsProvider {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) HttpClientImpl.class);
    private final VertxInternal vertx;
    private final HttpClientOptions options;
    private final ContextInternal creatingContext;
    private final ConnectionManager websocketCM;
    private final ConnectionManager httpCM;
    private final Closeable closeHook;
    private final ProxyType proxyType;
    private final SSLHelper sslHelper;
    private final HttpClientMetrics metrics;
    private final boolean keepAlive;
    private final boolean pipelining;
    private volatile boolean closed;
    private volatile Handler<HttpConnection> connectionHandler;
    private final Function<HttpClientResponse, Future<HttpClientRequest>> DEFAULT_HANDLER = resp -> {
        boolean ssl;
        try {
            int statusCode = resp.statusCode();
            String location = resp.getHeader(HttpHeaders.LOCATION);
            if (location == null) {
                return null;
            }
            if (statusCode == 301 || statusCode == 302 || statusCode == 303 || statusCode == 307 || statusCode == 308) {
                HttpMethod m = resp.request().method();
                if (statusCode == 303) {
                    m = HttpMethod.GET;
                } else if (m != HttpMethod.GET && m != HttpMethod.HEAD) {
                    return null;
                }
                URI uri = HttpUtils.resolveURIReference(resp.request().absoluteURI(), location);
                int port = uri.getPort();
                String protocol = uri.getScheme();
                char chend = protocol.charAt(protocol.length() - 1);
                if (chend == 'p') {
                    ssl = false;
                    if (port == -1) {
                        port = 80;
                    }
                } else if (chend == 's') {
                    ssl = true;
                    if (port == -1) {
                        port = 443;
                    }
                } else {
                    return null;
                }
                String requestURI = uri.getPath();
                String query = uri.getQuery();
                if (query != null) {
                    requestURI = requestURI + CallerData.NA + query;
                }
                return Future.succeededFuture(createRequest(m, null, uri.getHost(), port, Boolean.valueOf(ssl), requestURI, null));
            }
            return null;
        } catch (Exception e) {
            return Future.failedFuture(e);
        }
    };
    private volatile Function<HttpClientResponse, Future<HttpClientRequest>> redirectHandler = this.DEFAULT_HANDLER;

    public HttpClientImpl(VertxInternal vertx, HttpClientOptions options) {
        this.vertx = vertx;
        this.metrics = vertx.metricsSPI() != null ? vertx.metricsSPI().createHttpClientMetrics(options) : null;
        this.options = new HttpClientOptions(options);
        List<HttpVersion> alpnVersions = options.getAlpnVersions();
        if (alpnVersions == null || alpnVersions.isEmpty()) {
            switch (options.getProtocolVersion()) {
                case HTTP_2:
                    alpnVersions = Arrays.asList(HttpVersion.HTTP_2, HttpVersion.HTTP_1_1);
                    break;
                default:
                    alpnVersions = Collections.singletonList(options.getProtocolVersion());
                    break;
            }
        }
        this.keepAlive = options.isKeepAlive();
        this.pipelining = options.isPipelining();
        this.sslHelper = new SSLHelper(options, options.getKeyCertOptions(), options.getTrustOptions()).setApplicationProtocols(alpnVersions);
        this.sslHelper.validate(vertx);
        this.creatingContext = vertx.getContext();
        this.closeHook = completionHandler -> {
            close();
            completionHandler.handle(Future.succeededFuture());
        };
        if (this.creatingContext != null) {
            if (this.creatingContext.isMultiThreadedWorkerContext()) {
                throw new IllegalStateException("Cannot use HttpClient in a multi-threaded worker verticle");
            }
            if (options.getProtocolVersion() == HttpVersion.HTTP_2 && Context.isOnWorkerThread()) {
                throw new IllegalStateException("Cannot use HttpClient with HTTP_2 in a worker");
            }
            this.creatingContext.addCloseHook(this.closeHook);
        }
        if (!this.keepAlive && this.pipelining) {
            throw new IllegalStateException("Cannot have pipelining with no keep alive");
        }
        long maxWeight = options.getMaxPoolSize() * options.getHttp2MaxPoolSize();
        this.websocketCM = new ConnectionManager(this, this.metrics, HttpVersion.HTTP_1_1, maxWeight, options.getMaxWaitQueueSize());
        this.httpCM = new ConnectionManager(this, this.metrics, options.getProtocolVersion(), maxWeight, options.getMaxWaitQueueSize());
        this.proxyType = options.getProxyOptions() != null ? options.getProxyOptions().getType() : null;
        this.httpCM.start();
        this.websocketCM.start();
    }

    HttpClientMetrics metrics() {
        return this.metrics;
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(RequestOptions options, Handler<WebSocket> wsConnect) {
        return websocket(options, (MultiMap) null, wsConnect);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(int port, String host, String requestURI, Handler<WebSocket> wsConnect) {
        websocketStream(port, host, requestURI, (MultiMap) null, (WebsocketVersion) null).handler2(wsConnect);
        return this;
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(RequestOptions options, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
        return websocket(options, (MultiMap) null, wsConnect, failureHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(int port, String host, String requestURI, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
        websocketStream(port, host, requestURI, (MultiMap) null, (WebsocketVersion) null).subscribe(wsConnect, failureHandler);
        return this;
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(String host, String requestURI, Handler<WebSocket> wsConnect) {
        return websocket(this.options.getDefaultPort(), host, requestURI, wsConnect);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(String host, String requestURI, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
        return websocket(this.options.getDefaultPort(), host, requestURI, wsConnect, failureHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(RequestOptions options, MultiMap headers, Handler<WebSocket> wsConnect) {
        return websocket(options, headers, (WebsocketVersion) null, wsConnect);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(int port, String host, String requestURI, MultiMap headers, Handler<WebSocket> wsConnect) {
        websocketStream(port, host, requestURI, headers, (WebsocketVersion) null).handler2(wsConnect);
        return this;
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(RequestOptions options, MultiMap headers, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
        return websocket(options, headers, (WebsocketVersion) null, wsConnect, failureHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(int port, String host, String requestURI, MultiMap headers, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
        websocketStream(port, host, requestURI, headers, (WebsocketVersion) null).subscribe(wsConnect, failureHandler);
        return this;
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(String host, String requestURI, MultiMap headers, Handler<WebSocket> wsConnect) {
        return websocket(this.options.getDefaultPort(), host, requestURI, headers, wsConnect);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(String host, String requestURI, MultiMap headers, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
        return websocket(this.options.getDefaultPort(), host, requestURI, headers, wsConnect, failureHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(RequestOptions options, MultiMap headers, WebsocketVersion version, Handler<WebSocket> wsConnect) {
        return websocket(options, headers, version, (String) null, wsConnect);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(int port, String host, String requestURI, MultiMap headers, WebsocketVersion version, Handler<WebSocket> wsConnect) {
        websocketStream(port, host, requestURI, headers, version, (String) null).handler2(wsConnect);
        return this;
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(RequestOptions options, MultiMap headers, WebsocketVersion version, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
        return websocket(options, headers, version, (String) null, wsConnect, failureHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(int port, String host, String requestURI, MultiMap headers, WebsocketVersion version, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
        websocketStream(port, host, requestURI, headers, version, (String) null).subscribe(wsConnect, failureHandler);
        return this;
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(String host, String requestURI, MultiMap headers, WebsocketVersion version, Handler<WebSocket> wsConnect) {
        return websocket(this.options.getDefaultPort(), host, requestURI, headers, version, wsConnect);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(String host, String requestURI, MultiMap headers, WebsocketVersion version, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
        return websocket(this.options.getDefaultPort(), host, requestURI, headers, version, wsConnect, failureHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(RequestOptions options, MultiMap headers, WebsocketVersion version, String subProtocols, Handler<WebSocket> wsConnect) {
        websocketStream(options, headers, version, subProtocols).handler2(wsConnect);
        return this;
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(int port, String host, String requestURI, MultiMap headers, WebsocketVersion version, String subProtocols, Handler<WebSocket> wsConnect) {
        websocketStream(port, host, requestURI, headers, version, subProtocols).handler2(wsConnect);
        return this;
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocketAbs(String url, MultiMap headers, WebsocketVersion version, String subProtocols, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
        websocketStreamAbs(url, headers, version, subProtocols).subscribe(wsConnect, failureHandler);
        return this;
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(RequestOptions options, MultiMap headers, WebsocketVersion version, String subProtocols, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
        websocketStream(options, headers, version, subProtocols).subscribe(wsConnect, failureHandler);
        return this;
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(int port, String host, String requestURI, MultiMap headers, WebsocketVersion version, String subProtocols, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
        websocketStream(port, host, requestURI, headers, version, subProtocols).subscribe(wsConnect, failureHandler);
        return this;
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(String host, String requestURI, MultiMap headers, WebsocketVersion version, String subProtocols, Handler<WebSocket> wsConnect) {
        return websocket(this.options.getDefaultPort(), host, requestURI, headers, version, subProtocols, wsConnect);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(String host, String requestURI, MultiMap headers, WebsocketVersion version, String subProtocols, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
        return websocket(this.options.getDefaultPort(), host, requestURI, headers, version, subProtocols, wsConnect, failureHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(String requestURI, Handler<WebSocket> wsConnect) {
        return websocket(this.options.getDefaultPort(), this.options.getDefaultHost(), requestURI, wsConnect);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(String requestURI, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
        return websocket(this.options.getDefaultPort(), this.options.getDefaultHost(), requestURI, wsConnect, failureHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(String requestURI, MultiMap headers, Handler<WebSocket> wsConnect) {
        return websocket(this.options.getDefaultPort(), this.options.getDefaultHost(), requestURI, headers, wsConnect);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(String requestURI, MultiMap headers, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
        return websocket(this.options.getDefaultPort(), this.options.getDefaultHost(), requestURI, headers, wsConnect, failureHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(String requestURI, MultiMap headers, WebsocketVersion version, Handler<WebSocket> wsConnect) {
        return websocket(this.options.getDefaultPort(), this.options.getDefaultHost(), requestURI, headers, version, wsConnect);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(String requestURI, MultiMap headers, WebsocketVersion version, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
        return websocket(this.options.getDefaultPort(), this.options.getDefaultHost(), requestURI, headers, version, wsConnect, failureHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(String requestURI, MultiMap headers, WebsocketVersion version, String subProtocols, Handler<WebSocket> wsConnect) {
        return websocket(this.options.getDefaultPort(), this.options.getDefaultHost(), requestURI, headers, version, subProtocols, wsConnect);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient websocket(String requestURI, MultiMap headers, WebsocketVersion version, String subProtocols, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
        return websocket(this.options.getDefaultPort(), this.options.getDefaultHost(), requestURI, headers, version, subProtocols, wsConnect, failureHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public void webSocket(WebSocketConnectOptions connectOptions, Handler<AsyncResult<WebSocket>> handler) {
        ContextInternal ctx = this.vertx.getOrCreateContext();
        SocketAddress addr = SocketAddress.inetSocketAddress(connectOptions.getPort(), connectOptions.getHost());
        this.websocketCM.getConnection(ctx, addr, connectOptions.isSsl() != null ? connectOptions.isSsl().booleanValue() : this.options.isSsl(), addr, ar -> {
            if (ar.succeeded()) {
                Http1xClientConnection conn = (Http1xClientConnection) ar.result();
                conn.toWebSocket(connectOptions.getURI(), connectOptions.getHeaders(), connectOptions.getVersion(), connectOptions.getSubProtocols(), this.options.getMaxWebsocketFrameSize(), handler);
            } else {
                ctx.executeFromIO(v -> {
                    handler.handle(Future.failedFuture(ar.cause()));
                });
            }
        });
    }

    @Override // io.vertx.core.http.HttpClient
    public void webSocket(int port, String host, String requestURI, Handler<AsyncResult<WebSocket>> handler) {
        webSocket(new WebSocketConnectOptions().setURI(requestURI).setHost(host).setPort(port), handler);
    }

    @Override // io.vertx.core.http.HttpClient
    public void webSocket(String host, String requestURI, Handler<AsyncResult<WebSocket>> handler) {
        webSocket(this.options.getDefaultPort(), host, requestURI, handler);
    }

    @Override // io.vertx.core.http.HttpClient
    public void webSocket(String requestURI, Handler<AsyncResult<WebSocket>> handler) {
        webSocket(this.options.getDefaultPort(), this.options.getDefaultHost(), requestURI, handler);
    }

    @Override // io.vertx.core.http.HttpClient
    public void webSocketAbs(String url, MultiMap headers, WebsocketVersion version, List<String> subProtocols, Handler<AsyncResult<WebSocket>> handler) {
        WebSocketConnectOptions options = new WebSocketConnectOptions();
        parseWebSocketRequestOptions(options, url);
        options.setHeaders(headers);
        options.setVersion(version);
        options.setSubProtocols(subProtocols);
        webSocket(options, handler);
    }

    @Override // io.vertx.core.http.HttpClient
    public WebSocketStream websocketStream(RequestOptions options) {
        return websocketStream(options, (MultiMap) null);
    }

    @Override // io.vertx.core.http.HttpClient
    public WebSocketStream websocketStream(int port, String host, String requestURI) {
        return websocketStream(port, host, requestURI, (MultiMap) null, (WebsocketVersion) null);
    }

    @Override // io.vertx.core.http.HttpClient
    public WebSocketStream websocketStream(String host, String requestURI) {
        return websocketStream(this.options.getDefaultPort(), host, requestURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public WebSocketStream websocketStream(RequestOptions options, MultiMap headers) {
        return websocketStream(options, headers, (WebsocketVersion) null);
    }

    @Override // io.vertx.core.http.HttpClient
    public WebSocketStream websocketStream(int port, String host, String requestURI, MultiMap headers) {
        return websocketStream(port, host, requestURI, headers, (WebsocketVersion) null);
    }

    @Override // io.vertx.core.http.HttpClient
    public WebSocketStream websocketStream(String host, String requestURI, MultiMap headers) {
        return websocketStream(this.options.getDefaultPort(), host, requestURI, headers);
    }

    @Override // io.vertx.core.http.HttpClient
    public WebSocketStream websocketStream(RequestOptions options, MultiMap headers, WebsocketVersion version) {
        return websocketStream(options, headers, version, (String) null);
    }

    @Override // io.vertx.core.http.HttpClient
    public WebSocketStream websocketStream(int port, String host, String requestURI, MultiMap headers, WebsocketVersion version) {
        return websocketStream(port, host, requestURI, headers, version, (String) null);
    }

    @Override // io.vertx.core.http.HttpClient
    public WebSocketStream websocketStream(String host, String requestURI, MultiMap headers, WebsocketVersion version) {
        return websocketStream(this.options.getDefaultPort(), host, requestURI, headers, version);
    }

    private void parseWebSocketRequestOptions(RequestOptions options, String url) {
        try {
            URI uri = new URI(url);
            String scheme = uri.getScheme();
            if (!"ws".equals(scheme) && !"wss".equals(scheme)) {
                throw new IllegalArgumentException("Scheme: " + scheme);
            }
            boolean ssl = scheme.length() == 3;
            int port = uri.getPort();
            if (port == -1) {
                port = ssl ? 443 : 80;
            }
            StringBuilder relativeUri = new StringBuilder();
            if (uri.getRawPath() != null) {
                relativeUri.append(uri.getRawPath());
            }
            if (uri.getRawQuery() != null) {
                relativeUri.append('?').append(uri.getRawQuery());
            }
            if (uri.getRawFragment() != null) {
                relativeUri.append('#').append(uri.getRawFragment());
            }
            options.setHost(uri.getHost()).setPort(port).setSsl(Boolean.valueOf(ssl)).setURI(relativeUri.toString());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // io.vertx.core.http.HttpClient
    public WebSocketStream websocketStreamAbs(String url, MultiMap headers, WebsocketVersion version, String subProtocols) {
        RequestOptions options = new RequestOptions();
        parseWebSocketRequestOptions(options, url);
        return websocketStream(options, headers, version, subProtocols);
    }

    @Override // io.vertx.core.http.HttpClient
    public WebSocketStream websocketStream(RequestOptions options, MultiMap headers, WebsocketVersion version, String subProtocols) {
        WebSocketConnectOptions connectOptions = new WebSocketConnectOptions(options);
        connectOptions.setHeaders(headers);
        connectOptions.setVersion(version);
        if (subProtocols != null) {
            connectOptions.setSubProtocols(Arrays.asList(subProtocols.split(",")));
        }
        return new WebSocketStream(connectOptions);
    }

    @Override // io.vertx.core.http.HttpClient
    public WebSocketStream websocketStream(int port, String host, String requestURI, MultiMap headers, WebsocketVersion version, String subProtocols) {
        return websocketStream(new RequestOptions().setPort(port).setHost(host).setURI(requestURI), headers, version, subProtocols);
    }

    @Override // io.vertx.core.http.HttpClient
    public WebSocketStream websocketStream(String host, String requestURI, MultiMap headers, WebsocketVersion version, String subProtocols) {
        return websocketStream(this.options.getDefaultPort(), host, requestURI, headers, version, subProtocols);
    }

    @Override // io.vertx.core.http.HttpClient
    public WebSocketStream websocketStream(String requestURI) {
        return websocketStream(this.options.getDefaultPort(), this.options.getDefaultHost(), requestURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public WebSocketStream websocketStream(String requestURI, MultiMap headers) {
        return websocketStream(this.options.getDefaultPort(), this.options.getDefaultHost(), requestURI, headers);
    }

    @Override // io.vertx.core.http.HttpClient
    public WebSocketStream websocketStream(String requestURI, MultiMap headers, WebsocketVersion version) {
        return websocketStream(this.options.getDefaultPort(), this.options.getDefaultHost(), requestURI, headers, version);
    }

    @Override // io.vertx.core.http.HttpClient
    public WebSocketStream websocketStream(String requestURI, MultiMap headers, WebsocketVersion version, String subProtocols) {
        return websocketStream(this.options.getDefaultPort(), this.options.getDefaultHost(), requestURI, headers, version, subProtocols);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest requestAbs(HttpMethod method, String absoluteURI, Handler<HttpClientResponse> responseHandler) {
        return requestAbs(method, null, absoluteURI, responseHandler);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [io.vertx.core.http.HttpClientRequest] */
    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest requestAbs(HttpMethod method, SocketAddress serverAddress, String absoluteURI, Handler<HttpClientResponse> responseHandler) {
        return requestAbs(method, serverAddress, absoluteURI).handler2(responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest get(RequestOptions options) {
        return request(HttpMethod.GET, options);
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [io.vertx.core.http.HttpClientRequest] */
    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest request(HttpMethod method, int port, String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
        Objects.requireNonNull(responseHandler, "no null responseHandler accepted");
        return request(method, port, host, requestURI).handler2(responseHandler);
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [io.vertx.core.http.HttpClientRequest] */
    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest request(HttpMethod method, SocketAddress serverAddress, int port, String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
        Objects.requireNonNull(responseHandler, "no null responseHandler accepted");
        return request(method, serverAddress, port, host, requestURI).handler2(responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest request(HttpMethod method, String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
        return request(method, this.options.getDefaultPort(), host, requestURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest request(HttpMethod method, String requestURI) {
        return request(method, this.options.getDefaultPort(), this.options.getDefaultHost(), requestURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest request(HttpMethod method, String requestURI, Handler<HttpClientResponse> responseHandler) {
        return request(method, this.options.getDefaultPort(), this.options.getDefaultHost(), requestURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest requestAbs(HttpMethod method, String absoluteURI) {
        return requestAbs(method, (SocketAddress) null, absoluteURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest requestAbs(HttpMethod method, SocketAddress serverAddress, String absoluteURI) {
        URL url = parseUrl(absoluteURI);
        Boolean ssl = false;
        int port = url.getPort();
        String relativeUri = url.getPath().isEmpty() ? "/" + url.getFile() : url.getFile();
        String protocol = url.getProtocol();
        if ("ftp".equals(protocol)) {
            if (port == -1) {
                port = 21;
            }
        } else {
            char chend = protocol.charAt(protocol.length() - 1);
            if (chend == 'p') {
                if (port == -1) {
                    port = 80;
                }
            } else if (chend == 's') {
                ssl = true;
                if (port == -1) {
                    port = 443;
                }
            }
        }
        return createRequest(method, serverAddress, protocol, url.getHost(), port, ssl, relativeUri, null);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest request(HttpMethod method, int port, String host, String requestURI) {
        return createRequest(method, null, host, port, null, requestURI, null);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest request(HttpMethod method, SocketAddress serverAddress, int port, String host, String requestURI) {
        return createRequest(method, serverAddress, host, port, null, requestURI, null);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [io.vertx.core.http.HttpClientRequest] */
    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest request(HttpMethod method, RequestOptions options, Handler<HttpClientResponse> responseHandler) {
        return request(method, options).handler2(responseHandler);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [io.vertx.core.http.HttpClientRequest] */
    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest request(HttpMethod method, SocketAddress serverAddress, RequestOptions options, Handler<HttpClientResponse> responseHandler) {
        return request(method, serverAddress, options).handler2(responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest request(HttpMethod method, SocketAddress serverAddress, RequestOptions options) {
        return createRequest(method, serverAddress, options.getHost(), options.getPort(), options.isSsl(), options.getURI(), null);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest request(HttpMethod method, RequestOptions options) {
        return createRequest(method, null, options.getHost(), options.getPort(), options.isSsl(), options.getURI(), options.getHeaders());
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest request(HttpMethod method, String host, String requestURI) {
        return request(method, this.options.getDefaultPort(), host, requestURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest get(int port, String host, String requestURI) {
        return request(HttpMethod.GET, port, host, requestURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest get(String host, String requestURI) {
        return get(this.options.getDefaultPort(), host, requestURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest get(RequestOptions options, Handler<HttpClientResponse> responseHandler) {
        return request(HttpMethod.GET, options, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest get(int port, String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
        return request(HttpMethod.GET, port, host, requestURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest get(String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
        return get(this.options.getDefaultPort(), host, requestURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest get(String requestURI) {
        return request(HttpMethod.GET, requestURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest get(String requestURI, Handler<HttpClientResponse> responseHandler) {
        return request(HttpMethod.GET, requestURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest getAbs(String absoluteURI) {
        return requestAbs(HttpMethod.GET, absoluteURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest getAbs(String absoluteURI, Handler<HttpClientResponse> responseHandler) {
        return requestAbs(HttpMethod.GET, absoluteURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient getNow(RequestOptions options, Handler<HttpClientResponse> responseHandler) {
        return requestNow(HttpMethod.GET, options, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient getNow(int port, String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
        get(port, host, requestURI, responseHandler).end();
        return this;
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient getNow(String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
        return getNow(this.options.getDefaultPort(), host, requestURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient getNow(String requestURI, Handler<HttpClientResponse> responseHandler) {
        get(requestURI, responseHandler).end();
        return this;
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest post(RequestOptions options) {
        return request(HttpMethod.POST, options);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest post(int port, String host, String requestURI) {
        return request(HttpMethod.POST, port, host, requestURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest post(String host, String requestURI) {
        return post(this.options.getDefaultPort(), host, requestURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest post(RequestOptions options, Handler<HttpClientResponse> responseHandler) {
        return request(HttpMethod.POST, options, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest post(int port, String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
        return request(HttpMethod.POST, port, host, requestURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest post(String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
        return post(this.options.getDefaultPort(), host, requestURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest post(String requestURI) {
        return request(HttpMethod.POST, requestURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest post(String requestURI, Handler<HttpClientResponse> responseHandler) {
        return request(HttpMethod.POST, requestURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest postAbs(String absoluteURI) {
        return requestAbs(HttpMethod.POST, absoluteURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest postAbs(String absoluteURI, Handler<HttpClientResponse> responseHandler) {
        return requestAbs(HttpMethod.POST, absoluteURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest head(RequestOptions options) {
        return request(HttpMethod.HEAD, options);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest head(int port, String host, String requestURI) {
        return request(HttpMethod.HEAD, port, host, requestURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest head(String host, String requestURI) {
        return head(this.options.getDefaultPort(), host, requestURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest head(RequestOptions options, Handler<HttpClientResponse> responseHandler) {
        return request(HttpMethod.HEAD, options, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest head(int port, String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
        return request(HttpMethod.HEAD, port, host, requestURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest head(String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
        return head(this.options.getDefaultPort(), host, requestURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest head(String requestURI) {
        return request(HttpMethod.HEAD, requestURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest head(String requestURI, Handler<HttpClientResponse> responseHandler) {
        return request(HttpMethod.HEAD, requestURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest headAbs(String absoluteURI) {
        return requestAbs(HttpMethod.HEAD, absoluteURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest headAbs(String absoluteURI, Handler<HttpClientResponse> responseHandler) {
        return requestAbs(HttpMethod.HEAD, absoluteURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient headNow(RequestOptions options, Handler<HttpClientResponse> responseHandler) {
        return requestNow(HttpMethod.HEAD, options, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient headNow(int port, String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
        head(port, host, requestURI, responseHandler).end();
        return this;
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient headNow(String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
        return headNow(this.options.getDefaultPort(), host, requestURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient headNow(String requestURI, Handler<HttpClientResponse> responseHandler) {
        head(requestURI, responseHandler).end();
        return this;
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest options(RequestOptions options) {
        return request(HttpMethod.OPTIONS, options);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest options(int port, String host, String requestURI) {
        return request(HttpMethod.OPTIONS, port, host, requestURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest options(String host, String requestURI) {
        return options(this.options.getDefaultPort(), host, requestURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest options(RequestOptions options, Handler<HttpClientResponse> responseHandler) {
        return request(HttpMethod.OPTIONS, options, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest options(int port, String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
        return request(HttpMethod.OPTIONS, port, host, requestURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest options(String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
        return options(this.options.getDefaultPort(), host, requestURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest options(String requestURI) {
        return request(HttpMethod.OPTIONS, requestURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest options(String requestURI, Handler<HttpClientResponse> responseHandler) {
        return request(HttpMethod.OPTIONS, requestURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest optionsAbs(String absoluteURI) {
        return requestAbs(HttpMethod.OPTIONS, absoluteURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest optionsAbs(String absoluteURI, Handler<HttpClientResponse> responseHandler) {
        return requestAbs(HttpMethod.OPTIONS, absoluteURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient optionsNow(RequestOptions options, Handler<HttpClientResponse> responseHandler) {
        return requestNow(HttpMethod.OPTIONS, options, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient optionsNow(int port, String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
        options(port, host, requestURI, responseHandler).end();
        return this;
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient optionsNow(String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
        return optionsNow(this.options.getDefaultPort(), host, requestURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient optionsNow(String requestURI, Handler<HttpClientResponse> responseHandler) {
        options(requestURI, responseHandler).end();
        return this;
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest put(RequestOptions options) {
        return request(HttpMethod.PUT, options);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest put(int port, String host, String requestURI) {
        return request(HttpMethod.PUT, port, host, requestURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest put(String host, String requestURI) {
        return put(this.options.getDefaultPort(), host, requestURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest put(RequestOptions options, Handler<HttpClientResponse> responseHandler) {
        return request(HttpMethod.PUT, options, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest put(int port, String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
        return request(HttpMethod.PUT, port, host, requestURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest put(String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
        return put(this.options.getDefaultPort(), host, requestURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest put(String requestURI) {
        return request(HttpMethod.PUT, requestURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest put(String requestURI, Handler<HttpClientResponse> responseHandler) {
        return request(HttpMethod.PUT, requestURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest putAbs(String absoluteURI) {
        return requestAbs(HttpMethod.PUT, absoluteURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest putAbs(String absoluteURI, Handler<HttpClientResponse> responseHandler) {
        return requestAbs(HttpMethod.PUT, absoluteURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest delete(RequestOptions options) {
        return request(HttpMethod.DELETE, options);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest delete(int port, String host, String requestURI) {
        return request(HttpMethod.DELETE, port, host, requestURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest delete(String host, String requestURI) {
        return delete(this.options.getDefaultPort(), host, requestURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest delete(RequestOptions options, Handler<HttpClientResponse> responseHandler) {
        return request(HttpMethod.DELETE, options, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest delete(int port, String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
        return request(HttpMethod.DELETE, port, host, requestURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest delete(String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
        return delete(this.options.getDefaultPort(), host, requestURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest delete(String requestURI) {
        return request(HttpMethod.DELETE, requestURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest delete(String requestURI, Handler<HttpClientResponse> responseHandler) {
        return request(HttpMethod.DELETE, requestURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest deleteAbs(String absoluteURI) {
        return requestAbs(HttpMethod.DELETE, absoluteURI);
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClientRequest deleteAbs(String absoluteURI, Handler<HttpClientResponse> responseHandler) {
        return requestAbs(HttpMethod.DELETE, absoluteURI, responseHandler);
    }

    @Override // io.vertx.core.http.HttpClient
    public void close() {
        synchronized (this) {
            checkClosed();
            this.closed = true;
        }
        if (this.creatingContext != null) {
            this.creatingContext.removeCloseHook(this.closeHook);
        }
        this.websocketCM.close();
        this.httpCM.close();
        if (this.metrics != null) {
            this.metrics.close();
        }
    }

    @Override // io.vertx.core.metrics.Measured
    public boolean isMetricsEnabled() {
        return getMetrics() != null;
    }

    @Override // io.vertx.core.spi.metrics.MetricsProvider
    public Metrics getMetrics() {
        return this.metrics;
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient connectionHandler(Handler<HttpConnection> handler) {
        this.connectionHandler = handler;
        return this;
    }

    Handler<HttpConnection> connectionHandler() {
        return this.connectionHandler;
    }

    @Override // io.vertx.core.http.HttpClient
    public HttpClient redirectHandler(Function<HttpClientResponse, Future<HttpClientRequest>> handler) {
        if (handler == null) {
            handler = this.DEFAULT_HANDLER;
        }
        this.redirectHandler = handler;
        return this;
    }

    @Override // io.vertx.core.http.HttpClient
    public Function<HttpClientResponse, Future<HttpClientRequest>> redirectHandler() {
        return this.redirectHandler;
    }

    public HttpClientOptions getOptions() {
        return this.options;
    }

    void getConnectionForRequest(ContextInternal ctx, SocketAddress peerAddress, boolean ssl, SocketAddress server, Handler<AsyncResult<HttpClientStream>> handler) {
        this.httpCM.getConnection(ctx, peerAddress, ssl, server, ar -> {
            if (ar.succeeded()) {
                ((HttpClientConnection) ar.result()).createStream(handler);
            } else {
                handler.handle(Future.failedFuture(ar.cause()));
            }
        });
    }

    public VertxInternal getVertx() {
        return this.vertx;
    }

    SSLHelper getSslHelper() {
        return this.sslHelper;
    }

    private URL parseUrl(String surl) {
        try {
            return new URL(surl);
        } catch (MalformedURLException e) {
            throw new VertxException("Invalid url: " + surl, e);
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [io.vertx.core.http.HttpClientRequest] */
    private HttpClient requestNow(HttpMethod method, RequestOptions options, Handler<HttpClientResponse> responseHandler) {
        createRequest(method, null, options.getHost(), options.getPort(), options.isSsl(), options.getURI(), null).handler2(responseHandler).end();
        return this;
    }

    private HttpClientRequest createRequest(HttpMethod method, SocketAddress serverAddress, String host, int port, Boolean ssl, String relativeURI, MultiMap headers) {
        return createRequest(method, serverAddress, (ssl == null || !ssl.booleanValue()) ? "http" : "https", host, port, ssl, relativeURI, headers);
    }

    private HttpClientRequest createRequest(HttpMethod method, SocketAddress server, String protocol, String host, int port, Boolean ssl, String relativeURI, MultiMap headers) {
        HttpClientRequest req;
        Objects.requireNonNull(method, "no null method accepted");
        Objects.requireNonNull(protocol, "no null protocol accepted");
        Objects.requireNonNull(host, "no null host accepted");
        Objects.requireNonNull(relativeURI, "no null relativeURI accepted");
        boolean useAlpn = this.options.isUseAlpn();
        boolean useSSL = ssl != null ? ssl.booleanValue() : this.options.isSsl();
        if (!useAlpn && useSSL && this.options.getProtocolVersion() == HttpVersion.HTTP_2) {
            throw new IllegalArgumentException("Must enable ALPN when using H2");
        }
        checkClosed();
        boolean useProxy = !useSSL && this.proxyType == ProxyType.HTTP;
        if (useProxy) {
            int defaultPort = protocol.equals("ftp") ? 21 : 80;
            String addPort = (port == -1 || port == defaultPort) ? "" : ":" + port;
            String relativeURI2 = protocol + "://" + host + addPort + relativeURI;
            ProxyOptions proxyOptions = this.options.getProxyOptions();
            if (proxyOptions.getUsername() != null && proxyOptions.getPassword() != null) {
                if (headers == null) {
                    headers = MultiMap.caseInsensitiveMultiMap();
                }
                headers.add("Proxy-Authorization", "Basic " + Base64.getEncoder().encodeToString((proxyOptions.getUsername() + ":" + proxyOptions.getPassword()).getBytes()));
            }
            req = new HttpClientRequestImpl(this, useSSL, method, SocketAddress.inetSocketAddress(proxyOptions.getPort(), proxyOptions.getHost()), host, port, relativeURI2, this.vertx);
        } else {
            if (server == null) {
                server = SocketAddress.inetSocketAddress(port, host);
            }
            req = new HttpClientRequestImpl(this, useSSL, method, server, host, port, relativeURI, this.vertx);
        }
        if (headers != null) {
            req.headers().setAll(headers);
        }
        return req;
    }

    private synchronized void checkClosed() {
        if (this.closed) {
            throw new IllegalStateException("Client is closed");
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/HttpClientImpl$WebSocketStream.class */
    private class WebSocketStream implements ReadStream<WebSocket> {
        private WebSocketConnectOptions options;
        private Handler<WebSocket> handler;
        private Handler<Throwable> exceptionHandler;
        private Handler<Void> endHandler;

        @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
        public /* bridge */ /* synthetic */ StreamBase exceptionHandler(Handler handler) {
            return exceptionHandler((Handler<Throwable>) handler);
        }

        WebSocketStream(WebSocketConnectOptions options) {
            this.options = options;
        }

        void subscribe(Handler<WebSocket> completionHandler, Handler<Throwable> failureHandler) {
            Future<WebSocket> fut = Future.future();
            fut.setHandler2(ar -> {
                if (ar.succeeded()) {
                    completionHandler.handle(ar.result());
                } else if (failureHandler == null) {
                    HttpClientImpl.log.error(ar.cause());
                } else {
                    failureHandler.handle(ar.cause());
                }
            });
            HttpClientImpl.this.webSocket(this.options, fut);
        }

        @Override // io.vertx.core.streams.ReadStream
        public synchronized ReadStream<WebSocket> endHandler(Handler<Void> endHandler) {
            this.endHandler = endHandler;
            return this;
        }

        @Override // io.vertx.core.streams.ReadStream
        /* renamed from: pause */
        public ReadStream<WebSocket> pause2() {
            return this;
        }

        @Override // io.vertx.core.streams.ReadStream
        /* renamed from: resume */
        public ReadStream<WebSocket> resume2() {
            return this;
        }

        @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
        public synchronized ReadStream<WebSocket> exceptionHandler(Handler<Throwable> handler) {
            this.exceptionHandler = handler;
            return this;
        }

        @Override // io.vertx.core.streams.ReadStream
        /* renamed from: handler */
        public ReadStream<WebSocket> handler2(Handler<WebSocket> handler) {
            if (this.handler == null && handler != null) {
                this.handler = handler;
                subscribe(ws -> {
                    handler.handle(ws);
                    if (this.endHandler != null) {
                        this.endHandler.handle(null);
                    }
                }, err -> {
                    if (this.exceptionHandler != null) {
                        this.exceptionHandler.handle(err);
                    }
                    if (this.endHandler != null) {
                        this.endHandler.handle(null);
                    }
                });
            }
            return this;
        }

        @Override // io.vertx.core.streams.ReadStream
        /* renamed from: fetch */
        public ReadStream<WebSocket> fetch2(long amount) {
            return this;
        }
    }

    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }
}
