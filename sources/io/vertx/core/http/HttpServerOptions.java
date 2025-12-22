package io.vertx.core.http;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.impl.Arguments;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.JdkSSLEngineOptions;
import io.vertx.core.net.JksOptions;
import io.vertx.core.net.KeyCertOptions;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.OpenSSLEngineOptions;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.core.net.PemTrustOptions;
import io.vertx.core.net.PfxOptions;
import io.vertx.core.net.SSLEngineOptions;
import io.vertx.core.net.TCPSSLOptions;
import io.vertx.core.net.TrustOptions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@DataObject(generateConverter = true, publicConverter = false)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/HttpServerOptions.class */
public class HttpServerOptions extends NetServerOptions {
    public static final int DEFAULT_PORT = 80;
    public static final boolean DEFAULT_COMPRESSION_SUPPORTED = false;
    public static final int DEFAULT_COMPRESSION_LEVEL = 6;
    public static final int DEFAULT_MAX_WEBSOCKET_FRAME_SIZE = 65536;
    public static final int DEFAULT_MAX_WEBSOCKET_MESSAGE_SIZE = 262144;
    public static final int DEFAULT_MAX_CHUNK_SIZE = 8192;
    public static final int DEFAULT_MAX_INITIAL_LINE_LENGTH = 4096;
    public static final int DEFAULT_MAX_HEADER_SIZE = 8192;
    public static final boolean DEFAULT_HANDLE_100_CONTINE_AUTOMATICALLY = false;
    public static final List<HttpVersion> DEFAULT_ALPN_VERSIONS = Collections.unmodifiableList(Arrays.asList(HttpVersion.HTTP_2, HttpVersion.HTTP_1_1));
    public static final long DEFAULT_INITIAL_SETTINGS_MAX_CONCURRENT_STREAMS = 100;
    public static final int DEFAULT_HTTP2_CONNECTION_WINDOW_SIZE = -1;
    public static final boolean DEFAULT_DECOMPRESSION_SUPPORTED = false;
    public static final boolean DEFAULT_ACCEPT_UNMASKED_FRAMES = false;
    public static final int DEFAULT_DECODER_INITIAL_BUFFER_SIZE = 128;
    public static final boolean DEFAULT_PER_FRAME_WEBSOCKET_COMPRESSION_SUPPORTED = true;
    public static final boolean DEFAULT_PER_MESSAGE_WEBSOCKET_COMPRESSION_SUPPORTED = true;
    public static final int DEFAULT_WEBSOCKET_COMPRESSION_LEVEL = 6;
    public static final boolean DEFAULT_WEBSOCKET_ALLOW_SERVER_NO_CONTEXT = false;
    public static final boolean DEFAULT_WEBSOCKET_PREFERRED_CLIENT_NO_CONTEXT = false;
    private boolean compressionSupported;
    private int compressionLevel;
    private int maxWebSocketFrameSize;
    private int maxWebSocketMessageSize;
    private List<String> webSocketSubProtocols;
    private boolean handle100ContinueAutomatically;
    private int maxChunkSize;
    private int maxInitialLineLength;
    private int maxHeaderSize;
    private Http2Settings initialSettings;
    private List<HttpVersion> alpnVersions;
    private int http2ConnectionWindowSize;
    private boolean decompressionSupported;
    private boolean acceptUnmaskedFrames;
    private int decoderInitialBufferSize;
    private boolean perFrameWebSocketCompressionSupported;
    private boolean perMessageWebSocketCompressionSupported;
    private int webSocketCompressionLevel;
    private boolean webSocketAllowServerNoContext;
    private boolean webSocketPreferredClientNoContext;

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public /* bridge */ /* synthetic */ NetServerOptions setEnabledSecureTransportProtocols(Set set) {
        return setEnabledSecureTransportProtocols((Set<String>) set);
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public /* bridge */ /* synthetic */ TCPSSLOptions setEnabledSecureTransportProtocols(Set set) {
        return setEnabledSecureTransportProtocols((Set<String>) set);
    }

    public HttpServerOptions() {
        init();
        setPort(80);
    }

    public HttpServerOptions(HttpServerOptions other) {
        super(other);
        this.compressionSupported = other.isCompressionSupported();
        this.compressionLevel = other.getCompressionLevel();
        this.maxWebSocketFrameSize = other.maxWebSocketFrameSize;
        this.maxWebSocketMessageSize = other.maxWebSocketMessageSize;
        this.webSocketSubProtocols = other.webSocketSubProtocols != null ? new ArrayList(other.webSocketSubProtocols) : null;
        this.handle100ContinueAutomatically = other.handle100ContinueAutomatically;
        this.maxChunkSize = other.getMaxChunkSize();
        this.maxInitialLineLength = other.getMaxInitialLineLength();
        this.maxHeaderSize = other.getMaxHeaderSize();
        this.initialSettings = other.initialSettings != null ? new Http2Settings(other.initialSettings) : null;
        this.alpnVersions = other.alpnVersions != null ? new ArrayList(other.alpnVersions) : null;
        this.http2ConnectionWindowSize = other.http2ConnectionWindowSize;
        this.decompressionSupported = other.isDecompressionSupported();
        this.acceptUnmaskedFrames = other.isAcceptUnmaskedFrames();
        this.decoderInitialBufferSize = other.getDecoderInitialBufferSize();
        this.perFrameWebSocketCompressionSupported = other.perFrameWebSocketCompressionSupported;
        this.perMessageWebSocketCompressionSupported = other.perMessageWebSocketCompressionSupported;
        this.webSocketCompressionLevel = other.webSocketCompressionLevel;
        this.webSocketPreferredClientNoContext = other.webSocketPreferredClientNoContext;
        this.webSocketAllowServerNoContext = other.webSocketAllowServerNoContext;
    }

    public HttpServerOptions(JsonObject json) {
        super(json);
        init();
        setPort(json.getInteger(RtspHeaders.Values.PORT, 80).intValue());
        HttpServerOptionsConverter.fromJson(json, this);
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        HttpServerOptionsConverter.toJson(this, json);
        return json;
    }

    private void init() {
        this.compressionSupported = false;
        this.compressionLevel = 6;
        this.maxWebSocketFrameSize = 65536;
        this.maxWebSocketMessageSize = 262144;
        this.handle100ContinueAutomatically = false;
        this.maxChunkSize = 8192;
        this.maxInitialLineLength = 4096;
        this.maxHeaderSize = 8192;
        this.initialSettings = new Http2Settings().setMaxConcurrentStreams(100L);
        this.alpnVersions = new ArrayList(DEFAULT_ALPN_VERSIONS);
        this.http2ConnectionWindowSize = -1;
        this.decompressionSupported = false;
        this.acceptUnmaskedFrames = false;
        this.decoderInitialBufferSize = 128;
        this.perFrameWebSocketCompressionSupported = true;
        this.perMessageWebSocketCompressionSupported = true;
        this.webSocketCompressionLevel = 6;
        this.webSocketPreferredClientNoContext = false;
        this.webSocketAllowServerNoContext = false;
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public HttpServerOptions setSendBufferSize(int sendBufferSize) {
        super.setSendBufferSize(sendBufferSize);
        return this;
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public HttpServerOptions setReceiveBufferSize(int receiveBufferSize) {
        super.setReceiveBufferSize(receiveBufferSize);
        return this;
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public HttpServerOptions setReuseAddress(boolean reuseAddress) {
        super.setReuseAddress(reuseAddress);
        return this;
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public HttpServerOptions setReusePort(boolean reusePort) {
        super.setReusePort(reusePort);
        return this;
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public HttpServerOptions setTrafficClass(int trafficClass) {
        super.setTrafficClass(trafficClass);
        return this;
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions setTcpNoDelay(boolean tcpNoDelay) {
        super.setTcpNoDelay(tcpNoDelay);
        return this;
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions setTcpKeepAlive(boolean tcpKeepAlive) {
        super.setTcpKeepAlive(tcpKeepAlive);
        return this;
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions setSoLinger(int soLinger) {
        super.setSoLinger(soLinger);
        return this;
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions setUsePooledBuffers(boolean usePooledBuffers) {
        super.setUsePooledBuffers(usePooledBuffers);
        return this;
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions setIdleTimeout(int idleTimeout) {
        super.setIdleTimeout(idleTimeout);
        return this;
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions setIdleTimeoutUnit(TimeUnit idleTimeoutUnit) {
        super.setIdleTimeoutUnit(idleTimeoutUnit);
        return this;
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions setSsl(boolean ssl) {
        super.setSsl(ssl);
        return this;
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions setUseAlpn(boolean useAlpn) {
        super.setUseAlpn(useAlpn);
        return this;
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions setKeyCertOptions(KeyCertOptions options) {
        super.setKeyCertOptions(options);
        return this;
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions setKeyStoreOptions(JksOptions options) {
        super.setKeyStoreOptions(options);
        return this;
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions setPfxKeyCertOptions(PfxOptions options) {
        return (HttpServerOptions) super.setPfxKeyCertOptions(options);
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions setPemKeyCertOptions(PemKeyCertOptions options) {
        return (HttpServerOptions) super.setPemKeyCertOptions(options);
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions setTrustOptions(TrustOptions options) {
        super.setTrustOptions(options);
        return this;
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions setTrustStoreOptions(JksOptions options) {
        super.setTrustStoreOptions(options);
        return this;
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions setPemTrustOptions(PemTrustOptions options) {
        return (HttpServerOptions) super.setPemTrustOptions(options);
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions setPfxTrustOptions(PfxOptions options) {
        return (HttpServerOptions) super.setPfxTrustOptions(options);
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions addEnabledCipherSuite(String suite) {
        super.addEnabledCipherSuite(suite);
        return this;
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions addEnabledSecureTransportProtocol(String protocol) {
        super.addEnabledSecureTransportProtocol(protocol);
        return this;
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions removeEnabledSecureTransportProtocol(String protocol) {
        return (HttpServerOptions) super.removeEnabledSecureTransportProtocol(protocol);
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions setTcpFastOpen(boolean tcpFastOpen) {
        return (HttpServerOptions) super.setTcpFastOpen(tcpFastOpen);
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions setTcpCork(boolean tcpCork) {
        return (HttpServerOptions) super.setTcpCork(tcpCork);
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions setTcpQuickAck(boolean tcpQuickAck) {
        return (HttpServerOptions) super.setTcpQuickAck(tcpQuickAck);
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions addCrlPath(String crlPath) throws NullPointerException {
        return (HttpServerOptions) super.addCrlPath(crlPath);
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions addCrlValue(Buffer crlValue) throws NullPointerException {
        return (HttpServerOptions) super.addCrlValue(crlValue);
    }

    @Override // io.vertx.core.net.NetServerOptions
    public HttpServerOptions setAcceptBacklog(int acceptBacklog) {
        super.setAcceptBacklog(acceptBacklog);
        return this;
    }

    @Override // io.vertx.core.net.NetServerOptions
    public HttpServerOptions setPort(int port) {
        super.setPort(port);
        return this;
    }

    @Override // io.vertx.core.net.NetServerOptions
    public HttpServerOptions setHost(String host) {
        super.setHost(host);
        return this;
    }

    @Override // io.vertx.core.net.NetServerOptions
    @Deprecated
    public HttpServerOptions setClientAuthRequired(boolean clientAuthRequired) {
        super.setClientAuthRequired(clientAuthRequired);
        return this;
    }

    @Override // io.vertx.core.net.NetServerOptions
    public HttpServerOptions setClientAuth(ClientAuth clientAuth) {
        super.setClientAuth(clientAuth);
        return this;
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions setSslEngineOptions(SSLEngineOptions sslEngineOptions) {
        super.setSslEngineOptions(sslEngineOptions);
        return this;
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions setJdkSslEngineOptions(JdkSSLEngineOptions sslEngineOptions) {
        return (HttpServerOptions) super.setSslEngineOptions((SSLEngineOptions) sslEngineOptions);
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions setOpenSslEngineOptions(OpenSSLEngineOptions sslEngineOptions) {
        return (HttpServerOptions) super.setSslEngineOptions((SSLEngineOptions) sslEngineOptions);
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions setEnabledSecureTransportProtocols(Set<String> enabledSecureTransportProtocols) {
        return (HttpServerOptions) super.setEnabledSecureTransportProtocols(enabledSecureTransportProtocols);
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions setSslHandshakeTimeout(long sslHandshakeTimeout) {
        return (HttpServerOptions) super.setSslHandshakeTimeout(sslHandshakeTimeout);
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions
    public HttpServerOptions setSslHandshakeTimeoutUnit(TimeUnit sslHandshakeTimeoutUnit) {
        return (HttpServerOptions) super.setSslHandshakeTimeoutUnit(sslHandshakeTimeoutUnit);
    }

    public boolean isCompressionSupported() {
        return this.compressionSupported;
    }

    public HttpServerOptions setCompressionSupported(boolean compressionSupported) {
        this.compressionSupported = compressionSupported;
        return this;
    }

    public int getCompressionLevel() {
        return this.compressionLevel;
    }

    public HttpServerOptions setCompressionLevel(int compressionLevel) {
        this.compressionLevel = compressionLevel;
        return this;
    }

    public boolean isAcceptUnmaskedFrames() {
        return this.acceptUnmaskedFrames;
    }

    public HttpServerOptions setAcceptUnmaskedFrames(boolean acceptUnmaskedFrames) {
        this.acceptUnmaskedFrames = acceptUnmaskedFrames;
        return this;
    }

    @Deprecated
    public int getMaxWebsocketFrameSize() {
        return this.maxWebSocketFrameSize;
    }

    @Deprecated
    public HttpServerOptions setMaxWebsocketFrameSize(int maxWebSocketFrameSize) {
        this.maxWebSocketFrameSize = maxWebSocketFrameSize;
        return this;
    }

    public int getMaxWebSocketFrameSize() {
        return this.maxWebSocketFrameSize;
    }

    public HttpServerOptions setMaxWebSocketFrameSize(int maxWebSocketFrameSize) {
        this.maxWebSocketFrameSize = maxWebSocketFrameSize;
        return this;
    }

    @Deprecated
    public int getMaxWebsocketMessageSize() {
        return this.maxWebSocketMessageSize;
    }

    @Deprecated
    public HttpServerOptions setMaxWebsocketMessageSize(int maxWebSocketMessageSize) {
        this.maxWebSocketMessageSize = maxWebSocketMessageSize;
        return this;
    }

    public int getMaxWebSocketMessageSize() {
        return this.maxWebSocketMessageSize;
    }

    public HttpServerOptions setMaxWebSocketMessageSize(int maxWebSocketMessageSize) {
        this.maxWebSocketMessageSize = maxWebSocketMessageSize;
        return this;
    }

    @Deprecated
    public HttpServerOptions setWebsocketSubProtocols(String subProtocols) {
        return setWebSocketSubProtocols(subProtocols == null ? null : new ArrayList(Arrays.asList(subProtocols.split("\\s*,\\s*"))));
    }

    @Deprecated
    public String getWebsocketSubProtocols() {
        if (getWebSocketSubProtocols() != null) {
            return String.join(",", getWebSocketSubProtocols());
        }
        return null;
    }

    public HttpServerOptions addWebSocketSubProtocol(String subProtocol) {
        Objects.requireNonNull(subProtocol, "Cannot add a null WebSocket sub-protocol");
        if (this.webSocketSubProtocols == null) {
            this.webSocketSubProtocols = new ArrayList();
        }
        this.webSocketSubProtocols.add(subProtocol);
        return this;
    }

    public HttpServerOptions setWebSocketSubProtocols(List<String> subProtocols) {
        this.webSocketSubProtocols = subProtocols;
        return this;
    }

    public List<String> getWebSocketSubProtocols() {
        return this.webSocketSubProtocols;
    }

    public boolean isHandle100ContinueAutomatically() {
        return this.handle100ContinueAutomatically;
    }

    public HttpServerOptions setHandle100ContinueAutomatically(boolean handle100ContinueAutomatically) {
        this.handle100ContinueAutomatically = handle100ContinueAutomatically;
        return this;
    }

    public HttpServerOptions setMaxChunkSize(int maxChunkSize) {
        this.maxChunkSize = maxChunkSize;
        return this;
    }

    public int getMaxChunkSize() {
        return this.maxChunkSize;
    }

    public int getMaxInitialLineLength() {
        return this.maxInitialLineLength;
    }

    public HttpServerOptions setMaxInitialLineLength(int maxInitialLineLength) {
        this.maxInitialLineLength = maxInitialLineLength;
        return this;
    }

    public int getMaxHeaderSize() {
        return this.maxHeaderSize;
    }

    public HttpServerOptions setMaxHeaderSize(int maxHeaderSize) {
        this.maxHeaderSize = maxHeaderSize;
        return this;
    }

    public Http2Settings getInitialSettings() {
        return this.initialSettings;
    }

    public HttpServerOptions setInitialSettings(Http2Settings settings) {
        this.initialSettings = settings;
        return this;
    }

    public List<HttpVersion> getAlpnVersions() {
        return this.alpnVersions;
    }

    public HttpServerOptions setAlpnVersions(List<HttpVersion> alpnVersions) {
        this.alpnVersions = alpnVersions;
        return this;
    }

    public int getHttp2ConnectionWindowSize() {
        return this.http2ConnectionWindowSize;
    }

    public HttpServerOptions setHttp2ConnectionWindowSize(int http2ConnectionWindowSize) {
        this.http2ConnectionWindowSize = http2ConnectionWindowSize;
        return this;
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public HttpServerOptions setLogActivity(boolean logEnabled) {
        return (HttpServerOptions) super.setLogActivity(logEnabled);
    }

    @Override // io.vertx.core.net.NetServerOptions
    public HttpServerOptions setSni(boolean sni) {
        return (HttpServerOptions) super.setSni(sni);
    }

    public boolean isDecompressionSupported() {
        return this.decompressionSupported;
    }

    public HttpServerOptions setDecompressionSupported(boolean decompressionSupported) {
        this.decompressionSupported = decompressionSupported;
        return this;
    }

    public int getDecoderInitialBufferSize() {
        return this.decoderInitialBufferSize;
    }

    public HttpServerOptions setDecoderInitialBufferSize(int decoderInitialBufferSize) {
        Arguments.require(decoderInitialBufferSize > 0, "initialBufferSizeHttpDecoder must be > 0");
        this.decoderInitialBufferSize = decoderInitialBufferSize;
        return this;
    }

    @Deprecated
    public HttpServerOptions setPerFrameWebsocketCompressionSupported(boolean supported) {
        this.perFrameWebSocketCompressionSupported = supported;
        return this;
    }

    public HttpServerOptions setPerFrameWebSocketCompressionSupported(boolean supported) {
        this.perFrameWebSocketCompressionSupported = supported;
        return this;
    }

    @Deprecated
    public boolean getPerFrameWebsocketCompressionSupported() {
        return this.perFrameWebSocketCompressionSupported;
    }

    public boolean getPerFrameWebSocketCompressionSupported() {
        return this.perFrameWebSocketCompressionSupported;
    }

    @Deprecated
    public HttpServerOptions setPerMessageWebsocketCompressionSupported(boolean supported) {
        this.perMessageWebSocketCompressionSupported = supported;
        return this;
    }

    public HttpServerOptions setPerMessageWebSocketCompressionSupported(boolean supported) {
        this.perMessageWebSocketCompressionSupported = supported;
        return this;
    }

    @Deprecated
    public boolean getPerMessageWebsocketCompressionSupported() {
        return this.perMessageWebSocketCompressionSupported;
    }

    public boolean getPerMessageWebSocketCompressionSupported() {
        return this.perMessageWebSocketCompressionSupported;
    }

    @Deprecated
    public HttpServerOptions setWebsocketCompressionLevel(int compressionLevel) {
        this.webSocketCompressionLevel = compressionLevel;
        return this;
    }

    public HttpServerOptions setWebSocketCompressionLevel(int compressionLevel) {
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
    public HttpServerOptions setWebsocketAllowServerNoContext(boolean accept) {
        this.webSocketAllowServerNoContext = accept;
        return this;
    }

    public HttpServerOptions setWebSocketAllowServerNoContext(boolean accept) {
        this.webSocketAllowServerNoContext = accept;
        return this;
    }

    @Deprecated
    public boolean getWebsocketAllowServerNoContext() {
        return this.webSocketAllowServerNoContext;
    }

    public boolean getWebSocketAllowServerNoContext() {
        return this.webSocketAllowServerNoContext;
    }

    @Deprecated
    public HttpServerOptions setWebsocketPreferredClientNoContext(boolean accept) {
        this.webSocketPreferredClientNoContext = accept;
        return this;
    }

    public HttpServerOptions setWebSocketPreferredClientNoContext(boolean accept) {
        this.webSocketPreferredClientNoContext = accept;
        return this;
    }

    @Deprecated
    public boolean getWebsocketPreferredClientNoContext() {
        return this.webSocketPreferredClientNoContext;
    }

    public boolean getWebSocketPreferredClientNoContext() {
        return this.webSocketPreferredClientNoContext;
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass() || !super.equals(o)) {
            return false;
        }
        HttpServerOptions that = (HttpServerOptions) o;
        if (this.compressionSupported != that.compressionSupported || this.maxWebSocketFrameSize != that.maxWebSocketFrameSize || this.maxWebSocketMessageSize != that.maxWebSocketMessageSize || this.handle100ContinueAutomatically != that.handle100ContinueAutomatically || this.maxChunkSize != that.maxChunkSize || this.maxInitialLineLength != that.maxInitialLineLength || this.maxHeaderSize != that.maxHeaderSize) {
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
        if (this.http2ConnectionWindowSize == that.http2ConnectionWindowSize && this.decompressionSupported == that.decompressionSupported && this.acceptUnmaskedFrames == that.acceptUnmaskedFrames && this.decoderInitialBufferSize == that.decoderInitialBufferSize && this.perFrameWebSocketCompressionSupported == that.perFrameWebSocketCompressionSupported && this.perMessageWebSocketCompressionSupported == that.perMessageWebSocketCompressionSupported && this.webSocketCompressionLevel == that.webSocketCompressionLevel && this.webSocketAllowServerNoContext == that.webSocketAllowServerNoContext && this.webSocketPreferredClientNoContext == that.webSocketPreferredClientNoContext) {
            return this.webSocketSubProtocols == null ? that.webSocketSubProtocols == null : this.webSocketSubProtocols.equals(that.webSocketSubProtocols);
        }
        return false;
    }

    @Override // io.vertx.core.net.NetServerOptions, io.vertx.core.net.TCPSSLOptions, io.vertx.core.net.NetworkOptions
    public int hashCode() {
        int result = super.hashCode();
        return (31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * result) + (this.compressionSupported ? 1 : 0))) + this.maxWebSocketFrameSize)) + this.maxWebSocketMessageSize)) + (this.webSocketSubProtocols != null ? this.webSocketSubProtocols.hashCode() : 0))) + (this.initialSettings != null ? this.initialSettings.hashCode() : 0))) + (this.handle100ContinueAutomatically ? 1 : 0))) + this.maxChunkSize)) + this.maxInitialLineLength)) + this.maxHeaderSize)) + (this.alpnVersions != null ? this.alpnVersions.hashCode() : 0))) + this.http2ConnectionWindowSize)) + (this.decompressionSupported ? 1 : 0))) + (this.acceptUnmaskedFrames ? 1 : 0))) + this.decoderInitialBufferSize)) + (this.perFrameWebSocketCompressionSupported ? 1 : 0))) + (this.perMessageWebSocketCompressionSupported ? 1 : 0))) + this.webSocketCompressionLevel)) + (this.webSocketAllowServerNoContext ? 1 : 0))) + (this.webSocketPreferredClientNoContext ? 1 : 0);
    }
}
