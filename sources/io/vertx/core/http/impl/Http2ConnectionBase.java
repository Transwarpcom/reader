package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http2.Http2Connection;
import io.netty.handler.codec.http2.Http2Exception;
import io.netty.handler.codec.http2.Http2Flags;
import io.netty.handler.codec.http2.Http2FrameListener;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.handler.codec.http2.Http2LocalFlowController;
import io.netty.handler.codec.http2.Http2Stream;
import io.netty.util.collection.IntObjectHashMap;
import io.netty.util.collection.IntObjectMap;
import io.netty.util.concurrent.GenericFutureListener;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.VertxException;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.GoAway;
import io.vertx.core.http.Http2Settings;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.impl.ConnectionBase;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/Http2ConnectionBase.class */
abstract class Http2ConnectionBase extends ConnectionBase implements Http2FrameListener, HttpConnection {
    protected final IntObjectMap<VertxHttp2Stream> streams;
    protected final ChannelHandlerContext handlerContext;
    protected final VertxHttp2ConnectionHandler handler;
    private boolean shutdown;
    private Handler<Http2Settings> remoteSettingsHandler;
    private final ArrayDeque<Handler<Void>> updateSettingsHandlers;
    private final ArrayDeque<Handler<AsyncResult<Buffer>>> pongHandlers;
    private io.netty.handler.codec.http2.Http2Settings localSettings;
    private io.netty.handler.codec.http2.Http2Settings remoteSettings;
    private Handler<GoAway> goAwayHandler;
    private Handler<Void> shutdownHandler;
    private Handler<Buffer> pingHandler;
    private boolean goneAway;
    private int windowSize;
    private long maxConcurrentStreams;

    @Override // io.vertx.core.net.impl.ConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ ConnectionBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.net.impl.ConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ ConnectionBase closeHandler(Handler handler) {
        return closeHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ HttpConnection exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ HttpConnection closeHandler(Handler handler) {
        return closeHandler((Handler<Void>) handler);
    }

    static ByteBuf safeBuffer(ByteBuf buf, ByteBufAllocator allocator) {
        if (buf == Unpooled.EMPTY_BUFFER) {
            return buf;
        }
        if (buf.isDirect() || (buf instanceof CompositeByteBuf)) {
            if (buf.isReadable()) {
                ByteBuf buffer = allocator.heapBuffer(buf.readableBytes());
                buffer.writeBytes(buf);
                return buffer;
            }
            return Unpooled.EMPTY_BUFFER;
        }
        return buf.retain();
    }

    public Http2ConnectionBase(ContextInternal context, VertxHttp2ConnectionHandler handler) {
        super(context.owner(), handler.context(), context);
        this.streams = new IntObjectHashMap();
        this.updateSettingsHandlers = new ArrayDeque<>();
        this.pongHandlers = new ArrayDeque<>();
        this.localSettings = new io.netty.handler.codec.http2.Http2Settings();
        this.handler = handler;
        this.handlerContext = this.chctx;
        this.windowSize = ((Http2LocalFlowController) handler.connection().local().flowController()).windowSize(handler.connection().connectionStream());
        this.maxConcurrentStreams = 4294967295L;
    }

    VertxInternal vertx() {
        return this.vertx;
    }

    NetSocket toNetSocket(VertxHttp2Stream stream) {
        VertxHttp2NetSocket<Http2ConnectionBase> rempl = new VertxHttp2NetSocket<>(this, stream.stream, !stream.isNotWritable());
        this.streams.put(stream.stream.id(), (int) rempl);
        return rempl;
    }

    @Override // io.vertx.core.net.impl.ConnectionBase
    public void handleClosed() {
        super.handleClosed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.vertx.core.net.impl.ConnectionBase
    public void handleInterestedOpsChanged() {
    }

    @Override // io.vertx.core.net.impl.ConnectionBase
    protected void handleIdle() {
        super.handleIdle();
    }

    synchronized void onConnectionError(Throwable cause) {
        ArrayList<VertxHttp2Stream> copy;
        copy = new ArrayList<>(this.streams.values());
        Iterator<VertxHttp2Stream> it = copy.iterator();
        while (it.hasNext()) {
            VertxHttp2Stream stream = it.next();
            this.context.executeFromIO(v -> {
                stream.handleException(cause);
            });
        }
        handleException(cause);
    }

    void onStreamError(int streamId, Throwable cause) {
        VertxHttp2Stream stream;
        synchronized (this) {
            stream = this.streams.get(streamId);
        }
        if (stream != null) {
            stream.handleException(cause);
        }
    }

    void onStreamWritabilityChanged(Http2Stream s) {
        VertxHttp2Stream stream;
        synchronized (this) {
            stream = this.streams.get(s.id());
        }
        if (stream != null) {
            this.context.executeFromIO(v -> {
                stream.onWritabilityChanged();
            });
        }
    }

    void onStreamClosed(Http2Stream stream) {
        synchronized (this) {
            VertxHttp2Stream removed = this.streams.remove(stream.id());
            if (removed == null) {
                return;
            }
            this.context.executeFromIO(v -> {
                removed.handleClose();
            });
            checkShutdownHandler();
        }
    }

    boolean onGoAwaySent(int lastStreamId, long errorCode, ByteBuf debugData) {
        synchronized (this) {
            if (this.goneAway) {
                return false;
            }
            this.goneAway = true;
            checkShutdownHandler();
            return true;
        }
    }

    boolean onGoAwayReceived(int lastStreamId, long errorCode, ByteBuf debugData) {
        synchronized (this) {
            if (this.goneAway) {
                return false;
            }
            this.goneAway = true;
            Handler<GoAway> handler = this.goAwayHandler;
            if (handler != null) {
                Buffer buffer = Buffer.buffer(debugData);
                this.context.executeFromIO(v -> {
                    handler.handle(new GoAway().setErrorCode(errorCode).setLastStreamId(lastStreamId).setDebugData(buffer));
                });
            }
            checkShutdownHandler();
            return true;
        }
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onPriorityRead(ChannelHandlerContext ctx, int streamId, int streamDependency, short weight, boolean exclusive) {
        VertxHttp2Stream stream;
        synchronized (this) {
            stream = this.streams.get(streamId);
        }
        if (stream != null) {
            StreamPriority streamPriority = new StreamPriority().setDependency(streamDependency).setWeight(weight).setExclusive(exclusive);
            this.context.executeFromIO(v -> {
                stream.handlePriorityChange(streamPriority);
            });
        }
    }

    public void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int streamDependency, short weight, boolean exclusive, int padding, boolean endOfStream) throws Http2Exception {
        onHeadersRead(ctx, streamId, headers, padding, endOfStream);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onSettingsAckRead(ChannelHandlerContext ctx) {
        Handler<Void> handler;
        synchronized (this) {
            handler = this.updateSettingsHandlers.poll();
        }
        if (handler != null) {
            this.context.executeFromIO(handler);
        }
    }

    protected void concurrencyChanged(long concurrency) {
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onSettingsRead(ChannelHandlerContext ctx, io.netty.handler.codec.http2.Http2Settings settings) {
        boolean changed;
        Handler<Http2Settings> handler;
        synchronized (this) {
            Long val = settings.maxConcurrentStreams();
            if (val != null) {
                if (this.remoteSettings != null) {
                    changed = val.longValue() != this.maxConcurrentStreams;
                } else {
                    changed = false;
                }
                this.maxConcurrentStreams = val.longValue();
            } else {
                changed = false;
            }
            this.remoteSettings = settings;
            handler = this.remoteSettingsHandler;
        }
        if (handler != null) {
            this.context.executeFromIO(HttpUtils.toVertxSettings(settings), handler);
        }
        if (changed) {
            concurrencyChanged(this.maxConcurrentStreams);
        }
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onPingRead(ChannelHandlerContext ctx, long data) throws Http2Exception {
        Handler<Buffer> handler = this.pingHandler;
        if (handler != null) {
            Buffer buff = Buffer.buffer().appendLong(data);
            this.context.executeFromIO(v -> {
                handler.handle(buff);
            });
        }
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onPingAckRead(ChannelHandlerContext ctx, long data) throws Http2Exception {
        Handler<AsyncResult<Buffer>> handler = this.pongHandlers.poll();
        if (handler != null) {
            this.context.executeFromIO(v -> {
                Buffer buff = Buffer.buffer().appendLong(data);
                handler.handle(Future.succeededFuture(buff));
            });
        }
    }

    public void onPushPromiseRead(ChannelHandlerContext ctx, int streamId, int promisedStreamId, Http2Headers headers, int padding) throws Http2Exception {
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onGoAwayRead(ChannelHandlerContext ctx, int lastStreamId, long errorCode, ByteBuf debugData) {
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onWindowUpdateRead(ChannelHandlerContext ctx, int streamId, int windowSizeIncrement) {
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onUnknownFrame(ChannelHandlerContext ctx, byte frameType, int streamId, Http2Flags flags, ByteBuf payload) {
        VertxHttp2Stream req;
        synchronized (this) {
            req = this.streams.get(streamId);
        }
        if (req != null) {
            Buffer buff = Buffer.buffer(safeBuffer(payload, ctx.alloc()));
            this.context.executeFromIO(v -> {
                req.handleCustomFrame(frameType, flags.value(), buff);
            });
        }
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onRstStreamRead(ChannelHandlerContext ctx, int streamId, long errorCode) {
        synchronized (this) {
            VertxHttp2Stream req = this.streams.get(streamId);
            if (req == null) {
                return;
            }
            this.context.executeFromIO(v -> {
                req.onResetRead(errorCode);
            });
        }
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public int onDataRead(ChannelHandlerContext ctx, int streamId, ByteBuf data, int padding, boolean endOfStream) {
        VertxHttp2Stream req;
        int[] consumed = {padding};
        synchronized (this) {
            req = this.streams.get(streamId);
        }
        if (req != null) {
            Buffer buff = Buffer.buffer(safeBuffer(data, ctx.alloc()));
            this.context.executeFromIO(v -> {
                int len = buff.length();
                if (req.onDataRead(buff)) {
                    consumed[0] = consumed[0] + len;
                }
            });
            if (endOfStream) {
                this.context.executeFromIO(v2 -> {
                    req.onEnd();
                });
            }
        }
        return consumed[0];
    }

    @Override // io.vertx.core.http.HttpConnection
    public int getWindowSize() {
        return this.windowSize;
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection setWindowSize(int windowSize) {
        try {
            Http2Stream stream = this.handler.encoder().connection().connectionStream();
            int delta = windowSize - this.windowSize;
            this.handler.decoder().flowController().incrementWindowSize(stream, delta);
            this.windowSize = windowSize;
            return this;
        } catch (Http2Exception e) {
            throw new VertxException(e);
        }
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection goAway(long errorCode, int lastStreamId, Buffer debugData) {
        if (errorCode < 0) {
            throw new IllegalArgumentException();
        }
        if (lastStreamId < 0) {
            lastStreamId = this.handler.connection().remote().lastStreamCreated();
        }
        this.handler.writeGoAway(errorCode, lastStreamId, debugData != null ? debugData.getByteBuf() : Unpooled.EMPTY_BUFFER);
        return this;
    }

    @Override // io.vertx.core.http.HttpConnection
    public synchronized HttpConnection goAwayHandler(Handler<GoAway> handler) {
        this.goAwayHandler = handler;
        return this;
    }

    @Override // io.vertx.core.http.HttpConnection
    public synchronized HttpConnection shutdownHandler(Handler<Void> handler) {
        this.shutdownHandler = handler;
        return this;
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection shutdown(long timeout) {
        if (timeout < 0) {
            throw new IllegalArgumentException("Invalid timeout value " + timeout);
        }
        this.handler.gracefulShutdownTimeoutMillis(timeout);
        channel().close();
        return this;
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection shutdown() {
        return shutdown(30000L);
    }

    @Override // io.vertx.core.net.impl.ConnectionBase, io.vertx.core.http.HttpConnection
    public Http2ConnectionBase closeHandler(Handler<Void> handler) {
        return (Http2ConnectionBase) super.closeHandler(handler);
    }

    @Override // io.vertx.core.net.impl.ConnectionBase, io.vertx.core.http.HttpConnection
    public void close() {
        ChannelPromise promise = this.chctx.newPromise();
        flush(promise);
        promise.addListener2((GenericFutureListener<? extends io.netty.util.concurrent.Future<? super Void>>) future -> {
            shutdown(0L);
        });
    }

    @Override // io.vertx.core.http.HttpConnection
    public synchronized HttpConnection remoteSettingsHandler(Handler<Http2Settings> handler) {
        this.remoteSettingsHandler = handler;
        return this;
    }

    @Override // io.vertx.core.http.HttpConnection
    public synchronized Http2Settings remoteSettings() {
        return HttpUtils.toVertxSettings(this.remoteSettings);
    }

    @Override // io.vertx.core.http.HttpConnection
    public synchronized Http2Settings settings() {
        return HttpUtils.toVertxSettings(this.localSettings);
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection updateSettings(Http2Settings settings) {
        return updateSettings(settings, (Handler<AsyncResult<Void>>) null);
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection updateSettings(Http2Settings settings, Handler<AsyncResult<Void>> completionHandler) {
        io.netty.handler.codec.http2.Http2Settings settingsUpdate = HttpUtils.fromVertxSettings(settings);
        updateSettings(settingsUpdate, completionHandler);
        return this;
    }

    protected void updateSettings(io.netty.handler.codec.http2.Http2Settings settingsUpdate, Handler<AsyncResult<Void>> completionHandler) {
        io.netty.handler.codec.http2.Http2Settings current = this.handler.decoder().localSettings();
        for (Map.Entry<Character, Long> entry : current.entrySet()) {
            Character key = entry.getKey();
            if (Objects.equals(settingsUpdate.get(key), entry.getValue())) {
                settingsUpdate.remove(key);
            }
        }
        Handler<Void> pending = v -> {
            synchronized (this) {
                this.localSettings.putAll(settingsUpdate);
            }
            if (completionHandler != null) {
                completionHandler.handle(Future.succeededFuture());
            }
        };
        this.updateSettingsHandlers.add(pending);
        this.handler.writeSettings(settingsUpdate).addListener2(fut -> {
            if (!fut.isSuccess()) {
                synchronized (this) {
                    this.updateSettingsHandlers.remove(pending);
                }
                if (completionHandler != null) {
                    completionHandler.handle(Future.failedFuture(fut.cause()));
                }
            }
        });
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection ping(Buffer data, Handler<AsyncResult<Buffer>> pongHandler) {
        if (data.length() != 8) {
            throw new IllegalArgumentException("Ping data must be exactly 8 bytes");
        }
        this.handler.writePing(data.getLong(0)).addListener2(fut -> {
            if (fut.isSuccess()) {
                synchronized (this) {
                    this.pongHandlers.add(pongHandler);
                }
            } else {
                pongHandler.handle(Future.failedFuture(fut.cause()));
            }
        });
        return this;
    }

    @Override // io.vertx.core.http.HttpConnection
    public synchronized HttpConnection pingHandler(Handler<Buffer> handler) {
        this.pingHandler = handler;
        return this;
    }

    @Override // io.vertx.core.net.impl.ConnectionBase, io.vertx.core.http.HttpConnection
    public Http2ConnectionBase exceptionHandler(Handler<Throwable> handler) {
        return (Http2ConnectionBase) super.exceptionHandler(handler);
    }

    private void checkShutdownHandler() {
        synchronized (this) {
            if (this.shutdown) {
                return;
            }
            Http2Connection conn = this.handler.connection();
            if ((conn.goAwayReceived() || conn.goAwaySent()) && conn.numActiveStreams() <= 0) {
                this.shutdown = true;
                Handler<Void> shutdownHandler = this.shutdownHandler;
                if (shutdownHandler != null) {
                    this.context.executeFromIO(shutdownHandler);
                }
            }
        }
    }
}
