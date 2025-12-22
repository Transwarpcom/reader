package org.bson.codecs;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bson.BsonBinarySubType;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.Transformer;
import org.bson.assertions.Assertions;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/IterableCodec.class */
public class IterableCodec implements Codec<Iterable> {
    private final CodecRegistry registry;
    private final BsonTypeCodecMap bsonTypeCodecMap;
    private final Transformer valueTransformer;

    public IterableCodec(CodecRegistry registry, BsonTypeClassMap bsonTypeClassMap) {
        this(registry, bsonTypeClassMap, null);
    }

    public IterableCodec(CodecRegistry registry, BsonTypeClassMap bsonTypeClassMap, Transformer valueTransformer) {
        this.registry = (CodecRegistry) Assertions.notNull("registry", registry);
        this.bsonTypeCodecMap = new BsonTypeCodecMap((BsonTypeClassMap) Assertions.notNull("bsonTypeClassMap", bsonTypeClassMap), registry);
        this.valueTransformer = valueTransformer != null ? valueTransformer : new Transformer() { // from class: org.bson.codecs.IterableCodec.1
            @Override // org.bson.Transformer
            public Object transform(Object objectToTransform) {
                return objectToTransform;
            }
        };
    }

    @Override // org.bson.codecs.Decoder
    public Iterable decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartArray();
        List<Object> list = new ArrayList<>();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            list.add(readValue(reader, decoderContext));
        }
        reader.readEndArray();
        return list;
    }

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, Iterable value, EncoderContext encoderContext) {
        writer.writeStartArray();
        for (Object cur : value) {
            writeValue(writer, encoderContext, cur);
        }
        writer.writeEndArray();
    }

    @Override // org.bson.codecs.Encoder
    public Class<Iterable> getEncoderClass() {
        return Iterable.class;
    }

    private void writeValue(BsonWriter writer, EncoderContext encoderContext, Object value) {
        if (value == null) {
            writer.writeNull();
        } else {
            Codec codec = this.registry.get(value.getClass());
            encoderContext.encodeWithChildContext(codec, writer, value);
        }
    }

    private Object readValue(BsonReader reader, DecoderContext decoderContext) {
        BsonType bsonType = reader.getCurrentBsonType();
        if (bsonType == BsonType.NULL) {
            reader.readNull();
            return null;
        }
        if (bsonType == BsonType.BINARY && BsonBinarySubType.isUuid(reader.peekBinarySubType()) && reader.peekBinarySize() == 16) {
            return this.registry.get(UUID.class).decode(reader, decoderContext);
        }
        return this.valueTransformer.transform(this.bsonTypeCodecMap.get(bsonType).decode(reader, decoderContext));
    }
}
