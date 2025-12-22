package io.vertx.core.streams.impl;

import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import java.util.ArrayDeque;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/streams/impl/InboundBuffer.class */
public class InboundBuffer<E> {
    public static final Object END_SENTINEL = new Object();
    private final Context context;
    private final ArrayDeque<E> pending;
    private final long highWaterMark;
    private long demand;
    private Handler<E> handler;
    private boolean overflow;
    private Handler<Void> drainHandler;
    private Handler<Void> emptyHandler;
    private Handler<Throwable> exceptionHandler;
    private boolean emitting;

    public InboundBuffer(Context context) {
        this(context, 16L);
    }

    public InboundBuffer(Context context, long highWaterMark) {
        if (context == null) {
            throw new NullPointerException("context must not be null");
        }
        if (highWaterMark < 0) {
            throw new IllegalArgumentException("highWaterMark " + highWaterMark + " >= 0");
        }
        this.context = context;
        this.highWaterMark = highWaterMark;
        this.demand = Long.MAX_VALUE;
        this.pending = new ArrayDeque<>();
    }

    private void checkContext() {
        if (this.context != Vertx.currentContext()) {
            throw new IllegalStateException("This operation must be called from the context thread");
        }
    }

    public boolean write(E e) {
        checkContext();
        synchronized (this) {
            if (this.demand == 0 || this.emitting) {
                this.pending.add(e);
                return checkWritable();
            }
            if (this.demand != Long.MAX_VALUE) {
                this.demand--;
            }
            this.emitting = true;
            handleEvent(this.handler, e);
            return emitPending();
        }
    }

    private boolean checkWritable() {
        if (this.demand == Long.MAX_VALUE) {
            return true;
        }
        long actual = this.pending.size() - this.demand;
        boolean writable = actual < this.highWaterMark;
        this.overflow |= !writable;
        return writable;
    }

    public boolean write(Iterable<E> elements) {
        checkContext();
        synchronized (this) {
            for (E element : elements) {
                this.pending.add(element);
            }
            if (this.demand == 0 || this.emitting) {
                return checkWritable();
            }
            this.emitting = true;
            return emitPending();
        }
    }

    private boolean emitPending() {
        E ePoll;
        Handler<E> handler;
        while (true) {
            synchronized (this) {
                int size = this.pending.size();
                if (this.demand == 0) {
                    this.emitting = false;
                    boolean z = ((long) size) < this.highWaterMark;
                    this.overflow |= !z;
                    return z;
                }
                if (size == 0) {
                    this.emitting = false;
                    return true;
                }
                if (this.demand != Long.MAX_VALUE) {
                    this.demand--;
                }
                ePoll = this.pending.poll();
                handler = this.handler;
            }
            handleEvent(handler, ePoll);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void drain() {
        Handler handler;
        Handler handler2;
        E ePoll;
        Handler handler3;
        int i = 0;
        while (true) {
            synchronized (this) {
                if (this.pending.size() == 0) {
                    this.emitting = false;
                    if (this.overflow) {
                        this.overflow = false;
                        handler = this.drainHandler;
                    } else {
                        handler = null;
                    }
                    handler2 = i > 0 ? this.emptyHandler : null;
                } else {
                    if (this.demand == 0) {
                        this.emitting = false;
                        return;
                    }
                    i++;
                    if (this.demand != Long.MAX_VALUE) {
                        this.demand--;
                    }
                    ePoll = this.pending.poll();
                    handler3 = (Handler<E>) this.handler;
                }
            }
            if (handler != null) {
                handleEvent(handler, null);
            }
            if (handler2 != null) {
                handleEvent(handler2, null);
                return;
            }
            return;
            handleEvent(handler3, ePoll);
        }
    }

    private <T> void handleEvent(Handler<T> handler, T element) {
        if (handler != null) {
            try {
                handler.handle(element);
            } catch (Throwable t) {
                handleException(t);
            }
        }
    }

    private void handleException(Throwable err) {
        synchronized (this) {
            Handler<Throwable> handler = this.exceptionHandler;
            if (handler == null) {
                return;
            }
            handler.handle(err);
        }
    }

    public boolean fetch(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException();
        }
        synchronized (this) {
            this.demand += amount;
            if (this.demand < 0) {
                this.demand = Long.MAX_VALUE;
            }
            if (this.emitting || (this.pending.isEmpty() && !this.overflow)) {
                return false;
            }
            this.emitting = true;
            this.context.runOnContext(v -> {
                drain();
            });
            return true;
        }
    }

    public E read() {
        E ePoll;
        synchronized (this) {
            ePoll = this.pending.poll();
        }
        return ePoll;
    }

    public synchronized InboundBuffer<E> clear() {
        this.pending.clear();
        return this;
    }

    public synchronized InboundBuffer<E> pause() {
        this.demand = 0L;
        return this;
    }

    public boolean resume() {
        return fetch(Long.MAX_VALUE);
    }

    public synchronized InboundBuffer<E> handler(Handler<E> handler) {
        this.handler = handler;
        return this;
    }

    public synchronized InboundBuffer<E> drainHandler(Handler<Void> handler) {
        this.drainHandler = handler;
        return this;
    }

    public synchronized InboundBuffer<E> emptyHandler(Handler<Void> handler) {
        this.emptyHandler = handler;
        return this;
    }

    public synchronized InboundBuffer<E> exceptionHandler(Handler<Throwable> handler) {
        this.exceptionHandler = handler;
        return this;
    }

    public synchronized boolean isEmpty() {
        return this.pending.isEmpty();
    }

    public synchronized boolean isWritable() {
        return ((long) this.pending.size()) < this.highWaterMark;
    }

    public synchronized boolean isPaused() {
        return this.demand == 0;
    }

    public synchronized int size() {
        return this.pending.size();
    }
}
