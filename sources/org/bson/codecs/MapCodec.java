package org.bson.codecs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.bson.BsonBinarySubType;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.Transformer;
import org.bson.assertions.Assertions;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/MapCodec.class */
public class MapCodec implements Codec<Map<String, Object>> {
    private static final CodecRegistry DEFAULT_REGISTRY = CodecRegistries.fromProviders((List<? extends CodecProvider>) Arrays.asList(new ValueCodecProvider(), new BsonValueCodecProvider(), new DocumentCodecProvider(), new IterableCodecProvider(), new MapCodecProvider()));
    private static final BsonTypeClassMap DEFAULT_BSON_TYPE_CLASS_MAP = new BsonTypeClassMap();
    private final BsonTypeCodecMap bsonTypeCodecMap;
    private final CodecRegistry registry;
    private final Transformer valueTransformer;

    public MapCodec() {
        this(DEFAULT_REGISTRY);
    }

    public MapCodec(CodecRegistry registry) {
        this(registry, DEFAULT_BSON_TYPE_CLASS_MAP);
    }

    public MapCodec(CodecRegistry registry, BsonTypeClassMap bsonTypeClassMap) {
        this(registry, bsonTypeClassMap, null);
    }

    public MapCodec(CodecRegistry registry, BsonTypeClassMap bsonTypeClassMap, Transformer valueTransformer) {
        this.registry = (CodecRegistry) Assertions.notNull("registry", registry);
        this.bsonTypeCodecMap = new BsonTypeCodecMap((BsonTypeClassMap) Assertions.notNull("bsonTypeClassMap", bsonTypeClassMap), registry);
        this.valueTransformer = valueTransformer != null ? valueTransformer : new Transformer() { // from class: org.bson.codecs.MapCodec.1
            @Override // org.bson.Transformer
            public Object transform(Object value) {
                return value;
            }
        };
    }

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, Map<String, Object> map, EncoderContext encoderContext) {
        writer.writeStartDocument();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            writer.writeName(entry.getKey());
            writeValue(writer, encoderContext, entry.getValue());
        }
        writer.writeEndDocument();
    }

    @Override // org.bson.codecs.Decoder
    public Map<String, Object> decode(BsonReader reader, DecoderContext decoderContext) {
        Map<String, Object> map = new HashMap<>();
        reader.readStartDocument();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String fieldName = reader.readName();
            map.put(fieldName, readValue(reader, decoderContext));
        }
        reader.readEndDocument();
        return map;
    }

    @Override // org.bson.codecs.Encoder
    public Class<Map<String, Object>> getEncoderClass() {
        return Map.class;
    }

    private Object readValue(BsonReader reader, DecoderContext decoderContext) {
        BsonType bsonType = reader.getCurrentBsonType();
        if (bsonType == BsonType.NULL) {
            reader.readNull();
            return null;
        }
        if (bsonType == BsonType.ARRAY) {
            return decoderContext.decodeWithChildContext(this.registry.get(List.class), reader);
        }
        if (bsonType == BsonType.BINARY && BsonBinarySubType.isUuid(reader.peekBinarySubType()) && reader.peekBinarySize() == 16) {
            return decoderContext.decodeWithChildContext(this.registry.get(UUID.class), reader);
        }
        return this.valueTransformer.transform(this.bsonTypeCodecMap.get(bsonType).decode(reader, decoderContext));
    }

    private void writeValue(BsonWriter writer, EncoderContext encoderContext, Object value) {
        if (value == null) {
            writer.writeNull();
        } else {
            Codec codec = this.registry.get(value.getClass());
            encoderContext.encodeWithChildContext(codec, writer, value);
        }
    }
}
