package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.DefaultLastHttpContent;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketClientExtensionHandler;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketClientExtensionHandshaker;
import io.netty.handler.codec.http.websocketx.extensions.compression.DeflateFrameClientExtensionHandshaker;
import io.netty.handler.codec.http.websocketx.extensions.compression.PerMessageDeflateClientExtensionHandshaker;
import io.netty.util.ReferenceCountUtil;
import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.http.WebSocket;
import io.vertx.core.http.WebsocketVersion;
import io.vertx.core.http.impl.pool.ConnectionListener;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.net.impl.NetSocketImpl;
import io.vertx.core.net.impl.VertxHandler;
import io.vertx.core.spi.metrics.HttpClientMetrics;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.impl.InboundBuffer;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/Http1xClientConnection.class */
class Http1xClientConnection extends Http1xConnectionBase<WebSocketImpl> implements HttpClientConnection {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) Http1xClientConnection.class);
    private final ConnectionListener<HttpClientConnection> listener;
    private final HttpClientImpl client;
    private final HttpClientOptions options;
    private final boolean ssl;
    private final SocketAddress server;
    private final Object endpointMetric;
    private final HttpClientMetrics metrics;
    private final HttpVersion version;
    private StreamImpl requestInProgress;
    private StreamImpl responseInProgress;
    private boolean close;
    private boolean upgraded;
    private int keepAliveTimeout;
    private int seq;

    Http1xClientConnection(ConnectionListener<HttpClientConnection> listener, HttpVersion version, HttpClientImpl client, Object endpointMetric, ChannelHandlerContext channel, boolean ssl, SocketAddress server, ContextInternal context, HttpClientMetrics metrics) {
        super(client.getVertx(), channel, context);
        this.seq = 1;
        this.listener = listener;
        this.client = client;
        this.options = client.getOptions();
        this.ssl = ssl;
        this.server = server;
        this.metrics = metrics;
        this.version = version;
        this.endpointMetric = endpointMetric;
        this.keepAliveTimeout = this.options.getKeepAliveTimeout();
    }

    Object endpointMetric() {
        return this.endpointMetric;
    }

    ConnectionListener<HttpClientConnection> listener() {
        return this.listener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized NetSocket upgrade(final StreamImpl stream) {
        if (this.options.isPipelining()) {
            throw new IllegalStateException("Cannot upgrade a pipe-lined request");
        }
        if (this.upgraded) {
            throw new IllegalStateException("Request already upgraded to NetSocket");
        }
        this.upgraded = true;
        final AtomicBoolean paused = new AtomicBoolean(false);
        NetSocketImpl socket = new NetSocketImpl(this.vertx, this.chctx, this.context, this.client.getSslHelper(), this.metrics) { // from class: io.vertx.core.http.impl.Http1xClientConnection.1
            {
                super.pause2();
            }

            @Override // io.vertx.core.net.impl.NetSocketImpl, io.vertx.core.net.NetSocket, io.vertx.core.streams.ReadStream
            /* renamed from: handler */
            public /* bridge */ /* synthetic */ ReadStream handler2(Handler handler) {
                return handler2((Handler<Buffer>) handler);
            }

            @Override // io.vertx.core.net.impl.NetSocketImpl, io.vertx.core.net.NetSocket, io.vertx.core.streams.ReadStream
            /* renamed from: handler */
            public synchronized NetSocket handler2(Handler<Buffer> dataHandler) {
                return super.handler2(dataHandler);
            }

            @Override // io.vertx.core.net.impl.NetSocketImpl, io.vertx.core.net.NetSocket, io.vertx.core.streams.ReadStream
            /* renamed from: pause */
            public synchronized NetSocket pause2() {
                paused.set(true);
                return super.pause2();
            }

            @Override // io.vertx.core.net.impl.NetSocketImpl, io.vertx.core.net.NetSocket, io.vertx.core.streams.ReadStream
            /* renamed from: resume */
            public synchronized NetSocket resume2() {
                paused.set(false);
                return super.resume2();
            }

            @Override // io.vertx.core.net.impl.NetSocketImpl, io.vertx.core.net.impl.ConnectionBase
            public synchronized void handleMessage(Object msg) {
                if (msg instanceof HttpContent) {
                    if (msg instanceof LastHttpContent) {
                        stream.endResponse((LastHttpContent) msg);
                    }
                    ReferenceCountUtil.release(msg);
                    return;
                }
                super.handleMessage(msg);
            }

            @Override // io.vertx.core.net.impl.NetSocketImpl, io.vertx.core.net.impl.ConnectionBase
            protected void handleClosed() {
                Http1xClientConnection.this.listener.onEvict();
                super.handleClosed();
            }
        };
        socket.metric(metric());
        flush();
        ChannelPipeline pipeline = this.chctx.pipeline();
        ChannelHandler inflater = pipeline.get((Class<ChannelHandler>) HttpContentDecompressor.class);
        if (inflater != null) {
            pipeline.remove(inflater);
        }
        pipeline.replace("handler", "handler", VertxHandler.create(socket));
        pipeline.remove("codec");
        this.context.runOnContext(v -> {
            if (!paused.get()) {
                socket.resume2();
            }
        });
        return socket;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HttpRequest createRequest(HttpMethod method, String rawMethod, String uri, MultiMap headerMap, String hostHeader, boolean chunked) {
        DefaultHttpRequest request = new DefaultHttpRequest(HttpUtils.toNettyHttpVersion(this.version), HttpUtils.toNettyHttpMethod(method, rawMethod), uri, false);
        if (headerMap != null) {
            for (Map.Entry<String, String> header : headerMap) {
                request.headers().add(header.getKey(), (Object) header.getValue());
            }
        }
        HttpHeaders headers = request.headers();
        if (!headers.contains(io.vertx.core.http.HttpHeaders.HOST)) {
            request.headers().set(io.vertx.core.http.HttpHeaders.HOST, hostHeader);
        }
        if (chunked) {
            HttpUtil.setTransferEncodingChunked(request, true);
        } else {
            headers.mo1933remove(io.vertx.core.http.HttpHeaders.TRANSFER_ENCODING);
        }
        if (this.options.isTryUseCompression() && request.headers().get(io.vertx.core.http.HttpHeaders.ACCEPT_ENCODING) == null) {
            request.headers().set(io.vertx.core.http.HttpHeaders.ACCEPT_ENCODING, io.vertx.core.http.HttpHeaders.DEFLATE_GZIP);
        }
        if (!this.options.isKeepAlive() && this.options.getProtocolVersion() == HttpVersion.HTTP_1_1) {
            request.headers().set(io.vertx.core.http.HttpHeaders.CONNECTION, io.vertx.core.http.HttpHeaders.CLOSE);
        } else if (this.options.isKeepAlive() && this.options.getProtocolVersion() == HttpVersion.HTTP_1_0) {
            request.headers().set(io.vertx.core.http.HttpHeaders.CONNECTION, io.vertx.core.http.HttpHeaders.KEEP_ALIVE);
        }
        return request;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendRequest(HttpRequest request, ByteBuf buf, boolean end, Handler<AsyncResult<Void>> handler) {
        if (end) {
            if (buf != null) {
                request = new AssembledFullHttpRequest(request, buf);
            } else {
                request = new AssembledFullHttpRequest(request);
            }
        } else if (buf != null) {
            request = new AssembledHttpRequest(request, buf);
        }
        writeToChannel(request, toPromise(handler));
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/Http1xClientConnection$StreamImpl.class */
    private static class StreamImpl implements HttpClientStream {
        private final int id;
        private final Http1xClientConnection conn;
        private final Promise<HttpClientStream> fut;
        private final InboundBuffer<Object> queue;
        private HttpClientRequestImpl request;
        private Handler<Void> continueHandler;
        private HttpClientResponseImpl response;
        private boolean requestEnded;
        private boolean responseEnded;
        private boolean reset;
        private StreamImpl next;
        private long bytesWritten;
        private long bytesRead;
        private Object metric;

        StreamImpl(Http1xClientConnection conn, int id, Handler<AsyncResult<HttpClientStream>> handler) {
            Promise<HttpClientStream> promise = Promise.promise();
            promise.future().setHandler2(handler);
            this.conn = conn;
            this.fut = promise;
            this.id = id;
            this.queue = new InboundBuffer<>(conn.context, 5L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void append(StreamImpl s) {
            StreamImpl streamImpl = this;
            while (true) {
                StreamImpl c = streamImpl;
                if (c.next != null) {
                    streamImpl = c.next;
                } else {
                    c.next = s;
                    return;
                }
            }
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public int id() {
            return this.id;
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public Object metric() {
            return this.metric;
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public HttpVersion version() {
            return this.conn.version;
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public HttpClientConnection connection() {
            return this.conn;
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public Context getContext() {
            return this.conn.context;
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0038 A[Catch: all -> 0x004e, TryCatch #0 {, blocks: (B:6:0x000d, B:7:0x001c, B:9:0x002c, B:11:0x0043, B:12:0x004a, B:10:0x0038), top: B:21:0x000d }] */
        /* JADX WARN: Removed duplicated region for block: B:9:0x002c A[Catch: all -> 0x004e, TryCatch #0 {, blocks: (B:6:0x000d, B:7:0x001c, B:9:0x002c, B:11:0x0043, B:12:0x004a, B:10:0x0038), top: B:21:0x000d }] */
        @Override // io.vertx.core.http.impl.HttpClientStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void writeHead(io.vertx.core.http.HttpMethod r9, java.lang.String r10, java.lang.String r11, io.vertx.core.MultiMap r12, java.lang.String r13, boolean r14, io.netty.buffer.ByteBuf r15, boolean r16, io.vertx.core.http.StreamPriority r17, io.vertx.core.Handler<java.lang.Void> r18, io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> r19) {
            /*
                r8 = this;
                r0 = r8
                io.vertx.core.http.impl.Http1xClientConnection r0 = r0.conn
                r1 = r0
                r20 = r1
                monitor-enter(r0)
                r0 = r15
                if (r0 == 0) goto L1c
                r0 = r8
                r1 = r0
                long r1 = r1.bytesWritten     // Catch: java.lang.Throwable -> L4e
                r2 = r15
                int r2 = r2.readableBytes()     // Catch: java.lang.Throwable -> L4e
                long r2 = (long) r2     // Catch: java.lang.Throwable -> L4e
                long r1 = r1 + r2
                r0.bytesWritten = r1     // Catch: java.lang.Throwable -> L4e
            L1c:
                r0 = r8
                r1 = r18
                r0.continueHandler = r1     // Catch: java.lang.Throwable -> L4e
                r0 = r8
                io.vertx.core.http.impl.Http1xClientConnection r0 = r0.conn     // Catch: java.lang.Throwable -> L4e
                io.vertx.core.http.impl.Http1xClientConnection$StreamImpl r0 = io.vertx.core.http.impl.Http1xClientConnection.access$500(r0)     // Catch: java.lang.Throwable -> L4e
                if (r0 != 0) goto L38
                r0 = r8
                io.vertx.core.http.impl.Http1xClientConnection r0 = r0.conn     // Catch: java.lang.Throwable -> L4e
                r1 = r8
                io.vertx.core.http.impl.Http1xClientConnection$StreamImpl r0 = io.vertx.core.http.impl.Http1xClientConnection.access$502(r0, r1)     // Catch: java.lang.Throwable -> L4e
                goto L43
            L38:
                r0 = r8
                io.vertx.core.http.impl.Http1xClientConnection r0 = r0.conn     // Catch: java.lang.Throwable -> L4e
                io.vertx.core.http.impl.Http1xClientConnection$StreamImpl r0 = io.vertx.core.http.impl.Http1xClientConnection.access$500(r0)     // Catch: java.lang.Throwable -> L4e
                r1 = r8
                r0.append(r1)     // Catch: java.lang.Throwable -> L4e
            L43:
                r0 = r8
                r1 = 0
                r0.next = r1     // Catch: java.lang.Throwable -> L4e
                r0 = r20
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L4e
                goto L56
            L4e:
                r21 = move-exception
                r0 = r20
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L4e
                r0 = r21
                throw r0
            L56:
                r0 = r8
                io.vertx.core.http.impl.Http1xClientConnection r0 = r0.conn
                r1 = r9
                r2 = r10
                r3 = r11
                r4 = r12
                r5 = r13
                r6 = r14
                io.netty.handler.codec.http.HttpRequest r0 = io.vertx.core.http.impl.Http1xClientConnection.access$600(r0, r1, r2, r3, r4, r5, r6)
                r20 = r0
                r0 = r8
                io.vertx.core.http.impl.Http1xClientConnection r0 = r0.conn
                r1 = r20
                r2 = r15
                r3 = r16
                r4 = r19
                io.vertx.core.http.impl.Http1xClientConnection.access$700(r0, r1, r2, r3, r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.vertx.core.http.impl.Http1xClientConnection.StreamImpl.writeHead(io.vertx.core.http.HttpMethod, java.lang.String, java.lang.String, io.vertx.core.MultiMap, java.lang.String, boolean, io.netty.buffer.ByteBuf, boolean, io.vertx.core.http.StreamPriority, io.vertx.core.Handler, io.vertx.core.Handler):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean handleChunk(Buffer buff) {
            this.bytesRead += buff.length();
            return this.queue.write((InboundBuffer<Object>) buff);
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public void writeBuffer(ByteBuf buff, boolean end, Handler<AsyncResult<Void>> handler) {
            HttpContent msg;
            if (buff == null && !end) {
                return;
            }
            if (end) {
                if (buff != null && buff.isReadable()) {
                    msg = new DefaultLastHttpContent(buff, false);
                } else {
                    msg = LastHttpContent.EMPTY_LAST_CONTENT;
                }
            } else {
                msg = new DefaultHttpContent(buff);
            }
            this.bytesWritten += msg.content().readableBytes();
            this.conn.writeToChannel(msg, this.conn.toPromise(handler));
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public void writeFrame(int type, int flags, ByteBuf payload) {
            throw new IllegalStateException("Cannot write an HTTP/2 frame over an HTTP/1.x connection");
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public void doSetWriteQueueMaxSize(int size) {
            this.conn.doSetWriteQueueMaxSize(size);
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public boolean isNotWritable() {
            return this.conn.isNotWritable();
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public void doPause() {
            this.queue.pause();
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public void doFetch(long amount) {
            this.queue.fetch(amount);
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public void reset(Throwable cause) {
            synchronized (this.conn) {
                if (this.reset) {
                    return;
                }
                this.reset = true;
                handleException(cause);
                synchronized (this.conn) {
                    if (this.conn.requestInProgress == this) {
                        if (this.request == null) {
                            this.conn.handleRequestEnd(true);
                        } else {
                            this.conn.close();
                        }
                    } else if (!this.responseEnded) {
                        this.conn.close();
                    }
                }
            }
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public void beginRequest(HttpClientRequestImpl req) {
            synchronized (this.conn) {
                if (this.request == null) {
                    if (this.conn.requestInProgress != this) {
                        throw new IllegalStateException("Connection is already writing another request");
                    }
                    this.request = req;
                    if (this.conn.metrics != null) {
                        this.metric = this.conn.metrics.requestBegin(this.conn.endpointMetric, this.conn.metric(), this.conn.localAddress(), this.conn.remoteAddress(), this.request);
                    }
                } else {
                    throw new IllegalStateException("Already writing a request");
                }
            }
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public void endRequest() {
            boolean doRecycle;
            synchronized (this.conn) {
                StreamImpl s = this.conn.requestInProgress;
                if (s != this) {
                    throw new IllegalStateException("No write in progress");
                }
                if (this.requestEnded) {
                    throw new IllegalStateException("Request already sent");
                }
                this.requestEnded = true;
                if (this.conn.metrics != null) {
                    this.conn.metrics.requestEnd(this.metric);
                }
                doRecycle = this.responseEnded;
            }
            this.conn.reportBytesWritten(this.bytesWritten);
            this.conn.handleRequestEnd(doRecycle);
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public NetSocket createNetSocket() {
            NetSocket netSocketUpgrade;
            synchronized (this.conn) {
                if (!this.responseEnded) {
                    netSocketUpgrade = this.conn.upgrade(this);
                } else {
                    throw new IllegalStateException("Response already ended");
                }
            }
            return netSocketUpgrade;
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public StreamPriority priority() {
            return null;
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public void updatePriority(StreamPriority streamPriority) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public HttpClientResponseImpl beginResponse(HttpResponse resp) {
            HttpVersion version;
            int timeout;
            if (resp.protocolVersion() == io.netty.handler.codec.http.HttpVersion.HTTP_1_0) {
                version = HttpVersion.HTTP_1_0;
            } else {
                version = HttpVersion.HTTP_1_1;
            }
            this.response = new HttpClientResponseImpl(this.request, version, this, resp.status().code(), resp.status().reasonPhrase(), new HeadersAdaptor(resp.headers()));
            if (this.conn.metrics != null) {
                this.conn.metrics.responseBegin(this.metric, this.response);
            }
            if (resp.status().code() != 100 && this.request.method() != HttpMethod.CONNECT) {
                String responseConnectionHeader = resp.headers().get(HttpHeaderNames.CONNECTION);
                io.netty.handler.codec.http.HttpVersion protocolVersion = resp.protocolVersion();
                String requestConnectionHeader = this.request.headers().get(HttpHeaderNames.CONNECTION);
                if (HttpHeaderValues.CLOSE.contentEqualsIgnoreCase(responseConnectionHeader) || HttpHeaderValues.CLOSE.contentEqualsIgnoreCase(requestConnectionHeader)) {
                    this.conn.close = true;
                } else if (protocolVersion == io.netty.handler.codec.http.HttpVersion.HTTP_1_0 && !HttpHeaderValues.KEEP_ALIVE.contentEqualsIgnoreCase(responseConnectionHeader)) {
                    this.conn.close = true;
                }
                String keepAliveHeader = resp.headers().get(HttpHeaderNames.KEEP_ALIVE);
                if (keepAliveHeader != null && (timeout = HttpUtils.parseKeepAliveHeaderTimeout(keepAliveHeader)) != -1) {
                    this.conn.keepAliveTimeout = timeout;
                }
            }
            this.queue.handler(item -> {
                if (item instanceof MultiMap) {
                    this.conn.reportBytesRead(this.bytesRead);
                    this.response.handleEnd((MultiMap) item);
                } else {
                    this.response.handleChunk((Buffer) item);
                }
            });
            this.queue.drainHandler(v -> {
                if (!this.responseEnded) {
                    this.conn.doResume();
                }
            });
            return this.response;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean endResponse(LastHttpContent trailer) {
            boolean z;
            synchronized (this.conn) {
                if (this.conn.metrics != null) {
                    this.conn.metrics.responseEnd(this.metric, this.response);
                }
            }
            this.queue.write((InboundBuffer<Object>) new HeadersAdaptor(trailer.trailingHeaders()));
            synchronized (this.conn) {
                this.responseEnded = true;
                this.conn.close |= !this.conn.options.isKeepAlive();
                this.conn.doResume();
                z = this.requestEnded;
            }
            return z;
        }

        void handleException(Throwable cause) {
            HttpClientRequestImpl request;
            HttpClientResponseImpl response;
            Promise<HttpClientStream> fut;
            boolean requestEnded;
            synchronized (this.conn) {
                request = this.request;
                response = this.response;
                fut = this.fut;
                requestEnded = this.requestEnded;
            }
            if (request != null) {
                if (response == null) {
                    request.handleException(cause);
                    return;
                }
                if (!requestEnded) {
                    request.handleException(cause);
                }
                response.handleException(cause);
                return;
            }
            fut.tryFail(cause);
        }
    }

    private void checkLifecycle() {
        if (!this.upgraded) {
            if (this.close) {
                close();
            } else {
                recycle();
            }
        }
    }

    private Throwable validateMessage(Object msg) {
        io.netty.handler.codec.http.HttpVersion version;
        if (msg instanceof HttpObject) {
            HttpObject obj = (HttpObject) msg;
            DecoderResult result = obj.decoderResult();
            if (result.isFailure()) {
                return result.cause();
            }
            if ((obj instanceof HttpResponse) && (version = ((HttpResponse) obj).protocolVersion()) != io.netty.handler.codec.http.HttpVersion.HTTP_1_0 && version != io.netty.handler.codec.http.HttpVersion.HTTP_1_1) {
                return new IllegalStateException("Unsupported HTTP version: " + version);
            }
            return null;
        }
        return null;
    }

    @Override // io.vertx.core.net.impl.ConnectionBase
    public void handleMessage(Object msg) {
        Throwable error = validateMessage(msg);
        if (error != null) {
            fail(error);
            return;
        }
        if (msg instanceof HttpObject) {
            HttpObject obj = (HttpObject) msg;
            handleHttpMessage(obj);
        } else {
            if (msg instanceof WebSocketFrame) {
                handleWsFrame((WebSocketFrame) msg);
                return;
            }
            throw new IllegalStateException("Invalid object " + msg);
        }
    }

    private void handleHttpMessage(HttpObject obj) {
        if (obj instanceof HttpResponse) {
            handleResponseBegin((HttpResponse) obj);
            return;
        }
        if (obj instanceof HttpContent) {
            HttpContent chunk = (HttpContent) obj;
            if (chunk.content().isReadable()) {
                Buffer buff = Buffer.buffer(VertxHandler.safeBuffer(chunk.content(), this.chctx.alloc()));
                handleResponseChunk(buff);
            }
            if (chunk instanceof LastHttpContent) {
                handleResponseEnd((LastHttpContent) chunk);
            }
        }
    }

    private void handleResponseBegin(HttpResponse resp) {
        HttpClientRequestImpl request;
        HttpClientResponseImpl response;
        Handler<Void> handler;
        if (resp.status().code() == 100) {
            synchronized (this) {
                handler = this.responseInProgress.continueHandler;
            }
            if (handler != null) {
                handler.handle(null);
                return;
            }
            return;
        }
        synchronized (this) {
            StreamImpl stream = this.responseInProgress;
            request = stream.request;
            response = stream.beginResponse(resp);
        }
        request.handleResponse(response);
    }

    private void handleResponseChunk(Buffer buff) {
        StreamImpl resp;
        synchronized (this) {
            resp = this.responseInProgress;
        }
        if (resp != null && !resp.handleChunk(buff)) {
            doPause();
        }
    }

    private void handleResponseEnd(LastHttpContent trailer) {
        synchronized (this) {
            StreamImpl stream = this.responseInProgress;
            if (stream.response == null) {
                return;
            }
            this.responseInProgress = stream.next;
            if (stream.endResponse(trailer)) {
                checkLifecycle();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleRequestEnd(boolean recycle) {
        StreamImpl next;
        synchronized (this) {
            next = this.requestInProgress.next;
            this.requestInProgress = next;
        }
        if (recycle) {
            recycle();
        }
        if (next != null) {
            next.fut.complete(next);
        }
    }

    @Override // io.vertx.core.net.impl.ConnectionBase
    public HttpClientMetrics metrics() {
        return this.metrics;
    }

    synchronized void toWebSocket(String requestURI, MultiMap headers, WebsocketVersion vers, List<String> subProtocols, int maxWebSocketFrameSize, Handler<AsyncResult<WebSocket>> wsHandler) {
        HttpHeaders nettyHeaders;
        if (this.ws != 0) {
            throw new IllegalStateException("Already websocket");
        }
        try {
            URI wsuri = new URI(requestURI);
            if (!wsuri.isAbsolute()) {
                wsuri = new URI((this.ssl ? "https:" : "http:") + "//" + this.server.host() + ":" + this.server.port() + requestURI);
            }
            WebSocketVersion version = WebSocketVersion.valueOf((vers == null ? WebSocketVersion.V13 : vers).toString());
            if (headers != null) {
                nettyHeaders = new DefaultHttpHeaders();
                for (Map.Entry<String, String> entry : headers) {
                    nettyHeaders.add(entry.getKey(), entry.getValue());
                }
            } else {
                nettyHeaders = null;
            }
            ChannelPipeline p = this.chctx.channel().pipeline();
            ArrayList<WebSocketClientExtensionHandshaker> extensionHandshakers = initializeWebsocketExtensionHandshakers(this.client.getOptions());
            if (!extensionHandshakers.isEmpty()) {
                p.addBefore("handler", "websocketsExtensionsHandler", new WebSocketClientExtensionHandler((WebSocketClientExtensionHandshaker[]) extensionHandshakers.toArray(new WebSocketClientExtensionHandshaker[0])));
            }
            String subp = null;
            if (subProtocols != null) {
                subp = String.join(",", subProtocols);
            }
            WebSocketClientHandshaker handshaker = WebSocketClientHandshakerFactory.newHandshaker(wsuri, version, subp, !extensionHandshakers.isEmpty(), nettyHeaders, maxWebSocketFrameSize, !this.options.isSendUnmaskedFrames(), false, -1L);
            WebSocketHandshakeInboundHandler handshakeInboundHandler = new WebSocketHandshakeInboundHandler(handshaker, ar -> {
                AsyncResult<WebSocket> wsRes = ar.map(v -> {
                    WebSocketImpl w = new WebSocketImpl(this, version != WebSocketVersion.V00, this.options.getMaxWebsocketFrameSize(), this.options.getMaxWebsocketMessageSize());
                    w.subProtocol(handshaker.actualSubprotocol());
                    return w;
                });
                if (ar.failed()) {
                    close();
                } else {
                    this.ws = (WebSocketImpl) wsRes.result();
                    ((WebSocketImpl) this.ws).registerHandler(this.vertx.eventBus());
                }
                getContext().executeFromIO(wsRes, res -> {
                    if (res.succeeded()) {
                        log.debug("WebSocket handshake complete");
                        if (this.metrics != null) {
                            ((WebSocketImpl) this.ws).setMetric(this.metrics.connected(this.endpointMetric, metric(), (WebSocket) this.ws));
                        }
                        ((WebSocketImpl) this.ws).headers((MultiMap) ar.result());
                    }
                    wsHandler.handle(res);
                    if (res.succeeded()) {
                        ((WebSocketImpl) this.ws).headers(null);
                    }
                });
            });
            p.addBefore("handler", "handshakeCompleter", handshakeInboundHandler);
            handshaker.handshake(this.chctx.channel()).addListener2(f -> {
                if (!f.isSuccess()) {
                    wsHandler.handle(Future.failedFuture(f.cause()));
                }
            });
        } catch (Exception e) {
            handleException(e);
        }
    }

    ArrayList<WebSocketClientExtensionHandshaker> initializeWebsocketExtensionHandshakers(HttpClientOptions options) {
        ArrayList<WebSocketClientExtensionHandshaker> extensionHandshakers = new ArrayList<>();
        if (options.getTryWebsocketDeflateFrameCompression()) {
            extensionHandshakers.add(new DeflateFrameClientExtensionHandshaker(options.getWebsocketCompressionLevel(), false));
        }
        if (options.getTryUsePerMessageWebsocketCompression()) {
            extensionHandshakers.add(new PerMessageDeflateClientExtensionHandshaker(options.getWebsocketCompressionLevel(), ZlibCodecFactory.isSupportingWindowSizeAndMemLevel(), 15, options.getWebsocketCompressionAllowClientNoContext(), options.getWebsocketCompressionRequestServerNoContext()));
        }
        return extensionHandshakers;
    }

    @Override // io.vertx.core.net.impl.ConnectionBase
    public synchronized void handleInterestedOpsChanged() {
        if (!isNotWritable()) {
            StreamImpl current = this.requestInProgress;
            if (current != null) {
                current.request.handleDrained();
            } else if (this.ws != 0) {
                ((WebSocketImpl) this.ws).handleDrained();
            }
        }
    }

    @Override // io.vertx.core.net.impl.ConnectionBase
    protected void handleClosed() {
        WebSocketImpl ws;
        super.handleClosed();
        if (this.metrics != null) {
            this.metrics.endpointDisconnected(this.endpointMetric, metric());
        }
        List<StreamImpl> list = Collections.emptyList();
        synchronized (this) {
            ws = (WebSocketImpl) this.ws;
            for (StreamImpl r = this.responseInProgress; r != null; r = r.next) {
                if (this.metrics != null) {
                    this.metrics.requestReset(r.metric);
                }
                if (list.isEmpty()) {
                    list = new ArrayList<>();
                }
                list.add(r);
            }
        }
        if (ws != null) {
            ws.handleClosed();
        }
        for (StreamImpl stream : list) {
            stream.handleException(CLOSED_EXCEPTION);
        }
    }

    @Override // io.vertx.core.net.impl.ConnectionBase
    protected void handleIdle() {
        synchronized (this) {
            if (this.ws == 0 && this.responseInProgress == null) {
                return;
            }
            super.handleIdle();
        }
    }

    @Override // io.vertx.core.net.impl.ConnectionBase
    protected synchronized void handleException(Throwable e) {
        super.handleException(e);
        if (this.ws != 0) {
            ((WebSocketImpl) this.ws).handleException(e);
            return;
        }
        StreamImpl streamImpl = this.responseInProgress;
        while (true) {
            StreamImpl r = streamImpl;
            if (r != null) {
                r.handleException(e);
                streamImpl = r.next;
            } else {
                return;
            }
        }
    }

    @Override // io.vertx.core.http.impl.HttpClientConnection
    public void createStream(Handler<AsyncResult<HttpClientStream>> handler) {
        synchronized (this) {
            int i = this.seq;
            this.seq = i + 1;
            StreamImpl stream = new StreamImpl(this, i, handler);
            if (this.requestInProgress != null) {
                this.requestInProgress.append(stream);
            } else {
                this.requestInProgress = stream;
                stream.fut.complete(stream);
            }
        }
    }

    private void recycle() {
        long expiration = this.keepAliveTimeout == 0 ? 0L : System.currentTimeMillis() + (this.keepAliveTimeout * 1000);
        this.listener.onRecycle(expiration);
    }
}
