package com.mongodb.client.model.geojson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/geojson/CoordinateReferenceSystemType.class */
public enum CoordinateReferenceSystemType {
    NAME("name"),
    LINK("link");

    private final String typeName;

    public String getTypeName() {
        return this.typeName;
    }

    CoordinateReferenceSystemType(String typeName) {
        this.typeName = typeName;
    }
}
