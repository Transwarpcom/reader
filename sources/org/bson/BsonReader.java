package org.bson;

import java.io.Closeable;
import org.bson.types.Decimal128;
import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonReader.class */
public interface BsonReader extends Closeable {
    BsonType getCurrentBsonType();

    String getCurrentName();

    BsonBinary readBinaryData();

    byte peekBinarySubType();

    int peekBinarySize();

    BsonBinary readBinaryData(String str);

    boolean readBoolean();

    boolean readBoolean(String str);

    BsonType readBsonType();

    long readDateTime();

    long readDateTime(String str);

    double readDouble();

    double readDouble(String str);

    void readEndArray();

    void readEndDocument();

    int readInt32();

    int readInt32(String str);

    long readInt64();

    long readInt64(String str);

    Decimal128 readDecimal128();

    Decimal128 readDecimal128(String str);

    String readJavaScript();

    String readJavaScript(String str);

    String readJavaScriptWithScope();

    String readJavaScriptWithScope(String str);

    void readMaxKey();

    void readMaxKey(String str);

    void readMinKey();

    void readMinKey(String str);

    String readName();

    void readName(String str);

    void readNull();

    void readNull(String str);

    ObjectId readObjectId();

    ObjectId readObjectId(String str);

    BsonRegularExpression readRegularExpression();

    BsonRegularExpression readRegularExpression(String str);

    BsonDbPointer readDBPointer();

    BsonDbPointer readDBPointer(String str);

    void readStartArray();

    void readStartDocument();

    String readString();

    String readString(String str);

    String readSymbol();

    String readSymbol(String str);

    BsonTimestamp readTimestamp();

    BsonTimestamp readTimestamp(String str);

    void readUndefined();

    void readUndefined(String str);

    void skipName();

    void skipValue();

    @Deprecated
    void mark();

    BsonReaderMark getMark();

    void reset();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();
}
