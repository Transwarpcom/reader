package org.bson.codecs.pojo;

import org.bson.codecs.Codec;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/PojoCodec.class */
abstract class PojoCodec<T> implements Codec<T> {
    abstract ClassModel<T> getClassModel();

    PojoCodec() {
    }
}
