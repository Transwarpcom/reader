package com.mongodb.client.model.geojson;

import com.mongodb.assertions.Assertions;
import com.mongodb.lang.Nullable;
import java.util.Collections;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/geojson/GeometryCollection.class */
public final class GeometryCollection extends Geometry {
    private final List<? extends Geometry> geometries;

    public GeometryCollection(List<? extends Geometry> geometries) {
        this(null, geometries);
    }

    public GeometryCollection(@Nullable CoordinateReferenceSystem coordinateReferenceSystem, List<? extends Geometry> geometries) {
        super(coordinateReferenceSystem);
        Assertions.notNull("geometries", geometries);
        Assertions.isTrueArgument("geometries contains only non-null elements", !geometries.contains(null));
        this.geometries = Collections.unmodifiableList(geometries);
    }

    @Override // com.mongodb.client.model.geojson.Geometry
    public GeoJsonObjectType getType() {
        return GeoJsonObjectType.GEOMETRY_COLLECTION;
    }

    public List<? extends Geometry> getGeometries() {
        return this.geometries;
    }

    @Override // com.mongodb.client.model.geojson.Geometry
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass() || !super.equals(o)) {
            return false;
        }
        GeometryCollection that = (GeometryCollection) o;
        if (!this.geometries.equals(that.geometries)) {
            return false;
        }
        return true;
    }

    @Override // com.mongodb.client.model.geojson.Geometry
    public int hashCode() {
        int result = super.hashCode();
        return (31 * result) + this.geometries.hashCode();
    }

    public String toString() {
        CoordinateReferenceSystem coordinateReferenceSystem = getCoordinateReferenceSystem();
        return "GeometryCollection{geometries=" + this.geometries + (coordinateReferenceSystem == null ? "" : ", coordinateReferenceSystem=" + coordinateReferenceSystem) + '}';
    }
}
