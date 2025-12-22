package org.bson.codecs;

import org.bson.BsonBinary;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.types.Binary;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/BinaryCodec.class */
public class BinaryCodec implements Codec<Binary> {
    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, Binary value, EncoderContext encoderContext) {
        writer.writeBinaryData(new BsonBinary(value.getType(), value.getData()));
    }

    @Override // org.bson.codecs.Decoder
    public Binary decode(BsonReader reader, DecoderContext decoderContext) {
        BsonBinary bsonBinary = reader.readBinaryData();
        return new Binary(bsonBinary.getType(), bsonBinary.getData());
    }

    @Override // org.bson.codecs.Encoder
    public Class<Binary> getEncoderClass() {
        return Binary.class;
    }
}
