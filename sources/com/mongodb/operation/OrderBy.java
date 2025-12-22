package com.mongodb.operation;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/OrderBy.class */
public enum OrderBy {
    ASC(1),
    DESC(-1);

    private final int intRepresentation;

    OrderBy(int intRepresentation) {
        this.intRepresentation = intRepresentation;
    }

    public int getIntRepresentation() {
        return this.intRepresentation;
    }

    public static OrderBy fromInt(int intRepresentation) {
        switch (intRepresentation) {
            case -1:
                return DESC;
            case 1:
                return ASC;
            default:
                throw new IllegalArgumentException(intRepresentation + " is not a valid index Order");
        }
    }
}
