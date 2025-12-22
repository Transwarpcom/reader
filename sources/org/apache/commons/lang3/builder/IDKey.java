package org.apache.commons.lang3.builder;

/* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/builder/IDKey.class */
final class IDKey {
    private final Object value;
    private final int id;

    IDKey(Object _value) {
        this.id = System.identityHashCode(_value);
        this.value = _value;
    }

    public int hashCode() {
        return this.id;
    }

    public boolean equals(Object other) {
        if (!(other instanceof IDKey)) {
            return false;
        }
        IDKey idKey = (IDKey) other;
        return this.id == idKey.id && this.value == idKey.value;
    }
}
