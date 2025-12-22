package io.vertx.core.http;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.metrics.Measured;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.streams.ReadStream;
import java.util.List;
import java.util.function.Function;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/HttpClient.class */
public interface HttpClient extends Measured {
    HttpClientRequest request(HttpMethod httpMethod, SocketAddress socketAddress, RequestOptions requestOptions);

    HttpClientRequest request(HttpMethod httpMethod, RequestOptions requestOptions);

    HttpClientRequest request(HttpMethod httpMethod, int i, String str, String str2);

    HttpClientRequest request(HttpMethod httpMethod, SocketAddress socketAddress, int i, String str, String str2);

    HttpClientRequest request(HttpMethod httpMethod, String str, String str2);

    @Deprecated
    HttpClientRequest request(HttpMethod httpMethod, RequestOptions requestOptions, Handler<HttpClientResponse> handler);

    HttpClientRequest request(HttpMethod httpMethod, SocketAddress socketAddress, RequestOptions requestOptions, Handler<HttpClientResponse> handler);

    @Deprecated
    HttpClientRequest request(HttpMethod httpMethod, int i, String str, String str2, Handler<HttpClientResponse> handler);

    HttpClientRequest request(HttpMethod httpMethod, SocketAddress socketAddress, int i, String str, String str2, Handler<HttpClientResponse> handler);

    @Deprecated
    HttpClientRequest request(HttpMethod httpMethod, String str, String str2, Handler<HttpClientResponse> handler);

    HttpClientRequest request(HttpMethod httpMethod, String str);

    @Deprecated
    HttpClientRequest request(HttpMethod httpMethod, String str, Handler<HttpClientResponse> handler);

    HttpClientRequest requestAbs(HttpMethod httpMethod, String str);

    HttpClientRequest requestAbs(HttpMethod httpMethod, SocketAddress socketAddress, String str);

    @Deprecated
    HttpClientRequest requestAbs(HttpMethod httpMethod, String str, Handler<HttpClientResponse> handler);

    HttpClientRequest requestAbs(HttpMethod httpMethod, SocketAddress socketAddress, String str, Handler<HttpClientResponse> handler);

    HttpClientRequest get(RequestOptions requestOptions);

    HttpClientRequest get(int i, String str, String str2);

    HttpClientRequest get(String str, String str2);

    @Deprecated
    HttpClientRequest get(RequestOptions requestOptions, Handler<HttpClientResponse> handler);

    @Deprecated
    HttpClientRequest get(int i, String str, String str2, Handler<HttpClientResponse> handler);

    @Deprecated
    HttpClientRequest get(String str, String str2, Handler<HttpClientResponse> handler);

    HttpClientRequest get(String str);

    @Deprecated
    HttpClientRequest get(String str, Handler<HttpClientResponse> handler);

    HttpClientRequest getAbs(String str);

    @Deprecated
    HttpClientRequest getAbs(String str, Handler<HttpClientResponse> handler);

    @Fluent
    @Deprecated
    HttpClient getNow(RequestOptions requestOptions, Handler<HttpClientResponse> handler);

    @Fluent
    @Deprecated
    HttpClient getNow(int i, String str, String str2, Handler<HttpClientResponse> handler);

    @Fluent
    @Deprecated
    HttpClient getNow(String str, String str2, Handler<HttpClientResponse> handler);

    @Fluent
    @Deprecated
    HttpClient getNow(String str, Handler<HttpClientResponse> handler);

    HttpClientRequest post(RequestOptions requestOptions);

    HttpClientRequest post(int i, String str, String str2);

    HttpClientRequest post(String str, String str2);

    @Deprecated
    HttpClientRequest post(RequestOptions requestOptions, Handler<HttpClientResponse> handler);

    @Deprecated
    HttpClientRequest post(int i, String str, String str2, Handler<HttpClientResponse> handler);

    @Deprecated
    HttpClientRequest post(String str, String str2, Handler<HttpClientResponse> handler);

    HttpClientRequest post(String str);

    @Deprecated
    HttpClientRequest post(String str, Handler<HttpClientResponse> handler);

    HttpClientRequest postAbs(String str);

    @Deprecated
    HttpClientRequest postAbs(String str, Handler<HttpClientResponse> handler);

    HttpClientRequest head(RequestOptions requestOptions);

    HttpClientRequest head(int i, String str, String str2);

    HttpClientRequest head(String str, String str2);

    @Deprecated
    HttpClientRequest head(RequestOptions requestOptions, Handler<HttpClientResponse> handler);

    @Deprecated
    HttpClientRequest head(int i, String str, String str2, Handler<HttpClientResponse> handler);

    @Deprecated
    HttpClientRequest head(String str, String str2, Handler<HttpClientResponse> handler);

    HttpClientRequest head(String str);

    @Deprecated
    HttpClientRequest head(String str, Handler<HttpClientResponse> handler);

    HttpClientRequest headAbs(String str);

    @Deprecated
    HttpClientRequest headAbs(String str, Handler<HttpClientResponse> handler);

    @Fluent
    @Deprecated
    HttpClient headNow(RequestOptions requestOptions, Handler<HttpClientResponse> handler);

    @Fluent
    @Deprecated
    HttpClient headNow(int i, String str, String str2, Handler<HttpClientResponse> handler);

    @Fluent
    @Deprecated
    HttpClient headNow(String str, String str2, Handler<HttpClientResponse> handler);

    @Fluent
    @Deprecated
    HttpClient headNow(String str, Handler<HttpClientResponse> handler);

    HttpClientRequest options(RequestOptions requestOptions);

    HttpClientRequest options(int i, String str, String str2);

    HttpClientRequest options(String str, String str2);

    @Deprecated
    HttpClientRequest options(RequestOptions requestOptions, Handler<HttpClientResponse> handler);

    @Deprecated
    HttpClientRequest options(int i, String str, String str2, Handler<HttpClientResponse> handler);

    @Deprecated
    HttpClientRequest options(String str, String str2, Handler<HttpClientResponse> handler);

    HttpClientRequest options(String str);

    @Deprecated
    HttpClientRequest options(String str, Handler<HttpClientResponse> handler);

    HttpClientRequest optionsAbs(String str);

    @Deprecated
    HttpClientRequest optionsAbs(String str, Handler<HttpClientResponse> handler);

    @Fluent
    @Deprecated
    HttpClient optionsNow(RequestOptions requestOptions, Handler<HttpClientResponse> handler);

    @Fluent
    @Deprecated
    HttpClient optionsNow(int i, String str, String str2, Handler<HttpClientResponse> handler);

    @Fluent
    @Deprecated
    HttpClient optionsNow(String str, String str2, Handler<HttpClientResponse> handler);

    @Fluent
    @Deprecated
    HttpClient optionsNow(String str, Handler<HttpClientResponse> handler);

    HttpClientRequest put(RequestOptions requestOptions);

    HttpClientRequest put(int i, String str, String str2);

    HttpClientRequest put(String str, String str2);

    @Deprecated
    HttpClientRequest put(RequestOptions requestOptions, Handler<HttpClientResponse> handler);

    @Deprecated
    HttpClientRequest put(int i, String str, String str2, Handler<HttpClientResponse> handler);

    @Deprecated
    HttpClientRequest put(String str, String str2, Handler<HttpClientResponse> handler);

    HttpClientRequest put(String str);

    @Deprecated
    HttpClientRequest put(String str, Handler<HttpClientResponse> handler);

    HttpClientRequest putAbs(String str);

    @Deprecated
    HttpClientRequest putAbs(String str, Handler<HttpClientResponse> handler);

    HttpClientRequest delete(RequestOptions requestOptions);

    HttpClientRequest delete(int i, String str, String str2);

    HttpClientRequest delete(String str, String str2);

    @Deprecated
    HttpClientRequest delete(RequestOptions requestOptions, Handler<HttpClientResponse> handler);

    @Deprecated
    HttpClientRequest delete(int i, String str, String str2, Handler<HttpClientResponse> handler);

    @Deprecated
    HttpClientRequest delete(String str, String str2, Handler<HttpClientResponse> handler);

    HttpClientRequest delete(String str);

    @Deprecated
    HttpClientRequest delete(String str, Handler<HttpClientResponse> handler);

    HttpClientRequest deleteAbs(String str);

    @Deprecated
    HttpClientRequest deleteAbs(String str, Handler<HttpClientResponse> handler);

    @Fluent
    @Deprecated
    HttpClient websocket(RequestOptions requestOptions, Handler<WebSocket> handler);

    @Fluent
    @Deprecated
    HttpClient websocket(int i, String str, String str2, Handler<WebSocket> handler);

    @Fluent
    @Deprecated
    HttpClient websocket(RequestOptions requestOptions, Handler<WebSocket> handler, Handler<Throwable> handler2);

    @Fluent
    @Deprecated
    HttpClient websocket(int i, String str, String str2, Handler<WebSocket> handler, Handler<Throwable> handler2);

    @Fluent
    @Deprecated
    HttpClient websocket(String str, String str2, Handler<WebSocket> handler);

    @Fluent
    @Deprecated
    HttpClient websocket(String str, String str2, Handler<WebSocket> handler, Handler<Throwable> handler2);

    @Fluent
    @Deprecated
    HttpClient websocket(RequestOptions requestOptions, MultiMap multiMap, Handler<WebSocket> handler);

    @Fluent
    @Deprecated
    HttpClient websocket(int i, String str, String str2, MultiMap multiMap, Handler<WebSocket> handler);

    @Fluent
    @Deprecated
    HttpClient websocket(RequestOptions requestOptions, MultiMap multiMap, Handler<WebSocket> handler, Handler<Throwable> handler2);

    @Fluent
    @Deprecated
    HttpClient websocket(int i, String str, String str2, MultiMap multiMap, Handler<WebSocket> handler, Handler<Throwable> handler2);

    @Fluent
    @Deprecated
    HttpClient websocket(String str, String str2, MultiMap multiMap, Handler<WebSocket> handler);

    @Fluent
    @Deprecated
    HttpClient websocket(String str, String str2, MultiMap multiMap, Handler<WebSocket> handler, Handler<Throwable> handler2);

    @Fluent
    @Deprecated
    HttpClient websocket(RequestOptions requestOptions, MultiMap multiMap, WebsocketVersion websocketVersion, Handler<WebSocket> handler);

    @Fluent
    @Deprecated
    HttpClient websocket(int i, String str, String str2, MultiMap multiMap, WebsocketVersion websocketVersion, Handler<WebSocket> handler);

    @Fluent
    @Deprecated
    HttpClient websocket(RequestOptions requestOptions, MultiMap multiMap, WebsocketVersion websocketVersion, Handler<WebSocket> handler, Handler<Throwable> handler2);

    @Fluent
    @Deprecated
    HttpClient websocket(int i, String str, String str2, MultiMap multiMap, WebsocketVersion websocketVersion, Handler<WebSocket> handler, Handler<Throwable> handler2);

    @Fluent
    @Deprecated
    HttpClient websocket(String str, String str2, MultiMap multiMap, WebsocketVersion websocketVersion, Handler<WebSocket> handler);

    @Fluent
    @Deprecated
    HttpClient websocket(String str, String str2, MultiMap multiMap, WebsocketVersion websocketVersion, Handler<WebSocket> handler, Handler<Throwable> handler2);

    @Fluent
    @Deprecated
    HttpClient websocket(RequestOptions requestOptions, MultiMap multiMap, WebsocketVersion websocketVersion, String str, Handler<WebSocket> handler);

    @Fluent
    @Deprecated
    HttpClient websocket(int i, String str, String str2, MultiMap multiMap, WebsocketVersion websocketVersion, String str3, Handler<WebSocket> handler);

    @Fluent
    @Deprecated
    HttpClient websocketAbs(String str, MultiMap multiMap, WebsocketVersion websocketVersion, String str2, Handler<WebSocket> handler, Handler<Throwable> handler2);

    @Fluent
    @Deprecated
    HttpClient websocket(RequestOptions requestOptions, MultiMap multiMap, WebsocketVersion websocketVersion, String str, Handler<WebSocket> handler, Handler<Throwable> handler2);

    @Fluent
    @Deprecated
    HttpClient websocket(int i, String str, String str2, MultiMap multiMap, WebsocketVersion websocketVersion, String str3, Handler<WebSocket> handler, Handler<Throwable> handler2);

    @Fluent
    @Deprecated
    HttpClient websocket(String str, String str2, MultiMap multiMap, WebsocketVersion websocketVersion, String str3, Handler<WebSocket> handler);

    @Fluent
    @Deprecated
    HttpClient websocket(String str, String str2, MultiMap multiMap, WebsocketVersion websocketVersion, String str3, Handler<WebSocket> handler, Handler<Throwable> handler2);

    @Fluent
    @Deprecated
    HttpClient websocket(String str, Handler<WebSocket> handler);

    @Fluent
    @Deprecated
    HttpClient websocket(String str, Handler<WebSocket> handler, Handler<Throwable> handler2);

    @Fluent
    @Deprecated
    HttpClient websocket(String str, MultiMap multiMap, Handler<WebSocket> handler);

    @Fluent
    @Deprecated
    HttpClient websocket(String str, MultiMap multiMap, Handler<WebSocket> handler, Handler<Throwable> handler2);

    @Fluent
    @Deprecated
    HttpClient websocket(String str, MultiMap multiMap, WebsocketVersion websocketVersion, Handler<WebSocket> handler);

    @Fluent
    @Deprecated
    HttpClient websocket(String str, MultiMap multiMap, WebsocketVersion websocketVersion, Handler<WebSocket> handler, Handler<Throwable> handler2);

    @Fluent
    @Deprecated
    HttpClient websocket(String str, MultiMap multiMap, WebsocketVersion websocketVersion, String str2, Handler<WebSocket> handler);

    @Fluent
    @Deprecated
    HttpClient websocket(String str, MultiMap multiMap, WebsocketVersion websocketVersion, String str2, Handler<WebSocket> handler, Handler<Throwable> handler2);

    void webSocket(int i, String str, String str2, Handler<AsyncResult<WebSocket>> handler);

    void webSocket(String str, String str2, Handler<AsyncResult<WebSocket>> handler);

    void webSocket(String str, Handler<AsyncResult<WebSocket>> handler);

    void webSocket(WebSocketConnectOptions webSocketConnectOptions, Handler<AsyncResult<WebSocket>> handler);

    void webSocketAbs(String str, MultiMap multiMap, WebsocketVersion websocketVersion, List<String> list, Handler<AsyncResult<WebSocket>> handler);

    @Deprecated
    ReadStream<WebSocket> websocketStream(RequestOptions requestOptions);

    @Deprecated
    ReadStream<WebSocket> websocketStream(int i, String str, String str2);

    @Deprecated
    ReadStream<WebSocket> websocketStream(String str, String str2);

    @Deprecated
    ReadStream<WebSocket> websocketStream(RequestOptions requestOptions, MultiMap multiMap);

    @Deprecated
    ReadStream<WebSocket> websocketStream(int i, String str, String str2, MultiMap multiMap);

    @Deprecated
    ReadStream<WebSocket> websocketStream(String str, String str2, MultiMap multiMap);

    @Deprecated
    ReadStream<WebSocket> websocketStream(RequestOptions requestOptions, MultiMap multiMap, WebsocketVersion websocketVersion);

    @Deprecated
    ReadStream<WebSocket> websocketStream(int i, String str, String str2, MultiMap multiMap, WebsocketVersion websocketVersion);

    @Deprecated
    ReadStream<WebSocket> websocketStream(String str, String str2, MultiMap multiMap, WebsocketVersion websocketVersion);

    @Deprecated
    ReadStream<WebSocket> websocketStreamAbs(String str, MultiMap multiMap, WebsocketVersion websocketVersion, String str2);

    @Deprecated
    ReadStream<WebSocket> websocketStream(RequestOptions requestOptions, MultiMap multiMap, WebsocketVersion websocketVersion, String str);

    @Deprecated
    ReadStream<WebSocket> websocketStream(int i, String str, String str2, MultiMap multiMap, WebsocketVersion websocketVersion, String str3);

    @Deprecated
    ReadStream<WebSocket> websocketStream(String str, String str2, MultiMap multiMap, WebsocketVersion websocketVersion, String str3);

    @Deprecated
    ReadStream<WebSocket> websocketStream(String str);

    @Deprecated
    ReadStream<WebSocket> websocketStream(String str, MultiMap multiMap);

    @Deprecated
    ReadStream<WebSocket> websocketStream(String str, MultiMap multiMap, WebsocketVersion websocketVersion);

    @Deprecated
    ReadStream<WebSocket> websocketStream(String str, MultiMap multiMap, WebsocketVersion websocketVersion, String str2);

    @Fluent
    HttpClient connectionHandler(Handler<HttpConnection> handler);

    @Fluent
    HttpClient redirectHandler(Function<HttpClientResponse, Future<HttpClientRequest>> function);

    @GenIgnore
    Function<HttpClientResponse, Future<HttpClientRequest>> redirectHandler();

    void close();
}
