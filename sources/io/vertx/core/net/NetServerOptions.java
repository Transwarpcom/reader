package io.vertx.core.net;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.ClientAuth;
import io.vertx.core.json.JsonObject;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@DataObject(generateConverter = true, publicConverter = false)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/NetServerOptions.class */
public class NetServerOptions extends TCPSSLOptions {
    public static final int DEFAULT_PORT = 0;
    public static final String DEFAULT_HOST = "0.0.0.0";
    public static final int DEFAULT_ACCEPT_BACKLOG = -1;
    public static final ClientAuth DEFAULT_CLIENT_AUTH = ClientAuth.NONE;
    public static final boolean DEFAULT_SNI = false;
    private int port;
    private String host;
    private int acceptBacklog;
    private ClientAuth clientAuth;
    private boolean sni;

    @Override // io.vertx.core.net.TCPSSLOptions
    public /* bridge */ /* synthetic */ TCPSSLOptions setEnabledSecureTransportProtocols(Set set) {
        return setEnabledSecureTransportProtocols((Set<String>) set);
    }

    public NetServerOptions() {
        init();
    }

    public NetServerOptions(NetServerOptions other) {
        super(other);
        this.port = other.getPort();
        this.host = other.getHost();
        this.acceptBacklog = other.getAcceptBacklog();
        this.clientAuth = other.getClientAuth();
        this.sni = other.isSni();
    }

    public NetServerOptions(JsonObject json) {
        super(json);
        init();
        NetServerOptionsConverter.fromJson(json, this);
    }

    @Override // io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        NetServerOptionsConverter.toJson(this, json);
        return json;
    }

    @Override // io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public NetServerOptions setSendBufferSize(int sendBufferSize) {
        super.setSendBufferSize(sendBufferSize);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public NetServerOptions setReceiveBufferSize(int receiveBufferSize) {
        super.setReceiveBufferSize(receiveBufferSize);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public NetServerOptions setReuseAddress(boolean reuseAddress) {
        super.setReuseAddress(reuseAddress);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public NetServerOptions setReusePort(boolean reusePort) {
        super.setReusePort(reusePort);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public NetServerOptions setTrafficClass(int trafficClass) {
        super.setTrafficClass(trafficClass);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions setTcpNoDelay(boolean tcpNoDelay) {
        super.setTcpNoDelay(tcpNoDelay);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions setTcpKeepAlive(boolean tcpKeepAlive) {
        super.setTcpKeepAlive(tcpKeepAlive);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions setSoLinger(int soLinger) {
        super.setSoLinger(soLinger);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions setUsePooledBuffers(boolean usePooledBuffers) {
        super.setUsePooledBuffers(usePooledBuffers);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions setIdleTimeout(int idleTimeout) {
        super.setIdleTimeout(idleTimeout);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions setIdleTimeoutUnit(TimeUnit idleTimeoutUnit) {
        super.setIdleTimeoutUnit(idleTimeoutUnit);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions setSsl(boolean ssl) {
        super.setSsl(ssl);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions setUseAlpn(boolean useAlpn) {
        super.setUseAlpn(useAlpn);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions setSslEngineOptions(SSLEngineOptions sslEngineOptions) {
        super.setSslEngineOptions(sslEngineOptions);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions setJdkSslEngineOptions(JdkSSLEngineOptions sslEngineOptions) {
        return (NetServerOptions) super.setSslEngineOptions((SSLEngineOptions) sslEngineOptions);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions setOpenSslEngineOptions(OpenSSLEngineOptions sslEngineOptions) {
        return (NetServerOptions) super.setSslEngineOptions((SSLEngineOptions) sslEngineOptions);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions setKeyCertOptions(KeyCertOptions options) {
        super.setKeyCertOptions(options);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions setKeyStoreOptions(JksOptions options) {
        super.setKeyStoreOptions(options);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions setPfxKeyCertOptions(PfxOptions options) {
        return (NetServerOptions) super.setPfxKeyCertOptions(options);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions setPemKeyCertOptions(PemKeyCertOptions options) {
        return (NetServerOptions) super.setPemKeyCertOptions(options);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions setTrustOptions(TrustOptions options) {
        super.setTrustOptions(options);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions setTrustStoreOptions(JksOptions options) {
        super.setTrustStoreOptions(options);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions setPfxTrustOptions(PfxOptions options) {
        return (NetServerOptions) super.setPfxTrustOptions(options);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions setPemTrustOptions(PemTrustOptions options) {
        return (NetServerOptions) super.setPemTrustOptions(options);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions addEnabledCipherSuite(String suite) {
        super.addEnabledCipherSuite(suite);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions addEnabledSecureTransportProtocol(String protocol) {
        super.addEnabledSecureTransportProtocol(protocol);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions removeEnabledSecureTransportProtocol(String protocol) {
        return (NetServerOptions) super.removeEnabledSecureTransportProtocol(protocol);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions setTcpFastOpen(boolean tcpFastOpen) {
        return (NetServerOptions) super.setTcpFastOpen(tcpFastOpen);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions setTcpCork(boolean tcpCork) {
        return (NetServerOptions) super.setTcpCork(tcpCork);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions setTcpQuickAck(boolean tcpQuickAck) {
        return (NetServerOptions) super.setTcpQuickAck(tcpQuickAck);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions addCrlPath(String crlPath) throws NullPointerException {
        return (NetServerOptions) super.addCrlPath(crlPath);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions addCrlValue(Buffer crlValue) throws NullPointerException {
        return (NetServerOptions) super.addCrlValue(crlValue);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions setEnabledSecureTransportProtocols(Set<String> enabledSecureTransportProtocols) {
        return (NetServerOptions) super.setEnabledSecureTransportProtocols(enabledSecureTransportProtocols);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions setSslHandshakeTimeout(long sslHandshakeTimeout) {
        return (NetServerOptions) super.setSslHandshakeTimeout(sslHandshakeTimeout);
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public NetServerOptions setSslHandshakeTimeoutUnit(TimeUnit sslHandshakeTimeoutUnit) {
        return (NetServerOptions) super.setSslHandshakeTimeoutUnit(sslHandshakeTimeoutUnit);
    }

    public int getAcceptBacklog() {
        return this.acceptBacklog;
    }

    public NetServerOptions setAcceptBacklog(int acceptBacklog) {
        this.acceptBacklog = acceptBacklog;
        return this;
    }

    public int getPort() {
        return this.port;
    }

    public NetServerOptions setPort(int port) {
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("port p must be in range 0 <= p <= 65535");
        }
        this.port = port;
        return this;
    }

    public String getHost() {
        return this.host;
    }

    public NetServerOptions setHost(String host) {
        this.host = host;
        return this;
    }

    @Deprecated
    public boolean isClientAuthRequired() {
        return this.clientAuth == ClientAuth.REQUIRED;
    }

    @Deprecated
    public NetServerOptions setClientAuthRequired(boolean clientAuthRequired) {
        this.clientAuth = clientAuthRequired ? ClientAuth.REQUIRED : ClientAuth.NONE;
        return this;
    }

    public ClientAuth getClientAuth() {
        return this.clientAuth;
    }

    public NetServerOptions setClientAuth(ClientAuth clientAuth) {
        this.clientAuth = clientAuth;
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public NetServerOptions setLogActivity(boolean logEnabled) {
        return (NetServerOptions) super.setLogActivity(logEnabled);
    }

    public boolean isSni() {
        return this.sni;
    }

    public NetServerOptions setSni(boolean sni) {
        this.sni = sni;
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NetServerOptions) || !super.equals(o)) {
            return false;
        }
        NetServerOptions that = (NetServerOptions) o;
        if (this.acceptBacklog != that.acceptBacklog || this.clientAuth != that.clientAuth || this.port != that.port) {
            return false;
        }
        if (this.host != null) {
            if (!this.host.equals(that.host)) {
                return false;
            }
        } else if (that.host != null) {
            return false;
        }
        return this.sni == that.sni;
    }

    @Override // io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public int hashCode() {
        int result = super.hashCode();
        return (31 * ((31 * ((31 * ((31 * ((31 * result) + this.port)) + (this.host != null ? this.host.hashCode() : 0))) + this.acceptBacklog)) + this.clientAuth.hashCode())) + (this.sni ? 1 : 0);
    }

    private void init() {
        this.port = 0;
        this.host = DEFAULT_HOST;
        this.acceptBacklog = -1;
        this.clientAuth = DEFAULT_CLIENT_AUTH;
        this.sni = false;
    }
}
