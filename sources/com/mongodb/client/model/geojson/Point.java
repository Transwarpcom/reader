package com.mongodb.client.model.geojson;

import com.mongodb.assertions.Assertions;
import com.mongodb.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/geojson/Point.class */
public final class Point extends Geometry {
    private final Position coordinate;

    public Point(Position coordinate) {
        this(null, coordinate);
    }

    public Point(@Nullable CoordinateReferenceSystem coordinateReferenceSystem, Position coordinate) {
        super(coordinateReferenceSystem);
        this.coordinate = (Position) Assertions.notNull("coordinates", coordinate);
    }

    @Override // com.mongodb.client.model.geojson.Geometry
    public GeoJsonObjectType getType() {
        return GeoJsonObjectType.POINT;
    }

    public Position getCoordinates() {
        return this.coordinate;
    }

    public Position getPosition() {
        return this.coordinate;
    }

    @Override // com.mongodb.client.model.geojson.Geometry
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass() || !super.equals(o)) {
            return false;
        }
        Point point = (Point) o;
        if (!this.coordinate.equals(point.coordinate)) {
            return false;
        }
        return true;
    }

    @Override // com.mongodb.client.model.geojson.Geometry
    public int hashCode() {
        int result = super.hashCode();
        return (31 * result) + this.coordinate.hashCode();
    }

    public String toString() {
        return "Point{coordinate=" + this.coordinate + (getCoordinateReferenceSystem() == null ? "" : ", coordinateReferenceSystem=" + getCoordinateReferenceSystem()) + '}';
    }
}
