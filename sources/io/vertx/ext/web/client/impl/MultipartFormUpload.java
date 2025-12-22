package io.vertx.ext.web.client.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpConstants;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.impl.HeadersAdaptor;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.impl.InboundBuffer;
import io.vertx.ext.web.multipart.FormDataPart;
import io.vertx.ext.web.multipart.MultipartForm;
import java.io.File;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-client-3.8.5.jar:io/vertx/ext/web/client/impl/MultipartFormUpload.class */
public class MultipartFormUpload implements ReadStream<Buffer> {
    private static final UnpooledByteBufAllocator ALLOC = new UnpooledByteBufAllocator(false);
    private DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, "/");
    private HttpPostRequestEncoder encoder;
    private Handler<Throwable> exceptionHandler;
    private Handler<Void> endHandler;
    private InboundBuffer<Buffer> pending;
    private boolean ended;
    private final Context context;

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

    public MultipartFormUpload(Context context, MultipartForm parts, boolean multipart, HttpPostRequestEncoder.EncoderMode encoderMode) throws Exception {
        this.context = context;
        this.pending = new InboundBuffer(context).emptyHandler(v -> {
            checkEnd();
        }).drainHandler(v2 -> {
            run();
        }).pause();
        this.encoder = new HttpPostRequestEncoder(new DefaultHttpDataFactory(16384L), this.request, multipart, HttpConstants.DEFAULT_CHARSET, encoderMode);
        for (FormDataPart formDataPart : parts) {
            if (formDataPart.isAttribute()) {
                this.encoder.addBodyAttribute(formDataPart.name(), formDataPart.value());
            } else {
                this.encoder.addBodyFileUpload(formDataPart.name(), formDataPart.filename(), new File(formDataPart.pathname()), formDataPart.mediaType(), formDataPart.isText().booleanValue());
            }
        }
        this.encoder.finalizeRequest();
    }

    private void checkEnd() {
        Handler<Void> handler;
        synchronized (this) {
            handler = this.ended ? this.endHandler : null;
        }
        if (handler != null) {
            handler.handle(null);
        }
    }

    public void run() {
        if (Vertx.currentContext() != this.context) {
            this.context.runOnContext(v -> {
                run();
            });
            return;
        }
        while (!this.ended) {
            if (this.encoder.isChunked()) {
                try {
                    HttpContent chunk = this.encoder.readChunk((ByteBufAllocator) ALLOC);
                    ByteBuf content = chunk.content();
                    Buffer buff = Buffer.buffer(content);
                    boolean writable = this.pending.write((InboundBuffer<Buffer>) buff);
                    if (this.encoder.isEndOfInput()) {
                        this.ended = true;
                        this.request = null;
                        this.encoder = null;
                        if (this.pending.isEmpty()) {
                            this.endHandler.handle(null);
                        }
                    } else if (!writable) {
                        return;
                    }
                } catch (Exception e) {
                    this.ended = true;
                    this.request = null;
                    this.encoder = null;
                    if (this.exceptionHandler != null) {
                        this.exceptionHandler.handle(e);
                        return;
                    }
                    return;
                }
            } else {
                ByteBuf content2 = this.request.content();
                Buffer buffer = Buffer.buffer(content2);
                this.request = null;
                this.encoder = null;
                this.pending.write((InboundBuffer<Buffer>) buffer);
                this.ended = true;
                if (this.pending.isEmpty() && this.endHandler != null) {
                    this.endHandler.handle(null);
                }
            }
        }
    }

    public MultiMap headers() {
        return new HeadersAdaptor(this.request.headers());
    }

    @Override // io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public synchronized MultipartFormUpload exceptionHandler(Handler<Throwable> handler) {
        this.exceptionHandler = handler;
        return this;
    }

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    public synchronized ReadStream<Buffer> handler2(Handler<Buffer> handler) {
        this.pending.handler(handler);
        return this;
    }

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    public synchronized ReadStream<Buffer> pause2() {
        this.pending.pause();
        return this;
    }

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    public ReadStream<Buffer> fetch2(long amount) {
        this.pending.fetch(amount);
        return this;
    }

    @Override // io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    public synchronized ReadStream<Buffer> resume2() {
        this.pending.resume();
        return this;
    }

    @Override // io.vertx.core.streams.ReadStream
    public synchronized ReadStream<Buffer> endHandler(Handler<Void> handler) {
        this.endHandler = handler;
        return this;
    }
}
