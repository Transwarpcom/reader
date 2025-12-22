package com.mongodb.connection;

import com.mongodb.assertions.Assertions;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import org.bson.ByteBuf;
import org.bson.io.OutputBuffer;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/ByteBufferBsonOutput.class */
public class ByteBufferBsonOutput extends OutputBuffer {
    private static final int MAX_SHIFT = 31;
    private static final int INITIAL_SHIFT = 10;
    public static final int INITIAL_BUFFER_SIZE = 1024;
    public static final int MAX_BUFFER_SIZE = 16777216;
    private final BufferProvider bufferProvider;
    private final List<ByteBuf> bufferList = new ArrayList();
    private int curBufferIndex = 0;
    private int position = 0;
    private boolean closed;

    public ByteBufferBsonOutput(BufferProvider bufferProvider) {
        this.bufferProvider = (BufferProvider) Assertions.notNull("bufferProvider", bufferProvider);
    }

    @Override // org.bson.io.BsonOutput
    public void writeBytes(byte[] bytes, int offset, int length) {
        ensureOpen();
        int currentOffset = offset;
        int remainingLen = length;
        while (remainingLen > 0) {
            ByteBuf buf = getCurrentByteBuffer();
            int bytesToPutInCurrentBuffer = Math.min(buf.remaining(), remainingLen);
            buf.put(bytes, currentOffset, bytesToPutInCurrentBuffer);
            remainingLen -= bytesToPutInCurrentBuffer;
            currentOffset += bytesToPutInCurrentBuffer;
        }
        this.position += length;
    }

    @Override // org.bson.io.BsonOutput
    public void writeByte(int value) {
        ensureOpen();
        getCurrentByteBuffer().put((byte) value);
        this.position++;
    }

    private ByteBuf getCurrentByteBuffer() {
        ByteBuf curByteBuffer = getByteBufferAtIndex(this.curBufferIndex);
        if (curByteBuffer.hasRemaining()) {
            return curByteBuffer;
        }
        this.curBufferIndex++;
        return getByteBufferAtIndex(this.curBufferIndex);
    }

    private ByteBuf getByteBufferAtIndex(int index) {
        int iMin;
        if (this.bufferList.size() < index + 1) {
            List<ByteBuf> list = this.bufferList;
            BufferProvider bufferProvider = this.bufferProvider;
            if (index >= 21) {
                iMin = 16777216;
            } else {
                iMin = Math.min(1024 << index, 16777216);
            }
            list.add(bufferProvider.getBuffer(iMin));
        }
        return this.bufferList.get(index);
    }

    @Override // org.bson.io.BsonOutput
    public int getPosition() {
        ensureOpen();
        return this.position;
    }

    @Override // org.bson.io.BsonOutput
    public int getSize() {
        ensureOpen();
        return this.position;
    }

    @Override // org.bson.io.OutputBuffer
    protected void write(int absolutePosition, int value) {
        ensureOpen();
        if (absolutePosition < 0) {
            throw new IllegalArgumentException(String.format("position must be >= 0 but was %d", Integer.valueOf(absolutePosition)));
        }
        if (absolutePosition > this.position - 1) {
            throw new IllegalArgumentException(String.format("position must be <= %d but was %d", Integer.valueOf(this.position - 1), Integer.valueOf(absolutePosition)));
        }
        BufferPositionPair bufferPositionPair = getBufferPositionPair(absolutePosition);
        ByteBuf byteBuffer = getByteBufferAtIndex(bufferPositionPair.bufferIndex);
        byteBuffer.put(BufferPositionPair.access$108(bufferPositionPair), (byte) value);
    }

    @Override // org.bson.io.OutputBuffer
    public List<ByteBuf> getByteBuffers() {
        ensureOpen();
        List<ByteBuf> buffers = new ArrayList<>(this.bufferList.size());
        for (ByteBuf cur : this.bufferList) {
            buffers.add(cur.duplicate().order(ByteOrder.LITTLE_ENDIAN).flip());
        }
        return buffers;
    }

    @Override // org.bson.io.OutputBuffer
    public int pipe(OutputStream out) throws IOException {
        ensureOpen();
        byte[] tmp = new byte[1024];
        int total = 0;
        for (ByteBuf cur : getByteBuffers()) {
            ByteBuf dup = cur.duplicate();
            while (dup.hasRemaining()) {
                int numBytesToCopy = Math.min(dup.remaining(), tmp.length);
                dup.get(tmp, 0, numBytesToCopy);
                out.write(tmp, 0, numBytesToCopy);
            }
            total += dup.limit();
        }
        return total;
    }

    @Override // org.bson.io.OutputBuffer, org.bson.io.BsonOutput
    public void truncateToPosition(int newPosition) {
        ensureOpen();
        if (newPosition > this.position || newPosition < 0) {
            throw new IllegalArgumentException();
        }
        BufferPositionPair bufferPositionPair = getBufferPositionPair(newPosition);
        this.bufferList.get(bufferPositionPair.bufferIndex).position(bufferPositionPair.position);
        while (this.bufferList.size() > bufferPositionPair.bufferIndex + 1) {
            ByteBuf buffer = this.bufferList.remove(this.bufferList.size() - 1);
            buffer.release();
        }
        this.curBufferIndex = bufferPositionPair.bufferIndex;
        this.position = newPosition;
    }

    @Override // org.bson.io.OutputBuffer, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable, org.bson.io.BsonOutput
    public void close() {
        for (ByteBuf cur : this.bufferList) {
            cur.release();
        }
        this.bufferList.clear();
        this.closed = true;
    }

    private BufferPositionPair getBufferPositionPair(int absolutePosition) {
        int positionInBuffer = absolutePosition;
        int bufferIndex = 0;
        int bufferSize = 1024;
        int startPositionOfBuffer = 0;
        while (startPositionOfBuffer + bufferSize <= absolutePosition) {
            bufferIndex++;
            startPositionOfBuffer += bufferSize;
            positionInBuffer -= bufferSize;
            bufferSize = this.bufferList.get(bufferIndex).limit();
        }
        return new BufferPositionPair(bufferIndex, positionInBuffer);
    }

    private void ensureOpen() {
        if (this.closed) {
            throw new IllegalStateException("The output is closed");
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/ByteBufferBsonOutput$BufferPositionPair.class */
    private static final class BufferPositionPair {
        private final int bufferIndex;
        private int position;

        static /* synthetic */ int access$108(BufferPositionPair x0) {
            int i = x0.position;
            x0.position = i + 1;
            return i;
        }

        BufferPositionPair(int bufferIndex, int position) {
            this.bufferIndex = bufferIndex;
            this.position = position;
        }
    }
}
