package com.mongodb.client.model.geojson.codecs;

import com.mongodb.client.model.geojson.GeometryCollection;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/geojson/codecs/GeometryCollectionCodec.class */
public class GeometryCollectionCodec extends AbstractGeometryCodec<GeometryCollection> {
    @Override // com.mongodb.client.model.geojson.codecs.AbstractGeometryCodec, org.bson.codecs.Encoder
    public /* bridge */ /* synthetic */ Class getEncoderClass() {
        return super.getEncoderClass();
    }

    public GeometryCollectionCodec(CodecRegistry registry) {
        super(registry, GeometryCollection.class);
    }
}
