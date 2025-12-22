package com.mongodb.client.model.geojson.codecs;

import com.mongodb.client.model.geojson.MultiPolygon;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/geojson/codecs/MultiPolygonCodec.class */
public class MultiPolygonCodec extends AbstractGeometryCodec<MultiPolygon> {
    @Override // com.mongodb.client.model.geojson.codecs.AbstractGeometryCodec, org.bson.codecs.Encoder
    public /* bridge */ /* synthetic */ Class getEncoderClass() {
        return super.getEncoderClass();
    }

    public MultiPolygonCodec(CodecRegistry registry) {
        super(registry, MultiPolygon.class);
    }
}
