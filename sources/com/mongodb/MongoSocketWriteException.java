package com.mongodb;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/MongoSocketWriteException.class */
public class MongoSocketWriteException extends MongoSocketException {
    private static final long serialVersionUID = 5088061954415484493L;

    public MongoSocketWriteException(String message, ServerAddress address, Throwable cause) {
        super(message, address, cause);
    }
}
