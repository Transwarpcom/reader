package com.mongodb;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/MongoInterruptedException.class */
public class MongoInterruptedException extends MongoException {
    private static final long serialVersionUID = -4110417867718417860L;

    public MongoInterruptedException(String message, Exception e) {
        super(message, e);
    }
}
