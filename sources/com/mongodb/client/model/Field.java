package com.mongodb.client.model;

import com.mongodb.assertions.Assertions;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Field.class */
public class Field<TExpression> {
    private final String name;
    private TExpression value;

    public Field(String name, TExpression value) {
        this.name = (String) Assertions.notNull("name", name);
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public TExpression getValue() {
        return this.value;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Field)) {
            return false;
        }
        Field<?> field = (Field) o;
        if (this.name.equals(field.name)) {
            return this.value != null ? this.value.equals(field.value) : field.value == null;
        }
        return false;
    }

    public int hashCode() {
        int result = this.name.hashCode();
        return (31 * result) + (this.value != null ? this.value.hashCode() : 0);
    }

    public String toString() {
        return "Field{name='" + this.name + "', value=" + this.value + '}';
    }
}
