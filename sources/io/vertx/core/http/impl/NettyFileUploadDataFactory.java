package io.vertx.core.http.impl;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.FileUpload;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerFileUpload;
import io.vertx.core.http.HttpServerRequest;
import java.nio.charset.Charset;
import java.util.function.Supplier;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/NettyFileUploadDataFactory.class */
class NettyFileUploadDataFactory extends DefaultHttpDataFactory {
    final Context context;
    final HttpServerRequest request;
    final Supplier<Handler<HttpServerFileUpload>> lazyUploadHandler;

    NettyFileUploadDataFactory(Context context, HttpServerRequest request, Supplier<Handler<HttpServerFileUpload>> lazyUploadHandler) {
        super(false);
        this.context = context;
        this.request = request;
        this.lazyUploadHandler = lazyUploadHandler;
    }

    @Override // io.netty.handler.codec.http.multipart.DefaultHttpDataFactory, io.netty.handler.codec.http.multipart.HttpDataFactory
    public FileUpload createFileUpload(HttpRequest httpRequest, String name, String filename, String contentType, String contentTransferEncoding, Charset charset, long size) {
        NettyFileUpload nettyUpload = new NettyFileUpload(this.context, this.request, name, filename, contentType, contentTransferEncoding, charset);
        HttpServerFileUploadImpl upload = new HttpServerFileUploadImpl(this.context, nettyUpload, name, filename, contentType, contentTransferEncoding, charset, size);
        Handler<HttpServerFileUpload> uploadHandler = this.lazyUploadHandler.get();
        if (uploadHandler != null) {
            uploadHandler.handle(upload);
        }
        return nettyUpload;
    }
}
