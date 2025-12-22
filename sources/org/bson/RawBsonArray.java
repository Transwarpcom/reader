package org.bson;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.bson.assertions.Assertions;
import org.bson.io.ByteBufferBsonInput;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/RawBsonArray.class */
public class RawBsonArray extends BsonArray implements Serializable {
    private static final long serialVersionUID = 2;
    private static final String IMMUTABLE_MSG = "RawBsonArray instances are immutable";
    private final transient RawBsonArrayList delegate;

    public RawBsonArray(byte[] bytes) {
        this((byte[]) Assertions.notNull("bytes", bytes), 0, bytes.length);
    }

    public RawBsonArray(byte[] bytes, int offset, int length) {
        this(new RawBsonArrayList(bytes, offset, length));
    }

    private RawBsonArray(RawBsonArrayList values) {
        super(values, false);
        this.delegate = values;
    }

    ByteBuf getByteBuffer() {
        return this.delegate.getByteBuffer();
    }

    @Override // org.bson.BsonArray, java.util.List, java.util.Collection
    public boolean add(BsonValue bsonValue) {
        throw new UnsupportedOperationException(IMMUTABLE_MSG);
    }

    @Override // org.bson.BsonArray, java.util.List, java.util.Collection
    public boolean remove(Object o) {
        throw new UnsupportedOperationException(IMMUTABLE_MSG);
    }

    @Override // org.bson.BsonArray, java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends BsonValue> c) {
        throw new UnsupportedOperationException(IMMUTABLE_MSG);
    }

    @Override // org.bson.BsonArray, java.util.List
    public boolean addAll(int index, Collection<? extends BsonValue> c) {
        throw new UnsupportedOperationException(IMMUTABLE_MSG);
    }

    @Override // org.bson.BsonArray, java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException(IMMUTABLE_MSG);
    }

    @Override // org.bson.BsonArray, java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException(IMMUTABLE_MSG);
    }

    @Override // org.bson.BsonArray, java.util.List, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException(IMMUTABLE_MSG);
    }

    @Override // org.bson.BsonArray, java.util.List
    public BsonValue set(int index, BsonValue element) {
        throw new UnsupportedOperationException(IMMUTABLE_MSG);
    }

    @Override // org.bson.BsonArray, java.util.List
    public void add(int index, BsonValue element) {
        throw new UnsupportedOperationException(IMMUTABLE_MSG);
    }

    @Override // org.bson.BsonArray, java.util.List
    public BsonValue remove(int index) {
        throw new UnsupportedOperationException(IMMUTABLE_MSG);
    }

    @Override // org.bson.BsonArray
    /* renamed from: clone */
    public BsonArray mo1046clone() {
        return new RawBsonArray((byte[]) this.delegate.bytes.clone(), this.delegate.offset, this.delegate.length);
    }

    @Override // org.bson.BsonArray, java.util.List, java.util.Collection
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override // org.bson.BsonArray, java.util.List, java.util.Collection
    public int hashCode() {
        return super.hashCode();
    }

    private Object writeReplace() {
        return new SerializationProxy(this.delegate.bytes, this.delegate.offset, this.delegate.length);
    }

    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/RawBsonArray$SerializationProxy.class */
    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = 1;
        private final byte[] bytes;

        SerializationProxy(byte[] bytes, int offset, int length) {
            if (bytes.length == length) {
                this.bytes = bytes;
            } else {
                this.bytes = new byte[length];
                System.arraycopy(bytes, offset, this.bytes, 0, length);
            }
        }

        private Object readResolve() {
            return new RawBsonArray(this.bytes);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/RawBsonArray$RawBsonArrayList.class */
    static class RawBsonArrayList extends AbstractList<BsonValue> {
        private static final int MIN_BSON_ARRAY_SIZE = 5;
        private Integer cachedSize;
        private final byte[] bytes;
        private final int offset;
        private final int length;

        RawBsonArrayList(byte[] bytes, int offset, int length) {
            Assertions.notNull("bytes", bytes);
            Assertions.isTrueArgument("offset >= 0", offset >= 0);
            Assertions.isTrueArgument("offset < bytes.length", offset < bytes.length);
            Assertions.isTrueArgument("length <= bytes.length - offset", length <= bytes.length - offset);
            Assertions.isTrueArgument("length >= 5", length >= 5);
            this.bytes = bytes;
            this.offset = offset;
            this.length = length;
        }

        @Override // java.util.AbstractList, java.util.List
        public BsonValue get(int index) {
            if (index < 0) {
                throw new IndexOutOfBoundsException();
            }
            int curIndex = 0;
            BsonBinaryReader bsonReader = createReader();
            try {
                bsonReader.readStartDocument();
                while (bsonReader.readBsonType() != BsonType.END_OF_DOCUMENT) {
                    bsonReader.skipName();
                    if (curIndex == index) {
                        BsonValue bsonValueDecode = RawBsonValueHelper.decode(this.bytes, bsonReader);
                        bsonReader.close();
                        return bsonValueDecode;
                    }
                    bsonReader.skipValue();
                    curIndex++;
                }
                bsonReader.readEndDocument();
                bsonReader.close();
                throw new IndexOutOfBoundsException();
            } catch (Throwable th) {
                bsonReader.close();
                throw th;
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            if (this.cachedSize != null) {
                return this.cachedSize.intValue();
            }
            int size = 0;
            BsonBinaryReader bsonReader = createReader();
            try {
                bsonReader.readStartDocument();
                while (bsonReader.readBsonType() != BsonType.END_OF_DOCUMENT) {
                    size++;
                    bsonReader.readName();
                    bsonReader.skipValue();
                }
                bsonReader.readEndDocument();
                this.cachedSize = Integer.valueOf(size);
                return this.cachedSize.intValue();
            } finally {
                bsonReader.close();
            }
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        public Iterator<BsonValue> iterator() {
            return new Itr(this);
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<BsonValue> listIterator() {
            return new ListItr(0);
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<BsonValue> listIterator(int index) {
            return new ListItr(index);
        }

        /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/RawBsonArray$RawBsonArrayList$Itr.class */
        private class Itr implements Iterator<BsonValue> {
            private int cursor;
            private BsonBinaryReader bsonReader;
            private int currentPosition;

            Itr(RawBsonArrayList rawBsonArrayList) {
                this(0);
            }

            Itr(int cursorPosition) {
                this.cursor = 0;
                this.currentPosition = 0;
                setIterator(cursorPosition);
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                boolean hasNext = this.cursor != RawBsonArrayList.this.size();
                if (!hasNext) {
                    this.bsonReader.close();
                }
                return hasNext;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public BsonValue next() {
                while (this.cursor > this.currentPosition && this.bsonReader.readBsonType() != BsonType.END_OF_DOCUMENT) {
                    this.bsonReader.skipName();
                    this.bsonReader.skipValue();
                    this.currentPosition++;
                }
                if (this.bsonReader.readBsonType() != BsonType.END_OF_DOCUMENT) {
                    this.bsonReader.skipName();
                    this.cursor++;
                    this.currentPosition = this.cursor;
                    return RawBsonValueHelper.decode(RawBsonArrayList.this.bytes, this.bsonReader);
                }
                this.bsonReader.close();
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException(RawBsonArray.IMMUTABLE_MSG);
            }

            public int getCursor() {
                return this.cursor;
            }

            public void setCursor(int cursor) {
                this.cursor = cursor;
            }

            void setIterator(int cursorPosition) {
                this.cursor = cursorPosition;
                this.currentPosition = 0;
                if (this.bsonReader != null) {
                    this.bsonReader.close();
                }
                this.bsonReader = RawBsonArrayList.this.createReader();
                this.bsonReader.readStartDocument();
            }
        }

        /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/RawBsonArray$RawBsonArrayList$ListItr.class */
        private class ListItr extends Itr implements ListIterator<BsonValue> {
            ListItr(int index) {
                super(index);
            }

            @Override // java.util.ListIterator
            public boolean hasPrevious() {
                return getCursor() != 0;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.ListIterator
            public BsonValue previous() {
                try {
                    BsonValue previous = RawBsonArrayList.this.get(previousIndex());
                    setIterator(previousIndex());
                    return previous;
                } catch (IndexOutOfBoundsException e) {
                    throw new NoSuchElementException();
                }
            }

            @Override // java.util.ListIterator
            public int nextIndex() {
                return getCursor();
            }

            @Override // java.util.ListIterator
            public int previousIndex() {
                return getCursor() - 1;
            }

            @Override // java.util.ListIterator
            public void set(BsonValue bsonValue) {
                throw new UnsupportedOperationException(RawBsonArray.IMMUTABLE_MSG);
            }

            @Override // java.util.ListIterator
            public void add(BsonValue bsonValue) {
                throw new UnsupportedOperationException(RawBsonArray.IMMUTABLE_MSG);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public BsonBinaryReader createReader() {
            return new BsonBinaryReader(new ByteBufferBsonInput(getByteBuffer()));
        }

        ByteBuf getByteBuffer() {
            ByteBuffer buffer = ByteBuffer.wrap(this.bytes, this.offset, this.length);
            buffer.order(ByteOrder.LITTLE_ENDIAN);
            return new ByteBufNIO(buffer);
        }
    }
}
