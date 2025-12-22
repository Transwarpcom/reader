package io.vertx.kotlin.ext.stomp;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.ClientAuth;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.JdkSSLEngineOptions;
import io.vertx.core.net.JksOptions;
import io.vertx.core.net.OpenSSLEngineOptions;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.core.net.PemTrustOptions;
import io.vertx.core.net.PfxOptions;
import io.vertx.ext.stomp.StompServerOptions;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Opcodes;

/* compiled from: StompServerOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��j\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u001c\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0013\u001a\u008f\u0005\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\t2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010&2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010&2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u00102\u001a\u0004\u0018\u0001032\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u00142\u0010\b\u0002\u00105\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u00107\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010A\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010C\u001a\u0004\u0018\u00010\nH\u0007¢\u0006\u0002\u0010D\u001a\u008d\u0005\u0010E\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\t2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010&2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010&2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u00102\u001a\u0004\u0018\u0001032\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u00142\u0010\b\u0002\u00105\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u00107\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010A\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010C\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010D¨\u0006F"}, d2 = {"StompServerOptions", "Lio/vertx/ext/stomp/StompServerOptions;", "acceptBacklog", "", "clientAuth", "Lio/vertx/core/http/ClientAuth;", "clientAuthRequired", "", "crlPaths", "", "", "crlValues", "Lio/vertx/core/buffer/Buffer;", "enabledCipherSuites", "enabledSecureTransportProtocols", "heartbeat", "Lio/vertx/core/json/JsonObject;", "host", "idleTimeout", "idleTimeoutUnit", "Ljava/util/concurrent/TimeUnit;", "jdkSslEngineOptions", "Lio/vertx/core/net/JdkSSLEngineOptions;", "keyStoreOptions", "Lio/vertx/core/net/JksOptions;", "logActivity", "maxBodyLength", "maxFrameInTransaction", "maxHeaderLength", "maxHeaders", "maxSubscriptionsByClient", "openSslEngineOptions", "Lio/vertx/core/net/OpenSSLEngineOptions;", "pemKeyCertOptions", "Lio/vertx/core/net/PemKeyCertOptions;", "pemTrustOptions", "Lio/vertx/core/net/PemTrustOptions;", "pfxKeyCertOptions", "Lio/vertx/core/net/PfxOptions;", "pfxTrustOptions", RtspHeaders.Values.PORT, "receiveBufferSize", "reuseAddress", "reusePort", "secured", "sendBufferSize", "sendErrorOnNoSubscriptions", "sni", "soLinger", "ssl", "sslHandshakeTimeout", "", "sslHandshakeTimeoutUnit", "supportedVersions", "tcpCork", "tcpFastOpen", "tcpKeepAlive", "tcpNoDelay", "tcpQuickAck", "timeFactor", "trafficClass", "trailingLine", "transactionChunkSize", "trustStoreOptions", "useAlpn", "usePooledBuffers", "websocketBridge", "websocketPath", "(Ljava/lang/Integer;Lio/vertx/core/http/ClientAuth;Ljava/lang/Boolean;Ljava/lang/Iterable;Ljava/lang/Iterable;Ljava/lang/Iterable;Ljava/lang/Iterable;Lio/vertx/core/json/JsonObject;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/concurrent/TimeUnit;Lio/vertx/core/net/JdkSSLEngineOptions;Lio/vertx/core/net/JksOptions;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Lio/vertx/core/net/OpenSSLEngineOptions;Lio/vertx/core/net/PemKeyCertOptions;Lio/vertx/core/net/PemTrustOptions;Lio/vertx/core/net/PfxOptions;Lio/vertx/core/net/PfxOptions;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Long;Ljava/util/concurrent/TimeUnit;Ljava/lang/Iterable;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Integer;Lio/vertx/core/net/JksOptions;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;)Lio/vertx/ext/stomp/StompServerOptions;", "stompServerOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/stomp/StompServerOptionsKt.class */
public final class StompServerOptionsKt {
    @NotNull
    public static /* synthetic */ StompServerOptions stompServerOptionsOf$default(Integer num, ClientAuth clientAuth, Boolean bool, Iterable iterable, Iterable iterable2, Iterable iterable3, Iterable iterable4, JsonObject jsonObject, String str, Integer num2, TimeUnit timeUnit, JdkSSLEngineOptions jdkSSLEngineOptions, JksOptions jksOptions, Boolean bool2, Integer num3, Integer num4, Integer num5, Integer num6, Integer num7, OpenSSLEngineOptions openSSLEngineOptions, PemKeyCertOptions pemKeyCertOptions, PemTrustOptions pemTrustOptions, PfxOptions pfxOptions, PfxOptions pfxOptions2, Integer num8, Integer num9, Boolean bool3, Boolean bool4, Boolean bool5, Integer num10, Boolean bool6, Boolean bool7, Integer num11, Boolean bool8, Long l, TimeUnit timeUnit2, Iterable iterable5, Boolean bool9, Boolean bool10, Boolean bool11, Boolean bool12, Boolean bool13, Integer num12, Integer num13, Boolean bool14, Integer num14, JksOptions jksOptions2, Boolean bool15, Boolean bool16, Boolean bool17, String str2, int i, int i2, Object obj) {
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
            jsonObject = (JsonObject) null;
        }
        if ((i & 256) != 0) {
            str = (String) null;
        }
        if ((i & 512) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 1024) != 0) {
            timeUnit = (TimeUnit) null;
        }
        if ((i & 2048) != 0) {
            jdkSSLEngineOptions = (JdkSSLEngineOptions) null;
        }
        if ((i & 4096) != 0) {
            jksOptions = (JksOptions) null;
        }
        if ((i & 8192) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 16384) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 32768) != 0) {
            num4 = (Integer) null;
        }
        if ((i & 65536) != 0) {
            num5 = (Integer) null;
        }
        if ((i & 131072) != 0) {
            num6 = (Integer) null;
        }
        if ((i & 262144) != 0) {
            num7 = (Integer) null;
        }
        if ((i & Opcodes.ASM8) != 0) {
            openSSLEngineOptions = (OpenSSLEngineOptions) null;
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
            num8 = (Integer) null;
        }
        if ((i & 33554432) != 0) {
            num9 = (Integer) null;
        }
        if ((i & 67108864) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 134217728) != 0) {
            bool4 = (Boolean) null;
        }
        if ((i & 268435456) != 0) {
            bool5 = (Boolean) null;
        }
        if ((i & 536870912) != 0) {
            num10 = (Integer) null;
        }
        if ((i & 1073741824) != 0) {
            bool6 = (Boolean) null;
        }
        if ((i & Integer.MIN_VALUE) != 0) {
            bool7 = (Boolean) null;
        }
        if ((i2 & 1) != 0) {
            num11 = (Integer) null;
        }
        if ((i2 & 2) != 0) {
            bool8 = (Boolean) null;
        }
        if ((i2 & 4) != 0) {
            l = (Long) null;
        }
        if ((i2 & 8) != 0) {
            timeUnit2 = (TimeUnit) null;
        }
        if ((i2 & 16) != 0) {
            iterable5 = (Iterable) null;
        }
        if ((i2 & 32) != 0) {
            bool9 = (Boolean) null;
        }
        if ((i2 & 64) != 0) {
            bool10 = (Boolean) null;
        }
        if ((i2 & 128) != 0) {
            bool11 = (Boolean) null;
        }
        if ((i2 & 256) != 0) {
            bool12 = (Boolean) null;
        }
        if ((i2 & 512) != 0) {
            bool13 = (Boolean) null;
        }
        if ((i2 & 1024) != 0) {
            num12 = (Integer) null;
        }
        if ((i2 & 2048) != 0) {
            num13 = (Integer) null;
        }
        if ((i2 & 4096) != 0) {
            bool14 = (Boolean) null;
        }
        if ((i2 & 8192) != 0) {
            num14 = (Integer) null;
        }
        if ((i2 & 16384) != 0) {
            jksOptions2 = (JksOptions) null;
        }
        if ((i2 & 32768) != 0) {
            bool15 = (Boolean) null;
        }
        if ((i2 & 65536) != 0) {
            bool16 = (Boolean) null;
        }
        if ((i2 & 131072) != 0) {
            bool17 = (Boolean) null;
        }
        if ((i2 & 262144) != 0) {
            str2 = (String) null;
        }
        return stompServerOptionsOf(num, clientAuth, bool, iterable, iterable2, iterable3, iterable4, jsonObject, str, num2, timeUnit, jdkSSLEngineOptions, jksOptions, bool2, num3, num4, num5, num6, num7, openSSLEngineOptions, pemKeyCertOptions, pemTrustOptions, pfxOptions, pfxOptions2, num8, num9, bool3, bool4, bool5, num10, bool6, bool7, num11, bool8, l, timeUnit2, iterable5, bool9, bool10, bool11, bool12, bool13, num12, num13, bool14, num14, jksOptions2, bool15, bool16, bool17, str2);
    }

    @NotNull
    public static final StompServerOptions stompServerOptionsOf(@Nullable Integer acceptBacklog, @Nullable ClientAuth clientAuth, @Nullable Boolean clientAuthRequired, @Nullable Iterable<String> iterable, @Nullable Iterable<? extends Buffer> iterable2, @Nullable Iterable<String> iterable3, @Nullable Iterable<String> iterable4, @Nullable JsonObject heartbeat, @Nullable String host, @Nullable Integer idleTimeout, @Nullable TimeUnit idleTimeoutUnit, @Nullable JdkSSLEngineOptions jdkSslEngineOptions, @Nullable JksOptions keyStoreOptions, @Nullable Boolean logActivity, @Nullable Integer maxBodyLength, @Nullable Integer maxFrameInTransaction, @Nullable Integer maxHeaderLength, @Nullable Integer maxHeaders, @Nullable Integer maxSubscriptionsByClient, @Nullable OpenSSLEngineOptions openSslEngineOptions, @Nullable PemKeyCertOptions pemKeyCertOptions, @Nullable PemTrustOptions pemTrustOptions, @Nullable PfxOptions pfxKeyCertOptions, @Nullable PfxOptions pfxTrustOptions, @Nullable Integer port, @Nullable Integer receiveBufferSize, @Nullable Boolean reuseAddress, @Nullable Boolean reusePort, @Nullable Boolean secured, @Nullable Integer sendBufferSize, @Nullable Boolean sendErrorOnNoSubscriptions, @Nullable Boolean sni, @Nullable Integer soLinger, @Nullable Boolean ssl, @Nullable Long sslHandshakeTimeout, @Nullable TimeUnit sslHandshakeTimeoutUnit, @Nullable Iterable<String> iterable5, @Nullable Boolean tcpCork, @Nullable Boolean tcpFastOpen, @Nullable Boolean tcpKeepAlive, @Nullable Boolean tcpNoDelay, @Nullable Boolean tcpQuickAck, @Nullable Integer timeFactor, @Nullable Integer trafficClass, @Nullable Boolean trailingLine, @Nullable Integer transactionChunkSize, @Nullable JksOptions trustStoreOptions, @Nullable Boolean useAlpn, @Nullable Boolean usePooledBuffers, @Nullable Boolean websocketBridge, @Nullable String websocketPath) {
        StompServerOptions $this$apply = new StompServerOptions();
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
        if (heartbeat != null) {
            $this$apply.setHeartbeat(heartbeat);
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
        if (maxBodyLength != null) {
            $this$apply.setMaxBodyLength(maxBodyLength.intValue());
        }
        if (maxFrameInTransaction != null) {
            $this$apply.setMaxFrameInTransaction(maxFrameInTransaction.intValue());
        }
        if (maxHeaderLength != null) {
            $this$apply.setMaxHeaderLength(maxHeaderLength.intValue());
        }
        if (maxHeaders != null) {
            $this$apply.setMaxHeaders(maxHeaders.intValue());
        }
        if (maxSubscriptionsByClient != null) {
            $this$apply.setMaxSubscriptionsByClient(maxSubscriptionsByClient.intValue());
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
        if (secured != null) {
            $this$apply.setSecured(secured.booleanValue());
        }
        if (sendBufferSize != null) {
            $this$apply.setSendBufferSize(sendBufferSize.intValue());
        }
        if (sendErrorOnNoSubscriptions != null) {
            $this$apply.setSendErrorOnNoSubscriptions(sendErrorOnNoSubscriptions.booleanValue());
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
        if (iterable5 != null) {
            $this$apply.setSupportedVersions(CollectionsKt.toList(iterable5));
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
        if (timeFactor != null) {
            $this$apply.setTimeFactor(timeFactor.intValue());
        }
        if (trafficClass != null) {
            $this$apply.setTrafficClass(trafficClass.intValue());
        }
        if (trailingLine != null) {
            $this$apply.setTrailingLine(trailingLine.booleanValue());
        }
        if (transactionChunkSize != null) {
            $this$apply.setTransactionChunkSize(transactionChunkSize.intValue());
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
        if (websocketBridge != null) {
            $this$apply.setWebsocketBridge(websocketBridge.booleanValue());
        }
        if (websocketPath != null) {
            $this$apply.setWebsocketPath(websocketPath);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "stompServerOptionsOf(acceptBacklog, clientAuth, clientAuthRequired, crlPaths, crlValues, enabledCipherSuites, enabledSecureTransportProtocols, heartbeat, host, idleTimeout, idleTimeoutUnit, jdkSslEngineOptions, keyStoreOptions, logActivity, maxBodyLength, maxFrameInTransaction, maxHeaderLength, maxHeaders, maxSubscriptionsByClient, openSslEngineOptions, pemKeyCertOptions, pemTrustOptions, pfxKeyCertOptions, pfxTrustOptions, port, receiveBufferSize, reuseAddress, reusePort, secured, sendBufferSize, sendErrorOnNoSubscriptions, sni, soLinger, ssl, sslHandshakeTimeout, sslHandshakeTimeoutUnit, supportedVersions, tcpCork, tcpFastOpen, tcpKeepAlive, tcpNoDelay, tcpQuickAck, timeFactor, trafficClass, trailingLine, transactionChunkSize, trustStoreOptions, useAlpn, usePooledBuffers, websocketBridge, websocketPath)"))
    @NotNull
    public static /* synthetic */ StompServerOptions StompServerOptions$default(Integer num, ClientAuth clientAuth, Boolean bool, Iterable iterable, Iterable iterable2, Iterable iterable3, Iterable iterable4, JsonObject jsonObject, String str, Integer num2, TimeUnit timeUnit, JdkSSLEngineOptions jdkSSLEngineOptions, JksOptions jksOptions, Boolean bool2, Integer num3, Integer num4, Integer num5, Integer num6, Integer num7, OpenSSLEngineOptions openSSLEngineOptions, PemKeyCertOptions pemKeyCertOptions, PemTrustOptions pemTrustOptions, PfxOptions pfxOptions, PfxOptions pfxOptions2, Integer num8, Integer num9, Boolean bool3, Boolean bool4, Boolean bool5, Integer num10, Boolean bool6, Boolean bool7, Integer num11, Boolean bool8, Long l, TimeUnit timeUnit2, Iterable iterable5, Boolean bool9, Boolean bool10, Boolean bool11, Boolean bool12, Boolean bool13, Integer num12, Integer num13, Boolean bool14, Integer num14, JksOptions jksOptions2, Boolean bool15, Boolean bool16, Boolean bool17, String str2, int i, int i2, Object obj) {
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
            jsonObject = (JsonObject) null;
        }
        if ((i & 256) != 0) {
            str = (String) null;
        }
        if ((i & 512) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 1024) != 0) {
            timeUnit = (TimeUnit) null;
        }
        if ((i & 2048) != 0) {
            jdkSSLEngineOptions = (JdkSSLEngineOptions) null;
        }
        if ((i & 4096) != 0) {
            jksOptions = (JksOptions) null;
        }
        if ((i & 8192) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 16384) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 32768) != 0) {
            num4 = (Integer) null;
        }
        if ((i & 65536) != 0) {
            num5 = (Integer) null;
        }
        if ((i & 131072) != 0) {
            num6 = (Integer) null;
        }
        if ((i & 262144) != 0) {
            num7 = (Integer) null;
        }
        if ((i & Opcodes.ASM8) != 0) {
            openSSLEngineOptions = (OpenSSLEngineOptions) null;
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
            num8 = (Integer) null;
        }
        if ((i & 33554432) != 0) {
            num9 = (Integer) null;
        }
        if ((i & 67108864) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 134217728) != 0) {
            bool4 = (Boolean) null;
        }
        if ((i & 268435456) != 0) {
            bool5 = (Boolean) null;
        }
        if ((i & 536870912) != 0) {
            num10 = (Integer) null;
        }
        if ((i & 1073741824) != 0) {
            bool6 = (Boolean) null;
        }
        if ((i & Integer.MIN_VALUE) != 0) {
            bool7 = (Boolean) null;
        }
        if ((i2 & 1) != 0) {
            num11 = (Integer) null;
        }
        if ((i2 & 2) != 0) {
            bool8 = (Boolean) null;
        }
        if ((i2 & 4) != 0) {
            l = (Long) null;
        }
        if ((i2 & 8) != 0) {
            timeUnit2 = (TimeUnit) null;
        }
        if ((i2 & 16) != 0) {
            iterable5 = (Iterable) null;
        }
        if ((i2 & 32) != 0) {
            bool9 = (Boolean) null;
        }
        if ((i2 & 64) != 0) {
            bool10 = (Boolean) null;
        }
        if ((i2 & 128) != 0) {
            bool11 = (Boolean) null;
        }
        if ((i2 & 256) != 0) {
            bool12 = (Boolean) null;
        }
        if ((i2 & 512) != 0) {
            bool13 = (Boolean) null;
        }
        if ((i2 & 1024) != 0) {
            num12 = (Integer) null;
        }
        if ((i2 & 2048) != 0) {
            num13 = (Integer) null;
        }
        if ((i2 & 4096) != 0) {
            bool14 = (Boolean) null;
        }
        if ((i2 & 8192) != 0) {
            num14 = (Integer) null;
        }
        if ((i2 & 16384) != 0) {
            jksOptions2 = (JksOptions) null;
        }
        if ((i2 & 32768) != 0) {
            bool15 = (Boolean) null;
        }
        if ((i2 & 65536) != 0) {
            bool16 = (Boolean) null;
        }
        if ((i2 & 131072) != 0) {
            bool17 = (Boolean) null;
        }
        if ((i2 & 262144) != 0) {
            str2 = (String) null;
        }
        return StompServerOptions(num, clientAuth, bool, iterable, iterable2, iterable3, iterable4, jsonObject, str, num2, timeUnit, jdkSSLEngineOptions, jksOptions, bool2, num3, num4, num5, num6, num7, openSSLEngineOptions, pemKeyCertOptions, pemTrustOptions, pfxOptions, pfxOptions2, num8, num9, bool3, bool4, bool5, num10, bool6, bool7, num11, bool8, l, timeUnit2, iterable5, bool9, bool10, bool11, bool12, bool13, num12, num13, bool14, num14, jksOptions2, bool15, bool16, bool17, str2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "stompServerOptionsOf(acceptBacklog, clientAuth, clientAuthRequired, crlPaths, crlValues, enabledCipherSuites, enabledSecureTransportProtocols, heartbeat, host, idleTimeout, idleTimeoutUnit, jdkSslEngineOptions, keyStoreOptions, logActivity, maxBodyLength, maxFrameInTransaction, maxHeaderLength, maxHeaders, maxSubscriptionsByClient, openSslEngineOptions, pemKeyCertOptions, pemTrustOptions, pfxKeyCertOptions, pfxTrustOptions, port, receiveBufferSize, reuseAddress, reusePort, secured, sendBufferSize, sendErrorOnNoSubscriptions, sni, soLinger, ssl, sslHandshakeTimeout, sslHandshakeTimeoutUnit, supportedVersions, tcpCork, tcpFastOpen, tcpKeepAlive, tcpNoDelay, tcpQuickAck, timeFactor, trafficClass, trailingLine, transactionChunkSize, trustStoreOptions, useAlpn, usePooledBuffers, websocketBridge, websocketPath)"))
    @NotNull
    public static final StompServerOptions StompServerOptions(@Nullable Integer acceptBacklog, @Nullable ClientAuth clientAuth, @Nullable Boolean clientAuthRequired, @Nullable Iterable<String> iterable, @Nullable Iterable<? extends Buffer> iterable2, @Nullable Iterable<String> iterable3, @Nullable Iterable<String> iterable4, @Nullable JsonObject heartbeat, @Nullable String host, @Nullable Integer idleTimeout, @Nullable TimeUnit idleTimeoutUnit, @Nullable JdkSSLEngineOptions jdkSslEngineOptions, @Nullable JksOptions keyStoreOptions, @Nullable Boolean logActivity, @Nullable Integer maxBodyLength, @Nullable Integer maxFrameInTransaction, @Nullable Integer maxHeaderLength, @Nullable Integer maxHeaders, @Nullable Integer maxSubscriptionsByClient, @Nullable OpenSSLEngineOptions openSslEngineOptions, @Nullable PemKeyCertOptions pemKeyCertOptions, @Nullable PemTrustOptions pemTrustOptions, @Nullable PfxOptions pfxKeyCertOptions, @Nullable PfxOptions pfxTrustOptions, @Nullable Integer port, @Nullable Integer receiveBufferSize, @Nullable Boolean reuseAddress, @Nullable Boolean reusePort, @Nullable Boolean secured, @Nullable Integer sendBufferSize, @Nullable Boolean sendErrorOnNoSubscriptions, @Nullable Boolean sni, @Nullable Integer soLinger, @Nullable Boolean ssl, @Nullable Long sslHandshakeTimeout, @Nullable TimeUnit sslHandshakeTimeoutUnit, @Nullable Iterable<String> iterable5, @Nullable Boolean tcpCork, @Nullable Boolean tcpFastOpen, @Nullable Boolean tcpKeepAlive, @Nullable Boolean tcpNoDelay, @Nullable Boolean tcpQuickAck, @Nullable Integer timeFactor, @Nullable Integer trafficClass, @Nullable Boolean trailingLine, @Nullable Integer transactionChunkSize, @Nullable JksOptions trustStoreOptions, @Nullable Boolean useAlpn, @Nullable Boolean usePooledBuffers, @Nullable Boolean websocketBridge, @Nullable String websocketPath) {
        StompServerOptions $this$apply = new StompServerOptions();
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
        if (heartbeat != null) {
            $this$apply.setHeartbeat(heartbeat);
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
        if (maxBodyLength != null) {
            $this$apply.setMaxBodyLength(maxBodyLength.intValue());
        }
        if (maxFrameInTransaction != null) {
            $this$apply.setMaxFrameInTransaction(maxFrameInTransaction.intValue());
        }
        if (maxHeaderLength != null) {
            $this$apply.setMaxHeaderLength(maxHeaderLength.intValue());
        }
        if (maxHeaders != null) {
            $this$apply.setMaxHeaders(maxHeaders.intValue());
        }
        if (maxSubscriptionsByClient != null) {
            $this$apply.setMaxSubscriptionsByClient(maxSubscriptionsByClient.intValue());
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
        if (secured != null) {
            $this$apply.setSecured(secured.booleanValue());
        }
        if (sendBufferSize != null) {
            $this$apply.setSendBufferSize(sendBufferSize.intValue());
        }
        if (sendErrorOnNoSubscriptions != null) {
            $this$apply.setSendErrorOnNoSubscriptions(sendErrorOnNoSubscriptions.booleanValue());
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
        if (iterable5 != null) {
            $this$apply.setSupportedVersions(CollectionsKt.toList(iterable5));
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
        if (timeFactor != null) {
            $this$apply.setTimeFactor(timeFactor.intValue());
        }
        if (trafficClass != null) {
            $this$apply.setTrafficClass(trafficClass.intValue());
        }
        if (trailingLine != null) {
            $this$apply.setTrailingLine(trailingLine.booleanValue());
        }
        if (transactionChunkSize != null) {
            $this$apply.setTransactionChunkSize(transactionChunkSize.intValue());
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
        if (websocketBridge != null) {
            $this$apply.setWebsocketBridge(websocketBridge.booleanValue());
        }
        if (websocketPath != null) {
            $this$apply.setWebsocketPath(websocketPath);
        }
        return $this$apply;
    }
}
