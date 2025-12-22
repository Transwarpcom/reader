package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import java.lang.Comparable;
import java.util.Set;

@Beta
@GwtIncompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/RangeSet.class */
public interface RangeSet<C extends Comparable> {
    boolean contains(C c);

    Range<C> rangeContaining(C c);

    boolean intersects(Range<C> range);

    boolean encloses(Range<C> range);

    boolean enclosesAll(RangeSet<C> rangeSet);

    boolean isEmpty();

    Range<C> span();

    Set<Range<C>> asRanges();

    Set<Range<C>> asDescendingSetOfRanges();

    RangeSet<C> complement();

    RangeSet<C> subRangeSet(Range<C> range);

    void add(Range<C> range);

    void remove(Range<C> range);

    void clear();

    void addAll(RangeSet<C> rangeSet);

    void removeAll(RangeSet<C> rangeSet);

    boolean equals(Object obj);

    int hashCode();

    String toString();

    default boolean enclosesAll(Iterable<Range<C>> other) {
        for (Range<C> range : other) {
            if (!encloses(range)) {
                return false;
            }
        }
        return true;
    }

    default void addAll(Iterable<Range<C>> ranges) {
        for (Range<C> range : ranges) {
            add(range);
        }
    }

    default void removeAll(Iterable<Range<C>> ranges) {
        for (Range<C> range : ranges) {
            remove(range);
        }
    }
}
