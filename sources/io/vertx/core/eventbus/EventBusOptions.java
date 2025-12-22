package io.vertx.core.eventbus;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.core.VertxOptions;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.ClientAuth;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.JdkSSLEngineOptions;
import io.vertx.core.net.JksOptions;
import io.vertx.core.net.KeyCertOptions;
import io.vertx.core.net.OpenSSLEngineOptions;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.core.net.PemTrustOptions;
import io.vertx.core.net.PfxOptions;
import io.vertx.core.net.SSLEngineOptions;
import io.vertx.core.net.TCPSSLOptions;
import io.vertx.core.net.TrustOptions;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@DataObject(generateConverter = true, inheritConverter = true, publicConverter = false)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/EventBusOptions.class */
public class EventBusOptions extends TCPSSLOptions {
    public static final boolean DEFAULT_CLUSTERED = false;
    public static final String DEFAULT_CLUSTER_HOST = "localhost";
    public static final int DEFAULT_CLUSTER_PORT = 0;
    public static final int DEFAULT_CLUSTER_PUBLIC_PORT = -1;
    private boolean clustered;
    private String clusterPublicHost;
    private int clusterPublicPort;
    private long clusterPingInterval;
    private long clusterPingReplyInterval;
    public static final int DEFAULT_PORT = 0;
    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_ACCEPT_BACKLOG = -1;
    private int port;
    private String host;
    private int acceptBacklog;
    private ClientAuth clientAuth;
    public static final int DEFAULT_RECONNECT_ATTEMPTS = 0;
    public static final long DEFAULT_RECONNECT_INTERVAL = 1000;
    public static final int DEFAULT_CONNECT_TIMEOUT = 60000;
    public static final boolean DEFAULT_TRUST_ALL = true;
    private int reconnectAttempts;
    private long reconnectInterval;
    private int connectTimeout;
    private boolean trustAll;
    public static final String DEFAULT_CLUSTER_PUBLIC_HOST = VertxOptions.DEFAULT_CLUSTER_PUBLIC_HOST;
    public static final long DEFAULT_CLUSTER_PING_INTERVAL = VertxOptions.DEFAULT_CLUSTER_PING_INTERVAL;
    public static final long DEFAULT_CLUSTER_PING_REPLY_INTERVAL = VertxOptions.DEFAULT_CLUSTER_PING_REPLY_INTERVAL;
    public static final ClientAuth DEFAULT_CLIENT_AUTH = ClientAuth.NONE;

    @Override // io.vertx.core.net.TCPSSLOptions
    public /* bridge */ /* synthetic */ TCPSSLOptions setEnabledSecureTransportProtocols(Set set) {
        return setEnabledSecureTransportProtocols((Set<String>) set);
    }

    public EventBusOptions() {
        this.clustered = false;
        this.clusterPublicHost = DEFAULT_CLUSTER_PUBLIC_HOST;
        this.clusterPublicPort = -1;
        this.clusterPingInterval = DEFAULT_CLUSTER_PING_INTERVAL;
        this.clusterPingReplyInterval = DEFAULT_CLUSTER_PING_REPLY_INTERVAL;
        this.clientAuth = DEFAULT_CLIENT_AUTH;
        this.clustered = false;
        this.port = 0;
        this.host = "localhost";
        this.acceptBacklog = -1;
        this.clientAuth = DEFAULT_CLIENT_AUTH;
        this.reconnectAttempts = 0;
        this.reconnectInterval = 1000L;
        this.connectTimeout = 60000;
        this.trustAll = true;
    }

    public EventBusOptions(EventBusOptions other) {
        super(other);
        this.clustered = false;
        this.clusterPublicHost = DEFAULT_CLUSTER_PUBLIC_HOST;
        this.clusterPublicPort = -1;
        this.clusterPingInterval = DEFAULT_CLUSTER_PING_INTERVAL;
        this.clusterPingReplyInterval = DEFAULT_CLUSTER_PING_REPLY_INTERVAL;
        this.clientAuth = DEFAULT_CLIENT_AUTH;
        this.clustered = other.clustered;
        this.clusterPublicHost = other.clusterPublicHost;
        this.clusterPublicPort = other.clusterPublicPort;
        this.clusterPingInterval = other.clusterPingInterval;
        this.clusterPingReplyInterval = other.clusterPingReplyInterval;
        this.port = other.port;
        this.host = other.host;
        this.acceptBacklog = other.acceptBacklog;
        this.clientAuth = other.clientAuth;
        this.reconnectInterval = other.reconnectInterval;
        this.reconnectAttempts = other.reconnectAttempts;
        this.connectTimeout = other.connectTimeout;
        this.trustAll = other.trustAll;
    }

    public EventBusOptions(JsonObject json) {
        this();
        EventBusOptionsConverter.fromJson(json, this);
    }

    @Override // io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        EventBusOptionsConverter.toJson(this, json);
        return json;
    }

    public ClientAuth getClientAuth() {
        return this.clientAuth;
    }

    public EventBusOptions setClientAuth(ClientAuth clientAuth) {
        this.clientAuth = clientAuth;
        return this;
    }

    public int getAcceptBacklog() {
        return this.acceptBacklog;
    }

    public EventBusOptions setAcceptBacklog(int acceptBacklog) {
        this.acceptBacklog = acceptBacklog;
        return this;
    }

    public String getHost() {
        return this.host;
    }

    public EventBusOptions setHost(String host) {
        this.host = host;
        return this;
    }

    public int getPort() {
        return this.port;
    }

    public EventBusOptions setPort(int port) {
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("clusterPort p must be in range 0 <= p <= 65535");
        }
        this.port = port;
        return this;
    }

    public int getReconnectAttempts() {
        return this.reconnectAttempts;
    }

    public EventBusOptions setReconnectAttempts(int attempts) {
        this.reconnectAttempts = attempts;
        return this;
    }

    public long getReconnectInterval() {
        return this.reconnectInterval;
    }

    public EventBusOptions setReconnectInterval(long interval) {
        this.reconnectInterval = interval;
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions addCrlPath(String crlPath) throws NullPointerException {
        super.addCrlPath(crlPath);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions addCrlValue(Buffer crlValue) throws NullPointerException {
        super.addCrlValue(crlValue);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions addEnabledCipherSuite(String suite) {
        super.addEnabledCipherSuite(suite);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions setIdleTimeout(int idleTimeout) {
        super.setIdleTimeout(idleTimeout);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    @GenIgnore
    public EventBusOptions setKeyCertOptions(KeyCertOptions options) {
        super.setKeyCertOptions(options);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions setKeyStoreOptions(JksOptions options) {
        super.setKeyStoreOptions(options);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions setPemKeyCertOptions(PemKeyCertOptions options) {
        super.setPemKeyCertOptions(options);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions setPemTrustOptions(PemTrustOptions options) {
        super.setPemTrustOptions(options);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions setPfxKeyCertOptions(PfxOptions options) {
        super.setPfxKeyCertOptions(options);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions setPfxTrustOptions(PfxOptions options) {
        super.setPfxTrustOptions(options);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions setSoLinger(int soLinger) {
        super.setSoLinger(soLinger);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions setSsl(boolean ssl) {
        super.setSsl(ssl);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions setTcpKeepAlive(boolean tcpKeepAlive) {
        super.setTcpKeepAlive(tcpKeepAlive);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions setTcpNoDelay(boolean tcpNoDelay) {
        super.setTcpNoDelay(tcpNoDelay);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions setTrustOptions(TrustOptions options) {
        super.setTrustOptions(options);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions setTrustStoreOptions(JksOptions options) {
        super.setTrustStoreOptions(options);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions setUsePooledBuffers(boolean usePooledBuffers) {
        super.setUsePooledBuffers(usePooledBuffers);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public EventBusOptions setReceiveBufferSize(int receiveBufferSize) {
        super.setReceiveBufferSize(receiveBufferSize);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public EventBusOptions setReuseAddress(boolean reuseAddress) {
        super.setReuseAddress(reuseAddress);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public EventBusOptions setReusePort(boolean reusePort) {
        super.setReusePort(reusePort);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public EventBusOptions setSendBufferSize(int sendBufferSize) {
        super.setSendBufferSize(sendBufferSize);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public EventBusOptions setTrafficClass(int trafficClass) {
        super.setTrafficClass(trafficClass);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions setUseAlpn(boolean useAlpn) {
        return (EventBusOptions) super.setUseAlpn(useAlpn);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions setSslEngineOptions(SSLEngineOptions sslEngineOptions) {
        return (EventBusOptions) super.setSslEngineOptions(sslEngineOptions);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions setJdkSslEngineOptions(JdkSSLEngineOptions sslEngineOptions) {
        return (EventBusOptions) super.setJdkSslEngineOptions(sslEngineOptions);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions setOpenSslEngineOptions(OpenSSLEngineOptions sslEngineOptions) {
        return (EventBusOptions) super.setOpenSslEngineOptions(sslEngineOptions);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions setEnabledSecureTransportProtocols(Set<String> enabledSecureTransportProtocols) {
        return (EventBusOptions) super.setEnabledSecureTransportProtocols(enabledSecureTransportProtocols);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions addEnabledSecureTransportProtocol(String protocol) {
        return (EventBusOptions) super.addEnabledSecureTransportProtocol(protocol);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions removeEnabledSecureTransportProtocol(String protocol) {
        return (EventBusOptions) super.removeEnabledSecureTransportProtocol(protocol);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions setTcpFastOpen(boolean tcpFastOpen) {
        return (EventBusOptions) super.setTcpFastOpen(tcpFastOpen);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions setTcpCork(boolean tcpCork) {
        return (EventBusOptions) super.setTcpCork(tcpCork);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions setTcpQuickAck(boolean tcpQuickAck) {
        return (EventBusOptions) super.setTcpQuickAck(tcpQuickAck);
    }

    @Override // io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public EventBusOptions setLogActivity(boolean logEnabled) {
        return (EventBusOptions) super.setLogActivity(logEnabled);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions setSslHandshakeTimeout(long sslHandshakeTimeout) {
        return (EventBusOptions) super.setSslHandshakeTimeout(sslHandshakeTimeout);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public EventBusOptions setSslHandshakeTimeoutUnit(TimeUnit sslHandshakeTimeoutUnit) {
        return (EventBusOptions) super.setSslHandshakeTimeoutUnit(sslHandshakeTimeoutUnit);
    }

    public boolean isClustered() {
        return this.clustered;
    }

    public EventBusOptions setClustered(boolean clustered) {
        this.clustered = clustered;
        return this;
    }

    public EventBusOptions setTrustAll(boolean trustAll) {
        this.trustAll = trustAll;
        return this;
    }

    public boolean isTrustAll() {
        return this.trustAll;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public EventBusOptions setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public long getClusterPingInterval() {
        return this.clusterPingInterval;
    }

    public EventBusOptions setClusterPingInterval(long clusterPingInterval) {
        if (clusterPingInterval < 1) {
            throw new IllegalArgumentException("clusterPingInterval must be greater than 0");
        }
        this.clusterPingInterval = clusterPingInterval;
        return this;
    }

    public long getClusterPingReplyInterval() {
        return this.clusterPingReplyInterval;
    }

    public EventBusOptions setClusterPingReplyInterval(long clusterPingReplyInterval) {
        if (clusterPingReplyInterval < 1) {
            throw new IllegalArgumentException("clusterPingReplyInterval must be greater than 0");
        }
        this.clusterPingReplyInterval = clusterPingReplyInterval;
        return this;
    }

    public String getClusterPublicHost() {
        return this.clusterPublicHost;
    }

    public EventBusOptions setClusterPublicHost(String clusterPublicHost) {
        this.clusterPublicHost = clusterPublicHost;
        return this;
    }

    public int getClusterPublicPort() {
        return this.clusterPublicPort;
    }

    public EventBusOptions setClusterPublicPort(int clusterPublicPort) {
        if (clusterPublicPort < 0 || clusterPublicPort > 65535) {
            throw new IllegalArgumentException("clusterPublicPort p must be in range 0 <= p <= 65535");
        }
        this.clusterPublicPort = clusterPublicPort;
        return this;
    }
}
