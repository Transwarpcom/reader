package io.vertx.core.spi;

import io.netty.buffer.ByteBuf;
import io.vertx.core.buffer.Buffer;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/BufferFactory.class */
public interface BufferFactory {
    Buffer buffer(int i);

    Buffer buffer();

    Buffer buffer(String str);

    Buffer buffer(String str, String str2);

    Buffer buffer(byte[] bArr);

    Buffer buffer(ByteBuf byteBuf);

    Buffer directBuffer(String str, String str2);

    Buffer directBuffer(byte[] bArr);
}
