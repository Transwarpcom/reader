package org.bson;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import org.bson.io.Bits;
import org.bson.io.ByteBufferBsonInput;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BasicBSONDecoder.class */
public class BasicBSONDecoder implements BSONDecoder {
    @Override // org.bson.BSONDecoder
    public BSONObject readObject(byte[] bytes) {
        BSONCallback bsonCallback = new BasicBSONCallback();
        decode(bytes, bsonCallback);
        return (BSONObject) bsonCallback.get();
    }

    @Override // org.bson.BSONDecoder
    public BSONObject readObject(InputStream in) throws IOException {
        return readObject(readFully(in));
    }

    @Override // org.bson.BSONDecoder
    public int decode(byte[] bytes, BSONCallback callback) {
        BsonBinaryReader reader = new BsonBinaryReader(new ByteBufferBsonInput(new ByteBufNIO(ByteBuffer.wrap(bytes))));
        try {
            BsonWriter writer = new BSONCallbackAdapter(new BsonWriterSettings(), callback);
            writer.pipe(reader);
            int position = reader.getBsonInput().getPosition();
            reader.close();
            return position;
        } catch (Throwable th) {
            reader.close();
            throw th;
        }
    }

    @Override // org.bson.BSONDecoder
    public int decode(InputStream in, BSONCallback callback) throws IOException {
        return decode(readFully(in), callback);
    }

    private byte[] readFully(InputStream input) throws IOException {
        byte[] sizeBytes = new byte[4];
        Bits.readFully(input, sizeBytes);
        int size = Bits.readInt(sizeBytes);
        byte[] buffer = new byte[size];
        System.arraycopy(sizeBytes, 0, buffer, 0, 4);
        Bits.readFully(input, buffer, 4, size - 4);
        return buffer;
    }
}
