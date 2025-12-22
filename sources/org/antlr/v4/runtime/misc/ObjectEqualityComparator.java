package org.antlr.v4.runtime.misc;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/misc/ObjectEqualityComparator.class */
public final class ObjectEqualityComparator extends AbstractEqualityComparator<Object> {
    public static final ObjectEqualityComparator INSTANCE = new ObjectEqualityComparator();

    @Override // org.antlr.v4.runtime.misc.EqualityComparator
    public int hashCode(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    @Override // org.antlr.v4.runtime.misc.EqualityComparator
    public boolean equals(Object a, Object b) {
        if (a == null) {
            return b == null;
        }
        return a.equals(b);
    }
}
