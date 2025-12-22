package kotlin.reflect.jvm.internal.impl.utils;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import org.jetbrains.annotations.NotNull;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/utils/SmartList.class */
public class SmartList<E> extends AbstractList<E> implements RandomAccess {
    private int mySize;
    private Object myElem;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 0:
            case 1:
            case 4:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 4:
            default:
                i2 = 3;
                break;
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            case 1:
            default:
                objArr[0] = "elements";
                break;
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/utils/SmartList";
                break;
            case 4:
                objArr[0] = "a";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 4:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/utils/SmartList";
                break;
            case 2:
            case 3:
                objArr[1] = "iterator";
                break;
            case 5:
            case 6:
            case 7:
                objArr[1] = "toArray";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            default:
                objArr[2] = Constants.CONSTRUCTOR_NAME;
                break;
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
                break;
            case 4:
                objArr[2] = "toArray";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 0:
            case 1:
            case 4:
            default:
                throw new IllegalArgumentException(str2);
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
                throw new IllegalStateException(str2);
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i) {
        if (i < 0 || i >= this.mySize) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + this.mySize);
        }
        if (this.mySize == 1) {
            return (E) this.myElem;
        }
        return (E) ((Object[]) this.myElem)[i];
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e) {
        if (this.mySize == 0) {
            this.myElem = e;
        } else if (this.mySize == 1) {
            this.myElem = new Object[]{this.myElem, e};
        } else {
            Object[] array = (Object[]) this.myElem;
            int oldCapacity = array.length;
            if (this.mySize >= oldCapacity) {
                int newCapacity = ((oldCapacity * 3) / 2) + 1;
                int minCapacity = this.mySize + 1;
                if (newCapacity < minCapacity) {
                    newCapacity = minCapacity;
                }
                Object[] objArr = new Object[newCapacity];
                array = objArr;
                this.myElem = objArr;
                System.arraycopy(array, 0, array, 0, oldCapacity);
            }
            array[this.mySize] = e;
        }
        this.mySize++;
        this.modCount++;
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int index, E e) {
        if (index < 0 || index > this.mySize) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.mySize);
        }
        if (this.mySize == 0) {
            this.myElem = e;
        } else if (this.mySize == 1 && index == 0) {
            this.myElem = new Object[]{e, this.myElem};
        } else {
            Object[] array = new Object[this.mySize + 1];
            if (this.mySize == 1) {
                array[0] = this.myElem;
            } else {
                Object[] oldArray = (Object[]) this.myElem;
                System.arraycopy(oldArray, 0, array, 0, index);
                System.arraycopy(oldArray, index, array, index + 1, this.mySize - index);
            }
            array[index] = e;
            this.myElem = array;
        }
        this.mySize++;
        this.modCount++;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.mySize;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        this.myElem = null;
        this.mySize = 0;
        this.modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public E set(int i, E e) {
        Object obj;
        if (i < 0 || i >= this.mySize) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + this.mySize);
        }
        if (this.mySize == 1) {
            obj = this.myElem;
            this.myElem = e;
        } else {
            Object[] objArr = (Object[]) this.myElem;
            obj = objArr[i];
            objArr[i] = e;
        }
        return (E) obj;
    }

    @Override // java.util.AbstractList, java.util.List
    public E remove(int i) {
        Object obj;
        if (i < 0 || i >= this.mySize) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + this.mySize);
        }
        if (this.mySize == 1) {
            obj = this.myElem;
            this.myElem = null;
        } else {
            Object[] objArr = (Object[]) this.myElem;
            obj = objArr[i];
            if (this.mySize == 2) {
                this.myElem = objArr[1 - i];
            } else {
                int i2 = (this.mySize - i) - 1;
                if (i2 > 0) {
                    System.arraycopy(objArr, i + 1, objArr, i, i2);
                }
                objArr[this.mySize - 1] = null;
            }
        }
        this.mySize--;
        this.modCount++;
        return (E) obj;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/utils/SmartList$EmptyIterator.class */
    private static class EmptyIterator<T> implements Iterator<T> {
        private static final EmptyIterator INSTANCE = new EmptyIterator();

        private EmptyIterator() {
        }

        public static <T> EmptyIterator<T> getInstance() {
            return INSTANCE;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public T next() {
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new IllegalStateException();
        }
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    @NotNull
    public Iterator<E> iterator() {
        if (this.mySize == 0) {
            EmptyIterator emptyIterator = EmptyIterator.getInstance();
            if (emptyIterator == null) {
                $$$reportNull$$$0(2);
            }
            return emptyIterator;
        }
        if (this.mySize == 1) {
            return new SingletonIterator();
        }
        Iterator<E> it = super.iterator();
        if (it == null) {
            $$$reportNull$$$0(3);
        }
        return it;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/utils/SmartList$SingletonIteratorBase.class */
    private static abstract class SingletonIteratorBase<T> implements Iterator<T> {
        private boolean myVisited;

        protected abstract void checkCoModification();

        protected abstract T getElement();

        private SingletonIteratorBase() {
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return !this.myVisited;
        }

        @Override // java.util.Iterator
        public final T next() {
            if (this.myVisited) {
                throw new NoSuchElementException();
            }
            this.myVisited = true;
            checkCoModification();
            return getElement();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/utils/SmartList$SingletonIterator.class */
    private class SingletonIterator extends SingletonIteratorBase<E> {
        private final int myInitialModCount;

        public SingletonIterator() {
            super();
            this.myInitialModCount = SmartList.this.modCount;
        }

        @Override // kotlin.reflect.jvm.internal.impl.utils.SmartList.SingletonIteratorBase
        protected E getElement() {
            return (E) SmartList.this.myElem;
        }

        @Override // kotlin.reflect.jvm.internal.impl.utils.SmartList.SingletonIteratorBase
        protected void checkCoModification() {
            if (SmartList.this.modCount != this.myInitialModCount) {
                throw new ConcurrentModificationException("ModCount: " + SmartList.this.modCount + "; expected: " + this.myInitialModCount);
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            checkCoModification();
            SmartList.this.clear();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    @NotNull
    public <T> T[] toArray(@NotNull T[] tArr) {
        if (tArr == 0) {
            $$$reportNull$$$0(4);
        }
        int length = tArr.length;
        if (this.mySize == 1) {
            if (length != 0) {
                tArr[0] = this.myElem;
            } else {
                T[] tArr2 = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), 1));
                tArr2[0] = this.myElem;
                if (tArr2 == 0) {
                    $$$reportNull$$$0(5);
                }
                return tArr2;
            }
        } else {
            if (length < this.mySize) {
                T[] tArr3 = (T[]) Arrays.copyOf((Object[]) this.myElem, this.mySize, tArr.getClass());
                if (tArr3 == null) {
                    $$$reportNull$$$0(6);
                }
                return tArr3;
            }
            if (this.mySize != 0) {
                System.arraycopy(this.myElem, 0, tArr, 0, this.mySize);
            }
        }
        if (length > this.mySize) {
            tArr[this.mySize] = 0;
        }
        if (tArr == 0) {
            $$$reportNull$$$0(7);
        }
        return tArr;
    }
}
