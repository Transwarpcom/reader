package org.bson.codecs.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.codecs.Codec;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/configuration/MapOfCodecsProvider.class */
final class MapOfCodecsProvider implements CodecProvider {
    private final Map<Class<?>, Codec<?>> codecsMap = new HashMap();

    MapOfCodecsProvider(List<? extends Codec<?>> codecsList) {
        for (Codec<?> codec : codecsList) {
            this.codecsMap.put(codec.getEncoderClass(), codec);
        }
    }

    @Override // org.bson.codecs.configuration.CodecProvider
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        return (Codec) this.codecsMap.get(clazz);
    }
}
