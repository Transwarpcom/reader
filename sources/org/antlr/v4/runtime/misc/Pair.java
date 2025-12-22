package org.antlr.v4.runtime.misc;

import java.io.Serializable;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/misc/Pair.class */
public class Pair<A, B> implements Serializable {
    public final A a;
    public final B b;

    public Pair(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair<?, ?> other = (Pair) obj;
        return ObjectEqualityComparator.INSTANCE.equals(this.a, other.a) && ObjectEqualityComparator.INSTANCE.equals(this.b, other.b);
    }

    public int hashCode() {
        int hash = MurmurHash.initialize();
        return MurmurHash.finish(MurmurHash.update(MurmurHash.update(hash, this.a), this.b), 2);
    }

    public String toString() {
        return String.format("(%s, %s)", this.a, this.b);
    }
}
