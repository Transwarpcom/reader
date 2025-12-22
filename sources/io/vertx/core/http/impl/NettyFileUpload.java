package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.multipart.FileUpload;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.impl.InboundBuffer;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/NettyFileUpload.class */
final class NettyFileUpload implements FileUpload, ReadStream<Buffer> {
    private final String name;
    private String contentType;
    private String filename;
    private String contentTransferEncoding;
    private Charset charset;
    private boolean completed;
    private long maxSize = -1;
    private final HttpServerRequest request;
    private final InboundBuffer<Object> pending;
    private Handler<Void> endHandler;
    private Handler<Throwable> exceptionHandler;
    private Handler<Buffer> dataHandler;

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: endHandler, reason: avoid collision after fix types in other method */
    public /* bridge */ /* synthetic */ ReadStream<Buffer> endHandler2(Handler handler) {
        return endHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ ReadStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    NettyFileUpload(Context context, HttpServerRequest request, String name, String filename, String contentType, String contentTransferEncoding, Charset charset) {
        this.name = name;
        this.filename = filename;
        this.contentType = contentType;
        this.contentTransferEncoding = contentTransferEncoding;
        this.charset = charset;
        this.request = request;
        this.pending = new InboundBuffer(context).drainHandler(v -> {
            request.resume2();
        }).handler(buff -> {
            if (buff == InboundBuffer.END_SENTINEL) {
                Handler<Void> handler = endHandler();
                if (handler != null) {
                    handler.handle(null);
                    return;
                }
                return;
            }
            Handler<Buffer> handler2 = handler();
            if (handler2 != null) {
                handler2.handle((Buffer) buff);
            }
        });
    }

    @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public synchronized NettyFileUpload exceptionHandler(Handler<Throwable> handler) {
        this.exceptionHandler = handler;
        return this;
    }

    private Handler<Buffer> handler() {
        return this.dataHandler;
    }

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    public synchronized ReadStream<Buffer> handler2(Handler<Buffer> handler) {
        this.dataHandler = handler;
        return this;
    }

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    public ReadStream<Buffer> pause2() {
        this.pending.pause();
        return this;
    }

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    public ReadStream<Buffer> resume2() {
        return fetch2(Long.MAX_VALUE);
    }

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    public ReadStream<Buffer> fetch2(long amount) {
        this.pending.fetch(amount);
        return this;
    }

    private synchronized Handler<Void> endHandler() {
        return this.endHandler;
    }

    @Override // io.vertx.core.streams.ReadStream
    public synchronized ReadStream<Buffer> endHandler(Handler<Void> handler) {
        this.endHandler = handler;
        return this;
    }

    private void receiveData(Buffer data) {
        if (data.length() != 0 && !this.pending.write((InboundBuffer<Object>) data)) {
            this.request.pause2();
        }
    }

    private void end() {
        this.pending.write((InboundBuffer<Object>) InboundBuffer.END_SENTINEL);
    }

    public void handleException(Throwable err) {
        Handler<Throwable> handler;
        synchronized (this) {
            handler = this.exceptionHandler;
        }
        if (handler != null) {
            handler.handle(err);
        }
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public void setContent(ByteBuf channelBuffer) throws IOException {
        this.completed = true;
        receiveData(Buffer.buffer(channelBuffer));
        end();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public void addContent(ByteBuf channelBuffer, boolean last) throws IOException {
        receiveData(Buffer.buffer(channelBuffer));
        if (last) {
            this.completed = true;
            end();
        }
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public void setContent(File file) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public void setContent(InputStream inputStream) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public boolean isCompleted() {
        return this.completed;
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public long length() {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public void delete() {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public long definedLength() {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public void checkSize(long newSize) throws IOException {
        if (this.maxSize >= 0 && newSize > this.maxSize) {
            throw new IOException("Size exceed allowed maximum capacity");
        }
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public long getMaxSize() {
        return this.maxSize;
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public byte[] get() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public ByteBuf getChunk(int i) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public String getString() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public String getString(Charset charset) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public Charset getCharset() {
        return this.charset;
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public boolean renameTo(File file) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public boolean isInMemory() {
        return false;
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public File getFile() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http.multipart.InterfaceHttpData
    public String getName() {
        return this.name;
    }

    @Override // io.netty.handler.codec.http.multipart.InterfaceHttpData
    public InterfaceHttpData.HttpDataType getHttpDataType() {
        throw new UnsupportedOperationException();
    }

    @Override // java.lang.Comparable
    public int compareTo(InterfaceHttpData o) {
        return 0;
    }

    @Override // io.netty.handler.codec.http.multipart.FileUpload
    public String getFilename() {
        return this.filename;
    }

    @Override // io.netty.handler.codec.http.multipart.FileUpload
    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override // io.netty.handler.codec.http.multipart.FileUpload
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override // io.netty.handler.codec.http.multipart.FileUpload
    public String getContentType() {
        return this.contentType;
    }

    @Override // io.netty.handler.codec.http.multipart.FileUpload
    public void setContentTransferEncoding(String contentTransferEncoding) {
        this.contentTransferEncoding = contentTransferEncoding;
    }

    @Override // io.netty.handler.codec.http.multipart.FileUpload
    public String getContentTransferEncoding() {
        return this.contentTransferEncoding;
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public ByteBuf getByteBuf() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.buffer.ByteBufHolder
    public FileUpload copy() {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.buffer.ByteBufHolder
    public FileUpload duplicate() {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.buffer.ByteBufHolder
    public FileUpload retainedDuplicate() {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.buffer.ByteBufHolder
    public FileUpload replace(ByteBuf content) {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.util.ReferenceCounted
    public FileUpload retain() {
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public FileUpload retain(int increment) {
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public FileUpload touch(Object hint) {
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public FileUpload touch() {
        return this;
    }

    @Override // io.netty.buffer.ByteBufHolder
    public ByteBuf content() {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.util.ReferenceCounted
    public int refCnt() {
        return 1;
    }

    @Override // io.netty.util.ReferenceCounted
    public boolean release() {
        return false;
    }

    @Override // io.netty.util.ReferenceCounted
    public boolean release(int decrement) {
        return false;
    }
}
