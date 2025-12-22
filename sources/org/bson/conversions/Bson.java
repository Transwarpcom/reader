package org.bson.conversions;

import org.bson.BsonDocument;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/conversions/Bson.class */
public interface Bson {
    <TDocument> BsonDocument toBsonDocument(Class<TDocument> cls, CodecRegistry codecRegistry);
}
