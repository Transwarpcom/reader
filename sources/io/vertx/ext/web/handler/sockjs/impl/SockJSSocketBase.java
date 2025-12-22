package io.vertx.ext.web.handler.sockjs.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.WriteStream;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import java.util.UUID;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/SockJSSocketBase.class */
public abstract class SockJSSocketBase implements SockJSSocket {
    private final MessageConsumer<Buffer> registration;
    protected final Vertx vertx;
    protected Session webSession;
    protected User webUser;
    private final String writeHandlerID;

    @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public abstract SockJSSocket exceptionHandler(Handler<Throwable> handler);

    @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ ReadStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ WriteStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    protected SockJSSocketBase(Vertx vertx, Session webSession, User webUser) {
        this.vertx = vertx;
        this.webSession = webSession;
        this.webUser = webUser;
        Handler<Message<Buffer>> writeHandler = buff -> {
            write((Buffer) buff.body());
        };
        this.writeHandlerID = UUID.randomUUID().toString();
        this.registration = vertx.eventBus().consumer(this.writeHandlerID).handler2((Handler) writeHandler);
    }

    @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket
    public String writeHandlerID() {
        return this.writeHandlerID;
    }

    @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket, io.vertx.core.streams.WriteStream
    public void end() {
        this.registration.unregister();
    }

    @Override // io.vertx.core.streams.WriteStream
    public void end(Handler<AsyncResult<Void>> handler) {
        this.registration.unregister(handler);
    }

    @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket
    public void close() {
        end();
    }

    public void closeAfterSessionExpired() {
        close();
    }

    @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket
    public Session webSession() {
        return this.webSession;
    }

    @Override // io.vertx.ext.web.handler.sockjs.SockJSSocket
    public User webUser() {
        return this.webUser;
    }
}
