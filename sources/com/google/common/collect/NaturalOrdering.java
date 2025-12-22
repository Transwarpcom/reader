package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;

@GwtCompatible(serializable = true)
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/NaturalOrdering.class */
final class NaturalOrdering extends Ordering<Comparable> implements Serializable {
    static final NaturalOrdering INSTANCE = new NaturalOrdering();
    private transient Ordering<Comparable> nullsFirst;
    private transient Ordering<Comparable> nullsLast;
    private static final long serialVersionUID = 0;

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(Comparable left, Comparable right) {
        Preconditions.checkNotNull(left);
        Preconditions.checkNotNull(right);
        return left.compareTo(right);
    }

    @Override // com.google.common.collect.Ordering
    public <S extends Comparable> Ordering<S> nullsFirst() {
        Ordering<Comparable> ordering = this.nullsFirst;
        if (ordering == null) {
            Ordering<Comparable> orderingNullsFirst = super.nullsFirst();
            this.nullsFirst = orderingNullsFirst;
            ordering = orderingNullsFirst;
        }
        return (Ordering<S>) ordering;
    }

    @Override // com.google.common.collect.Ordering
    public <S extends Comparable> Ordering<S> nullsLast() {
        Ordering<Comparable> ordering = this.nullsLast;
        if (ordering == null) {
            Ordering<Comparable> orderingNullsLast = super.nullsLast();
            this.nullsLast = orderingNullsLast;
            ordering = orderingNullsLast;
        }
        return (Ordering<S>) ordering;
    }

    @Override // com.google.common.collect.Ordering
    public <S extends Comparable> Ordering<S> reverse() {
        return ReverseNaturalOrdering.INSTANCE;
    }

    private Object readResolve() {
        return INSTANCE;
    }

    public String toString() {
        return "Ordering.natural()";
    }

    private NaturalOrdering() {
    }
}
