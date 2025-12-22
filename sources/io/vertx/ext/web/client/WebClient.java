package io.vertx.ext.web.client;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.RequestOptions;
import io.vertx.core.http.impl.HttpClientImpl;
import io.vertx.core.net.SocketAddress;
import io.vertx.ext.web.client.impl.WebClientBase;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-client-3.8.5.jar:io/vertx/ext/web/client/WebClient.class */
public interface WebClient {
    HttpRequest<Buffer> request(HttpMethod httpMethod, int i, String str, String str2);

    HttpRequest<Buffer> request(HttpMethod httpMethod, SocketAddress socketAddress, int i, String str, String str2);

    HttpRequest<Buffer> request(HttpMethod httpMethod, String str, String str2);

    HttpRequest<Buffer> request(HttpMethod httpMethod, SocketAddress socketAddress, String str, String str2);

    HttpRequest<Buffer> request(HttpMethod httpMethod, String str);

    HttpRequest<Buffer> request(HttpMethod httpMethod, SocketAddress socketAddress, String str);

    HttpRequest<Buffer> request(HttpMethod httpMethod, RequestOptions requestOptions);

    HttpRequest<Buffer> request(HttpMethod httpMethod, SocketAddress socketAddress, RequestOptions requestOptions);

    HttpRequest<Buffer> requestAbs(HttpMethod httpMethod, String str);

    HttpRequest<Buffer> requestAbs(HttpMethod httpMethod, SocketAddress socketAddress, String str);

    HttpRequest<Buffer> get(String str);

    HttpRequest<Buffer> get(int i, String str, String str2);

    HttpRequest<Buffer> get(String str, String str2);

    HttpRequest<Buffer> getAbs(String str);

    HttpRequest<Buffer> post(String str);

    HttpRequest<Buffer> post(int i, String str, String str2);

    HttpRequest<Buffer> post(String str, String str2);

    HttpRequest<Buffer> postAbs(String str);

    HttpRequest<Buffer> put(String str);

    HttpRequest<Buffer> put(int i, String str, String str2);

    HttpRequest<Buffer> put(String str, String str2);

    HttpRequest<Buffer> putAbs(String str);

    HttpRequest<Buffer> delete(String str);

    HttpRequest<Buffer> delete(int i, String str, String str2);

    HttpRequest<Buffer> delete(String str, String str2);

    HttpRequest<Buffer> deleteAbs(String str);

    HttpRequest<Buffer> patch(String str);

    HttpRequest<Buffer> patch(int i, String str, String str2);

    HttpRequest<Buffer> patch(String str, String str2);

    HttpRequest<Buffer> patchAbs(String str);

    HttpRequest<Buffer> head(String str);

    HttpRequest<Buffer> head(int i, String str, String str2);

    HttpRequest<Buffer> head(String str, String str2);

    HttpRequest<Buffer> headAbs(String str);

    HttpRequest<Buffer> raw(String str, String str2);

    HttpRequest<Buffer> raw(String str, int i, String str2, String str3);

    HttpRequest<Buffer> raw(String str, String str2, String str3);

    HttpRequest<Buffer> rawAbs(String str, String str2);

    void close();

    static WebClient create(Vertx vertx) {
        WebClientOptions options = new WebClientOptions();
        return create(vertx, options);
    }

    static WebClient create(Vertx vertx, WebClientOptions options) {
        return new WebClientBase(vertx.createHttpClient(options), options);
    }

    static WebClient wrap(HttpClient httpClient) {
        return wrap(httpClient, new WebClientOptions());
    }

    static WebClient wrap(HttpClient httpClient, WebClientOptions options) {
        WebClientOptions actualOptions = new WebClientOptions(((HttpClientImpl) httpClient).getOptions());
        actualOptions.init(options);
        return new WebClientBase(httpClient, actualOptions);
    }
}
