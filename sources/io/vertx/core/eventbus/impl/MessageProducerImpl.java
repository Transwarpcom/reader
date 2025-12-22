package io.vertx.core.eventbus.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.eventbus.MessageProducer;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.WriteStream;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.UUID;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/MessageProducerImpl.class */
public class MessageProducerImpl<T> implements MessageProducer<T> {
    public static final String CREDIT_ADDRESS_HEADER_NAME = "__vertx.credit";
    private final Vertx vertx;
    private final EventBusImpl bus;
    private final boolean send;
    private final String address;
    private final MessageConsumer<Integer> creditConsumer;
    private DeliveryOptions options;
    private Handler<Void> drainHandler;
    private final Queue<MessageImpl<T, ?>> pending = new ArrayDeque();
    private int maxSize = 1000;
    private int credits = 1000;

    @Override // io.vertx.core.eventbus.MessageProducer, io.vertx.core.streams.WriteStream
    public /* bridge */ /* synthetic */ WriteStream drainHandler(Handler handler) {
        return drainHandler((Handler<Void>) handler);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.vertx.core.eventbus.MessageProducer, io.vertx.core.streams.WriteStream
    public /* bridge */ /* synthetic */ WriteStream write(Object obj, Handler handler) {
        return write((MessageProducerImpl<T>) obj, (Handler<AsyncResult<Void>>) handler);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.vertx.core.eventbus.MessageProducer, io.vertx.core.streams.WriteStream
    public /* bridge */ /* synthetic */ WriteStream write(Object obj) {
        return write((MessageProducerImpl<T>) obj);
    }

    @Override // io.vertx.core.eventbus.MessageProducer, io.vertx.core.streams.WriteStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ WriteStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.eventbus.MessageProducer, io.vertx.core.streams.WriteStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    public MessageProducerImpl(Vertx vertx, String address, boolean send, DeliveryOptions options) {
        this.vertx = vertx;
        this.bus = (EventBusImpl) vertx.eventBus();
        this.address = address;
        this.send = send;
        this.options = options;
        if (send) {
            String creditAddress = UUID.randomUUID().toString() + "-credit";
            this.creditConsumer = this.bus.consumer(creditAddress, msg -> {
                doReceiveCredit(((Integer) msg.body()).intValue());
            });
            options.addHeader(CREDIT_ADDRESS_HEADER_NAME, creditAddress);
            return;
        }
        this.creditConsumer = null;
    }

    @Override // io.vertx.core.eventbus.MessageProducer
    public synchronized MessageProducer<T> deliveryOptions(DeliveryOptions options) {
        if (this.creditConsumer != null) {
            options = new DeliveryOptions(options);
            options.addHeader(CREDIT_ADDRESS_HEADER_NAME, this.options.getHeaders().get(CREDIT_ADDRESS_HEADER_NAME));
        }
        this.options = options;
        return this;
    }

    @Override // io.vertx.core.eventbus.MessageProducer
    public MessageProducer<T> send(T message) {
        doSend(message, null, null);
        return this;
    }

    @Override // io.vertx.core.eventbus.MessageProducer
    public <R> MessageProducer<T> send(T message, Handler<AsyncResult<Message<R>>> replyHandler) {
        doSend(message, replyHandler, null);
        return this;
    }

    @Override // io.vertx.core.eventbus.MessageProducer, io.vertx.core.streams.WriteStream, io.vertx.core.streams.StreamBase
    public MessageProducer<T> exceptionHandler(Handler<Throwable> handler) {
        return this;
    }

    @Override // io.vertx.core.eventbus.MessageProducer, io.vertx.core.streams.WriteStream
    /* renamed from: setWriteQueueMaxSize */
    public synchronized MessageProducer<T> setWriteQueueMaxSize2(int s) {
        int delta = s - this.maxSize;
        this.maxSize = s;
        this.credits += delta;
        return this;
    }

    @Override // io.vertx.core.eventbus.MessageProducer, io.vertx.core.streams.WriteStream
    public synchronized MessageProducer<T> write(T data) {
        return write((MessageProducerImpl<T>) data, (Handler<AsyncResult<Void>>) null);
    }

    @Override // io.vertx.core.eventbus.MessageProducer, io.vertx.core.streams.WriteStream
    public MessageProducer<T> write(T data, Handler<AsyncResult<Void>> handler) {
        if (this.send) {
            doSend(data, null, handler);
        } else {
            MessageImpl msg = this.bus.createMessage(false, this.address, this.options.getHeaders(), data, this.options.getCodecName(), handler);
            msg.writeHandler = handler;
            this.bus.sendOrPubInternal(msg, this.options, null);
        }
        return this;
    }

    @Override // io.vertx.core.streams.WriteStream
    public synchronized boolean writeQueueFull() {
        return this.credits == 0;
    }

    @Override // io.vertx.core.eventbus.MessageProducer, io.vertx.core.streams.WriteStream
    public synchronized MessageProducer<T> drainHandler(Handler<Void> handler) {
        this.drainHandler = handler;
        if (handler != null) {
            checkDrained();
        }
        return this;
    }

    private void checkDrained() {
        Handler<Void> handler = this.drainHandler;
        if (handler != null && this.credits >= this.maxSize / 2) {
            this.drainHandler = null;
            this.vertx.runOnContext(v -> {
                handler.handle(null);
            });
        }
    }

    @Override // io.vertx.core.eventbus.MessageProducer
    public String address() {
        return this.address;
    }

    @Override // io.vertx.core.eventbus.MessageProducer, io.vertx.core.streams.WriteStream
    public void end() {
        close();
    }

    @Override // io.vertx.core.eventbus.MessageProducer, io.vertx.core.streams.WriteStream
    public void end(Handler<AsyncResult<Void>> handler) {
        close(null);
    }

    @Override // io.vertx.core.eventbus.MessageProducer
    public void close() {
        close(null);
    }

    @Override // io.vertx.core.eventbus.MessageProducer
    public void close(Handler<AsyncResult<Void>> handler) {
        if (this.creditConsumer != null) {
            this.creditConsumer.unregister(handler);
        } else {
            this.vertx.runOnContext(v -> {
                if (handler != null) {
                    handler.handle(Future.succeededFuture());
                }
            });
        }
    }

    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    private synchronized <R> void doSend(T data, Handler<AsyncResult<Message<R>>> replyHandler, Handler<AsyncResult<Void>> handler) {
        MessageImpl msg = this.bus.createMessage(true, this.address, this.options.getHeaders(), data, this.options.getCodecName(), handler);
        if (this.credits > 0) {
            this.credits--;
            this.bus.sendOrPubInternal(msg, this.options, replyHandler);
        } else {
            this.pending.add(msg);
        }
    }

    private synchronized void doReceiveCredit(int credit) {
        MessageImpl<T, ?> msg;
        this.credits += credit;
        while (this.credits > 0 && (msg = this.pending.poll()) != null) {
            this.credits--;
            this.bus.sendOrPubInternal(msg, this.options, null);
        }
        checkDrained();
    }
}
