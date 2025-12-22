package org.springframework.core.io.buffer;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.util.function.IntPredicate;
import org.springframework.util.Assert;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/core/io/buffer/DataBuffer.class */
public interface DataBuffer {
    DataBufferFactory factory();

    int indexOf(IntPredicate intPredicate, int i);

    int lastIndexOf(IntPredicate intPredicate, int i);

    int readableByteCount();

    int writableByteCount();

    int capacity();

    DataBuffer capacity(int i);

    int readPosition();

    DataBuffer readPosition(int i);

    int writePosition();

    DataBuffer writePosition(int i);

    byte getByte(int i);

    byte read();

    DataBuffer read(byte[] bArr);

    DataBuffer read(byte[] bArr, int i, int i2);

    DataBuffer write(byte b);

    DataBuffer write(byte[] bArr);

    DataBuffer write(byte[] bArr, int i, int i2);

    DataBuffer write(DataBuffer... dataBufferArr);

    DataBuffer write(ByteBuffer... byteBufferArr);

    DataBuffer slice(int i, int i2);

    ByteBuffer asByteBuffer();

    ByteBuffer asByteBuffer(int i, int i2);

    InputStream asInputStream();

    InputStream asInputStream(boolean z);

    OutputStream asOutputStream();

    default DataBuffer ensureCapacity(int capacity) {
        return this;
    }

    default DataBuffer write(CharSequence charSequence, Charset charset) {
        Assert.notNull(charSequence, "CharSequence must not be null");
        Assert.notNull(charset, "Charset must not be null");
        if (charSequence.length() != 0) {
            CharsetEncoder charsetEncoder = charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
            CharBuffer inBuffer = CharBuffer.wrap(charSequence);
            int estimatedSize = (int) (inBuffer.remaining() * charsetEncoder.averageBytesPerChar());
            ByteBuffer outBuffer = ensureCapacity(estimatedSize).asByteBuffer(writePosition(), writableByteCount());
            while (true) {
                CoderResult cr = inBuffer.hasRemaining() ? charsetEncoder.encode(inBuffer, outBuffer, true) : CoderResult.UNDERFLOW;
                if (cr.isUnderflow()) {
                    cr = charsetEncoder.flush(outBuffer);
                }
                if (cr.isUnderflow()) {
                    break;
                }
                if (cr.isOverflow()) {
                    writePosition(writePosition() + outBuffer.position());
                    int maximumSize = (int) (inBuffer.remaining() * charsetEncoder.maxBytesPerChar());
                    ensureCapacity(maximumSize);
                    outBuffer = asByteBuffer(writePosition(), writableByteCount());
                }
            }
            writePosition(writePosition() + outBuffer.position());
        }
        return this;
    }
}
