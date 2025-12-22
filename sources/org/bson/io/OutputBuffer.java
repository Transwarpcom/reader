package org.bson.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.bson.BsonSerializationException;
import org.bson.ByteBuf;
import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/io/OutputBuffer.class */
public abstract class OutputBuffer extends OutputStream implements BsonOutput {
    public abstract int pipe(OutputStream outputStream) throws IOException;

    public abstract List<ByteBuf> getByteBuffers();

    public abstract void truncateToPosition(int i);

    protected abstract void write(int i, int i2);

    @Override // java.io.OutputStream
    public void write(byte[] b) {
        write(b, 0, b.length);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable, org.bson.io.BsonOutput
    public void close() {
    }

    @Override // java.io.OutputStream
    public void write(byte[] bytes, int offset, int length) {
        writeBytes(bytes, offset, length);
    }

    @Override // org.bson.io.BsonOutput
    public void writeBytes(byte[] bytes) {
        writeBytes(bytes, 0, bytes.length);
    }

    @Override // org.bson.io.BsonOutput
    public void writeInt32(int value) {
        write(value >> 0);
        write(value >> 8);
        write(value >> 16);
        write(value >> 24);
    }

    @Override // org.bson.io.BsonOutput
    public void writeInt32(int position, int value) {
        write(position, value >> 0);
        write(position + 1, value >> 8);
        write(position + 2, value >> 16);
        write(position + 3, value >> 24);
    }

    @Override // org.bson.io.BsonOutput
    public void writeInt64(long value) {
        write((byte) (255 & (value >> 0)));
        write((byte) (255 & (value >> 8)));
        write((byte) (255 & (value >> 16)));
        write((byte) (255 & (value >> 24)));
        write((byte) (255 & (value >> 32)));
        write((byte) (255 & (value >> 40)));
        write((byte) (255 & (value >> 48)));
        write((byte) (255 & (value >> 56)));
    }

    @Override // org.bson.io.BsonOutput
    public void writeDouble(double x) {
        writeLong(Double.doubleToRawLongBits(x));
    }

    @Override // org.bson.io.BsonOutput
    public void writeString(String str) {
        writeInt(0);
        int strLen = writeCharacters(str, false);
        writeInt32((getPosition() - strLen) - 4, strLen);
    }

    @Override // org.bson.io.BsonOutput
    public void writeCString(String value) {
        writeCharacters(value, true);
    }

    @Override // org.bson.io.BsonOutput
    public void writeObjectId(ObjectId value) {
        write(value.toByteArray());
    }

    public int size() {
        return getSize();
    }

    public byte[] toByteArray() {
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream(size());
            pipe(bout);
            return bout.toByteArray();
        } catch (IOException ioe) {
            throw new RuntimeException("should be impossible", ioe);
        }
    }

    @Override // java.io.OutputStream
    public void write(int value) {
        writeByte(value);
    }

    public void writeInt(int value) {
        writeInt32(value);
    }

    public String toString() {
        return getClass().getName() + " size: " + size() + " pos: " + getPosition();
    }

    public void writeLong(long value) {
        writeInt64(value);
    }

    private int writeCharacters(String str, boolean checkForNullCharacters) {
        int len = str.length();
        int total = 0;
        int iCharCount = 0;
        while (true) {
            int i = iCharCount;
            if (i < len) {
                int c = Character.codePointAt(str, i);
                if (checkForNullCharacters && c == 0) {
                    throw new BsonSerializationException(String.format("BSON cstring '%s' is not valid because it contains a null character at index %d", str, Integer.valueOf(i)));
                }
                if (c < 128) {
                    write((byte) c);
                    total++;
                } else if (c < 2048) {
                    write((byte) (192 + (c >> 6)));
                    write((byte) (128 + (c & 63)));
                    total += 2;
                } else if (c < 65536) {
                    write((byte) (224 + (c >> 12)));
                    write((byte) (128 + ((c >> 6) & 63)));
                    write((byte) (128 + (c & 63)));
                    total += 3;
                } else {
                    write((byte) (240 + (c >> 18)));
                    write((byte) (128 + ((c >> 12) & 63)));
                    write((byte) (128 + ((c >> 6) & 63)));
                    write((byte) (128 + (c & 63)));
                    total += 4;
                }
                iCharCount = i + Character.charCount(c);
            } else {
                write(0);
                return total + 1;
            }
        }
    }
}
