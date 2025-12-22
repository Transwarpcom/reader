package io.vertx.core.buffer.impl;

import io.netty.buffer.ByteBuf;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.impl.PartialPooledByteBufAllocator;
import io.vertx.core.spi.BufferFactory;
import java.nio.charset.Charset;
import java.util.Objects;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/buffer/impl/BufferFactoryImpl.class */
public class BufferFactoryImpl implements BufferFactory {
    @Override // io.vertx.core.spi.BufferFactory
    public Buffer buffer(int initialSizeHint) {
        return new BufferImpl(initialSizeHint);
    }

    @Override // io.vertx.core.spi.BufferFactory
    public Buffer buffer() {
        return new BufferImpl();
    }

    @Override // io.vertx.core.spi.BufferFactory
    public Buffer buffer(String str) {
        return new BufferImpl(str);
    }

    @Override // io.vertx.core.spi.BufferFactory
    public Buffer buffer(String str, String enc) {
        return new BufferImpl(str, enc);
    }

    @Override // io.vertx.core.spi.BufferFactory
    public Buffer buffer(byte[] bytes) {
        return new BufferImpl(bytes);
    }

    @Override // io.vertx.core.spi.BufferFactory
    public Buffer buffer(ByteBuf byteBuffer) {
        return new BufferImpl(byteBuffer);
    }

    @Override // io.vertx.core.spi.BufferFactory
    public Buffer directBuffer(String str, String enc) {
        return directBuffer(str.getBytes(Charset.forName((String) Objects.requireNonNull(enc))));
    }

    @Override // io.vertx.core.spi.BufferFactory
    public Buffer directBuffer(byte[] bytes) {
        ByteBuf buff = PartialPooledByteBufAllocator.UNPOOLED.directBuffer(bytes.length);
        buff.writeBytes(bytes);
        return new BufferImpl(buff);
    }
}
