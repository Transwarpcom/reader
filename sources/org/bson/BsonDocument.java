package org.bson;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bson.codecs.BsonDocumentCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.bson.io.BasicOutputBuffer;
import org.bson.json.JsonReader;
import org.bson.json.JsonWriter;
import org.bson.json.JsonWriterSettings;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonDocument.class */
public class BsonDocument extends BsonValue implements Map<String, BsonValue>, Cloneable, Bson, Serializable {
    private static final long serialVersionUID = 1;
    private final Map<String, BsonValue> map = new LinkedHashMap();

    public static BsonDocument parse(String json) {
        return new BsonDocumentCodec().decode((BsonReader) new JsonReader(json), DecoderContext.builder().build());
    }

    public BsonDocument(List<BsonElement> bsonElements) {
        for (BsonElement cur : bsonElements) {
            put(cur.getName(), cur.getValue());
        }
    }

    public BsonDocument(String key, BsonValue value) {
        put(key, value);
    }

    public BsonDocument() {
    }

    @Override // org.bson.conversions.Bson
    public <C> BsonDocument toBsonDocument(Class<C> documentClass, CodecRegistry codecRegistry) {
        return this;
    }

    @Override // org.bson.BsonValue
    public BsonType getBsonType() {
        return BsonType.DOCUMENT;
    }

    public int size() {
        return this.map.size();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public boolean containsKey(Object key) {
        return this.map.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return this.map.containsValue(value);
    }

    @Override // java.util.Map
    public BsonValue get(Object key) {
        return this.map.get(key);
    }

    public BsonDocument getDocument(Object key) {
        throwIfKeyAbsent(key);
        return get(key).asDocument();
    }

    public BsonArray getArray(Object key) {
        throwIfKeyAbsent(key);
        return get(key).asArray();
    }

    public BsonNumber getNumber(Object key) {
        throwIfKeyAbsent(key);
        return get(key).asNumber();
    }

    public BsonInt32 getInt32(Object key) {
        throwIfKeyAbsent(key);
        return get(key).asInt32();
    }

    public BsonInt64 getInt64(Object key) {
        throwIfKeyAbsent(key);
        return get(key).asInt64();
    }

    public BsonDecimal128 getDecimal128(Object key) {
        throwIfKeyAbsent(key);
        return get(key).asDecimal128();
    }

    public BsonDouble getDouble(Object key) {
        throwIfKeyAbsent(key);
        return get(key).asDouble();
    }

    public BsonBoolean getBoolean(Object key) {
        throwIfKeyAbsent(key);
        return get(key).asBoolean();
    }

    public BsonString getString(Object key) {
        throwIfKeyAbsent(key);
        return get(key).asString();
    }

    public BsonDateTime getDateTime(Object key) {
        throwIfKeyAbsent(key);
        return get(key).asDateTime();
    }

    public BsonTimestamp getTimestamp(Object key) {
        throwIfKeyAbsent(key);
        return get(key).asTimestamp();
    }

    public BsonObjectId getObjectId(Object key) {
        throwIfKeyAbsent(key);
        return get(key).asObjectId();
    }

    public BsonRegularExpression getRegularExpression(Object key) {
        throwIfKeyAbsent(key);
        return get(key).asRegularExpression();
    }

    public BsonBinary getBinary(Object key) {
        throwIfKeyAbsent(key);
        return get(key).asBinary();
    }

    public boolean isNull(Object key) {
        if (!containsKey(key)) {
            return false;
        }
        return get(key).isNull();
    }

    public boolean isDocument(Object key) {
        if (!containsKey(key)) {
            return false;
        }
        return get(key).isDocument();
    }

    public boolean isArray(Object key) {
        if (!containsKey(key)) {
            return false;
        }
        return get(key).isArray();
    }

    public boolean isNumber(Object key) {
        if (!containsKey(key)) {
            return false;
        }
        return get(key).isNumber();
    }

    public boolean isInt32(Object key) {
        if (!containsKey(key)) {
            return false;
        }
        return get(key).isInt32();
    }

    public boolean isInt64(Object key) {
        if (!containsKey(key)) {
            return false;
        }
        return get(key).isInt64();
    }

    public boolean isDecimal128(Object key) {
        if (!containsKey(key)) {
            return false;
        }
        return get(key).isDecimal128();
    }

    public boolean isDouble(Object key) {
        if (!containsKey(key)) {
            return false;
        }
        return get(key).isDouble();
    }

    public boolean isBoolean(Object key) {
        if (!containsKey(key)) {
            return false;
        }
        return get(key).isBoolean();
    }

    public boolean isString(Object key) {
        if (!containsKey(key)) {
            return false;
        }
        return get(key).isString();
    }

    public boolean isDateTime(Object key) {
        if (!containsKey(key)) {
            return false;
        }
        return get(key).isDateTime();
    }

    public boolean isTimestamp(Object key) {
        if (!containsKey(key)) {
            return false;
        }
        return get(key).isTimestamp();
    }

    public boolean isObjectId(Object key) {
        if (!containsKey(key)) {
            return false;
        }
        return get(key).isObjectId();
    }

    public boolean isBinary(Object key) {
        if (!containsKey(key)) {
            return false;
        }
        return get(key).isBinary();
    }

    public BsonValue get(Object key, BsonValue defaultValue) {
        BsonValue value = get(key);
        return value != null ? value : defaultValue;
    }

    public BsonDocument getDocument(Object key, BsonDocument defaultValue) {
        if (!containsKey(key)) {
            return defaultValue;
        }
        return get(key).asDocument();
    }

    public BsonArray getArray(Object key, BsonArray defaultValue) {
        if (!containsKey(key)) {
            return defaultValue;
        }
        return get(key).asArray();
    }

    public BsonNumber getNumber(Object key, BsonNumber defaultValue) {
        if (!containsKey(key)) {
            return defaultValue;
        }
        return get(key).asNumber();
    }

    public BsonInt32 getInt32(Object key, BsonInt32 defaultValue) {
        if (!containsKey(key)) {
            return defaultValue;
        }
        return get(key).asInt32();
    }

    public BsonInt64 getInt64(Object key, BsonInt64 defaultValue) {
        if (!containsKey(key)) {
            return defaultValue;
        }
        return get(key).asInt64();
    }

    public BsonDecimal128 getDecimal128(Object key, BsonDecimal128 defaultValue) {
        if (!containsKey(key)) {
            return defaultValue;
        }
        return get(key).asDecimal128();
    }

    public BsonDouble getDouble(Object key, BsonDouble defaultValue) {
        if (!containsKey(key)) {
            return defaultValue;
        }
        return get(key).asDouble();
    }

    public BsonBoolean getBoolean(Object key, BsonBoolean defaultValue) {
        if (!containsKey(key)) {
            return defaultValue;
        }
        return get(key).asBoolean();
    }

    public BsonString getString(Object key, BsonString defaultValue) {
        if (!containsKey(key)) {
            return defaultValue;
        }
        return get(key).asString();
    }

    public BsonDateTime getDateTime(Object key, BsonDateTime defaultValue) {
        if (!containsKey(key)) {
            return defaultValue;
        }
        return get(key).asDateTime();
    }

    public BsonTimestamp getTimestamp(Object key, BsonTimestamp defaultValue) {
        if (!containsKey(key)) {
            return defaultValue;
        }
        return get(key).asTimestamp();
    }

    public BsonObjectId getObjectId(Object key, BsonObjectId defaultValue) {
        if (!containsKey(key)) {
            return defaultValue;
        }
        return get(key).asObjectId();
    }

    public BsonBinary getBinary(Object key, BsonBinary defaultValue) {
        if (!containsKey(key)) {
            return defaultValue;
        }
        return get(key).asBinary();
    }

    public BsonRegularExpression getRegularExpression(Object key, BsonRegularExpression defaultValue) {
        if (!containsKey(key)) {
            return defaultValue;
        }
        return get(key).asRegularExpression();
    }

    @Override // java.util.Map
    public BsonValue put(String key, BsonValue value) {
        if (value == null) {
            throw new IllegalArgumentException(String.format("The value for key %s can not be null", key));
        }
        if (key.contains("��")) {
            throw new BSONException(String.format("BSON cstring '%s' is not valid because it contains a null character at index %d", key, Integer.valueOf(key.indexOf(0))));
        }
        return this.map.put(key, value);
    }

    @Override // java.util.Map
    public BsonValue remove(Object key) {
        return this.map.remove(key);
    }

    public void putAll(Map<? extends String, ? extends BsonValue> m) {
        for (Map.Entry<? extends String, ? extends BsonValue> cur : m.entrySet()) {
            put(cur.getKey(), cur.getValue());
        }
    }

    public void clear() {
        this.map.clear();
    }

    public Set<String> keySet() {
        return this.map.keySet();
    }

    public Collection<BsonValue> values() {
        return this.map.values();
    }

    public Set<Map.Entry<String, BsonValue>> entrySet() {
        return this.map.entrySet();
    }

    public BsonDocument append(String key, BsonValue value) {
        put(key, value);
        return this;
    }

    public String getFirstKey() {
        return keySet().iterator().next();
    }

    public BsonReader asBsonReader() {
        return new BsonDocumentReader(this);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BsonDocument)) {
            return false;
        }
        BsonDocument that = (BsonDocument) o;
        return entrySet().equals(that.entrySet());
    }

    public int hashCode() {
        return entrySet().hashCode();
    }

    public String toJson() {
        return toJson(new JsonWriterSettings());
    }

    public String toJson(JsonWriterSettings settings) {
        StringWriter writer = new StringWriter();
        new BsonDocumentCodec().encode((BsonWriter) new JsonWriter(writer, settings), this, EncoderContext.builder().build());
        return writer.toString();
    }

    public String toString() {
        return toJson();
    }

    @Override // 
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public BsonDocument mo1002clone() {
        BsonDocument to = new BsonDocument();
        for (Map.Entry<String, BsonValue> cur : entrySet()) {
            switch (cur.getValue().getBsonType()) {
                case DOCUMENT:
                    to.put(cur.getKey(), (BsonValue) cur.getValue().asDocument().mo1002clone());
                    break;
                case ARRAY:
                    to.put(cur.getKey(), (BsonValue) cur.getValue().asArray().mo1046clone());
                    break;
                case BINARY:
                    to.put(cur.getKey(), (BsonValue) BsonBinary.clone(cur.getValue().asBinary()));
                    break;
                case JAVASCRIPT_WITH_SCOPE:
                    to.put(cur.getKey(), (BsonValue) BsonJavaScriptWithScope.clone(cur.getValue().asJavaScriptWithScope()));
                    break;
                default:
                    to.put(cur.getKey(), cur.getValue());
                    break;
            }
        }
        return to;
    }

    private void throwIfKeyAbsent(Object key) {
        if (!containsKey(key)) {
            throw new BsonInvalidOperationException("Document does not contain key " + key);
        }
    }

    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonDocument$SerializationProxy.class */
    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = 1;
        private final byte[] bytes;

        SerializationProxy(BsonDocument document) {
            BasicOutputBuffer buffer = new BasicOutputBuffer();
            new BsonDocumentCodec().encode((BsonWriter) new BsonBinaryWriter(buffer), document, EncoderContext.builder().build());
            this.bytes = new byte[buffer.size()];
            int curPos = 0;
            for (ByteBuf cur : buffer.getByteBuffers()) {
                System.arraycopy(cur.array(), cur.position(), this.bytes, curPos, cur.limit());
                curPos += cur.position();
            }
        }

        private Object readResolve() {
            return new BsonDocumentCodec().decode((BsonReader) new BsonBinaryReader(ByteBuffer.wrap(this.bytes).order(ByteOrder.LITTLE_ENDIAN)), DecoderContext.builder().build());
        }
    }
}
