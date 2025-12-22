package com.mongodb;

import org.bson.BSONObject;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/DBObject.class */
public interface DBObject extends BSONObject {
    void markAsPartialObject();

    boolean isPartialObject();
}
