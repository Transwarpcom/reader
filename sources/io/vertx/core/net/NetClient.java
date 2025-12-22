package io.vertx.core.net;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.metrics.Measured;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/NetClient.class */
public interface NetClient extends Measured {
    @Fluent
    NetClient connect(int i, String str, Handler<AsyncResult<NetSocket>> handler);

    @Fluent
    NetClient connect(int i, String str, String str2, Handler<AsyncResult<NetSocket>> handler);

    @Fluent
    NetClient connect(SocketAddress socketAddress, Handler<AsyncResult<NetSocket>> handler);

    @Fluent
    NetClient connect(SocketAddress socketAddress, String str, Handler<AsyncResult<NetSocket>> handler);

    void close();
}
