package io.vertx.core.http;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.impl.Arguments;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.ClientOptionsBase;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@DataObject(generateConverter = true, publicConverter = false)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/HttpClientOptions.class */
public class HttpClientOptions extends ClientOptionsBase {
    public static final int DEFAULT_MAX_POOL_SIZE = 5;
    public static final int DEFAULT_HTTP2_MAX_POOL_SIZE = 1;
    public static final int DEFAULT_HTTP2_MULTIPLEXING_LIMIT = -1;
    public static final int DEFAULT_HTTP2_CONNECTION_WINDOW_SIZE = -1;
    public static final int DEFAULT_HTTP2_KEEP_ALIVE_TIMEOUT = 60;
    public static final boolean DEFAULT_KEEP_ALIVE = true;
    public static final boolean DEFAULT_PIPELINING = false;
    public static final int DEFAULT_PIPELINING_LIMIT = 10;
    public static final int DEFAULT_KEEP_ALIVE_TIMEOUT = 60;
    public static final boolean DEFAULT_TRY_USE_COMPRESSION = false;
    public static final boolean DEFAULT_VERIFY_HOST = true;
    public static final int DEFAULT_MAX_WEBSOCKET_FRAME_SIZE = 65536;
    public static final int DEFAULT_MAX_WEBSOCKET_MESSAGE_SIZE = 262144;
    public static final String DEFAULT_DEFAULT_HOST = "localhost";
    public static final int DEFAULT_DEFAULT_PORT = 80;
    public static final int DEFAULT_MAX_CHUNK_SIZE = 8192;
    public static final int DEFAULT_MAX_INITIAL_LINE_LENGTH = 4096;
    public static final int DEFAULT_MAX_HEADER_SIZE = 8192;
    public static final int DEFAULT_MAX_WAIT_QUEUE_SIZE = -1;
    public static final boolean DEFAULT_HTTP2_CLEAR_TEXT_UPGRADE = true;
    public static final boolean DEFAULT_SEND_UNMASKED_FRAMES = false;
    public static final int DEFAULT_MAX_REDIRECTS = 16;
    public static final boolean DEFAULT_FORCE_SNI = false;
    public static final int DEFAULT_DECODER_INITIAL_BUFFER_SIZE = 128;
    public static final boolean DEFAULT_TRY_USE_PER_FRAME_WEBSOCKET_COMPRESSION = false;
    public static final boolean DEFAULT_TRY_USE_PER_MESSAGE_WEBSOCKET_COMPRESSION = false;
    public static final int DEFAULT_WEBSOCKET_COMPRESSION_LEVEL = 6;
    public static final boolean DEFAULT_WEBSOCKET_ALLOW_CLIENT_NO_CONTEXT = false;
    public static final boolean DEFAULT_WEBSOCKET_REQUEST_SERVER_NO_CONTEXT = false;
    public static final int DEFAULT_POOL_CLEANER_PERIOD = 1000;
    private boolean verifyHost;
    private int maxPoolSize;
    private boolean keepAlive;
    private int keepAliveTimeout;
    private int pipeliningLimit;
    private boolean pipelining;
    private int http2MaxPoolSize;
    private int http2MultiplexingLimit;
    private int http2ConnectionWindowSize;
    private int http2KeepAliveTimeout;
    private int poolCleanerPeriod;
    private boolean tryUseCompression;
    private int maxWebSocketFrameSize;
    private int maxWebSocketMessageSize;
    private String defaultHost;
    private int defaultPort;
    private HttpVersion protocolVersion;
    private int maxChunkSize;
    private int maxInitialLineLength;
    private int maxHeaderSize;
    private int maxWaitQueueSize;
    private Http2Settings initialSettings;
    private List<HttpVersion> alpnVersions;
    private boolean http2ClearTextUpgrade;
    private boolean sendUnmaskedFrames;
    private int maxRedirects;
    private boolean forceSni;
    private int decoderInitialBufferSize;
    private boolean tryUsePerFrameWebSocketCompression;
    private boolean tryUsePerMessageWebSocketCompression;
    private int webSocketCompressionLevel;
    private boolean webSocketAllowClientNoContext;
    private boolean webSocketRequestServerNoContext;
    public static final HttpVersion DEFAULT_PROTOCOL_VERSION = HttpVersion.HTTP_1_1;
    public static final List<HttpVersion> DEFAULT_ALPN_VERSIONS = Collections.emptyList();

    @Override // io.vertx.core.net.TCPSSLOptions
    public /* bridge */ /* synthetic */ TCPSSLOptions setEnabledSecureTransportProtocols(Set set) {
        return setEnabledSecureTransportProtocols((Set<String>) set);
    }

    public HttpClientOptions() {
        this.verifyHost = true;
        init();
    }

    public HttpClientOptions(HttpClientOptions other) {
        super(other);
        this.verifyHost = true;
        this.verifyHost = other.isVerifyHost();
        this.maxPoolSize = other.getMaxPoolSize();
        this.keepAlive = other.isKeepAlive();
        this.keepAliveTimeout = other.getKeepAliveTimeout();
        this.pipelining = other.isPipelining();
        this.pipeliningLimit = other.getPipeliningLimit();
        this.http2MaxPoolSize = other.getHttp2MaxPoolSize();
        this.http2MultiplexingLimit = other.http2MultiplexingLimit;
        this.http2ConnectionWindowSize = other.http2ConnectionWindowSize;
        this.http2KeepAliveTimeout = other.getHttp2KeepAliveTimeout();
        this.tryUseCompression = other.isTryUseCompression();
        this.maxWebSocketFrameSize = other.maxWebSocketFrameSize;
        this.maxWebSocketMessageSize = other.maxWebSocketMessageSize;
        this.defaultHost = other.defaultHost;
        this.defaultPort = other.defaultPort;
        this.protocolVersion = other.protocolVersion;
        this.maxChunkSize = other.maxChunkSize;
        this.maxInitialLineLength = other.getMaxInitialLineLength();
        this.maxHeaderSize = other.getMaxHeaderSize();
        this.maxWaitQueueSize = other.maxWaitQueueSize;
        this.initialSettings = other.initialSettings != null ? new Http2Settings(other.initialSettings) : null;
        this.alpnVersions = other.alpnVersions != null ? new ArrayList(other.alpnVersions) : null;
        this.http2ClearTextUpgrade = other.http2ClearTextUpgrade;
        this.sendUnmaskedFrames = other.isSendUnmaskedFrames();
        this.maxRedirects = other.maxRedirects;
        this.forceSni = other.forceSni;
        this.decoderInitialBufferSize = other.getDecoderInitialBufferSize();
        this.poolCleanerPeriod = other.getPoolCleanerPeriod();
        this.tryUsePerFrameWebSocketCompression = other.tryUsePerFrameWebSocketCompression;
        this.tryUsePerMessageWebSocketCompression = other.tryUsePerMessageWebSocketCompression;
        this.webSocketAllowClientNoContext = other.webSocketAllowClientNoContext;
        this.webSocketCompressionLevel = other.webSocketCompressionLevel;
        this.webSocketRequestServerNoContext = other.webSocketRequestServerNoContext;
    }

    public HttpClientOptions(JsonObject json) {
        super(json);
        this.verifyHost = true;
        init();
        HttpClientOptionsConverter.fromJson(json, this);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        HttpClientOptionsConverter.toJson(this, json);
        return json;
    }

    private void init() {
        this.verifyHost = true;
        this.maxPoolSize = 5;
        this.keepAlive = true;
        this.keepAliveTimeout = 60;
        this.pipelining = false;
        this.pipeliningLimit = 10;
        this.http2MultiplexingLimit = -1;
        this.http2MaxPoolSize = 1;
        this.http2ConnectionWindowSize = -1;
        this.http2KeepAliveTimeout = 60;
        this.tryUseCompression = false;
        this.maxWebSocketFrameSize = 65536;
        this.maxWebSocketMessageSize = 262144;
        this.defaultHost = "localhost";
        this.defaultPort = 80;
        this.protocolVersion = DEFAULT_PROTOCOL_VERSION;
        this.maxChunkSize = 8192;
        this.maxInitialLineLength = 4096;
        this.maxHeaderSize = 8192;
        this.maxWaitQueueSize = -1;
        this.initialSettings = new Http2Settings();
        this.alpnVersions = new ArrayList(DEFAULT_ALPN_VERSIONS);
        this.http2ClearTextUpgrade = true;
        this.sendUnmaskedFrames = false;
        this.maxRedirects = 16;
        this.forceSni = false;
        this.decoderInitialBufferSize = 128;
        this.tryUsePerFrameWebSocketCompression = false;
        this.tryUsePerMessageWebSocketCompression = false;
        this.webSocketCompressionLevel = 6;
        this.webSocketAllowClientNoContext = false;
        this.webSocketRequestServerNoContext = false;
        this.poolCleanerPeriod = 1000;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public HttpClientOptions setSendBufferSize(int sendBufferSize) {
        super.setSendBufferSize(sendBufferSize);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public HttpClientOptions setReceiveBufferSize(int receiveBufferSize) {
        super.setReceiveBufferSize(receiveBufferSize);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public HttpClientOptions setReuseAddress(boolean reuseAddress) {
        super.setReuseAddress(reuseAddress);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public HttpClientOptions setReusePort(boolean reusePort) {
        super.setReusePort(reusePort);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public HttpClientOptions setTrafficClass(int trafficClass) {
        super.setTrafficClass(trafficClass);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions setTcpNoDelay(boolean tcpNoDelay) {
        super.setTcpNoDelay(tcpNoDelay);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions setTcpKeepAlive(boolean tcpKeepAlive) {
        super.setTcpKeepAlive(tcpKeepAlive);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions setSoLinger(int soLinger) {
        super.setSoLinger(soLinger);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions setUsePooledBuffers(boolean usePooledBuffers) {
        super.setUsePooledBuffers(usePooledBuffers);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions setIdleTimeout(int idleTimeout) {
        super.setIdleTimeout(idleTimeout);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions setIdleTimeoutUnit(TimeUnit idleTimeoutUnit) {
        super.setIdleTimeoutUnit(idleTimeoutUnit);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions setSsl(boolean ssl) {
        super.setSsl(ssl);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions setKeyCertOptions(KeyCertOptions options) {
        super.setKeyCertOptions(options);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions setKeyStoreOptions(JksOptions options) {
        super.setKeyStoreOptions(options);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions setPfxKeyCertOptions(PfxOptions options) {
        return (HttpClientOptions) super.setPfxKeyCertOptions(options);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions setTrustOptions(TrustOptions options) {
        super.setTrustOptions(options);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions setPemKeyCertOptions(PemKeyCertOptions options) {
        return (HttpClientOptions) super.setPemKeyCertOptions(options);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions setTrustStoreOptions(JksOptions options) {
        super.setTrustStoreOptions(options);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions setPfxTrustOptions(PfxOptions options) {
        return (HttpClientOptions) super.setPfxTrustOptions(options);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions setPemTrustOptions(PemTrustOptions options) {
        return (HttpClientOptions) super.setPemTrustOptions(options);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions addEnabledCipherSuite(String suite) {
        super.addEnabledCipherSuite(suite);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions addEnabledSecureTransportProtocol(String protocol) {
        super.addEnabledSecureTransportProtocol(protocol);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions removeEnabledSecureTransportProtocol(String protocol) {
        return (HttpClientOptions) super.removeEnabledSecureTransportProtocol(protocol);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions setTcpFastOpen(boolean tcpFastOpen) {
        return (HttpClientOptions) super.setTcpFastOpen(tcpFastOpen);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions setTcpCork(boolean tcpCork) {
        return (HttpClientOptions) super.setTcpCork(tcpCork);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions setTcpQuickAck(boolean tcpQuickAck) {
        return (HttpClientOptions) super.setTcpQuickAck(tcpQuickAck);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions addCrlPath(String crlPath) throws NullPointerException {
        return (HttpClientOptions) super.addCrlPath(crlPath);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions addCrlValue(Buffer crlValue) throws NullPointerException {
        return (HttpClientOptions) super.addCrlValue(crlValue);
    }

    @Override // io.vertx.core.net.ClientOptionsBase
    public HttpClientOptions setConnectTimeout(int connectTimeout) {
        super.setConnectTimeout(connectTimeout);
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase
    public HttpClientOptions setTrustAll(boolean trustAll) {
        super.setTrustAll(trustAll);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions setEnabledSecureTransportProtocols(Set<String> enabledSecureTransportProtocols) {
        super.setEnabledSecureTransportProtocols(enabledSecureTransportProtocols);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions setSslHandshakeTimeout(long sslHandshakeTimeout) {
        super.setSslHandshakeTimeout(sslHandshakeTimeout);
        return this;
    }

    @Override // io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions setSslHandshakeTimeoutUnit(TimeUnit sslHandshakeTimeoutUnit) {
        super.setSslHandshakeTimeoutUnit(sslHandshakeTimeoutUnit);
        return this;
    }

    public int getMaxPoolSize() {
        return this.maxPoolSize;
    }

    public HttpClientOptions setMaxPoolSize(int maxPoolSize) {
        if (maxPoolSize < 1) {
            throw new IllegalArgumentException("maxPoolSize must be > 0");
        }
        this.maxPoolSize = maxPoolSize;
        return this;
    }

    public int getHttp2MultiplexingLimit() {
        return this.http2MultiplexingLimit;
    }

    public HttpClientOptions setHttp2MultiplexingLimit(int limit) {
        if (limit == 0 || limit < -1) {
            throw new IllegalArgumentException("maxPoolSize must be > 0 or -1 (disabled)");
        }
        this.http2MultiplexingLimit = limit;
        return this;
    }

    public int getHttp2MaxPoolSize() {
        return this.http2MaxPoolSize;
    }

    public HttpClientOptions setHttp2MaxPoolSize(int max) {
        if (max < 1) {
            throw new IllegalArgumentException("http2MaxPoolSize must be > 0");
        }
        this.http2MaxPoolSize = max;
        return this;
    }

    public int getHttp2ConnectionWindowSize() {
        return this.http2ConnectionWindowSize;
    }

    public HttpClientOptions setHttp2ConnectionWindowSize(int http2ConnectionWindowSize) {
        this.http2ConnectionWindowSize = http2ConnectionWindowSize;
        return this;
    }

    public int getHttp2KeepAliveTimeout() {
        return this.http2KeepAliveTimeout;
    }

    public HttpClientOptions setHttp2KeepAliveTimeout(int keepAliveTimeout) {
        if (keepAliveTimeout < 0) {
            throw new IllegalArgumentException("HTTP/2 keepAliveTimeout must be >= 0");
        }
        this.http2KeepAliveTimeout = keepAliveTimeout;
        return this;
    }

    public boolean isKeepAlive() {
        return this.keepAlive;
    }

    public HttpClientOptions setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
        return this;
    }

    public int getKeepAliveTimeout() {
        return this.keepAliveTimeout;
    }

    public HttpClientOptions setKeepAliveTimeout(int keepAliveTimeout) {
        if (keepAliveTimeout < 0) {
            throw new IllegalArgumentException("keepAliveTimeout must be >= 0");
        }
        this.keepAliveTimeout = keepAliveTimeout;
        return this;
    }

    public boolean isPipelining() {
        return this.pipelining;
    }

    public HttpClientOptions setPipelining(boolean pipelining) {
        this.pipelining = pipelining;
        return this;
    }

    public int getPipeliningLimit() {
        return this.pipeliningLimit;
    }

    public HttpClientOptions setPipeliningLimit(int limit) {
        if (limit < 1) {
            throw new IllegalArgumentException("pipeliningLimit must be > 0");
        }
        this.pipeliningLimit = limit;
        return this;
    }

    public boolean isVerifyHost() {
        return this.verifyHost;
    }

    public HttpClientOptions setVerifyHost(boolean verifyHost) {
        this.verifyHost = verifyHost;
        return this;
    }

    public boolean isTryUseCompression() {
        return this.tryUseCompression;
    }

    public HttpClientOptions setTryUseCompression(boolean tryUseCompression) {
        this.tryUseCompression = tryUseCompression;
        return this;
    }

    public boolean isSendUnmaskedFrames() {
        return this.sendUnmaskedFrames;
    }

    public HttpClientOptions setSendUnmaskedFrames(boolean sendUnmaskedFrames) {
        this.sendUnmaskedFrames = sendUnmaskedFrames;
        return this;
    }

    @Deprecated
    public int getMaxWebsocketFrameSize() {
        return this.maxWebSocketFrameSize;
    }

    public int getMaxWebSocketFrameSize() {
        return this.maxWebSocketFrameSize;
    }

    @Deprecated
    public HttpClientOptions setMaxWebsocketFrameSize(int maxWebSocketFrameSize) {
        this.maxWebSocketFrameSize = maxWebSocketFrameSize;
        return this;
    }

    public HttpClientOptions setMaxWebSocketFrameSize(int maxWebSocketFrameSize) {
        this.maxWebSocketFrameSize = maxWebSocketFrameSize;
        return this;
    }

    @Deprecated
    public int getMaxWebsocketMessageSize() {
        return this.maxWebSocketMessageSize;
    }

    public int getMaxWebSocketMessageSize() {
        return this.maxWebSocketMessageSize;
    }

    @Deprecated
    public HttpClientOptions setMaxWebsocketMessageSize(int maxWebSocketMessageSize) {
        this.maxWebSocketMessageSize = maxWebSocketMessageSize;
        return this;
    }

    public HttpClientOptions setMaxWebSocketMessageSize(int maxWebSocketMessageSize) {
        this.maxWebSocketMessageSize = maxWebSocketMessageSize;
        return this;
    }

    public String getDefaultHost() {
        return this.defaultHost;
    }

    public HttpClientOptions setDefaultHost(String defaultHost) {
        this.defaultHost = defaultHost;
        return this;
    }

    public int getDefaultPort() {
        return this.defaultPort;
    }

    public HttpClientOptions setDefaultPort(int defaultPort) {
        this.defaultPort = defaultPort;
        return this;
    }

    public HttpVersion getProtocolVersion() {
        return this.protocolVersion;
    }

    public HttpClientOptions setProtocolVersion(HttpVersion protocolVersion) {
        if (protocolVersion == null) {
            throw new IllegalArgumentException("protocolVersion must not be null");
        }
        this.protocolVersion = protocolVersion;
        return this;
    }

    public HttpClientOptions setMaxChunkSize(int maxChunkSize) {
        this.maxChunkSize = maxChunkSize;
        return this;
    }

    public int getMaxChunkSize() {
        return this.maxChunkSize;
    }

    public int getMaxInitialLineLength() {
        return this.maxInitialLineLength;
    }

    public HttpClientOptions setMaxInitialLineLength(int maxInitialLineLength) {
        this.maxInitialLineLength = maxInitialLineLength;
        return this;
    }

    public int getMaxHeaderSize() {
        return this.maxHeaderSize;
    }

    public HttpClientOptions setMaxHeaderSize(int maxHeaderSize) {
        this.maxHeaderSize = maxHeaderSize;
        return this;
    }

    public HttpClientOptions setMaxWaitQueueSize(int maxWaitQueueSize) {
        this.maxWaitQueueSize = maxWaitQueueSize;
        return this;
    }

    public int getMaxWaitQueueSize() {
        return this.maxWaitQueueSize;
    }

    public Http2Settings getInitialSettings() {
        return this.initialSettings;
    }

    public HttpClientOptions setInitialSettings(Http2Settings settings) {
        this.initialSettings = settings;
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions setUseAlpn(boolean useAlpn) {
        return (HttpClientOptions) super.setUseAlpn(useAlpn);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions setSslEngineOptions(SSLEngineOptions sslEngineOptions) {
        return (HttpClientOptions) super.setSslEngineOptions(sslEngineOptions);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions setJdkSslEngineOptions(JdkSSLEngineOptions sslEngineOptions) {
        return (HttpClientOptions) super.setSslEngineOptions((SSLEngineOptions) sslEngineOptions);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions
    public HttpClientOptions setOpenSslEngineOptions(OpenSSLEngineOptions sslEngineOptions) {
        return (HttpClientOptions) super.setSslEngineOptions((SSLEngineOptions) sslEngineOptions);
    }

    public List<HttpVersion> getAlpnVersions() {
        return this.alpnVersions;
    }

    public HttpClientOptions setAlpnVersions(List<HttpVersion> alpnVersions) {
        this.alpnVersions = alpnVersions;
        return this;
    }

    public boolean isHttp2ClearTextUpgrade() {
        return this.http2ClearTextUpgrade;
    }

    public HttpClientOptions setHttp2ClearTextUpgrade(boolean value) {
        this.http2ClearTextUpgrade = value;
        return this;
    }

    public int getMaxRedirects() {
        return this.maxRedirects;
    }

    public HttpClientOptions setMaxRedirects(int maxRedirects) {
        this.maxRedirects = maxRedirects;
        return this;
    }

    public boolean isForceSni() {
        return this.forceSni;
    }

    public HttpClientOptions setForceSni(boolean forceSni) {
        this.forceSni = forceSni;
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase
    public HttpClientOptions setMetricsName(String metricsName) {
        return (HttpClientOptions) super.setMetricsName(metricsName);
    }

    @Override // io.vertx.core.net.ClientOptionsBase
    public HttpClientOptions setProxyOptions(ProxyOptions proxyOptions) {
        return (HttpClientOptions) super.setProxyOptions(proxyOptions);
    }

    @Override // io.vertx.core.net.ClientOptionsBase
    public HttpClientOptions setLocalAddress(String localAddress) {
        return (HttpClientOptions) super.setLocalAddress(localAddress);
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public HttpClientOptions setLogActivity(boolean logEnabled) {
        return (HttpClientOptions) super.setLogActivity(logEnabled);
    }

    @Deprecated
    public HttpClientOptions setTryUsePerFrameWebsocketCompression(boolean offer) {
        this.tryUsePerFrameWebSocketCompression = offer;
        return this;
    }

    public HttpClientOptions setTryUsePerFrameWebSocketCompression(boolean offer) {
        this.tryUsePerFrameWebSocketCompression = offer;
        return this;
    }

    @Deprecated
    public boolean getTryWebsocketDeflateFrameCompression() {
        return this.tryUsePerFrameWebSocketCompression;
    }

    public boolean getTryWebSocketDeflateFrameCompression() {
        return this.tryUsePerFrameWebSocketCompression;
    }

    @Deprecated
    public HttpClientOptions setTryUsePerMessageWebsocketCompression(boolean offer) {
        this.tryUsePerMessageWebSocketCompression = offer;
        return this;
    }

    public HttpClientOptions setTryUsePerMessageWebSocketCompression(boolean offer) {
        this.tryUsePerMessageWebSocketCompression = offer;
        return this;
    }

    @Deprecated
    public boolean getTryUsePerMessageWebsocketCompression() {
        return this.tryUsePerMessageWebSocketCompression;
    }

    public boolean getTryUsePerMessageWebSocketCompression() {
        return this.tryUsePerMessageWebSocketCompression;
    }

    @Deprecated
    public HttpClientOptions setWebsocketCompressionLevel(int compressionLevel) {
        this.webSocketCompressionLevel = compressionLevel;
        return this;
    }

    public HttpClientOptions setWebSocketCompressionLevel(int compressionLevel) {
        this.webSocketCompressionLevel = compressionLevel;
        return this;
    }

    @Deprecated
    public int getWebsocketCompressionLevel() {
        return this.webSocketCompressionLevel;
    }

    public int getWebSocketCompressionLevel() {
        return this.webSocketCompressionLevel;
    }

    @Deprecated
    public HttpClientOptions setWebsocketCompressionAllowClientNoContext(boolean offer) {
        this.webSocketAllowClientNoContext = offer;
        return this;
    }

    public HttpClientOptions setWebSocketCompressionAllowClientNoContext(boolean offer) {
        this.webSocketAllowClientNoContext = offer;
        return this;
    }

    @Deprecated
    public boolean getWebsocketCompressionAllowClientNoContext() {
        return this.webSocketAllowClientNoContext;
    }

    public boolean getWebSocketCompressionAllowClientNoContext() {
        return this.webSocketAllowClientNoContext;
    }

    @Deprecated
    public HttpClientOptions setWebsocketCompressionRequestServerNoContext(boolean offer) {
        this.webSocketRequestServerNoContext = offer;
        return this;
    }

    public HttpClientOptions setWebSocketCompressionRequestServerNoContext(boolean offer) {
        this.webSocketRequestServerNoContext = offer;
        return this;
    }

    @Deprecated
    public boolean getWebsocketCompressionRequestServerNoContext() {
        return this.webSocketRequestServerNoContext;
    }

    public boolean getWebSocketCompressionRequestServerNoContext() {
        return this.webSocketRequestServerNoContext;
    }

    public int getDecoderInitialBufferSize() {
        return this.decoderInitialBufferSize;
    }

    public HttpClientOptions setDecoderInitialBufferSize(int decoderInitialBufferSize) {
        Arguments.require(decoderInitialBufferSize > 0, "initialBufferSizeHttpDecoder must be > 0");
        this.decoderInitialBufferSize = decoderInitialBufferSize;
        return this;
    }

    public int getPoolCleanerPeriod() {
        return this.poolCleanerPeriod;
    }

    public HttpClientOptions setPoolCleanerPeriod(int poolCleanerPeriod) {
        this.poolCleanerPeriod = poolCleanerPeriod;
        return this;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HttpClientOptions) || !super.equals(o)) {
            return false;
        }
        HttpClientOptions that = (HttpClientOptions) o;
        if (this.defaultPort != that.defaultPort || this.keepAlive != that.keepAlive || this.maxPoolSize != that.maxPoolSize || this.http2MultiplexingLimit != that.http2MultiplexingLimit || this.maxWebSocketFrameSize != that.maxWebSocketFrameSize || this.maxWebSocketMessageSize != that.maxWebSocketMessageSize || this.pipelining != that.pipelining || this.pipeliningLimit != that.pipeliningLimit || this.tryUseCompression != that.tryUseCompression || this.verifyHost != that.verifyHost || !this.defaultHost.equals(that.defaultHost) || this.protocolVersion != that.protocolVersion || this.maxChunkSize != that.maxChunkSize || this.maxWaitQueueSize != that.maxWaitQueueSize) {
            return false;
        }
        if (this.initialSettings == null) {
            if (that.initialSettings != null) {
                return false;
            }
        } else if (!this.initialSettings.equals(that.initialSettings)) {
            return false;
        }
        if (this.alpnVersions == null) {
            if (that.alpnVersions != null) {
                return false;
            }
        } else if (!this.alpnVersions.equals(that.alpnVersions)) {
            return false;
        }
        return this.http2ClearTextUpgrade == that.http2ClearTextUpgrade && this.http2ConnectionWindowSize == that.http2ConnectionWindowSize && this.sendUnmaskedFrames == that.sendUnmaskedFrames && this.maxRedirects == that.maxRedirects && this.decoderInitialBufferSize == that.decoderInitialBufferSize && this.keepAliveTimeout == that.keepAliveTimeout && this.http2KeepAliveTimeout == that.http2KeepAliveTimeout && this.poolCleanerPeriod == that.poolCleanerPeriod && this.tryUsePerFrameWebSocketCompression == that.tryUsePerFrameWebSocketCompression && this.tryUsePerMessageWebSocketCompression == that.tryUsePerMessageWebSocketCompression && this.webSocketCompressionLevel == that.webSocketCompressionLevel && this.webSocketAllowClientNoContext == that.webSocketAllowClientNoContext && this.webSocketRequestServerNoContext == that.webSocketRequestServerNoContext;
    }

    @Override // io.vertx.core.net.ClientOptionsBase, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public int hashCode() {
        int result = super.hashCode();
        return (31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * result) + (this.verifyHost ? 1 : 0))) + this.maxPoolSize)) + this.http2MultiplexingLimit)) + (this.keepAlive ? 1 : 0))) + (this.pipelining ? 1 : 0))) + this.pipeliningLimit)) + (this.tryUseCompression ? 1 : 0))) + this.maxWebSocketFrameSize)) + this.maxWebSocketMessageSize)) + this.defaultHost.hashCode())) + this.defaultPort)) + this.protocolVersion.hashCode())) + this.maxChunkSize)) + this.maxWaitQueueSize)) + (this.initialSettings != null ? this.initialSettings.hashCode() : 0))) + (this.alpnVersions != null ? this.alpnVersions.hashCode() : 0))) + (this.http2ClearTextUpgrade ? 1 : 0))) + this.http2ConnectionWindowSize)) + (this.sendUnmaskedFrames ? 1 : 0))) + this.maxRedirects)) + this.decoderInitialBufferSize)) + this.keepAliveTimeout)) + this.http2KeepAliveTimeout)) + this.poolCleanerPeriod)) + (this.tryUsePerFrameWebSocketCompression ? 1 : 0))) + (this.tryUsePerMessageWebSocketCompression ? 1 : 0))) + this.webSocketCompressionLevel)) + (this.webSocketAllowClientNoContext ? 1 : 0))) + (this.webSocketRequestServerNoContext ? 1 : 0);
    }
}
