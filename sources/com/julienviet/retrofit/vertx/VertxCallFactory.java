package com.julienviet.retrofit.vertx;

import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpMethod;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/* loaded from: reader.jar:BOOT-INF/lib/retrofit-vertx-1.1.3.jar:com/julienviet/retrofit/vertx/VertxCallFactory.class */
public class VertxCallFactory implements Call.Factory {
    private final HttpClient client;

    public VertxCallFactory(HttpClient client) {
        this.client = client;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/retrofit-vertx-1.1.3.jar:com/julienviet/retrofit/vertx/VertxCallFactory$VertxCall.class */
    private class VertxCall implements Call {
        private final Request retroRequest;
        private final AtomicBoolean executed = new AtomicBoolean();

        VertxCall(Request retroRequest) {
            this.retroRequest = retroRequest;
        }

        @Override // okhttp3.Call
        public Request request() {
            return this.retroRequest;
        }

        @Override // okhttp3.Call
        public Response execute() throws IOException {
            final CompletableFuture<Response> future = new CompletableFuture<>();
            enqueue(new Callback() { // from class: com.julienviet.retrofit.vertx.VertxCallFactory.VertxCall.1
                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) throws IOException {
                    future.complete(response);
                }

                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException e) {
                    future.completeExceptionally(e);
                }
            });
            try {
                return future.get(10L, TimeUnit.SECONDS);
            } catch (Exception e) {
                throw new IOException(e);
            }
        }

        @Override // okhttp3.Call
        public void enqueue(Callback callback) {
            if (this.executed.compareAndSet(false, true)) {
                Future<Response> fut = Future.future();
                fut.setHandler2(ar -> {
                    IOException ioe;
                    if (ar.succeeded()) {
                        try {
                            callback.onResponse(this, (Response) ar.result());
                            return;
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    Throwable cause = ar.cause();
                    if (cause instanceof IOException) {
                        ioe = (IOException) cause;
                    } else {
                        ioe = new IOException(cause);
                    }
                    callback.onFailure(this, ioe);
                });
                HttpMethod method = HttpMethod.valueOf(this.retroRequest.method());
                HttpClientRequest request = VertxCallFactory.this.client.requestAbs(method, this.retroRequest.url().toString(), resp -> {
                    fut.getClass();
                    resp.exceptionHandler(fut::tryFail);
                    resp.bodyHandler(body -> {
                        Response.Builder builder = new Response.Builder();
                        builder.protocol(Protocol.HTTP_1_1);
                        builder.request(this.retroRequest);
                        builder.code(resp.statusCode());
                        builder.message(resp.statusMessage());
                        for (Map.Entry<String, String> header : resp.headers()) {
                            builder.addHeader(header.getKey(), header.getValue());
                        }
                        String mediaTypeHeader = resp.getHeader("Content-Type");
                        MediaType mediaType = mediaTypeHeader != null ? MediaType.parse(mediaTypeHeader) : null;
                        builder.body(ResponseBody.create(mediaType, body.getBytes()));
                        fut.tryComplete(builder.build());
                    });
                });
                fut.getClass();
                request.exceptionHandler(fut::tryFail);
                int size = this.retroRequest.headers().size();
                Headers retroHeaders = this.retroRequest.headers();
                MultiMap headers = request.headers();
                for (int i = 0; i < size; i++) {
                    String header = retroHeaders.name(i);
                    String value = retroHeaders.value(i);
                    headers.add(header, value);
                }
                try {
                    RequestBody body = this.retroRequest.body();
                    if (body != null && body.contentLength() > 0) {
                        MediaType mediaType = body.contentType();
                        request.putHeader("content-type", mediaType.toString());
                        request.putHeader("content-length", "" + body.contentLength());
                        Buffer buffer = new Buffer();
                        body.writeTo(buffer);
                        request.write(io.vertx.core.buffer.Buffer.buffer(buffer.readByteArray()));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                request.end();
                return;
            }
            callback.onFailure(this, new IOException("Already executed"));
        }

        @Override // okhttp3.Call
        public void cancel() {
        }

        @Override // okhttp3.Call
        public boolean isExecuted() {
            return this.executed.get();
        }

        @Override // okhttp3.Call
        public boolean isCanceled() {
            return false;
        }

        @Override // okhttp3.Call
        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public Call m946clone() {
            return VertxCallFactory.this.new VertxCall(this.retroRequest);
        }
    }

    @Override // okhttp3.Call.Factory
    public Call newCall(Request request) {
        return new VertxCall(request);
    }
}
