package com.mongodb.connection;

import com.mongodb.assertions.Assertions;
import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/ClusterId.class */
public final class ClusterId {
    private final String value;
    private final String description;

    public ClusterId() {
        this(null);
    }

    public ClusterId(String description) {
        this.value = new ObjectId().toHexString();
        this.description = description;
    }

    ClusterId(String value, String description) {
        this.value = (String) Assertions.notNull("value", value);
        this.description = description;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClusterId clusterId = (ClusterId) o;
        if (!this.value.equals(clusterId.value)) {
            return false;
        }
        if (this.description != null) {
            if (!this.description.equals(clusterId.description)) {
                return false;
            }
            return true;
        }
        if (clusterId.description != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.value.hashCode();
        return (31 * result) + (this.description != null ? this.description.hashCode() : 0);
    }

    public String toString() {
        return "ClusterId{value='" + this.value + "', description='" + this.description + "'}";
    }
}
