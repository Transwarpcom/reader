package com.mongodb.client.model.geojson;

import com.mongodb.assertions.Assertions;
import com.mongodb.lang.Nullable;
import java.util.Collections;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/geojson/MultiPolygon.class */
public final class MultiPolygon extends Geometry {
    private final List<PolygonCoordinates> coordinates;

    public MultiPolygon(List<PolygonCoordinates> coordinates) {
        this(null, coordinates);
    }

    public MultiPolygon(@Nullable CoordinateReferenceSystem coordinateReferenceSystem, List<PolygonCoordinates> coordinates) {
        super(coordinateReferenceSystem);
        Assertions.notNull("coordinates", coordinates);
        Assertions.isTrueArgument("coordinates has no null elements", !coordinates.contains(null));
        this.coordinates = Collections.unmodifiableList(coordinates);
    }

    @Override // com.mongodb.client.model.geojson.Geometry
    public GeoJsonObjectType getType() {
        return GeoJsonObjectType.MULTI_POLYGON;
    }

    public List<PolygonCoordinates> getCoordinates() {
        return this.coordinates;
    }

    @Override // com.mongodb.client.model.geojson.Geometry
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass() || !super.equals(o)) {
            return false;
        }
        MultiPolygon that = (MultiPolygon) o;
        if (!this.coordinates.equals(that.coordinates)) {
            return false;
        }
        return true;
    }

    @Override // com.mongodb.client.model.geojson.Geometry
    public int hashCode() {
        int result = super.hashCode();
        return (31 * result) + this.coordinates.hashCode();
    }

    public String toString() {
        return "MultiPolygon{coordinates=" + this.coordinates + (getCoordinateReferenceSystem() == null ? "" : ", coordinateReferenceSystem=" + getCoordinateReferenceSystem()) + '}';
    }
}
