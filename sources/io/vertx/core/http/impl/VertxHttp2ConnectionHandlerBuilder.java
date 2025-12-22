package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder;
import io.netty.handler.codec.http2.CompressorHttp2ConnectionEncoder;
import io.netty.handler.codec.http2.Http2ConnectionDecoder;
import io.netty.handler.codec.http2.Http2ConnectionEncoder;
import io.netty.handler.codec.http2.Http2Exception;
import io.netty.handler.codec.http2.Http2Flags;
import io.netty.handler.codec.http2.Http2FrameListener;
import io.netty.handler.codec.http2.Http2FrameLogger;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.handler.logging.LogLevel;
import io.vertx.core.http.Http2Settings;
import io.vertx.core.http.impl.Http2ConnectionBase;
import java.util.function.Function;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/VertxHttp2ConnectionHandlerBuilder.class */
class VertxHttp2ConnectionHandlerBuilder<C extends Http2ConnectionBase> extends AbstractHttp2ConnectionHandlerBuilder<VertxHttp2ConnectionHandler<C>, VertxHttp2ConnectionHandlerBuilder<C>> {
    private boolean useCompression;
    private boolean useDecompression;
    private int compressionLevel = 6;
    private Http2Settings initialSettings;
    private Function<VertxHttp2ConnectionHandler<C>, C> connectionFactory;
    private boolean logEnabled;

    VertxHttp2ConnectionHandlerBuilder() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public VertxHttp2ConnectionHandlerBuilder<C> server(boolean isServer) {
        return (VertxHttp2ConnectionHandlerBuilder) super.server(isServer);
    }

    VertxHttp2ConnectionHandlerBuilder<C> initialSettings(Http2Settings settings) {
        this.initialSettings = settings;
        return this;
    }

    VertxHttp2ConnectionHandlerBuilder<C> useCompression(boolean useCompression) {
        this.useCompression = useCompression;
        return this;
    }

    VertxHttp2ConnectionHandlerBuilder<C> compressionLevel(int compressionLevel) {
        this.compressionLevel = compressionLevel;
        return this;
    }

    VertxHttp2ConnectionHandlerBuilder<C> useDecompression(boolean useDecompression) {
        this.useDecompression = useDecompression;
        return this;
    }

    VertxHttp2ConnectionHandlerBuilder<C> connectionFactory(Function<VertxHttp2ConnectionHandler<C>, C> connectionFactory) {
        this.connectionFactory = connectionFactory;
        return this;
    }

    VertxHttp2ConnectionHandlerBuilder<C> logEnabled(boolean logEnabled) {
        this.logEnabled = logEnabled;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public VertxHttp2ConnectionHandler<C> build() {
        if (this.initialSettings != null) {
            HttpUtils.fromVertxInitialSettings(isServer(), this.initialSettings, initialSettings());
        }
        if (this.logEnabled) {
            frameLogger(new Http2FrameLogger(LogLevel.DEBUG));
        }
        frameListener(new Http2FrameListener() { // from class: io.vertx.core.http.impl.VertxHttp2ConnectionHandlerBuilder.1
            @Override // io.netty.handler.codec.http2.Http2FrameListener
            public int onDataRead(ChannelHandlerContext ctx, int streamId, ByteBuf data, int padding, boolean endOfStream) throws Http2Exception {
                throw new UnsupportedOperationException();
            }

            @Override // io.netty.handler.codec.http2.Http2FrameListener
            public void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int padding, boolean endOfStream) throws Http2Exception {
                throw new UnsupportedOperationException();
            }

            @Override // io.netty.handler.codec.http2.Http2FrameListener
            public void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int streamDependency, short weight, boolean exclusive, int padding, boolean endOfStream) throws Http2Exception {
                throw new UnsupportedOperationException();
            }

            @Override // io.netty.handler.codec.http2.Http2FrameListener
            public void onPriorityRead(ChannelHandlerContext ctx, int streamId, int streamDependency, short weight, boolean exclusive) throws Http2Exception {
                throw new UnsupportedOperationException();
            }

            @Override // io.netty.handler.codec.http2.Http2FrameListener
            public void onRstStreamRead(ChannelHandlerContext ctx, int streamId, long errorCode) throws Http2Exception {
                throw new UnsupportedOperationException();
            }

            @Override // io.netty.handler.codec.http2.Http2FrameListener
            public void onSettingsAckRead(ChannelHandlerContext ctx) throws Http2Exception {
                throw new UnsupportedOperationException();
            }

            @Override // io.netty.handler.codec.http2.Http2FrameListener
            public void onSettingsRead(ChannelHandlerContext ctx, io.netty.handler.codec.http2.Http2Settings settings) throws Http2Exception {
                throw new UnsupportedOperationException();
            }

            @Override // io.netty.handler.codec.http2.Http2FrameListener
            public void onPingRead(ChannelHandlerContext channelHandlerContext, long l) throws Http2Exception {
                throw new UnsupportedOperationException();
            }

            @Override // io.netty.handler.codec.http2.Http2FrameListener
            public void onPingAckRead(ChannelHandlerContext channelHandlerContext, long l) throws Http2Exception {
                throw new UnsupportedOperationException();
            }

            @Override // io.netty.handler.codec.http2.Http2FrameListener
            public void onPushPromiseRead(ChannelHandlerContext ctx, int streamId, int promisedStreamId, Http2Headers headers, int padding) throws Http2Exception {
                throw new UnsupportedOperationException();
            }

            @Override // io.netty.handler.codec.http2.Http2FrameListener
            public void onGoAwayRead(ChannelHandlerContext ctx, int lastStreamId, long errorCode, ByteBuf debugData) throws Http2Exception {
                throw new UnsupportedOperationException();
            }

            @Override // io.netty.handler.codec.http2.Http2FrameListener
            public void onWindowUpdateRead(ChannelHandlerContext ctx, int streamId, int windowSizeIncrement) throws Http2Exception {
                throw new UnsupportedOperationException();
            }

            @Override // io.netty.handler.codec.http2.Http2FrameListener
            public void onUnknownFrame(ChannelHandlerContext ctx, byte frameType, int streamId, Http2Flags flags, ByteBuf payload) throws Http2Exception {
                throw new UnsupportedOperationException();
            }
        });
        return (VertxHttp2ConnectionHandler) super.build();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public VertxHttp2ConnectionHandler<C> build(Http2ConnectionDecoder decoder, Http2ConnectionEncoder encoder, io.netty.handler.codec.http2.Http2Settings initialSettings) throws Exception {
        if (isServer()) {
            if (this.useCompression) {
                encoder = new CompressorHttp2ConnectionEncoder(encoder, this.compressionLevel, 15, 8);
            }
            VertxHttp2ConnectionHandler<C> handler = new VertxHttp2ConnectionHandler<>(this.connectionFactory, this.useDecompression, decoder, encoder, initialSettings);
            decoder.frameListener(handler);
            return handler;
        }
        VertxHttp2ConnectionHandler<C> handler2 = new VertxHttp2ConnectionHandler<>(this.connectionFactory, this.useCompression, decoder, encoder, initialSettings);
        decoder.frameListener(handler2);
        return handler2;
    }
}
