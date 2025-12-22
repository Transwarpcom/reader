package org.bson.codecs.configuration;

import org.bson.codecs.Codec;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/configuration/CodecRegistry.class */
public interface CodecRegistry {
    <T> Codec<T> get(Class<T> cls);
}
