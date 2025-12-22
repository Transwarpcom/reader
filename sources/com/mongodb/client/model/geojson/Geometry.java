package com.mongodb.client.model.geojson;

import com.mongodb.client.model.geojson.codecs.GeoJsonCodecProvider;
import com.mongodb.lang.Nullable;
import java.io.StringWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.json.JsonWriter;
import org.bson.json.JsonWriterSettings;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/geojson/Geometry.class */
public abstract class Geometry {
    private static final CodecRegistry REGISTRY = CodecRegistries.fromProviders(new GeoJsonCodecProvider());
    private final CoordinateReferenceSystem coordinateReferenceSystem;

    public abstract GeoJsonObjectType getType();

    protected Geometry() {
        this(null);
    }

    protected Geometry(@Nullable CoordinateReferenceSystem coordinateReferenceSystem) {
        this.coordinateReferenceSystem = coordinateReferenceSystem;
    }

    public String toJson() {
        StringWriter stringWriter = new StringWriter();
        JsonWriter writer = new JsonWriter(stringWriter, new JsonWriterSettings());
        Codec codec = getRegistry().get(getClass());
        codec.encode(writer, this, EncoderContext.builder().build());
        return stringWriter.toString();
    }

    static CodecRegistry getRegistry() {
        return REGISTRY;
    }

    @Nullable
    public CoordinateReferenceSystem getCoordinateReferenceSystem() {
        return this.coordinateReferenceSystem;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Geometry geometry = (Geometry) o;
        if (this.coordinateReferenceSystem != null) {
            if (!this.coordinateReferenceSystem.equals(geometry.coordinateReferenceSystem)) {
                return false;
            }
            return true;
        }
        if (geometry.coordinateReferenceSystem != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        if (this.coordinateReferenceSystem != null) {
            return this.coordinateReferenceSystem.hashCode();
        }
        return 0;
    }
}
