package com.mongodb.internal.connection;

import com.mongodb.MongoSocketOpenException;
import com.mongodb.MongoSocketReadException;
import com.mongodb.MongoSocketReadTimeoutException;
import com.mongodb.ServerAddress;
import com.mongodb.assertions.Assertions;
import com.mongodb.connection.AsyncCompletionHandler;
import com.mongodb.connection.BufferProvider;
import com.mongodb.connection.SocketSettings;
import com.mongodb.connection.Stream;
import java.io.IOException;
import java.net.SocketOption;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.InterruptedByTimeoutException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.bson.ByteBuf;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/AsynchronousSocketChannelStream.class */
public final class AsynchronousSocketChannelStream implements Stream {
    private final ServerAddress serverAddress;
    private final SocketSettings settings;
    private final BufferProvider bufferProvider;
    private final AsynchronousChannelGroup group;
    private volatile AsynchronousSocketChannel channel;
    private volatile boolean isClosed;

    public AsynchronousSocketChannelStream(ServerAddress serverAddress, SocketSettings settings, BufferProvider bufferProvider, AsynchronousChannelGroup group) {
        this.serverAddress = serverAddress;
        this.settings = settings;
        this.bufferProvider = bufferProvider;
        this.group = group;
    }

    @Override // com.mongodb.connection.BufferProvider
    public ByteBuf getBuffer(int size) {
        return this.bufferProvider.getBuffer(size);
    }

    @Override // com.mongodb.connection.Stream
    public void open() throws InterruptedException, IOException {
        FutureAsyncCompletionHandler<Void> handler = new FutureAsyncCompletionHandler<>();
        openAsync(handler);
        handler.getOpen();
    }

    @Override // com.mongodb.connection.Stream
    public void openAsync(AsyncCompletionHandler<Void> handler) {
        Assertions.isTrue("unopened", this.channel == null);
        try {
            this.channel = AsynchronousSocketChannel.open(this.group);
            this.channel.setOption((SocketOption<SocketOption>) StandardSocketOptions.TCP_NODELAY, (SocketOption) true);
            this.channel.setOption((SocketOption<SocketOption>) StandardSocketOptions.SO_KEEPALIVE, (SocketOption) Boolean.valueOf(this.settings.isKeepAlive()));
            if (this.settings.getReceiveBufferSize() > 0) {
                this.channel.setOption((SocketOption<SocketOption>) StandardSocketOptions.SO_RCVBUF, (SocketOption) Integer.valueOf(this.settings.getReceiveBufferSize()));
            }
            if (this.settings.getSendBufferSize() > 0) {
                this.channel.setOption((SocketOption<SocketOption>) StandardSocketOptions.SO_SNDBUF, (SocketOption) Integer.valueOf(this.settings.getSendBufferSize()));
            }
            this.channel.connect(this.serverAddress.getSocketAddress(), null, new OpenCompletionHandler(handler));
        } catch (IOException e) {
            handler.failed(new MongoSocketOpenException("Exception opening socket", this.serverAddress, e));
        } catch (Throwable t) {
            handler.failed(t);
        }
    }

    @Override // com.mongodb.connection.Stream
    public void write(List<ByteBuf> buffers) throws InterruptedException, IOException {
        FutureAsyncCompletionHandler<Void> handler = new FutureAsyncCompletionHandler<>();
        writeAsync(buffers, handler);
        handler.getWrite();
    }

    @Override // com.mongodb.connection.Stream
    public ByteBuf read(int numBytes) throws IOException {
        FutureAsyncCompletionHandler<ByteBuf> handler = new FutureAsyncCompletionHandler<>();
        readAsync(numBytes, handler);
        return handler.getRead();
    }

    @Override // com.mongodb.connection.Stream
    public void writeAsync(List<ByteBuf> buffers, final AsyncCompletionHandler<Void> handler) {
        final AsyncWritableByteChannel byteChannel = new AsyncWritableByteChannelAdapter();
        final Iterator<ByteBuf> iter = buffers.iterator();
        pipeOneBuffer(byteChannel, iter.next(), new AsyncCompletionHandler<Void>() { // from class: com.mongodb.internal.connection.AsynchronousSocketChannelStream.1
            @Override // com.mongodb.connection.AsyncCompletionHandler
            public void completed(Void t) {
                if (iter.hasNext()) {
                    AsynchronousSocketChannelStream.this.pipeOneBuffer(byteChannel, (ByteBuf) iter.next(), this);
                } else {
                    handler.completed(null);
                }
            }

            @Override // com.mongodb.connection.AsyncCompletionHandler
            public void failed(Throwable t) {
                handler.failed(t);
            }
        });
    }

    @Override // com.mongodb.connection.Stream
    public void readAsync(int numBytes, AsyncCompletionHandler<ByteBuf> handler) {
        ByteBuf buffer = this.bufferProvider.getBuffer(numBytes);
        this.channel.read(buffer.asNIO(), this.settings.getReadTimeout(TimeUnit.MILLISECONDS), TimeUnit.MILLISECONDS, null, new BasicCompletionHandler(buffer, handler));
    }

    @Override // com.mongodb.connection.Stream
    public ServerAddress getAddress() {
        return this.serverAddress;
    }

    @Override // com.mongodb.connection.Stream
    public void close() {
        try {
            if (this.channel != null) {
                this.channel.close();
            }
        } catch (IOException e) {
        } finally {
            this.channel = null;
            this.isClosed = true;
        }
    }

    @Override // com.mongodb.connection.Stream
    public boolean isClosed() {
        return this.isClosed;
    }

    public ServerAddress getServerAddress() {
        return this.serverAddress;
    }

    public SocketSettings getSettings() {
        return this.settings;
    }

    public AsynchronousChannelGroup getGroup() {
        return this.group;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pipeOneBuffer(final AsyncWritableByteChannel byteChannel, final ByteBuf byteBuffer, final AsyncCompletionHandler<Void> outerHandler) {
        byteChannel.write(byteBuffer.asNIO(), new AsyncCompletionHandler<Void>() { // from class: com.mongodb.internal.connection.AsynchronousSocketChannelStream.2
            @Override // com.mongodb.connection.AsyncCompletionHandler
            public void completed(Void t) {
                if (byteBuffer.hasRemaining()) {
                    byteChannel.write(byteBuffer.asNIO(), this);
                } else {
                    outerHandler.completed(null);
                }
            }

            @Override // com.mongodb.connection.AsyncCompletionHandler
            public void failed(Throwable t) {
                outerHandler.failed(t);
            }
        });
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/AsynchronousSocketChannelStream$AsyncWritableByteChannelAdapter.class */
    private class AsyncWritableByteChannelAdapter implements AsyncWritableByteChannel {
        private AsyncWritableByteChannelAdapter() {
        }

        @Override // com.mongodb.internal.connection.AsyncWritableByteChannel
        public void write(ByteBuffer src, AsyncCompletionHandler<Void> handler) {
            AsynchronousSocketChannelStream.this.channel.write(src, null, new WriteCompletionHandler(handler));
        }

        /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/AsynchronousSocketChannelStream$AsyncWritableByteChannelAdapter$WriteCompletionHandler.class */
        private class WriteCompletionHandler extends BaseCompletionHandler<Void, Integer, Object> {
            WriteCompletionHandler(AsyncCompletionHandler<Void> handler) {
                super(handler);
            }

            @Override // java.nio.channels.CompletionHandler
            public void completed(Integer result, Object attachment) {
                AsyncCompletionHandler<Void> localHandler = getHandlerAndClear();
                localHandler.completed(null);
            }

            @Override // java.nio.channels.CompletionHandler
            public void failed(Throwable exc, Object attachment) {
                AsyncCompletionHandler<Void> localHandler = getHandlerAndClear();
                localHandler.failed(exc);
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/AsynchronousSocketChannelStream$BasicCompletionHandler.class */
    private final class BasicCompletionHandler extends BaseCompletionHandler<ByteBuf, Integer, Void> {
        private final AtomicReference<ByteBuf> byteBufReference;

        private BasicCompletionHandler(ByteBuf dst, AsyncCompletionHandler<ByteBuf> handler) {
            super(handler);
            this.byteBufReference = new AtomicReference<>(dst);
        }

        @Override // java.nio.channels.CompletionHandler
        public void completed(Integer result, Void attachment) {
            AsyncCompletionHandler<ByteBuf> localHandler = getHandlerAndClear();
            ByteBuf localByteBuf = this.byteBufReference.getAndSet(null);
            if (result.intValue() == -1) {
                localByteBuf.release();
                localHandler.failed(new MongoSocketReadException("Prematurely reached end of stream", AsynchronousSocketChannelStream.this.serverAddress));
            } else if (localByteBuf.hasRemaining()) {
                AsynchronousSocketChannelStream.this.channel.read(localByteBuf.asNIO(), AsynchronousSocketChannelStream.this.settings.getReadTimeout(TimeUnit.MILLISECONDS), TimeUnit.MILLISECONDS, null, AsynchronousSocketChannelStream.this.new BasicCompletionHandler(localByteBuf, localHandler));
            } else {
                localByteBuf.flip();
                localHandler.completed(localByteBuf);
            }
        }

        @Override // java.nio.channels.CompletionHandler
        public void failed(Throwable t, Void attachment) {
            AsyncCompletionHandler<ByteBuf> localHandler = getHandlerAndClear();
            ByteBuf localByteBuf = this.byteBufReference.getAndSet(null);
            localByteBuf.release();
            if (t instanceof InterruptedByTimeoutException) {
                localHandler.failed(new MongoSocketReadTimeoutException("Timeout while receiving message", AsynchronousSocketChannelStream.this.serverAddress, t));
            } else {
                localHandler.failed(t);
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/AsynchronousSocketChannelStream$OpenCompletionHandler.class */
    private class OpenCompletionHandler extends BaseCompletionHandler<Void, Void, Object> {
        OpenCompletionHandler(AsyncCompletionHandler<Void> handler) {
            super(handler);
        }

        @Override // java.nio.channels.CompletionHandler
        public void completed(Void result, Object attachment) {
            AsyncCompletionHandler<Void> localHandler = getHandlerAndClear();
            localHandler.completed(null);
        }

        @Override // java.nio.channels.CompletionHandler
        public void failed(Throwable exc, Object attachment) {
            AsyncCompletionHandler<Void> localHandler = getHandlerAndClear();
            if (exc instanceof IOException) {
                localHandler.failed(new MongoSocketOpenException("Exception opening socket", AsynchronousSocketChannelStream.this.getAddress(), exc));
            } else {
                localHandler.failed(exc);
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/AsynchronousSocketChannelStream$BaseCompletionHandler.class */
    private static abstract class BaseCompletionHandler<T, V, A> implements CompletionHandler<V, A> {
        private final AtomicReference<AsyncCompletionHandler<T>> handlerReference;

        BaseCompletionHandler(AsyncCompletionHandler<T> handler) {
            this.handlerReference = new AtomicReference<>(handler);
        }

        protected AsyncCompletionHandler<T> getHandlerAndClear() {
            return this.handlerReference.getAndSet(null);
        }
    }
}
