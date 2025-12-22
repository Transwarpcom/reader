package com.mongodb.connection.netty;

import com.mongodb.connection.BufferProvider;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import org.bson.ByteBuf;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/netty/NettyBufferProvider.class */
final class NettyBufferProvider implements BufferProvider {
    private final ByteBufAllocator allocator;

    NettyBufferProvider() {
        this.allocator = PooledByteBufAllocator.DEFAULT;
    }

    NettyBufferProvider(ByteBufAllocator allocator) {
        this.allocator = allocator;
    }

    @Override // com.mongodb.connection.BufferProvider
    public ByteBuf getBuffer(int size) {
        return new NettyByteBuf(this.allocator.directBuffer(size, size));
    }
}
