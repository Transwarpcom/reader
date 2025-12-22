package org.bson;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.bson.io.Bits;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/LazyBSONDecoder.class */
public class LazyBSONDecoder implements BSONDecoder {
    private static final int BYTES_IN_INTEGER = 4;

    @Override // org.bson.BSONDecoder
    public BSONObject readObject(byte[] bytes) {
        BSONCallback bsonCallback = new LazyBSONCallback();
        decode(bytes, bsonCallback);
        return (BSONObject) bsonCallback.get();
    }

    @Override // org.bson.BSONDecoder
    public BSONObject readObject(InputStream in) throws IOException {
        BSONCallback bsonCallback = new LazyBSONCallback();
        decode(in, bsonCallback);
        return (BSONObject) bsonCallback.get();
    }

    @Override // org.bson.BSONDecoder
    public int decode(byte[] bytes, BSONCallback callback) {
        try {
            return decode(new ByteArrayInputStream(bytes), callback);
        } catch (IOException e) {
            throw new BSONException("Invalid bytes received", e);
        }
    }

    @Override // org.bson.BSONDecoder
    public int decode(InputStream in, BSONCallback callback) throws IOException {
        byte[] documentSizeBuffer = new byte[4];
        int documentSize = Bits.readInt(in, documentSizeBuffer);
        byte[] documentBytes = Arrays.copyOf(documentSizeBuffer, documentSize);
        Bits.readFully(in, documentBytes, 4, documentSize - 4);
        callback.gotBinary(null, (byte) 0, documentBytes);
        return documentSize;
    }
}
