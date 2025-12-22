package com.mongodb.bulk;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/bulk/WriteRequest.class */
public abstract class WriteRequest {

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/bulk/WriteRequest$Type.class */
    public enum Type {
        INSERT,
        UPDATE,
        REPLACE,
        DELETE
    }

    public abstract Type getType();

    WriteRequest() {
    }
}
