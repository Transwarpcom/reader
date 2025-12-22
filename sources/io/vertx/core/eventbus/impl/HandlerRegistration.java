package io.vertx.core.eventbus.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.eventbus.ReplyFailure;
import io.vertx.core.eventbus.impl.clustered.ClusteredMessage;
import io.vertx.core.impl.Arguments;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.spi.metrics.EventBusMetrics;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/HandlerRegistration.class */
public class HandlerRegistration<T> implements MessageConsumer<T>, Handler<Message<T>> {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) HandlerRegistration.class);
    public static final int DEFAULT_MAX_BUFFERED_MESSAGES = 1000;
    private final Vertx vertx;
    private final EventBusMetrics metrics;
    private final EventBusImpl eventBus;
    private final String address;
    private final String repliedAddress;
    private final boolean localOnly;
    private final Handler<AsyncResult<Message<T>>> asyncResultHandler;
    private long timeoutID;
    private HandlerHolder<T> registered;
    private Handler<Message<T>> handler;
    private ContextInternal handlerContext;
    private AsyncResult<Void> result;
    private Handler<AsyncResult<Void>> completionHandler;
    private Handler<Void> endHandler;
    private Handler<Message<T>> discardHandler;
    private int maxBufferedMessages = 1000;
    private final Queue<Message<T>> pending = new ArrayDeque(8);
    private long demand = Long.MAX_VALUE;
    private Object metric;

    @Override // io.vertx.core.eventbus.MessageConsumer, io.vertx.core.streams.ReadStream
    public /* bridge */ /* synthetic */ ReadStream endHandler(Handler handler) {
        return endHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.eventbus.MessageConsumer, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ ReadStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.eventbus.MessageConsumer, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    public HandlerRegistration(Vertx vertx, EventBusMetrics metrics, EventBusImpl eventBus, String address, String repliedAddress, boolean localOnly, Handler<AsyncResult<Message<T>>> asyncResultHandler, long timeout) {
        this.timeoutID = -1L;
        this.vertx = vertx;
        this.metrics = metrics;
        this.eventBus = eventBus;
        this.address = address;
        this.repliedAddress = repliedAddress;
        this.localOnly = localOnly;
        this.asyncResultHandler = asyncResultHandler;
        if (timeout != -1) {
            this.timeoutID = vertx.setTimer(timeout, tid -> {
                if (metrics != null) {
                    metrics.replyFailure(address, ReplyFailure.TIMEOUT);
                }
                sendAsyncResultFailure(new ReplyException(ReplyFailure.TIMEOUT, "Timed out after waiting " + timeout + "(ms) for a reply. address: " + address + ", repliedAddress: " + repliedAddress));
            });
        }
    }

    @Override // io.vertx.core.eventbus.MessageConsumer
    public MessageConsumer<T> setMaxBufferedMessages(int maxBufferedMessages) {
        Arguments.require(maxBufferedMessages >= 0, "Max buffered messages cannot be negative");
        synchronized (this) {
            this.maxBufferedMessages = maxBufferedMessages;
            int overflow = this.pending.size() - maxBufferedMessages;
            if (overflow <= 0) {
                return this;
            }
            Handler<Message<T>> discardHandler = this.discardHandler;
            if (discardHandler == null) {
                while (this.pending.size() > maxBufferedMessages) {
                    this.pending.poll();
                }
                return this;
            }
            List<Message<T>> discarded = new ArrayList<>(overflow);
            while (this.pending.size() > maxBufferedMessages) {
                discarded.add(this.pending.poll());
            }
            for (Message<T> msg : discarded) {
                discardHandler.handle(msg);
            }
            return this;
        }
    }

    @Override // io.vertx.core.eventbus.MessageConsumer
    public synchronized int getMaxBufferedMessages() {
        return this.maxBufferedMessages;
    }

    @Override // io.vertx.core.eventbus.MessageConsumer
    public String address() {
        return this.address;
    }

    @Override // io.vertx.core.eventbus.MessageConsumer
    public synchronized void completionHandler(Handler<AsyncResult<Void>> completionHandler) {
        Objects.requireNonNull(completionHandler);
        if (this.result != null) {
            AsyncResult<Void> value = this.result;
            this.vertx.runOnContext(v -> {
                completionHandler.handle(value);
            });
        } else {
            this.completionHandler = completionHandler;
        }
    }

    @Override // io.vertx.core.eventbus.MessageConsumer
    public void unregister() {
        doUnregister(null);
    }

    @Override // io.vertx.core.eventbus.MessageConsumer
    public void unregister(Handler<AsyncResult<Void>> completionHandler) {
        doUnregister(completionHandler);
    }

    public void sendAsyncResultFailure(ReplyException failure) {
        unregister();
        this.asyncResultHandler.handle(Future.failedFuture(failure));
    }

    private void doUnregister(Handler<AsyncResult<Void>> doneHandler) {
        synchronized (this) {
            if (this.handler == null) {
                callHandlerAsync(Future.succeededFuture(), doneHandler);
                return;
            }
            this.handler = null;
            if (this.timeoutID != -1) {
                this.vertx.cancelTimer(this.timeoutID);
            }
            if (this.endHandler != null) {
                Handler<Void> theEndHandler = this.endHandler;
                doneHandler = ar -> {
                    theEndHandler.handle(null);
                    if (doneHandler != null) {
                        doneHandler.handle(ar);
                    }
                };
            }
            if (this.pending.size() > 0 && this.discardHandler != null) {
                Deque<Message<T>> discarded = new ArrayDeque<>(this.pending);
                Handler<Message<T>> handler = this.discardHandler;
                this.handlerContext.runOnContext(v -> {
                    while (true) {
                        Message<T> msg = (Message) discarded.poll();
                        if (msg != null) {
                            handler.handle(msg);
                        } else {
                            return;
                        }
                    }
                });
            }
            this.pending.clear();
            this.discardHandler = null;
            this.eventBus.removeRegistration(this.registered, doneHandler);
            this.registered = null;
            if (this.result == null) {
                this.result = Future.failedFuture("Consumer unregistered before registration completed");
                callHandlerAsync(this.result, this.completionHandler);
            } else {
                EventBusMetrics metrics = this.eventBus.metrics;
                if (metrics != null) {
                    metrics.handlerUnregistered(this.metric);
                }
            }
        }
    }

    private void callHandlerAsync(AsyncResult<Void> result, Handler<AsyncResult<Void>> completionHandler) {
        if (completionHandler != null) {
            this.vertx.runOnContext(v -> {
                completionHandler.handle(result);
            });
        }
    }

    synchronized void setHandlerContext(Context context) {
        this.handlerContext = (ContextInternal) context;
    }

    public synchronized void setResult(AsyncResult<Void> result) {
        if (this.result != null) {
            return;
        }
        this.result = result;
        if (result.failed()) {
            log.error("Failed to propagate registration for handler " + this.handler + " and address " + this.address);
            return;
        }
        if (this.metrics != null) {
            this.metric = this.metrics.handlerRegistered(this.address, this.repliedAddress);
        }
        callHandlerAsync(result, this.completionHandler);
    }

    @Override // io.vertx.core.Handler
    public void handle(Message<T> message) {
        synchronized (this) {
            if (this.registered == null) {
                return;
            }
            if (this.demand == 0) {
                if (this.pending.size() < this.maxBufferedMessages) {
                    this.pending.add(message);
                } else if (this.discardHandler != null) {
                    this.discardHandler.handle(message);
                } else {
                    log.warn("Discarding message as more than " + this.maxBufferedMessages + " buffered in paused consumer. address: " + this.address);
                }
                return;
            }
            if (this.pending.size() > 0) {
                this.pending.add(message);
                message = this.pending.poll();
            }
            if (this.demand != Long.MAX_VALUE) {
                this.demand--;
            }
            Handler<Message<T>> theHandler = this.handler;
            ContextInternal ctx = this.handlerContext;
            deliver(theHandler, message, ctx);
        }
    }

    private void deliver(Handler<Message<T>> theHandler, Message<T> message, ContextInternal context) {
        boolean local = true;
        if (message instanceof ClusteredMessage) {
            ClusteredMessage cmsg = (ClusteredMessage) message;
            if (cmsg.isFromWire()) {
                local = false;
            }
        }
        String creditsAddress = message.headers().get(MessageProducerImpl.CREDIT_ADDRESS_HEADER_NAME);
        if (creditsAddress != null) {
            this.eventBus.send(creditsAddress, 1);
        }
        try {
            if (this.metrics != null) {
                this.metrics.beginHandleMessage(this.metric, local);
            }
            theHandler.handle(message);
            if (this.metrics != null) {
                this.metrics.endHandleMessage(this.metric, null);
            }
        } catch (Exception e) {
            log.error("Failed to handleMessage. address: " + message.address(), e);
            if (this.metrics != null) {
                this.metrics.endHandleMessage(this.metric, e);
            }
            context.reportException(e);
        }
        checkNextTick();
    }

    private synchronized void checkNextTick() {
        if (!this.pending.isEmpty() && this.demand > 0) {
            this.handlerContext.runOnContext(v -> {
                Message<T> message;
                synchronized (this) {
                    if (this.demand == 0 || (message = this.pending.poll()) == null) {
                        return;
                    }
                    if (this.demand != Long.MAX_VALUE) {
                        this.demand--;
                    }
                    Handler<Message<T>> theHandler = this.handler;
                    ContextInternal ctx = this.handlerContext;
                    deliver(theHandler, message, ctx);
                }
            });
        }
    }

    public synchronized void discardHandler(Handler<Message<T>> handler) {
        this.discardHandler = handler;
    }

    @Override // io.vertx.core.eventbus.MessageConsumer, io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    public synchronized MessageConsumer<T> handler2(Handler<Message<T>> h) {
        if (h != null) {
            synchronized (this) {
                this.handler = h;
                if (this.registered == null) {
                    this.registered = this.eventBus.addRegistration(this.address, this, this.repliedAddress != null, this.localOnly);
                }
            }
            return this;
        }
        unregister();
        return this;
    }

    @Override // io.vertx.core.eventbus.MessageConsumer
    public ReadStream<T> bodyStream() {
        return new BodyReadStream(this);
    }

    @Override // io.vertx.core.eventbus.MessageConsumer
    public synchronized boolean isRegistered() {
        return this.registered != null;
    }

    @Override // io.vertx.core.eventbus.MessageConsumer, io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    public synchronized MessageConsumer<T> pause2() {
        this.demand = 0L;
        return this;
    }

    @Override // io.vertx.core.eventbus.MessageConsumer, io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    public MessageConsumer<T> resume2() {
        return fetch2(Long.MAX_VALUE);
    }

    @Override // io.vertx.core.eventbus.MessageConsumer, io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    public synchronized MessageConsumer<T> fetch2(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException();
        }
        this.demand += amount;
        if (this.demand < 0) {
            this.demand = Long.MAX_VALUE;
        }
        if (this.demand > 0) {
            checkNextTick();
        }
        return this;
    }

    @Override // io.vertx.core.eventbus.MessageConsumer, io.vertx.core.streams.ReadStream
    public synchronized MessageConsumer<T> endHandler(Handler<Void> endHandler) {
        if (endHandler != null) {
            Context endCtx = this.vertx.getOrCreateContext();
            this.endHandler = v1 -> {
                endCtx.runOnContext(v2 -> {
                    endHandler.handle(null);
                });
            };
        } else {
            this.endHandler = null;
        }
        return this;
    }

    @Override // io.vertx.core.eventbus.MessageConsumer, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public synchronized MessageConsumer<T> exceptionHandler(Handler<Throwable> handler) {
        return this;
    }

    public Handler<Message<T>> getHandler() {
        return this.handler;
    }

    public Object getMetric() {
        return this.metric;
    }
}
