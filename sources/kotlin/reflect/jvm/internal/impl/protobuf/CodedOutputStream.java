package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/protobuf/CodedOutputStream.class */
public final class CodedOutputStream {
    private final byte[] buffer;
    private final int limit;
    private final OutputStream output;
    private int totalBytesWritten = 0;
    private int position = 0;

    static int computePreferredBufferSize(int dataLength) {
        if (dataLength > 4096) {
            return 4096;
        }
        return dataLength;
    }

    private CodedOutputStream(OutputStream output, byte[] buffer) {
        this.output = output;
        this.buffer = buffer;
        this.limit = buffer.length;
    }

    public static CodedOutputStream newInstance(OutputStream output, int bufferSize) {
        return new CodedOutputStream(output, new byte[bufferSize]);
    }

    public void writeDouble(int fieldNumber, double value) throws IOException {
        writeTag(fieldNumber, 1);
        writeDoubleNoTag(value);
    }

    public void writeFloat(int fieldNumber, float value) throws IOException {
        writeTag(fieldNumber, 5);
        writeFloatNoTag(value);
    }

    public void writeInt32(int fieldNumber, int value) throws IOException {
        writeTag(fieldNumber, 0);
        writeInt32NoTag(value);
    }

    public void writeBool(int fieldNumber, boolean value) throws IOException {
        writeTag(fieldNumber, 0);
        writeBoolNoTag(value);
    }

    public void writeGroup(int fieldNumber, MessageLite value) throws IOException {
        writeTag(fieldNumber, 3);
        writeGroupNoTag(value);
        writeTag(fieldNumber, 4);
    }

    public void writeMessage(int fieldNumber, MessageLite value) throws IOException {
        writeTag(fieldNumber, 2);
        writeMessageNoTag(value);
    }

    public void writeBytes(int fieldNumber, ByteString value) throws IOException {
        writeTag(fieldNumber, 2);
        writeBytesNoTag(value);
    }

    public void writeUInt32(int fieldNumber, int value) throws IOException {
        writeTag(fieldNumber, 0);
        writeUInt32NoTag(value);
    }

    public void writeEnum(int fieldNumber, int value) throws IOException {
        writeTag(fieldNumber, 0);
        writeEnumNoTag(value);
    }

    public void writeSInt64(int fieldNumber, long value) throws IOException {
        writeTag(fieldNumber, 0);
        writeSInt64NoTag(value);
    }

    public void writeMessageSetExtension(int fieldNumber, MessageLite value) throws IOException {
        writeTag(1, 3);
        writeUInt32(2, fieldNumber);
        writeMessage(3, value);
        writeTag(1, 4);
    }

    public void writeDoubleNoTag(double value) throws IOException {
        writeRawLittleEndian64(Double.doubleToRawLongBits(value));
    }

    public void writeFloatNoTag(float value) throws IOException {
        writeRawLittleEndian32(Float.floatToRawIntBits(value));
    }

    public void writeUInt64NoTag(long value) throws IOException {
        writeRawVarint64(value);
    }

    public void writeInt64NoTag(long value) throws IOException {
        writeRawVarint64(value);
    }

    public void writeInt32NoTag(int value) throws IOException {
        if (value >= 0) {
            writeRawVarint32(value);
        } else {
            writeRawVarint64(value);
        }
    }

    public void writeFixed64NoTag(long value) throws IOException {
        writeRawLittleEndian64(value);
    }

    public void writeFixed32NoTag(int value) throws IOException {
        writeRawLittleEndian32(value);
    }

    public void writeBoolNoTag(boolean value) throws IOException {
        writeRawByte(value ? 1 : 0);
    }

    public void writeStringNoTag(String value) throws IOException {
        byte[] bytes = value.getBytes("UTF-8");
        writeRawVarint32(bytes.length);
        writeRawBytes(bytes);
    }

    public void writeGroupNoTag(MessageLite value) throws IOException {
        value.writeTo(this);
    }

    public void writeMessageNoTag(MessageLite value) throws IOException {
        writeRawVarint32(value.getSerializedSize());
        value.writeTo(this);
    }

    public void writeBytesNoTag(ByteString value) throws IOException {
        writeRawVarint32(value.size());
        writeRawBytes(value);
    }

    public void writeByteArrayNoTag(byte[] value) throws IOException {
        writeRawVarint32(value.length);
        writeRawBytes(value);
    }

    public void writeUInt32NoTag(int value) throws IOException {
        writeRawVarint32(value);
    }

    public void writeEnumNoTag(int value) throws IOException {
        writeInt32NoTag(value);
    }

    public void writeSFixed32NoTag(int value) throws IOException {
        writeRawLittleEndian32(value);
    }

    public void writeSFixed64NoTag(long value) throws IOException {
        writeRawLittleEndian64(value);
    }

    public void writeSInt32NoTag(int value) throws IOException {
        writeRawVarint32(encodeZigZag32(value));
    }

    public void writeSInt64NoTag(long value) throws IOException {
        writeRawVarint64(encodeZigZag64(value));
    }

    public static int computeDoubleSize(int fieldNumber, double value) {
        return computeTagSize(fieldNumber) + computeDoubleSizeNoTag(value);
    }

    public static int computeFloatSize(int fieldNumber, float value) {
        return computeTagSize(fieldNumber) + computeFloatSizeNoTag(value);
    }

    public static int computeInt32Size(int fieldNumber, int value) {
        return computeTagSize(fieldNumber) + computeInt32SizeNoTag(value);
    }

    public static int computeBoolSize(int fieldNumber, boolean value) {
        return computeTagSize(fieldNumber) + computeBoolSizeNoTag(value);
    }

    public static int computeMessageSize(int fieldNumber, MessageLite value) {
        return computeTagSize(fieldNumber) + computeMessageSizeNoTag(value);
    }

    public static int computeBytesSize(int fieldNumber, ByteString value) {
        return computeTagSize(fieldNumber) + computeBytesSizeNoTag(value);
    }

    public static int computeEnumSize(int fieldNumber, int value) {
        return computeTagSize(fieldNumber) + computeEnumSizeNoTag(value);
    }

    public static int computeSInt64Size(int fieldNumber, long value) {
        return computeTagSize(fieldNumber) + computeSInt64SizeNoTag(value);
    }

    public static int computeDoubleSizeNoTag(double value) {
        return 8;
    }

    public static int computeFloatSizeNoTag(float value) {
        return 4;
    }

    public static int computeUInt64SizeNoTag(long value) {
        return computeRawVarint64Size(value);
    }

    public static int computeInt64SizeNoTag(long value) {
        return computeRawVarint64Size(value);
    }

    public static int computeInt32SizeNoTag(int value) {
        if (value >= 0) {
            return computeRawVarint32Size(value);
        }
        return 10;
    }

    public static int computeFixed64SizeNoTag(long value) {
        return 8;
    }

    public static int computeFixed32SizeNoTag(int value) {
        return 4;
    }

    public static int computeBoolSizeNoTag(boolean value) {
        return 1;
    }

    public static int computeStringSizeNoTag(String value) throws UnsupportedEncodingException {
        try {
            byte[] bytes = value.getBytes("UTF-8");
            return computeRawVarint32Size(bytes.length) + bytes.length;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported.", e);
        }
    }

    public static int computeGroupSizeNoTag(MessageLite value) {
        return value.getSerializedSize();
    }

    public static int computeMessageSizeNoTag(MessageLite value) {
        int size = value.getSerializedSize();
        return computeRawVarint32Size(size) + size;
    }

    public static int computeLazyFieldSizeNoTag(LazyFieldLite value) {
        int size = value.getSerializedSize();
        return computeRawVarint32Size(size) + size;
    }

    public static int computeBytesSizeNoTag(ByteString value) {
        return computeRawVarint32Size(value.size()) + value.size();
    }

    public static int computeByteArraySizeNoTag(byte[] value) {
        return computeRawVarint32Size(value.length) + value.length;
    }

    public static int computeUInt32SizeNoTag(int value) {
        return computeRawVarint32Size(value);
    }

    public static int computeEnumSizeNoTag(int value) {
        return computeInt32SizeNoTag(value);
    }

    public static int computeSFixed32SizeNoTag(int value) {
        return 4;
    }

    public static int computeSFixed64SizeNoTag(long value) {
        return 8;
    }

    public static int computeSInt32SizeNoTag(int value) {
        return computeRawVarint32Size(encodeZigZag32(value));
    }

    public static int computeSInt64SizeNoTag(long value) {
        return computeRawVarint64Size(encodeZigZag64(value));
    }

    private void refreshBuffer() throws IOException {
        if (this.output == null) {
            throw new OutOfSpaceException();
        }
        this.output.write(this.buffer, 0, this.position);
        this.position = 0;
    }

    public void flush() throws IOException {
        if (this.output != null) {
            refreshBuffer();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/protobuf/CodedOutputStream$OutOfSpaceException.class */
    public static class OutOfSpaceException extends IOException {
        OutOfSpaceException() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }
    }

    public void writeRawByte(byte value) throws IOException {
        if (this.position == this.limit) {
            refreshBuffer();
        }
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        bArr[i] = value;
        this.totalBytesWritten++;
    }

    public void writeRawByte(int value) throws IOException {
        writeRawByte((byte) value);
    }

    public void writeRawBytes(ByteString value) throws IOException {
        writeRawBytes(value, 0, value.size());
    }

    public void writeRawBytes(byte[] value) throws IOException {
        writeRawBytes(value, 0, value.length);
    }

    public void writeRawBytes(byte[] value, int offset, int length) throws IOException {
        if (this.limit - this.position >= length) {
            System.arraycopy(value, offset, this.buffer, this.position, length);
            this.position += length;
            this.totalBytesWritten += length;
            return;
        }
        int bytesWritten = this.limit - this.position;
        System.arraycopy(value, offset, this.buffer, this.position, bytesWritten);
        int offset2 = offset + bytesWritten;
        int length2 = length - bytesWritten;
        this.position = this.limit;
        this.totalBytesWritten += bytesWritten;
        refreshBuffer();
        if (length2 <= this.limit) {
            System.arraycopy(value, offset2, this.buffer, 0, length2);
            this.position = length2;
        } else {
            this.output.write(value, offset2, length2);
        }
        this.totalBytesWritten += length2;
    }

    public void writeRawBytes(ByteString value, int offset, int length) throws IOException {
        if (this.limit - this.position >= length) {
            value.copyTo(this.buffer, offset, this.position, length);
            this.position += length;
            this.totalBytesWritten += length;
            return;
        }
        int bytesWritten = this.limit - this.position;
        value.copyTo(this.buffer, offset, this.position, bytesWritten);
        int offset2 = offset + bytesWritten;
        int length2 = length - bytesWritten;
        this.position = this.limit;
        this.totalBytesWritten += bytesWritten;
        refreshBuffer();
        if (length2 <= this.limit) {
            value.copyTo(this.buffer, offset2, 0, length2);
            this.position = length2;
        } else {
            value.writeTo(this.output, offset2, length2);
        }
        this.totalBytesWritten += length2;
    }

    public void writeTag(int fieldNumber, int wireType) throws IOException {
        writeRawVarint32(WireFormat.makeTag(fieldNumber, wireType));
    }

    public static int computeTagSize(int fieldNumber) {
        return computeRawVarint32Size(WireFormat.makeTag(fieldNumber, 0));
    }

    public void writeRawVarint32(int value) throws IOException {
        while ((value & (-128)) != 0) {
            writeRawByte((value & 127) | 128);
            value >>>= 7;
        }
        writeRawByte(value);
    }

    public static int computeRawVarint32Size(int value) {
        if ((value & (-128)) == 0) {
            return 1;
        }
        if ((value & (-16384)) == 0) {
            return 2;
        }
        if ((value & (-2097152)) == 0) {
            return 3;
        }
        return (value & (-268435456)) == 0 ? 4 : 5;
    }

    public void writeRawVarint64(long value) throws IOException {
        while ((value & (-128)) != 0) {
            writeRawByte((((int) value) & 127) | 128);
            value >>>= 7;
        }
        writeRawByte((int) value);
    }

    public static int computeRawVarint64Size(long value) {
        if ((value & (-128)) == 0) {
            return 1;
        }
        if ((value & (-16384)) == 0) {
            return 2;
        }
        if ((value & (-2097152)) == 0) {
            return 3;
        }
        if ((value & (-268435456)) == 0) {
            return 4;
        }
        if ((value & (-34359738368L)) == 0) {
            return 5;
        }
        if ((value & (-4398046511104L)) == 0) {
            return 6;
        }
        if ((value & (-562949953421312L)) == 0) {
            return 7;
        }
        if ((value & (-72057594037927936L)) == 0) {
            return 8;
        }
        return (value & Long.MIN_VALUE) == 0 ? 9 : 10;
    }

    public void writeRawLittleEndian32(int value) throws IOException {
        writeRawByte(value & 255);
        writeRawByte((value >> 8) & 255);
        writeRawByte((value >> 16) & 255);
        writeRawByte((value >> 24) & 255);
    }

    public void writeRawLittleEndian64(long value) throws IOException {
        writeRawByte(((int) value) & 255);
        writeRawByte(((int) (value >> 8)) & 255);
        writeRawByte(((int) (value >> 16)) & 255);
        writeRawByte(((int) (value >> 24)) & 255);
        writeRawByte(((int) (value >> 32)) & 255);
        writeRawByte(((int) (value >> 40)) & 255);
        writeRawByte(((int) (value >> 48)) & 255);
        writeRawByte(((int) (value >> 56)) & 255);
    }

    public static int encodeZigZag32(int n) {
        return (n << 1) ^ (n >> 31);
    }

    public static long encodeZigZag64(long n) {
        return (n << 1) ^ (n >> 63);
    }
}
