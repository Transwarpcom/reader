package kotlin.reflect.jvm.internal.impl.utils;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SmartSet.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/utils/SmartSet.class */
public final class SmartSet<T> extends AbstractSet<T> {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @Nullable
    private Object data;
    private int size;

    @JvmStatic
    @NotNull
    public static final <T> SmartSet<T> create() {
        return Companion.create();
    }

    public /* synthetic */ SmartSet(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final /* bridge */ int size() {
        return getSize();
    }

    /* compiled from: SmartSet.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/utils/SmartSet$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final <T> SmartSet<T> create() {
            return new SmartSet<>(null);
        }

        @JvmStatic
        @NotNull
        public final <T> SmartSet<T> create(@NotNull Collection<? extends T> set) {
            Intrinsics.checkNotNullParameter(set, "set");
            SmartSet $this$create_u24lambda_u2d0 = new SmartSet(null);
            $this$create_u24lambda_u2d0.addAll(set);
            return $this$create_u24lambda_u2d0;
        }
    }

    private SmartSet() {
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int i) {
        this.size = i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    @NotNull
    public Iterator<T> iterator() {
        if (size() == 0) {
            return Collections.emptySet().iterator();
        }
        if (size() == 1) {
            return new SingletonIterator(this.data);
        }
        if (size() < 5) {
            Object obj = this.data;
            if (obj == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of org.jetbrains.kotlin.utils.SmartSet>");
            }
            return new ArrayIterator((Object[]) obj);
        }
        Object obj2 = this.data;
        if (obj2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableSet<T of org.jetbrains.kotlin.utils.SmartSet>");
        }
        return TypeIntrinsics.asMutableSet(obj2).iterator();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(T t) {
        SmartSet<T> smartSet;
        Object[] objArr;
        if (size() == 0) {
            this.data = t;
        } else if (size() == 1) {
            if (Intrinsics.areEqual(this.data, t)) {
                return false;
            }
            this.data = new Object[]{this.data, t};
        } else if (size() < 5) {
            Object obj = this.data;
            if (obj == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of org.jetbrains.kotlin.utils.SmartSet>");
            }
            Object[] arr = (Object[]) obj;
            if (ArraysKt.contains(arr, t)) {
                return false;
            }
            if (size() == 4) {
                Object[] objArr2 = new Object[arr.length];
                System.arraycopy(arr, 0, objArr2, 0, arr.length);
                LinkedHashSet linkedHashSetLinkedSetOf = SetsKt.linkedSetOf(objArr2);
                linkedHashSetLinkedSetOf.add(t);
                Unit unit = Unit.INSTANCE;
                smartSet = this;
                objArr = linkedHashSetLinkedSetOf;
            } else {
                Object[] $this$add_u24lambda_u2d1 = Arrays.copyOf(arr, size() + 1);
                Intrinsics.checkNotNullExpressionValue($this$add_u24lambda_u2d1, "java.util.Arrays.copyOf(this, newSize)");
                $this$add_u24lambda_u2d1[$this$add_u24lambda_u2d1.length - 1] = t;
                Unit unit2 = Unit.INSTANCE;
                smartSet = this;
                objArr = $this$add_u24lambda_u2d1;
            }
            smartSet.data = objArr;
        } else {
            Object obj2 = this.data;
            if (obj2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableSet<T of org.jetbrains.kotlin.utils.SmartSet>");
            }
            Set set = TypeIntrinsics.asMutableSet(obj2);
            if (!set.add(t)) {
                return false;
            }
        }
        setSize(size() + 1);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.data = null;
        setSize(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object element) {
        if (size() == 0) {
            return false;
        }
        if (size() == 1) {
            return Intrinsics.areEqual(this.data, element);
        }
        if (size() < 5) {
            Object obj = this.data;
            if (obj == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of org.jetbrains.kotlin.utils.SmartSet>");
            }
            return ArraysKt.contains((Object[]) obj, element);
        }
        Object obj2 = this.data;
        if (obj2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Set<T of org.jetbrains.kotlin.utils.SmartSet>");
        }
        return ((Set) obj2).contains(element);
    }

    /* compiled from: SmartSet.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/utils/SmartSet$SingletonIterator.class */
    private static final class SingletonIterator<T> implements Iterator<T>, KMutableIterator {
        private final T element;
        private boolean hasNext = true;

        public SingletonIterator(T t) {
            this.element = t;
        }

        @Override // java.util.Iterator
        public T next() {
            if (this.hasNext) {
                this.hasNext = false;
                return this.element;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.hasNext;
        }

        @Override // java.util.Iterator
        @NotNull
        public Void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* compiled from: SmartSet.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/utils/SmartSet$ArrayIterator.class */
    private static final class ArrayIterator<T> implements Iterator<T>, KMutableIterator {

        @NotNull
        private final Iterator<T> arrayIterator;

        public ArrayIterator(@NotNull T[] array) {
            Intrinsics.checkNotNullParameter(array, "array");
            this.arrayIterator = ArrayIteratorKt.iterator(array);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.arrayIterator.hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            return this.arrayIterator.next();
        }

        @Override // java.util.Iterator
        @NotNull
        public Void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
