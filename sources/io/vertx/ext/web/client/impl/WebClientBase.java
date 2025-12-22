package io.vertx.ext.web.client.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.VertxException;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.RequestOptions;
import io.vertx.core.http.impl.HttpClientImpl;
import io.vertx.core.net.SocketAddress;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.ext.web.client.impl.predicate.PredicateInterceptor;
import io.vertx.ext.web.codec.impl.BodyCodecImpl;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-client-3.8.5.jar:io/vertx/ext/web/client/impl/WebClientBase.class */
public class WebClientBase implements WebClientInternal {
    final HttpClient client;
    final WebClientOptions options;
    private final List<Handler<HttpContext<?>>> interceptors;

    public WebClientBase(HttpClient client, WebClientOptions options) {
        this.client = client;
        this.options = new WebClientOptions(options);
        this.interceptors = new CopyOnWriteArrayList();
        addInterceptor(new PredicateInterceptor());
    }

    WebClientBase(WebClientBase webClient) {
        this.client = webClient.client;
        this.options = new WebClientOptions(webClient.options);
        this.interceptors = new CopyOnWriteArrayList(webClient.interceptors);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> get(int port, String host, String requestURI) {
        return request(HttpMethod.GET, port, host, requestURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> get(String requestURI) {
        return request(HttpMethod.GET, requestURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> get(String host, String requestURI) {
        return request(HttpMethod.GET, host, requestURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> getAbs(String absoluteURI) {
        return requestAbs(HttpMethod.GET, absoluteURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> post(String requestURI) {
        return request(HttpMethod.POST, requestURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> post(String host, String requestURI) {
        return request(HttpMethod.POST, host, requestURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> post(int port, String host, String requestURI) {
        return request(HttpMethod.POST, port, host, requestURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> put(String requestURI) {
        return request(HttpMethod.PUT, requestURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> put(String host, String requestURI) {
        return request(HttpMethod.PUT, host, requestURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> put(int port, String host, String requestURI) {
        return request(HttpMethod.PUT, port, host, requestURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> delete(String host, String requestURI) {
        return request(HttpMethod.DELETE, host, requestURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> delete(String requestURI) {
        return request(HttpMethod.DELETE, requestURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> delete(int port, String host, String requestURI) {
        return request(HttpMethod.DELETE, port, host, requestURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> patch(String requestURI) {
        return request(HttpMethod.PATCH, requestURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> patch(String host, String requestURI) {
        return request(HttpMethod.PATCH, host, requestURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> patch(int port, String host, String requestURI) {
        return request(HttpMethod.PATCH, port, host, requestURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> head(String requestURI) {
        return request(HttpMethod.HEAD, requestURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> head(String host, String requestURI) {
        return request(HttpMethod.HEAD, host, requestURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> head(int port, String host, String requestURI) {
        return request(HttpMethod.HEAD, port, host, requestURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> raw(String customHttpMethod, String requestURI) {
        return request(HttpMethod.OTHER, requestURI).rawMethod(customHttpMethod);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> raw(String customHttpMethod, int port, String host, String requestURI) {
        return request(HttpMethod.OTHER, port, host, requestURI).rawMethod(customHttpMethod);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> raw(String customHttpMethod, String host, String requestURI) {
        return request(HttpMethod.OTHER, host, requestURI).rawMethod(customHttpMethod);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> postAbs(String absoluteURI) {
        return requestAbs(HttpMethod.POST, absoluteURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> putAbs(String absoluteURI) {
        return requestAbs(HttpMethod.PUT, absoluteURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> deleteAbs(String absoluteURI) {
        return requestAbs(HttpMethod.DELETE, absoluteURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> patchAbs(String absoluteURI) {
        return requestAbs(HttpMethod.PATCH, absoluteURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> headAbs(String absoluteURI) {
        return requestAbs(HttpMethod.HEAD, absoluteURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> rawAbs(String customHttpMethod, String absoluteURI) {
        return requestAbs(HttpMethod.OTHER, absoluteURI).rawMethod(customHttpMethod);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> request(HttpMethod method, String requestURI) {
        return request(method, (SocketAddress) null, requestURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> request(HttpMethod method, SocketAddress serverAddress, String requestURI) {
        return new HttpRequestImpl(this, method, serverAddress, Boolean.valueOf(this.options.isSsl()), this.options.getDefaultPort(), this.options.getDefaultHost(), requestURI, BodyCodecImpl.BUFFER, this.options);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> request(HttpMethod method, RequestOptions requestOptions) {
        return request(method, (SocketAddress) null, requestOptions);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> request(HttpMethod method, SocketAddress serverAddress, RequestOptions requestOptions) {
        return new HttpRequestImpl(this, method, serverAddress, requestOptions.isSsl(), requestOptions.getPort(), requestOptions.getHost(), requestOptions.getURI(), BodyCodecImpl.BUFFER, this.options);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> request(HttpMethod method, String host, String requestURI) {
        return request(method, (SocketAddress) null, host, requestURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> request(HttpMethod method, SocketAddress serverAddress, String host, String requestURI) {
        return new HttpRequestImpl(this, method, serverAddress, Boolean.valueOf(this.options.isSsl()), this.options.getDefaultPort(), host, requestURI, BodyCodecImpl.BUFFER, this.options);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> request(HttpMethod method, int port, String host, String requestURI) {
        return request(method, null, port, host, requestURI);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> request(HttpMethod method, SocketAddress serverAddress, int port, String host, String requestURI) {
        return new HttpRequestImpl(this, method, serverAddress, Boolean.valueOf(this.options.isSsl()), port, host, requestURI, BodyCodecImpl.BUFFER, this.options);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> requestAbs(HttpMethod method, String surl) {
        return requestAbs(method, null, surl);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public HttpRequest<Buffer> requestAbs(HttpMethod method, SocketAddress serverAddress, String surl) {
        try {
            URL url = new URL(surl);
            boolean ssl = false;
            int port = url.getPort();
            String protocol = url.getProtocol();
            if ("ftp".equals(protocol)) {
                if (port == -1) {
                    port = 21;
                }
            } else {
                char chend = protocol.charAt(protocol.length() - 1);
                if (chend == 'p') {
                    if (port == -1) {
                        port = 80;
                    }
                } else if (chend == 's') {
                    ssl = true;
                    if (port == -1) {
                        port = 443;
                    }
                }
            }
            return new HttpRequestImpl(this, method, serverAddress, protocol, Boolean.valueOf(ssl), port, url.getHost(), url.getFile(), BodyCodecImpl.BUFFER, this.options);
        } catch (MalformedURLException e) {
            throw new VertxException("Invalid url: " + surl);
        }
    }

    @Override // io.vertx.ext.web.client.impl.WebClientInternal
    public WebClientInternal addInterceptor(Handler<HttpContext<?>> interceptor) {
        this.interceptors.add(interceptor);
        return this;
    }

    @Override // io.vertx.ext.web.client.impl.WebClientInternal
    public <T> HttpContext<T> createContext(Handler<AsyncResult<HttpResponse<T>>> handler) {
        HttpClientImpl client = (HttpClientImpl) this.client;
        return new HttpContext<>(client.getVertx().getOrCreateContext(), client, this.interceptors, handler);
    }

    @Override // io.vertx.ext.web.client.WebClient
    public void close() {
        this.client.close();
    }
}
