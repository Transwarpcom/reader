package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.FileRegion;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.TooLongFrameException;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.ReferenceCountUtil;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.Http2Settings;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.impl.NetSocketImpl;
import io.vertx.core.net.impl.SSLHelper;
import io.vertx.core.net.impl.VertxHandler;
import io.vertx.core.spi.metrics.HttpServerMetrics;
import io.vertx.core.spi.metrics.Metrics;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/Http1xServerConnection.class */
public class Http1xServerConnection extends Http1xConnectionBase<ServerWebSocketImpl> implements HttpServerConnection {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) Http1xServerConnection.class);
    private final String serverOrigin;
    private final SSLHelper sslHelper;
    private boolean requestFailed;
    private long bytesRead;
    private long bytesWritten;
    private HttpServerRequestImpl requestInProgress;
    private HttpServerRequestImpl responseInProgress;
    private boolean channelPaused;
    private Handler<HttpServerRequest> requestHandler;
    final HttpServerMetrics metrics;
    final boolean handle100ContinueAutomatically;
    final HttpServerOptions options;

    @Override // io.vertx.core.http.impl.Http1xConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ HttpConnection pingHandler(Handler handler) {
        return super.pingHandler(handler);
    }

    @Override // io.vertx.core.http.impl.Http1xConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ HttpConnection ping(Buffer buffer, Handler handler) {
        return super.ping(buffer, handler);
    }

    @Override // io.vertx.core.http.impl.Http1xConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ HttpConnection remoteSettingsHandler(Handler handler) {
        return super.remoteSettingsHandler(handler);
    }

    @Override // io.vertx.core.http.impl.Http1xConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ Http2Settings remoteSettings() {
        return super.remoteSettings();
    }

    @Override // io.vertx.core.http.impl.Http1xConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ HttpConnection updateSettings(Http2Settings http2Settings, Handler handler) {
        return super.updateSettings(http2Settings, handler);
    }

    @Override // io.vertx.core.http.impl.Http1xConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ HttpConnection updateSettings(Http2Settings http2Settings) {
        return super.updateSettings(http2Settings);
    }

    @Override // io.vertx.core.http.impl.Http1xConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ Http2Settings settings() {
        return super.settings();
    }

    @Override // io.vertx.core.http.impl.Http1xConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ HttpConnection shutdown(long j) {
        return super.shutdown(j);
    }

    @Override // io.vertx.core.http.impl.Http1xConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ HttpConnection shutdown() {
        return super.shutdown();
    }

    @Override // io.vertx.core.http.impl.Http1xConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ HttpConnection shutdownHandler(Handler handler) {
        return super.shutdownHandler(handler);
    }

    @Override // io.vertx.core.http.impl.Http1xConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ HttpConnection goAwayHandler(Handler handler) {
        return super.goAwayHandler(handler);
    }

    @Override // io.vertx.core.http.impl.Http1xConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ HttpConnection goAway(long j, int i, Buffer buffer) {
        return super.goAway(j, i, buffer);
    }

    @Override // io.vertx.core.http.impl.Http1xConnectionBase, io.vertx.core.net.impl.ConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ Http1xConnectionBase exceptionHandler(Handler handler) {
        return super.exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.http.impl.Http1xConnectionBase, io.vertx.core.net.impl.ConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ Http1xConnectionBase closeHandler(Handler handler) {
        return super.closeHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.http.impl.Http1xConnectionBase, io.vertx.core.net.impl.ConnectionBase
    public /* bridge */ /* synthetic */ void close(Handler handler) {
        super.close(handler);
    }

    @Override // io.vertx.core.http.impl.Http1xConnectionBase, io.vertx.core.net.impl.ConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    public Http1xServerConnection(VertxInternal vertx, SSLHelper sslHelper, HttpServerOptions options, ChannelHandlerContext channel, ContextInternal context, String serverOrigin, HttpServerMetrics metrics) {
        super(vertx, channel, context);
        this.serverOrigin = serverOrigin;
        this.options = options;
        this.sslHelper = sslHelper;
        this.metrics = metrics;
        this.handle100ContinueAutomatically = options.isHandle100ContinueAutomatically();
    }

    @Override // io.vertx.core.http.impl.HttpServerConnection
    public HttpServerConnection handler(Handler<HttpServerRequest> handler) {
        this.requestHandler = handler;
        return this;
    }

    @Override // io.vertx.core.net.impl.ConnectionBase
    public HttpServerMetrics metrics() {
        return this.metrics;
    }

    @Override // io.vertx.core.net.impl.ConnectionBase
    public void handleMessage(Object msg) {
        if (!(msg instanceof HttpRequest)) {
            if (msg == LastHttpContent.EMPTY_LAST_CONTENT) {
                handleEnd();
                return;
            } else if (msg instanceof HttpContent) {
                handleContent(msg);
                return;
            } else {
                if (msg instanceof WebSocketFrame) {
                    handleWsFrame((WebSocketFrame) msg);
                    return;
                }
                return;
            }
        }
        DefaultHttpRequest request = (DefaultHttpRequest) msg;
        if (request.decoderResult() != DecoderResult.SUCCESS) {
            handleError(request);
            return;
        }
        HttpServerRequestImpl req = new HttpServerRequestImpl(this, request);
        synchronized (this) {
            this.requestInProgress = req;
            if (this.responseInProgress != null) {
                req.pause2();
                this.responseInProgress.enqueue(req);
            } else {
                this.responseInProgress = this.requestInProgress;
                req.handleBegin();
                this.requestHandler.handle(req);
            }
        }
    }

    private void handleContent(Object msg) {
        HttpServerRequestImpl request;
        HttpContent content = (HttpContent) msg;
        if (content.decoderResult() != DecoderResult.SUCCESS) {
            handleError(content);
            return;
        }
        Buffer buffer = Buffer.buffer(VertxHandler.safeBuffer(content.content(), this.chctx.alloc()));
        synchronized (this) {
            if (Metrics.METRICS_ENABLED) {
                reportBytesRead(buffer);
            }
            request = this.requestInProgress;
        }
        request.handleContent(buffer);
        if (content instanceof LastHttpContent) {
            handleEnd();
        }
    }

    private void handleEnd() {
        HttpServerRequestImpl request;
        synchronized (this) {
            if (Metrics.METRICS_ENABLED) {
                reportRequestComplete();
            }
            request = this.requestInProgress;
            this.requestInProgress = null;
        }
        request.handleEnd();
    }

    synchronized void responseComplete() {
        if (Metrics.METRICS_ENABLED) {
            reportResponseComplete();
        }
        HttpServerRequestImpl request = this.responseInProgress;
        this.responseInProgress = null;
        HttpServerRequestImpl next = request.next();
        if (next != null) {
            handleNext(next);
        }
    }

    private void handleNext(HttpServerRequestImpl next) {
        this.responseInProgress = next;
        next.handleBegin();
        this.context.runOnContext(v -> {
            next.resume2();
            this.requestHandler.handle(next);
        });
    }

    @Override // io.vertx.core.net.impl.ConnectionBase
    public void doPause() {
        if (!this.channelPaused) {
            this.channelPaused = true;
            super.doPause();
        }
    }

    @Override // io.vertx.core.net.impl.ConnectionBase
    public void doResume() {
        if (this.channelPaused) {
            this.channelPaused = false;
            super.doResume();
        }
    }

    private void reportBytesRead(Buffer buffer) {
        if (this.metrics != null) {
            this.bytesRead += buffer.length();
        }
    }

    private void reportBytesWritten(Object msg) {
        if (this.metrics != null) {
            long bytes = getBytes(msg);
            if (bytes == -1) {
                log.warn("Metrics could not be updated to include bytes written because of unknown object " + msg.getClass() + " being written.");
            } else {
                this.bytesWritten += bytes;
            }
        }
    }

    private void reportRequestComplete() {
        if (this.metrics != null) {
            reportBytesRead(this.bytesRead);
            this.bytesRead = 0L;
        }
    }

    private void reportResponseComplete() {
        if (this.metrics != null) {
            reportBytesWritten(this.bytesWritten);
            if (this.requestFailed) {
                this.metrics.requestReset(this.responseInProgress.metric());
                this.requestFailed = false;
            } else {
                this.metrics.responseEnd(this.responseInProgress.metric(), this.responseInProgress.response());
            }
            this.bytesWritten = 0L;
        }
    }

    String getServerOrigin() {
        return this.serverOrigin;
    }

    Vertx vertx() {
        return this.vertx;
    }

    @Override // io.vertx.core.net.impl.ConnectionBase
    public void writeToChannel(Object msg, ChannelPromise promise) {
        if (Metrics.METRICS_ENABLED) {
            reportBytesWritten(msg);
        }
        super.writeToChannel(msg, promise);
    }

    ServerWebSocketImpl createWebSocket(HttpServerRequestImpl request) {
        if (this.ws != 0) {
            return (ServerWebSocketImpl) this.ws;
        }
        if (!(request.nettyRequest() instanceof FullHttpRequest)) {
            throw new IllegalStateException();
        }
        WebSocketServerHandshaker handshaker = createHandshaker(request);
        if (handshaker == null) {
            return null;
        }
        this.ws = new ServerWebSocketImpl(this, handshaker.version() != WebSocketVersion.V00, request, handshaker, this.options.getMaxWebsocketFrameSize(), this.options.getMaxWebsocketMessageSize());
        if (Metrics.METRICS_ENABLED && this.metrics != null) {
            ((ServerWebSocketImpl) this.ws).setMetric(this.metrics.connected(metric(), request.metric(), (ServerWebSocket) this.ws));
        }
        return (ServerWebSocketImpl) this.ws;
    }

    private WebSocketServerHandshaker createHandshaker(HttpServerRequestImpl request) {
        channel();
        String connectionHeader = request.getHeader(HttpHeaders.CONNECTION);
        if (connectionHeader == null || !connectionHeader.toLowerCase().contains("upgrade")) {
            request.response().setStatusCode(HttpResponseStatus.BAD_REQUEST.code()).end("\"Connection\" header must be \"Upgrade\".");
            return null;
        }
        if (request.method() != HttpMethod.GET) {
            request.response().setStatusCode(HttpResponseStatus.METHOD_NOT_ALLOWED.code()).end();
            return null;
        }
        try {
            String wsURL = HttpUtils.getWebSocketLocation(request, isSsl());
            WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory(wsURL, this.options.getWebsocketSubProtocols(), this.options.getPerMessageWebsocketCompressionSupported() || this.options.getPerFrameWebsocketCompressionSupported(), this.options.getMaxWebsocketFrameSize(), this.options.isAcceptUnmaskedFrames());
            WebSocketServerHandshaker shake = factory.newHandshaker(request.nettyRequest());
            if (shake == null) {
                request.response().putHeader(HttpHeaderNames.SEC_WEBSOCKET_VERSION, WebSocketVersion.V13.toHttpHeaderValue()).setStatusCode(HttpResponseStatus.UPGRADE_REQUIRED.code()).end();
            }
            return shake;
        } catch (Exception e) {
            request.response().setStatusCode(HttpResponseStatus.BAD_REQUEST.code()).end("Invalid request URI");
            return null;
        }
    }

    NetSocket createNetSocket() {
        final Map<Channel, NetSocketImpl> connectionMap = new HashMap<>(1);
        NetSocketImpl socket = new NetSocketImpl(this.vertx, this.chctx, this.context, this.sslHelper, this.metrics) { // from class: io.vertx.core.http.impl.Http1xServerConnection.1
            @Override // io.vertx.core.net.impl.NetSocketImpl, io.vertx.core.net.impl.ConnectionBase
            protected void handleClosed() {
                if (Http1xServerConnection.this.metrics != null) {
                    Http1xServerConnection.this.metrics.responseEnd(Http1xServerConnection.this.responseInProgress.metric(), Http1xServerConnection.this.responseInProgress.response());
                }
                connectionMap.remove(this.chctx.channel());
                super.handleClosed();
            }

            @Override // io.vertx.core.net.impl.NetSocketImpl, io.vertx.core.net.impl.ConnectionBase
            public synchronized void handleMessage(Object msg) {
                if (msg instanceof HttpContent) {
                    ReferenceCountUtil.release(msg);
                } else {
                    super.handleMessage(msg);
                }
            }
        };
        socket.metric(metric());
        connectionMap.put(this.chctx.channel(), socket);
        flush();
        ChannelPipeline pipeline = this.chctx.pipeline();
        ChannelHandler compressor = pipeline.get((Class<ChannelHandler>) HttpChunkContentCompressor.class);
        if (compressor != null) {
            pipeline.remove(compressor);
        }
        pipeline.remove("httpDecoder");
        if (pipeline.get("chunkedWriter") != null) {
            pipeline.remove("chunkedWriter");
        }
        this.chctx.pipeline().replace("handler", "handler", VertxHandler.create(socket));
        this.chctx.pipeline().remove("httpEncoder");
        return socket;
    }

    @Override // io.vertx.core.net.impl.ConnectionBase
    public synchronized void handleInterestedOpsChanged() {
        if (!isNotWritable()) {
            if (this.responseInProgress != null) {
                this.responseInProgress.response().handleDrained();
            } else if (this.ws != 0) {
                ((ServerWebSocketImpl) this.ws).handleDrained();
            }
        }
    }

    void write100Continue() {
        this.chctx.writeAndFlush(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE));
    }

    @Override // io.vertx.core.net.impl.ConnectionBase
    protected void handleClosed() {
        ServerWebSocketImpl ws;
        HttpServerRequestImpl requestInProgress;
        HttpServerRequestImpl responseInProgress;
        synchronized (this) {
            ws = (ServerWebSocketImpl) this.ws;
            requestInProgress = this.requestInProgress;
            responseInProgress = this.responseInProgress;
            if (Metrics.METRICS_ENABLED && this.metrics != null && ws != null) {
                this.metrics.disconnected(ws.getMetric());
                ws.setMetric(null);
            }
        }
        if (requestInProgress != null) {
            requestInProgress.handleException(CLOSED_EXCEPTION);
        }
        if (responseInProgress != null && responseInProgress != requestInProgress) {
            responseInProgress.handleException(CLOSED_EXCEPTION);
        }
        if (ws != null) {
            ws.handleClosed();
        }
        super.handleClosed();
    }

    @Override // io.vertx.core.net.impl.ConnectionBase
    protected void handleException(Throwable t) {
        ServerWebSocketImpl ws;
        HttpServerRequestImpl requestInProgress;
        HttpServerRequestImpl responseInProgress;
        super.handleException(t);
        synchronized (this) {
            ws = (ServerWebSocketImpl) this.ws;
            requestInProgress = this.requestInProgress;
            responseInProgress = this.responseInProgress;
            if (Metrics.METRICS_ENABLED && this.metrics != null) {
                this.requestFailed = true;
            }
        }
        if (requestInProgress != null) {
            requestInProgress.handleException(t);
        }
        if (responseInProgress != null && responseInProgress != requestInProgress) {
            responseInProgress.handleException(t);
        }
        if (ws != null) {
            ws.handleException(t);
        }
    }

    @Override // io.vertx.core.net.impl.ConnectionBase
    protected void addFuture(Handler<AsyncResult<Void>> completionHandler, ChannelFuture future) {
        super.addFuture(completionHandler, future);
    }

    @Override // io.vertx.core.net.impl.ConnectionBase
    protected boolean supportsFileRegion() {
        return super.supportsFileRegion() && this.chctx.pipeline().get(HttpChunkContentCompressor.class) == null;
    }

    @Override // io.vertx.core.net.impl.ConnectionBase
    protected ChannelFuture sendFile(RandomAccessFile file, long offset, long length) throws IOException {
        return super.sendFile(file, offset, length);
    }

    private void handleError(HttpObject obj) {
        HttpVersion version;
        DecoderResult result = obj.decoderResult();
        Throwable cause = result.cause();
        if (cause instanceof TooLongFrameException) {
            String causeMsg = cause.getMessage();
            if (obj instanceof HttpRequest) {
                version = ((HttpRequest) obj).protocolVersion();
            } else if (this.requestInProgress != null) {
                version = this.requestInProgress.version() == io.vertx.core.http.HttpVersion.HTTP_1_0 ? HttpVersion.HTTP_1_0 : HttpVersion.HTTP_1_1;
            } else {
                version = HttpVersion.HTTP_1_1;
            }
            HttpResponseStatus status = causeMsg.startsWith("An HTTP line is larger than") ? HttpResponseStatus.REQUEST_URI_TOO_LONG : HttpResponseStatus.BAD_REQUEST;
            DefaultFullHttpResponse resp = new DefaultFullHttpResponse(version, status);
            ChannelPromise fut = this.chctx.newPromise();
            writeToChannel(resp, fut);
            fut.addListener2(res -> {
                fail(result.cause());
            });
            return;
        }
        fail(result.cause());
    }

    private long getBytes(Object obj) {
        if (obj == null) {
            return 0L;
        }
        if (obj instanceof Buffer) {
            return ((Buffer) obj).length();
        }
        if (obj instanceof ByteBuf) {
            return ((ByteBuf) obj).readableBytes();
        }
        if (obj instanceof HttpContent) {
            return ((HttpContent) obj).content().readableBytes();
        }
        if (obj instanceof WebSocketFrame) {
            return ((WebSocketFrame) obj).content().readableBytes();
        }
        if (obj instanceof FileRegion) {
            return ((FileRegion) obj).count();
        }
        if (obj instanceof ChunkedFile) {
            ChunkedFile file = (ChunkedFile) obj;
            return file.endOffset() - file.startOffset();
        }
        return -1L;
    }
}
