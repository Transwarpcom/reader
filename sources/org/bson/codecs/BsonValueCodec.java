package org.bson.codecs;

import org.bson.BsonReader;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/BsonValueCodec.class */
public class BsonValueCodec implements Codec<BsonValue> {
    private final CodecRegistry codecRegistry;

    public BsonValueCodec() {
        this(CodecRegistries.fromProviders(new BsonValueCodecProvider()));
    }

    public BsonValueCodec(CodecRegistry codecRegistry) {
        this.codecRegistry = codecRegistry;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.bson.codecs.Decoder
    public BsonValue decode(BsonReader reader, DecoderContext decoderContext) {
        return (BsonValue) this.codecRegistry.get(BsonValueCodecProvider.getClassForBsonType(reader.getCurrentBsonType())).decode(reader, decoderContext);
    }

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, BsonValue value, EncoderContext encoderContext) {
        Codec codec = this.codecRegistry.get(value.getClass());
        encoderContext.encodeWithChildContext(codec, writer, value);
    }

    @Override // org.bson.codecs.Encoder
    public Class<BsonValue> getEncoderClass() {
        return BsonValue.class;
    }
}
