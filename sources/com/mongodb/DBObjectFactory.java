package com.mongodb;

import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/DBObjectFactory.class */
interface DBObjectFactory {
    DBObject getInstance();

    DBObject getInstance(List<String> list);
}
