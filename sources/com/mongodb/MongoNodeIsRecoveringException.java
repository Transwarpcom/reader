package com.mongodb;

import org.bson.BsonDocument;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/MongoNodeIsRecoveringException.class */
public class MongoNodeIsRecoveringException extends MongoCommandException {
    private static final long serialVersionUID = 6062524147327071635L;

    public MongoNodeIsRecoveringException(BsonDocument response, ServerAddress serverAddress) {
        super(response, serverAddress);
    }

    @Deprecated
    public MongoNodeIsRecoveringException(ServerAddress serverAddress) {
        super(new BsonDocument(), serverAddress);
    }
}
