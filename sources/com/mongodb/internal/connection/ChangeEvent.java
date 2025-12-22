package com.mongodb.internal.connection;

import com.mongodb.assertions.Assertions;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ChangeEvent.class */
final class ChangeEvent<T> {
    private final T previousValue;
    private final T newValue;

    ChangeEvent(T t, T t2) {
        this.previousValue = (T) Assertions.notNull("oldValue", t);
        this.newValue = (T) Assertions.notNull("newValue", t2);
    }

    public T getPreviousValue() {
        return this.previousValue;
    }

    public T getNewValue() {
        return this.newValue;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChangeEvent<?> that = (ChangeEvent) o;
        if (!this.newValue.equals(that.newValue)) {
            return false;
        }
        if (this.previousValue != null) {
            if (!this.previousValue.equals(that.previousValue)) {
                return false;
            }
            return true;
        }
        if (that.previousValue != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.previousValue != null ? this.previousValue.hashCode() : 0;
        return (31 * result) + this.newValue.hashCode();
    }

    public String toString() {
        return "ChangeEvent{previousValue=" + this.previousValue + ", newValue=" + this.newValue + '}';
    }
}
