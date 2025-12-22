package com.mongodb;

import org.bson.BsonDocument;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/MongoNotPrimaryException.class */
public class MongoNotPrimaryException extends MongoCommandException {
    private static final long serialVersionUID = 694876345217027108L;

    public MongoNotPrimaryException(BsonDocument response, ServerAddress serverAddress) {
        super(response, serverAddress);
    }

    @Deprecated
    public MongoNotPrimaryException(ServerAddress serverAddress) {
        super(new BsonDocument(), serverAddress);
    }
}
