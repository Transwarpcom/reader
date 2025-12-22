package io.vertx.core.buffer;

import io.netty.buffer.ByteBuf;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.ServiceHelper;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.shareddata.Shareable;
import io.vertx.core.shareddata.impl.ClusterSerializable;
import io.vertx.core.spi.BufferFactory;
import io.vertx.core.spi.json.JsonCodec;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/buffer/Buffer.class */
public interface Buffer extends ClusterSerializable, Shareable {

    @GenIgnore
    public static final BufferFactory factory = (BufferFactory) ServiceHelper.loadFactory(BufferFactory.class);

    String toString();

    String toString(String str);

    @GenIgnore({"permitted-type"})
    String toString(Charset charset);

    JsonObject toJsonObject();

    JsonArray toJsonArray();

    byte getByte(int i);

    short getUnsignedByte(int i);

    int getInt(int i);

    int getIntLE(int i);

    long getUnsignedInt(int i);

    long getUnsignedIntLE(int i);

    long getLong(int i);

    long getLongLE(int i);

    double getDouble(int i);

    float getFloat(int i);

    short getShort(int i);

    short getShortLE(int i);

    int getUnsignedShort(int i);

    int getUnsignedShortLE(int i);

    int getMedium(int i);

    int getMediumLE(int i);

    int getUnsignedMedium(int i);

    int getUnsignedMediumLE(int i);

    @GenIgnore({"permitted-type"})
    byte[] getBytes();

    @GenIgnore({"permitted-type"})
    byte[] getBytes(int i, int i2);

    @GenIgnore({"permitted-type"})
    @Fluent
    Buffer getBytes(byte[] bArr);

    @GenIgnore({"permitted-type"})
    @Fluent
    Buffer getBytes(byte[] bArr, int i);

    @GenIgnore({"permitted-type"})
    @Fluent
    Buffer getBytes(int i, int i2, byte[] bArr);

    @GenIgnore({"permitted-type"})
    @Fluent
    Buffer getBytes(int i, int i2, byte[] bArr, int i3);

    Buffer getBuffer(int i, int i2);

    String getString(int i, int i2, String str);

    String getString(int i, int i2);

    @Fluent
    Buffer appendBuffer(Buffer buffer);

    @Fluent
    Buffer appendBuffer(Buffer buffer, int i, int i2);

    @GenIgnore({"permitted-type"})
    @Fluent
    Buffer appendBytes(byte[] bArr);

    @GenIgnore({"permitted-type"})
    @Fluent
    Buffer appendBytes(byte[] bArr, int i, int i2);

    @Fluent
    Buffer appendByte(byte b);

    @Fluent
    Buffer appendUnsignedByte(short s);

    @Fluent
    Buffer appendInt(int i);

    @Fluent
    Buffer appendIntLE(int i);

    @Fluent
    Buffer appendUnsignedInt(long j);

    @Fluent
    Buffer appendUnsignedIntLE(long j);

    @Fluent
    Buffer appendMedium(int i);

    @Fluent
    Buffer appendMediumLE(int i);

    @Fluent
    Buffer appendLong(long j);

    @Fluent
    Buffer appendLongLE(long j);

    @Fluent
    Buffer appendShort(short s);

    @Fluent
    Buffer appendShortLE(short s);

    @Fluent
    Buffer appendUnsignedShort(int i);

    @Fluent
    Buffer appendUnsignedShortLE(int i);

    @Fluent
    Buffer appendFloat(float f);

    @Fluent
    Buffer appendDouble(double d);

    @Fluent
    Buffer appendString(String str, String str2);

    @Fluent
    Buffer appendString(String str);

    @Fluent
    Buffer setByte(int i, byte b);

    @Fluent
    Buffer setUnsignedByte(int i, short s);

    @Fluent
    Buffer setInt(int i, int i2);

    @Fluent
    Buffer setIntLE(int i, int i2);

    @Fluent
    Buffer setUnsignedInt(int i, long j);

    @Fluent
    Buffer setUnsignedIntLE(int i, long j);

    @Fluent
    Buffer setMedium(int i, int i2);

    @Fluent
    Buffer setMediumLE(int i, int i2);

    @Fluent
    Buffer setLong(int i, long j);

    @Fluent
    Buffer setLongLE(int i, long j);

    @Fluent
    Buffer setDouble(int i, double d);

    @Fluent
    Buffer setFloat(int i, float f);

    @Fluent
    Buffer setShort(int i, short s);

    @Fluent
    Buffer setShortLE(int i, short s);

    @Fluent
    Buffer setUnsignedShort(int i, int i2);

    @Fluent
    Buffer setUnsignedShortLE(int i, int i2);

    @Fluent
    Buffer setBuffer(int i, Buffer buffer);

    @Fluent
    Buffer setBuffer(int i, Buffer buffer, int i2, int i3);

    @GenIgnore({"permitted-type"})
    @Fluent
    Buffer setBytes(int i, ByteBuffer byteBuffer);

    @GenIgnore({"permitted-type"})
    @Fluent
    Buffer setBytes(int i, byte[] bArr);

    @GenIgnore({"permitted-type"})
    @Fluent
    Buffer setBytes(int i, byte[] bArr, int i2, int i3);

    @Fluent
    Buffer setString(int i, String str);

    @Fluent
    Buffer setString(int i, String str, String str2);

    int length();

    @Override // io.vertx.core.shareddata.Shareable
    Buffer copy();

    Buffer slice();

    Buffer slice(int i, int i2);

    @GenIgnore({"permitted-type"})
    ByteBuf getByteBuf();

    static Buffer buffer() {
        return factory.buffer();
    }

    static Buffer buffer(int initialSizeHint) {
        return factory.buffer(initialSizeHint);
    }

    static Buffer buffer(String string) {
        return factory.buffer(string);
    }

    static Buffer buffer(String string, String enc) {
        return factory.buffer(string, enc);
    }

    @GenIgnore({"permitted-type"})
    static Buffer buffer(byte[] bytes) {
        return factory.buffer(bytes);
    }

    @GenIgnore({"permitted-type"})
    static Buffer buffer(ByteBuf byteBuf) {
        return factory.buffer(byteBuf);
    }

    default Object toJson() {
        return JsonCodec.INSTANCE.fromBuffer(this, Object.class);
    }
}
