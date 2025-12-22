package com.mongodb.client.model.geojson.codecs;

import com.mongodb.client.model.geojson.Polygon;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/geojson/codecs/PolygonCodec.class */
public class PolygonCodec extends AbstractGeometryCodec<Polygon> {
    @Override // com.mongodb.client.model.geojson.codecs.AbstractGeometryCodec, org.bson.codecs.Encoder
    public /* bridge */ /* synthetic */ Class getEncoderClass() {
        return super.getEncoderClass();
    }

    public PolygonCodec(CodecRegistry registry) {
        super(registry, Polygon.class);
    }
}
