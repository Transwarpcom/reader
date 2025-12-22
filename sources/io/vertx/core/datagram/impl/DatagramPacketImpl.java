package io.vertx.core.datagram.impl;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.datagram.DatagramPacket;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.net.impl.SocketAddressImpl;
import java.net.InetSocketAddress;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/datagram/impl/DatagramPacketImpl.class */
final class DatagramPacketImpl implements DatagramPacket {
    private final InetSocketAddress sender;
    private final Buffer buffer;
    private SocketAddress senderAddress;

    DatagramPacketImpl(InetSocketAddress sender, Buffer buffer) {
        this.sender = sender;
        this.buffer = buffer;
    }

    @Override // io.vertx.core.datagram.DatagramPacket
    public SocketAddress sender() {
        if (this.senderAddress == null) {
            this.senderAddress = new SocketAddressImpl(this.sender.getPort(), this.sender.getAddress().getHostAddress());
        }
        return this.senderAddress;
    }

    @Override // io.vertx.core.datagram.DatagramPacket
    public Buffer data() {
        return this.buffer;
    }
}
