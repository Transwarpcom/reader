package org.apache.commons.lang3;

import java.io.Serializable;
import java.util.Comparator;

/* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/Range.class */
public final class Range<T> implements Serializable {
    private static final long serialVersionUID = 1;
    private final Comparator<T> comparator;
    private final T minimum;
    private final T maximum;
    private transient int hashCode;
    private transient String toString;

    /* JADX WARN: Incorrect types in method signature: <T::Ljava/lang/Comparable<TT;>;>(TT;)Lorg/apache/commons/lang3/Range<TT;>; */
    public static Range is(Comparable comparable) {
        return between(comparable, comparable, null);
    }

    public static <T> Range<T> is(T element, Comparator<T> comparator) {
        return between(element, element, comparator);
    }

    /* JADX WARN: Incorrect types in method signature: <T::Ljava/lang/Comparable<TT;>;>(TT;TT;)Lorg/apache/commons/lang3/Range<TT;>; */
    public static Range between(Comparable comparable, Comparable comparable2) {
        return between(comparable, comparable2, null);
    }

    public static <T> Range<T> between(T fromInclusive, T toInclusive, Comparator<T> comparator) {
        return new Range<>(fromInclusive, toInclusive, comparator);
    }

    private Range(T element1, T element2, Comparator<T> comp) {
        if (element1 == null || element2 == null) {
            throw new IllegalArgumentException("Elements in a range must not be null: element1=" + element1 + ", element2=" + element2);
        }
        if (comp == null) {
            this.comparator = ComparableComparator.INSTANCE;
        } else {
            this.comparator = comp;
        }
        if (this.comparator.compare(element1, element2) < 1) {
            this.minimum = element1;
            this.maximum = element2;
        } else {
            this.minimum = element2;
            this.maximum = element1;
        }
    }

    public T getMinimum() {
        return this.minimum;
    }

    public T getMaximum() {
        return this.maximum;
    }

    public Comparator<T> getComparator() {
        return this.comparator;
    }

    public boolean isNaturalOrdering() {
        return this.comparator == ComparableComparator.INSTANCE;
    }

    public boolean contains(T element) {
        return element != null && this.comparator.compare(element, this.minimum) > -1 && this.comparator.compare(element, this.maximum) < 1;
    }

    public boolean isAfter(T element) {
        return element != null && this.comparator.compare(element, this.minimum) < 0;
    }

    public boolean isStartedBy(T element) {
        return element != null && this.comparator.compare(element, this.minimum) == 0;
    }

    public boolean isEndedBy(T element) {
        return element != null && this.comparator.compare(element, this.maximum) == 0;
    }

    public boolean isBefore(T element) {
        return element != null && this.comparator.compare(element, this.maximum) > 0;
    }

    public int elementCompareTo(T element) {
        Validate.notNull(element, "Element is null", new Object[0]);
        if (isAfter(element)) {
            return -1;
        }
        if (isBefore(element)) {
            return 1;
        }
        return 0;
    }

    public boolean containsRange(Range<T> otherRange) {
        return otherRange != null && contains(otherRange.minimum) && contains(otherRange.maximum);
    }

    public boolean isAfterRange(Range<T> otherRange) {
        if (otherRange == null) {
            return false;
        }
        return isAfter(otherRange.maximum);
    }

    public boolean isOverlappedBy(Range<T> otherRange) {
        if (otherRange == null) {
            return false;
        }
        return otherRange.contains(this.minimum) || otherRange.contains(this.maximum) || contains(otherRange.minimum);
    }

    public boolean isBeforeRange(Range<T> otherRange) {
        if (otherRange == null) {
            return false;
        }
        return isBefore(otherRange.minimum);
    }

    public Range<T> intersectionWith(Range<T> other) {
        if (!isOverlappedBy(other)) {
            throw new IllegalArgumentException(String.format("Cannot calculate intersection with non-overlapping range %s", other));
        }
        if (equals(other)) {
            return this;
        }
        T min = getComparator().compare(this.minimum, other.minimum) < 0 ? other.minimum : this.minimum;
        T max = getComparator().compare(this.maximum, other.maximum) < 0 ? this.maximum : other.maximum;
        return between(min, max, getComparator());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Range<T> range = (Range) obj;
        return this.minimum.equals(range.minimum) && this.maximum.equals(range.maximum);
    }

    public int hashCode() {
        int result = this.hashCode;
        if (this.hashCode == 0) {
            int result2 = (37 * 17) + getClass().hashCode();
            result = (37 * ((37 * result2) + this.minimum.hashCode())) + this.maximum.hashCode();
            this.hashCode = result;
        }
        return result;
    }

    public String toString() {
        if (this.toString == null) {
            this.toString = "[" + this.minimum + ".." + this.maximum + "]";
        }
        return this.toString;
    }

    public String toString(String format) {
        return String.format(format, this.minimum, this.maximum, this.comparator);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/Range$ComparableComparator.class */
    private enum ComparableComparator implements Comparator {
        INSTANCE;

        @Override // java.util.Comparator
        public int compare(Object obj1, Object obj2) {
            return ((Comparable) obj1).compareTo(obj2);
        }
    }
}
