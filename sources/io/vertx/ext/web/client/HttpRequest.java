package io.vertx.ext.web.client;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.core.streams.ReadStream;
import io.vertx.ext.web.client.predicate.ResponsePredicate;
import io.vertx.ext.web.client.predicate.ResponsePredicateResult;
import io.vertx.ext.web.codec.BodyCodec;
import io.vertx.ext.web.multipart.MultipartForm;
import java.util.function.Function;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-client-3.8.5.jar:io/vertx/ext/web/client/HttpRequest.class */
public interface HttpRequest<T> {
    @Fluent
    HttpRequest<T> method(HttpMethod httpMethod);

    @Fluent
    HttpRequest<T> rawMethod(String str);

    @Fluent
    HttpRequest<T> port(int i);

    <U> HttpRequest<U> as(BodyCodec<U> bodyCodec);

    @Fluent
    HttpRequest<T> host(String str);

    @Fluent
    HttpRequest<T> virtualHost(String str);

    @Fluent
    HttpRequest<T> uri(String str);

    @Fluent
    HttpRequest<T> putHeaders(MultiMap multiMap);

    @Fluent
    HttpRequest<T> putHeader(String str, String str2);

    @GenIgnore({"permitted-type"})
    @Fluent
    HttpRequest<T> putHeader(String str, Iterable<String> iterable);

    @CacheReturn
    MultiMap headers();

    @Fluent
    HttpRequest<T> basicAuthentication(String str, String str2);

    @Fluent
    HttpRequest<T> basicAuthentication(Buffer buffer, Buffer buffer2);

    @Fluent
    HttpRequest<T> bearerTokenAuthentication(String str);

    @Fluent
    HttpRequest<T> ssl(Boolean bool);

    @Fluent
    HttpRequest<T> timeout(long j);

    @Fluent
    HttpRequest<T> addQueryParam(String str, String str2);

    @Fluent
    HttpRequest<T> setQueryParam(String str, String str2);

    @Fluent
    HttpRequest<T> followRedirects(boolean z);

    @Fluent
    HttpRequest<T> expect(ResponsePredicate responsePredicate);

    MultiMap queryParams();

    HttpRequest<T> copy();

    HttpRequest<T> multipartMixed(boolean z);

    void sendStream(ReadStream<Buffer> readStream, Handler<AsyncResult<HttpResponse<T>>> handler);

    void sendBuffer(Buffer buffer, Handler<AsyncResult<HttpResponse<T>>> handler);

    void sendJsonObject(JsonObject jsonObject, Handler<AsyncResult<HttpResponse<T>>> handler);

    void sendJson(Object obj, Handler<AsyncResult<HttpResponse<T>>> handler);

    void sendForm(MultiMap multiMap, Handler<AsyncResult<HttpResponse<T>>> handler);

    void sendMultipartForm(MultipartForm multipartForm, Handler<AsyncResult<HttpResponse<T>>> handler);

    void send(Handler<AsyncResult<HttpResponse<T>>> handler);

    @Fluent
    default HttpRequest<T> expect(Function<HttpResponse<Void>, ResponsePredicateResult> predicate) {
        predicate.getClass();
        return expect((v1) -> {
            return r1.apply(v1);
        });
    }
}
