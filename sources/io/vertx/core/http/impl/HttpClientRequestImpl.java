package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.http.impl.headers.VertxHttpHeaders;
import io.vertx.core.impl.Arguments;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.WriteStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/HttpClientRequestImpl.class */
public class HttpClientRequestImpl extends HttpClientRequestBase implements HttpClientRequest {
    static final Logger log = LoggerFactory.getLogger((Class<?>) HttpClientRequestImpl.class);
    private final VertxInternal vertx;
    private boolean chunked;
    private String hostHeader;
    private String rawMethod;
    private Handler<Void> continueHandler;
    private Handler<Void> drainHandler;
    private Handler<HttpClientRequest> pushHandler;
    private Handler<HttpConnection> connectionHandler;
    private Handler<Throwable> exceptionHandler;
    private Promise<Void> endPromise;
    private Future<Void> endFuture;
    private boolean ended;
    private Throwable reset;
    private ByteBuf pendingChunks;
    private List<Handler<AsyncResult<Void>>> pendingHandlers;
    private int pendingMaxSize;
    private int followRedirects;
    private VertxHttpHeaders headers;
    private StreamPriority priority;
    private HttpClientStream stream;
    private boolean connecting;
    private Handler<HttpClientResponse> respHandler;
    private Handler<Void> endHandler;

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

    HttpClientRequestImpl(HttpClientImpl client, boolean ssl, HttpMethod method, SocketAddress server, String host, int port, String relativeURI, VertxInternal vertx) {
        super(client, ssl, method, server, host, port, relativeURI);
        this.endPromise = Promise.promise();
        this.endFuture = this.endPromise.future();
        this.pendingMaxSize = -1;
        this.chunked = false;
        this.vertx = vertx;
        this.priority = HttpUtils.DEFAULT_STREAM_PRIORITY;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public synchronized int streamId() {
        if (this.stream == null) {
            return -1;
        }
        return this.stream.id();
    }

    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    public synchronized ReadStream<HttpClientResponse> handler2(Handler<HttpClientResponse> handler) {
        if (handler != null) {
            checkEnded();
        }
        this.respHandler = handler;
        return this;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public synchronized HttpClientRequest setFollowRedirects(boolean followRedirects) {
        checkEnded();
        if (followRedirects) {
            this.followRedirects = this.client.getOptions().getMaxRedirects() - 1;
        } else {
            this.followRedirects = 0;
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public synchronized HttpClientRequest setMaxRedirects(int maxRedirects) {
        Arguments.require(maxRedirects >= 0, "Max redirects must be >= 0");
        checkEnded();
        this.followRedirects = maxRedirects;
        return this;
    }

    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.ReadStream
    public synchronized ReadStream<HttpClientResponse> endHandler(Handler<Void> handler) {
        if (handler != null) {
            checkEnded();
        }
        this.endHandler = handler;
        return this;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public synchronized HttpClientRequestImpl setChunked(boolean chunked) {
        checkEnded();
        if (this.stream != null) {
            throw new IllegalStateException("Cannot set chunked after data has been written on request");
        }
        if (this.client.getOptions().getProtocolVersion() != HttpVersion.HTTP_1_0) {
            this.chunked = chunked;
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public synchronized boolean isChunked() {
        return this.chunked;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public synchronized String getRawMethod() {
        return this.rawMethod;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public synchronized HttpClientRequest setRawMethod(String method) {
        this.rawMethod = method;
        return this;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public synchronized HttpClientRequest setHost(String host) {
        this.hostHeader = host;
        return this;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public synchronized String getHost() {
        return this.hostHeader;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public synchronized MultiMap headers() {
        if (this.headers == null) {
            this.headers = new VertxHttpHeaders();
        }
        return this.headers;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public synchronized HttpClientRequest putHeader(String name, String value) {
        checkEnded();
        headers().set(name, value);
        return this;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public synchronized HttpClientRequest putHeader(String name, Iterable<String> values) {
        checkEnded();
        headers().mo1936set(name, values);
        return this;
    }

    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.WriteStream
    /* renamed from: setWriteQueueMaxSize */
    public synchronized WriteStream<Buffer> setWriteQueueMaxSize2(int maxSize) {
        checkEnded();
        if (this.stream == null) {
            this.pendingMaxSize = maxSize;
        } else {
            this.stream.doSetWriteQueueMaxSize(maxSize);
        }
        return this;
    }

    @Override // io.vertx.core.streams.WriteStream
    public synchronized boolean writeQueueFull() {
        checkEnded();
        synchronized (this) {
            checkEnded();
            if (this.stream == null) {
                return false;
            }
            return this.stream.isNotWritable();
        }
    }

    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.WriteStream
    public synchronized WriteStream<Buffer> drainHandler(Handler<Void> handler) {
        if (handler != null) {
            checkEnded();
            this.drainHandler = handler;
            if (this.stream == null) {
                return this;
            }
            this.stream.getContext().runOnContext(v -> {
                if (!this.stream.isNotWritable()) {
                    handleDrained();
                }
            });
        } else {
            this.drainHandler = null;
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public synchronized HttpClientRequest continueHandler(Handler<Void> handler) {
        if (handler != null) {
            checkEnded();
        }
        this.continueHandler = handler;
        return this;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest sendHead() {
        return sendHead(null);
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public synchronized HttpClientRequest sendHead(Handler<HttpVersion> headersHandler) {
        checkEnded();
        checkResponseHandler();
        if (this.stream != null) {
            throw new IllegalStateException("Head already written");
        }
        connect(headersHandler);
        return this;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public synchronized HttpClientRequest putHeader(CharSequence name, CharSequence value) {
        checkEnded();
        headers().set(name, value);
        return this;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public synchronized HttpClientRequest putHeader(CharSequence name, Iterable<CharSequence> values) {
        checkEnded();
        headers().mo1935set(name, values);
        return this;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public synchronized HttpClientRequest pushHandler(Handler<HttpClientRequest> handler) {
        this.pushHandler = handler;
        return this;
    }

    @Override // io.vertx.core.http.impl.HttpClientRequestBase
    boolean reset(Throwable cause) {
        synchronized (this) {
            if (this.reset != null) {
                return false;
            }
            this.reset = cause;
            HttpClientStream s = this.stream;
            if (s != null) {
                s.reset(cause);
                return true;
            }
            handleException(cause);
            return true;
        }
    }

    private void tryComplete() {
        this.endPromise.tryComplete();
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public synchronized HttpConnection connection() {
        if (this.stream == null) {
            return null;
        }
        return this.stream.connection();
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public synchronized HttpClientRequest connectionHandler(Handler<HttpConnection> handler) {
        this.connectionHandler = handler;
        return this;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest writeCustomFrame(int type, int flags, Buffer payload) {
        HttpClientStream s;
        synchronized (this) {
            checkEnded();
            s = this.stream;
            if (s == null) {
                throw new IllegalStateException("Not yet connected");
            }
        }
        s.writeFrame(type, flags, payload.getByteBuf());
        return this;
    }

    void handleDrained() {
        synchronized (this) {
            Handler<Void> handler = this.drainHandler;
            if (handler == null || this.endFuture.isComplete()) {
                return;
            }
            try {
                handler.handle(null);
            } catch (Throwable t) {
                handleException(t);
            }
        }
    }

    private void handleNextRequest(HttpClientRequest next, long timeoutMs) {
        next.handler2(this.respHandler);
        next.exceptionHandler(exceptionHandler());
        exceptionHandler((Handler<Throwable>) null);
        next.endHandler(this.endHandler);
        next.pushHandler(this.pushHandler);
        next.setMaxRedirects(this.followRedirects - 1);
        if (next.getHost() == null) {
            next.setHost(this.hostHeader);
        }
        if (this.headers != null) {
            next.headers().addAll(this.headers);
        }
        this.endFuture.setHandler2(ar -> {
            if (ar.succeeded()) {
                if (timeoutMs > 0) {
                    next.setTimeout(timeoutMs);
                }
                next.end();
                return;
            }
            next.reset(0L);
        });
    }

    @Override // io.vertx.core.http.impl.HttpClientRequestBase
    public void handleException(Throwable t) {
        super.handleException(t);
        this.endPromise.tryFail(t);
    }

    @Override // io.vertx.core.http.impl.HttpClientRequestBase
    void handleResponse(HttpClientResponse resp, long timeoutMs) {
        Future<HttpClientRequest> next;
        if (this.reset == null) {
            int statusCode = resp.statusCode();
            if (this.followRedirects > 0 && statusCode >= 300 && statusCode < 400 && (next = this.client.redirectHandler().apply(resp)) != null) {
                next.setHandler2(ar -> {
                    if (ar.succeeded()) {
                        handleNextRequest((HttpClientRequest) ar.result(), timeoutMs);
                    } else {
                        handleException(ar.cause());
                    }
                });
                return;
            }
            if (this.respHandler != null) {
                this.respHandler.handle(resp);
            }
            if (this.endHandler != null) {
                this.endHandler.handle(null);
            }
        }
    }

    @Override // io.vertx.core.http.impl.HttpClientRequestBase
    protected String hostHeader() {
        return this.hostHeader != null ? this.hostHeader : super.hostHeader();
    }

    private synchronized void connect(Handler<HttpVersion> headersHandler) {
        SocketAddress peerAddress;
        Handler<HttpConnection> initializer;
        if (!this.connecting) {
            if (this.method == HttpMethod.OTHER && this.rawMethod == null) {
                throw new IllegalStateException("You must provide a rawMethod when using an HttpMethod.OTHER method");
            }
            if (this.hostHeader != null) {
                int idx = this.hostHeader.lastIndexOf(58);
                if (idx != -1) {
                    peerAddress = SocketAddress.inetSocketAddress(Integer.parseInt(this.hostHeader.substring(idx + 1)), this.hostHeader.substring(0, idx));
                } else {
                    peerAddress = SocketAddress.inetSocketAddress(80, this.hostHeader);
                }
            } else {
                String peerHost = this.host;
                if (peerHost.endsWith(".")) {
                    peerHost = peerHost.substring(0, peerHost.length() - 1);
                }
                peerAddress = SocketAddress.inetSocketAddress(this.port, peerHost);
            }
            Handler<HttpConnection> h1 = this.connectionHandler;
            Handler<HttpConnection> h2 = this.client.connectionHandler();
            if (h1 != null) {
                if (h2 != null) {
                    initializer = conn -> {
                        h1.handle(conn);
                        h2.handle(conn);
                    };
                } else {
                    initializer = h1;
                }
            } else {
                initializer = h2;
            }
            ContextInternal connectCtx = this.vertx.getOrCreateContext();
            this.connecting = true;
            Handler<HttpConnection> handler = initializer;
            this.client.getConnectionForRequest(connectCtx, peerAddress, this.ssl, this.server, ar1 -> {
                if (ar1.succeeded()) {
                    HttpClientStream stream = (HttpClientStream) ar1.result();
                    ContextInternal ctx = (ContextInternal) stream.getContext();
                    if (stream.id() == 1 && handler != null) {
                        ctx.executeFromIO(v -> {
                            handler.handle(stream.connection());
                        });
                    }
                    if (this.reset != null) {
                        stream.reset(this.reset);
                        return;
                    } else {
                        ctx.executeFromIO(v2 -> {
                            connected(headersHandler, stream);
                        });
                        return;
                    }
                }
                connectCtx.executeFromIO(v3 -> {
                    handleException(ar1.cause());
                });
            });
        }
    }

    private void connected(Handler<HttpVersion> headersHandler, HttpClientStream stream) {
        synchronized (this) {
            this.stream = stream;
            stream.beginRequest(this);
            if (this.pendingMaxSize != -1) {
                stream.doSetWriteQueueMaxSize(this.pendingMaxSize);
            }
            ByteBuf pending = null;
            Handler<AsyncResult<Void>> handler = null;
            if (this.pendingChunks != null) {
                List<Handler<AsyncResult<Void>>> handlers = this.pendingHandlers;
                this.pendingHandlers = null;
                pending = this.pendingChunks;
                this.pendingChunks = null;
                if (handlers != null) {
                    handler = ar -> {
                        handlers.forEach(h -> {
                            h.handle(ar);
                        });
                    };
                }
            }
            stream.writeHead(this.method, this.rawMethod, this.uri, this.headers, hostHeader(), this.chunked, pending, this.ended, this.priority, this.continueHandler, handler);
            if (this.ended) {
                stream.endRequest();
                tryComplete();
            }
            this.connecting = false;
            this.stream = stream;
        }
        if (headersHandler != null) {
            headersHandler.handle(stream.version());
        }
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public void end(String chunk) {
        end(chunk, (Handler<AsyncResult<Void>>) null);
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public void end(String chunk, Handler<AsyncResult<Void>> handler) {
        end(Buffer.buffer(chunk), handler);
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public void end(String chunk, String enc) {
        end(chunk, enc, null);
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public void end(String chunk, String enc, Handler<AsyncResult<Void>> handler) {
        Objects.requireNonNull(enc, "no null encoding accepted");
        end(Buffer.buffer(chunk, enc), handler);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.WriteStream
    public void end(Buffer chunk) {
        write(chunk.getByteBuf(), true, (Handler<AsyncResult<Void>>) null);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.http.HttpClientRequest
    public void end(Buffer chunk, Handler<AsyncResult<Void>> handler) {
        write(chunk.getByteBuf(), true, handler);
    }

    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.WriteStream
    public void end() {
        write((ByteBuf) null, true, (Handler<AsyncResult<Void>>) null);
    }

    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.WriteStream
    public void end(Handler<AsyncResult<Void>> handler) {
        write((ByteBuf) null, true, handler);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.http.HttpClientRequest, io.vertx.core.streams.WriteStream
    public HttpClientRequest write(Buffer chunk) {
        return write(chunk, (Handler<AsyncResult<Void>>) null);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest write(Buffer chunk, Handler<AsyncResult<Void>> handler) {
        ByteBuf buf = chunk.getByteBuf();
        write(buf, false, handler);
        return this;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest write(String chunk) {
        return write(chunk, (Handler<AsyncResult<Void>>) null);
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest write(String chunk, Handler<AsyncResult<Void>> handler) {
        write(Buffer.buffer(chunk).getByteBuf(), false, handler);
        return this;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest write(String chunk, String enc) {
        return write(chunk, enc, (Handler<AsyncResult<Void>>) null);
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public HttpClientRequest write(String chunk, String enc, Handler<AsyncResult<Void>> handler) {
        Objects.requireNonNull(enc, "no null encoding accepted");
        write(Buffer.buffer(chunk, enc).getByteBuf(), false, handler);
        return this;
    }

    private boolean requiresContentLength() {
        return !this.chunked && (this.headers == null || !this.headers.contains(HttpHeaders.CONTENT_LENGTH));
    }

    private void write(ByteBuf buff, boolean end, Handler<AsyncResult<Void>> completionHandler) {
        CompositeByteBuf pending;
        if (buff == null && !end) {
            return;
        }
        synchronized (this) {
            checkEnded();
            checkResponseHandler();
            if (end) {
                if (buff != null && requiresContentLength()) {
                    headers().set(HttpHeaders.CONTENT_LENGTH, String.valueOf(buff.readableBytes()));
                }
            } else if (requiresContentLength()) {
                throw new IllegalStateException("You must set the Content-Length header to be the total size of the message body BEFORE sending any data if you are not using HTTP chunked encoding.");
            }
            this.ended |= end;
            if (this.stream == null) {
                if (buff != null) {
                    if (this.pendingChunks == null) {
                        this.pendingChunks = buff;
                    } else {
                        if (this.pendingChunks instanceof CompositeByteBuf) {
                            pending = (CompositeByteBuf) this.pendingChunks;
                        } else {
                            pending = Unpooled.compositeBuffer();
                            pending.addComponent(true, this.pendingChunks);
                            this.pendingChunks = pending;
                        }
                        pending.addComponent(true, buff);
                    }
                    if (completionHandler != null) {
                        if (this.pendingHandlers == null) {
                            this.pendingHandlers = new ArrayList();
                        }
                        this.pendingHandlers.add(completionHandler);
                    }
                }
                connect(null);
                return;
            }
            HttpClientStream s = this.stream;
            s.writeBuffer(buff, end, completionHandler);
            if (end) {
                s.endRequest();
                tryComplete();
            }
        }
    }

    @Override // io.vertx.core.http.impl.HttpClientRequestBase
    protected void checkEnded() {
        if (this.ended) {
            throw new IllegalStateException("Request already complete");
        }
    }

    private void checkResponseHandler() {
        if (this.respHandler == null) {
            throw new IllegalStateException("You must set an handler for the HttpClientResponse before connecting");
        }
    }

    synchronized Handler<HttpClientRequest> pushHandler() {
        return this.pushHandler;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public synchronized HttpClientRequest setStreamPriority(StreamPriority priority) {
        if (this.stream != null) {
            this.stream.updatePriority(priority);
        } else {
            this.priority = priority;
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpClientRequest
    public synchronized StreamPriority getStreamPriority() {
        HttpClientStream s = this.stream;
        return s != null ? s.priority() : this.priority;
    }
}
