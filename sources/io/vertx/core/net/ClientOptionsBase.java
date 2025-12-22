package io.vertx.core.net;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@DataObject(generateConverter = true, publicConverter = false)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/ClientOptionsBase.class */
public abstract class ClientOptionsBase extends TCPSSLOptions {
    public static final int DEFAULT_CONNECT_TIMEOUT = 60000;
    public static final boolean DEFAULT_TRUST_ALL = false;
    public static final String DEFAULT_METRICS_NAME = "";
    private int connectTimeout;
    private boolean trustAll;
    private String metricsName;
    private ProxyOptions proxyOptions;
    private String localAddress;

    public ClientOptionsBase() {
        init();
    }

    public ClientOptionsBase(ClientOptionsBase other) {
        super(other);
        this.connectTimeout = other.getConnectTimeout();
        this.trustAll = other.isTrustAll();
        this.metricsName = other.metricsName;
        this.proxyOptions = other.proxyOptions != null ? new ProxyOptions(other.proxyOptions) : null;
        this.localAddress = other.localAddress;
    }

    public ClientOptionsBase(JsonObject json) {
        super(json);
        init();
        ClientOptionsBaseConverter.fromJson(json, this);
    }

    @Override // io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        ClientOptionsBaseConverter.toJson(this, json);
        return json;
    }

    private void init() {
        this.connectTimeout = 60000;
        this.trustAll = false;
        this.metricsName = "";
        this.proxyOptions = null;
        this.localAddress = null;
    }

    public boolean isTrustAll() {
        return this.trustAll;
    }

    public ClientOptionsBase setTrustAll(boolean trustAll) {
        this.trustAll = trustAll;
        return this;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public ClientOptionsBase setConnectTimeout(int connectTimeout) {
        if (connectTimeout < 0) {
            throw new IllegalArgumentException("connectTimeout must be >= 0");
        }
        this.connectTimeout = connectTimeout;
        return this;
    }

    public String getMetricsName() {
        return this.metricsName;
    }

    public ClientOptionsBase setMetricsName(String metricsName) {
        this.metricsName = metricsName;
        return this;
    }

    public ClientOptionsBase setProxyOptions(ProxyOptions proxyOptions) {
        this.proxyOptions = proxyOptions;
        return this;
    }

    public ProxyOptions getProxyOptions() {
        return this.proxyOptions;
    }

    public String getLocalAddress() {
        return this.localAddress;
    }

    public ClientOptionsBase setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public ClientOptionsBase setLogActivity(boolean logEnabled) {
        return (ClientOptionsBase) super.setLogActivity(logEnabled);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase setTcpNoDelay(boolean tcpNoDelay) {
        return (ClientOptionsBase) super.setTcpNoDelay(tcpNoDelay);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase setTcpKeepAlive(boolean tcpKeepAlive) {
        return (ClientOptionsBase) super.setTcpKeepAlive(tcpKeepAlive);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase setSoLinger(int soLinger) {
        return (ClientOptionsBase) super.setSoLinger(soLinger);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase setUsePooledBuffers(boolean usePooledBuffers) {
        return (ClientOptionsBase) super.setUsePooledBuffers(usePooledBuffers);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase setIdleTimeout(int idleTimeout) {
        return (ClientOptionsBase) super.setIdleTimeout(idleTimeout);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase setIdleTimeoutUnit(TimeUnit idleTimeoutUnit) {
        return (ClientOptionsBase) super.setIdleTimeoutUnit(idleTimeoutUnit);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase setSsl(boolean ssl) {
        return (ClientOptionsBase) super.setSsl(ssl);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase setKeyCertOptions(KeyCertOptions options) {
        return (ClientOptionsBase) super.setKeyCertOptions(options);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase setKeyStoreOptions(JksOptions options) {
        return (ClientOptionsBase) super.setKeyStoreOptions(options);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase setPfxKeyCertOptions(PfxOptions options) {
        return (ClientOptionsBase) super.setPfxKeyCertOptions(options);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase setPemKeyCertOptions(PemKeyCertOptions options) {
        return (ClientOptionsBase) super.setPemKeyCertOptions(options);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase setTrustOptions(TrustOptions options) {
        return (ClientOptionsBase) super.setTrustOptions(options);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase setTrustStoreOptions(JksOptions options) {
        return (ClientOptionsBase) super.setTrustStoreOptions(options);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase setPfxTrustOptions(PfxOptions options) {
        return (ClientOptionsBase) super.setPfxTrustOptions(options);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase setPemTrustOptions(PemTrustOptions options) {
        return (ClientOptionsBase) super.setPemTrustOptions(options);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase setUseAlpn(boolean useAlpn) {
        return (ClientOptionsBase) super.setUseAlpn(useAlpn);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase setSslEngineOptions(SSLEngineOptions sslEngineOptions) {
        return (ClientOptionsBase) super.setSslEngineOptions(sslEngineOptions);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase setJdkSslEngineOptions(JdkSSLEngineOptions sslEngineOptions) {
        return (ClientOptionsBase) super.setJdkSslEngineOptions(sslEngineOptions);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase setOpenSslEngineOptions(OpenSSLEngineOptions sslEngineOptions) {
        return (ClientOptionsBase) super.setOpenSslEngineOptions(sslEngineOptions);
    }

    @Override // io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public ClientOptionsBase setSendBufferSize(int sendBufferSize) {
        return (ClientOptionsBase) super.setSendBufferSize(sendBufferSize);
    }

    @Override // io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public ClientOptionsBase setReceiveBufferSize(int receiveBufferSize) {
        return (ClientOptionsBase) super.setReceiveBufferSize(receiveBufferSize);
    }

    @Override // io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public ClientOptionsBase setReuseAddress(boolean reuseAddress) {
        return (ClientOptionsBase) super.setReuseAddress(reuseAddress);
    }

    @Override // io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public ClientOptionsBase setReusePort(boolean reusePort) {
        return (ClientOptionsBase) super.setReusePort(reusePort);
    }

    @Override // io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public ClientOptionsBase setTrafficClass(int trafficClass) {
        return (ClientOptionsBase) super.setTrafficClass(trafficClass);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase addEnabledCipherSuite(String suite) {
        return (ClientOptionsBase) super.addEnabledCipherSuite(suite);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase addCrlPath(String crlPath) throws NullPointerException {
        return (ClientOptionsBase) super.addCrlPath(crlPath);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase addCrlValue(Buffer crlValue) throws NullPointerException {
        return (ClientOptionsBase) super.addCrlValue(crlValue);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase addEnabledSecureTransportProtocol(String protocol) {
        return (ClientOptionsBase) super.addEnabledSecureTransportProtocol(protocol);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase removeEnabledSecureTransportProtocol(String protocol) {
        return (ClientOptionsBase) super.removeEnabledSecureTransportProtocol(protocol);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase setTcpFastOpen(boolean tcpFastOpen) {
        return (ClientOptionsBase) super.setTcpFastOpen(tcpFastOpen);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase setTcpCork(boolean tcpCork) {
        return (ClientOptionsBase) super.setTcpCork(tcpCork);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public ClientOptionsBase setTcpQuickAck(boolean tcpQuickAck) {
        return (ClientOptionsBase) super.setTcpQuickAck(tcpQuickAck);
    }

    @Override // io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClientOptionsBase) || !super.equals(o)) {
            return false;
        }
        ClientOptionsBase that = (ClientOptionsBase) o;
        return this.connectTimeout == that.connectTimeout && this.trustAll == that.trustAll && Objects.equals(this.metricsName, that.metricsName) && Objects.equals(this.proxyOptions, that.proxyOptions) && Objects.equals(this.localAddress, that.localAddress);
    }

    @Override // io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public int hashCode() {
        int result = super.hashCode();
        return (31 * ((31 * ((31 * ((31 * ((31 * result) + this.connectTimeout)) + (this.trustAll ? 1 : 0))) + (this.metricsName != null ? this.metricsName.hashCode() : 0))) + (this.proxyOptions != null ? this.proxyOptions.hashCode() : 0))) + (this.localAddress != null ? this.localAddress.hashCode() : 0);
    }
}
