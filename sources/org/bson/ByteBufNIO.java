package org.bson;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/ByteBufNIO.class */
public class ByteBufNIO implements ByteBuf {
    private ByteBuffer buf;
    private final AtomicInteger referenceCount = new AtomicInteger(1);

    public ByteBufNIO(ByteBuffer buf) {
        this.buf = buf.order(ByteOrder.LITTLE_ENDIAN);
    }

    @Override // org.bson.ByteBuf
    public int getReferenceCount() {
        return this.referenceCount.get();
    }

    @Override // org.bson.ByteBuf
    public ByteBufNIO retain() {
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
        if (this.referenceCount.get() == 0) {
            this.buf = null;
        }
    }

    @Override // org.bson.ByteBuf
    public int capacity() {
        return this.buf.capacity();
    }

    @Override // org.bson.ByteBuf
    public ByteBuf put(int index, byte b) {
        this.buf.put(index, b);
        return this;
    }

    @Override // org.bson.ByteBuf
    public int remaining() {
        return this.buf.remaining();
    }

    @Override // org.bson.ByteBuf
    public ByteBuf put(byte[] src, int offset, int length) {
        this.buf.put(src, offset, length);
        return this;
    }

    @Override // org.bson.ByteBuf
    public boolean hasRemaining() {
        return this.buf.hasRemaining();
    }

    @Override // org.bson.ByteBuf
    public ByteBuf put(byte b) {
        this.buf.put(b);
        return this;
    }

    @Override // org.bson.ByteBuf
    public ByteBuf flip() {
        this.buf.flip();
        return this;
    }

    @Override // org.bson.ByteBuf
    public byte[] array() {
        return this.buf.array();
    }

    @Override // org.bson.ByteBuf
    public int limit() {
        return this.buf.limit();
    }

    @Override // org.bson.ByteBuf
    public ByteBuf position(int newPosition) {
        this.buf.position(newPosition);
        return this;
    }

    @Override // org.bson.ByteBuf
    public ByteBuf clear() {
        this.buf.clear();
        return this;
    }

    @Override // org.bson.ByteBuf
    public ByteBuf order(ByteOrder byteOrder) {
        this.buf.order(byteOrder);
        return this;
    }

    @Override // org.bson.ByteBuf
    public byte get() {
        return this.buf.get();
    }

    @Override // org.bson.ByteBuf
    public byte get(int index) {
        return this.buf.get(index);
    }

    @Override // org.bson.ByteBuf
    public ByteBuf get(byte[] bytes) {
        this.buf.get(bytes);
        return this;
    }

    @Override // org.bson.ByteBuf
    public ByteBuf get(int index, byte[] bytes) {
        return get(index, bytes, 0, bytes.length);
    }

    @Override // org.bson.ByteBuf
    public ByteBuf get(byte[] bytes, int offset, int length) {
        this.buf.get(bytes, offset, length);
        return this;
    }

    @Override // org.bson.ByteBuf
    public ByteBuf get(int index, byte[] bytes, int offset, int length) {
        for (int i = 0; i < length; i++) {
            bytes[offset + i] = this.buf.get(index + i);
        }
        return this;
    }

    @Override // org.bson.ByteBuf
    public long getLong() {
        return this.buf.getLong();
    }

    @Override // org.bson.ByteBuf
    public long getLong(int index) {
        return this.buf.getLong(index);
    }

    @Override // org.bson.ByteBuf
    public double getDouble() {
        return this.buf.getDouble();
    }

    @Override // org.bson.ByteBuf
    public double getDouble(int index) {
        return this.buf.getDouble(index);
    }

    @Override // org.bson.ByteBuf
    public int getInt() {
        return this.buf.getInt();
    }

    @Override // org.bson.ByteBuf
    public int getInt(int index) {
        return this.buf.getInt(index);
    }

    @Override // org.bson.ByteBuf
    public int position() {
        return this.buf.position();
    }

    @Override // org.bson.ByteBuf
    public ByteBuf limit(int newLimit) {
        this.buf.limit(newLimit);
        return this;
    }

    @Override // org.bson.ByteBuf
    public ByteBuf asReadOnly() {
        return new ByteBufNIO(this.buf.asReadOnlyBuffer());
    }

    @Override // org.bson.ByteBuf
    public ByteBuf duplicate() {
        return new ByteBufNIO(this.buf.duplicate());
    }

    @Override // org.bson.ByteBuf
    public ByteBuffer asNIO() {
        return this.buf;
    }
}
