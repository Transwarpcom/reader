package io.netty.handler.codec.http.websocketx.extensions;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/http/websocketx/extensions/WebSocketServerExtensionHandler.class */
public class WebSocketServerExtensionHandler extends ChannelDuplexHandler {
    private final List<WebSocketServerExtensionHandshaker> extensionHandshakers;
    private List<WebSocketServerExtension> validExtensions;

    public WebSocketServerExtensionHandler(WebSocketServerExtensionHandshaker... extensionHandshakers) {
        if (extensionHandshakers == null) {
            throw new NullPointerException("extensionHandshakers");
        }
        if (extensionHandshakers.length == 0) {
            throw new IllegalArgumentException("extensionHandshakers must contains at least one handshaker");
        }
        this.extensionHandshakers = Arrays.asList(extensionHandshakers);
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String extensionsHeader;
        WebSocketServerExtension validExtension;
        if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;
            if (WebSocketExtensionUtil.isWebsocketUpgrade(request.headers()) && (extensionsHeader = request.headers().getAsString(HttpHeaderNames.SEC_WEBSOCKET_EXTENSIONS)) != null) {
                List<WebSocketExtensionData> extensions = WebSocketExtensionUtil.extractExtensions(extensionsHeader);
                int rsv = 0;
                for (WebSocketExtensionData extensionData : extensions) {
                    Iterator<WebSocketServerExtensionHandshaker> extensionHandshakersIterator = this.extensionHandshakers.iterator();
                    WebSocketServerExtension webSocketServerExtensionHandshakeExtension = null;
                    while (true) {
                        validExtension = webSocketServerExtensionHandshakeExtension;
                        if (validExtension != null || !extensionHandshakersIterator.hasNext()) {
                            break;
                        }
                        WebSocketServerExtensionHandshaker extensionHandshaker = extensionHandshakersIterator.next();
                        webSocketServerExtensionHandshakeExtension = extensionHandshaker.handshakeExtension(extensionData);
                    }
                    if (validExtension != null && (validExtension.rsv() & rsv) == 0) {
                        if (this.validExtensions == null) {
                            this.validExtensions = new ArrayList(1);
                        }
                        rsv |= validExtension.rsv();
                        this.validExtensions.add(validExtension);
                    }
                }
            }
        }
        super.channelRead(ctx, msg);
    }

    @Override // io.netty.channel.ChannelDuplexHandler, io.netty.channel.ChannelOutboundHandler
    public void write(final ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if ((msg instanceof HttpResponse) && WebSocketExtensionUtil.isWebsocketUpgrade(((HttpResponse) msg).headers()) && this.validExtensions != null) {
            HttpResponse response = (HttpResponse) msg;
            String headerValue = response.headers().getAsString(HttpHeaderNames.SEC_WEBSOCKET_EXTENSIONS);
            for (WebSocketServerExtension extension : this.validExtensions) {
                WebSocketExtensionData extensionData = extension.newReponseData();
                headerValue = WebSocketExtensionUtil.appendExtension(headerValue, extensionData.name(), extensionData.parameters());
            }
            promise.addListener2((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.netty.handler.codec.http.websocketx.extensions.WebSocketServerExtensionHandler.1
                @Override // io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        for (WebSocketServerExtension extension2 : WebSocketServerExtensionHandler.this.validExtensions) {
                            WebSocketExtensionDecoder decoder = extension2.newExtensionDecoder();
                            WebSocketExtensionEncoder encoder = extension2.newExtensionEncoder();
                            ctx.pipeline().addAfter(ctx.name(), decoder.getClass().getName(), decoder);
                            ctx.pipeline().addAfter(ctx.name(), encoder.getClass().getName(), encoder);
                        }
                    }
                    ctx.pipeline().remove(ctx.name());
                }
            });
            if (headerValue != null) {
                response.headers().set(HttpHeaderNames.SEC_WEBSOCKET_EXTENSIONS, headerValue);
            }
        }
        super.write(ctx, msg, promise);
    }
}
