package com.mongodb.client.model.geojson.codecs;

import com.mongodb.client.model.geojson.MultiPoint;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/geojson/codecs/MultiPointCodec.class */
public class MultiPointCodec extends AbstractGeometryCodec<MultiPoint> {
    @Override // com.mongodb.client.model.geojson.codecs.AbstractGeometryCodec, org.bson.codecs.Encoder
    public /* bridge */ /* synthetic */ Class getEncoderClass() {
        return super.getEncoderClass();
    }

    public MultiPointCodec(CodecRegistry registry) {
        super(registry, MultiPoint.class);
    }
}
