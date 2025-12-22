package io.vertx.ext.web.handler.sockjs.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.bridge.BridgeEventType;
import io.vertx.ext.web.handler.sockjs.BridgeEvent;
import io.vertx.ext.web.handler.sockjs.SockJSSocket;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/BridgeEventImpl.class */
class BridgeEventImpl implements BridgeEvent {
    private final BridgeEventType type;
    private final JsonObject rawMessage;
    private final SockJSSocket socket;
    private final Promise<Boolean> promise = Promise.promise();

    public BridgeEventImpl(BridgeEventType type, JsonObject rawMessage, SockJSSocket socket) {
        this.type = type;
        this.rawMessage = rawMessage;
        this.socket = socket;
    }

    @Override // io.vertx.core.Promise
    public Future<Boolean> future() {
        return this.promise.future();
    }

    @Override // io.vertx.ext.bridge.BaseBridgeEvent
    public BridgeEventType type() {
        return this.type;
    }

    @Override // io.vertx.ext.bridge.BaseBridgeEvent
    public JsonObject getRawMessage() {
        return this.rawMessage;
    }

    @Override // io.vertx.ext.web.handler.sockjs.BridgeEvent, io.vertx.ext.bridge.BaseBridgeEvent
    public BridgeEvent setRawMessage(JsonObject message) {
        if (message != this.rawMessage) {
            this.rawMessage.clear().mergeIn(message);
        }
        return this;
    }

    @Override // io.vertx.core.Promise, io.vertx.core.Handler
    public void handle(AsyncResult<Boolean> asyncResult) {
        this.promise.handle(asyncResult);
    }

    @Override // io.vertx.ext.web.handler.sockjs.BridgeEvent
    public SockJSSocket socket() {
        return this.socket;
    }

    @Override // io.vertx.core.Promise, io.vertx.core.Future
    public void complete(Boolean result) {
        this.promise.complete(result);
    }

    @Override // io.vertx.core.Promise, io.vertx.core.Future
    public void complete() {
        this.promise.complete();
    }

    @Override // io.vertx.core.Promise, io.vertx.core.Future
    public void fail(Throwable throwable) {
        this.promise.fail(throwable);
    }

    @Override // io.vertx.core.Promise, io.vertx.core.Future
    public void fail(String failureMessage) {
        this.promise.fail(failureMessage);
    }

    @Override // io.vertx.core.Promise, io.vertx.core.Future
    public boolean tryComplete(Boolean result) {
        return this.promise.tryComplete(result);
    }

    @Override // io.vertx.core.Promise, io.vertx.core.Future
    public boolean tryComplete() {
        return this.promise.tryComplete();
    }

    @Override // io.vertx.core.Promise, io.vertx.core.Future
    public boolean tryFail(Throwable cause) {
        return this.promise.tryFail(cause);
    }

    @Override // io.vertx.core.Promise, io.vertx.core.Future
    public boolean tryFail(String failureMessage) {
        return this.promise.tryFail(failureMessage);
    }
}
