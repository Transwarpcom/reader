package com.mongodb.client.model.geojson.codecs;

import com.mongodb.client.model.geojson.MultiLineString;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/geojson/codecs/MultiLineStringCodec.class */
public class MultiLineStringCodec extends AbstractGeometryCodec<MultiLineString> {
    @Override // com.mongodb.client.model.geojson.codecs.AbstractGeometryCodec, org.bson.codecs.Encoder
    public /* bridge */ /* synthetic */ Class getEncoderClass() {
        return super.getEncoderClass();
    }

    public MultiLineStringCodec(CodecRegistry registry) {
        super(registry, MultiLineString.class);
    }
}
