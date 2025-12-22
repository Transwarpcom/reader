package io.vertx.core.http.impl;

import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http2.Http2Stream;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GenericFutureListener;
import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.http.StreamResetException;
import io.vertx.core.http.impl.Http2ConnectionBase;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.WriteStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.security.cert.X509Certificate;
import org.apache.pdfbox.pdmodel.common.PDPageLabelRange;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/VertxHttp2NetSocket.class */
class VertxHttp2NetSocket<C extends Http2ConnectionBase> extends VertxHttp2Stream<C> implements NetSocket {
    private Handler<Throwable> exceptionHandler;
    private Handler<Void> closeHandler;
    private Handler<Void> endHandler;
    private Handler<Buffer> dataHandler;
    private Handler<Void> drainHandler;

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.ReadStream
    /* renamed from: endHandler, reason: avoid collision after fix types in other method */
    public /* bridge */ /* synthetic */ ReadStream<Buffer> endHandler2(Handler handler) {
        return endHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    public /* bridge */ /* synthetic */ ReadStream handler2(Handler handler) {
        return handler2((Handler<Buffer>) handler);
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ ReadStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.WriteStream
    public /* bridge */ /* synthetic */ WriteStream drainHandler(Handler handler) {
        return drainHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.WriteStream
    public /* bridge */ /* synthetic */ WriteStream write(Object obj, Handler handler) {
        return write((Buffer) obj, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ WriteStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    public VertxHttp2NetSocket(C conn, Http2Stream stream, boolean writable) {
        super(conn, stream, writable);
    }

    @Override // io.vertx.core.http.impl.VertxHttp2Stream
    void handleEnd(MultiMap trailers) {
        try {
            Handler<Void> handler = endHandler();
            if (handler != null) {
                handler.handle(null);
            }
        } finally {
            end();
        }
    }

    @Override // io.vertx.core.http.impl.VertxHttp2Stream
    void handleData(Buffer buf) {
        Handler<Buffer> handler = handler();
        if (handler != null) {
            handler.handle(buf);
        }
    }

    @Override // io.vertx.core.http.impl.VertxHttp2Stream
    void handleReset(long errorCode) {
        handleException(new StreamResetException(errorCode));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.vertx.core.http.impl.VertxHttp2Stream
    public void handleException(Throwable cause) {
        Handler<Throwable> handler = exceptionHandler();
        if (handler != null) {
            handler.handle(cause);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.vertx.core.http.impl.VertxHttp2Stream
    public void handleClose() {
        super.handleClose();
        Handler<Void> handler = closeHandler();
        if (handler != null) {
            handler.handle(null);
        }
    }

    @Override // io.vertx.core.http.impl.VertxHttp2Stream
    void handleInterestedOpsChanged() {
        Handler<Void> handler = drainHandler();
        if (handler != null && !writeQueueFull()) {
            handler.handle(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.vertx.core.http.impl.VertxHttp2Stream
    public void handlePriorityChange(StreamPriority streamPriority) {
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public NetSocket exceptionHandler(Handler<Throwable> handler) {
        synchronized (this.conn) {
            this.exceptionHandler = handler;
        }
        return this;
    }

    Handler<Throwable> exceptionHandler() {
        Handler<Throwable> handler;
        synchronized (this.conn) {
            handler = this.exceptionHandler;
        }
        return handler;
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    public NetSocket handler2(Handler<Buffer> handler) {
        synchronized (this.conn) {
            this.dataHandler = handler;
        }
        return this;
    }

    Handler<Buffer> handler() {
        Handler<Buffer> handler;
        synchronized (this.conn) {
            handler = this.dataHandler;
        }
        return handler;
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    public ReadStream<Buffer> fetch2(long amount) {
        doFetch(amount);
        return this;
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    public NetSocket pause2() {
        doPause();
        return this;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [io.vertx.core.net.NetSocket] */
    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    public NetSocket resume2() {
        return fetch2(Long.MAX_VALUE);
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.ReadStream
    public ReadStream<Buffer> endHandler(Handler<Void> handler) {
        synchronized (this.conn) {
            this.endHandler = handler;
        }
        return this;
    }

    Handler<Void> endHandler() {
        Handler<Void> handler;
        synchronized (this.conn) {
            handler = this.endHandler;
        }
        return handler;
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.WriteStream
    public NetSocket write(Buffer data) {
        synchronized (this.conn) {
            writeData(data.getByteBuf(), false);
        }
        return this;
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.WriteStream
    /* renamed from: setWriteQueueMaxSize */
    public NetSocket setWriteQueueMaxSize2(int maxSize) {
        return this;
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.WriteStream
    public NetSocket drainHandler(Handler<Void> handler) {
        synchronized (this.conn) {
            this.drainHandler = handler;
        }
        return this;
    }

    Handler<Void> drainHandler() {
        Handler<Void> handler;
        synchronized (this.conn) {
            handler = this.drainHandler;
        }
        return handler;
    }

    @Override // io.vertx.core.streams.WriteStream
    public boolean writeQueueFull() {
        return isNotWritable();
    }

    @Override // io.vertx.core.net.NetSocket
    public String writeHandlerID() {
        return null;
    }

    @Override // io.vertx.core.net.NetSocket
    public NetSocket write(String str) {
        return write(str, null, null);
    }

    @Override // io.vertx.core.net.NetSocket
    public NetSocket write(String str, Handler<AsyncResult<Void>> handler) {
        return write(str, null, handler);
    }

    @Override // io.vertx.core.net.NetSocket
    public NetSocket write(String str, String enc) {
        return write(str, enc, null);
    }

    @Override // io.vertx.core.net.NetSocket
    public NetSocket write(String str, String enc, Handler<AsyncResult<Void>> handler) {
        Charset cs = enc != null ? Charset.forName(enc) : CharsetUtil.UTF_8;
        writeData(Unpooled.copiedBuffer(str, cs), false, handler);
        return this;
    }

    @Override // io.vertx.core.net.NetSocket
    public NetSocket write(Buffer message, Handler<AsyncResult<Void>> handler) {
        this.conn.handler.writeData(this.stream, message.getByteBuf(), false, handler);
        return this;
    }

    @Override // io.vertx.core.net.NetSocket
    public NetSocket sendFile(String filename, long offset, long length) {
        return sendFile(filename, offset, length, null);
    }

    @Override // io.vertx.core.net.NetSocket
    public NetSocket sendFile(String filename, long offset, long length, Handler<AsyncResult<Void>> resultHandler) {
        synchronized (this.conn) {
            Context resultCtx = resultHandler != null ? this.vertx.getOrCreateContext() : null;
            File file = this.vertx.resolveFile(filename);
            if (!file.exists()) {
                if (resultHandler != null) {
                    resultCtx.runOnContext(v -> {
                        resultHandler.handle(Future.failedFuture(new FileNotFoundException()));
                    });
                }
                return this;
            }
            try {
                RandomAccessFile raf = new RandomAccessFile(file, PDPageLabelRange.STYLE_ROMAN_LOWER);
                long contentLength = Math.min(length, file.length() - offset);
                Promise<Long> result = Promise.promise();
                result.future().setHandler2(ar -> {
                    if (resultHandler != null) {
                        resultCtx.runOnContext(v2 -> {
                            resultHandler.handle(Future.succeededFuture());
                        });
                    }
                });
                FileStreamChannel fileChannel = new FileStreamChannel(result, this, offset, contentLength);
                drainHandler(fileChannel.drainHandler);
                this.handlerContext.channel().eventLoop().register(fileChannel).addListener2((GenericFutureListener<? extends io.netty.util.concurrent.Future<? super Void>>) future -> {
                    if (future.isSuccess()) {
                        fileChannel.pipeline().fireUserEventTriggered((Object) raf);
                    } else {
                        result.tryFail(future.cause());
                    }
                });
                return this;
            } catch (IOException e) {
                if (resultHandler != null) {
                    resultCtx.runOnContext(v2 -> {
                        resultHandler.handle(Future.failedFuture(e));
                    });
                }
                return this;
            }
        }
    }

    @Override // io.vertx.core.net.NetSocket
    public SocketAddress remoteAddress() {
        return this.conn.remoteAddress();
    }

    @Override // io.vertx.core.net.NetSocket
    public SocketAddress localAddress() {
        return this.conn.localAddress();
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.WriteStream
    public void end() {
        writeData(Unpooled.EMPTY_BUFFER, true);
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.WriteStream
    public void end(Handler<AsyncResult<Void>> handler) {
        writeData(Unpooled.EMPTY_BUFFER, true, handler);
    }

    @Override // io.vertx.core.streams.WriteStream
    public void end(Buffer buffer) {
        writeData(buffer.getByteBuf(), true);
    }

    @Override // io.vertx.core.net.NetSocket
    public void close() {
        end();
    }

    @Override // io.vertx.core.net.NetSocket
    public void close(Handler<AsyncResult<Void>> handler) {
        end(handler);
    }

    @Override // io.vertx.core.net.NetSocket
    /* renamed from: closeHandler */
    public NetSocket mo1996closeHandler(Handler<Void> handler) {
        synchronized (this.conn) {
            this.closeHandler = handler;
        }
        return this;
    }

    Handler<Void> closeHandler() {
        Handler<Void> handler;
        synchronized (this.conn) {
            handler = this.closeHandler;
        }
        return handler;
    }

    @Override // io.vertx.core.net.NetSocket
    public NetSocket upgradeToSsl(Handler<Void> handler) {
        throw new UnsupportedOperationException("Cannot upgrade HTTP/2 stream to SSL");
    }

    @Override // io.vertx.core.net.NetSocket
    public NetSocket upgradeToSsl(String serverName, Handler<Void> handler) {
        throw new UnsupportedOperationException("Cannot upgrade HTTP/2 stream to SSL");
    }

    @Override // io.vertx.core.net.NetSocket
    public boolean isSsl() {
        return this.conn.isSsl();
    }

    @Override // io.vertx.core.net.NetSocket
    public SSLSession sslSession() {
        return this.conn.sslSession();
    }

    @Override // io.vertx.core.net.NetSocket
    public X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException {
        return this.conn.peerCertificateChain();
    }

    @Override // io.vertx.core.net.NetSocket
    public String indicatedServerName() {
        return this.conn.indicatedServerName();
    }
}
