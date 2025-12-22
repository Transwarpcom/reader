package org.bson.io;

import java.nio.ByteOrder;
import java.nio.charset.Charset;
import org.bson.BsonSerializationException;
import org.bson.ByteBuf;
import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/io/ByteBufferBsonInput.class */
public class ByteBufferBsonInput implements BsonInput {
    private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
    private static final String[] ONE_BYTE_ASCII_STRINGS = new String[128];
    private ByteBuf buffer;
    private int mark = -1;

    static {
        for (int b = 0; b < ONE_BYTE_ASCII_STRINGS.length; b++) {
            ONE_BYTE_ASCII_STRINGS[b] = String.valueOf((char) b);
        }
    }

    public ByteBufferBsonInput(ByteBuf buffer) {
        if (buffer == null) {
            throw new IllegalArgumentException("buffer can not be null");
        }
        this.buffer = buffer;
        buffer.order(ByteOrder.LITTLE_ENDIAN);
    }

    @Override // org.bson.io.BsonInput
    public int getPosition() {
        ensureOpen();
        return this.buffer.position();
    }

    @Override // org.bson.io.BsonInput
    public byte readByte() {
        ensureOpen();
        ensureAvailable(1);
        return this.buffer.get();
    }

    @Override // org.bson.io.BsonInput
    public void readBytes(byte[] bytes) {
        ensureOpen();
        ensureAvailable(bytes.length);
        this.buffer.get(bytes);
    }

    @Override // org.bson.io.BsonInput
    public void readBytes(byte[] bytes, int offset, int length) {
        ensureOpen();
        ensureAvailable(length);
        this.buffer.get(bytes, offset, length);
    }

    @Override // org.bson.io.BsonInput
    public long readInt64() {
        ensureOpen();
        ensureAvailable(8);
        return this.buffer.getLong();
    }

    @Override // org.bson.io.BsonInput
    public double readDouble() {
        ensureOpen();
        ensureAvailable(8);
        return this.buffer.getDouble();
    }

    @Override // org.bson.io.BsonInput
    public int readInt32() {
        ensureOpen();
        ensureAvailable(4);
        return this.buffer.getInt();
    }

    @Override // org.bson.io.BsonInput
    public ObjectId readObjectId() {
        ensureOpen();
        byte[] bytes = new byte[12];
        readBytes(bytes);
        return new ObjectId(bytes);
    }

    @Override // org.bson.io.BsonInput
    public String readString() {
        ensureOpen();
        int size = readInt32();
        if (size <= 0) {
            throw new BsonSerializationException(String.format("While decoding a BSON string found a size that is not a positive number: %d", Integer.valueOf(size)));
        }
        return readString(size);
    }

    @Override // org.bson.io.BsonInput
    public String readCString() {
        ensureOpen();
        int mark = this.buffer.position();
        readUntilNullByte();
        int size = this.buffer.position() - mark;
        this.buffer.position(mark);
        return readString(size);
    }

    private String readString(int size) {
        if (size == 2) {
            byte asciiByte = readByte();
            byte nullByte = readByte();
            if (nullByte != 0) {
                throw new BsonSerializationException("Found a BSON string that is not null-terminated");
            }
            if (asciiByte < 0) {
                return UTF8_CHARSET.newDecoder().replacement();
            }
            return ONE_BYTE_ASCII_STRINGS[asciiByte];
        }
        byte[] bytes = new byte[size - 1];
        readBytes(bytes);
        byte nullByte2 = readByte();
        if (nullByte2 != 0) {
            throw new BsonSerializationException("Found a BSON string that is not null-terminated");
        }
        return new String(bytes, UTF8_CHARSET);
    }

    private void readUntilNullByte() {
        while (readByte() != 0) {
        }
    }

    @Override // org.bson.io.BsonInput
    public void skipCString() {
        ensureOpen();
        readUntilNullByte();
    }

    @Override // org.bson.io.BsonInput
    public void skip(int numBytes) {
        ensureOpen();
        this.buffer.position(this.buffer.position() + numBytes);
    }

    @Override // org.bson.io.BsonInput
    @Deprecated
    public void mark(int readLimit) {
        ensureOpen();
        this.mark = this.buffer.position();
    }

    @Override // org.bson.io.BsonInput
    public BsonInputMark getMark(int readLimit) {
        return new BsonInputMark() { // from class: org.bson.io.ByteBufferBsonInput.1
            private int mark;

            {
                this.mark = ByteBufferBsonInput.this.buffer.position();
            }

            @Override // org.bson.io.BsonInputMark
            public void reset() {
                ByteBufferBsonInput.this.ensureOpen();
                ByteBufferBsonInput.this.buffer.position(this.mark);
            }
        };
    }

    @Override // org.bson.io.BsonInput
    public void reset() {
        ensureOpen();
        if (this.mark == -1) {
            throw new IllegalStateException("Mark not set");
        }
        this.buffer.position(this.mark);
    }

    @Override // org.bson.io.BsonInput
    public boolean hasRemaining() {
        ensureOpen();
        return this.buffer.hasRemaining();
    }

    @Override // org.bson.io.BsonInput, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.buffer.release();
        this.buffer = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ensureOpen() {
        if (this.buffer == null) {
            throw new IllegalStateException("Stream is closed");
        }
    }

    private void ensureAvailable(int bytesNeeded) {
        if (this.buffer.remaining() < bytesNeeded) {
            throw new BsonSerializationException(String.format("While decoding a BSON document %d bytes were required, but only %d remain", Integer.valueOf(bytesNeeded), Integer.valueOf(this.buffer.remaining())));
        }
    }
}
