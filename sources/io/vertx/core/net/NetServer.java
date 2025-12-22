package io.vertx.core.net;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.metrics.Measured;
import io.vertx.core.streams.ReadStream;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/NetServer.class */
public interface NetServer extends Measured {
    ReadStream<NetSocket> connectStream();

    NetServer connectHandler(Handler<NetSocket> handler);

    @GenIgnore
    Handler<NetSocket> connectHandler();

    @Fluent
    NetServer listen();

    @Fluent
    NetServer listen(Handler<AsyncResult<NetServer>> handler);

    @Fluent
    NetServer listen(int i, String str);

    @Fluent
    NetServer listen(int i, String str, Handler<AsyncResult<NetServer>> handler);

    @Fluent
    NetServer listen(int i);

    @Fluent
    NetServer listen(int i, Handler<AsyncResult<NetServer>> handler);

    @Fluent
    NetServer listen(SocketAddress socketAddress);

    @Fluent
    NetServer listen(SocketAddress socketAddress, Handler<AsyncResult<NetServer>> handler);

    @GenIgnore
    @Fluent
    NetServer exceptionHandler(Handler<Throwable> handler);

    void close();

    void close(Handler<AsyncResult<Void>> handler);

    int actualPort();
}
