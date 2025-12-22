package org.antlr.v4.runtime.misc;

import cn.hutool.core.text.StrPool;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/misc/IntervalSet.class */
public class IntervalSet implements IntSet {
    public static final IntervalSet COMPLETE_CHAR_SET = of(0, Lexer.MAX_CHAR_VALUE);
    public static final IntervalSet EMPTY_SET;
    protected List<Interval> intervals;
    protected boolean readonly;

    static {
        COMPLETE_CHAR_SET.setReadonly(true);
        EMPTY_SET = new IntervalSet(new int[0]);
        EMPTY_SET.setReadonly(true);
    }

    public IntervalSet(List<Interval> intervals) {
        this.intervals = intervals;
    }

    public IntervalSet(IntervalSet set) {
        this(new int[0]);
        addAll((IntSet) set);
    }

    public IntervalSet(int... els) {
        if (els == null) {
            this.intervals = new ArrayList(2);
            return;
        }
        this.intervals = new ArrayList(els.length);
        for (int e : els) {
            add(e);
        }
    }

    public static IntervalSet of(int a) {
        IntervalSet s = new IntervalSet(new int[0]);
        s.add(a);
        return s;
    }

    public static IntervalSet of(int a, int b) {
        IntervalSet s = new IntervalSet(new int[0]);
        s.add(a, b);
        return s;
    }

    public void clear() {
        if (this.readonly) {
            throw new IllegalStateException("can't alter readonly IntervalSet");
        }
        this.intervals.clear();
    }

    @Override // org.antlr.v4.runtime.misc.IntSet
    public void add(int el) {
        if (this.readonly) {
            throw new IllegalStateException("can't alter readonly IntervalSet");
        }
        add(el, el);
    }

    public void add(int a, int b) {
        add(Interval.of(a, b));
    }

    protected void add(Interval addition) {
        if (this.readonly) {
            throw new IllegalStateException("can't alter readonly IntervalSet");
        }
        if (addition.b < addition.a) {
            return;
        }
        ListIterator<Interval> iter = this.intervals.listIterator();
        while (iter.hasNext()) {
            Interval r = iter.next();
            if (addition.equals(r)) {
                return;
            }
            if (addition.adjacent(r) || !addition.disjoint(r)) {
                Interval bigger = addition.union(r);
                iter.set(bigger);
                while (iter.hasNext()) {
                    Interval next = iter.next();
                    if (bigger.adjacent(next) || !bigger.disjoint(next)) {
                        iter.remove();
                        iter.previous();
                        iter.set(bigger.union(next));
                        iter.next();
                    } else {
                        return;
                    }
                }
                return;
            }
            if (addition.startsBeforeDisjoint(r)) {
                iter.previous();
                iter.add(addition);
                return;
            }
        }
        this.intervals.add(addition);
    }

    public static IntervalSet or(IntervalSet[] sets) {
        IntervalSet r = new IntervalSet(new int[0]);
        for (IntervalSet s : sets) {
            r.addAll((IntSet) s);
        }
        return r;
    }

    @Override // org.antlr.v4.runtime.misc.IntSet
    public IntervalSet addAll(IntSet set) {
        if (set == null) {
            return this;
        }
        if (set instanceof IntervalSet) {
            IntervalSet other = (IntervalSet) set;
            int n = other.intervals.size();
            for (int i = 0; i < n; i++) {
                Interval I = other.intervals.get(i);
                add(I.a, I.b);
            }
        } else {
            Iterator i$ = set.toList().iterator();
            while (i$.hasNext()) {
                int value = i$.next().intValue();
                add(value);
            }
        }
        return this;
    }

    public IntervalSet complement(int minElement, int maxElement) {
        return complement((IntSet) of(minElement, maxElement));
    }

    @Override // org.antlr.v4.runtime.misc.IntSet
    public IntervalSet complement(IntSet vocabulary) {
        IntervalSet vocabularyIS;
        if (vocabulary == null || vocabulary.isNil()) {
            return null;
        }
        if (vocabulary instanceof IntervalSet) {
            vocabularyIS = (IntervalSet) vocabulary;
        } else {
            vocabularyIS = new IntervalSet(new int[0]);
            vocabularyIS.addAll(vocabulary);
        }
        return vocabularyIS.subtract((IntSet) this);
    }

    @Override // org.antlr.v4.runtime.misc.IntSet
    public IntervalSet subtract(IntSet a) {
        if (a == null || a.isNil()) {
            return new IntervalSet(this);
        }
        if (a instanceof IntervalSet) {
            return subtract(this, (IntervalSet) a);
        }
        IntervalSet other = new IntervalSet(new int[0]);
        other.addAll(a);
        return subtract(this, other);
    }

    public static IntervalSet subtract(IntervalSet left, IntervalSet right) {
        if (left == null || left.isNil()) {
            return new IntervalSet(new int[0]);
        }
        IntervalSet result = new IntervalSet(left);
        if (right == null || right.isNil()) {
            return result;
        }
        int resultI = 0;
        int rightI = 0;
        while (resultI < result.intervals.size() && rightI < right.intervals.size()) {
            Interval resultInterval = result.intervals.get(resultI);
            Interval rightInterval = right.intervals.get(rightI);
            if (rightInterval.b < resultInterval.a) {
                rightI++;
            } else if (rightInterval.a > resultInterval.b) {
                resultI++;
            } else {
                Interval beforeCurrent = null;
                Interval afterCurrent = null;
                if (rightInterval.a > resultInterval.a) {
                    beforeCurrent = new Interval(resultInterval.a, rightInterval.a - 1);
                }
                if (rightInterval.b < resultInterval.b) {
                    afterCurrent = new Interval(rightInterval.b + 1, resultInterval.b);
                }
                if (beforeCurrent != null) {
                    if (afterCurrent != null) {
                        result.intervals.set(resultI, beforeCurrent);
                        result.intervals.add(resultI + 1, afterCurrent);
                        resultI++;
                        rightI++;
                    } else {
                        result.intervals.set(resultI, beforeCurrent);
                        resultI++;
                    }
                } else if (afterCurrent != null) {
                    result.intervals.set(resultI, afterCurrent);
                    rightI++;
                } else {
                    result.intervals.remove(resultI);
                }
            }
        }
        return result;
    }

    @Override // org.antlr.v4.runtime.misc.IntSet
    public IntervalSet or(IntSet a) {
        IntervalSet o = new IntervalSet(new int[0]);
        o.addAll((IntSet) this);
        o.addAll(a);
        return o;
    }

    @Override // org.antlr.v4.runtime.misc.IntSet
    public IntervalSet and(IntSet other) {
        if (other == null) {
            return null;
        }
        List<Interval> myIntervals = this.intervals;
        List<Interval> theirIntervals = ((IntervalSet) other).intervals;
        IntervalSet intersection = null;
        int mySize = myIntervals.size();
        int theirSize = theirIntervals.size();
        int i = 0;
        int j = 0;
        while (i < mySize && j < theirSize) {
            Interval mine = myIntervals.get(i);
            Interval theirs = theirIntervals.get(j);
            if (mine.startsBeforeDisjoint(theirs)) {
                i++;
            } else if (theirs.startsBeforeDisjoint(mine)) {
                j++;
            } else if (mine.properlyContains(theirs)) {
                if (intersection == null) {
                    intersection = new IntervalSet(new int[0]);
                }
                intersection.add(mine.intersection(theirs));
                j++;
            } else if (theirs.properlyContains(mine)) {
                if (intersection == null) {
                    intersection = new IntervalSet(new int[0]);
                }
                intersection.add(mine.intersection(theirs));
                i++;
            } else if (!mine.disjoint(theirs)) {
                if (intersection == null) {
                    intersection = new IntervalSet(new int[0]);
                }
                intersection.add(mine.intersection(theirs));
                if (mine.startsAfterNonDisjoint(theirs)) {
                    j++;
                } else if (theirs.startsAfterNonDisjoint(mine)) {
                    i++;
                }
            }
        }
        if (intersection == null) {
            return new IntervalSet(new int[0]);
        }
        return intersection;
    }

    @Override // org.antlr.v4.runtime.misc.IntSet
    public boolean contains(int el) {
        int n = this.intervals.size();
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            Interval I = this.intervals.get(m);
            int a = I.a;
            int b = I.b;
            if (b < el) {
                l = m + 1;
            } else if (a > el) {
                r = m - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override // org.antlr.v4.runtime.misc.IntSet
    public boolean isNil() {
        return this.intervals == null || this.intervals.isEmpty();
    }

    public int getMaxElement() {
        if (isNil()) {
            throw new RuntimeException("set is empty");
        }
        Interval last = this.intervals.get(this.intervals.size() - 1);
        return last.b;
    }

    public int getMinElement() {
        if (isNil()) {
            throw new RuntimeException("set is empty");
        }
        return this.intervals.get(0).a;
    }

    public List<Interval> getIntervals() {
        return this.intervals;
    }

    public int hashCode() {
        int hash = MurmurHash.initialize();
        for (Interval I : this.intervals) {
            hash = MurmurHash.update(MurmurHash.update(hash, I.a), I.b);
        }
        return MurmurHash.finish(hash, this.intervals.size() * 2);
    }

    @Override // org.antlr.v4.runtime.misc.IntSet
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof IntervalSet)) {
            return false;
        }
        IntervalSet other = (IntervalSet) obj;
        return this.intervals.equals(other.intervals);
    }

    @Override // org.antlr.v4.runtime.misc.IntSet
    public String toString() {
        return toString(false);
    }

    public String toString(boolean elemAreChar) {
        StringBuilder buf = new StringBuilder();
        if (this.intervals == null || this.intervals.isEmpty()) {
            return StrPool.EMPTY_JSON;
        }
        if (size() > 1) {
            buf.append(StrPool.DELIM_START);
        }
        Iterator<Interval> iter = this.intervals.iterator();
        while (iter.hasNext()) {
            Interval I = iter.next();
            int a = I.a;
            int b = I.b;
            if (a == b) {
                if (a == -1) {
                    buf.append("<EOF>");
                } else if (elemAreChar) {
                    buf.append(OperatorName.SHOW_TEXT_LINE).appendCodePoint(a).append(OperatorName.SHOW_TEXT_LINE);
                } else {
                    buf.append(a);
                }
            } else if (elemAreChar) {
                buf.append(OperatorName.SHOW_TEXT_LINE).appendCodePoint(a).append("'..'").appendCodePoint(b).append(OperatorName.SHOW_TEXT_LINE);
            } else {
                buf.append(a).append("..").append(b);
            }
            if (iter.hasNext()) {
                buf.append(", ");
            }
        }
        if (size() > 1) {
            buf.append("}");
        }
        return buf.toString();
    }

    @Deprecated
    public String toString(String[] tokenNames) {
        return toString(VocabularyImpl.fromTokenNames(tokenNames));
    }

    public String toString(Vocabulary vocabulary) {
        StringBuilder buf = new StringBuilder();
        if (this.intervals == null || this.intervals.isEmpty()) {
            return StrPool.EMPTY_JSON;
        }
        if (size() > 1) {
            buf.append(StrPool.DELIM_START);
        }
        Iterator<Interval> iter = this.intervals.iterator();
        while (iter.hasNext()) {
            Interval I = iter.next();
            int a = I.a;
            int b = I.b;
            if (a == b) {
                buf.append(elementName(vocabulary, a));
            } else {
                for (int i = a; i <= b; i++) {
                    if (i > a) {
                        buf.append(", ");
                    }
                    buf.append(elementName(vocabulary, i));
                }
            }
            if (iter.hasNext()) {
                buf.append(", ");
            }
        }
        if (size() > 1) {
            buf.append("}");
        }
        return buf.toString();
    }

    @Deprecated
    protected String elementName(String[] tokenNames, int a) {
        return elementName(VocabularyImpl.fromTokenNames(tokenNames), a);
    }

    protected String elementName(Vocabulary vocabulary, int a) {
        if (a == -1) {
            return "<EOF>";
        }
        if (a == -2) {
            return "<EPSILON>";
        }
        return vocabulary.getDisplayName(a);
    }

    @Override // org.antlr.v4.runtime.misc.IntSet
    public int size() {
        int n = 0;
        int numIntervals = this.intervals.size();
        if (numIntervals == 1) {
            Interval firstInterval = this.intervals.get(0);
            return (firstInterval.b - firstInterval.a) + 1;
        }
        for (int i = 0; i < numIntervals; i++) {
            Interval I = this.intervals.get(i);
            n += (I.b - I.a) + 1;
        }
        return n;
    }

    public IntegerList toIntegerList() {
        IntegerList values = new IntegerList(size());
        int n = this.intervals.size();
        for (int i = 0; i < n; i++) {
            Interval I = this.intervals.get(i);
            int a = I.a;
            int b = I.b;
            for (int v = a; v <= b; v++) {
                values.add(v);
            }
        }
        return values;
    }

    @Override // org.antlr.v4.runtime.misc.IntSet
    public List<Integer> toList() {
        List<Integer> values = new ArrayList<>();
        int n = this.intervals.size();
        for (int i = 0; i < n; i++) {
            Interval I = this.intervals.get(i);
            int a = I.a;
            int b = I.b;
            for (int v = a; v <= b; v++) {
                values.add(Integer.valueOf(v));
            }
        }
        return values;
    }

    public Set<Integer> toSet() {
        Set<Integer> s = new HashSet<>();
        for (Interval I : this.intervals) {
            int a = I.a;
            int b = I.b;
            for (int v = a; v <= b; v++) {
                s.add(Integer.valueOf(v));
            }
        }
        return s;
    }

    public int get(int i) {
        int n = this.intervals.size();
        int index = 0;
        for (int j = 0; j < n; j++) {
            Interval I = this.intervals.get(j);
            int a = I.a;
            int b = I.b;
            for (int v = a; v <= b; v++) {
                if (index == i) {
                    return v;
                }
                index++;
            }
        }
        return -1;
    }

    public int[] toArray() {
        return toIntegerList().toArray();
    }

    @Override // org.antlr.v4.runtime.misc.IntSet
    public void remove(int el) {
        if (this.readonly) {
            throw new IllegalStateException("can't alter readonly IntervalSet");
        }
        int n = this.intervals.size();
        for (int i = 0; i < n; i++) {
            Interval I = this.intervals.get(i);
            int a = I.a;
            int b = I.b;
            if (el >= a) {
                if (el == a && el == b) {
                    this.intervals.remove(i);
                    return;
                }
                if (el == a) {
                    I.a++;
                    return;
                }
                if (el == b) {
                    I.b--;
                    return;
                }
                if (el > a && el < b) {
                    int oldb = I.b;
                    I.b = el - 1;
                    add(el + 1, oldb);
                }
            } else {
                return;
            }
        }
    }

    public boolean isReadonly() {
        return this.readonly;
    }

    public void setReadonly(boolean readonly) {
        if (this.readonly && !readonly) {
            throw new IllegalStateException("can't alter readonly IntervalSet");
        }
        this.readonly = readonly;
    }
}
