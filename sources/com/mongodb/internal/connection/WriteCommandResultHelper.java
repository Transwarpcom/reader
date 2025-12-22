package com.mongodb.internal.connection;

import com.mongodb.MongoBulkWriteException;
import com.mongodb.MongoInternalException;
import com.mongodb.ServerAddress;
import com.mongodb.bulk.BulkWriteError;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.bulk.BulkWriteUpsert;
import com.mongodb.bulk.WriteConcernError;
import com.mongodb.bulk.WriteRequest;
import com.mongodb.internal.operation.WriteConcernHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonNumber;
import org.bson.BsonValue;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/WriteCommandResultHelper.class */
final class WriteCommandResultHelper {
    static boolean hasError(BsonDocument result) {
        return (result.get("writeErrors") == null && result.get("writeConcernError") == null) ? false : true;
    }

    static BulkWriteResult getBulkWriteResult(WriteRequest.Type type, BsonDocument result) {
        int count = getCount(result);
        List<BulkWriteUpsert> upsertedItems = getUpsertedItems(result);
        return BulkWriteResult.acknowledged(type, count - upsertedItems.size(), getModifiedCount(type, result), upsertedItems);
    }

    static MongoBulkWriteException getBulkWriteException(WriteRequest.Type type, BsonDocument result, ServerAddress serverAddress) {
        if (!hasError(result)) {
            throw new MongoInternalException("This method should not have been called");
        }
        return new MongoBulkWriteException(getBulkWriteResult(type, result), getWriteErrors(result), getWriteConcernError(result), serverAddress);
    }

    private static List<BulkWriteError> getWriteErrors(BsonDocument result) {
        List<BulkWriteError> writeErrors = new ArrayList<>();
        BsonArray writeErrorsDocuments = (BsonArray) result.get("writeErrors");
        if (writeErrorsDocuments != null) {
            Iterator<BsonValue> it = writeErrorsDocuments.iterator();
            while (it.hasNext()) {
                BsonValue cur = it.next();
                BsonDocument curDocument = (BsonDocument) cur;
                writeErrors.add(new BulkWriteError(curDocument.getNumber("code").intValue(), curDocument.getString("errmsg").getValue(), curDocument.getDocument("errInfo", new BsonDocument()), curDocument.getNumber("index").intValue()));
            }
        }
        return writeErrors;
    }

    private static WriteConcernError getWriteConcernError(BsonDocument result) {
        BsonDocument writeConcernErrorDocument = (BsonDocument) result.get("writeConcernError");
        if (writeConcernErrorDocument == null) {
            return null;
        }
        return WriteConcernHelper.createWriteConcernError(writeConcernErrorDocument);
    }

    private static List<BulkWriteUpsert> getUpsertedItems(BsonDocument result) {
        BsonValue upsertedValue = result.get("upserted");
        if (upsertedValue == null) {
            return Collections.emptyList();
        }
        List<BulkWriteUpsert> bulkWriteUpsertList = new ArrayList<>();
        Iterator<BsonValue> it = ((BsonArray) upsertedValue).iterator();
        while (it.hasNext()) {
            BsonValue upsertedItem = it.next();
            BsonDocument upsertedItemDocument = (BsonDocument) upsertedItem;
            bulkWriteUpsertList.add(new BulkWriteUpsert(upsertedItemDocument.getNumber("index").intValue(), upsertedItemDocument.get((Object) "_id")));
        }
        return bulkWriteUpsertList;
    }

    private static int getCount(BsonDocument result) {
        return result.getNumber(OperatorName.ENDPATH).intValue();
    }

    private static Integer getModifiedCount(WriteRequest.Type type, BsonDocument result) {
        BsonNumber modifiedCount = result.getNumber("nModified", (type == WriteRequest.Type.UPDATE || type == WriteRequest.Type.REPLACE) ? null : new BsonInt32(0));
        if (modifiedCount == null) {
            return null;
        }
        return Integer.valueOf(modifiedCount.intValue());
    }

    private WriteCommandResultHelper() {
    }
}
