package com.mongodb;

import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/BasicDBObjectFactory.class */
class BasicDBObjectFactory implements DBObjectFactory {
    BasicDBObjectFactory() {
    }

    @Override // com.mongodb.DBObjectFactory
    public DBObject getInstance() {
        return new BasicDBObject();
    }

    @Override // com.mongodb.DBObjectFactory
    public DBObject getInstance(List<String> path) {
        return new BasicDBObject();
    }
}
