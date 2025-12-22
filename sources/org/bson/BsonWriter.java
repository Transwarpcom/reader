package org.bson;

import org.bson.types.Decimal128;
import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonWriter.class */
public interface BsonWriter {
    void flush();

    void writeBinaryData(BsonBinary bsonBinary);

    void writeBinaryData(String str, BsonBinary bsonBinary);

    void writeBoolean(boolean z);

    void writeBoolean(String str, boolean z);

    void writeDateTime(long j);

    void writeDateTime(String str, long j);

    void writeDBPointer(BsonDbPointer bsonDbPointer);

    void writeDBPointer(String str, BsonDbPointer bsonDbPointer);

    void writeDouble(double d);

    void writeDouble(String str, double d);

    void writeEndArray();

    void writeEndDocument();

    void writeInt32(int i);

    void writeInt32(String str, int i);

    void writeInt64(long j);

    void writeInt64(String str, long j);

    void writeDecimal128(Decimal128 decimal128);

    void writeDecimal128(String str, Decimal128 decimal128);

    void writeJavaScript(String str);

    void writeJavaScript(String str, String str2);

    void writeJavaScriptWithScope(String str);

    void writeJavaScriptWithScope(String str, String str2);

    void writeMaxKey();

    void writeMaxKey(String str);

    void writeMinKey();

    void writeMinKey(String str);

    void writeName(String str);

    void writeNull();

    void writeNull(String str);

    void writeObjectId(ObjectId objectId);

    void writeObjectId(String str, ObjectId objectId);

    void writeRegularExpression(BsonRegularExpression bsonRegularExpression);

    void writeRegularExpression(String str, BsonRegularExpression bsonRegularExpression);

    void writeStartArray();

    void writeStartArray(String str);

    void writeStartDocument();

    void writeStartDocument(String str);

    void writeString(String str);

    void writeString(String str, String str2);

    void writeSymbol(String str);

    void writeSymbol(String str, String str2);

    void writeTimestamp(BsonTimestamp bsonTimestamp);

    void writeTimestamp(String str, BsonTimestamp bsonTimestamp);

    void writeUndefined();

    void writeUndefined(String str);

    void pipe(BsonReader bsonReader);
}
