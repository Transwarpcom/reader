package com.mongodb.client.model.geojson;

import com.mongodb.assertions.Assertions;
import com.mongodb.lang.Nullable;
import java.util.Collections;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/geojson/LineString.class */
public final class LineString extends Geometry {
    private final List<Position> coordinates;

    public LineString(List<Position> coordinates) {
        this(null, coordinates);
    }

    public LineString(@Nullable CoordinateReferenceSystem coordinateReferenceSystem, List<Position> coordinates) {
        super(coordinateReferenceSystem);
        Assertions.notNull("coordinates", coordinates);
        Assertions.isTrueArgument("coordinates must contain at least two positions", coordinates.size() >= 2);
        Assertions.isTrueArgument("coordinates contains only non-null positions", !coordinates.contains(null));
        this.coordinates = Collections.unmodifiableList(coordinates);
    }

    @Override // com.mongodb.client.model.geojson.Geometry
    public GeoJsonObjectType getType() {
        return GeoJsonObjectType.LINE_STRING;
    }

    public List<Position> getCoordinates() {
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
        LineString lineString = (LineString) o;
        if (!this.coordinates.equals(lineString.coordinates)) {
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
        return "LineString{coordinates=" + this.coordinates + (getCoordinateReferenceSystem() == null ? "" : ", coordinateReferenceSystem=" + getCoordinateReferenceSystem()) + '}';
    }
}
