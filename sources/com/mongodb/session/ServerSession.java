package com.mongodb.session;

import org.bson.BsonDocument;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/session/ServerSession.class */
public interface ServerSession {
    BsonDocument getIdentifier();

    long getTransactionNumber();

    long advanceTransactionNumber();

    boolean isClosed();
}
