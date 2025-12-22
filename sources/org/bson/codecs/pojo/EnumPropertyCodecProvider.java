package org.bson.codecs.pojo;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecConfigurationException;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/EnumPropertyCodecProvider.class */
final class EnumPropertyCodecProvider implements PropertyCodecProvider {
    private final CodecRegistry codecRegistry;

    EnumPropertyCodecProvider(CodecRegistry codecRegistry) {
        this.codecRegistry = codecRegistry;
    }

    @Override // org.bson.codecs.pojo.PropertyCodecProvider
    public <T> Codec<T> get(TypeWithTypeParameters<T> type, PropertyCodecRegistry propertyCodecRegistry) {
        Class<T> clazz = type.getType();
        if (Enum.class.isAssignableFrom(clazz)) {
            try {
                return this.codecRegistry.get(clazz);
            } catch (CodecConfigurationException e) {
                return new EnumCodec(clazz);
            }
        }
        return null;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/EnumPropertyCodecProvider$EnumCodec.class */
    private static class EnumCodec<T extends Enum<T>> implements Codec<T> {
        private final Class<T> clazz;

        EnumCodec(Class<T> clazz) {
            this.clazz = clazz;
        }

        @Override // org.bson.codecs.Encoder
        public void encode(BsonWriter writer, T value, EncoderContext encoderContext) {
            writer.writeString(value.name());
        }

        @Override // org.bson.codecs.Encoder
        public Class<T> getEncoderClass() {
            return this.clazz;
        }

        @Override // org.bson.codecs.Decoder
        public T decode(BsonReader bsonReader, DecoderContext decoderContext) {
            return (T) Enum.valueOf(this.clazz, bsonReader.readString());
        }
    }
}
