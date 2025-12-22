package com.mongodb.internal.connection;

import com.mongodb.MongoInternalException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.bson.ByteBuf;
import org.bson.io.BsonOutput;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/Compressor.class */
abstract class Compressor {
    static final int BUFFER_SIZE = 256;

    abstract String getName();

    abstract byte getId();

    Compressor() {
    }

    void compress(List<ByteBuf> source, BsonOutput target) throws IOException {
        BufferExposingByteArrayOutputStream baos = new BufferExposingByteArrayOutputStream(1024);
        OutputStream outputStream = null;
        try {
            try {
                outputStream = getOutputStream(baos);
                byte[] scratch = new byte[256];
                for (ByteBuf cur : source) {
                    while (cur.hasRemaining()) {
                        int numBytes = Math.min(cur.remaining(), scratch.length);
                        cur.get(scratch, 0, numBytes);
                        outputStream.write(scratch, 0, numBytes);
                    }
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                    }
                }
                target.writeBytes(baos.getInternalBytes(), 0, baos.size());
            } catch (IOException e2) {
                throw new MongoInternalException("Unexpected IOException", e2);
            }
        } catch (Throwable th) {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e3) {
                    throw th;
                }
            }
            throw th;
        }
    }

    void uncompress(ByteBuf source, ByteBuf target) throws IOException {
        InputStream inputStream = null;
        try {
            try {
                inputStream = getInputStream(new ByteBufInputStream(source));
                byte[] scratch = new byte[256];
                for (int numBytes = inputStream.read(scratch); numBytes != -1; numBytes = inputStream.read(scratch)) {
                    target.put(scratch, 0, numBytes);
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                    }
                }
            } catch (IOException e2) {
                throw new MongoInternalException("Unexpected IOException", e2);
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    throw th;
                }
            }
            throw th;
        }
    }

    OutputStream getOutputStream(OutputStream source) throws IOException {
        throw new UnsupportedEncodingException();
    }

    InputStream getInputStream(InputStream source) throws IOException {
        throw new UnsupportedOperationException();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/Compressor$ByteBufInputStream.class */
    private static final class ByteBufInputStream extends InputStream {
        private final ByteBuf source;

        ByteBufInputStream(ByteBuf source) {
            this.source = source;
        }

        @Override // java.io.InputStream
        public int read(byte[] bytes, int offset, int length) {
            if (!this.source.hasRemaining()) {
                return -1;
            }
            int bytesToRead = length > this.source.remaining() ? this.source.remaining() : length;
            this.source.get(bytes, offset, bytesToRead);
            return bytesToRead;
        }

        @Override // java.io.InputStream
        public int read() {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/Compressor$BufferExposingByteArrayOutputStream.class */
    private static final class BufferExposingByteArrayOutputStream extends ByteArrayOutputStream {
        BufferExposingByteArrayOutputStream(int size) {
            super(size);
        }

        byte[] getInternalBytes() {
            return this.buf;
        }
    }
}
