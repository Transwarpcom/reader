package com.mongodb.internal.connection;

import com.mongodb.internal.connection.RequestMessage;
import com.mongodb.session.SessionContext;
import org.bson.io.BsonOutput;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/LegacyMessage.class */
abstract class LegacyMessage extends RequestMessage {
    abstract RequestMessage.EncodingMetadata encodeMessageBodyWithMetadata(BsonOutput bsonOutput);

    LegacyMessage(String collectionName, OpCode opCode, MessageSettings settings) {
        super(collectionName, opCode, settings);
    }

    LegacyMessage(OpCode opCode, MessageSettings settings) {
        super(opCode, settings);
    }

    @Override // com.mongodb.internal.connection.RequestMessage
    protected RequestMessage.EncodingMetadata encodeMessageBodyWithMetadata(BsonOutput bsonOutput, SessionContext sessionContext) {
        return encodeMessageBodyWithMetadata(bsonOutput);
    }
}
