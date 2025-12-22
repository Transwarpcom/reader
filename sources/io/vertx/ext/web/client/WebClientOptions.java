package io.vertx.ext.web.client;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.Http2Settings;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.impl.launcher.commands.VersionCommand;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.JdkSSLEngineOptions;
import io.vertx.core.net.JksOptions;
import io.vertx.core.net.KeyCertOptions;
import io.vertx.core.net.OpenSSLEngineOptions;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.core.net.PemTrustOptions;
import io.vertx.core.net.PfxOptions;
import io.vertx.core.net.ProxyOptions;
import io.vertx.core.net.SSLEngineOptions;
import io.vertx.core.net.TCPSSLOptions;
import io.vertx.core.net.TrustOptions;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@DataObject(generateConverter = true)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-client-3.8.5.jar:io/vertx/ext/web/client/WebClientOptions.class */
public class WebClientOptions extends HttpClientOptions {
    public static final boolean DEFAULT_USER_AGENT_ENABLED = true;
    public static final String DEFAULT_USER_AGENT = loadUserAgent();
    public static final boolean DEFAULT_FOLLOW_REDIRECTS = true;
    private boolean userAgentEnabled;
    private String userAgent;
    private boolean followRedirects;

    @Override // io.vertx.core.http.HttpClientOptions
    public /* bridge */ /* synthetic */ HttpClientOptions setAlpnVersions(List list) {
        return setAlpnVersions((List<HttpVersion>) list);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.TCPSSLOptions
    public /* bridge */ /* synthetic */ HttpClientOptions setEnabledSecureTransportProtocols(Set set) {
        return setEnabledSecureTransportProtocols((Set<String>) set);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.TCPSSLOptions
    public /* bridge */ /* synthetic */ TCPSSLOptions setEnabledSecureTransportProtocols(Set set) {
        return setEnabledSecureTransportProtocols((Set<String>) set);
    }

    public WebClientOptions() {
        this.userAgentEnabled = true;
        this.userAgent = DEFAULT_USER_AGENT;
        this.followRedirects = true;
    }

    public WebClientOptions(WebClientOptions other) {
        super(other);
        this.userAgentEnabled = true;
        this.userAgent = DEFAULT_USER_AGENT;
        this.followRedirects = true;
        init(other);
    }

    public WebClientOptions(HttpClientOptions other) {
        super(other);
        this.userAgentEnabled = true;
        this.userAgent = DEFAULT_USER_AGENT;
        this.followRedirects = true;
    }

    public WebClientOptions(JsonObject json) {
        super(json);
        this.userAgentEnabled = true;
        this.userAgent = DEFAULT_USER_AGENT;
        this.followRedirects = true;
        WebClientOptionsConverter.fromJson(json, this);
    }

    void init(WebClientOptions other) {
        this.userAgentEnabled = other.userAgentEnabled;
        this.userAgent = other.userAgent;
        this.followRedirects = other.followRedirects;
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        WebClientOptionsConverter.toJson(this, json);
        return json;
    }

    public boolean isUserAgentEnabled() {
        return this.userAgentEnabled;
    }

    public WebClientOptions setUserAgentEnabled(boolean userAgentEnabled) {
        this.userAgentEnabled = userAgentEnabled;
        return this;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public WebClientOptions setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public boolean isFollowRedirects() {
        return this.followRedirects;
    }

    public WebClientOptions setFollowRedirects(boolean followRedirects) {
        this.followRedirects = followRedirects;
        return this;
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setMaxRedirects(int maxRedirects) {
        return (WebClientOptions) super.setMaxRedirects(maxRedirects);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public WebClientOptions setSendBufferSize(int sendBufferSize) {
        return (WebClientOptions) super.setSendBufferSize(sendBufferSize);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public WebClientOptions setReceiveBufferSize(int receiveBufferSize) {
        return (WebClientOptions) super.setReceiveBufferSize(receiveBufferSize);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public WebClientOptions setReuseAddress(boolean reuseAddress) {
        return (WebClientOptions) super.setReuseAddress(reuseAddress);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public WebClientOptions setTrafficClass(int trafficClass) {
        return (WebClientOptions) super.setTrafficClass(trafficClass);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions setTcpNoDelay(boolean tcpNoDelay) {
        return (WebClientOptions) super.setTcpNoDelay(tcpNoDelay);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions setTcpKeepAlive(boolean tcpKeepAlive) {
        return (WebClientOptions) super.setTcpKeepAlive(tcpKeepAlive);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions setSoLinger(int soLinger) {
        return (WebClientOptions) super.setSoLinger(soLinger);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions setUsePooledBuffers(boolean usePooledBuffers) {
        return (WebClientOptions) super.setUsePooledBuffers(usePooledBuffers);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions setIdleTimeout(int idleTimeout) {
        return (WebClientOptions) super.setIdleTimeout(idleTimeout);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions setIdleTimeoutUnit(TimeUnit idleTimeoutUnit) {
        return (WebClientOptions) super.setIdleTimeoutUnit(idleTimeoutUnit);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions setSsl(boolean ssl) {
        return (WebClientOptions) super.setSsl(ssl);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions setKeyCertOptions(KeyCertOptions options) {
        return (WebClientOptions) super.setKeyCertOptions(options);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions setKeyStoreOptions(JksOptions options) {
        return (WebClientOptions) super.setKeyStoreOptions(options);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions setPfxKeyCertOptions(PfxOptions options) {
        return (WebClientOptions) super.setPfxKeyCertOptions(options);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions setTrustOptions(TrustOptions options) {
        return (WebClientOptions) super.setTrustOptions(options);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions setPemKeyCertOptions(PemKeyCertOptions options) {
        return (WebClientOptions) super.setPemKeyCertOptions(options);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions setTrustStoreOptions(JksOptions options) {
        return (WebClientOptions) super.setTrustStoreOptions(options);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions setPfxTrustOptions(PfxOptions options) {
        return (WebClientOptions) super.setPfxTrustOptions(options);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions setPemTrustOptions(PemTrustOptions options) {
        return (WebClientOptions) super.setPemTrustOptions(options);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions addEnabledCipherSuite(String suite) {
        return (WebClientOptions) super.addEnabledCipherSuite(suite);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions addCrlPath(String crlPath) throws NullPointerException {
        return (WebClientOptions) super.addCrlPath(crlPath);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions addCrlValue(Buffer crlValue) throws NullPointerException {
        return (WebClientOptions) super.addCrlValue(crlValue);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase
    public WebClientOptions setConnectTimeout(int connectTimeout) {
        return (WebClientOptions) super.setConnectTimeout(connectTimeout);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase
    public WebClientOptions setTrustAll(boolean trustAll) {
        return (WebClientOptions) super.setTrustAll(trustAll);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setMaxPoolSize(int maxPoolSize) {
        return (WebClientOptions) super.setMaxPoolSize(maxPoolSize);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setHttp2MultiplexingLimit(int limit) {
        return (WebClientOptions) super.setHttp2MultiplexingLimit(limit);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setHttp2MaxPoolSize(int max) {
        return (WebClientOptions) super.setHttp2MaxPoolSize(max);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setHttp2ConnectionWindowSize(int http2ConnectionWindowSize) {
        return (WebClientOptions) super.setHttp2ConnectionWindowSize(http2ConnectionWindowSize);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setKeepAlive(boolean keepAlive) {
        return (WebClientOptions) super.setKeepAlive(keepAlive);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setPipelining(boolean pipelining) {
        return (WebClientOptions) super.setPipelining(pipelining);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setPipeliningLimit(int limit) {
        return (WebClientOptions) super.setPipeliningLimit(limit);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setVerifyHost(boolean verifyHost) {
        return (WebClientOptions) super.setVerifyHost(verifyHost);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setTryUseCompression(boolean tryUseCompression) {
        return (WebClientOptions) super.setTryUseCompression(tryUseCompression);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setSendUnmaskedFrames(boolean sendUnmaskedFrames) {
        return (WebClientOptions) super.setSendUnmaskedFrames(sendUnmaskedFrames);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setMaxWebSocketFrameSize(int maxWebsocketFrameSize) {
        return (WebClientOptions) super.setMaxWebSocketFrameSize(maxWebsocketFrameSize);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setDefaultHost(String defaultHost) {
        return (WebClientOptions) super.setDefaultHost(defaultHost);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setDefaultPort(int defaultPort) {
        return (WebClientOptions) super.setDefaultPort(defaultPort);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setMaxChunkSize(int maxChunkSize) {
        return (WebClientOptions) super.setMaxChunkSize(maxChunkSize);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setProtocolVersion(HttpVersion protocolVersion) {
        return (WebClientOptions) super.setProtocolVersion(protocolVersion);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setMaxHeaderSize(int maxHeaderSize) {
        return (WebClientOptions) super.setMaxHeaderSize(maxHeaderSize);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setMaxWaitQueueSize(int maxWaitQueueSize) {
        return (WebClientOptions) super.setMaxWaitQueueSize(maxWaitQueueSize);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions setUseAlpn(boolean useAlpn) {
        return (WebClientOptions) super.setUseAlpn(useAlpn);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions setSslEngineOptions(SSLEngineOptions sslEngineOptions) {
        return (WebClientOptions) super.setSslEngineOptions(sslEngineOptions);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions setJdkSslEngineOptions(JdkSSLEngineOptions sslEngineOptions) {
        return (WebClientOptions) super.setJdkSslEngineOptions(sslEngineOptions);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions setOpenSslEngineOptions(OpenSSLEngineOptions sslEngineOptions) {
        return (WebClientOptions) super.setOpenSslEngineOptions(sslEngineOptions);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setHttp2ClearTextUpgrade(boolean value) {
        return (WebClientOptions) super.setHttp2ClearTextUpgrade(value);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setAlpnVersions(List<HttpVersion> alpnVersions) {
        return (WebClientOptions) super.setAlpnVersions(alpnVersions);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase
    public WebClientOptions setMetricsName(String metricsName) {
        return (WebClientOptions) super.setMetricsName(metricsName);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase
    public WebClientOptions setProxyOptions(ProxyOptions proxyOptions) {
        return (WebClientOptions) super.setProxyOptions(proxyOptions);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase
    public WebClientOptions setLocalAddress(String localAddress) {
        return (WebClientOptions) super.setLocalAddress(localAddress);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public WebClientOptions setLogActivity(boolean logEnabled) {
        return (WebClientOptions) super.setLogActivity(logEnabled);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions addEnabledSecureTransportProtocol(String protocol) {
        return (WebClientOptions) super.addEnabledSecureTransportProtocol(protocol);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions removeEnabledSecureTransportProtocol(String protocol) {
        return (WebClientOptions) super.removeEnabledSecureTransportProtocol(protocol);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions setEnabledSecureTransportProtocols(Set<String> enabledSecureTransportProtocols) {
        return (WebClientOptions) super.setEnabledSecureTransportProtocols(enabledSecureTransportProtocols);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public WebClientOptions setReusePort(boolean reusePort) {
        return (WebClientOptions) super.setReusePort(reusePort);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions setTcpFastOpen(boolean tcpFastOpen) {
        return (WebClientOptions) super.setTcpFastOpen(tcpFastOpen);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions setTcpCork(boolean tcpCork) {
        return (WebClientOptions) super.setTcpCork(tcpCork);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions setTcpQuickAck(boolean tcpQuickAck) {
        return (WebClientOptions) super.setTcpQuickAck(tcpQuickAck);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setHttp2KeepAliveTimeout(int keepAliveTimeout) {
        return (WebClientOptions) super.setHttp2KeepAliveTimeout(keepAliveTimeout);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setForceSni(boolean forceSni) {
        return (WebClientOptions) super.setForceSni(forceSni);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setDecoderInitialBufferSize(int decoderInitialBufferSize) {
        return (WebClientOptions) super.setDecoderInitialBufferSize(decoderInitialBufferSize);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setPoolCleanerPeriod(int poolCleanerPeriod) {
        return (WebClientOptions) super.setPoolCleanerPeriod(poolCleanerPeriod);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setKeepAliveTimeout(int keepAliveTimeout) {
        return (WebClientOptions) super.setKeepAliveTimeout(keepAliveTimeout);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setMaxWebSocketMessageSize(int maxWebsocketMessageSize) {
        return (WebClientOptions) super.setMaxWebSocketMessageSize(maxWebsocketMessageSize);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setMaxInitialLineLength(int maxInitialLineLength) {
        return (WebClientOptions) super.setMaxInitialLineLength(maxInitialLineLength);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setInitialSettings(Http2Settings settings) {
        return (WebClientOptions) super.setInitialSettings(settings);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions setSslHandshakeTimeout(long sslHandshakeTimeout) {
        return (WebClientOptions) super.setSslHandshakeTimeout(sslHandshakeTimeout);
    }

    @Override // io.vertx.core.http.HttpClientOptions, io.vertx.core.net.TCPSSLOptions
    public WebClientOptions setSslHandshakeTimeoutUnit(TimeUnit sslHandshakeTimeoutUnit) {
        return (WebClientOptions) super.setSslHandshakeTimeoutUnit(sslHandshakeTimeoutUnit);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setTryUsePerFrameWebSocketCompression(boolean offer) {
        return (WebClientOptions) super.setTryUsePerFrameWebSocketCompression(offer);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setTryUsePerMessageWebSocketCompression(boolean offer) {
        return (WebClientOptions) super.setTryUsePerMessageWebSocketCompression(offer);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setWebSocketCompressionLevel(int compressionLevel) {
        return (WebClientOptions) super.setWebSocketCompressionLevel(compressionLevel);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setWebSocketCompressionAllowClientNoContext(boolean offer) {
        return (WebClientOptions) super.setWebSocketCompressionAllowClientNoContext(offer);
    }

    @Override // io.vertx.core.http.HttpClientOptions
    public WebClientOptions setWebSocketCompressionRequestServerNoContext(boolean offer) {
        return (WebClientOptions) super.setWebSocketCompressionRequestServerNoContext(offer);
    }

    public static String loadUserAgent() throws IOException {
        String userAgent = "Vert.x-WebClient";
        String version = VersionCommand.getVersion();
        if (version.length() > 0) {
            userAgent = userAgent + "/" + version;
        }
        return userAgent;
    }
}
