package io.vertx.core.http.impl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.HttpServerRequest;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/HttpServerConnection.class */
public interface HttpServerConnection extends HttpConnection {
    Channel channel();

    ChannelHandlerContext channelHandlerContext();

    @Fluent
    HttpServerConnection handler(Handler<HttpServerRequest> handler);
}
