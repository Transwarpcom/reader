package org.bson;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.bson.codecs.Encoder;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonDocumentWrapper.class */
public final class BsonDocumentWrapper<T> extends BsonDocument {
    private static final long serialVersionUID = 1;
    private final transient T wrappedDocument;
    private final transient Encoder<T> encoder;
    private BsonDocument unwrapped;

    public static BsonDocument asBsonDocument(Object document, CodecRegistry codecRegistry) {
        if (document == null) {
            return null;
        }
        if (document instanceof BsonDocument) {
            return (BsonDocument) document;
        }
        return new BsonDocumentWrapper(document, codecRegistry.get(document.getClass()));
    }

    public BsonDocumentWrapper(T wrappedDocument, Encoder<T> encoder) {
        if (wrappedDocument == null) {
            throw new IllegalArgumentException("Document can not be null");
        }
        this.wrappedDocument = wrappedDocument;
        this.encoder = encoder;
    }

    public T getWrappedDocument() {
        return this.wrappedDocument;
    }

    public Encoder<T> getEncoder() {
        return this.encoder;
    }

    public boolean isUnwrapped() {
        return this.unwrapped != null;
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public int size() {
        return getUnwrapped().size();
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public boolean isEmpty() {
        return getUnwrapped().isEmpty();
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public boolean containsKey(Object key) {
        return getUnwrapped().containsKey(key);
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public boolean containsValue(Object value) {
        return getUnwrapped().containsValue(value);
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public BsonValue get(Object key) {
        return getUnwrapped().get(key);
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public BsonValue put(String key, BsonValue value) {
        return getUnwrapped().put(key, value);
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public BsonValue remove(Object key) {
        return getUnwrapped().remove(key);
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public void putAll(Map<? extends String, ? extends BsonValue> m) {
        super.putAll(m);
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public void clear() {
        super.clear();
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public Set<String> keySet() {
        return getUnwrapped().keySet();
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public Collection<BsonValue> values() {
        return getUnwrapped().values();
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public Set<Map.Entry<String, BsonValue>> entrySet() {
        return getUnwrapped().entrySet();
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public boolean equals(Object o) {
        return getUnwrapped().equals(o);
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public int hashCode() {
        return getUnwrapped().hashCode();
    }

    @Override // org.bson.BsonDocument
    public String toString() {
        return getUnwrapped().toString();
    }

    @Override // org.bson.BsonDocument
    /* renamed from: clone */
    public BsonDocument mo1002clone() {
        return getUnwrapped().mo1002clone();
    }

    private BsonDocument getUnwrapped() {
        if (this.encoder == null) {
            throw new BsonInvalidOperationException("Can not unwrap a BsonDocumentWrapper with no Encoder");
        }
        if (this.unwrapped == null) {
            BsonDocument unwrapped = new BsonDocument();
            BsonWriter writer = new BsonDocumentWriter(unwrapped);
            this.encoder.encode(writer, this.wrappedDocument, EncoderContext.builder().build());
            this.unwrapped = unwrapped;
        }
        return this.unwrapped;
    }

    private Object writeReplace() {
        return getUnwrapped();
    }

    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }
}
