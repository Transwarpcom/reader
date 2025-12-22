package com.mongodb.operation;

import org.bson.BsonDocument;
import org.bson.BsonInt32;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/CursorHelper.class */
final class CursorHelper {
    static int getNumberToReturn(int limit, int batchSize, int numReturnedSoFar) {
        int numberToReturn;
        if (Math.abs(limit) != 0) {
            numberToReturn = Math.abs(limit) - numReturnedSoFar;
            if (batchSize != 0 && numberToReturn > Math.abs(batchSize)) {
                numberToReturn = batchSize;
            }
        } else {
            numberToReturn = batchSize;
        }
        return numberToReturn;
    }

    static BsonDocument getCursorDocumentFromBatchSize(Integer batchSize) {
        return batchSize == null ? new BsonDocument() : new BsonDocument("batchSize", new BsonInt32(batchSize.intValue()));
    }

    private CursorHelper() {
    }
}
