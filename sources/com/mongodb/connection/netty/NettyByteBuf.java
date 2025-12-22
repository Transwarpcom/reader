package com.mongodb.connection.netty;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.bson.ByteBuf;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/netty/NettyByteBuf.class */
final class NettyByteBuf implements ByteBuf {
    private io.netty.buffer.ByteBuf proxied;
    private boolean isWriting;

    NettyByteBuf(io.netty.buffer.ByteBuf proxied) {
        this.isWriting = true;
        this.proxied = proxied.order(ByteOrder.LITTLE_ENDIAN);
    }

    NettyByteBuf(io.netty.buffer.ByteBuf proxied, boolean isWriting) {
        this(proxied);
        this.isWriting = isWriting;
    }

    io.netty.buffer.ByteBuf asByteBuf() {
        return this.proxied;
    }

    @Override // org.bson.ByteBuf
    public int capacity() {
        return this.proxied.capacity();
    }

    @Override // org.bson.ByteBuf
    public ByteBuf put(int index, byte b) {
        this.proxied.setByte(index, b);
        return this;
    }

    @Override // org.bson.ByteBuf
    public int remaining() {
        if (this.isWriting) {
            return this.proxied.writableBytes();
        }
        return this.proxied.readableBytes();
    }

    @Override // org.bson.ByteBuf
    public ByteBuf put(byte[] src, int offset, int length) {
        this.proxied.writeBytes(src, offset, length);
        return this;
    }

    @Override // org.bson.ByteBuf
    public boolean hasRemaining() {
        return remaining() > 0;
    }

    @Override // org.bson.ByteBuf
    public ByteBuf put(byte b) {
        this.proxied.writeByte(b);
        return this;
    }

    @Override // org.bson.ByteBuf
    public ByteBuf flip() {
        this.isWriting = !this.isWriting;
        return this;
    }

    @Override // org.bson.ByteBuf
    public byte[] array() {
        return this.proxied.array();
    }

    @Override // org.bson.ByteBuf
    public int limit() {
        if (this.isWriting) {
            return this.proxied.writerIndex() + remaining();
        }
        return this.proxied.readerIndex() + remaining();
    }

    @Override // org.bson.ByteBuf
    public ByteBuf position(int newPosition) {
        if (this.isWriting) {
            this.proxied.writerIndex(newPosition);
        } else {
            this.proxied.readerIndex(newPosition);
        }
        return this;
    }

    @Override // org.bson.ByteBuf
    public ByteBuf clear() {
        this.proxied.clear();
        return this;
    }

    @Override // org.bson.ByteBuf
    public ByteBuf order(ByteOrder byteOrder) {
        this.proxied = this.proxied.order(byteOrder);
        return this;
    }

    @Override // org.bson.ByteBuf
    public byte get() {
        return this.proxied.readByte();
    }

    @Override // org.bson.ByteBuf
    public byte get(int index) {
        return this.proxied.getByte(index);
    }

    @Override // org.bson.ByteBuf
    public ByteBuf get(byte[] bytes) {
        this.proxied.readBytes(bytes);
        return this;
    }

    @Override // org.bson.ByteBuf
    public ByteBuf get(int index, byte[] bytes) {
        this.proxied.getBytes(index, bytes);
        return this;
    }

    @Override // org.bson.ByteBuf
    public ByteBuf get(byte[] bytes, int offset, int length) {
        this.proxied.readBytes(bytes, offset, length);
        return this;
    }

    @Override // org.bson.ByteBuf
    public ByteBuf get(int index, byte[] bytes, int offset, int length) {
        this.proxied.getBytes(index, bytes, offset, length);
        return this;
    }

    @Override // org.bson.ByteBuf
    public long getLong() {
        return this.proxied.readLong();
    }

    @Override // org.bson.ByteBuf
    public long getLong(int index) {
        return this.proxied.getLong(index);
    }

    @Override // org.bson.ByteBuf
    public double getDouble() {
        return this.proxied.readDouble();
    }

    @Override // org.bson.ByteBuf
    public double getDouble(int index) {
        return this.proxied.getDouble(index);
    }

    @Override // org.bson.ByteBuf
    public int getInt() {
        return this.proxied.readInt();
    }

    @Override // org.bson.ByteBuf
    public int getInt(int index) {
        return this.proxied.getInt(index);
    }

    @Override // org.bson.ByteBuf
    public int position() {
        if (this.isWriting) {
            return this.proxied.writerIndex();
        }
        return this.proxied.readerIndex();
    }

    @Override // org.bson.ByteBuf
    public ByteBuf limit(int newLimit) {
        if (this.isWriting) {
            throw new UnsupportedOperationException("Can not set the limit while writing");
        }
        this.proxied.writerIndex(newLimit);
        return this;
    }

    @Override // org.bson.ByteBuf
    public ByteBuf asReadOnly() {
        return this;
    }

    @Override // org.bson.ByteBuf
    public ByteBuf duplicate() {
        return new NettyByteBuf(this.proxied.duplicate().retain(), this.isWriting);
    }

    @Override // org.bson.ByteBuf
    public ByteBuffer asNIO() {
        if (this.isWriting) {
            return this.proxied.nioBuffer(this.proxied.writerIndex(), this.proxied.writableBytes());
        }
        return this.proxied.nioBuffer(this.proxied.readerIndex(), this.proxied.readableBytes());
    }

    @Override // org.bson.ByteBuf
    public int getReferenceCount() {
        return this.proxied.refCnt();
    }

    @Override // org.bson.ByteBuf
    public ByteBuf retain() {
        this.proxied.retain();
        return this;
    }

    @Override // org.bson.ByteBuf
    public void release() {
        this.proxied.release();
    }
}
