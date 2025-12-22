package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http2.DefaultHttp2Headers;
import io.netty.handler.codec.http2.Http2Connection;
import io.netty.handler.codec.http2.Http2Error;
import io.netty.handler.codec.http2.Http2Exception;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.handler.codec.http2.Http2Stream;
import io.netty.util.AsciiString;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.CaseInsensitiveHeaders;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.http.StreamResetException;
import io.vertx.core.http.impl.pool.ConnectionListener;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.impl.ConnectionBase;
import io.vertx.core.spi.metrics.HttpClientMetrics;
import java.util.Map;
import java.util.function.BiConsumer;
import okhttp3.internal.http2.Header;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/Http2ClientConnection.class */
class Http2ClientConnection extends Http2ConnectionBase implements HttpClientConnection {
    private final ConnectionListener<HttpClientConnection> listener;
    private final HttpClientImpl client;
    private final HttpClientMetrics metrics;
    private final Object queueMetric;

    Http2ClientConnection(ConnectionListener<HttpClientConnection> listener, Object queueMetric, HttpClientImpl client, ContextInternal context, VertxHttp2ConnectionHandler connHandler, HttpClientMetrics metrics) {
        super(context, connHandler);
        this.metrics = metrics;
        this.queueMetric = queueMetric;
        this.client = client;
        this.listener = listener;
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase
    synchronized boolean onGoAwaySent(int lastStreamId, long errorCode, ByteBuf debugData) {
        boolean goneAway = super.onGoAwaySent(lastStreamId, errorCode, debugData);
        if (goneAway) {
            this.listener.onEvict();
        }
        return goneAway;
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase
    synchronized boolean onGoAwayReceived(int lastStreamId, long errorCode, ByteBuf debugData) {
        boolean goneAway = super.onGoAwayReceived(lastStreamId, errorCode, debugData);
        if (goneAway) {
            this.listener.onEvict();
        }
        return goneAway;
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase
    protected void concurrencyChanged(long concurrency) {
        int limit = this.client.getOptions().getHttp2MultiplexingLimit();
        if (limit > 0) {
            concurrency = Math.min(concurrency, limit);
        }
        this.listener.onConcurrencyChange(concurrency);
    }

    @Override // io.vertx.core.net.impl.ConnectionBase
    public HttpClientMetrics metrics() {
        return this.metrics;
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase
    void onStreamClosed(Http2Stream nettyStream) {
        super.onStreamClosed(nettyStream);
    }

    void upgradeStream(Object metric, Handler<AsyncResult<HttpClientStream>> completionHandler) {
        Future<HttpClientStream> fut;
        synchronized (this) {
            try {
                Http2ClientStream stream = createStream(this.handler.connection().stream(1));
                stream.metric = metric;
                fut = Future.succeededFuture(stream);
            } catch (Exception e) {
                fut = Future.failedFuture(e);
            }
        }
        completionHandler.handle(fut);
    }

    @Override // io.vertx.core.http.impl.HttpClientConnection
    public void createStream(Handler<AsyncResult<HttpClientStream>> completionHandler) {
        Future<HttpClientStream> fut;
        synchronized (this) {
            Http2Connection conn = this.handler.connection();
            try {
                int id = conn.local().lastStreamCreated() == 0 ? 1 : conn.local().lastStreamCreated() + 2;
                Http2ClientStream stream = createStream(conn.local().createStream(id, false));
                fut = Future.succeededFuture(stream);
            } catch (Exception e) {
                fut = Future.failedFuture(e);
            }
        }
        completionHandler.handle(fut);
    }

    private Http2ClientStream createStream(Http2Stream stream) {
        boolean writable = this.handler.encoder().flowController().isWritable(stream);
        Http2ClientStream clientStream = new Http2ClientStream(this, stream, writable);
        this.streams.put(clientStream.stream.id(), (int) clientStream);
        return clientStream;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recycle() {
        int timeout = this.client.getOptions().getHttp2KeepAliveTimeout();
        long expired = timeout > 0 ? System.currentTimeMillis() + (timeout * 1000) : 0L;
        this.listener.onRecycle(expired);
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.netty.handler.codec.http2.Http2FrameListener
    public synchronized void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int streamDependency, short weight, boolean exclusive, int padding, boolean endOfStream) throws Http2Exception {
        Http2ClientStream stream = (Http2ClientStream) this.streams.get(streamId);
        if (stream != null) {
            StreamPriority streamPriority = new StreamPriority().setDependency(streamDependency).setWeight(weight).setExclusive(exclusive);
            this.context.executeFromIO(v -> {
                stream.handleHeaders(headers, streamPriority, endOfStream);
            });
        }
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public synchronized void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int padding, boolean endOfStream) throws Http2Exception {
        Http2ClientStream stream = (Http2ClientStream) this.streams.get(streamId);
        if (stream != null) {
            this.context.executeFromIO(v -> {
                stream.handleHeaders(headers, null, endOfStream);
            });
        }
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.netty.handler.codec.http2.Http2FrameListener
    public synchronized void onPushPromiseRead(ChannelHandlerContext ctx, int streamId, int promisedStreamId, Http2Headers headers, int padding) throws Http2Exception {
        Handler<HttpClientRequest> pushHandler;
        Http2ClientStream stream = (Http2ClientStream) this.streams.get(streamId);
        if (stream != null && (pushHandler = stream.pushHandler()) != null) {
            this.context.executeFromIO(v -> {
                String host;
                int port;
                String rawMethod = headers.method().toString();
                HttpMethod method = HttpUtils.toVertxMethod(rawMethod);
                String uri = headers.path().toString();
                String authority = headers.authority() != null ? headers.authority().toString() : null;
                MultiMap headersMap = new Http2HeadersAdaptor(headers);
                Http2Stream promisedStream = this.handler.connection().stream(promisedStreamId);
                int pos = authority.indexOf(58);
                if (pos == -1) {
                    host = authority;
                    port = 80;
                } else {
                    host = authority.substring(0, pos);
                    port = Integer.parseInt(authority.substring(pos + 1));
                }
                HttpClientRequestPushPromise pushReq = new HttpClientRequestPushPromise(this, promisedStream, this.client, isSsl(), method, rawMethod, uri, host, port, headersMap);
                if (this.metrics != null) {
                    pushReq.getStream().metric = this.metrics.responsePushed(this.queueMetric, metric(), localAddress(), remoteAddress(), pushReq);
                }
                this.streams.put(promisedStreamId, (int) pushReq.getStream());
                pushHandler.handle(pushReq);
            });
        } else {
            this.handler.writeReset(promisedStreamId, Http2Error.CANCEL.code());
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/Http2ClientConnection$Http2ClientStream.class */
    static class Http2ClientStream extends VertxHttp2Stream<Http2ClientConnection> implements HttpClientStream {
        private HttpClientRequestBase request;
        private HttpClientResponseImpl response;
        private Handler<Void> continueHandler;
        private boolean requestEnded;
        private boolean responseEnded;
        private Object metric;

        Http2ClientStream(Http2ClientConnection conn, Http2Stream stream, boolean writable) {
            super(conn, stream, writable);
        }

        Http2ClientStream(Http2ClientConnection conn, HttpClientRequestPushPromise request, Http2Stream stream, boolean writable) {
            super(conn, stream, writable);
            this.request = request;
        }

        @Override // io.vertx.core.http.impl.VertxHttp2Stream, io.vertx.core.http.impl.HttpClientStream
        public StreamPriority priority() {
            return super.priority();
        }

        @Override // io.vertx.core.http.impl.VertxHttp2Stream, io.vertx.core.http.impl.HttpClientStream
        public void updatePriority(StreamPriority streamPriority) {
            super.updatePriority(streamPriority);
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public HttpVersion version() {
            return HttpVersion.HTTP_2;
        }

        @Override // io.vertx.core.http.impl.VertxHttp2Stream, io.vertx.core.http.impl.HttpClientStream
        public int id() {
            return super.id();
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public Object metric() {
            return this.metric;
        }

        @Override // io.vertx.core.http.impl.VertxHttp2Stream
        void handleEnd(MultiMap trailers) {
            if (((Http2ClientConnection) this.conn).metrics != null) {
                ((Http2ClientConnection) this.conn).metrics.responseEnd(this.metric, this.response);
            }
            this.responseEnded = true;
            if (trailers == null) {
                trailers = new CaseInsensitiveHeaders();
            }
            this.response.handleEnd(trailers);
        }

        @Override // io.vertx.core.http.impl.VertxHttp2Stream
        void handleData(Buffer buf) {
            this.response.handleChunk(buf);
        }

        @Override // io.vertx.core.http.impl.VertxHttp2Stream
        void handleReset(long errorCode) {
            synchronized (((Http2ClientConnection) this.conn)) {
                if (this.responseEnded) {
                    return;
                }
                this.responseEnded = true;
                if (((Http2ClientConnection) this.conn).metrics != null) {
                    ((Http2ClientConnection) this.conn).metrics.requestReset(this.metric);
                }
                handleException(new StreamResetException(errorCode));
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // io.vertx.core.http.impl.VertxHttp2Stream
        public void handleClose() {
            super.handleClose();
            if (this.request == null || (this.request instanceof HttpClientRequestImpl)) {
                ((Http2ClientConnection) this.conn).recycle();
            }
            if (!this.responseEnded) {
                this.responseEnded = true;
                if (((Http2ClientConnection) this.conn).metrics != null) {
                    ((Http2ClientConnection) this.conn).metrics.requestReset(this.metric);
                }
                handleException(ConnectionBase.CLOSED_EXCEPTION);
            }
        }

        @Override // io.vertx.core.http.impl.VertxHttp2Stream
        void handleInterestedOpsChanged() {
            if ((this.request instanceof HttpClientRequestImpl) && !isNotWritable() && !isNotWritable()) {
                ((HttpClientRequestImpl) this.request).handleDrained();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // io.vertx.core.http.impl.VertxHttp2Stream
        public void handleCustomFrame(int type, int flags, Buffer buff) {
            this.response.handleUnknownFrame(new HttpFrameImpl(type, flags, buff));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // io.vertx.core.http.impl.VertxHttp2Stream
        public void handlePriorityChange(StreamPriority streamPriority) {
            if (streamPriority != null && !streamPriority.equals(priority())) {
                priority(streamPriority);
                this.response.handlePriorityChange(streamPriority);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void handleHeaders(Http2Headers headers, StreamPriority streamPriority, boolean end) throws NumberFormatException {
            if (streamPriority != null) {
                priority(streamPriority);
            }
            if (this.response != null) {
                if (end) {
                    onEnd(new Http2HeadersAdaptor(headers));
                    return;
                }
                return;
            }
            try {
                int status = Integer.parseInt(headers.status().toString());
                String statusMessage = HttpResponseStatus.valueOf(status).reasonPhrase();
                if (status == 100) {
                    if (this.continueHandler != null) {
                        this.continueHandler.handle(null);
                        return;
                    }
                    return;
                }
                headers.remove(Header.RESPONSE_STATUS_UTF8);
                this.response = new HttpClientResponseImpl(this.request, HttpVersion.HTTP_2, this, status, statusMessage, new Http2HeadersAdaptor(headers));
                if (((Http2ClientConnection) this.conn).metrics != null) {
                    ((Http2ClientConnection) this.conn).metrics.responseBegin(this.metric, this.response);
                }
                this.request.handleResponse(this.response);
                if (end) {
                    onEnd();
                }
            } catch (Exception e) {
                handleException(e);
                writeReset(1L);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // io.vertx.core.http.impl.VertxHttp2Stream
        public void handleException(Throwable exception) {
            HttpClientRequestBase req;
            HttpClientResponseImpl resp;
            synchronized (((Http2ClientConnection) this.conn)) {
                req = (!this.requestEnded || this.response == null || this.response.statusCode() == 100) ? this.request : null;
                resp = this.response;
            }
            if (req != null) {
                req.handleException(exception);
            }
            if (resp != null) {
                resp.handleException(exception);
            }
        }

        Handler<HttpClientRequest> pushHandler() {
            return ((HttpClientRequestImpl) this.request).pushHandler();
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public void writeHead(HttpMethod method, String rawMethod, String uri, MultiMap headers, String hostHeader, boolean chunked, ByteBuf content, boolean end, StreamPriority priority, Handler<Void> contHandler, Handler<AsyncResult<Void>> handler) {
            Http2Headers h = new DefaultHttp2Headers();
            h.method(method != HttpMethod.OTHER ? method.name() : rawMethod);
            if (method == HttpMethod.CONNECT) {
                if (hostHeader == null) {
                    throw new IllegalArgumentException("Missing :authority / host header");
                }
                h.authority(hostHeader);
            } else {
                h.path(uri);
                h.scheme(((Http2ClientConnection) this.conn).isSsl() ? "https" : "http");
                if (hostHeader != null) {
                    h.authority(hostHeader);
                }
            }
            if (headers != null && headers.size() > 0) {
                for (Map.Entry<String, String> header : headers) {
                    h.add((Http2Headers) Http2HeadersAdaptor.toLowerCase(header.getKey()), (CharSequence) header.getValue());
                }
            }
            if (((Http2ClientConnection) this.conn).client.getOptions().isTryUseCompression() && h.get(HttpHeaderNames.ACCEPT_ENCODING) == null) {
                h.set((Http2Headers) HttpHeaderNames.ACCEPT_ENCODING, (AsciiString) HttpHeaders.DEFLATE_GZIP);
            }
            this.continueHandler = contHandler;
            if (((Http2ClientConnection) this.conn).metrics != null) {
                this.metric = ((Http2ClientConnection) this.conn).metrics.requestBegin(((Http2ClientConnection) this.conn).queueMetric, ((Http2ClientConnection) this.conn).metric(), ((Http2ClientConnection) this.conn).localAddress(), ((Http2ClientConnection) this.conn).remoteAddress(), this.request);
            }
            priority(priority);
            if (content != null) {
                writeHeaders(h, false, null);
                writeBuffer(content, end, handler);
            } else {
                writeHeaders(h, end, handler);
                this.handlerContext.flush();
            }
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public void writeBuffer(ByteBuf buf, boolean end, Handler<AsyncResult<Void>> handler) {
            if (buf == null && end) {
                buf = Unpooled.EMPTY_BUFFER;
            }
            if (buf != null) {
                writeData(buf, end, handler);
            }
            if (end) {
                this.handlerContext.flush();
            }
        }

        @Override // io.vertx.core.http.impl.VertxHttp2Stream, io.vertx.core.http.impl.HttpClientStream
        public void writeFrame(int type, int flags, ByteBuf payload) {
            super.writeFrame(type, flags, payload);
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public ContextInternal getContext() {
            return this.context;
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public void doSetWriteQueueMaxSize(int size) {
        }

        @Override // io.vertx.core.http.impl.VertxHttp2Stream, io.vertx.core.http.impl.HttpClientStream
        public boolean isNotWritable() {
            return super.isNotWritable();
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public void beginRequest(HttpClientRequestImpl req) {
            this.request = req;
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public void endRequest() {
            if (((Http2ClientConnection) this.conn).metrics != null) {
                ((Http2ClientConnection) this.conn).metrics.requestEnd(this.metric);
            }
            this.requestEnded = true;
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public void reset(Throwable cause) {
            long code = cause instanceof StreamResetException ? ((StreamResetException) cause).getCode() : 0L;
            if (this.request == null) {
                writeReset(code);
                return;
            }
            if (!this.requestEnded || !this.responseEnded) {
                handleException(cause);
                this.requestEnded = true;
                this.responseEnded = true;
                writeReset(code);
                if (((Http2ClientConnection) this.conn).metrics != null) {
                    ((Http2ClientConnection) this.conn).metrics.requestReset(this.metric);
                }
            }
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public HttpClientConnection connection() {
            return (HttpClientConnection) this.conn;
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public NetSocket createNetSocket() {
            return ((Http2ClientConnection) this.conn).toNetSocket(this);
        }
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.vertx.core.net.impl.ConnectionBase
    protected void handleIdle() {
        synchronized (this) {
            if (this.streams.isEmpty()) {
                return;
            }
            super.handleIdle();
        }
    }

    public static VertxHttp2ConnectionHandler<Http2ClientConnection> createHttp2ConnectionHandler(HttpClientImpl client, Object queueMetric, ConnectionListener<HttpClientConnection> listener, ContextInternal context, Object socketMetric, BiConsumer<Http2ClientConnection, Long> c) {
        long http2MaxConcurrency = client.getOptions().getHttp2MultiplexingLimit() <= 0 ? Long.MAX_VALUE : client.getOptions().getHttp2MultiplexingLimit();
        HttpClientOptions options = client.getOptions();
        HttpClientMetrics metrics = client.metrics();
        VertxHttp2ConnectionHandler<Http2ClientConnection> handler = new VertxHttp2ConnectionHandlerBuilder().server(false).useCompression(client.getOptions().isTryUseCompression()).initialSettings(client.getOptions().getInitialSettings()).connectionFactory(connHandler -> {
            return new Http2ClientConnection(listener, queueMetric, client, context, connHandler, metrics);
        }).logEnabled(options.getLogActivity()).build();
        handler.addHandler(conn -> {
            if (options.getHttp2ConnectionWindowSize() > 0) {
                conn.setWindowSize(options.getHttp2ConnectionWindowSize());
            }
            if (metrics != null) {
                Object m = socketMetric;
                if (m == null) {
                    m = metrics.connected(conn.remoteAddress(), conn.remoteName());
                    metrics.endpointConnected(queueMetric, m);
                }
                conn.metric(m);
            }
            long concurrency = conn.remoteSettings().getMaxConcurrentStreams();
            if (http2MaxConcurrency > 0) {
                concurrency = Math.min(concurrency, http2MaxConcurrency);
            }
            c.accept(conn, Long.valueOf(concurrency));
        });
        handler.removeHandler(conn2 -> {
            if (metrics != null) {
                metrics.endpointDisconnected(queueMetric, conn2.metric());
            }
            listener.onEvict();
        });
        return handler;
    }
}
