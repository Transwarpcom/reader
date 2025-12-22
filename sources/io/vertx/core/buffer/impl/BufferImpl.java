package io.vertx.core.buffer.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.impl.Arguments;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/buffer/impl/BufferImpl.class */
public class BufferImpl implements Buffer {
    private ByteBuf buffer;

    public BufferImpl() {
        this(0);
    }

    BufferImpl(int initialSizeHint) {
        this.buffer = Unpooled.unreleasableBuffer(Unpooled.buffer(initialSizeHint, Integer.MAX_VALUE));
    }

    BufferImpl(byte[] bytes) {
        this.buffer = Unpooled.unreleasableBuffer(Unpooled.buffer(bytes.length, Integer.MAX_VALUE)).writeBytes(bytes);
    }

    BufferImpl(String str, String enc) {
        this(str.getBytes(Charset.forName((String) Objects.requireNonNull(enc))));
    }

    BufferImpl(String str, Charset cs) {
        this(str.getBytes(cs));
    }

    BufferImpl(String str) {
        this(str, StandardCharsets.UTF_8);
    }

    BufferImpl(ByteBuf buffer) {
        this.buffer = Unpooled.unreleasableBuffer(buffer);
    }

    @Override // io.vertx.core.buffer.Buffer
    public String toString() {
        return this.buffer.toString(StandardCharsets.UTF_8);
    }

    @Override // io.vertx.core.buffer.Buffer
    public String toString(String enc) {
        return this.buffer.toString(Charset.forName(enc));
    }

    @Override // io.vertx.core.buffer.Buffer
    public String toString(Charset enc) {
        return this.buffer.toString(enc);
    }

    @Override // io.vertx.core.buffer.Buffer
    public JsonObject toJsonObject() {
        return new JsonObject(this);
    }

    @Override // io.vertx.core.buffer.Buffer
    public JsonArray toJsonArray() {
        return new JsonArray(this);
    }

    @Override // io.vertx.core.buffer.Buffer
    public byte getByte(int pos) {
        return this.buffer.getByte(pos);
    }

    @Override // io.vertx.core.buffer.Buffer
    public short getUnsignedByte(int pos) {
        return this.buffer.getUnsignedByte(pos);
    }

    @Override // io.vertx.core.buffer.Buffer
    public int getInt(int pos) {
        return this.buffer.getInt(pos);
    }

    @Override // io.vertx.core.buffer.Buffer
    public int getIntLE(int pos) {
        return this.buffer.getIntLE(pos);
    }

    @Override // io.vertx.core.buffer.Buffer
    public long getUnsignedInt(int pos) {
        return this.buffer.getUnsignedInt(pos);
    }

    @Override // io.vertx.core.buffer.Buffer
    public long getUnsignedIntLE(int pos) {
        return this.buffer.getUnsignedIntLE(pos);
    }

    @Override // io.vertx.core.buffer.Buffer
    public long getLong(int pos) {
        return this.buffer.getLong(pos);
    }

    @Override // io.vertx.core.buffer.Buffer
    public long getLongLE(int pos) {
        return this.buffer.getLongLE(pos);
    }

    @Override // io.vertx.core.buffer.Buffer
    public double getDouble(int pos) {
        return this.buffer.getDouble(pos);
    }

    @Override // io.vertx.core.buffer.Buffer
    public float getFloat(int pos) {
        return this.buffer.getFloat(pos);
    }

    @Override // io.vertx.core.buffer.Buffer
    public short getShort(int pos) {
        return this.buffer.getShort(pos);
    }

    @Override // io.vertx.core.buffer.Buffer
    public short getShortLE(int pos) {
        return this.buffer.getShortLE(pos);
    }

    @Override // io.vertx.core.buffer.Buffer
    public int getUnsignedShort(int pos) {
        return this.buffer.getUnsignedShort(pos);
    }

    @Override // io.vertx.core.buffer.Buffer
    public int getUnsignedShortLE(int pos) {
        return this.buffer.getUnsignedShortLE(pos);
    }

    @Override // io.vertx.core.buffer.Buffer
    public int getMedium(int pos) {
        return this.buffer.getMedium(pos);
    }

    @Override // io.vertx.core.buffer.Buffer
    public int getMediumLE(int pos) {
        return this.buffer.getMediumLE(pos);
    }

    @Override // io.vertx.core.buffer.Buffer
    public int getUnsignedMedium(int pos) {
        return this.buffer.getUnsignedMedium(pos);
    }

    @Override // io.vertx.core.buffer.Buffer
    public int getUnsignedMediumLE(int pos) {
        return this.buffer.getUnsignedMediumLE(pos);
    }

    @Override // io.vertx.core.buffer.Buffer
    public byte[] getBytes() {
        byte[] arr = new byte[this.buffer.writerIndex()];
        this.buffer.getBytes(0, arr);
        return arr;
    }

    @Override // io.vertx.core.buffer.Buffer
    public byte[] getBytes(int start, int end) {
        Arguments.require(end >= start, "end must be greater or equal than start");
        byte[] arr = new byte[end - start];
        this.buffer.getBytes(start, arr, 0, end - start);
        return arr;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer getBytes(byte[] dst) {
        return getBytes(dst, 0);
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer getBytes(byte[] dst, int dstIndex) {
        return getBytes(0, this.buffer.writerIndex(), dst, dstIndex);
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer getBytes(int start, int end, byte[] dst) {
        return getBytes(start, end, dst, 0);
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer getBytes(int start, int end, byte[] dst, int dstIndex) {
        Arguments.require(end >= start, "end must be greater or equal than start");
        this.buffer.getBytes(start, dst, dstIndex, end - start);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer getBuffer(int start, int end) {
        return new BufferImpl(getBytes(start, end));
    }

    @Override // io.vertx.core.buffer.Buffer
    public String getString(int start, int end, String enc) {
        byte[] bytes = getBytes(start, end);
        Charset cs = Charset.forName(enc);
        return new String(bytes, cs);
    }

    @Override // io.vertx.core.buffer.Buffer
    public String getString(int start, int end) {
        byte[] bytes = getBytes(start, end);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer appendBuffer(Buffer buff) {
        this.buffer.writeBytes(buff.getByteBuf());
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer appendBuffer(Buffer buff, int offset, int len) {
        ByteBuf byteBuf = buff.getByteBuf();
        int from = byteBuf.readerIndex() + offset;
        this.buffer.writeBytes(byteBuf, from, len);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer appendBytes(byte[] bytes) {
        this.buffer.writeBytes(bytes);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer appendBytes(byte[] bytes, int offset, int len) {
        this.buffer.writeBytes(bytes, offset, len);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer appendByte(byte b) {
        this.buffer.writeByte(b);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer appendUnsignedByte(short b) {
        this.buffer.writeByte(b);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer appendInt(int i) {
        this.buffer.writeInt(i);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer appendIntLE(int i) {
        this.buffer.writeIntLE(i);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer appendUnsignedInt(long i) {
        this.buffer.writeInt((int) i);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer appendUnsignedIntLE(long i) {
        this.buffer.writeIntLE((int) i);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer appendMedium(int i) {
        this.buffer.writeMedium(i);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer appendMediumLE(int i) {
        this.buffer.writeMediumLE(i);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer appendLong(long l) {
        this.buffer.writeLong(l);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer appendLongLE(long l) {
        this.buffer.writeLongLE(l);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer appendShort(short s) {
        this.buffer.writeShort(s);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer appendShortLE(short s) {
        this.buffer.writeShortLE(s);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer appendUnsignedShort(int s) {
        this.buffer.writeShort(s);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer appendUnsignedShortLE(int s) {
        this.buffer.writeShortLE(s);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer appendFloat(float f) {
        this.buffer.writeFloat(f);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer appendDouble(double d) {
        this.buffer.writeDouble(d);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer appendString(String str, String enc) {
        return append(str, Charset.forName((String) Objects.requireNonNull(enc)));
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer appendString(String str) {
        return append(str, CharsetUtil.UTF_8);
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer setByte(int pos, byte b) {
        ensureWritable(pos, 1);
        this.buffer.setByte(pos, b);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer setUnsignedByte(int pos, short b) {
        ensureWritable(pos, 1);
        this.buffer.setByte(pos, b);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer setInt(int pos, int i) {
        ensureWritable(pos, 4);
        this.buffer.setInt(pos, i);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer setIntLE(int pos, int i) {
        ensureWritable(pos, 4);
        this.buffer.setIntLE(pos, i);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer setUnsignedInt(int pos, long i) {
        ensureWritable(pos, 4);
        this.buffer.setInt(pos, (int) i);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer setUnsignedIntLE(int pos, long i) {
        ensureWritable(pos, 4);
        this.buffer.setIntLE(pos, (int) i);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer setMedium(int pos, int i) {
        ensureWritable(pos, 3);
        this.buffer.setMedium(pos, i);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer setMediumLE(int pos, int i) {
        ensureWritable(pos, 3);
        this.buffer.setMediumLE(pos, i);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer setLong(int pos, long l) {
        ensureWritable(pos, 8);
        this.buffer.setLong(pos, l);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer setLongLE(int pos, long l) {
        ensureWritable(pos, 8);
        this.buffer.setLongLE(pos, l);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer setDouble(int pos, double d) {
        ensureWritable(pos, 8);
        this.buffer.setDouble(pos, d);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer setFloat(int pos, float f) {
        ensureWritable(pos, 4);
        this.buffer.setFloat(pos, f);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer setShort(int pos, short s) {
        ensureWritable(pos, 2);
        this.buffer.setShort(pos, s);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer setShortLE(int pos, short s) {
        ensureWritable(pos, 2);
        this.buffer.setShortLE(pos, s);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer setUnsignedShort(int pos, int s) {
        ensureWritable(pos, 2);
        this.buffer.setShort(pos, s);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer setUnsignedShortLE(int pos, int s) {
        ensureWritable(pos, 2);
        this.buffer.setShortLE(pos, s);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer setBuffer(int pos, Buffer b) {
        ensureWritable(pos, b.length());
        this.buffer.setBytes(pos, b.getByteBuf());
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer setBuffer(int pos, Buffer b, int offset, int len) {
        ensureWritable(pos, len);
        ByteBuf byteBuf = b.getByteBuf();
        this.buffer.setBytes(pos, byteBuf, byteBuf.readerIndex() + offset, len);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public BufferImpl setBytes(int pos, ByteBuffer b) {
        ensureWritable(pos, b.limit());
        this.buffer.setBytes(pos, b);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer setBytes(int pos, byte[] b) {
        ensureWritable(pos, b.length);
        this.buffer.setBytes(pos, b);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer setBytes(int pos, byte[] b, int offset, int len) {
        ensureWritable(pos, len);
        this.buffer.setBytes(pos, b, offset, len);
        return this;
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer setString(int pos, String str) {
        return setBytes(pos, str, CharsetUtil.UTF_8);
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer setString(int pos, String str, String enc) {
        return setBytes(pos, str, Charset.forName(enc));
    }

    @Override // io.vertx.core.buffer.Buffer
    public int length() {
        return this.buffer.writerIndex();
    }

    @Override // io.vertx.core.buffer.Buffer, io.vertx.core.shareddata.Shareable
    public Buffer copy() {
        return new BufferImpl(this.buffer.copy());
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer slice() {
        return new BufferImpl(this.buffer.slice());
    }

    @Override // io.vertx.core.buffer.Buffer
    public Buffer slice(int start, int end) {
        return new BufferImpl(this.buffer.slice(start, end - start));
    }

    @Override // io.vertx.core.buffer.Buffer
    public ByteBuf getByteBuf() {
        return this.buffer.duplicate();
    }

    private Buffer append(String str, Charset charset) {
        byte[] bytes = str.getBytes(charset);
        this.buffer.writeBytes(bytes);
        return this;
    }

    private Buffer setBytes(int pos, String str, Charset charset) {
        byte[] bytes = str.getBytes(charset);
        ensureWritable(pos, bytes.length);
        this.buffer.setBytes(pos, bytes);
        return this;
    }

    private void ensureWritable(int pos, int len) {
        int ni = pos + len;
        int cap = this.buffer.capacity();
        int over = ni - cap;
        if (over > 0) {
            this.buffer.writerIndex(cap);
            this.buffer.ensureWritable(over);
        }
        if (ni > this.buffer.writerIndex()) {
            this.buffer.writerIndex(ni);
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BufferImpl buffer1 = (BufferImpl) o;
        return this.buffer != null ? this.buffer.equals(buffer1.buffer) : buffer1.buffer == null;
    }

    public int hashCode() {
        if (this.buffer != null) {
            return this.buffer.hashCode();
        }
        return 0;
    }

    @Override // io.vertx.core.shareddata.impl.ClusterSerializable
    public void writeToBuffer(Buffer buff) {
        buff.appendInt(length());
        buff.appendBuffer(this);
    }

    @Override // io.vertx.core.shareddata.impl.ClusterSerializable
    public int readFromBuffer(int pos, Buffer buffer) {
        int len = buffer.getInt(pos);
        Buffer b = buffer.getBuffer(pos + 4, pos + 4 + len);
        this.buffer = b.getByteBuf();
        return pos + 4 + len;
    }
}
