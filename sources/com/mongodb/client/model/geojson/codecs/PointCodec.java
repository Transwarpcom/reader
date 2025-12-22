package com.mongodb.client.model.geojson.codecs;

import com.mongodb.client.model.geojson.Point;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/geojson/codecs/PointCodec.class */
public class PointCodec extends AbstractGeometryCodec<Point> {
    @Override // com.mongodb.client.model.geojson.codecs.AbstractGeometryCodec, org.bson.codecs.Encoder
    public /* bridge */ /* synthetic */ Class getEncoderClass() {
        return super.getEncoderClass();
    }

    public PointCodec(CodecRegistry registry) {
        super(registry, Point.class);
    }
}
