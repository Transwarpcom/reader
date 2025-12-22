package com.mongodb.client.model.geojson;

import com.mongodb.assertions.Assertions;
import com.mongodb.lang.Nullable;
import java.util.Collections;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/geojson/MultiLineString.class */
public final class MultiLineString extends Geometry {
    private final List<List<Position>> coordinates;

    public MultiLineString(List<List<Position>> coordinates) {
        this(null, coordinates);
    }

    public MultiLineString(@Nullable CoordinateReferenceSystem coordinateReferenceSystem, List<List<Position>> coordinates) {
        super(coordinateReferenceSystem);
        Assertions.notNull("coordinates", coordinates);
        for (List<Position> line : coordinates) {
            Assertions.notNull("line", line);
            Assertions.isTrueArgument("line contains only non-null positions", !line.contains(null));
        }
        this.coordinates = Collections.unmodifiableList(coordinates);
    }

    @Override // com.mongodb.client.model.geojson.Geometry
    public GeoJsonObjectType getType() {
        return GeoJsonObjectType.MULTI_LINE_STRING;
    }

    public List<List<Position>> getCoordinates() {
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
        MultiLineString polygon = (MultiLineString) o;
        if (!this.coordinates.equals(polygon.coordinates)) {
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
        return "MultiLineString{coordinates=" + this.coordinates + (getCoordinateReferenceSystem() == null ? "" : ", coordinateReferenceSystem=" + getCoordinateReferenceSystem()) + '}';
    }
}
