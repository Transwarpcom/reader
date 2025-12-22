package com.mongodb.client.model;

import com.mongodb.assertions.Assertions;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Variable.class */
public class Variable<TExpression> {
    private final String name;
    private final TExpression value;

    public Variable(String name, TExpression value) {
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
        if (!(o instanceof Variable)) {
            return false;
        }
        Variable<?> variable = (Variable) o;
        if (this.name.equals(variable.name)) {
            return this.value != null ? this.value.equals(variable.value) : variable.value == null;
        }
        return false;
    }

    public int hashCode() {
        int result = this.name.hashCode();
        return (31 * result) + (this.value != null ? this.value.hashCode() : 0);
    }

    public String toString() {
        return "Variable{name='" + this.name + "', value=" + this.value + '}';
    }
}
