package io.vertx.core.net;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@DataObject(generateConverter = true, publicConverter = false)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/NetClientOptions.class */
public class NetClientOptions extends ClientOptionsBase {
    public static final int DEFAULT_RECONNECT_ATTEMPTS = 0;
    public static final long DEFAULT_RECONNECT_INTERVAL = 1000;
    public static final String DEFAULT_HOSTNAME_VERIFICATION_ALGORITHM = "";
    private int reconnectAttempts;
    private long reconnectInterval;
    private String hostnameVerificationAlgorithm;

    @Override // io.vertx.core.net.TCPSSLOptions
    public /* bridge */ /* synthetic */ TCPSSLOptions setEnabledSecureTransportProtocols(Set set) {
        return setEnabledSecureTransportProtocols((Set<String>) set);
    }

    public NetClientOptions() {
        init();
    }

    public NetClientOptions(NetClientOptions other) {
        super(other);
        this.reconnectAttempts = other.getReconnectAttempts();
        this.reconnectInterval = other.getReconnectInterval();
        this.hostnameVerificationAlgorithm = other.getHostnameVerificationAlgorithm();
    }

    public NetClientOptions(JsonObject json) {
        super(json);
        init();
        NetClientOptionsConverter.fromJson(json, this);
    }

    private void init() {
        this.reconnectAttempts = 0;
        this.reconnectInterval = 1000L;
        this.hostnameVerificationAlgorithm = "";
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public NetClientOptions setSendBufferSize(int sendBufferSize) {
        super.setSendBufferSize(sendBufferSize);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public NetClientOptions setReceiveBufferSize(int receiveBufferSize) {
        super.setReceiveBufferSize(receiveBufferSize);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public NetClientOptions setReuseAddress(boolean reuseAddress) {
        super.setReuseAddress(reuseAddress);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public NetClientOptions setReusePort(boolean reusePort) {
        super.setReusePort(reusePort);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public NetClientOptions setTrafficClass(int trafficClass) {
        super.setTrafficClass(trafficClass);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public NetClientOptions setTcpNoDelay(boolean tcpNoDelay) {
        super.setTcpNoDelay(tcpNoDelay);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public NetClientOptions setTcpKeepAlive(boolean tcpKeepAlive) {
        super.setTcpKeepAlive(tcpKeepAlive);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public NetClientOptions setSoLinger(int soLinger) {
        super.setSoLinger(soLinger);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public NetClientOptions setUsePooledBuffers(boolean usePooledBuffers) {
        super.setUsePooledBuffers(usePooledBuffers);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public NetClientOptions setIdleTimeout(int idleTimeout) {
        super.setIdleTimeout(idleTimeout);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public NetClientOptions setIdleTimeoutUnit(TimeUnit idleTimeoutUnit) {
        super.setIdleTimeoutUnit(idleTimeoutUnit);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public NetClientOptions setSsl(boolean ssl) {
        super.setSsl(ssl);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public NetClientOptions setKeyCertOptions(KeyCertOptions options) {
        super.setKeyCertOptions(options);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public NetClientOptions setKeyStoreOptions(JksOptions options) {
        super.setKeyStoreOptions(options);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public NetClientOptions setPfxKeyCertOptions(PfxOptions options) {
        return (NetClientOptions) super.setPfxKeyCertOptions(options);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public NetClientOptions setPemKeyCertOptions(PemKeyCertOptions options) {
        return (NetClientOptions) super.setPemKeyCertOptions(options);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public NetClientOptions setTrustOptions(TrustOptions options) {
        super.setTrustOptions(options);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public NetClientOptions setTrustStoreOptions(JksOptions options) {
        super.setTrustStoreOptions(options);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public NetClientOptions setPemTrustOptions(PemTrustOptions options) {
        return (NetClientOptions) super.setPemTrustOptions(options);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public NetClientOptions setPfxTrustOptions(PfxOptions options) {
        return (NetClientOptions) super.setPfxTrustOptions(options);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public NetClientOptions addEnabledCipherSuite(String suite) {
        super.addEnabledCipherSuite(suite);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public NetClientOptions addEnabledSecureTransportProtocol(String protocol) {
        super.addEnabledSecureTransportProtocol(protocol);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public NetClientOptions removeEnabledSecureTransportProtocol(String protocol) {
        return (NetClientOptions) super.removeEnabledSecureTransportProtocol(protocol);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public NetClientOptions setUseAlpn(boolean useAlpn) {
        return (NetClientOptions) super.setUseAlpn(useAlpn);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public NetClientOptions setSslEngineOptions(SSLEngineOptions sslEngineOptions) {
        return (NetClientOptions) super.setSslEngineOptions(sslEngineOptions);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public NetClientOptions setJdkSslEngineOptions(JdkSSLEngineOptions sslEngineOptions) {
        return (NetClientOptions) super.setJdkSslEngineOptions(sslEngineOptions);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public NetClientOptions setTcpFastOpen(boolean tcpFastOpen) {
        return (NetClientOptions) super.setTcpFastOpen(tcpFastOpen);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public NetClientOptions setTcpCork(boolean tcpCork) {
        return (NetClientOptions) super.setTcpCork(tcpCork);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public NetClientOptions setTcpQuickAck(boolean tcpQuickAck) {
        return (NetClientOptions) super.setTcpQuickAck(tcpQuickAck);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase setOpenSslEngineOptions(OpenSSLEngineOptions sslEngineOptions) {
        return super.setOpenSslEngineOptions(sslEngineOptions);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public NetClientOptions addCrlPath(String crlPath) throws NullPointerException {
        return (NetClientOptions) super.addCrlPath(crlPath);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public NetClientOptions addCrlValue(Buffer crlValue) throws NullPointerException {
        return (NetClientOptions) super.addCrlValue(crlValue);
    }

    @Override // io.vertx.core.net.ClientOptionsBase
    public NetClientOptions setTrustAll(boolean trustAll) {
        super.setTrustAll(trustAll);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase
    public NetClientOptions setConnectTimeout(int connectTimeout) {
        super.setConnectTimeout(connectTimeout);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase
    public NetClientOptions setMetricsName(String metricsName) {
        return (NetClientOptions) super.setMetricsName(metricsName);
    }

    public NetClientOptions setReconnectAttempts(int attempts) {
        if (attempts < -1) {
            throw new IllegalArgumentException("reconnect attempts must be >= -1");
        }
        this.reconnectAttempts = attempts;
        return this;
    }

    public int getReconnectAttempts() {
        return this.reconnectAttempts;
    }

    public NetClientOptions setReconnectInterval(long interval) {
        if (interval < 1) {
            throw new IllegalArgumentException("reconnect interval must be >= 1");
        }
        this.reconnectInterval = interval;
        return this;
    }

    public String getHostnameVerificationAlgorithm() {
        return this.hostnameVerificationAlgorithm;
    }

    public NetClientOptions setHostnameVerificationAlgorithm(String hostnameVerificationAlgorithm) {
        Objects.requireNonNull(hostnameVerificationAlgorithm, "hostnameVerificationAlgorithm can not be null!");
        this.hostnameVerificationAlgorithm = hostnameVerificationAlgorithm;
        return this;
    }

    public long getReconnectInterval() {
        return this.reconnectInterval;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public NetClientOptions setLogActivity(boolean logEnabled) {
        return (NetClientOptions) super.setLogActivity(logEnabled);
    }

    @Override // io.vertx.core.net.ClientOptionsBase
    public NetClientOptions setProxyOptions(ProxyOptions proxyOptions) {
        return (NetClientOptions) super.setProxyOptions(proxyOptions);
    }

    @Override // io.vertx.core.net.ClientOptionsBase
    public NetClientOptions setLocalAddress(String localAddress) {
        return (NetClientOptions) super.setLocalAddress(localAddress);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetClientOptions setEnabledSecureTransportProtocols(Set<String> enabledSecureTransportProtocols) {
        return (NetClientOptions) super.setEnabledSecureTransportProtocols(enabledSecureTransportProtocols);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetClientOptions setSslHandshakeTimeout(long sslHandshakeTimeout) {
        return (NetClientOptions) super.setSslHandshakeTimeout(sslHandshakeTimeout);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetClientOptions setSslHandshakeTimeoutUnit(TimeUnit sslHandshakeTimeoutUnit) {
        return (NetClientOptions) super.setSslHandshakeTimeoutUnit(sslHandshakeTimeoutUnit);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NetClientOptions) || !super.equals(o)) {
            return false;
        }
        NetClientOptions that = (NetClientOptions) o;
        return this.reconnectAttempts == that.reconnectAttempts && this.reconnectInterval == that.reconnectInterval && this.hostnameVerificationAlgorithm.equals(that.hostnameVerificationAlgorithm);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public int hashCode() {
        int result = super.hashCode();
        return (31 * ((31 * ((31 * result) + this.reconnectAttempts)) + ((int) (this.reconnectInterval ^ (this.reconnectInterval >>> 32))))) + this.hostnameVerificationAlgorithm.hashCode();
    }
}
