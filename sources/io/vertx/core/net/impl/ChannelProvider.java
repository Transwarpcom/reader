package io.vertx.core.net.impl;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFactory;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.proxy.HttpProxyHandler;
import io.netty.handler.proxy.ProxyConnectionEvent;
import io.netty.handler.proxy.ProxyHandler;
import io.netty.handler.proxy.Socks4ProxyHandler;
import io.netty.handler.proxy.Socks5ProxyHandler;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.ssl.SslHandshakeCompletionEvent;
import io.netty.resolver.NoopAddressResolverGroup;
import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.net.ProxyOptions;
import io.vertx.core.net.ProxyType;
import io.vertx.core.net.SocketAddress;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import javax.net.ssl.SSLHandshakeException;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/ChannelProvider.class */
public final class ChannelProvider {
    private final Bootstrap bootstrap;
    private final SSLHelper sslHelper;
    private final ContextInternal context;
    private final ProxyOptions proxyOptions;
    private String applicationProtocol;
    private Channel channel;

    public ChannelProvider(Bootstrap bootstrap, SSLHelper sslHelper, ContextInternal context, ProxyOptions proxyOptions) {
        this.bootstrap = bootstrap;
        this.context = context;
        this.sslHelper = sslHelper;
        this.proxyOptions = proxyOptions;
    }

    public String applicationProtocol() {
        return this.applicationProtocol;
    }

    public Channel channel() {
        return this.channel;
    }

    public void connect(SocketAddress remoteAddress, SocketAddress peerAddress, String serverName, boolean ssl, Handler<AsyncResult<Channel>> channelHandler) {
        Handler<AsyncResult<Channel>> handler = res -> {
            if (Context.isOnEventLoopThread()) {
                channelHandler.handle(res);
            } else {
                this.context.nettyEventLoop().execute(() -> {
                    channelHandler.handle(res);
                });
            }
        };
        try {
            this.bootstrap.channelFactory((ChannelFactory) this.context.owner().transport().channelFactory(remoteAddress.path() != null));
            if (this.proxyOptions != null) {
                handleProxyConnect(remoteAddress, peerAddress, serverName, ssl, handler);
            } else {
                handleConnect(remoteAddress, peerAddress, serverName, ssl, handler);
            }
        } catch (Exception e) {
            channelHandler.handle(Future.failedFuture(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initSSL(SocketAddress peerAddress, String serverName, boolean ssl, Channel ch2, final Handler<AsyncResult<Channel>> channelHandler) {
        if (ssl) {
            final SslHandler sslHandler = new SslHandler(this.sslHelper.createEngine(this.context.owner(), peerAddress, serverName));
            sslHandler.setHandshakeTimeout(this.sslHelper.getSslHandshakeTimeout(), this.sslHelper.getSslHandshakeTimeoutUnit());
            ChannelPipeline pipeline = ch2.pipeline();
            pipeline.addLast("ssl", sslHandler);
            pipeline.addLast(new ChannelInboundHandlerAdapter() { // from class: io.vertx.core.net.impl.ChannelProvider.1
                @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
                public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
                    if (evt instanceof SslHandshakeCompletionEvent) {
                        SslHandshakeCompletionEvent completion = (SslHandshakeCompletionEvent) evt;
                        if (completion.isSuccess()) {
                            ctx.pipeline().remove(this);
                            ChannelProvider.this.applicationProtocol = sslHandler.applicationProtocol();
                            channelHandler.handle(Future.succeededFuture(ChannelProvider.this.channel));
                        } else {
                            SSLHandshakeException sslException = new SSLHandshakeException("Failed to create SSL connection");
                            sslException.initCause(completion.cause());
                            channelHandler.handle(Future.failedFuture(sslException));
                        }
                    }
                    ctx.fireUserEventTriggered(evt);
                }

                @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler, io.netty.channel.ChannelInboundHandler
                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
                }
            });
        }
    }

    private void handleConnect(SocketAddress remoteAddress, final SocketAddress peerAddress, final String serverName, final boolean ssl, final Handler<AsyncResult<Channel>> channelHandler) {
        VertxInternal vertx = this.context.owner();
        this.bootstrap.resolver(vertx.nettyAddressResolverGroup());
        this.bootstrap.handler(new ChannelInitializer<Channel>() { // from class: io.vertx.core.net.impl.ChannelProvider.2
            @Override // io.netty.channel.ChannelInitializer
            protected void initChannel(Channel ch2) {
                ChannelProvider.this.initSSL(peerAddress, serverName, ssl, ch2, channelHandler);
            }
        });
        ChannelFuture fut = this.bootstrap.connect(vertx.transport().convert(remoteAddress, false));
        fut.addListener2(res -> {
            if (res.isSuccess()) {
                connected(fut.channel(), ssl, channelHandler);
            } else {
                channelHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connected(Channel channel, boolean ssl, Handler<AsyncResult<Channel>> channelHandler) {
        this.channel = channel;
        if (!ssl) {
            channelHandler.handle(Future.succeededFuture(this.channel));
        }
    }

    private void handleProxyConnect(SocketAddress remoteAddress, SocketAddress peerAddress, String serverName, boolean ssl, Handler<AsyncResult<Channel>> channelHandler) {
        VertxInternal vertx = this.context.owner();
        String proxyHost = this.proxyOptions.getHost();
        int proxyPort = this.proxyOptions.getPort();
        String proxyUsername = this.proxyOptions.getUsername();
        String proxyPassword = this.proxyOptions.getPassword();
        ProxyType proxyType = this.proxyOptions.getType();
        vertx.resolveAddress(proxyHost, dnsRes -> {
            ProxyHandler proxy;
            if (dnsRes.succeeded()) {
                InetAddress address = (InetAddress) dnsRes.result();
                InetSocketAddress proxyAddr = new InetSocketAddress(address, proxyPort);
                switch (proxyType) {
                    case HTTP:
                    default:
                        proxy = (proxyUsername == null || proxyPassword == null) ? new HttpProxyHandler(proxyAddr) : new HttpProxyHandler(proxyAddr, proxyUsername, proxyPassword);
                        break;
                    case SOCKS5:
                        proxy = (proxyUsername == null || proxyPassword == null) ? new Socks5ProxyHandler(proxyAddr) : new Socks5ProxyHandler(proxyAddr, proxyUsername, proxyPassword);
                        break;
                    case SOCKS4:
                        proxy = proxyUsername != null ? new Socks4ProxyHandler(proxyAddr, proxyUsername) : new Socks4ProxyHandler(proxyAddr);
                        break;
                }
                this.bootstrap.resolver(NoopAddressResolverGroup.INSTANCE);
                java.net.SocketAddress targetAddress = vertx.transport().convert(remoteAddress, false);
                final ProxyHandler proxyHandler = proxy;
                this.bootstrap.handler(new ChannelInitializer<Channel>() { // from class: io.vertx.core.net.impl.ChannelProvider.3
                    @Override // io.netty.channel.ChannelInitializer
                    protected void initChannel(final Channel ch2) throws Exception {
                        final ChannelPipeline pipeline = ch2.pipeline();
                        pipeline.addFirst("proxy", proxyHandler);
                        pipeline.addLast(new ChannelInboundHandlerAdapter() { // from class: io.vertx.core.net.impl.ChannelProvider.3.1
                            @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
                            public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
                                if (evt instanceof ProxyConnectionEvent) {
                                    pipeline.remove(proxyHandler);
                                    pipeline.remove(this);
                                    ChannelProvider.this.initSSL(peerAddress, serverName, ssl, ch2, channelHandler);
                                    ChannelProvider.this.connected(ch2, ssl, channelHandler);
                                }
                                ctx.fireUserEventTriggered(evt);
                            }

                            @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler, io.netty.channel.ChannelInboundHandler
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
                                channelHandler.handle(Future.failedFuture(cause));
                            }
                        });
                    }
                });
                ChannelFuture future = this.bootstrap.connect(targetAddress);
                future.addListener2(res -> {
                    if (!res.isSuccess()) {
                        channelHandler.handle(Future.failedFuture(res.cause()));
                    }
                });
                return;
            }
            channelHandler.handle(Future.failedFuture(dnsRes.cause()));
        });
    }
}
