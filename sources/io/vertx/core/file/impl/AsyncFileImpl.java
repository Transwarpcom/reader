package io.vertx.core.file.impl;

import io.netty.buffer.ByteBuf;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.FileSystemException;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.impl.Arguments;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.WriteStream;
import io.vertx.core.streams.impl.InboundBuffer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/*  JADX ERROR: NullPointerException in pass: ClassModifier
    java.lang.NullPointerException: Cannot invoke "java.util.List.forEach(java.util.function.Consumer)" because "blocks" is null
    	at jadx.core.utils.BlockUtils.collectAllInsns(BlockUtils.java:1029)
    	at jadx.core.dex.visitors.ClassModifier.removeBridgeMethod(ClassModifier.java:245)
    	at jadx.core.dex.visitors.ClassModifier.removeSyntheticMethods(ClassModifier.java:160)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:65)
    */
/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/file/impl/AsyncFileImpl.class */
public class AsyncFileImpl implements AsyncFile {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) AsyncFile.class);
    public static final int DEFAULT_READ_BUFFER_SIZE = 8192;
    private final VertxInternal vertx;

    /* renamed from: ch, reason: collision with root package name */
    private final AsynchronousFileChannel f8ch;
    private final ContextInternal context;
    private boolean closed;
    private Runnable closedDeferred;
    private long writesOutstanding;
    private Handler<Throwable> exceptionHandler;
    private Handler<Void> drainHandler;
    private long writePos;
    private InboundBuffer<Buffer> queue;
    private Handler<Buffer> handler;
    private Handler<Void> endHandler;
    private long readPos;
    private int maxWrites = 131072;
    private int lwm = this.maxWrites / 2;
    private int readBufferSize = 8192;
    private long readLength = Long.MAX_VALUE;

    /*  JADX ERROR: Failed to decode insn: 0x0002: MOVE_MULTI
        java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[6]
        	at java.base/java.lang.System.arraycopy(Native Method)
        	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:52)
        	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:137)
        	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
        	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:50)
        	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    static /* synthetic */ long access$302(io.vertx.core.file.impl.AsyncFileImpl r6, long r7) {
        /*
            r0 = r6
            r1 = r7
            // decode failed: arraycopy: source index -1 out of bounds for object array[6]
            r0.writesOutstanding = r1
            return r-1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.vertx.core.file.impl.AsyncFileImpl.access$302(io.vertx.core.file.impl.AsyncFileImpl, long):long");
    }

    @Override // io.vertx.core.file.AsyncFile, io.vertx.core.streams.ReadStream
    /* renamed from: endHandler, reason: avoid collision after fix types in other method */
    public /* bridge */ /* synthetic */ ReadStream<Buffer> endHandler2(Handler handler) {
        return endHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.file.AsyncFile, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ ReadStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.file.AsyncFile, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.file.AsyncFile, io.vertx.core.streams.WriteStream
    /* renamed from: drainHandler, reason: avoid collision after fix types in other method */
    public /* bridge */ /* synthetic */ WriteStream<Buffer> drainHandler2(Handler handler) {
        return drainHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.file.AsyncFile, io.vertx.core.streams.WriteStream
    public /* bridge */ /* synthetic */ WriteStream<Buffer> write(Buffer buffer, Handler handler) {
        return write(buffer, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.file.AsyncFile, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ WriteStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    static {
    }

    AsyncFileImpl(VertxInternal vertx, String path, OpenOptions options, ContextInternal context) {
        if (!options.isRead() && !options.isWrite()) {
            throw new FileSystemException("Cannot open file for neither reading nor writing");
        }
        this.vertx = vertx;
        Path file = Paths.get(path, new String[0]);
        HashSet<OpenOption> opts = new HashSet<>();
        if (options.isRead()) {
            opts.add(StandardOpenOption.READ);
        }
        if (options.isWrite()) {
            opts.add(StandardOpenOption.WRITE);
        }
        if (options.isCreate()) {
            opts.add(StandardOpenOption.CREATE);
        }
        if (options.isCreateNew()) {
            opts.add(StandardOpenOption.CREATE_NEW);
        }
        if (options.isSync()) {
            opts.add(StandardOpenOption.SYNC);
        }
        if (options.isDsync()) {
            opts.add(StandardOpenOption.DSYNC);
        }
        if (options.isDeleteOnClose()) {
            opts.add(StandardOpenOption.DELETE_ON_CLOSE);
        }
        if (options.isSparse()) {
            opts.add(StandardOpenOption.SPARSE);
        }
        if (options.isTruncateExisting()) {
            opts.add(StandardOpenOption.TRUNCATE_EXISTING);
        }
        try {
            if (options.getPerms() != null) {
                FileAttribute<?> attrs = PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString(options.getPerms()));
                this.f8ch = AsynchronousFileChannel.open(file, opts, vertx.getWorkerPool(), attrs);
            } else {
                this.f8ch = AsynchronousFileChannel.open(file, opts, vertx.getWorkerPool(), new FileAttribute[0]);
            }
            if (options.isAppend()) {
                this.writePos = this.f8ch.size();
            }
            this.context = context;
            this.queue = new InboundBuffer<>(context, 0L);
            this.queue.handler(buff -> {
                if (buff.length() > 0) {
                    handleBuffer(buff);
                } else {
                    handleEnd();
                }
            });
            this.queue.drainHandler(v -> {
                doRead();
            });
        } catch (IOException e) {
            throw new FileSystemException(e);
        }
    }

    @Override // io.vertx.core.file.AsyncFile
    public void close() {
        closeInternal(null);
    }

    @Override // io.vertx.core.file.AsyncFile
    public void close(Handler<AsyncResult<Void>> handler) {
        closeInternal(handler);
    }

    @Override // io.vertx.core.file.AsyncFile, io.vertx.core.streams.WriteStream
    public void end() {
        close();
    }

    @Override // io.vertx.core.file.AsyncFile, io.vertx.core.streams.WriteStream
    public void end(Handler<AsyncResult<Void>> handler) {
        close(handler);
    }

    @Override // io.vertx.core.file.AsyncFile
    public synchronized AsyncFile read(Buffer buffer, int offset, long position, int length, Handler<AsyncResult<Buffer>> handler) {
        Objects.requireNonNull(buffer, "buffer");
        Objects.requireNonNull(handler, "handler");
        Arguments.require(offset >= 0, "offset must be >= 0");
        Arguments.require(position >= 0, "position must be >= 0");
        Arguments.require(length >= 0, "length must be >= 0");
        check();
        ByteBuffer bb = ByteBuffer.allocate(length);
        doRead(buffer, offset, bb, position, handler);
        return this;
    }

    @Override // io.vertx.core.file.AsyncFile, io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    public ReadStream<Buffer> fetch2(long amount) {
        this.queue.fetch(amount);
        return this;
    }

    @Override // io.vertx.core.file.AsyncFile
    public AsyncFile write(Buffer buffer, long position, Handler<AsyncResult<Void>> handler) {
        Objects.requireNonNull(handler, "handler");
        return doWrite(buffer, position, handler);
    }

    private synchronized AsyncFile doWrite(Buffer buffer, long position, Handler<AsyncResult<Void>> handler) {
        Objects.requireNonNull(buffer, "buffer");
        Arguments.require(position >= 0, "position must be >= 0");
        check();
        Handler<AsyncResult<Void>> wrapped = ar -> {
            Runnable action;
            if (ar.succeeded()) {
                checkContext();
                synchronized (this) {
                    if (this.writesOutstanding == 0 && this.closedDeferred != null) {
                        action = this.closedDeferred;
                    } else {
                        action = this::checkDrained;
                    }
                }
                action.run();
                if (handler != null) {
                    handler.handle(ar);
                    return;
                }
                return;
            }
            if (handler != null) {
                handler.handle(ar);
            } else {
                handleException(ar.cause());
            }
        };
        ByteBuf buf = buffer.getByteBuf();
        if (buf.nioBufferCount() > 1) {
            doWrite(buf.nioBuffers(), position, wrapped);
        } else {
            ByteBuffer bb = buf.nioBuffer();
            doWrite(bb, position, bb.limit(), wrapped);
        }
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.file.AsyncFile, io.vertx.core.streams.WriteStream
    public AsyncFile write(Buffer buffer) {
        return write(buffer, (Handler<AsyncResult<Void>>) null);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.file.AsyncFile
    public synchronized AsyncFile write(Buffer buffer, Handler<AsyncResult<Void>> handler) {
        int length = buffer.length();
        doWrite(buffer, this.writePos, handler);
        this.writePos += length;
        return this;
    }

    @Override // io.vertx.core.file.AsyncFile, io.vertx.core.streams.WriteStream
    /* renamed from: setWriteQueueMaxSize */
    public synchronized WriteStream<Buffer> setWriteQueueMaxSize2(int maxSize) {
        Arguments.require(maxSize >= 2, "maxSize must be >= 2");
        check();
        this.maxWrites = maxSize;
        this.lwm = this.maxWrites / 2;
        return this;
    }

    @Override // io.vertx.core.file.AsyncFile
    public synchronized AsyncFile setReadBufferSize(int readBufferSize) {
        this.readBufferSize = readBufferSize;
        return this;
    }

    @Override // io.vertx.core.streams.WriteStream
    public synchronized boolean writeQueueFull() {
        check();
        return this.writesOutstanding >= ((long) this.maxWrites);
    }

    @Override // io.vertx.core.file.AsyncFile, io.vertx.core.streams.WriteStream
    public synchronized WriteStream<Buffer> drainHandler(Handler<Void> handler) {
        check();
        this.drainHandler = handler;
        checkDrained();
        return this;
    }

    @Override // io.vertx.core.file.AsyncFile, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public synchronized AsyncFile exceptionHandler(Handler<Throwable> handler) {
        check();
        this.exceptionHandler = handler;
        return this;
    }

    @Override // io.vertx.core.file.AsyncFile, io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    public synchronized ReadStream<Buffer> handler2(Handler<Buffer> handler) {
        check();
        if (this.closed) {
            return this;
        }
        this.handler = handler;
        if (handler != null) {
            doRead();
        } else {
            this.queue.clear();
        }
        return this;
    }

    @Override // io.vertx.core.file.AsyncFile, io.vertx.core.streams.ReadStream
    public synchronized ReadStream<Buffer> endHandler(Handler<Void> handler) {
        check();
        this.endHandler = handler;
        return this;
    }

    @Override // io.vertx.core.file.AsyncFile, io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    public synchronized ReadStream<Buffer> pause2() {
        check();
        this.queue.pause();
        return this;
    }

    @Override // io.vertx.core.file.AsyncFile, io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    public synchronized ReadStream<Buffer> resume2() {
        check();
        if (!this.closed) {
            this.queue.resume();
        }
        return this;
    }

    @Override // io.vertx.core.file.AsyncFile
    public AsyncFile flush() {
        doFlush(null);
        return this;
    }

    @Override // io.vertx.core.file.AsyncFile
    public AsyncFile flush(Handler<AsyncResult<Void>> handler) {
        doFlush(handler);
        return this;
    }

    @Override // io.vertx.core.file.AsyncFile
    public synchronized AsyncFile setReadPos(long readPos) {
        this.readPos = readPos;
        return this;
    }

    @Override // io.vertx.core.file.AsyncFile
    public synchronized AsyncFile setWritePos(long writePos) {
        this.writePos = writePos;
        return this;
    }

    @Override // io.vertx.core.file.AsyncFile
    public synchronized AsyncFile setReadLength(long readLength) {
        this.readLength = readLength;
        return this;
    }

    @Override // io.vertx.core.file.AsyncFile
    public synchronized long getWritePos() {
        return this.writePos;
    }

    private synchronized void checkDrained() {
        if (this.drainHandler != null && this.writesOutstanding <= this.lwm) {
            Handler<Void> handler = this.drainHandler;
            this.drainHandler = null;
            handler.handle(null);
        }
    }

    private void handleException(Throwable t) {
        if (this.exceptionHandler != null && (t instanceof Exception)) {
            this.exceptionHandler.handle(t);
        } else {
            log.error("Unhandled exception", t);
        }
    }

    private synchronized void doWrite(ByteBuffer[] buffers, long position, Handler<AsyncResult<Void>> handler) {
        AtomicInteger cnt = new AtomicInteger();
        AtomicBoolean sentFailure = new AtomicBoolean();
        for (ByteBuffer b : buffers) {
            int limit = b.limit();
            doWrite(b, position, limit, ar -> {
                if (ar.succeeded()) {
                    if (cnt.incrementAndGet() == buffers.length) {
                        handler.handle(ar);
                    }
                } else if (sentFailure.compareAndSet(false, true)) {
                    handler.handle(ar);
                }
            });
            position += limit;
        }
    }

    private void doRead() {
        doRead(ByteBuffer.allocate(this.readBufferSize));
    }

    private synchronized void doRead(ByteBuffer bb) {
        Buffer buff = Buffer.buffer(this.readBufferSize);
        int readSize = (int) Math.min(this.readBufferSize, this.readLength);
        bb.limit(readSize);
        doRead(buff, 0, bb, this.readPos, ar -> {
            if (ar.succeeded()) {
                Buffer buffer = (Buffer) ar.result();
                this.readPos += buffer.length();
                this.readLength -= buffer.length();
                if (this.queue.write((InboundBuffer<Buffer>) buffer) && buffer.length() > 0) {
                    doRead(bb);
                    return;
                }
                return;
            }
            handleException(ar.cause());
        });
    }

    private void handleBuffer(Buffer buff) {
        Handler<Buffer> handler;
        synchronized (this) {
            handler = this.handler;
        }
        if (handler != null) {
            checkContext();
            handler.handle(buff);
        }
    }

    private void handleEnd() {
        Handler<Void> endHandler;
        synchronized (this) {
            this.handler = null;
            endHandler = this.endHandler;
        }
        if (endHandler != null) {
            checkContext();
            endHandler.handle(null);
        }
    }

    private synchronized void doFlush(Handler<AsyncResult<Void>> handler) {
        checkClosed();
        this.context.executeBlockingInternal(fut -> {
            try {
                this.f8ch.force(false);
                fut.complete();
            } catch (IOException e) {
                throw new FileSystemException(e);
            }
        }, handler);
    }

    private void doWrite(ByteBuffer buff, long position, long toWrite, Handler<AsyncResult<Void>> handler) {
        if (toWrite > 0) {
            synchronized (this) {
                this.writesOutstanding += toWrite;
            }
            writeInternal(buff, position, handler);
            return;
        }
        handler.handle(Future.succeededFuture());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeInternal(final ByteBuffer buff, final long position, final Handler<AsyncResult<Void>> handler) {
        this.f8ch.write(buff, position, null, new CompletionHandler<Integer, Object>() { // from class: io.vertx.core.file.impl.AsyncFileImpl.1
            @Override // java.nio.channels.CompletionHandler
            public void completed(Integer bytesWritten, Object attachment) {
                long pos = position;
                if (!buff.hasRemaining()) {
                    ContextInternal contextInternal = AsyncFileImpl.this.context;
                    ByteBuffer byteBuffer = buff;
                    Handler handler2 = handler;
                    contextInternal.runOnContext(v -> {
                        synchronized (AsyncFileImpl.this) {
                            AsyncFileImpl.access$302(AsyncFileImpl.this, AsyncFileImpl.this.writesOutstanding - byteBuffer.limit());
                        }
                        handler2.handle(Future.succeededFuture());
                    });
                    return;
                }
                AsyncFileImpl.this.writeInternal(buff, pos + bytesWritten.intValue(), handler);
            }

            @Override // java.nio.channels.CompletionHandler
            public void failed(Throwable exc, Object attachment) {
                if (exc instanceof Exception) {
                    ContextInternal contextInternal = AsyncFileImpl.this.context;
                    Handler handler2 = handler;
                    contextInternal.runOnContext(v -> {
                        handler2.handle(Future.failedFuture(exc));
                    });
                    return;
                }
                AsyncFileImpl.log.error("Error occurred", exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doRead(final Buffer writeBuff, final int offset, final ByteBuffer buff, final long position, final Handler<AsyncResult<Buffer>> handler) {
        this.f8ch.read(buff, position, null, new CompletionHandler<Integer, Object>() { // from class: io.vertx.core.file.impl.AsyncFileImpl.2
            long pos;

            {
                this.pos = position;
            }

            private void done() {
                ContextInternal contextInternal = AsyncFileImpl.this.context;
                ByteBuffer byteBuffer = buff;
                Buffer buffer = writeBuff;
                int i = offset;
                Handler handler2 = handler;
                contextInternal.runOnContext(v -> {
                    byteBuffer.flip();
                    buffer.setBytes(i, byteBuffer);
                    byteBuffer.compact();
                    handler2.handle(Future.succeededFuture(buffer));
                });
            }

            @Override // java.nio.channels.CompletionHandler
            public void completed(Integer bytesRead, Object attachment) {
                if (bytesRead.intValue() == -1) {
                    done();
                } else if (buff.hasRemaining()) {
                    this.pos += bytesRead.intValue();
                    AsyncFileImpl.this.doRead(writeBuff, offset, buff, this.pos, handler);
                } else {
                    done();
                }
            }

            @Override // java.nio.channels.CompletionHandler
            public void failed(Throwable t, Object attachment) {
                ContextInternal contextInternal = AsyncFileImpl.this.context;
                Handler handler2 = handler;
                contextInternal.runOnContext(v -> {
                    handler2.handle(Future.failedFuture(t));
                });
            }
        });
    }

    private void check() {
        checkClosed();
    }

    private void checkClosed() {
        if (this.closed) {
            throw new IllegalStateException("File handle is closed");
        }
    }

    private void checkContext() {
        if (!this.vertx.getContext().equals(this.context)) {
            throw new IllegalStateException("AsyncFile must only be used in the context that created it, expected: " + this.context + " actual " + this.vertx.getContext());
        }
    }

    private void doClose(Handler<AsyncResult<Void>> handler) {
        ContextInternal handlerContext = this.vertx.getOrCreateContext();
        handlerContext.executeBlockingInternal(res -> {
            try {
                this.f8ch.close();
                res.complete(null);
            } catch (IOException e) {
                res.fail(e);
            }
        }, handler);
    }

    private synchronized void closeInternal(Handler<AsyncResult<Void>> handler) {
        check();
        this.closed = true;
        if (this.writesOutstanding == 0) {
            doClose(handler);
        } else {
            this.closedDeferred = () -> {
                doClose(handler);
            };
        }
    }
}
