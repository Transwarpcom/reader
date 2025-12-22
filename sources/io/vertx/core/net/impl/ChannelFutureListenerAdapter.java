package io.vertx.core.net.impl;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.impl.ContextInternal;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/ChannelFutureListenerAdapter.class */
public final class ChannelFutureListenerAdapter<T> implements ChannelFutureListener {
    private final Handler<AsyncResult<T>> handler;
    private final T result;
    private final ContextInternal context;

    public ChannelFutureListenerAdapter(ContextInternal context, T result, Handler<AsyncResult<T>> handler) {
        this.handler = handler;
        this.result = result;
        this.context = context;
    }

    @Override // io.netty.util.concurrent.GenericFutureListener
    public void operationComplete(ChannelFuture future) {
        Future<T> res = future.isSuccess() ? Future.succeededFuture(this.result) : Future.failedFuture(future.cause());
        this.context.executeFromIO(res, this.handler);
    }
}
