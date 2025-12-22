package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.ObjIntConsumer;

@GwtCompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/Multiset.class */
public interface Multiset<E> extends Collection<E> {

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/Multiset$Entry.class */
    public interface Entry<E> {
        E getElement();

        int getCount();

        boolean equals(Object obj);

        int hashCode();

        String toString();
    }

    int size();

    int count(@CompatibleWith("E") Object obj);

    @CanIgnoreReturnValue
    int add(E e, int i);

    @CanIgnoreReturnValue
    boolean add(E e);

    @CanIgnoreReturnValue
    int remove(@CompatibleWith("E") Object obj, int i);

    @CanIgnoreReturnValue
    boolean remove(Object obj);

    @CanIgnoreReturnValue
    int setCount(E e, int i);

    @CanIgnoreReturnValue
    boolean setCount(E e, int i, int i2);

    Set<E> elementSet();

    Set<Entry<E>> entrySet();

    boolean equals(Object obj);

    int hashCode();

    String toString();

    Iterator<E> iterator();

    boolean contains(Object obj);

    @Override // java.util.Collection
    boolean containsAll(Collection<?> collection);

    @CanIgnoreReturnValue
    boolean removeAll(Collection<?> collection);

    @CanIgnoreReturnValue
    boolean retainAll(Collection<?> collection);

    @Beta
    default void forEachEntry(ObjIntConsumer<? super E> action) {
        Preconditions.checkNotNull(action);
        entrySet().forEach(entry -> {
            action.accept(entry.getElement(), entry.getCount());
        });
    }

    default void forEach(Consumer<? super E> action) {
        Preconditions.checkNotNull(action);
        entrySet().forEach(entry -> {
            Object element = entry.getElement();
            int count = entry.getCount();
            for (int i = 0; i < count; i++) {
                action.accept(element);
            }
        });
    }

    default Spliterator<E> spliterator() {
        return Multisets.spliteratorImpl(this);
    }
}
