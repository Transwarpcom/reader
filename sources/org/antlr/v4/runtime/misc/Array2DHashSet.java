package org.antlr.v4.runtime.misc;

import cn.hutool.core.text.StrPool;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/misc/Array2DHashSet.class */
public class Array2DHashSet<T> implements Set<T> {
    public static final int INITAL_CAPACITY = 16;
    public static final int INITAL_BUCKET_CAPACITY = 8;
    public static final double LOAD_FACTOR = 0.75d;
    protected final AbstractEqualityComparator<? super T> comparator;
    protected T[][] buckets;
    protected int n;
    protected int threshold;
    protected int currentPrime;
    protected int initialBucketCapacity;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !Array2DHashSet.class.desiredAssertionStatus();
    }

    public Array2DHashSet() {
        this(null, 16, 8);
    }

    public Array2DHashSet(AbstractEqualityComparator<? super T> comparator) {
        this(comparator, 16, 8);
    }

    public Array2DHashSet(AbstractEqualityComparator<? super T> comparator, int initialCapacity, int initialBucketCapacity) {
        this.n = 0;
        this.threshold = (int) Math.floor(12.0d);
        this.currentPrime = 1;
        this.initialBucketCapacity = 8;
        this.comparator = comparator == null ? ObjectEqualityComparator.INSTANCE : comparator;
        this.buckets = createBuckets(initialCapacity);
        this.initialBucketCapacity = initialBucketCapacity;
    }

    public final T getOrAdd(T o) {
        if (this.n > this.threshold) {
            expand();
        }
        return getOrAddImpl(o);
    }

    protected T getOrAddImpl(T t) {
        int bucket = getBucket(t);
        T[] tArr = this.buckets[bucket];
        if (tArr == null) {
            T[] tArrCreateBucket = createBucket(this.initialBucketCapacity);
            tArrCreateBucket[0] = t;
            this.buckets[bucket] = tArrCreateBucket;
            this.n++;
            return t;
        }
        for (int i = 0; i < tArr.length; i++) {
            T t2 = tArr[i];
            if (t2 == null) {
                tArr[i] = t;
                this.n++;
                return t;
            }
            if (this.comparator.equals(t2, t)) {
                return t2;
            }
        }
        int length = tArr.length;
        Object[] objArrCopyOf = Arrays.copyOf(tArr, tArr.length * 2);
        ((T[][]) this.buckets)[bucket] = objArrCopyOf;
        objArrCopyOf[length] = t;
        this.n++;
        return t;
    }

    public T get(T o) {
        T e;
        if (o == null) {
            return o;
        }
        int b = getBucket(o);
        T[] bucket = this.buckets[b];
        if (bucket == null) {
            return null;
        }
        int len$ = bucket.length;
        for (int i$ = 0; i$ < len$ && (e = bucket[i$]) != null; i$++) {
            if (this.comparator.equals(e, o)) {
                return e;
            }
        }
        return null;
    }

    protected final int getBucket(T o) {
        int hash = this.comparator.hashCode(o);
        int b = hash & (this.buckets.length - 1);
        return b;
    }

    @Override // java.util.Set, java.util.Collection
    public int hashCode() {
        Object obj;
        int hash = MurmurHash.initialize();
        Object[][] arr$ = this.buckets;
        for (Object[] objArr : arr$) {
            if (objArr != null) {
                int len$ = objArr.length;
                for (int i$ = 0; i$ < len$ && (obj = objArr[i$]) != null; i$++) {
                    hash = MurmurHash.update(hash, this.comparator.hashCode(obj));
                }
            }
        }
        return MurmurHash.finish(hash, size());
    }

    @Override // java.util.Set, java.util.Collection
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Array2DHashSet)) {
            return false;
        }
        Array2DHashSet<?> other = (Array2DHashSet) o;
        if (other.size() != size()) {
            return false;
        }
        boolean same = containsAll(other);
        return same;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v45, types: [java.lang.Object[]] */
    protected void expand() {
        T o;
        T[] newBucket;
        T[][] old = this.buckets;
        this.currentPrime += 4;
        int newCapacity = this.buckets.length * 2;
        T[][] newTable = createBuckets(newCapacity);
        int[] newBucketLengths = new int[newTable.length];
        this.buckets = newTable;
        this.threshold = (int) (newCapacity * 0.75d);
        int oldSize = size();
        for (T[] bucket : old) {
            if (bucket != null) {
                int len$ = bucket.length;
                for (int i$ = 0; i$ < len$ && (o = bucket[i$]) != null; i$++) {
                    int b = getBucket(o);
                    int bucketLength = newBucketLengths[b];
                    if (bucketLength == 0) {
                        newBucket = createBucket(this.initialBucketCapacity);
                        newTable[b] = newBucket;
                    } else {
                        newBucket = newTable[b];
                        if (bucketLength == newBucket.length) {
                            newBucket = Arrays.copyOf(newBucket, newBucket.length * 2);
                            newTable[b] = newBucket;
                        }
                    }
                    newBucket[bucketLength] = o;
                    newBucketLengths[b] = newBucketLengths[b] + 1;
                }
            }
        }
        if (!$assertionsDisabled && this.n != oldSize) {
            throw new AssertionError();
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean add(T t) {
        T existing = getOrAdd(t);
        return existing == t;
    }

    @Override // java.util.Set, java.util.Collection
    public final int size() {
        return this.n;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean isEmpty() {
        return this.n == 0;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean contains(Object o) {
        return containsFast(asElementType(o));
    }

    public boolean containsFast(T obj) {
        return (obj == null || get(obj) == null) ? false : true;
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return new SetIterator(toArray());
    }

    @Override // java.util.Set, java.util.Collection
    public T[] toArray() {
        T o;
        T[] a = createBucket(size());
        int i = 0;
        T[][] arr$ = this.buckets;
        for (T[] bucket : arr$) {
            if (bucket != null) {
                int len$ = bucket.length;
                for (int i$ = 0; i$ < len$ && (o = bucket[i$]) != null; i$++) {
                    int i2 = i;
                    i++;
                    a[i2] = o;
                }
            }
        }
        return a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v24, types: [java.lang.Object[]] */
    @Override // java.util.Set, java.util.Collection
    public <U> U[] toArray(U[] a) {
        T o;
        if (a.length < size()) {
            a = Arrays.copyOf(a, size());
        }
        int i = 0;
        T[][] arr$ = this.buckets;
        for (T[] bucket : arr$) {
            if (bucket != null) {
                int len$ = bucket.length;
                for (int i$ = 0; i$ < len$ && (o = bucket[i$]) != null; i$++) {
                    int i2 = i;
                    i++;
                    a[i2] = o;
                }
            }
        }
        return a;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean remove(Object o) {
        return removeFast(asElementType(o));
    }

    public boolean removeFast(T obj) {
        T e;
        if (obj == null) {
            return false;
        }
        int b = getBucket(obj);
        T[] bucket = this.buckets[b];
        if (bucket == null) {
            return false;
        }
        for (int i = 0; i < bucket.length && (e = bucket[i]) != null; i++) {
            if (this.comparator.equals(e, obj)) {
                System.arraycopy(bucket, i + 1, bucket, i, (bucket.length - i) - 1);
                bucket[bucket.length - 1] = null;
                this.n--;
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        Object o;
        if (collection instanceof Array2DHashSet) {
            Array2DHashSet<?> s = (Array2DHashSet) collection;
            Object[][] arr$ = s.buckets;
            for (Object[] bucket : arr$) {
                if (bucket != null) {
                    int len$ = bucket.length;
                    for (int i$ = 0; i$ < len$ && (o = bucket[i$]) != null; i$++) {
                        if (!containsFast(asElementType(o))) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        Iterator i$2 = collection.iterator();
        while (i$2.hasNext()) {
            if (!containsFast(asElementType(i$2.next()))) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection<? extends T> c) {
        boolean changed = false;
        for (T o : c) {
            T existing = getOrAdd(o);
            if (existing != o) {
                changed = true;
            }
        }
        return changed;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection<?> c) {
        int newsize = 0;
        T[][] arr$ = this.buckets;
        for (T[] bucket : arr$) {
            if (bucket != null) {
                int i = 0;
                int j = 0;
                while (i < bucket.length && bucket[i] != null) {
                    if (c.contains(bucket[i])) {
                        if (i != j) {
                            bucket[j] = bucket[i];
                        }
                        j++;
                        newsize++;
                    }
                    i++;
                }
                newsize += j;
                while (j < i) {
                    bucket[j] = null;
                    j++;
                }
            }
        }
        boolean changed = newsize != this.n;
        this.n = newsize;
        return changed;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (Object o : c) {
            changed |= removeFast(asElementType(o));
        }
        return changed;
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        this.buckets = createBuckets(16);
        this.n = 0;
        this.threshold = (int) Math.floor(12.0d);
    }

    public String toString() {
        T o;
        if (size() == 0) {
            return StrPool.EMPTY_JSON;
        }
        StringBuilder buf = new StringBuilder();
        buf.append('{');
        boolean first = true;
        T[][] arr$ = this.buckets;
        for (T[] bucket : arr$) {
            if (bucket != null) {
                int len$ = bucket.length;
                for (int i$ = 0; i$ < len$ && (o = bucket[i$]) != null; i$++) {
                    if (first) {
                        first = false;
                    } else {
                        buf.append(", ");
                    }
                    buf.append(o.toString());
                }
            }
        }
        buf.append('}');
        return buf.toString();
    }

    public String toTableString() {
        StringBuilder buf = new StringBuilder();
        T[][] arr$ = this.buckets;
        for (T[] bucket : arr$) {
            if (bucket == null) {
                buf.append("null\n");
            } else {
                buf.append('[');
                boolean first = true;
                for (T o : bucket) {
                    if (first) {
                        first = false;
                    } else {
                        buf.append(" ");
                    }
                    if (o == null) {
                        buf.append("_");
                    } else {
                        buf.append(o.toString());
                    }
                }
                buf.append("]\n");
            }
        }
        return buf.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected T asElementType(Object obj) {
        return obj;
    }

    protected T[][] createBuckets(int i) {
        return (T[][]) ((Object[][]) new Object[i]);
    }

    protected T[] createBucket(int i) {
        return (T[]) new Object[i];
    }

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/misc/Array2DHashSet$SetIterator.class */
    protected class SetIterator implements Iterator<T> {
        final T[] data;
        int nextIndex = 0;
        boolean removed = true;

        public SetIterator(T[] data) {
            this.data = data;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.nextIndex < this.data.length;
        }

        @Override // java.util.Iterator
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            this.removed = false;
            T[] tArr = this.data;
            int i = this.nextIndex;
            this.nextIndex = i + 1;
            return tArr[i];
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.removed) {
                throw new IllegalStateException();
            }
            Array2DHashSet.this.remove(this.data[this.nextIndex - 1]);
            this.removed = true;
        }
    }
}
