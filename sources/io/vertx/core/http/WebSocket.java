package io.vertx.core.http;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.WriteStream;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/WebSocket.class */
public interface WebSocket extends WebSocketBase {
    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    WebSocket exceptionHandler(Handler<Throwable> handler);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    ReadStream<Buffer> handler2(Handler<Buffer> handler);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    ReadStream<Buffer> pause2();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    ReadStream<Buffer> resume2();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    ReadStream<Buffer> fetch2(long j);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream
    ReadStream<Buffer> endHandler(Handler<Void> handler);

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.WriteStream
    WebSocket write(Buffer buffer);

    @Override // io.vertx.core.http.WebSocketBase
    WebSocket write(Buffer buffer, Handler<AsyncResult<Void>> handler);

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.WriteStream
    /* renamed from: setWriteQueueMaxSize */
    WebSocket setWriteQueueMaxSize2(int i);

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.WriteStream
    WebSocket drainHandler(Handler<Void> handler);

    @Override // io.vertx.core.http.WebSocketBase
    WebSocket writeFrame(WebSocketFrame webSocketFrame);

    @Override // io.vertx.core.http.WebSocketBase
    WebSocket writeFrame(WebSocketFrame webSocketFrame, Handler<AsyncResult<Void>> handler);

    @Override // io.vertx.core.http.WebSocketBase
    WebSocket writeFinalTextFrame(String str);

    @Override // io.vertx.core.http.WebSocketBase
    WebSocket writeFinalTextFrame(String str, Handler<AsyncResult<Void>> handler);

    @Override // io.vertx.core.http.WebSocketBase
    WebSocket writeFinalBinaryFrame(Buffer buffer);

    @Override // io.vertx.core.http.WebSocketBase
    WebSocket writeFinalBinaryFrame(Buffer buffer, Handler<AsyncResult<Void>> handler);

    @Override // io.vertx.core.http.WebSocketBase
    WebSocket writeBinaryMessage(Buffer buffer);

    @Override // io.vertx.core.http.WebSocketBase
    WebSocket writeBinaryMessage(Buffer buffer, Handler<AsyncResult<Void>> handler);

    @Override // io.vertx.core.http.WebSocketBase
    WebSocket writeTextMessage(String str);

    @Override // io.vertx.core.http.WebSocketBase
    WebSocket writeTextMessage(String str, Handler<AsyncResult<Void>> handler);

    @Override // io.vertx.core.http.WebSocketBase
    WebSocket closeHandler(Handler<Void> handler);

    @Override // io.vertx.core.http.WebSocketBase
    WebSocket frameHandler(Handler<WebSocketFrame> handler);

    @Override // io.vertx.core.http.WebSocketBase
    /* bridge */ /* synthetic */ default WebSocketBase frameHandler(Handler handler) {
        return frameHandler((Handler<WebSocketFrame>) handler);
    }

    @Override // io.vertx.core.http.WebSocketBase
    /* bridge */ /* synthetic */ default WebSocketBase closeHandler(Handler handler) {
        return closeHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.http.WebSocketBase
    /* bridge */ /* synthetic */ default WebSocketBase writeTextMessage(String str, Handler handler) {
        return writeTextMessage(str, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.http.WebSocketBase
    /* bridge */ /* synthetic */ default WebSocketBase writeBinaryMessage(Buffer buffer, Handler handler) {
        return writeBinaryMessage(buffer, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.http.WebSocketBase
    /* bridge */ /* synthetic */ default WebSocketBase writeFinalBinaryFrame(Buffer buffer, Handler handler) {
        return writeFinalBinaryFrame(buffer, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.http.WebSocketBase
    /* bridge */ /* synthetic */ default WebSocketBase writeFinalTextFrame(String str, Handler handler) {
        return writeFinalTextFrame(str, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.http.WebSocketBase
    /* bridge */ /* synthetic */ default WebSocketBase writeFrame(WebSocketFrame webSocketFrame, Handler handler) {
        return writeFrame(webSocketFrame, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.WriteStream
    /* bridge */ /* synthetic */ default WebSocketBase drainHandler(Handler handler) {
        return drainHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.http.WebSocketBase
    /* bridge */ /* synthetic */ default WebSocketBase write(Buffer buffer, Handler handler) {
        return write(buffer, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream
    /* renamed from: endHandler, reason: avoid collision after fix types in other method */
    /* bridge */ /* synthetic */ default ReadStream<Buffer> endHandler2(Handler handler) {
        return endHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    /* bridge */ /* synthetic */ default WebSocketBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream
    /* bridge */ /* synthetic */ default ReadStream<Buffer> endHandler(Handler handler) {
        return endHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    /* bridge */ /* synthetic */ default ReadStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    /* bridge */ /* synthetic */ default StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.WriteStream
    /* bridge */ /* synthetic */ default WriteStream drainHandler(Handler handler) {
        return drainHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.WriteStream
    /* bridge */ /* synthetic */ default WriteStream write(Object obj, Handler handler) {
        return write((Buffer) obj, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    /* bridge */ /* synthetic */ default WriteStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }
}
