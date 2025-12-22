package com.mongodb.internal.connection;

import com.mongodb.MongoSocketOpenException;
import com.mongodb.MongoSocketReadException;
import com.mongodb.ServerAddress;
import com.mongodb.assertions.Assertions;
import com.mongodb.connection.AsyncCompletionHandler;
import com.mongodb.connection.BufferProvider;
import com.mongodb.connection.SocketSettings;
import com.mongodb.connection.SslSettings;
import com.mongodb.connection.Stream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.List;
import org.bson.ByteBuf;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/SocketChannelStream.class */
public class SocketChannelStream implements Stream {
    private final ServerAddress address;
    private final SocketSettings settings;
    private final SslSettings sslSettings;
    private final BufferProvider bufferProvider;
    private volatile SocketChannel socketChannel;
    private volatile boolean isClosed;

    public SocketChannelStream(ServerAddress address, SocketSettings settings, SslSettings sslSettings, BufferProvider bufferProvider) {
        this.address = (ServerAddress) Assertions.notNull("address", address);
        this.settings = (SocketSettings) Assertions.notNull("settings", settings);
        this.sslSettings = (SslSettings) Assertions.notNull("sslSettings", sslSettings);
        this.bufferProvider = (BufferProvider) Assertions.notNull("bufferProvider", bufferProvider);
    }

    @Override // com.mongodb.connection.Stream
    public void open() throws IOException {
        try {
            this.socketChannel = SocketChannel.open();
            SocketStreamHelper.initialize(this.socketChannel.socket(), this.address, this.settings, this.sslSettings);
        } catch (IOException e) {
            close();
            throw new MongoSocketOpenException("Exception opening socket", getAddress(), e);
        }
    }

    @Override // com.mongodb.connection.BufferProvider
    public ByteBuf getBuffer(int size) {
        return this.bufferProvider.getBuffer(size);
    }

    @Override // com.mongodb.connection.Stream
    public void write(List<ByteBuf> buffers) throws IOException {
        Assertions.isTrue("open", !isClosed());
        int totalSize = 0;
        ByteBuffer[] byteBufferArray = new ByteBuffer[buffers.size()];
        for (int i = 0; i < buffers.size(); i++) {
            byteBufferArray[i] = buffers.get(i).asNIO();
            totalSize += byteBufferArray[i].limit();
        }
        long jWrite = 0;
        while (true) {
            long bytesRead = jWrite;
            if (bytesRead < totalSize) {
                jWrite = bytesRead + this.socketChannel.write(byteBufferArray);
            } else {
                return;
            }
        }
    }

    @Override // com.mongodb.connection.Stream
    public ByteBuf read(int numBytes) throws IOException {
        ByteBuf buffer = this.bufferProvider.getBuffer(numBytes);
        Assertions.isTrue("open", !isClosed());
        int i = 0;
        while (true) {
            int totalBytesRead = i;
            if (totalBytesRead < buffer.limit()) {
                int bytesRead = this.socketChannel.read(buffer.asNIO());
                if (bytesRead == -1) {
                    buffer.release();
                    throw new MongoSocketReadException("Prematurely reached end of stream", getAddress());
                }
                i = totalBytesRead + bytesRead;
            } else {
                return buffer.flip();
            }
        }
    }

    @Override // com.mongodb.connection.Stream
    public void openAsync(AsyncCompletionHandler<Void> handler) {
        throw new UnsupportedOperationException(getClass() + " does not support asynchronous operations.");
    }

    @Override // com.mongodb.connection.Stream
    public void writeAsync(List<ByteBuf> buffers, AsyncCompletionHandler<Void> handler) {
        throw new UnsupportedOperationException(getClass() + " does not support asynchronous operations.");
    }

    @Override // com.mongodb.connection.Stream
    public void readAsync(int numBytes, AsyncCompletionHandler<ByteBuf> handler) {
        throw new UnsupportedOperationException(getClass() + " does not support asynchronous operations.");
    }

    @Override // com.mongodb.connection.Stream
    public ServerAddress getAddress() {
        return this.address;
    }

    SocketSettings getSettings() {
        return this.settings;
    }

    void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override // com.mongodb.connection.Stream
    public void close() {
        try {
            this.isClosed = true;
            if (this.socketChannel != null) {
                this.socketChannel.close();
            }
        } catch (IOException e) {
        }
    }

    @Override // com.mongodb.connection.Stream
    public boolean isClosed() {
        return this.isClosed;
    }
}
