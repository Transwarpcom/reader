package com.mongodb.internal.connection;

import com.mongodb.ReadConcernLevel;
import com.mongodb.assertions.Assertions;
import com.mongodb.session.SessionContext;
import org.bson.BsonDocument;
import org.bson.BsonString;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ReadConcernHelper.class */
public final class ReadConcernHelper {
    public static BsonDocument getReadConcernDocument(SessionContext sessionContext) {
        Assertions.notNull("sessionContext", sessionContext);
        BsonDocument readConcernDocument = new BsonDocument();
        ReadConcernLevel level = sessionContext.getReadConcern().getLevel();
        if (level != null) {
            readConcernDocument.append("level", new BsonString(level.getValue()));
        }
        if (shouldAddAfterClusterTime(sessionContext)) {
            readConcernDocument.append("afterClusterTime", sessionContext.getOperationTime());
        }
        return readConcernDocument;
    }

    private static boolean shouldAddAfterClusterTime(SessionContext sessionContext) {
        return sessionContext.isCausallyConsistent() && sessionContext.getOperationTime() != null;
    }

    private ReadConcernHelper() {
    }
}
