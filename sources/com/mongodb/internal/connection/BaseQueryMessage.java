package com.mongodb.internal.connection;

import org.bson.io.BsonOutput;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/BaseQueryMessage.class */
abstract class BaseQueryMessage extends LegacyMessage {
    private final int skip;
    private final int numberToReturn;
    private boolean tailableCursor;
    private boolean slaveOk;
    private boolean oplogReplay;
    private boolean noCursorTimeout;
    private boolean awaitData;
    private boolean partial;

    BaseQueryMessage(String collectionName, int skip, int numberToReturn, MessageSettings settings) {
        super(collectionName, OpCode.OP_QUERY, settings);
        this.skip = skip;
        this.numberToReturn = numberToReturn;
    }

    public boolean isTailableCursor() {
        return this.tailableCursor;
    }

    public BaseQueryMessage tailableCursor(boolean tailableCursor) {
        this.tailableCursor = tailableCursor;
        return this;
    }

    public boolean isSlaveOk() {
        return this.slaveOk;
    }

    public BaseQueryMessage slaveOk(boolean slaveOk) {
        this.slaveOk = slaveOk;
        return this;
    }

    public boolean isOplogReplay() {
        return this.oplogReplay;
    }

    public BaseQueryMessage oplogReplay(boolean oplogReplay) {
        this.oplogReplay = oplogReplay;
        return this;
    }

    public boolean isNoCursorTimeout() {
        return this.noCursorTimeout;
    }

    public BaseQueryMessage noCursorTimeout(boolean noCursorTimeout) {
        this.noCursorTimeout = noCursorTimeout;
        return this;
    }

    public boolean isAwaitData() {
        return this.awaitData;
    }

    public BaseQueryMessage awaitData(boolean awaitData) {
        this.awaitData = awaitData;
        return this;
    }

    public boolean isPartial() {
        return this.partial;
    }

    public BaseQueryMessage partial(boolean partial) {
        this.partial = partial;
        return this;
    }

    private int getCursorFlag() {
        int cursorFlag = 0;
        if (isTailableCursor()) {
            cursorFlag = 0 | 2;
        }
        if (isSlaveOk()) {
            cursorFlag |= 4;
        }
        if (isOplogReplay()) {
            cursorFlag |= 8;
        }
        if (isNoCursorTimeout()) {
            cursorFlag |= 16;
        }
        if (isAwaitData()) {
            cursorFlag |= 32;
        }
        if (isPartial()) {
            cursorFlag |= 128;
        }
        return cursorFlag;
    }

    protected void writeQueryPrologue(BsonOutput bsonOutput) {
        bsonOutput.writeInt32(getCursorFlag());
        bsonOutput.writeCString(getCollectionName());
        bsonOutput.writeInt32(this.skip);
        bsonOutput.writeInt32(this.numberToReturn);
    }
}
