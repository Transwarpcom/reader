package com.mongodb.operation;

import com.mongodb.assertions.Assertions;
import com.mongodb.internal.connection.ReadConcernHelper;
import com.mongodb.session.SessionContext;
import org.bson.BsonDocument;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/OperationReadConcernHelper.class */
final class OperationReadConcernHelper {
    static void appendReadConcernToCommand(SessionContext sessionContext, BsonDocument commandDocument) {
        Assertions.notNull("commandDocument", commandDocument);
        Assertions.notNull("sessionContext", sessionContext);
        if (sessionContext.hasActiveTransaction()) {
            return;
        }
        BsonDocument readConcernDocument = ReadConcernHelper.getReadConcernDocument(sessionContext);
        if (!readConcernDocument.isEmpty()) {
            commandDocument.append("readConcern", readConcernDocument);
        }
    }

    private OperationReadConcernHelper() {
    }
}
