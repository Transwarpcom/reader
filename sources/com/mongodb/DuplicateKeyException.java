package com.mongodb;

import org.bson.BsonDocument;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/DuplicateKeyException.class */
public class DuplicateKeyException extends WriteConcernException {
    private static final long serialVersionUID = -4415279469780082174L;

    public DuplicateKeyException(BsonDocument response, ServerAddress address, WriteConcernResult writeConcernResult) {
        super(response, address, writeConcernResult);
    }
}
