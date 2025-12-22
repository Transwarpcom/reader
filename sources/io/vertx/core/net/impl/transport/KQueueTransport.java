package io.vertx.core.net.impl.transport;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFactory;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.kqueue.KQueue;
import io.netty.channel.kqueue.KQueueChannelOption;
import io.netty.channel.kqueue.KQueueDatagramChannel;
import io.netty.channel.kqueue.KQueueDomainSocketChannel;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.kqueue.KQueueServerDomainSocketChannel;
import io.netty.channel.kqueue.KQueueServerSocketChannel;
import io.netty.channel.kqueue.KQueueSocketChannel;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.unix.DomainSocketAddress;
import io.vertx.core.datagram.DatagramSocketOptions;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.impl.SocketAddressImpl;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.ThreadFactory;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/transport/KQueueTransport.class */
class KQueueTransport extends Transport {
    KQueueTransport() {
    }

    @Override // io.vertx.core.net.impl.transport.Transport
    public SocketAddress convert(io.vertx.core.net.SocketAddress address, boolean resolved) {
        if (address.path() != null) {
            return new DomainSocketAddress(address.path());
        }
        if (resolved) {
            return new InetSocketAddress(address.host(), address.port());
        }
        return InetSocketAddress.createUnresolved(address.host(), address.port());
    }

    @Override // io.vertx.core.net.impl.transport.Transport
    public io.vertx.core.net.SocketAddress convert(SocketAddress address) {
        if (address instanceof DomainSocketAddress) {
            return new SocketAddressImpl(((DomainSocketAddress) address).path());
        }
        return super.convert(address);
    }

    @Override // io.vertx.core.net.impl.transport.Transport
    public boolean isAvailable() {
        return KQueue.isAvailable();
    }

    @Override // io.vertx.core.net.impl.transport.Transport
    public Throwable unavailabilityCause() {
        return KQueue.unavailabilityCause();
    }

    @Override // io.vertx.core.net.impl.transport.Transport
    public EventLoopGroup eventLoopGroup(int type, int nThreads, ThreadFactory threadFactory, int ioRatio) {
        KQueueEventLoopGroup eventLoopGroup = new KQueueEventLoopGroup(nThreads, threadFactory);
        eventLoopGroup.setIoRatio(ioRatio);
        return eventLoopGroup;
    }

    @Override // io.vertx.core.net.impl.transport.Transport
    public DatagramChannel datagramChannel() {
        return new KQueueDatagramChannel();
    }

    @Override // io.vertx.core.net.impl.transport.Transport
    public DatagramChannel datagramChannel(InternetProtocolFamily family) {
        return new KQueueDatagramChannel();
    }

    @Override // io.vertx.core.net.impl.transport.Transport
    public ChannelFactory<? extends Channel> channelFactory(boolean domainSocket) {
        if (domainSocket) {
            return KQueueDomainSocketChannel::new;
        }
        return KQueueSocketChannel::new;
    }

    @Override // io.vertx.core.net.impl.transport.Transport
    public ChannelFactory<? extends ServerChannel> serverChannelFactory(boolean domainSocket) {
        if (domainSocket) {
            return KQueueServerDomainSocketChannel::new;
        }
        return KQueueServerSocketChannel::new;
    }

    @Override // io.vertx.core.net.impl.transport.Transport
    public void configure(NetServerOptions options, boolean domainSocket, ServerBootstrap bootstrap) {
        if (!domainSocket) {
            bootstrap.option(KQueueChannelOption.SO_REUSEPORT, Boolean.valueOf(options.isReusePort()));
        }
        super.configure(options, domainSocket, bootstrap);
    }

    @Override // io.vertx.core.net.impl.transport.Transport
    public void configure(DatagramChannel channel, DatagramSocketOptions options) {
        channel.config().setOption(KQueueChannelOption.SO_REUSEPORT, Boolean.valueOf(options.isReusePort()));
        super.configure(channel, options);
    }
}
