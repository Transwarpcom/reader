package io.vertx.core.net;

import cn.hutool.core.net.SSLProtocols;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@DataObject(generateConverter = true, publicConverter = false)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/TCPSSLOptions.class */
public abstract class TCPSSLOptions extends NetworkOptions {
    public static final boolean DEFAULT_TCP_NO_DELAY = true;
    public static final boolean DEFAULT_TCP_KEEP_ALIVE = false;
    public static final int DEFAULT_SO_LINGER = -1;
    public static final boolean DEFAULT_USE_POOLED_BUFFERS = false;
    public static final boolean DEFAULT_SSL = false;
    public static final int DEFAULT_IDLE_TIMEOUT = 0;
    public static final boolean DEFAULT_USE_ALPN = false;
    public static final boolean DEFAULT_TCP_FAST_OPEN = false;
    public static final boolean DEFAULT_TCP_CORK = false;
    public static final boolean DEFAULT_TCP_QUICKACK = false;
    public static final long DEFAULT_SSL_HANDSHAKE_TIMEOUT = 10;
    private boolean tcpNoDelay;
    private boolean tcpKeepAlive;
    private int soLinger;
    private boolean usePooledBuffers;
    private int idleTimeout;
    private TimeUnit idleTimeoutUnit;
    private boolean ssl;
    private long sslHandshakeTimeout;
    private TimeUnit sslHandshakeTimeoutUnit;
    private KeyCertOptions keyCertOptions;
    private TrustOptions trustOptions;
    private Set<String> enabledCipherSuites;
    private ArrayList<String> crlPaths;
    private ArrayList<Buffer> crlValues;
    private boolean useAlpn;
    private SSLEngineOptions sslEngineOptions;
    private Set<String> enabledSecureTransportProtocols;
    private boolean tcpFastOpen;
    private boolean tcpCork;
    private boolean tcpQuickAck;
    public static final TimeUnit DEFAULT_IDLE_TIMEOUT_TIME_UNIT = TimeUnit.SECONDS;
    public static final SSLEngineOptions DEFAULT_SSL_ENGINE = null;
    public static final List<String> DEFAULT_ENABLED_SECURE_TRANSPORT_PROTOCOLS = Collections.unmodifiableList(Arrays.asList(SSLProtocols.TLSv1, SSLProtocols.TLSv11, SSLProtocols.TLSv12));
    public static final TimeUnit DEFAULT_SSL_HANDSHAKE_TIMEOUT_TIME_UNIT = TimeUnit.SECONDS;

    public TCPSSLOptions() {
        init();
    }

    public TCPSSLOptions(TCPSSLOptions other) {
        super(other);
        this.tcpNoDelay = other.isTcpNoDelay();
        this.tcpKeepAlive = other.isTcpKeepAlive();
        this.soLinger = other.getSoLinger();
        this.usePooledBuffers = other.isUsePooledBuffers();
        this.idleTimeout = other.getIdleTimeout();
        this.idleTimeoutUnit = other.getIdleTimeoutUnit() != null ? other.getIdleTimeoutUnit() : DEFAULT_IDLE_TIMEOUT_TIME_UNIT;
        this.ssl = other.isSsl();
        this.sslHandshakeTimeout = other.sslHandshakeTimeout;
        this.sslHandshakeTimeoutUnit = other.getSslHandshakeTimeoutUnit() != null ? other.getSslHandshakeTimeoutUnit() : DEFAULT_SSL_HANDSHAKE_TIMEOUT_TIME_UNIT;
        this.keyCertOptions = other.getKeyCertOptions() != null ? other.getKeyCertOptions().copy() : null;
        this.trustOptions = other.getTrustOptions() != null ? other.getTrustOptions().copy() : null;
        this.enabledCipherSuites = other.getEnabledCipherSuites() == null ? new LinkedHashSet() : new LinkedHashSet(other.getEnabledCipherSuites());
        this.crlPaths = new ArrayList<>(other.getCrlPaths());
        this.crlValues = new ArrayList<>(other.getCrlValues());
        this.useAlpn = other.useAlpn;
        this.sslEngineOptions = other.sslEngineOptions != null ? other.sslEngineOptions.copy() : null;
        this.enabledSecureTransportProtocols = other.getEnabledSecureTransportProtocols() == null ? new LinkedHashSet() : new LinkedHashSet(other.getEnabledSecureTransportProtocols());
        this.tcpFastOpen = other.isTcpFastOpen();
        this.tcpCork = other.isTcpCork();
        this.tcpQuickAck = other.isTcpQuickAck();
    }

    public TCPSSLOptions(JsonObject json) {
        super(json);
        init();
        TCPSSLOptionsConverter.fromJson(json, this);
    }

    @Override // io.vertx.core.net.NetworkOptions
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        TCPSSLOptionsConverter.toJson(this, json);
        return json;
    }

    private void init() {
        this.tcpNoDelay = true;
        this.tcpKeepAlive = false;
        this.soLinger = -1;
        this.usePooledBuffers = false;
        this.idleTimeout = 0;
        this.idleTimeoutUnit = DEFAULT_IDLE_TIMEOUT_TIME_UNIT;
        this.ssl = false;
        this.sslHandshakeTimeout = 10L;
        this.sslHandshakeTimeoutUnit = DEFAULT_SSL_HANDSHAKE_TIMEOUT_TIME_UNIT;
        this.enabledCipherSuites = new LinkedHashSet();
        this.crlPaths = new ArrayList<>();
        this.crlValues = new ArrayList<>();
        this.useAlpn = false;
        this.sslEngineOptions = DEFAULT_SSL_ENGINE;
        this.enabledSecureTransportProtocols = new LinkedHashSet(DEFAULT_ENABLED_SECURE_TRANSPORT_PROTOCOLS);
        this.tcpFastOpen = false;
        this.tcpCork = false;
        this.tcpQuickAck = false;
    }

    public boolean isTcpNoDelay() {
        return this.tcpNoDelay;
    }

    public TCPSSLOptions setTcpNoDelay(boolean tcpNoDelay) {
        this.tcpNoDelay = tcpNoDelay;
        return this;
    }

    public boolean isTcpKeepAlive() {
        return this.tcpKeepAlive;
    }

    public TCPSSLOptions setTcpKeepAlive(boolean tcpKeepAlive) {
        this.tcpKeepAlive = tcpKeepAlive;
        return this;
    }

    public int getSoLinger() {
        return this.soLinger;
    }

    public TCPSSLOptions setSoLinger(int soLinger) {
        if (soLinger < 0 && soLinger != -1) {
            throw new IllegalArgumentException("soLinger must be >= 0");
        }
        this.soLinger = soLinger;
        return this;
    }

    @Deprecated
    public boolean isUsePooledBuffers() {
        return this.usePooledBuffers;
    }

    @Deprecated
    public TCPSSLOptions setUsePooledBuffers(boolean usePooledBuffers) {
        this.usePooledBuffers = usePooledBuffers;
        return this;
    }

    public TCPSSLOptions setIdleTimeout(int idleTimeout) {
        if (idleTimeout < 0) {
            throw new IllegalArgumentException("idleTimeout must be >= 0");
        }
        this.idleTimeout = idleTimeout;
        return this;
    }

    public int getIdleTimeout() {
        return this.idleTimeout;
    }

    public TCPSSLOptions setIdleTimeoutUnit(TimeUnit idleTimeoutUnit) {
        this.idleTimeoutUnit = idleTimeoutUnit;
        return this;
    }

    public TimeUnit getIdleTimeoutUnit() {
        return this.idleTimeoutUnit;
    }

    public boolean isSsl() {
        return this.ssl;
    }

    public TCPSSLOptions setSsl(boolean ssl) {
        this.ssl = ssl;
        return this;
    }

    @GenIgnore
    public KeyCertOptions getKeyCertOptions() {
        return this.keyCertOptions;
    }

    @GenIgnore
    public TCPSSLOptions setKeyCertOptions(KeyCertOptions options) {
        this.keyCertOptions = options;
        return this;
    }

    public JksOptions getKeyStoreOptions() {
        if (this.keyCertOptions instanceof JksOptions) {
            return (JksOptions) this.keyCertOptions;
        }
        return null;
    }

    public TCPSSLOptions setKeyStoreOptions(JksOptions options) {
        this.keyCertOptions = options;
        return this;
    }

    public PfxOptions getPfxKeyCertOptions() {
        if (this.keyCertOptions instanceof PfxOptions) {
            return (PfxOptions) this.keyCertOptions;
        }
        return null;
    }

    public TCPSSLOptions setPfxKeyCertOptions(PfxOptions options) {
        this.keyCertOptions = options;
        return this;
    }

    public PemKeyCertOptions getPemKeyCertOptions() {
        if (this.keyCertOptions instanceof PemKeyCertOptions) {
            return (PemKeyCertOptions) this.keyCertOptions;
        }
        return null;
    }

    public TCPSSLOptions setPemKeyCertOptions(PemKeyCertOptions options) {
        this.keyCertOptions = options;
        return this;
    }

    public TrustOptions getTrustOptions() {
        return this.trustOptions;
    }

    public TCPSSLOptions setTrustOptions(TrustOptions options) {
        this.trustOptions = options;
        return this;
    }

    public JksOptions getTrustStoreOptions() {
        if (this.trustOptions instanceof JksOptions) {
            return (JksOptions) this.trustOptions;
        }
        return null;
    }

    public TCPSSLOptions setTrustStoreOptions(JksOptions options) {
        this.trustOptions = options;
        return this;
    }

    public PfxOptions getPfxTrustOptions() {
        if (this.trustOptions instanceof PfxOptions) {
            return (PfxOptions) this.trustOptions;
        }
        return null;
    }

    public TCPSSLOptions setPfxTrustOptions(PfxOptions options) {
        this.trustOptions = options;
        return this;
    }

    public PemTrustOptions getPemTrustOptions() {
        if (this.trustOptions instanceof PemTrustOptions) {
            return (PemTrustOptions) this.trustOptions;
        }
        return null;
    }

    public TCPSSLOptions setPemTrustOptions(PemTrustOptions options) {
        this.trustOptions = options;
        return this;
    }

    public TCPSSLOptions addEnabledCipherSuite(String suite) {
        this.enabledCipherSuites.add(suite);
        return this;
    }

    public Set<String> getEnabledCipherSuites() {
        return this.enabledCipherSuites;
    }

    public List<String> getCrlPaths() {
        return this.crlPaths;
    }

    public TCPSSLOptions addCrlPath(String crlPath) throws NullPointerException {
        Objects.requireNonNull(crlPath, "No null crl accepted");
        this.crlPaths.add(crlPath);
        return this;
    }

    public List<Buffer> getCrlValues() {
        return this.crlValues;
    }

    public TCPSSLOptions addCrlValue(Buffer crlValue) throws NullPointerException {
        Objects.requireNonNull(crlValue, "No null crl accepted");
        this.crlValues.add(crlValue);
        return this;
    }

    public boolean isUseAlpn() {
        return this.useAlpn;
    }

    public TCPSSLOptions setUseAlpn(boolean useAlpn) {
        this.useAlpn = useAlpn;
        return this;
    }

    public SSLEngineOptions getSslEngineOptions() {
        return this.sslEngineOptions;
    }

    public TCPSSLOptions setSslEngineOptions(SSLEngineOptions sslEngineOptions) {
        this.sslEngineOptions = sslEngineOptions;
        return this;
    }

    public JdkSSLEngineOptions getJdkSslEngineOptions() {
        if (this.sslEngineOptions instanceof JdkSSLEngineOptions) {
            return (JdkSSLEngineOptions) this.sslEngineOptions;
        }
        return null;
    }

    public TCPSSLOptions setJdkSslEngineOptions(JdkSSLEngineOptions sslEngineOptions) {
        return setSslEngineOptions(sslEngineOptions);
    }

    public OpenSSLEngineOptions getOpenSslEngineOptions() {
        if (this.sslEngineOptions instanceof OpenSSLEngineOptions) {
            return (OpenSSLEngineOptions) this.sslEngineOptions;
        }
        return null;
    }

    public TCPSSLOptions setOpenSslEngineOptions(OpenSSLEngineOptions sslEngineOptions) {
        return setSslEngineOptions(sslEngineOptions);
    }

    public TCPSSLOptions setEnabledSecureTransportProtocols(Set<String> enabledSecureTransportProtocols) {
        this.enabledSecureTransportProtocols = enabledSecureTransportProtocols;
        return this;
    }

    public TCPSSLOptions addEnabledSecureTransportProtocol(String protocol) {
        this.enabledSecureTransportProtocols.add(protocol);
        return this;
    }

    public TCPSSLOptions removeEnabledSecureTransportProtocol(String protocol) {
        this.enabledSecureTransportProtocols.remove(protocol);
        return this;
    }

    public boolean isTcpFastOpen() {
        return this.tcpFastOpen;
    }

    public TCPSSLOptions setTcpFastOpen(boolean tcpFastOpen) {
        this.tcpFastOpen = tcpFastOpen;
        return this;
    }

    public boolean isTcpCork() {
        return this.tcpCork;
    }

    public TCPSSLOptions setTcpCork(boolean tcpCork) {
        this.tcpCork = tcpCork;
        return this;
    }

    public boolean isTcpQuickAck() {
        return this.tcpQuickAck;
    }

    public TCPSSLOptions setTcpQuickAck(boolean tcpQuickAck) {
        this.tcpQuickAck = tcpQuickAck;
        return this;
    }

    public Set<String> getEnabledSecureTransportProtocols() {
        return new LinkedHashSet(this.enabledSecureTransportProtocols);
    }

    public long getSslHandshakeTimeout() {
        return this.sslHandshakeTimeout;
    }

    public TCPSSLOptions setSslHandshakeTimeout(long sslHandshakeTimeout) {
        if (sslHandshakeTimeout < 0) {
            throw new IllegalArgumentException("sslHandshakeTimeout must be >= 0");
        }
        this.sslHandshakeTimeout = sslHandshakeTimeout;
        return this;
    }

    public TCPSSLOptions setSslHandshakeTimeoutUnit(TimeUnit sslHandshakeTimeoutUnit) {
        this.sslHandshakeTimeoutUnit = sslHandshakeTimeoutUnit;
        return this;
    }

    public TimeUnit getSslHandshakeTimeoutUnit() {
        return this.sslHandshakeTimeoutUnit;
    }

    @Override // io.vertx.core.net.NetworkOptions
    public TCPSSLOptions setLogActivity(boolean logEnabled) {
        return (TCPSSLOptions) super.setLogActivity(logEnabled);
    }

    @Override // io.vertx.core.net.NetworkOptions
    public TCPSSLOptions setSendBufferSize(int sendBufferSize) {
        return (TCPSSLOptions) super.setSendBufferSize(sendBufferSize);
    }

    @Override // io.vertx.core.net.NetworkOptions
    public TCPSSLOptions setReceiveBufferSize(int receiveBufferSize) {
        return (TCPSSLOptions) super.setReceiveBufferSize(receiveBufferSize);
    }

    @Override // io.vertx.core.net.NetworkOptions
    public TCPSSLOptions setReuseAddress(boolean reuseAddress) {
        return (TCPSSLOptions) super.setReuseAddress(reuseAddress);
    }

    @Override // io.vertx.core.net.NetworkOptions
    public TCPSSLOptions setTrafficClass(int trafficClass) {
        return (TCPSSLOptions) super.setTrafficClass(trafficClass);
    }

    @Override // io.vertx.core.net.NetworkOptions
    public TCPSSLOptions setReusePort(boolean reusePort) {
        return (TCPSSLOptions) super.setReusePort(reusePort);
    }

    @Override // io.vertx.core.net.NetworkOptions
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCPSSLOptions) || !super.equals(o)) {
            return false;
        }
        TCPSSLOptions that = (TCPSSLOptions) o;
        if (this.idleTimeout != that.idleTimeout) {
            return false;
        }
        if (this.idleTimeoutUnit != null) {
            if (!this.idleTimeoutUnit.equals(that.idleTimeoutUnit)) {
                return false;
            }
        } else if (that.idleTimeoutUnit != null) {
            return false;
        }
        if (this.soLinger != that.soLinger || this.ssl != that.ssl || this.tcpKeepAlive != that.tcpKeepAlive || this.tcpNoDelay != that.tcpNoDelay || this.tcpFastOpen != that.tcpFastOpen || this.tcpQuickAck != that.tcpQuickAck || this.tcpCork != that.tcpCork || this.usePooledBuffers != that.usePooledBuffers) {
            return false;
        }
        if (this.crlPaths != null) {
            if (!this.crlPaths.equals(that.crlPaths)) {
                return false;
            }
        } else if (that.crlPaths != null) {
            return false;
        }
        if (this.crlValues != null) {
            if (!this.crlValues.equals(that.crlValues)) {
                return false;
            }
        } else if (that.crlValues != null) {
            return false;
        }
        if (this.enabledCipherSuites != null) {
            if (!this.enabledCipherSuites.equals(that.enabledCipherSuites)) {
                return false;
            }
        } else if (that.enabledCipherSuites != null) {
            return false;
        }
        if (this.keyCertOptions != null) {
            if (!this.keyCertOptions.equals(that.keyCertOptions)) {
                return false;
            }
        } else if (that.keyCertOptions != null) {
            return false;
        }
        if (this.trustOptions != null) {
            if (!this.trustOptions.equals(that.trustOptions)) {
                return false;
            }
        } else if (that.trustOptions != null) {
            return false;
        }
        if (this.useAlpn != that.useAlpn) {
            return false;
        }
        if (this.sslEngineOptions != null) {
            if (!this.sslEngineOptions.equals(that.sslEngineOptions)) {
                return false;
            }
        } else if (that.sslEngineOptions != null) {
            return false;
        }
        return this.enabledSecureTransportProtocols.equals(that.enabledSecureTransportProtocols);
    }

    @Override // io.vertx.core.net.NetworkOptions
    public int hashCode() {
        int result = super.hashCode();
        return (31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * result) + (this.tcpNoDelay ? 1 : 0))) + (this.tcpFastOpen ? 1 : 0))) + (this.tcpCork ? 1 : 0))) + (this.tcpQuickAck ? 1 : 0))) + (this.tcpKeepAlive ? 1 : 0))) + this.soLinger)) + (this.usePooledBuffers ? 1 : 0))) + this.idleTimeout)) + (this.idleTimeoutUnit != null ? this.idleTimeoutUnit.hashCode() : 0))) + (this.ssl ? 1 : 0))) + (this.keyCertOptions != null ? this.keyCertOptions.hashCode() : 0))) + (this.trustOptions != null ? this.trustOptions.hashCode() : 0))) + (this.enabledCipherSuites != null ? this.enabledCipherSuites.hashCode() : 0))) + (this.crlPaths != null ? this.crlPaths.hashCode() : 0))) + (this.crlValues != null ? this.crlValues.hashCode() : 0))) + (this.useAlpn ? 1 : 0))) + (this.sslEngineOptions != null ? this.sslEngineOptions.hashCode() : 0))) + (this.enabledSecureTransportProtocols != null ? this.enabledSecureTransportProtocols.hashCode() : 0);
    }
}
