package io.vertx.core.eventbus.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.eventbus.ReplyFailure;
import io.vertx.core.http.CaseInsensitiveHeaders;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import java.util.List;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/MessageImpl.class */
public class MessageImpl<U, V> implements Message<V> {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) MessageImpl.class);
    protected MessageCodec<U, V> messageCodec;
    protected EventBusImpl bus;
    protected String address;
    protected String replyAddress;
    protected MultiMap headers;
    protected U sentBody;
    protected V receivedBody;
    protected boolean send;
    protected Handler<AsyncResult<Void>> writeHandler;

    public MessageImpl() {
    }

    public MessageImpl(String address, String replyAddress, MultiMap headers, U sentBody, MessageCodec<U, V> messageCodec, boolean send, EventBusImpl bus, Handler<AsyncResult<Void>> writeHandler) {
        this.messageCodec = messageCodec;
        this.address = address;
        this.replyAddress = replyAddress;
        this.headers = headers;
        this.sentBody = sentBody;
        this.send = send;
        this.bus = bus;
        this.writeHandler = writeHandler;
    }

    protected MessageImpl(MessageImpl<U, V> other) {
        this.bus = other.bus;
        this.address = other.address;
        this.replyAddress = other.replyAddress;
        this.messageCodec = other.messageCodec;
        if (other.headers != null) {
            List<Map.Entry<String, String>> entries = other.headers.entries();
            this.headers = new CaseInsensitiveHeaders();
            for (Map.Entry<String, String> entry : entries) {
                this.headers.add(entry.getKey(), entry.getValue());
            }
        }
        if (other.sentBody != null) {
            this.sentBody = other.sentBody;
            this.receivedBody = this.messageCodec.transform(other.sentBody);
        }
        this.send = other.send;
        this.writeHandler = other.writeHandler;
    }

    public MessageImpl<U, V> copyBeforeReceive() {
        return new MessageImpl<>(this);
    }

    @Override // io.vertx.core.eventbus.Message
    public String address() {
        return this.address;
    }

    @Override // io.vertx.core.eventbus.Message
    public MultiMap headers() {
        if (this.headers == null) {
            this.headers = new CaseInsensitiveHeaders();
        }
        return this.headers;
    }

    @Override // io.vertx.core.eventbus.Message
    public V body() {
        if (this.receivedBody == null && this.sentBody != null) {
            this.receivedBody = this.messageCodec.transform(this.sentBody);
        }
        return this.receivedBody;
    }

    @Override // io.vertx.core.eventbus.Message
    public String replyAddress() {
        return this.replyAddress;
    }

    @Override // io.vertx.core.eventbus.Message
    public void fail(int failureCode, String message) {
        if (this.replyAddress != null) {
            sendReply(this.bus.createMessage(true, this.replyAddress, null, new ReplyException(ReplyFailure.RECIPIENT_FAILURE, failureCode, message), null, null), null, null);
        }
    }

    @Override // io.vertx.core.eventbus.Message
    public void reply(Object message) {
        reply(message, new DeliveryOptions(), null);
    }

    @Override // io.vertx.core.eventbus.Message
    public <R> void reply(Object message, Handler<AsyncResult<Message<R>>> replyHandler) {
        reply(message, new DeliveryOptions(), replyHandler);
    }

    @Override // io.vertx.core.eventbus.Message
    public void reply(Object message, DeliveryOptions options) {
        reply(message, options, null);
    }

    @Override // io.vertx.core.eventbus.Message
    public <R> void reply(Object message, DeliveryOptions options, Handler<AsyncResult<Message<R>>> replyHandler) {
        if (this.replyAddress != null) {
            sendReply(this.bus.createMessage(true, this.replyAddress, options.getHeaders(), message, options.getCodecName(), null), options, replyHandler);
        }
    }

    @Override // io.vertx.core.eventbus.Message
    public boolean isSend() {
        return this.send;
    }

    public void setReplyAddress(String replyAddress) {
        this.replyAddress = replyAddress;
    }

    public Handler<AsyncResult<Void>> writeHandler() {
        return this.writeHandler;
    }

    public MessageCodec<U, V> codec() {
        return this.messageCodec;
    }

    public void setBus(EventBusImpl bus) {
        this.bus = bus;
    }

    protected <R> void sendReply(MessageImpl msg, DeliveryOptions options, Handler<AsyncResult<Message<R>>> replyHandler) {
        if (this.bus != null) {
            this.bus.sendReply(msg, this, options, replyHandler);
        }
    }

    protected boolean isLocal() {
        return true;
    }
}
