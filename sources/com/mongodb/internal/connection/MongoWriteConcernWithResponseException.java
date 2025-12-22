package com.mongodb.internal.connection;

import com.mongodb.MongoException;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/MongoWriteConcernWithResponseException.class */
public class MongoWriteConcernWithResponseException extends MongoException {
    private static final long serialVersionUID = 1707360842648550287L;
    private final MongoException cause;
    private final Object response;

    public MongoWriteConcernWithResponseException(MongoException exception, Object response) {
        super(exception.getCode(), exception.getMessage(), exception);
        this.cause = exception;
        this.response = response;
    }

    @Override // java.lang.Throwable
    public MongoException getCause() {
        return this.cause;
    }

    public Object getResponse() {
        return this.response;
    }
}
