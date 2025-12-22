package com.mongodb.internal.connection;

import com.mongodb.MongoInternalException;
import org.bson.ByteBuf;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/MessageHeader.class */
class MessageHeader {
    public static final int MESSAGE_HEADER_LENGTH = 16;
    private final int messageLength;
    private final int requestId;
    private final int responseTo;
    private final int opCode;

    MessageHeader(ByteBuf header, int maxMessageLength) {
        this.messageLength = header.getInt();
        this.requestId = header.getInt();
        this.responseTo = header.getInt();
        this.opCode = header.getInt();
        if (this.messageLength > maxMessageLength) {
            throw new MongoInternalException(String.format("The reply message length %d is less than the maximum message length %d", Integer.valueOf(this.messageLength), Integer.valueOf(maxMessageLength)));
        }
    }

    public int getMessageLength() {
        return this.messageLength;
    }

    public int getRequestId() {
        return this.requestId;
    }

    public int getResponseTo() {
        return this.responseTo;
    }

    public int getOpCode() {
        return this.opCode;
    }
}
