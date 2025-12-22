package org.bson.codecs;

import org.bson.BsonBinaryReader;
import org.bson.BsonBinaryWriter;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.RawBsonDocument;
import org.bson.io.BasicOutputBuffer;
import org.bson.io.ByteBufferBsonInput;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/RawBsonDocumentCodec.class */
public class RawBsonDocumentCodec implements Codec<RawBsonDocument> {
    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, RawBsonDocument value, EncoderContext encoderContext) {
        BsonBinaryReader reader = new BsonBinaryReader(new ByteBufferBsonInput(value.getByteBuffer()));
        try {
            writer.pipe(reader);
            reader.close();
        } catch (Throwable th) {
            reader.close();
            throw th;
        }
    }

    @Override // org.bson.codecs.Decoder
    public RawBsonDocument decode(BsonReader reader, DecoderContext decoderContext) {
        BasicOutputBuffer buffer = new BasicOutputBuffer(0);
        BsonBinaryWriter writer = new BsonBinaryWriter(buffer);
        try {
            writer.pipe(reader);
            RawBsonDocument rawBsonDocument = new RawBsonDocument(buffer.getInternalBuffer(), 0, buffer.getPosition());
            writer.close();
            buffer.close();
            return rawBsonDocument;
        } catch (Throwable th) {
            writer.close();
            buffer.close();
            throw th;
        }
    }

    @Override // org.bson.codecs.Encoder
    public Class<RawBsonDocument> getEncoderClass() {
        return RawBsonDocument.class;
    }
}
