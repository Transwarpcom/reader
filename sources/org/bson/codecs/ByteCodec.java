package org.bson.codecs;

import org.bson.BsonInvalidOperationException;
import org.bson.BsonReader;
import org.bson.BsonWriter;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/ByteCodec.class */
public class ByteCodec implements Codec<Byte> {
    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, Byte value, EncoderContext encoderContext) {
        writer.writeInt32(value.byteValue());
    }

    @Override // org.bson.codecs.Decoder
    public Byte decode(BsonReader reader, DecoderContext decoderContext) {
        int value = NumberCodecHelper.decodeInt(reader);
        if (value < -128 || value > 127) {
            throw new BsonInvalidOperationException(String.format("%s can not be converted into a Byte.", Integer.valueOf(value)));
        }
        return Byte.valueOf((byte) value);
    }

    @Override // org.bson.codecs.Encoder
    public Class<Byte> getEncoderClass() {
        return Byte.class;
    }
}
