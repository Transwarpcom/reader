package io.netty.buffer;

import io.netty.util.ReferenceCounted;

/* loaded from: reader.jar:BOOT-INF/lib/netty-buffer-4.1.42.Final.jar:io/netty/buffer/ByteBufHolder.class */
public interface ByteBufHolder extends ReferenceCounted {
    ByteBuf content();

    ByteBufHolder copy();

    ByteBufHolder duplicate();

    ByteBufHolder retainedDuplicate();

    ByteBufHolder replace(ByteBuf byteBuf);

    @Override // io.netty.util.ReferenceCounted
    ByteBufHolder retain();

    @Override // io.netty.util.ReferenceCounted
    ByteBufHolder retain(int i);

    @Override // io.netty.util.ReferenceCounted
    ByteBufHolder touch();

    @Override // io.netty.util.ReferenceCounted
    ByteBufHolder touch(Object obj);
}
