package io.vertx.kotlin.core.http;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.ClientAuth;
import io.vertx.core.http.Http2Settings;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.net.JdkSSLEngineOptions;
import io.vertx.core.net.JksOptions;
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

/* compiled from: HttpServerOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��r\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0015\u001a½\u0006\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00072\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00072\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00072\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00072\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010*\u001a\u0004\u0018\u00010+2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010-2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010/2\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u00104\u001a\u0004\u0018\u0001052\n\b\u0002\u00106\u001a\u0004\u0018\u0001052\n\b\u0002\u00107\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010?\u001a\u0004\u0018\u00010@2\n\b\u0002\u0010A\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010C\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010D\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010E\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010F\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010G\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010H\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010I\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010J\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010K\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010L\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010M\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010N\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00072\n\b\u0002\u0010O\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010P\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010R\u001a\u0004\u0018\u00010\u000fH\u0007¢\u0006\u0002\u0010S\u001a»\u0006\u0010T\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00072\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00072\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00072\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00072\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010*\u001a\u0004\u0018\u00010+2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010-2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010/2\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u00104\u001a\u0004\u0018\u0001052\n\b\u0002\u00106\u001a\u0004\u0018\u0001052\n\b\u0002\u00107\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010?\u001a\u0004\u0018\u00010@2\n\b\u0002\u0010A\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010C\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010D\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010E\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010F\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010G\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010H\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010I\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010J\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010K\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010L\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010M\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010N\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00072\n\b\u0002\u0010O\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010P\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010R\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010S¨\u0006U"}, d2 = {"HttpServerOptions", "Lio/vertx/core/http/HttpServerOptions;", "acceptBacklog", "", "acceptUnmaskedFrames", "", "alpnVersions", "", "Lio/vertx/core/http/HttpVersion;", "clientAuth", "Lio/vertx/core/http/ClientAuth;", "clientAuthRequired", "compressionLevel", "compressionSupported", "crlPaths", "", "crlValues", "Lio/vertx/core/buffer/Buffer;", "decoderInitialBufferSize", "decompressionSupported", "enabledCipherSuites", "enabledSecureTransportProtocols", "handle100ContinueAutomatically", "host", "http2ConnectionWindowSize", "idleTimeout", "idleTimeoutUnit", "Ljava/util/concurrent/TimeUnit;", "initialSettings", "Lio/vertx/core/http/Http2Settings;", "jdkSslEngineOptions", "Lio/vertx/core/net/JdkSSLEngineOptions;", "keyStoreOptions", "Lio/vertx/core/net/JksOptions;", "logActivity", "maxChunkSize", "maxHeaderSize", "maxInitialLineLength", "maxWebSocketFrameSize", "maxWebSocketMessageSize", "maxWebsocketFrameSize", "maxWebsocketMessageSize", "openSslEngineOptions", "Lio/vertx/core/net/OpenSSLEngineOptions;", "pemKeyCertOptions", "Lio/vertx/core/net/PemKeyCertOptions;", "pemTrustOptions", "Lio/vertx/core/net/PemTrustOptions;", "perFrameWebSocketCompressionSupported", "perFrameWebsocketCompressionSupported", "perMessageWebSocketCompressionSupported", "perMessageWebsocketCompressionSupported", "pfxKeyCertOptions", "Lio/vertx/core/net/PfxOptions;", "pfxTrustOptions", RtspHeaders.Values.PORT, "receiveBufferSize", "reuseAddress", "reusePort", "sendBufferSize", "sni", "soLinger", "ssl", "sslHandshakeTimeout", "", "sslHandshakeTimeoutUnit", "tcpCork", "tcpFastOpen", "tcpKeepAlive", "tcpNoDelay", "tcpQuickAck", "trafficClass", "trustStoreOptions", "useAlpn", "usePooledBuffers", "webSocketAllowServerNoContext", "webSocketCompressionLevel", "webSocketPreferredClientNoContext", "webSocketSubProtocols", "websocketAllowServerNoContext", "websocketCompressionLevel", "websocketPreferredClientNoContext", "websocketSubProtocols", "(Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Iterable;Lio/vertx/core/http/ClientAuth;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Iterable;Ljava/lang/Iterable;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Iterable;Ljava/lang/Iterable;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/concurrent/TimeUnit;Lio/vertx/core/http/Http2Settings;Lio/vertx/core/net/JdkSSLEngineOptions;Lio/vertx/core/net/JksOptions;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Lio/vertx/core/net/OpenSSLEngineOptions;Lio/vertx/core/net/PemKeyCertOptions;Lio/vertx/core/net/PemTrustOptions;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Lio/vertx/core/net/PfxOptions;Lio/vertx/core/net/PfxOptions;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Long;Ljava/util/concurrent/TimeUnit;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Lio/vertx/core/net/JksOptions;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Iterable;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;)Lio/vertx/core/http/HttpServerOptions;", "httpServerOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/http/HttpServerOptionsKt.class */
public final class HttpServerOptionsKt {
    @NotNull
    public static /* synthetic */ HttpServerOptions httpServerOptionsOf$default(Integer num, Boolean bool, Iterable iterable, ClientAuth clientAuth, Boolean bool2, Integer num2, Boolean bool3, Iterable iterable2, Iterable iterable3, Integer num3, Boolean bool4, Iterable iterable4, Iterable iterable5, Boolean bool5, String str, Integer num4, Integer num5, TimeUnit timeUnit, Http2Settings http2Settings, JdkSSLEngineOptions jdkSSLEngineOptions, JksOptions jksOptions, Boolean bool6, Integer num6, Integer num7, Integer num8, Integer num9, Integer num10, Integer num11, Integer num12, OpenSSLEngineOptions openSSLEngineOptions, PemKeyCertOptions pemKeyCertOptions, PemTrustOptions pemTrustOptions, Boolean bool7, Boolean bool8, Boolean bool9, Boolean bool10, PfxOptions pfxOptions, PfxOptions pfxOptions2, Integer num13, Integer num14, Boolean bool11, Boolean bool12, Integer num15, Boolean bool13, Integer num16, Boolean bool14, Long l, TimeUnit timeUnit2, Boolean bool15, Boolean bool16, Boolean bool17, Boolean bool18, Boolean bool19, Integer num17, JksOptions jksOptions2, Boolean bool20, Boolean bool21, Boolean bool22, Integer num18, Boolean bool23, Iterable iterable6, Boolean bool24, Integer num19, Boolean bool25, String str2, int i, int i2, int i3, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 4) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 8) != 0) {
            clientAuth = (ClientAuth) null;
        }
        if ((i & 16) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 32) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 64) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 128) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 256) != 0) {
            iterable3 = (Iterable) null;
        }
        if ((i & 512) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 1024) != 0) {
            bool4 = (Boolean) null;
        }
        if ((i & 2048) != 0) {
            iterable4 = (Iterable) null;
        }
        if ((i & 4096) != 0) {
            iterable5 = (Iterable) null;
        }
        if ((i & 8192) != 0) {
            bool5 = (Boolean) null;
        }
        if ((i & 16384) != 0) {
            str = (String) null;
        }
        if ((i & 32768) != 0) {
            num4 = (Integer) null;
        }
        if ((i & 65536) != 0) {
            num5 = (Integer) null;
        }
        if ((i & 131072) != 0) {
            timeUnit = (TimeUnit) null;
        }
        if ((i & 262144) != 0) {
            http2Settings = (Http2Settings) null;
        }
        if ((i & Opcodes.ASM8) != 0) {
            jdkSSLEngineOptions = (JdkSSLEngineOptions) null;
        }
        if ((i & 1048576) != 0) {
            jksOptions = (JksOptions) null;
        }
        if ((i & 2097152) != 0) {
            bool6 = (Boolean) null;
        }
        if ((i & 4194304) != 0) {
            num6 = (Integer) null;
        }
        if ((i & 8388608) != 0) {
            num7 = (Integer) null;
        }
        if ((i & 16777216) != 0) {
            num8 = (Integer) null;
        }
        if ((i & 33554432) != 0) {
            num9 = (Integer) null;
        }
        if ((i & 67108864) != 0) {
            num10 = (Integer) null;
        }
        if ((i & 134217728) != 0) {
            num11 = (Integer) null;
        }
        if ((i & 268435456) != 0) {
            num12 = (Integer) null;
        }
        if ((i & 536870912) != 0) {
            openSSLEngineOptions = (OpenSSLEngineOptions) null;
        }
        if ((i & 1073741824) != 0) {
            pemKeyCertOptions = (PemKeyCertOptions) null;
        }
        if ((i & Integer.MIN_VALUE) != 0) {
            pemTrustOptions = (PemTrustOptions) null;
        }
        if ((i2 & 1) != 0) {
            bool7 = (Boolean) null;
        }
        if ((i2 & 2) != 0) {
            bool8 = (Boolean) null;
        }
        if ((i2 & 4) != 0) {
            bool9 = (Boolean) null;
        }
        if ((i2 & 8) != 0) {
            bool10 = (Boolean) null;
        }
        if ((i2 & 16) != 0) {
            pfxOptions = (PfxOptions) null;
        }
        if ((i2 & 32) != 0) {
            pfxOptions2 = (PfxOptions) null;
        }
        if ((i2 & 64) != 0) {
            num13 = (Integer) null;
        }
        if ((i2 & 128) != 0) {
            num14 = (Integer) null;
        }
        if ((i2 & 256) != 0) {
            bool11 = (Boolean) null;
        }
        if ((i2 & 512) != 0) {
            bool12 = (Boolean) null;
        }
        if ((i2 & 1024) != 0) {
            num15 = (Integer) null;
        }
        if ((i2 & 2048) != 0) {
            bool13 = (Boolean) null;
        }
        if ((i2 & 4096) != 0) {
            num16 = (Integer) null;
        }
        if ((i2 & 8192) != 0) {
            bool14 = (Boolean) null;
        }
        if ((i2 & 16384) != 0) {
            l = (Long) null;
        }
        if ((i2 & 32768) != 0) {
            timeUnit2 = (TimeUnit) null;
        }
        if ((i2 & 65536) != 0) {
            bool15 = (Boolean) null;
        }
        if ((i2 & 131072) != 0) {
            bool16 = (Boolean) null;
        }
        if ((i2 & 262144) != 0) {
            bool17 = (Boolean) null;
        }
        if ((i2 & Opcodes.ASM8) != 0) {
            bool18 = (Boolean) null;
        }
        if ((i2 & 1048576) != 0) {
            bool19 = (Boolean) null;
        }
        if ((i2 & 2097152) != 0) {
            num17 = (Integer) null;
        }
        if ((i2 & 4194304) != 0) {
            jksOptions2 = (JksOptions) null;
        }
        if ((i2 & 8388608) != 0) {
            bool20 = (Boolean) null;
        }
        if ((i2 & 16777216) != 0) {
            bool21 = (Boolean) null;
        }
        if ((i2 & 33554432) != 0) {
            bool22 = (Boolean) null;
        }
        if ((i2 & 67108864) != 0) {
            num18 = (Integer) null;
        }
        if ((i2 & 134217728) != 0) {
            bool23 = (Boolean) null;
        }
        if ((i2 & 268435456) != 0) {
            iterable6 = (Iterable) null;
        }
        if ((i2 & 536870912) != 0) {
            bool24 = (Boolean) null;
        }
        if ((i2 & 1073741824) != 0) {
            num19 = (Integer) null;
        }
        if ((i2 & Integer.MIN_VALUE) != 0) {
            bool25 = (Boolean) null;
        }
        if ((i3 & 1) != 0) {
            str2 = (String) null;
        }
        return httpServerOptionsOf(num, bool, iterable, clientAuth, bool2, num2, bool3, iterable2, iterable3, num3, bool4, iterable4, iterable5, bool5, str, num4, num5, timeUnit, http2Settings, jdkSSLEngineOptions, jksOptions, bool6, num6, num7, num8, num9, num10, num11, num12, openSSLEngineOptions, pemKeyCertOptions, pemTrustOptions, bool7, bool8, bool9, bool10, pfxOptions, pfxOptions2, num13, num14, bool11, bool12, num15, bool13, num16, bool14, l, timeUnit2, bool15, bool16, bool17, bool18, bool19, num17, jksOptions2, bool20, bool21, bool22, num18, bool23, iterable6, bool24, num19, bool25, str2);
    }

    @NotNull
    public static final HttpServerOptions httpServerOptionsOf(@Nullable Integer acceptBacklog, @Nullable Boolean acceptUnmaskedFrames, @Nullable Iterable<? extends HttpVersion> iterable, @Nullable ClientAuth clientAuth, @Nullable Boolean clientAuthRequired, @Nullable Integer compressionLevel, @Nullable Boolean compressionSupported, @Nullable Iterable<String> iterable2, @Nullable Iterable<? extends Buffer> iterable3, @Nullable Integer decoderInitialBufferSize, @Nullable Boolean decompressionSupported, @Nullable Iterable<String> iterable4, @Nullable Iterable<String> iterable5, @Nullable Boolean handle100ContinueAutomatically, @Nullable String host, @Nullable Integer http2ConnectionWindowSize, @Nullable Integer idleTimeout, @Nullable TimeUnit idleTimeoutUnit, @Nullable Http2Settings initialSettings, @Nullable JdkSSLEngineOptions jdkSslEngineOptions, @Nullable JksOptions keyStoreOptions, @Nullable Boolean logActivity, @Nullable Integer maxChunkSize, @Nullable Integer maxHeaderSize, @Nullable Integer maxInitialLineLength, @Nullable Integer maxWebSocketFrameSize, @Nullable Integer maxWebSocketMessageSize, @Nullable Integer maxWebsocketFrameSize, @Nullable Integer maxWebsocketMessageSize, @Nullable OpenSSLEngineOptions openSslEngineOptions, @Nullable PemKeyCertOptions pemKeyCertOptions, @Nullable PemTrustOptions pemTrustOptions, @Nullable Boolean perFrameWebSocketCompressionSupported, @Nullable Boolean perFrameWebsocketCompressionSupported, @Nullable Boolean perMessageWebSocketCompressionSupported, @Nullable Boolean perMessageWebsocketCompressionSupported, @Nullable PfxOptions pfxKeyCertOptions, @Nullable PfxOptions pfxTrustOptions, @Nullable Integer port, @Nullable Integer receiveBufferSize, @Nullable Boolean reuseAddress, @Nullable Boolean reusePort, @Nullable Integer sendBufferSize, @Nullable Boolean sni, @Nullable Integer soLinger, @Nullable Boolean ssl, @Nullable Long sslHandshakeTimeout, @Nullable TimeUnit sslHandshakeTimeoutUnit, @Nullable Boolean tcpCork, @Nullable Boolean tcpFastOpen, @Nullable Boolean tcpKeepAlive, @Nullable Boolean tcpNoDelay, @Nullable Boolean tcpQuickAck, @Nullable Integer trafficClass, @Nullable JksOptions trustStoreOptions, @Nullable Boolean useAlpn, @Nullable Boolean usePooledBuffers, @Nullable Boolean webSocketAllowServerNoContext, @Nullable Integer webSocketCompressionLevel, @Nullable Boolean webSocketPreferredClientNoContext, @Nullable Iterable<String> iterable6, @Nullable Boolean websocketAllowServerNoContext, @Nullable Integer websocketCompressionLevel, @Nullable Boolean websocketPreferredClientNoContext, @Nullable String websocketSubProtocols) throws NullPointerException {
        HttpServerOptions $this$apply = new HttpServerOptions();
        if (acceptBacklog != null) {
            $this$apply.setAcceptBacklog(acceptBacklog.intValue());
        }
        if (acceptUnmaskedFrames != null) {
            $this$apply.setAcceptUnmaskedFrames(acceptUnmaskedFrames.booleanValue());
        }
        if (iterable != null) {
            $this$apply.setAlpnVersions(CollectionsKt.toList(iterable));
        }
        if (clientAuth != null) {
            $this$apply.setClientAuth(clientAuth);
        }
        if (clientAuthRequired != null) {
            $this$apply.setClientAuthRequired(clientAuthRequired.booleanValue());
        }
        if (compressionLevel != null) {
            $this$apply.setCompressionLevel(compressionLevel.intValue());
        }
        if (compressionSupported != null) {
            $this$apply.setCompressionSupported(compressionSupported.booleanValue());
        }
        if (iterable2 != null) {
            for (String item : iterable2) {
                $this$apply.addCrlPath(item);
            }
        }
        if (iterable3 != null) {
            for (Buffer item2 : iterable3) {
                $this$apply.addCrlValue(item2);
            }
        }
        if (decoderInitialBufferSize != null) {
            $this$apply.setDecoderInitialBufferSize(decoderInitialBufferSize.intValue());
        }
        if (decompressionSupported != null) {
            $this$apply.setDecompressionSupported(decompressionSupported.booleanValue());
        }
        if (iterable4 != null) {
            for (String item3 : iterable4) {
                $this$apply.addEnabledCipherSuite(item3);
            }
        }
        if (iterable5 != null) {
            $this$apply.setEnabledSecureTransportProtocols(CollectionsKt.toSet(iterable5));
        }
        if (handle100ContinueAutomatically != null) {
            $this$apply.setHandle100ContinueAutomatically(handle100ContinueAutomatically.booleanValue());
        }
        if (host != null) {
            $this$apply.setHost(host);
        }
        if (http2ConnectionWindowSize != null) {
            $this$apply.setHttp2ConnectionWindowSize(http2ConnectionWindowSize.intValue());
        }
        if (idleTimeout != null) {
            $this$apply.setIdleTimeout(idleTimeout.intValue());
        }
        if (idleTimeoutUnit != null) {
            $this$apply.setIdleTimeoutUnit(idleTimeoutUnit);
        }
        if (initialSettings != null) {
            $this$apply.setInitialSettings(initialSettings);
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
        if (maxChunkSize != null) {
            $this$apply.setMaxChunkSize(maxChunkSize.intValue());
        }
        if (maxHeaderSize != null) {
            $this$apply.setMaxHeaderSize(maxHeaderSize.intValue());
        }
        if (maxInitialLineLength != null) {
            $this$apply.setMaxInitialLineLength(maxInitialLineLength.intValue());
        }
        if (maxWebSocketFrameSize != null) {
            $this$apply.setMaxWebSocketFrameSize(maxWebSocketFrameSize.intValue());
        }
        if (maxWebSocketMessageSize != null) {
            $this$apply.setMaxWebSocketMessageSize(maxWebSocketMessageSize.intValue());
        }
        if (maxWebsocketFrameSize != null) {
            $this$apply.setMaxWebsocketFrameSize(maxWebsocketFrameSize.intValue());
        }
        if (maxWebsocketMessageSize != null) {
            $this$apply.setMaxWebsocketMessageSize(maxWebsocketMessageSize.intValue());
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
        if (perFrameWebSocketCompressionSupported != null) {
            $this$apply.setPerFrameWebSocketCompressionSupported(perFrameWebSocketCompressionSupported.booleanValue());
        }
        if (perFrameWebsocketCompressionSupported != null) {
            $this$apply.setPerFrameWebsocketCompressionSupported(perFrameWebsocketCompressionSupported.booleanValue());
        }
        if (perMessageWebSocketCompressionSupported != null) {
            $this$apply.setPerMessageWebSocketCompressionSupported(perMessageWebSocketCompressionSupported.booleanValue());
        }
        if (perMessageWebsocketCompressionSupported != null) {
            $this$apply.setPerMessageWebsocketCompressionSupported(perMessageWebsocketCompressionSupported.booleanValue());
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
        if (webSocketAllowServerNoContext != null) {
            $this$apply.setWebSocketAllowServerNoContext(webSocketAllowServerNoContext.booleanValue());
        }
        if (webSocketCompressionLevel != null) {
            $this$apply.setWebSocketCompressionLevel(webSocketCompressionLevel.intValue());
        }
        if (webSocketPreferredClientNoContext != null) {
            $this$apply.setWebSocketPreferredClientNoContext(webSocketPreferredClientNoContext.booleanValue());
        }
        if (iterable6 != null) {
            $this$apply.setWebSocketSubProtocols(CollectionsKt.toList(iterable6));
        }
        if (websocketAllowServerNoContext != null) {
            $this$apply.setWebsocketAllowServerNoContext(websocketAllowServerNoContext.booleanValue());
        }
        if (websocketCompressionLevel != null) {
            $this$apply.setWebsocketCompressionLevel(websocketCompressionLevel.intValue());
        }
        if (websocketPreferredClientNoContext != null) {
            $this$apply.setWebsocketPreferredClientNoContext(websocketPreferredClientNoContext.booleanValue());
        }
        if (websocketSubProtocols != null) {
            $this$apply.setWebsocketSubProtocols(websocketSubProtocols);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "httpServerOptionsOf(acceptBacklog, acceptUnmaskedFrames, alpnVersions, clientAuth, clientAuthRequired, compressionLevel, compressionSupported, crlPaths, crlValues, decoderInitialBufferSize, decompressionSupported, enabledCipherSuites, enabledSecureTransportProtocols, handle100ContinueAutomatically, host, http2ConnectionWindowSize, idleTimeout, idleTimeoutUnit, initialSettings, jdkSslEngineOptions, keyStoreOptions, logActivity, maxChunkSize, maxHeaderSize, maxInitialLineLength, maxWebSocketFrameSize, maxWebSocketMessageSize, maxWebsocketFrameSize, maxWebsocketMessageSize, openSslEngineOptions, pemKeyCertOptions, pemTrustOptions, perFrameWebSocketCompressionSupported, perFrameWebsocketCompressionSupported, perMessageWebSocketCompressionSupported, perMessageWebsocketCompressionSupported, pfxKeyCertOptions, pfxTrustOptions, port, receiveBufferSize, reuseAddress, reusePort, sendBufferSize, sni, soLinger, ssl, sslHandshakeTimeout, sslHandshakeTimeoutUnit, tcpCork, tcpFastOpen, tcpKeepAlive, tcpNoDelay, tcpQuickAck, trafficClass, trustStoreOptions, useAlpn, usePooledBuffers, webSocketAllowServerNoContext, webSocketCompressionLevel, webSocketPreferredClientNoContext, webSocketSubProtocols, websocketAllowServerNoContext, websocketCompressionLevel, websocketPreferredClientNoContext, websocketSubProtocols)"))
    @NotNull
    public static /* synthetic */ HttpServerOptions HttpServerOptions$default(Integer num, Boolean bool, Iterable iterable, ClientAuth clientAuth, Boolean bool2, Integer num2, Boolean bool3, Iterable iterable2, Iterable iterable3, Integer num3, Boolean bool4, Iterable iterable4, Iterable iterable5, Boolean bool5, String str, Integer num4, Integer num5, TimeUnit timeUnit, Http2Settings http2Settings, JdkSSLEngineOptions jdkSSLEngineOptions, JksOptions jksOptions, Boolean bool6, Integer num6, Integer num7, Integer num8, Integer num9, Integer num10, Integer num11, Integer num12, OpenSSLEngineOptions openSSLEngineOptions, PemKeyCertOptions pemKeyCertOptions, PemTrustOptions pemTrustOptions, Boolean bool7, Boolean bool8, Boolean bool9, Boolean bool10, PfxOptions pfxOptions, PfxOptions pfxOptions2, Integer num13, Integer num14, Boolean bool11, Boolean bool12, Integer num15, Boolean bool13, Integer num16, Boolean bool14, Long l, TimeUnit timeUnit2, Boolean bool15, Boolean bool16, Boolean bool17, Boolean bool18, Boolean bool19, Integer num17, JksOptions jksOptions2, Boolean bool20, Boolean bool21, Boolean bool22, Integer num18, Boolean bool23, Iterable iterable6, Boolean bool24, Integer num19, Boolean bool25, String str2, int i, int i2, int i3, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 4) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 8) != 0) {
            clientAuth = (ClientAuth) null;
        }
        if ((i & 16) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 32) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 64) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 128) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 256) != 0) {
            iterable3 = (Iterable) null;
        }
        if ((i & 512) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 1024) != 0) {
            bool4 = (Boolean) null;
        }
        if ((i & 2048) != 0) {
            iterable4 = (Iterable) null;
        }
        if ((i & 4096) != 0) {
            iterable5 = (Iterable) null;
        }
        if ((i & 8192) != 0) {
            bool5 = (Boolean) null;
        }
        if ((i & 16384) != 0) {
            str = (String) null;
        }
        if ((i & 32768) != 0) {
            num4 = (Integer) null;
        }
        if ((i & 65536) != 0) {
            num5 = (Integer) null;
        }
        if ((i & 131072) != 0) {
            timeUnit = (TimeUnit) null;
        }
        if ((i & 262144) != 0) {
            http2Settings = (Http2Settings) null;
        }
        if ((i & Opcodes.ASM8) != 0) {
            jdkSSLEngineOptions = (JdkSSLEngineOptions) null;
        }
        if ((i & 1048576) != 0) {
            jksOptions = (JksOptions) null;
        }
        if ((i & 2097152) != 0) {
            bool6 = (Boolean) null;
        }
        if ((i & 4194304) != 0) {
            num6 = (Integer) null;
        }
        if ((i & 8388608) != 0) {
            num7 = (Integer) null;
        }
        if ((i & 16777216) != 0) {
            num8 = (Integer) null;
        }
        if ((i & 33554432) != 0) {
            num9 = (Integer) null;
        }
        if ((i & 67108864) != 0) {
            num10 = (Integer) null;
        }
        if ((i & 134217728) != 0) {
            num11 = (Integer) null;
        }
        if ((i & 268435456) != 0) {
            num12 = (Integer) null;
        }
        if ((i & 536870912) != 0) {
            openSSLEngineOptions = (OpenSSLEngineOptions) null;
        }
        if ((i & 1073741824) != 0) {
            pemKeyCertOptions = (PemKeyCertOptions) null;
        }
        if ((i & Integer.MIN_VALUE) != 0) {
            pemTrustOptions = (PemTrustOptions) null;
        }
        if ((i2 & 1) != 0) {
            bool7 = (Boolean) null;
        }
        if ((i2 & 2) != 0) {
            bool8 = (Boolean) null;
        }
        if ((i2 & 4) != 0) {
            bool9 = (Boolean) null;
        }
        if ((i2 & 8) != 0) {
            bool10 = (Boolean) null;
        }
        if ((i2 & 16) != 0) {
            pfxOptions = (PfxOptions) null;
        }
        if ((i2 & 32) != 0) {
            pfxOptions2 = (PfxOptions) null;
        }
        if ((i2 & 64) != 0) {
            num13 = (Integer) null;
        }
        if ((i2 & 128) != 0) {
            num14 = (Integer) null;
        }
        if ((i2 & 256) != 0) {
            bool11 = (Boolean) null;
        }
        if ((i2 & 512) != 0) {
            bool12 = (Boolean) null;
        }
        if ((i2 & 1024) != 0) {
            num15 = (Integer) null;
        }
        if ((i2 & 2048) != 0) {
            bool13 = (Boolean) null;
        }
        if ((i2 & 4096) != 0) {
            num16 = (Integer) null;
        }
        if ((i2 & 8192) != 0) {
            bool14 = (Boolean) null;
        }
        if ((i2 & 16384) != 0) {
            l = (Long) null;
        }
        if ((i2 & 32768) != 0) {
            timeUnit2 = (TimeUnit) null;
        }
        if ((i2 & 65536) != 0) {
            bool15 = (Boolean) null;
        }
        if ((i2 & 131072) != 0) {
            bool16 = (Boolean) null;
        }
        if ((i2 & 262144) != 0) {
            bool17 = (Boolean) null;
        }
        if ((i2 & Opcodes.ASM8) != 0) {
            bool18 = (Boolean) null;
        }
        if ((i2 & 1048576) != 0) {
            bool19 = (Boolean) null;
        }
        if ((i2 & 2097152) != 0) {
            num17 = (Integer) null;
        }
        if ((i2 & 4194304) != 0) {
            jksOptions2 = (JksOptions) null;
        }
        if ((i2 & 8388608) != 0) {
            bool20 = (Boolean) null;
        }
        if ((i2 & 16777216) != 0) {
            bool21 = (Boolean) null;
        }
        if ((i2 & 33554432) != 0) {
            bool22 = (Boolean) null;
        }
        if ((i2 & 67108864) != 0) {
            num18 = (Integer) null;
        }
        if ((i2 & 134217728) != 0) {
            bool23 = (Boolean) null;
        }
        if ((i2 & 268435456) != 0) {
            iterable6 = (Iterable) null;
        }
        if ((i2 & 536870912) != 0) {
            bool24 = (Boolean) null;
        }
        if ((i2 & 1073741824) != 0) {
            num19 = (Integer) null;
        }
        if ((i2 & Integer.MIN_VALUE) != 0) {
            bool25 = (Boolean) null;
        }
        if ((i3 & 1) != 0) {
            str2 = (String) null;
        }
        return HttpServerOptions(num, bool, iterable, clientAuth, bool2, num2, bool3, iterable2, iterable3, num3, bool4, iterable4, iterable5, bool5, str, num4, num5, timeUnit, http2Settings, jdkSSLEngineOptions, jksOptions, bool6, num6, num7, num8, num9, num10, num11, num12, openSSLEngineOptions, pemKeyCertOptions, pemTrustOptions, bool7, bool8, bool9, bool10, pfxOptions, pfxOptions2, num13, num14, bool11, bool12, num15, bool13, num16, bool14, l, timeUnit2, bool15, bool16, bool17, bool18, bool19, num17, jksOptions2, bool20, bool21, bool22, num18, bool23, iterable6, bool24, num19, bool25, str2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "httpServerOptionsOf(acceptBacklog, acceptUnmaskedFrames, alpnVersions, clientAuth, clientAuthRequired, compressionLevel, compressionSupported, crlPaths, crlValues, decoderInitialBufferSize, decompressionSupported, enabledCipherSuites, enabledSecureTransportProtocols, handle100ContinueAutomatically, host, http2ConnectionWindowSize, idleTimeout, idleTimeoutUnit, initialSettings, jdkSslEngineOptions, keyStoreOptions, logActivity, maxChunkSize, maxHeaderSize, maxInitialLineLength, maxWebSocketFrameSize, maxWebSocketMessageSize, maxWebsocketFrameSize, maxWebsocketMessageSize, openSslEngineOptions, pemKeyCertOptions, pemTrustOptions, perFrameWebSocketCompressionSupported, perFrameWebsocketCompressionSupported, perMessageWebSocketCompressionSupported, perMessageWebsocketCompressionSupported, pfxKeyCertOptions, pfxTrustOptions, port, receiveBufferSize, reuseAddress, reusePort, sendBufferSize, sni, soLinger, ssl, sslHandshakeTimeout, sslHandshakeTimeoutUnit, tcpCork, tcpFastOpen, tcpKeepAlive, tcpNoDelay, tcpQuickAck, trafficClass, trustStoreOptions, useAlpn, usePooledBuffers, webSocketAllowServerNoContext, webSocketCompressionLevel, webSocketPreferredClientNoContext, webSocketSubProtocols, websocketAllowServerNoContext, websocketCompressionLevel, websocketPreferredClientNoContext, websocketSubProtocols)"))
    @NotNull
    public static final HttpServerOptions HttpServerOptions(@Nullable Integer acceptBacklog, @Nullable Boolean acceptUnmaskedFrames, @Nullable Iterable<? extends HttpVersion> iterable, @Nullable ClientAuth clientAuth, @Nullable Boolean clientAuthRequired, @Nullable Integer compressionLevel, @Nullable Boolean compressionSupported, @Nullable Iterable<String> iterable2, @Nullable Iterable<? extends Buffer> iterable3, @Nullable Integer decoderInitialBufferSize, @Nullable Boolean decompressionSupported, @Nullable Iterable<String> iterable4, @Nullable Iterable<String> iterable5, @Nullable Boolean handle100ContinueAutomatically, @Nullable String host, @Nullable Integer http2ConnectionWindowSize, @Nullable Integer idleTimeout, @Nullable TimeUnit idleTimeoutUnit, @Nullable Http2Settings initialSettings, @Nullable JdkSSLEngineOptions jdkSslEngineOptions, @Nullable JksOptions keyStoreOptions, @Nullable Boolean logActivity, @Nullable Integer maxChunkSize, @Nullable Integer maxHeaderSize, @Nullable Integer maxInitialLineLength, @Nullable Integer maxWebSocketFrameSize, @Nullable Integer maxWebSocketMessageSize, @Nullable Integer maxWebsocketFrameSize, @Nullable Integer maxWebsocketMessageSize, @Nullable OpenSSLEngineOptions openSslEngineOptions, @Nullable PemKeyCertOptions pemKeyCertOptions, @Nullable PemTrustOptions pemTrustOptions, @Nullable Boolean perFrameWebSocketCompressionSupported, @Nullable Boolean perFrameWebsocketCompressionSupported, @Nullable Boolean perMessageWebSocketCompressionSupported, @Nullable Boolean perMessageWebsocketCompressionSupported, @Nullable PfxOptions pfxKeyCertOptions, @Nullable PfxOptions pfxTrustOptions, @Nullable Integer port, @Nullable Integer receiveBufferSize, @Nullable Boolean reuseAddress, @Nullable Boolean reusePort, @Nullable Integer sendBufferSize, @Nullable Boolean sni, @Nullable Integer soLinger, @Nullable Boolean ssl, @Nullable Long sslHandshakeTimeout, @Nullable TimeUnit sslHandshakeTimeoutUnit, @Nullable Boolean tcpCork, @Nullable Boolean tcpFastOpen, @Nullable Boolean tcpKeepAlive, @Nullable Boolean tcpNoDelay, @Nullable Boolean tcpQuickAck, @Nullable Integer trafficClass, @Nullable JksOptions trustStoreOptions, @Nullable Boolean useAlpn, @Nullable Boolean usePooledBuffers, @Nullable Boolean webSocketAllowServerNoContext, @Nullable Integer webSocketCompressionLevel, @Nullable Boolean webSocketPreferredClientNoContext, @Nullable Iterable<String> iterable6, @Nullable Boolean websocketAllowServerNoContext, @Nullable Integer websocketCompressionLevel, @Nullable Boolean websocketPreferredClientNoContext, @Nullable String websocketSubProtocols) throws NullPointerException {
        HttpServerOptions $this$apply = new HttpServerOptions();
        if (acceptBacklog != null) {
            $this$apply.setAcceptBacklog(acceptBacklog.intValue());
        }
        if (acceptUnmaskedFrames != null) {
            $this$apply.setAcceptUnmaskedFrames(acceptUnmaskedFrames.booleanValue());
        }
        if (iterable != null) {
            $this$apply.setAlpnVersions(CollectionsKt.toList(iterable));
        }
        if (clientAuth != null) {
            $this$apply.setClientAuth(clientAuth);
        }
        if (clientAuthRequired != null) {
            $this$apply.setClientAuthRequired(clientAuthRequired.booleanValue());
        }
        if (compressionLevel != null) {
            $this$apply.setCompressionLevel(compressionLevel.intValue());
        }
        if (compressionSupported != null) {
            $this$apply.setCompressionSupported(compressionSupported.booleanValue());
        }
        if (iterable2 != null) {
            for (String item : iterable2) {
                $this$apply.addCrlPath(item);
            }
        }
        if (iterable3 != null) {
            for (Buffer item2 : iterable3) {
                $this$apply.addCrlValue(item2);
            }
        }
        if (decoderInitialBufferSize != null) {
            $this$apply.setDecoderInitialBufferSize(decoderInitialBufferSize.intValue());
        }
        if (decompressionSupported != null) {
            $this$apply.setDecompressionSupported(decompressionSupported.booleanValue());
        }
        if (iterable4 != null) {
            for (String item3 : iterable4) {
                $this$apply.addEnabledCipherSuite(item3);
            }
        }
        if (iterable5 != null) {
            $this$apply.setEnabledSecureTransportProtocols(CollectionsKt.toSet(iterable5));
        }
        if (handle100ContinueAutomatically != null) {
            $this$apply.setHandle100ContinueAutomatically(handle100ContinueAutomatically.booleanValue());
        }
        if (host != null) {
            $this$apply.setHost(host);
        }
        if (http2ConnectionWindowSize != null) {
            $this$apply.setHttp2ConnectionWindowSize(http2ConnectionWindowSize.intValue());
        }
        if (idleTimeout != null) {
            $this$apply.setIdleTimeout(idleTimeout.intValue());
        }
        if (idleTimeoutUnit != null) {
            $this$apply.setIdleTimeoutUnit(idleTimeoutUnit);
        }
        if (initialSettings != null) {
            $this$apply.setInitialSettings(initialSettings);
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
        if (maxChunkSize != null) {
            $this$apply.setMaxChunkSize(maxChunkSize.intValue());
        }
        if (maxHeaderSize != null) {
            $this$apply.setMaxHeaderSize(maxHeaderSize.intValue());
        }
        if (maxInitialLineLength != null) {
            $this$apply.setMaxInitialLineLength(maxInitialLineLength.intValue());
        }
        if (maxWebSocketFrameSize != null) {
            $this$apply.setMaxWebSocketFrameSize(maxWebSocketFrameSize.intValue());
        }
        if (maxWebSocketMessageSize != null) {
            $this$apply.setMaxWebSocketMessageSize(maxWebSocketMessageSize.intValue());
        }
        if (maxWebsocketFrameSize != null) {
            $this$apply.setMaxWebsocketFrameSize(maxWebsocketFrameSize.intValue());
        }
        if (maxWebsocketMessageSize != null) {
            $this$apply.setMaxWebsocketMessageSize(maxWebsocketMessageSize.intValue());
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
        if (perFrameWebSocketCompressionSupported != null) {
            $this$apply.setPerFrameWebSocketCompressionSupported(perFrameWebSocketCompressionSupported.booleanValue());
        }
        if (perFrameWebsocketCompressionSupported != null) {
            $this$apply.setPerFrameWebsocketCompressionSupported(perFrameWebsocketCompressionSupported.booleanValue());
        }
        if (perMessageWebSocketCompressionSupported != null) {
            $this$apply.setPerMessageWebSocketCompressionSupported(perMessageWebSocketCompressionSupported.booleanValue());
        }
        if (perMessageWebsocketCompressionSupported != null) {
            $this$apply.setPerMessageWebsocketCompressionSupported(perMessageWebsocketCompressionSupported.booleanValue());
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
        if (webSocketAllowServerNoContext != null) {
            $this$apply.setWebSocketAllowServerNoContext(webSocketAllowServerNoContext.booleanValue());
        }
        if (webSocketCompressionLevel != null) {
            $this$apply.setWebSocketCompressionLevel(webSocketCompressionLevel.intValue());
        }
        if (webSocketPreferredClientNoContext != null) {
            $this$apply.setWebSocketPreferredClientNoContext(webSocketPreferredClientNoContext.booleanValue());
        }
        if (iterable6 != null) {
            $this$apply.setWebSocketSubProtocols(CollectionsKt.toList(iterable6));
        }
        if (websocketAllowServerNoContext != null) {
            $this$apply.setWebsocketAllowServerNoContext(websocketAllowServerNoContext.booleanValue());
        }
        if (websocketCompressionLevel != null) {
            $this$apply.setWebsocketCompressionLevel(websocketCompressionLevel.intValue());
        }
        if (websocketPreferredClientNoContext != null) {
            $this$apply.setWebsocketPreferredClientNoContext(websocketPreferredClientNoContext.booleanValue());
        }
        if (websocketSubProtocols != null) {
            $this$apply.setWebsocketSubProtocols(websocketSubProtocols);
        }
        return $this$apply;
    }
}
