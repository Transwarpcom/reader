package org.bson.codecs;

import org.bson.BsonValue;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/CollectibleCodec.class */
public interface CollectibleCodec<T> extends Codec<T> {
    T generateIdIfAbsentFromDocument(T t);

    boolean documentHasId(T t);

    BsonValue getDocumentId(T t);
}
