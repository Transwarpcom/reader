package com.mongodb;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/MongoSecurityException.class */
public class MongoSecurityException extends MongoClientException {
    private static final long serialVersionUID = -7044790409935567275L;
    private final MongoCredential credential;

    public MongoSecurityException(MongoCredential credential, String message, Throwable cause) {
        super(message, cause);
        this.credential = credential;
    }

    public MongoSecurityException(MongoCredential credential, String message) {
        super(message);
        this.credential = credential;
    }

    public MongoCredential getCredential() {
        return this.credential;
    }
}
