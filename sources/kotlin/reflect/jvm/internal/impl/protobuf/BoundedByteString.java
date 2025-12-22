package kotlin.reflect.jvm.internal.impl.protobuf;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.reflect.jvm.internal.impl.protobuf.ByteString;
import org.slf4j.Marker;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/protobuf/BoundedByteString.class */
class BoundedByteString extends LiteralByteString {
    private final int bytesOffset;
    private final int bytesLength;

    BoundedByteString(byte[] bytes, int offset, int length) {
        super(bytes);
        if (offset < 0) {
            throw new IllegalArgumentException(new StringBuilder(29).append("Offset too small: ").append(offset).toString());
        }
        if (length < 0) {
            throw new IllegalArgumentException(new StringBuilder(29).append("Length too small: ").append(offset).toString());
        }
        if (offset + length > bytes.length) {
            throw new IllegalArgumentException(new StringBuilder(48).append("Offset+Length too large: ").append(offset).append(Marker.ANY_NON_NULL_MARKER).append(length).toString());
        }
        this.bytesOffset = offset;
        this.bytesLength = length;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.LiteralByteString
    public byte byteAt(int index) {
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException(new StringBuilder(28).append("Index too small: ").append(index).toString());
        }
        if (index >= size()) {
            throw new ArrayIndexOutOfBoundsException(new StringBuilder(41).append("Index too large: ").append(index).append(", ").append(size()).toString());
        }
        return this.bytes[this.bytesOffset + index];
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.LiteralByteString, kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    public int size() {
        return this.bytesLength;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.LiteralByteString
    protected int getOffsetIntoBytes() {
        return this.bytesOffset;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.LiteralByteString, kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    protected void copyToInternal(byte[] target, int sourceOffset, int targetOffset, int numberToCopy) {
        System.arraycopy(this.bytes, getOffsetIntoBytes() + sourceOffset, target, targetOffset, numberToCopy);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.LiteralByteString, kotlin.reflect.jvm.internal.impl.protobuf.ByteString, java.lang.Iterable
    /* renamed from: iterator, reason: merged with bridge method [inline-methods] */
    public Iterator<Byte> iterator2() {
        return new BoundedByteIterator();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/protobuf/BoundedByteString$BoundedByteIterator.class */
    private class BoundedByteIterator implements ByteString.ByteIterator {
        private int position;
        private final int limit;

        private BoundedByteIterator() {
            this.position = BoundedByteString.this.getOffsetIntoBytes();
            this.limit = this.position + BoundedByteString.this.size();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.position < this.limit;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Byte next() {
            return Byte.valueOf(nextByte());
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString.ByteIterator
        public byte nextByte() {
            if (this.position >= this.limit) {
                throw new NoSuchElementException();
            }
            byte[] bArr = BoundedByteString.this.bytes;
            int i = this.position;
            this.position = i + 1;
            return bArr[i];
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
