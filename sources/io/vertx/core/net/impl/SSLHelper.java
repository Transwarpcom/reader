package io.vertx.core.net.impl;

import cn.hutool.crypto.KeyUtil;
import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.ssl.ApplicationProtocolConfig;
import io.netty.handler.ssl.ApplicationProtocolNames;
import io.netty.handler.ssl.DelegatingSslContext;
import io.netty.handler.ssl.OpenSsl;
import io.netty.handler.ssl.OpenSslServerContext;
import io.netty.handler.ssl.OpenSslServerSessionContext;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslProvider;
import io.netty.util.Mapping;
import io.vertx.core.VertxException;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;
import io.vertx.core.http.ClientAuth;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.JdkSSLEngineOptions;
import io.vertx.core.net.KeyCertOptions;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.OpenSSLEngineOptions;
import io.vertx.core.net.SSLEngineOptions;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.net.TCPSSLOptions;
import io.vertx.core.net.TrustOptions;
import java.io.ByteArrayInputStream;
import java.security.cert.CRL;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SNIHostName;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/SSLHelper.class */
public class SSLHelper {
    private static final Map<HttpVersion, String> PROTOCOL_NAME_MAPPING = new EnumMap(HttpVersion.class);
    private static final Logger log;
    private boolean ssl;
    private boolean sni;
    private long sslHandshakeTimeout;
    private TimeUnit sslHandshakeTimeoutUnit;
    private KeyCertOptions keyCertOptions;
    private TrustOptions trustOptions;
    private boolean trustAll;
    private ArrayList<String> crlPaths;
    private ArrayList<Buffer> crlValues;
    private ClientAuth clientAuth;
    private Set<String> enabledCipherSuites;
    private boolean openSsl;
    private boolean client;
    private boolean useAlpn;
    private List<HttpVersion> applicationProtocols;
    private Set<String> enabledProtocols;
    private String endpointIdentificationAlgorithm;
    private SslContext sslContext;
    private Map<Certificate, SslContext> sslContextMap;
    private boolean openSslSessionCacheEnabled;

    public static SSLEngineOptions resolveEngineOptions(TCPSSLOptions options) {
        SSLEngineOptions engineOptions = options.getSslEngineOptions();
        if (engineOptions == null && options.isUseAlpn()) {
            if (JdkSSLEngineOptions.isAlpnAvailable()) {
                engineOptions = new JdkSSLEngineOptions();
            } else if (OpenSSLEngineOptions.isAlpnAvailable()) {
                engineOptions = new OpenSSLEngineOptions();
            }
        }
        if (engineOptions == null) {
            engineOptions = new JdkSSLEngineOptions();
        } else if ((engineOptions instanceof OpenSSLEngineOptions) && !OpenSsl.isAvailable()) {
            VertxException ex = new VertxException("OpenSSL is not available");
            Throwable cause = OpenSsl.unavailabilityCause();
            if (cause != null) {
                ex.initCause(cause);
            }
            throw ex;
        }
        if (options.isUseAlpn()) {
            if ((engineOptions instanceof JdkSSLEngineOptions) && !JdkSSLEngineOptions.isAlpnAvailable()) {
                throw new VertxException("ALPN not available for JDK SSL/TLS engine");
            }
            if ((engineOptions instanceof OpenSSLEngineOptions) && !OpenSSLEngineOptions.isAlpnAvailable()) {
                throw new VertxException("ALPN is not available for OpenSSL SSL/TLS engine");
            }
        }
        return engineOptions;
    }

    static {
        PROTOCOL_NAME_MAPPING.put(HttpVersion.HTTP_2, "h2");
        PROTOCOL_NAME_MAPPING.put(HttpVersion.HTTP_1_1, ApplicationProtocolNames.HTTP_1_1);
        PROTOCOL_NAME_MAPPING.put(HttpVersion.HTTP_1_0, "http/1.0");
        log = LoggerFactory.getLogger((Class<?>) SSLHelper.class);
    }

    public SSLHelper(HttpClientOptions options, KeyCertOptions keyCertOptions, TrustOptions trustOptions) {
        this.clientAuth = ClientAuth.NONE;
        this.endpointIdentificationAlgorithm = "";
        this.sslContextMap = new ConcurrentHashMap();
        this.openSslSessionCacheEnabled = true;
        SSLEngineOptions sslEngineOptions = resolveEngineOptions(options);
        this.ssl = options.isSsl();
        this.sslHandshakeTimeout = options.getSslHandshakeTimeout();
        this.sslHandshakeTimeoutUnit = options.getSslHandshakeTimeoutUnit();
        this.keyCertOptions = keyCertOptions;
        this.trustOptions = trustOptions;
        this.trustAll = options.isTrustAll();
        this.crlPaths = new ArrayList<>(options.getCrlPaths());
        this.crlValues = new ArrayList<>(options.getCrlValues());
        this.enabledCipherSuites = options.getEnabledCipherSuites();
        this.openSsl = sslEngineOptions instanceof OpenSSLEngineOptions;
        this.client = true;
        this.useAlpn = options.isUseAlpn();
        this.enabledProtocols = options.getEnabledSecureTransportProtocols();
        if (options.isVerifyHost()) {
            this.endpointIdentificationAlgorithm = "HTTPS";
        }
        this.openSslSessionCacheEnabled = (sslEngineOptions instanceof OpenSSLEngineOptions) && ((OpenSSLEngineOptions) sslEngineOptions).isSessionCacheEnabled();
    }

    public SSLHelper(HttpServerOptions options, KeyCertOptions keyCertOptions, TrustOptions trustOptions) {
        this.clientAuth = ClientAuth.NONE;
        this.endpointIdentificationAlgorithm = "";
        this.sslContextMap = new ConcurrentHashMap();
        this.openSslSessionCacheEnabled = true;
        SSLEngineOptions sslEngineOptions = resolveEngineOptions(options);
        this.ssl = options.isSsl();
        this.sslHandshakeTimeout = options.getSslHandshakeTimeout();
        this.sslHandshakeTimeoutUnit = options.getSslHandshakeTimeoutUnit();
        this.keyCertOptions = keyCertOptions;
        this.trustOptions = trustOptions;
        this.clientAuth = options.getClientAuth();
        this.crlPaths = options.getCrlPaths() != null ? new ArrayList<>(options.getCrlPaths()) : null;
        this.crlValues = options.getCrlValues() != null ? new ArrayList<>(options.getCrlValues()) : null;
        this.enabledCipherSuites = options.getEnabledCipherSuites();
        this.openSsl = sslEngineOptions instanceof OpenSSLEngineOptions;
        this.client = false;
        this.useAlpn = options.isUseAlpn();
        this.enabledProtocols = options.getEnabledSecureTransportProtocols();
        this.openSslSessionCacheEnabled = (sslEngineOptions instanceof OpenSSLEngineOptions) && ((OpenSSLEngineOptions) sslEngineOptions).isSessionCacheEnabled();
        this.sni = options.isSni();
    }

    public SSLHelper(NetClientOptions options, KeyCertOptions keyCertOptions, TrustOptions trustOptions) {
        this.clientAuth = ClientAuth.NONE;
        this.endpointIdentificationAlgorithm = "";
        this.sslContextMap = new ConcurrentHashMap();
        this.openSslSessionCacheEnabled = true;
        SSLEngineOptions sslEngineOptions = resolveEngineOptions(options);
        this.ssl = options.isSsl();
        this.sslHandshakeTimeout = options.getSslHandshakeTimeout();
        this.sslHandshakeTimeoutUnit = options.getSslHandshakeTimeoutUnit();
        this.keyCertOptions = keyCertOptions;
        this.trustOptions = trustOptions;
        this.trustAll = options.isTrustAll();
        this.crlPaths = new ArrayList<>(options.getCrlPaths());
        this.crlValues = new ArrayList<>(options.getCrlValues());
        this.enabledCipherSuites = options.getEnabledCipherSuites();
        this.openSsl = sslEngineOptions instanceof OpenSSLEngineOptions;
        this.client = true;
        this.useAlpn = false;
        this.enabledProtocols = options.getEnabledSecureTransportProtocols();
        this.endpointIdentificationAlgorithm = options.getHostnameVerificationAlgorithm();
        this.openSslSessionCacheEnabled = (sslEngineOptions instanceof OpenSSLEngineOptions) && ((OpenSSLEngineOptions) sslEngineOptions).isSessionCacheEnabled();
    }

    public SSLHelper(NetServerOptions options, KeyCertOptions keyCertOptions, TrustOptions trustOptions) {
        this.clientAuth = ClientAuth.NONE;
        this.endpointIdentificationAlgorithm = "";
        this.sslContextMap = new ConcurrentHashMap();
        this.openSslSessionCacheEnabled = true;
        SSLEngineOptions sslEngineOptions = resolveEngineOptions(options);
        this.ssl = options.isSsl();
        this.sslHandshakeTimeout = options.getSslHandshakeTimeout();
        this.sslHandshakeTimeoutUnit = options.getSslHandshakeTimeoutUnit();
        this.keyCertOptions = keyCertOptions;
        this.trustOptions = trustOptions;
        this.clientAuth = options.getClientAuth();
        this.crlPaths = options.getCrlPaths() != null ? new ArrayList<>(options.getCrlPaths()) : null;
        this.crlValues = options.getCrlValues() != null ? new ArrayList<>(options.getCrlValues()) : null;
        this.enabledCipherSuites = options.getEnabledCipherSuites();
        this.openSsl = sslEngineOptions instanceof OpenSSLEngineOptions;
        this.client = false;
        this.useAlpn = false;
        this.enabledProtocols = options.getEnabledSecureTransportProtocols();
        this.openSslSessionCacheEnabled = (options.getSslEngineOptions() instanceof OpenSSLEngineOptions) && ((OpenSSLEngineOptions) options.getSslEngineOptions()).isSessionCacheEnabled();
        this.sni = options.isSni();
    }

    public boolean isSSL() {
        return this.ssl;
    }

    public boolean isSNI() {
        return this.sni;
    }

    public long getSslHandshakeTimeout() {
        return this.sslHandshakeTimeout;
    }

    public TimeUnit getSslHandshakeTimeoutUnit() {
        return this.sslHandshakeTimeoutUnit;
    }

    public ClientAuth getClientAuth() {
        return this.clientAuth;
    }

    public List<HttpVersion> getApplicationProtocols() {
        return this.applicationProtocols;
    }

    public SSLHelper setApplicationProtocols(List<HttpVersion> applicationProtocols) {
        this.applicationProtocols = applicationProtocols;
        return this;
    }

    private SslContext createContext(VertxInternal vertx, X509KeyManager mgr, TrustManagerFactory trustMgrFactory) {
        SslContextBuilder builder;
        try {
            if (this.client) {
                builder = SslContextBuilder.forClient();
                KeyManagerFactory keyMgrFactory = getKeyMgrFactory(vertx);
                if (keyMgrFactory != null) {
                    builder.keyManager(keyMgrFactory);
                }
            } else if (mgr != null) {
                builder = SslContextBuilder.forServer(mgr.getPrivateKey(null), (String) null, mgr.getCertificateChain(null));
            } else {
                KeyManagerFactory keyMgrFactory2 = getKeyMgrFactory(vertx);
                if (keyMgrFactory2 == null) {
                    throw new VertxException("Key/certificate is mandatory for SSL");
                }
                builder = SslContextBuilder.forServer(keyMgrFactory2);
            }
            Collection<String> cipherSuites = this.enabledCipherSuites;
            if (this.openSsl) {
                builder.sslProvider(SslProvider.OPENSSL);
                if (cipherSuites == null || cipherSuites.isEmpty()) {
                    cipherSuites = OpenSsl.availableOpenSslCipherSuites();
                }
            } else {
                builder.sslProvider(SslProvider.JDK);
                if (cipherSuites == null || cipherSuites.isEmpty()) {
                    cipherSuites = DefaultJDKCipherSuite.get();
                }
            }
            if (trustMgrFactory != null) {
                builder.trustManager(trustMgrFactory);
            }
            if (cipherSuites != null && cipherSuites.size() > 0) {
                builder.ciphers(cipherSuites);
            }
            if (this.useAlpn && this.applicationProtocols != null && this.applicationProtocols.size() > 0) {
                ApplicationProtocolConfig.Protocol protocol = ApplicationProtocolConfig.Protocol.ALPN;
                ApplicationProtocolConfig.SelectorFailureBehavior selectorFailureBehavior = ApplicationProtocolConfig.SelectorFailureBehavior.NO_ADVERTISE;
                ApplicationProtocolConfig.SelectedListenerFailureBehavior selectedListenerFailureBehavior = ApplicationProtocolConfig.SelectedListenerFailureBehavior.ACCEPT;
                Stream<HttpVersion> stream = this.applicationProtocols.stream();
                Map<HttpVersion, String> map = PROTOCOL_NAME_MAPPING;
                map.getClass();
                builder.applicationProtocolConfig(new ApplicationProtocolConfig(protocol, selectorFailureBehavior, selectedListenerFailureBehavior, (Iterable<String>) stream.map((v1) -> {
                    return r7.get(v1);
                }).collect(Collectors.toList())));
            }
            SslContext ctx = builder.build();
            if (ctx instanceof OpenSslServerContext) {
                SSLSessionContext sslSessionContext = ctx.sessionContext();
                if (sslSessionContext instanceof OpenSslServerSessionContext) {
                    ((OpenSslServerSessionContext) sslSessionContext).setSessionCacheEnabled(this.openSslSessionCacheEnabled);
                }
            }
            return ctx;
        } catch (Exception e) {
            throw new VertxException(e);
        }
    }

    private KeyManagerFactory getKeyMgrFactory(VertxInternal vertx) throws Exception {
        if (this.keyCertOptions == null) {
            return null;
        }
        return this.keyCertOptions.getKeyManagerFactory(vertx);
    }

    private TrustManagerFactory getTrustMgrFactory(VertxInternal vertx, String serverName) throws Exception {
        TrustManagerFactory fact;
        TrustManager[] mgrs = null;
        if (this.trustAll) {
            mgrs = new TrustManager[]{createTrustAllTrustManager()};
        } else if (this.trustOptions != null) {
            if (serverName != null) {
                Function<String, TrustManager[]> mapper = this.trustOptions.trustManagerMapper(vertx);
                if (mapper != null) {
                    mgrs = mapper.apply(serverName);
                }
                if (mgrs == null && (fact = this.trustOptions.getTrustManagerFactory(vertx)) != null) {
                    mgrs = fact.getTrustManagers();
                }
            } else {
                TrustManagerFactory fact2 = this.trustOptions.getTrustManagerFactory(vertx);
                if (fact2 != null) {
                    mgrs = fact2.getTrustManagers();
                }
            }
        }
        if (mgrs == null) {
            return null;
        }
        if (this.crlPaths != null && this.crlValues != null && (this.crlPaths.size() > 0 || this.crlValues.size() > 0)) {
            Stream map = this.crlPaths.stream().map(path -> {
                return vertx.resolveFile(path).getAbsolutePath();
            });
            FileSystem fileSystem = vertx.fileSystem();
            fileSystem.getClass();
            Stream<Buffer> tmp = map.map(fileSystem::readFileBlocking);
            Stream<Buffer> tmp2 = Stream.concat(tmp, this.crlValues.stream());
            CertificateFactory certificatefactory = CertificateFactory.getInstance(KeyUtil.CERT_TYPE_X509);
            ArrayList<CRL> crls = new ArrayList<>();
            for (Buffer crlValue : (List) tmp2.collect(Collectors.toList())) {
                crls.addAll(certificatefactory.generateCRLs(new ByteArrayInputStream(crlValue.getBytes())));
            }
            mgrs = createUntrustRevokedCertTrustManager(mgrs, crls);
        }
        return new VertxTrustManagerFactory(mgrs);
    }

    private static TrustManager[] createUntrustRevokedCertTrustManager(TrustManager[] trustMgrs, final ArrayList<CRL> crls) {
        TrustManager[] trustMgrs2 = (TrustManager[]) trustMgrs.clone();
        for (int i = 0; i < trustMgrs2.length; i++) {
            TrustManager trustMgr = trustMgrs2[i];
            if (trustMgr instanceof X509TrustManager) {
                final X509TrustManager x509TrustManager = (X509TrustManager) trustMgr;
                trustMgrs2[i] = new X509TrustManager() { // from class: io.vertx.core.net.impl.SSLHelper.1
                    @Override // javax.net.ssl.X509TrustManager
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                        checkRevoked(x509Certificates);
                        x509TrustManager.checkClientTrusted(x509Certificates, s);
                    }

                    @Override // javax.net.ssl.X509TrustManager
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                        checkRevoked(x509Certificates);
                        x509TrustManager.checkServerTrusted(x509Certificates, s);
                    }

                    private void checkRevoked(X509Certificate[] x509Certificates) throws CertificateException {
                        for (X509Certificate cert : x509Certificates) {
                            Iterator it = crls.iterator();
                            while (it.hasNext()) {
                                CRL crl = (CRL) it.next();
                                if (crl.isRevoked(cert)) {
                                    throw new CertificateException("Certificate revoked");
                                }
                            }
                        }
                    }

                    @Override // javax.net.ssl.X509TrustManager
                    public X509Certificate[] getAcceptedIssuers() {
                        return x509TrustManager.getAcceptedIssuers();
                    }
                };
            }
        }
        return trustMgrs2;
    }

    private static TrustManager createTrustAllTrustManager() {
        return new X509TrustManager() { // from class: io.vertx.core.net.impl.SSLHelper.2
            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }

    public Mapping<? super String, ? extends SslContext> serverNameMapper(VertxInternal vertx) {
        return serverName -> {
            SslContext ctx = getContext(vertx, serverName);
            if (ctx != null) {
                ctx = new DelegatingSslContext(ctx) { // from class: io.vertx.core.net.impl.SSLHelper.3
                    @Override // io.netty.handler.ssl.DelegatingSslContext
                    protected void initEngine(SSLEngine engine) {
                        SSLHelper.this.configureEngine(engine, serverName);
                    }
                };
            }
            return ctx;
        };
    }

    public void configureEngine(SSLEngine engine, String serverName) {
        if (this.enabledCipherSuites != null && !this.enabledCipherSuites.isEmpty()) {
            String[] toUse = (String[]) this.enabledCipherSuites.toArray(new String[this.enabledCipherSuites.size()]);
            engine.setEnabledCipherSuites(toUse);
        }
        engine.setUseClientMode(this.client);
        Set<String> protocols = new LinkedHashSet<>(this.enabledProtocols);
        protocols.retainAll(Arrays.asList(engine.getSupportedProtocols()));
        if (protocols.isEmpty()) {
            log.warn("no SSL/TLS protocols are enabled due to configuration restrictions");
        }
        engine.setEnabledProtocols((String[]) protocols.toArray(new String[protocols.size()]));
        if (!this.client) {
            switch (getClientAuth()) {
                case REQUEST:
                    engine.setWantClientAuth(true);
                    break;
                case REQUIRED:
                    engine.setNeedClientAuth(true);
                    break;
                case NONE:
                    engine.setNeedClientAuth(false);
                    break;
            }
        } else if (!this.endpointIdentificationAlgorithm.isEmpty()) {
            SSLParameters sslParameters = engine.getSSLParameters();
            sslParameters.setEndpointIdentificationAlgorithm(this.endpointIdentificationAlgorithm);
            engine.setSSLParameters(sslParameters);
        }
        if (serverName != null) {
            SSLParameters sslParameters2 = engine.getSSLParameters();
            sslParameters2.setServerNames(Collections.singletonList(new SNIHostName(serverName)));
            engine.setSSLParameters(sslParameters2);
        }
    }

    public SslContext getContext(VertxInternal vertx) {
        return getContext(vertx, null);
    }

    public SslContext getContext(VertxInternal vertx, String serverName) {
        if (serverName == null) {
            if (this.sslContext == null) {
                try {
                    TrustManagerFactory trustMgrFactory = getTrustMgrFactory(vertx, null);
                    this.sslContext = createContext(vertx, null, trustMgrFactory);
                } catch (Exception e) {
                    throw new VertxException(e);
                }
            }
            return this.sslContext;
        }
        try {
            X509KeyManager mgr = this.keyCertOptions.keyManagerMapper(vertx).apply(serverName);
            if (mgr == null) {
                return this.sslContext;
            }
            try {
                TrustManagerFactory trustMgrFactory2 = getTrustMgrFactory(vertx, serverName);
                return this.sslContextMap.computeIfAbsent(mgr.getCertificateChain(null)[0], s -> {
                    return createContext(vertx, mgr, trustMgrFactory2);
                });
            } catch (Exception e2) {
                throw new VertxException(e2);
            }
        } catch (Exception e3) {
            throw new RuntimeException(e3);
        }
    }

    public synchronized void validate(VertxInternal vertx) {
        if (this.ssl) {
            getContext(vertx, null);
        }
    }

    public SSLEngine createEngine(SslContext sslContext) {
        SSLEngine engine = sslContext.newEngine(ByteBufAllocator.DEFAULT);
        configureEngine(engine, null);
        return engine;
    }

    public SSLEngine createEngine(VertxInternal vertx, SocketAddress socketAddress, String serverName) {
        SSLEngine engine;
        SslContext context = getContext(vertx, null);
        if (socketAddress.path() != null) {
            engine = context.newEngine(ByteBufAllocator.DEFAULT);
        } else {
            engine = context.newEngine(ByteBufAllocator.DEFAULT, socketAddress.host(), socketAddress.port());
        }
        configureEngine(engine, serverName);
        return engine;
    }

    public SSLEngine createEngine(VertxInternal vertx, String host, int port, boolean forceSNI) {
        SSLEngine engine = getContext(vertx, null).newEngine(ByteBufAllocator.DEFAULT, host, port);
        configureEngine(engine, forceSNI ? host : null);
        return engine;
    }

    public SSLEngine createEngine(VertxInternal vertx, String host, int port) {
        SSLEngine engine = getContext(vertx, null).newEngine(ByteBufAllocator.DEFAULT, host, port);
        configureEngine(engine, null);
        return engine;
    }

    public SSLEngine createEngine(VertxInternal vertx) {
        SSLEngine engine = getContext(vertx, null).newEngine(ByteBufAllocator.DEFAULT);
        configureEngine(engine, null);
        return engine;
    }
}
