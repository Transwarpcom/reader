package io.vertx.kotlin.core.net;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.ClientAuth;
import io.vertx.core.net.JdkSSLEngineOptions;
import io.vertx.core.net.JksOptions;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.OpenSSLEngineOptions;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.core.net.PemTrustOptions;
import io.vertx.core.net.PfxOptions;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Opcodes;

/* compiled from: NetServerOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u001c\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\r\u001aá\u0003\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\t2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u0007H\u0007¢\u0006\u0002\u00105\u001aß\u0003\u00106\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\t2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u00105¨\u00067"}, d2 = {"NetServerOptions", "Lio/vertx/core/net/NetServerOptions;", "acceptBacklog", "", "clientAuth", "Lio/vertx/core/http/ClientAuth;", "clientAuthRequired", "", "crlPaths", "", "", "crlValues", "Lio/vertx/core/buffer/Buffer;", "enabledCipherSuites", "enabledSecureTransportProtocols", "host", "idleTimeout", "idleTimeoutUnit", "Ljava/util/concurrent/TimeUnit;", "jdkSslEngineOptions", "Lio/vertx/core/net/JdkSSLEngineOptions;", "keyStoreOptions", "Lio/vertx/core/net/JksOptions;", "logActivity", "openSslEngineOptions", "Lio/vertx/core/net/OpenSSLEngineOptions;", "pemKeyCertOptions", "Lio/vertx/core/net/PemKeyCertOptions;", "pemTrustOptions", "Lio/vertx/core/net/PemTrustOptions;", "pfxKeyCertOptions", "Lio/vertx/core/net/PfxOptions;", "pfxTrustOptions", RtspHeaders.Values.PORT, "receiveBufferSize", "reuseAddress", "reusePort", "sendBufferSize", "sni", "soLinger", "ssl", "sslHandshakeTimeout", "", "sslHandshakeTimeoutUnit", "tcpCork", "tcpFastOpen", "tcpKeepAlive", "tcpNoDelay", "tcpQuickAck", "trafficClass", "trustStoreOptions", "useAlpn", "usePooledBuffers", "(Ljava/lang/Integer;Lio/vertx/core/http/ClientAuth;Ljava/lang/Boolean;Ljava/lang/Iterable;Ljava/lang/Iterable;Ljava/lang/Iterable;Ljava/lang/Iterable;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/concurrent/TimeUnit;Lio/vertx/core/net/JdkSSLEngineOptions;Lio/vertx/core/net/JksOptions;Ljava/lang/Boolean;Lio/vertx/core/net/OpenSSLEngineOptions;Lio/vertx/core/net/PemKeyCertOptions;Lio/vertx/core/net/PemTrustOptions;Lio/vertx/core/net/PfxOptions;Lio/vertx/core/net/PfxOptions;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Long;Ljava/util/concurrent/TimeUnit;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Lio/vertx/core/net/JksOptions;Ljava/lang/Boolean;Ljava/lang/Boolean;)Lio/vertx/core/net/NetServerOptions;", "netServerOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/net/NetServerOptionsKt.class */
public final class NetServerOptionsKt {
    @NotNull
    public static /* synthetic */ NetServerOptions netServerOptionsOf$default(Integer num, ClientAuth clientAuth, Boolean bool, Iterable iterable, Iterable iterable2, Iterable iterable3, Iterable iterable4, String str, Integer num2, TimeUnit timeUnit, JdkSSLEngineOptions jdkSSLEngineOptions, JksOptions jksOptions, Boolean bool2, OpenSSLEngineOptions openSSLEngineOptions, PemKeyCertOptions pemKeyCertOptions, PemTrustOptions pemTrustOptions, PfxOptions pfxOptions, PfxOptions pfxOptions2, Integer num3, Integer num4, Boolean bool3, Boolean bool4, Integer num5, Boolean bool5, Integer num6, Boolean bool6, Long l, TimeUnit timeUnit2, Boolean bool7, Boolean bool8, Boolean bool9, Boolean bool10, Boolean bool11, Integer num7, JksOptions jksOptions2, Boolean bool12, Boolean bool13, int i, int i2, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            clientAuth = (ClientAuth) null;
        }
        if ((i & 4) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 8) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 16) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 32) != 0) {
            iterable3 = (Iterable) null;
        }
        if ((i & 64) != 0) {
            iterable4 = (Iterable) null;
        }
        if ((i & 128) != 0) {
            str = (String) null;
        }
        if ((i & 256) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 512) != 0) {
            timeUnit = (TimeUnit) null;
        }
        if ((i & 1024) != 0) {
            jdkSSLEngineOptions = (JdkSSLEngineOptions) null;
        }
        if ((i & 2048) != 0) {
            jksOptions = (JksOptions) null;
        }
        if ((i & 4096) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 8192) != 0) {
            openSSLEngineOptions = (OpenSSLEngineOptions) null;
        }
        if ((i & 16384) != 0) {
            pemKeyCertOptions = (PemKeyCertOptions) null;
        }
        if ((i & 32768) != 0) {
            pemTrustOptions = (PemTrustOptions) null;
        }
        if ((i & 65536) != 0) {
            pfxOptions = (PfxOptions) null;
        }
        if ((i & 131072) != 0) {
            pfxOptions2 = (PfxOptions) null;
        }
        if ((i & 262144) != 0) {
            num3 = (Integer) null;
        }
        if ((i & Opcodes.ASM8) != 0) {
            num4 = (Integer) null;
        }
        if ((i & 1048576) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 2097152) != 0) {
            bool4 = (Boolean) null;
        }
        if ((i & 4194304) != 0) {
            num5 = (Integer) null;
        }
        if ((i & 8388608) != 0) {
            bool5 = (Boolean) null;
        }
        if ((i & 16777216) != 0) {
            num6 = (Integer) null;
        }
        if ((i & 33554432) != 0) {
            bool6 = (Boolean) null;
        }
        if ((i & 67108864) != 0) {
            l = (Long) null;
        }
        if ((i & 134217728) != 0) {
            timeUnit2 = (TimeUnit) null;
        }
        if ((i & 268435456) != 0) {
            bool7 = (Boolean) null;
        }
        if ((i & 536870912) != 0) {
            bool8 = (Boolean) null;
        }
        if ((i & 1073741824) != 0) {
            bool9 = (Boolean) null;
        }
        if ((i & Integer.MIN_VALUE) != 0) {
            bool10 = (Boolean) null;
        }
        if ((i2 & 1) != 0) {
            bool11 = (Boolean) null;
        }
        if ((i2 & 2) != 0) {
            num7 = (Integer) null;
        }
        if ((i2 & 4) != 0) {
            jksOptions2 = (JksOptions) null;
        }
        if ((i2 & 8) != 0) {
            bool12 = (Boolean) null;
        }
        if ((i2 & 16) != 0) {
            bool13 = (Boolean) null;
        }
        return netServerOptionsOf(num, clientAuth, bool, iterable, iterable2, iterable3, iterable4, str, num2, timeUnit, jdkSSLEngineOptions, jksOptions, bool2, openSSLEngineOptions, pemKeyCertOptions, pemTrustOptions, pfxOptions, pfxOptions2, num3, num4, bool3, bool4, num5, bool5, num6, bool6, l, timeUnit2, bool7, bool8, bool9, bool10, bool11, num7, jksOptions2, bool12, bool13);
    }

    @NotNull
    public static final NetServerOptions netServerOptionsOf(@Nullable Integer acceptBacklog, @Nullable ClientAuth clientAuth, @Nullable Boolean clientAuthRequired, @Nullable Iterable<String> iterable, @Nullable Iterable<? extends Buffer> iterable2, @Nullable Iterable<String> iterable3, @Nullable Iterable<String> iterable4, @Nullable String host, @Nullable Integer idleTimeout, @Nullable TimeUnit idleTimeoutUnit, @Nullable JdkSSLEngineOptions jdkSslEngineOptions, @Nullable JksOptions keyStoreOptions, @Nullable Boolean logActivity, @Nullable OpenSSLEngineOptions openSslEngineOptions, @Nullable PemKeyCertOptions pemKeyCertOptions, @Nullable PemTrustOptions pemTrustOptions, @Nullable PfxOptions pfxKeyCertOptions, @Nullable PfxOptions pfxTrustOptions, @Nullable Integer port, @Nullable Integer receiveBufferSize, @Nullable Boolean reuseAddress, @Nullable Boolean reusePort, @Nullable Integer sendBufferSize, @Nullable Boolean sni, @Nullable Integer soLinger, @Nullable Boolean ssl, @Nullable Long sslHandshakeTimeout, @Nullable TimeUnit sslHandshakeTimeoutUnit, @Nullable Boolean tcpCork, @Nullable Boolean tcpFastOpen, @Nullable Boolean tcpKeepAlive, @Nullable Boolean tcpNoDelay, @Nullable Boolean tcpQuickAck, @Nullable Integer trafficClass, @Nullable JksOptions trustStoreOptions, @Nullable Boolean useAlpn, @Nullable Boolean usePooledBuffers) throws NullPointerException {
        NetServerOptions $this$apply = new NetServerOptions();
        if (acceptBacklog != null) {
            $this$apply.setAcceptBacklog(acceptBacklog.intValue());
        }
        if (clientAuth != null) {
            $this$apply.setClientAuth(clientAuth);
        }
        if (clientAuthRequired != null) {
            $this$apply.setClientAuthRequired(clientAuthRequired.booleanValue());
        }
        if (iterable != null) {
            for (String item : iterable) {
                $this$apply.addCrlPath(item);
            }
        }
        if (iterable2 != null) {
            for (Buffer item2 : iterable2) {
                $this$apply.addCrlValue(item2);
            }
        }
        if (iterable3 != null) {
            for (String item3 : iterable3) {
                $this$apply.addEnabledCipherSuite(item3);
            }
        }
        if (iterable4 != null) {
            $this$apply.setEnabledSecureTransportProtocols(CollectionsKt.toSet(iterable4));
        }
        if (host != null) {
            $this$apply.setHost(host);
        }
        if (idleTimeout != null) {
            $this$apply.setIdleTimeout(idleTimeout.intValue());
        }
        if (idleTimeoutUnit != null) {
            $this$apply.setIdleTimeoutUnit(idleTimeoutUnit);
        }
        if (jdkSslEngineOptions != null) {
            $this$apply.setJdkSslEngineOptions(jdkSslEngineOptions);
        }
        if (keyStoreOptions != null) {
            $this$apply.setKeyStoreOptions(keyStoreOptions);
        }
        if (logActivity != null) {
            $this$apply.setLogActivity(logActivity.booleanValue());
        }
        if (openSslEngineOptions != null) {
            $this$apply.setOpenSslEngineOptions(openSslEngineOptions);
        }
        if (pemKeyCertOptions != null) {
            $this$apply.setPemKeyCertOptions(pemKeyCertOptions);
        }
        if (pemTrustOptions != null) {
            $this$apply.setPemTrustOptions(pemTrustOptions);
        }
        if (pfxKeyCertOptions != null) {
            $this$apply.setPfxKeyCertOptions(pfxKeyCertOptions);
        }
        if (pfxTrustOptions != null) {
            $this$apply.setPfxTrustOptions(pfxTrustOptions);
        }
        if (port != null) {
            $this$apply.setPort(port.intValue());
        }
        if (receiveBufferSize != null) {
            $this$apply.setReceiveBufferSize(receiveBufferSize.intValue());
        }
        if (reuseAddress != null) {
            $this$apply.setReuseAddress(reuseAddress.booleanValue());
        }
        if (reusePort != null) {
            $this$apply.setReusePort(reusePort.booleanValue());
        }
        if (sendBufferSize != null) {
            $this$apply.setSendBufferSize(sendBufferSize.intValue());
        }
        if (sni != null) {
            $this$apply.setSni(sni.booleanValue());
        }
        if (soLinger != null) {
            $this$apply.setSoLinger(soLinger.intValue());
        }
        if (ssl != null) {
            $this$apply.setSsl(ssl.booleanValue());
        }
        if (sslHandshakeTimeout != null) {
            $this$apply.setSslHandshakeTimeout(sslHandshakeTimeout.longValue());
        }
        if (sslHandshakeTimeoutUnit != null) {
            $this$apply.setSslHandshakeTimeoutUnit(sslHandshakeTimeoutUnit);
        }
        if (tcpCork != null) {
            $this$apply.setTcpCork(tcpCork.booleanValue());
        }
        if (tcpFastOpen != null) {
            $this$apply.setTcpFastOpen(tcpFastOpen.booleanValue());
        }
        if (tcpKeepAlive != null) {
            $this$apply.setTcpKeepAlive(tcpKeepAlive.booleanValue());
        }
        if (tcpNoDelay != null) {
            $this$apply.setTcpNoDelay(tcpNoDelay.booleanValue());
        }
        if (tcpQuickAck != null) {
            $this$apply.setTcpQuickAck(tcpQuickAck.booleanValue());
        }
        if (trafficClass != null) {
            $this$apply.setTrafficClass(trafficClass.intValue());
        }
        if (trustStoreOptions != null) {
            $this$apply.setTrustStoreOptions(trustStoreOptions);
        }
        if (useAlpn != null) {
            $this$apply.setUseAlpn(useAlpn.booleanValue());
        }
        if (usePooledBuffers != null) {
            $this$apply.setUsePooledBuffers(usePooledBuffers.booleanValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "netServerOptionsOf(acceptBacklog, clientAuth, clientAuthRequired, crlPaths, crlValues, enabledCipherSuites, enabledSecureTransportProtocols, host, idleTimeout, idleTimeoutUnit, jdkSslEngineOptions, keyStoreOptions, logActivity, openSslEngineOptions, pemKeyCertOptions, pemTrustOptions, pfxKeyCertOptions, pfxTrustOptions, port, receiveBufferSize, reuseAddress, reusePort, sendBufferSize, sni, soLinger, ssl, sslHandshakeTimeout, sslHandshakeTimeoutUnit, tcpCork, tcpFastOpen, tcpKeepAlive, tcpNoDelay, tcpQuickAck, trafficClass, trustStoreOptions, useAlpn, usePooledBuffers)"))
    @NotNull
    public static /* synthetic */ NetServerOptions NetServerOptions$default(Integer num, ClientAuth clientAuth, Boolean bool, Iterable iterable, Iterable iterable2, Iterable iterable3, Iterable iterable4, String str, Integer num2, TimeUnit timeUnit, JdkSSLEngineOptions jdkSSLEngineOptions, JksOptions jksOptions, Boolean bool2, OpenSSLEngineOptions openSSLEngineOptions, PemKeyCertOptions pemKeyCertOptions, PemTrustOptions pemTrustOptions, PfxOptions pfxOptions, PfxOptions pfxOptions2, Integer num3, Integer num4, Boolean bool3, Boolean bool4, Integer num5, Boolean bool5, Integer num6, Boolean bool6, Long l, TimeUnit timeUnit2, Boolean bool7, Boolean bool8, Boolean bool9, Boolean bool10, Boolean bool11, Integer num7, JksOptions jksOptions2, Boolean bool12, Boolean bool13, int i, int i2, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            clientAuth = (ClientAuth) null;
        }
        if ((i & 4) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 8) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 16) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 32) != 0) {
            iterable3 = (Iterable) null;
        }
        if ((i & 64) != 0) {
            iterable4 = (Iterable) null;
        }
        if ((i & 128) != 0) {
            str = (String) null;
        }
        if ((i & 256) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 512) != 0) {
            timeUnit = (TimeUnit) null;
        }
        if ((i & 1024) != 0) {
            jdkSSLEngineOptions = (JdkSSLEngineOptions) null;
        }
        if ((i & 2048) != 0) {
            jksOptions = (JksOptions) null;
        }
        if ((i & 4096) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 8192) != 0) {
            openSSLEngineOptions = (OpenSSLEngineOptions) null;
        }
        if ((i & 16384) != 0) {
            pemKeyCertOptions = (PemKeyCertOptions) null;
        }
        if ((i & 32768) != 0) {
            pemTrustOptions = (PemTrustOptions) null;
        }
        if ((i & 65536) != 0) {
            pfxOptions = (PfxOptions) null;
        }
        if ((i & 131072) != 0) {
            pfxOptions2 = (PfxOptions) null;
        }
        if ((i & 262144) != 0) {
            num3 = (Integer) null;
        }
        if ((i & Opcodes.ASM8) != 0) {
            num4 = (Integer) null;
        }
        if ((i & 1048576) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 2097152) != 0) {
            bool4 = (Boolean) null;
        }
        if ((i & 4194304) != 0) {
            num5 = (Integer) null;
        }
        if ((i & 8388608) != 0) {
            bool5 = (Boolean) null;
        }
        if ((i & 16777216) != 0) {
            num6 = (Integer) null;
        }
        if ((i & 33554432) != 0) {
            bool6 = (Boolean) null;
        }
        if ((i & 67108864) != 0) {
            l = (Long) null;
        }
        if ((i & 134217728) != 0) {
            timeUnit2 = (TimeUnit) null;
        }
        if ((i & 268435456) != 0) {
            bool7 = (Boolean) null;
        }
        if ((i & 536870912) != 0) {
            bool8 = (Boolean) null;
        }
        if ((i & 1073741824) != 0) {
            bool9 = (Boolean) null;
        }
        if ((i & Integer.MIN_VALUE) != 0) {
            bool10 = (Boolean) null;
        }
        if ((i2 & 1) != 0) {
            bool11 = (Boolean) null;
        }
        if ((i2 & 2) != 0) {
            num7 = (Integer) null;
        }
        if ((i2 & 4) != 0) {
            jksOptions2 = (JksOptions) null;
        }
        if ((i2 & 8) != 0) {
            bool12 = (Boolean) null;
        }
        if ((i2 & 16) != 0) {
            bool13 = (Boolean) null;
        }
        return NetServerOptions(num, clientAuth, bool, iterable, iterable2, iterable3, iterable4, str, num2, timeUnit, jdkSSLEngineOptions, jksOptions, bool2, openSSLEngineOptions, pemKeyCertOptions, pemTrustOptions, pfxOptions, pfxOptions2, num3, num4, bool3, bool4, num5, bool5, num6, bool6, l, timeUnit2, bool7, bool8, bool9, bool10, bool11, num7, jksOptions2, bool12, bool13);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "netServerOptionsOf(acceptBacklog, clientAuth, clientAuthRequired, crlPaths, crlValues, enabledCipherSuites, enabledSecureTransportProtocols, host, idleTimeout, idleTimeoutUnit, jdkSslEngineOptions, keyStoreOptions, logActivity, openSslEngineOptions, pemKeyCertOptions, pemTrustOptions, pfxKeyCertOptions, pfxTrustOptions, port, receiveBufferSize, reuseAddress, reusePort, sendBufferSize, sni, soLinger, ssl, sslHandshakeTimeout, sslHandshakeTimeoutUnit, tcpCork, tcpFastOpen, tcpKeepAlive, tcpNoDelay, tcpQuickAck, trafficClass, trustStoreOptions, useAlpn, usePooledBuffers)"))
    @NotNull
    public static final NetServerOptions NetServerOptions(@Nullable Integer acceptBacklog, @Nullable ClientAuth clientAuth, @Nullable Boolean clientAuthRequired, @Nullable Iterable<String> iterable, @Nullable Iterable<? extends Buffer> iterable2, @Nullable Iterable<String> iterable3, @Nullable Iterable<String> iterable4, @Nullable String host, @Nullable Integer idleTimeout, @Nullable TimeUnit idleTimeoutUnit, @Nullable JdkSSLEngineOptions jdkSslEngineOptions, @Nullable JksOptions keyStoreOptions, @Nullable Boolean logActivity, @Nullable OpenSSLEngineOptions openSslEngineOptions, @Nullable PemKeyCertOptions pemKeyCertOptions, @Nullable PemTrustOptions pemTrustOptions, @Nullable PfxOptions pfxKeyCertOptions, @Nullable PfxOptions pfxTrustOptions, @Nullable Integer port, @Nullable Integer receiveBufferSize, @Nullable Boolean reuseAddress, @Nullable Boolean reusePort, @Nullable Integer sendBufferSize, @Nullable Boolean sni, @Nullable Integer soLinger, @Nullable Boolean ssl, @Nullable Long sslHandshakeTimeout, @Nullable TimeUnit sslHandshakeTimeoutUnit, @Nullable Boolean tcpCork, @Nullable Boolean tcpFastOpen, @Nullable Boolean tcpKeepAlive, @Nullable Boolean tcpNoDelay, @Nullable Boolean tcpQuickAck, @Nullable Integer trafficClass, @Nullable JksOptions trustStoreOptions, @Nullable Boolean useAlpn, @Nullable Boolean usePooledBuffers) throws NullPointerException {
        NetServerOptions $this$apply = new NetServerOptions();
        if (acceptBacklog != null) {
            $this$apply.setAcceptBacklog(acceptBacklog.intValue());
        }
        if (clientAuth != null) {
            $this$apply.setClientAuth(clientAuth);
        }
        if (clientAuthRequired != null) {
            $this$apply.setClientAuthRequired(clientAuthRequired.booleanValue());
        }
        if (iterable != null) {
            for (String item : iterable) {
                $this$apply.addCrlPath(item);
            }
        }
        if (iterable2 != null) {
            for (Buffer item2 : iterable2) {
                $this$apply.addCrlValue(item2);
            }
        }
        if (iterable3 != null) {
            for (String item3 : iterable3) {
                $this$apply.addEnabledCipherSuite(item3);
            }
        }
        if (iterable4 != null) {
            $this$apply.setEnabledSecureTransportProtocols(CollectionsKt.toSet(iterable4));
        }
        if (host != null) {
            $this$apply.setHost(host);
        }
        if (idleTimeout != null) {
            $this$apply.setIdleTimeout(idleTimeout.intValue());
        }
        if (idleTimeoutUnit != null) {
            $this$apply.setIdleTimeoutUnit(idleTimeoutUnit);
        }
        if (jdkSslEngineOptions != null) {
            $this$apply.setJdkSslEngineOptions(jdkSslEngineOptions);
        }
        if (keyStoreOptions != null) {
            $this$apply.setKeyStoreOptions(keyStoreOptions);
        }
        if (logActivity != null) {
            $this$apply.setLogActivity(logActivity.booleanValue());
        }
        if (openSslEngineOptions != null) {
            $this$apply.setOpenSslEngineOptions(openSslEngineOptions);
        }
        if (pemKeyCertOptions != null) {
            $this$apply.setPemKeyCertOptions(pemKeyCertOptions);
        }
        if (pemTrustOptions != null) {
            $this$apply.setPemTrustOptions(pemTrustOptions);
        }
        if (pfxKeyCertOptions != null) {
            $this$apply.setPfxKeyCertOptions(pfxKeyCertOptions);
        }
        if (pfxTrustOptions != null) {
            $this$apply.setPfxTrustOptions(pfxTrustOptions);
        }
        if (port != null) {
            $this$apply.setPort(port.intValue());
        }
        if (receiveBufferSize != null) {
            $this$apply.setReceiveBufferSize(receiveBufferSize.intValue());
        }
        if (reuseAddress != null) {
            $this$apply.setReuseAddress(reuseAddress.booleanValue());
        }
        if (reusePort != null) {
            $this$apply.setReusePort(reusePort.booleanValue());
        }
        if (sendBufferSize != null) {
            $this$apply.setSendBufferSize(sendBufferSize.intValue());
        }
        if (sni != null) {
            $this$apply.setSni(sni.booleanValue());
        }
        if (soLinger != null) {
            $this$apply.setSoLinger(soLinger.intValue());
        }
        if (ssl != null) {
            $this$apply.setSsl(ssl.booleanValue());
        }
        if (sslHandshakeTimeout != null) {
            $this$apply.setSslHandshakeTimeout(sslHandshakeTimeout.longValue());
        }
        if (sslHandshakeTimeoutUnit != null) {
            $this$apply.setSslHandshakeTimeoutUnit(sslHandshakeTimeoutUnit);
        }
        if (tcpCork != null) {
            $this$apply.setTcpCork(tcpCork.booleanValue());
        }
        if (tcpFastOpen != null) {
            $this$apply.setTcpFastOpen(tcpFastOpen.booleanValue());
        }
        if (tcpKeepAlive != null) {
            $this$apply.setTcpKeepAlive(tcpKeepAlive.booleanValue());
        }
        if (tcpNoDelay != null) {
            $this$apply.setTcpNoDelay(tcpNoDelay.booleanValue());
        }
        if (tcpQuickAck != null) {
            $this$apply.setTcpQuickAck(tcpQuickAck.booleanValue());
        }
        if (trafficClass != null) {
            $this$apply.setTrafficClass(trafficClass.intValue());
        }
        if (trustStoreOptions != null) {
            $this$apply.setTrustStoreOptions(trustStoreOptions);
        }
        if (useAlpn != null) {
            $this$apply.setUseAlpn(useAlpn.booleanValue());
        }
        if (usePooledBuffers != null) {
            $this$apply.setUsePooledBuffers(usePooledBuffers.booleanValue());
        }
        return $this$apply;
    }
}
