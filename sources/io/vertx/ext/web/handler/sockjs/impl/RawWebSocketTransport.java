package io.vertx.ext.web.handler.sockjs.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.net.impl.ConnectionBase;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.WriteStream;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/RawWebSocketTransport.class */
class RawWebSocketTransport {

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/RawWebSocketTransport$RawWSSockJSSocket.class */
    private static class RawWSSockJSSocket extends SockJSSocketBase {
        final ServerWebSocket ws;
        MultiMap headers;
        boolean closed;

        @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.ReadStream
        /* renamed from: endHandler, reason: avoid collision after fix types in other method */
        public /* bridge */ /* synthetic */ ReadStream<Buffer> endHandler2(Handler handler) {
            return endHandler((Handler<Void>) handler);
        }

        @Override // io.vertx.ext.web.handler.sockjs.impl.SockJSSocketBase, io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
        public /* bridge */ /* synthetic */ ReadStream exceptionHandler(Handler handler) {
            return exceptionHandler((Handler<Throwable>) handler);
        }

        @Override // io.vertx.ext.web.handler.sockjs.impl.SockJSSocketBase, io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
        public /* bridge */ /* synthetic */ StreamBase exceptionHandler(Handler handler) {
            return exceptionHandler((Handler<Throwable>) handler);
        }

        @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.WriteStream
        /* renamed from: drainHandler, reason: avoid collision after fix types in other method */
        public /* bridge */ /* synthetic */ WriteStream<Buffer> drainHandler2(Handler handler) {
            return drainHandler((Handler<Void>) handler);
        }

        @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.WriteStream
        public /* bridge */ /* synthetic */ WriteStream<Buffer> write(Buffer buffer, Handler handler) {
            return write(buffer, (Handler<AsyncResult<Void>>) handler);
        }

        @Override // io.vertx.ext.web.handler.sockjs.impl.SockJSSocketBase, io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
        public /* bridge */ /* synthetic */ WriteStream exceptionHandler(Handler handler) {
            return exceptionHandler((Handler<Throwable>) handler);
        }

        RawWSSockJSSocket(Vertx vertx, Session webSession, User webUser, ServerWebSocket ws) {
            super(vertx, webSession, webUser);
            this.ws = ws;
            ws.closeHandler(v -> {
                synchronized (this) {
                    this.closed = true;
                }
                super.close();
            });
        }

        @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.ReadStream
        /* renamed from: handler */
        public ReadStream<Buffer> handler2(Handler<Buffer> handler) {
            this.ws.binaryMessageHandler(handler);
            this.ws.textMessageHandler(textMessage -> {
                handler.handle(Buffer.buffer(textMessage));
            });
            return this;
        }

        @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.ReadStream
        /* renamed from: pause */
        public ReadStream<Buffer> pause2() {
            this.ws.pause2();
            return this;
        }

        @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.ReadStream
        /* renamed from: resume */
        public ReadStream<Buffer> resume2() {
            this.ws.resume2();
            return this;
        }

        @Override // io.vertx.core.streams.ReadStream
        /* renamed from: fetch */
        public ReadStream<Buffer> fetch2(long amount) {
            this.ws.fetch2(amount);
            return this;
        }

        private synchronized boolean canWrite(Handler<AsyncResult<Void>> handler) {
            if (this.closed) {
                if (handler != null) {
                    this.vertx.runOnContext(v -> {
                        handler.handle(Future.failedFuture(ConnectionBase.CLOSED_EXCEPTION));
                    });
                    return false;
                }
                return false;
            }
            return true;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket
        public SockJSSocket write(Buffer data, Handler<AsyncResult<Void>> handler) {
            if (canWrite(handler)) {
                this.ws.writeBinaryMessage(data, handler);
            }
            return this;
        }

        @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket
        public SockJSSocket write(String data, Handler<AsyncResult<Void>> handler) {
            if (canWrite(handler)) {
                this.ws.writeTextMessage(data, handler);
            }
            return this;
        }

        @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.WriteStream
        /* renamed from: setWriteQueueMaxSize */
        public WriteStream<Buffer> setWriteQueueMaxSize2(int maxQueueSize) {
            this.ws.setWriteQueueMaxSize2(maxQueueSize);
            return this;
        }

        @Override // io.vertx.core.streams.WriteStream
        public boolean writeQueueFull() {
            return this.ws.writeQueueFull();
        }

        @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.WriteStream
        public WriteStream<Buffer> drainHandler(Handler<Void> handler) {
            this.ws.drainHandler(handler);
            return this;
        }

        @Override // io.vertx.ext.web.handler.sockjs.impl.SockJSSocketBase, io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
        public SockJSSocket exceptionHandler(Handler<Throwable> handler) {
            this.ws.exceptionHandler(handler);
            return this;
        }

        @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.ReadStream
        public ReadStream<Buffer> endHandler(Handler<Void> endHandler) {
            this.ws.endHandler(endHandler);
            return this;
        }

        @Override // io.vertx.ext.web.handler.sockjs.impl.SockJSSocketBase, io.vertx.ext.web.handler.sockjs.SockJSSocket
        public void close() {
            synchronized (this) {
                if (this.closed) {
                    return;
                }
                this.closed = true;
                super.close();
                this.ws.close();
            }
        }

        @Override // io.vertx.ext.web.handler.sockjs.impl.SockJSSocketBase
        public void closeAfterSessionExpired() {
            close(1001, "Session expired");
        }

        @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket
        public void close(int statusCode, String reason) {
            synchronized (this) {
                if (this.closed) {
                    return;
                }
                this.closed = true;
                super.close();
                this.ws.close((short) statusCode, reason);
            }
        }

        @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket
        public SocketAddress remoteAddress() {
            return this.ws.remoteAddress();
        }

        @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket
        public SocketAddress localAddress() {
            return this.ws.localAddress();
        }

        @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket
        public MultiMap headers() {
            if (this.headers == null) {
                this.headers = BaseTransport.removeCookieHeaders(this.ws.headers());
            }
            return this.headers;
        }

        @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket
        public String uri() {
            return this.ws.uri();
        }
    }

    RawWebSocketTransport(Vertx vertx, Router router, Handler<SockJSSocket> sockHandler) {
        router.get("/websocket").handler(rc -> {
            ServerWebSocket ws = rc.request().upgrade();
            sockHandler.handle(new RawWSSockJSSocket(vertx, rc.session(), rc.user(), ws));
        });
        router.get("/websocket").handler(rc2 -> {
            rc2.response().setStatusCode(OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL).end("Can \"Upgrade\" only to \"WebSocket\".");
        });
        router.get("/websocket").handler(rc3 -> {
            rc3.response().putHeader("Allow", "GET").setStatusCode(405).end();
        });
    }
}
