package io.vertx.core.net.impl.transport;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFactory;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.epoll.EpollDatagramChannel;
import io.netty.channel.epoll.EpollDomainSocketChannel;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerDomainSocketChannel;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.unix.DomainSocketAddress;
import io.vertx.core.datagram.DatagramSocketOptions;
import io.vertx.core.net.ClientOptionsBase;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.impl.SocketAddressImpl;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.ThreadFactory;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/transport/EpollTransport.class */
class EpollTransport extends Transport {
    private static volatile int pendingFastOpenRequestsThreshold = 256;

    public static int getPendingFastOpenRequestsThreshold() {
        return pendingFastOpenRequestsThreshold;
    }

    public static void setPendingFastOpenRequestsThreshold(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Invalid " + value);
        }
        pendingFastOpenRequestsThreshold = value;
    }

    EpollTransport() {
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
        return Epoll.isAvailable();
    }

    @Override // io.vertx.core.net.impl.transport.Transport
    public Throwable unavailabilityCause() {
        return Epoll.unavailabilityCause();
    }

    @Override // io.vertx.core.net.impl.transport.Transport
    public EventLoopGroup eventLoopGroup(int type, int nThreads, ThreadFactory threadFactory, int ioRatio) {
        EpollEventLoopGroup eventLoopGroup = new EpollEventLoopGroup(nThreads, threadFactory);
        eventLoopGroup.setIoRatio(ioRatio);
        return eventLoopGroup;
    }

    @Override // io.vertx.core.net.impl.transport.Transport
    public DatagramChannel datagramChannel() {
        return new EpollDatagramChannel();
    }

    @Override // io.vertx.core.net.impl.transport.Transport
    public DatagramChannel datagramChannel(InternetProtocolFamily family) {
        return new EpollDatagramChannel();
    }

    @Override // io.vertx.core.net.impl.transport.Transport
    public ChannelFactory<? extends Channel> channelFactory(boolean domainSocket) {
        if (domainSocket) {
            return EpollDomainSocketChannel::new;
        }
        return EpollSocketChannel::new;
    }

    @Override // io.vertx.core.net.impl.transport.Transport
    public ChannelFactory<? extends ServerChannel> serverChannelFactory(boolean domainSocket) {
        if (domainSocket) {
            return EpollServerDomainSocketChannel::new;
        }
        return EpollServerSocketChannel::new;
    }

    @Override // io.vertx.core.net.impl.transport.Transport
    public void configure(DatagramChannel channel, DatagramSocketOptions options) {
        channel.config().setOption(EpollChannelOption.SO_REUSEPORT, Boolean.valueOf(options.isReusePort()));
        super.configure(channel, options);
    }

    @Override // io.vertx.core.net.impl.transport.Transport
    public void configure(NetServerOptions options, boolean domainSocket, ServerBootstrap bootstrap) {
        if (!domainSocket) {
            bootstrap.option(EpollChannelOption.SO_REUSEPORT, Boolean.valueOf(options.isReusePort()));
        }
        if (options.isTcpFastOpen()) {
            bootstrap.option(EpollChannelOption.TCP_FASTOPEN, Integer.valueOf(options.isTcpFastOpen() ? pendingFastOpenRequestsThreshold : 0));
        }
        bootstrap.childOption(EpollChannelOption.TCP_QUICKACK, Boolean.valueOf(options.isTcpQuickAck()));
        bootstrap.childOption(EpollChannelOption.TCP_CORK, Boolean.valueOf(options.isTcpCork()));
        super.configure(options, domainSocket, bootstrap);
    }

    @Override // io.vertx.core.net.impl.transport.Transport
    public void configure(ClientOptionsBase options, boolean domainSocket, Bootstrap bootstrap) {
        if (options.isTcpFastOpen()) {
            bootstrap.option(EpollChannelOption.TCP_FASTOPEN_CONNECT, Boolean.valueOf(options.isTcpFastOpen()));
        }
        bootstrap.option(EpollChannelOption.TCP_QUICKACK, Boolean.valueOf(options.isTcpQuickAck()));
        bootstrap.option(EpollChannelOption.TCP_CORK, Boolean.valueOf(options.isTcpCork()));
        super.configure(options, domainSocket, bootstrap);
    }
}
