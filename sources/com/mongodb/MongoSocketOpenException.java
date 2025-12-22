package com.mongodb;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/MongoSocketOpenException.class */
public class MongoSocketOpenException extends MongoSocketException {
    private static final long serialVersionUID = 4176754100200191238L;

    public MongoSocketOpenException(String message, ServerAddress address, Throwable cause) {
        super(message, address, cause);
    }
}
