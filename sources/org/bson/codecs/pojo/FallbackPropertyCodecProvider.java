package org.bson.codecs.pojo;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/FallbackPropertyCodecProvider.class */
final class FallbackPropertyCodecProvider implements PropertyCodecProvider {
    private final CodecRegistry codecRegistry;
    private final PojoCodec<?> pojoCodec;

    FallbackPropertyCodecProvider(PojoCodec<?> pojoCodec, CodecRegistry codecRegistry) {
        this.pojoCodec = pojoCodec;
        this.codecRegistry = codecRegistry;
    }

    @Override // org.bson.codecs.pojo.PropertyCodecProvider
    public <S> Codec<S> get(TypeWithTypeParameters<S> type, PropertyCodecRegistry propertyCodecRegistry) {
        Class<S> clazz = type.getType();
        if (clazz == this.pojoCodec.getEncoderClass()) {
            return this.pojoCodec;
        }
        return this.codecRegistry.get(type.getType());
    }
}
