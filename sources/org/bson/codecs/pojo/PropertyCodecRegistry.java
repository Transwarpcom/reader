package org.bson.codecs.pojo;

import org.bson.codecs.Codec;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/PropertyCodecRegistry.class */
public interface PropertyCodecRegistry {
    <T> Codec<T> get(TypeWithTypeParameters<T> typeWithTypeParameters);
}
