package com.mongodb.client.model.geojson.codecs;

import com.mongodb.client.model.geojson.Geometry;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/geojson/codecs/AbstractGeometryCodec.class */
abstract class AbstractGeometryCodec<T extends Geometry> implements Codec<T> {
    private final CodecRegistry registry;
    private final Class<T> encoderClass;

    AbstractGeometryCodec(CodecRegistry registry, Class<T> encoderClass) {
        this.registry = registry;
        this.encoderClass = encoderClass;
    }

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, T value, EncoderContext encoderContext) {
        GeometryEncoderHelper.encodeGeometry(writer, value, encoderContext, this.registry);
    }

    @Override // org.bson.codecs.Decoder
    public T decode(BsonReader bsonReader, DecoderContext decoderContext) {
        return (T) GeometryDecoderHelper.decodeGeometry(bsonReader, getEncoderClass());
    }

    @Override // org.bson.codecs.Encoder
    public Class<T> getEncoderClass() {
        return this.encoderClass;
    }
}
