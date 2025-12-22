package com.mongodb.internal.connection;

import org.bson.BsonBinary;
import org.bson.BsonBinaryWriter;
import org.bson.BsonDbPointer;
import org.bson.BsonReader;
import org.bson.BsonRegularExpression;
import org.bson.BsonTimestamp;
import org.bson.BsonWriter;
import org.bson.assertions.Assertions;
import org.bson.types.Decimal128;
import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/LevelCountingBsonWriter.class */
abstract class LevelCountingBsonWriter implements BsonWriter {
    private final BsonBinaryWriter bsonBinaryWriter;
    private int level = -1;

    LevelCountingBsonWriter(BsonBinaryWriter bsonBinaryWriter) {
        this.bsonBinaryWriter = (BsonBinaryWriter) Assertions.notNull("bsonBinaryWriter", bsonBinaryWriter);
    }

    public int getCurrentLevel() {
        return this.level;
    }

    public BsonBinaryWriter getBsonBinaryWriter() {
        return this.bsonBinaryWriter;
    }

    @Override // org.bson.BsonWriter
    public void writeStartDocument(String name) {
        this.level++;
        this.bsonBinaryWriter.writeStartDocument(name);
    }

    @Override // org.bson.BsonWriter
    public void writeStartDocument() {
        this.level++;
        this.bsonBinaryWriter.writeStartDocument();
    }

    @Override // org.bson.BsonWriter
    public void writeEndDocument() {
        this.level--;
        this.bsonBinaryWriter.writeEndDocument();
    }

    @Override // org.bson.BsonWriter
    public void writeStartArray(String name) {
        this.bsonBinaryWriter.writeStartArray(name);
    }

    @Override // org.bson.BsonWriter
    public void writeStartArray() {
        this.bsonBinaryWriter.writeStartArray();
    }

    @Override // org.bson.BsonWriter
    public void writeEndArray() {
        this.bsonBinaryWriter.writeEndArray();
    }

    @Override // org.bson.BsonWriter
    public void writeBinaryData(String name, BsonBinary binary) {
        this.bsonBinaryWriter.writeBinaryData(name, binary);
    }

    @Override // org.bson.BsonWriter
    public void writeBinaryData(BsonBinary binary) {
        this.bsonBinaryWriter.writeBinaryData(binary);
    }

    @Override // org.bson.BsonWriter
    public void writeBoolean(String name, boolean value) {
        this.bsonBinaryWriter.writeBoolean(name, value);
    }

    @Override // org.bson.BsonWriter
    public void writeBoolean(boolean value) {
        this.bsonBinaryWriter.writeBoolean(value);
    }

    @Override // org.bson.BsonWriter
    public void writeDateTime(String name, long value) {
        this.bsonBinaryWriter.writeDateTime(name, value);
    }

    @Override // org.bson.BsonWriter
    public void writeDateTime(long value) {
        this.bsonBinaryWriter.writeDateTime(value);
    }

    @Override // org.bson.BsonWriter
    public void writeDBPointer(String name, BsonDbPointer value) {
        this.bsonBinaryWriter.writeDBPointer(name, value);
    }

    @Override // org.bson.BsonWriter
    public void writeDBPointer(BsonDbPointer value) {
        this.bsonBinaryWriter.writeDBPointer(value);
    }

    @Override // org.bson.BsonWriter
    public void writeDouble(String name, double value) {
        this.bsonBinaryWriter.writeDouble(name, value);
    }

    @Override // org.bson.BsonWriter
    public void writeDouble(double value) {
        this.bsonBinaryWriter.writeDouble(value);
    }

    @Override // org.bson.BsonWriter
    public void writeInt32(String name, int value) {
        this.bsonBinaryWriter.writeInt32(name, value);
    }

    @Override // org.bson.BsonWriter
    public void writeInt32(int value) {
        this.bsonBinaryWriter.writeInt32(value);
    }

    @Override // org.bson.BsonWriter
    public void writeInt64(String name, long value) {
        this.bsonBinaryWriter.writeInt64(name, value);
    }

    @Override // org.bson.BsonWriter
    public void writeInt64(long value) {
        this.bsonBinaryWriter.writeInt64(value);
    }

    @Override // org.bson.BsonWriter
    public void writeDecimal128(Decimal128 value) {
        this.bsonBinaryWriter.writeDecimal128(value);
    }

    @Override // org.bson.BsonWriter
    public void writeDecimal128(String name, Decimal128 value) {
        this.bsonBinaryWriter.writeDecimal128(name, value);
    }

    @Override // org.bson.BsonWriter
    public void writeJavaScript(String name, String code) {
        this.bsonBinaryWriter.writeJavaScript(name, code);
    }

    @Override // org.bson.BsonWriter
    public void writeJavaScript(String code) {
        this.bsonBinaryWriter.writeJavaScript(code);
    }

    @Override // org.bson.BsonWriter
    public void writeJavaScriptWithScope(String name, String code) {
        this.bsonBinaryWriter.writeJavaScriptWithScope(name, code);
    }

    @Override // org.bson.BsonWriter
    public void writeJavaScriptWithScope(String code) {
        this.bsonBinaryWriter.writeJavaScriptWithScope(code);
    }

    @Override // org.bson.BsonWriter
    public void writeMaxKey(String name) {
        this.bsonBinaryWriter.writeMaxKey(name);
    }

    @Override // org.bson.BsonWriter
    public void writeMaxKey() {
        this.bsonBinaryWriter.writeMaxKey();
    }

    @Override // org.bson.BsonWriter
    public void writeMinKey(String name) {
        this.bsonBinaryWriter.writeMinKey(name);
    }

    @Override // org.bson.BsonWriter
    public void writeMinKey() {
        this.bsonBinaryWriter.writeMinKey();
    }

    @Override // org.bson.BsonWriter
    public void writeName(String name) {
        this.bsonBinaryWriter.writeName(name);
    }

    @Override // org.bson.BsonWriter
    public void writeNull(String name) {
        this.bsonBinaryWriter.writeNull(name);
    }

    @Override // org.bson.BsonWriter
    public void writeNull() {
        this.bsonBinaryWriter.writeNull();
    }

    @Override // org.bson.BsonWriter
    public void writeObjectId(String name, ObjectId objectId) {
        this.bsonBinaryWriter.writeObjectId(name, objectId);
    }

    @Override // org.bson.BsonWriter
    public void writeObjectId(ObjectId objectId) {
        this.bsonBinaryWriter.writeObjectId(objectId);
    }

    @Override // org.bson.BsonWriter
    public void writeRegularExpression(String name, BsonRegularExpression regularExpression) {
        this.bsonBinaryWriter.writeRegularExpression(name, regularExpression);
    }

    @Override // org.bson.BsonWriter
    public void writeRegularExpression(BsonRegularExpression regularExpression) {
        this.bsonBinaryWriter.writeRegularExpression(regularExpression);
    }

    @Override // org.bson.BsonWriter
    public void writeString(String name, String value) {
        this.bsonBinaryWriter.writeString(name, value);
    }

    @Override // org.bson.BsonWriter
    public void writeString(String value) {
        this.bsonBinaryWriter.writeString(value);
    }

    @Override // org.bson.BsonWriter
    public void writeSymbol(String name, String value) {
        this.bsonBinaryWriter.writeSymbol(name, value);
    }

    @Override // org.bson.BsonWriter
    public void writeSymbol(String value) {
        this.bsonBinaryWriter.writeSymbol(value);
    }

    @Override // org.bson.BsonWriter
    public void writeTimestamp(String name, BsonTimestamp value) {
        this.bsonBinaryWriter.writeTimestamp(name, value);
    }

    @Override // org.bson.BsonWriter
    public void writeTimestamp(BsonTimestamp value) {
        this.bsonBinaryWriter.writeTimestamp(value);
    }

    @Override // org.bson.BsonWriter
    public void writeUndefined(String name) {
        this.bsonBinaryWriter.writeUndefined(name);
    }

    @Override // org.bson.BsonWriter
    public void writeUndefined() {
        this.bsonBinaryWriter.writeUndefined();
    }

    @Override // org.bson.BsonWriter
    public void pipe(BsonReader reader) {
        this.bsonBinaryWriter.pipe(reader);
    }

    @Override // org.bson.BsonWriter
    public void flush() {
        this.bsonBinaryWriter.flush();
    }
}
