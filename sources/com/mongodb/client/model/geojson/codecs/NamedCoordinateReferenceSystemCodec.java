package com.mongodb.client.model.geojson.codecs;

import com.mongodb.client.model.geojson.CoordinateReferenceSystem;
import com.mongodb.client.model.geojson.NamedCoordinateReferenceSystem;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecConfigurationException;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/geojson/codecs/NamedCoordinateReferenceSystemCodec.class */
public class NamedCoordinateReferenceSystemCodec implements Codec<NamedCoordinateReferenceSystem> {
    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, NamedCoordinateReferenceSystem value, EncoderContext encoderContext) {
        writer.writeStartDocument();
        writer.writeString("type", value.getType().getTypeName());
        writer.writeStartDocument(PackageDocumentBase.OPFAttributes.properties);
        writer.writeString("name", value.getName());
        writer.writeEndDocument();
        writer.writeEndDocument();
    }

    @Override // org.bson.codecs.Encoder
    public Class<NamedCoordinateReferenceSystem> getEncoderClass() {
        return NamedCoordinateReferenceSystem.class;
    }

    @Override // org.bson.codecs.Decoder
    public NamedCoordinateReferenceSystem decode(BsonReader reader, DecoderContext decoderContext) {
        CoordinateReferenceSystem crs = GeometryDecoderHelper.decodeCoordinateReferenceSystem(reader);
        if (crs == null || !(crs instanceof NamedCoordinateReferenceSystem)) {
            throw new CodecConfigurationException("Invalid NamedCoordinateReferenceSystem.");
        }
        return (NamedCoordinateReferenceSystem) crs;
    }
}
