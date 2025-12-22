package io.vertx.ext.web.client.impl;

import io.netty.handler.codec.http.QueryStringEncoder;
import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder;
import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.RequestOptions;
import io.vertx.core.http.impl.HttpClientImpl;
import io.vertx.core.json.EncodeException;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.json.JsonCodec;
import io.vertx.core.streams.Pipe;
import io.vertx.core.streams.ReadStream;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.codec.spi.BodyStream;
import io.vertx.ext.web.multipart.MultipartForm;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-client-3.8.5.jar:io/vertx/ext/web/client/impl/HttpContext.class */
public class HttpContext<T> {
    private final Context context;
    private final Handler<AsyncResult<HttpResponse<T>>> handler;
    private final HttpClientImpl client;
    private final List<Handler<HttpContext<?>>> interceptors;
    private HttpRequestImpl<T> request;
    private Object body;
    private String contentType;
    private Map<String, Object> attrs;
    private Iterator<Handler<HttpContext<?>>> it;
    private ClientPhase phase;
    private HttpClientRequest clientRequest;
    private HttpClientResponse clientResponse;
    private HttpResponse<T> response;
    private Throwable failure;
    private int redirects;
    private List<String> redirectedLocations = new ArrayList();

    HttpContext(Context context, HttpClientImpl client, List<Handler<HttpContext<?>>> interceptors, Handler<AsyncResult<HttpResponse<T>>> handler) {
        this.context = context;
        this.handler = handler;
        this.client = client;
        this.interceptors = interceptors;
    }

    public HttpClientRequest clientRequest() {
        return this.clientRequest;
    }

    public HttpClientResponse clientResponse() {
        return this.clientResponse;
    }

    public ClientPhase phase() {
        return this.phase;
    }

    public HttpRequest<T> request() {
        return this.request;
    }

    public HttpResponse<T> response() {
        return this.response;
    }

    public HttpContext<T> response(HttpResponse<T> response) {
        this.response = response;
        return this;
    }

    public int redirects() {
        return this.redirects;
    }

    public HttpContext<T> redirects(int redirects) {
        this.redirects = redirects;
        return this;
    }

    public String contentType() {
        return this.contentType;
    }

    public Object body() {
        return this.body;
    }

    public Throwable failure() {
        return this.failure;
    }

    public List<String> getRedirectedLocations() {
        return this.redirectedLocations;
    }

    public void prepareRequest(HttpRequest<T> request, String contentType, Object body) {
        this.request = (HttpRequestImpl) request;
        this.contentType = contentType;
        this.body = body;
        fire(ClientPhase.PREPARE_REQUEST);
    }

    public void sendRequest(HttpClientRequest clientRequest) {
        this.clientRequest = clientRequest;
        fire(ClientPhase.SEND_REQUEST);
    }

    public void followRedirect() {
        fire(ClientPhase.SEND_REQUEST);
    }

    public void receiveResponse(HttpClientResponse clientResponse) {
        int sc = clientResponse.statusCode();
        int maxRedirects = this.request.followRedirects ? this.client.getOptions().getMaxRedirects() : 0;
        if (this.redirects < maxRedirects && sc >= 300 && sc < 400) {
            this.redirects++;
            Future<HttpClientRequest> next = this.client.redirectHandler().apply(clientResponse);
            if (next != null) {
                this.redirectedLocations.add(clientResponse.getHeader(HttpHeaders.LOCATION));
                next.setHandler2(ar -> {
                    if (ar.succeeded()) {
                        HttpClientRequest nextRequest = (HttpClientRequest) ar.result();
                        if (this.request.headers != null) {
                            nextRequest.headers().addAll(this.request.headers);
                        }
                        this.clientRequest = nextRequest;
                        this.clientResponse = clientResponse;
                        fire(ClientPhase.FOLLOW_REDIRECT);
                        return;
                    }
                    fail(ar.cause());
                });
                return;
            }
        }
        this.clientResponse = clientResponse;
        fire(ClientPhase.RECEIVE_RESPONSE);
    }

    public void dispatchResponse(HttpResponse<T> response) {
        this.response = response;
        fire(ClientPhase.DISPATCH_RESPONSE);
    }

    public boolean fail(Throwable cause) {
        if (this.phase == ClientPhase.FAILURE) {
            return false;
        }
        this.failure = cause;
        fire(ClientPhase.FAILURE);
        return true;
    }

    public void next() {
        if (this.it.hasNext()) {
            Handler<HttpContext<?>> next = this.it.next();
            next.handle(this);
        } else {
            this.it = null;
            execute();
        }
    }

    private void fire(ClientPhase phase) {
        this.phase = phase;
        this.it = this.interceptors.iterator();
        next();
    }

    private void execute() throws EncodeException {
        switch (this.phase) {
            case PREPARE_REQUEST:
                handlePrepareRequest();
                break;
            case SEND_REQUEST:
                handleSendRequest();
                break;
            case FOLLOW_REDIRECT:
                followRedirect();
                break;
            case RECEIVE_RESPONSE:
                handleReceiveResponse();
                break;
            case DISPATCH_RESPONSE:
                handleDispatchResponse();
                break;
            case FAILURE:
                handleFailure();
                break;
        }
    }

    private void handleFailure() {
        this.handler.handle(Future.failedFuture(this.failure));
    }

    private void handleDispatchResponse() {
        this.handler.handle(Future.succeededFuture(this.response));
    }

    private void handlePrepareRequest() {
        String requestURI;
        HttpClientRequest req;
        if (this.request.params != null && this.request.params.size() > 0) {
            QueryStringEncoder enc = new QueryStringEncoder(this.request.uri);
            this.request.params.forEach(param -> {
                enc.addParam((String) param.getKey(), (String) param.getValue());
            });
            requestURI = enc.toString();
        } else {
            requestURI = this.request.uri;
        }
        int port = this.request.port;
        String host = this.request.host;
        if (this.request.ssl != null && this.request.ssl.booleanValue() != this.request.options.isSsl()) {
            req = this.client.request(this.request.method, this.request.serverAddress, new RequestOptions().setSsl(this.request.ssl).setHost(host).setPort(port).setURI(requestURI));
        } else if (this.request.protocol != null && !this.request.protocol.equals("http") && !this.request.protocol.equals("https")) {
            try {
                URI uri = new URI(this.request.protocol, null, host, port, requestURI, null, null);
                req = this.client.requestAbs(this.request.method, this.request.serverAddress, uri.toString());
            } catch (URISyntaxException ex) {
                fail(ex);
                return;
            }
        } else {
            req = this.client.request(this.request.method, this.request.serverAddress, port, host, requestURI);
        }
        if (this.request.virtualHost != null) {
            String virtalHost = this.request.virtualHost;
            if (port != 80) {
                virtalHost = virtalHost + ":" + port;
            }
            req.setHost(virtalHost);
        }
        this.redirects = 0;
        if (this.request.headers != null) {
            req.headers().addAll(this.request.headers);
        }
        if (this.request.rawMethod != null) {
            req.setRawMethod(this.request.rawMethod);
        }
        sendRequest(req);
    }

    private void handleReceiveResponse() {
        HttpClientResponse resp = this.clientResponse;
        Context context = Vertx.currentContext();
        Promise<HttpResponse<T>> promise = Promise.promise();
        promise.future().setHandler2(r -> {
            context.runOnContext(v -> {
                if (r.succeeded()) {
                    dispatchResponse((HttpResponse) r.result());
                } else {
                    fail(r.cause());
                }
            });
        });
        resp.exceptionHandler(err -> {
            if (!promise.future().isComplete()) {
                promise.fail(err);
            }
        });
        Pipe<Buffer> pipe = resp.pipe();
        this.request.codec.create(ar1 -> {
            if (ar1.succeeded()) {
                BodyStream<T> stream = (BodyStream) ar1.result();
                pipe.to(stream, ar2 -> {
                    if (ar2.succeeded()) {
                        stream.result().setHandler2(ar3 -> {
                            if (ar3.succeeded()) {
                                promise.complete(new HttpResponseImpl(resp.version(), resp.statusCode(), resp.statusMessage(), resp.headers(), resp.trailers(), resp.cookies(), stream.result().result(), this.redirectedLocations));
                            } else {
                                promise.fail(ar3.cause());
                            }
                        });
                    } else {
                        promise.fail(ar2.cause());
                    }
                });
            } else {
                pipe.close();
                fail(ar1.cause());
            }
        });
    }

    private void handleSendRequest() throws EncodeException {
        Buffer buffer;
        Promise<HttpClientResponse> responseFuture = Promise.promise();
        responseFuture.future().setHandler2(ar -> {
            if (ar.succeeded()) {
                HttpClientResponse resp = (HttpClientResponse) ar.result();
                resp.pause2();
                receiveResponse(resp);
                return;
            }
            fail(ar.cause());
        });
        HttpClientRequest req = this.clientRequest;
        responseFuture.getClass();
        req.handler2((v1) -> {
            r1.tryComplete(v1);
        });
        if (this.request.timeout > 0) {
            req.setTimeout(this.request.timeout);
        }
        if (this.contentType != null) {
            String prev = req.headers().get(HttpHeaders.CONTENT_TYPE);
            if (prev == null) {
                req.putHeader(HttpHeaders.CONTENT_TYPE, this.contentType);
            } else {
                this.contentType = prev;
            }
        }
        if (this.body != null || "application/json".equals(this.contentType)) {
            if (this.body instanceof MultiMap) {
                MultipartForm parts = MultipartForm.create();
                MultiMap attributes = (MultiMap) this.body;
                for (Map.Entry<String, String> attribute : attributes) {
                    parts.attribute(attribute.getKey(), attribute.getValue());
                }
                this.body = parts;
            }
            if (this.body instanceof MultipartForm) {
                try {
                    boolean multipart = "multipart/form-data".equals(this.contentType);
                    HttpPostRequestEncoder.EncoderMode encoderMode = this.request.multipartMixed ? HttpPostRequestEncoder.EncoderMode.RFC1738 : HttpPostRequestEncoder.EncoderMode.HTML5;
                    MultipartFormUpload multipartForm = new MultipartFormUpload(this.context, (MultipartForm) this.body, multipart, encoderMode);
                    this.body = multipartForm;
                    for (String headerName : this.request.headers().names()) {
                        req.putHeader(headerName, this.request.headers().get(headerName));
                    }
                    multipartForm.headers().forEach(header -> {
                        req.putHeader((String) header.getKey(), (String) header.getValue());
                    });
                    multipartForm.run();
                } catch (Exception e) {
                    responseFuture.tryFail(e);
                    return;
                }
            }
            if (this.body instanceof ReadStream) {
                ReadStream<Buffer> stream = (ReadStream) this.body;
                if (this.request.headers == null || !this.request.headers.contains(HttpHeaders.CONTENT_LENGTH)) {
                    req.setChunked(true);
                }
                stream.pipeTo(req, ar2 -> {
                    if (ar2.failed()) {
                        responseFuture.tryFail(ar2.cause());
                        req.reset();
                    }
                });
                return;
            }
            if (this.body instanceof Buffer) {
                buffer = (Buffer) this.body;
            } else if (this.body instanceof JsonObject) {
                buffer = Buffer.buffer(((JsonObject) this.body).encode());
            } else {
                buffer = JsonCodec.INSTANCE.toBuffer(this.body);
            }
            responseFuture.getClass();
            req.exceptionHandler(responseFuture::tryFail);
            req.end(buffer);
            return;
        }
        responseFuture.getClass();
        req.exceptionHandler(responseFuture::tryFail);
        req.end();
    }

    public <T> T get(String str) {
        if (this.attrs != null) {
            return (T) this.attrs.get(str);
        }
        return null;
    }

    public HttpContext<T> set(String key, Object value) {
        if (value == null) {
            if (this.attrs != null) {
                this.attrs.remove(key);
            }
        } else {
            if (this.attrs == null) {
                this.attrs = new HashMap();
            }
            this.attrs.put(key, value);
        }
        return this;
    }
}
