package com.mongodb.client.model;

import com.mongodb.assertions.Assertions;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/BsonField.class */
public final class BsonField {
    private final String name;
    private final Bson value;

    public BsonField(String name, Bson value) {
        this.name = (String) Assertions.notNull("name", name);
        this.value = (Bson) Assertions.notNull("value", value);
    }

    public String getName() {
        return this.name;
    }

    public Bson getValue() {
        return this.value;
    }

    public String toString() {
        return "BsonField{name='" + this.name + "', value=" + this.value + '}';
    }
}
