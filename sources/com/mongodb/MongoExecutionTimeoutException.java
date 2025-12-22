package com.mongodb;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/MongoExecutionTimeoutException.class */
public class MongoExecutionTimeoutException extends MongoException {
    private static final long serialVersionUID = 5955669123800274594L;

    public MongoExecutionTimeoutException(int code, String message) {
        super(code, message);
    }
}
