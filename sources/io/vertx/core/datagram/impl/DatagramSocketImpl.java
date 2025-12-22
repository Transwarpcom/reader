package io.vertx.core.datagram.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.MaxMessagesRecvByteBufAllocator;
import io.netty.channel.socket.DatagramChannel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.GenericFutureListener;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.datagram.DatagramPacket;
import io.vertx.core.datagram.DatagramSocket;
import io.vertx.core.datagram.DatagramSocketOptions;
import io.vertx.core.impl.Arguments;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.net.impl.ChannelFutureListenerAdapter;
import io.vertx.core.net.impl.ConnectionBase;
import io.vertx.core.net.impl.SocketAddressImpl;
import io.vertx.core.net.impl.VertxHandler;
import io.vertx.core.net.impl.transport.Transport;
import io.vertx.core.spi.metrics.DatagramSocketMetrics;
import io.vertx.core.spi.metrics.Metrics;
import io.vertx.core.spi.metrics.MetricsProvider;
import io.vertx.core.spi.metrics.NetworkMetrics;
import io.vertx.core.spi.metrics.VertxMetrics;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.WriteStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Objects;

/*  JADX ERROR: NullPointerException in pass: ClassModifier
    java.lang.NullPointerException
    */
/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/datagram/impl/DatagramSocketImpl.class */
public class DatagramSocketImpl implements DatagramSocket, MetricsProvider {
    private final ContextInternal context;
    private final DatagramSocketMetrics metrics;
    private DatagramChannel channel;
    private Handler<DatagramPacket> packetHandler;
    private Handler<Void> endHandler;
    private Handler<Throwable> exceptionHandler;
    private long demand;

    /*  JADX ERROR: Failed to decode insn: 0x0005: MOVE_MULTI
        java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[8]
        	at java.base/java.lang.System.arraycopy(Native Method)
        	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:52)
        	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:137)
        	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
        	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:50)
        	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    static /* synthetic */ long access$310(io.vertx.core.datagram.impl.DatagramSocketImpl r8) {
        /*
            r0 = r8
            r1 = r0
            long r1 = r1.demand
            // decode failed: arraycopy: source index -1 out of bounds for object array[8]
            r2 = 1
            long r1 = r1 - r2
            r0.demand = r1
            return r-1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.vertx.core.datagram.impl.DatagramSocketImpl.access$310(io.vertx.core.datagram.impl.DatagramSocketImpl):long");
    }

    @Override // io.vertx.core.datagram.DatagramSocket, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ DatagramSocket exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.datagram.DatagramSocket, io.vertx.core.streams.ReadStream
    /* renamed from: endHandler, reason: avoid collision after fix types in other method */
    public /* bridge */ /* synthetic */ ReadStream<DatagramPacket> endHandler2(Handler handler) {
        return endHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.datagram.DatagramSocket, io.vertx.core.streams.ReadStream
    public /* bridge */ /* synthetic */ ReadStream<DatagramPacket> endHandler(Handler handler) {
        return endHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.datagram.DatagramSocket, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ ReadStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.datagram.DatagramSocket, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    public static DatagramSocketImpl create(VertxInternal vertx, DatagramSocketOptions options) {
        DatagramSocketImpl socket = new DatagramSocketImpl(vertx, options);
        socket.init();
        return socket;
    }

    private DatagramSocketImpl(VertxInternal vertx, DatagramSocketOptions options) {
        Transport transport = vertx.transport();
        DatagramChannel channel = transport.datagramChannel(options.isIpV6() ? io.netty.channel.socket.InternetProtocolFamily.IPv6 : io.netty.channel.socket.InternetProtocolFamily.IPv4);
        transport.configure(channel, new DatagramSocketOptions(options));
        ContextInternal context = vertx.getOrCreateContext();
        if (context.isMultiThreadedWorkerContext()) {
            throw new IllegalStateException("Cannot use DatagramSocket in a multi-threaded worker verticle");
        }
        channel.config().setOption(ChannelOption.DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION, true);
        MaxMessagesRecvByteBufAllocator bufAllocator = (MaxMessagesRecvByteBufAllocator) channel.config().getRecvByteBufAllocator();
        bufAllocator.maxMessagesPerRead(1);
        context.nettyEventLoop().register(channel);
        if (options.getLogActivity()) {
            channel.pipeline().addLast("logging", new LoggingHandler());
        }
        VertxMetrics metrics = vertx.metricsSPI();
        this.metrics = metrics != null ? metrics.createDatagramSocketMetrics(options) : null;
        this.channel = channel;
        this.context = context;
        this.demand = Long.MAX_VALUE;
    }

    private void init() {
        this.channel.pipeline().addLast("handler", VertxHandler.create(this.context, this::createConnection));
    }

    @Override // io.vertx.core.datagram.DatagramSocket
    public DatagramSocket listenMulticastGroup(String multicastAddress, Handler<AsyncResult<DatagramSocket>> handler) {
        try {
            addListener(this.channel.joinGroup(InetAddress.getByName(multicastAddress)), handler);
        } catch (UnknownHostException e) {
            notifyException(handler, e);
        }
        return this;
    }

    @Override // io.vertx.core.datagram.DatagramSocket
    public DatagramSocket listenMulticastGroup(String multicastAddress, String networkInterface, String source, Handler<AsyncResult<DatagramSocket>> handler) throws UnknownHostException {
        InetAddress sourceAddress;
        if (source == null) {
            sourceAddress = null;
        } else {
            try {
                sourceAddress = InetAddress.getByName(source);
            } catch (Exception e) {
                notifyException(handler, e);
            }
        }
        addListener(this.channel.joinGroup(InetAddress.getByName(multicastAddress), NetworkInterface.getByName(networkInterface), sourceAddress), handler);
        return this;
    }

    @Override // io.vertx.core.datagram.DatagramSocket
    public DatagramSocket unlistenMulticastGroup(String multicastAddress, Handler<AsyncResult<DatagramSocket>> handler) {
        try {
            addListener(this.channel.leaveGroup(InetAddress.getByName(multicastAddress)), handler);
        } catch (UnknownHostException e) {
            notifyException(handler, e);
        }
        return this;
    }

    @Override // io.vertx.core.datagram.DatagramSocket
    public DatagramSocket unlistenMulticastGroup(String multicastAddress, String networkInterface, String source, Handler<AsyncResult<DatagramSocket>> handler) throws UnknownHostException {
        InetAddress sourceAddress;
        if (source == null) {
            sourceAddress = null;
        } else {
            try {
                sourceAddress = InetAddress.getByName(source);
            } catch (Exception e) {
                notifyException(handler, e);
            }
        }
        addListener(this.channel.leaveGroup(InetAddress.getByName(multicastAddress), NetworkInterface.getByName(networkInterface), sourceAddress), handler);
        return this;
    }

    @Override // io.vertx.core.datagram.DatagramSocket
    public DatagramSocket blockMulticastGroup(String multicastAddress, String networkInterface, String sourceToBlock, Handler<AsyncResult<DatagramSocket>> handler) throws UnknownHostException {
        InetAddress sourceAddress;
        if (sourceToBlock == null) {
            sourceAddress = null;
        } else {
            try {
                sourceAddress = InetAddress.getByName(sourceToBlock);
            } catch (Exception e) {
                notifyException(handler, e);
            }
        }
        addListener(this.channel.block(InetAddress.getByName(multicastAddress), NetworkInterface.getByName(networkInterface), sourceAddress), handler);
        return this;
    }

    @Override // io.vertx.core.datagram.DatagramSocket
    public DatagramSocket blockMulticastGroup(String multicastAddress, String sourceToBlock, Handler<AsyncResult<DatagramSocket>> handler) {
        try {
            addListener(this.channel.block(InetAddress.getByName(multicastAddress), InetAddress.getByName(sourceToBlock)), handler);
        } catch (UnknownHostException e) {
            notifyException(handler, e);
        }
        return this;
    }

    @Override // io.vertx.core.datagram.DatagramSocket
    public DatagramSocket listen(int port, String address, Handler<AsyncResult<DatagramSocket>> handler) {
        return listen(new SocketAddressImpl(port, address), handler);
    }

    @Override // io.vertx.core.datagram.DatagramSocket, io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    public synchronized ReadStream<DatagramPacket> handler2(Handler<DatagramPacket> handler) {
        this.packetHandler = handler;
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.datagram.DatagramSocket, io.vertx.core.streams.ReadStream
    public ReadStream<DatagramPacket> endHandler(Handler<Void> handler) {
        this.endHandler = handler;
        return this;
    }

    @Override // io.vertx.core.datagram.DatagramSocket, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public DatagramSocketImpl exceptionHandler(Handler<Throwable> handler) {
        this.exceptionHandler = handler;
        return this;
    }

    private DatagramSocket listen(SocketAddress local, Handler<AsyncResult<DatagramSocket>> handler) {
        Objects.requireNonNull(handler, "no null handler accepted");
        this.context.owner().resolveAddress(local.host(), res -> {
            if (res.succeeded()) {
                ChannelFuture future = this.channel.bind(new InetSocketAddress((InetAddress) res.result(), local.port()));
                addListener(future, ar -> {
                    if (this.metrics != null && ar.succeeded()) {
                        this.metrics.listening(local.host(), localAddress());
                    }
                    handler.handle(ar);
                });
            } else {
                handler.handle(Future.failedFuture(res.cause()));
            }
        });
        return this;
    }

    final void addListener(ChannelFuture future, Handler<AsyncResult<DatagramSocket>> handler) {
        if (handler != null) {
            future.addListener2((GenericFutureListener<? extends io.netty.util.concurrent.Future<? super Void>>) new ChannelFutureListenerAdapter(this.context, this, handler));
        }
    }

    @Override // io.vertx.core.datagram.DatagramSocket, io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    public synchronized ReadStream<DatagramPacket> pause2() {
        if (this.demand > 0) {
            this.demand = 0L;
            this.channel.config().setAutoRead(false);
        }
        return this;
    }

    @Override // io.vertx.core.datagram.DatagramSocket, io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    public synchronized ReadStream<DatagramPacket> resume2() {
        if (this.demand == 0) {
            this.demand = Long.MAX_VALUE;
            this.channel.config().setAutoRead(true);
        }
        return this;
    }

    @Override // io.vertx.core.datagram.DatagramSocket, io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    public synchronized ReadStream<DatagramPacket> fetch2(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Illegal fetch " + amount);
        }
        if (amount > 0) {
            if (this.demand == 0) {
                this.channel.config().setAutoRead(true);
            }
            this.demand += amount;
            if (this.demand < 0) {
                this.demand = Long.MAX_VALUE;
            }
        }
        return this;
    }

    @Override // io.vertx.core.datagram.DatagramSocket
    public DatagramSocket send(Buffer packet, int port, String host, Handler<AsyncResult<DatagramSocket>> handler) {
        Objects.requireNonNull(packet, "no null packet accepted");
        Objects.requireNonNull(host, "no null host accepted");
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("port out of range:" + port);
        }
        this.context.owner().resolveAddress(host, res -> {
            if (res.succeeded()) {
                doSend(packet, new InetSocketAddress((InetAddress) res.result(), port), handler);
            } else {
                handler.handle(Future.failedFuture(res.cause()));
            }
        });
        if (this.metrics != null) {
            this.metrics.bytesWritten(null, new SocketAddressImpl(port, host), packet.length());
        }
        return this;
    }

    private void doSend(Buffer packet, InetSocketAddress addr, Handler<AsyncResult<DatagramSocket>> handler) {
        ChannelFuture future = this.channel.writeAndFlush(new io.netty.channel.socket.DatagramPacket(packet.getByteBuf(), addr));
        addListener(future, handler);
    }

    @Override // io.vertx.core.datagram.DatagramSocket
    public WriteStream<Buffer> sender(int port, String host) {
        Arguments.requireInRange(port, 0, 65535, "port p must be in range 0 <= p <= 65535");
        Objects.requireNonNull(host, "no null host accepted");
        return new PacketWriteStreamImpl(this, port, host);
    }

    @Override // io.vertx.core.datagram.DatagramSocket
    public DatagramSocket send(String str, int port, String host, Handler<AsyncResult<DatagramSocket>> handler) {
        return send(Buffer.buffer(str), port, host, handler);
    }

    @Override // io.vertx.core.datagram.DatagramSocket
    public DatagramSocket send(String str, String enc, int port, String host, Handler<AsyncResult<DatagramSocket>> handler) {
        return send(Buffer.buffer(str, enc), port, host, handler);
    }

    @Override // io.vertx.core.datagram.DatagramSocket
    public SocketAddress localAddress() {
        return this.context.owner().transport().convert(this.channel.localAddress());
    }

    @Override // io.vertx.core.datagram.DatagramSocket
    public void close() {
        close(null);
    }

    @Override // io.vertx.core.datagram.DatagramSocket
    public synchronized void close(Handler<AsyncResult<Void>> handler) {
        if (!this.channel.isOpen()) {
            return;
        }
        this.channel.flush();
        ChannelFuture future = this.channel.close();
        if (handler != null) {
            future.addListener2((GenericFutureListener<? extends io.netty.util.concurrent.Future<? super Void>>) new ChannelFutureListenerAdapter(this.context, null, handler));
        }
    }

    @Override // io.vertx.core.metrics.Measured
    public boolean isMetricsEnabled() {
        return this.metrics != null;
    }

    @Override // io.vertx.core.spi.metrics.MetricsProvider
    public Metrics getMetrics() {
        return this.metrics;
    }

    private void notifyException(Handler<AsyncResult<DatagramSocket>> handler, Throwable cause) {
        this.context.executeFromIO(v -> {
            handler.handle(Future.failedFuture(cause));
        });
    }

    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    private Connection createConnection(ChannelHandlerContext chctx) {
        return new Connection(this.context.owner(), chctx, this.context);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/datagram/impl/DatagramSocketImpl$Connection.class */
    class Connection extends ConnectionBase {
        public Connection(VertxInternal vertx, ChannelHandlerContext channel, ContextInternal context) {
            super(vertx, channel, context);
        }

        @Override // io.vertx.core.net.impl.ConnectionBase
        public NetworkMetrics metrics() {
            return DatagramSocketImpl.this.metrics;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.vertx.core.net.impl.ConnectionBase
        public void handleInterestedOpsChanged() {
        }

        @Override // io.vertx.core.net.impl.ConnectionBase
        protected void handleException(Throwable t) {
            Handler<Throwable> handler;
            super.handleException(t);
            synchronized (DatagramSocketImpl.this) {
                handler = DatagramSocketImpl.this.exceptionHandler;
            }
            if (handler != null) {
                handler.handle(t);
            }
        }

        @Override // io.vertx.core.net.impl.ConnectionBase
        protected void handleClosed() {
            Handler<Void> handler;
            DatagramSocketMetrics metrics;
            super.handleClosed();
            synchronized (DatagramSocketImpl.this) {
                handler = DatagramSocketImpl.this.endHandler;
                metrics = DatagramSocketImpl.this.metrics;
            }
            if (metrics != null) {
                metrics.close();
            }
            if (handler != null) {
                handler.handle(null);
            }
        }

        @Override // io.vertx.core.net.impl.ConnectionBase
        public void handleMessage(Object msg) {
            if (msg instanceof io.netty.channel.socket.DatagramPacket) {
                io.netty.channel.socket.DatagramPacket packet = (io.netty.channel.socket.DatagramPacket) msg;
                ByteBuf content = (ByteBuf) packet.content();
                if (content.isDirect()) {
                    content = VertxHandler.safeBuffer(content, this.chctx.alloc());
                }
                handlePacket(new DatagramPacketImpl(packet.sender(), Buffer.buffer(content)));
            }
        }

        /* JADX WARN: Failed to check method for inline after forced processio.vertx.core.datagram.impl.DatagramSocketImpl.access$310(io.vertx.core.datagram.impl.DatagramSocketImpl):long */
        void handlePacket(DatagramPacket packet) {
            Handler<DatagramPacket> handler;
            synchronized (DatagramSocketImpl.this) {
                if (DatagramSocketImpl.this.metrics != null) {
                    DatagramSocketImpl.this.metrics.bytesRead(null, packet.sender(), packet.data().length());
                }
                if (DatagramSocketImpl.this.demand > 0) {
                    if (DatagramSocketImpl.this.demand != Long.MAX_VALUE) {
                        DatagramSocketImpl.access$310(DatagramSocketImpl.this);
                    }
                    handler = DatagramSocketImpl.this.packetHandler;
                } else {
                    handler = null;
                }
            }
            if (handler != null) {
                handler.handle(packet);
            }
        }
    }
}
