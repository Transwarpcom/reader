package com.mongodb.session;

import com.mongodb.ReadConcern;
import org.bson.BsonDocument;
import org.bson.BsonTimestamp;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/session/SessionContext.class */
public interface SessionContext {
    boolean hasSession();

    boolean isImplicitSession();

    BsonDocument getSessionId();

    boolean isCausallyConsistent();

    long getTransactionNumber();

    long advanceTransactionNumber();

    boolean notifyMessageSent();

    BsonTimestamp getOperationTime();

    void advanceOperationTime(BsonTimestamp bsonTimestamp);

    BsonDocument getClusterTime();

    void advanceClusterTime(BsonDocument bsonDocument);

    boolean hasActiveTransaction();

    ReadConcern getReadConcern();
}
