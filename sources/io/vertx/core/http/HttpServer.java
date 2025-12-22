package io.vertx.core.http;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.metrics.Measured;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.streams.ReadStream;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/HttpServer.class */
public interface HttpServer extends Measured {
    @CacheReturn
    ReadStream<HttpServerRequest> requestStream();

    @Fluent
    HttpServer requestHandler(Handler<HttpServerRequest> handler);

    @GenIgnore
    Handler<HttpServerRequest> requestHandler();

    @Fluent
    HttpServer connectionHandler(Handler<HttpConnection> handler);

    @Fluent
    HttpServer exceptionHandler(Handler<Throwable> handler);

    @Deprecated
    @CacheReturn
    ReadStream<ServerWebSocket> websocketStream();

    @CacheReturn
    ReadStream<ServerWebSocket> webSocketStream();

    @Fluent
    @Deprecated
    HttpServer websocketHandler(Handler<ServerWebSocket> handler);

    @GenIgnore
    @Deprecated
    Handler<ServerWebSocket> websocketHandler();

    @Fluent
    HttpServer webSocketHandler(Handler<ServerWebSocket> handler);

    @GenIgnore
    Handler<ServerWebSocket> webSocketHandler();

    @Fluent
    HttpServer listen();

    @Fluent
    HttpServer listen(int i, String str);

    @Fluent
    HttpServer listen(int i, String str, Handler<AsyncResult<HttpServer>> handler);

    @Fluent
    HttpServer listen(SocketAddress socketAddress, Handler<AsyncResult<HttpServer>> handler);

    @Fluent
    HttpServer listen(int i);

    @Fluent
    HttpServer listen(int i, Handler<AsyncResult<HttpServer>> handler);

    @Fluent
    HttpServer listen(Handler<AsyncResult<HttpServer>> handler);

    void close();

    void close(Handler<AsyncResult<Void>> handler);

    int actualPort();
}
