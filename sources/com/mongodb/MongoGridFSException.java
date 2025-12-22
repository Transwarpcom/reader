package com.mongodb;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/MongoGridFSException.class */
public class MongoGridFSException extends MongoException {
    private static final long serialVersionUID = -3894346172927543978L;

    public MongoGridFSException(String message) {
        super(message);
    }

    public MongoGridFSException(String message, Throwable t) {
        super(message, t);
    }
}
