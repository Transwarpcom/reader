package com.mongodb.client.model.geojson.codecs;

import com.mongodb.client.model.geojson.LineString;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/geojson/codecs/LineStringCodec.class */
public class LineStringCodec extends AbstractGeometryCodec<LineString> {
    @Override // com.mongodb.client.model.geojson.codecs.AbstractGeometryCodec, org.bson.codecs.Encoder
    public /* bridge */ /* synthetic */ Class getEncoderClass() {
        return super.getEncoderClass();
    }

    public LineStringCodec(CodecRegistry registry) {
        super(registry, LineString.class);
    }
}
