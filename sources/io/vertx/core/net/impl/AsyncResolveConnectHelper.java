package io.vertx.core.net.impl;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFactory;
import io.netty.channel.ChannelFuture;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.Promise;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.net.SocketAddress;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/AsyncResolveConnectHelper.class */
public class AsyncResolveConnectHelper {
    private static void checkPort(int port) {
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port " + port);
        }
    }

    public static Future<Channel> doBind(VertxInternal vertx, SocketAddress socketAddress, ServerBootstrap bootstrap) {
        Promise<Channel> promise = vertx.getAcceptorEventLoopGroup().next().newPromise();
        try {
            bootstrap.channelFactory((ChannelFactory) vertx.transport().serverChannelFactory(socketAddress.path() != null));
            if (socketAddress.path() != null) {
                java.net.SocketAddress converted = vertx.transport().convert(socketAddress, true);
                ChannelFuture future = bootstrap.bind(converted);
                future.addListener2(f -> {
                    if (f.isSuccess()) {
                        promise.setSuccess(future.channel());
                    } else {
                        promise.setFailure(f.cause());
                    }
                });
            } else {
                checkPort(socketAddress.port());
                vertx.resolveAddress(socketAddress.host(), res -> {
                    if (res.succeeded()) {
                        InetSocketAddress t = new InetSocketAddress((InetAddress) res.result(), socketAddress.port());
                        ChannelFuture future2 = bootstrap.bind(t);
                        future2.addListener2(f2 -> {
                            if (f2.isSuccess()) {
                                promise.setSuccess(future2.channel());
                            } else {
                                promise.setFailure(f2.cause());
                            }
                        });
                        return;
                    }
                    promise.setFailure(res.cause());
                });
            }
            return promise;
        } catch (Exception e) {
            promise.setFailure(e);
            return promise;
        }
    }
}
