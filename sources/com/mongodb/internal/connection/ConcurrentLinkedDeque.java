package com.mongodb.internal.connection;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ConcurrentLinkedDeque.class */
public class ConcurrentLinkedDeque<E> extends AbstractCollection<E> implements Deque<E>, Serializable {
    private static final long serialVersionUID = 876323262645176354L;
    private final Node<E> header;
    private final Node<E> trailer;

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ConcurrentLinkedDeque$RemovalReportingIterator.class */
    public interface RemovalReportingIterator<E> extends Iterator<E> {
        boolean reportingRemove();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ConcurrentLinkedDeque$Node.class */
    static final class Node<E> extends AtomicReference<Node<E>> {
        private volatile Node<E> prev;
        final E element;
        private static final long serialVersionUID = 876323262645176354L;

        Node(E element, Node<E> next, Node<E> prev) {
            super(next);
            this.prev = prev;
            this.element = element;
        }

        Node(Node<E> next) {
            super(next);
            this.prev = this;
            this.element = null;
        }

        private Node<E> getNext() {
            return get();
        }

        void setNext(Node<E> n) {
            set(n);
        }

        private boolean casNext(Node<E> cmp, Node<E> val) {
            return compareAndSet(cmp, val);
        }

        private Node<E> getPrev() {
            return this.prev;
        }

        void setPrev(Node<E> b) {
            this.prev = b;
        }

        boolean isSpecial() {
            return this.element == null;
        }

        boolean isTrailer() {
            return getNext() == null;
        }

        boolean isHeader() {
            return getPrev() == null;
        }

        boolean isMarker() {
            return getPrev() == this;
        }

        boolean isDeleted() {
            Node<E> f = getNext();
            return f != null && f.isMarker();
        }

        private Node<E> nextNonmarker() {
            Node<E> f = getNext();
            return (f == null || !f.isMarker()) ? f : f.getNext();
        }

        Node<E> successor() {
            Node<E> nodeNextNonmarker = nextNonmarker();
            while (true) {
                Node<E> f = nodeNextNonmarker;
                if (f == null) {
                    return null;
                }
                if (!f.isDeleted()) {
                    if (f.getPrev() != this && !isDeleted()) {
                        f.setPrev(this);
                    }
                    return f;
                }
                Node<E> s = f.nextNonmarker();
                if (f == getNext()) {
                    casNext(f, s);
                }
                nodeNextNonmarker = s;
            }
        }

        private Node<E> findPredecessorOf(Node<E> target) {
            Node<E> node = this;
            while (true) {
                Node<E> n = node;
                Node<E> f = n.successor();
                if (f == target) {
                    return n;
                }
                if (f == null) {
                    return null;
                }
                node = f;
            }
        }

        Node<E> predecessor() {
            Node<E> p;
            Node<E> node = this;
            while (true) {
                Node<E> n = node;
                Node<E> b = n.getPrev();
                if (b == null) {
                    return n.findPredecessorOf(this);
                }
                Node<E> s = b.getNext();
                if (s == this) {
                    return b;
                }
                if ((s == null || !s.isMarker()) && (p = b.findPredecessorOf(this)) != null) {
                    return p;
                }
                node = b;
            }
        }

        Node<E> forward() {
            Node<E> f = successor();
            if (f == null || f.isSpecial()) {
                return null;
            }
            return f;
        }

        Node<E> back() {
            Node<E> f = predecessor();
            if (f == null || f.isSpecial()) {
                return null;
            }
            return f;
        }

        Node<E> append(E element) {
            Node<E> f;
            Node<E> x;
            do {
                f = getNext();
                if (f == null || f.isMarker()) {
                    return null;
                }
                x = new Node<>(element, f, this);
            } while (!casNext(f, x));
            f.setPrev(x);
            return x;
        }

        Node<E> prepend(E element) {
            Node<E> b;
            Node<E> x;
            do {
                b = predecessor();
                if (b == null) {
                    return null;
                }
                x = new Node<>(element, this, b);
            } while (!b.casNext(this, x));
            setPrev(x);
            return x;
        }

        boolean delete() {
            Node<E> b = getPrev();
            Node<E> f = getNext();
            if (b != null && f != null && !f.isMarker() && casNext(f, new Node<>(f))) {
                if (b.casNext(this, f)) {
                    f.setPrev(b);
                    return true;
                }
                return true;
            }
            return false;
        }

        Node<E> replace(E newElement) {
            Node<E> b;
            Node<E> f;
            Node<E> x;
            do {
                b = getPrev();
                f = getNext();
                if (b == null || f == null || f.isMarker()) {
                    return null;
                }
                x = new Node<>(newElement, f, b);
            } while (!casNext(f, new Node<>(x)));
            b.successor();
            x.successor();
            return x;
        }
    }

    private static boolean usable(Node<?> n) {
        return (n == null || n.isSpecial()) ? false : true;
    }

    private static void checkNotNull(Object v) {
        if (v == null) {
            throw new NullPointerException();
        }
    }

    private E screenNullResult(E v) {
        if (v == null) {
            throw new NoSuchElementException();
        }
        return v;
    }

    private ArrayList<E> toArrayList() {
        ArrayList<E> c = new ArrayList<>();
        Node<E> nodeForward = this.header.forward();
        while (true) {
            Node<E> n = nodeForward;
            if (n != null) {
                c.add(n.element);
                nodeForward = n.forward();
            } else {
                return c;
            }
        }
    }

    public ConcurrentLinkedDeque() {
        Node<E> h = new Node<>(null, null, null);
        Node<E> t = new Node<>(null, null, h);
        h.setNext(t);
        this.header = h;
        this.trailer = t;
    }

    public ConcurrentLinkedDeque(Collection<? extends E> c) {
        this();
        addAll(c);
    }

    @Override // java.util.Deque
    public void addFirst(E e) {
        checkNotNull(e);
        while (this.header.append(e) == null) {
        }
    }

    @Override // java.util.Deque
    public void addLast(E e) {
        checkNotNull(e);
        while (this.trailer.prepend(e) == null) {
        }
    }

    @Override // java.util.Deque
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    @Override // java.util.Deque
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    @Override // java.util.Deque
    public E peekFirst() {
        Node<E> n = this.header.successor();
        if (n == null) {
            return null;
        }
        return n.element;
    }

    @Override // java.util.Deque
    public E peekLast() {
        Node<E> n = this.trailer.predecessor();
        if (n == null) {
            return null;
        }
        return n.element;
    }

    @Override // java.util.Deque
    public E getFirst() {
        return screenNullResult(peekFirst());
    }

    @Override // java.util.Deque
    public E getLast() {
        return screenNullResult(peekLast());
    }

    @Override // java.util.Deque
    public E pollFirst() {
        Node<E> n;
        do {
            n = this.header.successor();
            if (!usable(n)) {
                return null;
            }
        } while (!n.delete());
        return n.element;
    }

    @Override // java.util.Deque
    public E pollLast() {
        Node<E> n;
        do {
            n = this.trailer.predecessor();
            if (!usable(n)) {
                return null;
            }
        } while (!n.delete());
        return n.element;
    }

    @Override // java.util.Deque
    public E removeFirst() {
        return screenNullResult(pollFirst());
    }

    @Override // java.util.Deque
    public E removeLast() {
        return screenNullResult(pollLast());
    }

    @Override // java.util.Deque, java.util.Queue
    public boolean offer(E e) {
        return offerLast(e);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Deque, java.util.Queue
    public boolean add(E e) {
        return offerLast(e);
    }

    @Override // java.util.Deque, java.util.Queue
    public E poll() {
        return pollFirst();
    }

    @Override // java.util.Deque, java.util.Queue
    public E remove() {
        return removeFirst();
    }

    @Override // java.util.Deque, java.util.Queue
    public E peek() {
        return peekFirst();
    }

    @Override // java.util.Deque, java.util.Queue
    public E element() {
        return getFirst();
    }

    @Override // java.util.Deque
    public void push(E e) {
        addFirst(e);
    }

    @Override // java.util.Deque
    public E pop() {
        return removeFirst();
    }

    @Override // java.util.Deque
    public boolean removeFirstOccurrence(Object o) {
        Node<E> n;
        checkNotNull(o);
        do {
            Node<E> nodeForward = this.header.forward();
            while (true) {
                n = nodeForward;
                if (n == null) {
                    return false;
                }
                if (o.equals(n.element)) {
                    break;
                }
                nodeForward = n.forward();
            }
        } while (!n.delete());
        return true;
    }

    @Override // java.util.Deque
    public boolean removeLastOccurrence(Object o) {
        checkNotNull(o);
        while (true) {
            Node<E> node = this.trailer;
            while (true) {
                Node<E> s = node;
                Node<E> n = s.back();
                if (s.isDeleted() || !(n == null || n.successor() == s)) {
                    break;
                }
                if (n == null) {
                    return false;
                }
                if (o.equals(n.element)) {
                    if (n.delete()) {
                        return true;
                    }
                } else {
                    node = n;
                }
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Deque
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        Node<E> nodeForward = this.header.forward();
        while (true) {
            Node<E> n = nodeForward;
            if (n != null) {
                if (!o.equals(n.element)) {
                    nodeForward = n.forward();
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return !usable(this.header.successor());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Deque
    public int size() {
        long count = 0;
        Node<E> nodeForward = this.header.forward();
        while (true) {
            Node<E> n = nodeForward;
            if (n == null) {
                break;
            }
            count++;
            nodeForward = n.forward();
        }
        if (count >= 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) count;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Deque
    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Deque
    public boolean addAll(Collection<? extends E> c) {
        Iterator<? extends E> it = c.iterator();
        if (!it.hasNext()) {
            return false;
        }
        do {
            addLast(it.next());
        } while (it.hasNext());
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public void clear() {
        while (pollFirst() != null) {
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        return toArrayList().toArray();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) toArrayList().toArray(tArr);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Deque
    public RemovalReportingIterator<E> iterator() {
        return new CLDIterator();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ConcurrentLinkedDeque$CLDIterator.class */
    final class CLDIterator implements RemovalReportingIterator<E> {
        Node<E> last;
        Node<E> next;

        CLDIterator() {
            this.next = ConcurrentLinkedDeque.this.header.forward();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.next != null;
        }

        @Override // java.util.Iterator
        public E next() {
            Node<E> l = this.next;
            this.last = l;
            if (l == null) {
                throw new NoSuchElementException();
            }
            this.next = this.next.forward();
            return l.element;
        }

        @Override // java.util.Iterator
        public void remove() {
            reportingRemove();
        }

        @Override // com.mongodb.internal.connection.ConcurrentLinkedDeque.RemovalReportingIterator
        public boolean reportingRemove() {
            boolean successfullyRemoved;
            Node<E> l = this.last;
            if (l == null) {
                throw new IllegalStateException();
            }
            boolean zDelete = l.delete();
            while (true) {
                successfullyRemoved = zDelete;
                if (successfullyRemoved || l.isDeleted()) {
                    break;
                }
                zDelete = l.delete();
            }
            return successfullyRemoved;
        }
    }

    @Override // java.util.Deque
    public Iterator<E> descendingIterator() {
        throw new UnsupportedOperationException();
    }
}
