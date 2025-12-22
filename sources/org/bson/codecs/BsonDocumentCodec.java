package org.bson.codecs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.bson.BsonDocument;
import org.bson.BsonElement;
import org.bson.BsonObjectId;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/BsonDocumentCodec.class */
public class BsonDocumentCodec implements CollectibleCodec<BsonDocument> {
    private static final String ID_FIELD_NAME = "_id";
    private static final CodecRegistry DEFAULT_REGISTRY = CodecRegistries.fromProviders(new BsonValueCodecProvider());
    private final CodecRegistry codecRegistry;
    private final BsonTypeCodecMap bsonTypeCodecMap;

    public BsonDocumentCodec() {
        this(DEFAULT_REGISTRY);
    }

    public BsonDocumentCodec(CodecRegistry codecRegistry) {
        if (codecRegistry == null) {
            throw new IllegalArgumentException("Codec registry can not be null");
        }
        this.codecRegistry = codecRegistry;
        this.bsonTypeCodecMap = new BsonTypeCodecMap(BsonValueCodecProvider.getBsonTypeClassMap(), codecRegistry);
    }

    public CodecRegistry getCodecRegistry() {
        return this.codecRegistry;
    }

    @Override // org.bson.codecs.Decoder
    public BsonDocument decode(BsonReader reader, DecoderContext decoderContext) {
        List<BsonElement> keyValuePairs = new ArrayList<>();
        reader.readStartDocument();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String fieldName = reader.readName();
            keyValuePairs.add(new BsonElement(fieldName, readValue(reader, decoderContext)));
        }
        reader.readEndDocument();
        return new BsonDocument(keyValuePairs);
    }

    protected BsonValue readValue(BsonReader reader, DecoderContext decoderContext) {
        return (BsonValue) this.bsonTypeCodecMap.get(reader.getCurrentBsonType()).decode(reader, decoderContext);
    }

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, BsonDocument value, EncoderContext encoderContext) {
        writer.writeStartDocument();
        beforeFields(writer, encoderContext, value);
        for (Map.Entry<String, BsonValue> entry : value.entrySet()) {
            if (!skipField(encoderContext, entry.getKey())) {
                writer.writeName(entry.getKey());
                writeValue(writer, encoderContext, entry.getValue());
            }
        }
        writer.writeEndDocument();
    }

    private void beforeFields(BsonWriter bsonWriter, EncoderContext encoderContext, BsonDocument value) {
        if (encoderContext.isEncodingCollectibleDocument() && value.containsKey(ID_FIELD_NAME)) {
            bsonWriter.writeName(ID_FIELD_NAME);
            writeValue(bsonWriter, encoderContext, value.get(ID_FIELD_NAME));
        }
    }

    private boolean skipField(EncoderContext encoderContext, String key) {
        return encoderContext.isEncodingCollectibleDocument() && key.equals(ID_FIELD_NAME);
    }

    private void writeValue(BsonWriter writer, EncoderContext encoderContext, BsonValue value) {
        Codec codec = this.codecRegistry.get(value.getClass());
        encoderContext.encodeWithChildContext(codec, writer, value);
    }

    @Override // org.bson.codecs.Encoder
    public Class<BsonDocument> getEncoderClass() {
        return BsonDocument.class;
    }

    @Override // org.bson.codecs.CollectibleCodec
    public BsonDocument generateIdIfAbsentFromDocument(BsonDocument document) {
        if (!documentHasId(document)) {
            document.put(ID_FIELD_NAME, (BsonValue) new BsonObjectId(new ObjectId()));
        }
        return document;
    }

    @Override // org.bson.codecs.CollectibleCodec
    public boolean documentHasId(BsonDocument document) {
        return document.containsKey(ID_FIELD_NAME);
    }

    @Override // org.bson.codecs.CollectibleCodec
    public BsonValue getDocumentId(BsonDocument document) {
        return document.get(ID_FIELD_NAME);
    }
}
