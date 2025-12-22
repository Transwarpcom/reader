package org.antlr.v4.runtime.misc;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/misc/Triple.class */
public class Triple<A, B, C> {
    public final A a;
    public final B b;
    public final C c;

    public Triple(A a, B b, C c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Triple)) {
            return false;
        }
        Triple<?, ?, ?> other = (Triple) obj;
        return ObjectEqualityComparator.INSTANCE.equals(this.a, other.a) && ObjectEqualityComparator.INSTANCE.equals(this.b, other.b) && ObjectEqualityComparator.INSTANCE.equals(this.c, other.c);
    }

    public int hashCode() {
        int hash = MurmurHash.initialize();
        return MurmurHash.finish(MurmurHash.update(MurmurHash.update(MurmurHash.update(hash, this.a), this.b), this.c), 3);
    }

    public String toString() {
        return String.format("(%s, %s, %s)", this.a, this.b, this.c);
    }
}
