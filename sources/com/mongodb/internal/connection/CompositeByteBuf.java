package com.mongodb.internal.connection;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.bson.ByteBuf;
import org.bson.assertions.Assertions;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/CompositeByteBuf.class */
class CompositeByteBuf implements ByteBuf {
    private final List<Component> components;
    private final AtomicInteger referenceCount = new AtomicInteger(1);
    private int position;
    private int limit;

    CompositeByteBuf(List<ByteBuf> buffers) {
        Assertions.notNull("buffers", buffers);
        Assertions.isTrueArgument("buffer list not empty", !buffers.isEmpty());
        this.components = new ArrayList(buffers.size());
        int offset = 0;
        for (ByteBuf cur : buffers) {
            Component component = new Component(cur.asReadOnly().order(ByteOrder.LITTLE_ENDIAN), offset);
            this.components.add(component);
            offset = component.endOffset;
        }
        this.limit = this.components.get(this.components.size() - 1).endOffset;
    }

    CompositeByteBuf(CompositeByteBuf from) {
        this.components = from.components;
        this.position = from.position();
        this.limit = from.limit();
    }

    @Override // org.bson.ByteBuf
    public ByteBuf order(ByteOrder byteOrder) {
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            throw new UnsupportedOperationException(String.format("Only %s is supported", ByteOrder.BIG_ENDIAN));
        }
        return this;
    }

    @Override // org.bson.ByteBuf
    public int capacity() {
        return this.components.get(this.components.size() - 1).endOffset;
    }

    @Override // org.bson.ByteBuf
    public int remaining() {
        return limit() - position();
    }

    @Override // org.bson.ByteBuf
    public boolean hasRemaining() {
        return remaining() > 0;
    }

    @Override // org.bson.ByteBuf
    public int position() {
        return this.position;
    }

    @Override // org.bson.ByteBuf
    public ByteBuf position(int newPosition) {
        if (newPosition < 0 || newPosition > this.limit) {
            throw new IndexOutOfBoundsException(String.format("%d is out of bounds", Integer.valueOf(newPosition)));
        }
        this.position = newPosition;
        return this;
    }

    @Override // org.bson.ByteBuf
    public ByteBuf clear() {
        this.position = 0;
        this.limit = capacity();
        return this;
    }

    @Override // org.bson.ByteBuf
    public int limit() {
        return this.limit;
    }

    @Override // org.bson.ByteBuf
    public byte get() {
        checkIndex(this.position);
        this.position++;
        return get(this.position - 1);
    }

    @Override // org.bson.ByteBuf
    public byte get(int index) {
        checkIndex(index);
        Component component = findComponent(index);
        return component.buffer.get(index - component.offset);
    }

    @Override // org.bson.ByteBuf
    public ByteBuf get(byte[] bytes) {
        checkIndex(this.position, bytes.length);
        this.position += bytes.length;
        return get(this.position - bytes.length, bytes);
    }

    @Override // org.bson.ByteBuf
    public ByteBuf get(int index, byte[] bytes) {
        return get(index, bytes, 0, bytes.length);
    }

    @Override // org.bson.ByteBuf
    public ByteBuf get(byte[] bytes, int offset, int length) {
        checkIndex(this.position, length);
        this.position += length;
        return get(this.position - length, bytes, offset, length);
    }

    @Override // org.bson.ByteBuf
    public ByteBuf get(int index, byte[] bytes, int offset, int length) {
        checkDstIndex(index, length, offset, bytes.length);
        int i = findComponentIndex(index);
        int curIndex = index;
        int curOffset = offset;
        int curLength = length;
        while (curLength > 0) {
            Component c = this.components.get(i);
            int localLength = Math.min(curLength, c.buffer.capacity() - (curIndex - c.offset));
            c.buffer.get(curIndex - c.offset, bytes, curOffset, localLength);
            curIndex += localLength;
            curOffset += localLength;
            curLength -= localLength;
            i++;
        }
        return this;
    }

    @Override // org.bson.ByteBuf
    public long getLong() {
        this.position += 8;
        return getLong(this.position - 8);
    }

    @Override // org.bson.ByteBuf
    public long getLong(int index) {
        checkIndex(index, 8);
        Component component = findComponent(index);
        if (index + 8 > component.endOffset) {
            return (getInt(index) & 4294967295L) | ((getInt(index + 4) & 4294967295L) << 32);
        }
        return component.buffer.getLong(index - component.offset);
    }

    @Override // org.bson.ByteBuf
    public double getDouble() {
        this.position += 8;
        return getDouble(this.position - 8);
    }

    @Override // org.bson.ByteBuf
    public double getDouble(int index) {
        return Double.longBitsToDouble(getLong(index));
    }

    @Override // org.bson.ByteBuf
    public int getInt() {
        this.position += 4;
        return getInt(this.position - 4);
    }

    @Override // org.bson.ByteBuf
    public int getInt(int index) {
        checkIndex(index, 4);
        Component component = findComponent(index);
        if (index + 4 > component.endOffset) {
            return (getShort(index) & 65535) | ((getShort(index + 2) & 65535) << 16);
        }
        return component.buffer.getInt(index - component.offset);
    }

    private int getShort(int index) {
        checkIndex(index, 2);
        return (short) ((get(index) & 255) | ((get(index + 1) & 255) << 8));
    }

    @Override // org.bson.ByteBuf
    public byte[] array() {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override // org.bson.ByteBuf
    public ByteBuf limit(int newLimit) {
        if (newLimit < 0 || newLimit > capacity()) {
            throw new IndexOutOfBoundsException(String.format("%d is out of bounds", Integer.valueOf(newLimit)));
        }
        this.limit = newLimit;
        return this;
    }

    @Override // org.bson.ByteBuf
    public ByteBuf put(int index, byte b) {
        throw new UnsupportedOperationException();
    }

    @Override // org.bson.ByteBuf
    public ByteBuf put(byte[] src, int offset, int length) {
        throw new UnsupportedOperationException();
    }

    @Override // org.bson.ByteBuf
    public ByteBuf put(byte b) {
        throw new UnsupportedOperationException();
    }

    @Override // org.bson.ByteBuf
    public ByteBuf flip() {
        throw new UnsupportedOperationException();
    }

    @Override // org.bson.ByteBuf
    public ByteBuf asReadOnly() {
        throw new UnsupportedOperationException();
    }

    @Override // org.bson.ByteBuf
    public ByteBuf duplicate() {
        return new CompositeByteBuf(this);
    }

    @Override // org.bson.ByteBuf
    public ByteBuffer asNIO() {
        if (this.components.size() != 1) {
            byte[] bytes = new byte[remaining()];
            get(this.position, bytes, 0, bytes.length);
            return ByteBuffer.wrap(bytes);
        }
        ByteBuffer byteBuffer = this.components.get(0).buffer.asNIO().duplicate();
        byteBuffer.position(this.position).limit(this.limit);
        return byteBuffer;
    }

    @Override // org.bson.ByteBuf
    public int getReferenceCount() {
        return this.referenceCount.get();
    }

    @Override // org.bson.ByteBuf
    public ByteBuf retain() {
        if (this.referenceCount.incrementAndGet() == 1) {
            this.referenceCount.decrementAndGet();
            throw new IllegalStateException("Attempted to increment the reference count when it is already 0");
        }
        return this;
    }

    @Override // org.bson.ByteBuf
    public void release() {
        if (this.referenceCount.decrementAndGet() < 0) {
            this.referenceCount.incrementAndGet();
            throw new IllegalStateException("Attempted to decrement the reference count below 0");
        }
    }

    private Component findComponent(int index) {
        return this.components.get(findComponentIndex(index));
    }

    private int findComponentIndex(int index) {
        for (int i = this.components.size() - 1; i >= 0; i--) {
            Component cur = this.components.get(i);
            if (index >= cur.offset) {
                return i;
            }
        }
        throw new IndexOutOfBoundsException(String.format("%d is out of bounds", Integer.valueOf(index)));
    }

    private void checkIndex(int index) {
        ensureAccessible();
        if (index < 0 || index >= capacity()) {
            throw new IndexOutOfBoundsException(String.format("index: %d (expected: range(0, %d))", Integer.valueOf(index), Integer.valueOf(capacity())));
        }
    }

    private void checkIndex(int index, int fieldLength) {
        ensureAccessible();
        if (index < 0 || index > capacity() - fieldLength) {
            throw new IndexOutOfBoundsException(String.format("index: %d, length: %d (expected: range(0, %d))", Integer.valueOf(index), Integer.valueOf(fieldLength), Integer.valueOf(capacity())));
        }
    }

    private void checkDstIndex(int index, int length, int dstIndex, int dstCapacity) {
        checkIndex(index, length);
        if (dstIndex < 0 || dstIndex > dstCapacity - length) {
            throw new IndexOutOfBoundsException(String.format("dstIndex: %d, length: %d (expected: range(0, %d))", Integer.valueOf(dstIndex), Integer.valueOf(length), Integer.valueOf(dstCapacity)));
        }
    }

    private void ensureAccessible() {
        if (this.referenceCount.get() == 0) {
            throw new IllegalStateException("Reference count is 0");
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/CompositeByteBuf$Component.class */
    private static final class Component {
        private final ByteBuf buffer;
        private final int length;
        private final int offset;
        private final int endOffset;

        Component(ByteBuf buffer, int offset) {
            this.buffer = buffer;
            this.length = buffer.limit() - buffer.position();
            this.offset = offset;
            this.endOffset = offset + this.length;
        }
    }
}
