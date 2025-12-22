package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http2.DelegatingDecompressorFrameListener;
import io.netty.handler.codec.http2.Http2CodecUtil;
import io.netty.handler.codec.http2.Http2Connection;
import io.netty.handler.codec.http2.Http2ConnectionDecoder;
import io.netty.handler.codec.http2.Http2ConnectionEncoder;
import io.netty.handler.codec.http2.Http2ConnectionHandler;
import io.netty.handler.codec.http2.Http2DataFrame;
import io.netty.handler.codec.http2.Http2Exception;
import io.netty.handler.codec.http2.Http2Flags;
import io.netty.handler.codec.http2.Http2FrameListener;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.handler.codec.http2.Http2HeadersFrame;
import io.netty.handler.codec.http2.Http2RemoteFlowController;
import io.netty.handler.codec.http2.Http2Settings;
import io.netty.handler.codec.http2.Http2Stream;
import io.netty.handler.codec.http2.Http2StreamFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.EventExecutor;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.impl.Http2ConnectionBase;
import io.vertx.core.impl.ContextInternal;
import java.util.function.Function;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/VertxHttp2ConnectionHandler.class */
class VertxHttp2ConnectionHandler<C extends Http2ConnectionBase> extends Http2ConnectionHandler implements Http2FrameListener, Http2Connection.Listener {
    private final Function<VertxHttp2ConnectionHandler<C>, C> connectionFactory;
    private C connection;
    private ChannelHandlerContext chctx;
    private Handler<C> addHandler;
    private Handler<C> removeHandler;
    private final boolean useDecompressor;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !VertxHttp2ConnectionHandler.class.desiredAssertionStatus();
    }

    public VertxHttp2ConnectionHandler(Function<VertxHttp2ConnectionHandler<C>, C> connectionFactory, boolean useDecompressor, Http2ConnectionDecoder decoder, Http2ConnectionEncoder encoder, Http2Settings initialSettings) {
        super(decoder, encoder, initialSettings);
        this.connectionFactory = connectionFactory;
        this.useDecompressor = useDecompressor;
        encoder().flowController().listener(s -> {
            if (this.connection != null) {
                this.connection.onStreamWritabilityChanged(s);
            }
        });
        connection().addListener(this);
    }

    public ChannelHandlerContext context() {
        return this.chctx;
    }

    public VertxHttp2ConnectionHandler<C> addHandler(Handler<C> handler) {
        this.addHandler = handler;
        return this;
    }

    public VertxHttp2ConnectionHandler<C> removeHandler(Handler<C> handler) {
        this.removeHandler = handler;
        return this;
    }

    @Override // io.netty.handler.codec.http2.Http2ConnectionHandler, io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        this.chctx = ctx;
    }

    @Override // io.netty.handler.codec.http2.Http2ConnectionHandler, io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler, io.netty.channel.ChannelInboundHandler
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Http2Exception http2Cause = Http2CodecUtil.getEmbeddedHttp2Exception(cause);
        if (http2Cause != null) {
            super.exceptionCaught(ctx, http2Cause);
        }
        ctx.close();
    }

    public void serverUpgrade(ChannelHandlerContext ctx, Http2Settings serverUpgradeSettings, HttpRequest request) throws Exception {
        onHttpServerUpgrade(serverUpgradeSettings);
        onSettingsRead(ctx, serverUpgradeSettings);
    }

    public void clientUpgrade(ChannelHandlerContext ctx) throws Exception {
        onHttpClientUpgrade();
        ctx.flush();
    }

    @Override // io.netty.handler.codec.http2.Http2ConnectionHandler, io.netty.handler.codec.ByteToMessageDecoder, io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext chctx) throws Exception {
        super.channelInactive(chctx);
        if (this.connection != null) {
            ContextInternal ctx = this.connection.getContext();
            ctx.executeFromIO(v -> {
                this.connection.handleClosed();
            });
            if (this.removeHandler != null) {
                this.removeHandler.handle(this.connection);
            }
        }
    }

    @Override // io.netty.handler.codec.http2.Http2ConnectionHandler
    protected void onConnectionError(ChannelHandlerContext ctx, boolean outbound, Throwable cause, Http2Exception http2Ex) {
        this.connection.getContext().executeFromIO(v -> {
            this.connection.onConnectionError(cause);
        });
        super.onConnectionError(ctx, outbound, cause, http2Ex);
    }

    @Override // io.netty.handler.codec.http2.Http2ConnectionHandler
    protected void onStreamError(ChannelHandlerContext ctx, boolean outbound, Throwable cause, Http2Exception.StreamException http2Ex) {
        this.connection.getContext().executeFromIO(v -> {
            this.connection.onStreamError(http2Ex.streamId(), http2Ex);
        });
        super.onStreamError(ctx, outbound, cause, http2Ex);
    }

    @Override // io.netty.handler.codec.ByteToMessageDecoder, io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        try {
            super.userEventTriggered(ctx, evt);
        } finally {
            if ((evt instanceof IdleStateEvent) && ((IdleStateEvent) evt).state() == IdleState.ALL_IDLE) {
                this.connection.handleIdle();
            }
        }
    }

    @Override // io.netty.handler.codec.http2.Http2Connection.Listener
    public void onStreamClosed(Http2Stream stream) {
        this.connection.onStreamClosed(stream);
    }

    @Override // io.netty.handler.codec.http2.Http2Connection.Listener
    public void onStreamAdded(Http2Stream stream) {
    }

    @Override // io.netty.handler.codec.http2.Http2Connection.Listener
    public void onStreamActive(Http2Stream stream) {
    }

    @Override // io.netty.handler.codec.http2.Http2Connection.Listener
    public void onStreamHalfClosed(Http2Stream stream) {
    }

    @Override // io.netty.handler.codec.http2.Http2Connection.Listener
    public void onStreamRemoved(Http2Stream stream) {
    }

    @Override // io.netty.handler.codec.http2.Http2Connection.Listener
    public void onGoAwaySent(int lastStreamId, long errorCode, ByteBuf debugData) {
        this.connection.onGoAwaySent(lastStreamId, errorCode, debugData);
    }

    @Override // io.netty.handler.codec.http2.Http2Connection.Listener
    public void onGoAwayReceived(int lastStreamId, long errorCode, ByteBuf debugData) {
        this.connection.onGoAwayReceived(lastStreamId, errorCode, debugData);
    }

    void writeHeaders(Http2Stream stream, Http2Headers headers, boolean end, int streamDependency, short weight, boolean exclusive, Handler<AsyncResult<Void>> handler) {
        EventExecutor executor = this.chctx.executor();
        ChannelPromise promise = this.connection.toPromise(handler);
        if (executor.inEventLoop()) {
            _writeHeaders(stream, headers, end, streamDependency, weight, exclusive, promise);
        } else {
            executor.execute(() -> {
                _writeHeaders(stream, headers, end, streamDependency, weight, exclusive, promise);
            });
        }
    }

    private void _writeHeaders(Http2Stream stream, Http2Headers headers, boolean end, int streamDependency, short weight, boolean exclusive, ChannelPromise promise) {
        encoder().writeHeaders(this.chctx, stream.id(), headers, streamDependency, weight, exclusive, 0, end, promise);
    }

    void writeData(Http2Stream stream, ByteBuf chunk, boolean end, Handler<AsyncResult<Void>> handler) {
        EventExecutor executor = this.chctx.executor();
        ChannelPromise promise = this.connection.toPromise(handler);
        if (executor.inEventLoop()) {
            _writeData(stream, chunk, end, promise);
        } else {
            executor.execute(() -> {
                _writeData(stream, chunk, end, promise);
            });
        }
    }

    private void _writeData(Http2Stream stream, ByteBuf chunk, boolean end, ChannelPromise promise) {
        encoder().writeData(this.chctx, stream.id(), chunk, 0, end, promise);
        Http2RemoteFlowController controller = encoder().flowController();
        if (!controller.isWritable(stream) || end) {
            try {
                encoder().flowController().writePendingBytes();
            } catch (Http2Exception e) {
                onError(this.chctx, true, e);
            }
        }
        this.chctx.channel().flush();
    }

    ChannelFuture writePing(long data) {
        ChannelPromise promise = this.chctx.newPromise();
        EventExecutor executor = this.chctx.executor();
        if (executor.inEventLoop()) {
            _writePing(data, promise);
        } else {
            executor.execute(() -> {
                _writePing(data, promise);
            });
        }
        return promise;
    }

    private void _writePing(long data, ChannelPromise promise) {
        encoder().writePing(this.chctx, false, data, promise);
        this.chctx.channel().flush();
    }

    void consume(Http2Stream stream, int numBytes) {
        try {
            boolean windowUpdateSent = decoder().flowController().consumeBytes(stream, numBytes);
            if (windowUpdateSent) {
                this.chctx.channel().flush();
            }
        } catch (Http2Exception e) {
            onError(this.chctx, true, e);
        }
    }

    void writeFrame(Http2Stream stream, byte type, short flags, ByteBuf payload) {
        EventExecutor executor = this.chctx.executor();
        if (executor.inEventLoop()) {
            _writeFrame(stream, type, flags, payload);
        } else {
            executor.execute(() -> {
                _writeFrame(stream, type, flags, payload);
            });
        }
    }

    private void _writeFrame(Http2Stream stream, byte type, short flags, ByteBuf payload) {
        encoder().writeFrame(this.chctx, type, stream.id(), new Http2Flags(flags), payload, this.chctx.newPromise());
        this.chctx.flush();
    }

    void writeReset(int streamId, long code) {
        EventExecutor executor = this.chctx.executor();
        if (executor.inEventLoop()) {
            _writeReset(streamId, code);
        } else {
            executor.execute(() -> {
                _writeReset(streamId, code);
            });
        }
    }

    private void _writeReset(int streamId, long code) {
        encoder().writeRstStream(this.chctx, streamId, code, this.chctx.newPromise());
        this.chctx.flush();
    }

    void writeGoAway(long errorCode, int lastStreamId, ByteBuf debugData) {
        EventExecutor executor = this.chctx.executor();
        if (executor.inEventLoop()) {
            _writeGoAway(errorCode, lastStreamId, debugData);
        } else {
            executor.execute(() -> {
                _writeGoAway(errorCode, lastStreamId, debugData);
            });
        }
    }

    private void _writeGoAway(long errorCode, int lastStreamId, ByteBuf debugData) {
        encoder().writeGoAway(this.chctx, lastStreamId, errorCode, debugData, this.chctx.newPromise());
        this.chctx.flush();
    }

    ChannelFuture writeSettings(Http2Settings settingsUpdate) {
        ChannelPromise promise = this.chctx.newPromise();
        EventExecutor executor = this.chctx.executor();
        if (executor.inEventLoop()) {
            _writeSettings(settingsUpdate, promise);
        } else {
            executor.execute(() -> {
                _writeSettings(settingsUpdate, promise);
            });
        }
        return promise;
    }

    private void _writeSettings(Http2Settings settingsUpdate, ChannelPromise promise) {
        encoder().writeSettings(this.chctx, settingsUpdate, promise);
        this.chctx.flush();
    }

    void writePushPromise(int streamId, Http2Headers headers, Handler<AsyncResult<Integer>> completionHandler) {
        int promisedStreamId = connection().local().incrementAndGetNextStreamId();
        ChannelPromise promise = this.chctx.newPromise();
        promise.addListener2(fut -> {
            if (fut.isSuccess()) {
                completionHandler.handle(Future.succeededFuture(Integer.valueOf(promisedStreamId)));
            } else {
                completionHandler.handle(Future.failedFuture(fut.cause()));
            }
        });
        EventExecutor executor = this.chctx.executor();
        if (executor.inEventLoop()) {
            _writePushPromise(streamId, promisedStreamId, headers, promise);
        } else {
            executor.execute(() -> {
                _writePushPromise(streamId, promisedStreamId, headers, promise);
            });
        }
    }

    private void _writePushPromise(int streamId, int promisedStreamId, Http2Headers headers, ChannelPromise promise) {
        encoder().writePushPromise(this.chctx, streamId, promisedStreamId, headers, 0, promise);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public int onDataRead(ChannelHandlerContext ctx, int streamId, ByteBuf data, int padding, boolean endOfStream) throws Http2Exception {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int padding, boolean endOfStream) throws Http2Exception {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int streamDependency, short weight, boolean exclusive, int padding, boolean endOfStream) throws Http2Exception {
        if (!$assertionsDisabled && this.connection == null) {
            throw new AssertionError();
        }
        this.connection.onHeadersRead(ctx, streamId, headers, streamDependency, weight, exclusive, padding, endOfStream);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onPriorityRead(ChannelHandlerContext ctx, int streamId, int streamDependency, short weight, boolean exclusive) throws Http2Exception {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onRstStreamRead(ChannelHandlerContext ctx, int streamId, long errorCode) throws Http2Exception {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onSettingsAckRead(ChannelHandlerContext ctx) throws Http2Exception {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onSettingsRead(ChannelHandlerContext ctx, Http2Settings settings) throws Http2Exception {
        this.connection = this.connectionFactory.apply(this);
        if (this.useDecompressor) {
            decoder().frameListener(new DelegatingDecompressorFrameListener(decoder().connection(), this.connection));
        } else {
            decoder().frameListener(this.connection);
        }
        this.connection.onSettingsRead(ctx, settings);
        if (this.addHandler != null) {
            this.addHandler.handle(this.connection);
        }
    }

    @Override // io.netty.handler.codec.ByteToMessageDecoder, io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof Http2StreamFrame) {
            if (msg instanceof Http2HeadersFrame) {
                Http2HeadersFrame frame = (Http2HeadersFrame) msg;
                this.connection.onHeadersRead(ctx, 1, frame.headers(), frame.padding(), frame.isEndStream());
                return;
            } else {
                if (msg instanceof Http2DataFrame) {
                    Http2DataFrame frame2 = (Http2DataFrame) msg;
                    this.connection.onDataRead(ctx, 1, frame2.content(), frame2.padding(), frame2.isEndStream());
                    return;
                }
                return;
            }
        }
        super.channelRead(ctx, msg);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onPingRead(ChannelHandlerContext ctx, long data) throws Http2Exception {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onPingAckRead(ChannelHandlerContext ctx, long data) throws Http2Exception {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onPushPromiseRead(ChannelHandlerContext ctx, int streamId, int promisedStreamId, Http2Headers headers, int padding) throws Http2Exception {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onGoAwayRead(ChannelHandlerContext ctx, int lastStreamId, long errorCode, ByteBuf debugData) throws Http2Exception {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onWindowUpdateRead(ChannelHandlerContext ctx, int streamId, int windowSizeIncrement) throws Http2Exception {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onUnknownFrame(ChannelHandlerContext ctx, byte frameType, int streamId, Http2Flags flags, ByteBuf payload) throws Http2Exception {
        throw new UnsupportedOperationException();
    }

    private void _writePriority(Http2Stream stream, int streamDependency, short weight, boolean exclusive) {
        encoder().writePriority(this.chctx, stream.id(), streamDependency, weight, exclusive, this.chctx.newPromise());
    }

    void writePriority(Http2Stream stream, int streamDependency, short weight, boolean exclusive) {
        EventExecutor executor = this.chctx.executor();
        if (executor.inEventLoop()) {
            _writePriority(stream, streamDependency, weight, exclusive);
        } else {
            executor.execute(() -> {
                _writePriority(stream, streamDependency, weight, exclusive);
            });
        }
    }
}
