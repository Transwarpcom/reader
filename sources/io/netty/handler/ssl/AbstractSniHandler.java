package io.netty.handler.ssl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DecoderException;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.SocketAddress;
import java.util.List;
import java.util.Locale;

/* loaded from: reader.jar:BOOT-INF/lib/netty-handler-4.1.42.Final.jar:io/netty/handler/ssl/AbstractSniHandler.class */
public abstract class AbstractSniHandler<T> extends ByteToMessageDecoder implements ChannelOutboundHandler {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) AbstractSniHandler.class);
    private boolean handshakeFailed;
    private boolean suppressRead;
    private boolean readPending;

    protected abstract Future<T> lookup(ChannelHandlerContext channelHandlerContext, String str) throws Exception;

    protected abstract void onLookupComplete(ChannelHandlerContext channelHandlerContext, String str, Future<T> future) throws Exception;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int readerIndex;
        int readableBytes;
        if (!this.suppressRead && !this.handshakeFailed) {
            try {
                readerIndex = in.readerIndex();
                readableBytes = in.readableBytes();
            } catch (NotSslRecordException e) {
                throw e;
            } catch (Exception e2) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Unexpected client hello packet: " + ByteBufUtil.hexDump(in), (Throwable) e2);
                }
            }
            if (readableBytes < 5) {
                return;
            }
            int command = in.getUnsignedByte(readerIndex);
            switch (command) {
                case 20:
                case 21:
                    int len = SslUtils.getEncryptedPacketLength(in, readerIndex);
                    if (len == -2) {
                        this.handshakeFailed = true;
                        NotSslRecordException e3 = new NotSslRecordException("not an SSL/TLS record: " + ByteBufUtil.hexDump(in));
                        in.skipBytes(in.readableBytes());
                        ctx.fireUserEventTriggered((Object) new SniCompletionEvent(e3));
                        SslUtils.handleHandshakeFailure(ctx, e3, true);
                        throw e3;
                    }
                    if (len == -1) {
                        return;
                    }
                    select(ctx, null);
                    return;
                case 22:
                    int majorVersion = in.getUnsignedByte(readerIndex + 1);
                    if (majorVersion == 3) {
                        int packetLength = in.getUnsignedShort(readerIndex + 3) + 5;
                        if (readableBytes < packetLength) {
                            return;
                        }
                        int endOffset = readerIndex + packetLength;
                        int offset = readerIndex + 43;
                        if (endOffset - offset >= 6) {
                            int sessionIdLength = in.getUnsignedByte(offset);
                            int offset2 = offset + sessionIdLength + 1;
                            int cipherSuitesLength = in.getUnsignedShort(offset2);
                            int offset3 = offset2 + cipherSuitesLength + 2;
                            int compressionMethodLength = in.getUnsignedByte(offset3);
                            int offset4 = offset3 + compressionMethodLength + 1;
                            int extensionsLength = in.getUnsignedShort(offset4);
                            int offset5 = offset4 + 2;
                            int extensionsLimit = offset5 + extensionsLength;
                            if (extensionsLimit <= endOffset) {
                                while (true) {
                                    if (extensionsLimit - offset5 >= 4) {
                                        int extensionType = in.getUnsignedShort(offset5);
                                        int offset6 = offset5 + 2;
                                        int extensionLength = in.getUnsignedShort(offset6);
                                        int offset7 = offset6 + 2;
                                        if (extensionsLimit - offset7 >= extensionLength) {
                                            if (extensionType == 0) {
                                                int offset8 = offset7 + 2;
                                                if (extensionsLimit - offset8 >= 3) {
                                                    int serverNameType = in.getUnsignedByte(offset8);
                                                    int offset9 = offset8 + 1;
                                                    if (serverNameType == 0) {
                                                        int serverNameLength = in.getUnsignedShort(offset9);
                                                        int offset10 = offset9 + 2;
                                                        if (extensionsLimit - offset10 >= serverNameLength) {
                                                            String hostname = in.toString(offset10, serverNameLength, CharsetUtil.US_ASCII);
                                                            try {
                                                                select(ctx, hostname.toLowerCase(Locale.US));
                                                                return;
                                                            } catch (Throwable t) {
                                                                PlatformDependent.throwException(t);
                                                                return;
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                offset5 = offset7 + extensionLength;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    select(ctx, null);
                    return;
                default:
                    select(ctx, null);
                    return;
            }
        }
    }

    private void select(final ChannelHandlerContext ctx, final String hostname) throws Exception {
        Future<T> future = lookup(ctx, hostname);
        if (future.isDone()) {
            fireSniCompletionEvent(ctx, hostname, future);
            onLookupComplete(ctx, hostname, future);
        } else {
            this.suppressRead = true;
            future.addListener2(new FutureListener<T>() { // from class: io.netty.handler.ssl.AbstractSniHandler.1
                @Override // io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(Future<T> future2) throws Exception {
                    try {
                        AbstractSniHandler.this.suppressRead = false;
                        try {
                            AbstractSniHandler.this.fireSniCompletionEvent(ctx, hostname, future2);
                            AbstractSniHandler.this.onLookupComplete(ctx, hostname, future2);
                        } catch (DecoderException err) {
                            ctx.fireExceptionCaught((Throwable) err);
                        } catch (Exception cause) {
                            ctx.fireExceptionCaught((Throwable) new DecoderException(cause));
                        } catch (Throwable cause2) {
                            ctx.fireExceptionCaught(cause2);
                        }
                    } finally {
                        if (AbstractSniHandler.this.readPending) {
                            AbstractSniHandler.this.readPending = false;
                            ctx.read();
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fireSniCompletionEvent(ChannelHandlerContext ctx, String hostname, Future<T> future) {
        Throwable cause = future.cause();
        if (cause == null) {
            ctx.fireUserEventTriggered((Object) new SniCompletionEvent(hostname));
        } else {
            ctx.fireUserEventTriggered((Object) new SniCompletionEvent(hostname, cause));
        }
    }

    @Override // io.netty.channel.ChannelOutboundHandler
    public void read(ChannelHandlerContext ctx) throws Exception {
        if (this.suppressRead) {
            this.readPending = true;
        } else {
            ctx.read();
        }
    }

    @Override // io.netty.channel.ChannelOutboundHandler
    public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        ctx.bind(localAddress, promise);
    }

    @Override // io.netty.channel.ChannelOutboundHandler
    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        ctx.connect(remoteAddress, localAddress, promise);
    }

    @Override // io.netty.channel.ChannelOutboundHandler
    public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        ctx.disconnect(promise);
    }

    @Override // io.netty.channel.ChannelOutboundHandler
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        ctx.close(promise);
    }

    @Override // io.netty.channel.ChannelOutboundHandler
    public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        ctx.deregister(promise);
    }

    @Override // io.netty.channel.ChannelOutboundHandler
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ctx.write(msg, promise);
    }

    @Override // io.netty.channel.ChannelOutboundHandler
    public void flush(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
