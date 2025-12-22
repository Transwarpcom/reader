package org.bson.codecs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bson.BsonArray;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.assertions.Assertions;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/BsonArrayCodec.class */
public class BsonArrayCodec implements Codec<BsonArray> {
    private static final CodecRegistry DEFAULT_REGISTRY = CodecRegistries.fromProviders(new BsonValueCodecProvider());
    private final CodecRegistry codecRegistry;

    public BsonArrayCodec() {
        this(DEFAULT_REGISTRY);
    }

    public BsonArrayCodec(CodecRegistry codecRegistry) {
        this.codecRegistry = (CodecRegistry) Assertions.notNull("codecRegistry", codecRegistry);
    }

    @Override // org.bson.codecs.Decoder
    public BsonArray decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartArray();
        List<BsonValue> list = new ArrayList<>();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            list.add(readValue(reader, decoderContext));
        }
        reader.readEndArray();
        return new BsonArray(list);
    }

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, BsonArray array, EncoderContext encoderContext) {
        writer.writeStartArray();
        Iterator<BsonValue> it = array.iterator();
        while (it.hasNext()) {
            BsonValue value = it.next();
            Codec codec = this.codecRegistry.get(value.getClass());
            encoderContext.encodeWithChildContext(codec, writer, value);
        }
        writer.writeEndArray();
    }

    @Override // org.bson.codecs.Encoder
    public Class<BsonArray> getEncoderClass() {
        return BsonArray.class;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected BsonValue readValue(BsonReader reader, DecoderContext decoderContext) {
        return (BsonValue) this.codecRegistry.get(BsonValueCodecProvider.getClassForBsonType(reader.getCurrentBsonType())).decode(reader, decoderContext);
    }
}
