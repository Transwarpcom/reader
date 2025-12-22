package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.GoAway;
import io.vertx.core.http.Http2Settings;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.impl.WebSocketImplBase;
import io.vertx.core.http.impl.ws.WebSocketFrameImpl;
import io.vertx.core.http.impl.ws.WebSocketFrameInternal;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.net.impl.ChannelFutureListenerAdapter;
import io.vertx.core.net.impl.ConnectionBase;
import io.vertx.core.net.impl.VertxHandler;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/Http1xConnectionBase.class */
abstract class Http1xConnectionBase<S extends WebSocketImplBase<S>> extends ConnectionBase implements HttpConnection {
    protected S ws;
    private boolean closeFrameSent;

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

    Http1xConnectionBase(VertxInternal vertx, ChannelHandlerContext chctx, ContextInternal context) {
        super(vertx, chctx, context);
    }

    WebSocketFrame encodeFrame(WebSocketFrameImpl frame) {
        ByteBuf buf = frame.getBinaryData();
        if (buf != Unpooled.EMPTY_BUFFER) {
            buf = VertxHandler.safeBuffer(buf, this.chctx.alloc());
        }
        switch (frame.type()) {
            case BINARY:
                return new BinaryWebSocketFrame(frame.isFinal(), 0, buf);
            case TEXT:
                return new TextWebSocketFrame(frame.isFinal(), 0, buf);
            case CLOSE:
                return new CloseWebSocketFrame(true, 0, buf);
            case CONTINUATION:
                return new ContinuationWebSocketFrame(frame.isFinal(), 0, buf);
            case PONG:
                return new PongWebSocketFrame(buf);
            case PING:
                return new PingWebSocketFrame(buf);
            default:
                throw new IllegalStateException("Unsupported websocket msg " + frame);
        }
    }

    private WebSocketFrameInternal decodeFrame(WebSocketFrame msg) {
        FrameType frameType;
        ByteBuf payload = VertxHandler.safeBuffer(msg, this.chctx.alloc());
        boolean isFinal = msg.isFinalFragment();
        if (msg instanceof BinaryWebSocketFrame) {
            frameType = FrameType.BINARY;
        } else if (msg instanceof CloseWebSocketFrame) {
            frameType = FrameType.CLOSE;
        } else if (msg instanceof PingWebSocketFrame) {
            frameType = FrameType.PING;
        } else if (msg instanceof PongWebSocketFrame) {
            frameType = FrameType.PONG;
        } else if (msg instanceof TextWebSocketFrame) {
            frameType = FrameType.TEXT;
        } else if (msg instanceof ContinuationWebSocketFrame) {
            frameType = FrameType.CONTINUATION;
        } else {
            throw new IllegalStateException("Unsupported websocket msg " + msg);
        }
        return new WebSocketFrameImpl(frameType, payload, isFinal);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    void handleWsFrame(WebSocketFrame msg) {
        S w;
        WebSocketFrameInternal frame = decodeFrame(msg);
        synchronized (this) {
            switch (frame.type()) {
                case CLOSE:
                    synchronized (this) {
                        if (!this.closeFrameSent) {
                            CloseWebSocketFrame closeFrame = new CloseWebSocketFrame(frame.closeStatusCode(), frame.closeReason());
                            this.chctx.writeAndFlush(closeFrame).addListener2((GenericFutureListener<? extends Future<? super Void>>) ChannelFutureListener.CLOSE);
                            this.closeFrameSent = true;
                        }
                        w = this.ws;
                        break;
                    }
                case PING:
                    this.chctx.writeAndFlush(new PongWebSocketFrame(frame.getBinaryData().copy()));
                    w = this.ws;
                    break;
                default:
                    w = this.ws;
                    break;
            }
        }
        if (w != null) {
            w.handleFrame(frame);
        }
    }

    @Override // io.vertx.core.net.impl.ConnectionBase, io.vertx.core.http.HttpConnection
    public void close() {
        close(null);
    }

    @Override // io.vertx.core.net.impl.ConnectionBase
    public void close(Handler<AsyncResult<Void>> handler) {
        closeWithPayload((short) 1000, null, handler);
    }

    void closeWithPayload(short code, String reason, Handler<AsyncResult<Void>> handler) {
        if (this.ws == null) {
            super.close(handler);
            return;
        }
        ByteBuf byteBuf = HttpUtils.generateWSCloseFrameByteBuf(code, reason);
        CloseWebSocketFrame frame = new CloseWebSocketFrame(true, 0, byteBuf);
        ChannelPromise promise = this.chctx.newPromise();
        flush(promise);
        promise.addListener2((GenericFutureListener<? extends Future<? super Void>>) future -> {
            ChannelFuture fut = this.chctx.writeAndFlush(frame);
            boolean server = this instanceof Http1xServerConnection;
            if (server) {
                fut.addListener2((GenericFutureListener<? extends Future<? super Void>>) f -> {
                    ChannelFuture closeFut = this.chctx.channel().close();
                    if (handler != null) {
                        closeFut.addListener2((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListenerAdapter(this.context, null, handler));
                    }
                });
            } else if (handler != null) {
                fut.addListener2((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListenerAdapter(this.context, null, handler));
            }
        });
    }

    @Override // io.vertx.core.net.impl.ConnectionBase, io.vertx.core.http.HttpConnection
    public Http1xConnectionBase closeHandler(Handler<Void> handler) {
        return (Http1xConnectionBase) super.closeHandler(handler);
    }

    @Override // io.vertx.core.net.impl.ConnectionBase, io.vertx.core.http.HttpConnection
    public Http1xConnectionBase exceptionHandler(Handler<Throwable> handler) {
        return (Http1xConnectionBase) super.exceptionHandler(handler);
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection goAway(long errorCode, int lastStreamId, Buffer debugData) {
        throw new UnsupportedOperationException("HTTP/1.x connections don't support GOAWAY");
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection goAwayHandler(Handler<GoAway> handler) {
        throw new UnsupportedOperationException("HTTP/1.x connections don't support GOAWAY");
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection shutdownHandler(Handler<Void> handler) {
        throw new UnsupportedOperationException("HTTP/1.x connections don't support GOAWAY");
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection shutdown() {
        throw new UnsupportedOperationException("HTTP/1.x connections don't support GOAWAY");
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection shutdown(long timeoutMs) {
        throw new UnsupportedOperationException("HTTP/1.x connections don't support GOAWAY");
    }

    @Override // io.vertx.core.http.HttpConnection
    public Http2Settings settings() {
        throw new UnsupportedOperationException("HTTP/1.x connections don't support SETTINGS");
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection updateSettings(Http2Settings settings) {
        throw new UnsupportedOperationException("HTTP/1.x connections don't support SETTINGS");
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection updateSettings(Http2Settings settings, Handler<AsyncResult<Void>> completionHandler) {
        throw new UnsupportedOperationException("HTTP/1.x connections don't support SETTINGS");
    }

    @Override // io.vertx.core.http.HttpConnection
    public Http2Settings remoteSettings() {
        throw new UnsupportedOperationException("HTTP/1.x connections don't support SETTINGS");
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection remoteSettingsHandler(Handler<Http2Settings> handler) {
        throw new UnsupportedOperationException("HTTP/1.x connections don't support SETTINGS");
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection ping(Buffer data, Handler<AsyncResult<Buffer>> pongHandler) {
        throw new UnsupportedOperationException("HTTP/1.x connections don't support PING");
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection pingHandler(Handler<Buffer> handler) {
        throw new UnsupportedOperationException("HTTP/1.x connections don't support PING");
    }
}
