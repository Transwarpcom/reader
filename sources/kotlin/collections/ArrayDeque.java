package kotlin.collections;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* compiled from: ArrayDeque.kt */
@SinceKotlin(version = "1.4")
@Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��L\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010��\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\b\u0007\u0018�� P*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001PB\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0007\b\u0016¢\u0006\u0002\u0010\u0006B\u0015\b\u0016\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028��0\b¢\u0006\u0002\u0010\tJ\u0015\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028��H\u0016¢\u0006\u0002\u0010\u0016J\u001d\u0010\u0013\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00028��H\u0016¢\u0006\u0002\u0010\u0019J\u001e\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028��0\bH\u0016J\u0016\u0010\u001a\u001a\u00020\u00142\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028��0\bH\u0016J\u0013\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00028��¢\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00028��¢\u0006\u0002\u0010\u001cJ\b\u0010\u001e\u001a\u00020\u0017H\u0016J\u0016\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028��H\u0096\u0002¢\u0006\u0002\u0010\u0016J\u001e\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028��0\bH\u0002J\u0010\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0004H\u0002J\u0010\u0010$\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0010\u0010%\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u0004H\u0002J\u001d\u0010'\u001a\u00020\u00142\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00020\u00140)H\u0082\bJ\u000b\u0010*\u001a\u00028��¢\u0006\u0002\u0010+J\r\u0010,\u001a\u0004\u0018\u00018��¢\u0006\u0002\u0010+J\u0016\u0010-\u001a\u00028��2\u0006\u0010\u0018\u001a\u00020\u0004H\u0096\u0002¢\u0006\u0002\u0010.J\u0010\u0010/\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0015\u00100\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00028��H\u0016¢\u0006\u0002\u00101J\u0016\u00102\u001a\u00028��2\u0006\u0010!\u001a\u00020\u0004H\u0083\b¢\u0006\u0002\u0010.J\u0011\u0010!\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0083\bJM\u00103\u001a\u00020\u00172>\u00104\u001a:\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(\u000e\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u001705H��¢\u0006\u0002\b8J\b\u00109\u001a\u00020\u0014H\u0016J\u000b\u0010:\u001a\u00028��¢\u0006\u0002\u0010+J\u0015\u0010;\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00028��H\u0016¢\u0006\u0002\u00101J\r\u0010<\u001a\u0004\u0018\u00018��¢\u0006\u0002\u0010+J\u0010\u0010=\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0010\u0010>\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0015\u0010?\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028��H\u0016¢\u0006\u0002\u0010\u0016J\u0016\u0010@\u001a\u00020\u00142\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028��0\bH\u0016J\u0015\u0010A\u001a\u00028��2\u0006\u0010\u0018\u001a\u00020\u0004H\u0016¢\u0006\u0002\u0010.J\u000b\u0010B\u001a\u00028��¢\u0006\u0002\u0010+J\r\u0010C\u001a\u0004\u0018\u00018��¢\u0006\u0002\u0010+J\u000b\u0010D\u001a\u00028��¢\u0006\u0002\u0010+J\r\u0010E\u001a\u0004\u0018\u00018��¢\u0006\u0002\u0010+J\u0016\u0010F\u001a\u00020\u00142\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028��0\bH\u0016J\u001e\u0010G\u001a\u00028��2\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00028��H\u0096\u0002¢\u0006\u0002\u0010HJ\u0017\u0010I\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bH��¢\u0006\u0004\bJ\u0010KJ)\u0010I\u001a\b\u0012\u0004\u0012\u0002HL0\u000b\"\u0004\b\u0001\u0010L2\f\u0010M\u001a\b\u0012\u0004\u0012\u0002HL0\u000bH��¢\u0006\u0004\bJ\u0010NJ\u0015\u0010O\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bH\u0016¢\u0006\u0002\u0010KJ'\u0010O\u001a\b\u0012\u0004\u0012\u0002HL0\u000b\"\u0004\b\u0001\u0010L2\f\u0010M\u001a\b\u0012\u0004\u0012\u0002HL0\u000bH\u0016¢\u0006\u0002\u0010NR\u0018\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n��R\u001e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004@RX\u0096\u000e¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012¨\u0006Q"}, d2 = {"Lkotlin/collections/ArrayDeque;", "E", "Lkotlin/collections/AbstractMutableList;", "initialCapacity", "", "(I)V", "()V", "elements", "", "(Ljava/util/Collection;)V", "elementData", "", "", "[Ljava/lang/Object;", "head", "<set-?>", "size", "getSize", "()I", BeanUtil.PREFIX_ADDER, "", "element", "(Ljava/lang/Object;)Z", "", "index", "(ILjava/lang/Object;)V", "addAll", "addFirst", "(Ljava/lang/Object;)V", "addLast", "clear", "contains", "copyCollectionElements", "internalIndex", "copyElements", "newCapacity", "decremented", "ensureCapacity", "minCapacity", "filterInPlace", "predicate", "Lkotlin/Function1;", "first", "()Ljava/lang/Object;", "firstOrNull", BeanUtil.PREFIX_GETTER_GET, "(I)Ljava/lang/Object;", "incremented", "indexOf", "(Ljava/lang/Object;)I", "internalGet", "internalStructure", "structure", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "internalStructure$kotlin_stdlib", "isEmpty", "last", "lastIndexOf", "lastOrNull", "negativeMod", "positiveMod", "remove", "removeAll", "removeAt", "removeFirst", "removeFirstOrNull", "removeLast", "removeLastOrNull", "retainAll", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "testToArray", "testToArray$kotlin_stdlib", "()[Ljava/lang/Object;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, BeanDefinitionParserDelegate.ARRAY_ELEMENT, "([Ljava/lang/Object;)[Ljava/lang/Object;", "toArray", "Companion", "kotlin-stdlib"})
@WasExperimental(markerClass = {ExperimentalStdlibApi.class})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/collections/ArrayDeque.class */
public final class ArrayDeque<E> extends AbstractMutableList<E> {
    private int head;
    private Object[] elementData;
    private int size;
    private static final int maxArraySize = 2147483639;
    private static final int defaultMinCapacity = 10;

    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final Object[] emptyElementData = new Object[0];

    @Override // kotlin.collections.AbstractMutableList
    public int getSize() {
        return this.size;
    }

    public ArrayDeque(int initialCapacity) {
        Object[] objArr;
        if (initialCapacity == 0) {
            objArr = emptyElementData;
        } else {
            if (initialCapacity <= 0) {
                throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
            }
            objArr = new Object[initialCapacity];
        }
        this.elementData = objArr;
    }

    public ArrayDeque() {
        this.elementData = emptyElementData;
    }

    public ArrayDeque(@NotNull Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Object[] array = elements.toArray(new Object[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        this.elementData = array;
        this.size = this.elementData.length;
        if (this.elementData.length == 0) {
            this.elementData = emptyElementData;
        }
    }

    private final void ensureCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new IllegalStateException("Deque is too big.");
        }
        if (minCapacity <= this.elementData.length) {
            return;
        }
        if (this.elementData == emptyElementData) {
            this.elementData = new Object[RangesKt.coerceAtLeast(minCapacity, 10)];
        } else {
            int newCapacity = Companion.newCapacity$kotlin_stdlib(this.elementData.length, minCapacity);
            copyElements(newCapacity);
        }
    }

    private final void copyElements(int newCapacity) {
        Object[] newElements = new Object[newCapacity];
        ArraysKt.copyInto(this.elementData, newElements, 0, this.head, this.elementData.length);
        ArraysKt.copyInto(this.elementData, newElements, this.elementData.length - this.head, 0, this.head);
        this.head = 0;
        this.elementData = newElements;
    }

    @InlineOnly
    private final E internalGet(int i) {
        return (E) this.elementData[i];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int positiveMod(int index) {
        return index >= this.elementData.length ? index - this.elementData.length : index;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int negativeMod(int index) {
        return index < 0 ? index + this.elementData.length : index;
    }

    @InlineOnly
    private final int internalIndex(int index) {
        return positiveMod(this.head + index);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int incremented(int index) {
        if (index == ArraysKt.getLastIndex(this.elementData)) {
            return 0;
        }
        return index + 1;
    }

    private final int decremented(int index) {
        return index == 0 ? ArraysKt.getLastIndex(this.elementData) : index - 1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        return size() == 0;
    }

    public final E first() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        return (E) this.elementData[this.head];
    }

    @Nullable
    public final E firstOrNull() {
        if (isEmpty()) {
            return null;
        }
        return (E) this.elementData[this.head];
    }

    public final E last() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        return (E) this.elementData[positiveMod(this.head + CollectionsKt.getLastIndex(this))];
    }

    @Nullable
    public final E lastOrNull() {
        if (isEmpty()) {
            return null;
        }
        return (E) this.elementData[positiveMod(this.head + CollectionsKt.getLastIndex(this))];
    }

    public final void addFirst(E e) {
        ensureCapacity(size() + 1);
        this.head = decremented(this.head);
        this.elementData[this.head] = e;
        this.size = size() + 1;
    }

    public final void addLast(E e) {
        ensureCapacity(size() + 1);
        this.elementData[positiveMod(this.head + size())] = e;
        this.size = size() + 1;
    }

    public final E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        E e = (E) this.elementData[this.head];
        this.elementData[this.head] = null;
        this.head = incremented(this.head);
        this.size = size() - 1;
        return e;
    }

    @Nullable
    public final E removeFirstOrNull() {
        if (isEmpty()) {
            return null;
        }
        return removeFirst();
    }

    public final E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        int iPositiveMod = positiveMod(this.head + CollectionsKt.getLastIndex(this));
        E e = (E) this.elementData[iPositiveMod];
        this.elementData[iPositiveMod] = null;
        this.size = size() - 1;
        return e;
    }

    @Nullable
    public final E removeLastOrNull() {
        if (isEmpty()) {
            return null;
        }
        return removeLast();
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public void add(int index, E e) {
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(index, size());
        if (index == size()) {
            addLast(e);
            return;
        }
        if (index == 0) {
            addFirst(e);
            return;
        }
        ensureCapacity(size() + 1);
        int internalIndex = positiveMod(this.head + index);
        if (index >= ((size() + 1) >> 1)) {
            int tail = positiveMod(this.head + size());
            if (internalIndex < tail) {
                ArraysKt.copyInto(this.elementData, this.elementData, internalIndex + 1, internalIndex, tail);
            } else {
                ArraysKt.copyInto(this.elementData, this.elementData, 1, 0, tail);
                this.elementData[0] = this.elementData[this.elementData.length - 1];
                ArraysKt.copyInto(this.elementData, this.elementData, internalIndex + 1, internalIndex, this.elementData.length - 1);
            }
            this.elementData[internalIndex] = e;
        } else {
            int decrementedInternalIndex = decremented(internalIndex);
            int decrementedHead = decremented(this.head);
            if (decrementedInternalIndex >= this.head) {
                this.elementData[decrementedHead] = this.elementData[this.head];
                ArraysKt.copyInto(this.elementData, this.elementData, this.head, this.head + 1, decrementedInternalIndex + 1);
            } else {
                ArraysKt.copyInto(this.elementData, this.elementData, this.head - 1, this.head, this.elementData.length);
                this.elementData[this.elementData.length - 1] = this.elementData[0];
                ArraysKt.copyInto(this.elementData, this.elementData, 0, 1, decrementedInternalIndex + 1);
            }
            this.elementData[decrementedInternalIndex] = e;
            this.head = decrementedHead;
        }
        this.size = size() + 1;
    }

    private final void copyCollectionElements(int internalIndex, Collection<? extends E> collection) {
        Iterator iterator = collection.iterator();
        int length = this.elementData.length;
        for (int index = internalIndex; index < length && iterator.hasNext(); index++) {
            this.elementData[index] = iterator.next();
        }
        int i = this.head;
        for (int index2 = 0; index2 < i && iterator.hasNext(); index2++) {
            this.elementData[index2] = iterator.next();
        }
        this.size = size() + collection.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(@NotNull Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements.isEmpty()) {
            return false;
        }
        ensureCapacity(size() + elements.size());
        copyCollectionElements(positiveMod(this.head + size()), elements);
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int index, @NotNull Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(index, size());
        if (elements.isEmpty()) {
            return false;
        }
        if (index == size()) {
            return addAll(elements);
        }
        ensureCapacity(size() + elements.size());
        int tail = positiveMod(this.head + size());
        int internalIndex = positiveMod(this.head + index);
        int elementsSize = elements.size();
        if (index < ((size() + 1) >> 1)) {
            int shiftedHead = this.head - elementsSize;
            if (internalIndex >= this.head) {
                if (shiftedHead >= 0) {
                    ArraysKt.copyInto(this.elementData, this.elementData, shiftedHead, this.head, internalIndex);
                } else {
                    shiftedHead += this.elementData.length;
                    int elementsToShift = internalIndex - this.head;
                    int shiftToBack = this.elementData.length - shiftedHead;
                    if (shiftToBack >= elementsToShift) {
                        ArraysKt.copyInto(this.elementData, this.elementData, shiftedHead, this.head, internalIndex);
                    } else {
                        ArraysKt.copyInto(this.elementData, this.elementData, shiftedHead, this.head, this.head + shiftToBack);
                        ArraysKt.copyInto(this.elementData, this.elementData, 0, this.head + shiftToBack, internalIndex);
                    }
                }
            } else {
                ArraysKt.copyInto(this.elementData, this.elementData, shiftedHead, this.head, this.elementData.length);
                if (elementsSize >= internalIndex) {
                    ArraysKt.copyInto(this.elementData, this.elementData, this.elementData.length - elementsSize, 0, internalIndex);
                } else {
                    ArraysKt.copyInto(this.elementData, this.elementData, this.elementData.length - elementsSize, 0, elementsSize);
                    ArraysKt.copyInto(this.elementData, this.elementData, 0, elementsSize, internalIndex);
                }
            }
            this.head = shiftedHead;
            copyCollectionElements(negativeMod(internalIndex - elementsSize), elements);
            return true;
        }
        int shiftedInternalIndex = internalIndex + elementsSize;
        if (internalIndex >= tail) {
            ArraysKt.copyInto(this.elementData, this.elementData, elementsSize, 0, tail);
            if (shiftedInternalIndex >= this.elementData.length) {
                ArraysKt.copyInto(this.elementData, this.elementData, shiftedInternalIndex - this.elementData.length, internalIndex, this.elementData.length);
            } else {
                ArraysKt.copyInto(this.elementData, this.elementData, 0, this.elementData.length - elementsSize, this.elementData.length);
                ArraysKt.copyInto(this.elementData, this.elementData, shiftedInternalIndex, internalIndex, this.elementData.length - elementsSize);
            }
        } else if (tail + elementsSize <= this.elementData.length) {
            ArraysKt.copyInto(this.elementData, this.elementData, shiftedInternalIndex, internalIndex, tail);
        } else if (shiftedInternalIndex >= this.elementData.length) {
            ArraysKt.copyInto(this.elementData, this.elementData, shiftedInternalIndex - this.elementData.length, internalIndex, tail);
        } else {
            int shiftToFront = (tail + elementsSize) - this.elementData.length;
            ArraysKt.copyInto(this.elementData, this.elementData, 0, tail - shiftToFront, tail);
            ArraysKt.copyInto(this.elementData, this.elementData, shiftedInternalIndex, internalIndex, tail - shiftToFront);
        }
        copyCollectionElements(internalIndex, elements);
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, size());
        return (E) this.elementData[positiveMod(this.head + i)];
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public E set(int i, E e) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, size());
        int iPositiveMod = positiveMod(this.head + i);
        E e2 = (E) this.elementData[iPositiveMod];
        this.elementData[iPositiveMod] = e;
        return e2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object element) {
        return indexOf(element) != -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object element) {
        int tail = positiveMod(this.head + size());
        if (this.head >= tail) {
            if (this.head >= tail) {
                int length = this.elementData.length;
                for (int index = this.head; index < length; index++) {
                    if (Intrinsics.areEqual(element, this.elementData[index])) {
                        return index - this.head;
                    }
                }
                for (int index2 = 0; index2 < tail; index2++) {
                    if (Intrinsics.areEqual(element, this.elementData[index2])) {
                        return (index2 + this.elementData.length) - this.head;
                    }
                }
                return -1;
            }
            return -1;
        }
        for (int index3 = this.head; index3 < tail; index3++) {
            if (Intrinsics.areEqual(element, this.elementData[index3])) {
                return index3 - this.head;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object element) {
        int tail = positiveMod(this.head + size());
        if (this.head >= tail) {
            if (this.head > tail) {
                for (int index = tail - 1; index >= 0; index--) {
                    if (Intrinsics.areEqual(element, this.elementData[index])) {
                        return (index + this.elementData.length) - this.head;
                    }
                }
                int index2 = ArraysKt.getLastIndex(this.elementData);
                int i = this.head;
                if (index2 >= i) {
                    while (!Intrinsics.areEqual(element, this.elementData[index2])) {
                        if (index2 == i) {
                            return -1;
                        }
                        index2--;
                    }
                    return index2 - this.head;
                }
                return -1;
            }
            return -1;
        }
        int index3 = tail - 1;
        int i2 = this.head;
        if (index3 >= i2) {
            while (!Intrinsics.areEqual(element, this.elementData[index3])) {
                if (index3 == i2) {
                    return -1;
                }
                index3--;
            }
            return index3 - this.head;
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object element) {
        int index = indexOf(element);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override // kotlin.collections.AbstractMutableList
    public E removeAt(int i) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, size());
        if (i == CollectionsKt.getLastIndex(this)) {
            return removeLast();
        }
        if (i != 0) {
            int iPositiveMod = positiveMod(this.head + i);
            E e = (E) this.elementData[iPositiveMod];
            if (i >= (size() >> 1)) {
                int iPositiveMod2 = positiveMod(this.head + CollectionsKt.getLastIndex(this));
                if (iPositiveMod <= iPositiveMod2) {
                    ArraysKt.copyInto(this.elementData, this.elementData, iPositiveMod, iPositiveMod + 1, iPositiveMod2 + 1);
                } else {
                    ArraysKt.copyInto(this.elementData, this.elementData, iPositiveMod, iPositiveMod + 1, this.elementData.length);
                    this.elementData[this.elementData.length - 1] = this.elementData[0];
                    ArraysKt.copyInto(this.elementData, this.elementData, 0, 1, iPositiveMod2 + 1);
                }
                this.elementData[iPositiveMod2] = null;
            } else {
                if (iPositiveMod >= this.head) {
                    ArraysKt.copyInto(this.elementData, this.elementData, this.head + 1, this.head, iPositiveMod);
                } else {
                    ArraysKt.copyInto(this.elementData, this.elementData, 1, 0, iPositiveMod);
                    this.elementData[0] = this.elementData[this.elementData.length - 1];
                    ArraysKt.copyInto(this.elementData, this.elementData, this.head + 1, this.head, this.elementData.length - 1);
                }
                this.elementData[this.head] = null;
                this.head = incremented(this.head);
            }
            this.size = size() - 1;
            return e;
        }
        return removeFirst();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(@NotNull Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (!isEmpty()) {
            if (!(this.elementData.length == 0)) {
                int tail$iv = positiveMod(this.head + size());
                int newTail$iv = this.head;
                boolean modified$iv = false;
                if (this.head < tail$iv) {
                    for (int index$iv = this.head; index$iv < tail$iv; index$iv++) {
                        Object element$iv = this.elementData[index$iv];
                        if (!elements.contains(element$iv)) {
                            int i = newTail$iv;
                            newTail$iv++;
                            this.elementData[i] = element$iv;
                        } else {
                            modified$iv = true;
                        }
                    }
                    ArraysKt.fill(this.elementData, (Object) null, newTail$iv, tail$iv);
                } else {
                    int length = this.elementData.length;
                    for (int index$iv2 = this.head; index$iv2 < length; index$iv2++) {
                        Object element$iv2 = this.elementData[index$iv2];
                        this.elementData[index$iv2] = null;
                        if (!elements.contains(element$iv2)) {
                            int i2 = newTail$iv;
                            newTail$iv++;
                            this.elementData[i2] = element$iv2;
                        } else {
                            modified$iv = true;
                        }
                    }
                    newTail$iv = positiveMod(newTail$iv);
                    for (int index$iv3 = 0; index$iv3 < tail$iv; index$iv3++) {
                        Object element$iv3 = this.elementData[index$iv3];
                        this.elementData[index$iv3] = null;
                        if (!elements.contains(element$iv3)) {
                            this.elementData[newTail$iv] = element$iv3;
                            newTail$iv = incremented(newTail$iv);
                        } else {
                            modified$iv = true;
                        }
                    }
                }
                if (modified$iv) {
                    this.size = negativeMod(newTail$iv - this.head);
                }
                return modified$iv;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(@NotNull Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (!isEmpty()) {
            if (!(this.elementData.length == 0)) {
                int tail$iv = positiveMod(this.head + size());
                int newTail$iv = this.head;
                boolean modified$iv = false;
                if (this.head < tail$iv) {
                    for (int index$iv = this.head; index$iv < tail$iv; index$iv++) {
                        Object element$iv = this.elementData[index$iv];
                        if (elements.contains(element$iv)) {
                            int i = newTail$iv;
                            newTail$iv++;
                            this.elementData[i] = element$iv;
                        } else {
                            modified$iv = true;
                        }
                    }
                    ArraysKt.fill(this.elementData, (Object) null, newTail$iv, tail$iv);
                } else {
                    int length = this.elementData.length;
                    for (int index$iv2 = this.head; index$iv2 < length; index$iv2++) {
                        Object element$iv2 = this.elementData[index$iv2];
                        this.elementData[index$iv2] = null;
                        if (elements.contains(element$iv2)) {
                            int i2 = newTail$iv;
                            newTail$iv++;
                            this.elementData[i2] = element$iv2;
                        } else {
                            modified$iv = true;
                        }
                    }
                    newTail$iv = positiveMod(newTail$iv);
                    for (int index$iv3 = 0; index$iv3 < tail$iv; index$iv3++) {
                        Object element$iv3 = this.elementData[index$iv3];
                        this.elementData[index$iv3] = null;
                        if (elements.contains(element$iv3)) {
                            this.elementData[newTail$iv] = element$iv3;
                            newTail$iv = incremented(newTail$iv);
                        } else {
                            modified$iv = true;
                        }
                    }
                }
                if (modified$iv) {
                    this.size = negativeMod(newTail$iv - this.head);
                }
                return modified$iv;
            }
        }
        return false;
    }

    private final boolean filterInPlace(Function1<? super E, Boolean> function1) {
        if (!isEmpty()) {
            if (!(this.elementData.length == 0)) {
                int tail = positiveMod(this.head + size());
                int newTail = this.head;
                boolean modified = false;
                if (this.head < tail) {
                    for (int index = this.head; index < tail; index++) {
                        Object element = this.elementData[index];
                        if (function1.invoke(element).booleanValue()) {
                            int i = newTail;
                            newTail++;
                            this.elementData[i] = element;
                        } else {
                            modified = true;
                        }
                    }
                    ArraysKt.fill(this.elementData, (Object) null, newTail, tail);
                } else {
                    int length = this.elementData.length;
                    for (int index2 = this.head; index2 < length; index2++) {
                        Object element2 = this.elementData[index2];
                        this.elementData[index2] = null;
                        if (function1.invoke(element2).booleanValue()) {
                            int i2 = newTail;
                            newTail++;
                            this.elementData[i2] = element2;
                        } else {
                            modified = true;
                        }
                    }
                    newTail = positiveMod(newTail);
                    for (int index3 = 0; index3 < tail; index3++) {
                        Object element3 = this.elementData[index3];
                        this.elementData[index3] = null;
                        if (function1.invoke(element3).booleanValue()) {
                            this.elementData[newTail] = element3;
                            newTail = incremented(newTail);
                        } else {
                            modified = true;
                        }
                    }
                }
                if (modified) {
                    this.size = negativeMod(newTail - this.head);
                }
                return modified;
            }
            return false;
        }
        return false;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        int tail = positiveMod(this.head + size());
        if (this.head < tail) {
            ArraysKt.fill(this.elementData, (Object) null, this.head, tail);
        } else {
            if (!isEmpty()) {
                ArraysKt.fill(this.elementData, (Object) null, this.head, this.elementData.length);
                ArraysKt.fill(this.elementData, (Object) null, 0, tail);
            }
        }
        this.head = 0;
        this.size = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    @NotNull
    public <T> T[] toArray(@NotNull T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        Object[] objArrArrayOfNulls = array.length >= size() ? array : ArraysKt.arrayOfNulls(array, size());
        if (objArrArrayOfNulls == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        }
        T[] tArr = (T[]) objArrArrayOfNulls;
        int iPositiveMod = positiveMod(this.head + size());
        if (this.head < iPositiveMod) {
            ArraysKt.copyInto$default(this.elementData, tArr, 0, this.head, iPositiveMod, 2, (Object) null);
        } else {
            if (!isEmpty()) {
                ArraysKt.copyInto(this.elementData, tArr, 0, this.head, this.elementData.length);
                ArraysKt.copyInto(this.elementData, tArr, this.elementData.length - this.head, 0, iPositiveMod);
            }
        }
        if (tArr.length > size()) {
            tArr[size()] = null;
        }
        if (tArr == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        return tArr;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    @NotNull
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }

    @NotNull
    public final <T> T[] testToArray$kotlin_stdlib(@NotNull T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        return (T[]) toArray(array);
    }

    @NotNull
    public final Object[] testToArray$kotlin_stdlib() {
        return toArray();
    }

    /* compiled from: ArrayDeque.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u0011\n\u0002\b\u0007\b\u0080\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H��¢\u0006\u0002\b\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n��R\u0018\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n��¨\u0006\r"}, d2 = {"Lkotlin/collections/ArrayDeque$Companion;", "", "()V", "defaultMinCapacity", "", "emptyElementData", "", "[Ljava/lang/Object;", "maxArraySize", "newCapacity", "oldCapacity", "minCapacity", "newCapacity$kotlin_stdlib", "kotlin-stdlib"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/collections/ArrayDeque$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        public final int newCapacity$kotlin_stdlib(int oldCapacity, int minCapacity) {
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity - minCapacity < 0) {
                newCapacity = minCapacity;
            }
            if (newCapacity - ArrayDeque.maxArraySize > 0) {
                newCapacity = minCapacity > ArrayDeque.maxArraySize ? Integer.MAX_VALUE : ArrayDeque.maxArraySize;
            }
            return newCapacity;
        }
    }

    public final void internalStructure$kotlin_stdlib(@NotNull Function2<? super Integer, ? super Object[], Unit> structure) {
        Intrinsics.checkNotNullParameter(structure, "structure");
        int tail = positiveMod(this.head + size());
        int head = (isEmpty() || this.head < tail) ? this.head : this.head - this.elementData.length;
        structure.invoke(Integer.valueOf(head), toArray());
    }
}
