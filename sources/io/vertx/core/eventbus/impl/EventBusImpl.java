package io.vertx.core.eventbus.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Closeable;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.eventbus.DeliveryContext;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.eventbus.MessageProducer;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.eventbus.ReplyFailure;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.impl.utils.ConcurrentCyclicSequence;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.spi.metrics.EventBusMetrics;
import io.vertx.core.spi.metrics.MetricsProvider;
import io.vertx.core.spi.metrics.VertxMetrics;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/EventBusImpl.class */
public class EventBusImpl implements EventBus, MetricsProvider {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) EventBusImpl.class);
    protected final VertxInternal vertx;
    protected final EventBusMetrics metrics;
    protected volatile boolean started;
    private final List<Handler<DeliveryContext>> sendInterceptors = new CopyOnWriteArrayList();
    private final List<Handler<DeliveryContext>> receiveInterceptors = new CopyOnWriteArrayList();
    private final AtomicLong replySequence = new AtomicLong(0);
    protected final ConcurrentMap<String, ConcurrentCyclicSequence<HandlerHolder>> handlerMap = new ConcurrentHashMap();
    protected final CodecManager codecManager = new CodecManager();

    public EventBusImpl(VertxInternal vertx) {
        VertxMetrics metrics = vertx.metricsSPI();
        this.vertx = vertx;
        this.metrics = metrics != null ? metrics.createEventBusMetrics() : null;
    }

    @Override // io.vertx.core.eventbus.EventBus
    public <T> EventBus addOutboundInterceptor(Handler<DeliveryContext<T>> interceptor) {
        this.sendInterceptors.add(interceptor);
        return this;
    }

    @Override // io.vertx.core.eventbus.EventBus
    public <T> EventBus addInboundInterceptor(Handler<DeliveryContext<T>> interceptor) {
        this.receiveInterceptors.add(interceptor);
        return this;
    }

    @Override // io.vertx.core.eventbus.EventBus
    public <T> EventBus removeOutboundInterceptor(Handler<DeliveryContext<T>> interceptor) {
        this.sendInterceptors.remove(interceptor);
        return this;
    }

    @Override // io.vertx.core.eventbus.EventBus
    public <T> EventBus removeInboundInterceptor(Handler<DeliveryContext<T>> interceptor) {
        this.receiveInterceptors.remove(interceptor);
        return this;
    }

    @Override // io.vertx.core.eventbus.EventBus
    public synchronized void start(Handler<AsyncResult<Void>> completionHandler) {
        if (this.started) {
            throw new IllegalStateException("Already started");
        }
        this.started = true;
        completionHandler.handle(Future.succeededFuture());
    }

    @Override // io.vertx.core.eventbus.EventBus
    public EventBus send(String address, Object message) {
        return send(address, message, new DeliveryOptions(), null);
    }

    @Override // io.vertx.core.eventbus.EventBus
    public <T> EventBus send(String address, Object message, Handler<AsyncResult<Message<T>>> replyHandler) {
        return send(address, message, new DeliveryOptions(), replyHandler);
    }

    @Override // io.vertx.core.eventbus.EventBus
    public EventBus send(String address, Object message, DeliveryOptions options) {
        return send(address, message, options, null);
    }

    @Override // io.vertx.core.eventbus.EventBus
    public <T> EventBus send(String address, Object message, DeliveryOptions options, Handler<AsyncResult<Message<T>>> replyHandler) {
        sendOrPubInternal(createMessage(true, address, options.getHeaders(), message, options.getCodecName(), null), options, replyHandler);
        return this;
    }

    @Override // io.vertx.core.eventbus.EventBus
    public <T> MessageProducer<T> sender(String address) {
        Objects.requireNonNull(address, "address");
        return new MessageProducerImpl(this.vertx, address, true, new DeliveryOptions());
    }

    @Override // io.vertx.core.eventbus.EventBus
    public <T> MessageProducer<T> sender(String address, DeliveryOptions options) {
        Objects.requireNonNull(address, "address");
        Objects.requireNonNull(options, "options");
        return new MessageProducerImpl(this.vertx, address, true, options);
    }

    @Override // io.vertx.core.eventbus.EventBus
    public <T> MessageProducer<T> publisher(String address) {
        Objects.requireNonNull(address, "address");
        return new MessageProducerImpl(this.vertx, address, false, new DeliveryOptions());
    }

    @Override // io.vertx.core.eventbus.EventBus
    public <T> MessageProducer<T> publisher(String address, DeliveryOptions options) {
        Objects.requireNonNull(address, "address");
        Objects.requireNonNull(options, "options");
        return new MessageProducerImpl(this.vertx, address, false, options);
    }

    @Override // io.vertx.core.eventbus.EventBus
    public EventBus publish(String address, Object message) {
        return publish(address, message, new DeliveryOptions());
    }

    @Override // io.vertx.core.eventbus.EventBus
    public EventBus publish(String address, Object message, DeliveryOptions options) {
        sendOrPubInternal(createMessage(false, address, options.getHeaders(), message, options.getCodecName(), null), options, null);
        return this;
    }

    @Override // io.vertx.core.eventbus.EventBus
    public <T> MessageConsumer<T> consumer(String address) {
        checkStarted();
        Objects.requireNonNull(address, "address");
        return new HandlerRegistration(this.vertx, this.metrics, this, address, null, false, null, -1L);
    }

    @Override // io.vertx.core.eventbus.EventBus
    public <T> MessageConsumer<T> consumer(String address, Handler<Message<T>> handler) {
        Objects.requireNonNull(handler, "handler");
        MessageConsumer<T> consumer = consumer(address);
        consumer.handler2((Handler) handler);
        return consumer;
    }

    @Override // io.vertx.core.eventbus.EventBus
    public <T> MessageConsumer<T> localConsumer(String address) {
        checkStarted();
        Objects.requireNonNull(address, "address");
        return new HandlerRegistration(this.vertx, this.metrics, this, address, null, true, null, -1L);
    }

    @Override // io.vertx.core.eventbus.EventBus
    public <T> MessageConsumer<T> localConsumer(String address, Handler<Message<T>> handler) {
        Objects.requireNonNull(handler, "handler");
        MessageConsumer<T> consumer = localConsumer(address);
        consumer.handler2((Handler) handler);
        return consumer;
    }

    @Override // io.vertx.core.eventbus.EventBus
    public EventBus registerCodec(MessageCodec codec) {
        this.codecManager.registerCodec(codec);
        return this;
    }

    @Override // io.vertx.core.eventbus.EventBus
    public EventBus unregisterCodec(String name) {
        this.codecManager.unregisterCodec(name);
        return this;
    }

    @Override // io.vertx.core.eventbus.EventBus
    public <T> EventBus registerDefaultCodec(Class<T> clazz, MessageCodec<T, ?> codec) {
        this.codecManager.registerDefaultCodec(clazz, codec);
        return this;
    }

    @Override // io.vertx.core.eventbus.EventBus
    public EventBus unregisterDefaultCodec(Class clazz) {
        this.codecManager.unregisterDefaultCodec(clazz);
        return this;
    }

    @Override // io.vertx.core.eventbus.EventBus
    public void close(Handler<AsyncResult<Void>> completionHandler) {
        checkStarted();
        unregisterAll();
        if (this.metrics != null) {
            this.metrics.close();
        }
        if (completionHandler != null) {
            this.vertx.runOnContext(v -> {
                completionHandler.handle(Future.succeededFuture());
            });
        }
    }

    @Override // io.vertx.core.metrics.Measured
    public boolean isMetricsEnabled() {
        return this.metrics != null;
    }

    @Override // io.vertx.core.spi.metrics.MetricsProvider
    public EventBusMetrics<?> getMetrics() {
        return this.metrics;
    }

    public MessageImpl createMessage(boolean send, String address, MultiMap headers, Object body, String codecName, Handler<AsyncResult<Void>> writeHandler) {
        Objects.requireNonNull(address, "no null address accepted");
        MessageCodec codec = this.codecManager.lookupCodec(body, codecName);
        MessageImpl msg = new MessageImpl(address, null, headers, body, codec, send, this, writeHandler);
        return msg;
    }

    protected <T> HandlerHolder<T> addRegistration(String address, HandlerRegistration<T> registration, boolean replyHandler, boolean localOnly) {
        Objects.requireNonNull(registration.getHandler(), "handler");
        LocalRegistrationResult<T> result = addLocalRegistration(address, registration, replyHandler, localOnly);
        boolean z = result.newAddress;
        registration.getClass();
        addRegistration(z, address, replyHandler, localOnly, registration::setResult);
        return result.holder;
    }

    protected <T> void addRegistration(boolean newAddress, String address, boolean replyHandler, boolean localOnly, Handler<AsyncResult<Void>> completionHandler) {
        completionHandler.handle(Future.succeededFuture());
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/EventBusImpl$LocalRegistrationResult.class */
    private static class LocalRegistrationResult<T> {
        final HandlerHolder<T> holder;
        final boolean newAddress;

        LocalRegistrationResult(HandlerHolder<T> holder, boolean newAddress) {
            this.holder = holder;
            this.newAddress = newAddress;
        }
    }

    private <T> LocalRegistrationResult<T> addLocalRegistration(String address, HandlerRegistration<T> registration, boolean replyHandler, boolean localOnly) {
        Objects.requireNonNull(address, "address");
        Context context = this.vertx.getOrCreateContext();
        registration.setHandlerContext(context);
        HandlerHolder<T> holder = new HandlerHolder<>(registration, replyHandler, localOnly, context);
        ConcurrentCyclicSequence<T> concurrentCyclicSequenceAdd = new ConcurrentCyclicSequence().add(holder);
        ConcurrentCyclicSequence<T> concurrentCyclicSequence = (ConcurrentCyclicSequence) this.handlerMap.merge(address, concurrentCyclicSequenceAdd, (old, prev) -> {
            return old.add(prev.first());
        });
        if (context.deploymentID() != null) {
            HandlerEntry entry = new HandlerEntry(address, registration);
            context.addCloseHook(entry);
        }
        boolean newAddress = concurrentCyclicSequenceAdd == concurrentCyclicSequence;
        return new LocalRegistrationResult<>(holder, newAddress);
    }

    protected <T> void removeRegistration(HandlerHolder<T> holder, Handler<AsyncResult<Void>> completionHandler) {
        boolean last = removeLocalRegistration(holder);
        removeRegistration(last ? holder : null, holder.getHandler().address(), completionHandler);
    }

    protected <T> void removeRegistration(HandlerHolder<T> handlerHolder, String address, Handler<AsyncResult<Void>> completionHandler) {
        callCompletionHandlerAsync(completionHandler);
    }

    private <T> boolean removeLocalRegistration(HandlerHolder<T> holder) {
        String address = holder.getHandler().address();
        boolean last = this.handlerMap.compute(address, (key, val) -> {
            if (val == null) {
                return null;
            }
            ConcurrentCyclicSequence<HandlerHolder> next = val.remove(holder);
            if (next.size() == 0) {
                return null;
            }
            return next;
        }) == null;
        if (holder.setRemoved() && holder.getContext().deploymentID() != null) {
            holder.getContext().removeCloseHook(new HandlerEntry(address, holder.getHandler()));
        }
        return last;
    }

    protected <T> void sendReply(MessageImpl replyMessage, MessageImpl replierMessage, DeliveryOptions options, Handler<AsyncResult<Message<T>>> replyHandler) {
        if (replyMessage.address() == null) {
            throw new IllegalStateException("address not specified");
        }
        HandlerRegistration<T> replyHandlerRegistration = createReplyHandlerRegistration(replyMessage, options, replyHandler);
        new OutboundDeliveryContext(replyMessage, options, replyHandlerRegistration, replierMessage).next();
    }

    protected <T> void sendReply(OutboundDeliveryContext<T> sendContext, MessageImpl replierMessage) {
        sendOrPub(sendContext);
    }

    protected <T> void sendOrPub(OutboundDeliveryContext<T> sendContext) {
        MessageImpl message = sendContext.message;
        if (this.metrics != null) {
            this.metrics.messageSent(message.address(), !message.isSend(), true, false);
        }
        deliverMessageLocally(sendContext);
    }

    protected <T> Handler<Message<T>> convertHandler(Handler<AsyncResult<Message<T>>> handler) {
        return reply -> {
            Future futureSucceededFuture;
            if (reply.body() instanceof ReplyException) {
                ReplyException exception = (ReplyException) reply.body();
                if (this.metrics != null) {
                    this.metrics.replyFailure(reply.address(), exception.failureType());
                }
                futureSucceededFuture = Future.failedFuture(exception);
            } else {
                futureSucceededFuture = Future.succeededFuture(reply);
            }
            handler.handle(futureSucceededFuture);
        };
    }

    protected void callCompletionHandlerAsync(Handler<AsyncResult<Void>> completionHandler) {
        if (completionHandler != null) {
            this.vertx.runOnContext(v -> {
                completionHandler.handle(Future.succeededFuture());
            });
        }
    }

    protected <T> void deliverMessageLocally(OutboundDeliveryContext<T> sendContext) {
        ReplyException failure = deliverMessageLocally(sendContext.message);
        if (failure != null) {
            if (this.metrics != null) {
                this.metrics.replyFailure(sendContext.message.address, ReplyFailure.NO_HANDLERS);
            }
            if (((OutboundDeliveryContext) sendContext).handlerRegistration == null) {
                return;
            }
            ((OutboundDeliveryContext) sendContext).handlerRegistration.sendAsyncResultFailure(failure);
        }
    }

    protected boolean isMessageLocal(MessageImpl msg) {
        return true;
    }

    protected ReplyException deliverMessageLocally(MessageImpl msg) {
        msg.setBus(this);
        ConcurrentCyclicSequence<HandlerHolder> handlers = this.handlerMap.get(msg.address());
        if (handlers != null) {
            if (msg.isSend()) {
                HandlerHolder holder = handlers.next();
                if (this.metrics != null) {
                    this.metrics.messageReceived(msg.address(), !msg.isSend(), isMessageLocal(msg), holder != null ? 1 : 0);
                }
                if (holder != null) {
                    deliverToHandler(msg, holder);
                    Handler<AsyncResult<Void>> handler = msg.writeHandler;
                    if (handler != null) {
                        handler.handle(Future.succeededFuture());
                        return null;
                    }
                    return null;
                }
                return null;
            }
            if (this.metrics != null) {
                this.metrics.messageReceived(msg.address(), !msg.isSend(), isMessageLocal(msg), handlers.size());
            }
            Iterator<HandlerHolder> it = handlers.iterator();
            while (it.hasNext()) {
                deliverToHandler(msg, it.next());
            }
            Handler<AsyncResult<Void>> handler2 = msg.writeHandler;
            if (handler2 != null) {
                handler2.handle(Future.succeededFuture());
                return null;
            }
            return null;
        }
        if (this.metrics != null) {
            this.metrics.messageReceived(msg.address(), !msg.isSend(), isMessageLocal(msg), 0);
        }
        ReplyException failure = new ReplyException(ReplyFailure.NO_HANDLERS, "No handlers for address " + msg.address);
        Handler<AsyncResult<Void>> handler3 = msg.writeHandler;
        if (handler3 != null) {
            handler3.handle(Future.failedFuture(failure));
        }
        return failure;
    }

    protected void checkStarted() {
        if (!this.started) {
            throw new IllegalStateException("Event Bus is not started");
        }
    }

    protected String generateReplyAddress() {
        return "__vertx.reply." + Long.toString(this.replySequence.incrementAndGet());
    }

    private <T> HandlerRegistration<T> createReplyHandlerRegistration(MessageImpl message, DeliveryOptions options, Handler<AsyncResult<Message<T>>> replyHandler) {
        if (replyHandler != null) {
            long timeout = options.getSendTimeout();
            String replyAddress = generateReplyAddress();
            message.setReplyAddress(replyAddress);
            Handler<Message<T>> simpleReplyHandler = convertHandler(replyHandler);
            HandlerRegistration<T> registration = new HandlerRegistration<>(this.vertx, this.metrics, this, replyAddress, message.address, true, replyHandler, timeout);
            registration.handler2((Handler) simpleReplyHandler);
            return registration;
        }
        return null;
    }

    public <T> void sendOrPubInternal(MessageImpl message, DeliveryOptions options, Handler<AsyncResult<Message<T>>> replyHandler) {
        checkStarted();
        HandlerRegistration<T> replyHandlerRegistration = createReplyHandlerRegistration(message, options, replyHandler);
        OutboundDeliveryContext<T> sendContext = new OutboundDeliveryContext<>(message, options, replyHandlerRegistration);
        sendContext.next();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/EventBusImpl$OutboundDeliveryContext.class */
    protected class OutboundDeliveryContext<T> implements DeliveryContext<T> {
        public final MessageImpl message;
        public final DeliveryOptions options;
        public final Iterator<Handler<DeliveryContext>> iter;
        private final HandlerRegistration<T> handlerRegistration;
        private final MessageImpl replierMessage;

        private OutboundDeliveryContext(EventBusImpl this$0, MessageImpl message, DeliveryOptions options, HandlerRegistration<T> handlerRegistration) {
            this(message, options, handlerRegistration, (MessageImpl) null);
        }

        private OutboundDeliveryContext(MessageImpl message, DeliveryOptions options, HandlerRegistration<T> handlerRegistration, MessageImpl replierMessage) {
            this.message = message;
            this.options = options;
            this.handlerRegistration = handlerRegistration;
            this.iter = EventBusImpl.this.sendInterceptors.iterator();
            this.replierMessage = replierMessage;
        }

        @Override // io.vertx.core.eventbus.DeliveryContext
        public Message<T> message() {
            return this.message;
        }

        @Override // io.vertx.core.eventbus.DeliveryContext
        public void next() {
            if (this.iter.hasNext()) {
                Handler<DeliveryContext> handler = this.iter.next();
                try {
                    if (handler != null) {
                        handler.handle(this);
                    } else {
                        next();
                    }
                    return;
                } catch (Throwable t) {
                    EventBusImpl.log.error("Failure in interceptor", t);
                    return;
                }
            }
            if (this.replierMessage == null) {
                EventBusImpl.this.sendOrPub(this);
            } else {
                EventBusImpl.this.sendReply(this, this.replierMessage);
            }
        }

        @Override // io.vertx.core.eventbus.DeliveryContext
        public boolean send() {
            return this.message.isSend();
        }

        @Override // io.vertx.core.eventbus.DeliveryContext
        public Object body() {
            return this.message.sentBody;
        }
    }

    private void unregisterAll() {
        for (ConcurrentCyclicSequence<HandlerHolder> handlers : this.handlerMap.values()) {
            Iterator<HandlerHolder> it = handlers.iterator();
            while (it.hasNext()) {
                HandlerHolder holder = it.next();
                holder.getHandler().unregister();
            }
        }
    }

    private <T> void deliverToHandler(MessageImpl msg, HandlerHolder<T> holder) {
        MessageImpl copied = msg.copyBeforeReceive();
        DeliveryContext<T> receiveContext = new InboundDeliveryContext<>(copied, holder);
        if (this.metrics != null) {
            this.metrics.scheduleMessage(holder.getHandler().getMetric(), msg.isLocal());
        }
        holder.getContext().runOnContext(v -> {
            try {
                if (!holder.isRemoved()) {
                    receiveContext.next();
                }
            } finally {
                if (holder.isReplyHandler()) {
                    holder.getHandler().unregister();
                }
            }
        });
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/EventBusImpl$InboundDeliveryContext.class */
    protected class InboundDeliveryContext<T> implements DeliveryContext<T> {
        private final MessageImpl message;
        private final Iterator<Handler<DeliveryContext>> iter;
        private final HandlerHolder<T> holder;

        private InboundDeliveryContext(MessageImpl message, HandlerHolder<T> holder) {
            this.message = message;
            this.holder = holder;
            this.iter = EventBusImpl.this.receiveInterceptors.iterator();
        }

        @Override // io.vertx.core.eventbus.DeliveryContext
        public Message<T> message() {
            return this.message;
        }

        @Override // io.vertx.core.eventbus.DeliveryContext
        public void next() {
            if (this.iter.hasNext()) {
                try {
                    Handler<DeliveryContext> handler = this.iter.next();
                    if (handler != null) {
                        handler.handle(this);
                    } else {
                        next();
                    }
                    return;
                } catch (Throwable t) {
                    EventBusImpl.log.error("Failure in interceptor", t);
                    return;
                }
            }
            this.holder.getHandler().handle((Message) this.message);
        }

        @Override // io.vertx.core.eventbus.DeliveryContext
        public boolean send() {
            return this.message.isSend();
        }

        @Override // io.vertx.core.eventbus.DeliveryContext
        public Object body() {
            return this.message.receivedBody;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/EventBusImpl$HandlerEntry.class */
    public class HandlerEntry<T> implements Closeable {
        final String address;
        final HandlerRegistration<T> handler;

        public HandlerEntry(String address, HandlerRegistration<T> handler) {
            this.address = address;
            this.handler = handler;
        }

        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            if (this == o) {
                return true;
            }
            if (getClass() != o.getClass()) {
                return false;
            }
            HandlerEntry entry = (HandlerEntry) o;
            return this.address.equals(entry.address) && this.handler.equals(entry.handler);
        }

        public int hashCode() {
            int result = this.address != null ? this.address.hashCode() : 0;
            return (31 * result) + (this.handler != null ? this.handler.hashCode() : 0);
        }

        @Override // io.vertx.core.Closeable
        public void close(Handler<AsyncResult<Void>> completionHandler) {
            this.handler.unregister(completionHandler);
        }
    }

    protected void finalize() throws Throwable {
        close(ar -> {
        });
        super.finalize();
    }
}
