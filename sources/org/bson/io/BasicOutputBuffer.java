package org.bson.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.List;
import org.bson.ByteBuf;
import org.bson.ByteBufNIO;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/io/BasicOutputBuffer.class */
public class BasicOutputBuffer extends OutputBuffer {
    private byte[] buffer;
    private int position;

    public BasicOutputBuffer() {
        this(1024);
    }

    public BasicOutputBuffer(int initialSize) {
        this.buffer = new byte[1024];
        this.buffer = new byte[initialSize];
    }

    public byte[] getInternalBuffer() {
        return this.buffer;
    }

    @Override // org.bson.io.OutputBuffer, java.io.OutputStream
    public void write(byte[] b) {
        ensureOpen();
        write(b, 0, b.length);
    }

    @Override // org.bson.io.BsonOutput
    public void writeBytes(byte[] bytes, int offset, int length) {
        ensureOpen();
        ensure(length);
        System.arraycopy(bytes, offset, this.buffer, this.position, length);
        this.position += length;
    }

    @Override // org.bson.io.BsonOutput
    public void writeByte(int value) {
        ensureOpen();
        ensure(1);
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        bArr[i] = (byte) (255 & value);
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
        this.buffer[absolutePosition] = (byte) (255 & value);
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
    public int pipe(OutputStream out) throws IOException {
        ensureOpen();
        out.write(this.buffer, 0, this.position);
        return this.position;
    }

    @Override // org.bson.io.OutputBuffer, org.bson.io.BsonOutput
    public void truncateToPosition(int newPosition) {
        ensureOpen();
        if (newPosition > this.position || newPosition < 0) {
            throw new IllegalArgumentException();
        }
        this.position = newPosition;
    }

    @Override // org.bson.io.OutputBuffer
    public List<ByteBuf> getByteBuffers() {
        ensureOpen();
        return Arrays.asList(new ByteBufNIO(ByteBuffer.wrap(this.buffer, 0, this.position).duplicate().order(ByteOrder.LITTLE_ENDIAN)));
    }

    @Override // org.bson.io.OutputBuffer, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable, org.bson.io.BsonOutput
    public void close() {
        this.buffer = null;
    }

    private void ensureOpen() {
        if (this.buffer == null) {
            throw new IllegalStateException("The output is closed");
        }
    }

    private void ensure(int more) {
        int need = this.position + more;
        if (need <= this.buffer.length) {
            return;
        }
        int newSize = this.buffer.length * 2;
        if (newSize < need) {
            newSize = need + 128;
        }
        byte[] n = new byte[newSize];
        System.arraycopy(this.buffer, 0, n, 0, this.position);
        this.buffer = n;
    }
}
