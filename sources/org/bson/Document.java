package org.bson;

import java.io.Serializable;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.bson.assertions.Assertions;
import org.bson.codecs.Decoder;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.Encoder;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.bson.json.JsonReader;
import org.bson.json.JsonWriter;
import org.bson.json.JsonWriterSettings;
import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/Document.class */
public class Document implements Map<String, Object>, Serializable, Bson {
    private static final long serialVersionUID = 6297731997167536582L;
    private final LinkedHashMap<String, Object> documentAsMap;

    public Document() {
        this.documentAsMap = new LinkedHashMap<>();
    }

    public Document(String key, Object value) {
        this.documentAsMap = new LinkedHashMap<>();
        this.documentAsMap.put(key, value);
    }

    public Document(Map<String, Object> map) {
        this.documentAsMap = new LinkedHashMap<>(map);
    }

    public static Document parse(String json) {
        return parse(json, new DocumentCodec());
    }

    public static Document parse(String json, Decoder<Document> decoder) {
        Assertions.notNull("codec", decoder);
        JsonReader bsonReader = new JsonReader(json);
        return decoder.decode(bsonReader, DecoderContext.builder().build());
    }

    @Override // org.bson.conversions.Bson
    public <C> BsonDocument toBsonDocument(Class<C> documentClass, CodecRegistry codecRegistry) {
        return new BsonDocumentWrapper(this, codecRegistry.get(Document.class));
    }

    public Document append(String key, Object value) {
        this.documentAsMap.put(key, value);
        return this;
    }

    public <T> T get(Object key, Class<T> clazz) {
        Assertions.notNull("clazz", clazz);
        return clazz.cast(this.documentAsMap.get(key));
    }

    public <T> T get(Object obj, T t) {
        Assertions.notNull("defaultValue", t);
        T t2 = (T) this.documentAsMap.get(obj);
        return t2 == null ? t : t2;
    }

    public Integer getInteger(Object key) {
        return (Integer) get(key);
    }

    public int getInteger(Object key, int defaultValue) {
        return ((Integer) get(key, Integer.valueOf(defaultValue))).intValue();
    }

    public Long getLong(Object key) {
        return (Long) get(key);
    }

    public Double getDouble(Object key) {
        return (Double) get(key);
    }

    public String getString(Object key) {
        return (String) get(key);
    }

    public Boolean getBoolean(Object key) {
        return (Boolean) get(key);
    }

    public boolean getBoolean(Object key, boolean defaultValue) {
        return ((Boolean) get(key, Boolean.valueOf(defaultValue))).booleanValue();
    }

    public ObjectId getObjectId(Object key) {
        return (ObjectId) get(key);
    }

    public Date getDate(Object key) {
        return (Date) get(key);
    }

    public String toJson() {
        return toJson(new JsonWriterSettings());
    }

    public String toJson(JsonWriterSettings writerSettings) {
        return toJson(writerSettings, new DocumentCodec());
    }

    public String toJson(Encoder<Document> encoder) {
        return toJson(new JsonWriterSettings(), encoder);
    }

    public String toJson(JsonWriterSettings writerSettings, Encoder<Document> encoder) {
        JsonWriter writer = new JsonWriter(new StringWriter(), writerSettings);
        encoder.encode(writer, this, EncoderContext.builder().isEncodingCollectibleDocument(true).build());
        return writer.getWriter().toString();
    }

    @Override // java.util.Map
    public int size() {
        return this.documentAsMap.size();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.documentAsMap.isEmpty();
    }

    @Override // java.util.Map
    public boolean containsValue(Object value) {
        return this.documentAsMap.containsValue(value);
    }

    @Override // java.util.Map
    public boolean containsKey(Object key) {
        return this.documentAsMap.containsKey(key);
    }

    @Override // java.util.Map
    public Object get(Object key) {
        return this.documentAsMap.get(key);
    }

    @Override // java.util.Map
    public Object put(String key, Object value) {
        return this.documentAsMap.put(key, value);
    }

    @Override // java.util.Map
    public Object remove(Object key) {
        return this.documentAsMap.remove(key);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends String, ? extends Object> map) {
        this.documentAsMap.putAll(map);
    }

    @Override // java.util.Map
    public void clear() {
        this.documentAsMap.clear();
    }

    @Override // java.util.Map
    public Set<String> keySet() {
        return this.documentAsMap.keySet();
    }

    @Override // java.util.Map
    public Collection<Object> values() {
        return this.documentAsMap.values();
    }

    @Override // java.util.Map
    public Set<Map.Entry<String, Object>> entrySet() {
        return this.documentAsMap.entrySet();
    }

    @Override // java.util.Map
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Document document = (Document) o;
        if (!this.documentAsMap.equals(document.documentAsMap)) {
            return false;
        }
        return true;
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.documentAsMap.hashCode();
    }

    public String toString() {
        return "Document{" + this.documentAsMap + '}';
    }
}
