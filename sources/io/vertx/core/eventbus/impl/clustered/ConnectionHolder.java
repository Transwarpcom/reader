package io.vertx.core.eventbus.impl.clustered;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.core.eventbus.impl.codecs.PingMessageCodec;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.impl.ConnectionBase;
import io.vertx.core.net.impl.NetClientImpl;
import io.vertx.core.net.impl.ServerID;
import io.vertx.core.spi.metrics.EventBusMetrics;
import java.util.ArrayDeque;
import java.util.Queue;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/clustered/ConnectionHolder.class */
class ConnectionHolder {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) ConnectionHolder.class);
    private static final String PING_ADDRESS = "__vertx_ping";
    private final ClusteredEventBus eventBus;
    private final NetClient client;
    private final ServerID serverID;
    private final Vertx vertx;
    private final EventBusMetrics metrics;
    private Queue<ClusteredMessage> pending;
    private NetSocket socket;
    private boolean connected;
    private long timeoutID = -1;
    private long pingTimeoutID = -1;

    ConnectionHolder(ClusteredEventBus eventBus, ServerID serverID, EventBusOptions options) {
        this.eventBus = eventBus;
        this.serverID = serverID;
        this.vertx = eventBus.vertx();
        this.metrics = eventBus.getMetrics();
        NetClientOptions clientOptions = new NetClientOptions(options.toJson());
        ClusteredEventBus.setCertOptions(clientOptions, options.getKeyCertOptions());
        ClusteredEventBus.setTrustOptions(clientOptions, options.getTrustOptions());
        this.client = new NetClientImpl(eventBus.vertx(), clientOptions, false);
    }

    synchronized void connect() {
        if (this.connected) {
            throw new IllegalStateException("Already connected");
        }
        this.client.connect(this.serverID.port, this.serverID.host, res -> {
            if (res.succeeded()) {
                connected((NetSocket) res.result());
            } else {
                log.warn("Connecting to server " + this.serverID + " failed", res.cause());
                close(res.cause());
            }
        });
    }

    synchronized void writeMessage(ClusteredMessage message) {
        if (this.connected) {
            Buffer data = message.encodeToWire();
            if (this.metrics != null) {
                this.metrics.messageWritten(message.address(), data.length());
            }
            this.socket.write(data, message.writeHandler());
            return;
        }
        if (this.pending == null) {
            if (log.isDebugEnabled()) {
                log.debug("Not connected to server " + this.serverID + " - starting queuing");
            }
            this.pending = new ArrayDeque();
        }
        this.pending.add(message);
    }

    void close() {
        close(ConnectionBase.CLOSED_EXCEPTION);
    }

    private void close(Throwable cause) {
        if (this.timeoutID != -1) {
            this.vertx.cancelTimer(this.timeoutID);
        }
        if (this.pingTimeoutID != -1) {
            this.vertx.cancelTimer(this.pingTimeoutID);
        }
        synchronized (this) {
            if (this.pending != null) {
                Future<Void> failure = Future.failedFuture(cause);
                while (true) {
                    ClusteredMessage<?, ?> msg = this.pending.poll();
                    if (msg == null) {
                        break;
                    }
                    Handler<AsyncResult<Void>> handler = msg.writeHandler();
                    if (handler != null) {
                        handler.handle(failure);
                    }
                }
            }
        }
        try {
            this.client.close();
        } catch (Exception e) {
        }
        if (this.eventBus.connections().remove(this.serverID, this) && log.isDebugEnabled()) {
            log.debug("Cluster connection closed for server " + this.serverID);
        }
    }

    private void schedulePing() {
        EventBusOptions options = this.eventBus.options();
        this.pingTimeoutID = this.vertx.setTimer(options.getClusterPingInterval(), id1 -> {
            this.timeoutID = this.vertx.setTimer(options.getClusterPingReplyInterval(), id2 -> {
                log.warn("No pong from server " + this.serverID + " - will consider it dead");
                close();
            });
            ClusteredMessage pingMessage = new ClusteredMessage(this.serverID, PING_ADDRESS, null, null, null, new PingMessageCodec(), true, this.eventBus, null);
            Buffer data = pingMessage.encodeToWire();
            this.socket.write(data);
        });
    }

    private synchronized void connected(NetSocket socket) {
        this.socket = socket;
        this.connected = true;
        socket.exceptionHandler(err -> {
            close(err);
        });
        socket.mo1996closeHandler(v -> {
            close();
        });
        socket.handler2(data -> {
            this.vertx.cancelTimer(this.timeoutID);
            schedulePing();
        });
        schedulePing();
        if (this.pending != null) {
            if (log.isDebugEnabled()) {
                log.debug("Draining the queue for server " + this.serverID);
            }
            for (ClusteredMessage message : this.pending) {
                Buffer data2 = message.encodeToWire();
                if (this.metrics != null) {
                    this.metrics.messageWritten(message.address(), data2.length());
                }
                socket.write(data2, message.writeHandler());
            }
        }
        this.pending = null;
    }
}
