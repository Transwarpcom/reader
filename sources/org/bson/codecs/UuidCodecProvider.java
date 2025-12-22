package org.bson.codecs;

import java.util.UUID;
import org.bson.UuidRepresentation;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/UuidCodecProvider.class */
public class UuidCodecProvider implements CodecProvider {
    private UuidRepresentation uuidRepresentation;

    public UuidCodecProvider(UuidRepresentation uuidRepresentation) {
        this.uuidRepresentation = uuidRepresentation;
    }

    @Override // org.bson.codecs.configuration.CodecProvider
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        if (clazz == UUID.class) {
            return new UuidCodec(this.uuidRepresentation);
        }
        return null;
    }
}
