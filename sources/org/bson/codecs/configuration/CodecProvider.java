package org.bson.codecs.configuration;

import org.bson.codecs.Codec;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/configuration/CodecProvider.class */
public interface CodecProvider {
    <T> Codec<T> get(Class<T> cls, CodecRegistry codecRegistry);
}
