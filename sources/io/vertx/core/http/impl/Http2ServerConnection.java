package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http2.DefaultHttp2Headers;
import io.netty.handler.codec.http2.Http2Error;
import io.netty.handler.codec.http2.Http2Exception;
import io.netty.handler.codec.http2.Http2Flags;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.handler.codec.http2.Http2Stream;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.Http2Settings;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.http.StreamResetException;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.spi.metrics.HttpServerMetrics;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayDeque;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/Http2ServerConnection.class */
public class Http2ServerConnection extends Http2ConnectionBase implements HttpServerConnection {
    private final HttpServerOptions options;
    private final String serverOrigin;
    private final HttpServerMetrics metrics;
    private Handler<HttpServerRequest> requestHandler;
    private Long maxConcurrentStreams;
    private int concurrentStreams;
    private final ArrayDeque<Push> pendingPushes;

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.vertx.core.net.impl.ConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ Http2ConnectionBase exceptionHandler(Handler handler) {
        return super.exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ HttpConnection pingHandler(Handler handler) {
        return super.pingHandler(handler);
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ HttpConnection ping(Buffer buffer, Handler handler) {
        return super.ping(buffer, handler);
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ HttpConnection updateSettings(Http2Settings http2Settings, Handler handler) {
        return super.updateSettings(http2Settings, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ HttpConnection updateSettings(Http2Settings http2Settings) {
        return super.updateSettings(http2Settings);
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ Http2Settings settings() {
        return super.settings();
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ Http2Settings remoteSettings() {
        return super.remoteSettings();
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ HttpConnection remoteSettingsHandler(Handler handler) {
        return super.remoteSettingsHandler(handler);
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.vertx.core.net.impl.ConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.vertx.core.net.impl.ConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ Http2ConnectionBase closeHandler(Handler handler) {
        return super.closeHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ HttpConnection shutdown() {
        return super.shutdown();
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ HttpConnection shutdown(long j) {
        return super.shutdown(j);
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ HttpConnection shutdownHandler(Handler handler) {
        return super.shutdownHandler(handler);
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ HttpConnection goAwayHandler(Handler handler) {
        return super.goAwayHandler(handler);
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ HttpConnection goAway(long j, int i, Buffer buffer) {
        return super.goAway(j, i, buffer);
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ HttpConnection setWindowSize(int i) {
        return super.setWindowSize(i);
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ int getWindowSize() {
        return super.getWindowSize();
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.netty.handler.codec.http2.Http2FrameListener
    public /* bridge */ /* synthetic */ int onDataRead(ChannelHandlerContext channelHandlerContext, int i, ByteBuf byteBuf, int i2, boolean z) {
        return super.onDataRead(channelHandlerContext, i, byteBuf, i2, z);
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.netty.handler.codec.http2.Http2FrameListener
    public /* bridge */ /* synthetic */ void onRstStreamRead(ChannelHandlerContext channelHandlerContext, int i, long j) {
        super.onRstStreamRead(channelHandlerContext, i, j);
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.netty.handler.codec.http2.Http2FrameListener
    public /* bridge */ /* synthetic */ void onUnknownFrame(ChannelHandlerContext channelHandlerContext, byte b, int i, Http2Flags http2Flags, ByteBuf byteBuf) {
        super.onUnknownFrame(channelHandlerContext, b, i, http2Flags, byteBuf);
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.netty.handler.codec.http2.Http2FrameListener
    public /* bridge */ /* synthetic */ void onWindowUpdateRead(ChannelHandlerContext channelHandlerContext, int i, int i2) {
        super.onWindowUpdateRead(channelHandlerContext, i, i2);
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.netty.handler.codec.http2.Http2FrameListener
    public /* bridge */ /* synthetic */ void onGoAwayRead(ChannelHandlerContext channelHandlerContext, int i, long j, ByteBuf byteBuf) {
        super.onGoAwayRead(channelHandlerContext, i, j, byteBuf);
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.netty.handler.codec.http2.Http2FrameListener
    public /* bridge */ /* synthetic */ void onPushPromiseRead(ChannelHandlerContext channelHandlerContext, int i, int i2, Http2Headers http2Headers, int i3) throws Http2Exception {
        super.onPushPromiseRead(channelHandlerContext, i, i2, http2Headers, i3);
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.netty.handler.codec.http2.Http2FrameListener
    public /* bridge */ /* synthetic */ void onPingAckRead(ChannelHandlerContext channelHandlerContext, long j) throws Http2Exception {
        super.onPingAckRead(channelHandlerContext, j);
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.netty.handler.codec.http2.Http2FrameListener
    public /* bridge */ /* synthetic */ void onPingRead(ChannelHandlerContext channelHandlerContext, long j) throws Http2Exception {
        super.onPingRead(channelHandlerContext, j);
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.netty.handler.codec.http2.Http2FrameListener
    public /* bridge */ /* synthetic */ void onSettingsAckRead(ChannelHandlerContext channelHandlerContext) {
        super.onSettingsAckRead(channelHandlerContext);
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.netty.handler.codec.http2.Http2FrameListener
    public /* bridge */ /* synthetic */ void onPriorityRead(ChannelHandlerContext channelHandlerContext, int i, int i2, short s, boolean z) {
        super.onPriorityRead(channelHandlerContext, i, i2, s, z);
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.vertx.core.net.impl.ConnectionBase
    public /* bridge */ /* synthetic */ void handleClosed() {
        super.handleClosed();
    }

    static /* synthetic */ int access$108(Http2ServerConnection x0) {
        int i = x0.concurrentStreams;
        x0.concurrentStreams = i + 1;
        return i;
    }

    static /* synthetic */ int access$110(Http2ServerConnection x0) {
        int i = x0.concurrentStreams;
        x0.concurrentStreams = i - 1;
        return i;
    }

    Http2ServerConnection(ContextInternal context, String serverOrigin, VertxHttp2ConnectionHandler connHandler, HttpServerOptions options, HttpServerMetrics metrics) {
        super(context, connHandler);
        this.pendingPushes = new ArrayDeque<>(8);
        this.options = options;
        this.serverOrigin = serverOrigin;
        this.metrics = metrics;
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

    private static boolean isMalformedRequest(Http2Headers headers) {
        if (headers.method() == null) {
            return true;
        }
        String method = headers.method().toString();
        if (method.equals("CONNECT")) {
            if (headers.scheme() != null || headers.path() != null || headers.authority() == null) {
                return true;
            }
        } else if (headers.method() == null || headers.scheme() == null || headers.path() == null) {
            return true;
        }
        if (headers.authority() != null) {
            try {
                URI uri = new URI(null, headers.authority().toString(), null, null, null);
                if (uri.getRawUserInfo() != null) {
                    return true;
                }
                return false;
            } catch (URISyntaxException e) {
                return true;
            }
        }
        return false;
    }

    private Http2ServerRequestImpl createRequest(int streamId, Http2Headers headers, boolean streamEnded) {
        Http2Stream stream = this.handler.connection().stream(streamId);
        String contentEncoding = this.options.isCompressionSupported() ? HttpUtils.determineContentEncoding(headers) : null;
        boolean writable = this.handler.encoder().flowController().isWritable(stream);
        return new Http2ServerRequestImpl(this, stream, this.metrics, this.serverOrigin, headers, contentEncoding, writable, streamEnded);
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.netty.handler.codec.http2.Http2FrameListener
    public synchronized void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int streamDependency, short weight, boolean exclusive, int padding, boolean endOfStream) {
        VertxHttp2Stream stream = this.streams.get(streamId);
        if (stream == null) {
            if (isMalformedRequest(headers)) {
                this.handler.writeReset(streamId, Http2Error.PROTOCOL_ERROR.code());
                return;
            }
            Http2ServerRequestImpl req = createRequest(streamId, headers, endOfStream);
            req.registerMetrics();
            req.priority(new StreamPriority().setDependency(streamDependency).setWeight(weight).setExclusive(exclusive));
            stream = req;
            CharSequence value = headers.get(HttpHeaderNames.EXPECT);
            if (this.options.isHandle100ContinueAutomatically() && ((value != null && HttpHeaderValues.CONTINUE.equals(value)) || headers.contains(HttpHeaderNames.EXPECT, HttpHeaderValues.CONTINUE))) {
                req.response().writeContinue();
            }
            this.streams.put(streamId, (int) req);
            this.context.executeFromIO(req, this.requestHandler);
        }
        if (endOfStream) {
            VertxHttp2Stream finalStream = stream;
            this.context.executeFromIO(v -> {
                finalStream.onEnd();
            });
        }
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public synchronized void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int padding, boolean endOfStream) {
        onHeadersRead(ctx, streamId, headers, 0, (short) 16, false, padding, endOfStream);
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase, io.netty.handler.codec.http2.Http2FrameListener
    public synchronized void onSettingsRead(ChannelHandlerContext ctx, io.netty.handler.codec.http2.Http2Settings settings) {
        Long v = settings.maxConcurrentStreams();
        if (v != null) {
            this.maxConcurrentStreams = v;
        }
        super.onSettingsRead(ctx, settings);
    }

    synchronized void sendPush(int streamId, String host, final HttpMethod method, MultiMap headers, final String path, final StreamPriority streamPriority, final Handler<AsyncResult<HttpServerResponse>> completionHandler) {
        final Http2Headers headers_ = new DefaultHttp2Headers();
        if (method == HttpMethod.OTHER) {
            throw new IllegalArgumentException("Cannot push HttpMethod.OTHER");
        }
        headers_.method(method.name());
        headers_.path(path);
        headers_.scheme(isSsl() ? "https" : "http");
        if (host != null) {
            headers_.authority(host);
        }
        if (headers != null) {
            headers.forEach(header -> {
                headers_.add((Http2Headers) header.getKey(), header.getValue());
            });
        }
        this.handler.writePushPromise(streamId, headers_, new Handler<AsyncResult<Integer>>() { // from class: io.vertx.core.http.impl.Http2ServerConnection.1
            @Override // io.vertx.core.Handler
            public void handle(AsyncResult<Integer> ar) {
                if (!ar.succeeded()) {
                    ContextInternal contextInternal = Http2ServerConnection.this.context;
                    Handler handler = completionHandler;
                    contextInternal.executeFromIO(v -> {
                        handler.handle(Future.failedFuture(ar.cause()));
                    });
                    return;
                }
                synchronized (Http2ServerConnection.this) {
                    int promisedStreamId = ar.result().intValue();
                    String contentEncoding = HttpUtils.determineContentEncoding(headers_);
                    Http2Stream promisedStream = Http2ServerConnection.this.handler.connection().stream(promisedStreamId);
                    boolean writable = Http2ServerConnection.this.handler.encoder().flowController().isWritable(promisedStream);
                    Push push = Http2ServerConnection.this.new Push(promisedStream, contentEncoding, method, path, writable, completionHandler);
                    push.priority(streamPriority);
                    Http2ServerConnection.this.streams.put(promisedStreamId, (int) push);
                    if (Http2ServerConnection.this.maxConcurrentStreams != null && Http2ServerConnection.this.concurrentStreams >= Http2ServerConnection.this.maxConcurrentStreams.longValue()) {
                        Http2ServerConnection.this.pendingPushes.add(push);
                    } else {
                        Http2ServerConnection.access$108(Http2ServerConnection.this);
                        Http2ServerConnection.this.context.executeFromIO(v2 -> {
                            push.complete();
                        });
                    }
                }
            }
        });
    }

    @Override // io.vertx.core.http.impl.Http2ConnectionBase
    protected void updateSettings(io.netty.handler.codec.http2.Http2Settings settingsUpdate, Handler<AsyncResult<Void>> completionHandler) {
        settingsUpdate.remove((char) 2);
        super.updateSettings(settingsUpdate, completionHandler);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/Http2ServerConnection$Push.class */
    private class Push extends Http2ServerStream {
        private final Promise<HttpServerResponse> completionHandler;

        public Push(Http2Stream stream, String contentEncoding, HttpMethod method, String uri, boolean writable, Handler<AsyncResult<HttpServerResponse>> completionHandler) {
            super(Http2ServerConnection.this, stream, contentEncoding, method, uri, writable);
            Promise<HttpServerResponse> promise = Promise.promise();
            promise.future().setHandler2(completionHandler);
            this.completionHandler = promise;
        }

        @Override // io.vertx.core.http.impl.VertxHttp2Stream
        void handleEnd(MultiMap trailers) {
        }

        @Override // io.vertx.core.http.impl.VertxHttp2Stream
        void handleData(Buffer buf) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // io.vertx.core.http.impl.VertxHttp2Stream
        public void handlePriorityChange(StreamPriority streamPriority) {
        }

        @Override // io.vertx.core.http.impl.Http2ServerStream, io.vertx.core.http.impl.VertxHttp2Stream
        void handleInterestedOpsChanged() {
            if (this.response != null) {
                this.response.writabilityChanged();
            }
        }

        @Override // io.vertx.core.http.impl.VertxHttp2Stream
        void handleReset(long errorCode) {
            if (!this.completionHandler.tryFail(new StreamResetException(errorCode))) {
                this.response.handleReset(errorCode);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // io.vertx.core.http.impl.VertxHttp2Stream
        public void handleException(Throwable cause) {
            if (this.response != null) {
                this.response.handleException(cause);
            }
        }

        @Override // io.vertx.core.http.impl.Http2ServerStream, io.vertx.core.http.impl.VertxHttp2Stream
        void handleClose() {
            super.handleClose();
            if (Http2ServerConnection.this.pendingPushes.remove(this)) {
                this.completionHandler.fail("Push reset by client");
                return;
            }
            Http2ServerConnection.access$110(Http2ServerConnection.this);
            while (true) {
                if ((Http2ServerConnection.this.maxConcurrentStreams != null && Http2ServerConnection.this.concurrentStreams >= Http2ServerConnection.this.maxConcurrentStreams.longValue()) || Http2ServerConnection.this.pendingPushes.size() <= 0) {
                    break;
                }
                Push push = (Push) Http2ServerConnection.this.pendingPushes.pop();
                Http2ServerConnection.access$108(Http2ServerConnection.this);
                this.context.runOnContext(v -> {
                    push.complete();
                });
            }
            this.response.handleClose();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void complete() {
            registerMetrics();
            this.completionHandler.complete(this.response);
        }
    }
}
