package io.vertx.core.http.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.WebSocket;
import io.vertx.core.http.WebSocketFrame;
import io.vertx.core.spi.metrics.HttpClientMetrics;
import io.vertx.core.streams.ReadStream;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/WebSocketImpl.class */
public class WebSocketImpl extends WebSocketImplBase<WebSocketImpl> implements WebSocket {
    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ WebSocket frameHandler(Handler handler) {
        return (WebSocket) super.frameHandler((Handler<WebSocketFrame>) handler);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ WebSocket closeHandler(Handler handler) {
        return (WebSocket) super.closeHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ WebSocket writeTextMessage(String str, Handler handler) {
        return (WebSocket) super.writeTextMessage(str, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ WebSocket writeTextMessage(String str) {
        return (WebSocket) super.writeTextMessage(str);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ WebSocket writeBinaryMessage(Buffer buffer, Handler handler) {
        return (WebSocket) super.writeBinaryMessage(buffer, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ WebSocket writeBinaryMessage(Buffer buffer) {
        return (WebSocket) super.writeBinaryMessage(buffer);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ WebSocket writeFinalBinaryFrame(Buffer buffer, Handler handler) {
        return (WebSocket) super.writeFinalBinaryFrame(buffer, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ WebSocket writeFinalBinaryFrame(Buffer buffer) {
        return (WebSocket) super.writeFinalBinaryFrame(buffer);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ WebSocket writeFinalTextFrame(String str, Handler handler) {
        return (WebSocket) super.writeFinalTextFrame(str, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ WebSocket writeFinalTextFrame(String str) {
        return (WebSocket) super.writeFinalTextFrame(str);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ WebSocket writeFrame(WebSocketFrame webSocketFrame, Handler handler) {
        return (WebSocket) super.writeFrame(webSocketFrame, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ WebSocket writeFrame(WebSocketFrame webSocketFrame) {
        return (WebSocket) super.writeFrame(webSocketFrame);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase, io.vertx.core.streams.WriteStream
    public /* bridge */ /* synthetic */ WebSocket drainHandler(Handler handler) {
        return (WebSocket) super.drainHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase, io.vertx.core.streams.WriteStream
    /* renamed from: setWriteQueueMaxSize */
    public /* bridge */ /* synthetic */ WebSocket setWriteQueueMaxSize2(int i) {
        return (WebSocket) super.setWriteQueueMaxSize2(i);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ WebSocket write(Buffer buffer, Handler handler) {
        return (WebSocket) super.write(buffer, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ WebSocket write(Buffer buffer) {
        return (WebSocket) super.write(buffer);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream
    public /* bridge */ /* synthetic */ ReadStream<Buffer> endHandler(Handler handler) {
        return (WebSocket) super.endHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    public /* bridge */ /* synthetic */ ReadStream<Buffer> fetch2(long j) {
        return (WebSocket) super.fetch2(j);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    public /* bridge */ /* synthetic */ ReadStream<Buffer> resume2() {
        return (WebSocket) super.resume2();
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    public /* bridge */ /* synthetic */ ReadStream<Buffer> pause2() {
        return (WebSocket) super.pause2();
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    public /* bridge */ /* synthetic */ ReadStream<Buffer> handler2(Handler<Buffer> handler) {
        return (WebSocket) super.handler2(handler);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ WebSocket exceptionHandler(Handler handler) {
        return (WebSocket) super.exceptionHandler((Handler<Throwable>) handler);
    }

    public WebSocketImpl(Http1xClientConnection conn, boolean supportsContinuation, int maxWebSocketFrameSize, int maxWebSocketMessageSize) {
        super(conn, supportsContinuation, maxWebSocketFrameSize, maxWebSocketMessageSize);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase
    void handleClosed() {
        synchronized (this.conn) {
            HttpClientMetrics metrics = ((Http1xClientConnection) this.conn).metrics();
            if (metrics != null) {
                metrics.disconnected(getMetric());
            }
        }
        super.handleClosed();
    }
}
