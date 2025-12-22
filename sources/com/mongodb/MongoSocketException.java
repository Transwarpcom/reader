package com.mongodb;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/MongoSocketException.class */
public class MongoSocketException extends MongoException {
    private static final long serialVersionUID = -4415279469780082174L;
    private final ServerAddress serverAddress;

    MongoSocketException(String msg, ServerAddress serverAddress, Throwable e) {
        super(-2, msg, e);
        this.serverAddress = serverAddress;
    }

    public MongoSocketException(String message, ServerAddress serverAddress) {
        super(-2, message);
        this.serverAddress = serverAddress;
    }

    public ServerAddress getServerAddress() {
        return this.serverAddress;
    }
}
