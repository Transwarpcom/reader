package io.vertx.core.http.impl;

import io.netty.channel.Channel;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.impl.pool.Pool;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.spi.metrics.HttpClientMetrics;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.LongSupplier;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/ConnectionManager.class */
class ConnectionManager {
    private static final LongSupplier CLOCK = System::currentTimeMillis;
    private final int maxWaitQueueSize;
    private final HttpClientMetrics metrics;
    private final HttpClientImpl client;
    private final Map<Channel, HttpClientConnection> connectionMap = new ConcurrentHashMap();
    private final Map<EndpointKey, Endpoint> endpointMap = new ConcurrentHashMap();
    private final HttpVersion version;
    private final long maxSize;
    private long timerID;

    ConnectionManager(HttpClientImpl client, HttpClientMetrics metrics, HttpVersion version, long maxSize, int maxWaitQueueSize) {
        this.client = client;
        this.maxWaitQueueSize = maxWaitQueueSize;
        this.metrics = metrics;
        this.maxSize = maxSize;
        this.version = version;
    }

    synchronized void start() {
        long period = this.client.getOptions().getPoolCleanerPeriod();
        this.timerID = period > 0 ? this.client.getVertx().setTimer(period, id -> {
            checkExpired(period);
        }) : -1L;
    }

    private synchronized void checkExpired(long period) {
        this.endpointMap.values().forEach(e -> {
            e.pool.closeIdle();
        });
        this.timerID = this.client.getVertx().setTimer(period, id -> {
            checkExpired(period);
        });
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/ConnectionManager$EndpointKey.class */
    private static final class EndpointKey {
        private final boolean ssl;
        private final SocketAddress server;
        private final SocketAddress peerAddress;

        EndpointKey(boolean ssl, SocketAddress server, SocketAddress peerAddress) {
            if (server == null) {
                throw new NullPointerException("No null host");
            }
            if (peerAddress == null) {
                throw new NullPointerException("No null peer address");
            }
            this.ssl = ssl;
            this.peerAddress = peerAddress;
            this.server = server;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            EndpointKey that = (EndpointKey) o;
            return this.ssl == that.ssl && this.server.equals(that.server) && this.peerAddress.equals(that.peerAddress);
        }

        public int hashCode() {
            int result = this.ssl ? 1 : 0;
            return (31 * ((31 * result) + this.peerAddress.hashCode())) + this.server.hashCode();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/ConnectionManager$Endpoint.class */
    class Endpoint {
        private final Pool<HttpClientConnection> pool;
        private final Object metric;

        public Endpoint(Pool<HttpClientConnection> pool, Object metric) {
            this.pool = pool;
            this.metric = metric;
        }
    }

    void getConnection(ContextInternal ctx, SocketAddress peerAddress, boolean ssl, SocketAddress server, Handler<AsyncResult<HttpClientConnection>> handler) {
        Endpoint endpoint;
        Object metric;
        Object obj;
        EndpointKey key = new EndpointKey(ssl, server, peerAddress);
        do {
            endpoint = this.endpointMap.computeIfAbsent(key, targetAddress -> {
                String host;
                int port;
                int maxPoolSize = Math.max(this.client.getOptions().getMaxPoolSize(), this.client.getOptions().getHttp2MaxPoolSize());
                if (server.path() == null) {
                    host = server.host();
                    port = server.port();
                } else {
                    host = server.path();
                    port = 0;
                }
                Object metric2 = this.metrics != null ? this.metrics.createEndpoint(host, port, maxPoolSize) : null;
                HttpChannelConnector connector = new HttpChannelConnector(this.client, metric2, this.version, ssl, peerAddress, server);
                String str = host;
                int i = port;
                Pool<HttpClientConnection> pool = new Pool<>(ctx, connector, CLOCK, this.maxWaitQueueSize, connector.weight(), this.maxSize, v -> {
                    if (this.metrics != null) {
                        this.metrics.closeEndpoint(str, i, metric2);
                    }
                    this.endpointMap.remove(key);
                }, conn -> {
                    this.connectionMap.put(conn.channel(), conn);
                }, conn2 -> {
                    this.connectionMap.remove(conn2.channel(), conn2);
                }, false);
                return new Endpoint(pool, metric2);
            });
            if (this.metrics != null) {
                metric = this.metrics.enqueueRequest(endpoint.metric);
            } else {
                metric = null;
            }
            obj = metric;
        } while (!endpoint.pool.getConnection(ar -> {
            if (this.metrics != null) {
                this.metrics.dequeueRequest(endpoint.metric, obj);
            }
            handler.handle(ar);
        }));
    }

    public void close() {
        synchronized (this) {
            if (this.timerID >= 0) {
                this.client.getVertx().cancelTimer(this.timerID);
                this.timerID = -1L;
            }
        }
        this.endpointMap.clear();
        for (HttpClientConnection conn : this.connectionMap.values()) {
            conn.close();
        }
    }
}
