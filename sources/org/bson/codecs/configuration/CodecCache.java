package org.bson.codecs.configuration;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.bson.codecs.Codec;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/configuration/CodecCache.class */
final class CodecCache {
    private final ConcurrentMap<Class<?>, Optional<? extends Codec<?>>> codecCache = new ConcurrentHashMap();

    CodecCache() {
    }

    public boolean containsKey(Class<?> clazz) {
        return this.codecCache.containsKey(clazz);
    }

    public void put(Class<?> clazz, Codec<?> codec) {
        this.codecCache.put(clazz, Optional.of(codec));
    }

    public <T> Codec<T> getOrThrow(Class<T> clazz) {
        if (this.codecCache.containsKey(clazz)) {
            Optional<? extends Codec<?>> optionalCodec = this.codecCache.get(clazz);
            if (!optionalCodec.isEmpty()) {
                return (Codec) optionalCodec.get();
            }
        }
        throw new CodecConfigurationException(String.format("Can't find a codec for %s.", clazz));
    }
}
