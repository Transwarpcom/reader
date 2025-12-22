package io.vertx.core.http.impl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.http.WebSocketBase;
import io.vertx.core.http.WebSocketFrame;
import io.vertx.core.streams.ReadStream;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.security.cert.X509Certificate;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/ServerWebSocketImpl.class */
public class ServerWebSocketImpl extends WebSocketImplBase<ServerWebSocketImpl> implements ServerWebSocket {
    private final Http1xServerConnection conn;
    private final String uri;
    private final String path;
    private final String query;
    private final WebSocketServerHandshaker handshaker;
    private HttpServerRequestImpl request;
    private Integer status;
    private Promise<Integer> handshakePromise;

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ WebSocketBase writeFrame(WebSocketFrame webSocketFrame, Handler handler) {
        return writeFrame(webSocketFrame, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ ServerWebSocket frameHandler(Handler handler) {
        return (ServerWebSocket) super.frameHandler((Handler<WebSocketFrame>) handler);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ ServerWebSocket closeHandler(Handler handler) {
        return (ServerWebSocket) super.closeHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ ServerWebSocket writeTextMessage(String str, Handler handler) {
        return (ServerWebSocket) super.writeTextMessage(str, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ ServerWebSocket writeTextMessage(String str) {
        return (ServerWebSocket) super.writeTextMessage(str);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ ServerWebSocket writeBinaryMessage(Buffer buffer, Handler handler) {
        return (ServerWebSocket) super.writeBinaryMessage(buffer, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ ServerWebSocket writeBinaryMessage(Buffer buffer) {
        return (ServerWebSocket) super.writeBinaryMessage(buffer);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ ServerWebSocket writeFinalBinaryFrame(Buffer buffer, Handler handler) {
        return (ServerWebSocket) super.writeFinalBinaryFrame(buffer, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ ServerWebSocket writeFinalBinaryFrame(Buffer buffer) {
        return (ServerWebSocket) super.writeFinalBinaryFrame(buffer);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ ServerWebSocket writeFinalTextFrame(String str, Handler handler) {
        return (ServerWebSocket) super.writeFinalTextFrame(str, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ ServerWebSocket writeFinalTextFrame(String str) {
        return (ServerWebSocket) super.writeFinalTextFrame(str);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ ServerWebSocket writeFrame(WebSocketFrame webSocketFrame, Handler handler) {
        return writeFrame(webSocketFrame, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ ServerWebSocket writeFrame(WebSocketFrame webSocketFrame) {
        return (ServerWebSocket) super.writeFrame(webSocketFrame);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase, io.vertx.core.streams.WriteStream
    public /* bridge */ /* synthetic */ ServerWebSocket drainHandler(Handler handler) {
        return (ServerWebSocket) super.drainHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase, io.vertx.core.streams.WriteStream
    /* renamed from: setWriteQueueMaxSize */
    public /* bridge */ /* synthetic */ ServerWebSocket setWriteQueueMaxSize2(int i) {
        return (ServerWebSocket) super.setWriteQueueMaxSize2(i);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ ServerWebSocket write(Buffer buffer, Handler handler) {
        return (ServerWebSocket) super.write(buffer, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public /* bridge */ /* synthetic */ ServerWebSocket write(Buffer buffer) {
        return (ServerWebSocket) super.write(buffer);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream
    public /* bridge */ /* synthetic */ ReadStream<Buffer> endHandler(Handler handler) {
        return (ServerWebSocket) super.endHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    public /* bridge */ /* synthetic */ ReadStream<Buffer> fetch2(long j) {
        return (ServerWebSocket) super.fetch2(j);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    public /* bridge */ /* synthetic */ ReadStream<Buffer> resume2() {
        return (ServerWebSocket) super.resume2();
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    public /* bridge */ /* synthetic */ ReadStream<Buffer> pause2() {
        return (ServerWebSocket) super.pause2();
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    public /* bridge */ /* synthetic */ ReadStream<Buffer> handler2(Handler<Buffer> handler) {
        return (ServerWebSocket) super.handler2(handler);
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ ServerWebSocket exceptionHandler(Handler handler) {
        return (ServerWebSocket) super.exceptionHandler((Handler<Throwable>) handler);
    }

    ServerWebSocketImpl(Http1xServerConnection conn, boolean supportsContinuation, HttpServerRequestImpl request, WebSocketServerHandshaker handshaker, int maxWebSocketFrameSize, int maxWebSocketMessageSize) {
        super(conn, supportsContinuation, maxWebSocketFrameSize, maxWebSocketMessageSize);
        this.conn = conn;
        this.uri = request.uri();
        this.path = request.path();
        this.query = request.query();
        this.request = request;
        this.handshaker = handshaker;
        headers(request.headers());
    }

    @Override // io.vertx.core.http.ServerWebSocket
    public String uri() {
        return this.uri;
    }

    @Override // io.vertx.core.http.ServerWebSocket
    public String path() {
        return this.path;
    }

    @Override // io.vertx.core.http.ServerWebSocket
    public String query() {
        return this.query;
    }

    @Override // io.vertx.core.http.ServerWebSocket
    public void accept() {
        if (tryHandshake(101) != Boolean.TRUE) {
            throw new IllegalStateException("WebSocket already rejected");
        }
    }

    @Override // io.vertx.core.http.ServerWebSocket
    public void reject() {
        reject(502);
    }

    @Override // io.vertx.core.http.ServerWebSocket
    public void reject(int sc) {
        if (sc == 101) {
            throw new IllegalArgumentException("Invalid WebSocket rejection status code: 101");
        }
        if (tryHandshake(sc) != Boolean.TRUE) {
            throw new IllegalStateException("Cannot reject WebSocket, it has already been written to");
        }
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public SSLSession sslSession() {
        return this.conn.sslSession();
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException {
        return this.conn.peerCertificateChain();
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public void close() {
        synchronized (this.conn) {
            checkClosed();
            if (this.status == null) {
                if (this.handshakePromise == null) {
                    tryHandshake(101);
                } else {
                    this.handshakePromise.tryComplete(101);
                }
            }
        }
        super.close();
    }

    @Override // io.vertx.core.http.impl.WebSocketImplBase, io.vertx.core.http.WebSocketBase
    public ServerWebSocketImpl writeFrame(WebSocketFrame frame, Handler<AsyncResult<Void>> handler) {
        ServerWebSocketImpl serverWebSocketImpl;
        synchronized (this.conn) {
            Boolean check = checkAccept();
            if (check == null) {
                throw new IllegalStateException("Cannot write to WebSocket, it is pending accept or reject");
            }
            if (!check.booleanValue()) {
                throw new IllegalStateException("Cannot write to WebSocket, it has been rejected");
            }
            serverWebSocketImpl = (ServerWebSocketImpl) super.writeFrame(frame, handler);
        }
        return serverWebSocketImpl;
    }

    private Boolean checkAccept() {
        return tryHandshake(101);
    }

    private void handleHandshake(int sc) {
        synchronized (this.conn) {
            if (this.status == null) {
                if (sc == 101) {
                    doHandshake();
                } else {
                    this.status = Integer.valueOf(sc);
                    HttpUtils.sendError(this.conn.channel(), HttpResponseStatus.valueOf(sc));
                }
            }
        }
    }

    private void doHandshake() {
        Channel channel = this.conn.channel();
        try {
            try {
                this.handshaker.handshake(channel, this.request.nettyRequest());
                this.request = null;
                this.conn.responseComplete();
                this.status = Integer.valueOf(HttpResponseStatus.SWITCHING_PROTOCOLS.code());
                subProtocol(this.handshaker.selectedSubprotocol());
                ChannelPipeline pipeline = channel.pipeline();
                ChannelHandler handler = pipeline.get((Class<ChannelHandler>) HttpChunkContentCompressor.class);
                if (handler != null) {
                    pipeline.remove(handler);
                }
                registerHandler(this.conn.getContext().owner().eventBus());
            } catch (Exception e) {
                this.request.response().setStatusCode(HttpResponseStatus.BAD_REQUEST.code()).end();
                throw e;
            }
        } catch (Throwable th) {
            this.request = null;
            throw th;
        }
    }

    Boolean tryHandshake(int sc) {
        Boolean boolValueOf;
        synchronized (this.conn) {
            if (this.status == null && this.handshakePromise == null) {
                setHandshake(Future.succeededFuture(Integer.valueOf(sc)));
            }
            if (this.status == null) {
                boolValueOf = null;
            } else {
                boolValueOf = Boolean.valueOf(this.status.intValue() == sc);
            }
        }
        return boolValueOf;
    }

    @Override // io.vertx.core.http.ServerWebSocket
    public void setHandshake(Future<Integer> future) {
        setHandshake(future, null);
    }

    @Override // io.vertx.core.http.ServerWebSocket
    public void setHandshake(Future<Integer> future, Handler<AsyncResult<Integer>> handler) {
        if (future == null) {
            throw new NullPointerException();
        }
        Promise<Integer> promise = Promise.promise();
        synchronized (this.conn) {
            if (this.handshakePromise != null) {
                throw new IllegalStateException();
            }
            this.handshakePromise = promise;
        }
        future.setHandler2(promise);
        promise.future().setHandler2(ar -> {
            if (ar.succeeded()) {
                handleHandshake(((Integer) ar.result()).intValue());
            } else {
                handleHandshake(500);
            }
            if (handler != null) {
                handler.handle(ar);
            }
        });
    }
}
