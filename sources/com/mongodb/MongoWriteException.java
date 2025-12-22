package com.mongodb;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/MongoWriteException.class */
public class MongoWriteException extends MongoServerException {
    private static final long serialVersionUID = -1906795074458258147L;
    private final WriteError error;

    public MongoWriteException(WriteError error, ServerAddress serverAddress) {
        super(error.getCode(), error.getMessage(), serverAddress);
        this.error = error;
    }

    public WriteError getError() {
        return this.error;
    }
}
