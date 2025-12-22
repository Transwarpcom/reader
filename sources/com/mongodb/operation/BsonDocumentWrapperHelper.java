package com.mongodb.operation;

import java.util.List;
import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/BsonDocumentWrapperHelper.class */
final class BsonDocumentWrapperHelper {
    static <T> List<T> toList(BsonDocument result, String fieldContainingWrappedArray) {
        return ((BsonArrayWrapper) result.getArray(fieldContainingWrappedArray)).getWrappedArray();
    }

    static <T> T toDocument(BsonDocument bsonDocument) {
        if (bsonDocument == null) {
            return null;
        }
        return (T) ((BsonDocumentWrapper) bsonDocument).getWrappedDocument();
    }

    private BsonDocumentWrapperHelper() {
    }
}
