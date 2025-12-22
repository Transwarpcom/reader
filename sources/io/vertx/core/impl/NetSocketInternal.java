package io.vertx.core.impl;

import io.netty.channel.ChannelHandlerContext;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.net.NetSocket;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/NetSocketInternal.class */
public interface NetSocketInternal extends NetSocket {
    ChannelHandlerContext channelHandlerContext();

    NetSocketInternal writeMessage(Object obj);

    NetSocketInternal writeMessage(Object obj, Handler<AsyncResult<Void>> handler);

    NetSocketInternal messageHandler(Handler<Object> handler);
}
