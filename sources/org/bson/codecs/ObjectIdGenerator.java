package org.bson.codecs;

import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/ObjectIdGenerator.class */
public class ObjectIdGenerator implements IdGenerator {
    @Override // org.bson.codecs.IdGenerator
    public Object generate() {
        return new ObjectId();
    }
}
