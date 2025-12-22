package com.mongodb.internal.connection;

import com.mongodb.internal.connection.RequestMessage;
import org.bson.io.BsonOutput;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/GetMoreMessage.class */
class GetMoreMessage extends LegacyMessage {
    private final long cursorId;
    private final int numberToReturn;

    GetMoreMessage(String collectionName, long cursorId, int numberToReturn) {
        super(collectionName, OpCode.OP_GETMORE, MessageSettings.builder().build());
        this.cursorId = cursorId;
        this.numberToReturn = numberToReturn;
    }

    public long getCursorId() {
        return this.cursorId;
    }

    @Override // com.mongodb.internal.connection.LegacyMessage
    protected RequestMessage.EncodingMetadata encodeMessageBodyWithMetadata(BsonOutput bsonOutput) {
        writeGetMore(bsonOutput);
        return new RequestMessage.EncodingMetadata(bsonOutput.getPosition());
    }

    private void writeGetMore(BsonOutput buffer) {
        buffer.writeInt32(0);
        buffer.writeCString(getCollectionName());
        buffer.writeInt32(this.numberToReturn);
        buffer.writeInt64(this.cursorId);
    }
}
