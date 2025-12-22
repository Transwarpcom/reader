package com.mongodb.operation;

import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonInt64;
import org.bson.BsonValue;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/DocumentHelper.class */
final class DocumentHelper {
    private DocumentHelper() {
    }

    static void putIfTrue(BsonDocument command, String key, boolean condition) {
        if (condition) {
            command.put(key, (BsonValue) BsonBoolean.TRUE);
        }
    }

    static void putIfFalse(BsonDocument command, String key, boolean condition) {
        if (!condition) {
            command.put(key, (BsonValue) BsonBoolean.FALSE);
        }
    }

    static void putIfNotNullOrEmpty(BsonDocument command, String key, BsonDocument documentValue) {
        if (documentValue != null && !documentValue.isEmpty()) {
            command.put(key, (BsonValue) documentValue);
        }
    }

    static void putIfNotNull(BsonDocument command, String key, BsonValue value) {
        if (value != null) {
            command.put(key, value);
        }
    }

    static void putIfNotZero(BsonDocument command, String key, int value) {
        if (value != 0) {
            command.put(key, (BsonValue) new BsonInt32(value));
        }
    }

    static void putIfNotZero(BsonDocument command, String key, long value) {
        if (value != 0) {
            command.put(key, (BsonValue) new BsonInt64(value));
        }
    }
}
