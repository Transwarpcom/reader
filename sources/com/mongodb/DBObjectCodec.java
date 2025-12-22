package com.mongodb;

import com.mongodb.assertions.Assertions;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;
import org.bson.BSON;
import org.bson.BSONObject;
import org.bson.BsonBinary;
import org.bson.BsonBinarySubType;
import org.bson.BsonDbPointer;
import org.bson.BsonDocument;
import org.bson.BsonDocumentWriter;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.codecs.BsonTypeClassMap;
import org.bson.codecs.BsonTypeCodecMap;
import org.bson.codecs.BsonValueCodecProvider;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.IdGenerator;
import org.bson.codecs.ObjectIdGenerator;
import org.bson.codecs.ValueCodecProvider;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.BSONTimestamp;
import org.bson.types.Binary;
import org.bson.types.CodeWScope;
import org.bson.types.Symbol;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/DBObjectCodec.class */
public class DBObjectCodec implements CollectibleCodec<DBObject> {
    private static final BsonTypeClassMap DEFAULT_BSON_TYPE_CLASS_MAP = createDefaultBsonTypeClassMap();
    private static final CodecRegistry DEFAULT_REGISTRY = CodecRegistries.fromProviders((List<? extends CodecProvider>) Arrays.asList(new ValueCodecProvider(), new BsonValueCodecProvider(), new DBObjectCodecProvider()));
    private static final String ID_FIELD_NAME = "_id";
    private final CodecRegistry codecRegistry;
    private final BsonTypeCodecMap bsonTypeCodecMap;
    private final DBObjectFactory objectFactory;
    private final IdGenerator idGenerator;

    static BsonTypeClassMap createDefaultBsonTypeClassMap() {
        Map<BsonType, Class<?>> replacements = new HashMap<>();
        replacements.put(BsonType.REGULAR_EXPRESSION, Pattern.class);
        replacements.put(BsonType.SYMBOL, String.class);
        replacements.put(BsonType.TIMESTAMP, BSONTimestamp.class);
        replacements.put(BsonType.JAVASCRIPT_WITH_SCOPE, null);
        replacements.put(BsonType.DOCUMENT, null);
        return new BsonTypeClassMap(replacements);
    }

    static BsonTypeClassMap getDefaultBsonTypeClassMap() {
        return DEFAULT_BSON_TYPE_CLASS_MAP;
    }

    static CodecRegistry getDefaultRegistry() {
        return DEFAULT_REGISTRY;
    }

    public DBObjectCodec() {
        this(DEFAULT_REGISTRY);
    }

    public DBObjectCodec(CodecRegistry codecRegistry) {
        this(codecRegistry, DEFAULT_BSON_TYPE_CLASS_MAP);
    }

    public DBObjectCodec(CodecRegistry codecRegistry, BsonTypeClassMap bsonTypeClassMap) {
        this(codecRegistry, bsonTypeClassMap, new BasicDBObjectFactory());
    }

    public DBObjectCodec(CodecRegistry codecRegistry, BsonTypeClassMap bsonTypeClassMap, DBObjectFactory objectFactory) {
        this.idGenerator = new ObjectIdGenerator();
        this.objectFactory = (DBObjectFactory) Assertions.notNull("objectFactory", objectFactory);
        this.codecRegistry = (CodecRegistry) Assertions.notNull("codecRegistry", codecRegistry);
        this.bsonTypeCodecMap = new BsonTypeCodecMap((BsonTypeClassMap) Assertions.notNull("bsonTypeClassMap", bsonTypeClassMap), codecRegistry);
    }

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, DBObject document, EncoderContext encoderContext) {
        writer.writeStartDocument();
        beforeFields(writer, encoderContext, document);
        for (String key : document.keySet()) {
            if (!skipField(encoderContext, key)) {
                writer.writeName(key);
                writeValue(writer, encoderContext, document.get(key));
            }
        }
        writer.writeEndDocument();
    }

    @Override // org.bson.codecs.Decoder
    public DBObject decode(BsonReader reader, DecoderContext decoderContext) {
        List<String> path = new ArrayList<>(10);
        return readDocument(reader, decoderContext, path);
    }

    @Override // org.bson.codecs.Encoder
    public Class<DBObject> getEncoderClass() {
        return DBObject.class;
    }

    @Override // org.bson.codecs.CollectibleCodec
    public boolean documentHasId(DBObject document) {
        return document.containsField(ID_FIELD_NAME);
    }

    @Override // org.bson.codecs.CollectibleCodec
    public BsonValue getDocumentId(DBObject document) {
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
    public DBObject generateIdIfAbsentFromDocument(DBObject document) {
        if (!documentHasId(document)) {
            document.put(ID_FIELD_NAME, this.idGenerator.generate());
        }
        return document;
    }

    private void beforeFields(BsonWriter bsonWriter, EncoderContext encoderContext, DBObject document) {
        if (encoderContext.isEncodingCollectibleDocument() && document.containsField(ID_FIELD_NAME)) {
            bsonWriter.writeName(ID_FIELD_NAME);
            writeValue(bsonWriter, null, document.get(ID_FIELD_NAME));
        }
    }

    private boolean skipField(EncoderContext encoderContext, String key) {
        return encoderContext.isEncodingCollectibleDocument() && key.equals(ID_FIELD_NAME);
    }

    private void writeValue(BsonWriter bsonWriter, EncoderContext encoderContext, Object initialValue) {
        Object value = BSON.applyEncodingHooks(initialValue);
        if (value == null) {
            bsonWriter.writeNull();
            return;
        }
        if (value instanceof DBRef) {
            encodeDBRef(bsonWriter, (DBRef) value);
            return;
        }
        if (value instanceof Map) {
            encodeMap(bsonWriter, (Map) value);
            return;
        }
        if (value instanceof Iterable) {
            encodeIterable(bsonWriter, (Iterable) value);
            return;
        }
        if (value instanceof BSONObject) {
            encodeBsonObject(bsonWriter, (BSONObject) value);
            return;
        }
        if (value instanceof CodeWScope) {
            encodeCodeWScope(bsonWriter, (CodeWScope) value);
            return;
        }
        if (value instanceof byte[]) {
            encodeByteArray(bsonWriter, (byte[]) value);
            return;
        }
        if (value.getClass().isArray()) {
            encodeArray(bsonWriter, value);
        } else if (value instanceof Symbol) {
            bsonWriter.writeSymbol(((Symbol) value).getSymbol());
        } else {
            Codec codec = this.codecRegistry.get(value.getClass());
            codec.encode(bsonWriter, value, encoderContext);
        }
    }

    private void encodeMap(BsonWriter bsonWriter, Map<String, Object> document) {
        bsonWriter.writeStartDocument();
        for (Map.Entry<String, Object> entry : document.entrySet()) {
            bsonWriter.writeName(entry.getKey());
            writeValue(bsonWriter, null, entry.getValue());
        }
        bsonWriter.writeEndDocument();
    }

    private void encodeBsonObject(BsonWriter bsonWriter, BSONObject document) {
        bsonWriter.writeStartDocument();
        for (String key : document.keySet()) {
            bsonWriter.writeName(key);
            writeValue(bsonWriter, null, document.get(key));
        }
        bsonWriter.writeEndDocument();
    }

    private void encodeByteArray(BsonWriter bsonWriter, byte[] value) {
        bsonWriter.writeBinaryData(new BsonBinary(value));
    }

    private void encodeArray(BsonWriter bsonWriter, Object value) {
        bsonWriter.writeStartArray();
        int size = Array.getLength(value);
        for (int i = 0; i < size; i++) {
            writeValue(bsonWriter, null, Array.get(value, i));
        }
        bsonWriter.writeEndArray();
    }

    private void encodeDBRef(BsonWriter bsonWriter, DBRef dbRef) {
        bsonWriter.writeStartDocument();
        bsonWriter.writeString("$ref", dbRef.getCollectionName());
        bsonWriter.writeName("$id");
        writeValue(bsonWriter, null, dbRef.getId());
        if (dbRef.getDatabaseName() != null) {
            bsonWriter.writeString("$db", dbRef.getDatabaseName());
        }
        bsonWriter.writeEndDocument();
    }

    private void encodeCodeWScope(BsonWriter bsonWriter, CodeWScope value) {
        bsonWriter.writeJavaScriptWithScope(value.getCode());
        encodeBsonObject(bsonWriter, value.getScope());
    }

    private void encodeIterable(BsonWriter bsonWriter, Iterable iterable) {
        bsonWriter.writeStartArray();
        for (Object cur : iterable) {
            writeValue(bsonWriter, null, cur);
        }
        bsonWriter.writeEndArray();
    }

    private Object readValue(BsonReader reader, DecoderContext decoderContext, String fieldName, List<String> path) {
        Object initialRetVal;
        BsonType bsonType = reader.getCurrentBsonType();
        if (bsonType.isContainer() && fieldName != null) {
            path.add(fieldName);
        }
        switch (bsonType) {
            case DOCUMENT:
                initialRetVal = verifyForDBRef(readDocument(reader, decoderContext, path));
                break;
            case ARRAY:
                initialRetVal = readArray(reader, decoderContext, path);
                break;
            case JAVASCRIPT_WITH_SCOPE:
                initialRetVal = readCodeWScope(reader, decoderContext, path);
                break;
            case DB_POINTER:
                BsonDbPointer dbPointer = reader.readDBPointer();
                initialRetVal = new DBRef(dbPointer.getNamespace(), dbPointer.getId());
                break;
            case BINARY:
                initialRetVal = readBinary(reader, decoderContext);
                break;
            case NULL:
                reader.readNull();
                initialRetVal = null;
                break;
            default:
                initialRetVal = this.bsonTypeCodecMap.get(bsonType).decode(reader, decoderContext);
                break;
        }
        if (bsonType.isContainer() && fieldName != null) {
            path.remove(fieldName);
        }
        return BSON.applyDecodingHooks(initialRetVal);
    }

    private Object readBinary(BsonReader reader, DecoderContext decoderContext) {
        byte bsonBinarySubType = reader.peekBinarySubType();
        if (BsonBinarySubType.isUuid(bsonBinarySubType) && reader.peekBinarySize() == 16) {
            return this.codecRegistry.get(UUID.class).decode(reader, decoderContext);
        }
        if (bsonBinarySubType == BsonBinarySubType.BINARY.getValue() || bsonBinarySubType == BsonBinarySubType.OLD_BINARY.getValue()) {
            return this.codecRegistry.get(byte[].class).decode(reader, decoderContext);
        }
        return this.codecRegistry.get(Binary.class).decode(reader, decoderContext);
    }

    private List readArray(BsonReader reader, DecoderContext decoderContext, List<String> path) {
        reader.readStartArray();
        BasicDBList list = new BasicDBList();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            list.add(readValue(reader, decoderContext, null, path));
        }
        reader.readEndArray();
        return list;
    }

    private DBObject readDocument(BsonReader reader, DecoderContext decoderContext, List<String> path) {
        DBObject document = this.objectFactory.getInstance(path);
        reader.readStartDocument();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String fieldName = reader.readName();
            document.put(fieldName, readValue(reader, decoderContext, fieldName, path));
        }
        reader.readEndDocument();
        return document;
    }

    private CodeWScope readCodeWScope(BsonReader reader, DecoderContext decoderContext, List<String> path) {
        return new CodeWScope(reader.readJavaScriptWithScope(), readDocument(reader, decoderContext, path));
    }

    private Object verifyForDBRef(DBObject document) {
        if (document.containsField("$ref") && document.containsField("$id")) {
            return new DBRef((String) document.get("$db"), (String) document.get("$ref"), document.get("$id"));
        }
        return document;
    }
}
