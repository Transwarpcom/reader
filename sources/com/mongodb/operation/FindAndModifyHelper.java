package com.mongodb.operation;

import com.mongodb.MongoWriteConcernException;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcernResult;
import com.mongodb.internal.operation.WriteConcernHelper;
import com.mongodb.operation.CommandOperationHelper;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonInt32;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/FindAndModifyHelper.class */
final class FindAndModifyHelper {
    static <T> CommandOperationHelper.CommandTransformer<BsonDocument, T> transformer() {
        return new CommandOperationHelper.CommandTransformer<BsonDocument, T>() { // from class: com.mongodb.operation.FindAndModifyHelper.1
            @Override // com.mongodb.operation.CommandOperationHelper.CommandTransformer
            public T apply(BsonDocument bsonDocument, ServerAddress serverAddress) {
                if (WriteConcernHelper.hasWriteConcernError(bsonDocument)) {
                    throw new MongoWriteConcernException(WriteConcernHelper.createWriteConcernError(bsonDocument.getDocument("writeConcernError")), FindAndModifyHelper.createWriteConcernResult(bsonDocument.getDocument("lastErrorObject", new BsonDocument())), serverAddress);
                }
                if (!bsonDocument.isDocument("value")) {
                    return null;
                }
                return (T) BsonDocumentWrapperHelper.toDocument(bsonDocument.getDocument("value", null));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static WriteConcernResult createWriteConcernResult(BsonDocument result) {
        BsonBoolean updatedExisting = result.getBoolean("updatedExisting", BsonBoolean.FALSE);
        return WriteConcernResult.acknowledged(result.getNumber(OperatorName.ENDPATH, new BsonInt32(0)).intValue(), updatedExisting.getValue(), result.get("upserted"));
    }

    private FindAndModifyHelper() {
    }
}
