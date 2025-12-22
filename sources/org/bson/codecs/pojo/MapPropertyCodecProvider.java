package org.bson.codecs.pojo;

import java.util.HashMap;
import java.util.Map;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecConfigurationException;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/MapPropertyCodecProvider.class */
final class MapPropertyCodecProvider implements PropertyCodecProvider {
    MapPropertyCodecProvider() {
    }

    @Override // org.bson.codecs.pojo.PropertyCodecProvider
    public <T> Codec<T> get(TypeWithTypeParameters<T> type, PropertyCodecRegistry registry) {
        if (Map.class.isAssignableFrom(type.getType()) && type.getTypeParameters().size() == 2) {
            Class<?> keyType = type.getTypeParameters().get(0).getType();
            if (!keyType.equals(String.class)) {
                throw new CodecConfigurationException(String.format("Invalid Map type. Maps MUST have string keys, found %s instead.", keyType));
            }
            try {
                return new MapCodec(type.getType(), registry.get((TypeWithTypeParameters) type.getTypeParameters().get(1)));
            } catch (CodecConfigurationException e) {
                if (type.getTypeParameters().get(1).getType() == Object.class) {
                    try {
                        return registry.get(TypeData.builder(Map.class).build());
                    } catch (CodecConfigurationException e2) {
                        throw e;
                    }
                }
                throw e;
            }
        }
        return null;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/MapPropertyCodecProvider$MapCodec.class */
    private static class MapCodec<T> implements Codec<Map<String, T>> {
        private final Class<Map<String, T>> encoderClass;
        private final Codec<T> codec;

        MapCodec(Class<Map<String, T>> encoderClass, Codec<T> codec) {
            this.encoderClass = encoderClass;
            this.codec = codec;
        }

        @Override // org.bson.codecs.Encoder
        public void encode(BsonWriter writer, Map<String, T> map, EncoderContext encoderContext) {
            writer.writeStartDocument();
            for (Map.Entry<String, T> entry : map.entrySet()) {
                writer.writeName(entry.getKey());
                if (entry.getValue() == null) {
                    writer.writeNull();
                } else {
                    this.codec.encode(writer, entry.getValue(), encoderContext);
                }
            }
            writer.writeEndDocument();
        }

        @Override // org.bson.codecs.Decoder
        public Map<String, T> decode(BsonReader reader, DecoderContext context) {
            reader.readStartDocument();
            Map<String, T> map = getInstance();
            while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
                if (reader.getCurrentBsonType() == BsonType.NULL) {
                    map.put(reader.readName(), null);
                    reader.readNull();
                } else {
                    map.put(reader.readName(), this.codec.decode(reader, context));
                }
            }
            reader.readEndDocument();
            return map;
        }

        @Override // org.bson.codecs.Encoder
        public Class<Map<String, T>> getEncoderClass() {
            return this.encoderClass;
        }

        private Map<String, T> getInstance() {
            if (this.encoderClass.isInterface()) {
                return new HashMap();
            }
            try {
                return this.encoderClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Exception e) {
                throw new CodecConfigurationException(e.getMessage(), e);
            }
        }
    }
}
