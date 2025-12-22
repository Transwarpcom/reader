package io.vertx.core.http.impl;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.StreamResetException;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.WriteStream;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/HttpClientRequestBase.class */
public abstract class HttpClientRequestBase implements HttpClientRequest {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) HttpClientRequestImpl.class);
    protected final HttpClientImpl client;
    protected final HttpMethod method;
    protected final String uri;
    protected final String path;
    protected final String query;
    protected final String host;
    protected final int port;
    protected final SocketAddress server;
    protected final boolean ssl;
    private Handler<Throwable> exceptionHandler;
    private long currentTimeoutTimerId = -1;
    private long currentTimeoutMs;
    private long lastDataReceived;
    protected Throwable exceptionOccurred;
    private boolean paused;
    private HttpClientResponse response;

    abstract void handleResponse(HttpClientResponse httpClientResponse, long j);

    abstract boolean reset(Throwable th);

    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.WriteStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ WriteStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.WriteStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.WriteStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ ReadStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    HttpClientRequestBase(HttpClientImpl client, boolean ssl, HttpMethod method, SocketAddress server, String host, int port, String uri) {
        this.client = client;
        this.uri = uri;
        this.method = method;
        this.server = server;
        this.host = host;
        this.port = port;
        this.path = uri.length() > 0 ? HttpUtils.parsePath(uri) : "";
        this.query = HttpUtils.parseQuery(uri);
        this.ssl = ssl;
    }

    protected void checkEnded() {
    }

    protected String hostHeader() {
        if ((this.port == 80 && !this.ssl) || (this.port == 443 && this.ssl)) {
            return this.host;
        }
        return this.host + ':' + this.port;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public String absoluteURI() {
        return (this.ssl ? "https://" : "http://") + hostHeader() + this.uri;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public String query() {
        return this.query;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public String path() {
        return this.path;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public String uri() {
        return this.uri;
    }

    public String host() {
        return this.server.host();
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpMethod method() {
        return this.method;
    }

    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.WriteStream, io.vertx.core.streams.StreamBase
    public synchronized HttpClientRequest exceptionHandler(Handler<Throwable> handler) {
        if (handler != null) {
            checkEnded();
            this.exceptionHandler = handler;
        } else {
            this.exceptionHandler = null;
        }
        return this;
    }

    synchronized Handler<Throwable> exceptionHandler() {
        return this.exceptionHandler;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public synchronized HttpClientRequest setTimeout(long timeoutMs) {
        cancelTimeout();
        this.currentTimeoutMs = timeoutMs;
        this.currentTimeoutTimerId = this.client.getVertx().setTimer(timeoutMs, id -> {
            handleTimeout(timeoutMs);
        });
        return this;
    }

    public void handleException(Throwable t) {
        Handler<Throwable> handler;
        synchronized (this) {
            cancelTimeout();
            this.exceptionOccurred = t;
            if (this.exceptionHandler != null) {
                handler = this.exceptionHandler;
            } else {
                Logger logger = log;
                logger.getClass();
                handler = (v1) -> {
                    r0.error(v1);
                };
            }
        }
        handler.handle(t);
    }

    void handleResponse(HttpClientResponse resp) {
        synchronized (this) {
            this.response = resp;
        }
        checkHandleResponse();
    }

    private void checkHandleResponse() {
        synchronized (this) {
            if (this.response != null) {
                if (this.paused) {
                    return;
                }
                long timeoutMS = cancelTimeout();
                HttpClientResponse resp = this.response;
                this.response = null;
                try {
                    handleResponse(resp, timeoutMS);
                } catch (Throwable t) {
                    handleException(t);
                }
            }
        }
    }

    private synchronized long cancelTimeout() {
        long j = this.currentTimeoutTimerId;
        long ret = j;
        if (j != -1) {
            this.client.getVertx().cancelTimer(this.currentTimeoutTimerId);
            this.currentTimeoutTimerId = -1L;
            ret = this.currentTimeoutMs;
            this.currentTimeoutMs = 0L;
        }
        return ret;
    }

    private void handleTimeout(long timeoutMs) {
        synchronized (this) {
            if (this.lastDataReceived > 0) {
                long now = System.currentTimeMillis();
                long timeSinceLastData = now - this.lastDataReceived;
                if (timeSinceLastData < timeoutMs) {
                    this.lastDataReceived = 0L;
                    setTimeout(timeoutMs - timeSinceLastData);
                    return;
                }
            }
            String msg = "The timeout period of " + timeoutMs + "ms has been exceeded while executing " + this.method + " " + this.uri + " for server " + this.server;
            reset(new NoStackTraceTimeoutException(msg));
        }
    }

    synchronized void dataReceived() {
        if (this.currentTimeoutTimerId != -1) {
            this.lastDataReceived = System.currentTimeMillis();
        }
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public boolean reset(long code) {
        return reset(new StreamResetException(code));
    }

    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    public ReadStream<HttpClientResponse> pause2() {
        this.paused = true;
        return this;
    }

    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    public ReadStream<HttpClientResponse> resume2() {
        synchronized (this) {
            if (this.paused) {
                this.paused = false;
                checkHandleResponse();
                return this;
            }
            return this;
        }
    }

    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    public synchronized ReadStream<HttpClientResponse> fetch2(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException();
        }
        if (amount > 0) {
            resume2();
        }
        return this;
    }
}
