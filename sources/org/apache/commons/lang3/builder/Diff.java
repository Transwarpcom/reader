package org.apache.commons.lang3.builder;

import java.lang.reflect.Type;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.apache.commons.lang3.tuple.Pair;

/* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/builder/Diff.class */
public abstract class Diff<T> extends Pair<T, T> {
    private static final long serialVersionUID = 1;
    private final Type type = (Type) ObjectUtils.defaultIfNull(TypeUtils.getTypeArguments(getClass(), Diff.class).get(Diff.class.getTypeParameters()[0]), Object.class);
    private final String fieldName;

    protected Diff(String fieldName) {
        this.fieldName = fieldName;
    }

    public final Type getType() {
        return this.type;
    }

    public final String getFieldName() {
        return this.fieldName;
    }

    @Override // org.apache.commons.lang3.tuple.Pair
    public final String toString() {
        return String.format("[%s: %s, %s]", this.fieldName, getLeft(), getRight());
    }

    @Override // java.util.Map.Entry
    public final T setValue(T value) {
        throw new UnsupportedOperationException("Cannot alter Diff object.");
    }
}
