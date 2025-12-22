package io.vertx.core.eventbus.impl;

import io.vertx.core.Context;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/HandlerHolder.class */
public class HandlerHolder<T> {
    private final Context context;
    private final HandlerRegistration<T> handler;
    private final boolean replyHandler;
    private final boolean localOnly;
    private boolean removed;

    public HandlerHolder(HandlerRegistration<T> handler, boolean replyHandler, boolean localOnly, Context context) {
        this.context = context;
        this.handler = handler;
        this.replyHandler = replyHandler;
        this.localOnly = localOnly;
    }

    boolean setRemoved() {
        boolean unregistered = false;
        synchronized (this) {
            if (!this.removed) {
                this.removed = true;
                unregistered = true;
            }
        }
        return unregistered;
    }

    public synchronized boolean isRemoved() {
        return this.removed;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HandlerHolder that = (HandlerHolder) o;
        return this.handler != null ? this.handler.equals(that.handler) : that.handler == null;
    }

    public int hashCode() {
        if (this.handler != null) {
            return this.handler.hashCode();
        }
        return 0;
    }

    public Context getContext() {
        return this.context;
    }

    public HandlerRegistration<T> getHandler() {
        return this.handler;
    }

    public boolean isReplyHandler() {
        return this.replyHandler;
    }

    public boolean isLocalOnly() {
        return this.localOnly;
    }
}
