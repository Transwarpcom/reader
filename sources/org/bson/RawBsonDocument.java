package org.bson;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.bson.assertions.Assertions;
import org.bson.codecs.BsonDocumentCodec;
import org.bson.codecs.Codec;
import org.bson.codecs.Decoder;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.RawBsonDocumentCodec;
import org.bson.io.BasicOutputBuffer;
import org.bson.io.ByteBufferBsonInput;
import org.bson.json.JsonReader;
import org.bson.json.JsonWriter;
import org.bson.json.JsonWriterSettings;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/RawBsonDocument.class */
public final class RawBsonDocument extends BsonDocument {
    private static final long serialVersionUID = 1;
    private static final int MIN_BSON_DOCUMENT_SIZE = 5;
    private final byte[] bytes;
    private final int offset;
    private final int length;

    public static RawBsonDocument parse(String json) {
        Assertions.notNull("json", json);
        return new RawBsonDocumentCodec().decode((BsonReader) new JsonReader(json), DecoderContext.builder().build());
    }

    public RawBsonDocument(byte[] bytes) {
        this((byte[]) Assertions.notNull("bytes", bytes), 0, bytes.length);
    }

    public RawBsonDocument(byte[] bytes, int offset, int length) {
        Assertions.notNull("bytes", bytes);
        Assertions.isTrueArgument("offset >= 0", offset >= 0);
        Assertions.isTrueArgument("offset < bytes.length", offset < bytes.length);
        Assertions.isTrueArgument("length <= bytes.length - offset", length <= bytes.length - offset);
        Assertions.isTrueArgument("length >= 5", length >= 5);
        this.bytes = bytes;
        this.offset = offset;
        this.length = length;
    }

    public <T> RawBsonDocument(T document, Codec<T> codec) {
        Assertions.notNull("document", document);
        Assertions.notNull("codec", codec);
        BasicOutputBuffer buffer = new BasicOutputBuffer();
        BsonBinaryWriter writer = new BsonBinaryWriter(buffer);
        try {
            codec.encode(writer, document, EncoderContext.builder().build());
            this.bytes = buffer.getInternalBuffer();
            this.offset = 0;
            this.length = buffer.getPosition();
            writer.close();
        } catch (Throwable th) {
            writer.close();
            throw th;
        }
    }

    public ByteBuf getByteBuffer() {
        ByteBuffer buffer = ByteBuffer.wrap(this.bytes, this.offset, this.length);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        return new ByteBufNIO(buffer);
    }

    public <T> T decode(Codec<T> codec) {
        return (T) decode((Decoder) codec);
    }

    public <T> T decode(Decoder<T> decoder) {
        BsonBinaryReader reader = createReader();
        try {
            T tDecode = decoder.decode(reader, DecoderContext.builder().build());
            reader.close();
            return tDecode;
        } catch (Throwable th) {
            reader.close();
            throw th;
        }
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public void clear() {
        throw new UnsupportedOperationException("RawBsonDocument instances are immutable");
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public BsonValue put(String key, BsonValue value) {
        throw new UnsupportedOperationException("RawBsonDocument instances are immutable");
    }

    @Override // org.bson.BsonDocument
    public BsonDocument append(String key, BsonValue value) {
        throw new UnsupportedOperationException("RawBsonDocument instances are immutable");
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public void putAll(Map<? extends String, ? extends BsonValue> m) {
        throw new UnsupportedOperationException("RawBsonDocument instances are immutable");
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public BsonValue remove(Object key) {
        throw new UnsupportedOperationException("RawBsonDocument instances are immutable");
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public boolean isEmpty() {
        BsonBinaryReader bsonReader = createReader();
        try {
            bsonReader.readStartDocument();
            if (bsonReader.readBsonType() != BsonType.END_OF_DOCUMENT) {
                return false;
            }
            bsonReader.readEndDocument();
            return true;
        } finally {
            bsonReader.close();
        }
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public int size() {
        int size = 0;
        BsonBinaryReader bsonReader = createReader();
        try {
            bsonReader.readStartDocument();
            while (bsonReader.readBsonType() != BsonType.END_OF_DOCUMENT) {
                size++;
                bsonReader.readName();
                bsonReader.skipValue();
            }
            bsonReader.readEndDocument();
            return size;
        } finally {
            bsonReader.close();
        }
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public Set<Map.Entry<String, BsonValue>> entrySet() {
        return toBsonDocument().entrySet();
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public Collection<BsonValue> values() {
        return toBsonDocument().values();
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public Set<String> keySet() {
        return toBsonDocument().keySet();
    }

    @Override // org.bson.BsonDocument
    public String getFirstKey() {
        BsonBinaryReader bsonReader = createReader();
        try {
            bsonReader.readStartDocument();
            try {
                String name = bsonReader.readName();
                bsonReader.close();
                return name;
            } catch (BsonInvalidOperationException e) {
                throw new NoSuchElementException();
            }
        } catch (Throwable th) {
            bsonReader.close();
            throw th;
        }
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public boolean containsKey(Object key) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null");
        }
        BsonBinaryReader bsonReader = createReader();
        try {
            bsonReader.readStartDocument();
            while (bsonReader.readBsonType() != BsonType.END_OF_DOCUMENT) {
                if (bsonReader.readName().equals(key)) {
                    return true;
                }
                bsonReader.skipValue();
            }
            bsonReader.readEndDocument();
            bsonReader.close();
            return false;
        } finally {
            bsonReader.close();
        }
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public boolean containsValue(Object value) {
        BsonBinaryReader bsonReader = createReader();
        try {
            bsonReader.readStartDocument();
            while (bsonReader.readBsonType() != BsonType.END_OF_DOCUMENT) {
                bsonReader.skipName();
                if (RawBsonValueHelper.decode(this.bytes, bsonReader).equals(value)) {
                    return true;
                }
            }
            bsonReader.readEndDocument();
            bsonReader.close();
            return false;
        } finally {
            bsonReader.close();
        }
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public BsonValue get(Object key) {
        Assertions.notNull("key", key);
        BsonBinaryReader bsonReader = createReader();
        try {
            bsonReader.readStartDocument();
            while (bsonReader.readBsonType() != BsonType.END_OF_DOCUMENT) {
                if (bsonReader.readName().equals(key)) {
                    BsonValue bsonValueDecode = RawBsonValueHelper.decode(this.bytes, bsonReader);
                    bsonReader.close();
                    return bsonValueDecode;
                }
                bsonReader.skipValue();
            }
            bsonReader.readEndDocument();
            bsonReader.close();
            return null;
        } catch (Throwable th) {
            bsonReader.close();
            throw th;
        }
    }

    @Override // org.bson.BsonDocument
    public String toJson() {
        return toJson(new JsonWriterSettings());
    }

    @Override // org.bson.BsonDocument
    public String toJson(JsonWriterSettings settings) {
        StringWriter writer = new StringWriter();
        new RawBsonDocumentCodec().encode((BsonWriter) new JsonWriter(writer, settings), this, EncoderContext.builder().build());
        return writer.toString();
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public boolean equals(Object o) {
        return toBsonDocument().equals(o);
    }

    @Override // org.bson.BsonDocument, java.util.Map
    public int hashCode() {
        return toBsonDocument().hashCode();
    }

    @Override // org.bson.BsonDocument
    /* renamed from: clone */
    public BsonDocument mo1002clone() {
        return new RawBsonDocument((byte[]) this.bytes.clone(), this.offset, this.length);
    }

    private BsonBinaryReader createReader() {
        return new BsonBinaryReader(new ByteBufferBsonInput(getByteBuffer()));
    }

    private BsonDocument toBsonDocument() {
        BsonBinaryReader bsonReader = createReader();
        try {
            return new BsonDocumentCodec().decode((BsonReader) bsonReader, DecoderContext.builder().build());
        } finally {
            bsonReader.close();
        }
    }

    private Object writeReplace() {
        return new SerializationProxy(this.bytes, this.offset, this.length);
    }

    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/RawBsonDocument$SerializationProxy.class */
    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = 1;
        private final byte[] bytes;

        SerializationProxy(byte[] bytes, int offset, int length) {
            if (bytes.length == length) {
                this.bytes = bytes;
            } else {
                this.bytes = new byte[length];
                System.arraycopy(bytes, offset, this.bytes, 0, length);
            }
        }

        private Object readResolve() {
            return new RawBsonDocument(this.bytes);
        }
    }
}
