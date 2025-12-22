package io.netty.handler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.ChannelInputShutdownEvent;
import io.netty.util.internal.ObjectUtil;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-4.1.42.Final.jar:io/netty/handler/codec/ByteToMessageDecoder.class */
public abstract class ByteToMessageDecoder extends ChannelInboundHandlerAdapter {
    public static final Cumulator MERGE_CUMULATOR = new Cumulator() { // from class: io.netty.handler.codec.ByteToMessageDecoder.1
        @Override // io.netty.handler.codec.ByteToMessageDecoder.Cumulator
        public ByteBuf cumulate(ByteBufAllocator alloc, ByteBuf cumulation, ByteBuf in) {
            ByteBuf buffer;
            try {
                if (cumulation.writerIndex() > cumulation.maxCapacity() - in.readableBytes() || cumulation.refCnt() > 1 || cumulation.isReadOnly()) {
                    buffer = ByteToMessageDecoder.expandCumulation(alloc, cumulation, in.readableBytes());
                } else {
                    buffer = cumulation;
                }
                buffer.writeBytes(in);
                ByteBuf byteBuf = buffer;
                in.release();
                return byteBuf;
            } catch (Throwable th) {
                in.release();
                throw th;
            }
        }
    };
    public static final Cumulator COMPOSITE_CUMULATOR = new Cumulator() { // from class: io.netty.handler.codec.ByteToMessageDecoder.2
        @Override // io.netty.handler.codec.ByteToMessageDecoder.Cumulator
        public ByteBuf cumulate(ByteBufAllocator alloc, ByteBuf cumulation, ByteBuf in) {
            CompositeByteBuf composite;
            ByteBuf buffer;
            try {
                if (cumulation.refCnt() > 1) {
                    buffer = ByteToMessageDecoder.expandCumulation(alloc, cumulation, in.readableBytes());
                    buffer.writeBytes(in);
                } else {
                    if (cumulation instanceof CompositeByteBuf) {
                        composite = (CompositeByteBuf) cumulation;
                    } else {
                        composite = alloc.compositeBuffer(Integer.MAX_VALUE);
                        composite.addComponent(true, cumulation);
                    }
                    composite.addComponent(true, in);
                    in = null;
                    buffer = composite;
                }
                ByteBuf byteBuf = buffer;
                if (in != null) {
                    in.release();
                }
                return byteBuf;
            } catch (Throwable th) {
                if (in != null) {
                    in.release();
                }
                throw th;
            }
        }
    };
    private static final byte STATE_INIT = 0;
    private static final byte STATE_CALLING_CHILD_DECODE = 1;
    private static final byte STATE_HANDLER_REMOVED_PENDING = 2;
    ByteBuf cumulation;
    private boolean singleDecode;
    private boolean first;
    private boolean firedChannelRead;
    private int numReads;
    private Cumulator cumulator = MERGE_CUMULATOR;
    private byte decodeState = 0;
    private int discardAfterReads = 16;

    /* loaded from: reader.jar:BOOT-INF/lib/netty-codec-4.1.42.Final.jar:io/netty/handler/codec/ByteToMessageDecoder$Cumulator.class */
    public interface Cumulator {
        ByteBuf cumulate(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf, ByteBuf byteBuf2);
    }

    protected abstract void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception;

    protected ByteToMessageDecoder() {
        ensureNotSharable();
    }

    public void setSingleDecode(boolean singleDecode) {
        this.singleDecode = singleDecode;
    }

    public boolean isSingleDecode() {
        return this.singleDecode;
    }

    public void setCumulator(Cumulator cumulator) {
        if (cumulator == null) {
            throw new NullPointerException("cumulator");
        }
        this.cumulator = cumulator;
    }

    public void setDiscardAfterReads(int discardAfterReads) {
        ObjectUtil.checkPositive(discardAfterReads, "discardAfterReads");
        this.discardAfterReads = discardAfterReads;
    }

    protected int actualReadableBytes() {
        return internalBuffer().readableBytes();
    }

    protected ByteBuf internalBuffer() {
        if (this.cumulation != null) {
            return this.cumulation;
        }
        return Unpooled.EMPTY_BUFFER;
    }

    @Override // io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler
    public final void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (this.decodeState == 1) {
            this.decodeState = (byte) 2;
            return;
        }
        ByteBuf buf = this.cumulation;
        if (buf != null) {
            this.cumulation = null;
            this.numReads = 0;
            int readable = buf.readableBytes();
            if (readable > 0) {
                ByteBuf bytes = buf.readBytes(readable);
                buf.release();
                ctx.fireChannelRead((Object) bytes);
                ctx.fireChannelReadComplete();
            } else {
                buf.release();
            }
        }
        handlerRemoved0(ctx);
    }

    protected void handlerRemoved0(ChannelHandlerContext ctx) throws Exception {
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof ByteBuf) {
            CodecOutputList out = CodecOutputList.newInstance();
            try {
                try {
                    ByteBuf data = (ByteBuf) msg;
                    this.first = this.cumulation == null;
                    if (this.first) {
                        this.cumulation = data;
                    } else {
                        this.cumulation = this.cumulator.cumulate(ctx.alloc(), this.cumulation, data);
                    }
                    callDecode(ctx, this.cumulation, out);
                    if (this.cumulation != null && !this.cumulation.isReadable()) {
                        this.numReads = 0;
                        this.cumulation.release();
                        this.cumulation = null;
                    } else {
                        int i = this.numReads + 1;
                        this.numReads = i;
                        if (i >= this.discardAfterReads) {
                            this.numReads = 0;
                            discardSomeReadBytes();
                        }
                    }
                    int size = out.size();
                    this.firedChannelRead |= out.insertSinceRecycled();
                    fireChannelRead(ctx, out, size);
                    out.recycle();
                    return;
                } catch (DecoderException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new DecoderException(e2);
                }
            } catch (Throwable th) {
                if (this.cumulation != null && !this.cumulation.isReadable()) {
                    this.numReads = 0;
                    this.cumulation.release();
                    this.cumulation = null;
                } else {
                    int i2 = this.numReads + 1;
                    this.numReads = i2;
                    if (i2 >= this.discardAfterReads) {
                        this.numReads = 0;
                        discardSomeReadBytes();
                    }
                }
                int size2 = out.size();
                this.firedChannelRead |= out.insertSinceRecycled();
                fireChannelRead(ctx, out, size2);
                out.recycle();
                throw th;
            }
        }
        ctx.fireChannelRead(msg);
    }

    static void fireChannelRead(ChannelHandlerContext ctx, List<Object> msgs, int numElements) {
        if (msgs instanceof CodecOutputList) {
            fireChannelRead(ctx, (CodecOutputList) msgs, numElements);
            return;
        }
        for (int i = 0; i < numElements; i++) {
            ctx.fireChannelRead(msgs.get(i));
        }
    }

    static void fireChannelRead(ChannelHandlerContext ctx, CodecOutputList msgs, int numElements) {
        for (int i = 0; i < numElements; i++) {
            ctx.fireChannelRead(msgs.getUnsafe(i));
        }
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        this.numReads = 0;
        discardSomeReadBytes();
        if (!this.firedChannelRead && !ctx.channel().config().isAutoRead()) {
            ctx.read();
        }
        this.firedChannelRead = false;
        ctx.fireChannelReadComplete();
    }

    protected final void discardSomeReadBytes() {
        if (this.cumulation != null && !this.first && this.cumulation.refCnt() == 1) {
            this.cumulation.discardSomeReadBytes();
        }
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        channelInputClosed(ctx, true);
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof ChannelInputShutdownEvent) {
            channelInputClosed(ctx, false);
        }
        super.userEventTriggered(ctx, evt);
    }

    private void channelInputClosed(ChannelHandlerContext ctx, boolean callChannelInactive) throws Exception {
        CodecOutputList out = CodecOutputList.newInstance();
        try {
            try {
                try {
                    channelInputClosed(ctx, out);
                    try {
                        if (this.cumulation != null) {
                            this.cumulation.release();
                            this.cumulation = null;
                        }
                        int size = out.size();
                        fireChannelRead(ctx, out, size);
                        if (size > 0) {
                            ctx.fireChannelReadComplete();
                        }
                        if (callChannelInactive) {
                            ctx.fireChannelInactive();
                        }
                        out.recycle();
                    } finally {
                    }
                } catch (DecoderException e) {
                    throw e;
                }
            } catch (Exception e2) {
                throw new DecoderException(e2);
            }
        } catch (Throwable th) {
            try {
                if (this.cumulation != null) {
                    this.cumulation.release();
                    this.cumulation = null;
                }
                int size2 = out.size();
                fireChannelRead(ctx, out, size2);
                if (size2 > 0) {
                    ctx.fireChannelReadComplete();
                }
                if (callChannelInactive) {
                    ctx.fireChannelInactive();
                }
                out.recycle();
                throw th;
            } finally {
            }
        }
    }

    void channelInputClosed(ChannelHandlerContext ctx, List<Object> out) throws Exception {
        if (this.cumulation != null) {
            callDecode(ctx, this.cumulation, out);
            decodeLast(ctx, this.cumulation, out);
        } else {
            decodeLast(ctx, Unpooled.EMPTY_BUFFER, out);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0049 A[Catch: DecoderException -> 0x009a, Exception -> 0x009f, TryCatch #2 {DecoderException -> 0x009a, Exception -> 0x009f, blocks: (B:2:0x0000, B:4:0x0007, B:6:0x0014, B:10:0x0030, B:13:0x0049, B:15:0x0054, B:18:0x0060, B:20:0x0069, B:21:0x0089, B:22:0x008a), top: B:34:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0046 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void callDecode(io.netty.channel.ChannelHandlerContext r6, io.netty.buffer.ByteBuf r7, java.util.List<java.lang.Object> r8) {
        /*
            r5 = this;
        L0:
            r0 = r7
            boolean r0 = r0.isReadable()     // Catch: io.netty.handler.codec.DecoderException -> L9a java.lang.Exception -> L9f
            if (r0 == 0) goto L97
            r0 = r8
            int r0 = r0.size()     // Catch: io.netty.handler.codec.DecoderException -> L9a java.lang.Exception -> L9f
            r9 = r0
            r0 = r9
            if (r0 <= 0) goto L30
            r0 = r6
            r1 = r8
            r2 = r9
            fireChannelRead(r0, r1, r2)     // Catch: io.netty.handler.codec.DecoderException -> L9a java.lang.Exception -> L9f
            r0 = r8
            r0.clear()     // Catch: io.netty.handler.codec.DecoderException -> L9a java.lang.Exception -> L9f
            r0 = r6
            boolean r0 = r0.isRemoved()     // Catch: io.netty.handler.codec.DecoderException -> L9a java.lang.Exception -> L9f
            if (r0 == 0) goto L2d
            goto L97
        L2d:
            r0 = 0
            r9 = r0
        L30:
            r0 = r7
            int r0 = r0.readableBytes()     // Catch: io.netty.handler.codec.DecoderException -> L9a java.lang.Exception -> L9f
            r10 = r0
            r0 = r5
            r1 = r6
            r2 = r7
            r3 = r8
            r0.decodeRemovalReentryProtection(r1, r2, r3)     // Catch: io.netty.handler.codec.DecoderException -> L9a java.lang.Exception -> L9f
            r0 = r6
            boolean r0 = r0.isRemoved()     // Catch: io.netty.handler.codec.DecoderException -> L9a java.lang.Exception -> L9f
            if (r0 == 0) goto L49
            goto L97
        L49:
            r0 = r9
            r1 = r8
            int r1 = r1.size()     // Catch: io.netty.handler.codec.DecoderException -> L9a java.lang.Exception -> L9f
            if (r0 != r1) goto L60
            r0 = r10
            r1 = r7
            int r1 = r1.readableBytes()     // Catch: io.netty.handler.codec.DecoderException -> L9a java.lang.Exception -> L9f
            if (r0 != r1) goto L0
            goto L97
        L60:
            r0 = r10
            r1 = r7
            int r1 = r1.readableBytes()     // Catch: io.netty.handler.codec.DecoderException -> L9a java.lang.Exception -> L9f
            if (r0 != r1) goto L8a
            io.netty.handler.codec.DecoderException r0 = new io.netty.handler.codec.DecoderException     // Catch: io.netty.handler.codec.DecoderException -> L9a java.lang.Exception -> L9f
            r1 = r0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: io.netty.handler.codec.DecoderException -> L9a java.lang.Exception -> L9f
            r3 = r2
            r3.<init>()     // Catch: io.netty.handler.codec.DecoderException -> L9a java.lang.Exception -> L9f
            r3 = r5
            java.lang.Class r3 = r3.getClass()     // Catch: io.netty.handler.codec.DecoderException -> L9a java.lang.Exception -> L9f
            java.lang.String r3 = io.netty.util.internal.StringUtil.simpleClassName(r3)     // Catch: io.netty.handler.codec.DecoderException -> L9a java.lang.Exception -> L9f
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: io.netty.handler.codec.DecoderException -> L9a java.lang.Exception -> L9f
            java.lang.String r3 = ".decode() did not read anything but decoded a message."
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: io.netty.handler.codec.DecoderException -> L9a java.lang.Exception -> L9f
            java.lang.String r2 = r2.toString()     // Catch: io.netty.handler.codec.DecoderException -> L9a java.lang.Exception -> L9f
            r1.<init>(r2)     // Catch: io.netty.handler.codec.DecoderException -> L9a java.lang.Exception -> L9f
            throw r0     // Catch: io.netty.handler.codec.DecoderException -> L9a java.lang.Exception -> L9f
        L8a:
            r0 = r5
            boolean r0 = r0.isSingleDecode()     // Catch: io.netty.handler.codec.DecoderException -> L9a java.lang.Exception -> L9f
            if (r0 == 0) goto L94
            goto L97
        L94:
            goto L0
        L97:
            goto Lab
        L9a:
            r9 = move-exception
            r0 = r9
            throw r0
        L9f:
            r9 = move-exception
            io.netty.handler.codec.DecoderException r0 = new io.netty.handler.codec.DecoderException
            r1 = r0
            r2 = r9
            r1.<init>(r2)
            throw r0
        Lab:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.ByteToMessageDecoder.callDecode(io.netty.channel.ChannelHandlerContext, io.netty.buffer.ByteBuf, java.util.List):void");
    }

    final void decodeRemovalReentryProtection(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        this.decodeState = (byte) 1;
        try {
            decode(ctx, in, out);
            boolean removePending = this.decodeState == 2;
            this.decodeState = (byte) 0;
            if (removePending) {
                handlerRemoved(ctx);
            }
        } catch (Throwable th) {
            boolean removePending2 = this.decodeState == 2;
            this.decodeState = (byte) 0;
            if (removePending2) {
                handlerRemoved(ctx);
            }
            throw th;
        }
    }

    protected void decodeLast(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.isReadable()) {
            decodeRemovalReentryProtection(ctx, in, out);
        }
    }

    static ByteBuf expandCumulation(ByteBufAllocator alloc, ByteBuf cumulation, int readable) {
        ByteBuf cumulation2 = alloc.buffer(cumulation.readableBytes() + readable);
        cumulation2.writeBytes(cumulation);
        cumulation.release();
        return cumulation2;
    }
}
