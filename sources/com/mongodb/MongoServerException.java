package com.mongodb;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/MongoServerException.class */
public abstract class MongoServerException extends MongoException {
    private static final long serialVersionUID = -5213859742051776206L;
    private final ServerAddress serverAddress;

    public MongoServerException(String message, ServerAddress serverAddress) {
        super(message);
        this.serverAddress = serverAddress;
    }

    public MongoServerException(int code, String message, ServerAddress serverAddress) {
        super(code, message);
        this.serverAddress = serverAddress;
    }

    public ServerAddress getServerAddress() {
        return this.serverAddress;
    }
}
