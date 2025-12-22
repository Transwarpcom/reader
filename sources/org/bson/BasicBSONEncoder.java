package org.bson;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import org.bson.AbstractBsonWriter;
import org.bson.io.BasicOutputBuffer;
import org.bson.io.OutputBuffer;
import org.bson.types.BSONTimestamp;
import org.bson.types.Binary;
import org.bson.types.Code;
import org.bson.types.CodeWScope;
import org.bson.types.Decimal128;
import org.bson.types.MaxKey;
import org.bson.types.MinKey;
import org.bson.types.ObjectId;
import org.bson.types.Symbol;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BasicBSONEncoder.class */
public class BasicBSONEncoder implements BSONEncoder {
    private BsonBinaryWriter bsonWriter;
    private OutputBuffer outputBuffer;

    @Override // org.bson.BSONEncoder
    public byte[] encode(BSONObject document) {
        OutputBuffer outputBuffer = new BasicOutputBuffer();
        set(outputBuffer);
        putObject(document);
        done();
        return outputBuffer.toByteArray();
    }

    @Override // org.bson.BSONEncoder
    public void done() {
        this.bsonWriter.close();
        this.bsonWriter = null;
    }

    @Override // org.bson.BSONEncoder
    public void set(OutputBuffer buffer) {
        if (this.bsonWriter != null) {
            throw new IllegalStateException("Performing another operation at this moment");
        }
        this.outputBuffer = buffer;
        this.bsonWriter = new BsonBinaryWriter(buffer);
    }

    protected OutputBuffer getOutputBuffer() {
        return this.outputBuffer;
    }

    protected BsonBinaryWriter getBsonWriter() {
        return this.bsonWriter;
    }

    @Override // org.bson.BSONEncoder
    public int putObject(BSONObject document) {
        int startPosition = getOutputBuffer().getPosition();
        this.bsonWriter.writeStartDocument();
        if (isTopLevelDocument() && document.containsField("_id")) {
            _putObjectField("_id", document.get("_id"));
        }
        for (String key : document.keySet()) {
            if (!isTopLevelDocument() || !key.equals("_id")) {
                _putObjectField(key, document.get(key));
            }
        }
        this.bsonWriter.writeEndDocument();
        return getOutputBuffer().getPosition() - startPosition;
    }

    private boolean isTopLevelDocument() {
        return this.bsonWriter.getContext().getParentContext() == null;
    }

    protected void putName(String name) {
        if (this.bsonWriter.getState() == AbstractBsonWriter.State.NAME) {
            this.bsonWriter.writeName(name);
        }
    }

    protected void _putObjectField(String name, Object initialValue) {
        if ("_transientFields".equals(name)) {
            return;
        }
        if (name.contains("��")) {
            throw new IllegalArgumentException("Document field names can't have a NULL character. (Bad Key: '" + name + "')");
        }
        if ("$where".equals(name) && (initialValue instanceof String)) {
            putCode(name, new Code((String) initialValue));
        }
        Object value = BSON.applyEncodingHooks(initialValue);
        if (value == null) {
            putNull(name);
            return;
        }
        if (value instanceof Date) {
            putDate(name, (Date) value);
            return;
        }
        if (value instanceof Number) {
            putNumber(name, (Number) value);
            return;
        }
        if (value instanceof Decimal128) {
            putDecimal128(name, (Decimal128) value);
            return;
        }
        if (value instanceof Character) {
            putString(name, value.toString());
            return;
        }
        if (value instanceof String) {
            putString(name, value.toString());
            return;
        }
        if (value instanceof ObjectId) {
            putObjectId(name, (ObjectId) value);
            return;
        }
        if (value instanceof Boolean) {
            putBoolean(name, (Boolean) value);
            return;
        }
        if (value instanceof Pattern) {
            putPattern(name, (Pattern) value);
            return;
        }
        if (value instanceof Iterable) {
            putIterable(name, (Iterable) value);
            return;
        }
        if (value instanceof BSONObject) {
            putObject(name, (BSONObject) value);
            return;
        }
        if (value instanceof Map) {
            putMap(name, (Map) value);
            return;
        }
        if (value instanceof byte[]) {
            putBinary(name, (byte[]) value);
            return;
        }
        if (value instanceof Binary) {
            putBinary(name, (Binary) value);
            return;
        }
        if (value instanceof UUID) {
            putUUID(name, (UUID) value);
            return;
        }
        if (value.getClass().isArray()) {
            putArray(name, value);
            return;
        }
        if (value instanceof Symbol) {
            putSymbol(name, (Symbol) value);
            return;
        }
        if (value instanceof BSONTimestamp) {
            putTimestamp(name, (BSONTimestamp) value);
            return;
        }
        if (value instanceof CodeWScope) {
            putCodeWScope(name, (CodeWScope) value);
            return;
        }
        if (value instanceof Code) {
            putCode(name, (Code) value);
            return;
        }
        if (value instanceof MinKey) {
            putMinKey(name);
        } else if (value instanceof MaxKey) {
            putMaxKey(name);
        } else if (!putSpecial(name, value)) {
            throw new IllegalArgumentException("Can't serialize " + value.getClass());
        }
    }

    protected void putNull(String name) {
        putName(name);
        this.bsonWriter.writeNull();
    }

    protected void putUndefined(String name) {
        putName(name);
        this.bsonWriter.writeUndefined();
    }

    protected void putTimestamp(String name, BSONTimestamp timestamp) {
        putName(name);
        this.bsonWriter.writeTimestamp(new BsonTimestamp(timestamp.getTime(), timestamp.getInc()));
    }

    protected void putCode(String name, Code code) {
        putName(name);
        this.bsonWriter.writeJavaScript(code.getCode());
    }

    protected void putCodeWScope(String name, CodeWScope codeWScope) {
        putName(name);
        this.bsonWriter.writeJavaScriptWithScope(codeWScope.getCode());
        putObject(codeWScope.getScope());
    }

    protected void putBoolean(String name, Boolean value) {
        putName(name);
        this.bsonWriter.writeBoolean(value.booleanValue());
    }

    protected void putDate(String name, Date date) {
        putName(name);
        this.bsonWriter.writeDateTime(date.getTime());
    }

    protected void putNumber(String name, Number number) {
        putName(name);
        if ((number instanceof Integer) || (number instanceof Short) || (number instanceof Byte) || (number instanceof AtomicInteger)) {
            this.bsonWriter.writeInt32(number.intValue());
            return;
        }
        if ((number instanceof Long) || (number instanceof AtomicLong)) {
            this.bsonWriter.writeInt64(number.longValue());
        } else {
            if ((number instanceof Float) || (number instanceof Double)) {
                this.bsonWriter.writeDouble(number.doubleValue());
                return;
            }
            throw new IllegalArgumentException("Can't serialize " + number.getClass());
        }
    }

    protected void putDecimal128(String name, Decimal128 value) {
        putName(name);
        this.bsonWriter.writeDecimal128(value);
    }

    protected void putBinary(String name, byte[] bytes) {
        putName(name);
        this.bsonWriter.writeBinaryData(new BsonBinary(bytes));
    }

    protected void putBinary(String name, Binary binary) {
        putName(name);
        this.bsonWriter.writeBinaryData(new BsonBinary(binary.getType(), binary.getData()));
    }

    protected void putUUID(String name, UUID uuid) {
        putName(name);
        byte[] bytes = new byte[16];
        writeLongToArrayLittleEndian(bytes, 0, uuid.getMostSignificantBits());
        writeLongToArrayLittleEndian(bytes, 8, uuid.getLeastSignificantBits());
        this.bsonWriter.writeBinaryData(new BsonBinary(BsonBinarySubType.UUID_LEGACY, bytes));
    }

    protected void putSymbol(String name, Symbol symbol) {
        putName(name);
        this.bsonWriter.writeSymbol(symbol.getSymbol());
    }

    protected void putString(String name, String value) {
        putName(name);
        this.bsonWriter.writeString(value);
    }

    protected void putPattern(String name, Pattern value) {
        putName(name);
        this.bsonWriter.writeRegularExpression(new BsonRegularExpression(value.pattern(), BSON.regexFlags(value.flags())));
    }

    protected void putObjectId(String name, ObjectId objectId) {
        putName(name);
        this.bsonWriter.writeObjectId(objectId);
    }

    protected void putArray(String name, Object object) {
        putName(name);
        this.bsonWriter.writeStartArray();
        if (object instanceof int[]) {
            for (int i : (int[]) object) {
                this.bsonWriter.writeInt32(i);
            }
        } else if (object instanceof long[]) {
            for (long i2 : (long[]) object) {
                this.bsonWriter.writeInt64(i2);
            }
        } else if (object instanceof float[]) {
            for (float i3 : (float[]) object) {
                this.bsonWriter.writeDouble(i3);
            }
        } else if (object instanceof short[]) {
            for (short i4 : (short[]) object) {
                this.bsonWriter.writeInt32(i4);
            }
        } else if (object instanceof byte[]) {
            for (byte i5 : (byte[]) object) {
                this.bsonWriter.writeInt32(i5);
            }
        } else if (object instanceof double[]) {
            for (double i6 : (double[]) object) {
                this.bsonWriter.writeDouble(i6);
            }
        } else if (object instanceof boolean[]) {
            for (boolean i7 : (boolean[]) object) {
                this.bsonWriter.writeBoolean(i7);
            }
        } else if (object instanceof String[]) {
            for (String i8 : (String[]) object) {
                this.bsonWriter.writeString(i8);
            }
        } else {
            int length = Array.getLength(object);
            for (int i9 = 0; i9 < length; i9++) {
                _putObjectField(String.valueOf(i9), Array.get(object, i9));
            }
        }
        this.bsonWriter.writeEndArray();
    }

    protected void putIterable(String name, Iterable iterable) {
        putName(name);
        this.bsonWriter.writeStartArray();
        for (Object o : iterable) {
            _putObjectField(String.valueOf(0), o);
        }
        this.bsonWriter.writeEndArray();
    }

    protected void putMap(String name, Map map) {
        putName(name);
        this.bsonWriter.writeStartDocument();
        for (Map.Entry entry : map.entrySet()) {
            _putObjectField((String) entry.getKey(), entry.getValue());
        }
        this.bsonWriter.writeEndDocument();
    }

    protected int putObject(String name, BSONObject document) {
        putName(name);
        return putObject(document);
    }

    protected boolean putSpecial(String name, Object special) {
        return false;
    }

    protected void putMinKey(String name) {
        putName(name);
        this.bsonWriter.writeMinKey();
    }

    protected void putMaxKey(String name) {
        putName(name);
        this.bsonWriter.writeMaxKey();
    }

    private static void writeLongToArrayLittleEndian(byte[] bytes, int offset, long x) {
        bytes[offset] = (byte) (255 & x);
        bytes[offset + 1] = (byte) (255 & (x >> 8));
        bytes[offset + 2] = (byte) (255 & (x >> 16));
        bytes[offset + 3] = (byte) (255 & (x >> 24));
        bytes[offset + 4] = (byte) (255 & (x >> 32));
        bytes[offset + 5] = (byte) (255 & (x >> 40));
        bytes[offset + 6] = (byte) (255 & (x >> 48));
        bytes[offset + 7] = (byte) (255 & (x >> 56));
    }
}
