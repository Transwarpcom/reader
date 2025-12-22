package io.vertx.core.http.impl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.impl.ContextInternal;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/HttpClientConnection.class */
public interface HttpClientConnection extends HttpConnection {
    Channel channel();

    ChannelHandlerContext channelHandlerContext();

    @Override // io.vertx.core.http.HttpConnection
    void close();

    void createStream(Handler<AsyncResult<HttpClientStream>> handler);

    ContextInternal getContext();

    Object metric();
}
