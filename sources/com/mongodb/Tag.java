package com.mongodb;

import com.mongodb.annotations.Immutable;
import com.mongodb.assertions.Assertions;

@Immutable
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/Tag.class */
public final class Tag {
    private final String name;
    private final String value;

    public Tag(String name, String value) {
        this.name = (String) Assertions.notNull("name", name);
        this.value = (String) Assertions.notNull("value", value);
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tag that = (Tag) o;
        if (!this.name.equals(that.name) || !this.value.equals(that.value)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.name.hashCode();
        return (31 * result) + this.value.hashCode();
    }

    public String toString() {
        return "Tag{name='" + this.name + "', value='" + this.value + "'}";
    }
}
