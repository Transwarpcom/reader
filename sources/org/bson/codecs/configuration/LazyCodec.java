package org.bson.codecs.configuration;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/configuration/LazyCodec.class */
class LazyCodec<T> implements Codec<T> {
    private final CodecRegistry registry;
    private final Class<T> clazz;
    private volatile Codec<T> wrapped;

    LazyCodec(CodecRegistry registry, Class<T> clazz) {
        this.registry = registry;
        this.clazz = clazz;
    }

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, T value, EncoderContext encoderContext) {
        getWrapped().encode(writer, value, encoderContext);
    }

    @Override // org.bson.codecs.Encoder
    public Class<T> getEncoderClass() {
        return this.clazz;
    }

    @Override // org.bson.codecs.Decoder
    public T decode(BsonReader reader, DecoderContext decoderContext) {
        return getWrapped().decode(reader, decoderContext);
    }

    private Codec<T> getWrapped() {
        if (this.wrapped == null) {
            this.wrapped = this.registry.get(this.clazz);
        }
        return this.wrapped;
    }
}
