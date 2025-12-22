package io.vertx.core.net.impl;

import io.netty.channel.EventLoop;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/HandlerManager.class */
public class HandlerManager<T> {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) HandlerManager.class);
    private final VertxEventLoopGroup availableWorkers;
    private final ConcurrentMap<EventLoop, Handlers<T>> handlerMap = new ConcurrentHashMap();
    private volatile boolean hasHandlers;

    public HandlerManager(VertxEventLoopGroup availableWorkers) {
        this.availableWorkers = availableWorkers;
    }

    public boolean hasHandlers() {
        return this.hasHandlers;
    }

    public synchronized List<T> handlers() {
        return (List) this.handlerMap.values().stream().flatMap(handlers -> {
            return handlers.list.stream();
        }).map(holder -> {
            return holder.handler;
        }).collect(Collectors.toList());
    }

    public HandlerHolder<T> chooseHandler(EventLoop worker) {
        Handlers<T> handlers = this.handlerMap.get(worker);
        if (handlers == null) {
            return null;
        }
        return handlers.chooseHandler();
    }

    public synchronized void addHandler(T handler, ContextInternal context) {
        EventLoop worker = context.nettyEventLoop();
        this.availableWorkers.addWorker(worker);
        Handlers<T> handlers = new Handlers<>();
        Handlers<T> prev = this.handlerMap.putIfAbsent(worker, handlers);
        if (prev != null) {
            handlers = prev;
        }
        handlers.addHandler(new HandlerHolder<>(context, handler));
        this.hasHandlers = true;
    }

    public synchronized void removeHandler(T handler, ContextInternal context) {
        EventLoop worker = context.nettyEventLoop();
        Handlers<T> handlers = this.handlerMap.get(worker);
        if (!handlers.removeHandler(new HandlerHolder<>(context, handler))) {
            throw new IllegalStateException("Can't find handler");
        }
        if (handlers.isEmpty()) {
            this.handlerMap.remove(worker);
        }
        if (this.handlerMap.isEmpty()) {
            this.hasHandlers = false;
        }
        this.availableWorkers.removeWorker(worker);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/HandlerManager$Handlers.class */
    private static final class Handlers<T> {
        private int pos;
        private final List<HandlerHolder<T>> list;

        private Handlers() {
            this.list = new CopyOnWriteArrayList();
        }

        HandlerHolder<T> chooseHandler() {
            HandlerHolder<T> handler = this.list.get(this.pos);
            this.pos++;
            checkPos();
            return handler;
        }

        void addHandler(HandlerHolder<T> handler) {
            this.list.add(handler);
        }

        boolean removeHandler(HandlerHolder<T> handler) {
            if (this.list.remove(handler)) {
                checkPos();
                return true;
            }
            return false;
        }

        boolean isEmpty() {
            return this.list.isEmpty();
        }

        void checkPos() {
            if (this.pos == this.list.size()) {
                this.pos = 0;
            }
        }
    }
}
