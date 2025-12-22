package io.vertx.core.net.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelProgressivePromise;
import io.netty.channel.ChannelPromise;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.EventExecutor;
import java.net.SocketAddress;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/PartialPooledByteBufAllocator.class */
public final class PartialPooledByteBufAllocator implements ByteBufAllocator {
    private static final ByteBufAllocator POOLED = new PooledByteBufAllocator(true);
    public static final ByteBufAllocator UNPOOLED = new UnpooledByteBufAllocator(false);
    public static final PartialPooledByteBufAllocator INSTANCE = new PartialPooledByteBufAllocator();

    private PartialPooledByteBufAllocator() {
    }

    @Override // io.netty.buffer.ByteBufAllocator
    public ByteBuf buffer() {
        return UNPOOLED.heapBuffer();
    }

    @Override // io.netty.buffer.ByteBufAllocator
    public ByteBuf buffer(int initialCapacity) {
        return UNPOOLED.heapBuffer(initialCapacity);
    }

    @Override // io.netty.buffer.ByteBufAllocator
    public ByteBuf buffer(int initialCapacity, int maxCapacity) {
        return UNPOOLED.heapBuffer(initialCapacity, maxCapacity);
    }

    @Override // io.netty.buffer.ByteBufAllocator
    public ByteBuf ioBuffer() {
        return POOLED.directBuffer();
    }

    @Override // io.netty.buffer.ByteBufAllocator
    public ByteBuf ioBuffer(int initialCapacity) {
        return POOLED.directBuffer(initialCapacity);
    }

    @Override // io.netty.buffer.ByteBufAllocator
    public ByteBuf ioBuffer(int initialCapacity, int maxCapacity) {
        return POOLED.directBuffer(initialCapacity, maxCapacity);
    }

    @Override // io.netty.buffer.ByteBufAllocator
    public ByteBuf heapBuffer() {
        return UNPOOLED.heapBuffer();
    }

    @Override // io.netty.buffer.ByteBufAllocator
    public ByteBuf heapBuffer(int initialCapacity) {
        return UNPOOLED.heapBuffer(initialCapacity);
    }

    @Override // io.netty.buffer.ByteBufAllocator
    public ByteBuf heapBuffer(int initialCapacity, int maxCapacity) {
        return UNPOOLED.heapBuffer(initialCapacity, maxCapacity);
    }

    @Override // io.netty.buffer.ByteBufAllocator
    public ByteBuf directBuffer() {
        return POOLED.directBuffer();
    }

    @Override // io.netty.buffer.ByteBufAllocator
    public ByteBuf directBuffer(int initialCapacity) {
        return POOLED.directBuffer(initialCapacity);
    }

    @Override // io.netty.buffer.ByteBufAllocator
    public ByteBuf directBuffer(int initialCapacity, int maxCapacity) {
        return POOLED.directBuffer(initialCapacity, maxCapacity);
    }

    @Override // io.netty.buffer.ByteBufAllocator
    public CompositeByteBuf compositeBuffer() {
        return UNPOOLED.compositeHeapBuffer();
    }

    @Override // io.netty.buffer.ByteBufAllocator
    public CompositeByteBuf compositeBuffer(int maxNumComponents) {
        return UNPOOLED.compositeHeapBuffer(maxNumComponents);
    }

    @Override // io.netty.buffer.ByteBufAllocator
    public CompositeByteBuf compositeHeapBuffer() {
        return UNPOOLED.compositeHeapBuffer();
    }

    @Override // io.netty.buffer.ByteBufAllocator
    public CompositeByteBuf compositeHeapBuffer(int maxNumComponents) {
        return UNPOOLED.compositeHeapBuffer(maxNumComponents);
    }

    @Override // io.netty.buffer.ByteBufAllocator
    public CompositeByteBuf compositeDirectBuffer() {
        return POOLED.compositeDirectBuffer();
    }

    @Override // io.netty.buffer.ByteBufAllocator
    public CompositeByteBuf compositeDirectBuffer(int maxNumComponents) {
        return POOLED.compositeDirectBuffer();
    }

    @Override // io.netty.buffer.ByteBufAllocator
    public boolean isDirectBufferPooled() {
        return true;
    }

    @Override // io.netty.buffer.ByteBufAllocator
    public int calculateNewCapacity(int minNewCapacity, int maxCapacity) {
        return POOLED.calculateNewCapacity(minNewCapacity, maxCapacity);
    }

    public static ChannelHandlerContext forceDirectAllocator(ChannelHandlerContext ctx) {
        return new PooledChannelHandlerContext(ctx);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/PartialPooledByteBufAllocator$PooledChannelHandlerContext.class */
    private static final class PooledChannelHandlerContext implements ChannelHandlerContext {
        private final ChannelHandlerContext ctx;

        PooledChannelHandlerContext(ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override // io.netty.channel.ChannelHandlerContext, io.netty.util.AttributeMap
        public <T> boolean hasAttr(AttributeKey<T> attributeKey) {
            return this.ctx.channel().hasAttr(attributeKey);
        }

        @Override // io.netty.channel.ChannelHandlerContext
        public Channel channel() {
            return this.ctx.channel();
        }

        @Override // io.netty.channel.ChannelHandlerContext
        public EventExecutor executor() {
            return this.ctx.executor();
        }

        @Override // io.netty.channel.ChannelHandlerContext
        public String name() {
            return this.ctx.name();
        }

        @Override // io.netty.channel.ChannelHandlerContext
        public ChannelHandler handler() {
            return this.ctx.handler();
        }

        @Override // io.netty.channel.ChannelHandlerContext
        public boolean isRemoved() {
            return this.ctx.isRemoved();
        }

        @Override // io.netty.channel.ChannelInboundInvoker
        public ChannelHandlerContext fireChannelRegistered() {
            this.ctx.fireChannelRegistered();
            return this;
        }

        @Override // io.netty.channel.ChannelInboundInvoker
        @Deprecated
        public ChannelHandlerContext fireChannelUnregistered() {
            this.ctx.fireChannelUnregistered();
            return this;
        }

        @Override // io.netty.channel.ChannelInboundInvoker
        public ChannelHandlerContext fireChannelActive() {
            this.ctx.fireChannelActive();
            return this;
        }

        @Override // io.netty.channel.ChannelInboundInvoker
        public ChannelHandlerContext fireChannelInactive() {
            this.ctx.fireChannelInactive();
            return this;
        }

        @Override // io.netty.channel.ChannelInboundInvoker
        public ChannelHandlerContext fireExceptionCaught(Throwable cause) {
            this.ctx.fireExceptionCaught(cause);
            return this;
        }

        @Override // io.netty.channel.ChannelInboundInvoker
        public ChannelHandlerContext fireUserEventTriggered(Object event) {
            this.ctx.fireUserEventTriggered(event);
            return this;
        }

        @Override // io.netty.channel.ChannelInboundInvoker
        public ChannelHandlerContext fireChannelRead(Object msg) {
            this.ctx.fireChannelRead(msg);
            return this;
        }

        @Override // io.netty.channel.ChannelInboundInvoker
        public ChannelHandlerContext fireChannelReadComplete() {
            this.ctx.fireChannelReadComplete();
            return this;
        }

        @Override // io.netty.channel.ChannelInboundInvoker
        public ChannelHandlerContext fireChannelWritabilityChanged() {
            this.ctx.fireChannelWritabilityChanged();
            return this;
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture bind(SocketAddress localAddress) {
            return this.ctx.bind(localAddress);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture connect(SocketAddress remoteAddress) {
            return this.ctx.connect(remoteAddress);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture connect(SocketAddress remoteAddress, SocketAddress localAddress) {
            return this.ctx.connect(remoteAddress, localAddress);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture disconnect() {
            return this.ctx.disconnect();
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture close() {
            return this.ctx.close();
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        @Deprecated
        public ChannelFuture deregister() {
            return this.ctx.deregister();
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture bind(SocketAddress localAddress, ChannelPromise promise) {
            return this.ctx.bind(localAddress, promise);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture connect(SocketAddress remoteAddress, ChannelPromise promise) {
            return this.ctx.connect(remoteAddress, promise);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture connect(SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) {
            return this.ctx.connect(remoteAddress, localAddress, promise);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture disconnect(ChannelPromise promise) {
            return this.ctx.disconnect(promise);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture close(ChannelPromise promise) {
            return this.ctx.close(promise);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        @Deprecated
        public ChannelFuture deregister(ChannelPromise promise) {
            return this.ctx.deregister(promise);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelHandlerContext read() {
            this.ctx.read();
            return this;
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture write(Object msg) {
            return this.ctx.write(msg);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture write(Object msg, ChannelPromise promise) {
            return this.ctx.write(msg, promise);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelHandlerContext flush() {
            this.ctx.flush();
            return this;
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture writeAndFlush(Object msg, ChannelPromise promise) {
            return this.ctx.writeAndFlush(msg, promise);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture writeAndFlush(Object msg) {
            return this.ctx.writeAndFlush(msg);
        }

        @Override // io.netty.channel.ChannelHandlerContext
        public ChannelPipeline pipeline() {
            return this.ctx.pipeline();
        }

        @Override // io.netty.channel.ChannelHandlerContext
        public ByteBufAllocator alloc() {
            return ForceDirectPoooledByteBufAllocator.INSTANCE;
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelPromise newPromise() {
            return this.ctx.newPromise();
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelProgressivePromise newProgressivePromise() {
            return this.ctx.newProgressivePromise();
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture newSucceededFuture() {
            return this.ctx.newSucceededFuture();
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture newFailedFuture(Throwable cause) {
            return this.ctx.newFailedFuture(cause);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelPromise voidPromise() {
            return this.ctx.voidPromise();
        }

        @Override // io.netty.channel.ChannelHandlerContext, io.netty.util.AttributeMap
        public <T> Attribute<T> attr(AttributeKey<T> key) {
            return this.ctx.channel().attr(key);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/PartialPooledByteBufAllocator$ForceDirectPoooledByteBufAllocator.class */
    private static final class ForceDirectPoooledByteBufAllocator implements ByteBufAllocator {
        static ByteBufAllocator INSTANCE = new ForceDirectPoooledByteBufAllocator();

        private ForceDirectPoooledByteBufAllocator() {
        }

        @Override // io.netty.buffer.ByteBufAllocator
        public ByteBuf buffer() {
            return PartialPooledByteBufAllocator.INSTANCE.directBuffer();
        }

        @Override // io.netty.buffer.ByteBufAllocator
        public ByteBuf buffer(int initialCapacity) {
            return PartialPooledByteBufAllocator.INSTANCE.directBuffer(initialCapacity);
        }

        @Override // io.netty.buffer.ByteBufAllocator
        public ByteBuf buffer(int initialCapacity, int maxCapacity) {
            return PartialPooledByteBufAllocator.INSTANCE.directBuffer(initialCapacity, maxCapacity);
        }

        @Override // io.netty.buffer.ByteBufAllocator
        public ByteBuf ioBuffer() {
            return PartialPooledByteBufAllocator.INSTANCE.directBuffer();
        }

        @Override // io.netty.buffer.ByteBufAllocator
        public ByteBuf ioBuffer(int initialCapacity) {
            return PartialPooledByteBufAllocator.INSTANCE.directBuffer(initialCapacity);
        }

        @Override // io.netty.buffer.ByteBufAllocator
        public ByteBuf ioBuffer(int initialCapacity, int maxCapacity) {
            return PartialPooledByteBufAllocator.INSTANCE.directBuffer(initialCapacity, maxCapacity);
        }

        @Override // io.netty.buffer.ByteBufAllocator
        public ByteBuf heapBuffer() {
            return PartialPooledByteBufAllocator.INSTANCE.heapBuffer();
        }

        @Override // io.netty.buffer.ByteBufAllocator
        public ByteBuf heapBuffer(int initialCapacity) {
            return PartialPooledByteBufAllocator.INSTANCE.heapBuffer(initialCapacity);
        }

        @Override // io.netty.buffer.ByteBufAllocator
        public ByteBuf heapBuffer(int initialCapacity, int maxCapacity) {
            return PartialPooledByteBufAllocator.INSTANCE.heapBuffer(initialCapacity, maxCapacity);
        }

        @Override // io.netty.buffer.ByteBufAllocator
        public ByteBuf directBuffer() {
            return PartialPooledByteBufAllocator.INSTANCE.directBuffer();
        }

        @Override // io.netty.buffer.ByteBufAllocator
        public ByteBuf directBuffer(int initialCapacity) {
            return PartialPooledByteBufAllocator.INSTANCE.directBuffer(initialCapacity);
        }

        @Override // io.netty.buffer.ByteBufAllocator
        public ByteBuf directBuffer(int initialCapacity, int maxCapacity) {
            return PartialPooledByteBufAllocator.INSTANCE.directBuffer(initialCapacity, maxCapacity);
        }

        @Override // io.netty.buffer.ByteBufAllocator
        public CompositeByteBuf compositeBuffer() {
            return PartialPooledByteBufAllocator.INSTANCE.compositeBuffer();
        }

        @Override // io.netty.buffer.ByteBufAllocator
        public CompositeByteBuf compositeBuffer(int maxNumComponents) {
            return PartialPooledByteBufAllocator.INSTANCE.compositeBuffer(maxNumComponents);
        }

        @Override // io.netty.buffer.ByteBufAllocator
        public CompositeByteBuf compositeHeapBuffer() {
            return PartialPooledByteBufAllocator.INSTANCE.compositeHeapBuffer();
        }

        @Override // io.netty.buffer.ByteBufAllocator
        public CompositeByteBuf compositeHeapBuffer(int maxNumComponents) {
            return PartialPooledByteBufAllocator.INSTANCE.compositeHeapBuffer(maxNumComponents);
        }

        @Override // io.netty.buffer.ByteBufAllocator
        public CompositeByteBuf compositeDirectBuffer() {
            return PartialPooledByteBufAllocator.INSTANCE.compositeDirectBuffer();
        }

        @Override // io.netty.buffer.ByteBufAllocator
        public CompositeByteBuf compositeDirectBuffer(int maxNumComponents) {
            return PartialPooledByteBufAllocator.INSTANCE.compositeDirectBuffer(maxNumComponents);
        }

        @Override // io.netty.buffer.ByteBufAllocator
        public boolean isDirectBufferPooled() {
            return PartialPooledByteBufAllocator.INSTANCE.isDirectBufferPooled();
        }

        @Override // io.netty.buffer.ByteBufAllocator
        public int calculateNewCapacity(int minNewCapacity, int maxCapacity) {
            return PartialPooledByteBufAllocator.INSTANCE.calculateNewCapacity(minNewCapacity, maxCapacity);
        }
    }
}
