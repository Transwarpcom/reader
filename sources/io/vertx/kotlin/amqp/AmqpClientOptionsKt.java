package io.vertx.kotlin.amqp;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.amqp.AmqpClientOptions;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.JdkSSLEngineOptions;
import io.vertx.core.net.JksOptions;
import io.vertx.core.net.OpenSSLEngineOptions;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.core.net.PemTrustOptions;
import io.vertx.core.net.PfxOptions;
import io.vertx.core.net.ProxyOptions;
import io.vertx.ext.web.handler.FormLoginHandler;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Opcodes;

/* compiled from: AmqpClientOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��j\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u001c\n��\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0017\u001a÷\u0004\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00072\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010$2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010'\u001a\u0004\u0018\u00010(2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u00103\u001a\u0004\u0018\u00010,2\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u00107\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010A\u001aõ\u0004\u0010B\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00072\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010$2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010'\u001a\u0004\u0018\u00010(2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u00103\u001a\u0004\u0018\u00010,2\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u00107\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010A¨\u0006C"}, d2 = {"AmqpClientOptions", "Lio/vertx/amqp/AmqpClientOptions;", "connectTimeout", "", "containerId", "", "crlPaths", "", "crlValues", "Lio/vertx/core/buffer/Buffer;", "enabledCipherSuites", "enabledSaslMechanisms", "enabledSecureTransportProtocols", "heartbeat", "host", "hostnameVerificationAlgorithm", "idleTimeout", "idleTimeoutUnit", "Ljava/util/concurrent/TimeUnit;", "jdkSslEngineOptions", "Lio/vertx/core/net/JdkSSLEngineOptions;", "keyStoreOptions", "Lio/vertx/core/net/JksOptions;", "localAddress", "logActivity", "", "maxFrameSize", "metricsName", "openSslEngineOptions", "Lio/vertx/core/net/OpenSSLEngineOptions;", FormLoginHandler.DEFAULT_PASSWORD_PARAM, "pemKeyCertOptions", "Lio/vertx/core/net/PemKeyCertOptions;", "pemTrustOptions", "Lio/vertx/core/net/PemTrustOptions;", "pfxKeyCertOptions", "Lio/vertx/core/net/PfxOptions;", "pfxTrustOptions", RtspHeaders.Values.PORT, "proxyOptions", "Lio/vertx/core/net/ProxyOptions;", "receiveBufferSize", "reconnectAttempts", "reconnectInterval", "", "reuseAddress", "reusePort", "sendBufferSize", "sniServerName", "soLinger", "ssl", "sslHandshakeTimeout", "sslHandshakeTimeoutUnit", "tcpCork", "tcpFastOpen", "tcpKeepAlive", "tcpNoDelay", "tcpQuickAck", "trafficClass", "trustAll", "trustStoreOptions", "useAlpn", "usePooledBuffers", FormLoginHandler.DEFAULT_USERNAME_PARAM, "virtualHost", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Iterable;Ljava/lang/Iterable;Ljava/lang/Iterable;Ljava/lang/Iterable;Ljava/lang/Iterable;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/concurrent/TimeUnit;Lio/vertx/core/net/JdkSSLEngineOptions;Lio/vertx/core/net/JksOptions;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Lio/vertx/core/net/OpenSSLEngineOptions;Ljava/lang/String;Lio/vertx/core/net/PemKeyCertOptions;Lio/vertx/core/net/PemTrustOptions;Lio/vertx/core/net/PfxOptions;Lio/vertx/core/net/PfxOptions;Ljava/lang/Integer;Lio/vertx/core/net/ProxyOptions;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Long;Ljava/util/concurrent/TimeUnit;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;Lio/vertx/core/net/JksOptions;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)Lio/vertx/amqp/AmqpClientOptions;", "amqpClientOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/amqp/AmqpClientOptionsKt.class */
public final class AmqpClientOptionsKt {
    @NotNull
    public static /* synthetic */ AmqpClientOptions amqpClientOptionsOf$default(Integer num, String str, Iterable iterable, Iterable iterable2, Iterable iterable3, Iterable iterable4, Iterable iterable5, Integer num2, String str2, String str3, Integer num3, TimeUnit timeUnit, JdkSSLEngineOptions jdkSSLEngineOptions, JksOptions jksOptions, String str4, Boolean bool, Integer num4, String str5, OpenSSLEngineOptions openSSLEngineOptions, String str6, PemKeyCertOptions pemKeyCertOptions, PemTrustOptions pemTrustOptions, PfxOptions pfxOptions, PfxOptions pfxOptions2, Integer num5, ProxyOptions proxyOptions, Integer num6, Integer num7, Long l, Boolean bool2, Boolean bool3, Integer num8, String str7, Integer num9, Boolean bool4, Long l2, TimeUnit timeUnit2, Boolean bool5, Boolean bool6, Boolean bool7, Boolean bool8, Boolean bool9, Integer num10, Boolean bool10, JksOptions jksOptions2, Boolean bool11, Boolean bool12, String str8, String str9, int i, int i2, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 8) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 16) != 0) {
            iterable3 = (Iterable) null;
        }
        if ((i & 32) != 0) {
            iterable4 = (Iterable) null;
        }
        if ((i & 64) != 0) {
            iterable5 = (Iterable) null;
        }
        if ((i & 128) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 256) != 0) {
            str2 = (String) null;
        }
        if ((i & 512) != 0) {
            str3 = (String) null;
        }
        if ((i & 1024) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 2048) != 0) {
            timeUnit = (TimeUnit) null;
        }
        if ((i & 4096) != 0) {
            jdkSSLEngineOptions = (JdkSSLEngineOptions) null;
        }
        if ((i & 8192) != 0) {
            jksOptions = (JksOptions) null;
        }
        if ((i & 16384) != 0) {
            str4 = (String) null;
        }
        if ((i & 32768) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 65536) != 0) {
            num4 = (Integer) null;
        }
        if ((i & 131072) != 0) {
            str5 = (String) null;
        }
        if ((i & 262144) != 0) {
            openSSLEngineOptions = (OpenSSLEngineOptions) null;
        }
        if ((i & Opcodes.ASM8) != 0) {
            str6 = (String) null;
        }
        if ((i & 1048576) != 0) {
            pemKeyCertOptions = (PemKeyCertOptions) null;
        }
        if ((i & 2097152) != 0) {
            pemTrustOptions = (PemTrustOptions) null;
        }
        if ((i & 4194304) != 0) {
            pfxOptions = (PfxOptions) null;
        }
        if ((i & 8388608) != 0) {
            pfxOptions2 = (PfxOptions) null;
        }
        if ((i & 16777216) != 0) {
            num5 = (Integer) null;
        }
        if ((i & 33554432) != 0) {
            proxyOptions = (ProxyOptions) null;
        }
        if ((i & 67108864) != 0) {
            num6 = (Integer) null;
        }
        if ((i & 134217728) != 0) {
            num7 = (Integer) null;
        }
        if ((i & 268435456) != 0) {
            l = (Long) null;
        }
        if ((i & 536870912) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 1073741824) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & Integer.MIN_VALUE) != 0) {
            num8 = (Integer) null;
        }
        if ((i2 & 1) != 0) {
            str7 = (String) null;
        }
        if ((i2 & 2) != 0) {
            num9 = (Integer) null;
        }
        if ((i2 & 4) != 0) {
            bool4 = (Boolean) null;
        }
        if ((i2 & 8) != 0) {
            l2 = (Long) null;
        }
        if ((i2 & 16) != 0) {
            timeUnit2 = (TimeUnit) null;
        }
        if ((i2 & 32) != 0) {
            bool5 = (Boolean) null;
        }
        if ((i2 & 64) != 0) {
            bool6 = (Boolean) null;
        }
        if ((i2 & 128) != 0) {
            bool7 = (Boolean) null;
        }
        if ((i2 & 256) != 0) {
            bool8 = (Boolean) null;
        }
        if ((i2 & 512) != 0) {
            bool9 = (Boolean) null;
        }
        if ((i2 & 1024) != 0) {
            num10 = (Integer) null;
        }
        if ((i2 & 2048) != 0) {
            bool10 = (Boolean) null;
        }
        if ((i2 & 4096) != 0) {
            jksOptions2 = (JksOptions) null;
        }
        if ((i2 & 8192) != 0) {
            bool11 = (Boolean) null;
        }
        if ((i2 & 16384) != 0) {
            bool12 = (Boolean) null;
        }
        if ((i2 & 32768) != 0) {
            str8 = (String) null;
        }
        if ((i2 & 65536) != 0) {
            str9 = (String) null;
        }
        return amqpClientOptionsOf(num, str, iterable, iterable2, iterable3, iterable4, iterable5, num2, str2, str3, num3, timeUnit, jdkSSLEngineOptions, jksOptions, str4, bool, num4, str5, openSSLEngineOptions, str6, pemKeyCertOptions, pemTrustOptions, pfxOptions, pfxOptions2, num5, proxyOptions, num6, num7, l, bool2, bool3, num8, str7, num9, bool4, l2, timeUnit2, bool5, bool6, bool7, bool8, bool9, num10, bool10, jksOptions2, bool11, bool12, str8, str9);
    }

    @NotNull
    public static final AmqpClientOptions amqpClientOptionsOf(@Nullable Integer connectTimeout, @Nullable String containerId, @Nullable Iterable<String> iterable, @Nullable Iterable<? extends Buffer> iterable2, @Nullable Iterable<String> iterable3, @Nullable Iterable<String> iterable4, @Nullable Iterable<String> iterable5, @Nullable Integer heartbeat, @Nullable String host, @Nullable String hostnameVerificationAlgorithm, @Nullable Integer idleTimeout, @Nullable TimeUnit idleTimeoutUnit, @Nullable JdkSSLEngineOptions jdkSslEngineOptions, @Nullable JksOptions keyStoreOptions, @Nullable String localAddress, @Nullable Boolean logActivity, @Nullable Integer maxFrameSize, @Nullable String metricsName, @Nullable OpenSSLEngineOptions openSslEngineOptions, @Nullable String password, @Nullable PemKeyCertOptions pemKeyCertOptions, @Nullable PemTrustOptions pemTrustOptions, @Nullable PfxOptions pfxKeyCertOptions, @Nullable PfxOptions pfxTrustOptions, @Nullable Integer port, @Nullable ProxyOptions proxyOptions, @Nullable Integer receiveBufferSize, @Nullable Integer reconnectAttempts, @Nullable Long reconnectInterval, @Nullable Boolean reuseAddress, @Nullable Boolean reusePort, @Nullable Integer sendBufferSize, @Nullable String sniServerName, @Nullable Integer soLinger, @Nullable Boolean ssl, @Nullable Long sslHandshakeTimeout, @Nullable TimeUnit sslHandshakeTimeoutUnit, @Nullable Boolean tcpCork, @Nullable Boolean tcpFastOpen, @Nullable Boolean tcpKeepAlive, @Nullable Boolean tcpNoDelay, @Nullable Boolean tcpQuickAck, @Nullable Integer trafficClass, @Nullable Boolean trustAll, @Nullable JksOptions trustStoreOptions, @Nullable Boolean useAlpn, @Nullable Boolean usePooledBuffers, @Nullable String username, @Nullable String virtualHost) {
        AmqpClientOptions $this$apply = new AmqpClientOptions();
        if (connectTimeout != null) {
            $this$apply.setConnectTimeout(connectTimeout.intValue());
        }
        if (containerId != null) {
            $this$apply.setContainerId(containerId);
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
            for (String item4 : iterable4) {
                $this$apply.addEnabledSaslMechanism(item4);
            }
        }
        if (iterable5 != null) {
            $this$apply.setEnabledSecureTransportProtocols(CollectionsKt.toSet(iterable5));
        }
        if (heartbeat != null) {
            $this$apply.setHeartbeat(heartbeat.intValue());
        }
        if (host != null) {
            $this$apply.setHost(host);
        }
        if (hostnameVerificationAlgorithm != null) {
            $this$apply.setHostnameVerificationAlgorithm(hostnameVerificationAlgorithm);
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
        if (localAddress != null) {
            $this$apply.setLocalAddress(localAddress);
        }
        if (logActivity != null) {
            $this$apply.setLogActivity(logActivity.booleanValue());
        }
        if (maxFrameSize != null) {
            $this$apply.setMaxFrameSize(maxFrameSize.intValue());
        }
        if (metricsName != null) {
            $this$apply.setMetricsName(metricsName);
        }
        if (openSslEngineOptions != null) {
            $this$apply.setOpenSslEngineOptions(openSslEngineOptions);
        }
        if (password != null) {
            $this$apply.setPassword(password);
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
        if (proxyOptions != null) {
            $this$apply.setProxyOptions(proxyOptions);
        }
        if (receiveBufferSize != null) {
            $this$apply.setReceiveBufferSize(receiveBufferSize.intValue());
        }
        if (reconnectAttempts != null) {
            $this$apply.setReconnectAttempts(reconnectAttempts.intValue());
        }
        if (reconnectInterval != null) {
            $this$apply.setReconnectInterval(reconnectInterval.longValue());
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
        if (sniServerName != null) {
            $this$apply.setSniServerName(sniServerName);
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
        if (trustAll != null) {
            $this$apply.setTrustAll(trustAll.booleanValue());
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
        if (username != null) {
            $this$apply.setUsername(username);
        }
        if (virtualHost != null) {
            $this$apply.setVirtualHost(virtualHost);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "amqpClientOptionsOf(connectTimeout, containerId, crlPaths, crlValues, enabledCipherSuites, enabledSaslMechanisms, enabledSecureTransportProtocols, heartbeat, host, hostnameVerificationAlgorithm, idleTimeout, idleTimeoutUnit, jdkSslEngineOptions, keyStoreOptions, localAddress, logActivity, maxFrameSize, metricsName, openSslEngineOptions, password, pemKeyCertOptions, pemTrustOptions, pfxKeyCertOptions, pfxTrustOptions, port, proxyOptions, receiveBufferSize, reconnectAttempts, reconnectInterval, reuseAddress, reusePort, sendBufferSize, sniServerName, soLinger, ssl, sslHandshakeTimeout, sslHandshakeTimeoutUnit, tcpCork, tcpFastOpen, tcpKeepAlive, tcpNoDelay, tcpQuickAck, trafficClass, trustAll, trustStoreOptions, useAlpn, usePooledBuffers, username, virtualHost)"))
    @NotNull
    public static /* synthetic */ AmqpClientOptions AmqpClientOptions$default(Integer num, String str, Iterable iterable, Iterable iterable2, Iterable iterable3, Iterable iterable4, Iterable iterable5, Integer num2, String str2, String str3, Integer num3, TimeUnit timeUnit, JdkSSLEngineOptions jdkSSLEngineOptions, JksOptions jksOptions, String str4, Boolean bool, Integer num4, String str5, OpenSSLEngineOptions openSSLEngineOptions, String str6, PemKeyCertOptions pemKeyCertOptions, PemTrustOptions pemTrustOptions, PfxOptions pfxOptions, PfxOptions pfxOptions2, Integer num5, ProxyOptions proxyOptions, Integer num6, Integer num7, Long l, Boolean bool2, Boolean bool3, Integer num8, String str7, Integer num9, Boolean bool4, Long l2, TimeUnit timeUnit2, Boolean bool5, Boolean bool6, Boolean bool7, Boolean bool8, Boolean bool9, Integer num10, Boolean bool10, JksOptions jksOptions2, Boolean bool11, Boolean bool12, String str8, String str9, int i, int i2, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 8) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 16) != 0) {
            iterable3 = (Iterable) null;
        }
        if ((i & 32) != 0) {
            iterable4 = (Iterable) null;
        }
        if ((i & 64) != 0) {
            iterable5 = (Iterable) null;
        }
        if ((i & 128) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 256) != 0) {
            str2 = (String) null;
        }
        if ((i & 512) != 0) {
            str3 = (String) null;
        }
        if ((i & 1024) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 2048) != 0) {
            timeUnit = (TimeUnit) null;
        }
        if ((i & 4096) != 0) {
            jdkSSLEngineOptions = (JdkSSLEngineOptions) null;
        }
        if ((i & 8192) != 0) {
            jksOptions = (JksOptions) null;
        }
        if ((i & 16384) != 0) {
            str4 = (String) null;
        }
        if ((i & 32768) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 65536) != 0) {
            num4 = (Integer) null;
        }
        if ((i & 131072) != 0) {
            str5 = (String) null;
        }
        if ((i & 262144) != 0) {
            openSSLEngineOptions = (OpenSSLEngineOptions) null;
        }
        if ((i & Opcodes.ASM8) != 0) {
            str6 = (String) null;
        }
        if ((i & 1048576) != 0) {
            pemKeyCertOptions = (PemKeyCertOptions) null;
        }
        if ((i & 2097152) != 0) {
            pemTrustOptions = (PemTrustOptions) null;
        }
        if ((i & 4194304) != 0) {
            pfxOptions = (PfxOptions) null;
        }
        if ((i & 8388608) != 0) {
            pfxOptions2 = (PfxOptions) null;
        }
        if ((i & 16777216) != 0) {
            num5 = (Integer) null;
        }
        if ((i & 33554432) != 0) {
            proxyOptions = (ProxyOptions) null;
        }
        if ((i & 67108864) != 0) {
            num6 = (Integer) null;
        }
        if ((i & 134217728) != 0) {
            num7 = (Integer) null;
        }
        if ((i & 268435456) != 0) {
            l = (Long) null;
        }
        if ((i & 536870912) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 1073741824) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & Integer.MIN_VALUE) != 0) {
            num8 = (Integer) null;
        }
        if ((i2 & 1) != 0) {
            str7 = (String) null;
        }
        if ((i2 & 2) != 0) {
            num9 = (Integer) null;
        }
        if ((i2 & 4) != 0) {
            bool4 = (Boolean) null;
        }
        if ((i2 & 8) != 0) {
            l2 = (Long) null;
        }
        if ((i2 & 16) != 0) {
            timeUnit2 = (TimeUnit) null;
        }
        if ((i2 & 32) != 0) {
            bool5 = (Boolean) null;
        }
        if ((i2 & 64) != 0) {
            bool6 = (Boolean) null;
        }
        if ((i2 & 128) != 0) {
            bool7 = (Boolean) null;
        }
        if ((i2 & 256) != 0) {
            bool8 = (Boolean) null;
        }
        if ((i2 & 512) != 0) {
            bool9 = (Boolean) null;
        }
        if ((i2 & 1024) != 0) {
            num10 = (Integer) null;
        }
        if ((i2 & 2048) != 0) {
            bool10 = (Boolean) null;
        }
        if ((i2 & 4096) != 0) {
            jksOptions2 = (JksOptions) null;
        }
        if ((i2 & 8192) != 0) {
            bool11 = (Boolean) null;
        }
        if ((i2 & 16384) != 0) {
            bool12 = (Boolean) null;
        }
        if ((i2 & 32768) != 0) {
            str8 = (String) null;
        }
        if ((i2 & 65536) != 0) {
            str9 = (String) null;
        }
        return AmqpClientOptions(num, str, iterable, iterable2, iterable3, iterable4, iterable5, num2, str2, str3, num3, timeUnit, jdkSSLEngineOptions, jksOptions, str4, bool, num4, str5, openSSLEngineOptions, str6, pemKeyCertOptions, pemTrustOptions, pfxOptions, pfxOptions2, num5, proxyOptions, num6, num7, l, bool2, bool3, num8, str7, num9, bool4, l2, timeUnit2, bool5, bool6, bool7, bool8, bool9, num10, bool10, jksOptions2, bool11, bool12, str8, str9);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "amqpClientOptionsOf(connectTimeout, containerId, crlPaths, crlValues, enabledCipherSuites, enabledSaslMechanisms, enabledSecureTransportProtocols, heartbeat, host, hostnameVerificationAlgorithm, idleTimeout, idleTimeoutUnit, jdkSslEngineOptions, keyStoreOptions, localAddress, logActivity, maxFrameSize, metricsName, openSslEngineOptions, password, pemKeyCertOptions, pemTrustOptions, pfxKeyCertOptions, pfxTrustOptions, port, proxyOptions, receiveBufferSize, reconnectAttempts, reconnectInterval, reuseAddress, reusePort, sendBufferSize, sniServerName, soLinger, ssl, sslHandshakeTimeout, sslHandshakeTimeoutUnit, tcpCork, tcpFastOpen, tcpKeepAlive, tcpNoDelay, tcpQuickAck, trafficClass, trustAll, trustStoreOptions, useAlpn, usePooledBuffers, username, virtualHost)"))
    @NotNull
    public static final AmqpClientOptions AmqpClientOptions(@Nullable Integer connectTimeout, @Nullable String containerId, @Nullable Iterable<String> iterable, @Nullable Iterable<? extends Buffer> iterable2, @Nullable Iterable<String> iterable3, @Nullable Iterable<String> iterable4, @Nullable Iterable<String> iterable5, @Nullable Integer heartbeat, @Nullable String host, @Nullable String hostnameVerificationAlgorithm, @Nullable Integer idleTimeout, @Nullable TimeUnit idleTimeoutUnit, @Nullable JdkSSLEngineOptions jdkSslEngineOptions, @Nullable JksOptions keyStoreOptions, @Nullable String localAddress, @Nullable Boolean logActivity, @Nullable Integer maxFrameSize, @Nullable String metricsName, @Nullable OpenSSLEngineOptions openSslEngineOptions, @Nullable String password, @Nullable PemKeyCertOptions pemKeyCertOptions, @Nullable PemTrustOptions pemTrustOptions, @Nullable PfxOptions pfxKeyCertOptions, @Nullable PfxOptions pfxTrustOptions, @Nullable Integer port, @Nullable ProxyOptions proxyOptions, @Nullable Integer receiveBufferSize, @Nullable Integer reconnectAttempts, @Nullable Long reconnectInterval, @Nullable Boolean reuseAddress, @Nullable Boolean reusePort, @Nullable Integer sendBufferSize, @Nullable String sniServerName, @Nullable Integer soLinger, @Nullable Boolean ssl, @Nullable Long sslHandshakeTimeout, @Nullable TimeUnit sslHandshakeTimeoutUnit, @Nullable Boolean tcpCork, @Nullable Boolean tcpFastOpen, @Nullable Boolean tcpKeepAlive, @Nullable Boolean tcpNoDelay, @Nullable Boolean tcpQuickAck, @Nullable Integer trafficClass, @Nullable Boolean trustAll, @Nullable JksOptions trustStoreOptions, @Nullable Boolean useAlpn, @Nullable Boolean usePooledBuffers, @Nullable String username, @Nullable String virtualHost) {
        AmqpClientOptions $this$apply = new AmqpClientOptions();
        if (connectTimeout != null) {
            $this$apply.setConnectTimeout(connectTimeout.intValue());
        }
        if (containerId != null) {
            $this$apply.setContainerId(containerId);
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
            for (String item4 : iterable4) {
                $this$apply.addEnabledSaslMechanism(item4);
            }
        }
        if (iterable5 != null) {
            $this$apply.setEnabledSecureTransportProtocols(CollectionsKt.toSet(iterable5));
        }
        if (heartbeat != null) {
            $this$apply.setHeartbeat(heartbeat.intValue());
        }
        if (host != null) {
            $this$apply.setHost(host);
        }
        if (hostnameVerificationAlgorithm != null) {
            $this$apply.setHostnameVerificationAlgorithm(hostnameVerificationAlgorithm);
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
        if (localAddress != null) {
            $this$apply.setLocalAddress(localAddress);
        }
        if (logActivity != null) {
            $this$apply.setLogActivity(logActivity.booleanValue());
        }
        if (maxFrameSize != null) {
            $this$apply.setMaxFrameSize(maxFrameSize.intValue());
        }
        if (metricsName != null) {
            $this$apply.setMetricsName(metricsName);
        }
        if (openSslEngineOptions != null) {
            $this$apply.setOpenSslEngineOptions(openSslEngineOptions);
        }
        if (password != null) {
            $this$apply.setPassword(password);
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
        if (proxyOptions != null) {
            $this$apply.setProxyOptions(proxyOptions);
        }
        if (receiveBufferSize != null) {
            $this$apply.setReceiveBufferSize(receiveBufferSize.intValue());
        }
        if (reconnectAttempts != null) {
            $this$apply.setReconnectAttempts(reconnectAttempts.intValue());
        }
        if (reconnectInterval != null) {
            $this$apply.setReconnectInterval(reconnectInterval.longValue());
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
        if (sniServerName != null) {
            $this$apply.setSniServerName(sniServerName);
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
        if (trustAll != null) {
            $this$apply.setTrustAll(trustAll.booleanValue());
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
        if (username != null) {
            $this$apply.setUsername(username);
        }
        if (virtualHost != null) {
            $this$apply.setVirtualHost(virtualHost);
        }
        return $this$apply;
    }
}
