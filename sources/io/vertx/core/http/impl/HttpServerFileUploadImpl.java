package io.vertx.core.http.impl;

import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.http.HttpServerFileUpload;
import io.vertx.core.streams.Pipe;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import java.nio.charset.Charset;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/HttpServerFileUploadImpl.class */
class HttpServerFileUploadImpl implements HttpServerFileUpload {
    private final ReadStream<Buffer> stream;
    private final Context context;
    private final String name;
    private final String filename;
    private final String contentType;
    private final String contentTransferEncoding;
    private final Charset charset;
    private Handler<Buffer> dataHandler;
    private Handler<Void> endHandler;
    private AsyncFile file;
    private Handler<Throwable> exceptionHandler;
    private long size;
    private boolean lazyCalculateSize;

    @Override // io.vertx.core.http.HttpServerFileUpload, io.vertx.core.streams.ReadStream
    /* renamed from: endHandler, reason: avoid collision after fix types in other method */
    public /* bridge */ /* synthetic */ ReadStream<Buffer> endHandler2(Handler handler) {
        return endHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.http.HttpServerFileUpload, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ ReadStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.http.HttpServerFileUpload, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    HttpServerFileUploadImpl(Context context, ReadStream<Buffer> stream, String name, String filename, String contentType, String contentTransferEncoding, Charset charset, long size) {
        this.context = context;
        this.stream = stream;
        this.name = name;
        this.filename = filename;
        this.contentType = contentType;
        this.contentTransferEncoding = contentTransferEncoding;
        this.charset = charset;
        this.size = size;
        this.lazyCalculateSize = size == 0;
        stream.handler2(this::handleData);
        stream.endHandler(v -> {
            handleEnd();
        });
    }

    private void handleData(Buffer data) {
        Handler<Buffer> h;
        synchronized (this) {
            h = this.dataHandler;
            this.size += data.length();
        }
        if (h != null) {
            h.handle(data);
        }
    }

    private void handleEnd() {
        Handler<Void> handler;
        synchronized (this) {
            this.lazyCalculateSize = false;
            handler = this.endHandler;
        }
        if (handler != null) {
            handler.handle(null);
        }
    }

    private void notifyExceptionHandler(Throwable cause) {
        if (this.exceptionHandler != null) {
            this.exceptionHandler.handle(cause);
        }
    }

    @Override // io.vertx.core.http.HttpServerFileUpload
    public String filename() {
        return this.filename;
    }

    @Override // io.vertx.core.http.HttpServerFileUpload
    public String name() {
        return this.name;
    }

    @Override // io.vertx.core.http.HttpServerFileUpload
    public String contentType() {
        return this.contentType;
    }

    @Override // io.vertx.core.http.HttpServerFileUpload
    public String contentTransferEncoding() {
        return this.contentTransferEncoding;
    }

    @Override // io.vertx.core.http.HttpServerFileUpload
    public String charset() {
        return this.charset.toString();
    }

    @Override // io.vertx.core.http.HttpServerFileUpload
    public synchronized long size() {
        return this.size;
    }

    @Override // io.vertx.core.http.HttpServerFileUpload, io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    public synchronized ReadStream<Buffer> handler2(Handler<Buffer> handler) {
        this.dataHandler = handler;
        return this;
    }

    @Override // io.vertx.core.http.HttpServerFileUpload, io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    public ReadStream<Buffer> pause2() {
        this.stream.pause2();
        return this;
    }

    @Override // io.vertx.core.http.HttpServerFileUpload, io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    public ReadStream<Buffer> fetch2(long amount) {
        this.stream.fetch2(amount);
        return this;
    }

    @Override // io.vertx.core.http.HttpServerFileUpload, io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    public ReadStream<Buffer> resume2() {
        this.stream.resume2();
        return this;
    }

    @Override // io.vertx.core.http.HttpServerFileUpload, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public synchronized HttpServerFileUpload exceptionHandler(Handler<Throwable> exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
        return this;
    }

    @Override // io.vertx.core.http.HttpServerFileUpload, io.vertx.core.streams.ReadStream
    public synchronized ReadStream<Buffer> endHandler(Handler<Void> handler) {
        this.endHandler = handler;
        return this;
    }

    @Override // io.vertx.core.http.HttpServerFileUpload
    public HttpServerFileUpload streamToFileSystem(String filename) {
        Pipe<Buffer> pipe = this.stream.pipe().endOnComplete(false);
        this.context.owner().fileSystem().open(filename, new OpenOptions(), ar -> {
            if (ar.succeeded()) {
                this.file = (AsyncFile) ar.result();
                pipe.to(this.file, ar2 -> {
                    this.file.close(ar3 -> {
                        Throwable failure = ar2.failed() ? ar2.cause() : ar3.failed() ? ar3.cause() : null;
                        if (failure != null) {
                            notifyExceptionHandler(failure);
                        }
                        synchronized (this) {
                            this.size = this.file.getWritePos();
                        }
                        handleEnd();
                    });
                });
            } else {
                pipe.close();
                notifyExceptionHandler(ar.cause());
            }
        });
        return this;
    }

    @Override // io.vertx.core.http.HttpServerFileUpload
    public synchronized boolean isSizeAvailable() {
        return !this.lazyCalculateSize;
    }

    @Override // io.vertx.core.http.HttpServerFileUpload
    public synchronized AsyncFile file() {
        return this.file;
    }
}
