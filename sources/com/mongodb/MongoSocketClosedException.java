package com.mongodb;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/MongoSocketClosedException.class */
public class MongoSocketClosedException extends MongoSocketException {
    private static final long serialVersionUID = -6855036625330867705L;

    public MongoSocketClosedException(String message, ServerAddress address) {
        super(message, address);
    }
}
