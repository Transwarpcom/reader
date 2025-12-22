package com.mongodb;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/MongoSocketReadException.class */
public class MongoSocketReadException extends MongoSocketException {
    private static final long serialVersionUID = -1142547119966956531L;

    public MongoSocketReadException(String message, ServerAddress address) {
        super(message, address);
    }

    public MongoSocketReadException(String message, ServerAddress address, Throwable cause) {
        super(message, address, cause);
    }
}
