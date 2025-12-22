package org.bson.codecs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.bson.BsonBinarySubType;
import org.bson.BsonDocument;
import org.bson.BsonDocumentWriter;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.Transformer;
import org.bson.assertions.Assertions;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/DocumentCodec.class */
public class DocumentCodec implements CollectibleCodec<Document> {
    private static final String ID_FIELD_NAME = "_id";
    private static final CodecRegistry DEFAULT_REGISTRY = CodecRegistries.fromProviders((List<? extends CodecProvider>) Arrays.asList(new ValueCodecProvider(), new BsonValueCodecProvider(), new DocumentCodecProvider()));
    private static final BsonTypeClassMap DEFAULT_BSON_TYPE_CLASS_MAP = new BsonTypeClassMap();
    private final BsonTypeCodecMap bsonTypeCodecMap;
    private final CodecRegistry registry;
    private final IdGenerator idGenerator;
    private final Transformer valueTransformer;

    public DocumentCodec() {
        this(DEFAULT_REGISTRY);
    }

    public DocumentCodec(CodecRegistry registry) {
        this(registry, DEFAULT_BSON_TYPE_CLASS_MAP);
    }

    public DocumentCodec(CodecRegistry registry, BsonTypeClassMap bsonTypeClassMap) {
        this(registry, bsonTypeClassMap, null);
    }

    public DocumentCodec(CodecRegistry registry, BsonTypeClassMap bsonTypeClassMap, Transformer valueTransformer) {
        this.registry = (CodecRegistry) Assertions.notNull("registry", registry);
        this.bsonTypeCodecMap = new BsonTypeCodecMap((BsonTypeClassMap) Assertions.notNull("bsonTypeClassMap", bsonTypeClassMap), registry);
        this.idGenerator = new ObjectIdGenerator();
        this.valueTransformer = valueTransformer != null ? valueTransformer : new Transformer() { // from class: org.bson.codecs.DocumentCodec.1
            @Override // org.bson.Transformer
            public Object transform(Object value) {
                return value;
            }
        };
    }

    @Override // org.bson.codecs.CollectibleCodec
    public boolean documentHasId(Document document) {
        return document.containsKey(ID_FIELD_NAME);
    }

    @Override // org.bson.codecs.CollectibleCodec
    public BsonValue getDocumentId(Document document) {
        if (!documentHasId(document)) {
            throw new IllegalStateException("The document does not contain an _id");
        }
        Object id = document.get(ID_FIELD_NAME);
        if (id instanceof BsonValue) {
            return (BsonValue) id;
        }
        BsonDocument idHoldingDocument = new BsonDocument();
        BsonWriter writer = new BsonDocumentWriter(idHoldingDocument);
        writer.writeStartDocument();
        writer.writeName(ID_FIELD_NAME);
        writeValue(writer, EncoderContext.builder().build(), id);
        writer.writeEndDocument();
        return idHoldingDocument.get((Object) ID_FIELD_NAME);
    }

    @Override // org.bson.codecs.CollectibleCodec
    public Document generateIdIfAbsentFromDocument(Document document) {
        if (!documentHasId(document)) {
            document.put(ID_FIELD_NAME, this.idGenerator.generate());
        }
        return document;
    }

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, Document document, EncoderContext encoderContext) {
        writeMap(writer, document, encoderContext);
    }

    @Override // org.bson.codecs.Decoder
    public Document decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = new Document();
        reader.readStartDocument();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String fieldName = reader.readName();
            document.put(fieldName, readValue(reader, decoderContext));
        }
        reader.readEndDocument();
        return document;
    }

    @Override // org.bson.codecs.Encoder
    public Class<Document> getEncoderClass() {
        return Document.class;
    }

    private void beforeFields(BsonWriter bsonWriter, EncoderContext encoderContext, Map<String, Object> document) {
        if (encoderContext.isEncodingCollectibleDocument() && document.containsKey(ID_FIELD_NAME)) {
            bsonWriter.writeName(ID_FIELD_NAME);
            writeValue(bsonWriter, encoderContext, document.get(ID_FIELD_NAME));
        }
    }

    private boolean skipField(EncoderContext encoderContext, String key) {
        return encoderContext.isEncodingCollectibleDocument() && key.equals(ID_FIELD_NAME);
    }

    private void writeValue(BsonWriter writer, EncoderContext encoderContext, Object value) {
        if (value == null) {
            writer.writeNull();
            return;
        }
        if (value instanceof Iterable) {
            writeIterable(writer, (Iterable) value, encoderContext.getChildContext());
        } else if (value instanceof Map) {
            writeMap(writer, (Map) value, encoderContext.getChildContext());
        } else {
            Codec codec = this.registry.get(value.getClass());
            encoderContext.encodeWithChildContext(codec, writer, value);
        }
    }

    private void writeMap(BsonWriter writer, Map<String, Object> map, EncoderContext encoderContext) {
        writer.writeStartDocument();
        beforeFields(writer, encoderContext, map);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (!skipField(encoderContext, entry.getKey())) {
                writer.writeName(entry.getKey());
                writeValue(writer, encoderContext, entry.getValue());
            }
        }
        writer.writeEndDocument();
    }

    private void writeIterable(BsonWriter writer, Iterable<Object> list, EncoderContext encoderContext) {
        writer.writeStartArray();
        for (Object value : list) {
            writeValue(writer, encoderContext, value);
        }
        writer.writeEndArray();
    }

    private Object readValue(BsonReader reader, DecoderContext decoderContext) {
        BsonType bsonType = reader.getCurrentBsonType();
        if (bsonType == BsonType.NULL) {
            reader.readNull();
            return null;
        }
        if (bsonType == BsonType.ARRAY) {
            return readList(reader, decoderContext);
        }
        if (bsonType == BsonType.BINARY && BsonBinarySubType.isUuid(reader.peekBinarySubType()) && reader.peekBinarySize() == 16) {
            return this.registry.get(UUID.class).decode(reader, decoderContext);
        }
        return this.valueTransformer.transform(this.bsonTypeCodecMap.get(bsonType).decode(reader, decoderContext));
    }

    private List<Object> readList(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartArray();
        List<Object> list = new ArrayList<>();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            list.add(readValue(reader, decoderContext));
        }
        reader.readEndArray();
        return list;
    }
}
