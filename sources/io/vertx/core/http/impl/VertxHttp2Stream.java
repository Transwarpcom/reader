package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http2.EmptyHttp2Headers;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.handler.codec.http2.Http2Stream;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.http.impl.Http2ConnectionBase;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.streams.impl.InboundBuffer;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/VertxHttp2Stream.class */
abstract class VertxHttp2Stream<C extends Http2ConnectionBase> {
    private static final MultiMap EMPTY = new Http2HeadersAdaptor(EmptyHttp2Headers.INSTANCE);
    protected final C conn;
    protected final VertxInternal vertx;
    protected final ContextInternal context;
    protected final ChannelHandlerContext handlerContext;
    protected final Http2Stream stream;
    private final InboundBuffer<Object> pending;
    private int pendingBytes;
    private MultiMap trailers;
    private boolean writable;
    private StreamPriority priority = HttpUtils.DEFAULT_STREAM_PRIORITY;
    private long bytesRead;
    private long bytesWritten;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void handlePriorityChange(StreamPriority streamPriority);

    VertxHttp2Stream(C conn, Http2Stream stream, boolean writable) {
        this.conn = conn;
        this.vertx = conn.vertx();
        this.handlerContext = conn.handlerContext;
        this.stream = stream;
        this.context = conn.getContext();
        this.writable = writable;
        this.pending = new InboundBuffer<>(this.context, 5L);
        this.pending.drainHandler(v -> {
            int numBytes = this.pendingBytes;
            this.pendingBytes = 0;
            conn.handler.consume(stream, numBytes);
        });
        this.pending.handler(buff -> {
            if (buff == InboundBuffer.END_SENTINEL) {
                conn.reportBytesRead(this.bytesRead);
                handleEnd(this.trailers);
            } else {
                Buffer data = (Buffer) buff;
                this.bytesRead += data.length();
                handleData(data);
            }
        });
        this.pending.exceptionHandler(this.context.exceptionHandler());
        this.pending.resume();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onResetRead(long code) {
        handleReset(code);
    }

    boolean onDataRead(Buffer data) {
        boolean read = this.pending.write((InboundBuffer<Object>) data);
        if (!read) {
            this.pendingBytes += data.length();
        }
        return read;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onWritabilityChanged() {
        synchronized (this.conn) {
            this.writable = !this.writable;
        }
        handleInterestedOpsChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onEnd() {
        onEnd(EMPTY);
    }

    void onEnd(MultiMap map) {
        synchronized (this.conn) {
            this.trailers = map;
        }
        this.pending.write((InboundBuffer<Object>) InboundBuffer.END_SENTINEL);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int id() {
        return this.stream.id();
    }

    long bytesWritten() {
        return this.bytesWritten;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long bytesRead() {
        return this.bytesRead;
    }

    public void doPause() {
        this.pending.pause();
    }

    public void doFetch(long amount) {
        this.pending.fetch(amount);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isNotWritable() {
        boolean z;
        synchronized (this.conn) {
            z = !this.writable;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeFrame(int type, int flags, ByteBuf payload) {
        this.conn.handler.writeFrame(this.stream, (byte) type, (short) flags, payload);
    }

    void writeHeaders(Http2Headers headers, boolean end, Handler<AsyncResult<Void>> handler) {
        this.conn.handler.writeHeaders(this.stream, headers, end, this.priority.getDependency(), this.priority.getWeight(), this.priority.isExclusive(), handler);
    }

    private void writePriorityFrame(StreamPriority priority) {
        this.conn.handler.writePriority(this.stream, priority.getDependency(), priority.getWeight(), priority.isExclusive());
    }

    void writeData(ByteBuf chunk, boolean end) {
        writeData(chunk, end, null);
    }

    void writeData(ByteBuf chunk, boolean end, Handler<AsyncResult<Void>> handler) {
        this.bytesWritten += chunk.readableBytes();
        this.conn.handler.writeData(this.stream, chunk, end, handler);
    }

    void writeReset(long code) {
        this.conn.handler.writeReset(this.stream.id(), code);
    }

    void handleInterestedOpsChanged() {
    }

    void handleData(Buffer buf) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleCustomFrame(int type, int flags, Buffer buff) {
    }

    void handleEnd(MultiMap trailers) {
    }

    void handleReset(long errorCode) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleException(Throwable cause) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleClose() {
        this.conn.reportBytesWritten(this.bytesWritten);
    }

    synchronized void priority(StreamPriority streamPriority) {
        this.priority = streamPriority;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized StreamPriority priority() {
        return this.priority;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void updatePriority(StreamPriority priority) {
        if (!this.priority.equals(priority)) {
            this.priority = priority;
            if (this.stream.isHeadersSent()) {
                writePriorityFrame(priority);
            }
        }
    }
}
