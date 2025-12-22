package org.bson;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonElement.class */
public class BsonElement {
    private final String name;
    private final BsonValue value;

    public BsonElement(String name, BsonValue value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public BsonValue getValue() {
        return this.value;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BsonElement that = (BsonElement) o;
        if (getName() != null) {
            if (!getName().equals(that.getName())) {
                return false;
            }
        } else if (that.getName() != null) {
            return false;
        }
        if (getValue() != null) {
            if (!getValue().equals(that.getValue())) {
                return false;
            }
            return true;
        }
        if (that.getValue() != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        return (31 * result) + (getValue() != null ? getValue().hashCode() : 0);
    }
}
