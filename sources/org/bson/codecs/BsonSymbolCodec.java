package org.bson.codecs;

import org.bson.BsonReader;
import org.bson.BsonSymbol;
import org.bson.BsonWriter;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/BsonSymbolCodec.class */
public class BsonSymbolCodec implements Codec<BsonSymbol> {
    @Override // org.bson.codecs.Decoder
    public BsonSymbol decode(BsonReader reader, DecoderContext decoderContext) {
        return new BsonSymbol(reader.readSymbol());
    }

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, BsonSymbol value, EncoderContext encoderContext) {
        writer.writeSymbol(value.getSymbol());
    }

    @Override // org.bson.codecs.Encoder
    public Class<BsonSymbol> getEncoderClass() {
        return BsonSymbol.class;
    }
}
