package org.bson.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/io/Bits.class */
public class Bits {
    public static void readFully(InputStream inputStream, byte[] buffer) throws IOException {
        readFully(inputStream, buffer, buffer.length);
    }

    public static void readFully(InputStream inputStream, byte[] buffer, int length) throws IOException {
        readFully(inputStream, buffer, 0, length);
    }

    public static void readFully(InputStream inputStream, byte[] buffer, int offset, int length) throws IOException {
        if (buffer.length < length + offset) {
            throw new IllegalArgumentException("Buffer is too small");
        }
        int arrayOffset = offset;
        int bytesToRead = length;
        while (bytesToRead > 0) {
            int bytesRead = inputStream.read(buffer, arrayOffset, bytesToRead);
            if (bytesRead < 0) {
                throw new EOFException();
            }
            bytesToRead -= bytesRead;
            arrayOffset += bytesRead;
        }
    }

    public static int readInt(InputStream inputStream) throws IOException {
        return readInt(inputStream, new byte[4]);
    }

    public static int readInt(InputStream inputStream, byte[] buffer) throws IOException {
        readFully(inputStream, buffer, 4);
        return readInt(buffer);
    }

    public static int readInt(byte[] buffer) {
        return readInt(buffer, 0);
    }

    public static int readInt(byte[] buffer, int offset) {
        int x = 0 | ((255 & buffer[offset + 0]) << 0);
        return x | ((255 & buffer[offset + 1]) << 8) | ((255 & buffer[offset + 2]) << 16) | ((255 & buffer[offset + 3]) << 24);
    }

    public static int readIntBE(byte[] buffer, int offset) {
        int x = 0 | ((255 & buffer[offset + 0]) << 24);
        return x | ((255 & buffer[offset + 1]) << 16) | ((255 & buffer[offset + 2]) << 8) | ((255 & buffer[offset + 3]) << 0);
    }

    public static long readLong(InputStream inputStream) throws IOException {
        return readLong(inputStream, new byte[8]);
    }

    public static long readLong(InputStream inputStream, byte[] buffer) throws IOException {
        readFully(inputStream, buffer, 8);
        return readLong(buffer);
    }

    public static long readLong(byte[] buffer) {
        return readLong(buffer, 0);
    }

    public static long readLong(byte[] buffer, int offset) {
        long x = 0 | ((255 & buffer[offset + 0]) << 0);
        return x | ((255 & buffer[offset + 1]) << 8) | ((255 & buffer[offset + 2]) << 16) | ((255 & buffer[offset + 3]) << 24) | ((255 & buffer[offset + 4]) << 32) | ((255 & buffer[offset + 5]) << 40) | ((255 & buffer[offset + 6]) << 48) | ((255 & buffer[offset + 7]) << 56);
    }
}
