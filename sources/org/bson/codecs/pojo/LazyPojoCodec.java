package org.bson.codecs.pojo;

import java.util.concurrent.ConcurrentMap;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/LazyPojoCodec.class */
class LazyPojoCodec<T> extends PojoCodec<T> {
    private final ClassModel<T> classModel;
    private final CodecRegistry registry;
    private final PropertyCodecRegistry propertyCodecRegistry;
    private final DiscriminatorLookup discriminatorLookup;
    private final ConcurrentMap<ClassModel<?>, Codec<?>> codecCache;
    private volatile PojoCodecImpl<T> pojoCodec;

    LazyPojoCodec(ClassModel<T> classModel, CodecRegistry registry, PropertyCodecRegistry propertyCodecRegistry, DiscriminatorLookup discriminatorLookup, ConcurrentMap<ClassModel<?>, Codec<?>> codecCache) {
        this.classModel = classModel;
        this.registry = registry;
        this.propertyCodecRegistry = propertyCodecRegistry;
        this.discriminatorLookup = discriminatorLookup;
        this.codecCache = codecCache;
    }

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, T value, EncoderContext encoderContext) {
        getPojoCodec().encode(writer, value, encoderContext);
    }

    @Override // org.bson.codecs.Encoder
    public Class<T> getEncoderClass() {
        return this.classModel.getType();
    }

    @Override // org.bson.codecs.Decoder
    public T decode(BsonReader reader, DecoderContext decoderContext) {
        return getPojoCodec().decode(reader, decoderContext);
    }

    private Codec<T> getPojoCodec() {
        if (this.pojoCodec == null) {
            this.pojoCodec = new PojoCodecImpl<>(this.classModel, this.registry, this.propertyCodecRegistry, this.discriminatorLookup, this.codecCache, true);
        }
        return this.pojoCodec;
    }

    @Override // org.bson.codecs.pojo.PojoCodec
    ClassModel<T> getClassModel() {
        return this.classModel;
    }
}
