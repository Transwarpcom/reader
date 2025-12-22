package io.vertx.core.net.impl;

import io.vertx.core.impl.ContextInternal;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/HandlerHolder.class */
public class HandlerHolder<T> {
    public final ContextInternal context;
    public final T handler;

    public HandlerHolder(ContextInternal context, T handler) {
        this.context = context;
        this.handler = handler;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HandlerHolder that = (HandlerHolder) o;
        if (this.context != that.context) {
            return false;
        }
        return this.handler != null ? this.handler.equals(that.handler) : that.handler == null;
    }

    public int hashCode() {
        int result = this.context.hashCode();
        return (31 * result) + this.handler.hashCode();
    }
}
