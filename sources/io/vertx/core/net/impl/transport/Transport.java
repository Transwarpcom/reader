package io.vertx.core.net.impl.transport;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFactory;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.ServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.vertx.core.datagram.DatagramSocketOptions;
import io.vertx.core.net.ClientOptionsBase;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.impl.PartialPooledByteBufAllocator;
import io.vertx.core.net.impl.SocketAddressImpl;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.concurrent.ThreadFactory;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/transport/Transport.class */
public class Transport {
    public static final int ACCEPTOR_EVENT_LOOP_GROUP = 0;
    public static final int IO_EVENT_LOOP_GROUP = 1;
    public static Transport JDK = new Transport();

    public static Transport nativeTransport() {
        Transport kqueue;
        Transport epoll;
        Transport transport = null;
        try {
            epoll = new EpollTransport();
        } catch (Throwable th) {
        }
        if (epoll.isAvailable()) {
            return epoll;
        }
        transport = epoll;
        try {
            kqueue = new KQueueTransport();
        } catch (Throwable th2) {
        }
        if (kqueue.isAvailable()) {
            return kqueue;
        }
        if (transport == null) {
            transport = kqueue;
        }
        return transport;
    }

    public static Transport transport(boolean preferNative) {
        if (preferNative) {
            Transport nativeTransport = nativeTransport();
            if (nativeTransport != null && nativeTransport.isAvailable()) {
                return nativeTransport;
            }
            return JDK;
        }
        return JDK;
    }

    protected Transport() {
    }

    public boolean isAvailable() {
        return true;
    }

    public Throwable unavailabilityCause() {
        return null;
    }

    public SocketAddress convert(io.vertx.core.net.SocketAddress address, boolean resolved) {
        if (address.path() != null) {
            throw new IllegalArgumentException("Domain socket not supported by JDK transport");
        }
        if (resolved) {
            return new InetSocketAddress(address.host(), address.port());
        }
        return InetSocketAddress.createUnresolved(address.host(), address.port());
    }

    public io.vertx.core.net.SocketAddress convert(SocketAddress address) {
        if (address instanceof InetSocketAddress) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) address;
            if (inetSocketAddress.isUnresolved()) {
                return new SocketAddressImpl(inetSocketAddress.getPort(), inetSocketAddress.getHostName());
            }
            return new SocketAddressImpl(inetSocketAddress.getPort(), inetSocketAddress.getAddress().getHostAddress());
        }
        return null;
    }

    ChannelOption<?> channelOption(String name) {
        return null;
    }

    public EventLoopGroup eventLoopGroup(int type, int nThreads, ThreadFactory threadFactory, int ioRatio) {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup(nThreads, threadFactory);
        eventLoopGroup.setIoRatio(ioRatio);
        return eventLoopGroup;
    }

    public DatagramChannel datagramChannel() {
        return new NioDatagramChannel();
    }

    public DatagramChannel datagramChannel(InternetProtocolFamily family) {
        switch (family) {
            case IPv4:
                return new NioDatagramChannel(InternetProtocolFamily.IPv4);
            case IPv6:
                return new NioDatagramChannel(InternetProtocolFamily.IPv6);
            default:
                throw new UnsupportedOperationException();
        }
    }

    public ChannelFactory<? extends Channel> channelFactory(boolean domainSocket) {
        if (domainSocket) {
            throw new IllegalArgumentException();
        }
        return NioSocketChannel::new;
    }

    public ChannelFactory<? extends ServerChannel> serverChannelFactory(boolean domainSocket) {
        if (domainSocket) {
            throw new IllegalArgumentException();
        }
        return NioServerSocketChannel::new;
    }

    public void configure(DatagramChannel channel, DatagramSocketOptions options) {
        channel.config().setAllocator((ByteBufAllocator) PartialPooledByteBufAllocator.INSTANCE);
        if (options.getSendBufferSize() != -1) {
            channel.config().setSendBufferSize(options.getSendBufferSize());
        }
        if (options.getReceiveBufferSize() != -1) {
            channel.config().setReceiveBufferSize(options.getReceiveBufferSize());
            channel.config().setRecvByteBufAllocator((RecvByteBufAllocator) new FixedRecvByteBufAllocator(options.getReceiveBufferSize()));
        }
        channel.config().setOption(ChannelOption.SO_REUSEADDR, Boolean.valueOf(options.isReuseAddress()));
        if (options.getTrafficClass() != -1) {
            channel.config().setTrafficClass(options.getTrafficClass());
        }
        channel.config().setBroadcast(options.isBroadcast());
        if (this == JDK) {
            channel.config().setLoopbackModeDisabled(options.isLoopbackModeDisabled());
            if (options.getMulticastTimeToLive() != -1) {
                channel.config().setTimeToLive(options.getMulticastTimeToLive());
            }
            if (options.getMulticastNetworkInterface() != null) {
                try {
                    channel.config().setNetworkInterface(NetworkInterface.getByName(options.getMulticastNetworkInterface()));
                } catch (SocketException e) {
                    throw new IllegalArgumentException("Could not find network interface with name " + options.getMulticastNetworkInterface());
                }
            }
        }
    }

    public void configure(ClientOptionsBase options, boolean domainSocket, Bootstrap bootstrap) {
        if (!domainSocket) {
            bootstrap.option(ChannelOption.SO_REUSEADDR, Boolean.valueOf(options.isReuseAddress()));
            bootstrap.option(ChannelOption.TCP_NODELAY, Boolean.valueOf(options.isTcpNoDelay()));
            bootstrap.option(ChannelOption.SO_KEEPALIVE, Boolean.valueOf(options.isTcpKeepAlive()));
        }
        if (options.getLocalAddress() != null) {
            bootstrap.localAddress(options.getLocalAddress(), 0);
        }
        if (options.getSendBufferSize() != -1) {
            bootstrap.option(ChannelOption.SO_SNDBUF, Integer.valueOf(options.getSendBufferSize()));
        }
        if (options.getReceiveBufferSize() != -1) {
            bootstrap.option(ChannelOption.SO_RCVBUF, Integer.valueOf(options.getReceiveBufferSize()));
            bootstrap.option(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(options.getReceiveBufferSize()));
        }
        if (options.getSoLinger() != -1) {
            bootstrap.option(ChannelOption.SO_LINGER, Integer.valueOf(options.getSoLinger()));
        }
        if (options.getTrafficClass() != -1) {
            bootstrap.option(ChannelOption.IP_TOS, Integer.valueOf(options.getTrafficClass()));
        }
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, Integer.valueOf(options.getConnectTimeout()));
        bootstrap.option(ChannelOption.ALLOCATOR, PartialPooledByteBufAllocator.INSTANCE);
    }

    public void configure(NetServerOptions options, boolean domainSocket, ServerBootstrap bootstrap) {
        bootstrap.option(ChannelOption.SO_REUSEADDR, Boolean.valueOf(options.isReuseAddress()));
        if (!domainSocket) {
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, Boolean.valueOf(options.isTcpKeepAlive()));
            bootstrap.childOption(ChannelOption.TCP_NODELAY, Boolean.valueOf(options.isTcpNoDelay()));
        }
        if (options.getSendBufferSize() != -1) {
            bootstrap.childOption(ChannelOption.SO_SNDBUF, Integer.valueOf(options.getSendBufferSize()));
        }
        if (options.getReceiveBufferSize() != -1) {
            bootstrap.childOption(ChannelOption.SO_RCVBUF, Integer.valueOf(options.getReceiveBufferSize()));
            bootstrap.childOption(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(options.getReceiveBufferSize()));
        }
        if (options.getSoLinger() != -1) {
            bootstrap.childOption(ChannelOption.SO_LINGER, Integer.valueOf(options.getSoLinger()));
        }
        if (options.getTrafficClass() != -1) {
            bootstrap.childOption(ChannelOption.IP_TOS, Integer.valueOf(options.getTrafficClass()));
        }
        bootstrap.childOption(ChannelOption.ALLOCATOR, PartialPooledByteBufAllocator.INSTANCE);
        if (options.getAcceptBacklog() != -1) {
            bootstrap.option(ChannelOption.SO_BACKLOG, Integer.valueOf(options.getAcceptBacklog()));
        }
    }
}
