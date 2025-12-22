package io.netty.buffer;

/* loaded from: reader.jar:BOOT-INF/lib/netty-buffer-4.1.42.Final.jar:io/netty/buffer/ByteBufAllocator.class */
public interface ByteBufAllocator {
    public static final ByteBufAllocator DEFAULT = ByteBufUtil.DEFAULT_ALLOCATOR;

    ByteBuf buffer();

    ByteBuf buffer(int i);

    ByteBuf buffer(int i, int i2);

    ByteBuf ioBuffer();

    ByteBuf ioBuffer(int i);

    ByteBuf ioBuffer(int i, int i2);

    ByteBuf heapBuffer();

    ByteBuf heapBuffer(int i);

    ByteBuf heapBuffer(int i, int i2);

    ByteBuf directBuffer();

    ByteBuf directBuffer(int i);

    ByteBuf directBuffer(int i, int i2);

    CompositeByteBuf compositeBuffer();

    CompositeByteBuf compositeBuffer(int i);

    CompositeByteBuf compositeHeapBuffer();

    CompositeByteBuf compositeHeapBuffer(int i);

    CompositeByteBuf compositeDirectBuffer();

    CompositeByteBuf compositeDirectBuffer(int i);

    boolean isDirectBufferPooled();

    int calculateNewCapacity(int i, int i2);
}
